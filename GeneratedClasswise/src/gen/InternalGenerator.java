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

public class InternalGenerator extends Generator {



	public static final Element camelizeElement = new Element("camelize",new String[]{"FlowController.camelize(",/*In*/".toString())"});
	public static final Element getMethodNameElement = new Element("getMethodName",new String[]{"get",/*Name*/""});
	public static final Element getVariableFromNameElement = new Element("getVariableFromName",new String[]{"MainClass.",/*VariableName*/""});
	public static final Element getClassFromClassMapElement = new Element("getClassFromClassMap",new String[]{"ExternalClassEntry.classMap.get(",/*Class Name*/")"});
	public static final Element newBodyElement = new Element("newBody",new String[]{"new ExternalStatement.Body(",/*Body Elements*/")"});
	public static final Element manipulateObjectElement = new Element("manipulateObject",new String[]{"",/*Class Name*/".",/*Method*/"(",/*Parameter*/");"});
	public static final Element manipulateSoloTokenElement = new Element("manipulateSoloToken",new String[]{"for(final IToken ",/*Element Name*/":",/*Subject*/".getAllSafely(\"",/*Token Name*/"\")) {",/*Body*/""});
	public static final Element manipulateMultiTokenElement = new Element("manipulateMultiToken",new String[]{"for(IToken.Key __",/*Element Name*/"__KEY:",/*Subject*/".keySet()) {",/*Body*/""});
	public static final Element manipulateOneMultiTokenElement = new Element("manipulateOneMultiToken",new String[]{"if(__",/*Element Name*/"__KEY.getName().equals(\"",/*Token Name*/"\")){ final IToken ",/*Element Name*/" = ",/*Subject*/".get(__",/*Element Name*/"__KEY);",/*Body*/""});
	public static final Element bodyEntryElement = new Element("bodyEntry",new String[]{"",/*Entry*/""});
	public static final Element bodyOperatorElement = new Element("bodyOperator",new String[]{"",/*Left*/" ",/*Operator*/" ",/*Right*/""});
	public static final Element bodyBracedStatementElement = new Element("bodyBracedStatement",new String[]{"(",/*Subject*/")"});
	public static final Element bodyBracedOperatorStatementElement = new Element("bodyBracedOperatorStatement",new String[]{"(",/*Subject*/") ",/*Operator*/" ",/*Right*/""});
	public static final Element bodyCastedStatementElement = new Element("bodyCastedStatement",new String[]{"(",/*Left*/") ",/*Right*/""});
	public static final Element bodyCallWithSubjectWithArrayElement = new Element("bodyCallWithSubjectWithArray",new String[]{"",/*Subject*/".",/*Method*/"(",/*Parameters*/")[",/*Array*/"]"});
	public static final Element bodyCallWithoutSubjectWithArrayElement = new Element("bodyCallWithoutSubjectWithArray",new String[]{"",/*Method*/"(",/*Parameters*/")[",/*Array*/"]"});
	public static final Element bodyAccessWithSubjectWithArrayElement = new Element("bodyAccessWithSubjectWithArray",new String[]{"",/*Subject*/".",/*Method*/"[",/*Array*/"]"});
	public static final Element bodyAccessWithoutSubjectWithArrayElement = new Element("bodyAccessWithoutSubjectWithArray",new String[]{"",/*Method*/"[",/*Array*/"]"});
	public static final Element bodyCallWithSubjectElement = new Element("bodyCallWithSubject",new String[]{"",/*Subject*/".",/*Method*/"(",/*Parameters*/")"});
	public static final Element bodyCallWithoutSubjectElement = new Element("bodyCallWithoutSubject",new String[]{"",/*Method*/"(",/*Parameters*/")"});
	public static final Element bodyAccessWithSubjectElement = new Element("bodyAccessWithSubject",new String[]{"",/*Subject*/".",/*Method*/""});
	public static final Element bodyAccessWithoutSubjectElement = new Element("bodyAccessWithoutSubject",new String[]{"",/*Method*/""});
	public static final Element bodyNameElement = new Element("bodyName",new String[]{"",/*Name*/""});
	public static final Element bodyForIntHeaderElement = new Element("bodyForIntHeader",new String[]{"",/*Variable Declaration*/"; ",/*VariableName*/" ",/*Operator*/" ",/*Variable Limit*/"; ++",/*Variable Name*/""});
	public static final Element bodyNameWithParametersElement = new Element("bodyNameWithParameters",new String[]{"",/*Name*/"<",/*Parameters*/">"});
	public static final Element bodyNewObjPAElement = new Element("bodyNewObjPA",new String[]{"new ",/*Method Name*/"(",/*Parameters*/")[",/*Array*/"]"});
	public static final Element bodyNewObjPElement = new Element("bodyNewObjP",new String[]{"new ",/*Method Name*/"(",/*Parameters*/")"});
	public static final Element bodyNewObjAElement = new Element("bodyNewObjA",new String[]{"new ",/*Method Name*/"[",/*Parameters*/"]"});
	public static final Element bodyNewObjElement = new Element("bodyNewObj",new String[]{"new ",/*Method Name*/""});
	public static final Element bodyConditionalElement = new Element("bodyConditional",new String[]{"",/*Name*/"(",/*Header*/") {",/*Body*/""});
	public static final Element bodyConditionalWithoutHeaderElement = new Element("bodyConditionalWithoutHeader",new String[]{"",/*Name*/" {",/*Body*/""});
	public static final Element bodyElementElement = new Element("bodyElement",new String[]{"",/*Prefix*/"",/*Entry*/"",/*Suffix*/""});
	public static final Element declareVariableWithAssignmentFinalElement = new Element("declareVariableWithAssignmentFinal",new String[]{"final ",/*Type*/"",/*Array*/" ",/*Name*/" = ",/*Assignment*/""});
	public static final Element declareVariableWithAssignmentNonFinalElement = new Element("declareVariableWithAssignmentNonFinal",new String[]{"",/*Type*/"",/*Array*/" ",/*Name*/" = ",/*Assignment*/""});
	public static final Element declareVariableWithoutAssignmentNonFinalElement = new Element("declareVariableWithoutAssignmentNonFinal",new String[]{"",/*Type*/"",/*Array*/" ",/*Name*/""});
	public static final Element declareVariableWithoutAssignmentFinalElement = new Element("declareVariableWithoutAssignmentFinal",new String[]{"final ",/*Type*/"",/*Array*/" ",/*Name*/""});
	public static final Element declareStaticVariableWithAssignmentFinalElement = new Element("declareStaticVariableWithAssignmentFinal",new String[]{"static final ",/*Type*/"",/*Array*/" ",/*Name*/" = ",/*Assignment*/""});
	public static final Element declareStaticVariableWithAssignmentNonFinalElement = new Element("declareStaticVariableWithAssignmentNonFinal",new String[]{"static ",/*Type*/"",/*Array*/" ",/*Name*/" = ",/*Assignment*/""});
	public static final Element declareStaticVariableWithoutAssignmentNonFinalElement = new Element("declareStaticVariableWithoutAssignmentNonFinal",new String[]{"static ",/*Type*/"",/*Array*/" ",/*Name*/""});
	public static final Element declareStaticVariableWithoutAssignmentFinalElement = new Element("declareStaticVariableWithoutAssignmentFinal",new String[]{"static final ",/**/" Array ",/*Type*/" ",/*Name*/""});
	public static final Element declareMemberElement = new Element("declareMember",new String[]{"protected ",/*Name And Type*/" = ",/*Assignment*/";"});
	public static final Element declareMemberCompleteElement = new Element("declareMemberComplete",new String[]{"protected ",/*Name And Type and Assignment*/";"});
	public static final Element declareMemberVariableElement = new Element("declareMemberVariable",new String[]{"protected ExternalVariableEntry ",/*Name*/"Variable = ",/*Member*/";"});
	public static final Element declareMemberMethodElement = new Element("declareMemberMethod",new String[]{"protected ExternalMethodEntry ",/*Name*/"Method = ",/*Member*/";"});
	public static final Element declareMemberClassElement = new Element("declareMemberClass",new String[]{"protected ",/*Name*/" ",/*Name*/"Class = ",/*Member*/""});
	public static final Element declareMethodElement = new Element("declareMethod",new String[]{"public ",/*IsStatic*/"",/*Type*/"",/*Array*/" ",/*Name*/"(",/*Parameters*/") {",/*Method Body*/""});
	public static final Element declareClassElement = new Element("declareClass",new String[]{"package ",/*Package name*/";",/*Import List*/""+
			"\npublic ",/*IsStatic*/"",/*Class Type*/" ",/*Class Name*/" ",/*Extends*/" ",/*Implements*/" {"+
			"\n	//Externals",/*Variables*/"\n",/*Methods*/"\n",/*Classes*/""+
			"\n	//Internals",/*Variables*/"\n",/*Methods*/"\n",/*Classes*/""+
			"\n}"});
	public static final Element declareSubClassElement = new Element("declareSubClass",new String[]{"public ",/*IsStatic*/"",/*Class Type*/" ",/*Class Name*/" ",/*Extends*/" ",/*Implements*/" {"+
			"\n	//Externals",/*Variables*/"\n",/*Methods*/"\n",/*Classes*/""+
			"\n	//Internals",/*Variables*/"\n",/*Methods*/"\n",/*Classes*/""});
	public InternalGenerator(){
		addElement("camelize",camelizeElement);
		addElement("getMethodName",getMethodNameElement);
		addElement("getVariableFromName",getVariableFromNameElement);
		addElement("getClassFromClassMap",getClassFromClassMapElement);
		addElement("newBody",newBodyElement);
		addElement("manipulateObject",manipulateObjectElement);
		addElement("manipulateSoloToken",manipulateSoloTokenElement);
		addElement("manipulateMultiToken",manipulateMultiTokenElement);
		addElement("manipulateOneMultiToken",manipulateOneMultiTokenElement);
		addElement("bodyEntry",bodyEntryElement);
		addElement("bodyOperator",bodyOperatorElement);
		addElement("bodyBracedStatement",bodyBracedStatementElement);
		addElement("bodyBracedOperatorStatement",bodyBracedOperatorStatementElement);
		addElement("bodyCastedStatement",bodyCastedStatementElement);
		addElement("bodyCallWithSubjectWithArray",bodyCallWithSubjectWithArrayElement);
		addElement("bodyCallWithoutSubjectWithArray",bodyCallWithoutSubjectWithArrayElement);
		addElement("bodyAccessWithSubjectWithArray",bodyAccessWithSubjectWithArrayElement);
		addElement("bodyAccessWithoutSubjectWithArray",bodyAccessWithoutSubjectWithArrayElement);
		addElement("bodyCallWithSubject",bodyCallWithSubjectElement);
		addElement("bodyCallWithoutSubject",bodyCallWithoutSubjectElement);
		addElement("bodyAccessWithSubject",bodyAccessWithSubjectElement);
		addElement("bodyAccessWithoutSubject",bodyAccessWithoutSubjectElement);
		addElement("bodyName",bodyNameElement);
		addElement("bodyForIntHeader",bodyForIntHeaderElement);
		addElement("bodyNameWithParameters",bodyNameWithParametersElement);
		addElement("bodyNewObjPA",bodyNewObjPAElement);
		addElement("bodyNewObjP",bodyNewObjPElement);
		addElement("bodyNewObjA",bodyNewObjAElement);
		addElement("bodyNewObj",bodyNewObjElement);
		addElement("bodyConditional",bodyConditionalElement);
		addElement("bodyConditionalWithoutHeader",bodyConditionalWithoutHeaderElement);
		addElement("bodyElement",bodyElementElement);
		addElement("declareVariableWithAssignmentFinal",declareVariableWithAssignmentFinalElement);
		addElement("declareVariableWithAssignmentNonFinal",declareVariableWithAssignmentNonFinalElement);
		addElement("declareVariableWithoutAssignmentNonFinal",declareVariableWithoutAssignmentNonFinalElement);
		addElement("declareVariableWithoutAssignmentFinal",declareVariableWithoutAssignmentFinalElement);
		addElement("declareStaticVariableWithAssignmentFinal",declareStaticVariableWithAssignmentFinalElement);
		addElement("declareStaticVariableWithAssignmentNonFinal",declareStaticVariableWithAssignmentNonFinalElement);
		addElement("declareStaticVariableWithoutAssignmentNonFinal",declareStaticVariableWithoutAssignmentNonFinalElement);
		addElement("declareStaticVariableWithoutAssignmentFinal",declareStaticVariableWithoutAssignmentFinalElement);
		addElement("declareMember",declareMemberElement);
		addElement("declareMemberComplete",declareMemberCompleteElement);
		addElement("declareMemberVariable",declareMemberVariableElement);
		addElement("declareMemberMethod",declareMemberMethodElement);
		addElement("declareMemberClass",declareMemberClassElement);
		addElement("declareMethod",declareMethodElement);
		addElement("declareClass",declareClassElement);
		addElement("declareSubClass",declareSubClassElement);
	}


	public String getName(){
		return "Internal";
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