package com.rem.gen;
import java.util.*;
import java.io.*;
import com.rem.parser.generation.VariableNameEntry;
import com.rem.parser.generation.Entry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.TabEntry;
import com.rem.gen.Token;
import com.rem.parser.generation.classwise.ExternalMethodEntry;
import com.rem.parser.generation.classwise.ExternalStatement;
import com.rem.parser.generation.classwise.ExternalVariableEntry;
import com.rem.parser.generation.classwise.ExternalClassEntry;

public class Token extends ExternalClassEntry{
	public static final Token _ = new Token();
	public void __add_method_0__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("get"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("tokenName"),""),null)}),null,new ExternalStatement.Body()));
	}
	public void __add_method_1__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("getLast"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
	}
	public void __add_method_2__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("getLast"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("tokenName"),"")),null)}),null,new ExternalStatement.Body()));
	}
	public void __add_method_3__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAll"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
	}
	public void __add_method_4__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAll"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),"")),null)}),null,new ExternalStatement.Body()));
	}
	public void __add_method_5__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAllSafely"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),"")),null)}),null,new ExternalStatement.Body()));
	}
	public void __add_method_6__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),"",new ExternalStatement(new StringEntry("newToken"),""),null)}),null,new ExternalStatement.Body()));
	}
	public void __add_method_7__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("print"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
	}
	public void __add_method_8__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("print"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(new StringEntry("tab"),""),null)}),null,new ExternalStatement.Body()));
	}
	public void __add_method_9__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("printShort"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
	}
	public void __add_method_10__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getFileName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
	}
	public void __add_method_11__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),null,new ExternalStatement(new StringEntry("getLineNumber"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
	}
	public void __add_method_12__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
	}
	public void __add_method_13__(){
		addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body()));
	}
	public void __add_sub_class_0__(){
		addSubClass(Parsed._);
	}
	public void __add_sub_class_1__(){
		addSubClass(Leaf._);
	}
	public void __add_sub_class_2__(){
		addSubClass(Branch._);
	}
	public void __INIT__(){
		super.__SETUP__(
			new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new VariableNameEntry(MainFlow.self.packageName),"")),new ExternalStatement(new StringEntry("parser"),"")),
		new Entry(){public void get(StringBuilder builder){}},
			new VariableNameEntry("Token"),
			"inteface ",
			null,
			Arrays.asList(new Entry[]{}),
			null);
		setIsInterface(true);
		setIsStatic(false);
		__add_method_0__();
		__add_method_1__();
		__add_method_2__();
		__add_method_3__();
		__add_method_4__();
		__add_method_5__();
		__add_method_6__();
		__add_method_7__();
		__add_method_8__();
		__add_method_9__();
		__add_method_10__();
		__add_method_11__();
		__add_method_12__();
		__add_method_13__();
		__add_sub_class_0__();
		__add_sub_class_1__();
		__add_sub_class_2__();
	}
	public static class Parsed extends ExternalClassEntry{
		public static final Parsed _ = new Parsed();
		public void __add_variable_0__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),"")))))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),"")))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_1__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new ExternalStatement(new StringEntry("positions"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_2__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(new StringEntry("name"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_method_0__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),"")))))),"",new Entry(){public void get(StringBuilder builder){builder.append("getChildren");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("children"),"")))));
		}
		public void __add_method_1__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setChildren");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),"")))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newChildren");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("children = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newChildren"),"")))));
		}
		public void __add_method_2__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("getPositions");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("positions"),"")))));
		}
		public void __add_method_3__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setPositions");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newPositions");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("positions = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newPositions"),"")))));
		}
		public void __add_method_4__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("name"),"")))));
		}
		public void __add_method_5__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("name = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newName"),"")))));
		}
		public void __add_method_6__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))));
		}
		public void __add_method_7__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("setValue"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("newValue"),"")),null)}),null,new ExternalStatement.Body()));
		}
		public void __add_method_8__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getLastValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))));
		}
		public void __add_method_9__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),null),new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("newToken"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("newToken"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))))))))));
		}
		public void __add_method_10__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("addAll"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),"",new ExternalStatement(new StringEntry("inductee"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("for",new ExternalStatement(";",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(new StringEntry("0"),"")),new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("inductee"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(new StringEntry("++"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("inductee"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("inductee"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("positions"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),""))))))))))))))));
		}
		public void __add_sub_class_0__(){
			addSubClass(Import._);
		}
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Parsed"),
				"class ",
				null,
				Arrays.asList(new Entry[]{}),
				null);
			setIsStatic(true);
			__add_variable_0__();
			__add_variable_1__();
			__add_variable_2__();
			__add_method_0__();
			__add_method_1__();
			__add_method_2__();
			__add_method_3__();
			__add_method_4__();
			__add_method_5__();
			__add_method_6__();
			__add_method_7__();
			__add_method_8__();
			__add_method_9__();
			__add_method_10__();
			__add_sub_class_0__();
		}
		public static class Import extends ExternalClassEntry{
			public static final Import _ = new Import();
			public void __add_variable_0__(){
				addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
			}
			public void __add_method_0__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getFileName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("fileName"),"")))));
			}
			public void __add_method_1__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setFileName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newFileName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("fileName = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newFileName"),"")))));
			}
			public void __add_method_2__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),"")))))),null,new ExternalStatement(new StringEntry("getChildren"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get_root"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getChildren"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
			}
			public void __add_method_3__(){
				addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),""))))),null,new ExternalStatement(new StringEntry("getPositions"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Parser"),"")),new ExternalStatement(new StringEntry("contexts"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("fileName"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get_root"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getPositions"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
			}
			public void __INIT__(){
				super.__SETUP__(
					new ExternalStatement("."),
				new Entry(){public void get(StringBuilder builder){}},
					new VariableNameEntry("Import"),
					"class ",
					new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Token"),""),new ExternalStatement(new StringEntry("Parsed"),""))),
					Arrays.asList(new Entry[]{}),
					null);
				setIsStatic(true);
				__add_variable_0__();
				__add_method_0__();
				__add_method_1__();
				__add_method_2__();
				__add_method_3__();
			}
		}
	}
	public static class Leaf extends ExternalClassEntry{
		public static final Leaf _ = new Leaf();
		public void __add_variable_0__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("name"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_1__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_2__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_3__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("parentPosition"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_4__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))),"",new ExternalStatement(new StringEntry("context"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_method_0__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("name"),"")))));
		}
		public void __add_method_1__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("name = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newName"),"")))));
		}
		public void __add_method_2__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getValue");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("value"),"")))));
		}
		public void __add_method_3__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setValue");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newValue");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("value = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newValue"),"")))));
		}
		public void __add_method_4__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("position"),"")))));
		}
		public void __add_method_5__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("position = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newPosition"),"")))));
		}
		public void __add_method_6__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getParentPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parentPosition"),"")))));
		}
		public void __add_method_7__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParentPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newParentPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parentPosition = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParentPosition"),"")))));
		}
		public void __add_method_8__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getContext");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("context"),"")))));
		}
		public void __add_method_9__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setContext");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newContext");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("context = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newContext"),"")))));
		}
		public void __add_method_10__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("get"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("tokenName"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("this"),""))))));
		}
		public void __add_method_11__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("getLast"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))));
		}
		public void __add_method_12__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("getLast"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("tokenName"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))));
		}
		public void __add_method_13__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),"",new ExternalStatement(new StringEntry("token"),""),null)}),null,new ExternalStatement.Body()));
		}
		public void __add_method_14__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAll"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))));
		}
		public void __add_method_15__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAll"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))));
		}
		public void __add_method_16__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAllSafely"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
		}
		public void __add_method_17__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("getValue"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
		}
		public void __add_method_18__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("print"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("printShort"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
		}
		public void __add_method_19__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("print"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("tab"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("for",new ExternalStatement(";",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(new StringEntry("0"),"")),new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("tab"),""))),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(new StringEntry("++"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("  "),""))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(".",new ExternalStatement(new StringEntry("printShort"),"")),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))));
		}
		public void __add_method_20__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("printShort"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("["),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("name"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(":"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("value"),""))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("]"),""))))))))))));
		}
		public void __add_method_21__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getFileName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
		}
		public void __add_method_22__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),null,new ExternalStatement(new StringEntry("getLineNumber"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLineNumber"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))))))))));
		}
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Leaf"),
				"class ",
				null,
				Arrays.asList(new Entry[]{new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))}),
				null);
			setIsStatic(true);
			__add_variable_0__();
			__add_variable_1__();
			__add_variable_2__();
			__add_variable_3__();
			__add_variable_4__();
			__add_method_0__();
			__add_method_1__();
			__add_method_2__();
			__add_method_3__();
			__add_method_4__();
			__add_method_5__();
			__add_method_6__();
			__add_method_7__();
			__add_method_8__();
			__add_method_9__();
			__add_method_10__();
			__add_method_11__();
			__add_method_12__();
			__add_method_13__();
			__add_method_14__();
			__add_method_15__();
			__add_method_16__();
			__add_method_17__();
			__add_method_18__();
			__add_method_19__();
			__add_method_20__();
			__add_method_21__();
			__add_method_22__();
		}
	}
	public static class Branch extends ExternalClassEntry{
		public static final Branch _ = new Branch();
		public void __add_variable_0__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")))))))),"",new ExternalStatement(new StringEntry("namedLists"),""),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("HashMap"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")))))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_1__(){
			addVariable(new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))));
		}
		public void __add_variable_2__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("name"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_3__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_4__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(new StringEntry("parentPosition"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_variable_5__(){
			addVariable(new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))),"",new ExternalStatement(new StringEntry("context"),""),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))));
		}
		public void __add_method_0__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")))))))),"",new Entry(){public void get(StringBuilder builder){builder.append("getNamedLists");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("namedLists"),"")))));
		}
		public void __add_method_1__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setNamedLists");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Map"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")))))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newNamedLists");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("namedLists = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newNamedLists"),"")))));
		}
		public void __add_method_2__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("getChildren");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("children"),"")))));
		}
		public void __add_method_3__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setChildren");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),"",new Entry(){public void get(StringBuilder builder){builder.append("newChildren");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("children = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newChildren"),"")))));
		}
		public void __add_method_4__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getName");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("name"),"")))));
		}
		public void __add_method_5__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setName");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newName");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("name = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newName"),"")))));
		}
		public void __add_method_6__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("position"),"")))));
		}
		public void __add_method_7__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("position = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newPosition"),"")))));
		}
		public void __add_method_8__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("getParentPosition");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("parentPosition"),"")))));
		}
		public void __add_method_9__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setParentPosition");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new Entry(){public void get(StringBuilder builder){builder.append("newParentPosition");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("parentPosition = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newParentPosition"),"")))));
		}
		public void __add_method_10__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("getContext");}},Arrays.asList(new ExternalVariableEntry[]{}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("context"),"")))));
		}
		public void __add_method_11__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new StringEntry("void")),"",new Entry(){public void get(StringBuilder builder){builder.append("setContext");}},Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(true,false,false,new ExternalStatement.TypeName(new ExternalStatement(".",new ExternalStatement(new StringEntry("Parser"),""),new ExternalStatement(new StringEntry("Result"),""),new ExternalStatement(new StringEntry("Pass"),""))),"",new Entry(){public void get(StringBuilder builder){builder.append("newContext");}},null)}),"",new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("context = ")),new StringEntry(";"),"",new ExternalStatement(new StringEntry("newContext"),"")))));
		}
		public void __add_method_12__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("get"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("tokenName"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),"",new ExternalStatement(new StringEntry("nameList"),""),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("tokenName"),"")))))))),new ExternalStatement.Conditional("if",new ExternalStatement(null,null,"||",Arrays.asList(new String[]{"","==","||"}),new ExternalStatement(".",new ExternalStatement(new StringEntry("nameList"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),"")),new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("nameList"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("isEmpty"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("null"),""))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("nameList"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))))))))));
		}
		public void __add_method_13__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getValue"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
		}
		public void __add_method_14__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("toString"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("0"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
		}
		public void __add_method_15__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("getLast"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))))))));
		}
		public void __add_method_16__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),null,new ExternalStatement(new StringEntry("getLast"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("tokenName"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("tokenName"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("-",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("tokenName"),""))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("size"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))))))));
		}
		public void __add_method_17__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("add"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),"",new ExternalStatement(new StringEntry("token"),""),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("token"),""))))))),new ExternalStatement.Conditional("if",new ExternalStatement("==",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("containsKey"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))),new ExternalStatement(".",new ExternalStatement(new StringEntry("false"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("put"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))),new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("token"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("add"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("token"),""))))))))));
		}
		public void __add_method_18__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAll"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),""))))));
		}
		public void __add_method_19__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAll"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),""))))))))));
		}
		public void __add_method_20__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("List"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))),null,new ExternalStatement(new StringEntry("getAllSafely"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("if",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("containsKey"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),"")))))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("namedLists"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("get"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("key"),""))))))))),new ExternalStatement.Conditional("else",null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement.NewObject(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("ArrayList"),""),new ExternalStatement(new StringEntry("<"),new StringEntry(">"),"",new ExternalStatement(",",new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))))))),new ExternalStatement.Parameters(new ExternalStatement.Parameters()))))))));
		}
		public void __add_method_21__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("print"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(":>"),"")))),new ExternalStatement(".",new ExternalStatement(new StringEntry("name"),"")))))))),new ExternalStatement.Conditional("for",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),"",new ExternalStatement(new StringEntry("node"),""),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("node"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),""))))))))))));
		}
		public void __add_method_22__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("print"),""),Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("tab"),"")),null)}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("for",new ExternalStatement(";",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Integer"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(new StringEntry("0"),"")),new ExternalStatement("<",new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("tab"),""))),new ExternalStatement(new ExternalStatement(".",new ExternalStatement(new StringEntry("i"),"")),new ExternalStatement(new StringEntry("++"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("  "),""))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("name"),""))))))),new ExternalStatement.Conditional("for",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("node"),"")),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("node"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement("+",new ExternalStatement(".",new ExternalStatement(new StringEntry("tab"),"")),new ExternalStatement(".",new ExternalStatement(new StringEntry("1"),"")))))))))))));
		}
		public void __add_method_23__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("void"),"")),null,new ExternalStatement(new StringEntry("printShort"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement.Conditional("for",new ExternalStatement(":",new ExternalVariableEntry(false,false,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),"")),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("node"),"")),null),new ExternalStatement(".",new ExternalStatement(new StringEntry("children"),""))),new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("["),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("node"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry(":"),""))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("node"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getValue"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("print"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("\""),new StringEntry("\""),"",new ExternalStatement(".",new ExternalStatement(new StringEntry("]"),""))))))))))),new ExternalStatement(new TabEntry(new StringEntry("")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("System"),"")),new ExternalStatement(new StringEntry("out"),""),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("println"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
		}
		public void __add_method_24__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("String"),"")),null,new ExternalStatement(new StringEntry("getFileName"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getFileName"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters())))))));
		}
		public void __add_method_25__(){
			addMethod(new ExternalMethodEntry(0,false,new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("int"),"")),null,new ExternalStatement(new StringEntry("getLineNumber"),""),Arrays.asList(new ExternalVariableEntry[]{}),null,new ExternalStatement.Body(new ExternalStatement(new TabEntry(new StringEntry("return ")),new StringEntry(";"),"",new ExternalStatement(".",new ExternalStatement(".",new ExternalStatement(new StringEntry("context"),"")),new ExternalStatement(null,new StringEntry(")"),"(",new ExternalStatement(new StringEntry("getLineNumber"),""),new ExternalStatement.Parameters(new ExternalStatement.Parameters(new ExternalStatement(".",new ExternalStatement(new StringEntry("position"),""))))))))));
		}
		public void __INIT__(){
			super.__SETUP__(
				new ExternalStatement("."),
			new Entry(){public void get(StringBuilder builder){}},
				new VariableNameEntry("Branch"),
				"class ",
				null,
				Arrays.asList(new Entry[]{new ExternalStatement.TypeName(new ExternalStatement(new StringEntry("Token"),""))}),
				null);
			setIsStatic(true);
			__add_variable_0__();
			__add_variable_1__();
			__add_variable_2__();
			__add_variable_3__();
			__add_variable_4__();
			__add_variable_5__();
			__add_method_0__();
			__add_method_1__();
			__add_method_2__();
			__add_method_3__();
			__add_method_4__();
			__add_method_5__();
			__add_method_6__();
			__add_method_7__();
			__add_method_8__();
			__add_method_9__();
			__add_method_10__();
			__add_method_11__();
			__add_method_12__();
			__add_method_13__();
			__add_method_14__();
			__add_method_15__();
			__add_method_16__();
			__add_method_17__();
			__add_method_18__();
			__add_method_19__();
			__add_method_20__();
			__add_method_21__();
			__add_method_22__();
			__add_method_23__();
			__add_method_24__();
			__add_method_25__();
		}
	}
}