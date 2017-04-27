package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class ListNameElementParser extends ConcreteParser{

	private String listName;

	public ListNameElementParser(String listName){
		this.listName = listName;
	}

	@Override
	public void real_parse(ParseContext data) {

		if(NameParser.lazyParser!=null){
			synchronized(NameParser.lazyParser){
				String lazyName = NameParser.lazyParser.getName();
				((RegexParser)NameParser.lazyParser).setName(listName);
				NameParser.lazyParser.parse(data);
				((RegexParser)NameParser.lazyParser).setName(lazyName);
			}		
		}
		else {
			if(data.getList(listName)!=null){
				if(data.getFileName().contains("gentestx")&&listName.equals("entry_class_names")){

					System.out.println(data.getLine()+":"+data.getLineNumber(data.getFrontPosition())+":"+listName+":"+data);
					NameParser names = data.getList(listName).getNamesParser();

					while(names!=null){
						System.out.println("\t"+names+":"+names.getPattern());
						ParseContext parent = names.getParent();
						if(parent!=null){
							names = parent.getList(listName).getNamesParser();
						}
						else {
							break;
						}
					}
				}
				data.getList(listName).getNamesParser().parse(data);	
			}

			else {
				System.err.println(data.getLine());
				throw new RuntimeException("ListNameParser:"+listName+"is not recognized listName");
			}
		}
	}

}
