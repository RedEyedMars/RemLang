package base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;

import base.GeneratorGenerator.VariableEntry;

import com.rem.parser.parser.*;


public class PropertyGenerator extends Generator{

	private File directory = null;
	private Map<String/*Property Name*/, Map<String,Entry>> methodsOfProperties = new HashMap<String,Map<String,Entry>>(); 
	private Map<String/*Property Name*/, Map<String,Entry>> variablesOfProperties = new HashMap<String,Map<String,Entry>>();
	private Map<String/*Property Name*/, Map<String,VariableEntry>> actualVariablesOfProperties = new HashMap<String,Map<String,VariableEntry>>();

	private Map<String, List<String>> variablesOfPropertiesContexts = new HashMap<String,List<String>>();
	private Map<String, List<ListEntry>> variablesOfPropertiesAcceptors = new HashMap<String,List<ListEntry>>();
	private Map<String, List<Set<String>>> variablesOfPropertiesRejectors = new HashMap<String,List<Set<String>>>();
	private Map<String, List<ListEntry>> methodsOfPropertiesAcceptors = new HashMap<String,List<ListEntry>>();
	private Map<String, List<Set<String>>> methodsOfPropertiesRejectors = new HashMap<String,List<Set<String>>>();

	public PropertyGenerator(){
		addElement("outline",new String[]{
				"package gen.properties;\n\n"+
						"import java.io.*;\n"+
						"import java.util.*;\n"+
						"import com.rem.parser.*;\n"+
						"import com.rem.parser.generation.*;\n"+
						"import com.rem.parser.token.*;\n"+
						"import gen.*;\n"+
						"import gen.checks.*;\n"+
						"import gen.properties.*;\n"+
						"import lists.*;\n\n"+
						"public interface ",/*Class Name*/" {\n",/*Contents*/"\n}"});

		addElement("variableMethod",new String[]{
				"public ",/*Type*/" get",/*Variable Name*/"();"});

		addElement("method",new String[]{
				"public ",/*Type*/" ",/*Method*/";"});
	}

	@Override
	public void generateRoot(IToken rootToken) {
		String propertyName = rootToken.get("propertyName").getString();
		String className = "I"+camelize(propertyName);
		addFile(directory,className+".java",new ListEntry(new StringEntry(className)));
		ListEntry constants = new ListEntry();
		constants.setDelimiter("");
		Map<String,VariableEntry> actualVariables = new LinkedHashMap<String,VariableEntry>();
		Map<String,Entry> propertyVariables = new LinkedHashMap<String,Entry>();
		Map<String,Entry> propertyMethods = new LinkedHashMap<String,Entry>();
		ListEntry bareProperty = new ListEntry();
		bareProperty.setDelimiter("");
		List<IToken> elements = rootToken.getAll("entry_class_element");
		Generators.generator.addContext(className, null, Generators.generator.getLocalContext(), Generators.generator.getNoDefaultToken());
		if(elements!=null){
			for(IToken element:elements){
				for(IToken.Key key:element.keySet()){
					if("constant_declaration".equals(key.getName())){
						IToken varDeclaration = element.get(key);
						String variableName = varDeclaration.get("variableName").getString();
						constants.add(new TabEntry(1,Generators.entryClass.generateVariableDeclaration(varDeclaration, className, true)));
						Generators.generator.getContexts().get(className).get(Generators.generator.getLocalContext()).get(variableName).setFinal(true);
						Generators.generator.getContexts().get(className).get(Generators.generator.getLocalContext()).get(variableName).setStatic(true);
					}
					else if("variable_declaration".equals(key.getName())){
						IToken varDeclaration = element.get(key);
						String variableName = varDeclaration.get("variableName").getString();
						propertyVariables.put(variableName,new TabEntry(1,Generators.entryClass.generateVariableDeclaration(varDeclaration, className, false)));
						propertyMethods.put(variableName,new TabEntry(0,Generators.entryClass.generateVariableDeclarationMethod(varDeclaration, className)));
						GeneratorGenerator.VariableEntry var = Generators.generator.getContexts().get(className).get(Generators.generator.getLocalContext()).get(variableName);
						actualVariables.put(variableName,var);
						GeneratorGenerator.TypeEntry type = Generators.generator.new TypeEntry(var);
						bareProperty.add(new TabEntry(1,new ElementEntry(Generators.property,"variableMethod", new ListEntry(type,new StringEntry(camelize(variableName))))));
					}
					else if("entry_method".equals(key.getName())){
						GeneratorGenerator.MethodEntry entry = (GeneratorGenerator.MethodEntry) Generators.entryClass.generateEntryMethodHeader(element.get(key), className);
						GeneratorGenerator.TypeEntry type = Generators.generator.new TypeEntry(entry);
						ListEntry method = new ListEntry(new StringEntry("\tpublic "),type,new StringEntry(" "),entry,Generators.entryClass.generateEntryMethodBody(element.get(key), entry, className, entry.getMethodName()));
						method.setDelimiter("");
						propertyMethods.put(entry.getMethodName(),new TabEntry(0,method));
						bareProperty.add(new TabEntry(1,new ElementEntry(Generators.property,"method", new ListEntry(type,entry))));						
					
					}
				}
			}
		}
		ListEntry complete = new ListEntry(constants,bareProperty);
		complete.setDelimiter("\n");
		addEntry(directory,className+".java","property",complete);
		
		methodsOfProperties.put(propertyName, propertyMethods);
		variablesOfProperties.put(propertyName, propertyVariables);
		actualVariablesOfProperties.put(propertyName, actualVariables);
		if(methodsOfPropertiesAcceptors.containsKey(propertyName)){
			List<ListEntry> acceptors = methodsOfPropertiesAcceptors.get(propertyName);
			List<Set<String>> rejects = methodsOfPropertiesRejectors.get(propertyName);
			for(int i=0;i<acceptors.size();++i){
				for(String key:propertyMethods.keySet()){
					if(!rejects.get(i).contains(key)){
						acceptors.get(i).add(propertyMethods.get(key));
					}
				}
			}
		}
		if(variablesOfPropertiesAcceptors.containsKey(propertyName)){
			List<ListEntry> acceptors = variablesOfPropertiesAcceptors.get(propertyName);
			List<Set<String>> rejects = variablesOfPropertiesRejectors.get(propertyName);
			for(int i=0;i<acceptors.size();++i){
				for(String key:propertyVariables.keySet()){
					if(!rejects.get(i).contains(key)){
						acceptors.get(i).add(propertyVariables.get(key));
					}
				}
			}
			for(String contextName:variablesOfPropertiesContexts.get(propertyName)){
				for(String variableName:Generators.generator.getContexts().get(contextName).get(Generators.generator.getLocalContext()).keySet()){
					System.out.println("PROPERTYGENERATOR>"+variableName);
				}
				for(String variableName:actualVariables.keySet()){
						Generators.generator.getContexts().get(contextName).get(Generators.generator.getLocalContext()).put(variableName, actualVariables.get(variableName));
					
				}
			}
		}
	}
	@Override
	public void setup(ParseContext data) {

		directory = new File(Generators.generator.getDirectory(),"properties");
		directory.mkdirs();
	}
	
	@Override
	public void generate(ParseContext data){		
		generateAll(data.getList("property_definitions").getNewTokens(),"property_dec");
	}

	@Override
	public String getName() {
		return "Property";
	}

	public Map<String,Map<String,Entry>> getPropertyMethods(){
		return this.methodsOfProperties;
	}

	public Map<String,Map<String,Entry>> getPropertyVariables() {
		return this.variablesOfProperties;
	}
	public Map<String,Map<String,VariableEntry>> getPropertyActualVariables() {
		return this.actualVariablesOfProperties;
	}

	public void addVariableListener(String proprety,String contextName,ListEntry acceptor, Set<String> rejects) {
		if(!variablesOfPropertiesAcceptors.containsKey(proprety)){
			variablesOfPropertiesAcceptors.put(proprety, new ArrayList<ListEntry>());
			variablesOfPropertiesRejectors.put(proprety, new ArrayList<Set<String>>());
			variablesOfPropertiesContexts.put(proprety, new ArrayList<String>());
		}
		variablesOfPropertiesAcceptors.get(proprety).add(acceptor);
		variablesOfPropertiesRejectors.get(proprety).add(rejects);
		variablesOfPropertiesContexts.get(proprety).add(contextName);
	}

	public void addMethodListener(String proprety,ListEntry acceptor, Set<String> rejects) {
		if(!methodsOfPropertiesAcceptors.containsKey(proprety)){
			methodsOfPropertiesAcceptors.put(proprety, new ArrayList<ListEntry>());
			methodsOfPropertiesRejectors.put(proprety, new ArrayList<Set<String>>());
		}
		methodsOfPropertiesAcceptors.get(proprety).add(acceptor);
		methodsOfPropertiesRejectors.get(proprety).add(rejects);
	}

	public String getPropertyMethodType(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
