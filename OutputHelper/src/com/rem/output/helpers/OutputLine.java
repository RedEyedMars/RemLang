package com.rem.output.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class OutputLine extends ArrayList<Outputable>{
	private static final long serialVersionUID = 8295524977241516949L;
	private OutputLine next;
	private int nextTabs = 0;
	private boolean isBlank = false;

	public void output(int tabs, Consumer<String> builder) {
		if(!isBlank&&!isEmpty()){
			IntStream.range(0,tabs).forEach(I->builder.accept("\t"));
			this.stream().forEach(O->{if(O!=null)O.output(builder);});
			builder.accept("\n");
		}
		if(next!=null){
			next.output(tabs+nextTabs, builder);
		}
	}

	public OutputLine exact(Object value){
		add(new OutputExact(value.toString()));
		return this;
	}

	public OutputLine exact(int value){
		add(new OutputExact(new Integer(value).toString()));
		return this;
	}
	public OutputLine exact(double value){
		add(new OutputExact(new Double(value).toString()));
		return this;
	}
	public OutputLine exact(long value){
		add(new OutputExact(new Long(value).toString()));
		return this;
	}

	public OutputLine exact(byte value){
		add(new OutputExact(new Byte(value).toString()));
		return this;
	}
	public OutputLine exact(short value){
		add(new OutputExact(new Short(value).toString()));
		return this;
	}
	public OutputLine exact(boolean value){
		add(new OutputExact(new Boolean(value).toString()));
		return this;
	}

	public OutputLine exact(float value){
		add(new OutputExact(new Float(value).toString()));
		return this;
	}
	public OutputLine exact(char value){
		add(new OutputExact(new Character(value).toString()));
		return this;
	}

	public OutputLine connect(OutputLine next){
		this.next = next;
		OutputLine last = next;
		while(last.next!=null){
			last = last.next;
		}
		return last;
	}
	public OutputLine tab(OutputLine next){
		next = this;
		this.nextTabs = 1;
		this.next = next;
		return next;
	}
	public OutputLine variable(Output var){
		add(var);
		return this;
	}
	public OutputLine variable(Outputable var){
		add(var);
		return this;
	}

	public OutputLine nextLine(){
		OutputLine next = new OutputLine();
		this.nextTabs = 0;
		this.next = next;
		return next;
	}
	public OutputLine tabNextLine(){
		OutputLine next = new OutputLine();
		this.nextTabs = 1;
		this.next = next;
		return next;
	}
	public OutputLine untabNextLine(){
		OutputLine next = new OutputLine();
		this.nextTabs = -1;
		this.next = next;
		return next;
	}

	public OutputLine all(String prefix, List<? extends Output> list, String suffix) {

		
		return list.stream().reduce(this,(L,O)->L.exact(prefix).variable(O).exact(suffix).nextLine(),(L,N)->N);
	}
	public OutputLine each(List<? extends Lineable> outputs) {
		return outputs.stream().reduce(this,(L,O)->L.connect(O.line()),(L,N)->N);
	}

	public OutputLine all(List<? extends Output> outputs) {
		return outputs.stream().reduce(this,(L,O)->L.variable(O).nextLine(),(L,N)->N);
	}

	public OutputLine variablesIfPresent(String prefix, List<? extends Output> outputs, String suffix){
		if(outputs.isEmpty()){
			return this;
		}
		else {
			return outputs.stream().reduce(this.exact(prefix),(L,O)->L.variable(O),(L,N)->N).exact(suffix);
		}
	}

	public <T extends Output> OutputLine variableIfPresent(String prefix, T variable, String suffix) {
		if(variable==null){
			return this;
		}
		else {
			return this.exact(prefix).variable(variable).exact(suffix);
		}
	}
	public OutputLine blank() {
		this.isBlank  = true;
		return this;
	}

}
