package com.rem.gen;
import java.util.Set;
import java.util.HashSet;
public class MethodGenerator {
	protected static Set<String> definedMethodNames = new HashSet<String>();
	public static void addDefinedMethodName(com.rem.output.helpers.OutputMethod newMethod){
		definedMethodNames.add(newMethod.getName().evaluate());
	}
	public static com.rem.output.helpers.OutputMethod declaration(com.rem.gen.parser.Token declaration,Boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		return definition(declaration,isInner,parentContext);
	}
	public static com.rem.output.helpers.OutputMethod definition(com.rem.gen.parser.Token input,Boolean mustInner,com.rem.output.helpers.OutputContext parentContext){
		Boolean isInner = mustInner||input.get(com.rem.gen.parser.Token.Id._inner)!=null;
		com.rem.output.helpers.OutputMethod newMethod = new com.rem.output.helpers.OutputMethod();
		newMethod.setParent(parentContext);
		com.rem.output.helpers.OutputType methodType = Classwise2.classwise.all_type(input.get(com.rem.gen.parser.Token.Id._all_type_name),isInner,parentContext);
		if(input.get(com.rem.gen.parser.Token.Id._ARRAY_TYPE)!=null){
			for(com.rem.gen.parser.Token element:input.getAllSafely(com.rem.gen.parser.Token.Id._ARRAY_TYPE)){
				methodType.array();
			}
		}
		if(input.get(com.rem.gen.parser.Token.Id._inline)!=null){
			if(input.get(com.rem.gen.parser.Token.Id._inline).get(com.rem.gen.parser.Token.Id._method_parameters)!=null){
				com.rem.output.helpers.OutputParameters parameters = new com.rem.output.helpers.OutputParameters();
				for(com.rem.gen.parser.Token parameter:input.get(com.rem.gen.parser.Token.Id._inline).get(com.rem.gen.parser.Token.Id._method_parameters).getAllSafely(com.rem.gen.parser.Token.Id._parameter)){
					parameters.add(Classwise2.variable.declaration(parameter,isInner,parentContext));
				}
				newMethod.parameters(parameters);
			}
		}
		else if(input.get(com.rem.gen.parser.Token.Id._variableParameters)!=null){
			newMethod.parametersAsVariable(Classwise2.body_gen.statement(input.get(com.rem.gen.parser.Token.Id._variableParameters).get(com.rem.gen.parser.Token.Id._statement_as_method).get(com.rem.gen.parser.Token.Id._body_statement),true,parentContext));
		}
		if(input.get(com.rem.gen.parser.Token.Id._throwsException)!=null){
			for(com.rem.gen.parser.Token element:input.get(com.rem.gen.parser.Token.Id._throwsException).getAllSafely(com.rem.gen.parser.Token.Id._exception)){
				newMethod._throws((element).toString());
			}
		}
		if(input.get(com.rem.gen.parser.Token.Id._body)!=null){
			com.rem.output.helpers.OutputBody methodBody = new com.rem.output.helpers.OutputBody();
			methodBody.setParent(parentContext);
			for(com.rem.gen.parser.Token element:input.get(com.rem.gen.parser.Token.Id._body).getAllSafely(com.rem.gen.parser.Token.Id._body_element)){
				methodBody.add(Classwise2.body_gen.element(element,isInner,methodBody));
			}
			newMethod.body(methodBody);
		}
		if(input.get(com.rem.gen.parser.Token.Id._methodName)==null){
			newMethod.set(methodType,new com.rem.output.helpers.OutputExact(""));
		}
		else if(input.get(com.rem.gen.parser.Token.Id._methodName).get(com.rem.gen.parser.Token.Id._NAME)!=null){
			newMethod.set(methodType,new com.rem.output.helpers.OutputExact((input.get(com.rem.gen.parser.Token.Id._methodName).get(com.rem.gen.parser.Token.Id._NAME)).toString()));
		}
		else{
			newMethod.set(methodType,Classwise2.classwise.name_var(input.get(com.rem.gen.parser.Token.Id._methodName).get(com.rem.gen.parser.Token.Id._name_var),isInner,parentContext));
		}
		if(input.get(com.rem.gen.parser.Token.Id._static)!=null||isInner){
			newMethod.isStatic();
		}
		if(input.get(com.rem.gen.parser.Token.Id._weak)!=null){
			newMethod.isAbstract();
		}
		return newMethod;
	}
}
