package com.rem.output.helpers;

import java.util.Set;
import java.util.function.Consumer;

public class OutputOperator  extends Output {

	private Output left = null;
	private Output right = null;
	private String operator = "";

	public OutputOperator(){
	}
	public OutputOperator(Output left){
		this.left = left;
	}
	public OutputOperator(String operator){
		this.operator = operator;
	}
	public OutputOperator(Output left, String operator, Output right){
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	public OutputOperator operator(String operator){
		this.operator = operator;
		return this;
	}
	public OutputOperator left(Output subject){
		this.left = subject;
		return this;
	}
	public OutputOperator right(Output subject){
		this.right = subject;
		return this;
	}
	public void getImports(Set<String> imports) {
		if(left != null){
			left.getImports(imports);
		}
		if(right != null){
			right.getImports(imports);
		}
	}
	@Override
	public void output(Consumer<String> builder) {
		if(left!=null)left.add(builder);
		builder.accept(operator);
		if(right != null)right.add(builder);
	}
	@Override
	public Output stasis() {
		if(left!=null&&operator.equals(""))return left.stasis();
		OutputStasis stasis = new OutputStasis().name("OutputOperator");
		if(left!=null)stasis = stasis.add("left",left);
		if(!operator.equals(""))stasis = stasis.add("operator","\""+operator+"\"");
		if(right!=null)stasis = stasis.add("right",right);
		return stasis;
	}
	@Override
	public boolean verify(OutputContext context) {
		return (left==null||left.verify(context)) && (right==null||right.verify(context));
	}
}
