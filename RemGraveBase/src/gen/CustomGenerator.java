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

public class CustomGenerator extends Generator {

	private String packageName = null;
	private File directory = (File)null;


	public static final Element outlineElement = new Element("outline",new String[]{"package ",/*Package Name*/";\n\n"+
			"import java.util.*;\n"+
			"import com.rem.parser.*;\n"+
			"import com.rem.parser.generation.*;\n"+
			"import com.rem.parser.token.*;\n"+
			"import com.rem.parser.parser.*;\n"+
			"import lists.*;\n\n"+
			"public class ",/*Class Name*/" extends DefineParser {\n\n"+
			"\tpublic static final IRule parser = new ",/*Class Name*/"();\n"+
			"\tpublic ",/*Class Name*/"(){\n"+
			"\t\tsuper(",/*Sub Parser*/", \"",/*Rule Name*/"\");\n"+
			"\t}\n"+
			"\t@Override\n"+
			"\tpublic void setup(){\n",/*Parameters*/"\n",/*Events*/"\n\t}\n\n}"});
	public static final Element eventDefinitionElement = new Element("eventDefinition",new String[]{"addEvent(new DefineParser.Event(\"",/*Event Name*/"\"){\n"+
			"\t\t\t@Override\n"+
			"\t\t\tpublic void onValidate(IToken successfulToken, Map<String,DefineParser.Parameter<?>> parameters){",/*On Validate Actions*/"\n\t\t\t}});"});
	public static final Element parameterDeclarationElement = new Element("parameterDeclaration",new String[]{"addParameter(\"",/*Parameter Name*/"\", ",/*Parameter*/");"});
	public static final Element acceptCallElement = new Element("acceptCall",new String[]{"accept(parameters, \"",/*Parameter Name*/"\", ",/*Accept Action*/");"});
	public static final Element parserParameterDefinitionElement = new Element("parserParameterDefinition",new String[]{"new DefineParser.ParserParameter(",/*Parser*/")"});
	public static final Element stringParameterDefinitionElement = new Element("stringParameterDefinition",new String[]{"new DefineParser.StringParameter(",/*String*/")"});
	public static final Element intParameterDefinitionElement = new Element("intParameterDefinition",new String[]{"new DefineParser.IntParameter(",/*Int*/")"});
	public static final Element parameterParameterDefinitionElement = new Element("parameterParameterDefinition",new String[]{"((",/*Cast Name*/")parameters.get(\"",/*Parameter*/"\"))"});
	public static final Element tokenGetStringElement = new Element("tokenGetString",new String[]{"successfulToken",/*Steps*/".getString()"});
	public static final Element tokenStepElement = new Element("tokenStep",new String[]{".get(\"",/*Step Name*/"\")"});
	public static final Element newParserCallElement = new Element("newParserCall",new String[]{"new ",/*Parser Class Name*/"Parser( ",/*Parameters*/" )"});
	public static final Element getValueCallElement = new Element("getValueCall",new String[]{"",/*Argument*/".getValue()"});
	public CustomGenerator(){
		addElement("outline",outlineElement);
		addElement("eventDefinition",eventDefinitionElement);
		addElement("parameterDeclaration",parameterDeclarationElement);
		addElement("acceptCall",acceptCallElement);
		addElement("parserParameterDefinition",parserParameterDefinitionElement);
		addElement("stringParameterDefinition",stringParameterDefinitionElement);
		addElement("intParameterDefinition",intParameterDefinitionElement);
		addElement("parameterParameterDefinition",parameterParameterDefinitionElement);
		addElement("tokenGetString",tokenGetStringElement);
		addElement("tokenStep",tokenStepElement);
		addElement("newParserCall",newParserCallElement);
		addElement("getValueCall",getValueCallElement);
	}
	public void setup(ParseContext data){
		this.addPage();
		packageName = Generators.rule.buildString(Generators.base.getSeedName(),".rules");
		directory = new File(Generators.base.getDirectory(),packageName.replace(".","/"));
		directory.mkdirs();
	}
	public void generate(ParseContext data){
		ParseList decs = (ParseList)data.getList("rules");
		Generators.custom.generateAll(decs.getNewTokens(),"custom_declaration");
	}
	public void generateRoot(IToken root){
		Generators.custom.generateCustomDefinition(root.get("custom_definition"));
	}
	public Entry generateCustomDefinition(IToken customDefinition){
		String name = customDefinition.get("name").getString();
		ListEntry parameters = new ListEntry();
		parameters.setDelimiter("");
		Entry subParser = null;
		ListEntry events = new ListEntry();
		events.setDelimiter("");
		HashMap<String,ListEntry> eventMap = new HashMap<String,ListEntry>();
		HashMap<String,String> classMap = new HashMap<String,String>();
		List<IToken> elementCustomElement = customDefinition.getAll("custom_element");
		if(elementCustomElement != null){
			for(IToken element:elementCustomElement){
				for(IToken.Key atomKey:element.keySet()){
					if("definition".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						subParser = Generators.rule.generateDefinition(atom,name,0);
					}
					else if("define_variable".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						Entry assignment = Generators.rule.generateDefinition(atom.get("definition"),atom.get("variableName").getString(),2);
						classMap.put(atom.get("variableName").getString(),"DefineParser.ParserParameter");
						parameters.add(new TabEntry(2,new ListEntry(new ElementEntry(CustomGenerator.parameterDeclarationElement,new ListEntry(new StringEntry(atom.get("variableName").getString()),new ElementEntry(CustomGenerator.parserParameterDefinitionElement,new ListEntry(assignment)))))));
					}
					else if("event_definition".equals(atomKey.getName())){
						IToken atom = element.get(atomKey);
						Generators.custom.generateEventDefinition(atom,events,eventMap,classMap);
					}
				}
			}
		}
		String className = Generators.custom.camelize(name);
		Generators.custom.addFile(directory,Generators.custom.buildString(className,".java"),new ListEntry(new StringEntry(packageName),new StringEntry(className),new StringEntry(className),new StringEntry(className),subParser,new StringEntry(name),parameters,events));
		String fileName = Generators.rule.buildString(className,".java");
		Generators.list.addClassList("rulenames",name,name,null);
		Entry rulenames = new ElementEntry(RuleGenerator.ruleElementElement,new ListEntry(new StringEntry(name),new StringEntry(className)));
		String seedName = Generators.base.getSeedName();
		Generators.list.addList(new ListEntry(new ElementEntry(RuleGenerator.importRulesElement,new ListEntry(new ListEntry(new StringEntry(seedName))))),"rules",name,rulenames);
		return null;
	}
	public Entry generateEventDefinition(IToken event_definition,ListEntry events,Map<String,ListEntry> eventMap,Map<String,String> classMap){
		String eventName = event_definition.get("eventName").getString();
		String acceptorName = event_definition.get("variableName").getString();
		Entry assignment = null;
		List<IToken> elementEventElement = event_definition.getAll("event_element");
		if(elementEventElement != null){
			for(IToken element:elementEventElement){
				assignment = generateEventElement(element,acceptorName,classMap);
			}
		}
		ListEntry actions = new ListEntry();
		actions.setDelimiter("");
		if((eventMap.containsKey(eventName))){
			actions = (ListEntry)eventMap.get(eventName);
			actions.add(new TabEntry(4,new ListEntry(new ElementEntry(CustomGenerator.acceptCallElement,new ListEntry(new StringEntry(acceptorName),assignment)))));
		}
		else {
			actions.add(new TabEntry(4,new ListEntry(new ElementEntry(CustomGenerator.acceptCallElement,new ListEntry(new StringEntry(acceptorName),assignment)))));
			eventMap.put(eventName,actions);
			events.add(new TabEntry(2,new ListEntry(new ElementEntry(CustomGenerator.eventDefinitionElement,new ListEntry(new StringEntry(eventName),actions)))));
		}
		return null;
	}
	public Entry generateEventElement(IToken eventElement,String resultName,Map<String,String> classMap){
		for(IToken.Key atomKey:eventElement.keySet()){
			if("quote".equals(atomKey.getName())){
				IToken atom = eventElement.get(atomKey);
				classMap.put(resultName,"DefineParser.StringParameter");
				return new ElementEntry(CustomGenerator.stringParameterDefinitionElement,new ListEntry(new QuoteEntry(atom.getString())));
			}
			else if("NUMBER".equals(atomKey.getName())){
				IToken atom = eventElement.get(atomKey);
				classMap.put(resultName,"DefineParser.IntParameter");
				return new ElementEntry(CustomGenerator.intParameterDefinitionElement,new ListEntry(new StringEntry(atom.getString())));
			}
			else if("token".equals(atomKey.getName())){
				IToken atom = eventElement.get(atomKey);
				classMap.put(resultName,"DefineParser.StringParameter");
				return new ElementEntry(CustomGenerator.stringParameterDefinitionElement,new ListEntry(generateTokenDef(atom)));
			}
			else if("classDefinition".equals(atomKey.getName())){
				IToken atom = eventElement.get(atomKey);
				classMap.put(resultName,"DefineParser.ParserParameter");
				return new ElementEntry(CustomGenerator.parserParameterDefinitionElement,new ListEntry(generateClassDef(atom,classMap)));
			}
			else if("custom_variable_names".equals(atomKey.getName())){
				IToken atom = eventElement.get(atomKey);
				String variableClass = classMap.get(atom.getString());
				return new ElementEntry(CustomGenerator.parameterParameterDefinitionElement,new ListEntry(new StringEntry(variableClass),new StringEntry(atom.getString())));
			}
		}
		throw new UnableToGenerateException("(999) eventElement could not find appropriate subToken",eventElement);
	}
	public Entry generateTokenDef(IToken tokenDef){
		ListEntry steps = new ListEntry();
		steps.setDelimiter("");
		for(IToken.Key elementKey:tokenDef.keySet()){
			if("tokenName".equals(elementKey.getName())){
				IToken element = tokenDef.get(elementKey);
				steps.add(new ElementEntry(CustomGenerator.tokenStepElement,new ListEntry(new StringEntry(element.getString()))));
			}
		}
		return new ElementEntry(CustomGenerator.tokenGetStringElement,new ListEntry(steps));
	}
	public Entry generateClassDef(IToken classDef,Map<String,String> classMap){
		ListEntry parameters = new ListEntry();
		StringEntry className = new StringEntry(classDef.get("className").getString());
		List<IToken> elementEventElement = classDef.getAll("event_element");
		if(elementEventElement != null){
			for(IToken element:elementEventElement){
				parameters.add(new ElementEntry(CustomGenerator.getValueCallElement,new ListEntry(generateEventElement(element,"$HIDDEN",classMap))));
			}
		}
		return new ElementEntry(CustomGenerator.newParserCallElement,new ListEntry(className,parameters));
	}

	public String getPackageName(){
		return packageName;
	}

	public File getDirectory(){
		return directory;
	}

	public String getName(){
		return "Custom";
	}

	public IParser getLazyNameParser(){
		return null;
	}
}