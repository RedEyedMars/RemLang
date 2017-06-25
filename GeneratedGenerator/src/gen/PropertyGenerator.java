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

public class PropertyGenerator extends Generator {

	private File directory = null;
	private Map<String,Map<String,Entry>> methodsOfProperties = (Map<String,Map<String,Entry>>)new HashMap<String,Map<String,Entry>>();
	private Map<String,Map<String,ITypeListener>> methodTypesOfProperties = (Map<String,Map<String,ITypeListener>>)new HashMap<String,Map<String,ITypeListener>>();
	private Map<String,Map<String,Entry>> variablesOfProperties = (Map<String,Map<String,Entry>>)new HashMap<String,Map<String,Entry>>();
	private Map<String,Map<String,VariableEntry>> actualVariablesOfProperties = (Map<String,Map<String,VariableEntry>>)new HashMap<String,Map<String,VariableEntry>>();
	private Map<String,List<String>> variablesOfPropertiesContexts = (Map<String,List<String>>)new HashMap<String,List<String>>();
	private Map<String,List<ListEntry>> variablesOfPropertiesAcceptors = (Map<String,List<ListEntry>>)new HashMap<String,List<ListEntry>>();
	private Map<String,List<Set<String>>> variablesOfPropertiesRejectors = (Map<String,List<Set<String>>>)new HashMap<String,List<Set<String>>>();
	private Map<String,List<ListEntry>> methodsOfPropertiesAcceptors = (Map<String,List<ListEntry>>)new HashMap<String,List<ListEntry>>();
	private Map<String,List<Set<String>>> methodsOfPropertiesRejectors = (Map<String,List<Set<String>>>)new HashMap<String,List<Set<String>>>();


	public static final Element outlineElement = new Element("outline",new String[]{"package gen.properties;\n\n"+
			"import java.io.*;\n"+
			"import java.util.*;\n"+
			"import com.rem.parser.*;\n"+
			"import com.rem.parser.generation.*;\n"+
			"import com.rem.parser.token.*;\n"+
			"import gen.*;\n"+
			"import gen.checks.*;\n"+
			"import gen.entries.*;\n"+
			"import lists.*;\n\n"+
			"public interface ",/*Class Name*/" extends Entry {\n",/*Contents*/"\n}"});
	public static final Element variableMethodElement = new Element("variableMethod",new String[]{"public ",/*Type*/" get",/*Variable Name*/"();"});
	public static final Element methodElement = new Element("method",new String[]{"public ",/*Type*/" ",/*Method*/";"});
	public PropertyGenerator(){
		addElement("outline",outlineElement);
		addElement("variableMethod",variableMethodElement);
		addElement("method",methodElement);
	}
	public void generateRoot(IToken root){
		String propertyName = root.get("propertyName").getString();
		String className = Generators.property.buildString("I",Generators.property.camelize(propertyName));
		Generators.property.addFile(directory,Generators.property.buildString(className,".java"),new ListEntry(new StringEntry(className)));
		ListEntry constants = new ListEntry();
		constants.setDelimiter("");
		Map<String,VariableEntry> actualVariables = (Map<String,VariableEntry>)new LinkedHashMap<String,VariableEntry>();
		Map<String,Entry> propertyVariables = (Map<String,Entry>)new LinkedHashMap<String,Entry>();
		Map<String,Entry> propertyMethods = (Map<String,Entry>)new LinkedHashMap<String,Entry>();
		Map<String,ITypeListener> propertyMethodTypes = (Map<String,ITypeListener>)new LinkedHashMap<String,ITypeListener>();
		ListEntry bareProperty = new ListEntry();
		bareProperty.setDelimiter("");
		Map<String,Map<String,Map<String,VariableEntry>>> contexts = (Map<String,Map<String,Map<String,VariableEntry>>>)Generators.generator.getContexts();
		String localContext = Generators.generator.LOCAL_CONTEXT;
		Generators.generator.addContext(className,null,Generators.generator.LOCAL_CONTEXT,Generators.generator.NO_DEFAULT_TOKEN);
		List<IToken> elementEntryClassElement = root.getAll("entry_class_element");
		if(elementEntryClassElement != null){
			for(IToken element:elementEntryClassElement){
				for(IToken.Key atomKey:element.keySet()){
					if("constant_declaration".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String variableName = atom.get("variableName").getString();
						constants.add(new TabEntry(1,new ListEntry(Generators.entryClass.generateVariableDeclaration(atom,className,true))));
						contexts.get(className).get(localContext).get(variableName).setFinal(true);
						contexts.get(className).get(localContext).get(variableName).setStatic(true);
					}
					else if("variable_declaration".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						String variableName = atom.get("variableName").getString();
						Entry variable = Generators.entryClass.generateVariableDeclaration(atom,className,false);
						MethodEntry method = (MethodEntry)Generators.entryClass.generateVariableDeclarationMethod(atom,className);
						propertyVariables.put(variableName,new TabEntry(1,new ListEntry(variable)));
						propertyMethods.put(method.getMethodName(),new TabEntry(0,new ListEntry(method)));
						VariableEntry realVar = (VariableEntry)contexts.get(className).get(localContext).get(variableName);
						actualVariables.put(variableName,realVar);
						TypeEntry type = new TypeEntry(realVar);
						String varClassName = Generators.property.camelize(variableName);
						bareProperty.add(new TabEntry(1,new ListEntry(new ElementEntry(PropertyGenerator.variableMethodElement,new ListEntry(type,new StringEntry(varClassName))))));
					}
					else if("entry_method".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						MethodEntry header = (MethodEntry)Generators.entryClass.generateEntryMethodHeader(atom,className);
						TypeEntry type = new TypeEntry(header);
						Entry methodBody = (Entry)Generators.entryClass.generateEntryMethodBody(atom,header,className,header.getMethodName());
						ListEntry method = new ListEntry(new StringEntry("\tpublic "),type,new StringEntry(" "),header,new StringEntry(" "),methodBody);
						method.setDelimiter("");
						ListEntry params = (ListEntry)header.getParameters();
						params = (ListEntry)params.get(1);
						StringBuilder methodBuilder = new StringBuilder();
						methodBuilder.append(header.getMethodName());
						methodBuilder.append(":");
						for(Entry e:params){
							VariableEntry v = (VariableEntry)e;
							methodBuilder.append(v.getType());
							methodBuilder.append(",");
						}
						propertyMethods.put(methodBuilder.toString(),new TabEntry(0,new ListEntry(method)));
						propertyMethodTypes.put(methodBuilder.toString(),header);
						bareProperty.add(new TabEntry(1,new ListEntry(new ElementEntry(PropertyGenerator.methodElement,new ListEntry(type,header)))));
					}
				}
			}
		}
		ListEntry complete = new ListEntry(constants,bareProperty);
		complete.setDelimiter("\n");
		Generators.property.addEntry(directory,Generators.property.buildString(className,".java"),"property",complete);
		methodsOfProperties.put(propertyName,propertyMethods);
		methodTypesOfProperties.put(propertyName,propertyMethodTypes);
		variablesOfProperties.put(propertyName,propertyVariables);
		actualVariablesOfProperties.put(propertyName,actualVariables);
		if((methodsOfPropertiesAcceptors.containsKey(propertyName))){
			List<ListEntry> acceptors = (List<ListEntry>)methodsOfPropertiesAcceptors.get(propertyName);
			List<Set<String>> rejects = (List<Set<String>>)methodsOfPropertiesRejectors.get(propertyName);
			Integer size = acceptors.size();
			for(Integer i = 0;i<size;++i){
				Set<String> keySet = (Set<String>)propertyMethods.keySet();
				for(String key:keySet){
					if((!rejects.get(i).contains(key))){
						acceptors.get(i).add(propertyMethods.get(key));
					}
				}
			}
		}
		if((variablesOfPropertiesAcceptors.containsKey(propertyName))){
			List<ListEntry> acceptors = (List<ListEntry>)variablesOfPropertiesAcceptors.get(propertyName);
			List<Set<String>> rejects = (List<Set<String>>)variablesOfPropertiesRejectors.get(propertyName);
			Integer size = acceptors.size();
			for(Integer i = 0;i<size;++i){
				Set<String> keySet = (Set<String>)propertyVariables.keySet();
				for(String key:keySet){
					if((!rejects.contains(key))){
						acceptors.get(i).add(propertyVariables.get(key));
					}
				}
			}
			List<String> variableOfPropertiesContextsNames = (List<String>)variablesOfPropertiesContexts.get(propertyName);
			for(String contextName:variableOfPropertiesContextsNames){
				Set<String> context = (Set<String>)contexts.get(contextName).get(localContext).keySet();
				for(String variableName:context){
					Generators.property.println(Generators.property.buildString("PROPERTYGENERATOR>",variableName));
				}
				Set<String> varKeySet = (Set<String>)actualVariables.keySet();
				for(String variableName:varKeySet){
					contexts.get(contextName).get(localContext).put(variableName,actualVariables.get(variableName));
				}
			}
		}
	}
	public void setup(ParseContext data){
		this.addPage();
		directory = new File(Generators.generator.getDirectory(),"properties");
		directory.mkdirs();
	}
	public void generate(ParseContext data){
		ParseList dataList = (ParseList)data.getList("property_definitions");
		Generators.property.generateAll(dataList.getNewTokens(),"property_dec");
	}
	public void addVariableListener(String proprety,String contextName,ListEntry acceptor,Set<String> rejects){
		if((!variablesOfPropertiesAcceptors.containsKey(proprety))){
			variablesOfPropertiesAcceptors.put(proprety,new ArrayList<ListEntry>());
			variablesOfPropertiesRejectors.put(proprety,new ArrayList<Set<String>>());
			variablesOfPropertiesContexts.put(proprety,new ArrayList<String>());
		}
		variablesOfPropertiesAcceptors.get(proprety).add(acceptor);
		variablesOfPropertiesRejectors.get(proprety).add(rejects);
		variablesOfPropertiesContexts.get(proprety).add(contextName);
	}
	public void addMethodListener(String proprety,ListEntry acceptor,Set<String> rejects){
		if((!methodsOfPropertiesAcceptors.containsKey(proprety))){
			methodsOfPropertiesAcceptors.put(proprety,new ArrayList<ListEntry>());
			methodsOfPropertiesRejectors.put(proprety,new ArrayList<Set<String>>());
		}
		methodsOfPropertiesAcceptors.get(proprety).add(acceptor);
		methodsOfPropertiesRejectors.get(proprety).add(rejects);
	}

	public File getDirectory(){
		return directory;
	}

	public Map<String,Map<String,Entry>> getMethodsOfProperties(){
		return methodsOfProperties;
	}

	public Map<String,Map<String,ITypeListener>> getMethodTypesOfProperties(){
		return methodTypesOfProperties;
	}

	public Map<String,Map<String,Entry>> getVariablesOfProperties(){
		return variablesOfProperties;
	}

	public Map<String,Map<String,VariableEntry>> getActualVariablesOfProperties(){
		return actualVariablesOfProperties;
	}

	public Map<String,List<String>> getVariablesOfPropertiesContexts(){
		return variablesOfPropertiesContexts;
	}

	public Map<String,List<ListEntry>> getVariablesOfPropertiesAcceptors(){
		return variablesOfPropertiesAcceptors;
	}

	public Map<String,List<Set<String>>> getVariablesOfPropertiesRejectors(){
		return variablesOfPropertiesRejectors;
	}

	public Map<String,List<ListEntry>> getMethodsOfPropertiesAcceptors(){
		return methodsOfPropertiesAcceptors;
	}

	public Map<String,List<Set<String>>> getMethodsOfPropertiesRejectors(){
		return methodsOfPropertiesRejectors;
	}

	public String getName(){
		return "Property";
	}

	public IParser getLazyNameParser(){
		return null;
	}
}