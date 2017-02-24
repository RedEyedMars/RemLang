package base;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rem.parser.Generator;
import com.rem.parser.IParser;
import com.rem.parser.IToken;
import com.rem.parser.ParseData;
import com.rem.parser.ParseList;
import com.rem.parser.RegexParser;

import base.lists.Listnames;
import base.lists.Tokens;

public class GeneratorGenerator extends Generator {

	private File directory;
	private String[] outline = new String[]{
			"package gen;\n\n"+
			"import java.io.*;\n"+
			"import java.util.*;\n"+
			"import com.rem.parser;\n\n"+
			"public class ",/*Class Name*/" extends Generator {\n",/*Contents*/ "\n}"};

	
	public GeneratorGenerator(){
		addElement("outline",outline);
	}
	
	@Override
	public void generateRoot(IToken root){
		
		for(IToken.Key key:root.keySet()){
			System.out.println(key.getName());
		}
	}

	@Override
	public IParser getLazyNameParser(){
		return Tokens.NAME;
	}
	
	public String getName(){
		return "gen";
	}
	
	@Override
	protected void generate(ParseData data) {
		String fileName = data.getFileName();
		int indexOfDot = fileName.lastIndexOf('.');
		if(indexOfDot>-1)fileName = fileName.substring(0, indexOfDot);
		directory = new File("../Generated"+camelize(fileName)+"/src/gen/");
		directory.mkdirs();

		generateAll(this,((ParseList)data.getList("class_definitions")).getNewTokens(),"class_def");
		outputAll();

	}

	@Override
	public void assignListElementNames(Map<String,ParseList> listMap, IToken root){		
		for(IParser parser:Listnames.parser){
			String pattern = ((RegexParser)parser).getPattern();
			String listName = pattern.substring(1, pattern.length()-4);
			if(!listMap.containsKey(listName)){
				listMap.put(listName, ParseList.createNew(listName));
			}
		}
	}
}
