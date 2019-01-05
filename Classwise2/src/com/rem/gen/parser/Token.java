package com.rem.gen.parser;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.EnumMap;
import java.util.Map;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;

public interface Token{
	public Token get(Token.Id tokenName);
	public Token getLast();
	public Token getLast(Token.Id tokenName);
	public List<Token> getAllSafely(Token.Id id);
	public List<Token> getAll();
	public void add(Token newToken);
	public String err();
	public String err(int tab);
	public void print();
	public void print(int tab);
	public void printShort();
	public String getFileName();
	public int getLineNumber();
	public String getValue();
	public Token.Id getName();
	public static enum Id{
		_SYNTAX,
		ROOT,
		ANON,
		_base,
		_comments,
		_comment,
		_NAME,
		_OPERATOR,
		_exact,
		_instance,
		_NUMBER,
		_quote,
		_class_body,
		_method_body,
		_method_arguments,
		_template_parameters,
		_template_parameter,
		_array_parameters,
		_statement_as_quote,
		_statement_as_string,
		_statement_as_method,
		_statement_as_braced,
		_as_statement,
		_cast_statement,
		_statement_as_char,
		_value,
		_class_file_name,
		_base_element,
		_import_clws,
		_import_clws__file_import,
		_import_imports,
		_import_imports__file_import,
		_packageName,
		_anonymous_classes,
		_anonymous_class,
		_anonymous_class_name,
		_className,
		_inner,
		_weak,
		_class_declaration,
		_static,
		_objectType,
		_templateTypeName,
		_parentName,
		_interfaceName,
		_class_element,
		_inheritance_declaration,
		_variableName,
		_comma,
		_body_element,
		_body_return,
		_body_throw,
		_body_conditional,
		_conditional_operator,
		_conditional_choice,
		_variable_names,
		_as_body,
		_conditional,
		_variable_declaration,
		_isFinal,
		_PRINT,
		_exception,
		_body_statement,
		_NOT,
		_body_call,
		_group,
		_name_var,
		_separator,
		_as_braced,
		_inline_method_reference,
		_NEW,
		_all_type_name,
		_body_add_to_class,
		_accessMethod,
		_add,
		_body,
		_body_access_token,
		_tokenAccess,
		_access,
		_get,
		_tokenInstance,
		_tokenName,
		_method_prototype_parameters,
		_method_parameters,
		_parameter,
		_method_argument,
		_lambda,
		_body_entries,
		_method_declaration,
		_ARRAY_TYPE,
		_methodName,
		_inline,
		_variableParameters,
		_throwsException,
		_variable_assignment,
		_variable_name_definition,
		_non_class_variable_name_definition,
		_typeName,
		_INLINE_LIST,
		_class_variable_name_definition,
		_non_class_name,
		_OutputClass,
		_OutputMethod,
		_OutputVariable,
		_OutputContext,
		_OutputConditionalHeader,
		_OutputConcatenation,
		_OutputQuote,
		_OutputCast,
		_OutputString,
		_OutputStatement,
		_OutputParameters,
		_OutputArguments,
		_Token,
		_Id,
		_Parser,
		_OutputConditional,
		_OutputNewObject,
		_Result,
		_Pass,
		_Fail,
		_OutputBody,
		_OutputType,
		_OutputExact,
		_CallableOutput,
		_OutputCall,
		_OutputHelper,
		_OutputOperator,
		_OutputStaticCall,
		_OutputLambda,
		_OutputBraced,
		_Output,
		_LineableOutput,
		_getAllSafely,
		_name_atom,
		_class_variable_names,
		_type_var,
		_asVariable,
		_type_atom,
		_class,
		_class_names
	}
	public static class Parsed{
		protected List<Token.Parsed> children = new ArrayList<Token.Parsed>();
		protected List<Integer> positions = new ArrayList<Integer>();
		protected Token.Id name = null;
		public Parsed(final Token.Id name) {
			if(name != null){
				this.name = name;
			}
		}
		public Parsed() {
		}
		public List<Token.Parsed> getChildren() {
			return children;
		}
		public void setChildren(List<Token.Parsed> newChildren) {
			children = newChildren;
		}
		public List<Integer> getPositions() {
			return positions;
		}
		public void setPositions(List<Integer> newPositions) {
			positions = newPositions;
		}
		public Token.Id getName() {
			return name;
		}
		public void setName(Token.Id newName) {
			name = newName;
		}
		public String getValue() {
			if(children.isEmpty()) {
				return null;
			}
			else {
				return children.get(0).getValue();
			}
		}
		public void setValue(String newValue) {
		}
		public String getLastValue() {
			if(children.isEmpty()) {
				return null;
			}
			else {
				return children.get(children.size()-1).getValue();
			}
		}
		public void add(Integer position,Token.Parsed newToken) {
			children.add(newToken);
			positions.add(position);
		}
		public void addAll(Token.Parsed inductee) {
			for(Integer i = 0;i<inductee.children.size();i++) {
				children.add(inductee.children.get(i));
				positions.add(inductee.positions.get(i));
			}
		}
		public static class Import extends Token.Parsed{
			protected String fileName = null;
			public Import(final Token.Id initalSuperName, final String fileName) {
				super(initalSuperName);
				this.fileName = fileName;
			}
			public Import(final String fileName) {
				this.fileName = fileName;
			}
			public Import() {
			}
			public String getFileName() {
				return fileName;
			}
			public void setFileName(String newFileName) {
				fileName = newFileName;
			}
			public List<Token.Parsed> getChildren() {
				return Parser.contexts.get(fileName).get_root().getChildren();
			}
			public List<Integer> getPositions() {
				return Parser.contexts.get(fileName).get_root().getPositions();
			}
		}
	}
	public static class Leaf implements Token{
		protected Token.Id name = null;
		protected String value = null;
		protected Integer position = null;
		protected Integer parentPosition = null;
		protected Parser.Result.Pass context = null;
		public Leaf(final Token.Id name, final String value, final Integer position, final Integer parentPosition, final Parser.Result.Pass context) {
			if(name != null){
				this.name = name;
			}
			this.value = value;
			this.position = position;
			this.parentPosition = parentPosition;
			if(context != null){
				this.context = context;
			}
		}
		public Leaf() {
		}
		public Token.Id getName() {
			return name;
		}
		public void setName(Token.Id newName) {
			name = newName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String newValue) {
			value = newValue;
		}
		public Integer getPosition() {
			return position;
		}
		public void setPosition(Integer newPosition) {
			position = newPosition;
		}
		public Integer getParentPosition() {
			return parentPosition;
		}
		public void setParentPosition(Integer newParentPosition) {
			parentPosition = newParentPosition;
		}
		public Parser.Result.Pass getContext() {
			return context;
		}
		public void setContext(Parser.Result.Pass newContext) {
			context = newContext;
		}
		public Token get(Token.Id tokenName) {
			return this;
		}
		public Token getLast() {
			return null;
		}
		public Token getLast(Token.Id tokenName) {
			return null;
		}
		public void add(Token token) {
		}
		public List<Token> getAllSafely(Token.Id id) {
			return new ArrayList<Token>();
		}
		public List<Token> getAll() {
			return null;
		}
		public String toString() {
			return getValue();
		}
		public String err() {
			return shortString();
		}
		public String err(int tab) {
			StringBuilder builder = new StringBuilder();
			for(Integer i = 0;i<tab;i++) {
				builder.append("  ");
			}
			builder.append(shortString());
			return builder.toString();
		}
		public void print() {
			printShort();
		}
		public void print(int tab) {
			for(Integer i = 0;i<tab;i++) {
				System.out.print("  ");
			}
			printShort();
		}
		public void printShort() {
			System.out.println(shortString());
		}
		public String shortString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			builder.append(name);
			builder.append(":");
			builder.append(value);
			builder.append("]");
			return builder.toString();
		}
		public String getFileName() {
			return context.getFileName();
		}
		public int getLineNumber() {
			return context.getLineNumber(position);
		}
	}
	public static class Branch implements Token{
		protected Map<Token.Id,List<Token>> namedLists = new EnumMap<Token.Id,List<Token>>(Token.Id.class);
		protected List<Token> children = new ArrayList<Token>();
		protected Token.Id name = null;
		protected Integer position = null;
		protected Integer parentPosition = null;
		protected Parser.Result.Pass context = null;
		public Branch(final Token.Id name, final Integer position, final Integer parentPosition, final Parser.Result.Pass context) {
			if(name != null){
				this.name = name;
			}
			this.position = position;
			this.parentPosition = parentPosition;
			if(context != null){
				this.context = context;
			}
		}
		public Branch() {
		}
		public Map<Token.Id,List<Token>> getNamedLists() {
			return namedLists;
		}
		public void setNamedLists(Map<Token.Id,List<Token>> newNamedLists) {
			namedLists = newNamedLists;
		}
		public List<Token> getChildren() {
			return children;
		}
		public void setChildren(List<Token> newChildren) {
			children = newChildren;
		}
		public Token.Id getName() {
			return name;
		}
		public void setName(Token.Id newName) {
			name = newName;
		}
		public Integer getPosition() {
			return position;
		}
		public void setPosition(Integer newPosition) {
			position = newPosition;
		}
		public Integer getParentPosition() {
			return parentPosition;
		}
		public void setParentPosition(Integer newParentPosition) {
			parentPosition = newParentPosition;
		}
		public Parser.Result.Pass getContext() {
			return context;
		}
		public void setContext(Parser.Result.Pass newContext) {
			context = newContext;
		}
		public Token get(Token.Id tokenName) {
			List<Token> nameList = namedLists.get(tokenName);
			if(nameList==null||nameList.isEmpty()) {
				return null;
			}
			else {
				return nameList.get(0);
			}
		}
		public String getValue() {
			return children.get(0).getValue();
		}
		public String toString() {
			return children.get(0).getValue();
		}
		public Token getLast() {
			return children.get(children.size()-1);
		}
		public Token getLast(Token.Id tokenName) {
			return namedLists.get(tokenName).get(namedLists.get(tokenName).size()-1);
		}
		public void add(Token token) {
			children.add(token);
			if(namedLists.containsKey(token.getName())==false) {
				namedLists.put(token.getName(),new ArrayList<Token>());
			}
			namedLists.get(token.getName()).add(token);
		}
		public List<Token> getAllSafely(Token.Id id) {
			List<Token> list = namedLists.get(id);
			if(list==null) {
				return new ArrayList<Token>();
			}
			else {
				return list;
			}
		}
		public List<Token> getAll() {
			return children;
		}
		public String err() {
			StringBuilder builder = new StringBuilder();
			builder.append(":>");
			builder.append(name);
			for(Token node:children) {
				builder.append(node.err(1));
			}
			return builder.toString();
		}
		public String err(int tab) {
			StringBuilder builder = new StringBuilder();
			for(Integer i = 0;i<tab;i++) {
				builder.append("  ");
			}
			builder.append(name);
			for(Token node:children) {
				builder.append(node.err(tab+1));
			}
			return builder.toString();
		}
		public void print() {
			System.out.println(":>"+name);
			for(Token node:children) {
				node.print(1);
			}
		}
		public void print(int tab) {
			for(Integer i = 0;i<tab;i++) {
				System.out.print("  ");
			}
			System.out.println(name);
			for(Token node:children) {
				node.print(tab+1);
			}
		}
		public void printShort() {
			for(Token node:children) {
				System.out.print("[");
				System.out.print(node.getName());
				System.out.print(":");
				System.out.print(node.getValue());
				System.out.print("]");
			}
			System.out.println();
		}
		public String getFileName() {
			return context.getFileName();
		}
		public int getLineNumber() {
			return context.getLineNumber(position);
		}
	}
}