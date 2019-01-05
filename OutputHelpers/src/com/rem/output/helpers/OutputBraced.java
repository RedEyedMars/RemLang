package com.rem.output.helpers;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputBraced  extends Output {

	private Output subject = null;
	private String open = "(";
	private String close = ")";
	public OutputBraced(){
	}
	public OutputBraced(Output other){
		set(other);
	}
	public OutputBraced set(Output subject){
		this.subject = subject;
		return this;
	}
	public Stream<? extends Importable> flatStream(){
		return subject!=null?subject.flatStream():Stream.empty();
	}
	@Override
	public void output(Consumer<String> builder) {
		builder.accept(open);
		if(subject!=null){
			subject.add(builder);
		}
		builder.accept(close);
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputBraced");
		if(subject!=null){
			stasis = stasis.add("set",subject);
		}
		if(!open.equals("(")){
			stasis = stasis.add("style","\""+open+"\"","\""+close+"\"");
		}
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return (subject==null||subject.verify(context));
	}
	public OutputBraced style(String open, String close) {
		this.open = open;
		this.close = close;
		return this;
	}
}
