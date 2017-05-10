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

public class ContextCheck implements ICheck {
	private String errorMessage;

	private Map<String,VariableEntry> myContext1;
	private String iterableName1;
	public ContextCheck(Map<String,VariableEntry> myContext1,String iterableName1,String errorMessage){
		this.errorMessage = errorMessage;

		this.myContext1 = myContext1;
		this.iterableName1 = iterableName1;
	}

	public void check(){
		if(!(myContext1.containsKey(iterableName1))){
			System.err.println(errorMessage);
		}
	}

}