package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.parser.generation.classwise.*;
import com.rem.parser.generation.*;
import com.rem.parser.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.*;
import clgen.ClassGenerator;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import com.rem.gen.parser.Token;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import com.rem.parser.generation.classwise.ExternalStatement;
import clgen.TypeStatement;
import java.lang.StringBuilder;
public class  ClassGenerator   {
	public static class classes {
	}
	public static ClassGenerator variables = null;
	public static ClassGenerator methods = null;
	//Externals


	//Internals
protected final Set<String> definedClassNames = new HashSet<String>();
protected final Integer classIndex = 0;
protected Integer addVariableIndex = 0;
protected Integer addMethodIndex = 0;
protected Integer addSubClassIndex = 0;

	public Set<String> getDefinedClassNames()  {
		return definedClassNames;
	}
	public Set<String> get_definedClassNames()  {
		return definedClassNames;
	}
	public Integer getClassIndex()  {
		return classIndex;
	}
	public Integer get_classIndex()  {
		return classIndex;
	}
	public Integer getAddVariableIndex()  {
		return addVariableIndex;
	}
	public Integer get_addVariableIndex()  {
		return addVariableIndex;
	}
	public Integer getAddMethodIndex()  {
		return addMethodIndex;
	}
	public Integer get_addMethodIndex()  {
		return addMethodIndex;
	}
	public Integer getAddSubClassIndex()  {
		return addSubClassIndex;
	}
	public Integer get_addSubClassIndex()  {
		return addSubClassIndex;
	}
public void addDefinedClassName(final String className)  {
	definedClassNames.add(className);
}
public Boolean hasDefinedClassName(final String className)  {
	return definedClassNames.contains(className);
}
public void declaration(final Token declaration,final ExternalClassEntry innerClass,final ExternalClassEntry outerClass,final Boolean mustInner,final ExternalContext parentContext)  {
	final Boolean isInner = declaration.get("inner") != null || mustInner;
	final ExternalStatement classPackageName = new ExternalStatement();
	classPackageName.set(".");
	if (declaration.get("className").get("NAME") != null) {
		innerClass.setNameAsStatement(new ExternalStatement(new StringEntry("\"" + declaration.get("className").toString() + "\"")));
		innerClass.setName(declaration.get("className").toString());
		outerClass.setNameAsStatement(new ExternalStatement(new StringEntry("\"" + declaration.get("className").toString() + "\"")));
		outerClass.setName(declaration.get("className").toString());
	}
	else  {
		innerClass.setNameAsStatement(new ExternalStatement(new StringEntry("\"+" + declaration.get("className").toString() + "+\"")));
		innerClass.setNameAsStatement(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(declaration.get("className").toString())))));
		innerClass.setName(declaration.get("className").toString());
		outerClass.setNameAsStatement(new ExternalStatement(new StringEntry("\"+" + declaration.get("className").toString() + "+\"")));
		outerClass.setNameAsStatement(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(declaration.get("className").toString())))));
		outerClass.setName(declaration.get("className").toString());
	}
	for (final Token element :  declaration.getAllSafely("packageName")) {
		if (element.get("NAME") != null) {
			classPackageName.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.get("NAME").toString())))));
		}
		else if (element.get("quote") != null) {
			classPackageName.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.get("quote").toString())))));
		}
		else if (element.get("statement_as_string") != null) {
			classPackageName.add(MainFlow.variables.get_body().statement(element.get("statement_as_string").get("body_statement"),true,parentContext));
		}
	}
	for (final Token element :  declaration.getAllSafely("templateTypeName")) {
		innerClass.addTemplateType(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.toString())))));
		outerClass.addTemplateType(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(element.toString())))));
	}
	innerClass.setPackageName(classPackageName);
	outerClass.setPackageName(classPackageName);
	for (final Token element :  declaration.getAllSafely("parentName")) {
		final Type parentType = new Type();
		MainFlow.variables.get_classwise().type_var(element.get("type_var"),parentType,isInner,parentContext);
		innerClass.setParentClass(parentType.getAsStatement());
		outerClass.setParentClass(parentType.getAsStatement());
	}
	for (final Token element :  declaration.getAllSafely("interfaceName")) {
		final Type interfaceType = new Type();
		MainFlow.variables.get_classwise().type_var(element.get("type_var"),interfaceType,isInner,parentContext);
		innerClass.addImplementingInterface(interfaceType.getAsStatement());
		outerClass.addImplementingInterface(interfaceType.getAsStatement());
	}
	innerClass.setupContext();
	outerClass.setupContext();
	for (final Token element :  declaration.getAllSafely("variable_declaration")) {
		if (isInner || element.get("inner") != null) {
			final ExternalVariableEntry newVariable = MainFlow.variables.get_variable().declaration(element,isInner,parentContext);
			if (newVariable.isFinal() && element.get("IS_FINAL") == null) {
				newVariable.setIsFinal(false);
			}
			innerClass.addVariable(newVariable);
		}
		else  {
			outerClass.addVariable(MainFlow.variables.get_variable().declaration(element,isInner,parentContext));
		}
	}
	for (final Token element :  declaration.getAllSafely("method_declaration")) {
		if (isInner || element.get("inner") != null) {
			innerClass.addMethod(MainFlow.variables.get_method().declaration(element,isInner,parentContext));
		}
		else  {
			outerClass.addMethod(MainFlow.variables.get_method().declaration(element,isInner,parentContext));
		}
	}
	for (final Token element :  declaration.getAllSafely("class_declaration")) {
		final ExternalClassEntry subInnerClass = new ExternalClassEntry();
		final ExternalClassEntry subOuterClass = new ExternalClassEntry();
		MainFlow.variables.get_classGenerator().declaration(element,subInnerClass,subOuterClass,isInner,parentContext);
		if (element.get("inner") == null) {
			subInnerClass.addVariable(new ExternalVariableEntry(true,true, /*TypeName*/new ExternalStatement.TypeName(/*TypeName*/new ExternalStatement.TypeName(/*Enty*/new ExternalStatement(new StringEntry(subOuterClass.getName().toString())))),"", /*Name*/new ExternalStatement(new StringEntry("_")), /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(/*Concat*/new ExternalStatement("", /*Name*/new ExternalStatement(new StringEntry("new ")), /*Enty*/new ExternalStatement(new StringEntry(subOuterClass.getName().toString())))), /*Name*/new ExternalStatement(new StringEntry("()"))))))));
			subInnerClass.addInitMethodFromClass(subOuterClass);
			subInnerClass.setParentClass(new ExternalStatement.TypeName("ExternalClassEntry"));
			subInnerClass.removeInterfaces();
			outerClass.addSubClass(subOuterClass);
		}
		innerClass.addSubClass(subInnerClass);
	}
	if (declaration.get("objectType").toString().contains("interface")) {
		if (isInner) {
			innerClass.setIsInterface(true);
		}
		else  {
			outerClass.setIsInterface(true);
		}
	}
	else if (declaration.get("objectType").toString().contains("enum")) {
		if (isInner) {
			innerClass.setIsEnum(true);
		}
		else  {
			outerClass.setIsEnum(true);
		}
	}
	if (isInner == false) {
		outerClass.setIsStatic(declaration.get("weak") == null);
		innerClass.setIsStatic(true);
	}
}
public void collectClassNames(final Token classToken)  {
	if (classToken.get("className").get("NAME") != null && classToken.get("inner") == null) {
		addDefinedClassName(FlowController.camelize(classToken.get("className").get("NAME").toString()));
	}
	for (final Token element :  classToken.getAllSafely("class_declaration")) {
		collectClassNames(element);
	}
}

}