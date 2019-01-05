package com.rem.gen;
import java.util.Set;
import java.util.HashSet;
public class ClassGenerator {
	protected static Set<String> definedClassNames = new HashSet<String>();
	public static void addDefinedClassName(String className){
		definedClassNames.add(className);
	}
	public static Boolean hasDefinedClassName(String className){
		return definedClassNames.contains(className);
	}
	public static com.rem.output.helpers.OutputClass declaration(com.rem.gen.parser.Token declaration,com.rem.output.helpers.OutputClass innerClass,boolean mustInner,com.rem.output.helpers.OutputContext parentContext){
		Boolean isInner = declaration.get(com.rem.gen.parser.Token.Id._inner)!=null||mustInner;
		com.rem.output.helpers.OutputClass outerClass = new com.rem.output.helpers.OutputClass();
		if(declaration.get(com.rem.gen.parser.Token.Id._className).get(com.rem.gen.parser.Token.Id._NAME)!=null){
			outerClass.name(new com.rem.output.helpers.OutputExact((declaration.get(com.rem.gen.parser.Token.Id._className)).toString()));
			innerClass.name(new com.rem.output.helpers.OutputExact((declaration.get(com.rem.gen.parser.Token.Id._className)).toString()));
		}
		else{
			outerClass.name(new com.rem.output.helpers.OutputConcatenation().add(Classwise2.classwise.name_var(declaration.get(com.rem.gen.parser.Token.Id._className).get(com.rem.gen.parser.Token.Id._name_var),true,parentContext)));
			innerClass.name(new com.rem.output.helpers.OutputConcatenation().add(Classwise2.classwise.name_var(declaration.get(com.rem.gen.parser.Token.Id._className).get(com.rem.gen.parser.Token.Id._name_var),true,parentContext)));
		}
		com.rem.output.helpers.OutputCall classPackageName = new com.rem.output.helpers.OutputCall();
		for(com.rem.gen.parser.Token element:declaration.getAllSafely(com.rem.gen.parser.Token.Id._packageName)){
			for(com.rem.gen.parser.Token atom:element.getAll()){
				switch(atom.getName()){
					case _NAME :{
						classPackageName.add(new com.rem.output.helpers.OutputExact((atom).toString()));
						break;
					}
					case _name_var :{
						classPackageName.add(Classwise2.classwise.name_var(atom,true,parentContext));
						break;
					}
				}
			}
		}
		outerClass._package(classPackageName);
		innerClass._package(Classwise2.innerPackageName);
		for(com.rem.gen.parser.Token element:declaration.getAllSafely(com.rem.gen.parser.Token.Id._templateTypeName)){
			outerClass.template(new com.rem.output.helpers.OutputExact((element).toString()));
			innerClass.template(new com.rem.output.helpers.OutputExact((element).toString()));
		}
		for(com.rem.gen.parser.Token element:declaration.getAllSafely(com.rem.gen.parser.Token.Id._parentName)){
			if(declaration.get(com.rem.gen.parser.Token.Id._inner)!=null){
				innerClass.extendType(Classwise2.classwise.type_var(element.get(com.rem.gen.parser.Token.Id._type_var),isInner,parentContext));
			}
			outerClass.extendType(Classwise2.classwise.type_var(element.get(com.rem.gen.parser.Token.Id._type_var),isInner,parentContext));
		}
		for(com.rem.gen.parser.Token element:declaration.getAllSafely(com.rem.gen.parser.Token.Id._interfaceName)){
			if(declaration.get(com.rem.gen.parser.Token.Id._inner)!=null){
				innerClass.implement(Classwise2.classwise.type_var(element.get(com.rem.gen.parser.Token.Id._type_var),isInner,parentContext));
			}
			outerClass.implement(Classwise2.classwise.type_var(element.get(com.rem.gen.parser.Token.Id._type_var),isInner,parentContext));
		}
		for(com.rem.gen.parser.Token element:declaration.getAllSafely(com.rem.gen.parser.Token.Id._variable_declaration)){
			if(isInner||element.get(com.rem.gen.parser.Token.Id._inner)!=null){
				innerClass.addVariable(Classwise2.variable.declaration(element,isInner,parentContext));
			}
			else{
				outerClass.addVariable(Classwise2.variable.declaration(element,isInner,parentContext));
			}
		}
		for(com.rem.gen.parser.Token element:declaration.getAllSafely(com.rem.gen.parser.Token.Id._method_declaration)){
			if(isInner||element.get(com.rem.gen.parser.Token.Id._inner)!=null){
				innerClass.addMethod(Classwise2.method.declaration(element,isInner,parentContext));
			}
			else{
				outerClass.addMethod(Classwise2.method.declaration(element,isInner,parentContext));
			}
		}
		for(com.rem.gen.parser.Token element:declaration.getAllSafely(com.rem.gen.parser.Token.Id._class_declaration)){
			com.rem.output.helpers.OutputClass subInnerClass = new com.rem.output.helpers.OutputClass();
			com.rem.output.helpers.OutputClass subOuterClass = Classwise2.classGenerator.declaration(element,subInnerClass,isInner,parentContext);
			if(element.get(com.rem.gen.parser.Token.Id._inner)==null){
				subInnerClass.addVariable(new com.rem.output.helpers.OutputVariable().isPublic().isStatic().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("com.rem.output.helpers.OutputClass")),new com.rem.output.helpers.OutputExact().set("OUTPUT")).assign(new com.rem.output.helpers.OutputCall().add(subOuterClass.stasis(),null).add(new com.rem.output.helpers.OutputExact().set("mark"),new com.rem.output.helpers.OutputArguments())));
				outerClass.encloseClass(subOuterClass.getAsVariable());
			}
			innerClass.encloseClass(subInnerClass);
		}
		if((declaration.get(com.rem.gen.parser.Token.Id._objectType)).toString().contains("interface")){
			if(isInner){
				innerClass.isInterface();
			}
			else{
				outerClass.isInterface();
			}
		}
		else if((declaration.get(com.rem.gen.parser.Token.Id._objectType)).toString().contains("enum")){
			if(isInner){
				innerClass.isEnum();
			}
			else{
				outerClass.isEnum();
			}
		}
		if(declaration.get(com.rem.gen.parser.Token.Id._weak)!=null){
			if(isInner){
				innerClass.isAbstract();
			}
			else{
				outerClass.isAbstract();
			}
		}
		if(declaration.get(com.rem.gen.parser.Token.Id._static)==null){
			outerClass.isStatic();
			innerClass.isStatic();
		}
		else{
			outerClass.isNonStatic();
			if(isInner){
				innerClass.isNonStatic();
			}
		}
		return outerClass;
	}
	public static void collectClassNames(com.rem.gen.parser.Token classToken){
		if(classToken.get(com.rem.gen.parser.Token.Id._className).get(com.rem.gen.parser.Token.Id._NAME)!=null&&classToken.get(com.rem.gen.parser.Token.Id._inner)==null){
			addDefinedClassName(com.rem.output.helpers.OutputHelper.camelize((classToken.get(com.rem.gen.parser.Token.Id._className).get(com.rem.gen.parser.Token.Id._NAME)).toString()));
		}
		for(com.rem.gen.parser.Token element:classToken.getAllSafely(com.rem.gen.parser.Token.Id._class_declaration)){
			collectClassNames(element);
		}
	}
}
