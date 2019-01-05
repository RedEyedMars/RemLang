package com.rem.gen;
import java.util.Set;
import java.util.HashSet;
public class VariableGenerator {
	protected static Set<String> definedVariableNames = new HashSet<String>();
	public static void addDefinedVariableName(com.rem.output.helpers.OutputVariable definedVariable){
		definedVariableNames.add(definedVariable.getName().evaluate());
	}
	public static com.rem.output.helpers.OutputVariable declaration(com.rem.gen.parser.Token declaration,boolean mustInner,com.rem.output.helpers.OutputContext parentContext){
		Boolean isInner = mustInner||declaration.get(com.rem.gen.parser.Token.Id._inner)!=null;
		com.rem.output.helpers.OutputVariable newVariable = new com.rem.output.helpers.OutputVariable();
		com.rem.output.helpers.OutputType type = Classwise2.classwise.all_type(declaration.get(com.rem.gen.parser.Token.Id._typeName),isInner,parentContext);
		for(com.rem.gen.parser.Token element:declaration.getAllSafely(com.rem.gen.parser.Token.Id._ARRAY_TYPE)){
			type.array();
		}
		if(declaration.get(com.rem.gen.parser.Token.Id._INLINE_LIST)!=null){
			type.isInlineList();
		}
		if(declaration.get(com.rem.gen.parser.Token.Id._method_argument)!=null){
			newVariable.assign(Classwise2.body_gen.argument(declaration.get(com.rem.gen.parser.Token.Id._method_argument),isInner,parentContext));
		}
		if(declaration.get(com.rem.gen.parser.Token.Id._isFinal)!=null){
			newVariable.isFinal();
		}
		if(declaration.get(com.rem.gen.parser.Token.Id._static)!=null){
			newVariable.isStatic();
			newVariable.isPublic();
		}
		if(declaration.get(com.rem.gen.parser.Token.Id._variableName).get(com.rem.gen.parser.Token.Id._NAME)!=null||isInner){
			newVariable.set(type,new com.rem.output.helpers.OutputExact((declaration.get(com.rem.gen.parser.Token.Id._variableName)).toString()));
		}
		else{
			newVariable.set(type,Classwise2.classwise.name_var(declaration.get(com.rem.gen.parser.Token.Id._variableName).get(com.rem.gen.parser.Token.Id._name_var),isInner,parentContext));
		}
		if(isInner){
			newVariable.isStatic();
		}
		return newVariable;
	}
	public static com.rem.output.helpers.LineableOutput assignment(com.rem.gen.parser.Token input,Boolean isInner,com.rem.output.helpers.OutputContext parentContext){
		return new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputOperator().left(Classwise2.classwise.name_var(input.get(com.rem.gen.parser.Token.Id._name_var),isInner,parentContext)).operator("=").right(Classwise2.body_gen.argument(input.get(com.rem.gen.parser.Token.Id._method_argument),isInner,parentContext))));
	}
}
