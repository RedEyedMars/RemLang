package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class OutputCast  extends Output {

	private List<OutputType> types = new ArrayList<OutputType>();
	private Output subject = null;
	public OutputCast(){
	}
	public OutputCast(OutputType other){
		set(other);
	}
	public OutputCast set(OutputType type){
		this.types.add(type);
		return this;
	}
	public OutputCast type(OutputType type){
		this.types.add(type);
		return this;
	}
	public OutputCast subject(Output subject){
		this.subject = subject;
		return this;
	}
	public void getImports(Set<String> imports) {
		types.forEach(T->T.getImports(imports));
		if(subject != null){
			subject.getImports(imports);
		}
	}
	@Override
	public void output(Consumer<String> builder) {
		types.forEach(T->{builder.accept("(");T.output(builder);builder.accept(")");});
		if(subject!=null)subject.add(builder);
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputOperator").addAll("type", types);
		if(subject!=null)stasis = stasis.add("subject",subject);
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return types.parallelStream().allMatch(T->T.verify(context))&&(subject==null||subject.verify(context));
	}
}
