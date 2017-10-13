package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import gen.checks.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class MethodGenerator extends Generator {



	public MethodGenerator(){
	}
	public Entry generateDeclaration(IToken declaration,Boolean isInner,ContextEntry parentContext){
		List<IToken> elementMethodDefinition = declaration.getAll("methodDefinition");
		if(elementMethodDefinition != null){
			for(IToken element:elementMethodDefinition){
				return generateDefinition(element,isInner,parentContext);
			}
		}
		return null;
	}
	public Entry generateDefinition(IToken definition,Boolean isInner,ContextEntry parentContext){
		if((isInner == false)){
			isInner = (definition.get("inner") != null);
		}
		ICanAddSubClass typeName = (ICanAddSubClass)null;
		if((isInner == true)){
			typeName = new ITypeVarEntry();
		}
		else {
			typeName = new ETypeVarEntry();
		}
		List<IToken> atomTypeName = definition.getAll("typeName");
		if(atomTypeName != null){
			for(IToken atom:atomTypeName){
				typeName.addSubClass(Generators.classwise.generateAllType(atom,isInner));
			}
		}
		ListEntry methodBody = new ListEntry();
		methodBody.setDelimiter("");
		ListEntry parameters = new ListEntry();
		Boolean parametersAreStatement = false;
		ContextEntry bodyContext = new ContextEntry(parentContext);
		List<IToken> elementInline = definition.getAll("inline");
		if(elementInline != null){
			for(IToken element:elementInline){
				List<IToken> atomVariableDeclaration = element.getAll("variable_declaration");
				if(atomVariableDeclaration != null){
					for(IToken atom:atomVariableDeclaration){
						parameters.add(Generators.variable.generateDeclaration(atom,isInner));
					}
				}
			}
		}
		List<IToken> elementVariableParameters = definition.getAll("variableParameters");
		if(elementVariableParameters != null){
			for(IToken element:elementVariableParameters){
				parametersAreStatement = true;
				List<IToken> atomBodyStatement = element.getAll("body_statement");
				if(atomBodyStatement != null){
					for(IToken atom:atomBodyStatement){
						parameters.add(Generators.body.generateStatement(atom,true));
					}
				}
			}
		}
		List<IToken> elementBodyElement = definition.getAll("body_element");
		if(elementBodyElement != null){
			for(IToken element:elementBodyElement){
				Entry bodyElem = Generators.body.generateElement(element,isInner,bodyContext);
				if((bodyElem != null)){
					methodBody.add(bodyElem);
				}
			}
		}
		Entry ret = (Entry)null;
		if((definition.get("methodName").get("NAME") != null)){
			if((isInner == true)){
				ret = new IMethodEntry(typeName,new IExactEntry(new StringEntry(definition.get("methodName").getString())),parameters,methodBody,parentContext);
			}
			else {
				EMethodEntry eMethod = new EMethodEntry(typeName,new EExactEntry(new StringEntry(definition.get("methodName").getString())),parametersAreStatement,parameters,methodBody,parentContext);
				if((definition.get("static") != null)){
					eMethod.setIsStatic(true);
				}
				ret = eMethod;
			}
		}
		else {
			Entry vName = (Entry)Generators.classwise.generateNameVar(definition.get("methodName").get("name_var"),isInner);
			if((isInner == true)){
				ret = new IMethodEntry(typeName,vName,parameters,methodBody,parentContext);
			}
			else {
				EMethodEntry eMethod = new EMethodEntry(typeName,vName,parametersAreStatement,parameters,methodBody,parentContext);
				if((definition.get("static") != null)){
					eMethod.setIsStatic(true);
				}
				ret = eMethod;
			}
		}
		if((definition.get("ARRAY_TYPE") != null)){
			IArraytypeable retAsArrayTypeable = (IArraytypeable)ret;
			ListEntry arrayType = new ListEntry();
			arrayType.setDelimiter("");
			retAsArrayTypeable.setArrayType(arrayType);
			List<IToken> elementARRAYTYPE = definition.getAll("ARRAY_TYPE");
			if(elementARRAYTYPE != null){
				for(IToken element:elementARRAYTYPE){
					arrayType.add(new StringEntry("[]"));
				}
			}
		}
		return ret;
	}


	public String getName(){
		return "Method";
	}

		public void generateRoot(IToken root){
	}

		public void generate(ParseContext data){
	}

		public void setup(ParseContext context){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}