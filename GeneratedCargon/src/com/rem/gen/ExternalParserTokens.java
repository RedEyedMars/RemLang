package com.rem.gen;
import java.util.*;
import java.io.*;
import com.rem.parser.generation.VariableNameEntry;
import com.rem.parser.generation.Entry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.classwise.ExternalStatement;
import com.rem.parser.generation.classwise.ExternalClassEntry;
import com.rem.gen.ExternalParserTokens;

public class ExternalParserTokens extends ExternalClassEntry{
	public static final ExternalParserTokens _ = new ExternalParserTokens();
	public void __add_sub_class_0__(){
		addSubClass(Plain._);
	}
	public void __add_sub_class_1__(){
		addSubClass(Name._);
	}
	public void __add_sub_class_2__(){
		addSubClass(Rule._);
	}
	public void __INIT__(){
		super.__SETUP__(
			new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.packageName),"")),new ExternalStatement(new StringEntry("parser"),"")),
		new Entry(){public void get(StringBuilder builder){}},
			new VariableNameEntry(("ExternalParserTokens").toString()),
			"class ",
			null,
			Arrays.asList(new Entry[]{}),
			null);
		setIsStatic(false);
		__add_sub_class_0__();
		__add_sub_class_1__();
		__add_sub_class_2__();
	}
	public static class Plain extends ExternalClassEntry{
		public static final Plain _ = new Plain();
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(("Plain").toString()),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
		}
	}
	public static class Name extends ExternalClassEntry{
		public static final Name _ = new Name();
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(("Name").toString()),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
		}
	}
	public static class Rule extends ExternalClassEntry{
		public static final Rule _ = new Rule();
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry(("Rule").toString()),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
		}
	}
}