package gen.checks;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import gen.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class DefinedCheck implements ICheck {
	private String errorMessage;

	private VariableEntry variable1;
	private Boolean truth1;
	public DefinedCheck(VariableEntry variable1,Boolean truth1,String errorMessage){
		this.errorMessage = errorMessage;

		this.variable1 = variable1;
		this.truth1 = truth1;
	}

	public void check(){
		if(!(variable1.isDefined(truth1))){
			System.err.println(errorMessage);
		}
	}

}