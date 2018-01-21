package com.rem.gen;
import java.util.*;
import java.io.*;
import com.rem.parser.generation.VariableNameEntry;
import com.rem.parser.generation.Entry;
import com.rem.parser.generation.StringEntry;
import com.rem.gen.AnonymousContext;
import com.rem.gen.Parser;
import com.rem.parser.generation.classwise.ExternalMethodEntry;
import com.rem.parser.generation.classwise.ExternalStatement;
import com.rem.parser.generation.classwise.ExternalVariableEntry;
import com.rem.parser.generation.classwise.ExternalClassEntry;

public class AnonymousContext extends ExternalClassEntry{
	public static final AnonymousContext _ = new AnonymousContext();
	public void __add_method_0__(){
		addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("AnonymousContext");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
	}
	public void __INIT__(){
		super.__SETUP__(
			null,
			new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.packageName),"")),new ExternalStatement(new StringEntry("contexts"),"")),
		new Entry(){public void get(StringBuilder builder){}},
			new VariableNameEntry("AnonymousContext"),
			"class ",
			new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Context"),""))),
			Arrays.asList(new Entry[]{}),
			null);
		setIsStatic(false);
		__add_method_0__();
	}
}