package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class OutputLine extends ArrayList<Outputable>{
	private static final long serialVersionUID = 8295524977241516949L;
	private OutputLine next;
	private int nextTabs = 0;
	private boolean isBlank = false;

	@SuppressWarnings("unchecked")
	public void output(int tabs, Consumer<String> builder){
		Supplier<Supplier<?>> current = get(tabs,builder);
		while(current!=null){
			current = (Supplier<Supplier<?>>) current.get();
		}
	}
	private Supplier<Supplier<?>> get(int tabs, Consumer<String> builder) {
		if(!isBlank&&!isEmpty()){
			IntStream.range(0,tabs).forEach(I->builder.accept("\t"));
			this.stream().forEach(O->{if(O!=null)O.output(builder);});
			builder.accept("\n");
		}
		return next!=null?()->next.get(tabs+nextTabs,builder):null;
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
	public <T  extends Output > OutputLine allExcept(String prefix, List<T> list, String suffix, Predicate<T> predicate) {
		return list.stream().reduce(this,(L,O)->{
			if(!predicate.test(O)){
				return L.exact(prefix).variable(O).exact(suffix).nextLine();
			}
			else {
				return L;
			}
		},(L,N)->N);
	}
	public OutputLine each(List<? extends Lineable> outputs) {
		return outputs.stream().reduce(this,(L,O)->L.connect(O.line()),(L,N)->N);
	}
	public OutputLine eachNonNull(List<? extends Lineable> outputs) {
		return outputs.stream().reduce(this,(L,O)->{if(O!=null&&O.line()!=null)return L.connect(O.line());else return L;},(L,N)->N);
	}

	public OutputLine all(List<? extends Output> outputs) {
		return outputs.stream().reduce(this,(L,O)->L.variable(O).nextLine(),(L,N)->N);
	}
	public OutputLine indexedByLambda(int limit,BiFunction<OutputLine,Integer,OutputLine> lambda){
		return IntStream.range(0, limit).boxed().reduce(this, lambda,(L,N)->N);
	}

	public OutputLine variablesIfPresent(String prefix, List<? extends Output> outputs, String suffix){
		if(outputs==null||outputs.isEmpty()){
			return this;
		}
		else {
			return outputs.stream().reduce(this.exact(prefix),(L,O)->L.variable(O),(L,N)->N).exact(suffix);
		}
	}
	public OutputLine variablesIfPresent(String prefix, List<? extends Output> outputs, String delimiter, String suffix){
		if(outputs==null||outputs.isEmpty()){
			return this;
		}
		else {
			return IntStream.range(0,outputs.size()).boxed().reduce(
					this.exact(prefix),
					(L,O)->{
						if(O>0){
							L = L.exact(delimiter);
						}
						return L.variable(outputs.get(O));
					},(L,N)->N).exact(suffix);
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
	public <T extends Output,U extends List<W>,W extends Output> OutputLine variables2IfPresent(String prefix, List<T> outputs, Map<Integer, U> extras, String onNotNull, String pipe, String topDelimit, String suffix){
		if(outputs==null||outputs.isEmpty()){
			return this;
		}
		else {
			return IntStream.range(0,outputs.size()).boxed().reduce(this.exact(prefix),(L,I)->
			{
				if(I>0)L=L.exact(topDelimit);
				L=L.variable(outputs.get(I));
				if(extras.get(I)!=null){
					L=L.exact(onNotNull);
					L = IntStream.range(0, extras.get(I).size()).boxed().reduce(L,(M,J)->{if(J>0)M=M.exact(pipe);return M.variable(extras.get(I).get(J));},(M,N)->N);
				}
				return L;
				},(L,N)->N).exact(suffix);
		}
	}
	public OutputLine blank() {
		this.isBlank  = true;
		return this;
	}

}
