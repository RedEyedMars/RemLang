package com.rem.lang.helpers.output;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class OutputLambda extends Output {

	private List<OutputVariable> variables = new ArrayList<OutputVariable>();
	private Output call = null;
	private OutputBody body = null;
	
	public OutputLambda declare(String variable){
		this.variables.add(new OutputVariable(OutputType.Any,new OutputExact(variable)));
		return this;
	}
	public OutputLambda declare(Output output){
		this.variables.add(new OutputVariable(OutputType.Any,output));
		return this;
	}
	public OutputLambda declare(OutputVariable variable){
		this.variables.add(variable);
		return this;
	}
	public OutputLambda add(LineableOutput output){
		if(this.body==null){
			this.body = new OutputBody();
		}
		this.body.add(output);
		return this;
	}
	public OutputLambda body(OutputBody body){
		this.body = body;
		return this;
	}
	public OutputLambda call(Output call){
		this.call = call;
		return this;
	}
	@Override
	public void output(Consumer<String> builder) {
		if(variables.size()==0||variables.size()>1){
			builder.accept("(");
			IntStream.range(0,variables.size()).forEach(I->{if(I>0)builder.accept(",");variables.get(I).getName().output(builder);});
			builder.accept(")->");
		}
		else {
			variables.get(0).output(builder);
			builder.accept("->");
		}
		if(call!=null){
			call.output(builder);
		}
		else if(body!=null){
			builder.accept("{");
			body.line().output(6, builder);
			builder.accept(" }");
		}
	}

	@Override
	public Output stasis() {
		OutputStasis stasis = new OutputStasis().name("OutputLambda").addAll("declare", variables);
		if(body!=null)stasis = stasis.add("body",body);
		else if(call!=null)stasis = stasis.add("call",call);
		return stasis;
	}

	@Override
	public boolean verify(OutputContext context) {
		return true;
	}
	public Stream<? extends Importable> flatStream(){
		return body!=null?
				call!=null?Stream.concat(body.flatStream(),call.flatStream())
						  :body.flatStream()
				:call!=null?call.flatStream()
						:Stream.empty();
	}
	

}
