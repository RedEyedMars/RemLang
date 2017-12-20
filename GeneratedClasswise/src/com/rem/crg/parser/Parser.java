package com.rem.crg.parser;
import java.util.*;
import java.io.*;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.HashSet;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import com.rem.crg.parser.Token;
import com.rem.crg.parser.Parser;
import com.rem.crg.parser.Tokens;

public  class Parser{
	public static Integer SUCCESS = 0;
	public static Integer FAILED = 1;
	public static Integer FIRST_PASS = 0;
	public static Integer SECOND_PASS = 1;
	public static List<String> fileNames = new ArrayList<String>();
	public static Map<String,Parser.Context> contexts = new HashMap<String,Parser.Context>();
	protected Parser.NameList.Root class_names_root = new Parser.NameList.Root();
	protected Parser.NameList.Root class_variable_names_root = new Parser.NameList.Root();
	protected Parser.NameList.Root variable_names_root = new Parser.NameList.Root();
	public Parser() {
	}
	public Parser.Result parse(String fileName){
		Parser.Result firstResult = parseFile(fileName,FIRST_PASS);
		if (firstResult.getState()==SUCCESS){
			System.out.println("First-Pass Successful");
			Parser.Result secondResult = parseFile(fileName,SECOND_PASS);
			if (secondResult.getState()==SUCCESS){
				System.out.println("Second-Pass Successful");
			}
			else {
				System.out.println("Second-Pass Failed");
			}
			return secondResult;
		}
		else {
			System.out.println("First-Pass Failed");
			return firstResult;
		}
	}
	public Parser.Result parseFile(String fileName,int _pass){
		class_names_root.add("void");
		class_names_root.add("boolean");
		class_names_root.add("Boolean");
		class_names_root.add("int");
		class_names_root.add("Integer");
		class_names_root.add("long");
		class_names_root.add("Long");
		class_names_root.add("float");
		class_names_root.add("Float");
		class_names_root.add("double");
		class_names_root.add("Double");
		class_names_root.add("byte");
		class_names_root.add("Byte");
		class_names_root.add("char");
		class_names_root.add("Character");
		class_names_root.add("String");
		class_names_root.add("Object");
		class_names_root.add("System");
		class_names_root.add("Math");
		class_names_root.add("Thread");
		class_names_root.add("Runnable");
		class_names_root.add("Iterator");
		class_names_root.add("Iterable");
		class_names_root.add("Serializable");
		class_names_root.add("Random");
		if (_pass==FIRST_PASS){
			Parser.Context context = new Parser.Context();
			contexts.put(fileName,context);
			return context.parse(fileName,FIRST_PASS);
		}
		else {
			contexts.get(fileName)._root=new Token.Parsed("$ROOT");
			contexts.get(fileName)._token=contexts.get(fileName)._root;
			return contexts.get(fileName).parse(fileName,SECOND_PASS);
		}
	}
	public static String readLine(String input,int position){
		int indexOfLine = input.indexOf('\n',position);
		if (indexOfLine==-1 ){
			return input.substring(position);
		}
		else {
			return input.substring(position,indexOfLine);
		}
	}
	public Parser.NameList.Root getClassNamesRoot(){
		return class_names_root;
	}
	public void setClassNamesRoot(Parser.NameList.Root newClassNamesRoot){
		class_names_root = newClassNamesRoot;
	}
	public Parser.NameList.Root getClassVariableNamesRoot(){
		return class_variable_names_root;
	}
	public void setClassVariableNamesRoot(Parser.NameList.Root newClassVariableNamesRoot){
		class_variable_names_root = newClassVariableNamesRoot;
	}
	public Parser.NameList.Root getVariableNamesRoot(){
		return variable_names_root;
	}
	public void setVariableNamesRoot(Parser.NameList.Root newVariableNamesRoot){
		variable_names_root = newVariableNamesRoot;
	}
	public  class Context{
		protected int _pass = FIRST_PASS;
		protected int _position = 0;
		protected int _inputLength = -1;
		protected int _state = SUCCESS;
		protected int _furthestPosition = -1;
		protected int _lineNumber = 1;
		protected String _input = null;
		protected String _fileName = null;
		protected char[] _inputArray = null;
		protected Parser.Result _result = null;
		protected Parser.Result.Acceptor _result_acceptor = new Parser.Result.Acceptor();
		protected Boolean _succeedOnEnd = true;
		protected String _list_name_result = null;
		protected List<Integer> _lineNumberRanges = new ArrayList<Integer>();
		protected Token.Parsed _root = new Token.Parsed("$ROOT");
		protected Token.Parsed _token = _root;
		protected Map<Integer,Integer> brace_0 = new HashMap<Integer,Integer>();
		protected Parser.NameList class_names = class_names_root;
		protected Parser.NameList class_names_additions = null;
		protected Map<Integer,Parser.NameList> class_names_first_passes = new HashMap<Integer,Parser.NameList>();
		protected Parser.NameList class_variable_names = class_variable_names_root;
		protected Parser.NameList class_variable_names_additions = null;
		protected Map<Integer,Parser.NameList> class_variable_names_first_passes = new HashMap<Integer,Parser.NameList>();
		protected Parser.NameList variable_names = variable_names_root;
		protected Parser.NameList variable_names_additions = null;
		protected Map<Integer,Parser.NameList> variable_names_first_passes = new HashMap<Integer,Parser.NameList>();
		protected Map<Integer,Integer> brace_1 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_2 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_3 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_4 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_5 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_6 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_7 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_8 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_9 = new HashMap<Integer,Integer>();
		protected int _readInput_1 = 0;
		protected Parser.NameList class_variable_names_array_parameters = null;
		protected Parser.NameList variable_names_array_parameters = null;
		protected Set<Integer> _recursion_protection_array_parameters_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_conditional_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_conditional_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_conditional_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_conditional_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_conditional_4 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_quote = null;
		protected Parser.NameList variable_names_quote = null;
		protected Set<Integer> _recursion_protection_quote_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_4 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_5 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_6 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_7 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_statement_as_quote = null;
		protected Parser.NameList variable_names_statement_as_quote = null;
		protected Set<Integer> _recursion_protection_statement_as_quote_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_element_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_element_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_element_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_element_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_element_4 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_element_5 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_element_6 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_element_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_element_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_element_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_element_3 = new HashSet<Integer>();
		protected Parser.NameList variable_names_additions__anonymous_132 = null;
		protected Set<Integer> _recursion_protection_class_name_definition_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_name_definition_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_OPERATOR_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_call_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_call_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_call_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_call_3 = new HashSet<Integer>();
		protected Parser.NameList class_names_additions__anonymous_41 = null;
		protected Parser.NameList class_variable_names_additions__anonymous_41 = null;
		protected Set<Integer> _recursion_protection_NUMBER_0 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_class_body = null;
		protected Parser.NameList variable_names_class_body = null;
		protected Set<Integer> _recursion_protection_class_body_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_argument_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_argument_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_argument_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_argument_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_argument_4 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_argument_5 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_0 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_additions__anonymous_133 = null;
		protected Parser.NameList class_names_additions__anonymous_31 = null;
		protected Parser.NameList class_variable_names_additions__anonymous_31 = null;
		protected Parser.NameList class_variable_names_method_arguments = null;
		protected Parser.NameList variable_names_method_arguments = null;
		protected Set<Integer> _recursion_protection_method_arguments_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_parameters_0 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_method_body = null;
		protected Parser.NameList variable_names_method_body = null;
		protected Set<Integer> _recursion_protection_method_body_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_4 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_5 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_6 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_7 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_element_8 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_weak_0 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_statement_as_braced = null;
		protected Parser.NameList variable_names_statement_as_braced = null;
		protected Set<Integer> _recursion_protection_statement_as_braced_0 = new HashSet<Integer>();
		protected Parser.NameList class_names_additions_anonymous_class = null;
		protected Set<Integer> _recursion_protection_anonymous_class_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_base_element_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_base_element_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_base_element_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_base_element_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_base_element_4 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_base_element_5 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_imports_0 = new HashSet<Integer>();
		protected Parser.NameList variable_names_additions_body_manipulate = null;
		protected Set<Integer> _recursion_protection_body_manipulate_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_manipulate_1 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_as_statement = null;
		protected Parser.NameList variable_names_as_statement = null;
		protected Set<Integer> _recursion_protection_as_statement_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_declaration_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_0 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_statement_as_string = null;
		protected Parser.NameList variable_names_statement_as_string = null;
		protected Set<Integer> _recursion_protection_statement_as_string_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_element_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_element_1 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_element_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_element_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_element_4 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_assignment_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_packageName_0 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_comments = null;
		protected Parser.NameList variable_names_comments = null;
		protected Set<Integer> _recursion_protection_comments_0 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_template_parameters = null;
		protected Parser.NameList variable_names_template_parameters = null;
		protected Set<Integer> _recursion_protection_template_parameters_0 = new HashSet<Integer>();
		protected Parser.NameList class_names_additions_class_declaration = null;
		protected Parser.NameList class_variable_names_additions_class_declaration = null;
		protected Set<Integer> _recursion_protection_class_declaration_0 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_declaration_1 = new HashSet<Integer>();
		protected Parser.NameList class_variable_names_statement_as_method = null;
		protected Parser.NameList variable_names_statement_as_method = null;
		protected Set<Integer> _recursion_protection_statement_as_method_0 = new HashSet<Integer>();
		public Context(final String _input, final String _fileName, final char[] _inputArray, final Parser.Result _result, final Parser.Result.Acceptor _result_acceptor, final Boolean _succeedOnEnd, final String _list_name_result, final List<Integer> _lineNumberRanges, final Token.Parsed _root, final Token.Parsed _token, final Map<Integer,Integer> brace_0, final Parser.NameList class_names, final Parser.NameList class_names_additions, final Map<Integer,Parser.NameList> class_names_first_passes, final Parser.NameList class_variable_names, final Parser.NameList class_variable_names_additions, final Map<Integer,Parser.NameList> class_variable_names_first_passes, final Parser.NameList variable_names, final Parser.NameList variable_names_additions, final Map<Integer,Parser.NameList> variable_names_first_passes, final Map<Integer,Integer> brace_1, final Map<Integer,Integer> brace_2, final Map<Integer,Integer> brace_3, final Map<Integer,Integer> brace_4, final Map<Integer,Integer> brace_5, final Map<Integer,Integer> brace_6, final Map<Integer,Integer> brace_7, final Map<Integer,Integer> brace_8, final Map<Integer,Integer> brace_9, final Parser.NameList class_variable_names_array_parameters, final Parser.NameList variable_names_array_parameters, final Set<Integer> _recursion_protection_array_parameters_0, final Set<Integer> _recursion_protection_body_conditional_0, final Set<Integer> _recursion_protection_body_conditional_1, final Set<Integer> _recursion_protection_body_conditional_2, final Set<Integer> _recursion_protection_body_conditional_3, final Set<Integer> _recursion_protection_body_conditional_4, final Parser.NameList class_variable_names_quote, final Parser.NameList variable_names_quote, final Set<Integer> _recursion_protection_quote_0, final Set<Integer> _recursion_protection_all_type_name_0, final Set<Integer> _recursion_protection_all_type_name_1, final Set<Integer> _recursion_protection_all_type_name_2, final Set<Integer> _recursion_protection_all_type_name_3, final Set<Integer> _recursion_protection_all_type_name_4, final Set<Integer> _recursion_protection_all_type_name_5, final Set<Integer> _recursion_protection_all_type_name_6, final Set<Integer> _recursion_protection_all_type_name_7, final Parser.NameList class_variable_names_statement_as_quote, final Parser.NameList variable_names_statement_as_quote, final Set<Integer> _recursion_protection_statement_as_quote_0, final Set<Integer> _recursion_protection_name_var_element_0, final Set<Integer> _recursion_protection_name_var_element_1, final Set<Integer> _recursion_protection_name_var_element_2, final Set<Integer> _recursion_protection_name_var_element_3, final Set<Integer> _recursion_protection_name_var_element_4, final Set<Integer> _recursion_protection_name_var_element_5, final Set<Integer> _recursion_protection_name_var_element_6, final Set<Integer> _recursion_protection_class_element_0, final Set<Integer> _recursion_protection_class_element_1, final Set<Integer> _recursion_protection_class_element_2, final Set<Integer> _recursion_protection_class_element_3, final Parser.NameList variable_names_additions__anonymous_132, final Set<Integer> _recursion_protection_class_name_definition_0, final Set<Integer> _recursion_protection_variable_declaration_0, final Set<Integer> _recursion_protection_variable_name_definition_0, final Set<Integer> _recursion_protection_OPERATOR_0, final Set<Integer> _recursion_protection_body_call_0, final Set<Integer> _recursion_protection_body_call_1, final Set<Integer> _recursion_protection_body_call_2, final Set<Integer> _recursion_protection_body_call_3, final Parser.NameList class_names_additions__anonymous_41, final Parser.NameList class_variable_names_additions__anonymous_41, final Set<Integer> _recursion_protection_NUMBER_0, final Parser.NameList class_variable_names_class_body, final Parser.NameList variable_names_class_body, final Set<Integer> _recursion_protection_class_body_0, final Set<Integer> _recursion_protection_method_argument_0, final Set<Integer> _recursion_protection_method_argument_1, final Set<Integer> _recursion_protection_method_argument_2, final Set<Integer> _recursion_protection_method_argument_3, final Set<Integer> _recursion_protection_method_argument_4, final Set<Integer> _recursion_protection_method_argument_5, final Set<Integer> _recursion_protection_NAME_0, final Parser.NameList class_variable_names_additions__anonymous_133, final Parser.NameList class_names_additions__anonymous_31, final Parser.NameList class_variable_names_additions__anonymous_31, final Parser.NameList class_variable_names_method_arguments, final Parser.NameList variable_names_method_arguments, final Set<Integer> _recursion_protection_method_arguments_0, final Set<Integer> _recursion_protection_name_var_0, final Set<Integer> _recursion_protection_name_var_1, final Set<Integer> _recursion_protection_name_var_2, final Set<Integer> _recursion_protection_method_parameters_0, final Parser.NameList class_variable_names_method_body, final Parser.NameList variable_names_method_body, final Set<Integer> _recursion_protection_method_body_0, final Set<Integer> _recursion_protection_body_element_0, final Set<Integer> _recursion_protection_body_element_1, final Set<Integer> _recursion_protection_body_element_2, final Set<Integer> _recursion_protection_body_element_3, final Set<Integer> _recursion_protection_body_element_4, final Set<Integer> _recursion_protection_body_element_5, final Set<Integer> _recursion_protection_body_element_6, final Set<Integer> _recursion_protection_body_element_7, final Set<Integer> _recursion_protection_body_element_8, final Set<Integer> _recursion_protection_type_var_0, final Set<Integer> _recursion_protection_type_var_1, final Set<Integer> _recursion_protection_type_var_2, final Set<Integer> _recursion_protection_type_var_3, final Set<Integer> _recursion_protection_body_statement_0, final Set<Integer> _recursion_protection_body_statement_1, final Set<Integer> _recursion_protection_weak_0, final Parser.NameList class_variable_names_statement_as_braced, final Parser.NameList variable_names_statement_as_braced, final Set<Integer> _recursion_protection_statement_as_braced_0, final Parser.NameList class_names_additions_anonymous_class, final Set<Integer> _recursion_protection_anonymous_class_0, final Set<Integer> _recursion_protection_base_element_0, final Set<Integer> _recursion_protection_base_element_1, final Set<Integer> _recursion_protection_base_element_2, final Set<Integer> _recursion_protection_base_element_3, final Set<Integer> _recursion_protection_base_element_4, final Set<Integer> _recursion_protection_base_element_5, final Set<Integer> _recursion_protection_imports_0, final Parser.NameList variable_names_additions_body_manipulate, final Set<Integer> _recursion_protection_body_manipulate_0, final Set<Integer> _recursion_protection_body_manipulate_1, final Parser.NameList class_variable_names_as_statement, final Parser.NameList variable_names_as_statement, final Set<Integer> _recursion_protection_as_statement_0, final Set<Integer> _recursion_protection_method_declaration_0, final Set<Integer> _recursion_protection_inner_0, final Parser.NameList class_variable_names_statement_as_string, final Parser.NameList variable_names_statement_as_string, final Set<Integer> _recursion_protection_statement_as_string_0, final Set<Integer> _recursion_protection_type_var_element_0, final Set<Integer> _recursion_protection_type_var_element_1, final Set<Integer> _recursion_protection_type_var_element_2, final Set<Integer> _recursion_protection_type_var_element_3, final Set<Integer> _recursion_protection_type_var_element_4, final Set<Integer> _recursion_protection_variable_assignment_0, final Set<Integer> _recursion_protection_packageName_0, final Parser.NameList class_variable_names_comments, final Parser.NameList variable_names_comments, final Set<Integer> _recursion_protection_comments_0, final Parser.NameList class_variable_names_template_parameters, final Parser.NameList variable_names_template_parameters, final Set<Integer> _recursion_protection_template_parameters_0, final Parser.NameList class_names_additions_class_declaration, final Parser.NameList class_variable_names_additions_class_declaration, final Set<Integer> _recursion_protection_class_declaration_0, final Set<Integer> _recursion_protection_class_declaration_1, final Parser.NameList class_variable_names_statement_as_method, final Parser.NameList variable_names_statement_as_method, final Set<Integer> _recursion_protection_statement_as_method_0) {
			if(_input != null){
				this._input = _input;
			}
			if(_fileName != null){
				this._fileName = _fileName;
			}
			if(_inputArray != null){
				this._inputArray = _inputArray;
			}
			if(_result != null){
				this._result = _result;
			}
			if(_result_acceptor != null){
				this._result_acceptor = _result_acceptor;
			}
			if(_succeedOnEnd != null){
				this._succeedOnEnd = _succeedOnEnd;
			}
			if(_list_name_result != null){
				this._list_name_result = _list_name_result;
			}
			if(_lineNumberRanges != null){
				this._lineNumberRanges = _lineNumberRanges;
			}
			if(_root != null){
				this._root = _root;
			}
			if(_token != null){
				this._token = _token;
			}
			if(brace_0 != null){
				this.brace_0 = brace_0;
			}
			if(class_names != null){
				this.class_names = class_names;
			}
			if(class_names_additions != null){
				this.class_names_additions = class_names_additions;
			}
			if(class_names_first_passes != null){
				this.class_names_first_passes = class_names_first_passes;
			}
			if(class_variable_names != null){
				this.class_variable_names = class_variable_names;
			}
			if(class_variable_names_additions != null){
				this.class_variable_names_additions = class_variable_names_additions;
			}
			if(class_variable_names_first_passes != null){
				this.class_variable_names_first_passes = class_variable_names_first_passes;
			}
			if(variable_names != null){
				this.variable_names = variable_names;
			}
			if(variable_names_additions != null){
				this.variable_names_additions = variable_names_additions;
			}
			if(variable_names_first_passes != null){
				this.variable_names_first_passes = variable_names_first_passes;
			}
			if(brace_1 != null){
				this.brace_1 = brace_1;
			}
			if(brace_2 != null){
				this.brace_2 = brace_2;
			}
			if(brace_3 != null){
				this.brace_3 = brace_3;
			}
			if(brace_4 != null){
				this.brace_4 = brace_4;
			}
			if(brace_5 != null){
				this.brace_5 = brace_5;
			}
			if(brace_6 != null){
				this.brace_6 = brace_6;
			}
			if(brace_7 != null){
				this.brace_7 = brace_7;
			}
			if(brace_8 != null){
				this.brace_8 = brace_8;
			}
			if(brace_9 != null){
				this.brace_9 = brace_9;
			}
			if(class_variable_names_array_parameters != null){
				this.class_variable_names_array_parameters = class_variable_names_array_parameters;
			}
			if(variable_names_array_parameters != null){
				this.variable_names_array_parameters = variable_names_array_parameters;
			}
			if(_recursion_protection_array_parameters_0 != null){
				this._recursion_protection_array_parameters_0 = _recursion_protection_array_parameters_0;
			}
			if(_recursion_protection_body_conditional_0 != null){
				this._recursion_protection_body_conditional_0 = _recursion_protection_body_conditional_0;
			}
			if(_recursion_protection_body_conditional_1 != null){
				this._recursion_protection_body_conditional_1 = _recursion_protection_body_conditional_1;
			}
			if(_recursion_protection_body_conditional_2 != null){
				this._recursion_protection_body_conditional_2 = _recursion_protection_body_conditional_2;
			}
			if(_recursion_protection_body_conditional_3 != null){
				this._recursion_protection_body_conditional_3 = _recursion_protection_body_conditional_3;
			}
			if(_recursion_protection_body_conditional_4 != null){
				this._recursion_protection_body_conditional_4 = _recursion_protection_body_conditional_4;
			}
			if(class_variable_names_quote != null){
				this.class_variable_names_quote = class_variable_names_quote;
			}
			if(variable_names_quote != null){
				this.variable_names_quote = variable_names_quote;
			}
			if(_recursion_protection_quote_0 != null){
				this._recursion_protection_quote_0 = _recursion_protection_quote_0;
			}
			if(_recursion_protection_all_type_name_0 != null){
				this._recursion_protection_all_type_name_0 = _recursion_protection_all_type_name_0;
			}
			if(_recursion_protection_all_type_name_1 != null){
				this._recursion_protection_all_type_name_1 = _recursion_protection_all_type_name_1;
			}
			if(_recursion_protection_all_type_name_2 != null){
				this._recursion_protection_all_type_name_2 = _recursion_protection_all_type_name_2;
			}
			if(_recursion_protection_all_type_name_3 != null){
				this._recursion_protection_all_type_name_3 = _recursion_protection_all_type_name_3;
			}
			if(_recursion_protection_all_type_name_4 != null){
				this._recursion_protection_all_type_name_4 = _recursion_protection_all_type_name_4;
			}
			if(_recursion_protection_all_type_name_5 != null){
				this._recursion_protection_all_type_name_5 = _recursion_protection_all_type_name_5;
			}
			if(_recursion_protection_all_type_name_6 != null){
				this._recursion_protection_all_type_name_6 = _recursion_protection_all_type_name_6;
			}
			if(_recursion_protection_all_type_name_7 != null){
				this._recursion_protection_all_type_name_7 = _recursion_protection_all_type_name_7;
			}
			if(class_variable_names_statement_as_quote != null){
				this.class_variable_names_statement_as_quote = class_variable_names_statement_as_quote;
			}
			if(variable_names_statement_as_quote != null){
				this.variable_names_statement_as_quote = variable_names_statement_as_quote;
			}
			if(_recursion_protection_statement_as_quote_0 != null){
				this._recursion_protection_statement_as_quote_0 = _recursion_protection_statement_as_quote_0;
			}
			if(_recursion_protection_name_var_element_0 != null){
				this._recursion_protection_name_var_element_0 = _recursion_protection_name_var_element_0;
			}
			if(_recursion_protection_name_var_element_1 != null){
				this._recursion_protection_name_var_element_1 = _recursion_protection_name_var_element_1;
			}
			if(_recursion_protection_name_var_element_2 != null){
				this._recursion_protection_name_var_element_2 = _recursion_protection_name_var_element_2;
			}
			if(_recursion_protection_name_var_element_3 != null){
				this._recursion_protection_name_var_element_3 = _recursion_protection_name_var_element_3;
			}
			if(_recursion_protection_name_var_element_4 != null){
				this._recursion_protection_name_var_element_4 = _recursion_protection_name_var_element_4;
			}
			if(_recursion_protection_name_var_element_5 != null){
				this._recursion_protection_name_var_element_5 = _recursion_protection_name_var_element_5;
			}
			if(_recursion_protection_name_var_element_6 != null){
				this._recursion_protection_name_var_element_6 = _recursion_protection_name_var_element_6;
			}
			if(_recursion_protection_class_element_0 != null){
				this._recursion_protection_class_element_0 = _recursion_protection_class_element_0;
			}
			if(_recursion_protection_class_element_1 != null){
				this._recursion_protection_class_element_1 = _recursion_protection_class_element_1;
			}
			if(_recursion_protection_class_element_2 != null){
				this._recursion_protection_class_element_2 = _recursion_protection_class_element_2;
			}
			if(_recursion_protection_class_element_3 != null){
				this._recursion_protection_class_element_3 = _recursion_protection_class_element_3;
			}
			if(variable_names_additions__anonymous_132 != null){
				this.variable_names_additions__anonymous_132 = variable_names_additions__anonymous_132;
			}
			if(_recursion_protection_class_name_definition_0 != null){
				this._recursion_protection_class_name_definition_0 = _recursion_protection_class_name_definition_0;
			}
			if(_recursion_protection_variable_declaration_0 != null){
				this._recursion_protection_variable_declaration_0 = _recursion_protection_variable_declaration_0;
			}
			if(_recursion_protection_variable_name_definition_0 != null){
				this._recursion_protection_variable_name_definition_0 = _recursion_protection_variable_name_definition_0;
			}
			if(_recursion_protection_OPERATOR_0 != null){
				this._recursion_protection_OPERATOR_0 = _recursion_protection_OPERATOR_0;
			}
			if(_recursion_protection_body_call_0 != null){
				this._recursion_protection_body_call_0 = _recursion_protection_body_call_0;
			}
			if(_recursion_protection_body_call_1 != null){
				this._recursion_protection_body_call_1 = _recursion_protection_body_call_1;
			}
			if(_recursion_protection_body_call_2 != null){
				this._recursion_protection_body_call_2 = _recursion_protection_body_call_2;
			}
			if(_recursion_protection_body_call_3 != null){
				this._recursion_protection_body_call_3 = _recursion_protection_body_call_3;
			}
			if(class_names_additions__anonymous_41 != null){
				this.class_names_additions__anonymous_41 = class_names_additions__anonymous_41;
			}
			if(class_variable_names_additions__anonymous_41 != null){
				this.class_variable_names_additions__anonymous_41 = class_variable_names_additions__anonymous_41;
			}
			if(_recursion_protection_NUMBER_0 != null){
				this._recursion_protection_NUMBER_0 = _recursion_protection_NUMBER_0;
			}
			if(class_variable_names_class_body != null){
				this.class_variable_names_class_body = class_variable_names_class_body;
			}
			if(variable_names_class_body != null){
				this.variable_names_class_body = variable_names_class_body;
			}
			if(_recursion_protection_class_body_0 != null){
				this._recursion_protection_class_body_0 = _recursion_protection_class_body_0;
			}
			if(_recursion_protection_method_argument_0 != null){
				this._recursion_protection_method_argument_0 = _recursion_protection_method_argument_0;
			}
			if(_recursion_protection_method_argument_1 != null){
				this._recursion_protection_method_argument_1 = _recursion_protection_method_argument_1;
			}
			if(_recursion_protection_method_argument_2 != null){
				this._recursion_protection_method_argument_2 = _recursion_protection_method_argument_2;
			}
			if(_recursion_protection_method_argument_3 != null){
				this._recursion_protection_method_argument_3 = _recursion_protection_method_argument_3;
			}
			if(_recursion_protection_method_argument_4 != null){
				this._recursion_protection_method_argument_4 = _recursion_protection_method_argument_4;
			}
			if(_recursion_protection_method_argument_5 != null){
				this._recursion_protection_method_argument_5 = _recursion_protection_method_argument_5;
			}
			if(_recursion_protection_NAME_0 != null){
				this._recursion_protection_NAME_0 = _recursion_protection_NAME_0;
			}
			if(class_variable_names_additions__anonymous_133 != null){
				this.class_variable_names_additions__anonymous_133 = class_variable_names_additions__anonymous_133;
			}
			if(class_names_additions__anonymous_31 != null){
				this.class_names_additions__anonymous_31 = class_names_additions__anonymous_31;
			}
			if(class_variable_names_additions__anonymous_31 != null){
				this.class_variable_names_additions__anonymous_31 = class_variable_names_additions__anonymous_31;
			}
			if(class_variable_names_method_arguments != null){
				this.class_variable_names_method_arguments = class_variable_names_method_arguments;
			}
			if(variable_names_method_arguments != null){
				this.variable_names_method_arguments = variable_names_method_arguments;
			}
			if(_recursion_protection_method_arguments_0 != null){
				this._recursion_protection_method_arguments_0 = _recursion_protection_method_arguments_0;
			}
			if(_recursion_protection_name_var_0 != null){
				this._recursion_protection_name_var_0 = _recursion_protection_name_var_0;
			}
			if(_recursion_protection_name_var_1 != null){
				this._recursion_protection_name_var_1 = _recursion_protection_name_var_1;
			}
			if(_recursion_protection_name_var_2 != null){
				this._recursion_protection_name_var_2 = _recursion_protection_name_var_2;
			}
			if(_recursion_protection_method_parameters_0 != null){
				this._recursion_protection_method_parameters_0 = _recursion_protection_method_parameters_0;
			}
			if(class_variable_names_method_body != null){
				this.class_variable_names_method_body = class_variable_names_method_body;
			}
			if(variable_names_method_body != null){
				this.variable_names_method_body = variable_names_method_body;
			}
			if(_recursion_protection_method_body_0 != null){
				this._recursion_protection_method_body_0 = _recursion_protection_method_body_0;
			}
			if(_recursion_protection_body_element_0 != null){
				this._recursion_protection_body_element_0 = _recursion_protection_body_element_0;
			}
			if(_recursion_protection_body_element_1 != null){
				this._recursion_protection_body_element_1 = _recursion_protection_body_element_1;
			}
			if(_recursion_protection_body_element_2 != null){
				this._recursion_protection_body_element_2 = _recursion_protection_body_element_2;
			}
			if(_recursion_protection_body_element_3 != null){
				this._recursion_protection_body_element_3 = _recursion_protection_body_element_3;
			}
			if(_recursion_protection_body_element_4 != null){
				this._recursion_protection_body_element_4 = _recursion_protection_body_element_4;
			}
			if(_recursion_protection_body_element_5 != null){
				this._recursion_protection_body_element_5 = _recursion_protection_body_element_5;
			}
			if(_recursion_protection_body_element_6 != null){
				this._recursion_protection_body_element_6 = _recursion_protection_body_element_6;
			}
			if(_recursion_protection_body_element_7 != null){
				this._recursion_protection_body_element_7 = _recursion_protection_body_element_7;
			}
			if(_recursion_protection_body_element_8 != null){
				this._recursion_protection_body_element_8 = _recursion_protection_body_element_8;
			}
			if(_recursion_protection_type_var_0 != null){
				this._recursion_protection_type_var_0 = _recursion_protection_type_var_0;
			}
			if(_recursion_protection_type_var_1 != null){
				this._recursion_protection_type_var_1 = _recursion_protection_type_var_1;
			}
			if(_recursion_protection_type_var_2 != null){
				this._recursion_protection_type_var_2 = _recursion_protection_type_var_2;
			}
			if(_recursion_protection_type_var_3 != null){
				this._recursion_protection_type_var_3 = _recursion_protection_type_var_3;
			}
			if(_recursion_protection_body_statement_0 != null){
				this._recursion_protection_body_statement_0 = _recursion_protection_body_statement_0;
			}
			if(_recursion_protection_body_statement_1 != null){
				this._recursion_protection_body_statement_1 = _recursion_protection_body_statement_1;
			}
			if(_recursion_protection_weak_0 != null){
				this._recursion_protection_weak_0 = _recursion_protection_weak_0;
			}
			if(class_variable_names_statement_as_braced != null){
				this.class_variable_names_statement_as_braced = class_variable_names_statement_as_braced;
			}
			if(variable_names_statement_as_braced != null){
				this.variable_names_statement_as_braced = variable_names_statement_as_braced;
			}
			if(_recursion_protection_statement_as_braced_0 != null){
				this._recursion_protection_statement_as_braced_0 = _recursion_protection_statement_as_braced_0;
			}
			if(class_names_additions_anonymous_class != null){
				this.class_names_additions_anonymous_class = class_names_additions_anonymous_class;
			}
			if(_recursion_protection_anonymous_class_0 != null){
				this._recursion_protection_anonymous_class_0 = _recursion_protection_anonymous_class_0;
			}
			if(_recursion_protection_base_element_0 != null){
				this._recursion_protection_base_element_0 = _recursion_protection_base_element_0;
			}
			if(_recursion_protection_base_element_1 != null){
				this._recursion_protection_base_element_1 = _recursion_protection_base_element_1;
			}
			if(_recursion_protection_base_element_2 != null){
				this._recursion_protection_base_element_2 = _recursion_protection_base_element_2;
			}
			if(_recursion_protection_base_element_3 != null){
				this._recursion_protection_base_element_3 = _recursion_protection_base_element_3;
			}
			if(_recursion_protection_base_element_4 != null){
				this._recursion_protection_base_element_4 = _recursion_protection_base_element_4;
			}
			if(_recursion_protection_base_element_5 != null){
				this._recursion_protection_base_element_5 = _recursion_protection_base_element_5;
			}
			if(_recursion_protection_imports_0 != null){
				this._recursion_protection_imports_0 = _recursion_protection_imports_0;
			}
			if(variable_names_additions_body_manipulate != null){
				this.variable_names_additions_body_manipulate = variable_names_additions_body_manipulate;
			}
			if(_recursion_protection_body_manipulate_0 != null){
				this._recursion_protection_body_manipulate_0 = _recursion_protection_body_manipulate_0;
			}
			if(_recursion_protection_body_manipulate_1 != null){
				this._recursion_protection_body_manipulate_1 = _recursion_protection_body_manipulate_1;
			}
			if(class_variable_names_as_statement != null){
				this.class_variable_names_as_statement = class_variable_names_as_statement;
			}
			if(variable_names_as_statement != null){
				this.variable_names_as_statement = variable_names_as_statement;
			}
			if(_recursion_protection_as_statement_0 != null){
				this._recursion_protection_as_statement_0 = _recursion_protection_as_statement_0;
			}
			if(_recursion_protection_method_declaration_0 != null){
				this._recursion_protection_method_declaration_0 = _recursion_protection_method_declaration_0;
			}
			if(_recursion_protection_inner_0 != null){
				this._recursion_protection_inner_0 = _recursion_protection_inner_0;
			}
			if(class_variable_names_statement_as_string != null){
				this.class_variable_names_statement_as_string = class_variable_names_statement_as_string;
			}
			if(variable_names_statement_as_string != null){
				this.variable_names_statement_as_string = variable_names_statement_as_string;
			}
			if(_recursion_protection_statement_as_string_0 != null){
				this._recursion_protection_statement_as_string_0 = _recursion_protection_statement_as_string_0;
			}
			if(_recursion_protection_type_var_element_0 != null){
				this._recursion_protection_type_var_element_0 = _recursion_protection_type_var_element_0;
			}
			if(_recursion_protection_type_var_element_1 != null){
				this._recursion_protection_type_var_element_1 = _recursion_protection_type_var_element_1;
			}
			if(_recursion_protection_type_var_element_2 != null){
				this._recursion_protection_type_var_element_2 = _recursion_protection_type_var_element_2;
			}
			if(_recursion_protection_type_var_element_3 != null){
				this._recursion_protection_type_var_element_3 = _recursion_protection_type_var_element_3;
			}
			if(_recursion_protection_type_var_element_4 != null){
				this._recursion_protection_type_var_element_4 = _recursion_protection_type_var_element_4;
			}
			if(_recursion_protection_variable_assignment_0 != null){
				this._recursion_protection_variable_assignment_0 = _recursion_protection_variable_assignment_0;
			}
			if(_recursion_protection_packageName_0 != null){
				this._recursion_protection_packageName_0 = _recursion_protection_packageName_0;
			}
			if(class_variable_names_comments != null){
				this.class_variable_names_comments = class_variable_names_comments;
			}
			if(variable_names_comments != null){
				this.variable_names_comments = variable_names_comments;
			}
			if(_recursion_protection_comments_0 != null){
				this._recursion_protection_comments_0 = _recursion_protection_comments_0;
			}
			if(class_variable_names_template_parameters != null){
				this.class_variable_names_template_parameters = class_variable_names_template_parameters;
			}
			if(variable_names_template_parameters != null){
				this.variable_names_template_parameters = variable_names_template_parameters;
			}
			if(_recursion_protection_template_parameters_0 != null){
				this._recursion_protection_template_parameters_0 = _recursion_protection_template_parameters_0;
			}
			if(class_names_additions_class_declaration != null){
				this.class_names_additions_class_declaration = class_names_additions_class_declaration;
			}
			if(class_variable_names_additions_class_declaration != null){
				this.class_variable_names_additions_class_declaration = class_variable_names_additions_class_declaration;
			}
			if(_recursion_protection_class_declaration_0 != null){
				this._recursion_protection_class_declaration_0 = _recursion_protection_class_declaration_0;
			}
			if(_recursion_protection_class_declaration_1 != null){
				this._recursion_protection_class_declaration_1 = _recursion_protection_class_declaration_1;
			}
			if(class_variable_names_statement_as_method != null){
				this.class_variable_names_statement_as_method = class_variable_names_statement_as_method;
			}
			if(variable_names_statement_as_method != null){
				this.variable_names_statement_as_method = variable_names_statement_as_method;
			}
			if(_recursion_protection_statement_as_method_0 != null){
				this._recursion_protection_statement_as_method_0 = _recursion_protection_statement_as_method_0;
			}
		}
		public Context() {
		}
		public int get_pass(){
			return _pass;
		}
		public void set_pass(int new_pass){
			_pass = new_pass;
		}
		public int get_position(){
			return _position;
		}
		public void set_position(int new_position){
			_position = new_position;
		}
		public int get_inputLength(){
			return _inputLength;
		}
		public void set_inputLength(int new_inputLength){
			_inputLength = new_inputLength;
		}
		public int get_state(){
			return _state;
		}
		public void set_state(int new_state){
			_state = new_state;
		}
		public int get_furthestPosition(){
			return _furthestPosition;
		}
		public void set_furthestPosition(int new_furthestPosition){
			_furthestPosition = new_furthestPosition;
		}
		public int get_lineNumber(){
			return _lineNumber;
		}
		public void set_lineNumber(int new_lineNumber){
			_lineNumber = new_lineNumber;
		}
		public String get_input(){
			return _input;
		}
		public String get_fileName(){
			return _fileName;
		}
		public char[] get_inputArray(){
			return _inputArray;
		}
		public Parser.Result get_result(){
			return _result;
		}
		public Parser.Result.Acceptor get_resultAcceptor(){
			return _result_acceptor;
		}
		public Boolean get_succeedOnEnd(){
			return _succeedOnEnd;
		}
		public String get_listNameResult(){
			return _list_name_result;
		}
		public List<Integer> get_lineNumberRanges(){
			return _lineNumberRanges;
		}
		public Token.Parsed get_root(){
			return _root;
		}
		public Token.Parsed get_token(){
			return _token;
		}
		public Parser.Result parse(String _fileName,int _pass_index){
			Stack<Integer> brace_open_9 = new Stack<Integer>();
			Stack<Integer> brace_open_8 = new Stack<Integer>();
			Stack<Integer> brace_open_7 = new Stack<Integer>();
			Stack<Integer> brace_open_6 = new Stack<Integer>();
			Stack<Integer> brace_open_5 = new Stack<Integer>();
			Stack<Integer> brace_open_4 = new Stack<Integer>();
			Stack<Integer> brace_open_3 = new Stack<Integer>();
			Stack<Integer> brace_open_2 = new Stack<Integer>();
			Stack<Integer> brace_open_1 = new Stack<Integer>();
			Stack<Integer> brace_open_0 = new Stack<Integer>();
			long startParseTime = System.currentTimeMillis();
			_pass=_pass_index;
			StringBuilder _inputBuffer = new StringBuilder();
			try {
				BufferedReader _inputReader = new BufferedReader(new FileReader(_fileName));
				int _readInput = _inputReader.read();
				boolean escaping = false;
				boolean quoting = false;
				while (_readInput>=0 ){
					_inputBuffer.append((char)_readInput);
					if (_readInput=='\n'){
						_lineNumberRanges.add(_position);
					}
					if (escaping){
						escaping=false;
					}
					else if (!escaping&&_readInput=='\\'){
						escaping=true;
					}
					else if (!quoting&&_readInput=='\"'){
						quoting=true;
						brace_open_0.push(_position);
					}
					else if (quoting&&_readInput=='\"'){
						quoting=false;
						brace_0.put(brace_open_0.pop(),_position);
					}
					else if (!quoting&&!escaping){
						if (_readInput=='\''&&_readInput_1=='\''){
							if (!brace_open_6.isEmpty()){
								brace_6.put(brace_open_6.pop(),_position-1);
							}
						}
						if (_readInput=='\''&&_readInput_1=='\''){
							brace_open_6.push(_position-1);
						}
						if (_readInput=='`'){
							if (!brace_open_8.isEmpty()){
								brace_8.put(brace_open_8.pop(),_position);
							}
						}
						if (_readInput=='#'){
							if (!brace_open_1.isEmpty()){
								brace_1.put(brace_open_1.pop(),_position);
							}
						}
						if (_readInput=='\''){
							if (!brace_open_7.isEmpty()){
								brace_7.put(brace_open_7.pop(),_position);
							}
						}
						if (_readInput==')'){
							if (!brace_open_3.isEmpty()){
								brace_3.put(brace_open_3.pop(),_position);
							}
						}
						if (_readInput=='|'){
							if (!brace_open_9.isEmpty()){
								brace_9.put(brace_open_9.pop(),_position);
							}
						}
						if (_readInput=='}'){
							if (!brace_open_2.isEmpty()){
								brace_2.put(brace_open_2.pop(),_position);
							}
						}
						if (_readInput==']'){
							if (!brace_open_5.isEmpty()){
								brace_5.put(brace_open_5.pop(),_position);
							}
						}
						if (_readInput=='>'){
							if (!brace_open_4.isEmpty()){
								brace_4.put(brace_open_4.pop(),_position);
							}
						}
						if (_readInput=='`'){
							brace_open_8.push(_position);
						}
						if (_readInput=='#'){
							brace_open_1.push(_position);
						}
						if (_readInput=='\''){
							brace_open_7.push(_position);
						}
						if (_readInput=='('){
							brace_open_3.push(_position);
						}
						if (_readInput=='{'){
							brace_open_2.push(_position);
						}
						if (_readInput=='['){
							brace_open_5.push(_position);
						}
						if (_readInput=='<'){
							brace_open_4.push(_position);
						}
						if (_readInput=='|'){
							brace_open_9.push(_position);
						}
					}
					_position=_position+1;
					_readInput_1=_readInput;
					_readInput=_inputReader.read();
				}
				_lineNumberRanges.add(_position);
				_inputReader.close();
			}
			catch (IOException e0){
				e0.printStackTrace();
			}
			_input=_inputBuffer.toString();
			_inputArray=_input.toCharArray();
			_inputLength=_inputArray.length;
			int _fileId = fileNames.size();
			fileNames.add(_fileName);
			this._fileName=_fileName;
			_furthestPosition=0;
			_result=null;
			_position=0;
			_state=SUCCESS;
			while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
			parse_base();
			if (_state==SUCCESS&&_position==_inputLength){
				if (_succeedOnEnd){
					Parser.Result.Pass pass = new Parser.Result.Pass(SUCCESS,_position,_lineNumberRanges,_input,_fileName,_root);
					pass.setup();
					_result=pass;
				}
				else {
					_result_acceptor.setFileName(_fileName);
					_result=_result_acceptor;
				}
			}
			else if (_state==SUCCESS){
				if (_result!=null){
					_result_acceptor.add(_result);
				}
				_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName," tail of file could not be parsed"));
				_result_acceptor.setFileName(_fileName);
				_result=_result_acceptor;
			}
			else if (_state==FAILED){
				_result_acceptor.add(_result);
				_result_acceptor.setFileName(_fileName);
				_result=_result_acceptor;
			}
			long parseTime = System.currentTimeMillis()-startParseTime;
			_result.setParseTime(parseTime);
			return _result;
		}
		public Map<Integer,Integer> getBrace0(){
			return brace_0;
		}
		public Parser.NameList getClassNames(){
			return class_names;
		}
		public Parser.NameList getClassNamesAdditions(){
			return class_names_additions;
		}
		public Map<Integer,Parser.NameList> getClassNamesFirstPasses(){
			return class_names_first_passes;
		}
		public Parser.NameList getClassVariableNames(){
			return class_variable_names;
		}
		public Parser.NameList getClassVariableNamesAdditions(){
			return class_variable_names_additions;
		}
		public Map<Integer,Parser.NameList> getClassVariableNamesFirstPasses(){
			return class_variable_names_first_passes;
		}
		public Parser.NameList getVariableNames(){
			return variable_names;
		}
		public Parser.NameList getVariableNamesAdditions(){
			return variable_names_additions;
		}
		public Map<Integer,Parser.NameList> getVariableNamesFirstPasses(){
			return variable_names_first_passes;
		}
		public Map<Integer,Integer> getBrace1(){
			return brace_1;
		}
		public Map<Integer,Integer> getBrace2(){
			return brace_2;
		}
		public Map<Integer,Integer> getBrace3(){
			return brace_3;
		}
		public Map<Integer,Integer> getBrace4(){
			return brace_4;
		}
		public Map<Integer,Integer> getBrace5(){
			return brace_5;
		}
		public Map<Integer,Integer> getBrace6(){
			return brace_6;
		}
		public Map<Integer,Integer> getBrace7(){
			return brace_7;
		}
		public Map<Integer,Integer> getBrace8(){
			return brace_8;
		}
		public Map<Integer,Integer> getBrace9(){
			return brace_9;
		}
		public int get_readInput1(){
			return _readInput_1;
		}
		public void set_readInput1(int new_readInput1){
			_readInput_1 = new_readInput1;
		}
		public Parser.NameList getClassVariableNamesArrayParameters(){
			return class_variable_names_array_parameters;
		}
		public Parser.NameList getVariableNamesArrayParameters(){
			return variable_names_array_parameters;
		}
		public Set<Integer> get_recursionProtectionArrayParameters0(){
			return _recursion_protection_array_parameters_0;
		}
		public void parse_array_parameters(){
			int _position_array_parameters = -1;
			Token.Parsed _token_array_parameters = null;
			int _length_array_parameters_brace = _inputLength;
			if (brace_5.containsKey(_position)){
				class_variable_names_array_parameters=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_array_parameters=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_5.get(_position);
				int _position_array_parameters_brace = _position;
				_position+=1;
				while (_position<_length_array_parameters_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_array_parameters_0.contains(_position)==false){
					_recursion_protection_array_parameters_0.add(_position);
					_position_array_parameters=_position;
					_token_array_parameters=_token;
					_token=new Tokens.Rule.ArrayParametersToken();
					int _state_17 = _state;
					parse__anonymous_9();
					if (_state_17==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(array_parameters)");
							_furthestPosition=_position;
						}
						_position=_position_array_parameters;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_array_parameters.add(_position_array_parameters,_token);
					}
					_token=_token_array_parameters;
					_recursion_protection_array_parameters_0.remove(_position_array_parameters);
				}
				if (_state==SUCCESS&&brace_5.get(_position_array_parameters_brace)==_position){
					_position+=1;
					while (_position<_length_array_parameters_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_5.get(_position_array_parameters_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_array_parameters_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_array_parameters_brace;
				class_variable_names=class_variable_names_array_parameters;
				variable_names=variable_names_array_parameters;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(array_parameters)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse_class_file_name(){
			int _position_class_file_name = -1;
			Token.Parsed _token_class_file_name = null;
			_position_class_file_name=_position;
			_token_class_file_name=_token;
			_token=new Tokens.Rule.ClassFileNameToken();
			int _position_regex_5 = _position;
			int _multiple_index_19 = 0;
			while (_position<_inputLength){
				if (_inputArray[_position]!='.'){
					++_position;
					++_multiple_index_19;
				}
				else {
					break;
				}
			}
			if (_multiple_index_19==0 ){
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_token.setValue(_input.substring(_position_regex_5,_position));
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[.]+");
					_furthestPosition=_position;
				}
				_position=_position_regex_5;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_file_name(class_file_name)");
					_furthestPosition=_position;
				}
				_position=_position_class_file_name;
			}
			else {
			}
			if (_state==SUCCESS){
				_token_class_file_name.add(_position_class_file_name,_token);
			}
			_token=_token_class_file_name;
		}
		public void parse__anonymous_88(){
			int _position__anonymous_88 = -1;
			Token.Parsed _token__anonymous_88 = null;
			_position__anonymous_88=_position;
			_token__anonymous_88=_token;
			_token=new Token.Parsed("$ANON");
			parse_name_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_88)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_88;
			}
			else {
				int _state_54 = _state;
				parse__anonymous_89();
				if (_state_54==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_88)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_88;
				}
				else {
					int _state_55 = _state;
					while (_position<_inputLength){
						parse__anonymous_90();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_55==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_88)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_88;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_88.addAll(_token);
			}
			_token=_token__anonymous_88;
		}
		public void parse__anonymous_87(){
			int _position__anonymous_87 = -1;
			Token.Parsed _token__anonymous_87 = null;
			_position__anonymous_87=_position;
			_token__anonymous_87=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_87)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_87;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_87.addAll(_token);
			}
			_token=_token__anonymous_87;
		}
		public void parse__anonymous_89(){
			int _position__anonymous_89 = -1;
			Token.Parsed _token__anonymous_89 = null;
			_position__anonymous_89=_position;
			_token__anonymous_89=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_arguments();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_89)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_89;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_89.addAll(_token);
			}
			_token=_token__anonymous_89;
		}
		public void parse__anonymous_110(){
			int _position__anonymous_110 = -1;
			Token.Parsed _token__anonymous_110 = null;
			_position__anonymous_110=_position;
			_token__anonymous_110=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_111();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_110)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_110;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_110.addAll(_token);
			}
			_token=_token__anonymous_110;
		}
		public Set<Integer> get_recursionProtectionBodyConditional0(){
			return _recursion_protection_body_conditional_0;
		}
		public Set<Integer> get_recursionProtectionBodyConditional1(){
			return _recursion_protection_body_conditional_1;
		}
		public Set<Integer> get_recursionProtectionBodyConditional2(){
			return _recursion_protection_body_conditional_2;
		}
		public Set<Integer> get_recursionProtectionBodyConditional3(){
			return _recursion_protection_body_conditional_3;
		}
		public Set<Integer> get_recursionProtectionBodyConditional4(){
			return _recursion_protection_body_conditional_4;
		}
		public void parse_body_conditional(){
			int _position_body_conditional = -1;
			Token.Parsed _token_body_conditional = null;
			if (_state==SUCCESS&&_recursion_protection_body_conditional_0.contains(_position)==false){
				_recursion_protection_body_conditional_0.add(_position);
				_position_body_conditional=_position;
				_token_body_conditional=_token;
				_token=new Tokens.Rule.BodyConditionalToken();
				int _state_36 = _state;
				parse__anonymous_54();
				if (_state_36==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else {
					parse__anonymous_55();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else {
						parse_body_statement();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else {
							parse__anonymous_56();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else {
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_body_conditional.add(_position_body_conditional,_token);
				}
				_token=_token_body_conditional;
				_recursion_protection_body_conditional_0.remove(_position_body_conditional);
				if (_state==FAILED&&_recursion_protection_body_conditional_1.contains(_position)==false){
					_recursion_protection_body_conditional_1.add(_position);
					_state=SUCCESS;
					_position_body_conditional=_position;
					_token_body_conditional=_token;
					_token=new Tokens.Rule.BodyConditionalToken();
					int _state_37 = _state;
					parse__anonymous_57();
					if (_state_37==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else {
						if (_position+4-1 >=_inputLength){
							_state=FAILED;
						}
						else {
							if (_inputArray[_position+0]!='e'){
								_state=FAILED;
							}
							if (_inputArray[_position+1]!='l'){
								_state=FAILED;
							}
							if (_inputArray[_position+2]!='s'){
								_state=FAILED;
							}
							if (_inputArray[_position+3]!='e'){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_token.add(_position,Tokens.Plain.ConditionalToken.plain_0);
							_position=_position+4;
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
						else if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"else\"");
								_furthestPosition=_position;
							}
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else {
							parse__anonymous_58();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else {
							}
						}
					}
					if (_state==SUCCESS){
						_token_body_conditional.add(_position_body_conditional,_token);
					}
					_token=_token_body_conditional;
					_recursion_protection_body_conditional_1.remove(_position_body_conditional);
					if (_state==FAILED&&_recursion_protection_body_conditional_2.contains(_position)==false){
						_recursion_protection_body_conditional_2.add(_position);
						_state=SUCCESS;
						_position_body_conditional=_position;
						_token_body_conditional=_token;
						_token=new Tokens.Rule.BodyConditionalToken();
						int _state_38 = _state;
						parse__anonymous_59();
						if (_state_38==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else {
							if (_position+3-1 >=_inputLength){
								_state=FAILED;
							}
							else {
								if (_inputArray[_position+0]!='f'){
									_state=FAILED;
								}
								if (_inputArray[_position+1]!='o'){
									_state=FAILED;
								}
								if (_inputArray[_position+2]!='r'){
									_state=FAILED;
								}
							}
							if (_state==SUCCESS){
								_token.add(_position,Tokens.Plain.ConditionalToken.plain_1);
								_position=_position+3;
								while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
									++_position;
								}
							}
							else if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"for\"");
									_furthestPosition=_position;
								}
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else {
								parse_variable_declaration();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else {
									parse_OPERATOR();
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else {
										parse_body_statement();
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
												_furthestPosition=_position;
											}
											_position=_position_body_conditional;
										}
										else {
											parse__anonymous_60();
											if (_state==FAILED){
												if (_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
													_furthestPosition=_position;
												}
												_position=_position_body_conditional;
											}
											else {
											}
										}
									}
								}
							}
						}
						if (_state==SUCCESS){
							_token_body_conditional.add(_position_body_conditional,_token);
						}
						_token=_token_body_conditional;
						_recursion_protection_body_conditional_2.remove(_position_body_conditional);
						if (_state==FAILED&&_recursion_protection_body_conditional_3.contains(_position)==false){
							_recursion_protection_body_conditional_3.add(_position);
							_state=SUCCESS;
							_position_body_conditional=_position;
							_token_body_conditional=_token;
							_token=new Tokens.Rule.BodyConditionalToken();
							int _state_39 = _state;
							parse__anonymous_61();
							if (_state_39==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else {
								if (_position+3-1 >=_inputLength){
									_state=FAILED;
								}
								else {
									if (_inputArray[_position+0]!='t'){
										_state=FAILED;
									}
									if (_inputArray[_position+1]!='r'){
										_state=FAILED;
									}
									if (_inputArray[_position+2]!='y'){
										_state=FAILED;
									}
								}
								if (_state==SUCCESS){
									_token.add(_position,Tokens.Plain.ConditionalToken.plain_2);
									_position=_position+3;
									while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
										++_position;
									}
								}
								else if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"try\"");
										_furthestPosition=_position;
									}
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else {
									parse__anonymous_62();
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else {
									}
								}
							}
							if (_state==SUCCESS){
								_token_body_conditional.add(_position_body_conditional,_token);
							}
							_token=_token_body_conditional;
							_recursion_protection_body_conditional_3.remove(_position_body_conditional);
							if (_state==FAILED&&_recursion_protection_body_conditional_4.contains(_position)==false){
								_recursion_protection_body_conditional_4.add(_position);
								_state=SUCCESS;
								_position_body_conditional=_position;
								_token_body_conditional=_token;
								_token=new Tokens.Rule.BodyConditionalToken();
								int _state_40 = _state;
								parse__anonymous_63();
								if (_state_40==SUCCESS&&_state==FAILED){
									_state=SUCCESS;
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else {
									int _state_41 = _state;
									parse__anonymous_64();
									if (_state_41==SUCCESS&&_state==FAILED){
										_state=SUCCESS;
									}
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else {
										if (_position+5-1 >=_inputLength){
											_state=FAILED;
										}
										else {
											if (_inputArray[_position+0]!='c'){
												_state=FAILED;
											}
											if (_inputArray[_position+1]!='a'){
												_state=FAILED;
											}
											if (_inputArray[_position+2]!='t'){
												_state=FAILED;
											}
											if (_inputArray[_position+3]!='c'){
												_state=FAILED;
											}
											if (_inputArray[_position+4]!='h'){
												_state=FAILED;
											}
										}
										if (_state==SUCCESS){
											_token.add(_position,Tokens.Plain.ConditionalToken.plain_3);
											_position=_position+5;
											while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
												++_position;
											}
										}
										else if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"catch\"");
												_furthestPosition=_position;
											}
										}
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
												_furthestPosition=_position;
											}
											_position=_position_body_conditional;
										}
										else {
											parse__anonymous_65();
											if (_state==FAILED){
												if (_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
													_furthestPosition=_position;
												}
												_position=_position_body_conditional;
											}
											else {
												int _state_42 = _state;
												while (_position<_inputLength){
													parse__anonymous_66();
													if (_state==FAILED){
														break;
													}
												}
												if (_state_42==SUCCESS&&_state==FAILED){
													_state=SUCCESS;
												}
												if (_state==FAILED){
													if (_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
														_furthestPosition=_position;
													}
													_position=_position_body_conditional;
												}
												else {
													parse__anonymous_69();
													if (_state==FAILED){
														if (_position>=_furthestPosition){
															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
															_furthestPosition=_position;
														}
														_position=_position_body_conditional;
													}
													else {
													}
												}
											}
										}
									}
								}
								if (_state==SUCCESS){
									_token_body_conditional.add(_position_body_conditional,_token);
								}
								_token=_token_body_conditional;
								_recursion_protection_body_conditional_4.remove(_position_body_conditional);
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_80(){
			int _position__anonymous_80 = -1;
			Token.Parsed _token__anonymous_80 = null;
			_position__anonymous_80=_position;
			_token__anonymous_80=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+3-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='n'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='e'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='w'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+3;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"new\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_80)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_80;
			}
			else {
				parse_all_type_name();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_80)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_80;
				}
				else {
					int _state_49 = _state;
					parse__anonymous_81();
					if (_state_49==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_80)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_80;
					}
					else {
						int _state_50 = _state;
						while (_position<_inputLength){
							parse__anonymous_82();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_50==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_80)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_80;
						}
						else {
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_80.addAll(_token);
			}
			_token=_token__anonymous_80;
		}
		public void parse__anonymous_104(){
			int _position__anonymous_104 = -1;
			Token.Parsed _token__anonymous_104 = null;
			_position__anonymous_104=_position;
			_token__anonymous_104=_token;
			_token=new Token.Parsed("$ANON");
			parse_NAME();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_104)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_104;
			}
			else {
				int _state_62 = _state;
				parse__anonymous_105();
				if (_state_62==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_104)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_104;
				}
				else {
					int _state_63 = _state;
					while (_position<_inputLength){
						parse__anonymous_106();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_63==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_104)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_104;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_104.addAll(_token);
			}
			_token=_token__anonymous_104;
		}
		public void parse__anonymous_105(){
			int _position__anonymous_105 = -1;
			Token.Parsed _token__anonymous_105 = null;
			_position__anonymous_105=_position;
			_token__anonymous_105=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_arguments();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_105)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_105;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_105.addAll(_token);
			}
			_token=_token__anonymous_105;
		}
		public void parse__anonymous_82(){
			int _position__anonymous_82 = -1;
			Token.Parsed _token__anonymous_82 = null;
			_position__anonymous_82=_position;
			_token__anonymous_82=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_82)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_82;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_82.addAll(_token);
			}
			_token=_token__anonymous_82;
		}
		public void parse__anonymous_106(){
			int _position__anonymous_106 = -1;
			Token.Parsed _token__anonymous_106 = null;
			_position__anonymous_106=_position;
			_token__anonymous_106=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_106)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_106;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_106.addAll(_token);
			}
			_token=_token__anonymous_106;
		}
		public Parser.NameList getClassVariableNamesQuote(){
			return class_variable_names_quote;
		}
		public Parser.NameList getVariableNamesQuote(){
			return variable_names_quote;
		}
		public Set<Integer> get_recursionProtectionQuote0(){
			return _recursion_protection_quote_0;
		}
		public void parse_quote(){
			int _position_quote = -1;
			Token.Parsed _token_quote = null;
			int _length_quote_brace = _inputLength;
			if (brace_0.containsKey(_position)){
				class_variable_names_quote=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_quote=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_0.get(_position);
				int _position_quote_brace = _position;
				_position+=1;
				while (_position<_length_quote_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_quote_0.contains(_position)==false){
					_recursion_protection_quote_0.add(_position);
					_position_quote=_position;
					_token_quote=_token;
					_token=new Tokens.Rule.QuoteToken();
					int _position_regex_4 = _position;
					int _multiple_index_10 = 0;
					int _state_10 = _state;
					while (_position<_inputLength){
						if (_position<_inputLength){
							++_position;
						}
						else {
							_state=FAILED;
						}
						if (_state==FAILED){
							break;
						}
						else {
							++_multiple_index_10;
						}
					}
					if (_state_10==SUCCESS){
						_state=SUCCESS;
					}
					if (_state==SUCCESS){
						_token.setValue(_input.substring(_position_regex_4,_position));
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".*");
							_furthestPosition=_position;
						}
						_position=_position_regex_4;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"quote(quote)");
							_furthestPosition=_position;
						}
						_position=_position_quote;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_quote.add(_position_quote,_token);
					}
					_token=_token_quote;
					_recursion_protection_quote_0.remove(_position_quote);
				}
				if (_state==SUCCESS&&brace_0.get(_position_quote_brace)==_position){
					_position+=1;
					while (_position<_length_quote_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_0.get(_position_quote_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_quote_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_quote_brace;
				class_variable_names=class_variable_names_quote;
				variable_names=variable_names_quote;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"quote(quote)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_81(){
			int _position__anonymous_81 = -1;
			Token.Parsed _token__anonymous_81 = null;
			_position__anonymous_81=_position;
			_token__anonymous_81=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_arguments();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_81)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_81;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_81.addAll(_token);
			}
			_token=_token__anonymous_81;
		}
		public void parse__anonymous_107(){
			int _position__anonymous_107 = -1;
			Token.Parsed _token__anonymous_107 = null;
			_position__anonymous_107=_position;
			_token__anonymous_107=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_107)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_107;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_107.addAll(_token);
			}
			_token=_token__anonymous_107;
		}
		public void parse__anonymous_84(){
			int _position__anonymous_84 = -1;
			Token.Parsed _token__anonymous_84 = null;
			_position__anonymous_84=_position;
			_token__anonymous_84=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \".\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_84)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_84;
			}
			else {
				parse__anonymous_85();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_84)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_84;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_84.addAll(_token);
			}
			_token=_token__anonymous_84;
		}
		public void parse__anonymous_100(){
			int _position__anonymous_100 = -1;
			Token.Parsed _token__anonymous_100 = null;
			_position__anonymous_100=_position;
			_token__anonymous_100=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_arguments();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_100)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_100;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_100.addAll(_token);
			}
			_token=_token__anonymous_100;
		}
		public void parse__anonymous_83(){
			int _position__anonymous_83 = -1;
			Token.Parsed _token__anonymous_83 = null;
			_position__anonymous_83=_position;
			_token__anonymous_83=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_84();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_83)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_83;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_83.addAll(_token);
			}
			_token=_token__anonymous_83;
		}
		public void parse__anonymous_101(){
			int _position__anonymous_101 = -1;
			Token.Parsed _token__anonymous_101 = null;
			_position__anonymous_101=_position;
			_token__anonymous_101=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_101)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_101;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_101.addAll(_token);
			}
			_token=_token__anonymous_101;
		}
		public void parse__anonymous_86(){
			int _position__anonymous_86 = -1;
			Token.Parsed _token__anonymous_86 = null;
			_position__anonymous_86=_position;
			_token__anonymous_86=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_arguments();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_86)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_86;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_86.addAll(_token);
			}
			_token=_token__anonymous_86;
		}
		public void parse__anonymous_102(){
			int _position__anonymous_102 = -1;
			Token.Parsed _token__anonymous_102 = null;
			_position__anonymous_102=_position;
			_token__anonymous_102=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_103();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_102)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_102;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_102.addAll(_token);
			}
			_token=_token__anonymous_102;
		}
		public void parse__anonymous_85(){
			int _position__anonymous_85 = -1;
			Token.Parsed _token__anonymous_85 = null;
			_position__anonymous_85=_position;
			_token__anonymous_85=_token;
			_token=new Token.Parsed("$ANON");
			parse_NAME();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_85)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_85;
			}
			else {
				int _state_51 = _state;
				parse__anonymous_86();
				if (_state_51==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_85)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_85;
				}
				else {
					int _state_52 = _state;
					while (_position<_inputLength){
						parse__anonymous_87();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_52==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_85)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_85;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_85.addAll(_token);
			}
			_token=_token__anonymous_85;
		}
		public void parse__anonymous_103(){
			int _position__anonymous_103 = -1;
			Token.Parsed _token__anonymous_103 = null;
			_position__anonymous_103=_position;
			_token__anonymous_103=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \".\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_103)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_103;
			}
			else {
				parse__anonymous_104();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_103)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_103;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_103.addAll(_token);
			}
			_token=_token__anonymous_103;
		}
		public Set<Integer> get_recursionProtectionAllTypeName0(){
			return _recursion_protection_all_type_name_0;
		}
		public Set<Integer> get_recursionProtectionAllTypeName1(){
			return _recursion_protection_all_type_name_1;
		}
		public Set<Integer> get_recursionProtectionAllTypeName2(){
			return _recursion_protection_all_type_name_2;
		}
		public Set<Integer> get_recursionProtectionAllTypeName3(){
			return _recursion_protection_all_type_name_3;
		}
		public Set<Integer> get_recursionProtectionAllTypeName4(){
			return _recursion_protection_all_type_name_4;
		}
		public Set<Integer> get_recursionProtectionAllTypeName5(){
			return _recursion_protection_all_type_name_5;
		}
		public Set<Integer> get_recursionProtectionAllTypeName6(){
			return _recursion_protection_all_type_name_6;
		}
		public Set<Integer> get_recursionProtectionAllTypeName7(){
			return _recursion_protection_all_type_name_7;
		}
		public void parse_all_type_name(){
			int _position_all_type_name = -1;
			Token.Parsed _token_all_type_name = null;
			if (_state==SUCCESS&&_recursion_protection_all_type_name_0.contains(_position)==false){
				_recursion_protection_all_type_name_0.add(_position);
				_position_all_type_name=_position;
				_token_all_type_name=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+5-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='C'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='l'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='a'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='s'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='s'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+5;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"Class\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
						_furthestPosition=_position;
					}
					_position=_position_all_type_name;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_all_type_name.addAll(_token);
				}
				_token=_token_all_type_name;
				_recursion_protection_all_type_name_0.remove(_position_all_type_name);
				if (_state==FAILED&&_recursion_protection_all_type_name_1.contains(_position)==false){
					_recursion_protection_all_type_name_1.add(_position);
					_state=SUCCESS;
					_position_all_type_name=_position;
					_token_all_type_name=_token;
					_token=new Token.Parsed("$ANON");
					if (_position+6-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!='M'){
							_state=FAILED;
						}
						if (_inputArray[_position+1]!='e'){
							_state=FAILED;
						}
						if (_inputArray[_position+2]!='t'){
							_state=FAILED;
						}
						if (_inputArray[_position+3]!='h'){
							_state=FAILED;
						}
						if (_inputArray[_position+4]!='o'){
							_state=FAILED;
						}
						if (_inputArray[_position+5]!='d'){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_position=_position+6;
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"Method\"");
							_furthestPosition=_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
							_furthestPosition=_position;
						}
						_position=_position_all_type_name;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_all_type_name.addAll(_token);
					}
					_token=_token_all_type_name;
					_recursion_protection_all_type_name_1.remove(_position_all_type_name);
					if (_state==FAILED&&_recursion_protection_all_type_name_2.contains(_position)==false){
						_recursion_protection_all_type_name_2.add(_position);
						_state=SUCCESS;
						_position_all_type_name=_position;
						_token_all_type_name=_token;
						_token=new Token.Parsed("$ANON");
						if (_position+8-1 >=_inputLength){
							_state=FAILED;
						}
						else {
							if (_inputArray[_position+0]!='V'){
								_state=FAILED;
							}
							if (_inputArray[_position+1]!='a'){
								_state=FAILED;
							}
							if (_inputArray[_position+2]!='r'){
								_state=FAILED;
							}
							if (_inputArray[_position+3]!='i'){
								_state=FAILED;
							}
							if (_inputArray[_position+4]!='a'){
								_state=FAILED;
							}
							if (_inputArray[_position+5]!='b'){
								_state=FAILED;
							}
							if (_inputArray[_position+6]!='l'){
								_state=FAILED;
							}
							if (_inputArray[_position+7]!='e'){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_position=_position+8;
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
						else if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"Variable\"");
								_furthestPosition=_position;
							}
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
								_furthestPosition=_position;
							}
							_position=_position_all_type_name;
						}
						else {
						}
						if (_state==SUCCESS){
							_token_all_type_name.addAll(_token);
						}
						_token=_token_all_type_name;
						_recursion_protection_all_type_name_2.remove(_position_all_type_name);
						if (_state==FAILED&&_recursion_protection_all_type_name_3.contains(_position)==false){
							_recursion_protection_all_type_name_3.add(_position);
							_state=SUCCESS;
							_position_all_type_name=_position;
							_token_all_type_name=_token;
							_token=new Token.Parsed("$ANON");
							if (_position+4-1 >=_inputLength){
								_state=FAILED;
							}
							else {
								if (_inputArray[_position+0]!='B'){
									_state=FAILED;
								}
								if (_inputArray[_position+1]!='o'){
									_state=FAILED;
								}
								if (_inputArray[_position+2]!='d'){
									_state=FAILED;
								}
								if (_inputArray[_position+3]!='y'){
									_state=FAILED;
								}
							}
							if (_state==SUCCESS){
								_position=_position+4;
								while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
									++_position;
								}
							}
							else if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"Body\"");
									_furthestPosition=_position;
								}
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
									_furthestPosition=_position;
								}
								_position=_position_all_type_name;
							}
							else {
							}
							if (_state==SUCCESS){
								_token_all_type_name.addAll(_token);
							}
							_token=_token_all_type_name;
							_recursion_protection_all_type_name_3.remove(_position_all_type_name);
							if (_state==FAILED&&_recursion_protection_all_type_name_4.contains(_position)==false){
								_recursion_protection_all_type_name_4.add(_position);
								_state=SUCCESS;
								_position_all_type_name=_position;
								_token_all_type_name=_token;
								_token=new Token.Parsed("$ANON");
								if (_position+9-1 >=_inputLength){
									_state=FAILED;
								}
								else {
									if (_inputArray[_position+0]!='S'){
										_state=FAILED;
									}
									if (_inputArray[_position+1]!='t'){
										_state=FAILED;
									}
									if (_inputArray[_position+2]!='a'){
										_state=FAILED;
									}
									if (_inputArray[_position+3]!='t'){
										_state=FAILED;
									}
									if (_inputArray[_position+4]!='e'){
										_state=FAILED;
									}
									if (_inputArray[_position+5]!='m'){
										_state=FAILED;
									}
									if (_inputArray[_position+6]!='e'){
										_state=FAILED;
									}
									if (_inputArray[_position+7]!='n'){
										_state=FAILED;
									}
									if (_inputArray[_position+8]!='t'){
										_state=FAILED;
									}
								}
								if (_state==SUCCESS){
									_position=_position+9;
									while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
										++_position;
									}
								}
								else if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"Statement\"");
										_furthestPosition=_position;
									}
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
										_furthestPosition=_position;
									}
									_position=_position_all_type_name;
								}
								else {
								}
								if (_state==SUCCESS){
									_token_all_type_name.addAll(_token);
								}
								_token=_token_all_type_name;
								_recursion_protection_all_type_name_4.remove(_position_all_type_name);
								if (_state==FAILED&&_recursion_protection_all_type_name_5.contains(_position)==false){
									_recursion_protection_all_type_name_5.add(_position);
									_state=SUCCESS;
									_position_all_type_name=_position;
									_token_all_type_name=_token;
									_token=new Token.Parsed("$ANON");
									if (_position+10-1 >=_inputLength){
										_state=FAILED;
									}
									else {
										if (_inputArray[_position+0]!='P'){
											_state=FAILED;
										}
										if (_inputArray[_position+1]!='a'){
											_state=FAILED;
										}
										if (_inputArray[_position+2]!='r'){
											_state=FAILED;
										}
										if (_inputArray[_position+3]!='a'){
											_state=FAILED;
										}
										if (_inputArray[_position+4]!='m'){
											_state=FAILED;
										}
										if (_inputArray[_position+5]!='e'){
											_state=FAILED;
										}
										if (_inputArray[_position+6]!='t'){
											_state=FAILED;
										}
										if (_inputArray[_position+7]!='e'){
											_state=FAILED;
										}
										if (_inputArray[_position+8]!='r'){
											_state=FAILED;
										}
										if (_inputArray[_position+9]!='s'){
											_state=FAILED;
										}
									}
									if (_state==SUCCESS){
										_position=_position+10;
										while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
											++_position;
										}
									}
									else if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"Parameters\"");
											_furthestPosition=_position;
										}
									}
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
											_furthestPosition=_position;
										}
										_position=_position_all_type_name;
									}
									else {
									}
									if (_state==SUCCESS){
										_token_all_type_name.addAll(_token);
									}
									_token=_token_all_type_name;
									_recursion_protection_all_type_name_5.remove(_position_all_type_name);
									if (_state==FAILED&&_recursion_protection_all_type_name_6.contains(_position)==false){
										_recursion_protection_all_type_name_6.add(_position);
										_state=SUCCESS;
										_position_all_type_name=_position;
										_token_all_type_name=_token;
										_token=new Token.Parsed("$ANON");
										if (_position+7-1 >=_inputLength){
											_state=FAILED;
										}
										else {
											if (_inputArray[_position+0]!='C'){
												_state=FAILED;
											}
											if (_inputArray[_position+1]!='o'){
												_state=FAILED;
											}
											if (_inputArray[_position+2]!='n'){
												_state=FAILED;
											}
											if (_inputArray[_position+3]!='t'){
												_state=FAILED;
											}
											if (_inputArray[_position+4]!='e'){
												_state=FAILED;
											}
											if (_inputArray[_position+5]!='x'){
												_state=FAILED;
											}
											if (_inputArray[_position+6]!='t'){
												_state=FAILED;
											}
										}
										if (_state==SUCCESS){
											_position=_position+7;
											while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
												++_position;
											}
										}
										else if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"Context\"");
												_furthestPosition=_position;
											}
										}
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
												_furthestPosition=_position;
											}
											_position=_position_all_type_name;
										}
										else {
										}
										if (_state==SUCCESS){
											_token_all_type_name.addAll(_token);
										}
										_token=_token_all_type_name;
										_recursion_protection_all_type_name_6.remove(_position_all_type_name);
										if (_state==FAILED&&_recursion_protection_all_type_name_7.contains(_position)==false){
											_recursion_protection_all_type_name_7.add(_position);
											_state=SUCCESS;
											_position_all_type_name=_position;
											_token_all_type_name=_token;
											_token=new Token.Parsed("$ANON");
											parse_type_var();
											if (_state==FAILED){
												if (_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
													_furthestPosition=_position;
												}
												_position=_position_all_type_name;
											}
											else {
												int _state_81 = _state;
												while (_position<_inputLength){
													parse__anonymous_134();
													if (_state==FAILED){
														break;
													}
												}
												if (_state_81==SUCCESS&&_state==FAILED){
													_state=SUCCESS;
												}
												if (_state==FAILED){
													if (_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
														_furthestPosition=_position;
													}
													_position=_position_all_type_name;
												}
												else {
												}
											}
											if (_state==SUCCESS){
												_token_all_type_name.addAll(_token);
											}
											_token=_token_all_type_name;
											_recursion_protection_all_type_name_7.remove(_position_all_type_name);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_108(){
			int _position__anonymous_108 = -1;
			Token.Parsed _token__anonymous_108 = null;
			_position__anonymous_108=_position;
			_token__anonymous_108=_token;
			_token=new Token.Parsed("$ANON");
			parse_class_declaration();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_108)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_108;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_108.addAll(_token);
			}
			_token=_token__anonymous_108;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_108=_position;
				_token__anonymous_108=_token;
				_token=new Token.Parsed("$ANON");
				parse_method_declaration();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_108)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_108;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_108.addAll(_token);
				}
				_token=_token__anonymous_108;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_108=_position;
					_token__anonymous_108=_token;
					_token=new Token.Parsed("$ANON");
					parse_variable_declaration();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_108)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_108;
					}
					else {
						if (_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else {
							if (_inputArray[_position+0]!=';'){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_position=_position+1;
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
						else if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
								_furthestPosition=_position;
							}
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_108)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_108;
						}
						else {
						}
					}
					if (_state==SUCCESS){
						_token__anonymous_108.addAll(_token);
					}
					_token=_token__anonymous_108;
					if (_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_108=_position;
						_token__anonymous_108=_token;
						_token=new Token.Parsed("$ANON");
						parse_method_body();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_108)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_108;
						}
						else {
						}
						if (_state==SUCCESS){
							_token__anonymous_108.addAll(_token);
						}
						_token=_token__anonymous_108;
						if (_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_108=_position;
							_token__anonymous_108=_token;
							_token=new Token.Parsed("$ANON");
							parse_as_statement();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_108)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_108;
							}
							else {
							}
							if (_state==SUCCESS){
								_token__anonymous_108.addAll(_token);
							}
							_token=_token__anonymous_108;
						}
					}
				}
			}
		}
		public void parse__anonymous_109(){
			int _position__anonymous_109 = -1;
			Token.Parsed _token__anonymous_109 = null;
			_position__anonymous_109=_position;
			_token__anonymous_109=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_109)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_109;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_109.addAll(_token);
			}
			_token=_token__anonymous_109;
		}
		public void parse__anonymous_77(){
			int _position__anonymous_77 = -1;
			Token.Parsed _token__anonymous_77 = null;
			_position__anonymous_77=_position;
			_token__anonymous_77=_token;
			_token=new Token.Parsed("$ANON");
			int _state_46 = _state;
			parse__anonymous_78();
			if (_state_46==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_77)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_77;
			}
			else {
				parse_OPERATOR();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_77)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_77;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_77.addAll(_token);
			}
			_token=_token__anonymous_77;
		}
		public void parse__anonymous_76(){
			int _position__anonymous_76 = -1;
			Token.Parsed _token__anonymous_76 = null;
			_position__anonymous_76=_position;
			_token__anonymous_76=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_77();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_76)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_76;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_76.addAll(_token);
			}
			_token=_token__anonymous_76;
		}
		public void parse__anonymous_79(){
			int _position__anonymous_79 = -1;
			Token.Parsed _token__anonymous_79 = null;
			_position__anonymous_79=_position;
			_token__anonymous_79=_token;
			_token=new Token.Parsed("$ANON");
			parse_body_statement();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_79)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_79;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_79.addAll(_token);
			}
			_token=_token__anonymous_79;
		}
		public void parse__anonymous_78(){
			int _position__anonymous_78 = -1;
			Token.Parsed _token__anonymous_78 = null;
			_position__anonymous_78=_position;
			_token__anonymous_78=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"\\\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_78)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_78;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_78.addAll(_token);
			}
			_token=_token__anonymous_78;
		}
		public Parser.NameList getClassVariableNamesStatementAsQuote(){
			return class_variable_names_statement_as_quote;
		}
		public Parser.NameList getVariableNamesStatementAsQuote(){
			return variable_names_statement_as_quote;
		}
		public Set<Integer> get_recursionProtectionStatementAsQuote0(){
			return _recursion_protection_statement_as_quote_0;
		}
		public void parse_statement_as_quote(){
			int _position_statement_as_quote = -1;
			Token.Parsed _token_statement_as_quote = null;
			int _length_statement_as_quote_brace = _inputLength;
			if (brace_6.containsKey(_position)){
				class_variable_names_statement_as_quote=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_statement_as_quote=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_6.get(_position);
				int _position_statement_as_quote_brace = _position;
				_position+=2;
				while (_position<_length_statement_as_quote_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_statement_as_quote_0.contains(_position)==false){
					_recursion_protection_statement_as_quote_0.add(_position);
					_position_statement_as_quote=_position;
					_token_statement_as_quote=_token;
					_token=new Tokens.Rule.StatementAsQuoteToken();
					parse_body_statement();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_quote(statement_as_quote)");
							_furthestPosition=_position;
						}
						_position=_position_statement_as_quote;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_statement_as_quote.add(_position_statement_as_quote,_token);
					}
					_token=_token_statement_as_quote;
					_recursion_protection_statement_as_quote_0.remove(_position_statement_as_quote);
				}
				if (_state==SUCCESS&&brace_6.get(_position_statement_as_quote_brace)==_position){
					_position+=2;
					while (_position<_length_statement_as_quote_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_6.get(_position_statement_as_quote_brace)+2;
					_succeedOnEnd=false;
					while (_position<_length_statement_as_quote_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_statement_as_quote_brace;
				class_variable_names=class_variable_names_statement_as_quote;
				variable_names=variable_names_statement_as_quote;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_quote(statement_as_quote)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_71(){
			int _position__anonymous_71 = -1;
			Token.Parsed _token__anonymous_71 = null;
			_position__anonymous_71=_position;
			_token__anonymous_71=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_72();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_71)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_71;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_71.addAll(_token);
			}
			_token=_token__anonymous_71;
		}
		public void parse__anonymous_70(){
			int _position__anonymous_70 = -1;
			Token.Parsed _token__anonymous_70 = null;
			_position__anonymous_70=_position;
			_token__anonymous_70=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_70)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_70;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_70.addAll(_token);
			}
			_token=_token__anonymous_70;
		}
		public void parse__anonymous_73(){
			int _position__anonymous_73 = -1;
			Token.Parsed _token__anonymous_73 = null;
			_position__anonymous_73=_position;
			_token__anonymous_73=_token;
			_token=new Token.Parsed("$ANON");
			int _state_44 = _state;
			parse__anonymous_74();
			if (_state_44==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_73)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_73;
			}
			else {
				parse_OPERATOR();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_73)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_73;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_73.addAll(_token);
			}
			_token=_token__anonymous_73;
		}
		public void parse__anonymous_72(){
			int _position__anonymous_72 = -1;
			Token.Parsed _token__anonymous_72 = null;
			_position__anonymous_72=_position;
			_token__anonymous_72=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_73();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_72)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_72;
			}
			else {
				parse_body_call();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_72)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_72;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_72.addAll(_token);
			}
			_token=_token__anonymous_72;
		}
		public void parse__anonymous_75(){
			int _position__anonymous_75 = -1;
			Token.Parsed _token__anonymous_75 = null;
			_position__anonymous_75=_position;
			_token__anonymous_75=_token;
			_token=new Token.Parsed("$ANON");
			parse_statement_as_braced();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_75)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_75;
			}
			else {
				int _state_47 = _state;
				parse__anonymous_76();
				if (_state_47==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_75)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_75;
				}
				else {
					int _state_48 = _state;
					parse__anonymous_79();
					if (_state_48==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_75)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_75;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_75.addAll(_token);
			}
			_token=_token__anonymous_75;
		}
		public void parse__anonymous_74(){
			int _position__anonymous_74 = -1;
			Token.Parsed _token__anonymous_74 = null;
			_position__anonymous_74=_position;
			_token__anonymous_74=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"\\\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_74)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_74;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_74.addAll(_token);
			}
			_token=_token__anonymous_74;
		}
		public Set<Integer> get_recursionProtectionNameVarElement0(){
			return _recursion_protection_name_var_element_0;
		}
		public Set<Integer> get_recursionProtectionNameVarElement1(){
			return _recursion_protection_name_var_element_1;
		}
		public Set<Integer> get_recursionProtectionNameVarElement2(){
			return _recursion_protection_name_var_element_2;
		}
		public Set<Integer> get_recursionProtectionNameVarElement3(){
			return _recursion_protection_name_var_element_3;
		}
		public Set<Integer> get_recursionProtectionNameVarElement4(){
			return _recursion_protection_name_var_element_4;
		}
		public Set<Integer> get_recursionProtectionNameVarElement5(){
			return _recursion_protection_name_var_element_5;
		}
		public Set<Integer> get_recursionProtectionNameVarElement6(){
			return _recursion_protection_name_var_element_6;
		}
		public void parse_name_var_element(){
			int _position_name_var_element = -1;
			Token.Parsed _token_name_var_element = null;
			if (_state==SUCCESS&&_recursion_protection_name_var_element_0.contains(_position)==false){
				_recursion_protection_name_var_element_0.add(_position);
				_position_name_var_element=_position;
				_token_name_var_element=_token;
				_token=new Token.Parsed("$ANON");
				parse__anonymous_145();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
						_furthestPosition=_position;
					}
					_position=_position_name_var_element;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_name_var_element.addAll(_token);
				}
				_token=_token_name_var_element;
				_recursion_protection_name_var_element_0.remove(_position_name_var_element);
				if (_state==FAILED&&_recursion_protection_name_var_element_1.contains(_position)==false){
					_recursion_protection_name_var_element_1.add(_position);
					_state=SUCCESS;
					_position_name_var_element=_position;
					_token_name_var_element=_token;
					_token=new Token.Parsed("$ANON");
					parse_statement_as_method();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
							_furthestPosition=_position;
						}
						_position=_position_name_var_element;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_name_var_element.addAll(_token);
					}
					_token=_token_name_var_element;
					_recursion_protection_name_var_element_1.remove(_position_name_var_element);
					if (_state==FAILED&&_recursion_protection_name_var_element_2.contains(_position)==false){
						_recursion_protection_name_var_element_2.add(_position);
						_state=SUCCESS;
						_position_name_var_element=_position;
						_token_name_var_element=_token;
						_token=new Token.Parsed("$ANON");
						parse_statement_as_quote();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
								_furthestPosition=_position;
							}
							_position=_position_name_var_element;
						}
						else {
						}
						if (_state==SUCCESS){
							_token_name_var_element.addAll(_token);
						}
						_token=_token_name_var_element;
						_recursion_protection_name_var_element_2.remove(_position_name_var_element);
						if (_state==FAILED&&_recursion_protection_name_var_element_3.contains(_position)==false){
							_recursion_protection_name_var_element_3.add(_position);
							_state=SUCCESS;
							_position_name_var_element=_position;
							_token_name_var_element=_token;
							_token=new Token.Parsed("$ANON");
							parse_statement_as_string();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
									_furthestPosition=_position;
								}
								_position=_position_name_var_element;
							}
							else {
							}
							if (_state==SUCCESS){
								_token_name_var_element.addAll(_token);
							}
							_token=_token_name_var_element;
							_recursion_protection_name_var_element_3.remove(_position_name_var_element);
							if (_state==FAILED&&_recursion_protection_name_var_element_4.contains(_position)==false){
								_recursion_protection_name_var_element_4.add(_position);
								_state=SUCCESS;
								_position_name_var_element=_position;
								_token_name_var_element=_token;
								_token=new Token.Parsed("$ANON");
								parse__anonymous_146();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
										_furthestPosition=_position;
									}
									_position=_position_name_var_element;
								}
								else {
								}
								if (_state==SUCCESS){
									_token_name_var_element.addAll(_token);
								}
								_token=_token_name_var_element;
								_recursion_protection_name_var_element_4.remove(_position_name_var_element);
								if (_state==FAILED&&_recursion_protection_name_var_element_5.contains(_position)==false){
									_recursion_protection_name_var_element_5.add(_position);
									_state=SUCCESS;
									_position_name_var_element=_position;
									_token_name_var_element=_token;
									_token=new Token.Parsed("$ANON");
									parse__anonymous_149();
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
											_furthestPosition=_position;
										}
										_position=_position_name_var_element;
									}
									else {
									}
									if (_state==SUCCESS){
										_token_name_var_element.addAll(_token);
									}
									_token=_token_name_var_element;
									_recursion_protection_name_var_element_5.remove(_position_name_var_element);
									if (_state==FAILED&&_recursion_protection_name_var_element_6.contains(_position)==false){
										_recursion_protection_name_var_element_6.add(_position);
										_state=SUCCESS;
										_position_name_var_element=_position;
										_token_name_var_element=_token;
										_token=new Token.Parsed("$ANON");
										parse__anonymous_152();
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
												_furthestPosition=_position;
											}
											_position=_position_name_var_element;
										}
										else {
										}
										if (_state==SUCCESS){
											_token_name_var_element.addAll(_token);
										}
										_token=_token_name_var_element;
										_recursion_protection_name_var_element_6.remove(_position_name_var_element);
									}
								}
							}
						}
					}
				}
			}
		}
		public Set<Integer> get_recursionProtectionClassElement0(){
			return _recursion_protection_class_element_0;
		}
		public Set<Integer> get_recursionProtectionClassElement1(){
			return _recursion_protection_class_element_1;
		}
		public Set<Integer> get_recursionProtectionClassElement2(){
			return _recursion_protection_class_element_2;
		}
		public Set<Integer> get_recursionProtectionClassElement3(){
			return _recursion_protection_class_element_3;
		}
		public void parse_class_element(){
			int _position_class_element = -1;
			Token.Parsed _token_class_element = null;
			if (_state==SUCCESS&&_recursion_protection_class_element_0.contains(_position)==false){
				_recursion_protection_class_element_0.add(_position);
				_position_class_element=_position;
				_token_class_element=_token;
				_token=new Token.Parsed("$ANON");
				parse_comments();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
						_furthestPosition=_position;
					}
					_position=_position_class_element;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_class_element.addAll(_token);
				}
				_token=_token_class_element;
				_recursion_protection_class_element_0.remove(_position_class_element);
				if (_state==FAILED&&_recursion_protection_class_element_1.contains(_position)==false){
					_recursion_protection_class_element_1.add(_position);
					_state=SUCCESS;
					_position_class_element=_position;
					_token_class_element=_token;
					_token=new Token.Parsed("$ANON");
					parse_class_declaration();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
							_furthestPosition=_position;
						}
						_position=_position_class_element;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_class_element.addAll(_token);
					}
					_token=_token_class_element;
					_recursion_protection_class_element_1.remove(_position_class_element);
					if (_state==FAILED&&_recursion_protection_class_element_2.contains(_position)==false){
						_recursion_protection_class_element_2.add(_position);
						_state=SUCCESS;
						_position_class_element=_position;
						_token_class_element=_token;
						_token=new Token.Parsed("$ANON");
						parse_method_declaration();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
								_furthestPosition=_position;
							}
							_position=_position_class_element;
						}
						else {
						}
						if (_state==SUCCESS){
							_token_class_element.addAll(_token);
						}
						_token=_token_class_element;
						_recursion_protection_class_element_2.remove(_position_class_element);
						if (_state==FAILED&&_recursion_protection_class_element_3.contains(_position)==false){
							_recursion_protection_class_element_3.add(_position);
							_state=SUCCESS;
							_position_class_element=_position;
							_token_class_element=_token;
							_token=new Token.Parsed("$ANON");
							parse_variable_declaration();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
									_furthestPosition=_position;
								}
								_position=_position_class_element;
							}
							else {
								if (_position+1-1 >=_inputLength){
									_state=FAILED;
								}
								else {
									if (_inputArray[_position+0]!=';'){
										_state=FAILED;
									}
								}
								if (_state==SUCCESS){
									_position=_position+1;
									while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
										++_position;
									}
								}
								else if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
										_furthestPosition=_position;
									}
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
										_furthestPosition=_position;
									}
									_position=_position_class_element;
								}
								else {
								}
							}
							if (_state==SUCCESS){
								_token_class_element.addAll(_token);
							}
							_token=_token_class_element;
							_recursion_protection_class_element_3.remove(_position_class_element);
						}
					}
				}
			}
		}
		public void parse__anonymous_66(){
			int _position__anonymous_66 = -1;
			Token.Parsed _token__anonymous_66 = null;
			_position__anonymous_66=_position;
			_token__anonymous_66=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_67();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_66)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_66;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_66.addAll(_token);
			}
			_token=_token__anonymous_66;
		}
		public void parse__anonymous_65(){
			int _position__anonymous_65 = -1;
			Token.Parsed _token__anonymous_65 = null;
			_position__anonymous_65=_position;
			_token__anonymous_65=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"*\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_65)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_65;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_65.addAll(_token);
			}
			_token=_token__anonymous_65;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_65=_position;
				_token__anonymous_65=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_65)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_65;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_65.addAll(_token);
				}
				_token=_token__anonymous_65;
			}
		}
		public void parse__anonymous_130(){
			int _position__anonymous_130 = -1;
			Token.Parsed _token__anonymous_130 = null;
			_position__anonymous_130=_position;
			_token__anonymous_130=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+3-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='.'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+3;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"...\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_130)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_130;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_130.addAll(_token);
			}
			_token=_token__anonymous_130;
		}
		public void parse__anonymous_68(){
			int _position__anonymous_68 = -1;
			Token.Parsed _token__anonymous_68 = null;
			_position__anonymous_68=_position;
			_token__anonymous_68=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"*\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_68)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_68;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_68.addAll(_token);
			}
			_token=_token__anonymous_68;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_68=_position;
				_token__anonymous_68=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_68)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_68;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_68.addAll(_token);
				}
				_token=_token__anonymous_68;
			}
		}
		public void parse__anonymous_131(){
			int _position__anonymous_131 = -1;
			Token.Parsed _token__anonymous_131 = null;
			_position__anonymous_131=_position;
			_token__anonymous_131=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='['){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!=']'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.ARRAYTYPEToken.plain_7);
				_position=_position+2;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"[]\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_131)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_131;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_131.addAll(_token);
			}
			_token=_token__anonymous_131;
		}
		public void parse__anonymous_67(){
			int _position__anonymous_67 = -1;
			Token.Parsed _token__anonymous_67 = null;
			_position__anonymous_67=_position;
			_token__anonymous_67=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='|'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"|\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_67)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_67;
			}
			else {
				parse__anonymous_68();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_67)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_67;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_67.addAll(_token);
			}
			_token=_token__anonymous_67;
		}
		public Parser.NameList getVariableNamesAdditionsAnonymous132(){
			return variable_names_additions__anonymous_132;
		}
		public void parse__anonymous_132(){
			int _position__anonymous_132 = -1;
			Token.Parsed _token__anonymous_132 = null;
			variable_names_additions__anonymous_132=variable_names_additions;
			variable_names_additions=new Parser.NameList.Child(variable_names);
			_position__anonymous_132=_position;
			_token__anonymous_132=_token;
			_token=new Token.Parsed("$ANON");
			parse_name_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_132)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_132;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_132.addAll(_token);
			}
			_token=_token__anonymous_132;
			if (_state==FAILED){
				variable_names.removeAll(variable_names_additions);
				variable_names_additions.clear();
				_state=SUCCESS;
				_position__anonymous_132=_position;
				_token__anonymous_132=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if (_state==SUCCESS){
					String _value = _token.getPollLast().getValue();
					if (_value!=null&&variable_names.add(_value)){
						variable_names_additions.add(_value);
					}
					_token.add(_position,new Tokens.Name.VariableNameToken(_value));
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_132)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_132;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_132.addAll(_token);
				}
				_token=_token__anonymous_132;
				if (_state==FAILED){
					if (variable_names_additions!=null){
						variable_names.removeAll(variable_names_additions);
						variable_names_additions.clear();
					}
				}
				else if (_state==SUCCESS){
					if (variable_names_additions!=null){
						variable_names.addAll(variable_names_additions);
						variable_names_additions.clear();
					}
				}
				variable_names_additions=variable_names_additions__anonymous_132;
			}
		}
		public void parse__anonymous_69(){
			int _position__anonymous_69 = -1;
			Token.Parsed _token__anonymous_69 = null;
			_position__anonymous_69=_position;
			_token__anonymous_69=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_body();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_69)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_69;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_69.addAll(_token);
			}
			_token=_token__anonymous_69;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_69=_position;
				_token__anonymous_69=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_69)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_69;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_69.addAll(_token);
				}
				_token=_token__anonymous_69;
			}
		}
		public Set<Integer> get_recursionProtectionClassNameDefinition0(){
			return _recursion_protection_class_name_definition_0;
		}
		public void parse_class_name_definition(){
			int _position_class_name_definition = -1;
			Token.Parsed _token_class_name_definition = null;
			if (_state==SUCCESS&&_recursion_protection_class_name_definition_0.contains(_position)==false){
				_recursion_protection_class_name_definition_0.add(_position);
				_position_class_name_definition=_position;
				_token_class_name_definition=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+5-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='C'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='l'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='a'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='s'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='s'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_token.add(_position,Tokens.Plain.TypeNameToken.plain_8);
					_position=_position+5;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"Class\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(class_name_definition)");
						_furthestPosition=_position;
					}
					_position=_position_class_name_definition;
				}
				else {
					parse__anonymous_133();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(class_name_definition)");
							_furthestPosition=_position;
						}
						_position=_position_class_name_definition;
					}
					else {
					}
				}
				if (_state==SUCCESS){
					_token_class_name_definition.addAll(_token);
				}
				_token=_token_class_name_definition;
				_recursion_protection_class_name_definition_0.remove(_position_class_name_definition);
			}
		}
		public void parse__anonymous_126(){
			int _position__anonymous_126 = -1;
			Token.Parsed _token__anonymous_126 = null;
			_position__anonymous_126=_position;
			_token__anonymous_126=_token;
			_token=new Token.Parsed("$ANON");
			parse_class_name_definition();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_126)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_126;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_126.addAll(_token);
			}
			_token=_token__anonymous_126;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_126=_position;
				_token__anonymous_126=_token;
				_token=new Token.Parsed("$ANON");
				parse_variable_name_definition();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_126)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_126;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_126.addAll(_token);
				}
				_token=_token__anonymous_126;
			}
		}
		public void parse__anonymous_127(){
			int _position__anonymous_127 = -1;
			Token.Parsed _token__anonymous_127 = null;
			_position__anonymous_127=_position;
			_token__anonymous_127=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_128();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_127)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_127;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_127.addAll(_token);
			}
			_token=_token__anonymous_127;
		}
		public void parse__anonymous_60(){
			int _position__anonymous_60 = -1;
			Token.Parsed _token__anonymous_60 = null;
			_position__anonymous_60=_position;
			_token__anonymous_60=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_body();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_60)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_60;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_60.addAll(_token);
			}
			_token=_token__anonymous_60;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_60=_position;
				_token__anonymous_60=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_60)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_60;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_60.addAll(_token);
				}
				_token=_token__anonymous_60;
			}
		}
		public void parse__anonymous_128(){
			int _position__anonymous_128 = -1;
			Token.Parsed _token__anonymous_128 = null;
			_position__anonymous_128=_position;
			_token__anonymous_128=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='='){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"=\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_128)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_128;
			}
			else {
				parse_method_argument();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_128)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_128;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_128.addAll(_token);
			}
			_token=_token__anonymous_128;
		}
		public void parse__anonymous_129(){
			int _position__anonymous_129 = -1;
			Token.Parsed _token__anonymous_129 = null;
			_position__anonymous_129=_position;
			_token__anonymous_129=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(_anonymous_129)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_129;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_129.addAll(_token);
			}
			_token=_token__anonymous_129;
		}
		public void parse__anonymous_62(){
			int _position__anonymous_62 = -1;
			Token.Parsed _token__anonymous_62 = null;
			_position__anonymous_62=_position;
			_token__anonymous_62=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_body();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_62)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_62;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_62.addAll(_token);
			}
			_token=_token__anonymous_62;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_62=_position;
				_token__anonymous_62=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_62)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_62;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_62.addAll(_token);
				}
				_token=_token__anonymous_62;
			}
		}
		public void parse__anonymous_122(){
			int _position__anonymous_122 = -1;
			Token.Parsed _token__anonymous_122 = null;
			_position__anonymous_122=_position;
			_token__anonymous_122=_token;
			_token=new Token.Parsed("$ANON");
			parse_weak();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_122)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_122;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_122.addAll(_token);
			}
			_token=_token__anonymous_122;
		}
		public void parse__anonymous_61(){
			int _position__anonymous_61 = -1;
			Token.Parsed _token__anonymous_61 = null;
			_position__anonymous_61=_position;
			_token__anonymous_61=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_61)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_61;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_61.addAll(_token);
			}
			_token=_token__anonymous_61;
		}
		public void parse__anonymous_123(){
			int _position__anonymous_123 = -1;
			Token.Parsed _token__anonymous_123 = null;
			_position__anonymous_123=_position;
			_token__anonymous_123=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_124();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_123)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_123;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_123.addAll(_token);
			}
			_token=_token__anonymous_123;
		}
		public void parse__anonymous_64(){
			int _position__anonymous_64 = -1;
			Token.Parsed _token__anonymous_64 = null;
			_position__anonymous_64=_position;
			_token__anonymous_64=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+5-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='p'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='r'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='i'){
					_state=FAILED;
				}
				if (_inputArray[_position+3]!='n'){
					_state=FAILED;
				}
				if (_inputArray[_position+4]!='t'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+5;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"print\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_64)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_64;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_64.addAll(_token);
			}
			_token=_token__anonymous_64;
		}
		public void parse__anonymous_124(){
			int _position__anonymous_124 = -1;
			Token.Parsed _token__anonymous_124 = null;
			_position__anonymous_124=_position;
			_token__anonymous_124=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='@'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"@\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_124)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_124;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_124.addAll(_token);
			}
			_token=_token__anonymous_124;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_124=_position;
				_token__anonymous_124=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='s'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='t'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='a'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='t'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='i'){
						_state=FAILED;
					}
					if (_inputArray[_position+5]!='c'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+6;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"static\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_124)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_124;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_124.addAll(_token);
				}
				_token=_token__anonymous_124;
			}
		}
		public void parse__anonymous_63(){
			int _position__anonymous_63 = -1;
			Token.Parsed _token__anonymous_63 = null;
			_position__anonymous_63=_position;
			_token__anonymous_63=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_63)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_63;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_63.addAll(_token);
			}
			_token=_token__anonymous_63;
		}
		public void parse__anonymous_125(){
			int _position__anonymous_125 = -1;
			Token.Parsed _token__anonymous_125 = null;
			_position__anonymous_125=_position;
			_token__anonymous_125=_token;
			_token=new Token.Parsed("$ANON");
			parse_weak();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_125)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_125;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_125.addAll(_token);
			}
			_token=_token__anonymous_125;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration0(){
			return _recursion_protection_variable_declaration_0;
		}
		public void parse_variable_declaration(){
			int _position_variable_declaration = -1;
			Token.Parsed _token_variable_declaration = null;
			if (_state==SUCCESS&&_recursion_protection_variable_declaration_0.contains(_position)==false){
				_recursion_protection_variable_declaration_0.add(_position);
				_position_variable_declaration=_position;
				_token_variable_declaration=_token;
				_token=new Tokens.Rule.VariableDeclarationToken();
				int _state_73 = _state;
				parse__anonymous_121();
				if (_state_73==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_variable_declaration;
				}
				else {
					int _state_74 = _state;
					parse__anonymous_122();
					if (_state_74==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_variable_declaration;
					}
					else {
						int _state_75 = _state;
						parse__anonymous_123();
						if (_state_75==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_variable_declaration;
						}
						else {
							int _state_76 = _state;
							parse__anonymous_125();
							if (_state_76==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_variable_declaration;
							}
							else {
								parse__anonymous_126();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_variable_declaration;
								}
								else {
									int _state_77 = _state;
									parse__anonymous_127();
									if (_state_77==SUCCESS&&_state==FAILED){
										_state=SUCCESS;
									}
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_variable_declaration;
									}
									else {
									}
								}
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_variable_declaration.add(_position_variable_declaration,_token);
				}
				_token=_token_variable_declaration;
				_recursion_protection_variable_declaration_0.remove(_position_variable_declaration);
			}
		}
		public Set<Integer> get_recursionProtectionVariableNameDefinition0(){
			return _recursion_protection_variable_name_definition_0;
		}
		public void parse_variable_name_definition(){
			int _position_variable_name_definition = -1;
			Token.Parsed _token_variable_name_definition = null;
			if (_state==SUCCESS&&_recursion_protection_variable_name_definition_0.contains(_position)==false){
				_recursion_protection_variable_name_definition_0.add(_position);
				_position_variable_name_definition=_position;
				_token_variable_name_definition=_token;
				_token=new Token.Parsed("$ANON");
				parse_all_type_name();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
						_furthestPosition=_position;
					}
					_position=_position_variable_name_definition;
				}
				else {
					int _state_79 = _state;
					parse__anonymous_130();
					if (_state_79==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
							_furthestPosition=_position;
						}
						_position=_position_variable_name_definition;
					}
					else {
						int _state_80 = _state;
						while (_position<_inputLength){
							parse__anonymous_131();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_80==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
								_furthestPosition=_position;
							}
							_position=_position_variable_name_definition;
						}
						else {
							parse__anonymous_132();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
									_furthestPosition=_position;
								}
								_position=_position_variable_name_definition;
							}
							else {
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_variable_name_definition.addAll(_token);
				}
				_token=_token_variable_name_definition;
				_recursion_protection_variable_name_definition_0.remove(_position_variable_name_definition);
			}
		}
		public void parse__anonymous_55(){
			int _position__anonymous_55 = -1;
			Token.Parsed _token__anonymous_55 = null;
			_position__anonymous_55=_position;
			_token__anonymous_55=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='i'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='f'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+2;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"if\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_55)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_55;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_55.addAll(_token);
			}
			_token=_token__anonymous_55;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_55=_position;
				_token__anonymous_55=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+4-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='e'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='l'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='s'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+4;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"else\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_55)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_55;
				}
				else {
					if (_position+2-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!='i'){
							_state=FAILED;
						}
						if (_inputArray[_position+1]!='f'){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_position=_position+2;
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"if\"");
							_furthestPosition=_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_55)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_55;
					}
					else {
					}
				}
				if (_state==SUCCESS){
					_token__anonymous_55.addAll(_token);
				}
				_token=_token__anonymous_55;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_55=_position;
					_token__anonymous_55=_token;
					_token=new Token.Parsed("$ANON");
					if (_position+5-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!='w'){
							_state=FAILED;
						}
						if (_inputArray[_position+1]!='h'){
							_state=FAILED;
						}
						if (_inputArray[_position+2]!='i'){
							_state=FAILED;
						}
						if (_inputArray[_position+3]!='l'){
							_state=FAILED;
						}
						if (_inputArray[_position+4]!='e'){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_position=_position+5;
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"while\"");
							_furthestPosition=_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_55)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_55;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_55.addAll(_token);
					}
					_token=_token__anonymous_55;
					if (_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_55=_position;
						_token__anonymous_55=_token;
						_token=new Token.Parsed("$ANON");
						if (_position+12-1 >=_inputLength){
							_state=FAILED;
						}
						else {
							if (_inputArray[_position+0]!='s'){
								_state=FAILED;
							}
							if (_inputArray[_position+1]!='y'){
								_state=FAILED;
							}
							if (_inputArray[_position+2]!='n'){
								_state=FAILED;
							}
							if (_inputArray[_position+3]!='c'){
								_state=FAILED;
							}
							if (_inputArray[_position+4]!='h'){
								_state=FAILED;
							}
							if (_inputArray[_position+5]!='r'){
								_state=FAILED;
							}
							if (_inputArray[_position+6]!='o'){
								_state=FAILED;
							}
							if (_inputArray[_position+7]!='n'){
								_state=FAILED;
							}
							if (_inputArray[_position+8]!='i'){
								_state=FAILED;
							}
							if (_inputArray[_position+9]!='z'){
								_state=FAILED;
							}
							if (_inputArray[_position+10]!='e'){
								_state=FAILED;
							}
							if (_inputArray[_position+11]!='d'){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_position=_position+12;
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
						else if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"synchronized\"");
								_furthestPosition=_position;
							}
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_55)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_55;
						}
						else {
						}
						if (_state==SUCCESS){
							_token__anonymous_55.addAll(_token);
						}
						_token=_token__anonymous_55;
						if (_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_55=_position;
							_token__anonymous_55=_token;
							_token=new Token.Parsed("$ANON");
							if (_position+6-1 >=_inputLength){
								_state=FAILED;
							}
							else {
								if (_inputArray[_position+0]!='s'){
									_state=FAILED;
								}
								if (_inputArray[_position+1]!='w'){
									_state=FAILED;
								}
								if (_inputArray[_position+2]!='i'){
									_state=FAILED;
								}
								if (_inputArray[_position+3]!='t'){
									_state=FAILED;
								}
								if (_inputArray[_position+4]!='c'){
									_state=FAILED;
								}
								if (_inputArray[_position+5]!='h'){
									_state=FAILED;
								}
							}
							if (_state==SUCCESS){
								_position=_position+6;
								while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
									++_position;
								}
							}
							else if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"switch\"");
									_furthestPosition=_position;
								}
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_55)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_55;
							}
							else {
							}
							if (_state==SUCCESS){
								_token__anonymous_55.addAll(_token);
							}
							_token=_token__anonymous_55;
							if (_state==FAILED){
								_state=SUCCESS;
								_position__anonymous_55=_position;
								_token__anonymous_55=_token;
								_token=new Token.Parsed("$ANON");
								if (_position+4-1 >=_inputLength){
									_state=FAILED;
								}
								else {
									if (_inputArray[_position+0]!='c'){
										_state=FAILED;
									}
									if (_inputArray[_position+1]!='a'){
										_state=FAILED;
									}
									if (_inputArray[_position+2]!='s'){
										_state=FAILED;
									}
									if (_inputArray[_position+3]!='e'){
										_state=FAILED;
									}
								}
								if (_state==SUCCESS){
									_position=_position+4;
									while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
										++_position;
									}
								}
								else if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"case\"");
										_furthestPosition=_position;
									}
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_55)");
										_furthestPosition=_position;
									}
									_position=_position__anonymous_55;
								}
								else {
								}
								if (_state==SUCCESS){
									_token__anonymous_55.addAll(_token);
								}
								_token=_token__anonymous_55;
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_54(){
			int _position__anonymous_54 = -1;
			Token.Parsed _token__anonymous_54 = null;
			_position__anonymous_54=_position;
			_token__anonymous_54=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_54)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_54;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_54.addAll(_token);
			}
			_token=_token__anonymous_54;
		}
		public void parse__anonymous_57(){
			int _position__anonymous_57 = -1;
			Token.Parsed _token__anonymous_57 = null;
			_position__anonymous_57=_position;
			_token__anonymous_57=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_57)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_57;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_57.addAll(_token);
			}
			_token=_token__anonymous_57;
		}
		public void parse__anonymous_120(){
			int _position__anonymous_120 = -1;
			Token.Parsed _token__anonymous_120 = null;
			_position__anonymous_120=_position;
			_token__anonymous_120=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_120)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_120;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_120.addAll(_token);
			}
			_token=_token__anonymous_120;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_120=_position;
				_token__anonymous_120=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_120)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_120;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_120.addAll(_token);
				}
				_token=_token__anonymous_120;
			}
		}
		public void parse__anonymous_56(){
			int _position__anonymous_56 = -1;
			Token.Parsed _token__anonymous_56 = null;
			_position__anonymous_56=_position;
			_token__anonymous_56=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_body();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_56;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_56.addAll(_token);
			}
			_token=_token__anonymous_56;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_56=_position;
				_token__anonymous_56=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_56;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_56.addAll(_token);
				}
				_token=_token__anonymous_56;
			}
		}
		public void parse__anonymous_121(){
			int _position__anonymous_121 = -1;
			Token.Parsed _token__anonymous_121 = null;
			_position__anonymous_121=_position;
			_token__anonymous_121=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_121)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_121;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_121.addAll(_token);
			}
			_token=_token__anonymous_121;
		}
		public void parse__anonymous_59(){
			int _position__anonymous_59 = -1;
			Token.Parsed _token__anonymous_59 = null;
			_position__anonymous_59=_position;
			_token__anonymous_59=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_59)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_59;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_59.addAll(_token);
			}
			_token=_token__anonymous_59;
		}
		public void parse__anonymous_58(){
			int _position__anonymous_58 = -1;
			Token.Parsed _token__anonymous_58 = null;
			_position__anonymous_58=_position;
			_token__anonymous_58=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_body();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_58)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_58;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_58.addAll(_token);
			}
			_token=_token__anonymous_58;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_58=_position;
				_token__anonymous_58=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_58)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_58;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_58.addAll(_token);
				}
				_token=_token__anonymous_58;
			}
		}
		public void parse__anonymous_115(){
			int _position__anonymous_115 = -1;
			Token.Parsed _token__anonymous_115 = null;
			_position__anonymous_115=_position;
			_token__anonymous_115=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_115)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_115;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_115.addAll(_token);
			}
			_token=_token__anonymous_115;
		}
		public void parse__anonymous_116(){
			int _position__anonymous_116 = -1;
			Token.Parsed _token__anonymous_116 = null;
			_position__anonymous_116=_position;
			_token__anonymous_116=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_117();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_116)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_116;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_116.addAll(_token);
			}
			_token=_token__anonymous_116;
		}
		public void parse__anonymous_117(){
			int _position__anonymous_117 = -1;
			Token.Parsed _token__anonymous_117 = null;
			_position__anonymous_117=_position;
			_token__anonymous_117=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='@'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"@\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_117)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_117;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_117.addAll(_token);
			}
			_token=_token__anonymous_117;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_117=_position;
				_token__anonymous_117=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='s'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='t'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='a'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='t'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='i'){
						_state=FAILED;
					}
					if (_inputArray[_position+5]!='c'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+6;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"static\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_117)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_117;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_117.addAll(_token);
				}
				_token=_token__anonymous_117;
			}
		}
		public void parse__anonymous_118(){
			int _position__anonymous_118 = -1;
			Token.Parsed _token__anonymous_118 = null;
			_position__anonymous_118=_position;
			_token__anonymous_118=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='['){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!=']'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.ARRAYTYPEToken.plain_5);
				_position=_position+2;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"[]\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_118)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_118;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_118.addAll(_token);
			}
			_token=_token__anonymous_118;
		}
		public Set<Integer> get_recursionProtectionOPERATOR0(){
			return _recursion_protection_OPERATOR_0;
		}
		public void parse_OPERATOR(){
			int _position_OPERATOR = -1;
			Token.Parsed _token_OPERATOR = null;
			if (_state==SUCCESS&&_recursion_protection_OPERATOR_0.contains(_position)==false){
				_recursion_protection_OPERATOR_0.add(_position);
				_position_OPERATOR=_position;
				_token_OPERATOR=_token;
				_token=new Tokens.Rule.OPERATORToken();
				int _position_regex_2 = _position;
				int _multiple_index_3 = 0;
				while (_position<_inputLength){
					if (_inputArray[_position]!='a'&&_inputArray[_position]!='b'&&_inputArray[_position]!='c'&&_inputArray[_position]!='d'&&_inputArray[_position]!='e'&&_inputArray[_position]!='f'&&_inputArray[_position]!='g'&&_inputArray[_position]!='h'&&_inputArray[_position]!='i'&&_inputArray[_position]!='j'&&_inputArray[_position]!='k'&&_inputArray[_position]!='l'&&_inputArray[_position]!='m'&&_inputArray[_position]!='n'&&_inputArray[_position]!='o'&&_inputArray[_position]!='p'&&_inputArray[_position]!='q'&&_inputArray[_position]!='r'&&_inputArray[_position]!='s'&&_inputArray[_position]!='t'&&_inputArray[_position]!='u'&&_inputArray[_position]!='v'&&_inputArray[_position]!='w'&&_inputArray[_position]!='x'&&_inputArray[_position]!='y'&&_inputArray[_position]!='z'&&_inputArray[_position]!='A'&&_inputArray[_position]!='B'&&_inputArray[_position]!='C'&&_inputArray[_position]!='D'&&_inputArray[_position]!='E'&&_inputArray[_position]!='F'&&_inputArray[_position]!='G'&&_inputArray[_position]!='H'&&_inputArray[_position]!='I'&&_inputArray[_position]!='J'&&_inputArray[_position]!='K'&&_inputArray[_position]!='L'&&_inputArray[_position]!='M'&&_inputArray[_position]!='N'&&_inputArray[_position]!='O'&&_inputArray[_position]!='P'&&_inputArray[_position]!='Q'&&_inputArray[_position]!='R'&&_inputArray[_position]!='S'&&_inputArray[_position]!='T'&&_inputArray[_position]!='U'&&_inputArray[_position]!='V'&&_inputArray[_position]!='W'&&_inputArray[_position]!='X'&&_inputArray[_position]!='Y'&&_inputArray[_position]!='Z'&&_inputArray[_position]!='0'&&_inputArray[_position]!='1'&&_inputArray[_position]!='2'&&_inputArray[_position]!='3'&&_inputArray[_position]!='4'&&_inputArray[_position]!='5'&&_inputArray[_position]!='6'&&_inputArray[_position]!='7'&&_inputArray[_position]!='8'&&_inputArray[_position]!='9'&&_inputArray[_position]!='_'&&_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'&&_inputArray[_position]!='\\'&&_inputArray[_position]!='n'&&_inputArray[_position]!='('&&_inputArray[_position]!=')'&&_inputArray[_position]!='{'&&_inputArray[_position]!='}'&&_inputArray[_position]!='['&&_inputArray[_position]!=']'&&_inputArray[_position]!=';'&&_inputArray[_position]!='\"'&&_inputArray[_position]!='\''&&_inputArray[_position]!='`'&&_inputArray[_position]!=','){
						++_position;
						++_multiple_index_3;
					}
					else {
						break;
					}
				}
				if (_multiple_index_3==0 ){
					_state=FAILED;
				}
				if (_state==SUCCESS){
					_token.setValue(_input.substring(_position_regex_2,_position));
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z0-9_\\\\s\\n(){}[];\"\'`,]+");
						_furthestPosition=_position;
					}
					_position=_position_regex_2;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"OPERATOR(OPERATOR)");
						_furthestPosition=_position;
					}
					_position=_position_OPERATOR;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_OPERATOR.add(_position_OPERATOR,_token);
				}
				_token=_token_OPERATOR;
				_recursion_protection_OPERATOR_0.remove(_position_OPERATOR);
			}
		}
		public void parse__anonymous_51(){
			int _position__anonymous_51 = -1;
			Token.Parsed _token__anonymous_51 = null;
			_position__anonymous_51=_position;
			_token__anonymous_51=_token;
			_token=new Token.Parsed("$ANON");
			parse_variable_declaration();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_51)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_51;
			}
			else {
				if (_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!=';'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+1;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_51)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_51;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_51.addAll(_token);
			}
			_token=_token__anonymous_51;
		}
		public void parse__anonymous_111(){
			int _position__anonymous_111 = -1;
			Token.Parsed _token__anonymous_111 = null;
			_position__anonymous_111=_position;
			_token__anonymous_111=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!=':'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \":\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_111)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_111;
			}
			else {
				parse_NAME();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_111)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_111;
				}
				else {
					parse_method_body();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_111)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_111;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_111.addAll(_token);
			}
			_token=_token__anonymous_111;
		}
		public void parse__anonymous_50(){
			int _position__anonymous_50 = -1;
			Token.Parsed _token__anonymous_50 = null;
			_position__anonymous_50=_position;
			_token__anonymous_50=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+5-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='t'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='h'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='r'){
					_state=FAILED;
				}
				if (_inputArray[_position+3]!='o'){
					_state=FAILED;
				}
				if (_inputArray[_position+4]!='w'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+5;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"throw\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_50)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_50;
			}
			else {
				parse_body_statement();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_50)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_50;
				}
				else {
					if (_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!=';'){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_position=_position+1;
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
							_furthestPosition=_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_50)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_50;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_50.addAll(_token);
			}
			_token=_token__anonymous_50;
		}
		public void parse__anonymous_112(){
			int _position__anonymous_112 = -1;
			Token.Parsed _token__anonymous_112 = null;
			_position__anonymous_112=_position;
			_token__anonymous_112=_token;
			_token=new Token.Parsed("$ANON");
			parse_variable_declaration();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_112)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_112;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_112.addAll(_token);
			}
			_token=_token__anonymous_112;
		}
		public void parse__anonymous_53(){
			int _position__anonymous_53 = -1;
			Token.Parsed _token__anonymous_53 = null;
			_position__anonymous_53=_position;
			_token__anonymous_53=_token;
			_token=new Token.Parsed("$ANON");
			parse_body_statement();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_53)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_53;
			}
			else {
				if (_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!=';'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+1;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_53)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_53;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_53.addAll(_token);
			}
			_token=_token__anonymous_53;
		}
		public void parse__anonymous_113(){
			int _position__anonymous_113 = -1;
			Token.Parsed _token__anonymous_113 = null;
			_position__anonymous_113=_position;
			_token__anonymous_113=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_114();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_113)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_113;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_113.addAll(_token);
			}
			_token=_token__anonymous_113;
		}
		public void parse__anonymous_52(){
			int _position__anonymous_52 = -1;
			Token.Parsed _token__anonymous_52 = null;
			_position__anonymous_52=_position;
			_token__anonymous_52=_token;
			_token=new Token.Parsed("$ANON");
			parse_variable_assignment();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_52)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_52;
			}
			else {
				if (_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!=';'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+1;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_52)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_52;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_52.addAll(_token);
			}
			_token=_token__anonymous_52;
		}
		public void parse__anonymous_114(){
			int _position__anonymous_114 = -1;
			Token.Parsed _token__anonymous_114 = null;
			_position__anonymous_114=_position;
			_token__anonymous_114=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!=','){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \",\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_114)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_114;
			}
			else {
				parse_variable_declaration();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_114)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_114;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_114.addAll(_token);
			}
			_token=_token__anonymous_114;
		}
		public Set<Integer> get_recursionProtectionBodyCall0(){
			return _recursion_protection_body_call_0;
		}
		public Set<Integer> get_recursionProtectionBodyCall1(){
			return _recursion_protection_body_call_1;
		}
		public Set<Integer> get_recursionProtectionBodyCall2(){
			return _recursion_protection_body_call_2;
		}
		public Set<Integer> get_recursionProtectionBodyCall3(){
			return _recursion_protection_body_call_3;
		}
		public void parse_body_call(){
			int _position_body_call = -1;
			Token.Parsed _token_body_call = null;
			if (_state==SUCCESS&&_recursion_protection_body_call_0.contains(_position)==false){
				_recursion_protection_body_call_0.add(_position);
				_position_body_call=_position;
				_token_body_call=_token;
				_token=new Tokens.Rule.BodyCallToken();
				parse__anonymous_75();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
						_furthestPosition=_position;
					}
					_position=_position_body_call;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_body_call.add(_position_body_call,_token);
				}
				_token=_token_body_call;
				_recursion_protection_body_call_0.remove(_position_body_call);
				if (_state==FAILED&&_recursion_protection_body_call_1.contains(_position)==false){
					_recursion_protection_body_call_1.add(_position);
					_state=SUCCESS;
					_position_body_call=_position;
					_token_body_call=_token;
					_token=new Tokens.Rule.BodyCallToken();
					parse__anonymous_80();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
							_furthestPosition=_position;
						}
						_position=_position_body_call;
					}
					else {
						int _state_53 = _state;
						while (_position<_inputLength){
							parse__anonymous_83();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_53==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
								_furthestPosition=_position;
							}
							_position=_position_body_call;
						}
						else {
						}
					}
					if (_state==SUCCESS){
						_token_body_call.add(_position_body_call,_token);
					}
					_token=_token_body_call;
					_recursion_protection_body_call_1.remove(_position_body_call);
					if (_state==FAILED&&_recursion_protection_body_call_2.contains(_position)==false){
						_recursion_protection_body_call_2.add(_position);
						_state=SUCCESS;
						_position_body_call=_position;
						_token_body_call=_token;
						_token=new Tokens.Rule.BodyCallToken();
						parse__anonymous_88();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
								_furthestPosition=_position;
							}
							_position=_position_body_call;
						}
						else {
							int _state_58 = _state;
							while (_position<_inputLength){
								parse__anonymous_91();
								if (_state==FAILED){
									break;
								}
							}
							if (_state_58==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
									_furthestPosition=_position;
								}
								_position=_position_body_call;
							}
							else {
							}
						}
						if (_state==SUCCESS){
							_token_body_call.add(_position_body_call,_token);
						}
						_token=_token_body_call;
						_recursion_protection_body_call_2.remove(_position_body_call);
						if (_state==FAILED&&_recursion_protection_body_call_3.contains(_position)==false){
							_recursion_protection_body_call_3.add(_position);
							_state=SUCCESS;
							_position_body_call=_position;
							_token_body_call=_token;
							_token=new Tokens.Rule.BodyCallToken();
							parse__anonymous_97();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
									_furthestPosition=_position;
								}
								_position=_position_body_call;
							}
							else {
								int _state_64 = _state;
								while (_position<_inputLength){
									parse__anonymous_102();
									if (_state==FAILED){
										break;
									}
								}
								if (_state_64==SUCCESS&&_state==FAILED){
									_state=SUCCESS;
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
										_furthestPosition=_position;
									}
									_position=_position_body_call;
								}
								else {
								}
							}
							if (_state==SUCCESS){
								_token_body_call.add(_position_body_call,_token);
							}
							_token=_token_body_call;
							_recursion_protection_body_call_3.remove(_position_body_call);
						}
					}
				}
			}
		}
		public void parse__anonymous_119(){
			int _position__anonymous_119 = -1;
			Token.Parsed _token__anonymous_119 = null;
			_position__anonymous_119=_position;
			_token__anonymous_119=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.NAMEToken.plain_6);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"*\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_119)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_119;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_119.addAll(_token);
			}
			_token=_token__anonymous_119;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_119=_position;
				_token__anonymous_119=_token;
				_token=new Token.Parsed("$ANON");
				parse_name_var();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_119)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_119;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_119.addAll(_token);
				}
				_token=_token__anonymous_119;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_119=_position;
					_token__anonymous_119=_token;
					_token=new Token.Parsed("$ANON");
					parse_NAME();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_119)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_119;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_119.addAll(_token);
					}
					_token=_token__anonymous_119;
				}
			}
		}
		public void parse__anonymous_44(){
			int _position__anonymous_44 = -1;
			Token.Parsed _token__anonymous_44 = null;
			_position__anonymous_44=_position;
			_token__anonymous_44=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_45();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_44)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_44;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_44.addAll(_token);
			}
			_token=_token__anonymous_44;
		}
		public void parse__anonymous_151(){
			int _position__anonymous_151 = -1;
			Token.Parsed _token__anonymous_151 = null;
			_position__anonymous_151=_position;
			_token__anonymous_151=_token;
			_token=new Token.Parsed("$ANON");
			if (_pass==FIRST_PASS){
				parse_NAME();
			}
			else if (_pass==SECOND_PASS){
				_list_name_result=variable_names.get(_position,_inputLength,_inputArray);
				if (_list_name_result!=null){
					if (_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
				}
				else {
					_state=FAILED;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_151)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_151;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_151.addAll(_token);
			}
			_token=_token__anonymous_151;
		}
		public void parse__anonymous_43(){
			int _position__anonymous_43 = -1;
			Token.Parsed _token__anonymous_43 = null;
			_position__anonymous_43=_position;
			_token__anonymous_43=_token;
			_token=new Token.Parsed("$ANON");
			parse_type_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_43)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_43;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_43.addAll(_token);
			}
			_token=_token__anonymous_43;
		}
		public void parse__anonymous_152(){
			int _position__anonymous_152 = -1;
			Token.Parsed _token__anonymous_152 = null;
			_position__anonymous_152=_position;
			_token__anonymous_152=_token;
			_token=new Token.Parsed("$ANON");
			int _state_88 = _state;
			parse__anonymous_153();
			if (_state_88==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_152)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_152;
			}
			else {
				parse__anonymous_154();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_152)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_152;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_152.addAll(_token);
			}
			_token=_token__anonymous_152;
		}
		public void parse__anonymous_46(){
			int _position__anonymous_46 = -1;
			Token.Parsed _token__anonymous_46 = null;
			_position__anonymous_46=_position;
			_token__anonymous_46=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_46)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_46;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_46.addAll(_token);
			}
			_token=_token__anonymous_46;
		}
		public void parse__anonymous_153(){
			int _position__anonymous_153 = -1;
			Token.Parsed _token__anonymous_153 = null;
			_position__anonymous_153=_position;
			_token__anonymous_153=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_13);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_153)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_153;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_153.addAll(_token);
			}
			_token=_token__anonymous_153;
		}
		public void parse__anonymous_45(){
			int _position__anonymous_45 = -1;
			Token.Parsed _token__anonymous_45 = null;
			_position__anonymous_45=_position;
			_token__anonymous_45=_token;
			_token=new Token.Parsed("$ANON");
			parse_type_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_45)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_45;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_45.addAll(_token);
			}
			_token=_token__anonymous_45;
		}
		public void parse__anonymous_154(){
			int _position__anonymous_154 = -1;
			Token.Parsed _token__anonymous_154 = null;
			_position__anonymous_154=_position;
			_token__anonymous_154=_token;
			_token=new Token.Parsed("$ANON");
			parse_quote();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_154)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_154;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_154.addAll(_token);
			}
			_token=_token__anonymous_154;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_154=_position;
				_token__anonymous_154=_token;
				_token=new Token.Parsed("$ANON");
				parse__anonymous_155();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_154)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_154;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_154.addAll(_token);
				}
				_token=_token__anonymous_154;
			}
		}
		public void parse__anonymous_48(){
			int _position__anonymous_48 = -1;
			Token.Parsed _token__anonymous_48 = null;
			_position__anonymous_48=_position;
			_token__anonymous_48=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+4-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='v'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='o'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='i'){
					_state=FAILED;
				}
				if (_inputArray[_position+3]!='d'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+4;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"void\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_48)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_48;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_48.addAll(_token);
			}
			_token=_token__anonymous_48;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_48=_position;
				_token__anonymous_48=_token;
				_token=new Token.Parsed("$ANON");
				parse_method_argument();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_48)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_48;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_48.addAll(_token);
				}
				_token=_token__anonymous_48;
			}
		}
		public void parse__anonymous_47(){
			int _position__anonymous_47 = -1;
			Token.Parsed _token__anonymous_47 = null;
			_position__anonymous_47=_position;
			_token__anonymous_47=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='r'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='e'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='t'){
					_state=FAILED;
				}
				if (_inputArray[_position+3]!='u'){
					_state=FAILED;
				}
				if (_inputArray[_position+4]!='r'){
					_state=FAILED;
				}
				if (_inputArray[_position+5]!='n'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+6;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"return\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_47)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_47;
			}
			else {
				parse__anonymous_48();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_47)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_47;
				}
				else {
					if (_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!=';'){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_position=_position+1;
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
							_furthestPosition=_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_47)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_47;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_47.addAll(_token);
			}
			_token=_token__anonymous_47;
		}
		public void parse__anonymous_49(){
			int _position__anonymous_49 = -1;
			Token.Parsed _token__anonymous_49 = null;
			_position__anonymous_49=_position;
			_token__anonymous_49=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_49)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_49;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_49.addAll(_token);
			}
			_token=_token__anonymous_49;
		}
		public void parse__anonymous_150(){
			int _position__anonymous_150 = -1;
			Token.Parsed _token__anonymous_150 = null;
			_position__anonymous_150=_position;
			_token__anonymous_150=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_12);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_150)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_150;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_150.addAll(_token);
			}
			_token=_token__anonymous_150;
		}
		public void parse__anonymous_148(){
			int _position__anonymous_148 = -1;
			Token.Parsed _token__anonymous_148 = null;
			_position__anonymous_148=_position;
			_token__anonymous_148=_token;
			_token=new Token.Parsed("$ANON");
			parse_quote();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_148)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_148;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_148.addAll(_token);
			}
			_token=_token__anonymous_148;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_148=_position;
				_token__anonymous_148=_token;
				_token=new Token.Parsed("$ANON");
				if (_pass==FIRST_PASS){
					parse_NAME();
				}
				else if (_pass==SECOND_PASS){
					_list_name_result=variable_names.get(_position,_inputLength,_inputArray);
					if (_list_name_result!=null){
						if (_position+_list_name_result.length()<_inputLength){
							int _next_char = _inputArray[_position+_list_name_result.length()];
							if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
							_position+=_list_name_result.length();
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
							_furthestPosition=_position;
						}
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_148)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_148;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_148.addAll(_token);
				}
				_token=_token__anonymous_148;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_148=_position;
					_token__anonymous_148=_token;
					_token=new Token.Parsed("$ANON");
					parse_NAME();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_148)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_148;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_148.addAll(_token);
					}
					_token=_token__anonymous_148;
				}
			}
		}
		public void parse__anonymous_149(){
			int _position__anonymous_149 = -1;
			Token.Parsed _token__anonymous_149 = null;
			_position__anonymous_149=_position;
			_token__anonymous_149=_token;
			_token=new Token.Parsed("$ANON");
			int _state_87 = _state;
			parse__anonymous_150();
			if (_state_87==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_149)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_149;
			}
			else {
				parse__anonymous_151();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_149)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_149;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_149.addAll(_token);
			}
			_token=_token__anonymous_149;
		}
		public void parse__anonymous_40(){
			int _position__anonymous_40 = -1;
			Token.Parsed _token__anonymous_40 = null;
			_position__anonymous_40=_position;
			_token__anonymous_40=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_41();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_40)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_40;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_40.addAll(_token);
			}
			_token=_token__anonymous_40;
		}
		public void parse__anonymous_144(){
			int _position__anonymous_144 = -1;
			Token.Parsed _token__anonymous_144 = null;
			_position__anonymous_144=_position;
			_token__anonymous_144=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='+'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"+\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_144)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_144;
			}
			else {
				parse_name_var_element();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_144)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_144;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_144.addAll(_token);
			}
			_token=_token__anonymous_144;
		}
		public void parse__anonymous_145(){
			int _position__anonymous_145 = -1;
			Token.Parsed _token__anonymous_145 = null;
			_position__anonymous_145=_position;
			_token__anonymous_145=_token;
			_token=new Token.Parsed("$ANON");
			parse_NUMBER();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_145)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_145;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_145.addAll(_token);
			}
			_token=_token__anonymous_145;
		}
		public void parse__anonymous_42(){
			int _position__anonymous_42 = -1;
			Token.Parsed _token__anonymous_42 = null;
			_position__anonymous_42=_position;
			_token__anonymous_42=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_43();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_42)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_42;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_42.addAll(_token);
			}
			_token=_token__anonymous_42;
		}
		public void parse__anonymous_146(){
			int _position__anonymous_146 = -1;
			Token.Parsed _token__anonymous_146 = null;
			_position__anonymous_146=_position;
			_token__anonymous_146=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"\\\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_146)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_146;
			}
			else {
				int _state_86 = _state;
				parse__anonymous_147();
				if (_state_86==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_146)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_146;
				}
				else {
					parse__anonymous_148();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_146)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_146;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_146.addAll(_token);
			}
			_token=_token__anonymous_146;
		}
		public Parser.NameList getClassNamesAdditionsAnonymous41(){
			return class_names_additions__anonymous_41;
		}
		public Parser.NameList getClassVariableNamesAdditionsAnonymous41(){
			return class_variable_names_additions__anonymous_41;
		}
		public void parse__anonymous_41(){
			int _position__anonymous_41 = -1;
			Token.Parsed _token__anonymous_41 = null;
			class_names_additions__anonymous_41=class_names_additions;
			class_names_additions=new Parser.NameList.Child(class_names);
			class_variable_names_additions__anonymous_41=class_variable_names_additions;
			class_variable_names_additions=new Parser.NameList.Child(class_variable_names);
			_position__anonymous_41=_position;
			_token__anonymous_41=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='<'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"<\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_41)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_41;
			}
			else {
				parse_NAME();
				if (_state==SUCCESS){
					String _value = _token.getPollLast().getValue();
					if (_value!=null&&class_variable_names.add(_value)){
						class_variable_names_additions.add(_value);
					}
					_token.add(_position,new Tokens.Name.TemplateTypeNameToken(_value));
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_41)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_41;
				}
				else {
					if (_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!='>'){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_position=_position+1;
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \">\"");
							_furthestPosition=_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_41)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_41;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_41.addAll(_token);
			}
			_token=_token__anonymous_41;
			if (_state==FAILED){
				if (class_names_additions!=null){
					class_names.removeAll(class_names_additions);
					class_names_additions.clear();
				}
				if (class_variable_names_additions!=null){
					class_variable_names.removeAll(class_variable_names_additions);
					class_variable_names_additions.clear();
				}
			}
			else if (_state==SUCCESS){
				if (class_names_additions!=null){
					class_names.addAll(class_names_additions);
					class_names_additions.clear();
				}
				if (class_variable_names_additions!=null){
					class_variable_names.addAll(class_variable_names_additions);
					class_variable_names_additions.clear();
				}
			}
			class_names_additions=class_names_additions__anonymous_41;
			class_variable_names_additions=class_variable_names_additions__anonymous_41;
		}
		public void parse__anonymous_147(){
			int _position__anonymous_147 = -1;
			Token.Parsed _token__anonymous_147 = null;
			_position__anonymous_147=_position;
			_token__anonymous_147=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_11);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_147)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_147;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_147.addAll(_token);
			}
			_token=_token__anonymous_147;
		}
		public Set<Integer> get_recursionProtectionNUMBER0(){
			return _recursion_protection_NUMBER_0;
		}
		public void parse_NUMBER(){
			int _position_NUMBER = -1;
			Token.Parsed _token_NUMBER = null;
			if (_state==SUCCESS&&_recursion_protection_NUMBER_0.contains(_position)==false){
				_recursion_protection_NUMBER_0.add(_position);
				_position_NUMBER=_position;
				_token_NUMBER=_token;
				_token=new Tokens.Rule.NUMBERToken();
				int _position_regex_3 = _position;
				if (_position<_inputLength){
					if (_inputArray[_position]=='-'){
						++_position;
					}
				}
				int _state_5 = _state;
				if (_position<_inputLength){
					int _position_of_last_success_5 = _position;
					int _multiple_index_6 = 0;
					int _state_6 = _state;
					while (_position<_inputLength){
						if (_position<_inputLength){
							if (_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9'){
								++_position;
							}
							else {
								_state=FAILED;
							}
						}
						else {
							_state=FAILED;
						}
						if (_state==FAILED){
							break;
						}
						else {
							++_multiple_index_6;
						}
					}
					if (_state_6==SUCCESS){
						_state=SUCCESS;
					}
					if (_position<_inputLength){
						if (_inputArray[_position]=='.'){
							++_position;
						}
						else {
							_state=FAILED;
						}
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						_position=_position_of_last_success_5;
					}
				}
				if (_state_5==SUCCESS){
					_state=SUCCESS;
				}
				int _multiple_index_7 = 0;
				int _state_7 = _state;
				while (_position<_inputLength){
					if (_position<_inputLength){
						if (_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9'){
							++_position;
						}
						else {
							_state=FAILED;
						}
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						break;
					}
					else {
						++_multiple_index_7;
					}
				}
				if (_state_7==SUCCESS&&_multiple_index_7>0 ){
					_state=SUCCESS;
				}
				else {
					_state=FAILED;
				}
				int _state_8 = _state;
				if (_position<_inputLength){
					if (_position<_inputLength){
						if (_inputArray[_position]=='f'){
							++_position;
						}
						else {
							_state=FAILED;
						}
					}
					else {
						_state=FAILED;
					}
				}
				if (_state_8==SUCCESS){
					_state=SUCCESS;
				}
				int _multiple_index_9 = 0;
				int _state_9 = _state;
				while (_position<_inputLength){
					if (_position<_inputLength){
						if (_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n'){
							++_position;
						}
						else {
							_state=FAILED;
						}
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						break;
					}
					else {
						++_multiple_index_9;
					}
				}
				if (_state_9==SUCCESS){
					_state=SUCCESS;
				}
				if (_state==SUCCESS){
					_token.setValue(_input.substring(_position_regex_3,_position));
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[-]?(\\\\d*[.]?\\\\d+f?\\\\s*");
						_furthestPosition=_position;
					}
					_position=_position_regex_3;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"NUMBER(NUMBER)");
						_furthestPosition=_position;
					}
					_position=_position_NUMBER;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_NUMBER.add(_position_NUMBER,_token);
				}
				_token=_token_NUMBER;
				_recursion_protection_NUMBER_0.remove(_position_NUMBER);
			}
		}
		public Parser.NameList getClassVariableNamesClassBody(){
			return class_variable_names_class_body;
		}
		public Parser.NameList getVariableNamesClassBody(){
			return variable_names_class_body;
		}
		public Set<Integer> get_recursionProtectionClassBody0(){
			return _recursion_protection_class_body_0;
		}
		public void parse_class_body(){
			int _position_class_body = -1;
			Token.Parsed _token_class_body = null;
			int _length_class_body_brace = _inputLength;
			if (brace_2.containsKey(_position)){
				class_variable_names_class_body=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_class_body=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_2.get(_position);
				int _position_class_body_brace = _position;
				_position+=1;
				while (_position<_length_class_body_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_class_body_0.contains(_position)==false){
					_recursion_protection_class_body_0.add(_position);
					_position_class_body=_position;
					_token_class_body=_token;
					_token=new Tokens.Rule.ClassBodyToken();
					int _state_11 = _state;
					while (_position<_inputLength){
						parse__anonymous_1();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_11==SUCCESS&&_state==SUCCESS)
					if (_state_11==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_body(class_body)");
							_furthestPosition=_position;
						}
						_position=_position_class_body;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_class_body.add(_position_class_body,_token);
					}
					_token=_token_class_body;
					_recursion_protection_class_body_0.remove(_position_class_body);
				}
				if (_state==SUCCESS&&brace_2.get(_position_class_body_brace)==_position){
					_position+=1;
					while (_position<_length_class_body_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_2.get(_position_class_body_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_class_body_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_class_body_brace;
				class_variable_names=class_variable_names_class_body;
				variable_names=variable_names_class_body;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_body(class_body)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_33(){
			int _position__anonymous_33 = -1;
			Token.Parsed _token__anonymous_33 = null;
			_position__anonymous_33=_position;
			_token__anonymous_33=_token;
			_token=new Token.Parsed("$ANON");
			parse_type_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_33)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_33;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_33.addAll(_token);
			}
			_token=_token__anonymous_33;
		}
		public void parse__anonymous_140(){
			int _position__anonymous_140 = -1;
			Token.Parsed _token__anonymous_140 = null;
			_position__anonymous_140=_position;
			_token__anonymous_140=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='-'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+2;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"->\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_140)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_140;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_140.addAll(_token);
			}
			_token=_token__anonymous_140;
		}
		public void parse__anonymous_32(){
			int _position__anonymous_32 = -1;
			Token.Parsed _token__anonymous_32 = null;
			_position__anonymous_32=_position;
			_token__anonymous_32=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_33();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_32)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_32;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_32.addAll(_token);
			}
			_token=_token__anonymous_32;
		}
		public void parse__anonymous_141(){
			int _position__anonymous_141 = -1;
			Token.Parsed _token__anonymous_141 = null;
			_position__anonymous_141=_position;
			_token__anonymous_141=_token;
			_token=new Token.Parsed("$ANON");
			int _state_84 = _state;
			parse__anonymous_142();
			if (_state_84==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_141)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_141;
			}
			else {
				parse_name_var_element();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_141)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_141;
				}
				else {
					int _state_85 = _state;
					int _multiple_index_85 = 0;
					while (_position<_inputLength){
						parse__anonymous_143();
						if (_state==FAILED){
							break;
						}
						else {
							++_multiple_index_85;
						}
					}
					if (_multiple_index_85==0 ){
						_state=FAILED;
					}
					else if (_state_85==SUCCESS&&_multiple_index_85>0 &&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_141)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_141;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_141.addAll(_token);
			}
			_token=_token__anonymous_141;
		}
		public void parse__anonymous_35(){
			int _position__anonymous_35 = -1;
			Token.Parsed _token__anonymous_35 = null;
			_position__anonymous_35=_position;
			_token__anonymous_35=_token;
			_token=new Token.Parsed("$ANON");
			parse_type_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_35)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_35;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_35.addAll(_token);
			}
			_token=_token__anonymous_35;
		}
		public void parse__anonymous_142(){
			int _position__anonymous_142 = -1;
			Token.Parsed _token__anonymous_142 = null;
			_position__anonymous_142=_position;
			_token__anonymous_142=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_10);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_142)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_142;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_142.addAll(_token);
			}
			_token=_token__anonymous_142;
		}
		public void parse__anonymous_34(){
			int _position__anonymous_34 = -1;
			Token.Parsed _token__anonymous_34 = null;
			_position__anonymous_34=_position;
			_token__anonymous_34=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_35();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_34)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_34;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_34.addAll(_token);
			}
			_token=_token__anonymous_34;
		}
		public void parse__anonymous_143(){
			int _position__anonymous_143 = -1;
			Token.Parsed _token__anonymous_143 = null;
			_position__anonymous_143=_position;
			_token__anonymous_143=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_144();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_143)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_143;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_143.addAll(_token);
			}
			_token=_token__anonymous_143;
		}
		public void parse__anonymous_37(){
			int _position__anonymous_37 = -1;
			Token.Parsed _token__anonymous_37 = null;
			_position__anonymous_37=_position;
			_token__anonymous_37=_token;
			_token=new Token.Parsed("$ANON");
			parse_weak();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_37)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_37;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_37.addAll(_token);
			}
			_token=_token__anonymous_37;
		}
		public void parse__anonymous_36(){
			int _position__anonymous_36 = -1;
			Token.Parsed _token__anonymous_36 = null;
			_position__anonymous_36=_position;
			_token__anonymous_36=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_36)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_36;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_36.addAll(_token);
			}
			_token=_token__anonymous_36;
		}
		public void parse__anonymous_39(){
			int _position__anonymous_39 = -1;
			Token.Parsed _token__anonymous_39 = null;
			_position__anonymous_39=_position;
			_token__anonymous_39=_token;
			_token=new Token.Parsed("$ANON");
			if (_pass==FIRST_PASS){
				parse_NAME();
			}
			else if (_pass==SECOND_PASS){
				_list_name_result=variable_names.get(_position,_inputLength,_inputArray);
				if (_list_name_result!=null){
					if (_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
				}
				else {
					_state=FAILED;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_39)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_39;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_39.addAll(_token);
			}
			_token=_token__anonymous_39;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_39=_position;
				_token__anonymous_39=_token;
				_token=new Token.Parsed("$ANON");
				if (_pass==FIRST_PASS){
					parse_NAME();
				}
				else if (_pass==SECOND_PASS){
					_list_name_result=class_variable_names.get(_position,_inputLength,_inputArray);
					if (_list_name_result!=null){
						if (_position+_list_name_result.length()<_inputLength){
							int _next_char = _inputArray[_position+_list_name_result.length()];
							if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
							_position+=_list_name_result.length();
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
							_furthestPosition=_position;
						}
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_39)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_39;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_39.addAll(_token);
				}
				_token=_token__anonymous_39;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_39=_position;
					_token__anonymous_39=_token;
					_token=new Token.Parsed("$ANON");
					parse_NAME();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_39)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_39;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_39.addAll(_token);
					}
					_token=_token__anonymous_39;
				}
			}
		}
		public Set<Integer> get_recursionProtectionMethodArgument0(){
			return _recursion_protection_method_argument_0;
		}
		public Set<Integer> get_recursionProtectionMethodArgument1(){
			return _recursion_protection_method_argument_1;
		}
		public Set<Integer> get_recursionProtectionMethodArgument2(){
			return _recursion_protection_method_argument_2;
		}
		public Set<Integer> get_recursionProtectionMethodArgument3(){
			return _recursion_protection_method_argument_3;
		}
		public Set<Integer> get_recursionProtectionMethodArgument4(){
			return _recursion_protection_method_argument_4;
		}
		public Set<Integer> get_recursionProtectionMethodArgument5(){
			return _recursion_protection_method_argument_5;
		}
		public void parse_method_argument(){
			int _position_method_argument = -1;
			Token.Parsed _token_method_argument = null;
			if (_state==SUCCESS&&_recursion_protection_method_argument_0.contains(_position)==false){
				_recursion_protection_method_argument_0.add(_position);
				_position_method_argument=_position;
				_token_method_argument=_token;
				_token=new Tokens.Rule.MethodArgumentToken();
				parse_class_declaration();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
						_furthestPosition=_position;
					}
					_position=_position_method_argument;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_method_argument.add(_position_method_argument,_token);
				}
				_token=_token_method_argument;
				_recursion_protection_method_argument_0.remove(_position_method_argument);
				if (_state==FAILED&&_recursion_protection_method_argument_1.contains(_position)==false){
					_recursion_protection_method_argument_1.add(_position);
					_state=SUCCESS;
					_position_method_argument=_position;
					_token_method_argument=_token;
					_token=new Tokens.Rule.MethodArgumentToken();
					parse_method_declaration();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
							_furthestPosition=_position;
						}
						_position=_position_method_argument;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_method_argument.add(_position_method_argument,_token);
					}
					_token=_token_method_argument;
					_recursion_protection_method_argument_1.remove(_position_method_argument);
					if (_state==FAILED&&_recursion_protection_method_argument_2.contains(_position)==false){
						_recursion_protection_method_argument_2.add(_position);
						_state=SUCCESS;
						_position_method_argument=_position;
						_token_method_argument=_token;
						_token=new Tokens.Rule.MethodArgumentToken();
						parse_variable_declaration();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
								_furthestPosition=_position;
							}
							_position=_position_method_argument;
						}
						else {
							if (_position+1-1 >=_inputLength){
								_state=FAILED;
							}
							else {
								if (_inputArray[_position+0]!=';'){
									_state=FAILED;
								}
							}
							if (_state==SUCCESS){
								_position=_position+1;
								while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
									++_position;
								}
							}
							else if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
									_furthestPosition=_position;
								}
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
									_furthestPosition=_position;
								}
								_position=_position_method_argument;
							}
							else {
							}
						}
						if (_state==SUCCESS){
							_token_method_argument.add(_position_method_argument,_token);
						}
						_token=_token_method_argument;
						_recursion_protection_method_argument_2.remove(_position_method_argument);
						if (_state==FAILED&&_recursion_protection_method_argument_3.contains(_position)==false){
							_recursion_protection_method_argument_3.add(_position);
							_state=SUCCESS;
							_position_method_argument=_position;
							_token_method_argument=_token;
							_token=new Tokens.Rule.MethodArgumentToken();
							parse_as_statement();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
									_furthestPosition=_position;
								}
								_position=_position_method_argument;
							}
							else {
							}
							if (_state==SUCCESS){
								_token_method_argument.add(_position_method_argument,_token);
							}
							_token=_token_method_argument;
							_recursion_protection_method_argument_3.remove(_position_method_argument);
							if (_state==FAILED&&_recursion_protection_method_argument_4.contains(_position)==false){
								_recursion_protection_method_argument_4.add(_position);
								_state=SUCCESS;
								_position_method_argument=_position;
								_token_method_argument=_token;
								_token=new Tokens.Rule.MethodArgumentToken();
								parse_body_statement();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
										_furthestPosition=_position;
									}
									_position=_position_method_argument;
								}
								else {
								}
								if (_state==SUCCESS){
									_token_method_argument.add(_position_method_argument,_token);
								}
								_token=_token_method_argument;
								_recursion_protection_method_argument_4.remove(_position_method_argument);
								if (_state==FAILED&&_recursion_protection_method_argument_5.contains(_position)==false){
									_recursion_protection_method_argument_5.add(_position);
									_state=SUCCESS;
									_position_method_argument=_position;
									_token_method_argument=_token;
									_token=new Tokens.Rule.MethodArgumentToken();
									parse_method_body();
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
											_furthestPosition=_position;
										}
										_position=_position_method_argument;
									}
									else {
									}
									if (_state==SUCCESS){
										_token_method_argument.add(_position_method_argument,_token);
									}
									_token=_token_method_argument;
									_recursion_protection_method_argument_5.remove(_position_method_argument);
								}
							}
						}
					}
				}
			}
		}
		public Set<Integer> get_recursionProtectionNAME0(){
			return _recursion_protection_NAME_0;
		}
		public void parse_NAME(){
			int _position_NAME = -1;
			Token.Parsed _token_NAME = null;
			if (_state==SUCCESS&&_recursion_protection_NAME_0.contains(_position)==false){
				_recursion_protection_NAME_0.add(_position);
				_position_NAME=_position;
				_token_NAME=_token;
				_token=new Tokens.Rule.NAMEToken();
				int _position_regex_1 = _position;
				if (_position<_inputLength){
					if (_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F'||_inputArray[_position]=='G'||_inputArray[_position]=='H'||_inputArray[_position]=='I'||_inputArray[_position]=='J'||_inputArray[_position]=='K'||_inputArray[_position]=='L'||_inputArray[_position]=='M'||_inputArray[_position]=='N'||_inputArray[_position]=='O'||_inputArray[_position]=='P'||_inputArray[_position]=='Q'||_inputArray[_position]=='R'||_inputArray[_position]=='S'||_inputArray[_position]=='T'||_inputArray[_position]=='U'||_inputArray[_position]=='V'||_inputArray[_position]=='W'||_inputArray[_position]=='X'||_inputArray[_position]=='Y'||_inputArray[_position]=='Z'||_inputArray[_position]=='_'){
						++_position;
					}
					else {
						_state=FAILED;
					}
				}
				else {
					_state=FAILED;
				}
				while (_position<_inputLength){
					if (_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F'||_inputArray[_position]=='G'||_inputArray[_position]=='H'||_inputArray[_position]=='I'||_inputArray[_position]=='J'||_inputArray[_position]=='K'||_inputArray[_position]=='L'||_inputArray[_position]=='M'||_inputArray[_position]=='N'||_inputArray[_position]=='O'||_inputArray[_position]=='P'||_inputArray[_position]=='Q'||_inputArray[_position]=='R'||_inputArray[_position]=='S'||_inputArray[_position]=='T'||_inputArray[_position]=='U'||_inputArray[_position]=='V'||_inputArray[_position]=='W'||_inputArray[_position]=='X'||_inputArray[_position]=='Y'||_inputArray[_position]=='Z'||_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9'||_inputArray[_position]=='_'){
						++_position;
					}
					else {
						break;
					}
				}
				if (_state==SUCCESS){
					_token.setValue(_input.substring(_position_regex_1,_position));
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z_][a-zA-Z0-9_]*");
						_furthestPosition=_position;
					}
					_position=_position_regex_1;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"NAME(NAME)");
						_furthestPosition=_position;
					}
					_position=_position_NAME;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_NAME.add(_position_NAME,_token);
				}
				_token=_token_NAME;
				_recursion_protection_NAME_0.remove(_position_NAME);
			}
		}
		public void parse__anonymous_38(){
			int _position__anonymous_38 = -1;
			Token.Parsed _token__anonymous_38 = null;
			_position__anonymous_38=_position;
			_token__anonymous_38=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='c'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='l'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if (_inputArray[_position+3]!='s'){
					_state=FAILED;
				}
				if (_inputArray[_position+4]!='s'){
					_state=FAILED;
				}
				if (_inputArray[_position+5]!=' '){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+6;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"class \"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_38)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_38;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_38.addAll(_token);
			}
			_token=_token__anonymous_38;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_38=_position;
				_token__anonymous_38=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+9-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='i'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='n'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='t'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='r'){
						_state=FAILED;
					}
					if (_inputArray[_position+5]!='f'){
						_state=FAILED;
					}
					if (_inputArray[_position+6]!='a'){
						_state=FAILED;
					}
					if (_inputArray[_position+7]!='c'){
						_state=FAILED;
					}
					if (_inputArray[_position+8]!='e'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+9;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"interface\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_38)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_38;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_38.addAll(_token);
				}
				_token=_token__anonymous_38;
			}
		}
		public void parse__anonymous_137(){
			int _position__anonymous_137 = -1;
			Token.Parsed _token__anonymous_137 = null;
			_position__anonymous_137=_position;
			_token__anonymous_137=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_9);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_137)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_137;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_137.addAll(_token);
			}
			_token=_token__anonymous_137;
		}
		public void parse__anonymous_138(){
			int _position__anonymous_138 = -1;
			Token.Parsed _token__anonymous_138 = null;
			_position__anonymous_138=_position;
			_token__anonymous_138=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_139();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_138)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_138;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_138.addAll(_token);
			}
			_token=_token__anonymous_138;
		}
		public void parse__anonymous_139(){
			int _position__anonymous_139 = -1;
			Token.Parsed _token__anonymous_139 = null;
			_position__anonymous_139=_position;
			_token__anonymous_139=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_140();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_139)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_139;
			}
			else {
				parse_name_var_element();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_139)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_139;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_139.addAll(_token);
			}
			_token=_token__anonymous_139;
		}
		public Parser.NameList getClassVariableNamesAdditionsAnonymous133(){
			return class_variable_names_additions__anonymous_133;
		}
		public void parse__anonymous_133(){
			int _position__anonymous_133 = -1;
			Token.Parsed _token__anonymous_133 = null;
			class_variable_names_additions__anonymous_133=class_variable_names_additions;
			class_variable_names_additions=new Parser.NameList.Child(class_variable_names);
			_position__anonymous_133=_position;
			_token__anonymous_133=_token;
			_token=new Token.Parsed("$ANON");
			parse_name_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(_anonymous_133)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_133;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_133.addAll(_token);
			}
			_token=_token__anonymous_133;
			if (_state==FAILED){
				class_variable_names.removeAll(class_variable_names_additions);
				class_variable_names_additions.clear();
				_state=SUCCESS;
				_position__anonymous_133=_position;
				_token__anonymous_133=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if (_state==SUCCESS){
					String _value = _token.getPollLast().getValue();
					if (_value!=null&&class_variable_names.add(_value)){
						class_variable_names_additions.add(_value);
					}
					_token.add(_position,new Tokens.Name.VariableNameToken(_value));
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(_anonymous_133)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_133;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_133.addAll(_token);
				}
				_token=_token__anonymous_133;
				if (_state==FAILED){
					if (class_variable_names_additions!=null){
						class_variable_names.removeAll(class_variable_names_additions);
						class_variable_names_additions.clear();
					}
				}
				else if (_state==SUCCESS){
					if (class_variable_names_additions!=null){
						class_variable_names.addAll(class_variable_names_additions);
						class_variable_names_additions.clear();
					}
				}
				class_variable_names_additions=class_variable_names_additions__anonymous_133;
			}
		}
		public void parse__anonymous_134(){
			int _position__anonymous_134 = -1;
			Token.Parsed _token__anonymous_134 = null;
			_position__anonymous_134=_position;
			_token__anonymous_134=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_135();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_134)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_134;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_134.addAll(_token);
			}
			_token=_token__anonymous_134;
		}
		public Parser.NameList getClassNamesAdditionsAnonymous31(){
			return class_names_additions__anonymous_31;
		}
		public Parser.NameList getClassVariableNamesAdditionsAnonymous31(){
			return class_variable_names_additions__anonymous_31;
		}
		public void parse__anonymous_31(){
			int _position__anonymous_31 = -1;
			Token.Parsed _token__anonymous_31 = null;
			class_names_additions__anonymous_31=class_names_additions;
			class_names_additions=new Parser.NameList.Child(class_names);
			class_variable_names_additions__anonymous_31=class_variable_names_additions;
			class_variable_names_additions=new Parser.NameList.Child(class_variable_names);
			_position__anonymous_31=_position;
			_token__anonymous_31=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='<'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"<\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_31)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_31;
			}
			else {
				parse_NAME();
				if (_state==SUCCESS){
					String _value = _token.getPollLast().getValue();
					if (_value!=null&&class_variable_names.add(_value)){
						class_variable_names_additions.add(_value);
					}
					_token.add(_position,new Tokens.Name.TemplateTypeNameToken(_value));
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_31)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_31;
				}
				else {
					if (_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!='>'){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_position=_position+1;
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \">\"");
							_furthestPosition=_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_31)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_31;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_31.addAll(_token);
			}
			_token=_token__anonymous_31;
			if (_state==FAILED){
				if (class_names_additions!=null){
					class_names.removeAll(class_names_additions);
					class_names_additions.clear();
				}
				if (class_variable_names_additions!=null){
					class_variable_names.removeAll(class_variable_names_additions);
					class_variable_names_additions.clear();
				}
			}
			else if (_state==SUCCESS){
				if (class_names_additions!=null){
					class_names.addAll(class_names_additions);
					class_names_additions.clear();
				}
				if (class_variable_names_additions!=null){
					class_variable_names.addAll(class_variable_names_additions);
					class_variable_names_additions.clear();
				}
			}
			class_names_additions=class_names_additions__anonymous_31;
			class_variable_names_additions=class_variable_names_additions__anonymous_31;
		}
		public void parse__anonymous_135(){
			int _position__anonymous_135 = -1;
			Token.Parsed _token__anonymous_135 = null;
			_position__anonymous_135=_position;
			_token__anonymous_135=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \".\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_135)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_135;
			}
			else {
				parse_type_var();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_135)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_135;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_135.addAll(_token);
			}
			_token=_token__anonymous_135;
		}
		public void parse__anonymous_30(){
			int _position__anonymous_30 = -1;
			Token.Parsed _token__anonymous_30 = null;
			_position__anonymous_30=_position;
			_token__anonymous_30=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_31();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_30)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_30;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_30.addAll(_token);
			}
			_token=_token__anonymous_30;
		}
		public void parse__anonymous_136(){
			int _position__anonymous_136 = -1;
			Token.Parsed _token__anonymous_136 = null;
			_position__anonymous_136=_position;
			_token__anonymous_136=_token;
			_token=new Token.Parsed("$ANON");
			int _state_82 = _state;
			parse__anonymous_137();
			if (_state_82==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_136)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_136;
			}
			else {
				parse_name_var_element();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_136)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_136;
				}
				else {
					int _state_83 = _state;
					int _multiple_index_83 = 0;
					while (_position<_inputLength){
						parse__anonymous_138();
						if (_state==FAILED){
							break;
						}
						else {
							++_multiple_index_83;
						}
					}
					if (_multiple_index_83==0 ){
						_state=FAILED;
					}
					else if (_state_83==SUCCESS&&_multiple_index_83>0 &&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_136)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_136;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_136.addAll(_token);
			}
			_token=_token__anonymous_136;
		}
		public void parse__anonymous_29(){
			int _position__anonymous_29 = -1;
			Token.Parsed _token__anonymous_29 = null;
			_position__anonymous_29=_position;
			_token__anonymous_29=_token;
			_token=new Token.Parsed("$ANON");
			if (_pass==FIRST_PASS){
				parse_NAME();
			}
			else if (_pass==SECOND_PASS){
				_list_name_result=variable_names.get(_position,_inputLength,_inputArray);
				if (_list_name_result!=null){
					if (_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
				}
				else {
					_state=FAILED;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_29)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_29;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_29.addAll(_token);
			}
			_token=_token__anonymous_29;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_29=_position;
				_token__anonymous_29=_token;
				_token=new Token.Parsed("$ANON");
				if (_pass==FIRST_PASS){
					parse_NAME();
				}
				else if (_pass==SECOND_PASS){
					_list_name_result=class_variable_names.get(_position,_inputLength,_inputArray);
					if (_list_name_result!=null){
						if (_position+_list_name_result.length()<_inputLength){
							int _next_char = _inputArray[_position+_list_name_result.length()];
							if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
							_position+=_list_name_result.length();
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
							_furthestPosition=_position;
						}
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_29)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_29;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_29.addAll(_token);
				}
				_token=_token__anonymous_29;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_29=_position;
					_token__anonymous_29=_token;
					_token=new Token.Parsed("$ANON");
					parse_NAME();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_29)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_29;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_29.addAll(_token);
					}
					_token=_token__anonymous_29;
				}
			}
		}
		public Parser.NameList getClassVariableNamesMethodArguments(){
			return class_variable_names_method_arguments;
		}
		public Parser.NameList getVariableNamesMethodArguments(){
			return variable_names_method_arguments;
		}
		public Set<Integer> get_recursionProtectionMethodArguments0(){
			return _recursion_protection_method_arguments_0;
		}
		public void parse_method_arguments(){
			int _position_method_arguments = -1;
			Token.Parsed _token_method_arguments = null;
			int _length_method_arguments_brace = _inputLength;
			if (brace_3.containsKey(_position)){
				class_variable_names_method_arguments=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_method_arguments=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_3.get(_position);
				int _position_method_arguments_brace = _position;
				_position+=1;
				while (_position<_length_method_arguments_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_method_arguments_0.contains(_position)==false){
					_recursion_protection_method_arguments_0.add(_position);
					_position_method_arguments=_position;
					_token_method_arguments=_token;
					_token=new Tokens.Rule.MethodArgumentsToken();
					int _state_14 = _state;
					parse__anonymous_3();
					if (_state_14==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(method_arguments)");
							_furthestPosition=_position;
						}
						_position=_position_method_arguments;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_method_arguments.add(_position_method_arguments,_token);
					}
					_token=_token_method_arguments;
					_recursion_protection_method_arguments_0.remove(_position_method_arguments);
				}
				if (_state==SUCCESS&&brace_3.get(_position_method_arguments_brace)==_position){
					_position+=1;
					while (_position<_length_method_arguments_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_method_arguments_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_method_arguments_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_method_arguments_brace;
				class_variable_names=class_variable_names_method_arguments;
				variable_names=variable_names_method_arguments;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(method_arguments)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_22(){
			int _position__anonymous_22 = -1;
			Token.Parsed _token__anonymous_22 = null;
			_position__anonymous_22=_position;
			_token__anonymous_22=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='>'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \">\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(_anonymous_22)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_22;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_22.addAll(_token);
			}
			_token=_token__anonymous_22;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_22=_position;
				_token__anonymous_22=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='i'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='n'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='n'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='r'){
						_state=FAILED;
					}
					if (_inputArray[_position+5]!=' '){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+6;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"inner \"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(_anonymous_22)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_22;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_22.addAll(_token);
				}
				_token=_token__anonymous_22;
			}
		}
		public void parse__anonymous_173(){
			int _position__anonymous_173 = -1;
			Token.Parsed _token__anonymous_173 = null;
			_position__anonymous_173=_position;
			_token__anonymous_173=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.ISTYPENAMEToken.plain_18);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"$\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_173)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_173;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_173.addAll(_token);
			}
			_token=_token__anonymous_173;
		}
		public void parse__anonymous_21(){
			int _position__anonymous_21 = -1;
			Token.Parsed _token__anonymous_21 = null;
			_position__anonymous_21=_position;
			_token__anonymous_21=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \".\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_21)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_21;
			}
			else {
				parse_packageName();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_21)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_21;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_21.addAll(_token);
			}
			_token=_token__anonymous_21;
		}
		public Set<Integer> get_recursionProtectionNameVar0(){
			return _recursion_protection_name_var_0;
		}
		public Set<Integer> get_recursionProtectionNameVar1(){
			return _recursion_protection_name_var_1;
		}
		public Set<Integer> get_recursionProtectionNameVar2(){
			return _recursion_protection_name_var_2;
		}
		public void parse_name_var(){
			int _position_name_var = -1;
			Token.Parsed _token_name_var = null;
			if (_state==SUCCESS&&_recursion_protection_name_var_0.contains(_position)==false){
				_recursion_protection_name_var_0.add(_position);
				_position_name_var=_position;
				_token_name_var=_token;
				_token=new Tokens.Rule.NameVarToken();
				parse__anonymous_136();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
						_furthestPosition=_position;
					}
					_position=_position_name_var;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_name_var.add(_position_name_var,_token);
				}
				_token=_token_name_var;
				_recursion_protection_name_var_0.remove(_position_name_var);
				if (_state==FAILED&&_recursion_protection_name_var_1.contains(_position)==false){
					_recursion_protection_name_var_1.add(_position);
					_state=SUCCESS;
					_position_name_var=_position;
					_token_name_var=_token;
					_token=new Tokens.Rule.NameVarToken();
					parse__anonymous_141();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
							_furthestPosition=_position;
						}
						_position=_position_name_var;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_name_var.add(_position_name_var,_token);
					}
					_token=_token_name_var;
					_recursion_protection_name_var_1.remove(_position_name_var);
					if (_state==FAILED&&_recursion_protection_name_var_2.contains(_position)==false){
						_recursion_protection_name_var_2.add(_position);
						_state=SUCCESS;
						_position_name_var=_position;
						_token_name_var=_token;
						_token=new Tokens.Rule.NameVarToken();
						parse_name_var_element();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
								_furthestPosition=_position;
							}
							_position=_position_name_var;
						}
						else {
						}
						if (_state==SUCCESS){
							_token_name_var.add(_position_name_var,_token);
						}
						_token=_token_name_var;
						_recursion_protection_name_var_2.remove(_position_name_var);
					}
				}
			}
		}
		public void parse__anonymous_174(){
			int _position__anonymous_174 = -1;
			Token.Parsed _token__anonymous_174 = null;
			_position__anonymous_174=_position;
			_token__anonymous_174=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_19);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_174)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_174;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_174.addAll(_token);
			}
			_token=_token__anonymous_174;
		}
		public void parse__anonymous_24(){
			int _position__anonymous_24 = -1;
			Token.Parsed _token__anonymous_24 = null;
			_position__anonymous_24=_position;
			_token__anonymous_24=_token;
			_token=new Token.Parsed("$ANON");
			parse_weak();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_24)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_24;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_24.addAll(_token);
			}
			_token=_token__anonymous_24;
		}
		public void parse__anonymous_175(){
			int _position__anonymous_175 = -1;
			Token.Parsed _token__anonymous_175 = null;
			_position__anonymous_175=_position;
			_token__anonymous_175=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_176();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_175)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_175;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_175.addAll(_token);
			}
			_token=_token__anonymous_175;
		}
		public void parse__anonymous_23(){
			int _position__anonymous_23 = -1;
			Token.Parsed _token__anonymous_23 = null;
			_position__anonymous_23=_position;
			_token__anonymous_23=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_23)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_23;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_23.addAll(_token);
			}
			_token=_token__anonymous_23;
		}
		public void parse__anonymous_176(){
			int _position__anonymous_176 = -1;
			Token.Parsed _token__anonymous_176 = null;
			_position__anonymous_176=_position;
			_token__anonymous_176=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='+'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"+\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_176)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_176;
			}
			else {
				parse_type_var_element();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_176)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_176;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_176.addAll(_token);
			}
			_token=_token__anonymous_176;
		}
		public void parse__anonymous_26(){
			int _position__anonymous_26 = -1;
			Token.Parsed _token__anonymous_26 = null;
			_position__anonymous_26=_position;
			_token__anonymous_26=_token;
			_token=new Token.Parsed("$ANON");
			parse_packageName();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_26)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_26;
			}
			else {
				int _state_25 = _state;
				while (_position<_inputLength){
					parse__anonymous_27();
					if (_state==FAILED){
						break;
					}
				}
				if (_state_25==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_26)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_26;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_26.addAll(_token);
			}
			_token=_token__anonymous_26;
		}
		public void parse__anonymous_25(){
			int _position__anonymous_25 = -1;
			Token.Parsed _token__anonymous_25 = null;
			_position__anonymous_25=_position;
			_token__anonymous_25=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='c'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='l'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if (_inputArray[_position+3]!='s'){
					_state=FAILED;
				}
				if (_inputArray[_position+4]!='s'){
					_state=FAILED;
				}
				if (_inputArray[_position+5]!=' '){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+6;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"class \"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_25)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_25;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_25.addAll(_token);
			}
			_token=_token__anonymous_25;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_25=_position;
				_token__anonymous_25=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+10-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='i'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='n'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='t'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='r'){
						_state=FAILED;
					}
					if (_inputArray[_position+5]!='f'){
						_state=FAILED;
					}
					if (_inputArray[_position+6]!='a'){
						_state=FAILED;
					}
					if (_inputArray[_position+7]!='c'){
						_state=FAILED;
					}
					if (_inputArray[_position+8]!='e'){
						_state=FAILED;
					}
					if (_inputArray[_position+9]!=' '){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+10;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"interface \"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_25)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_25;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_25.addAll(_token);
				}
				_token=_token__anonymous_25;
			}
		}
		public void parse__anonymous_170(){
			int _position__anonymous_170 = -1;
			Token.Parsed _token__anonymous_170 = null;
			_position__anonymous_170=_position;
			_token__anonymous_170=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='-'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+2;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"->\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_170)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_170;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_170.addAll(_token);
			}
			_token=_token__anonymous_170;
		}
		public void parse__anonymous_28(){
			int _position__anonymous_28 = -1;
			Token.Parsed _token__anonymous_28 = null;
			_position__anonymous_28=_position;
			_token__anonymous_28=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \".\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_28)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_28;
			}
			else {
				parse_packageName();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_28)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_28;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_28.addAll(_token);
			}
			_token=_token__anonymous_28;
		}
		public void parse__anonymous_171(){
			int _position__anonymous_171 = -1;
			Token.Parsed _token__anonymous_171 = null;
			_position__anonymous_171=_position;
			_token__anonymous_171=_token;
			_token=new Token.Parsed("$ANON");
			parse_name_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_171)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_171;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_171.addAll(_token);
			}
			_token=_token__anonymous_171;
		}
		public void parse__anonymous_27(){
			int _position__anonymous_27 = -1;
			Token.Parsed _token__anonymous_27 = null;
			_position__anonymous_27=_position;
			_token__anonymous_27=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_28();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_27)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_27;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_27.addAll(_token);
			}
			_token=_token__anonymous_27;
		}
		public void parse__anonymous_172(){
			int _position__anonymous_172 = -1;
			Token.Parsed _token__anonymous_172 = null;
			_position__anonymous_172=_position;
			_token__anonymous_172=_token;
			_token=new Token.Parsed("$ANON");
			int _state_97 = _state;
			parse__anonymous_173();
			if (_state_97==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_172)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_172;
			}
			else {
				int _state_98 = _state;
				parse__anonymous_174();
				if (_state_98==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_172)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_172;
				}
				else {
					parse_type_var_element();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_172)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_172;
					}
					else {
						int _state_99 = _state;
						int _multiple_index_99 = 0;
						while (_position<_inputLength){
							parse__anonymous_175();
							if (_state==FAILED){
								break;
							}
							else {
								++_multiple_index_99;
							}
						}
						if (_multiple_index_99==0 ){
							_state=FAILED;
						}
						else if (_state_99==SUCCESS&&_multiple_index_99>0 &&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_172)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_172;
						}
						else {
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_172.addAll(_token);
			}
			_token=_token__anonymous_172;
		}
		public void parse__anonymous_166(){
			int _position__anonymous_166 = -1;
			Token.Parsed _token__anonymous_166 = null;
			_position__anonymous_166=_position;
			_token__anonymous_166=_token;
			_token=new Token.Parsed("$ANON");
			int _state_94 = _state;
			parse__anonymous_167();
			if (_state_94==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_166)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_166;
			}
			else {
				int _state_95 = _state;
				parse__anonymous_168();
				if (_state_95==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_166)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_166;
				}
				else {
					parse_type_var_element();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_166)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_166;
					}
					else {
						parse__anonymous_169();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_166)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_166;
						}
						else {
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_166.addAll(_token);
			}
			_token=_token__anonymous_166;
		}
		public void parse__anonymous_167(){
			int _position__anonymous_167 = -1;
			Token.Parsed _token__anonymous_167 = null;
			_position__anonymous_167=_position;
			_token__anonymous_167=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.ISTYPENAMEToken.plain_16);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"$\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_167)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_167;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_167.addAll(_token);
			}
			_token=_token__anonymous_167;
		}
		public void parse__anonymous_20(){
			int _position__anonymous_20 = -1;
			Token.Parsed _token__anonymous_20 = null;
			_position__anonymous_20=_position;
			_token__anonymous_20=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_21();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_20)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_20;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_20.addAll(_token);
			}
			_token=_token__anonymous_20;
		}
		public void parse__anonymous_168(){
			int _position__anonymous_168 = -1;
			Token.Parsed _token__anonymous_168 = null;
			_position__anonymous_168=_position;
			_token__anonymous_168=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_17);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_168)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_168;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_168.addAll(_token);
			}
			_token=_token__anonymous_168;
		}
		public void parse__anonymous_169(){
			int _position__anonymous_169 = -1;
			Token.Parsed _token__anonymous_169 = null;
			_position__anonymous_169=_position;
			_token__anonymous_169=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_170();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_169)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_169;
			}
			else {
				if (_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+1;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"*\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_169)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_169;
				}
				else {
					int _state_96 = _state;
					parse__anonymous_171();
					if (_state_96==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_169)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_169;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_169.addAll(_token);
			}
			_token=_token__anonymous_169;
		}
		public Set<Integer> get_recursionProtectionMethodParameters0(){
			return _recursion_protection_method_parameters_0;
		}
		public void parse_method_parameters(){
			int _position_method_parameters = -1;
			Token.Parsed _token_method_parameters = null;
			if (_state==SUCCESS&&_recursion_protection_method_parameters_0.contains(_position)==false){
				_recursion_protection_method_parameters_0.add(_position);
				_position_method_parameters=_position;
				_token_method_parameters=_token;
				_token=new Tokens.Rule.MethodParametersToken();
				if (_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='('){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+1;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"(\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
						_furthestPosition=_position;
					}
					_position=_position_method_parameters;
				}
				else {
					int _state_68 = _state;
					parse__anonymous_112();
					if (_state_68==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
							_furthestPosition=_position;
						}
						_position=_position_method_parameters;
					}
					else {
						int _state_69 = _state;
						while (_position<_inputLength){
							parse__anonymous_113();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_69==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
								_furthestPosition=_position;
							}
							_position=_position_method_parameters;
						}
						else {
							if (_position+1-1 >=_inputLength){
								_state=FAILED;
							}
							else {
								if (_inputArray[_position+0]!=')'){
									_state=FAILED;
								}
							}
							if (_state==SUCCESS){
								_position=_position+1;
								while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
									++_position;
								}
							}
							else if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \")\"");
									_furthestPosition=_position;
								}
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
									_furthestPosition=_position;
								}
								_position=_position_method_parameters;
							}
							else {
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_method_parameters.add(_position_method_parameters,_token);
				}
				_token=_token_method_parameters;
				_recursion_protection_method_parameters_0.remove(_position_method_parameters);
			}
		}
		public void parse__anonymous_19(){
			int _position__anonymous_19 = -1;
			Token.Parsed _token__anonymous_19 = null;
			_position__anonymous_19=_position;
			_token__anonymous_19=_token;
			_token=new Token.Parsed("$ANON");
			parse_packageName();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_19)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_19;
			}
			else {
				int _state_21 = _state;
				while (_position<_inputLength){
					parse__anonymous_20();
					if (_state==FAILED){
						break;
					}
				}
				if (_state_21==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_19)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_19;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_19.addAll(_token);
			}
			_token=_token__anonymous_19;
		}
		public void parse__anonymous_18(){
			int _position__anonymous_18 = -1;
			Token.Parsed _token__anonymous_18 = null;
			_position__anonymous_18=_position;
			_token__anonymous_18=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_19();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_18)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_18;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_18.addAll(_token);
			}
			_token=_token__anonymous_18;
		}
		public void parse__anonymous_11(){
			int _position__anonymous_11 = -1;
			Token.Parsed _token__anonymous_11 = null;
			_position__anonymous_11=_position;
			_token__anonymous_11=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_12();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_11)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_11;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_11.addAll(_token);
			}
			_token=_token__anonymous_11;
		}
		public void parse__anonymous_162(){
			int _position__anonymous_162 = -1;
			Token.Parsed _token__anonymous_162 = null;
			_position__anonymous_162=_position;
			_token__anonymous_162=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_163();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_162)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_162;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_162.addAll(_token);
			}
			_token=_token__anonymous_162;
		}
		public void parse__anonymous_10(){
			int _position__anonymous_10 = -1;
			Token.Parsed _token__anonymous_10 = null;
			_position__anonymous_10=_position;
			_token__anonymous_10=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_argument();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_10)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_10;
			}
			else {
				int _state_16 = _state;
				while (_position<_inputLength){
					parse__anonymous_11();
					if (_state==FAILED){
						break;
					}
				}
				if (_state_16==SUCCESS&&_state==SUCCESS)
				if (_state_16==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_10)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_10;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_10.addAll(_token);
			}
			_token=_token__anonymous_10;
		}
		public void parse__anonymous_163(){
			int _position__anonymous_163 = -1;
			Token.Parsed _token__anonymous_163 = null;
			_position__anonymous_163=_position;
			_token__anonymous_163=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_164();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_163)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_163;
			}
			else {
				if (_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+1;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"*\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_163)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_163;
				}
				else {
					int _state_92 = _state;
					parse__anonymous_165();
					if (_state_92==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_163)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_163;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_163.addAll(_token);
			}
			_token=_token__anonymous_163;
		}
		public void parse__anonymous_13(){
			int _position__anonymous_13 = -1;
			Token.Parsed _token__anonymous_13 = null;
			_position__anonymous_13=_position;
			_token__anonymous_13=_token;
			_token=new Token.Parsed("$ANON");
			int _state_18 = _state;
			int _multiple_index_18 = 0;
			while (_position<_inputLength){
				parse__anonymous_14();
				if (_state==FAILED){
					break;
				}
				else {
					++_multiple_index_18;
				}
			}
			if (_multiple_index_18==0 ){
				_state=FAILED;
			}
			else if (_state_18==SUCCESS&&_multiple_index_18>0 &&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(_anonymous_13)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_13;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_13.addAll(_token);
			}
			_token=_token__anonymous_13;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_13=_position;
				_token__anonymous_13=_token;
				_token=new Token.Parsed("$ANON");
				parse_body_statement();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(_anonymous_13)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_13;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_13.addAll(_token);
				}
				_token=_token__anonymous_13;
			}
		}
		public void parse__anonymous_164(){
			int _position__anonymous_164 = -1;
			Token.Parsed _token__anonymous_164 = null;
			_position__anonymous_164=_position;
			_token__anonymous_164=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='-'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+2;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"->\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_164)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_164;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_164.addAll(_token);
			}
			_token=_token__anonymous_164;
		}
		public void parse__anonymous_12(){
			int _position__anonymous_12 = -1;
			Token.Parsed _token__anonymous_12 = null;
			_position__anonymous_12=_position;
			_token__anonymous_12=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!=','){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \",\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_12)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_12;
			}
			else {
				parse_method_argument();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_12)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_12;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_12.addAll(_token);
			}
			_token=_token__anonymous_12;
		}
		public void parse__anonymous_165(){
			int _position__anonymous_165 = -1;
			Token.Parsed _token__anonymous_165 = null;
			_position__anonymous_165=_position;
			_token__anonymous_165=_token;
			_token=new Token.Parsed("$ANON");
			parse_name_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_165)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_165;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_165.addAll(_token);
			}
			_token=_token__anonymous_165;
		}
		public void parse__anonymous_15(){
			int _position__anonymous_15 = -1;
			Token.Parsed _token__anonymous_15 = null;
			_position__anonymous_15=_position;
			_token__anonymous_15=_token;
			_token=new Token.Parsed("$ANON");
			parse_base_element();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(_anonymous_15)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_15;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_15.addAll(_token);
			}
			_token=_token__anonymous_15;
		}
		public void parse__anonymous_14(){
			int _position__anonymous_14 = -1;
			Token.Parsed _token__anonymous_14 = null;
			_position__anonymous_14=_position;
			_token__anonymous_14=_token;
			_token=new Token.Parsed("$ANON");
			parse_body_element();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(_anonymous_14)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_14;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_14.addAll(_token);
			}
			_token=_token__anonymous_14;
		}
		public void parse__anonymous_17(){
			int _position__anonymous_17 = -1;
			Token.Parsed _token__anonymous_17 = null;
			_position__anonymous_17=_position;
			_token__anonymous_17=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!=':'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \":\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_17)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_17;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_17.addAll(_token);
			}
			_token=_token__anonymous_17;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_17=_position;
				_token__anonymous_17=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+4-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='f'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='r'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='o'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='m'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+4;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"from\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_17)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_17;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_17.addAll(_token);
				}
				_token=_token__anonymous_17;
			}
		}
		public void parse__anonymous_160(){
			int _position__anonymous_160 = -1;
			Token.Parsed _token__anonymous_160 = null;
			_position__anonymous_160=_position;
			_token__anonymous_160=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_161();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_160)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_160;
			}
			else {
				parse_type_var_element();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_160)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_160;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_160.addAll(_token);
			}
			_token=_token__anonymous_160;
		}
		public void parse__anonymous_16(){
			int _position__anonymous_16 = -1;
			Token.Parsed _token__anonymous_16 = null;
			_position__anonymous_16=_position;
			_token__anonymous_16=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='<'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"<\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_16)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_16;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_16.addAll(_token);
			}
			_token=_token__anonymous_16;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_16=_position;
				_token__anonymous_16=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='h'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='i'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='d'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='d'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='e'){
						_state=FAILED;
					}
					if (_inputArray[_position+5]!='n'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+6;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"hidden\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_16)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_16;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_16.addAll(_token);
				}
				_token=_token__anonymous_16;
			}
		}
		public void parse__anonymous_161(){
			int _position__anonymous_161 = -1;
			Token.Parsed _token__anonymous_161 = null;
			_position__anonymous_161=_position;
			_token__anonymous_161=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='-'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+2;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"->\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_161)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_161;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_161.addAll(_token);
			}
			_token=_token__anonymous_161;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_161=_position;
				_token__anonymous_161=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+2-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='\\'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='>'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+2;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"\\>\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_161)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_161;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_161.addAll(_token);
				}
				_token=_token__anonymous_161;
			}
		}
		public void parse__anonymous_159(){
			int _position__anonymous_159 = -1;
			Token.Parsed _token__anonymous_159 = null;
			_position__anonymous_159=_position;
			_token__anonymous_159=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_160();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_159)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_159;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_159.addAll(_token);
			}
			_token=_token__anonymous_159;
		}
		public void parse__anonymous_155(){
			int _position__anonymous_155 = -1;
			Token.Parsed _token__anonymous_155 = null;
			_position__anonymous_155=_position;
			_token__anonymous_155=_token;
			_token=new Token.Parsed("$ANON");
			parse_NUMBER();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_155)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_155;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_155.addAll(_token);
			}
			_token=_token__anonymous_155;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_155=_position;
				_token__anonymous_155=_token;
				_token=new Token.Parsed("$ANON");
				if (_position+5-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='s'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='u'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='p'){
						_state=FAILED;
					}
					if (_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
					if (_inputArray[_position+4]!='r'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+5;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"super\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_155)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_155;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_155.addAll(_token);
				}
				_token=_token__anonymous_155;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_155=_position;
					_token__anonymous_155=_token;
					_token=new Token.Parsed("$ANON");
					if (_position+4-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!='t'){
							_state=FAILED;
						}
						if (_inputArray[_position+1]!='h'){
							_state=FAILED;
						}
						if (_inputArray[_position+2]!='i'){
							_state=FAILED;
						}
						if (_inputArray[_position+3]!='s'){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_position=_position+4;
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"this\"");
							_furthestPosition=_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_155)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_155;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_155.addAll(_token);
					}
					_token=_token__anonymous_155;
					if (_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_155=_position;
						_token__anonymous_155=_token;
						_token=new Token.Parsed("$ANON");
						if (_position+4-1 >=_inputLength){
							_state=FAILED;
						}
						else {
							if (_inputArray[_position+0]!='n'){
								_state=FAILED;
							}
							if (_inputArray[_position+1]!='u'){
								_state=FAILED;
							}
							if (_inputArray[_position+2]!='l'){
								_state=FAILED;
							}
							if (_inputArray[_position+3]!='l'){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_position=_position+4;
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
						else if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"null\"");
								_furthestPosition=_position;
							}
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_155)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_155;
						}
						else {
						}
						if (_state==SUCCESS){
							_token__anonymous_155.addAll(_token);
						}
						_token=_token__anonymous_155;
						if (_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_155=_position;
							_token__anonymous_155=_token;
							_token=new Token.Parsed("$ANON");
							if (_position+4-1 >=_inputLength){
								_state=FAILED;
							}
							else {
								if (_inputArray[_position+0]!='t'){
									_state=FAILED;
								}
								if (_inputArray[_position+1]!='r'){
									_state=FAILED;
								}
								if (_inputArray[_position+2]!='u'){
									_state=FAILED;
								}
								if (_inputArray[_position+3]!='e'){
									_state=FAILED;
								}
							}
							if (_state==SUCCESS){
								_position=_position+4;
								while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
									++_position;
								}
							}
							else if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"true\"");
									_furthestPosition=_position;
								}
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_155)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_155;
							}
							else {
							}
							if (_state==SUCCESS){
								_token__anonymous_155.addAll(_token);
							}
							_token=_token__anonymous_155;
							if (_state==FAILED){
								_state=SUCCESS;
								_position__anonymous_155=_position;
								_token__anonymous_155=_token;
								_token=new Token.Parsed("$ANON");
								if (_position+5-1 >=_inputLength){
									_state=FAILED;
								}
								else {
									if (_inputArray[_position+0]!='f'){
										_state=FAILED;
									}
									if (_inputArray[_position+1]!='a'){
										_state=FAILED;
									}
									if (_inputArray[_position+2]!='l'){
										_state=FAILED;
									}
									if (_inputArray[_position+3]!='s'){
										_state=FAILED;
									}
									if (_inputArray[_position+4]!='e'){
										_state=FAILED;
									}
								}
								if (_state==SUCCESS){
									_position=_position+5;
									while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
										++_position;
									}
								}
								else if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"false\"");
										_furthestPosition=_position;
									}
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_155)");
										_furthestPosition=_position;
									}
									_position=_position__anonymous_155;
								}
								else {
								}
								if (_state==SUCCESS){
									_token__anonymous_155.addAll(_token);
								}
								_token=_token__anonymous_155;
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_156(){
			int _position__anonymous_156 = -1;
			Token.Parsed _token__anonymous_156 = null;
			_position__anonymous_156=_position;
			_token__anonymous_156=_token;
			_token=new Token.Parsed("$ANON");
			int _state_89 = _state;
			parse__anonymous_157();
			if (_state_89==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_156)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_156;
			}
			else {
				int _state_90 = _state;
				parse__anonymous_158();
				if (_state_90==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_156)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_156;
				}
				else {
					parse_type_var_element();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_156)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_156;
					}
					else {
						int _state_91 = _state;
						int _multiple_index_91 = 0;
						while (_position<_inputLength){
							parse__anonymous_159();
							if (_state==FAILED){
								break;
							}
							else {
								++_multiple_index_91;
							}
						}
						if (_multiple_index_91==0 ){
							_state=FAILED;
						}
						else if (_state_91==SUCCESS&&_multiple_index_91>0 &&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_156)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_156;
						}
						else {
							int _state_93 = _state;
							parse__anonymous_162();
							if (_state_93==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_156)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_156;
							}
							else {
							}
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_156.addAll(_token);
			}
			_token=_token__anonymous_156;
		}
		public void parse__anonymous_157(){
			int _position__anonymous_157 = -1;
			Token.Parsed _token__anonymous_157 = null;
			_position__anonymous_157=_position;
			_token__anonymous_157=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.ISTYPENAMEToken.plain_14);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"$\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_157)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_157;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_157.addAll(_token);
			}
			_token=_token__anonymous_157;
		}
		public Parser.NameList getClassVariableNamesMethodBody(){
			return class_variable_names_method_body;
		}
		public Parser.NameList getVariableNamesMethodBody(){
			return variable_names_method_body;
		}
		public Set<Integer> get_recursionProtectionMethodBody0(){
			return _recursion_protection_method_body_0;
		}
		public void parse_method_body(){
			int _position_method_body = -1;
			Token.Parsed _token_method_body = null;
			int _length_method_body_brace = _inputLength;
			if (brace_2.containsKey(_position)){
				class_variable_names_method_body=class_variable_names;
				if (_pass==SECOND_PASS){
					class_variable_names=new Parser.NameList.Child(class_variable_names);
				}
				variable_names_method_body=variable_names;
				if (_pass==SECOND_PASS){
					variable_names=new Parser.NameList.Child(variable_names);
				}
				if (_pass==SECOND_PASS){
					_inputLength=brace_2.get(_position);
					int _position_method_body_brace = _position;
					_position+=1;
					while (_position<_length_method_body_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
					if (_state==SUCCESS&&_recursion_protection_method_body_0.contains(_position)==false){
						_recursion_protection_method_body_0.add(_position);
						_position_method_body=_position;
						_token_method_body=_token;
						_token=new Tokens.Rule.MethodBodyToken();
						int _state_12 = _state;
						while (_position<_inputLength){
							parse__anonymous_2();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_12==SUCCESS&&_state==SUCCESS)
						if (_state_12==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_body(method_body)");
								_furthestPosition=_position;
							}
							_position=_position_method_body;
						}
						else {
						}
						if (_state==SUCCESS){
							_token_method_body.add(_position_method_body,_token);
						}
						_token=_token_method_body;
						_recursion_protection_method_body_0.remove(_position_method_body);
					}
					if (_state==SUCCESS&&brace_2.get(_position_method_body_brace)==_position){
						_position+=1;
						while (_position<_length_method_body_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else {
						_state=SUCCESS;
						_result_acceptor.add(_result);
						_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
						_position=brace_2.get(_position_method_body_brace)+1;
						_succeedOnEnd=false;
						while (_position<_length_method_body_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					_inputLength=_length_method_body_brace;
					class_variable_names=class_variable_names_method_body;
					variable_names=variable_names_method_body;
				}
				else {
					_position=brace_2.get(_position)+1;
					while (_position<_length_method_body_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_body(method_body)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_158(){
			int _position__anonymous_158 = -1;
			Token.Parsed _token__anonymous_158 = null;
			_position__anonymous_158=_position;
			_token__anonymous_158=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_15);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_158)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_158;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_158.addAll(_token);
			}
			_token=_token__anonymous_158;
		}
		public Set<Integer> get_recursionProtectionBodyElement0(){
			return _recursion_protection_body_element_0;
		}
		public Set<Integer> get_recursionProtectionBodyElement1(){
			return _recursion_protection_body_element_1;
		}
		public Set<Integer> get_recursionProtectionBodyElement2(){
			return _recursion_protection_body_element_2;
		}
		public Set<Integer> get_recursionProtectionBodyElement3(){
			return _recursion_protection_body_element_3;
		}
		public Set<Integer> get_recursionProtectionBodyElement4(){
			return _recursion_protection_body_element_4;
		}
		public Set<Integer> get_recursionProtectionBodyElement5(){
			return _recursion_protection_body_element_5;
		}
		public Set<Integer> get_recursionProtectionBodyElement6(){
			return _recursion_protection_body_element_6;
		}
		public Set<Integer> get_recursionProtectionBodyElement7(){
			return _recursion_protection_body_element_7;
		}
		public Set<Integer> get_recursionProtectionBodyElement8(){
			return _recursion_protection_body_element_8;
		}
		public void parse_body_element(){
			int _position_body_element = -1;
			Token.Parsed _token_body_element = null;
			if (_state==SUCCESS&&_recursion_protection_body_element_0.contains(_position)==false){
				_recursion_protection_body_element_0.add(_position);
				_position_body_element=_position;
				_token_body_element=_token;
				_token=new Tokens.Rule.BodyElementToken();
				parse_comments();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
						_furthestPosition=_position;
					}
					_position=_position_body_element;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_body_element.add(_position_body_element,_token);
				}
				_token=_token_body_element;
				_recursion_protection_body_element_0.remove(_position_body_element);
				if (_state==FAILED&&_recursion_protection_body_element_1.contains(_position)==false){
					_recursion_protection_body_element_1.add(_position);
					_state=SUCCESS;
					_position_body_element=_position;
					_token_body_element=_token;
					_token=new Tokens.Rule.BodyElementToken();
					int _state_34 = _state;
					parse__anonymous_46();
					if (_state_34==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
							_furthestPosition=_position;
						}
						_position=_position_body_element;
					}
					else {
						parse__anonymous_47();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
								_furthestPosition=_position;
							}
							_position=_position_body_element;
						}
						else {
						}
					}
					if (_state==SUCCESS){
						_token_body_element.add(_position_body_element,_token);
					}
					_token=_token_body_element;
					_recursion_protection_body_element_1.remove(_position_body_element);
					if (_state==FAILED&&_recursion_protection_body_element_2.contains(_position)==false){
						_recursion_protection_body_element_2.add(_position);
						_state=SUCCESS;
						_position_body_element=_position;
						_token_body_element=_token;
						_token=new Tokens.Rule.BodyElementToken();
						int _state_35 = _state;
						parse__anonymous_49();
						if (_state_35==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
								_furthestPosition=_position;
							}
							_position=_position_body_element;
						}
						else {
							parse__anonymous_50();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
									_furthestPosition=_position;
								}
								_position=_position_body_element;
							}
							else {
							}
						}
						if (_state==SUCCESS){
							_token_body_element.add(_position_body_element,_token);
						}
						_token=_token_body_element;
						_recursion_protection_body_element_2.remove(_position_body_element);
						if (_state==FAILED&&_recursion_protection_body_element_3.contains(_position)==false){
							_recursion_protection_body_element_3.add(_position);
							_state=SUCCESS;
							_position_body_element=_position;
							_token_body_element=_token;
							_token=new Tokens.Rule.BodyElementToken();
							parse_class_declaration();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
									_furthestPosition=_position;
								}
								_position=_position_body_element;
							}
							else {
							}
							if (_state==SUCCESS){
								_token_body_element.add(_position_body_element,_token);
							}
							_token=_token_body_element;
							_recursion_protection_body_element_3.remove(_position_body_element);
							if (_state==FAILED&&_recursion_protection_body_element_4.contains(_position)==false){
								_recursion_protection_body_element_4.add(_position);
								_state=SUCCESS;
								_position_body_element=_position;
								_token_body_element=_token;
								_token=new Tokens.Rule.BodyElementToken();
								parse__anonymous_51();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
										_furthestPosition=_position;
									}
									_position=_position_body_element;
								}
								else {
								}
								if (_state==SUCCESS){
									_token_body_element.add(_position_body_element,_token);
								}
								_token=_token_body_element;
								_recursion_protection_body_element_4.remove(_position_body_element);
								if (_state==FAILED&&_recursion_protection_body_element_5.contains(_position)==false){
									_recursion_protection_body_element_5.add(_position);
									_state=SUCCESS;
									_position_body_element=_position;
									_token_body_element=_token;
									_token=new Tokens.Rule.BodyElementToken();
									parse__anonymous_52();
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
											_furthestPosition=_position;
										}
										_position=_position_body_element;
									}
									else {
									}
									if (_state==SUCCESS){
										_token_body_element.add(_position_body_element,_token);
									}
									_token=_token_body_element;
									_recursion_protection_body_element_5.remove(_position_body_element);
									if (_state==FAILED&&_recursion_protection_body_element_6.contains(_position)==false){
										_recursion_protection_body_element_6.add(_position);
										_state=SUCCESS;
										_position_body_element=_position;
										_token_body_element=_token;
										_token=new Tokens.Rule.BodyElementToken();
										parse_body_manipulate();
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
												_furthestPosition=_position;
											}
											_position=_position_body_element;
										}
										else {
										}
										if (_state==SUCCESS){
											_token_body_element.add(_position_body_element,_token);
										}
										_token=_token_body_element;
										_recursion_protection_body_element_6.remove(_position_body_element);
										if (_state==FAILED&&_recursion_protection_body_element_7.contains(_position)==false){
											_recursion_protection_body_element_7.add(_position);
											_state=SUCCESS;
											_position_body_element=_position;
											_token_body_element=_token;
											_token=new Tokens.Rule.BodyElementToken();
											parse_body_conditional();
											if (_state==FAILED){
												if (_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
													_furthestPosition=_position;
												}
												_position=_position_body_element;
											}
											else {
											}
											if (_state==SUCCESS){
												_token_body_element.add(_position_body_element,_token);
											}
											_token=_token_body_element;
											_recursion_protection_body_element_7.remove(_position_body_element);
											if (_state==FAILED&&_recursion_protection_body_element_8.contains(_position)==false){
												_recursion_protection_body_element_8.add(_position);
												_state=SUCCESS;
												_position_body_element=_position;
												_token_body_element=_token;
												_token=new Tokens.Rule.BodyElementToken();
												parse__anonymous_53();
												if (_state==FAILED){
													if (_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
														_furthestPosition=_position;
													}
													_position=_position_body_element;
												}
												else {
												}
												if (_state==SUCCESS){
													_token_body_element.add(_position_body_element,_token);
												}
												_token=_token_body_element;
												_recursion_protection_body_element_8.remove(_position_body_element);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		public void parse_base(){
			int _position_base = -1;
			Token.Parsed _token_base = null;
			_position_base=_position;
			_token_base=_token;
			_token=new Token.Parsed("$ANON");
			int _state_0 = _state;
			while (_position<_inputLength){
				parse__anonymous_0();
				if (_state==FAILED){
					break;
				}
			}
			if (_state_0==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base(base)");
					_furthestPosition=_position;
				}
				_position=_position_base;
			}
			else {
			}
			if (_state==SUCCESS){
				_token_base.addAll(_token);
			}
			_token=_token_base;
		}
		public Set<Integer> get_recursionProtectionTypeVar0(){
			return _recursion_protection_type_var_0;
		}
		public Set<Integer> get_recursionProtectionTypeVar1(){
			return _recursion_protection_type_var_1;
		}
		public Set<Integer> get_recursionProtectionTypeVar2(){
			return _recursion_protection_type_var_2;
		}
		public Set<Integer> get_recursionProtectionTypeVar3(){
			return _recursion_protection_type_var_3;
		}
		public void parse_type_var(){
			int _position_type_var = -1;
			Token.Parsed _token_type_var = null;
			if (_state==SUCCESS&&_recursion_protection_type_var_0.contains(_position)==false){
				_recursion_protection_type_var_0.add(_position);
				_position_type_var=_position;
				_token_type_var=_token;
				_token=new Tokens.Rule.TypeVarToken();
				parse__anonymous_156();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
						_furthestPosition=_position;
					}
					_position=_position_type_var;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_type_var.add(_position_type_var,_token);
				}
				_token=_token_type_var;
				_recursion_protection_type_var_0.remove(_position_type_var);
				if (_state==FAILED&&_recursion_protection_type_var_1.contains(_position)==false){
					_recursion_protection_type_var_1.add(_position);
					_state=SUCCESS;
					_position_type_var=_position;
					_token_type_var=_token;
					_token=new Tokens.Rule.TypeVarToken();
					parse__anonymous_166();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
							_furthestPosition=_position;
						}
						_position=_position_type_var;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_type_var.add(_position_type_var,_token);
					}
					_token=_token_type_var;
					_recursion_protection_type_var_1.remove(_position_type_var);
					if (_state==FAILED&&_recursion_protection_type_var_2.contains(_position)==false){
						_recursion_protection_type_var_2.add(_position);
						_state=SUCCESS;
						_position_type_var=_position;
						_token_type_var=_token;
						_token=new Tokens.Rule.TypeVarToken();
						parse__anonymous_172();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
								_furthestPosition=_position;
							}
							_position=_position_type_var;
						}
						else {
						}
						if (_state==SUCCESS){
							_token_type_var.add(_position_type_var,_token);
						}
						_token=_token_type_var;
						_recursion_protection_type_var_2.remove(_position_type_var);
						if (_state==FAILED&&_recursion_protection_type_var_3.contains(_position)==false){
							_recursion_protection_type_var_3.add(_position);
							_state=SUCCESS;
							_position_type_var=_position;
							_token_type_var=_token;
							_token=new Tokens.Rule.TypeVarToken();
							int _state_100 = _state;
							parse__anonymous_177();
							if (_state_100==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
									_furthestPosition=_position;
								}
								_position=_position_type_var;
							}
							else {
								parse_type_var_element();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
										_furthestPosition=_position;
									}
									_position=_position_type_var;
								}
								else {
								}
							}
							if (_state==SUCCESS){
								_token_type_var.add(_position_type_var,_token);
							}
							_token=_token_type_var;
							_recursion_protection_type_var_3.remove(_position_type_var);
						}
					}
				}
			}
		}
		public void parse__anonymous_184(){
			int _position__anonymous_184 = -1;
			Token.Parsed _token__anonymous_184 = null;
			_position__anonymous_184=_position;
			_token__anonymous_184=_token;
			_token=new Token.Parsed("$ANON");
			if (_pass==FIRST_PASS){
				parse_NAME();
			}
			else if (_pass==SECOND_PASS){
				_list_name_result=class_variable_names.get(_position,_inputLength,_inputArray);
				if (_list_name_result!=null){
					if (_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
				}
				else {
					_state=FAILED;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_184)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_184;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_184.addAll(_token);
			}
			_token=_token__anonymous_184;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_184=_position;
				_token__anonymous_184=_token;
				_token=new Token.Parsed("$ANON");
				if (_pass==FIRST_PASS){
					parse_NAME();
				}
				else if (_pass==SECOND_PASS){
					_list_name_result=class_names.get(_position,_inputLength,_inputArray);
					if (_list_name_result!=null){
						if (_position+_list_name_result.length()<_inputLength){
							int _next_char = _inputArray[_position+_list_name_result.length()];
							if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_token.add(_position,new Tokens.Name.ClassNamesToken(_list_name_result));
							_position+=_list_name_result.length();
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_names");
							_furthestPosition=_position;
						}
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_184)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_184;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_184.addAll(_token);
				}
				_token=_token__anonymous_184;
			}
		}
		public void parse__anonymous_185(){
			int _position__anonymous_185 = -1;
			Token.Parsed _token__anonymous_185 = null;
			_position__anonymous_185=_position;
			_token__anonymous_185=_token;
			_token=new Token.Parsed("$ANON");
			parse_template_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_185)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_185;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_185.addAll(_token);
			}
			_token=_token__anonymous_185;
		}
		public void parse__anonymous_180(){
			int _position__anonymous_180 = -1;
			Token.Parsed _token__anonymous_180 = null;
			_position__anonymous_180=_position;
			_token__anonymous_180=_token;
			_token=new Token.Parsed("$ANON");
			if (_pass==FIRST_PASS){
				parse_NAME();
			}
			else if (_pass==SECOND_PASS){
				_list_name_result=variable_names.get(_position,_inputLength,_inputArray);
				if (_list_name_result!=null){
					if (_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if (_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
				}
				else {
					_state=FAILED;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_180)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_180;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_180.addAll(_token);
			}
			_token=_token__anonymous_180;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_180=_position;
				_token__anonymous_180=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_180)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_180;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_180.addAll(_token);
				}
				_token=_token__anonymous_180;
			}
		}
		public void parse__anonymous_181(){
			int _position__anonymous_181 = -1;
			Token.Parsed _token__anonymous_181 = null;
			_position__anonymous_181=_position;
			_token__anonymous_181=_token;
			_token=new Token.Parsed("$ANON");
			parse_template_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_181)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_181;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_181.addAll(_token);
			}
			_token=_token__anonymous_181;
		}
		public void parse__anonymous_182(){
			int _position__anonymous_182 = -1;
			Token.Parsed _token__anonymous_182 = null;
			_position__anonymous_182=_position;
			_token__anonymous_182=_token;
			_token=new Token.Parsed("$ANON");
			int _state_103 = _state;
			parse__anonymous_183();
			if (_state_103==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_182)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_182;
			}
			else {
				parse__anonymous_184();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_182)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_182;
				}
				else {
					int _state_104 = _state;
					parse__anonymous_185();
					if (_state_104==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_182)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_182;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_182.addAll(_token);
			}
			_token=_token__anonymous_182;
		}
		public void parse__anonymous_183(){
			int _position__anonymous_183 = -1;
			Token.Parsed _token__anonymous_183 = null;
			_position__anonymous_183=_position;
			_token__anonymous_183=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_22);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_183)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_183;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_183.addAll(_token);
			}
			_token=_token__anonymous_183;
		}
		public void parse__anonymous_177(){
			int _position__anonymous_177 = -1;
			Token.Parsed _token__anonymous_177 = null;
			_position__anonymous_177=_position;
			_token__anonymous_177=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.ISTYPENAMEToken.plain_20);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"$\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_177)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_177;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_177.addAll(_token);
			}
			_token=_token__anonymous_177;
		}
		public void parse__anonymous_178(){
			int _position__anonymous_178 = -1;
			Token.Parsed _token__anonymous_178 = null;
			_position__anonymous_178=_position;
			_token__anonymous_178=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"\\\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_178)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_178;
			}
			else {
				int _state_101 = _state;
				parse__anonymous_179();
				if (_state_101==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_178)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_178;
				}
				else {
					parse__anonymous_180();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_178)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_178;
					}
					else {
						int _state_102 = _state;
						parse__anonymous_181();
						if (_state_102==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_178)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_178;
						}
						else {
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_178.addAll(_token);
			}
			_token=_token__anonymous_178;
		}
		public void parse__anonymous_179(){
			int _position__anonymous_179 = -1;
			Token.Parsed _token__anonymous_179 = null;
			_position__anonymous_179=_position;
			_token__anonymous_179=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Plain.CAMELToken.plain_21);
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"^\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_179)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_179;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_179.addAll(_token);
			}
			_token=_token__anonymous_179;
		}
		public Set<Integer> get_recursionProtectionBodyStatement0(){
			return _recursion_protection_body_statement_0;
		}
		public Set<Integer> get_recursionProtectionBodyStatement1(){
			return _recursion_protection_body_statement_1;
		}
		public void parse_body_statement(){
			int _position_body_statement = -1;
			Token.Parsed _token_body_statement = null;
			if (_state==SUCCESS&&_recursion_protection_body_statement_0.contains(_position)==false){
				_recursion_protection_body_statement_0.add(_position);
				_position_body_statement=_position;
				_token_body_statement=_token;
				_token=new Tokens.Rule.BodyStatementToken();
				int _state_43 = _state;
				parse__anonymous_70();
				if (_state_43==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
						_furthestPosition=_position;
					}
					_position=_position_body_statement;
				}
				else {
					parse_body_call();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
							_furthestPosition=_position;
						}
						_position=_position_body_statement;
					}
					else {
						int _state_45 = _state;
						while (_position<_inputLength){
							parse__anonymous_71();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_45==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
								_furthestPosition=_position;
							}
							_position=_position_body_statement;
						}
						else {
						}
					}
				}
				if (_state==SUCCESS){
					_token_body_statement.add(_position_body_statement,_token);
				}
				_token=_token_body_statement;
				_recursion_protection_body_statement_0.remove(_position_body_statement);
				if (_state==FAILED&&_recursion_protection_body_statement_1.contains(_position)==false){
					_recursion_protection_body_statement_1.add(_position);
					_state=SUCCESS;
					_position_body_statement=_position;
					_token_body_statement=_token;
					_token=new Tokens.Rule.BodyStatementToken();
					parse_statement_as_string();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
							_furthestPosition=_position;
						}
						_position=_position_body_statement;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_body_statement.add(_position_body_statement,_token);
					}
					_token=_token_body_statement;
					_recursion_protection_body_statement_1.remove(_position_body_statement);
				}
			}
		}
		public Set<Integer> get_recursionProtectionWeak0(){
			return _recursion_protection_weak_0;
		}
		public void parse_weak(){
			int _position_weak = -1;
			Token.Parsed _token_weak = null;
			if (_state==SUCCESS&&_recursion_protection_weak_0.contains(_position)==false){
				_recursion_protection_weak_0.add(_position);
				_position_weak=_position;
				_token_weak=_token;
				_token=new Tokens.Rule.WeakToken();
				if (_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='~'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+1;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"~\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"weak(weak)");
						_furthestPosition=_position;
					}
					_position=_position_weak;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_weak.add(_position_weak,_token);
				}
				_token=_token_weak;
				_recursion_protection_weak_0.remove(_position_weak);
			}
		}
		public Parser.NameList getClassVariableNamesStatementAsBraced(){
			return class_variable_names_statement_as_braced;
		}
		public Parser.NameList getVariableNamesStatementAsBraced(){
			return variable_names_statement_as_braced;
		}
		public Set<Integer> get_recursionProtectionStatementAsBraced0(){
			return _recursion_protection_statement_as_braced_0;
		}
		public void parse_statement_as_braced(){
			int _position_statement_as_braced = -1;
			Token.Parsed _token_statement_as_braced = null;
			int _length_statement_as_braced_brace = _inputLength;
			if (brace_3.containsKey(_position)){
				class_variable_names_statement_as_braced=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_statement_as_braced=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_3.get(_position);
				int _position_statement_as_braced_brace = _position;
				_position+=1;
				while (_position<_length_statement_as_braced_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_statement_as_braced_0.contains(_position)==false){
					_recursion_protection_statement_as_braced_0.add(_position);
					_position_statement_as_braced=_position;
					_token_statement_as_braced=_token;
					_token=new Tokens.Rule.StatementAsBracedToken();
					parse_body_statement();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_braced(statement_as_braced)");
							_furthestPosition=_position;
						}
						_position=_position_statement_as_braced;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_statement_as_braced.add(_position_statement_as_braced,_token);
					}
					_token=_token_statement_as_braced;
					_recursion_protection_statement_as_braced_0.remove(_position_statement_as_braced);
				}
				if (_state==SUCCESS&&brace_3.get(_position_statement_as_braced_brace)==_position){
					_position+=1;
					while (_position<_length_statement_as_braced_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_statement_as_braced_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_statement_as_braced_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_statement_as_braced_brace;
				class_variable_names=class_variable_names_statement_as_braced;
				variable_names=variable_names_statement_as_braced;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_braced(statement_as_braced)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_6(){
			int _position__anonymous_6 = -1;
			Token.Parsed _token__anonymous_6 = null;
			_position__anonymous_6=_position;
			_token__anonymous_6=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!=','){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \",\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_6)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_6;
			}
			else {
				parse_method_argument();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_6)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_6;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_6.addAll(_token);
			}
			_token=_token__anonymous_6;
		}
		public Parser.NameList getClassNamesAdditionsAnonymousClass(){
			return class_names_additions_anonymous_class;
		}
		public Set<Integer> get_recursionProtectionAnonymousClass0(){
			return _recursion_protection_anonymous_class_0;
		}
		public void parse_anonymous_class(){
			int _position_anonymous_class = -1;
			Token.Parsed _token_anonymous_class = null;
			class_names_additions_anonymous_class=class_names_additions;
			class_names_additions=new Parser.NameList.Child(class_names);
			if (_state==SUCCESS&&_recursion_protection_anonymous_class_0.contains(_position)==false){
				_recursion_protection_anonymous_class_0.add(_position);
				_position_anonymous_class=_position;
				_token_anonymous_class=_token;
				_token=new Tokens.Rule.AnonymousClassToken();
				parse__anonymous_16();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
						_furthestPosition=_position;
					}
					_position=_position_anonymous_class;
				}
				else {
					parse_NAME();
					if (_state==SUCCESS){
						String _value = _token.getPollLast().getValue();
						if (_value!=null&&class_names.add(_value)){
							class_names_additions.add(_value);
						}
						_token.add(_position,new Tokens.Name.ClassNameToken(_value));
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
							_furthestPosition=_position;
						}
						_position=_position_anonymous_class;
					}
					else {
						parse__anonymous_17();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
								_furthestPosition=_position;
							}
							_position=_position_anonymous_class;
						}
						else {
							int _state_22 = _state;
							parse__anonymous_18();
							if (_state_22==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
									_furthestPosition=_position;
								}
								_position=_position_anonymous_class;
							}
							else {
								if (_position+1-1 >=_inputLength){
									_state=FAILED;
								}
								else {
									if (_inputArray[_position+0]!=';'){
										_state=FAILED;
									}
								}
								if (_state==SUCCESS){
									_position=_position+1;
									while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
										++_position;
									}
								}
								else if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
										_furthestPosition=_position;
									}
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
										_furthestPosition=_position;
									}
									_position=_position_anonymous_class;
								}
								else {
								}
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_anonymous_class.add(_position_anonymous_class,_token);
				}
				_token=_token_anonymous_class;
				_recursion_protection_anonymous_class_0.remove(_position_anonymous_class);
				if (_state==FAILED){
					if (class_names_additions!=null){
						class_names.removeAll(class_names_additions);
						class_names_additions.clear();
					}
				}
				else if (_state==SUCCESS){
					if (class_names_additions!=null){
						class_names.addAll(class_names_additions);
						class_names_additions.clear();
					}
				}
				class_names_additions=class_names_additions_anonymous_class;
			}
		}
		public void parse__anonymous_5(){
			int _position__anonymous_5 = -1;
			Token.Parsed _token__anonymous_5 = null;
			_position__anonymous_5=_position;
			_token__anonymous_5=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_6();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_5)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_5;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_5.addAll(_token);
			}
			_token=_token__anonymous_5;
		}
		public void parse__anonymous_8(){
			int _position__anonymous_8 = -1;
			Token.Parsed _token__anonymous_8 = null;
			_position__anonymous_8=_position;
			_token__anonymous_8=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!=','){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \",\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(_anonymous_8)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_8;
			}
			else {
				parse_all_type_name();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(_anonymous_8)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_8;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_8.addAll(_token);
			}
			_token=_token__anonymous_8;
		}
		public void parse__anonymous_7(){
			int _position__anonymous_7 = -1;
			Token.Parsed _token__anonymous_7 = null;
			_position__anonymous_7=_position;
			_token__anonymous_7=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_8();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(_anonymous_7)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_7;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_7.addAll(_token);
			}
			_token=_token__anonymous_7;
		}
		public void parse__anonymous_2(){
			int _position__anonymous_2 = -1;
			Token.Parsed _token__anonymous_2 = null;
			_position__anonymous_2=_position;
			_token__anonymous_2=_token;
			_token=new Token.Parsed("$ANON");
			parse_body_element();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_body(_anonymous_2)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_2;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_2.addAll(_token);
			}
			_token=_token__anonymous_2;
		}
		public void parse__anonymous_1(){
			int _position__anonymous_1 = -1;
			Token.Parsed _token__anonymous_1 = null;
			_position__anonymous_1=_position;
			_token__anonymous_1=_token;
			_token=new Token.Parsed("$ANON");
			parse_class_element();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_body(_anonymous_1)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_1;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_1.addAll(_token);
			}
			_token=_token__anonymous_1;
		}
		public void parse__anonymous_4(){
			int _position__anonymous_4 = -1;
			Token.Parsed _token__anonymous_4 = null;
			_position__anonymous_4=_position;
			_token__anonymous_4=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_argument();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_4)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_4;
			}
			else {
				int _state_13 = _state;
				while (_position<_inputLength){
					parse__anonymous_5();
					if (_state==FAILED){
						break;
					}
				}
				if (_state_13==SUCCESS&&_state==SUCCESS)
				if (_state_13==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_4)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_4;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_4.addAll(_token);
			}
			_token=_token__anonymous_4;
		}
		public void parse__anonymous_3(){
			int _position__anonymous_3 = -1;
			Token.Parsed _token__anonymous_3 = null;
			_position__anonymous_3=_position;
			_token__anonymous_3=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_4();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_3)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_3;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_3.addAll(_token);
			}
			_token=_token__anonymous_3;
		}
		public Set<Integer> get_recursionProtectionBaseElement0(){
			return _recursion_protection_base_element_0;
		}
		public Set<Integer> get_recursionProtectionBaseElement1(){
			return _recursion_protection_base_element_1;
		}
		public Set<Integer> get_recursionProtectionBaseElement2(){
			return _recursion_protection_base_element_2;
		}
		public Set<Integer> get_recursionProtectionBaseElement3(){
			return _recursion_protection_base_element_3;
		}
		public Set<Integer> get_recursionProtectionBaseElement4(){
			return _recursion_protection_base_element_4;
		}
		public Set<Integer> get_recursionProtectionBaseElement5(){
			return _recursion_protection_base_element_5;
		}
		public void parse_base_element(){
			int _position_base_element = -1;
			Token.Parsed _token_base_element = null;
			if (_state==SUCCESS&&_recursion_protection_base_element_0.contains(_position)==false){
				_recursion_protection_base_element_0.add(_position);
				_position_base_element=_position;
				_token_base_element=_token;
				_token=new Token.Parsed("$ANON");
				parse_comments();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
						_furthestPosition=_position;
					}
					_position=_position_base_element;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_base_element.addAll(_token);
				}
				_token=_token_base_element;
				_recursion_protection_base_element_0.remove(_position_base_element);
				if (_state==FAILED&&_recursion_protection_base_element_1.contains(_position)==false){
					_recursion_protection_base_element_1.add(_position);
					_state=SUCCESS;
					_position_base_element=_position;
					_token_base_element=_token;
					_token=new Token.Parsed("$ANON");
					parse_imports();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
							_furthestPosition=_position;
						}
						_position=_position_base_element;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_base_element.addAll(_token);
					}
					_token=_token_base_element;
					_recursion_protection_base_element_1.remove(_position_base_element);
					if (_state==FAILED&&_recursion_protection_base_element_2.contains(_position)==false){
						_recursion_protection_base_element_2.add(_position);
						_state=SUCCESS;
						_position_base_element=_position;
						_token_base_element=_token;
						_token=new Token.Parsed("$ANON");
						parse_anonymous_class();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
								_furthestPosition=_position;
							}
							_position=_position_base_element;
						}
						else {
						}
						if (_state==SUCCESS){
							_token_base_element.addAll(_token);
						}
						_token=_token_base_element;
						_recursion_protection_base_element_2.remove(_position_base_element);
						if (_state==FAILED&&_recursion_protection_base_element_3.contains(_position)==false){
							_recursion_protection_base_element_3.add(_position);
							_state=SUCCESS;
							_position_base_element=_position;
							_token_base_element=_token;
							_token=new Token.Parsed("$ANON");
							parse_class_declaration();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
									_furthestPosition=_position;
								}
								_position=_position_base_element;
							}
							else {
							}
							if (_state==SUCCESS){
								_token_base_element.addAll(_token);
							}
							_token=_token_base_element;
							_recursion_protection_base_element_3.remove(_position_base_element);
							if (_state==FAILED&&_recursion_protection_base_element_4.contains(_position)==false){
								_recursion_protection_base_element_4.add(_position);
								_state=SUCCESS;
								_position_base_element=_position;
								_token_base_element=_token;
								_token=new Token.Parsed("$ANON");
								parse_method_declaration();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
										_furthestPosition=_position;
									}
									_position=_position_base_element;
								}
								else {
								}
								if (_state==SUCCESS){
									_token_base_element.addAll(_token);
								}
								_token=_token_base_element;
								_recursion_protection_base_element_4.remove(_position_base_element);
								if (_state==FAILED&&_recursion_protection_base_element_5.contains(_position)==false){
									_recursion_protection_base_element_5.add(_position);
									_state=SUCCESS;
									_position_base_element=_position;
									_token_base_element=_token;
									_token=new Token.Parsed("$ANON");
									parse_variable_declaration();
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
											_furthestPosition=_position;
										}
										_position=_position_base_element;
									}
									else {
										if (_position+1-1 >=_inputLength){
											_state=FAILED;
										}
										else {
											if (_inputArray[_position+0]!=';'){
												_state=FAILED;
											}
										}
										if (_state==SUCCESS){
											_position=_position+1;
											while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
												++_position;
											}
										}
										else if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \";\"");
												_furthestPosition=_position;
											}
										}
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
												_furthestPosition=_position;
											}
											_position=_position_base_element;
										}
										else {
										}
									}
									if (_state==SUCCESS){
										_token_base_element.addAll(_token);
									}
									_token=_token_base_element;
									_recursion_protection_base_element_5.remove(_position_base_element);
								}
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_9(){
			int _position__anonymous_9 = -1;
			Token.Parsed _token__anonymous_9 = null;
			_position__anonymous_9=_position;
			_token__anonymous_9=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_10();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_9)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_9;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_9.addAll(_token);
			}
			_token=_token__anonymous_9;
		}
		public Set<Integer> get_recursionProtectionImports0(){
			return _recursion_protection_imports_0;
		}
		public void parse_imports(){
			int _position_imports = -1;
			Token.Parsed _token_imports = null;
			if (_state==SUCCESS&&_recursion_protection_imports_0.contains(_position)==false){
				_recursion_protection_imports_0.add(_position);
				_position_imports=_position;
				_token_imports=_token;
				_token=new Tokens.Rule.ImportsToken();
				if (_position+3-1 >=_inputLength){
					_state=FAILED;
				}
				else {
					if (_inputArray[_position+0]!='@'){
						_state=FAILED;
					}
					if (_inputArray[_position+1]!='@'){
						_state=FAILED;
					}
					if (_inputArray[_position+2]!='@'){
						_state=FAILED;
					}
				}
				if (_state==SUCCESS){
					_position=_position+3;
					while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"@@@\"");
						_furthestPosition=_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
						_furthestPosition=_position;
					}
					_position=_position_imports;
				}
				else {
					int _state_20 = _state;
					while (_position<_inputLength){
						parse__anonymous_15();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_20==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
							_furthestPosition=_position;
						}
						_position=_position_imports;
					}
					else {
					}
				}
				if (_state==SUCCESS){
					_token_imports.add(_position_imports,_token);
				}
				_token=_token_imports;
				_recursion_protection_imports_0.remove(_position_imports);
			}
		}
		public Parser.NameList getVariableNamesAdditionsBodyManipulate(){
			return variable_names_additions_body_manipulate;
		}
		public Set<Integer> get_recursionProtectionBodyManipulate0(){
			return _recursion_protection_body_manipulate_0;
		}
		public Set<Integer> get_recursionProtectionBodyManipulate1(){
			return _recursion_protection_body_manipulate_1;
		}
		public void parse_body_manipulate(){
			int _position_body_manipulate = -1;
			Token.Parsed _token_body_manipulate = null;
			variable_names_additions_body_manipulate=variable_names_additions;
			variable_names_additions=new Parser.NameList.Child(variable_names);
			if (_state==SUCCESS&&_recursion_protection_body_manipulate_0.contains(_position)==false){
				_recursion_protection_body_manipulate_0.add(_position);
				_position_body_manipulate=_position;
				_token_body_manipulate=_token;
				_token=new Tokens.Rule.BodyManipulateToken();
				int _state_65 = _state;
				parse__anonymous_107();
				if (_state_65==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
						_furthestPosition=_position;
					}
					_position=_position_body_manipulate;
				}
				else {
					parse_type_var();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
							_furthestPosition=_position;
						}
						_position=_position_body_manipulate;
					}
					else {
						if (_position+2-1 >=_inputLength){
							_state=FAILED;
						}
						else {
							if (_inputArray[_position+0]!='+'){
								_state=FAILED;
							}
							if (_inputArray[_position+1]!='='){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_token.add(_position,Tokens.Plain.MethodNameToken.plain_4);
							_position=_position+2;
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
						else if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"+=\"");
								_furthestPosition=_position;
							}
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
								_furthestPosition=_position;
							}
							_position=_position_body_manipulate;
						}
						else {
							parse__anonymous_108();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
									_furthestPosition=_position;
								}
								_position=_position_body_manipulate;
							}
							else {
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_body_manipulate.add(_position_body_manipulate,_token);
				}
				_token=_token_body_manipulate;
				_recursion_protection_body_manipulate_0.remove(_position_body_manipulate);
				if (_state==FAILED&&_recursion_protection_body_manipulate_1.contains(_position)==false){
					_recursion_protection_body_manipulate_1.add(_position);
					variable_names.removeAll(variable_names_additions);
					variable_names_additions.clear();
					_state=SUCCESS;
					_position_body_manipulate=_position;
					_token_body_manipulate=_token;
					_token=new Tokens.Rule.BodyManipulateToken();
					int _state_66 = _state;
					parse__anonymous_109();
					if (_state_66==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
							_furthestPosition=_position;
						}
						_position=_position_body_manipulate;
					}
					else {
						parse_name_var();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
								_furthestPosition=_position;
							}
							_position=_position_body_manipulate;
						}
						else {
							if (_position+1-1 >=_inputLength){
								_state=FAILED;
							}
							else {
								if (_inputArray[_position+0]!=':'){
									_state=FAILED;
								}
							}
							if (_state==SUCCESS){
								_position=_position+1;
								while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
									++_position;
								}
							}
							else if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \":\"");
									_furthestPosition=_position;
								}
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
									_furthestPosition=_position;
								}
								_position=_position_body_manipulate;
							}
							else {
								parse_NAME();
								if (_state==SUCCESS){
									String _value = _token.getPollLast().getValue();
									if (_value!=null&&variable_names.add(_value)){
										variable_names_additions.add(_value);
									}
									_token.add(_position,new Tokens.Name.VariableNameToken(_value));
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
										_furthestPosition=_position;
									}
									_position=_position_body_manipulate;
								}
								else {
									int _state_67 = _state;
									int _multiple_index_67 = 0;
									while (_position<_inputLength){
										parse__anonymous_110();
										if (_state==FAILED){
											break;
										}
										else {
											++_multiple_index_67;
										}
									}
									if (_multiple_index_67==0 ){
										_state=FAILED;
									}
									else if (_state_67==SUCCESS&&_multiple_index_67>0 &&_state==FAILED){
										_state=SUCCESS;
									}
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
											_furthestPosition=_position;
										}
										_position=_position_body_manipulate;
									}
									else {
									}
								}
							}
						}
					}
					if (_state==SUCCESS){
						_token_body_manipulate.add(_position_body_manipulate,_token);
					}
					_token=_token_body_manipulate;
					_recursion_protection_body_manipulate_1.remove(_position_body_manipulate);
					if (_state==FAILED){
						if (variable_names_additions!=null){
							variable_names.removeAll(variable_names_additions);
							variable_names_additions.clear();
						}
					}
					else if (_state==SUCCESS){
						if (variable_names_additions!=null){
							variable_names.addAll(variable_names_additions);
							variable_names_additions.clear();
						}
					}
					variable_names_additions=variable_names_additions_body_manipulate;
				}
			}
		}
		public Parser.NameList getClassVariableNamesAsStatement(){
			return class_variable_names_as_statement;
		}
		public Parser.NameList getVariableNamesAsStatement(){
			return variable_names_as_statement;
		}
		public Set<Integer> get_recursionProtectionAsStatement0(){
			return _recursion_protection_as_statement_0;
		}
		public void parse_as_statement(){
			int _position_as_statement = -1;
			Token.Parsed _token_as_statement = null;
			int _length_as_statement_brace = _inputLength;
			if (brace_9.containsKey(_position)){
				class_variable_names_as_statement=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_as_statement=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_9.get(_position);
				int _position_as_statement_brace = _position;
				_position+=1;
				while (_position<_length_as_statement_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_as_statement_0.contains(_position)==false){
					_recursion_protection_as_statement_0.add(_position);
					_position_as_statement=_position;
					_token_as_statement=_token;
					_token=new Tokens.Rule.AsStatementToken();
					parse__anonymous_13();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(as_statement)");
							_furthestPosition=_position;
						}
						_position=_position_as_statement;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_as_statement.add(_position_as_statement,_token);
					}
					_token=_token_as_statement;
					_recursion_protection_as_statement_0.remove(_position_as_statement);
				}
				if (_state==SUCCESS&&brace_9.get(_position_as_statement_brace)==_position){
					_position+=1;
					while (_position<_length_as_statement_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_9.get(_position_as_statement_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_as_statement_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_as_statement_brace;
				class_variable_names=class_variable_names_as_statement;
				variable_names=variable_names_as_statement;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(as_statement)");
					_furthestPosition=_position;
				}
			}
		}
		public Set<Integer> get_recursionProtectionMethodDeclaration0(){
			return _recursion_protection_method_declaration_0;
		}
		public void parse_method_declaration(){
			int _position_method_declaration = -1;
			Token.Parsed _token_method_declaration = null;
			if (_state==SUCCESS&&_recursion_protection_method_declaration_0.contains(_position)==false){
				_recursion_protection_method_declaration_0.add(_position);
				_position_method_declaration=_position;
				_token_method_declaration=_token;
				_token=new Tokens.Rule.MethodDeclarationToken();
				int _state_70 = _state;
				parse__anonymous_115();
				if (_state_70==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_method_declaration;
				}
				else {
					int _state_71 = _state;
					parse__anonymous_116();
					if (_state_71==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_method_declaration;
					}
					else {
						parse_all_type_name();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_method_declaration;
						}
						else {
							int _state_72 = _state;
							while (_position<_inputLength){
								parse__anonymous_118();
								if (_state==FAILED){
									break;
								}
							}
							if (_state_72==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_method_declaration;
							}
							else {
								parse__anonymous_119();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_method_declaration;
								}
								else {
									parse__anonymous_120();
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_method_declaration;
									}
									else {
										parse_method_body();
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
												_furthestPosition=_position;
											}
											_position=_position_method_declaration;
										}
										else {
										}
									}
								}
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_method_declaration.add(_position_method_declaration,_token);
				}
				_token=_token_method_declaration;
				_recursion_protection_method_declaration_0.remove(_position_method_declaration);
			}
		}
		public void parse__anonymous_0(){
			int _position__anonymous_0 = -1;
			Token.Parsed _token__anonymous_0 = null;
			_position__anonymous_0=_position;
			_token__anonymous_0=_token;
			_token=new Token.Parsed("$ANON");
			parse_base_element();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base(_anonymous_0)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_0;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_0.addAll(_token);
			}
			_token=_token__anonymous_0;
		}
		public Set<Integer> get_recursionProtectionInner0(){
			return _recursion_protection_inner_0;
		}
		public void parse_inner(){
			int _position_inner = -1;
			Token.Parsed _token_inner = null;
			if (_state==SUCCESS&&_recursion_protection_inner_0.contains(_position)==false){
				_recursion_protection_inner_0.add(_position);
				_position_inner=_position;
				_token_inner=_token;
				_token=new Tokens.Rule.InnerToken();
				parse__anonymous_22();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(inner)");
						_furthestPosition=_position;
					}
					_position=_position_inner;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_inner.add(_position_inner,_token);
				}
				_token=_token_inner;
				_recursion_protection_inner_0.remove(_position_inner);
			}
		}
		public Parser.NameList getClassVariableNamesStatementAsString(){
			return class_variable_names_statement_as_string;
		}
		public Parser.NameList getVariableNamesStatementAsString(){
			return variable_names_statement_as_string;
		}
		public Set<Integer> get_recursionProtectionStatementAsString0(){
			return _recursion_protection_statement_as_string_0;
		}
		public void parse_statement_as_string(){
			int _position_statement_as_string = -1;
			Token.Parsed _token_statement_as_string = null;
			int _length_statement_as_string_brace = _inputLength;
			if (brace_7.containsKey(_position)){
				class_variable_names_statement_as_string=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_statement_as_string=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_7.get(_position);
				int _position_statement_as_string_brace = _position;
				_position+=1;
				while (_position<_length_statement_as_string_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_statement_as_string_0.contains(_position)==false){
					_recursion_protection_statement_as_string_0.add(_position);
					_position_statement_as_string=_position;
					_token_statement_as_string=_token;
					_token=new Tokens.Rule.StatementAsStringToken();
					parse_body_statement();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_string(statement_as_string)");
							_furthestPosition=_position;
						}
						_position=_position_statement_as_string;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_statement_as_string.add(_position_statement_as_string,_token);
					}
					_token=_token_statement_as_string;
					_recursion_protection_statement_as_string_0.remove(_position_statement_as_string);
				}
				if (_state==SUCCESS&&brace_7.get(_position_statement_as_string_brace)==_position){
					_position+=1;
					while (_position<_length_statement_as_string_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_7.get(_position_statement_as_string_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_statement_as_string_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_statement_as_string_brace;
				class_variable_names=class_variable_names_statement_as_string;
				variable_names=variable_names_statement_as_string;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_string(statement_as_string)");
					_furthestPosition=_position;
				}
			}
		}
		public Set<Integer> get_recursionProtectionTypeVarElement0(){
			return _recursion_protection_type_var_element_0;
		}
		public Set<Integer> get_recursionProtectionTypeVarElement1(){
			return _recursion_protection_type_var_element_1;
		}
		public Set<Integer> get_recursionProtectionTypeVarElement2(){
			return _recursion_protection_type_var_element_2;
		}
		public Set<Integer> get_recursionProtectionTypeVarElement3(){
			return _recursion_protection_type_var_element_3;
		}
		public Set<Integer> get_recursionProtectionTypeVarElement4(){
			return _recursion_protection_type_var_element_4;
		}
		public void parse_type_var_element(){
			int _position_type_var_element = -1;
			Token.Parsed _token_type_var_element = null;
			if (_state==SUCCESS&&_recursion_protection_type_var_element_0.contains(_position)==false){
				_recursion_protection_type_var_element_0.add(_position);
				_position_type_var_element=_position;
				_token_type_var_element=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
						_furthestPosition=_position;
					}
					_position=_position_type_var_element;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_type_var_element.addAll(_token);
				}
				_token=_token_type_var_element;
				_recursion_protection_type_var_element_0.remove(_position_type_var_element);
				if (_state==FAILED&&_recursion_protection_type_var_element_1.contains(_position)==false){
					_recursion_protection_type_var_element_1.add(_position);
					_state=SUCCESS;
					_position_type_var_element=_position;
					_token_type_var_element=_token;
					_token=new Token.Parsed("$ANON");
					parse_statement_as_quote();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
							_furthestPosition=_position;
						}
						_position=_position_type_var_element;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_type_var_element.addAll(_token);
					}
					_token=_token_type_var_element;
					_recursion_protection_type_var_element_1.remove(_position_type_var_element);
					if (_state==FAILED&&_recursion_protection_type_var_element_2.contains(_position)==false){
						_recursion_protection_type_var_element_2.add(_position);
						_state=SUCCESS;
						_position_type_var_element=_position;
						_token_type_var_element=_token;
						_token=new Token.Parsed("$ANON");
						parse_statement_as_string();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
								_furthestPosition=_position;
							}
							_position=_position_type_var_element;
						}
						else {
						}
						if (_state==SUCCESS){
							_token_type_var_element.addAll(_token);
						}
						_token=_token_type_var_element;
						_recursion_protection_type_var_element_2.remove(_position_type_var_element);
						if (_state==FAILED&&_recursion_protection_type_var_element_3.contains(_position)==false){
							_recursion_protection_type_var_element_3.add(_position);
							_state=SUCCESS;
							_position_type_var_element=_position;
							_token_type_var_element=_token;
							_token=new Token.Parsed("$ANON");
							parse__anonymous_178();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
									_furthestPosition=_position;
								}
								_position=_position_type_var_element;
							}
							else {
							}
							if (_state==SUCCESS){
								_token_type_var_element.addAll(_token);
							}
							_token=_token_type_var_element;
							_recursion_protection_type_var_element_3.remove(_position_type_var_element);
							if (_state==FAILED&&_recursion_protection_type_var_element_4.contains(_position)==false){
								_recursion_protection_type_var_element_4.add(_position);
								_state=SUCCESS;
								_position_type_var_element=_position;
								_token_type_var_element=_token;
								_token=new Token.Parsed("$ANON");
								parse__anonymous_182();
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
										_furthestPosition=_position;
									}
									_position=_position_type_var_element;
								}
								else {
								}
								if (_state==SUCCESS){
									_token_type_var_element.addAll(_token);
								}
								_token=_token_type_var_element;
								_recursion_protection_type_var_element_4.remove(_position_type_var_element);
							}
						}
					}
				}
			}
		}
		public Set<Integer> get_recursionProtectionVariableAssignment0(){
			return _recursion_protection_variable_assignment_0;
		}
		public void parse_variable_assignment(){
			int _position_variable_assignment = -1;
			Token.Parsed _token_variable_assignment = null;
			if (_state==SUCCESS&&_recursion_protection_variable_assignment_0.contains(_position)==false){
				_recursion_protection_variable_assignment_0.add(_position);
				_position_variable_assignment=_position;
				_token_variable_assignment=_token;
				_token=new Tokens.Rule.VariableAssignmentToken();
				int _state_78 = _state;
				parse__anonymous_129();
				if (_state_78==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
						_furthestPosition=_position;
					}
					_position=_position_variable_assignment;
				}
				else {
					parse_name_var();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
							_furthestPosition=_position;
						}
						_position=_position_variable_assignment;
					}
					else {
						if (_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else {
							if (_inputArray[_position+0]!='='){
								_state=FAILED;
							}
						}
						if (_state==SUCCESS){
							_position=_position+1;
							while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
								++_position;
							}
						}
						else if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"=\"");
								_furthestPosition=_position;
							}
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
								_furthestPosition=_position;
							}
							_position=_position_variable_assignment;
						}
						else {
							parse_method_argument();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
									_furthestPosition=_position;
								}
								_position=_position_variable_assignment;
							}
							else {
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_variable_assignment.add(_position_variable_assignment,_token);
				}
				_token=_token_variable_assignment;
				_recursion_protection_variable_assignment_0.remove(_position_variable_assignment);
			}
		}
		public Set<Integer> get_recursionProtectionPackageName0(){
			return _recursion_protection_packageName_0;
		}
		public void parse_packageName(){
			int _position_packageName = -1;
			Token.Parsed _token_packageName = null;
			if (_state==SUCCESS&&_recursion_protection_packageName_0.contains(_position)==false){
				_recursion_protection_packageName_0.add(_position);
				_position_packageName=_position;
				_token_packageName=_token;
				_token=new Tokens.Rule.PackageNameToken();
				parse_name_var();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
						_furthestPosition=_position;
					}
					_position=_position_packageName;
				}
				else {
				}
				if (_state==SUCCESS){
					_token_packageName.add(_position_packageName,_token);
				}
				_token=_token_packageName;
				_recursion_protection_packageName_0.remove(_position_packageName);
			}
		}
		public Parser.NameList getClassVariableNamesComments(){
			return class_variable_names_comments;
		}
		public Parser.NameList getVariableNamesComments(){
			return variable_names_comments;
		}
		public Set<Integer> get_recursionProtectionComments0(){
			return _recursion_protection_comments_0;
		}
		public void parse_comments(){
			int _position_comments = -1;
			Token.Parsed _token_comments = null;
			int _length_comments_brace = _inputLength;
			if (brace_1.containsKey(_position)){
				class_variable_names_comments=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_comments=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_1.get(_position);
				int _position_comments_brace = _position;
				_position+=1;
				while (_position<_length_comments_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_comments_0.contains(_position)==false){
					_recursion_protection_comments_0.add(_position);
					_position_comments=_position;
					_token_comments=_token;
					_token=new Tokens.Rule.CommentsToken();
					int _position_regex_0 = _position;
					int _multiple_index_1 = 0;
					int _state_1 = _state;
					while (_position<_inputLength){
						if (_position<_inputLength){
							++_position;
						}
						else {
							_state=FAILED;
						}
						if (_state==FAILED){
							break;
						}
						else {
							++_multiple_index_1;
						}
					}
					if (_state_1==SUCCESS){
						_state=SUCCESS;
					}
					if (_state==SUCCESS){
						_token.add(_position_regex_0,new Tokens.Plain.Comment(_input.substring(_position_regex_0,_position)));
						while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".*");
							_furthestPosition=_position;
						}
						_position=_position_regex_0;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"comments(comments)");
							_furthestPosition=_position;
						}
						_position=_position_comments;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_comments.add(_position_comments,_token);
					}
					_token=_token_comments;
					_recursion_protection_comments_0.remove(_position_comments);
				}
				if (_state==SUCCESS&&brace_1.get(_position_comments_brace)==_position){
					_position+=1;
					while (_position<_length_comments_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_1.get(_position_comments_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_comments_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_comments_brace;
				class_variable_names=class_variable_names_comments;
				variable_names=variable_names_comments;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"comments(comments)");
					_furthestPosition=_position;
				}
			}
		}
		public Parser.NameList getClassVariableNamesTemplateParameters(){
			return class_variable_names_template_parameters;
		}
		public Parser.NameList getVariableNamesTemplateParameters(){
			return variable_names_template_parameters;
		}
		public Set<Integer> get_recursionProtectionTemplateParameters0(){
			return _recursion_protection_template_parameters_0;
		}
		public void parse_template_parameters(){
			int _position_template_parameters = -1;
			Token.Parsed _token_template_parameters = null;
			int _length_template_parameters_brace = _inputLength;
			if (brace_4.containsKey(_position)){
				class_variable_names_template_parameters=class_variable_names;
				if (_pass==SECOND_PASS){
					class_variable_names=new Parser.NameList.Child(class_variable_names);
				}
				variable_names_template_parameters=variable_names;
				if (_pass==SECOND_PASS){
					variable_names=new Parser.NameList.Child(variable_names);
				}
				if (_pass==SECOND_PASS){
					_inputLength=brace_4.get(_position);
					int _position_template_parameters_brace = _position;
					_position+=1;
					while (_position<_length_template_parameters_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
					if (_state==SUCCESS&&_recursion_protection_template_parameters_0.contains(_position)==false){
						_recursion_protection_template_parameters_0.add(_position);
						_position_template_parameters=_position;
						_token_template_parameters=_token;
						_token=new Tokens.Rule.TemplateParametersToken();
						parse_all_type_name();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
								_furthestPosition=_position;
							}
							_position=_position_template_parameters;
						}
						else {
							int _state_15 = _state;
							while (_position<_inputLength){
								parse__anonymous_7();
								if (_state==FAILED){
									break;
								}
							}
							if (_state_15==SUCCESS&&_state==SUCCESS)
							if (_state_15==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
									_furthestPosition=_position;
								}
								_position=_position_template_parameters;
							}
							else {
							}
						}
						if (_state==SUCCESS){
							_token_template_parameters.add(_position_template_parameters,_token);
						}
						_token=_token_template_parameters;
						_recursion_protection_template_parameters_0.remove(_position_template_parameters);
					}
					if (_state==SUCCESS&&brace_4.get(_position_template_parameters_brace)==_position){
						_position+=1;
						while (_position<_length_template_parameters_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					else {
						_state=SUCCESS;
						_result_acceptor.add(_result);
						_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
						_position=brace_4.get(_position_template_parameters_brace)+1;
						_succeedOnEnd=false;
						while (_position<_length_template_parameters_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
							++_position;
						}
					}
					_inputLength=_length_template_parameters_brace;
					class_variable_names=class_variable_names_template_parameters;
					variable_names=variable_names_template_parameters;
				}
				else {
					_position=brace_4.get(_position)+1;
					while (_position<_length_template_parameters_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_99(){
			int _position__anonymous_99 = -1;
			Token.Parsed _token__anonymous_99 = null;
			_position__anonymous_99=_position;
			_token__anonymous_99=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \".\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_99)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_99;
			}
			else {
				parse_type_var();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_99)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_99;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_99.addAll(_token);
			}
			_token=_token__anonymous_99;
		}
		public void parse__anonymous_98(){
			int _position__anonymous_98 = -1;
			Token.Parsed _token__anonymous_98 = null;
			_position__anonymous_98=_position;
			_token__anonymous_98=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_99();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_98)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_98;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_98.addAll(_token);
			}
			_token=_token__anonymous_98;
		}
		public Parser.NameList getClassNamesAdditionsClassDeclaration(){
			return class_names_additions_class_declaration;
		}
		public Parser.NameList getClassVariableNamesAdditionsClassDeclaration(){
			return class_variable_names_additions_class_declaration;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration0(){
			return _recursion_protection_class_declaration_0;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration1(){
			return _recursion_protection_class_declaration_1;
		}
		public void parse_class_declaration(){
			int _position_class_declaration = -1;
			Token.Parsed _token_class_declaration = null;
			class_names_additions_class_declaration=class_names_additions;
			class_names_additions=new Parser.NameList.Child(class_names);
			class_variable_names_additions_class_declaration=class_variable_names_additions;
			class_variable_names_additions=new Parser.NameList.Child(class_variable_names);
			if (_state==SUCCESS&&_recursion_protection_class_declaration_0.contains(_position)==false){
				_recursion_protection_class_declaration_0.add(_position);
				_position_class_declaration=_position;
				_token_class_declaration=_token;
				_token=new Tokens.Rule.ClassDeclarationToken();
				int _state_23 = _state;
				parse__anonymous_23();
				if (_state_23==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_class_declaration;
				}
				else {
					int _state_24 = _state;
					parse__anonymous_24();
					if (_state_24==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_class_declaration;
					}
					else {
						parse__anonymous_25();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_class_declaration;
						}
						else {
							parse__anonymous_26();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_class_declaration;
							}
							else {
								parse__anonymous_29();
								if (_state==SUCCESS){
									String _value = _token.getPollLast().getValue();
									if (_value!=null&&class_names.add(_value)){
										class_names_additions.add(_value);
									}
									_token.add(_position,new Tokens.Name.ClassNameToken(_value));
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_class_declaration;
								}
								else {
									int _state_26 = _state;
									parse__anonymous_30();
									if (_state_26==SUCCESS&&_state==FAILED){
										_state=SUCCESS;
									}
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_class_declaration;
									}
									else {
										int _state_27 = _state;
										parse__anonymous_32();
										if (_state_27==SUCCESS&&_state==FAILED){
											_state=SUCCESS;
										}
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
												_furthestPosition=_position;
											}
											_position=_position_class_declaration;
										}
										else {
											int _state_28 = _state;
											while (_position<_inputLength){
												parse__anonymous_34();
												if (_state==FAILED){
													break;
												}
											}
											if (_state_28==SUCCESS&&_state==FAILED){
												_state=SUCCESS;
											}
											if (_state==FAILED){
												if (_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
													_furthestPosition=_position;
												}
												_position=_position_class_declaration;
											}
											else {
												parse_class_body();
												if (_state==FAILED){
													if (_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
														_furthestPosition=_position;
													}
													_position=_position_class_declaration;
												}
												else {
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (_state==SUCCESS){
					_token_class_declaration.add(_position_class_declaration,_token);
				}
				_token=_token_class_declaration;
				_recursion_protection_class_declaration_0.remove(_position_class_declaration);
				if (_state==FAILED&&_recursion_protection_class_declaration_1.contains(_position)==false){
					_recursion_protection_class_declaration_1.add(_position);
					class_names.removeAll(class_names_additions);
					class_names_additions.clear();
					class_variable_names.removeAll(class_variable_names_additions);
					class_variable_names_additions.clear();
					_state=SUCCESS;
					_position_class_declaration=_position;
					_token_class_declaration=_token;
					_token=new Tokens.Rule.ClassDeclarationToken();
					int _state_29 = _state;
					parse__anonymous_36();
					if (_state_29==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_class_declaration;
					}
					else {
						int _state_30 = _state;
						parse__anonymous_37();
						if (_state_30==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_class_declaration;
						}
						else {
							parse__anonymous_38();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_class_declaration;
							}
							else {
								parse__anonymous_39();
								if (_state==SUCCESS){
									String _value = _token.getPollLast().getValue();
									if (_value!=null&&class_names.add(_value)){
										class_names_additions.add(_value);
									}
									_token.add(_position,new Tokens.Name.ClassNameToken(_value));
								}
								if (_state==FAILED){
									if (_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_class_declaration;
								}
								else {
									int _state_31 = _state;
									parse__anonymous_40();
									if (_state_31==SUCCESS&&_state==FAILED){
										_state=SUCCESS;
									}
									if (_state==FAILED){
										if (_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_class_declaration;
									}
									else {
										if (_position+1-1 >=_inputLength){
											_state=FAILED;
										}
										else {
											if (_inputArray[_position+0]!='/'){
												_state=FAILED;
											}
										}
										if (_state==SUCCESS){
											_position=_position+1;
											while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
												++_position;
											}
										}
										else if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"/\"");
												_furthestPosition=_position;
											}
										}
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
												_furthestPosition=_position;
											}
											_position=_position_class_declaration;
										}
										else {
											int _state_32 = _state;
											parse__anonymous_42();
											if (_state_32==SUCCESS&&_state==FAILED){
												_state=SUCCESS;
											}
											if (_state==FAILED){
												if (_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
													_furthestPosition=_position;
												}
												_position=_position_class_declaration;
											}
											else {
												if (_position+1-1 >=_inputLength){
													_state=FAILED;
												}
												else {
													if (_inputArray[_position+0]!='/'){
														_state=FAILED;
													}
												}
												if (_state==SUCCESS){
													_position=_position+1;
													while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
														++_position;
													}
												}
												else if (_state==FAILED){
													if (_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"/\"");
														_furthestPosition=_position;
													}
												}
												if (_state==FAILED){
													if (_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
														_furthestPosition=_position;
													}
													_position=_position_class_declaration;
												}
												else {
													int _state_33 = _state;
													while (_position<_inputLength){
														parse__anonymous_44();
														if (_state==FAILED){
															break;
														}
													}
													if (_state_33==SUCCESS&&_state==FAILED){
														_state=SUCCESS;
													}
													if (_state==FAILED){
														if (_position>=_furthestPosition){
															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
															_furthestPosition=_position;
														}
														_position=_position_class_declaration;
													}
													else {
														parse_class_body();
														if (_state==FAILED){
															if (_position>=_furthestPosition){
																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
																_furthestPosition=_position;
															}
															_position=_position_class_declaration;
														}
														else {
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if (_state==SUCCESS){
						_token_class_declaration.add(_position_class_declaration,_token);
					}
					_token=_token_class_declaration;
					_recursion_protection_class_declaration_1.remove(_position_class_declaration);
					if (_state==FAILED){
						if (class_names_additions!=null){
							class_names.removeAll(class_names_additions);
							class_names_additions.clear();
						}
						if (class_variable_names_additions!=null){
							class_variable_names.removeAll(class_variable_names_additions);
							class_variable_names_additions.clear();
						}
					}
					else if (_state==SUCCESS){
						if (class_names_additions!=null){
							class_names.addAll(class_names_additions);
							class_names_additions.clear();
						}
						if (class_variable_names_additions!=null){
							class_variable_names.addAll(class_variable_names_additions);
							class_variable_names_additions.clear();
						}
					}
					class_names_additions=class_names_additions_class_declaration;
					class_variable_names_additions=class_variable_names_additions_class_declaration;
				}
			}
		}
		public void parse__anonymous_91(){
			int _position__anonymous_91 = -1;
			Token.Parsed _token__anonymous_91 = null;
			_position__anonymous_91=_position;
			_token__anonymous_91=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_92();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_91)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_91;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_91.addAll(_token);
			}
			_token=_token__anonymous_91;
		}
		public void parse__anonymous_90(){
			int _position__anonymous_90 = -1;
			Token.Parsed _token__anonymous_90 = null;
			_position__anonymous_90=_position;
			_token__anonymous_90=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_90)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_90;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_90.addAll(_token);
			}
			_token=_token__anonymous_90;
		}
		public void parse__anonymous_93(){
			int _position__anonymous_93 = -1;
			Token.Parsed _token__anonymous_93 = null;
			_position__anonymous_93=_position;
			_token__anonymous_93=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_94();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_93)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_93;
			}
			else {
				int _state_56 = _state;
				parse__anonymous_95();
				if (_state_56==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_93)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_93;
				}
				else {
					int _state_57 = _state;
					while (_position<_inputLength){
						parse__anonymous_96();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_57==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_93)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_93;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_93.addAll(_token);
			}
			_token=_token__anonymous_93;
		}
		public Parser.NameList getClassVariableNamesStatementAsMethod(){
			return class_variable_names_statement_as_method;
		}
		public Parser.NameList getVariableNamesStatementAsMethod(){
			return variable_names_statement_as_method;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod0(){
			return _recursion_protection_statement_as_method_0;
		}
		public void parse_statement_as_method(){
			int _position_statement_as_method = -1;
			Token.Parsed _token_statement_as_method = null;
			int _length_statement_as_method_brace = _inputLength;
			if (brace_8.containsKey(_position)){
				class_variable_names_statement_as_method=class_variable_names;
				class_variable_names=new Parser.NameList.Child(class_variable_names);
				variable_names_statement_as_method=variable_names;
				variable_names=new Parser.NameList.Child(variable_names);
				_inputLength=brace_8.get(_position);
				int _position_statement_as_method_brace = _position;
				_position+=1;
				while (_position<_length_statement_as_method_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
				if (_state==SUCCESS&&_recursion_protection_statement_as_method_0.contains(_position)==false){
					_recursion_protection_statement_as_method_0.add(_position);
					_position_statement_as_method=_position;
					_token_statement_as_method=_token;
					_token=new Tokens.Rule.StatementAsMethodToken();
					parse_body_statement();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_method(statement_as_method)");
							_furthestPosition=_position;
						}
						_position=_position_statement_as_method;
					}
					else {
					}
					if (_state==SUCCESS){
						_token_statement_as_method.add(_position_statement_as_method,_token);
					}
					_token=_token_statement_as_method;
					_recursion_protection_statement_as_method_0.remove(_position_statement_as_method);
				}
				if (_state==SUCCESS&&brace_8.get(_position_statement_as_method_brace)==_position){
					_position+=1;
					while (_position<_length_statement_as_method_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_8.get(_position_statement_as_method_brace)+1;
					_succeedOnEnd=false;
					while (_position<_length_statement_as_method_brace&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
						++_position;
					}
				}
				_inputLength=_length_statement_as_method_brace;
				class_variable_names=class_variable_names_statement_as_method;
				variable_names=variable_names_statement_as_method;
			}
			else {
				_state=FAILED;
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_method(statement_as_method)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_92(){
			int _position__anonymous_92 = -1;
			Token.Parsed _token__anonymous_92 = null;
			_position__anonymous_92=_position;
			_token__anonymous_92=_token;
			_token=new Token.Parsed("$ANON");
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_position=_position+1;
				while (_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \".\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_92)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_92;
			}
			else {
				parse__anonymous_93();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_92)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_92;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_92.addAll(_token);
			}
			_token=_token__anonymous_92;
		}
		public void parse__anonymous_95(){
			int _position__anonymous_95 = -1;
			Token.Parsed _token__anonymous_95 = null;
			_position__anonymous_95=_position;
			_token__anonymous_95=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_arguments();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_95)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_95;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_95.addAll(_token);
			}
			_token=_token__anonymous_95;
		}
		public void parse__anonymous_94(){
			int _position__anonymous_94 = -1;
			Token.Parsed _token__anonymous_94 = null;
			_position__anonymous_94=_position;
			_token__anonymous_94=_token;
			_token=new Token.Parsed("$ANON");
			parse_name_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_94)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_94;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_94.addAll(_token);
			}
			_token=_token__anonymous_94;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_94=_position;
				_token__anonymous_94=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_94)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_94;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_94.addAll(_token);
				}
				_token=_token__anonymous_94;
			}
		}
		public void parse__anonymous_97(){
			int _position__anonymous_97 = -1;
			Token.Parsed _token__anonymous_97 = null;
			_position__anonymous_97=_position;
			_token__anonymous_97=_token;
			_token=new Token.Parsed("$ANON");
			parse_type_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_97)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_97;
			}
			else {
				int _state_59 = _state;
				while (_position<_inputLength){
					parse__anonymous_98();
					if (_state==FAILED){
						break;
					}
				}
				if (_state_59==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_97)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_97;
				}
				else {
					int _state_60 = _state;
					parse__anonymous_100();
					if (_state_60==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_97)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_97;
					}
					else {
						int _state_61 = _state;
						while (_position<_inputLength){
							parse__anonymous_101();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_61==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_97)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_97;
						}
						else {
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_97.addAll(_token);
			}
			_token=_token__anonymous_97;
		}
		public void parse__anonymous_96(){
			int _position__anonymous_96 = -1;
			Token.Parsed _token__anonymous_96 = null;
			_position__anonymous_96=_position;
			_token__anonymous_96=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_96)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_96;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_96.addAll(_token);
			}
			_token=_token__anonymous_96;
		}
	}
	public static class NameList{
		protected Parser.NameList.Node.Root root = new Parser.NameList.Node.Root();
		public NameList() {
		}
		public Parser.NameList.Node.Root getRoot(){
			return root;
		}
		public void setRoot(Parser.NameList.Node.Root newRoot){
			root = newRoot;
		}
		public boolean add(String newEntry){
			return root.add(newEntry);
		}
		public String get(int position,int length,char[] input){
			return root.get(position,length,input);
		}
		public Set<String> list(){
			return root.list();
		}
		public void clear(){
			root.clear();
		}
		public void removeAll(Parser.NameList fromList){
			root.removeAll(fromList.getRoot());
		}
		public void addAll(Parser.NameList fromList){
			root.addAll(fromList.getRoot());
		}
		public static class Node{
			protected Node[] children = new Node[128];
			protected int numberOfEntries = 0;
			protected String value = null;
			public Node() {
			}
			public Node[] getChildren(){
				return children;
			}
			public void setChildren(Node[] newChildren){
				children = newChildren;
			}
			public int getNumberOfEntries(){
				return numberOfEntries;
			}
			public void setNumberOfEntries(int newNumberOfEntries){
				numberOfEntries = newNumberOfEntries;
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public boolean add(String newEntry,int position){
				if (position<newEntry.length()){
					if (children[newEntry.charAt(position)]==null){
						children[newEntry.charAt(position)]=new Parser.NameList.Node();
						++numberOfEntries;
					}
					return children[newEntry.charAt(position)].add(newEntry,position+1);
				}
				else {
					if (value==null||!value.equals(newEntry)){
						value=newEntry;
						return true;
					}
					else {
						return false;
					}
				}
			}
			public String get(int position,int length,char[] input){
				if (position>=length){
					return value;
				}
				if (children[input[position]]!=null){
					String result = children[input[position]].get(position+1,length,input);
					if (result!=null){
						return result;
					}
					else {
						return value;
					}
				}
				return value;
			}
			public int remove(String toRemove,int index){
				if (index>=toRemove.length()){
					if (toRemove.equals(value)){
						value=null;
						return 0;
					}
					else {
						return 2;
					}
				}
				else {
					if (children[toRemove.charAt(index)]!=null){
						int result = children[toRemove.charAt(index)].remove(toRemove,index+1);
						if (result<2){
							if (numberOfEntries>1 ){
								children[toRemove.charAt(index)]=null;
								--numberOfEntries;
							}
							return numberOfEntries;
						}
						else {
							return result;
						}
					}
					else {
						return 2;
					}
				}
			}
			public static class Root extends Parser.NameList.Node{
				protected Set<String> allEntries = new HashSet<String>();
				public Root() {
					super();
				}
				public Set<String> getAllEntries(){
					return allEntries;
				}
				public void setAllEntries(Set<String> newAllEntries){
					allEntries = newAllEntries;
				}
				public boolean add(String newEntry){
					if (allEntries.add(newEntry)){
						return super.add(newEntry,0);
					}
					else {
						return false;
					}
				}
				public Set<String> list(){
					return allEntries;
				}
				public void clear(){
					for (String entry: allEntries){
						children[entry.charAt(0)]=null;
					}
				}
				public void removeAll(Parser.NameList.Node.Root fromNode){
					for (String entry: fromNode.allEntries){
						remove(entry,0);
					}
					allEntries.removeAll(fromNode.allEntries);
					numberOfEntries=allEntries.size();
				}
				public void addAll(Parser.NameList.Node.Root fromNode){
					for (String entry: fromNode.allEntries){
						add(entry,0);
					}
				}
			}
		}
		public static class Root extends Parser.NameList{
			public Root() {
				super();
			}
			public boolean add(String query){
				synchronized (this){
					return super.add(query);
				}
			}
		}
		public static class Child extends Parser.NameList{
			protected NameList parentList = null;
			public Child(final NameList parentList) {
				super();
				if(parentList != null){
					this.parentList = parentList;
				}
			}
			public Child() {
			}
			public NameList getParentList(){
				return parentList;
			}
			public String get(int position,int length,char[] input){
				String result = super.get(position,length,input);
				String parentResult = parentList.get(position,length,input);
				if (result!=null){
					if (parentResult==null){
						return result;
					}
					else if (parentResult.length()<result.length()){
						return result;
					}
					else {
						return parentResult;
					}
				}
				else {
					return parentResult;
				}
			}
			public Set<String> list(){
				Set<String> set = new HashSet<String>();
				set.addAll(super.list());
				set.addAll(parentList.list());
				return set;
			}
		}
	}
	public static class Result{
		protected Integer state = -1;
		protected Integer position = -1;
		protected List<Integer> lineNumberRanges = null;
		protected String input = null;
		protected String fileName = null;
		protected long parseTime = -1;
		public Result(final Integer state, final Integer position, final List<Integer> lineNumberRanges, final String input, final String fileName) {
			if(state != null){
				this.state = state;
			}
			if(position != null){
				this.position = position;
			}
			if(lineNumberRanges != null){
				this.lineNumberRanges = lineNumberRanges;
			}
			if(input != null){
				this.input = input;
			}
			if(fileName != null){
				this.fileName = fileName;
			}
		}
		public Result() {
		}
		public Integer getState(){
			return state;
		}
		public Integer getPosition(){
			return position;
		}
		public List<Integer> getLineNumberRanges(){
			return lineNumberRanges;
		}
		public String getInput(){
			return input;
		}
		public String getFileName(){
			return fileName;
		}
		public long getParseTime(){
			return parseTime;
		}
		public void setParseTime(long newParseTime){
			parseTime = newParseTime;
		}
		public void setFileName(String newFileName){
			fileName=newFileName;
		}
		public Integer getLineNumber(Integer position){
			Integer upperBound = 0;
			Integer lineNumber = 0;
			while (lineNumber<lineNumberRanges.size()&&upperBound<position){
				upperBound=lineNumberRanges.get(lineNumber);
				lineNumber+=1;
			}
			return lineNumber;
		}
		public Integer getLineNumber(){
			Integer upperBound = 0;
			Integer lineNumber = 0;
			while (lineNumber<lineNumberRanges.size()&&upperBound<position){
				upperBound=lineNumberRanges.get(lineNumber);
				lineNumber+=1;
			}
			return lineNumber;
		}
		public String toString(){
			if (state==Parser.FAILED){
				Integer lineNumber = getLineNumber();
				Integer rangeIndex = lineNumber-1;
				if (rangeIndex<0 ){
					rangeIndex=0;
				}
				Integer upperBound = lineNumberRanges.get(rangeIndex);
				Integer lowerBound = 0;
				if (rangeIndex>0 ){
					lowerBound=lineNumberRanges.get(rangeIndex-1)+1;
				}
				String errorAt;
				if (upperBound<input.length()){
					errorAt=input.substring(lowerBound,position)+"$>"+input.substring(position,upperBound);
				}
				else {
					errorAt=input.substring(lowerBound,position)+"<$"+input.substring(position);
				}
				if (parseTime<=0 ){
					if (fileName==null){
						return "\n\tError: "+errorAt+"\n\tLine Number: "+lineNumber;
					}
					else {
						return "\n\tError: "+errorAt+"\n\tLine Number: "+lineNumber+"\n\tFile Name: "+fileName;
					}
				}
				else {
					if (position==-1 ){
						if (parseTime<1000 ){
							return "File Name: "+fileName+"\nParse Time: "+parseTime+"ms";
						}
						else {
							return "File Name: "+fileName+"\nParse Time: "+parseTime/1000 +"."+parseTime%1000 +"s";
						}
					}
					else {
						if (parseTime<1000 ){
							return "\n\tError: "+errorAt+"\n\tLine Number: "+lineNumber+"\n\tFile Name: "+fileName+"\n\tParse Time: "+parseTime+"ms";
						}
						else {
							return "\n\tError: "+errorAt+"\n\tLine Number: "+lineNumber+"\n\tFile Name: "+fileName+"\n\tParse Time: "+parseTime/1000 +"."+parseTime%1000 +"s";
						}
					}
				}
			}
			else {
				if (parseTime==0 ){
					if (fileName==null){
						return "";
					}
					else {
						return "File Name: "+fileName;
					}
				}
				else {
					if (parseTime<1000 ){
						return "File Name: "+fileName+"\nParse Time: "+parseTime+"ms";
					}
					else {
						return "File Name: "+fileName+"\nParse Time: "+parseTime/1000 +"."+parseTime%1000 +"s";
					}
				}
			}
		}
		public static class Pass extends Parser.Result{
			protected Token.Parsed parsedRoot = null;
			protected Token.Branch root = null;
			public Pass(final Integer initalSuperState, final Integer initalSuperPosition, final List<Integer> initalSuperLineNumberRanges, final String initalSuperInput, final String initalSuperFileName, final Token.Parsed parsedRoot) {
				super(initalSuperState, initalSuperPosition, initalSuperLineNumberRanges, initalSuperInput, initalSuperFileName);
				if(parsedRoot != null){
					this.parsedRoot = parsedRoot;
				}
			}
			public Pass(final Token.Parsed parsedRoot) {
				if(parsedRoot != null){
					this.parsedRoot = parsedRoot;
				}
			}
			public Pass() {
			}
			public Token.Parsed getParsedRoot(){
				return parsedRoot;
			}
			public Token.Branch getRoot(){
				return root;
			}
			public void setRoot(Token.Branch newRoot){
				root = newRoot;
			}
			public void setup(){
				root=new Token.Branch();
				setup(root,parsedRoot,0);
			}
			public void setup(Token.Branch current,Token.Parsed currentParsed,Integer currentPosition){
				List<Token.Parsed> children = currentParsed.getChildren();
				List<Integer> positions = currentParsed.getPositions();
				Integer size = currentParsed.getChildren().size();
				for (Integer i = 0;i< size;++i){
					if (children.get(i).getChildren().isEmpty()==false){
						Token.Branch newToken = new Token.Branch(children.get(i).getName(),positions.get(i),currentPosition,this);
						current.add(newToken);
						setup(newToken,children.get(i),positions.get(i));
					}
					else {
						current.add(new Token.Leaf(children.get(i).getName(),children.get(i).getValue(),positions.get(i),currentPosition,this));
					}
				}
			}
			public String toString(){
				if (fileName!=null){
					return "Passed:\n\t"+super.toString();
				}
				else {
					return null;
				}
			}
		}
		public static class Fail extends Parser.Result{
			protected String ruleName = null;
			public Fail(final Integer initalSuperState, final Integer initalSuperPosition, final List<Integer> initalSuperLineNumberRanges, final String initalSuperInput, final String initalSuperFileName, final String ruleName) {
				super(initalSuperState, initalSuperPosition, initalSuperLineNumberRanges, initalSuperInput, initalSuperFileName);
				if(ruleName != null){
					this.ruleName = ruleName;
				}
			}
			public Fail(final String ruleName) {
				if(ruleName != null){
					this.ruleName = ruleName;
				}
			}
			public Fail() {
			}
			public String getRuleName(){
				return ruleName;
			}
			public String toString(){
				return "Failed:\n\tRule:"+ruleName+super.toString();
			}
		}
		public static class Acceptor extends Parser.Result{
			protected List<Parser.Result> results = new ArrayList<Parser.Result>();
			public Acceptor(final Integer initalSuperState, final Integer initalSuperPosition, final List<Integer> initalSuperLineNumberRanges, final String initalSuperInput, final String initalSuperFileName, final List<Parser.Result> results) {
				super(initalSuperState, initalSuperPosition, initalSuperLineNumberRanges, initalSuperInput, initalSuperFileName);
				if(results != null){
					this.results = results;
				}
			}
			public Acceptor(final List<Parser.Result> results) {
				if(results != null){
					this.results = results;
				}
			}
			public Acceptor() {
			}
			public List<Parser.Result> getResults(){
				return results;
			}
			public void add(Parser.Result result){
				result.setFileName(null);
				results.add(result);
			}
			public String toString(){
				StringBuilder builder = new StringBuilder();
				for (Parser.Result result: results){
					String resultString = result.toString();
					if (resultString!=null){
						builder.append("\n");
						builder.append(resultString);
					}
				}
				return super.toString()+builder.toString();
			}
		}
	}
}