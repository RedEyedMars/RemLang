package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OutputCall  extends CallableOutput {
	private List<Output> subjects = new ArrayList<Output>();
	private List<OutputArguments> arguments = new ArrayList<OutputArguments>();
	public OutputCall(){
	}
	public OutputCall(OutputCall other){
		addCall(other);
	}
	@Override
	protected List<Output> subjects(){
		return subjects;
	}
	@Override
	protected List<OutputArguments> arguments(){
		return arguments;
	}
	@Override
	public CallableOutput addCall(CallableOutput call){
		subjects.addAll(call.subjects());
		arguments.addAll(call.arguments());
		return this;
	}
	public CallableOutput array(Output subject){
		if(arguments.get(arguments.size()-1)==null){
			arguments.set(arguments.size()-1,new OutputArrayOnlyArguments().add(subject));
		}
		else {
			arguments.get(arguments.size()-1).array(subject);
		}
		return this;
	}
	public CallableOutput add(Output subject){
		this.subjects.add(subject);
		this.arguments.add(null);
		return this;
	}
	public CallableOutput add(Output subject, OutputArguments arguments){
		this.subjects.add(subject);
		this.arguments.add(arguments);
		return this;
	}
	public void getImports(Set<String> imports) {
		subjects.parallelStream().forEach(S->{if(S!=null)S.getImports(imports);});
		arguments.parallelStream().forEach(A->{if(A!=null)A.getImports(imports);});
	}
	@Override
	public void output(Consumer<String> builder) {
		IntStream.range(0,subjects.size()).forEach(I->{
			if(I>0)builder.accept(".");
			if(subjects.get(I)!=null)subjects.get(I).add(builder);
			if(arguments.get(I)!=null)arguments.get(I).add(builder);});
	}
	@Override
	public Output stasis() {
		if(subjects.size()==1&&arguments.get(0)==null)return subjects.get(0).stasis();
		return new OutputStasis().name("OutputCall").addAll("add", subjects, arguments);
	}
	@Override
	public boolean verify(OutputContext context) {
		return IntStream.range(0, subjects.size()).boxed().reduce(
				context,(C,I)->C==null?null:arguments.get(I)==null?
						C.getVariableClassFromContext(subjects.get(I).evaluate()):
						(C.hasMethodInContext(subjects.get(I).evaluate())&&arguments.get(I).verify(context)?
								C.getMethodClassFromContext(subjects.get(I).evaluate()):null),(C,D)->D)!=null;
	}
	
}
