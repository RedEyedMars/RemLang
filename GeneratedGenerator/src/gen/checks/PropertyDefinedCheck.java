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

public class PropertyDefinedCheck implements ICheck {
	private String errorMessage;

	private Map<String,Map<String,Entry>> propertyVariables1;
	private String propertyName1;
	public PropertyDefinedCheck(Map<String,Map<String,Entry>> propertyVariables1,String propertyName1,String errorMessage){
		this.errorMessage = errorMessage;

		this.propertyVariables1 = propertyVariables1;
		this.propertyName1 = propertyName1;
	}

	public void check(){
		if(!(propertyVariables1.containsKey(propertyName1))){
			System.err.println(errorMessage);
		}
	}

}