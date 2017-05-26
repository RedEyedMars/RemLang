package com.rem.parser.parser;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rem.parser.ParseContext;
import com.rem.parser.ParseUtil;
import com.rem.parser.parallelism.JobCreator;
import com.rem.parser.parallelism.ParallelJob;
import com.rem.parser.token.IToken;
import com.rem.parser.token.NewFileBranchToken;

public class ImportParser extends ConcreteParser{


	private IParser subParser;
	private String listName;
	private Pattern open;
	private Pattern close;
	private String tokenName;
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
			tokenName = parameters.substring(left+2,right);
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
			if(token.containsKey(tokenName)){
				final String fileName = token.get(tokenName).getString();
				final File file = new File(fileName);
				if(file.exists()){
					final Matcher closeMatcher = close.matcher(data.get());
					if(closeMatcher.matches()){
						final IToken currentToken = data.getToken();
						final int parentPosition = data.getFrontPosition();
						data.setFrontPosition(position+1);
						final ParseContext newContext = data.getContextFromPosition();
						data.setFrontPosition(parentPosition);
						if(!newContext.getFileName().equals(fileName)){
							newContext.setFileName(fileName);
							newContext.setFile(ParseUtil.getString(file));
						}
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
						});
						return;
					}
					else {
						data.invalidate();
						data.setFrontPosition(position);
					}
				}
				else {
					System.err.println("File name:"+fileName+" not found!");
					data.invalidate();
					data.setFrontPosition(position);
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
