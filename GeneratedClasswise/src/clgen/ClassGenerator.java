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
import com.rem.crg.parser.Token;
import com.rem.parser.generation.classwise.ExternalStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.StringBuilder;
import clgen.TypeStatement;
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
public ExternalClassEntry declaration(final Token declaration,final Boolean mustInner,final ExternalContext parentContext)  {
	final Boolean isInner = declaration.get("inner") != null || mustInner;
	final ExternalStatement classPackageName = new ExternalStatement();
	classPackageName.set(".");
	final ExternalClassEntry newClass = new ExternalClassEntry();
	if (declaration.get("className").get("NAME") != null) {
		newClass.setName(new ExternalStatement(new StringEntry("\"" + declaration.get("className").toString() + "\"")));
	}
	else  {
		newClass.setName(new ExternalStatement(new StringEntry(declaration.get("className").toString())));
	}
	for (final Token element :  declaration.getAllSafely("packageName")) {
		final NameVar nameVar = new NameVar();
		MainFlow.variables.get_classwise().name_var(element,nameVar,isInner,parentContext);
		classPackageName.add(nameVar.getAsStatement());
	}
	newClass.setPackageName("");
	for (final Token element :  declaration.getAllSafely("parentName")) {
		final Type parentType = new Type();
		MainFlow.variables.get_classwise().type_var(element.get("type_var"),parentType,isInner,parentContext);
		newClass.setParentClass(parentType.getAsStatement());
	}
	for (final Token element :  declaration.getAllSafely("interfaceName")) {
		final Type interfaceType = new Type();
		MainFlow.variables.get_classwise().type_var(element.get("type_var"),interfaceType,isInner,parentContext);
		newClass.addImplementingInterface(interfaceType.getAsStatement());
	}
	for (final Token element :  declaration.getAllSafely("variable_declaration")) {
		newClass.addVariable(MainFlow.variables.get_variable().declaration(element,isInner,parentContext));
	}
	for (final Token element :  declaration.getAllSafely("method_declaration")) {
		newClass.addMethod(MainFlow.variables.get_method().declaration(element,isInner,parentContext));
	}
	for (final Token element :  declaration.getAllSafely("class_declaration")) {
		newClass.addSubClass(MainFlow.variables.get_classGenerator().declaration(element,isInner,parentContext));
	}
	newClass.setupContext();
	return newClass;
}

}