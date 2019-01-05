package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

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

	@Override
	public Stream<? extends Importable> flatStream(){
		return subject!=null
			   ?Stream.concat(types.stream().flatMap(Flattenable::flatStream),subject.flatStream())
			   :types.stream().flatMap(Flattenable::flatStream);
	}
	@Override
	public void output(Consumer<String> builder) {
		types.forEach(T->{builder.accept("(");T.output(builder);builder.accept(")");});
		if(subject!=null)subject.add(builder);
	}
	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputCast").addAll("type", types);
		if(subject!=null)stasis = stasis.add("subject",subject);
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return types.parallelStream().allMatch(T->T.verify(context))&&(subject==null||subject.verify(context));
	}
}
