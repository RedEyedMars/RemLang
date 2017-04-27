package base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rem.parser.ParseContext;
import com.rem.parser.generation.ElementEntry;
import com.rem.parser.generation.Entry;
import com.rem.parser.generation.Generator;
import com.rem.parser.generation.ListEntry;
import com.rem.parser.generation.QuoteEntry;
import com.rem.parser.generation.StringEntry;
import com.rem.parser.generation.TabEntry;
import com.rem.parser.token.IToken;

import base.GeneratorGenerator.MethodEntry;


public class CheckGenerator extends Generator {

	private File directory = null;
	private Set<String> checkNames = new HashSet<String>();
	private Map<String,IToken> checkTokens = new HashMap<String,IToken>();

	private Map<String,List<CheckEntry>> vars = new HashMap<String,List<CheckEntry>>();
	public  CheckGenerator(){

		addElement("outline",new String[]{
				"package gen.checks;\n\n"+
						"import java.io.*;\n"+
						"import java.util.*;\n"+
						"import com.rem.parser.*;\n"+
						"import com.rem.parser.generation.*;\n"+
						"import com.rem.parser.token.*;\n"+
						"import gen.*;\n"+
						"import gen.entries.*;\n"+
						"import gen.properties.*;\n"+
						"import lists.*;\n\n"+
						"public class ",/*Class Name*/" implements ICheck {\n\tprivate String errorMessage;\n",
						/*Members*/"\n"+
						"\tpublic ",/*Class Name*/"(",/*Parameters*/",String errorMessage){\n\t\tthis.errorMessage = errorMessage;\n"+
						"",/*Assignment*/
						"\n\t}\n\n\tpublic void check(){\n\t\tif(",/*If Statement*/"){\n\t\t\tSystem.err.println(errorMessage);\n\t\t}\n\t}\n\n}"});
		addElement("operator",new String[]{
				"",/*Left*/" ",/*Operator*/" ",/*Right*/""});
		addElement("method",new String[]{
				"",/*Left*/".",/*MethodName*/"(",/*Right*/")"});
		addElement("NEGoperator",new String[]{
				"!(",/*Left*/" ",/*Operator*/" ",/*Right*/")"});
		addElement("NEGmethod",new String[]{
				"!",/*Left*/".",/*MethodName*/"(",/*Right*/")"});
		addElement("constructorAssignment",new String[]{
				"this.",/*Name*/" = ",/*Name*/";"});
		addElement("member",new String[]{
				"private ",/*Variable*/";"});
	}
	@Override
	public String getName() {
		return "check";
	}

	@Override
	public void setup(ParseContext data) {
		directory = new File(Generators.generator.getDirectory(),"checks");
		directory.mkdirs();		
	}

	@Override
	public void generate(ParseContext data) {
		addPage();
		for(String checkName:checkNames){
			generateClass(checkTokens.get(checkName),vars.get(checkName));
		}
	}

	@Override
	public void generateRoot(IToken check) {
	}

	public void generateClass(IToken check,List<CheckEntry> entries){
		StringEntry checkName = new StringEntry( camelize(check.get("checkName").getString())+ "Check");
		ListEntry parameters = new ListEntry();
		ListEntry members = new ListEntry();
		members.setDelimiter("");
		ListEntry assignment = new ListEntry();
		assignment.setDelimiter("");
		ListEntry ifStatement = new ListEntry();
		ifStatement.setDelimiter("");
		for(CheckEntry entry:entries){

			members.add(entry.asMembers());
			parameters.add(entry.asParameters());
			assignment.add(entry.asAssignment());
			ifStatement.add(entry.asIfPart());
		}
		addFile(directory,checkName.getString()+".java",new ListEntry(
				checkName,members,checkName,parameters,assignment));
		addEntry(directory,checkName.getString()+".java","ret",new ListEntry(ifStatement));
	}

	public Entry generateCheck(IToken check,int tabs,String contextName, String contextSubName){
		String checkName = camelize(check.get("checkName").getString())+ "Check";

		CheckEntry entry = null;
		boolean addToVars = false;
		ListEntry methodParams = new ListEntry();
		if(!checkNames.contains(checkName)){
			checkNames.add(checkName);
			checkTokens.put(checkName, check);
			vars.put(checkName, new ArrayList<CheckEntry>());
			addToVars = true;
		}
		int index = 1;
		for(IToken.Key key:check.keySet()){
			if("parameter".equals(key.getName())){
				GeneratorGenerator.VariableEntry leftVar = (GeneratorGenerator.VariableEntry)
						Generators.generator.getVariable(check.get(key).get("left").getString(),contextName,contextSubName,GeneratorGenerator.TYPE_UNKNOWN,"Check (100)");
				GeneratorGenerator.VariableEntry rightVar = (GeneratorGenerator.VariableEntry)
						Generators.generator.getVariable(check.get(key).get("right").getString(),contextName,contextSubName,GeneratorGenerator.TYPE_UNKNOWN,"Check (101)");
				IToken method = check.get(key).get("method");

				if(method==null){
					entry = (CheckEntry) generateOperator(check.get(key).get("operator"),index,leftVar,rightVar);
				}
				else {
					entry = new CheckEntry(index,leftVar,new StringEntry(method.getString()),rightVar);
				}
				IToken con_op = check.get(key).get("con_op");
				if(con_op!=null){
					entry.setContination(con_op.getString());
				}
				if(addToVars){
					vars.get(checkName).add(entry);
				}
				methodParams.add(entry.asMethodArguments());
				++index;
			}
		}

		return  
				new TabEntry(tabs,new ElementEntry(Generators.generator,"semicoloned",new ListEntry(Generators.generator.new MethodEntry(
						new StringEntry("checks"),"add",new ListEntry(
								Generators.generator.new MethodEntry(
										GeneratorGenerator.METHOD_NEW,checkName,new ListEntry(
												methodParams,
												new QuoteEntry(check.get("errorMessage").getString()))))))));
	}
	
	public Entry generateOperator(IToken operator,int index,GeneratorGenerator.VariableEntry left,GeneratorGenerator.VariableEntry right){
		boolean isPrimitive = left.getType().equals("Integer")||left.getType().equals("Boolean")||
				right.getType().equals("Integer")||right.getType().equals("Boolean")||
				left.getName().equals("null")||right.getName().equals("null");
		int operatorState = -1;
		if(operator.get("IS")!=null){
			if(operator.get("NOT")!=null){						
				operatorState = 1;
			}
			else {
				operatorState = 0;
			}
		}
		else {
			operatorState = 2;
		}
		if(operatorState==2){
			left.changeType("Integer");
			right.changeType("Integer");
			return new CheckEntry(index,left,operator.getString(),right);
		}
		if(!left.hasType()&&right.hasType()){
			left.changeType(right.getType());
		}
		else if(left.hasType()&&!right.hasType()){
			right.changeType(left.getType());
		}
		if(isPrimitive){							
			if(operatorState==0){
				return new CheckEntry(index,left,"==",right);
			}
			else if(operatorState==1){
				return new CheckEntry(index,left,"!=",right);
			}
		}
		else {
			if(operatorState==0){
				return new CheckEntry(index,left,new StringEntry("equals"),right);
			}
			else if(operatorState==1){
				CheckEntry entry = new CheckEntry(index,left,new StringEntry("equals"),right);
				entry.negify();
				return entry;
			}
		}
		return null;
	}

	private class CheckEntry extends ElementEntry {
		private GeneratorGenerator.VariableEntry left;
		private GeneratorGenerator.VariableEntry right;
		private Entry continuationOperator;
		private String leftName;
		private String rightName;
		private String eid;
		public CheckEntry(int index,GeneratorGenerator.VariableEntry left,String operator,GeneratorGenerator.VariableEntry right){
			super(Generators.check,"eid",new ListEntry(new StringEntry(left.getName()+index),new StringEntry(operator),new StringEntry(right.getName()+index)));
			this.left = left;
			this.right = right;
			this.leftName = left.getName()+index;
			this.rightName = right.getName()+index;
			this.eid = "operator";
		}
		public CheckEntry(int index,GeneratorGenerator.VariableEntry left,StringEntry method,GeneratorGenerator.VariableEntry right){
			super(Generators.check,"method",new ListEntry(new StringEntry(left.getName()+index),method,new StringEntry(right.getName()+index)));
			this.left = left;
			this.right = right;
			this.leftName = left.getName()+index;
			this.rightName = right.getName()+index;
			this.eid = "method";
		}

		public void negify() {
			this.setElementName(Generators.check,"NEG"+eid);
		}
		public void setContination(String op){
			if("and ".equals(op)){
				this.continuationOperator = new StringEntry("&&");
			}
			else {
				this.continuationOperator = new StringEntry("||");
			}
		}
		public Entry asIfPart(){
			if(continuationOperator!=null){
				ListEntry part = new ListEntry(continuationOperator,this);
				part.setDelimiter("");
				return part;
			}
			else {
				return this;
			}
		}
		public Entry asParameters(){
			GeneratorGenerator.VariableEntry l = null;
			GeneratorGenerator.VariableEntry r = null;
			if(left.hasType()){
				l = Generators.generator.new VariableEntry(leftName,left.getType());
			}
			else {
				l = Generators.generator.new VariableEntry(leftName,left.getDefaultType());
			}
			if(right.hasType()){
				r = Generators.generator.new VariableEntry(rightName,right.getType());
			}
			else {
				r = Generators.generator.new VariableEntry(rightName,right.getDefaultType());
			}
			return new ListEntry(l,r);
		}
		public Entry asMethodArguments(){

			Entry leftName = new StringEntry(this.leftName);
			Entry rightName = new StringEntry(this.rightName);
			return new ListEntry(leftName,rightName);
		}
		public Entry asAssignment(){

			Entry leftName = new StringEntry(this.leftName);
			Entry rightName = new StringEntry(this.rightName);
			ListEntry part = new ListEntry();
			part.setDelimiter("");
			part.add(new TabEntry(2,new ElementEntry(Generators.check,"constructorAssignment",new ListEntry(leftName,leftName))));
			part.add(new TabEntry(2,new ElementEntry(Generators.check,"constructorAssignment",new ListEntry(rightName,rightName))));
			return part;
		}
		public Entry asMembers(){

			GeneratorGenerator.VariableEntry l = null;
			GeneratorGenerator.VariableEntry r = null;
			if(left.hasType()){
				l = Generators.generator.new VariableEntry(leftName,left.getType());
			}
			else {
				l = Generators.generator.new VariableEntry(leftName,left.getDefaultType());
			}
			if(right.hasType()){
				r = Generators.generator.new VariableEntry(rightName,right.getType());
			}
			else {
				r = Generators.generator.new VariableEntry(rightName,right.getDefaultType());
			}
			ListEntry part = new ListEntry();
			part.setDelimiter("");
			part.add(new TabEntry(1,new ElementEntry(Generators.check,"member",new ListEntry(l))));
			part.add(new TabEntry(1,new ElementEntry(Generators.check,"member",new ListEntry(r))));
			return part;
		}
	}

}
