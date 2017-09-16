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

public class VariableGenerator extends Generator {



	public VariableGenerator(){
	}
	public Entry generateDeclaration(IToken declaration,Boolean isInner){
		if((isInner == false)){
			isInner = (declaration.get("inner") != null);
		}
		ICanAddSubClass typeName = (ICanAddSubClass)null;
		if((isInner == true)){
			typeName = new ITypeVarEntry();
		}
		else {
			typeName = new ETypeVarEntry();
		}
		List<IToken> atomTypeName = declaration.getAll("typeName");
		if(atomTypeName != null){
			for(IToken atom:atomTypeName){
				typeName.addSubClass(Generators.classwise.generateAllType(atom,isInner));
			}
		}
		Entry assignment = null;
		List<IToken> atomMethodArgument = declaration.getAll("method_argument");
		if(atomMethodArgument != null){
			for(IToken atom:atomMethodArgument){
				assignment = Generators.body.generateArgument(atom,isInner);
			}
		}
		Entry ret = (Entry)null;
		if((declaration.get("variableName").get("NAME") != null)){
			if((isInner == true)){
				ret = new IVariableEntry(typeName,new IExactEntry(new StringEntry(declaration.get("variableName").getString())),assignment);
			}
			else {
				ret = new EVariableEntry(typeName,new EExactEntry(new QuoteEntry(declaration.get("variableName").getString())),assignment);
			}
		}
		else {
			Entry vName = (Entry)Generators.classwise.generateNameVar(declaration.get("variableName").get("name_var"),isInner);
			if((isInner == true)){
				ret = new IVariableEntry(typeName,vName,assignment);
			}
			else {
				ret = new EVariableEntry(typeName,vName,assignment);
			}
		}
		if((declaration.get("WEAK") != null)){
			IFinalizable retAsFinalizable = (IFinalizable)ret;
			retAsFinalizable.setIsFinal(false);
		}
		if((declaration.get("static") != null)){
			IStatickable retAsStatickable = (IStatickable)ret;
			retAsStatickable.setIsStatic(true);
		}
		if((declaration.get("INLINE_LIST") != null)){
			IInlinelistable retAsInlineListable = (IInlinelistable)ret;
			retAsInlineListable.setIsInlineList(true);
		}
		return ret;
	}
	public Entry generateAssignment(IToken assignment,Boolean isInner){
		if((isInner == false)){
			isInner = (assignment.get("inner") != null);
		}
		Entry assignmentVar = null;
		List<IToken> atomMethodArgument = assignment.getAll("method_argument");
		if(atomMethodArgument != null){
			for(IToken atom:atomMethodArgument){
				assignmentVar = Generators.body.generateArgument(atom,isInner);
			}
		}
		Entry vName = (Entry)Generators.classwise.generateNameVar(assignment.get("name_var"),isInner);
		if((isInner == true)){
			return new IOperatorEntry(vName,"=",assignmentVar);
		}
		else {
			return new EOperatorEntry(vName,"=",assignmentVar);
		}
	}


	public String getName(){
		return "Variable";
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