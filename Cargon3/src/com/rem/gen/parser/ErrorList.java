package com.rem.gen.parser;
import com.rem.gen.parser.ErrorList;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.nio.CharBuffer;
import com.rem.gen.parser.ParseError;
import java.util.List;
import java.util.stream.IntStream;
public class ErrorList {
	protected List<ParseError> children = new ArrayList<ParseError>();
	protected ParseError error = null;
	public List<ParseError> getChildren(){
		return children;
	}
	public ParseError getError(){
		return error;
	}
	public ErrorList (){
	}
	public ErrorList (ParseError error){
		this.error=error;
	}
	public void adjust(ParseError newError){
		if(error==null||newError.getPosition()>error.getPosition()){
			error=newError;
		}
	}
	public void merge(ErrorList errors){
		children.addAll(errors.children);
		if(errors.error!=null){
			adjust(errors.error);
		}
	}
	public void append(ParseError errors){
		children.add(errors);
	}
	public boolean isEmpty(){
		return error==null&&children.isEmpty();
	}
	public String toString(CharBuffer chars){
		StringBuilder builder = new StringBuilder();
		List<Integer> lines = new ArrayList<Integer>();
		CharBuffer liner = chars.duplicate();
		IntStream.range(0,chars.limit()).forEach( I->{						if(liner.get()=='\n'){
							lines.add(I);
						}
 });
		toString(builder::append,lines,chars);
		return builder.toString();
	}
	public void toString(Consumer<String> builder,List<Integer> lines,CharBuffer chars){
		children.forEach( C->C.toString(builder,lines,chars));
		if(error!=null){
			error.toString(builder,lines,chars);
		}
	}
	public static class Dummy extends ErrorList {
		public Dummy (){
		}
		public void adjust(ParseError newError){
		}
		public void merge(ErrorList errors){
		}
		public void append(ErrorList errors){
		}
		public boolean isEmpty(){
			return true;
		}
		public String toString(CharBuffer chars){
			return "";
		}
		public void toString(Consumer<String> builder,List<Integer> lines,CharBuffer chars){
		}
	}
}
