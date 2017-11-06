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

public class ClazzGenerator extends Generator {

	private HashSet<String> definedClassNames = new HashSet<String>();


	public ClazzGenerator(){
	}
	public void addDefinedClassName(String className){
		definedClassNames.add(className);
	}
	public Entry generateDeclaration(IToken declaration,Boolean mustInner,ContextEntry parentContext){
		Boolean isInner = false;
		if((mustInner == true)){
			isInner = true;
		}
		else {
			isInner = (declaration.get("inner") != null);
		}
		ListEntry packageName = new ListEntry();
		packageName.setDelimiter(".");
		ListEntry methodBody = new ListEntry();
		methodBody.setDelimiter("");
		ListEntry variableBody = new ListEntry();
		variableBody.setDelimiter("");
		Entry parent = null;
		ListEntry interfaces = new ListEntry();
		ListEntry subClasses = new ListEntry();
		ContextEntry context = new ContextEntry(parentContext);
		List<IToken> elementVariableDeclaration = declaration.getAll("variable_declaration");
		if(elementVariableDeclaration != null){
			for(IToken element:elementVariableDeclaration){
				variableBody.add(Generators.variable.generateDeclaration(element,isInner,parentContext));
			}
		}
		List<IToken> elementMethodDeclaration = declaration.getAll("method_declaration");
		if(elementMethodDeclaration != null){
			for(IToken element:elementMethodDeclaration){
				methodBody.add(Generators.method.generateDeclaration(element,isInner,context));
			}
		}
		List<IToken> elementClassDeclaration = declaration.getAll("class_declaration");
		if(elementClassDeclaration != null){
			for(IToken element:elementClassDeclaration){
				subClasses.add(Generators.clazz.generateDeclaration(element,isInner,context));
			}
		}
		List<IToken> elementParentName = declaration.getAll("parentName");
		if(elementParentName != null){
			for(IToken element:elementParentName){
				List<IToken> atomTypeVar = element.getAll("type_var");
				if(atomTypeVar != null){
					for(IToken atom:atomTypeVar){
						parent = Generators.classwise.generateTypeVar(atom,isInner,2,context);
					}
				}
			}
		}
		List<IToken> elementInterfaceName = declaration.getAll("interfaceName");
		if(elementInterfaceName != null){
			for(IToken element:elementInterfaceName){
				List<IToken> atomTypeVar = element.getAll("type_var");
				if(atomTypeVar != null){
					for(IToken atom:atomTypeVar){
						interfaces.add(Generators.classwise.generateTypeVar(atom,isInner,2,context));
					}
				}
			}
		}
		List<IToken> elementPackageName = declaration.getAll("packageName");
		if(elementPackageName != null){
			for(IToken element:elementPackageName){
				List<IToken> atomNameVar = element.getAll("name_var");
				if(atomNameVar != null){
					for(IToken atom:atomNameVar){
						packageName.add(Generators.classwise.generateNameVar(atom,isInner));
					}
				}
			}
		}
		if((isInner == true)){
			IClassEntry iret = new IClassEntry(packageName,declaration.get("objectType").getString(),new StringEntry(declaration.get("className").getString()),parent,interfaces,variableBody,methodBody,context);
			for(Entry e:subClasses){
				IInnerable eAsInnerable = (IInnerable)e;
				if((eAsInnerable.getIsInner())){
					IClassEntry eAsIClass = (IClassEntry)e;
					iret.addSubClass(eAsIClass);
				}
				else {
					EClassEntry eAsEClass = (EClassEntry)e;
					iret.addSubClass(eAsEClass);
				}
			}
			return iret;
		}
		else {
			if((packageName.isEmpty())){
				packageName = null;
			}
			else {
				packageName.setDelimiter(".get(__BUILDER__);\n\t\t\t\t__BUILDER__.append(\".\");\n\t\t\t\t");
			}
			Entry className = (Entry)null;
			String actualName = null;
			if((declaration.get("className").get("NAME") != null)){
				className = new QuoteEntry(declaration.get("className").get("NAME").getString());
				actualName = declaration.get("className").get("NAME").getString();
			}
			else {
				if((declaration.get("className").get("class_variable_names") != null)){
					actualName = Generators.clazz.buildString(declaration.get("className").get("class_variable_names").getString(),".getName()");
					className = new StringEntry(actualName);
				}
				else {
					className = new StringEntry(declaration.get("className").get("variable_names").getString());
					actualName = declaration.get("className").get("variable_names").getString();
				}
			}
			EClassEntry eret = new EClassEntry(packageName,declaration.get("objectType").getString(),actualName,className,declaration.get("templateTypeName"),parent,interfaces,variableBody,methodBody,context);
			for(Entry e:subClasses){
				IInnerable eAsInnerable = (IInnerable)e;
				if((eAsInnerable.getIsInner())){
					IClassEntry eAsIClass = (IClassEntry)e;
					eret.addSubClass(eAsIClass);
				}
				else {
					EClassEntry eAsEClass = (EClassEntry)e;
					eret.addSubClass(eAsEClass);
				}
			}
			if((declaration.get("WEAK") != null)){
				eret.setIsWeak(true);
			}
			return eret;
		}
	}

	public HashSet<String> getDefinedClassNames(){
		return definedClassNames;
	}

	public String getName(){
		return "Clazz";
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