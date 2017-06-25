package com.rem.parser.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseUtil;
import com.rem.parser.generation.Generator;
import com.rem.parser.parallelism.JobCreator;
import com.rem.parser.parallelism.ParallelJob;
import com.rem.parser.token.IToken;
import com.rem.parser.token.NewFileBranchToken;

public class ImportParser extends ConcreteParser{


	private IParser subParser;
	private String listName;
	private Pattern open;
	private Pattern close;
	private List<String> tokenNames = new ArrayList<String>();
	private IParser startParser;

	public ImportParser(IParser initialParser, String name, String listName, String parameters){
		subParser = initialParser;
		this.name = name;
		this.listName = listName;
		this.startParser = null;
		this.setup(parameters);
	}
	public ImportParser(IParser initialParser, String name, String listName, String parameters, IParser initialStartParser){
		subParser = initialParser;
		this.name = name;
		this.listName = listName;
		this.startParser = initialStartParser;
		this.setup(parameters);
	}

	private void setup(String parameters) {
		int left = parameters.indexOf("<<");
		int right = parameters.indexOf(">>",left);
		if(left>-1&&right>-1){
			open = Pattern.compile("("+parameters.substring(0,left)+"[ \\t]*).*",Pattern.DOTALL);
			close = Pattern.compile("("+parameters.substring(right+2)+").*",Pattern.DOTALL);
			String rest = parameters.substring(left+2,right);
			left = 0;
			int commaIndex = rest.indexOf(',');
			while(commaIndex>-1){
				tokenNames.add(rest.substring(left,commaIndex));
				left = commaIndex+1;
				commaIndex = rest.indexOf(',',commaIndex+1);
			}
			tokenNames.add(rest.substring(left));
		}
		else {
			throw new RuntimeException(parameters+" does not follow the template OPEN<<TOKENNAME>>CLOSE");
		}
	}

	@Override
	public void real_parse(final ParseContext data) {
		String toExamine = data.get();
		Matcher openMatcher = open.matcher(toExamine);
		if(openMatcher.matches()){
			int position = data.getFrontPosition();
			data.setFrontPosition(data.getFrontPosition()+openMatcher.group(1).length());
			IToken token = data.addTokenLayer();
			subParser.parse(data);
			data.collectTokens();
			if(data.isValid()){
				StringBuilder fileNameBuilder = new StringBuilder();
				boolean canFindFileName = true;
				for(int i=0;i<tokenNames.size();++i){
					if(tokenNames.get(i).startsWith("\"")){
						fileNameBuilder.append(tokenNames.get(i).substring(1, tokenNames.get(i).length()-1));
					}
					else if(token.containsKey(tokenNames.get(i))){
						fileNameBuilder.append(token.get(tokenNames.get(i)).getString());
					}
					else {
						canFindFileName = false;
						break;
					}
				}
				if(canFindFileName){
					final String fileName = fileNameBuilder.toString();
					final File file = new File(data.getDirectory(),fileName);
					if(file.exists()){
						final Matcher closeMatcher = close.matcher(data.get());
						if(closeMatcher.matches()){
							final IToken currentToken = data.getToken();
							final int parentPosition = data.getFrontPosition();
							final ParseContext newContext = data.getContextFromFile(fileName,file);
							data.addDoneDependency(newContext);
							data.validate();							
							data.setFrontPosition(data.getFrontPosition()+closeMatcher.group(1).length());						

							final IToken newRoot = newContext.addTokenLayer(new NewFileBranchToken(newContext));						
							currentToken.put(new IToken.Key(name,-1,parentPosition), newRoot);
							newRoot.setList(listName);
							newRoot.setName(name);
							JobCreator.add(new ParallelJob(){
								@Override
								public void act(){
									try {
									newContext.setBackPosition(-1);
									newContext.setFrontPosition(0);
									if(startParser==null){
										newContext.getRootParser().parse(newContext);
									}
									else {
										startParser.parse(newContext);
									}
									newContext.collectTokens();

									if(!newContext.isDone()){
										System.err.println(newContext+":"+fileName+" error at("+newContext.getFurthestPosition()+","+newContext.getLineNumber(newContext.getFurthestPosition())+
												"):"+
												newContext.get().substring(0,newContext.getFurthestPosition()-newContext.getFrontPosition())+
												"$>"+
												newContext.get().substring(newContext.getFurthestPosition()-newContext.getFrontPosition()));

										newContext.setFrontPosition(0);	
									}
									}
									catch(Exception e){
										System.err.println(newContext.getFileName()+":"+newContext.getLineNumber(newContext.getFrontPosition()));
										BufferedWriter writer;
										try {
											writer = new BufferedWriter(new FileWriter("log.log"));

											writer.append(newContext.getFileName()+">>"+Generator.completeTokenErrorMessage(newContext.getRoot())+"<<");
											writer.close();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										throw new RuntimeException(e);
									}
								}
							});
							return;
						}
						else {
							data.invalidate();
							data.setFrontPosition(position);
						}
					}
					else {
						System.err.println("File name:"+data.getDirectory()+":"+fileName+" not found!");
						data.invalidate();
						data.setFrontPosition(position);
					}
				}
			}
			data.invalidate();
			data.setFrontPosition(position);
		}
		else {
			data.invalidate();
		}
	}

}
