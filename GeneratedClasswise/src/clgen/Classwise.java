package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.parser.generation.classwise.*;
import com.rem.parser.generation.*;
import com.rem.parser.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.*;
import clgen.Classwise;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.io.File;
import com.rem.crg.parser.Parser;
import com.rem.crg.parser.Token;
import java.lang.StringBuilder;
import com.rem.parser.generation.classwise.ExternalStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import com.rem.parser.generation.StringEntry;
import clgen.TypeStatement;
public class  Classwise   {
	public static class classes {
	}
	public static Classwise variables = null;
	public static Classwise methods = null;
	//Externals


	//Internals
protected File innerDirectory = null;
protected File outerDirectory = null;
protected ExternalClassEntry mainClass = null;
protected final Parser pulsar = null;
protected final String parserResult = "Parser.Result";

	public File getInnerDirectory()  {
		return innerDirectory;
	}
	public File get_innerDirectory()  {
		return innerDirectory;
	}
	public File getOuterDirectory()  {
		return outerDirectory;
	}
	public File get_outerDirectory()  {
		return outerDirectory;
	}
	public ExternalClassEntry getMainClass()  {
		return mainClass;
	}
	public ExternalClassEntry get_mainClass()  {
		return mainClass;
	}
	public Parser getPulsar()  {
		return pulsar;
	}
	public Parser get_pulsar()  {
		return pulsar;
	}
	public String getParserResult()  {
		return parserResult;
	}
	public String get_parserResult()  {
		return parserResult;
	}
public void setup(final Parser.Result result)  {
	String fileName = result.getFileName();
	final Integer indexOfDot = fileName.lastIndexOf(".");
	if (indexOfDot > -1 ) {
		fileName = fileName.substring(0,indexOfDot);
	}
	fileName = FlowController.camelize(fileName.toString());
	innerDirectory = new File("../" + fileName + "/src",MainFlow.variables.get_innerPackageName());
	innerDirectory.mkdirs();
	outerDirectory = new File("../" + fileName + "/src",MainFlow.variables.get_outerPackageName());
	outerDirectory.mkdirs();
}
public void generateAll(final Token input)  {
	for (final Token element :  input.getAllSafely("imports")) {
		generateAll(element);
	}
	for (final Token element :  input.getAllSafely("IMPORT_CLASS")) {
		generateAll(element);
	}
	for (final Token element :  input.getAllSafely("anonymous_class")) {
		final StringBuilder anonymousPackageName = new StringBuilder();
		final String className = FlowController.camelize(element.get("className").toString());
		String dot = "";
		for (final Token atom :  element.getAllSafely("packageName")) {
			for (final Token quark :  atom.getAllSafely("name_var")) {
				anonymousPackageName.append(dot);
				final NameVar anonymousNameVar = new NameVar();
				name_var(quark,anonymousNameVar,true,new ExternalContext(false));
				anonymousPackageName.append(anonymousNameVar.getAsString());
				dot = ".";
			}
		}
		MainFlow.variables.get_setupClassList().add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("ExternalClassEntry"))), /*Enty*/new ExternalStatement(new StringEntry("suppliment"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(className.toString().toString())))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(anonymousPackageName.toString().toString())))))))))));
	}
	for (final Token element :  input.getAllSafely("class_declaration")) {
		final ExternalClassEntry newClass = MainFlow.variables.get_classGenerator().declaration(element,false,null);
		final String className = FlowController.camelize(element.get("className").toString());
		final Boolean isInner = element.get("inner") != null;
		if (isInner) {
			MainFlow.variables.get_innerClasses().put(newClass.getFullName(),newClass);
			newClass.outputToFile(MainFlow.methods,new File(innerDirectory,MainFlow.variables.get_packageName()));
		}
		else  {
			MainFlow.variables.get_outerClasses().put(newClass.getFullName(),newClass);
			newClass.outputToFile(MainFlow.methods,new File(outerDirectory,MainFlow.variables.get_packageName()));
newClass.addVariable(new ExternalVariableEntry(true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(newClass.getName().toString())))),"", /*Name*/new ExternalStatement(new StringEntry("_")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(newClass.getName().toString())))),new ExternalStatement.Parameters()))));
			MainFlow.variables.get_setupClassList().add(/*InCl*/new ExternalStatement(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(className.toString()))), /*Name*/new ExternalStatement(new StringEntry("_"))), /*Enty*/new ExternalStatement(new StringEntry("__INIT__"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))),
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("MainFlow"))), /*Enty*/new ExternalStatement(new StringEntry("outputClasses"))), /*Enty*/new ExternalStatement(new StringEntry("add"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(className.toString()))), /*Name*/new ExternalStatement(new StringEntry("_")))))))))));
		}
	}
	for (final Token element :  input.getAllSafely("method_declaration")) {
		final ExternalMethodEntry newMethod = MainFlow.variables.get_method().declaration(element,true,mainClass.getContext());
		mainClass.addMethod(newMethod);
	}
	for (final Token element :  input.getAllSafely("variable_declaration")) {
		final ExternalVariableEntry newVariable = MainFlow.variables.get_variable().declaration(element,true,mainClass.getContext());
		mainClass.addVariable(newVariable);
		MainFlow.variables.get_variable().addDefinedVariableName(newVariable);
	}
}
public void generate(final Parser.Result result)  {
	final String mainFlow = "MainFlow";
	
	final ExternalClassEntry mainFlowClass = new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(new Entry(){
			public void get(StringBuilder __BUILDER__){/*Enty*/new ExternalStatement(new StringEntry(MainFlow.variables.get_packageName().toString())).get(__BUILDER__);
			}
		}, 
		new Entry(){
			public void get(StringBuilder __BUILDER__){
			}
		},new StringEntry(mainFlow), "class ", /*TypeName*/new ExternalStatement.TypeName(new StringEntry("FlowController")), new ArrayList<Entry>(Arrays.asList(new Entry[]{})), 
		new Entry(){
		public void get(StringBuilder builder){
			builder.append(" class ");
			new StringEntry(mainFlow).get(builder);
			new StringEntry("").get(builder);
		}
	});
       /* Variables */
		add_variable_7();
		add_variable_8();
		add_variable_9(); 
	   /* Methods */
		add_method_0();
		add_method_1();
		add_method_2();
		add_method_3(); 
	   /* Classes */
	}
		private void add_variable_7() {
	 		addVariable(new ExternalVariableEntry(true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(mainFlow.toString())))),"", /*Name*/new ExternalStatement(new StringEntry("self")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(mainFlow.toString())))),new ExternalStatement.Parameters()))));
	 	}
		private void add_variable_8() {
	 		addVariable(new ExternalVariableEntry(true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Set"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(/*Exac*/new ExternalStatement(new StringEntry("ExternalClassEntry")))))),"", /*Name*/new ExternalStatement(new StringEntry("outputClasses")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("HashSet"), new ExternalStatement(new StringEntry("<"), new StringEntry(">"), ",", /*TypeName*/new ExternalStatement.TypeName(/*Exac*/new ExternalStatement(new StringEntry("ExternalClassEntry")))))),new ExternalStatement.Parameters()))));
	 	}
		private void add_variable_9() {
	 		addVariable(new ExternalVariableEntry(true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("File"))),"", /*Name*/new ExternalStatement(new StringEntry("__ROOTDIRECTORY__")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("File"))),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(".".toString())))))))));
	 	}
	
		private void add_method_0() {
	 		addMethod(new ExternalMethodEntry(1, true,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))),"", /*Exac*/new ExternalStatement(new StringEntry("main")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))),"[]", /*Name*/new ExternalStatement(new StringEntry("args")))}), "", /*Body*/new ExternalStatement.Body(
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement("==", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("args"))), /*Enty*/new ExternalStatement(new StringEntry("length")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("1 ")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser"))),"", /*Name*/new ExternalStatement(new StringEntry("parser")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("Parser"))),new ExternalStatement.Parameters())))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(parserResult.toString())))),"", /*Name*/new ExternalStatement(new StringEntry("result")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("parser"))), /*Enty*/new ExternalStatement(new StringEntry("parse"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("args")), new ExternalStatement.ArrayParameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0"))))))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("self"))), /*Enty*/new ExternalStatement(new StringEntry("setupRootDirectory"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("args")), new ExternalStatement.ArrayParameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("self"))), /*Enty*/new ExternalStatement(new StringEntry("setupGenerators"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("self"))), /*Enty*/new ExternalStatement(new StringEntry("setup"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("result"))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("self"))), /*Enty*/new ExternalStatement(new StringEntry("generate"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("result"))))))))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("ExternalImportEntry"))), /*Enty*/new ExternalStatement(new StringEntry("solidify"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))),
/*BODY*/				
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("self"))), /*Enty*/new ExternalStatement(new StringEntry("output"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))),
/*BODY*/				
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("System")))), /*Enty*/new ExternalStatement(new StringEntry("err"))), /*Enty*/new ExternalStatement(new StringEntry("println"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("System")))), /*Enty*/new ExternalStatement(new StringEntry("err"))), /*Enty*/new ExternalStatement(new StringEntry("println"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("No Filename Provided!".toString()))))))))))))))))));
	 	}
		private void add_method_1() {
	 		addMethod(new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))),"", /*Exac*/new ExternalStatement(new StringEntry("setupRootDirectory")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))),"", /*Name*/new ExternalStatement(new StringEntry("fileName")))}), "", /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("String"))),"", /*Name*/new ExternalStatement(new StringEntry("fileName")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("camelize")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fileName"))))))))),
/*BODY*/				
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("int"))),"", /*Name*/new ExternalStatement(new StringEntry("indexOfDot")), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fileName"))), /*Enty*/new ExternalStatement(new StringEntry("indexOf"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(".".toString())))))))))),
/*BODY*/				
		/*Cond*/new ExternalStatement.Conditional(
			"if ", 
			/*Optr*/new ExternalStatement(">=", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("indexOfDot")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0 ")))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("__ROOTDIRECTORY__")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("File"))),new ExternalStatement.Parameters(/*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("../")))), /*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fileName"))), /*Enty*/new ExternalStatement(new StringEntry("substring"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("0")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("indexOfDot")))))))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("/src"))))))))))),
/*BODY*/				
		/*Cond*/new ExternalStatement.Conditional(
			"else ", null,
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Optr*/new ExternalStatement("=", /*Name*/new ExternalStatement(new StringEntry("__ROOTDIRECTORY__")), /*Name*/new ExternalStatement(/*NObj*/new ExternalStatement.NewObject(/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("File"))),new ExternalStatement.Parameters(/*Optr*/new ExternalStatement("+", /*Optr*/new ExternalStatement("+", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("../")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("fileName")))), /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("/src"))))))))))),
/*BODY*/				
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("__ROOTDIRECTORY__"))), /*Enty*/new ExternalStatement(new StringEntry("mkdirs"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters())))))));
	 	}
		private void add_method_2() {
	 		addMethod(new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))),"", /*Exac*/new ExternalStatement(new StringEntry("setupGenerators")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), "", /*Body*/new ExternalStatement.Body(
		/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_setupClassList())))))));
	 	}
		private void add_method_3() {
	 		addMethod(new ExternalMethodEntry(1, false,/*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(new StringEntry("void"))),"", /*Exac*/new ExternalStatement(new StringEntry("output")), /*Parameters*/Arrays.asList(new ExternalVariableEntry[]{}), "", /*Body*/new ExternalStatement.Body(
		/*Cond*/new ExternalStatement.Conditional(
			"for ", 
			/*Optr*/new ExternalStatement(": ", new ExternalVariableEntry(false, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Exac*/new ExternalStatement(new StringEntry("ExternalClassEntry")))),"", /*Name*/new ExternalStatement(new StringEntry("outputClass"))), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("outputClasses"))))),
			/*Body*/new ExternalStatement.Body(
			/*Elem*/new ExternalStatement(new TabEntry(new StringEntry("")), new StringEntry(";"), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("outputClass"))), /*Enty*/new ExternalStatement(new StringEntry("outputToFile"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("this")))),/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("__ROOTDIRECTORY__"))))))))))))));
	 	}
	
};
	mainFlowClass.__INIT__();
	final StringBuilder __BUILDER__;
	final File __DIRECTORY__;
	__BUILDER__ = new StringBuilder();
	/*Enty*/new ExternalStatement(new StringEntry(MainFlow.variables.get_packageName().toString())).get(__BUILDER__);
	__DIRECTORY__ = new File(MainFlow.__ROOT_DIRECTORY__, __BUILDER__.toString().replace(".","/"));
	__DIRECTORY__.mkdirs();
	MainFlow.methods.addFile(__DIRECTORY__,FlowController.camelize(mainFlow)+".java", mainFlowClass);
	mainClass = mainFlowClass;
	generateAll(((Parser . Result . Pass) result) . getRoot());
}
public void name_var(final Token input,final NameVar nameVar,final boolean isInner,final ExternalContext context)  {
	if (isInner) {
		nameVar.inner();
	}
	else  {
		nameVar.outer();
	}
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("access")) {
			ExternalStatement left = null;
			ExternalStatement right = null;
			for (final Token atom :  element.getAllSafely("name_var")) {
				name_var(atom,nameVar,isInner,context);
			}
			if (element.get("CAMEL") != null) {
				nameVar.camelize();
			}
		}
		else if (element.getName().equals("concat")) {
			NameVar right = null;
			for (final Token atom :  element.getAllSafely("name_var")) {
				if (right == null) {
					name_var(atom,nameVar,isInner,context);
					right = new NameVar();
				}
				else  {
					name_var(atom,right,isInner,context);
				}
			}
			nameVar.concatenateWith(right);
			if (element.get("CAMEL") != null) {
				nameVar.camelize();
			}
		}
		else if (element.getName().equals("as_method")) {
			nameVar.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().statement(element.get("body_statement"),true,context)))));
		}
		else if (element.getName().equals("as_quote")) {
			final ExternalStatement asQuoteStatement = MainFlow.variables.get_body().statement(element.get("body_statement"),true,context);
			nameVar.add(new ExternalStatement(new StringEntry("\""),new StringEntry("\""),asQuoteStatement));
		}
		else if (element.getName().equals("as_string")) {
			nameVar.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().statement(element.get("body_statement"),true,context))), /*Enty*/new ExternalStatement(new StringEntry("toString"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))));
		}
		else if (element.getName().equals("exact")) {
			if (element.get("WILD") != null) {
				nameVar.add(element.get("WILD").toString());
			}
			else if (element.get("variable_names") != null) {
				nameVar.add(element.get("variable_names").toString());
			}
			else  {
				nameVar.add(element.get("NAME").toString());
			}
			if (element.get("CAMEL") != null) {
				nameVar.camelize();
			}
		}
		else if (element.getName().equals("variable")) {
			if (element.get("WILD") != null) {
				nameVar.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.get("class_variable_names").toString())))));
			}
			else if (element.get("variable_names") != null) {
				nameVar.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.get("variable_names").toString())))));
			}
			if (element.get("CAMEL") != null) {
				nameVar.camelize();
			}
		}
	}
}
public void type_var(final Token input,final Type output,final Boolean isInner,final ExternalContext parentContext)  {
	if (isInner) {
		output.inner();
	}
	else  {
		output.outer();
	}
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("access_multi")) {
			for (final Token atom :  element.getAllSafely("type_var")) {
				type_var(atom,output,isInner,parentContext);
			}
			for (final Token atom :  element.getAllSafely("as_method")) {
				if (atom.get("name_var") != null) {
					final NameVar methodNameVar = new NameVar();
					name_var(atom.get("name_var"),methodNameVar,isInner,parentContext);
					output.addFindMethod(methodNameVar.getAsStatement());
				}
				else  {
					output.addFindMethod(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("*".toString())))));
				}
			}
			if (element.get("CAMEL") != null) {
				output.camelize();
			}
		}
		else if (element.getName().equals("access_method")) {
			for (final Token atom :  element.getAllSafely("type_var")) {
				type_var(atom,output,isInner,parentContext);
			}
			if (element.get("name_var") != null) {
				final NameVar methodNameVar = new NameVar();
				name_var(element.get("name_var"),methodNameVar,isInner,parentContext);
				output.addFindMethod(methodNameVar.getAsStatement());
			}
			else  {
				output.addFindMethod(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry("*".toString())))));
			}
			if (element.get("CAMEL") != null) {
				output.camelize();
			}
		}
		else if (element.getName().equals("concat")) {
			Type right = null;
			for (final Token atom :  element.getAllSafely("type_var")) {
				if (right == null) {
					type_var(atom,output,isInner,parentContext);
					right = new Type();
				}
				else  {
					type_var(atom,right,isInner,parentContext);
				}
			}
			output.concatenateWith(right);
			if (element.get("CAMEL") != null) {
				output.camelize();
			}
		}
		else if (element.getName().equals("as_method")) {
			output.addSubClass(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().statement(element.get("body_statement"),true,parentContext)))));
		}
		else if (element.getName().equals("as_quote")) {
			final ExternalStatement quoteAsStatement = MainFlow.variables.get_body().statement(element.get("body_statement"),true,parentContext);
			output.addSubClass(new ExternalStatement(new StringEntry("\""),new StringEntry("\""),quoteAsStatement));
		}
		else if (element.getName().equals("as_string")) {
			output.addSubClass(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(MainFlow.variables.get_body().statement(element.get("body_statement"),true,parentContext))), /*Enty*/new ExternalStatement(new StringEntry("toString"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters()))));
		}
		else if (element.getName().equals("exact")) {
			if (element.get("NAME") != null) {
				output.addSubClass(element.get("NAME").toString());
			}
			else  {
				output.addSubClass(element.get("variable_names").toString());
			}
			if (element.get("CAMEL") != null) {
				output.camelize();
			}
			for (final Token atom :  element.getAllSafely("template_parameters")) {
				for (final Token quark :  atom.getAllSafely("template_parameter")) {
					final Type templateType = new Type();
					all_type(quark,templateType,isInner,parentContext);
					output.addTemplateClass(templateType);
				}
			}
		}
		else if (element.getName().equals("class")) {
			Type ret = null;
			ExternalStatement value = null;
			if (element.get("class_variable_names") != null) {
				value = /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.get("class_variable_names").toString()))));
			}
			else  {
				value = /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.get("class_names").toString()))));
			}
			output.addSubClass(value);
			if (element.get("CAMEL") != null) {
				output.camelize();
			}
			for (final Token atom :  element.getAllSafely("template_parameters")) {
				for (final Token quark :  atom.getAllSafely("template_parameter")) {
					final Type templateType = new Type();
					all_type(quark,templateType,isInner,parentContext);
					output.addTemplateClass(templateType);
				}
			}
		}
	}
}
public void all_type(final Token input,final Type output,final Boolean isInner,final ExternalContext parentContext)  {
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("type_var")) {
			type_var(element,output,isInner,parentContext);
		}
		else if (element.getName().equals("CLASS_TYPE")) {
			output.addSubClass("ExternalClassEntry");
		}
		else if (element.getName().equals("METHOD_TYPE")) {
			output.addSubClass("ExternalMethodEntry");
		}
		else if (element.getName().equals("VARIABLE_TYPE")) {
			output.addSubClass("ExternalVariableEntry");
		}
		else if (element.getName().equals("BODY_TYPE")) {
			output.addSubClass("ExternalStatmeent.Body");
		}
		else if (element.getName().equals("STATEMENT_TYPE")) {
			output.addSubClass("ExternalStatement");
		}
		else if (element.getName().equals("PARAMETERS_TYPE")) {
			output.addSubClass("ExternalStatement.Parameters");
		}
		else if (element.getName().equals("CONTEXT_TYPE")) {
			output.addSubClass("ExternalContext");
		}
	}
}

}