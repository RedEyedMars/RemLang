package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public class PrintParser extends ConcreteParser implements IParser{

	private String prephrase;
	private String phrase;
	private IParser post;
	private IParser pre;

	public PrintParser(String phrase){
		this.phrase = phrase;
	}
	public PrintParser(String phrase, IParser post){
		this.phrase = phrase;
		this.post = post;
	}
	public PrintParser(IParser pre,String phrase){
		this.phrase = phrase;
		this.pre = pre;
	}
	public PrintParser(IParser pre,String phrase, IParser post){
		this.phrase = phrase;
		this.pre = pre;
		this.post = post;
	}
	public PrintParser(String pre,IParser parser, String phrase){
		this.prephrase = pre;
		this.pre = parser;
		this.phrase = phrase;
	}
	
	@Override
	public void real_parse(ParseContext data) {
		if(prephrase!=null)
			System.out.println(prephrase+":"+data.getFileName()+"("+data.getLineNumber(data.getFrontPosition())+")");
		if(pre!=null)pre.parse(data);
		System.out.println(phrase+":"+data.getFileName()+"("+data.getLineNumber(data.getFrontPosition())+")");
		if(post!=null)post.parse(data);
	}

}
