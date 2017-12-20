package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.parser.generation.classwise.*;
import com.rem.parser.generation.*;
import com.rem.parser.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.*;
import clgen.TypeStatement;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import com.rem.parser.generation.classwise.ExternalStatement;
public class  TypeStatement   {
	public static class classes {
	}
	public static TypeStatement variables = null;
	public static TypeStatement methods = null;
	//Externals


	//Internals
protected ExternalStatement asStatement = null;
protected String asString = null;

	public ExternalStatement getAsStatement()  {
		return asStatement;
	}
	public ExternalStatement get_asStatement()  {
		return asStatement;
	}
	public String getAsString()  {
		return asString;
	}
	public String get_asString()  {
		return asString;
	}
public void set(final ExternalStatement statement)  {
	asStatement = statement;
}
public void set(final String string)  {
	asString = string;
}

}