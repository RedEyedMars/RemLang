package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class OutputMethod extends OutputContext {

	private String access = "public ";
	private boolean isStatic = false;
	private boolean isAbstract = false;
	private boolean isFinal = false;
	private Outputable header = new Outputable(){
		@Override
		public void output(Consumer<String> builder) {
			builder.accept(access);
			if(isStatic)builder.accept("static ");
			if(isAbstract)builder.accept("abstract ");
			if(isFinal)builder.accept("final ");
		}
		
	};
	private List<Output> templates = null;
	private OutputType type;
	private Output name;
	private OutputParameters parameters = new OutputParameters();
	private Output parametersAsVariable = null;
	private OutputBody body = null;
	private ListOfOutputs<OutputType> throwsExceptions = null;
	private boolean display = true;
	
	private LineableOutput endsWith = null;
	
	private CallableOutput onCallEndsWith = null;
	private Map<Integer,ListOfOutputs<com.rem.lang.helpers.output.OutputType>> templateExtendsType;
	private List<com.rem.lang.helpers.output.Output> templateTypes;

	
	public Stream<? extends Importable> flatStream(){
		Flattenable[] arr = new Flattenable[4+(templateTypes!=null?templateTypes.size():0)];
		arr[0]=type;
		arr[1]=parameters;
		arr[2]=body;
		arr[3]=throwsExceptions;
		if(templateTypes!=null)IntStream.range(0,templateTypes.size()).forEach(I->arr[I+4]=templateExtendsType.get(I));
		return Stream.of(arr).filter(O->O!=null).flatMap(Flattenable::flatStream);
	}

	@Override
	public OutputLine line() {
		if(!display){
			return null;
		}
		OutputLine line = new OutputLine();
		line.variable(header).variables2IfPresent("<",templateTypes,templateExtendsType," extends ","|",",","> ").variable(type).exact(" ").variable(name).variable(parameters).indexedByLambda(throwsExceptions==null?0:throwsExceptions.size(), (L,I)->{
			   if(I==0){
				   L=L.exact("throws ");
			   }
			   else {
				   L=L.exact(", ");
			   }
			   L=L.variable(throwsExceptions.get(I));
			   return L;
		   });
		if(body!=null&&!isAbstract){
			if(endsWith!=null){
				line.exact("{").tabNextLine()
				              .connect(body.line()).connect(endsWith.line()).untabNextLine()
				              .exact("}");
			}
			else {
				line.exact("{").tabNextLine()
	              .connect(body.line()).untabNextLine()
	              .exact("}");
			}
		}
		else {
			line.exact(";");
		}
		return line;
	}

	public OutputMethod set(OutputType type, Output name){
		this.type = type;
		this.name = name;
		return this;
	}
	public OutputMethod parameter(OutputVariable parameter){
		if(this.parameters==null)this.parameters = new OutputParameters();
		this.parameters.add(parameter);
		if(body!=null)body.addVariable(parameter);
		return this;
	}
	public OutputMethod parameters(OutputParameters parameters){
		this.parameters = parameters;
		if(body!=null)body.addVariables(parameters.getVariables());
		return this;
	}
	public OutputMethod parametersAsVariable(Output parameters){
		this.parametersAsVariable = parameters;
		return this;
	}
	public OutputMethod isPublic(){
		this.access = "public ";
		return this;
	}
	public OutputMethod isProtected(){
		this.access = "protected ";
		return this;
	}
	public OutputMethod isPrivate(){
		this.access = "private ";
		return this;
	}
	public OutputMethod access(String access){
		this.access = access;
		return this;
	}
	public OutputMethod isFinal(){
		this.isFinal = true;
		return this;
	}
	public OutputMethod isAbstract(){
		this.isAbstract = true;
		return this;
	}
	public OutputMethod isStatic(){
		this.isStatic = true;
		return this;
	}
	public OutputMethod template(String string) {
		if(this.templateTypes==null){
			this.templateTypes = new ArrayList<Output>();
			this.templateExtendsType = new HashMap<Integer,ListOfOutputs<OutputType>>();
		}
		this.templateTypes.add(new OutputExact(string));
		return this;
	}
	public OutputMethod template(Output output) {
		if(this.templateTypes==null){
			this.templateTypes = new ArrayList<Output>();
			this.templateExtendsType = new HashMap<Integer,ListOfOutputs<OutputType>>();
		}
		this.templateTypes.add(output);
		return this;
	}
	public OutputMethod templateExtends(int index, OutputType output) {
		if(this.templateExtendsType==null){
			this.templateExtendsType = new HashMap<Integer,ListOfOutputs<OutputType>>();
		}
		if(!this.templateExtendsType.containsKey(index)){
			this.templateExtendsType.put(index,new ListOfOutputs<OutputType>());
		}
		this.templateExtendsType.get(index).add(output);
		return this;
	}
	public OutputMethod type(String string) {
		type = new OutputType(new OutputExact(string));
		return this;
	}
	
	public OutputMethod type(OutputType type) {
		this.type = type;
		return this;
	}
	public OutputMethod name(String string) {
		name = new OutputExact(string);
		return this;
	}
	
	public OutputMethod name(OutputType name) {
		this.name = name;
		return this;
	}
	public OutputMethod _throws(String exceptionName){
		if(throwsExceptions==null){
			this.throwsExceptions = new ListOfOutputs<OutputType>();
		}
		this.throwsExceptions.add(new OutputType(exceptionName+"Exception"));
		return this;
	}
	public OutputMethod _throws(OutputType exception){
		if(throwsExceptions==null){
			this.throwsExceptions = new ListOfOutputs<OutputType>();
		}
		this.throwsExceptions.add(exception);
		return this;
	}
	public OutputMethod body(OutputBody newBody){
		this.body = newBody;
		this.body.addVariables(parameters.getVariables());
		return this;
	}
	public OutputMethod add(LineableOutput newLine){
		this.body.add(newLine);
		return this;
	}
	public OutputMethod add(OutputBody newLines){
		this.body.addAll(newLines);
		return this;
	}
	public OutputMethod append(LineableOutput newLine){
		this.body.append(newLine);
		return this;
	}
	public OutputMethod append(OutputBody newLines){
		this.body.append(newLines);
		return this;
	}
	public OutputMethod prepend(LineableOutput newLine){
		this.body.prepend(newLine);
		return this;
	}
	public OutputMethod prepend(OutputBody newLines){
		this.body.prepend(newLines);
		return this;
	}
	public Output getName() {
		return name;
	}
	public OutputType getType() {
		return type;
	}


	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputMethod").add("access","\""+access+"\"");
		if(isStatic)stasis = stasis.add("isStatic");
		if(isAbstract)stasis = stasis.add("isAbstract");
		if(isFinal)stasis = stasis.add("isFinal");
		stasis = stasis.add("set",type,name);
		if(templates!=null)stasis = stasis.addAll("templates",templates);
		if(templateExtendsType!=null){
			IntStream.range(0,templateTypes.size()).boxed().reduce(stasis,(S,I)->{
				if(templateExtendsType.get(I)!=null)S = S.addAll("templateExtends",
						IntStream.range(0,templateExtendsType.get(I).size()).boxed()
						  .map(J->new OutputExact(I).vibrate()).collect(Collectors.toList()),
						templateExtendsType.get(I));
				return S;
			},(S,N)->N);
		}
		if(parametersAsVariable==null)stasis = stasis.add("parameters",parameters);
		else stasis = stasis.asIs("parameters",parametersAsVariable);
		if(throwsExceptions!=null)stasis = stasis.addAll("_throws",throwsExceptions);
		if(body != null){
			stasis = stasis.add("body",body);
		}
		return stasis;
	}

	@Override
	public boolean verify(OutputContext context) {
		return type.verify(context)&&parameters.verify(context)&&(body==null||body.verify(context));
	}

	@Override
	public OutputContext setParent(OutputContext context) {
		if(body!=null)this.body.setParent(context);
		return this.body;
	}

	public void isInterface() {
		if(!isStatic){
			isAbstract = false;
			body = null;
		}
	}

	public String getSignature() {
		List<OutputVariable> variables = parameters.getVariables();
		return name.evaluate()+"("+variables.stream().reduce(new StringBuilder(),(S,V)->{
			if(V!=variables.get(0)){
				S.append(",");
			}
			S.append(V.getType().getTightFullName());
			return S;
		},(S,T)->S)+")";
	}
	
	
	
	
	
	
	
	public Boolean hasVariableInContext(String query){
		if(body!=null){
			return this.body.hasVariableInContext(query);
		}
		else {
			return false;
		}
	}
	public void addVariable(OutputVariable variable) {
		if(body!=null)this.body.addVariable(variable);
	}
	public void addVariables(List<OutputVariable> variables) {
		if(body!=null)this.body.addVariables(variables);
	}
	public OutputVariable getVariableFromContext(String query){
		if(body!=null){
			return this.body.getVariableFromContext(query);
		}
		else {
			return null;
		}
	}
	public OutputClass getVariableClassFromContext(String query){
		if(body!=null){
			return this.body.getVariableClassFromContext(query);
		}
		else {
			return null;
		}
	}

	public Boolean hasMethodInContext(String query){
		if(body!=null){
			return this.body.hasMethodInContext(query);
		}
		else {
			return null;
		}
	}
	public void addMethod(OutputMethod method) {
		if(body!=null){
			this.body.addMethod(method);
		}
	}
	public void addMethods(List<OutputMethod> methods) {
		if(body!=null){
			this.body.addMethods(methods);
		}
	}
	public OutputMethod getMethodFromContext(String query){
		if(body!=null){
			return this.body.getMethodFromContext(query);
		}
		else {
			return null;
		}
	}
	public OutputClass getMethodClassFromContext(String query){
		if(body!=null){
			return this.body.getMethodClassFromContext(query);
		}
		else {
			return null;
		}
	}

	public void display(boolean display){
		this.display  = display;
	}

	public void setEndsWith(LineableOutput line){
		this.endsWith = line;
	}
	public void setEndWith(LineableOutput line){
		this.endsWith = line;
	}
	
	public void setOnCallEndsWith(CallableOutput call){
		this.onCallEndsWith = call;
	}
	public CallableOutput getOnCallEndsWith(){
		return this.onCallEndsWith;
	}
}
