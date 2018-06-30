package com.rem.gen;
import java.util.*;
import java.io.*;
import com.rem.parser.generation.VariableNameEntry;
import com.rem.parser.generation.Entry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.classwise.ExternalMethodEntry;
import com.rem.parser.generation.classwise.ExternalStatement;
import com.rem.parser.generation.classwise.ExternalVariableEntry;
import com.rem.parser.generation.classwise.ExternalClassEntry;
import com.rem.gen.Tokens;

public class Tokens extends ExternalClassEntry{
	public static final Tokens _ = new Tokens();
	public void __add_method_0__() {
		addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Tokens");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
	}
	public void __add_sub_class_0__() {
		addSubClass(Plain._);
	}
	public void __add_sub_class_1__() {
		addSubClass(Syntax._);
	}
	public void __add_sub_class_2__() {
		addSubClass(Name._);
	}
	public void __add_sub_class_3__() {
		addSubClass(Rule._);
	}
	public void __INIT__() {
		super.__SETUP__(
			null,
			new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.packageName),"")),new ExternalStatement(new StringEntry("parser"),"")),
			new Entry(){public void get(StringBuilder builder){}},
			new VariableNameEntry("Tokens"),
			"class ",
			null,
			Arrays.asList(new Entry[]{}),
			null);
		setIsStatic(false);
		__add_method_0__();
		__add_sub_class_0__();
		__add_sub_class_1__();
		__add_sub_class_2__();
		__add_sub_class_3__();
	}
	public static class Plain extends ExternalClassEntry{
		public static final Plain _ = new Plain();
		public Plain() {
			super();
		}
		public void __add_method_0__() {
			addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Plain");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
		}
		public void __INIT__() {
			super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Plain"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			__add_method_0__();
		}
	}
	public static class Syntax extends ExternalClassEntry{
		public static final Syntax _ = new Syntax();
		public Syntax() {
			super();
		}
		public void __add_method_0__() {
			addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Syntax");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
		}
		public void __INIT__() {
			super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Syntax"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			__add_method_0__();
		}
	}
	public static class Name extends ExternalClassEntry{
		public static final Name _ = new Name();
		public Name() {
			super();
		}
		public void __add_method_0__() {
			addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Name");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
		}
		public void __INIT__() {
			super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Name"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			__add_method_0__();
		}
	}
	public static class Rule extends ExternalClassEntry{
		public static final Rule _ = new Rule();
		public Rule() {
			super();
		}
		public void __add_method_0__() {
			addMethod(new ExternalMethodEntry(0,false,new Entry(){public void get(StringBuilder builder){builder.append("Rule");}},"",new Entry(){public void get(StringBuilder builder){builder.append("*");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body()));
		}
		public void __INIT__() {
			super.__SETUP__(
				null,
				new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Rule"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			__add_method_0__();
		}
	}
}