package com.rem.parser.parser;

import com.rem.parser.ParseContext;

public abstract class Argument <ValueType extends Object> {

	public abstract ValueType evaluate(ParseContext context);
	

	public static class Number extends Argument<Integer> {
		private int i;
		public Number(Integer i){
			this.i = i;
		}

		@Override
		public Integer evaluate(ParseContext context) {
			return i;
		}
	}
	public static abstract class ArthmaticArgument extends Argument<Integer> {

		private Argument<Integer> left;
		private Argument<Integer> right;

		public ArthmaticArgument(Argument<Integer> left, Argument<Integer> right){
			this.left = left;
			this.right = right;
		}
		
		public abstract Integer operate(Integer a, Integer b);
		
		@Override
		public Integer evaluate(ParseContext context) {
			return operate(left.evaluate(context),right.evaluate(context));
		}		
	}
	
	public static abstract class Increment extends ArthmaticArgument {
		public Increment(Argument<Integer> toIncrement) {
			super(toIncrement,new Number(0));
		}
		@Override
		public Integer operate(Integer a, Integer b) {
			return ++a;
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
