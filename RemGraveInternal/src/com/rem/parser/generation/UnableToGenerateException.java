package com.rem.parser.generation;

import com.rem.parser.token.IToken;

public class UnableToGenerateException extends RuntimeException {
	private static final long serialVersionUID = -7770164685406782500L;
	public UnableToGenerateException(String name, IToken offender){
		super(name+": failed to generate!\n"+Generator.tokenErrorMessage(offender));
	}
	public UnableToGenerateException(String name, IToken offender,String errorMessage){
		super(name+": failed to generate!\n"+Generator.tokenErrorMessage(offender)+"\n"+errorMessage);
	}
}
