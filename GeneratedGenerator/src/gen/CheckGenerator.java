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

public class CheckGenerator extends Generator {

	private File directory = null;
	private Set<String> checkNames = (Set<String>)new HashSet<String>();
	private Map<String,IToken> checkTokens = (Map<String,IToken>)new HashMap<String,IToken>();
	private Map<String,List<ChequeEntry>> vars = (Map<String,List<ChequeEntry>>)new HashMap<String,List<ChequeEntry>>();


	public static final Element outlineElement = new Element("outline",new String[]{"package gen.checks;\n\n"+
			"import java.io.*;\n"+
			"import java.util.*;\n"+
			"import com.rem.parser.*;\n"+
			"import com.rem.parser.generation.*;\n"+
			"import com.rem.parser.token.*;\n"+
			"import gen.*;\n"+
			"import gen.entries.*;\n"+
			"import gen.properties.*;\n"+
			"import lists.*;\n\n"+
			"public class ",/*Class Name*/" implements ICheck {\n\tprivate String errorMessage;\n",/*Members*/"\n"+
			"\tpublic ",/*Class Name*/"(",/*Parameters*/",String errorMessage){\n\t\tthis.errorMessage = errorMessage;\n"+
			"",/*Assignment*/"\n\t}\n\n\tpublic void check(){\n\t\tif(!(",/*If Statement*/")){\n\t\t\tSystem.err.println(errorMessage);\n\t\t}\n\t}\n\n}"});
	public static final Element operatorElement = new Element("operator",new String[]{"",/*Left*/" ",/*Operator*/" ",/*Right*/""});
	public static final Element methodElement = new Element("method",new String[]{"",/*Left*/".",/*Method Name*/"(",/*Right*/")"});
	public static final Element NEGoperatorElement = new Element("NEGoperator",new String[]{"!(",/*Left*/" ",/*Operator*/" ",/*Right*/")"});
	public static final Element NEGmethodElement = new Element("NEGmethod",new String[]{"!",/*Left*/".",/*MethodName*/"(",/*Right*/")"});
	public static final Element constructorAssignmentElement = new Element("constructorAssignment",new String[]{"this.",/*Name*/" = ",/*Name*/";"});
	public static final Element memberElement = new Element("member",new String[]{"private ",/*Variable*/";"});
	public CheckGenerator(){
		addElement("outline",outlineElement);
		addElement("operator",operatorElement);
		addElement("method",methodElement);
		addElement("NEGoperator",NEGoperatorElement);
		addElement("NEGmethod",NEGmethodElement);
		addElement("constructorAssignment",constructorAssignmentElement);
		addElement("member",memberElement);
	}
	public void setup(ParseContext data){
		this.addPage();
		directory = new File(Generators.generator.getDirectory(),"checks");
		directory.mkdirs();
	}
	public void generate(ParseContext data){
		Generators.check.addPage();
		for(String checkName:checkNames){
			Generators.check.generateCheckClass(checkTokens.get(checkName),vars.get(checkName));
		}
	}
	public Entry generateCheckClass(IToken checkClass,List<ChequeEntry> entries){
		String checkNameVar = Generators.check.buildString(Generators.check.camelize(checkClass.get("checkName").getString()),"Check");
		StringEntry checkName = new StringEntry(checkNameVar);
		ListEntry parameters = new ListEntry();
		ListEntry members = new ListEntry();
		members.setDelimiter("");
		ListEntry assignment = new ListEntry();
		assignment.setDelimiter("");
		ListEntry ifStatement = new ListEntry();
		ifStatement.setDelimiter("");
		for(ChequeEntry param:entries){
			members.add(param.asMembers());
			parameters.add(param.asParameters());
			assignment.add(param.asAssignment());
			ifStatement.add(param.asIfPart());
		}
		Generators.check.addFile(directory,Generators.check.buildString(checkName.getString(),".java"),new ListEntry(checkName,members,checkName,parameters,assignment));
		Generators.check.addEntry(directory,Generators.check.buildString(checkName.getString(),".java"),"ret",new ListEntry(ifStatement));
		return null;
	}
	public Entry generateCheckCheck(IToken checkCheck,Integer tabs,String contextName,String contextSubName){
		String checkName = Generators.check.buildString(Generators.check.camelize(checkCheck.get("checkName").getString()),"Check");
		ChequeEntry checkEntry = null;
		Boolean addToVars = false;
		ListEntry methodParams = new ListEntry();
		if((!checkNames.contains(checkName))){
			checkNames.add(checkName);
			checkTokens.put(checkName,checkCheck);
			vars.put(checkName,new ArrayList<ChequeEntry>());
			addToVars = true;
		}
		Integer index = 1;
		for(IToken.Key elementKey:checkCheck.keySet()){
			if("parameter".equals(elementKey.getName())){
				IToken element = checkCheck.get(elementKey);
				VariableEntry leftVar = (VariableEntry)Generators.generator.getVariable(element.get("left").getString(),contextName,contextSubName,MethodEntry.TYPE_UNKNOWN,"Check (100)");
				VariableEntry rightVar = (VariableEntry)Generators.generator.getVariable(element.get("right").getString(),contextName,contextSubName,MethodEntry.TYPE_UNKNOWN,"Check (101)");
				IToken checkMethod = element.get("method");
				if((checkMethod == null)){
					checkEntry = (ChequeEntry)Generators.check.generateOperator(element.get("operator"),index,leftVar,rightVar);
				}
				else {
					checkEntry = new ChequeEntry(index,leftVar,new StringEntry(checkMethod.getString()),rightVar);
				}
				IToken con_op = element.get("con_op");
				if((con_op != null)){
					checkEntry.setContination(con_op.getString());
				}
				if((addToVars == true)){
					vars.get(checkName).add(checkEntry);
				}
				methodParams.add(checkEntry.asMethodArguments());
				index = index + 1;
			}
		}
		StringEntry new_method = new StringEntry("");
		new_method = MethodEntry.NEW_METHOD;
		return new TabEntry(tabs,new ListEntry(new ElementEntry(GeneratorGenerator.semicolonedElement,new ListEntry(new MethodEntry(new StringEntry("checks"),"add",new ListEntry(new MethodEntry(new_method,checkName,new ListEntry(methodParams,new QuoteEntry(checkCheck.get("errorMessage").getString())))))))));
	}
	public Entry generateOperator(IToken operator,Integer index,VariableEntry left,VariableEntry right){
		String leftType = left.getType();
		String rightType = right.getType();
		String leftName = left.getName();
		String rightName = right.getName();
		Boolean isPrimitive = (leftType.equals("Integer") || leftType.equals("Boolean") || rightType.equals("Integer") || rightType.equals("Boolean") || leftName.equals("null") || rightName.equals("null"));
		Integer operatorState = -1;
		if((operator.get("IS") != null)){
			if((operator.get("NOT") != null)){
				operatorState = 1;
			}
			else {
				operatorState = 0;
			}
		}
		else {
			operatorState = 2;
		}
		if((operatorState == 2)){
			left.changeType("Integer");
			right.changeType("Integer");
			return new ChequeEntry(index,left,operator.getString(),right);
		}
		Boolean leftHasType = (left.hasType());
		Boolean rightHasType = (right.hasType());
		if((leftHasType == false && rightHasType == true)){
			left.changeType(right.getType());
		}
		if((leftHasType == true && rightHasType == false)){
			right.changeType(left.getType());
		}
		if((isPrimitive == true)){
			if((operatorState == 0)){
				return new ChequeEntry(index,left,"==",right);
			}
			else {
				if((operatorState == 1)){
					return new ChequeEntry(index,left,"!=",right);
				}
			}
		}
		else {
			if((operatorState == 0)){
				return new ChequeEntry(index,left,new StringEntry("equals"),right);
			}
			else {
				if((operatorState == 1)){
					ChequeEntry ret = new ChequeEntry(index,left,new StringEntry("equals"),right);
					ret.negify();
					return ret;
				}
			}
		}
		return null;
	}

	public File getDirectory(){
		return directory;
	}

	public Set<String> getCheckNames(){
		return checkNames;
	}

	public Map<String,IToken> getCheckTokens(){
		return checkTokens;
	}

	public Map<String,List<ChequeEntry>> getVars(){
		return vars;
	}

	public String getName(){
		return "Check";
	}

	public void generateRoot(IToken root){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}