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
import clgen.ClassGenerator;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import com.rem.gen.parser.Token;
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
public OutputClass declaration(final Token declaration,final OutputClass innerClass,final Boolean mustInner,final OutputContext parentContext)  {
	final Boolean isInner = declaration.get("inner") != null || mustInner;
	final OutputClass outerClass = new OutputClass();
	if (declaration.get("className").get("NAME") != null) {
		outerClass.name(new OutputExact(declaration.get("className").toString()));
		innerClass.name(new OutputExact(declaration.get("className").toString()));
	}
	else  {
		outerClass.name(new OutputConcatenation().add(MainFlow.variables.get_classwise().name_var(declaration.get("className").get("name_var"),isInner,parentContext)));
		innerClass.name(new OutputConcatenation().add(MainFlow.variables.get_classwise().name_var(declaration.get("className").get("name_var"),isInner,parentContext)));
	}
	final OutputCall classPackageName = new OutputCall();
	for (final Token element :  declaration.getAllSafely("packageName")) {
		if (element.get("NAME") != null) {
			classPackageName.add(new OutputExact(element.get("NAME").toString()));
		}
		else if (element.get("name_var") != null) {
			classPackageName.add(MainFlow.variables.get_classwise().name_var(element.get("name_var"),true,parentContext));
		}
	}
	outerClass._package(classPackageName);
	innerClass._package(MainFlow.variables.get_innerPackageName());
	for (final Token element :  declaration.getAllSafely("templateTypeName")) {
		outerClass.template(new OutputExact(element.toString()));
		innerClass.template(new OutputExact(element.toString()));
	}
	for (final Token element :  declaration.getAllSafely("parentName")) {
		if (declaration.get("inner") != null) {
			innerClass.extendType(MainFlow.variables.get_classwise().type_var(element.get("type_var"),isInner,parentContext));
		}
		outerClass.extendType(MainFlow.variables.get_classwise().type_var(element.get("type_var"),isInner,parentContext));
	}
	for (final Token element :  declaration.getAllSafely("interfaceName")) {
		if (declaration.get("inner") != null) {
			innerClass.implement(MainFlow.variables.get_classwise().type_var(element.get("type_var"),isInner,parentContext));
		}
		outerClass.implement(MainFlow.variables.get_classwise().type_var(element.get("type_var"),isInner,parentContext));
	}
	for (final Token element :  declaration.getAllSafely("variable_declaration")) {
		if (isInner || element.get("inner") != null) {
			innerClass.addVariable(MainFlow.variables.get_variable().declaration(element,isInner,parentContext));
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
		final OutputClass subInnerClass = new OutputClass();
		final OutputClass subOuterClass = MainFlow.variables.get_classGenerator().declaration(element,subInnerClass,isInner,parentContext);
		if (element.get("inner") == null) {
			subInnerClass.addVariable(new OutputVariable().isStatic().set(new OutputType(subInnerClass.getName()),new OutputExact("_")).assign(subOuterClass));
			outerClass.encloseClass(subOuterClass);
		}
		innerClass.encloseClass(subInnerClass);
	}
	if (declaration.get("objectType").toString().contains("interface")) {
		if (isInner) {
			innerClass.isInterface();
		}
		else  {
			outerClass.isInterface();
		}
	}
	else if (declaration.get("objectType").toString().contains("enum")) {
		if (isInner) {
			innerClass.isEnum();
		}
		else  {
			outerClass.isEnum();
		}
	}
	if (declaration.get("weak") != null) {
		if (isInner) {
			innerClass.isAbstract();
		}
		else  {
			outerClass.isAbstract();
		}
	}
	if (isInner == false) {
		if (declaration.get("static") != null) {
			outerClass.isStatic();
		}
		innerClass.isStatic();
	}
	return outerClass;
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