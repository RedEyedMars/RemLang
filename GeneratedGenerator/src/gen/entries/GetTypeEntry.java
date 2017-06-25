package gen.entries;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import gen.*;
import gen.checks.*;
import gen.properties.*;
import lists.*;

public class GetTypeEntry implements Entry {
	public GetTypeEntry getSelf(){
		return this;
	}


	private String type = "";

	public GetTypeEntry(ITypeListener subject){
		Boolean hasType = subject.hasType();
		if((hasType == true)){
			type = subject.getType();
		}
		else {
			type = subject.getDefaultType();
		}
	}

	public String getType(){
		return type;
	}
	public void get(StringBuilder builder){
		if((type != null)){
			new StringEntry(type).get(builder);
		}
	}
}