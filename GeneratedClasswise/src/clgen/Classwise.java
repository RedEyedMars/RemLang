package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.output.helpers.*;
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
import com.rem.output.helpers.OutputClass;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Token;
import com.rem.output.helpers.OutputCall;
import com.rem.output.helpers.OutputExact;
import java.lang.StringBuilder;
import com.rem.output.helpers.OutputStaticCall;
import com.rem.output.helpers.OutputType;
import com.rem.output.helpers.OutputArguments;
import com.rem.output.helpers.OutputQuote;
import com.rem.output.helpers.OutputHelper;
import com.rem.output.helpers.OutputVariable;
import com.rem.output.helpers.OutputMethod;
import com.rem.output.helpers.OutputNewObject;
import com.rem.output.helpers.OutputParameters;
import com.rem.output.helpers.OutputBody;
import com.rem.output.helpers.Output;
import com.rem.output.helpers.OutputContext;
import com.rem.output.helpers.OutputConcatenation;
import com.rem.output.helpers.OutputCast;
import com.rem.output.helpers.CallableOutput;
import com.rem.output.helpers.OutputBraced;
import com.rem.output.helpers.OutputString;
public class  Classwise   {
	public static class classes {
	}
	public static Classwise variables = null;
	public static Classwise methods = null;
	//Externals


	//Internals
protected String mainClassName = "MainFlow";
protected File innerDirectory = null;
protected File outerDirectory = null;
protected OutputClass mainClass = null;
protected String sourceDirectory = null;
protected final Parser pulsar = null;

	public String getMainClassName()  {
		return mainClassName;
	}
	public String get_mainClassName()  {
		return mainClassName;
	}
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
	public OutputClass getMainClass()  {
		return mainClass;
	}
	public OutputClass get_mainClass()  {
		return mainClass;
	}
	public String getSourceDirectory()  {
		return sourceDirectory;
	}
	public String get_sourceDirectory()  {
		return sourceDirectory;
	}
	public Parser getPulsar()  {
		return pulsar;
	}
	public Parser get_pulsar()  {
		return pulsar;
	}
public void setup(final Parser.Result result)  {
	String fileName = result.getFileName();
	final Integer indexOfDot = fileName.lastIndexOf(".");
	if (indexOfDot > -1 ) {
		fileName = fileName.substring(0,indexOfDot);
	}
	int indexOfSlash = fileName.lastIndexOf("/");
	if (indexOfSlash > -1 ) {
		fileName = fileName.substring(indexOfSlash + 1);
	}
	indexOfSlash = fileName.lastIndexOf("\\");
	if (indexOfSlash > -1 ) {
		fileName = fileName.substring(indexOfSlash + 1);
	}
	fileName = FlowController.camelize(fileName.toString());
	mainClassName = fileName;
	innerDirectory = new File("../" + fileName + "/src");
	innerDirectory.mkdirs();
	outerDirectory = new File("../" + fileName + "/src",MainFlow.variables.get_innerPackageName().replace(".","/"));
	outerDirectory.mkdirs();
	sourceDirectory = "../" + fileName + "/src/";
}
public void importAllClasses(final Token input)  {
	for (final Token element :  input.getAllSafely("anonymous_classes")) {
		final OutputCall classPackageName = new OutputCall();
		for (final Token packageElement :  element.getAllSafely("packageName")) {
			if (packageElement.get("NAME") != null) {
				classPackageName.add(new OutputExact(packageElement.get("NAME").toString()));
			}
			else if (packageElement.get("quote") != null) {
				classPackageName.add(new OutputExact(packageElement.get("quote").toString()));
			}
			else if (packageElement.get("statement_as_string") != null) {
				classPackageName.add(MainFlow.variables.get_body_gen().statement(packageElement.get("statement_as_string").get("body_statement"),true,mainClass));
			}
		}
		for (final Token anonymousClass :  element.getAllSafely("anonymous_class")) {
			defineAnonymousClass(anonymousClass,true,classPackageName.evaluate(),"");
		}
	}
}
public void defineAnonymousClass(final Token anonymousClass,final Boolean isFirst,final String anonymousPackageName,final String parentClass)  {
	final StringBuilder currentClass = new StringBuilder();
	currentClass.append(parentClass);
	for (final Token className :  anonymousClass.getAllSafely("className")) {
		if (isFirst == false) {
			currentClass.append(".");
		}
		currentClass.append(className);
		final String currentClassValue = currentClass.toString();
		MainFlow.variables.get_setupClassList().add(new OutputStaticCall().set(new OutputType("com.rem.output.helpers.OutputHelper")).add(new OutputExact("suppliment"),new OutputArguments().add(new OutputQuote(currentClassValue.toString())).add(new OutputQuote(anonymousPackageName.toString()))));
		OutputHelper.suppliment(currentClassValue.toString(),anonymousPackageName.toString());
	}
	for (final Token subAnonymousClass :  anonymousClass.getAllSafely("anonymous_class")) {
		defineAnonymousClass(subAnonymousClass,false,anonymousPackageName,currentClass.toString());
	}
}
public void findAllClasses(final Token input)  {
	for (final Token element :  input.getAllSafely("import_imports")) {
		importAllClasses(element);
	}
	for (final Token element :  input.getAllSafely("import_clws")) {
		findAllClasses(element);
	}
	for (final Token element :  input.getAllSafely("class_declaration")) {
		MainFlow.variables.get_classGenerator().collectClassNames(element);
	}
}
public void generateGlobals(final Token input)  {
	for (final Token element :  input.getAllSafely("import_clws")) {
		generateGlobals(element);
	}
	for (final Token element :  input.getAllSafely("variable_declaration")) {
		final OutputVariable newVariable = MainFlow.variables.get_variable().declaration(element,true,mainClass);
		newVariable.isStatic().isPublic();
		mainClass.addVariable(newVariable);
		MainFlow.variables.get_variable().addDefinedVariableName(newVariable);
	}
	for (final Token element :  input.getAllSafely("method_declaration")) {
		final OutputMethod newMethod = MainFlow.variables.get_method().declaration(element,true,mainClass);
		newMethod.isStatic();
		mainClass.addMethod(newMethod);
		MainFlow.variables.get_method().addDefinedMethodName(newMethod);
	}
}
public void generateAll(final Token input)  {
	for (final Token element :  input.getAllSafely("import_clws")) {
		generateAll(element);
	}
	final String packageFileName = MainFlow.variables.get_packageName().replace(".",File.separator);
	for (final Token element :  input.getAllSafely("class_declaration")) {
		MainFlow.variables.get_classGenerator().collectClassNames(element);
	}
	for (final Token element :  input.getAllSafely("class_declaration")) {
		final OutputClass innerClass = new OutputClass();
		final OutputClass outerClass = MainFlow.variables.get_classGenerator().declaration(element,innerClass,element.get("inner") == null,null);
		if (element.get("inner") == null) {
			innerClass.addVariable(new OutputVariable().isStatic().set(new OutputType(innerClass.getName()),new OutputExact("_")).assign(outerClass.stasis()));
			MainFlow.variables.get_setupClassList().add(new OutputStaticCall().set(new OutputType("com.rem.output.helpers.OutputHelper")).add(new OutputExact("addOutputClass"),new OutputArguments().add(new OutputStaticCall().set(new OutputType(outerClass.getName())).add(new OutputExact("_")))));
		}
		OutputHelper.addOutputClass(innerClass);
	}
}
public void generate(final Parser.Result result)  {
	final String mainPackageName = sourceDirectory;
	final String mainFlow = "MainFlow";
	final String parserTypePath = "com.rem.gen.parser.Parser";
	final String resultTypePath = "com.rem.gen.parser.Parser.Result";
	final String passTypePath = "com.rem.gen.parser.Parser.Result.Pass";
	mainClass = new OutputClass()._package(new OutputExact(MainFlow.variables.get_innerPackageName())).name(new OutputExact(mainClassName)).variable(new OutputVariable().isStatic().set(new OutputType(mainClassName),new OutputExact("self")).assign(new OutputNewObject().set(new OutputType(mainClassName),new OutputArguments()))).method(new OutputMethod().isStatic().set(new OutputType("void"),new OutputExact("main")).parameters(new OutputParameters().add(new OutputVariable(new OutputType("String").array(),new OutputExact("args")))).body(new OutputBody().add(new OutputStaticCall().set(new OutputType("com.rem.output.helpers.OutputHelper")).add(new OutputExact("parse"),new OutputArguments().add(new OutputExact("args")).add(new OutputNewObject().set(new OutputType(parserTypePath),new OutputArguments())).add(new OutputExact(mainClassName+"::init")).add(new OutputExact(mainClassName+"::setup")).add(new OutputExact(mainClassName+"::generate")))))).method(new OutputMethod().isStatic().set(new OutputType("void"),new OutputExact("init")).parameters(new OutputParameters().add(new OutputVariable(new OutputType("com.rem.gen.parser.Parser").add(new OutputType("Result")).add(new OutputType("Pass")),new OutputExact("result")))).body(MainFlow.variables.get_setupClassList()));
	findAllClasses(((Parser . Result . Pass) result) . getRoot());
	generateGlobals(((Parser . Result . Pass) result) . getRoot());
	generateAll(((Parser . Result . Pass) result) . getRoot());
	OutputHelper.addOutputClass(mainClass);
}
public Output name_var(final Token input,final boolean isInner,final OutputContext context)  {
	if (input.get("tokenAccess") != null) {
		return tokenAccess(input.get("tokenAccess"),isInner,context);
	}
	else  {
		final OutputConcatenation result = new OutputConcatenation();
		for (final Token atom :  input.getAllSafely("name_atom")) {
			result.add(name_atom(atom,isInner,context));
		}
		if (input.get("cast_statement") != null) {
			final OutputCast cast = new OutputCast();
			for (final Token atom :  input.getAllSafely("cast_statement")) {
				cast.type(MainFlow.variables.get_classwise().all_type(atom.get("all_type_name"),isInner,context));
			}
			return cast.subject(result);
		}
		else  {
			return result;
		}
	}
}
public Output tokenAccess(final Token element,final boolean isInner,final OutputContext context)  {
	final CallableOutput call = new OutputCall().add(name_atom(element.get("name_atom"),isInner,context));
	for (final Token atom :  element.getAllSafely("access")) {
		final String name = atom.get("NAME").toString();
		if (atom.get("get") != null) {
			call.add(new OutputExact("get"),new OutputArguments().add(new OutputExact("com.rem.gen.parser.Token.Id._" + name)));
		}
		else if (atom.get("getAllSafely") != null) {
			call.add(new OutputExact("getAllSafely"),new OutputArguments().add(new OutputExact("com.rem.gen.parser.Token.Id._" + name)));
		}
	}
	return call;
}
public Output name_atom(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	if (input.get("statement_as_char") != null) {
		if (input.get("statement_as_char").get("value").toString().equals("\\")) {
			return new OutputBraced().set(new OutputExact("\\")).style("\'","\'");
		}
		else if (input.get("statement_as_char").get("value").toString().equals("\"")) {
			return new OutputBraced().set(new OutputExact("\"")).style("\'","\'");
		}
		else if (input.get("statement_as_char").get("value").toString().equals("\'")) {
			return new OutputBraced().set(new OutputExact("\'")).style("\'","\'");
		}
		else  {
			return new OutputBraced().set(new OutputExact(input.get("statement_as_char").get("value").toString())).style("\'","\'");
		}
	}
	else if (input.get("statement_as_method") != null) {
		if (isInner) {
			return MainFlow.variables.get_body_gen().statement(input.get("statement_as_method").get("body_statement"),true,parentContext);
		}
		else  {
			return MainFlow.variables.get_body_gen().statement(input.get("statement_as_method").get("body_statement"),true,parentContext).vibrate();
		}
	}
	else if (input.get("statement_as_quote") != null) {
		final Output asQuoteStatement = MainFlow.variables.get_body_gen().statement(input.get("statement_as_quote").get("body_statement"),isInner,parentContext);
		if (isInner) {
			return new OutputQuote().set(asQuoteStatement);
		}
		else  {
			return new OutputQuote().set(asQuoteStatement.vibrate()).stasis();
		}
	}
	else if (input.get("statement_as_string") != null) {
		final Output result = new OutputString().set(MainFlow.variables.get_body_gen().statement(input.get("statement_as_string").get("body_statement"),true,parentContext).vibrate());
		return result;
	}
	else if (input.get("quote") != null) {
		if (isInner) {
			return new OutputQuote().set(input.get("quote").toString());
		}
		else  {
			return new OutputQuote().set(input.get("quote").toString()).stasis();
		}
	}
	else if (input.get("NUMBER") != null) {
		return new OutputExact(input.get("NUMBER").toString());
	}
	else if (input.get("variable_names") != null) {
		String value = input.get("variable_names").toString();
		if (isInner && mainClass.hasVariable(value) && (parentContext == null || parentContext.hasVariableInContext(value) == false)) {
			value = mainClassName + "." + value;
			return new OutputExact(value);
		}
		else  {
			return new OutputExact(value);
		}
	}
	else  {
		input.err();
		return null;
	}
}
public OutputType type_var(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	final OutputType type = new OutputType();
	for (final Token element :  input.getAll()) {
		if (element.getName().equals("type_atom")) {
			type.add(type_atom(element,isInner,parentContext));
		}
		else if (element.getName().equals("template_parameters")) {
			for (final Token quark :  element.getAllSafely("template_parameter")) {
				type.template(all_type(quark,isInner,parentContext));
			}
		}
	}
	return type;
}
public OutputType type_atom(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	if (input.get("statement_as_method") != null) {
		return new OutputType(MainFlow.variables.get_body_gen().statement(input.get("statement_as_method").get("body_statement"),true,parentContext));
	}
	else if (input.get("statement_as_string") != null) {
		return new OutputType(new OutputString().set(MainFlow.variables.get_body_gen().statement(input.get("statement_as_string").get("body_statement"),true,parentContext)));
	}
	else if (input.get("class") != null) {
		if (input.get("class").get("class_variable_names") != null) {
			return new OutputType(new OutputCall().add(new OutputExact(input.get("class").get("class_variable_names").toString())).add(new OutputExact("getFullName"),new OutputArguments()));
		}
		else  {
			return new OutputType(input.get("class").get("class_names").toString());
		}
	}
	else  {
		return null;
	}
}
public OutputType all_type(final Token input,final Boolean isInner,final OutputContext parentContext)  {
	if (input.get("non_class_name") != null) {
		final Token element = input.get("non_class_name").getAll().get(0);
		if (element.getName().startsWith("O")) {
			return new OutputType(new OutputExact("com.rem.output.helpers." + element.getName()));
		}
		else if (element.getName().startsWith("Callable")) {
			return new OutputType(new OutputExact("com.rem.output.helpers.CallableOutput"));
		}
		else if (element.getName().startsWith("Lineable")) {
			return new OutputType(new OutputExact("com.rem.output.helpers.LineableOutput"));
		}
		else  {
			if (element.getName().equals("Id")) {
				return new OutputType(new OutputExact("com.rem.gen.parser.Token.Id"));
			}
			else if (element.getName().equals("Result")) {
				return new OutputType(new OutputExact("com.rem.gen.parser.Parser.Result"));
			}
			else if (element.getName().equals("Pass")) {
				return new OutputType(new OutputExact("com.rem.gen.parser.Parser.Result.Pass"));
			}
			else if (element.getName().equals("Fail")) {
				return new OutputType(new OutputExact("com.rem.gen.parser.Parser.Result.Fail"));
			}
			else  {
				return new OutputType(new OutputExact("com.rem.gen.parser." + element.getName()));
			}
		}
	}
	else if (input.get("type_var") != null) {
		return type_var(input.get("type_var"),isInner,parentContext);
	}
	else  {
		if (input.get("all_type_name") != null) {
			return all_type(input.get("all_type_name"),isInner,parentContext);
		}
		else  {
			return null;
		}
	}
}

}