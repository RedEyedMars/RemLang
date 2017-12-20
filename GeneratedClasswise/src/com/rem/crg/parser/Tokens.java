package com.rem.crg.parser;
import java.util.*;
import java.io.*;
import com.rem.crg.parser.Token;
import com.rem.crg.parser.Tokens;

public  class Tokens{
	public Tokens() {
	}
	public static class Plain{
		public Plain() {
		}
		public static class Comment extends Token.Parsed{
			protected String value = null;
			public Comment(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public Comment(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public Comment() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "comment";
			}
		}
		public static class ConditionalToken extends Token.Parsed{
			protected String value = null;
			public static Token.Parsed plain_0 = new Tokens.Plain.ConditionalToken("else");
			public static Token.Parsed plain_1 = new Tokens.Plain.ConditionalToken("for");
			public static Token.Parsed plain_2 = new Tokens.Plain.ConditionalToken("try");
			public static Token.Parsed plain_3 = new Tokens.Plain.ConditionalToken("catch");
			public ConditionalToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ConditionalToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ConditionalToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "conditional";
			}
			public void setValue(String newValue){
			}
		}
		public static class MethodNameToken extends Token.Parsed{
			protected String value = null;
			public static Token.Parsed plain_4 = new Tokens.Plain.MethodNameToken("+=");
			public MethodNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public MethodNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public MethodNameToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "methodName";
			}
			public void setValue(String newValue){
			}
		}
		public static class ARRAYTYPEToken extends Token.Parsed{
			protected String value = null;
			public static Token.Parsed plain_5 = new Tokens.Plain.ARRAYTYPEToken("[]");
			public static Token.Parsed plain_7 = new Tokens.Plain.ARRAYTYPEToken("[]");
			public ARRAYTYPEToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ARRAYTYPEToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ARRAYTYPEToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "ARRAY_TYPE";
			}
			public void setValue(String newValue){
			}
		}
		public static class NAMEToken extends Token.Parsed{
			protected String value = null;
			public static Token.Parsed plain_6 = new Tokens.Plain.NAMEToken("*");
			public NAMEToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public NAMEToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public NAMEToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "NAME";
			}
			public void setValue(String newValue){
			}
		}
		public static class TypeNameToken extends Token.Parsed{
			protected String value = null;
			public static Token.Parsed plain_8 = new Tokens.Plain.TypeNameToken("Class");
			public TypeNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public TypeNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public TypeNameToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "typeName";
			}
			public void setValue(String newValue){
			}
		}
		public static class CAMELToken extends Token.Parsed{
			protected String value = null;
			public static Token.Parsed plain_9 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_10 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_11 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_12 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_13 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_15 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_17 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_19 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_21 = new Tokens.Plain.CAMELToken("^");
			public static Token.Parsed plain_22 = new Tokens.Plain.CAMELToken("^");
			public CAMELToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public CAMELToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public CAMELToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "CAMEL";
			}
			public void setValue(String newValue){
			}
		}
		public static class ISTYPENAMEToken extends Token.Parsed{
			protected String value = null;
			public static Token.Parsed plain_14 = new Tokens.Plain.ISTYPENAMEToken("$");
			public static Token.Parsed plain_16 = new Tokens.Plain.ISTYPENAMEToken("$");
			public static Token.Parsed plain_18 = new Tokens.Plain.ISTYPENAMEToken("$");
			public static Token.Parsed plain_20 = new Tokens.Plain.ISTYPENAMEToken("$");
			public ISTYPENAMEToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ISTYPENAMEToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ISTYPENAMEToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "ISTYPENAME";
			}
			public void setValue(String newValue){
			}
		}
	}
	public static class Name{
		public Name() {
		}
		public static class ClassNameToken extends Token.Parsed{
			protected String value = null;
			public ClassNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ClassNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ClassNameToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "className";
			}
			public void setValue(String newValue){
			}
		}
		public static class VariableNamesToken extends Token.Parsed{
			protected String value = null;
			public VariableNamesToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public VariableNamesToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public VariableNamesToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "variable_names";
			}
			public void setValue(String newValue){
			}
		}
		public static class ClassVariableNamesToken extends Token.Parsed{
			protected String value = null;
			public ClassVariableNamesToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ClassVariableNamesToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ClassVariableNamesToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "class_variable_names";
			}
			public void setValue(String newValue){
			}
		}
		public static class TemplateTypeNameToken extends Token.Parsed{
			protected String value = null;
			public TemplateTypeNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public TemplateTypeNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public TemplateTypeNameToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "templateTypeName";
			}
			public void setValue(String newValue){
			}
		}
		public static class VariableNameToken extends Token.Parsed{
			protected String value = null;
			public VariableNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public VariableNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public VariableNameToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "variableName";
			}
			public void setValue(String newValue){
			}
		}
		public static class ClassNamesToken extends Token.Parsed{
			protected String value = null;
			public ClassNamesToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ClassNamesToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ClassNamesToken() {
			}
			public String getValue(){
				return value;
			}
			public String getName(){
				return "class_names";
			}
			public void setValue(String newValue){
			}
		}
	}
	public static class Rule{
		public Rule() {
		}
		public static class BaseToken extends Token.Parsed{
			protected String value = null;
			public BaseToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BaseToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "base";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class CommentsToken extends Token.Parsed{
			protected String value = null;
			public CommentsToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public CommentsToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "comments";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class NAMEToken extends Token.Parsed{
			protected String value = null;
			public NAMEToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public NAMEToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "NAME";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class OPERATORToken extends Token.Parsed{
			protected String value = null;
			public OPERATORToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public OPERATORToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "OPERATOR";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class NUMBERToken extends Token.Parsed{
			protected String value = null;
			public NUMBERToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public NUMBERToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "NUMBER";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class QuoteToken extends Token.Parsed{
			protected String value = null;
			public QuoteToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public QuoteToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "quote";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassBodyToken extends Token.Parsed{
			protected String value = null;
			public ClassBodyToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassBodyToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_body";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodBodyToken extends Token.Parsed{
			protected String value = null;
			public MethodBodyToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodBodyToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_body";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodArgumentsToken extends Token.Parsed{
			protected String value = null;
			public MethodArgumentsToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodArgumentsToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_arguments";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class TemplateParametersToken extends Token.Parsed{
			protected String value = null;
			public TemplateParametersToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public TemplateParametersToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "template_parameters";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ArrayParametersToken extends Token.Parsed{
			protected String value = null;
			public ArrayParametersToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ArrayParametersToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "array_parameters";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsQuoteToken extends Token.Parsed{
			protected String value = null;
			public StatementAsQuoteToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsQuoteToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_quote";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsStringToken extends Token.Parsed{
			protected String value = null;
			public StatementAsStringToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsStringToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_string";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsMethodToken extends Token.Parsed{
			protected String value = null;
			public StatementAsMethodToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsMethodToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_method";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsBracedToken extends Token.Parsed{
			protected String value = null;
			public StatementAsBracedToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsBracedToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_braced";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class AsStatementToken extends Token.Parsed{
			protected String value = null;
			public AsStatementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public AsStatementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "as_statement";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassFileNameToken extends Token.Parsed{
			protected String value = null;
			public ClassFileNameToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassFileNameToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_file_name";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BaseElementToken extends Token.Parsed{
			protected String value = null;
			public BaseElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BaseElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "base_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ImportsToken extends Token.Parsed{
			protected String value = null;
			public ImportsToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ImportsToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "imports";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class PackageNameToken extends Token.Parsed{
			protected String value = null;
			public PackageNameToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public PackageNameToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "packageName";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class AnonymousClassToken extends Token.Parsed{
			protected String value = null;
			public AnonymousClassToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public AnonymousClassToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "anonymous_class";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class InnerToken extends Token.Parsed{
			protected String value = null;
			public InnerToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public InnerToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "inner";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class WeakToken extends Token.Parsed{
			protected String value = null;
			public WeakToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public WeakToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "weak";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassDeclarationToken extends Token.Parsed{
			protected String value = null;
			public ClassDeclarationToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassDeclarationToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_declaration";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassElementToken extends Token.Parsed{
			protected String value = null;
			public ClassElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyElementToken extends Token.Parsed{
			protected String value = null;
			public BodyElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyConditionalToken extends Token.Parsed{
			protected String value = null;
			public BodyConditionalToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyConditionalToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_conditional";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyStatementToken extends Token.Parsed{
			protected String value = null;
			public BodyStatementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyStatementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_statement";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyCallToken extends Token.Parsed{
			protected String value = null;
			public BodyCallToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyCallToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_call";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyManipulateToken extends Token.Parsed{
			protected String value = null;
			public BodyManipulateToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyManipulateToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_manipulate";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodParametersToken extends Token.Parsed{
			protected String value = null;
			public MethodParametersToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodParametersToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_parameters";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodArgumentToken extends Token.Parsed{
			protected String value = null;
			public MethodArgumentToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodArgumentToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_argument";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodDeclarationToken extends Token.Parsed{
			protected String value = null;
			public MethodDeclarationToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodDeclarationToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_declaration";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class VariableDeclarationToken extends Token.Parsed{
			protected String value = null;
			public VariableDeclarationToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public VariableDeclarationToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "variable_declaration";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class VariableAssignmentToken extends Token.Parsed{
			protected String value = null;
			public VariableAssignmentToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public VariableAssignmentToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "variable_assignment";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class VariableNameDefinitionToken extends Token.Parsed{
			protected String value = null;
			public VariableNameDefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public VariableNameDefinitionToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "variable_name_definition";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassNameDefinitionToken extends Token.Parsed{
			protected String value = null;
			public ClassNameDefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassNameDefinitionToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_name_definition";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class AllTypeNameToken extends Token.Parsed{
			protected String value = null;
			public AllTypeNameToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public AllTypeNameToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "all_type_name";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class NameVarToken extends Token.Parsed{
			protected String value = null;
			public NameVarToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public NameVarToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "name_var";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class NameVarElementToken extends Token.Parsed{
			protected String value = null;
			public NameVarElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public NameVarElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "name_var_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class TypeVarToken extends Token.Parsed{
			protected String value = null;
			public TypeVarToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public TypeVarToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "type_var";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class TypeVarElementToken extends Token.Parsed{
			protected String value = null;
			public TypeVarElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public TypeVarElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "type_var_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
	}
}