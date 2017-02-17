package com.rem.parser;

public abstract class Argument <ValueType extends Object> {

	public abstract ValueType evaluate();	
	
	public static abstract class ArthmaticArgument extends Argument<Integer> {

		private Argument<Integer> left;
		private Argument<Integer> right;

		public ArthmaticArgument(Argument<Integer> left, Argument<Integer> right){
			this.left = left;
			this.right = right;
		}
		
		public abstract Integer operate(Integer a, Integer b);
		
		@Override
		public Integer evaluate() {
			return operate(left.evaluate(),right.evaluate());
		}		
	}
	
	public static class Multiply extends ArthmaticArgument {
		public Multiply(Argument<Integer> left, Argument<Integer> right) {
			super(left, right);
		}
		@Override
		public Integer operate(Integer a, Integer b) {
			return a*b;
		}		
	}
	public static class Divide extends ArthmaticArgument {
		public Divide(Argument<Integer> left, Argument<Integer> right) {
			super(left, right);
		}
		@Override
		public Integer operate(Integer a, Integer b) {
			return a/b;
		}		
	}
	public static class Add extends ArthmaticArgument {
		public Add(Argument<Integer> left, Argument<Integer> right) {
			super(left, right);
		}
		@Override
		public Integer operate(Integer a, Integer b) {
			return a+b;
		}		
	}
	public static class Subtract extends ArthmaticArgument {
		public Subtract(Argument<Integer> left, Argument<Integer> right) {
			super(left, right);
		}
		@Override
		public Integer operate(Integer a, Integer b) {
			return a-b;
		}		
	}
}
