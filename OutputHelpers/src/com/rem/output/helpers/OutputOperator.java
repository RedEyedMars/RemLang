package com.rem.output.helpers;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class OutputOperator  extends Output {

	private Output left = null;
	private Output right = null;
	private String operator = "";
	private boolean operatorlessIfSingle = false;

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
	public OutputOperator add(Output subject){
		if(left==null)return left(subject);
		else if(right==null)return right(subject);
		else return new OutputOperator().left(this).operator(operator).right(subject);
	}
	public OutputOperator operatorlessIfSingle(){
		this.operatorlessIfSingle  = true;
		return this;
	}
	public Stream<? extends Importable> flatStream(){
		return left!=null?right!=null?Stream.concat(left.flatStream(),right.flatStream()):left.flatStream():right!=null?right.flatStream():Stream.empty();
	}
	@Override
	public void output(Consumer<String> builder) {
		if(left!=null)left.add(builder);

		if(right != null){
			builder.accept(operator);
			right.add(builder);
		}
		else if(!operatorlessIfSingle) {
			builder.accept(operator);
		}
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
	
	public String operator(){
		return operator;
	}
	public boolean isSingle() {
		return left!=null&&right==null&&operator.equals("");
	}
	public Output getLeft(){
		return left;
	}
	public String getOperator() {
		return operator;
	}
	public Output getRight(){
		return right;
	}
}
