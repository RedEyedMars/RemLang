package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class OutputString extends Output {

	private OutputContext context = null;
	private List<Output> outputs = new ArrayList<Output>();
	public OutputString(){
	}
	public OutputString set(Output output){
		this.outputs.add(output);
		return this;
	}
	public OutputString set(Object output){
		this.outputs.add(new OutputExact(output.toString()));
		return this;
	}
	public OutputString set(int i){
		this.outputs.add(new OutputExact(i));
		return this;
	}
	public OutputString set(double d){
		this.outputs.add(new OutputExact(Double.toString(d)));
		return this;
	}
	public OutputString set(float f){
		this.outputs.add(new OutputExact(new Float(f).toString()));
		return this;
	}
	public OutputString set(short s){
		this.outputs.add(new OutputExact(new Short(s).toString()));
		return this;
	}
	public OutputString set(long l){
		this.outputs.add(new OutputExact(new Long(l).toString()));
		return this;
	}
	public OutputString set(byte b){
		this.outputs.add(new OutputExact(new Byte(b).toString()));
		return this;
	}
	public OutputString set(boolean b){
		this.outputs.add(new OutputExact(new Boolean(b).toString()));
		return this;
	}
	public OutputString set(char c){
		this.outputs.add(new OutputExact(new Character(c).toString()));
		return this;
	}
	public OutputString set(OutputConcatenation output,OutputContext context){
		this.outputs = output.getOutputs();
		this.context = context;
		return this;
	}
	public OutputString set(Output output,OutputContext context){
		if(output instanceof OutputConcatenation){
			return set((OutputConcatenation)output,context);
		}
		else {
			this.outputs.add( output);
			this.context = context;
			return this;
		}
	}
	public OutputString set(String value,OutputContext context){
		return set(new OutputExact(value),context);
	}
	private void update(){
		if(context!=null){
			outputs = outputs.stream().map(
					O->{
						if(context.hasVariableInContext(O.evaluate())){
							OutputVariable variable = context.getVariableFromContext(O.evaluate());
							if(variable.getType().evaluate().equals("double")||
									variable.getType().evaluate().equals("float")||
									variable.getType().evaluate().equals("short")||
									variable.getType().evaluate().equals("long")||
									variable.getType().evaluate().equals("byte")||
									variable.getType().evaluate().equals("boolean")){
								return (Output)new OutputNewObject().set(new OutputType(new OutputCamelized(variable.getType())), new OutputArguments().add(O));
							}
							else if(variable.getType().evaluate().equals("int")){
								return (Output)new OutputNewObject().set(new OutputType("Integer"), new OutputArguments().add(O));
							}
						}
						return O;
					}).collect(Collectors.toList());
			
			context = null;
		}
	}
	@Override
	public void output(Consumer<String> builder) {

		update();
		builder.accept("(");
		IntStream.range(0,outputs.size()).forEach(I->{if(I>0)builder.accept("+");outputs.get(I).output(builder);});
		builder.accept(").toString()");
	}
	@Override
	public Output stasis() {
		update();
		StringBuilder valueBuilder = new StringBuilder();
		valueBuilder.append("(");
		IntStream.range(0,outputs.size()).forEach(I->{if(I>0)valueBuilder.append("+");outputs.get(I).output(valueBuilder::append);});
		StringBuilder builder = new StringBuilder();
		builder.append("new "+OutputStasis.outputHelperPackageName+".OutputExact");
		builder.append(valueBuilder.toString());
		builder.append(")");
		valueBuilder.append(").toString()");
		this.value = valueBuilder.toString();
		return new OutputExact(builder.toString());
	}
	@Override
	public boolean verify(OutputContext context) {
		return true;
	}

	public Stream<? extends Importable> flatStream(){
		return Stream.empty();
	}
	public Output getOutput() {
		StringBuilder builder = new StringBuilder();
		outputs.forEach(O->O.add(builder::append));
		return new OutputExact(builder.toString());
	}

}
