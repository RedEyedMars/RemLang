package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class GeneratorGenerator extends Generator {



	public static final Element typeAndNameElement = new Element("typeAndName",new String[]{"",/*Type*/" ",/*Name*/""});
	public static final Element methodCallElement = new Element("methodCall",new String[]{"",/*Subject*/".",/*Method*/"(",/*Parameters*/")"});
	public static final Element exactCallElement = new Element("exactCall",new String[]{"",/*Contents*/""});
	public static final Element variableDeclarationElement = new Element("variableDeclaration",new String[]{"",/*Type*/" ",/*Name*/" = ",/*Assignment*/";"});
	public static final Element newObjectCallElement = new Element("newObjectCall",new String[]{"new ",/*Type Name*/"(",/*Parameters*/")"});
	public GeneratorGenerator(){
		addElement("typeAndName",typeAndNameElement);
		addElement("methodCall",methodCallElement);
		addElement("exactCall",exactCallElement);
		addElement("variableDeclaration",variableDeclarationElement);
		addElement("newObjectCall",newObjectCallElement);
	}


	public String getName(){
		return "Generator";
	}

	public void generateRoot(IToken root){
	}

	public void setup(ParseContext data){
	}

	public void generate(ParseContext listMap){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}