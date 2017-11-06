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

public class ExternalGenerator extends Generator {



	public static final Element getNameElement = new Element("getName",new String[]{"new StringEntry(",/*Name Getter*/".getFullName())"});
	public static final Element accessClassElement = new Element("accessClass",new String[]{"new ExternalStatement(\".\", ",/*Left*/", ",/*Right*/")"});
	public static final Element accessMethodElement = new Element("accessMethod",new String[]{"new ExternalStatement(\".\", ",/*Left*/", ",/*Right*/")"});
	public static final Element getClassFromClassMapElement = new Element("getClassFromClassMap",new String[]{"ExternalClassEntry.classMap.get(",/*Class Name*/")"});
	public static final Element throwsStatementElement = new Element("throwsStatement",new String[]{" throws ",/*Exceptions*/" "});
	public static final Element exactElement = new Element("exact",new String[]{"new StringEntry(",/*In*/")"});
	public static final Element exactEntryElement = new Element("exactEntry",new String[]{"new Entry(){"+
			"\n			public void get(StringBuilder __BUILDER__){",/*In*/".get(__BUILDER__);"+
			"\n			}"+
			"\n		}"});
	public static final Element asTemplateElement = new Element("asTemplate",new String[]{"new StringEntry(\"<",/*In*/">\")"});
	public static final Element camelizeElement = new Element("camelize",new String[]{"new StringEntry(FlowController.camelize(",/*In*/"))"});
	public static final Element extendsElement = new Element("extends",new String[]{"builder.append(\" extends \");"+
			"\n	",/*Extends Name*/""});
	public static final Element implementsElement = new Element("implements",new String[]{"builder.append(\" implements \");"+
			"\n	",/*Implements Name*/""});
	public static final Element asEntryListElement = new Element("asEntryList",new String[]{"new ArrayList<Entry>(Arrays.asList(new Entry[]{",/*Class List*/"}))"});
	public static final Element bodyConcatElement = new Element("bodyConcat",new String[]{"/*Concat*/new ExternalStatement(\"\", ",/*Left*/", ",/*Right*/")"});
	public static final Element bodyNameElement = new Element("bodyName",new String[]{"/*Name*/new ExternalStatement(",/*Name*/")"});
	public static final Element bodyNameWithParametersElement = new Element("bodyNameWithParameters",new String[]{"/*Name*/new ExternalStatement(new StringEntry(",/*Name*/"+\"<\"), new StringEntry(\">\"), \",\", ",/*Parameters*/")"});
	public static final Element bodyTypeNameElement = new Element("bodyTypeName",new String[]{"/*TypeName*/new ExternalStatement.TypeName(",/*Name*/")"});
	public static final Element bodyTypeNameWithParametersElement = new Element("bodyTypeNameWithParameters",new String[]{"/*TypeName*/new ExternalStatement.TypeName(",/*Name*/", new ExternalStatement(new StringEntry(\"<\"), new StringEntry(\">\"), \",\", ",/*Parameters*/"))"});
	public static final Element bodyInnerCallElement = new Element("bodyInnerCall",new String[]{"/*InCl*/new ExternalStatement(",/*Name*/")"});
	public static final Element bodyExactElement = new Element("bodyExact",new String[]{"/*Exac*/new ExternalStatement(new StringEntry(\"",/*Name*/"\"))"});
	public static final Element bodyExactWithParametersElement = new Element("bodyExactWithParameters",new String[]{"/*Exac*/new ExternalStatement(new StringEntry(",/*Name*/"+\"<\"), new StringEntry(\">\"), \",\", ",/*Parameters*/")"});
	public static final Element bodyQuoteElement = new Element("bodyQuote",new String[]{"/*Quot*/new ExternalStatement(new QuoteEntry(",/*Value*/"))"});
	public static final Element bodyEntryElement = new Element("bodyEntry",new String[]{"/*Enty*/new ExternalStatement(new StringEntry(",/*Value*/"))"});
	public static final Element bodyOperatorElement = new Element("bodyOperator",new String[]{"/*Optr*/new ExternalStatement(",/*Operator*/", ",/*Left*/", ",/*Right*/")"});
	public static final Element bodyForIntHeaderElement = new Element("bodyForIntHeader",new String[]{"/*FIHd*/new ExternalStatement(\";\", ",/*Variable Declaration*/", new ExternalStatement(",/*Operator*/", ",/*Variable Name*/", ",/*Variable Limit*/"), new ExternalStatement(new StringEntry(\"++\"),\"\",",/*Variable Name*/"))"});
	public static final Element bodyBracedStatementElement = new Element("bodyBracedStatement",new String[]{"/*Brac*/new ExternalStatement(new StringEntry(\"(\"),new StringEntry(\")\"),\"\", ",/*Subject*/")"});
	public static final Element bodyCastedStatementElement = new Element("bodyCastedStatement",new String[]{"/*Cast*/new ExternalStatement(\"\",new ExternalStatement(new StringEntry(\"(\"),new StringEntry(\")\"),\"\", ",/*Type*/"), ",/*ToCast*/")"});
	public static final Element bodyBracedOperatorStatementElement = new Element("bodyBracedOperatorStatement",new String[]{"/*BrOp*/new ExternalStatement(\"\",new ExternalStatement(new StringEntry(\"(\"),new StringEntry(\")\"),\"\", ",/*Type*/"), new ExternalStatement(\"",/*Operator*/"\"), ",/*ToCast*/")"});
	public static final Element bodyCallWithSubjectWithArrayElement = new Element("bodyCallWithSubjectWithArray",new String[]{"/*Call*/new ExternalStatement(\"\","+
			"\n			 	new ExternalStatement(\".\", ",/*Subject*/", ",/*Method Name*/"),"+
			"\n			 	new ExternalStatement(new StringEntry(\"(\"),new StringEntry(\")\"),\"\","+
			"\n			 		new ExternalStatement.Parameters(",/*Parameters*/")),"+
			"\n			 	new ExternalStatement.ArrayParameters(",/*Array*/"))"});
	public static final Element bodyCallWithoutSubjectWithArrayElement = new Element("bodyCallWithoutSubjectWithArray",new String[]{"/*Call*/new ExternalStatement(\"\",",/*Method Name*/","+
			"\n			 	new ExternalStatement(new StringEntry(\"(\"),new StringEntry(\")\"),\"\","+
			"\n			 		new ExternalStatement.Parameters(",/*Parameters*/")),"+
			"\n			 	new ExternalStatement.ArrayParameters(",/*Array*/"))"});
	public static final Element bodyAccessWithSubjectWithArrayElement = new Element("bodyAccessWithSubjectWithArray",new String[]{"/*Acss*/new ExternalStatement(\".\", ",/*Subject*/", new ExternalStatement(\"\", ",/*Method Name*/", new ExternalStatement.ArrayParameters(",/*Array*/")))"});
	public static final Element bodyAccessWithoutSubjectWithArrayElement = new Element("bodyAccessWithoutSubjectWithArray",new String[]{"/*Acss*/new ExternalStatement(\"\", ",/*Method Name*/", new ExternalStatement.ArrayParameters(",/*Array*/"))"});
	public static final Element bodyCallWithSubjectElement = new Element("bodyCallWithSubject",new String[]{"/*Call*/new ExternalStatement(\"\","+
			"\n			 	new ExternalStatement(\".\", ",/*Subject*/", ",/*Method Name*/"),"+
			"\n			 	new ExternalStatement(new StringEntry(\"(\"),new StringEntry(\")\"),\"\","+
			"\n			 		new ExternalStatement.Parameters(",/*Parameters*/")))"});
	public static final Element bodyCallWithoutSubjectElement = new Element("bodyCallWithoutSubject",new String[]{"/*Call*/new ExternalStatement(null,new StringEntry(\")\"),\"(\",",/*Method Name*/",new ExternalStatement.Parameters(",/*Parameters*/"))"});
	public static final Element bodyAccessWithSubjectElement = new Element("bodyAccessWithSubject",new String[]{"/*Acss*/new ExternalStatement(\".\", ",/*Subject*/", ",/*Method Name*/")"});
	public static final Element bodyAccessWithoutSubjectElement = new Element("bodyAccessWithoutSubject",new String[]{"/*Acss*/new ExternalStatement(",/*Method Name*/")"});
	public static final Element bodyNewObjPAElement = new Element("bodyNewObjPA",new String[]{"/*NObj*/new ExternalStatement.NewObject(",/*Type Name*/",new ExternalStatement.Parameters(",/*Parameters*/"), new ExternalStatement.ArrayParameters(",/*Array*/"))"});
	public static final Element bodyNewObjPElement = new Element("bodyNewObjP",new String[]{"/*NObj*/new ExternalStatement.NewObject(",/*Type Name*/",new ExternalStatement.Parameters(",/*Parameters*/"))"});
	public static final Element bodyNewObjAElement = new Element("bodyNewObjA",new String[]{"/*NObj*/new ExternalStatement.NewObject(",/*Type Name*/",new ExternalStatement.ArrayParameters(",/*Parameters*/"))"});
	public static final Element bodyNewObjElement = new Element("bodyNewObj",new String[]{"/*NObj*/new ExternalStatement.NewObject(",/*Type Name*/")"});
	public static final Element bodyElementElement = new Element("bodyElement",new String[]{"/*Elem*/new ExternalStatement(new TabEntry(new StringEntry(",/*Preffix*/")), new StringEntry(",/*Suffix*/"), ",/*Entry*/")"});
	public static final Element bodyThrowElement = new Element("bodyThrow",new String[]{"/*Thrw*/new ExternalStatement(new TabEntry(new StringEntry(\"throw new RuntimeException(\\\"\")), new StringEntry(\"\\\");\"),\"\", ",/*Name Var*/")"});
	public static final Element bodyCaseElement = new Element("bodyCase",new String[]{"/*Case*/new ExternalStatement.Conditional("+
			"\n			\"case \", "+
			"\n			",/*Header*/","+
			"\n			",/*Body*/").setBraces(\"\",\":\")"});
	public static final Element bodyConditionalElement = new Element("bodyConditional",new String[]{"/*Cond*/new ExternalStatement.Conditional("+
			"\n			\"",/*Name*/"\", "+
			"\n			",/*Header*/","+
			"\n			",/*Body*/")"});
	public static final Element bodyConditionalWithoutHeaderElement = new Element("bodyConditionalWithoutHeader",new String[]{"/*Cond*/new ExternalStatement.Conditional("+
			"\n			\"",/*Name*/"\", null,"+
			"\n			",/*Body*/")"});
	public static final Element bodyParametersElement = new Element("bodyParameters",new String[]{"/*Parameters*/Arrays.asList(new ExternalVariableEntry[]{",/*Body*/"})"});
	public static final Element bodyBodyElement = new Element("bodyBody",new String[]{"/*Body*/new ExternalStatement.Body(",/*Body*/")"});
	public static final Element declareMemberElement = new Element("declareMember",new String[]{"new Entry(){"+
			"\n				public void get(StringBuilder builder){"+
			"\n					new TabEntry(",/*Tabs*/", new StringEntry(",/*protected*/")).get(builder);"+
			"\n					",/*Variable Declaration*/".get(builder);"+
			"\n					builder.append(\";\");"+
			"\n				}"+
			"\n			}"});
	public static final Element declareVariableAsInlineListElement = new Element("declareVariableAsInlineList",new String[]{"new ExternalVariableEntry(false, ",/*Type*/",\"",/*Array*/"\", new ExternalStatement(new StringEntry(\"...\"), ",/*Name*/"))"});
	public static final Element declareVariableWithAssignmentFinalElement = new Element("declareVariableWithAssignmentFinal",new String[]{"new ExternalVariableEntry(false, ",/*Type*/",\"",/*Array*/"\", ",/*Name*/", ",/*Assignment*/")"});
	public static final Element declareVariableWithAssignmentNonFinalElement = new Element("declareVariableWithAssignmentNonFinal",new String[]{"new ExternalVariableEntry(false, true, ",/*Type*/",\"",/*Array*/"\", ",/*Name*/", ",/*Assignment*/")"});
	public static final Element declareVariableWithoutAssignmentNonFinalElement = new Element("declareVariableWithoutAssignmentNonFinal",new String[]{"new ExternalVariableEntry(false, true, ",/*Type*/",\"",/*Array*/"\", ",/*Name*/")"});
	public static final Element declareVariableWithoutAssignmentFinalElement = new Element("declareVariableWithoutAssignmentFinal",new String[]{"new ExternalVariableEntry(false, ",/*Type*/",\"",/*Array*/"\", ",/*Name*/")"});
	public static final Element declareStaticVariableWithAssignmentElement = new Element("declareStaticVariableWithAssignment",new String[]{"new ExternalVariableEntry(true, ",/*Type*/",\"",/*Array*/"\", ",/*Name*/", ",/*Assignment*/")"});
	public static final Element declareStaticVariableWithoutAssignmentNonFinalElement = new Element("declareStaticVariableWithoutAssignmentNonFinal",new String[]{"new ExternalVariableEntry(true, ",/*Type*/",\"",/*Array*/"\", ",/*Name*/")"});
	public static final Element declareStaticVariableWithoutAssignmentFinalElement = new Element("declareStaticVariableWithoutAssignmentFinal",new String[]{"new ExternalVariableEntry(true, ",/*Type*/",\"",/*Array*/"\", ",/*Name*/")"});
	public static final Element declareStaticMethodElement = new Element("declareStaticMethod",new String[]{"new ExternalMethodEntry(",/*Tabs*/", true,",/*Type*/",\"",/*Array*/"\", ",/*Name*/", ",/*Parameters*/", \"",/*Throws*/"\", ",/*Body*/")"});
	public static final Element declareMethodElement = new Element("declareMethod",new String[]{"new ExternalMethodEntry(",/*Tabs*/", false,",/*Type*/",\"",/*Array*/"\", ",/*Name*/", ",/*Parameters*/", \"",/*Throws*/"\", ",/*Body*/")"});
	public static final Element declareInterfaceMethodElement = new Element("declareInterfaceMethod",new String[]{"new ExternalMethodEntry(",/*Tabs*/", false, ",/*Type*/",\"",/*Array*/"\", ",/*Name*/", ",/*Parameters*/" , \"",/*Throws*/"\", null )"});
	public static final Element getCompleteHeaderElement = new Element("getCompleteHeader",new String[]{"new Entry(){"+
			"\n		public void get(StringBuilder builder){"+
			"\n			builder.append(\"",/*Statickality*/" ",/*Class Type*/"\");"+
			"\n			",/*Class Name*/".get(builder);"+
			"\n			",/*Template Name*/".get(builder);"+
			"\n		}"+
			"\n	}"});
	public static final Element addTailBraceElement = new Element("addTailBrace",new String[]{"new TabEntry(",/*Tabs*/", new StringEntry(\"}\")).get(builder);"});
	public static final Element declareNewClassElement = new Element("declareNewClass",new String[]{"new ",/*Name*/"();"});
	public static final Element declareClassElement = new Element("declareClass",new String[]{"new ",/*Name*/"();"+
			"\n	public class ",/*Name*/" extends ExternalClassEntry {",/*Variables*/"\n",/*Methods*/"\n",/*Classes*/"\n",/*Classes*/"\n"+
			"\n	public void __INIT__(){"+
			"\n		super.__SETUP__("+
			"\n		",/*Package Name*/", "+
			"\n		new Entry(){"+
			"\n			public void get(StringBuilder __BUILDER__){",/*ImportList*/""+
			"\n			}"+
			"\n		}, ",/*Name*/", ",/*isInterface*/", ",/*Parent Class*/", ",/*Interfaces*/", ",/*Header*/", Arrays.asList(new ExternalVariableEntry[]{",/*Variables*/"}), Arrays.asList(new ExternalMethodEntry[]{",/*Methods*/"}), Arrays.asList(new ExternalClassEntry[]{",/*Classes*/"}));"+
			"\n	}"+
			"\n}"});
	public static final Element declareOutputClassElement = new Element("declareOutputClass",new String[]{"package clgen;"+
			"\nimport java.util.*;"+
			"\nimport java.io.*;"+
			"\nimport com.rem.parser.generation.classwise.*;"+
			"\nimport com.rem.parser.generation.*;"+
			"\nimport com.rem.parser.parser.*;"+
			"\nimport com.rem.parser.token.*;"+
			"\nimport com.rem.parser.*;",/*Imports*/""+
			"\npublic class ",/*Name*/" extends ExternalClassEntry {",/*Variables*/"\n",/*Methods*/"\n",/*Classes*/"\n",/*Classes*/"\n"+
			"\n	public void __INIT__(){"+
			"\n		super.__SETUP__("+
			"\n			",/*Package Name*/", "+
			"\n			new Entry(){"+
			"\n				public void get(StringBuilder __BUILDER__){",/*ImportList*/""+
			"\n				}}, "+
			"\n			",/*Name*/", "+
			"\n			",/*isInterface*/", "+
			"\n			",/*Parent Class*/", "+
			"\n			",/*Interfaces*/","+
			"\n			",/*Header*/","+
			"\n			Arrays.asList(new ExternalVariableEntry[]{",/*Variables*/"}), "+
			"\n			Arrays.asList(new ExternalMethodEntry[]{",/*Methods*/"}), "+
			"\n			Arrays.asList(new ExternalClassEntry[]{",/*Classes*/"}));"+
			"\n	}"+
			"\n}"});
	public static final Element declareClassAsArgumentElement = new Element("declareClassAsArgument",new String[]{"new ExternalClassEntry(){public void __INIT__(){super.__SETUP__(",/*Package Name*/", "+
			"\n		new Entry(){"+
			"\n			public void get(StringBuilder __BUILDER__){",/*ImportList*/""+
			"\n			}"+
			"\n		},",/*Name*/", ",/*isInterface*/", ",/*Parent Class*/", ",/*Interfaces*/", ",/*Header*/", Arrays.asList(new ExternalVariableEntry[]{",/*Variables*/"}), Arrays.asList(new ExternalMethodEntry[]{",/*Methods*/"}), Arrays.asList(new ExternalClassEntry[]{",/*Classes*/"})) ;}}"});
	public static final Element addMemberVariableElement = new Element("addMemberVariable",new String[]{"addVariable(\"",/*Name*/"\", ",/*Entry*/");"});
	public static final Element addMemberMethodElement = new Element("addMemberMethod",new String[]{"addMethod(\"",/*Name*/"\", ",/*Entry*/");"});
	public static final Element addMemberSubClassElement = new Element("addMemberSubClass",new String[]{"addSubClass(\"",/*Name*/"\", ",/*Entry*/");"});
	public ExternalGenerator(){
		addElement("getName",getNameElement);
		addElement("accessClass",accessClassElement);
		addElement("accessMethod",accessMethodElement);
		addElement("getClassFromClassMap",getClassFromClassMapElement);
		addElement("throwsStatement",throwsStatementElement);
		addElement("exact",exactElement);
		addElement("exactEntry",exactEntryElement);
		addElement("asTemplate",asTemplateElement);
		addElement("camelize",camelizeElement);
		addElement("extends",extendsElement);
		addElement("implements",implementsElement);
		addElement("asEntryList",asEntryListElement);
		addElement("bodyConcat",bodyConcatElement);
		addElement("bodyName",bodyNameElement);
		addElement("bodyNameWithParameters",bodyNameWithParametersElement);
		addElement("bodyTypeName",bodyTypeNameElement);
		addElement("bodyTypeNameWithParameters",bodyTypeNameWithParametersElement);
		addElement("bodyInnerCall",bodyInnerCallElement);
		addElement("bodyExact",bodyExactElement);
		addElement("bodyExactWithParameters",bodyExactWithParametersElement);
		addElement("bodyQuote",bodyQuoteElement);
		addElement("bodyEntry",bodyEntryElement);
		addElement("bodyOperator",bodyOperatorElement);
		addElement("bodyForIntHeader",bodyForIntHeaderElement);
		addElement("bodyBracedStatement",bodyBracedStatementElement);
		addElement("bodyCastedStatement",bodyCastedStatementElement);
		addElement("bodyBracedOperatorStatement",bodyBracedOperatorStatementElement);
		addElement("bodyCallWithSubjectWithArray",bodyCallWithSubjectWithArrayElement);
		addElement("bodyCallWithoutSubjectWithArray",bodyCallWithoutSubjectWithArrayElement);
		addElement("bodyAccessWithSubjectWithArray",bodyAccessWithSubjectWithArrayElement);
		addElement("bodyAccessWithoutSubjectWithArray",bodyAccessWithoutSubjectWithArrayElement);
		addElement("bodyCallWithSubject",bodyCallWithSubjectElement);
		addElement("bodyCallWithoutSubject",bodyCallWithoutSubjectElement);
		addElement("bodyAccessWithSubject",bodyAccessWithSubjectElement);
		addElement("bodyAccessWithoutSubject",bodyAccessWithoutSubjectElement);
		addElement("bodyNewObjPA",bodyNewObjPAElement);
		addElement("bodyNewObjP",bodyNewObjPElement);
		addElement("bodyNewObjA",bodyNewObjAElement);
		addElement("bodyNewObj",bodyNewObjElement);
		addElement("bodyElement",bodyElementElement);
		addElement("bodyThrow",bodyThrowElement);
		addElement("bodyCase",bodyCaseElement);
		addElement("bodyConditional",bodyConditionalElement);
		addElement("bodyConditionalWithoutHeader",bodyConditionalWithoutHeaderElement);
		addElement("bodyParameters",bodyParametersElement);
		addElement("bodyBody",bodyBodyElement);
		addElement("declareMember",declareMemberElement);
		addElement("declareVariableAsInlineList",declareVariableAsInlineListElement);
		addElement("declareVariableWithAssignmentFinal",declareVariableWithAssignmentFinalElement);
		addElement("declareVariableWithAssignmentNonFinal",declareVariableWithAssignmentNonFinalElement);
		addElement("declareVariableWithoutAssignmentNonFinal",declareVariableWithoutAssignmentNonFinalElement);
		addElement("declareVariableWithoutAssignmentFinal",declareVariableWithoutAssignmentFinalElement);
		addElement("declareStaticVariableWithAssignment",declareStaticVariableWithAssignmentElement);
		addElement("declareStaticVariableWithoutAssignmentNonFinal",declareStaticVariableWithoutAssignmentNonFinalElement);
		addElement("declareStaticVariableWithoutAssignmentFinal",declareStaticVariableWithoutAssignmentFinalElement);
		addElement("declareStaticMethod",declareStaticMethodElement);
		addElement("declareMethod",declareMethodElement);
		addElement("declareInterfaceMethod",declareInterfaceMethodElement);
		addElement("getCompleteHeader",getCompleteHeaderElement);
		addElement("addTailBrace",addTailBraceElement);
		addElement("declareNewClass",declareNewClassElement);
		addElement("declareClass",declareClassElement);
		addElement("declareOutputClass",declareOutputClassElement);
		addElement("declareClassAsArgument",declareClassAsArgumentElement);
		addElement("addMemberVariable",addMemberVariableElement);
		addElement("addMemberMethod",addMemberMethodElement);
		addElement("addMemberSubClass",addMemberSubClassElement);
	}


	public String getName(){
		return "External";
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