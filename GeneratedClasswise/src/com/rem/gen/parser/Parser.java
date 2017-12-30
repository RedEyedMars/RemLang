package com.rem.gen.parser;
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
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public  class Parser{
	public static final Integer SUCCESS = 0;
	public static final Integer FAILED = 1;
	public static final Integer FIRST_PASS = 0;
	public static final Integer SECOND_PASS = 1;
	public static final List<String> fileNames = new ArrayList<String>();
	public static final Map<String,Parser.Context> contexts = new HashMap<String,Parser.Context>();
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
		class_names_root.add("StringBuilder");
		class_names_root.add("Thread");
		class_names_root.add("Runnable");
		class_names_root.add("Iterator");
		class_names_root.add("Iterable");
		class_names_root.add("Serializable");
		class_names_root.add("Random");
		class_names_root.add("Set");
		class_names_root.add("HashSet");
		class_names_root.add("TreeSet");
		class_names_root.add("List");
		class_names_root.add("ArrayList");
		class_names_root.add("LinkedList");
		class_names_root.add("Map");
		class_names_root.add("HashMap");
		class_names_root.add("Stack");
		class_names_root.add("Deque");
		class_names_root.add("File");
		class_names_root.add("FileReader");
		class_names_root.add("FileWriter");
		class_names_root.add("BufferedReader");
		class_names_root.add("BufferedWriter");
		class_names_root.add("Parser");
		class_names_root.add("Token");
		class_names_root.add("Result");
		class_names_root.add("Tokens");
		class_names_root.add("Pass");
		class_names_root.add("Fail");
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
		protected Set<Integer> _recursion_protection_method_argument_0 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_4 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_all_type_name_1 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_5 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_6 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_statement_2 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_7 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_statement_3 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_8 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_statement_4 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_5 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_9 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_element_6 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_7 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_comments_8 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_imports_9 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_declaration_10 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_declaration_11 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_12 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_base_element_13 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_quote_14 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_15 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_weak_16 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_packageName_17 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_18 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_19 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_20 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_21 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_weak_22 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_23 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_24 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_25 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_comments_26 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_declaration_27 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_declaration_28 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_29 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_30 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_argument_31 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_32 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_declaration_33 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_34 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_35 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_36 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_body_37 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_method_38 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_39 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_body_40 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_method_41 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_42 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_body_43 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_method_44 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_45 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_body_46 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_method_47 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_48 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_49 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_50 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_body_51 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_method_52 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_53 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_string_54 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_char_55 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_56 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_57 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_58 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_59 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_60 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_61 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_62 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_63 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_declaration_64 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_65 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_body_66 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_67 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_68 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_declaration_69 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_70 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_as_statement_71 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_72 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_body_73 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_74 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_75 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_76 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_method_77 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_78 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_79 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_80 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_81 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_82 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_method_83 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_quote_84 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_string_85 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_86 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_string_87 = new HashSet<Integer>();
		protected int _readInput_1 = 0;
		protected Parser.NameList class_variable_names_array_parameters = null;
		protected Parser.NameList variable_names_array_parameters = null;
		protected Parser.NameList class_variable_names_quote = null;
		protected Parser.NameList variable_names_quote = null;
		protected Parser.NameList class_variable_names_statement_as_quote = null;
		protected Parser.NameList variable_names_statement_as_quote = null;
		protected Parser.NameList class_names_additions__anonymous_42 = null;
		protected Parser.NameList class_variable_names_additions__anonymous_42 = null;
		protected Parser.NameList class_variable_names_class_body = null;
		protected Parser.NameList variable_names_class_body = null;
		protected Parser.NameList class_names_additions__anonymous_32 = null;
		protected Parser.NameList class_variable_names_additions__anonymous_32 = null;
		protected Parser.NameList variable_names_additions__anonymous_133 = null;
		protected Parser.NameList class_variable_names_additions__anonymous_134 = null;
		protected Parser.NameList class_variable_names_method_arguments = null;
		protected Parser.NameList variable_names_method_arguments = null;
		protected Parser.NameList class_variable_names_method_body = null;
		protected Parser.NameList variable_names_method_body = null;
		protected Parser.NameList class_variable_names_statement_as_braced = null;
		protected Parser.NameList variable_names_statement_as_braced = null;
		protected Parser.NameList class_names_additions_anonymous_class = null;
		protected Parser.NameList variable_names_additions_body_manipulate = null;
		protected Parser.NameList class_variable_names_as_statement = null;
		protected Parser.NameList variable_names_as_statement = null;
		protected Parser.NameList class_variable_names_statement_as_string = null;
		protected Parser.NameList variable_names_statement_as_string = null;
		protected Parser.NameList class_variable_names_comments = null;
		protected Parser.NameList variable_names_comments = null;
		protected Parser.NameList class_variable_names_template_parameters = null;
		protected Parser.NameList variable_names_template_parameters = null;
		protected Parser.NameList class_names_additions_class_declaration = null;
		protected Parser.NameList class_variable_names_additions_class_declaration = null;
		protected Parser.NameList class_variable_names_statement_as_method = null;
		protected Parser.NameList variable_names_statement_as_method = null;
		public Context(final String _input, final String _fileName, final char[] _inputArray, final Parser.Result _result, final Parser.Result.Acceptor _result_acceptor, final Boolean _succeedOnEnd, final String _list_name_result, final List<Integer> _lineNumberRanges, final Token.Parsed _root, final Token.Parsed _token, final Map<Integer,Integer> brace_0, final Parser.NameList class_names, final Parser.NameList class_names_additions, final Map<Integer,Parser.NameList> class_names_first_passes, final Parser.NameList class_variable_names, final Parser.NameList class_variable_names_additions, final Map<Integer,Parser.NameList> class_variable_names_first_passes, final Parser.NameList variable_names, final Parser.NameList variable_names_additions, final Map<Integer,Parser.NameList> variable_names_first_passes, final Map<Integer,Integer> brace_1, final Map<Integer,Integer> brace_2, final Map<Integer,Integer> brace_3, final Set<Integer> _recursion_protection_method_argument_0, final Map<Integer,Integer> brace_4, final Set<Integer> _recursion_protection_all_type_name_1, final Map<Integer,Integer> brace_5, final Map<Integer,Integer> brace_6, final Set<Integer> _recursion_protection_body_statement_2, final Map<Integer,Integer> brace_7, final Set<Integer> _recursion_protection_body_statement_3, final Map<Integer,Integer> brace_8, final Set<Integer> _recursion_protection_body_statement_4, final Set<Integer> _recursion_protection_body_statement_5, final Map<Integer,Integer> brace_9, final Set<Integer> _recursion_protection_body_element_6, final Set<Integer> _recursion_protection_body_statement_7, final Set<Integer> _recursion_protection_comments_8, final Set<Integer> _recursion_protection_imports_9, final Set<Integer> _recursion_protection_class_declaration_10, final Set<Integer> _recursion_protection_method_declaration_11, final Set<Integer> _recursion_protection_variable_declaration_12, final Set<Integer> _recursion_protection_base_element_13, final Set<Integer> _recursion_protection_quote_14, final Set<Integer> _recursion_protection_inner_15, final Set<Integer> _recursion_protection_weak_16, final Set<Integer> _recursion_protection_packageName_17, final Set<Integer> _recursion_protection_NAME_18, final Set<Integer> _recursion_protection_type_var_19, final Set<Integer> _recursion_protection_type_var_20, final Set<Integer> _recursion_protection_inner_21, final Set<Integer> _recursion_protection_weak_22, final Set<Integer> _recursion_protection_NAME_23, final Set<Integer> _recursion_protection_type_var_24, final Set<Integer> _recursion_protection_type_var_25, final Set<Integer> _recursion_protection_comments_26, final Set<Integer> _recursion_protection_class_declaration_27, final Set<Integer> _recursion_protection_method_declaration_28, final Set<Integer> _recursion_protection_variable_declaration_29, final Set<Integer> _recursion_protection_inner_30, final Set<Integer> _recursion_protection_method_argument_31, final Set<Integer> _recursion_protection_inner_32, final Set<Integer> _recursion_protection_class_declaration_33, final Set<Integer> _recursion_protection_variable_declaration_34, final Set<Integer> _recursion_protection_body_statement_35, final Set<Integer> _recursion_protection_inner_36, final Set<Integer> _recursion_protection_method_body_37, final Set<Integer> _recursion_protection_statement_as_method_38, final Set<Integer> _recursion_protection_inner_39, final Set<Integer> _recursion_protection_method_body_40, final Set<Integer> _recursion_protection_statement_as_method_41, final Set<Integer> _recursion_protection_inner_42, final Set<Integer> _recursion_protection_method_body_43, final Set<Integer> _recursion_protection_statement_as_method_44, final Set<Integer> _recursion_protection_inner_45, final Set<Integer> _recursion_protection_method_body_46, final Set<Integer> _recursion_protection_statement_as_method_47, final Set<Integer> _recursion_protection_inner_48, final Set<Integer> _recursion_protection_NAME_49, final Set<Integer> _recursion_protection_NAME_50, final Set<Integer> _recursion_protection_method_body_51, final Set<Integer> _recursion_protection_statement_as_method_52, final Set<Integer> _recursion_protection_inner_53, final Set<Integer> _recursion_protection_statement_as_string_54, final Set<Integer> _recursion_protection_statement_as_char_55, final Set<Integer> _recursion_protection_body_statement_56, final Set<Integer> _recursion_protection_NAME_57, final Set<Integer> _recursion_protection_name_var_58, final Set<Integer> _recursion_protection_name_var_59, final Set<Integer> _recursion_protection_NAME_60, final Set<Integer> _recursion_protection_type_var_61, final Set<Integer> _recursion_protection_NAME_62, final Set<Integer> _recursion_protection_inner_63, final Set<Integer> _recursion_protection_method_declaration_64, final Set<Integer> _recursion_protection_variable_declaration_65, final Set<Integer> _recursion_protection_method_body_66, final Set<Integer> _recursion_protection_inner_67, final Set<Integer> _recursion_protection_variable_declaration_68, final Set<Integer> _recursion_protection_class_declaration_69, final Set<Integer> _recursion_protection_variable_declaration_70, final Set<Integer> _recursion_protection_as_statement_71, final Set<Integer> _recursion_protection_body_statement_72, final Set<Integer> _recursion_protection_method_body_73, final Set<Integer> _recursion_protection_inner_74, final Set<Integer> _recursion_protection_name_var_75, final Set<Integer> _recursion_protection_NAME_76, final Set<Integer> _recursion_protection_statement_as_method_77, final Set<Integer> _recursion_protection_inner_78, final Set<Integer> _recursion_protection_name_var_79, final Set<Integer> _recursion_protection_NAME_80, final Set<Integer> _recursion_protection_name_var_81, final Set<Integer> _recursion_protection_NAME_82, final Set<Integer> _recursion_protection_statement_as_method_83, final Set<Integer> _recursion_protection_statement_as_quote_84, final Set<Integer> _recursion_protection_statement_as_string_85, final Set<Integer> _recursion_protection_NAME_86, final Set<Integer> _recursion_protection_statement_as_string_87, final Parser.NameList class_variable_names_array_parameters, final Parser.NameList variable_names_array_parameters, final Parser.NameList class_variable_names_quote, final Parser.NameList variable_names_quote, final Parser.NameList class_variable_names_statement_as_quote, final Parser.NameList variable_names_statement_as_quote, final Parser.NameList class_names_additions__anonymous_42, final Parser.NameList class_variable_names_additions__anonymous_42, final Parser.NameList class_variable_names_class_body, final Parser.NameList variable_names_class_body, final Parser.NameList class_names_additions__anonymous_32, final Parser.NameList class_variable_names_additions__anonymous_32, final Parser.NameList variable_names_additions__anonymous_133, final Parser.NameList class_variable_names_additions__anonymous_134, final Parser.NameList class_variable_names_method_arguments, final Parser.NameList variable_names_method_arguments, final Parser.NameList class_variable_names_method_body, final Parser.NameList variable_names_method_body, final Parser.NameList class_variable_names_statement_as_braced, final Parser.NameList variable_names_statement_as_braced, final Parser.NameList class_names_additions_anonymous_class, final Parser.NameList variable_names_additions_body_manipulate, final Parser.NameList class_variable_names_as_statement, final Parser.NameList variable_names_as_statement, final Parser.NameList class_variable_names_statement_as_string, final Parser.NameList variable_names_statement_as_string, final Parser.NameList class_variable_names_comments, final Parser.NameList variable_names_comments, final Parser.NameList class_variable_names_template_parameters, final Parser.NameList variable_names_template_parameters, final Parser.NameList class_names_additions_class_declaration, final Parser.NameList class_variable_names_additions_class_declaration, final Parser.NameList class_variable_names_statement_as_method, final Parser.NameList variable_names_statement_as_method) {
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
			if(_recursion_protection_method_argument_0 != null){
				this._recursion_protection_method_argument_0 = _recursion_protection_method_argument_0;
			}
			if(brace_4 != null){
				this.brace_4 = brace_4;
			}
			if(_recursion_protection_all_type_name_1 != null){
				this._recursion_protection_all_type_name_1 = _recursion_protection_all_type_name_1;
			}
			if(brace_5 != null){
				this.brace_5 = brace_5;
			}
			if(brace_6 != null){
				this.brace_6 = brace_6;
			}
			if(_recursion_protection_body_statement_2 != null){
				this._recursion_protection_body_statement_2 = _recursion_protection_body_statement_2;
			}
			if(brace_7 != null){
				this.brace_7 = brace_7;
			}
			if(_recursion_protection_body_statement_3 != null){
				this._recursion_protection_body_statement_3 = _recursion_protection_body_statement_3;
			}
			if(brace_8 != null){
				this.brace_8 = brace_8;
			}
			if(_recursion_protection_body_statement_4 != null){
				this._recursion_protection_body_statement_4 = _recursion_protection_body_statement_4;
			}
			if(_recursion_protection_body_statement_5 != null){
				this._recursion_protection_body_statement_5 = _recursion_protection_body_statement_5;
			}
			if(brace_9 != null){
				this.brace_9 = brace_9;
			}
			if(_recursion_protection_body_element_6 != null){
				this._recursion_protection_body_element_6 = _recursion_protection_body_element_6;
			}
			if(_recursion_protection_body_statement_7 != null){
				this._recursion_protection_body_statement_7 = _recursion_protection_body_statement_7;
			}
			if(_recursion_protection_comments_8 != null){
				this._recursion_protection_comments_8 = _recursion_protection_comments_8;
			}
			if(_recursion_protection_imports_9 != null){
				this._recursion_protection_imports_9 = _recursion_protection_imports_9;
			}
			if(_recursion_protection_class_declaration_10 != null){
				this._recursion_protection_class_declaration_10 = _recursion_protection_class_declaration_10;
			}
			if(_recursion_protection_method_declaration_11 != null){
				this._recursion_protection_method_declaration_11 = _recursion_protection_method_declaration_11;
			}
			if(_recursion_protection_variable_declaration_12 != null){
				this._recursion_protection_variable_declaration_12 = _recursion_protection_variable_declaration_12;
			}
			if(_recursion_protection_base_element_13 != null){
				this._recursion_protection_base_element_13 = _recursion_protection_base_element_13;
			}
			if(_recursion_protection_quote_14 != null){
				this._recursion_protection_quote_14 = _recursion_protection_quote_14;
			}
			if(_recursion_protection_inner_15 != null){
				this._recursion_protection_inner_15 = _recursion_protection_inner_15;
			}
			if(_recursion_protection_weak_16 != null){
				this._recursion_protection_weak_16 = _recursion_protection_weak_16;
			}
			if(_recursion_protection_packageName_17 != null){
				this._recursion_protection_packageName_17 = _recursion_protection_packageName_17;
			}
			if(_recursion_protection_NAME_18 != null){
				this._recursion_protection_NAME_18 = _recursion_protection_NAME_18;
			}
			if(_recursion_protection_type_var_19 != null){
				this._recursion_protection_type_var_19 = _recursion_protection_type_var_19;
			}
			if(_recursion_protection_type_var_20 != null){
				this._recursion_protection_type_var_20 = _recursion_protection_type_var_20;
			}
			if(_recursion_protection_inner_21 != null){
				this._recursion_protection_inner_21 = _recursion_protection_inner_21;
			}
			if(_recursion_protection_weak_22 != null){
				this._recursion_protection_weak_22 = _recursion_protection_weak_22;
			}
			if(_recursion_protection_NAME_23 != null){
				this._recursion_protection_NAME_23 = _recursion_protection_NAME_23;
			}
			if(_recursion_protection_type_var_24 != null){
				this._recursion_protection_type_var_24 = _recursion_protection_type_var_24;
			}
			if(_recursion_protection_type_var_25 != null){
				this._recursion_protection_type_var_25 = _recursion_protection_type_var_25;
			}
			if(_recursion_protection_comments_26 != null){
				this._recursion_protection_comments_26 = _recursion_protection_comments_26;
			}
			if(_recursion_protection_class_declaration_27 != null){
				this._recursion_protection_class_declaration_27 = _recursion_protection_class_declaration_27;
			}
			if(_recursion_protection_method_declaration_28 != null){
				this._recursion_protection_method_declaration_28 = _recursion_protection_method_declaration_28;
			}
			if(_recursion_protection_variable_declaration_29 != null){
				this._recursion_protection_variable_declaration_29 = _recursion_protection_variable_declaration_29;
			}
			if(_recursion_protection_inner_30 != null){
				this._recursion_protection_inner_30 = _recursion_protection_inner_30;
			}
			if(_recursion_protection_method_argument_31 != null){
				this._recursion_protection_method_argument_31 = _recursion_protection_method_argument_31;
			}
			if(_recursion_protection_inner_32 != null){
				this._recursion_protection_inner_32 = _recursion_protection_inner_32;
			}
			if(_recursion_protection_class_declaration_33 != null){
				this._recursion_protection_class_declaration_33 = _recursion_protection_class_declaration_33;
			}
			if(_recursion_protection_variable_declaration_34 != null){
				this._recursion_protection_variable_declaration_34 = _recursion_protection_variable_declaration_34;
			}
			if(_recursion_protection_body_statement_35 != null){
				this._recursion_protection_body_statement_35 = _recursion_protection_body_statement_35;
			}
			if(_recursion_protection_inner_36 != null){
				this._recursion_protection_inner_36 = _recursion_protection_inner_36;
			}
			if(_recursion_protection_method_body_37 != null){
				this._recursion_protection_method_body_37 = _recursion_protection_method_body_37;
			}
			if(_recursion_protection_statement_as_method_38 != null){
				this._recursion_protection_statement_as_method_38 = _recursion_protection_statement_as_method_38;
			}
			if(_recursion_protection_inner_39 != null){
				this._recursion_protection_inner_39 = _recursion_protection_inner_39;
			}
			if(_recursion_protection_method_body_40 != null){
				this._recursion_protection_method_body_40 = _recursion_protection_method_body_40;
			}
			if(_recursion_protection_statement_as_method_41 != null){
				this._recursion_protection_statement_as_method_41 = _recursion_protection_statement_as_method_41;
			}
			if(_recursion_protection_inner_42 != null){
				this._recursion_protection_inner_42 = _recursion_protection_inner_42;
			}
			if(_recursion_protection_method_body_43 != null){
				this._recursion_protection_method_body_43 = _recursion_protection_method_body_43;
			}
			if(_recursion_protection_statement_as_method_44 != null){
				this._recursion_protection_statement_as_method_44 = _recursion_protection_statement_as_method_44;
			}
			if(_recursion_protection_inner_45 != null){
				this._recursion_protection_inner_45 = _recursion_protection_inner_45;
			}
			if(_recursion_protection_method_body_46 != null){
				this._recursion_protection_method_body_46 = _recursion_protection_method_body_46;
			}
			if(_recursion_protection_statement_as_method_47 != null){
				this._recursion_protection_statement_as_method_47 = _recursion_protection_statement_as_method_47;
			}
			if(_recursion_protection_inner_48 != null){
				this._recursion_protection_inner_48 = _recursion_protection_inner_48;
			}
			if(_recursion_protection_NAME_49 != null){
				this._recursion_protection_NAME_49 = _recursion_protection_NAME_49;
			}
			if(_recursion_protection_NAME_50 != null){
				this._recursion_protection_NAME_50 = _recursion_protection_NAME_50;
			}
			if(_recursion_protection_method_body_51 != null){
				this._recursion_protection_method_body_51 = _recursion_protection_method_body_51;
			}
			if(_recursion_protection_statement_as_method_52 != null){
				this._recursion_protection_statement_as_method_52 = _recursion_protection_statement_as_method_52;
			}
			if(_recursion_protection_inner_53 != null){
				this._recursion_protection_inner_53 = _recursion_protection_inner_53;
			}
			if(_recursion_protection_statement_as_string_54 != null){
				this._recursion_protection_statement_as_string_54 = _recursion_protection_statement_as_string_54;
			}
			if(_recursion_protection_statement_as_char_55 != null){
				this._recursion_protection_statement_as_char_55 = _recursion_protection_statement_as_char_55;
			}
			if(_recursion_protection_body_statement_56 != null){
				this._recursion_protection_body_statement_56 = _recursion_protection_body_statement_56;
			}
			if(_recursion_protection_NAME_57 != null){
				this._recursion_protection_NAME_57 = _recursion_protection_NAME_57;
			}
			if(_recursion_protection_name_var_58 != null){
				this._recursion_protection_name_var_58 = _recursion_protection_name_var_58;
			}
			if(_recursion_protection_name_var_59 != null){
				this._recursion_protection_name_var_59 = _recursion_protection_name_var_59;
			}
			if(_recursion_protection_NAME_60 != null){
				this._recursion_protection_NAME_60 = _recursion_protection_NAME_60;
			}
			if(_recursion_protection_type_var_61 != null){
				this._recursion_protection_type_var_61 = _recursion_protection_type_var_61;
			}
			if(_recursion_protection_NAME_62 != null){
				this._recursion_protection_NAME_62 = _recursion_protection_NAME_62;
			}
			if(_recursion_protection_inner_63 != null){
				this._recursion_protection_inner_63 = _recursion_protection_inner_63;
			}
			if(_recursion_protection_method_declaration_64 != null){
				this._recursion_protection_method_declaration_64 = _recursion_protection_method_declaration_64;
			}
			if(_recursion_protection_variable_declaration_65 != null){
				this._recursion_protection_variable_declaration_65 = _recursion_protection_variable_declaration_65;
			}
			if(_recursion_protection_method_body_66 != null){
				this._recursion_protection_method_body_66 = _recursion_protection_method_body_66;
			}
			if(_recursion_protection_inner_67 != null){
				this._recursion_protection_inner_67 = _recursion_protection_inner_67;
			}
			if(_recursion_protection_variable_declaration_68 != null){
				this._recursion_protection_variable_declaration_68 = _recursion_protection_variable_declaration_68;
			}
			if(_recursion_protection_class_declaration_69 != null){
				this._recursion_protection_class_declaration_69 = _recursion_protection_class_declaration_69;
			}
			if(_recursion_protection_variable_declaration_70 != null){
				this._recursion_protection_variable_declaration_70 = _recursion_protection_variable_declaration_70;
			}
			if(_recursion_protection_as_statement_71 != null){
				this._recursion_protection_as_statement_71 = _recursion_protection_as_statement_71;
			}
			if(_recursion_protection_body_statement_72 != null){
				this._recursion_protection_body_statement_72 = _recursion_protection_body_statement_72;
			}
			if(_recursion_protection_method_body_73 != null){
				this._recursion_protection_method_body_73 = _recursion_protection_method_body_73;
			}
			if(_recursion_protection_inner_74 != null){
				this._recursion_protection_inner_74 = _recursion_protection_inner_74;
			}
			if(_recursion_protection_name_var_75 != null){
				this._recursion_protection_name_var_75 = _recursion_protection_name_var_75;
			}
			if(_recursion_protection_NAME_76 != null){
				this._recursion_protection_NAME_76 = _recursion_protection_NAME_76;
			}
			if(_recursion_protection_statement_as_method_77 != null){
				this._recursion_protection_statement_as_method_77 = _recursion_protection_statement_as_method_77;
			}
			if(_recursion_protection_inner_78 != null){
				this._recursion_protection_inner_78 = _recursion_protection_inner_78;
			}
			if(_recursion_protection_name_var_79 != null){
				this._recursion_protection_name_var_79 = _recursion_protection_name_var_79;
			}
			if(_recursion_protection_NAME_80 != null){
				this._recursion_protection_NAME_80 = _recursion_protection_NAME_80;
			}
			if(_recursion_protection_name_var_81 != null){
				this._recursion_protection_name_var_81 = _recursion_protection_name_var_81;
			}
			if(_recursion_protection_NAME_82 != null){
				this._recursion_protection_NAME_82 = _recursion_protection_NAME_82;
			}
			if(_recursion_protection_statement_as_method_83 != null){
				this._recursion_protection_statement_as_method_83 = _recursion_protection_statement_as_method_83;
			}
			if(_recursion_protection_statement_as_quote_84 != null){
				this._recursion_protection_statement_as_quote_84 = _recursion_protection_statement_as_quote_84;
			}
			if(_recursion_protection_statement_as_string_85 != null){
				this._recursion_protection_statement_as_string_85 = _recursion_protection_statement_as_string_85;
			}
			if(_recursion_protection_NAME_86 != null){
				this._recursion_protection_NAME_86 = _recursion_protection_NAME_86;
			}
			if(_recursion_protection_statement_as_string_87 != null){
				this._recursion_protection_statement_as_string_87 = _recursion_protection_statement_as_string_87;
			}
			if(class_variable_names_array_parameters != null){
				this.class_variable_names_array_parameters = class_variable_names_array_parameters;
			}
			if(variable_names_array_parameters != null){
				this.variable_names_array_parameters = variable_names_array_parameters;
			}
			if(class_variable_names_quote != null){
				this.class_variable_names_quote = class_variable_names_quote;
			}
			if(variable_names_quote != null){
				this.variable_names_quote = variable_names_quote;
			}
			if(class_variable_names_statement_as_quote != null){
				this.class_variable_names_statement_as_quote = class_variable_names_statement_as_quote;
			}
			if(variable_names_statement_as_quote != null){
				this.variable_names_statement_as_quote = variable_names_statement_as_quote;
			}
			if(class_names_additions__anonymous_42 != null){
				this.class_names_additions__anonymous_42 = class_names_additions__anonymous_42;
			}
			if(class_variable_names_additions__anonymous_42 != null){
				this.class_variable_names_additions__anonymous_42 = class_variable_names_additions__anonymous_42;
			}
			if(class_variable_names_class_body != null){
				this.class_variable_names_class_body = class_variable_names_class_body;
			}
			if(variable_names_class_body != null){
				this.variable_names_class_body = variable_names_class_body;
			}
			if(class_names_additions__anonymous_32 != null){
				this.class_names_additions__anonymous_32 = class_names_additions__anonymous_32;
			}
			if(class_variable_names_additions__anonymous_32 != null){
				this.class_variable_names_additions__anonymous_32 = class_variable_names_additions__anonymous_32;
			}
			if(variable_names_additions__anonymous_133 != null){
				this.variable_names_additions__anonymous_133 = variable_names_additions__anonymous_133;
			}
			if(class_variable_names_additions__anonymous_134 != null){
				this.class_variable_names_additions__anonymous_134 = class_variable_names_additions__anonymous_134;
			}
			if(class_variable_names_method_arguments != null){
				this.class_variable_names_method_arguments = class_variable_names_method_arguments;
			}
			if(variable_names_method_arguments != null){
				this.variable_names_method_arguments = variable_names_method_arguments;
			}
			if(class_variable_names_method_body != null){
				this.class_variable_names_method_body = class_variable_names_method_body;
			}
			if(variable_names_method_body != null){
				this.variable_names_method_body = variable_names_method_body;
			}
			if(class_variable_names_statement_as_braced != null){
				this.class_variable_names_statement_as_braced = class_variable_names_statement_as_braced;
			}
			if(variable_names_statement_as_braced != null){
				this.variable_names_statement_as_braced = variable_names_statement_as_braced;
			}
			if(class_names_additions_anonymous_class != null){
				this.class_names_additions_anonymous_class = class_names_additions_anonymous_class;
			}
			if(variable_names_additions_body_manipulate != null){
				this.variable_names_additions_body_manipulate = variable_names_additions_body_manipulate;
			}
			if(class_variable_names_as_statement != null){
				this.class_variable_names_as_statement = class_variable_names_as_statement;
			}
			if(variable_names_as_statement != null){
				this.variable_names_as_statement = variable_names_as_statement;
			}
			if(class_variable_names_statement_as_string != null){
				this.class_variable_names_statement_as_string = class_variable_names_statement_as_string;
			}
			if(variable_names_statement_as_string != null){
				this.variable_names_statement_as_string = variable_names_statement_as_string;
			}
			if(class_variable_names_comments != null){
				this.class_variable_names_comments = class_variable_names_comments;
			}
			if(variable_names_comments != null){
				this.variable_names_comments = variable_names_comments;
			}
			if(class_variable_names_template_parameters != null){
				this.class_variable_names_template_parameters = class_variable_names_template_parameters;
			}
			if(variable_names_template_parameters != null){
				this.variable_names_template_parameters = variable_names_template_parameters;
			}
			if(class_names_additions_class_declaration != null){
				this.class_names_additions_class_declaration = class_names_additions_class_declaration;
			}
			if(class_variable_names_additions_class_declaration != null){
				this.class_variable_names_additions_class_declaration = class_variable_names_additions_class_declaration;
			}
			if(class_variable_names_statement_as_method != null){
				this.class_variable_names_statement_as_method = class_variable_names_statement_as_method;
			}
			if(variable_names_statement_as_method != null){
				this.variable_names_statement_as_method = variable_names_statement_as_method;
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
		public void set_input(String new_input){
			_input = new_input;
		}
		public String get_fileName(){
			return _fileName;
		}
		public void set_fileName(String new_fileName){
			_fileName = new_fileName;
		}
		public char[] get_inputArray(){
			return _inputArray;
		}
		public void set_inputArray(char[] new_inputArray){
			_inputArray = new_inputArray;
		}
		public Parser.Result get_result(){
			return _result;
		}
		public void set_result(Parser.Result new_result){
			_result = new_result;
		}
		public Parser.Result.Acceptor get_resultAcceptor(){
			return _result_acceptor;
		}
		public void set_resultAcceptor(Parser.Result.Acceptor new_resultAcceptor){
			_result_acceptor = new_resultAcceptor;
		}
		public Boolean get_succeedOnEnd(){
			return _succeedOnEnd;
		}
		public void set_succeedOnEnd(Boolean new_succeedOnEnd){
			_succeedOnEnd = new_succeedOnEnd;
		}
		public String get_listNameResult(){
			return _list_name_result;
		}
		public void set_listNameResult(String new_listNameResult){
			_list_name_result = new_listNameResult;
		}
		public List<Integer> get_lineNumberRanges(){
			return _lineNumberRanges;
		}
		public void set_lineNumberRanges(List<Integer> new_lineNumberRanges){
			_lineNumberRanges = new_lineNumberRanges;
		}
		public Token.Parsed get_root(){
			return _root;
		}
		public void set_root(Token.Parsed new_root){
			_root = new_root;
		}
		public Token.Parsed get_token(){
			return _token;
		}
		public void set_token(Token.Parsed new_token){
			_token = new_token;
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
		public void setBrace0(Map<Integer,Integer> newBrace0){
			brace_0 = newBrace0;
		}
		public Parser.NameList getClassNames(){
			return class_names;
		}
		public void setClassNames(Parser.NameList newClassNames){
			class_names = newClassNames;
		}
		public Parser.NameList getClassNamesAdditions(){
			return class_names_additions;
		}
		public void setClassNamesAdditions(Parser.NameList newClassNamesAdditions){
			class_names_additions = newClassNamesAdditions;
		}
		public Map<Integer,Parser.NameList> getClassNamesFirstPasses(){
			return class_names_first_passes;
		}
		public void setClassNamesFirstPasses(Map<Integer,Parser.NameList> newClassNamesFirstPasses){
			class_names_first_passes = newClassNamesFirstPasses;
		}
		public Parser.NameList getClassVariableNames(){
			return class_variable_names;
		}
		public void setClassVariableNames(Parser.NameList newClassVariableNames){
			class_variable_names = newClassVariableNames;
		}
		public Parser.NameList getClassVariableNamesAdditions(){
			return class_variable_names_additions;
		}
		public void setClassVariableNamesAdditions(Parser.NameList newClassVariableNamesAdditions){
			class_variable_names_additions = newClassVariableNamesAdditions;
		}
		public Map<Integer,Parser.NameList> getClassVariableNamesFirstPasses(){
			return class_variable_names_first_passes;
		}
		public void setClassVariableNamesFirstPasses(Map<Integer,Parser.NameList> newClassVariableNamesFirstPasses){
			class_variable_names_first_passes = newClassVariableNamesFirstPasses;
		}
		public Parser.NameList getVariableNames(){
			return variable_names;
		}
		public void setVariableNames(Parser.NameList newVariableNames){
			variable_names = newVariableNames;
		}
		public Parser.NameList getVariableNamesAdditions(){
			return variable_names_additions;
		}
		public void setVariableNamesAdditions(Parser.NameList newVariableNamesAdditions){
			variable_names_additions = newVariableNamesAdditions;
		}
		public Map<Integer,Parser.NameList> getVariableNamesFirstPasses(){
			return variable_names_first_passes;
		}
		public void setVariableNamesFirstPasses(Map<Integer,Parser.NameList> newVariableNamesFirstPasses){
			variable_names_first_passes = newVariableNamesFirstPasses;
		}
		public Map<Integer,Integer> getBrace1(){
			return brace_1;
		}
		public void setBrace1(Map<Integer,Integer> newBrace1){
			brace_1 = newBrace1;
		}
		public Map<Integer,Integer> getBrace2(){
			return brace_2;
		}
		public void setBrace2(Map<Integer,Integer> newBrace2){
			brace_2 = newBrace2;
		}
		public Map<Integer,Integer> getBrace3(){
			return brace_3;
		}
		public void setBrace3(Map<Integer,Integer> newBrace3){
			brace_3 = newBrace3;
		}
		public Set<Integer> get_recursionProtectionMethodArgument0(){
			return _recursion_protection_method_argument_0;
		}
		public void set_recursionProtectionMethodArgument0(Set<Integer> new_recursionProtectionMethodArgument0){
			_recursion_protection_method_argument_0 = new_recursionProtectionMethodArgument0;
		}
		public Map<Integer,Integer> getBrace4(){
			return brace_4;
		}
		public void setBrace4(Map<Integer,Integer> newBrace4){
			brace_4 = newBrace4;
		}
		public Set<Integer> get_recursionProtectionAllTypeName1(){
			return _recursion_protection_all_type_name_1;
		}
		public void set_recursionProtectionAllTypeName1(Set<Integer> new_recursionProtectionAllTypeName1){
			_recursion_protection_all_type_name_1 = new_recursionProtectionAllTypeName1;
		}
		public Map<Integer,Integer> getBrace5(){
			return brace_5;
		}
		public void setBrace5(Map<Integer,Integer> newBrace5){
			brace_5 = newBrace5;
		}
		public Map<Integer,Integer> getBrace6(){
			return brace_6;
		}
		public void setBrace6(Map<Integer,Integer> newBrace6){
			brace_6 = newBrace6;
		}
		public Set<Integer> get_recursionProtectionBodyStatement2(){
			return _recursion_protection_body_statement_2;
		}
		public void set_recursionProtectionBodyStatement2(Set<Integer> new_recursionProtectionBodyStatement2){
			_recursion_protection_body_statement_2 = new_recursionProtectionBodyStatement2;
		}
		public Map<Integer,Integer> getBrace7(){
			return brace_7;
		}
		public void setBrace7(Map<Integer,Integer> newBrace7){
			brace_7 = newBrace7;
		}
		public Set<Integer> get_recursionProtectionBodyStatement3(){
			return _recursion_protection_body_statement_3;
		}
		public void set_recursionProtectionBodyStatement3(Set<Integer> new_recursionProtectionBodyStatement3){
			_recursion_protection_body_statement_3 = new_recursionProtectionBodyStatement3;
		}
		public Map<Integer,Integer> getBrace8(){
			return brace_8;
		}
		public void setBrace8(Map<Integer,Integer> newBrace8){
			brace_8 = newBrace8;
		}
		public Set<Integer> get_recursionProtectionBodyStatement4(){
			return _recursion_protection_body_statement_4;
		}
		public void set_recursionProtectionBodyStatement4(Set<Integer> new_recursionProtectionBodyStatement4){
			_recursion_protection_body_statement_4 = new_recursionProtectionBodyStatement4;
		}
		public Set<Integer> get_recursionProtectionBodyStatement5(){
			return _recursion_protection_body_statement_5;
		}
		public void set_recursionProtectionBodyStatement5(Set<Integer> new_recursionProtectionBodyStatement5){
			_recursion_protection_body_statement_5 = new_recursionProtectionBodyStatement5;
		}
		public Map<Integer,Integer> getBrace9(){
			return brace_9;
		}
		public void setBrace9(Map<Integer,Integer> newBrace9){
			brace_9 = newBrace9;
		}
		public Set<Integer> get_recursionProtectionBodyElement6(){
			return _recursion_protection_body_element_6;
		}
		public void set_recursionProtectionBodyElement6(Set<Integer> new_recursionProtectionBodyElement6){
			_recursion_protection_body_element_6 = new_recursionProtectionBodyElement6;
		}
		public Set<Integer> get_recursionProtectionBodyStatement7(){
			return _recursion_protection_body_statement_7;
		}
		public void set_recursionProtectionBodyStatement7(Set<Integer> new_recursionProtectionBodyStatement7){
			_recursion_protection_body_statement_7 = new_recursionProtectionBodyStatement7;
		}
		public Set<Integer> get_recursionProtectionComments8(){
			return _recursion_protection_comments_8;
		}
		public void set_recursionProtectionComments8(Set<Integer> new_recursionProtectionComments8){
			_recursion_protection_comments_8 = new_recursionProtectionComments8;
		}
		public Set<Integer> get_recursionProtectionImports9(){
			return _recursion_protection_imports_9;
		}
		public void set_recursionProtectionImports9(Set<Integer> new_recursionProtectionImports9){
			_recursion_protection_imports_9 = new_recursionProtectionImports9;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration10(){
			return _recursion_protection_class_declaration_10;
		}
		public void set_recursionProtectionClassDeclaration10(Set<Integer> new_recursionProtectionClassDeclaration10){
			_recursion_protection_class_declaration_10 = new_recursionProtectionClassDeclaration10;
		}
		public Set<Integer> get_recursionProtectionMethodDeclaration11(){
			return _recursion_protection_method_declaration_11;
		}
		public void set_recursionProtectionMethodDeclaration11(Set<Integer> new_recursionProtectionMethodDeclaration11){
			_recursion_protection_method_declaration_11 = new_recursionProtectionMethodDeclaration11;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration12(){
			return _recursion_protection_variable_declaration_12;
		}
		public void set_recursionProtectionVariableDeclaration12(Set<Integer> new_recursionProtectionVariableDeclaration12){
			_recursion_protection_variable_declaration_12 = new_recursionProtectionVariableDeclaration12;
		}
		public Set<Integer> get_recursionProtectionBaseElement13(){
			return _recursion_protection_base_element_13;
		}
		public void set_recursionProtectionBaseElement13(Set<Integer> new_recursionProtectionBaseElement13){
			_recursion_protection_base_element_13 = new_recursionProtectionBaseElement13;
		}
		public Set<Integer> get_recursionProtectionQuote14(){
			return _recursion_protection_quote_14;
		}
		public void set_recursionProtectionQuote14(Set<Integer> new_recursionProtectionQuote14){
			_recursion_protection_quote_14 = new_recursionProtectionQuote14;
		}
		public Set<Integer> get_recursionProtectionInner15(){
			return _recursion_protection_inner_15;
		}
		public void set_recursionProtectionInner15(Set<Integer> new_recursionProtectionInner15){
			_recursion_protection_inner_15 = new_recursionProtectionInner15;
		}
		public Set<Integer> get_recursionProtectionWeak16(){
			return _recursion_protection_weak_16;
		}
		public void set_recursionProtectionWeak16(Set<Integer> new_recursionProtectionWeak16){
			_recursion_protection_weak_16 = new_recursionProtectionWeak16;
		}
		public Set<Integer> get_recursionProtectionPackageName17(){
			return _recursion_protection_packageName_17;
		}
		public void set_recursionProtectionPackageName17(Set<Integer> new_recursionProtectionPackageName17){
			_recursion_protection_packageName_17 = new_recursionProtectionPackageName17;
		}
		public Set<Integer> get_recursionProtectionNAME18(){
			return _recursion_protection_NAME_18;
		}
		public void set_recursionProtectionNAME18(Set<Integer> new_recursionProtectionNAME18){
			_recursion_protection_NAME_18 = new_recursionProtectionNAME18;
		}
		public Set<Integer> get_recursionProtectionTypeVar19(){
			return _recursion_protection_type_var_19;
		}
		public void set_recursionProtectionTypeVar19(Set<Integer> new_recursionProtectionTypeVar19){
			_recursion_protection_type_var_19 = new_recursionProtectionTypeVar19;
		}
		public Set<Integer> get_recursionProtectionTypeVar20(){
			return _recursion_protection_type_var_20;
		}
		public void set_recursionProtectionTypeVar20(Set<Integer> new_recursionProtectionTypeVar20){
			_recursion_protection_type_var_20 = new_recursionProtectionTypeVar20;
		}
		public Set<Integer> get_recursionProtectionInner21(){
			return _recursion_protection_inner_21;
		}
		public void set_recursionProtectionInner21(Set<Integer> new_recursionProtectionInner21){
			_recursion_protection_inner_21 = new_recursionProtectionInner21;
		}
		public Set<Integer> get_recursionProtectionWeak22(){
			return _recursion_protection_weak_22;
		}
		public void set_recursionProtectionWeak22(Set<Integer> new_recursionProtectionWeak22){
			_recursion_protection_weak_22 = new_recursionProtectionWeak22;
		}
		public Set<Integer> get_recursionProtectionNAME23(){
			return _recursion_protection_NAME_23;
		}
		public void set_recursionProtectionNAME23(Set<Integer> new_recursionProtectionNAME23){
			_recursion_protection_NAME_23 = new_recursionProtectionNAME23;
		}
		public Set<Integer> get_recursionProtectionTypeVar24(){
			return _recursion_protection_type_var_24;
		}
		public void set_recursionProtectionTypeVar24(Set<Integer> new_recursionProtectionTypeVar24){
			_recursion_protection_type_var_24 = new_recursionProtectionTypeVar24;
		}
		public Set<Integer> get_recursionProtectionTypeVar25(){
			return _recursion_protection_type_var_25;
		}
		public void set_recursionProtectionTypeVar25(Set<Integer> new_recursionProtectionTypeVar25){
			_recursion_protection_type_var_25 = new_recursionProtectionTypeVar25;
		}
		public Set<Integer> get_recursionProtectionComments26(){
			return _recursion_protection_comments_26;
		}
		public void set_recursionProtectionComments26(Set<Integer> new_recursionProtectionComments26){
			_recursion_protection_comments_26 = new_recursionProtectionComments26;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration27(){
			return _recursion_protection_class_declaration_27;
		}
		public void set_recursionProtectionClassDeclaration27(Set<Integer> new_recursionProtectionClassDeclaration27){
			_recursion_protection_class_declaration_27 = new_recursionProtectionClassDeclaration27;
		}
		public Set<Integer> get_recursionProtectionMethodDeclaration28(){
			return _recursion_protection_method_declaration_28;
		}
		public void set_recursionProtectionMethodDeclaration28(Set<Integer> new_recursionProtectionMethodDeclaration28){
			_recursion_protection_method_declaration_28 = new_recursionProtectionMethodDeclaration28;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration29(){
			return _recursion_protection_variable_declaration_29;
		}
		public void set_recursionProtectionVariableDeclaration29(Set<Integer> new_recursionProtectionVariableDeclaration29){
			_recursion_protection_variable_declaration_29 = new_recursionProtectionVariableDeclaration29;
		}
		public Set<Integer> get_recursionProtectionInner30(){
			return _recursion_protection_inner_30;
		}
		public void set_recursionProtectionInner30(Set<Integer> new_recursionProtectionInner30){
			_recursion_protection_inner_30 = new_recursionProtectionInner30;
		}
		public Set<Integer> get_recursionProtectionMethodArgument31(){
			return _recursion_protection_method_argument_31;
		}
		public void set_recursionProtectionMethodArgument31(Set<Integer> new_recursionProtectionMethodArgument31){
			_recursion_protection_method_argument_31 = new_recursionProtectionMethodArgument31;
		}
		public Set<Integer> get_recursionProtectionInner32(){
			return _recursion_protection_inner_32;
		}
		public void set_recursionProtectionInner32(Set<Integer> new_recursionProtectionInner32){
			_recursion_protection_inner_32 = new_recursionProtectionInner32;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration33(){
			return _recursion_protection_class_declaration_33;
		}
		public void set_recursionProtectionClassDeclaration33(Set<Integer> new_recursionProtectionClassDeclaration33){
			_recursion_protection_class_declaration_33 = new_recursionProtectionClassDeclaration33;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration34(){
			return _recursion_protection_variable_declaration_34;
		}
		public void set_recursionProtectionVariableDeclaration34(Set<Integer> new_recursionProtectionVariableDeclaration34){
			_recursion_protection_variable_declaration_34 = new_recursionProtectionVariableDeclaration34;
		}
		public Set<Integer> get_recursionProtectionBodyStatement35(){
			return _recursion_protection_body_statement_35;
		}
		public void set_recursionProtectionBodyStatement35(Set<Integer> new_recursionProtectionBodyStatement35){
			_recursion_protection_body_statement_35 = new_recursionProtectionBodyStatement35;
		}
		public Set<Integer> get_recursionProtectionInner36(){
			return _recursion_protection_inner_36;
		}
		public void set_recursionProtectionInner36(Set<Integer> new_recursionProtectionInner36){
			_recursion_protection_inner_36 = new_recursionProtectionInner36;
		}
		public Set<Integer> get_recursionProtectionMethodBody37(){
			return _recursion_protection_method_body_37;
		}
		public void set_recursionProtectionMethodBody37(Set<Integer> new_recursionProtectionMethodBody37){
			_recursion_protection_method_body_37 = new_recursionProtectionMethodBody37;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod38(){
			return _recursion_protection_statement_as_method_38;
		}
		public void set_recursionProtectionStatementAsMethod38(Set<Integer> new_recursionProtectionStatementAsMethod38){
			_recursion_protection_statement_as_method_38 = new_recursionProtectionStatementAsMethod38;
		}
		public Set<Integer> get_recursionProtectionInner39(){
			return _recursion_protection_inner_39;
		}
		public void set_recursionProtectionInner39(Set<Integer> new_recursionProtectionInner39){
			_recursion_protection_inner_39 = new_recursionProtectionInner39;
		}
		public Set<Integer> get_recursionProtectionMethodBody40(){
			return _recursion_protection_method_body_40;
		}
		public void set_recursionProtectionMethodBody40(Set<Integer> new_recursionProtectionMethodBody40){
			_recursion_protection_method_body_40 = new_recursionProtectionMethodBody40;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod41(){
			return _recursion_protection_statement_as_method_41;
		}
		public void set_recursionProtectionStatementAsMethod41(Set<Integer> new_recursionProtectionStatementAsMethod41){
			_recursion_protection_statement_as_method_41 = new_recursionProtectionStatementAsMethod41;
		}
		public Set<Integer> get_recursionProtectionInner42(){
			return _recursion_protection_inner_42;
		}
		public void set_recursionProtectionInner42(Set<Integer> new_recursionProtectionInner42){
			_recursion_protection_inner_42 = new_recursionProtectionInner42;
		}
		public Set<Integer> get_recursionProtectionMethodBody43(){
			return _recursion_protection_method_body_43;
		}
		public void set_recursionProtectionMethodBody43(Set<Integer> new_recursionProtectionMethodBody43){
			_recursion_protection_method_body_43 = new_recursionProtectionMethodBody43;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod44(){
			return _recursion_protection_statement_as_method_44;
		}
		public void set_recursionProtectionStatementAsMethod44(Set<Integer> new_recursionProtectionStatementAsMethod44){
			_recursion_protection_statement_as_method_44 = new_recursionProtectionStatementAsMethod44;
		}
		public Set<Integer> get_recursionProtectionInner45(){
			return _recursion_protection_inner_45;
		}
		public void set_recursionProtectionInner45(Set<Integer> new_recursionProtectionInner45){
			_recursion_protection_inner_45 = new_recursionProtectionInner45;
		}
		public Set<Integer> get_recursionProtectionMethodBody46(){
			return _recursion_protection_method_body_46;
		}
		public void set_recursionProtectionMethodBody46(Set<Integer> new_recursionProtectionMethodBody46){
			_recursion_protection_method_body_46 = new_recursionProtectionMethodBody46;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod47(){
			return _recursion_protection_statement_as_method_47;
		}
		public void set_recursionProtectionStatementAsMethod47(Set<Integer> new_recursionProtectionStatementAsMethod47){
			_recursion_protection_statement_as_method_47 = new_recursionProtectionStatementAsMethod47;
		}
		public Set<Integer> get_recursionProtectionInner48(){
			return _recursion_protection_inner_48;
		}
		public void set_recursionProtectionInner48(Set<Integer> new_recursionProtectionInner48){
			_recursion_protection_inner_48 = new_recursionProtectionInner48;
		}
		public Set<Integer> get_recursionProtectionNAME49(){
			return _recursion_protection_NAME_49;
		}
		public void set_recursionProtectionNAME49(Set<Integer> new_recursionProtectionNAME49){
			_recursion_protection_NAME_49 = new_recursionProtectionNAME49;
		}
		public Set<Integer> get_recursionProtectionNAME50(){
			return _recursion_protection_NAME_50;
		}
		public void set_recursionProtectionNAME50(Set<Integer> new_recursionProtectionNAME50){
			_recursion_protection_NAME_50 = new_recursionProtectionNAME50;
		}
		public Set<Integer> get_recursionProtectionMethodBody51(){
			return _recursion_protection_method_body_51;
		}
		public void set_recursionProtectionMethodBody51(Set<Integer> new_recursionProtectionMethodBody51){
			_recursion_protection_method_body_51 = new_recursionProtectionMethodBody51;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod52(){
			return _recursion_protection_statement_as_method_52;
		}
		public void set_recursionProtectionStatementAsMethod52(Set<Integer> new_recursionProtectionStatementAsMethod52){
			_recursion_protection_statement_as_method_52 = new_recursionProtectionStatementAsMethod52;
		}
		public Set<Integer> get_recursionProtectionInner53(){
			return _recursion_protection_inner_53;
		}
		public void set_recursionProtectionInner53(Set<Integer> new_recursionProtectionInner53){
			_recursion_protection_inner_53 = new_recursionProtectionInner53;
		}
		public Set<Integer> get_recursionProtectionStatementAsString54(){
			return _recursion_protection_statement_as_string_54;
		}
		public void set_recursionProtectionStatementAsString54(Set<Integer> new_recursionProtectionStatementAsString54){
			_recursion_protection_statement_as_string_54 = new_recursionProtectionStatementAsString54;
		}
		public Set<Integer> get_recursionProtectionStatementAsChar55(){
			return _recursion_protection_statement_as_char_55;
		}
		public void set_recursionProtectionStatementAsChar55(Set<Integer> new_recursionProtectionStatementAsChar55){
			_recursion_protection_statement_as_char_55 = new_recursionProtectionStatementAsChar55;
		}
		public Set<Integer> get_recursionProtectionBodyStatement56(){
			return _recursion_protection_body_statement_56;
		}
		public void set_recursionProtectionBodyStatement56(Set<Integer> new_recursionProtectionBodyStatement56){
			_recursion_protection_body_statement_56 = new_recursionProtectionBodyStatement56;
		}
		public Set<Integer> get_recursionProtectionNAME57(){
			return _recursion_protection_NAME_57;
		}
		public void set_recursionProtectionNAME57(Set<Integer> new_recursionProtectionNAME57){
			_recursion_protection_NAME_57 = new_recursionProtectionNAME57;
		}
		public Set<Integer> get_recursionProtectionNameVar58(){
			return _recursion_protection_name_var_58;
		}
		public void set_recursionProtectionNameVar58(Set<Integer> new_recursionProtectionNameVar58){
			_recursion_protection_name_var_58 = new_recursionProtectionNameVar58;
		}
		public Set<Integer> get_recursionProtectionNameVar59(){
			return _recursion_protection_name_var_59;
		}
		public void set_recursionProtectionNameVar59(Set<Integer> new_recursionProtectionNameVar59){
			_recursion_protection_name_var_59 = new_recursionProtectionNameVar59;
		}
		public Set<Integer> get_recursionProtectionNAME60(){
			return _recursion_protection_NAME_60;
		}
		public void set_recursionProtectionNAME60(Set<Integer> new_recursionProtectionNAME60){
			_recursion_protection_NAME_60 = new_recursionProtectionNAME60;
		}
		public Set<Integer> get_recursionProtectionTypeVar61(){
			return _recursion_protection_type_var_61;
		}
		public void set_recursionProtectionTypeVar61(Set<Integer> new_recursionProtectionTypeVar61){
			_recursion_protection_type_var_61 = new_recursionProtectionTypeVar61;
		}
		public Set<Integer> get_recursionProtectionNAME62(){
			return _recursion_protection_NAME_62;
		}
		public void set_recursionProtectionNAME62(Set<Integer> new_recursionProtectionNAME62){
			_recursion_protection_NAME_62 = new_recursionProtectionNAME62;
		}
		public Set<Integer> get_recursionProtectionInner63(){
			return _recursion_protection_inner_63;
		}
		public void set_recursionProtectionInner63(Set<Integer> new_recursionProtectionInner63){
			_recursion_protection_inner_63 = new_recursionProtectionInner63;
		}
		public Set<Integer> get_recursionProtectionMethodDeclaration64(){
			return _recursion_protection_method_declaration_64;
		}
		public void set_recursionProtectionMethodDeclaration64(Set<Integer> new_recursionProtectionMethodDeclaration64){
			_recursion_protection_method_declaration_64 = new_recursionProtectionMethodDeclaration64;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration65(){
			return _recursion_protection_variable_declaration_65;
		}
		public void set_recursionProtectionVariableDeclaration65(Set<Integer> new_recursionProtectionVariableDeclaration65){
			_recursion_protection_variable_declaration_65 = new_recursionProtectionVariableDeclaration65;
		}
		public Set<Integer> get_recursionProtectionMethodBody66(){
			return _recursion_protection_method_body_66;
		}
		public void set_recursionProtectionMethodBody66(Set<Integer> new_recursionProtectionMethodBody66){
			_recursion_protection_method_body_66 = new_recursionProtectionMethodBody66;
		}
		public Set<Integer> get_recursionProtectionInner67(){
			return _recursion_protection_inner_67;
		}
		public void set_recursionProtectionInner67(Set<Integer> new_recursionProtectionInner67){
			_recursion_protection_inner_67 = new_recursionProtectionInner67;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration68(){
			return _recursion_protection_variable_declaration_68;
		}
		public void set_recursionProtectionVariableDeclaration68(Set<Integer> new_recursionProtectionVariableDeclaration68){
			_recursion_protection_variable_declaration_68 = new_recursionProtectionVariableDeclaration68;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration69(){
			return _recursion_protection_class_declaration_69;
		}
		public void set_recursionProtectionClassDeclaration69(Set<Integer> new_recursionProtectionClassDeclaration69){
			_recursion_protection_class_declaration_69 = new_recursionProtectionClassDeclaration69;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration70(){
			return _recursion_protection_variable_declaration_70;
		}
		public void set_recursionProtectionVariableDeclaration70(Set<Integer> new_recursionProtectionVariableDeclaration70){
			_recursion_protection_variable_declaration_70 = new_recursionProtectionVariableDeclaration70;
		}
		public Set<Integer> get_recursionProtectionAsStatement71(){
			return _recursion_protection_as_statement_71;
		}
		public void set_recursionProtectionAsStatement71(Set<Integer> new_recursionProtectionAsStatement71){
			_recursion_protection_as_statement_71 = new_recursionProtectionAsStatement71;
		}
		public Set<Integer> get_recursionProtectionBodyStatement72(){
			return _recursion_protection_body_statement_72;
		}
		public void set_recursionProtectionBodyStatement72(Set<Integer> new_recursionProtectionBodyStatement72){
			_recursion_protection_body_statement_72 = new_recursionProtectionBodyStatement72;
		}
		public Set<Integer> get_recursionProtectionMethodBody73(){
			return _recursion_protection_method_body_73;
		}
		public void set_recursionProtectionMethodBody73(Set<Integer> new_recursionProtectionMethodBody73){
			_recursion_protection_method_body_73 = new_recursionProtectionMethodBody73;
		}
		public Set<Integer> get_recursionProtectionInner74(){
			return _recursion_protection_inner_74;
		}
		public void set_recursionProtectionInner74(Set<Integer> new_recursionProtectionInner74){
			_recursion_protection_inner_74 = new_recursionProtectionInner74;
		}
		public Set<Integer> get_recursionProtectionNameVar75(){
			return _recursion_protection_name_var_75;
		}
		public void set_recursionProtectionNameVar75(Set<Integer> new_recursionProtectionNameVar75){
			_recursion_protection_name_var_75 = new_recursionProtectionNameVar75;
		}
		public Set<Integer> get_recursionProtectionNAME76(){
			return _recursion_protection_NAME_76;
		}
		public void set_recursionProtectionNAME76(Set<Integer> new_recursionProtectionNAME76){
			_recursion_protection_NAME_76 = new_recursionProtectionNAME76;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod77(){
			return _recursion_protection_statement_as_method_77;
		}
		public void set_recursionProtectionStatementAsMethod77(Set<Integer> new_recursionProtectionStatementAsMethod77){
			_recursion_protection_statement_as_method_77 = new_recursionProtectionStatementAsMethod77;
		}
		public Set<Integer> get_recursionProtectionInner78(){
			return _recursion_protection_inner_78;
		}
		public void set_recursionProtectionInner78(Set<Integer> new_recursionProtectionInner78){
			_recursion_protection_inner_78 = new_recursionProtectionInner78;
		}
		public Set<Integer> get_recursionProtectionNameVar79(){
			return _recursion_protection_name_var_79;
		}
		public void set_recursionProtectionNameVar79(Set<Integer> new_recursionProtectionNameVar79){
			_recursion_protection_name_var_79 = new_recursionProtectionNameVar79;
		}
		public Set<Integer> get_recursionProtectionNAME80(){
			return _recursion_protection_NAME_80;
		}
		public void set_recursionProtectionNAME80(Set<Integer> new_recursionProtectionNAME80){
			_recursion_protection_NAME_80 = new_recursionProtectionNAME80;
		}
		public Set<Integer> get_recursionProtectionNameVar81(){
			return _recursion_protection_name_var_81;
		}
		public void set_recursionProtectionNameVar81(Set<Integer> new_recursionProtectionNameVar81){
			_recursion_protection_name_var_81 = new_recursionProtectionNameVar81;
		}
		public Set<Integer> get_recursionProtectionNAME82(){
			return _recursion_protection_NAME_82;
		}
		public void set_recursionProtectionNAME82(Set<Integer> new_recursionProtectionNAME82){
			_recursion_protection_NAME_82 = new_recursionProtectionNAME82;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod83(){
			return _recursion_protection_statement_as_method_83;
		}
		public void set_recursionProtectionStatementAsMethod83(Set<Integer> new_recursionProtectionStatementAsMethod83){
			_recursion_protection_statement_as_method_83 = new_recursionProtectionStatementAsMethod83;
		}
		public Set<Integer> get_recursionProtectionStatementAsQuote84(){
			return _recursion_protection_statement_as_quote_84;
		}
		public void set_recursionProtectionStatementAsQuote84(Set<Integer> new_recursionProtectionStatementAsQuote84){
			_recursion_protection_statement_as_quote_84 = new_recursionProtectionStatementAsQuote84;
		}
		public Set<Integer> get_recursionProtectionStatementAsString85(){
			return _recursion_protection_statement_as_string_85;
		}
		public void set_recursionProtectionStatementAsString85(Set<Integer> new_recursionProtectionStatementAsString85){
			_recursion_protection_statement_as_string_85 = new_recursionProtectionStatementAsString85;
		}
		public Set<Integer> get_recursionProtectionNAME86(){
			return _recursion_protection_NAME_86;
		}
		public void set_recursionProtectionNAME86(Set<Integer> new_recursionProtectionNAME86){
			_recursion_protection_NAME_86 = new_recursionProtectionNAME86;
		}
		public Set<Integer> get_recursionProtectionStatementAsString87(){
			return _recursion_protection_statement_as_string_87;
		}
		public void set_recursionProtectionStatementAsString87(Set<Integer> new_recursionProtectionStatementAsString87){
			_recursion_protection_statement_as_string_87 = new_recursionProtectionStatementAsString87;
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
		public void setClassVariableNamesArrayParameters(Parser.NameList newClassVariableNamesArrayParameters){
			class_variable_names_array_parameters = newClassVariableNamesArrayParameters;
		}
		public Parser.NameList getVariableNamesArrayParameters(){
			return variable_names_array_parameters;
		}
		public void setVariableNamesArrayParameters(Parser.NameList newVariableNamesArrayParameters){
			variable_names_array_parameters = newVariableNamesArrayParameters;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
				if (_state==SUCCESS&&brace_5.get(_position_array_parameters_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_5.get(_position_array_parameters_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_array_parameters_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
			int _position_regex_6 = _position;
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
				_token.setValue(_input.substring(_position_regex_6,_position));
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[.]+");
					_furthestPosition=_position;
				}
				_position=_position_regex_6;
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
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_88)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_88;
			}
			else {
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
			parse_method_arguments();
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
			_token=new Tokens.Name.GroupToken();
			int _position_name_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_name_var_58.contains(_position)){
				_recursion_protection_name_var_58.add(_position);
				parse_name_var();
				_recursion_protection_name_var_58.remove(_position_name_var);
			}
			else {
				_state=FAILED;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_89)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_89;
			}
			else {
				int _state_55 = _state;
				parse__anonymous_90();
				if (_state_55==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_89)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_89;
				}
				else {
					int _state_56 = _state;
					while (_position<_inputLength){
						parse__anonymous_91();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_56==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_89)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_89;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_89.add(_position__anonymous_89,_token);
			}
			_token=_token__anonymous_89;
		}
		public void parse__anonymous_110(){
			int _position__anonymous_110 = -1;
			Token.Parsed _token__anonymous_110 = null;
			_position__anonymous_110=_position;
			_token__anonymous_110=_token;
			_token=new Token.Parsed("$ANON");
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_67.contains(_position)){
				_recursion_protection_inner_67.add(_position);
				parse_inner();
				_recursion_protection_inner_67.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
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
		public void parse_body_conditional(){
			int _position_body_conditional = -1;
			Token.Parsed _token_body_conditional = null;
			_position_body_conditional=_position;
			_token_body_conditional=_token;
			_token=new Tokens.Rule.BodyConditionalToken();
			int _state_37 = _state;
			parse__anonymous_55();
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
				parse__anonymous_56();
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
						parse__anonymous_57();
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position_body_conditional=_position;
				_token_body_conditional=_token;
				_token=new Tokens.Rule.BodyConditionalToken();
				int _state_38 = _state;
				parse__anonymous_58();
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
						_token.add(_position,Tokens.Syntax.syntax_21.conditional);
						_position=_position+4;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						parse__anonymous_59();
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position_body_conditional=_position;
					_token_body_conditional=_token;
					_token=new Tokens.Rule.BodyConditionalToken();
					int _state_39 = _state;
					parse__anonymous_60();
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
							_token.add(_position,Tokens.Syntax.syntax_26.conditional);
							_position=_position+3;
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
										parse__anonymous_61();
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
					if (_state==FAILED){
						_state=SUCCESS;
						_position_body_conditional=_position;
						_token_body_conditional=_token;
						_token=new Tokens.Rule.BodyConditionalToken();
						int _state_40 = _state;
						parse__anonymous_62();
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
								_token.add(_position,Tokens.Syntax.syntax_27.conditional);
								_position=_position+3;
								while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
								parse__anonymous_63();
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
						if (_state==FAILED){
							_state=SUCCESS;
							_position_body_conditional=_position;
							_token_body_conditional=_token;
							_token=new Tokens.Rule.BodyConditionalToken();
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
								int _state_42 = _state;
								parse__anonymous_65();
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
										_token.add(_position,Tokens.Syntax.syntax_29.conditional);
										_position=_position+5;
										while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
										parse__anonymous_66();
										if (_state==FAILED){
											if (_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
												_furthestPosition=_position;
											}
											_position=_position_body_conditional;
										}
										else {
											int _state_43 = _state;
											while (_position<_inputLength){
												parse__anonymous_67();
												if (_state==FAILED){
													break;
												}
											}
											if (_state_43==SUCCESS&&_state==FAILED){
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
												parse__anonymous_70();
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
			Token.Parsed _tokenbody_statement = _token;
			_token=new Tokens.Name.RightToken();
			int _position_body_statement = _position;
			if (_state==SUCCESS&&!_recursion_protection_body_statement_56.contains(_position)){
				_recursion_protection_body_statement_56.add(_position);
				parse_body_statement();
				_recursion_protection_body_statement_56.remove(_position_body_statement);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokenbody_statement.add(_position_body_statement,_token);
			}
			_token=_tokenbody_statement;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_80)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_80;
			}
			else {
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_104)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_104;
			}
			else {
				parse__anonymous_105();
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
			_token=new Tokens.Name.GroupToken();
			int _position_NAME = _position;
			if (_state==SUCCESS&&!_recursion_protection_NAME_62.contains(_position)){
				_recursion_protection_NAME_62.add(_position);
				parse_NAME();
				_recursion_protection_NAME_62.remove(_position_NAME);
			}
			else {
				_state=FAILED;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_105)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_105;
			}
			else {
				int _state_63 = _state;
				parse__anonymous_106();
				if (_state_63==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_105)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_105;
				}
				else {
					int _state_64 = _state;
					while (_position<_inputLength){
						parse__anonymous_107();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_64==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_105)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_105;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_105.add(_position__anonymous_105,_token);
			}
			_token=_token__anonymous_105;
		}
		public void parse__anonymous_82(){
			int _position__anonymous_82 = -1;
			Token.Parsed _token__anonymous_82 = null;
			_position__anonymous_82=_position;
			_token__anonymous_82=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_arguments();
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
			parse_method_arguments();
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
		public void setClassVariableNamesQuote(Parser.NameList newClassVariableNamesQuote){
			class_variable_names_quote = newClassVariableNamesQuote;
		}
		public Parser.NameList getVariableNamesQuote(){
			return variable_names_quote;
		}
		public void setVariableNamesQuote(Parser.NameList newVariableNamesQuote){
			variable_names_quote = newVariableNamesQuote;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				if (_state==SUCCESS&&brace_0.get(_position_quote_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_0.get(_position_quote_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_quote_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
			_token=new Tokens.Name.GroupToken();
			if (_position+4-1 >=_inputLength){
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
				if (_inputArray[_position+3]!=' '){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_32.NEW);
				_position=_position+4;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"new \"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_81)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_81;
			}
			else {
				Token.Parsed _tokenall_type_name = _token;
				_token=new Tokens.Name.TypeNameToken();
				int _position_all_type_name = _position;
				parse_all_type_name();
				if (_state==SUCCESS){
					_tokenall_type_name.add(_position_all_type_name,_token);
				}
				_token=_tokenall_type_name;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_81)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_81;
				}
				else {
					int _state_50 = _state;
					parse__anonymous_82();
					if (_state_50==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_81)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_81;
					}
					else {
						int _state_51 = _state;
						while (_position<_inputLength){
							parse__anonymous_83();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_51==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_81)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_81;
						}
						else {
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_81.add(_position__anonymous_81,_token);
			}
			_token=_token__anonymous_81;
		}
		public void parse__anonymous_107(){
			int _position__anonymous_107 = -1;
			Token.Parsed _token__anonymous_107 = null;
			_position__anonymous_107=_position;
			_token__anonymous_107=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_107)");
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_100)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_100;
			}
			else {
				parse_type_var();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_100)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_100;
				}
				else {
				}
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
			parse_array_parameters();
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
			parse_method_arguments();
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
			_token=new Tokens.Name.GroupToken();
			int _position_NAME = _position;
			if (_state==SUCCESS&&!_recursion_protection_NAME_57.contains(_position)){
				_recursion_protection_NAME_57.add(_position);
				parse_NAME();
				_recursion_protection_NAME_57.remove(_position_NAME);
			}
			else {
				_state=FAILED;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_86)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_86;
			}
			else {
				int _state_52 = _state;
				parse__anonymous_87();
				if (_state_52==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_86)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_86;
				}
				else {
					int _state_53 = _state;
					while (_position<_inputLength){
						parse__anonymous_88();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_53==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_86)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_86;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_86.add(_position__anonymous_86,_token);
			}
			_token=_token__anonymous_86;
		}
		public void parse__anonymous_102(){
			int _position__anonymous_102 = -1;
			Token.Parsed _token__anonymous_102 = null;
			_position__anonymous_102=_position;
			_token__anonymous_102=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_85)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_85;
			}
			else {
				parse__anonymous_86();
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
			if (_state==SUCCESS){
				_token__anonymous_103.addAll(_token);
			}
			_token=_token__anonymous_103;
		}
		public void parse_all_type_name(){
			int _position_all_type_name = -1;
			Token.Parsed _token_all_type_name = null;
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
				_token.add(_position,Tokens.Syntax.syntax_41.__SYNTAX__);
				_position=_position+5;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_state==FAILED){
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
					_token.add(_position,Tokens.Syntax.syntax_42.__SYNTAX__);
					_position=_position+6;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				if (_state==FAILED){
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
						_token.add(_position,Tokens.Syntax.syntax_43.__SYNTAX__);
						_position=_position+8;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					if (_state==FAILED){
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
							_token.add(_position,Tokens.Syntax.syntax_44.__SYNTAX__);
							_position=_position+4;
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						if (_state==FAILED){
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
								_token.add(_position,Tokens.Syntax.syntax_45.__SYNTAX__);
								_position=_position+9;
								while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
							if (_state==FAILED){
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
									_token.add(_position,Tokens.Syntax.syntax_46.__SYNTAX__);
									_position=_position+10;
									while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
								if (_state==FAILED){
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
										_token.add(_position,Tokens.Syntax.syntax_47.__SYNTAX__);
										_position=_position+7;
										while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
									if (_state==FAILED){
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
											int _state_82 = _state;
											while (_position<_inputLength){
												parse__anonymous_135();
												if (_state==FAILED){
													break;
												}
											}
											if (_state_82==SUCCESS&&_state==FAILED){
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
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_63.contains(_position)){
				_recursion_protection_inner_63.add(_position);
				parse_inner();
				_recursion_protection_inner_63.remove(_position_inner);
			}
			else {
				_state=FAILED;
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
			if (_state==SUCCESS){
				_token__anonymous_108.addAll(_token);
			}
			_token=_token__anonymous_108;
		}
		public void parse__anonymous_109(){
			int _position__anonymous_109 = -1;
			Token.Parsed _token__anonymous_109 = null;
			_position__anonymous_109=_position;
			_token__anonymous_109=_token;
			_token=new Token.Parsed("$ANON");
			parse_class_declaration();
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_109=_position;
				_token__anonymous_109=_token;
				_token=new Token.Parsed("$ANON");
				int _position_method_declaration = _position;
				if (_state==SUCCESS&&!_recursion_protection_method_declaration_64.contains(_position)){
					_recursion_protection_method_declaration_64.add(_position);
					parse_method_declaration();
					_recursion_protection_method_declaration_64.remove(_position_method_declaration);
				}
				else {
					_state=FAILED;
				}
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_109=_position;
					_token__anonymous_109=_token;
					_token=new Token.Parsed("$ANON");
					int _position_variable_declaration = _position;
					if (_state==SUCCESS&&!_recursion_protection_variable_declaration_65.contains(_position)){
						_recursion_protection_variable_declaration_65.add(_position);
						parse_variable_declaration();
						_recursion_protection_variable_declaration_65.remove(_position_variable_declaration);
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_109)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_109;
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
							_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
							_position=_position+1;
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_109)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_109;
						}
						else {
						}
					}
					if (_state==SUCCESS){
						_token__anonymous_109.addAll(_token);
					}
					_token=_token__anonymous_109;
					if (_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_109=_position;
						_token__anonymous_109=_token;
						_token=new Token.Parsed("$ANON");
						Token.Parsed _tokenmethod_body = _token;
						_token=new Tokens.Name.BodyToken();
						int _position_method_body = _position;
						if (_state==SUCCESS&&!_recursion_protection_method_body_66.contains(_position)){
							_recursion_protection_method_body_66.add(_position);
							parse_method_body();
							_recursion_protection_method_body_66.remove(_position_method_body);
						}
						else {
							_state=FAILED;
						}
						if (_state==SUCCESS){
							_tokenmethod_body.add(_position_method_body,_token);
						}
						_token=_tokenmethod_body;
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
						if (_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_109=_position;
							_token__anonymous_109=_token;
							_token=new Token.Parsed("$ANON");
							Token.Parsed _tokenas_statement = _token;
							_token=new Tokens.Name.BodyToken();
							int _position_as_statement = _position;
							parse_as_statement();
							if (_state==SUCCESS){
								_tokenas_statement.add(_position_as_statement,_token);
							}
							_token=_tokenas_statement;
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
					}
				}
			}
		}
		public void parse__anonymous_77(){
			int _position__anonymous_77 = -1;
			Token.Parsed _token__anonymous_77 = null;
			_position__anonymous_77=_position;
			_token__anonymous_77=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_78();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_77)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_77;
			}
			else {
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
			_token=new Tokens.Name.AsBracedToken();
			Token.Parsed _tokenstatement_as_braced = _token;
			_token=new Tokens.Name.LeftToken();
			int _position_statement_as_braced = _position;
			parse_statement_as_braced();
			if (_state==SUCCESS){
				_tokenstatement_as_braced.add(_position_statement_as_braced,_token);
			}
			_token=_tokenstatement_as_braced;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_76)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_76;
			}
			else {
				int _state_48 = _state;
				parse__anonymous_77();
				if (_state_48==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_76)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_76;
				}
				else {
					int _state_49 = _state;
					parse__anonymous_80();
					if (_state_49==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_76)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_76;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_76.add(_position__anonymous_76,_token);
			}
			_token=_token__anonymous_76;
		}
		public void parse__anonymous_79(){
			int _position__anonymous_79 = -1;
			Token.Parsed _token__anonymous_79 = null;
			_position__anonymous_79=_position;
			_token__anonymous_79=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_4.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			int _state_47 = _state;
			parse__anonymous_79();
			if (_state_47==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_78)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_78;
			}
			else {
				parse_OPERATOR();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_78)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_78;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_78.addAll(_token);
			}
			_token=_token__anonymous_78;
		}
		public Parser.NameList getClassVariableNamesStatementAsQuote(){
			return class_variable_names_statement_as_quote;
		}
		public void setClassVariableNamesStatementAsQuote(Parser.NameList newClassVariableNamesStatementAsQuote){
			class_variable_names_statement_as_quote = newClassVariableNamesStatementAsQuote;
		}
		public Parser.NameList getVariableNamesStatementAsQuote(){
			return variable_names_statement_as_quote;
		}
		public void setVariableNamesStatementAsQuote(Parser.NameList newVariableNamesStatementAsQuote){
			variable_names_statement_as_quote = newVariableNamesStatementAsQuote;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_statement_as_quote=_position;
				_token_statement_as_quote=_token;
				_token=new Tokens.Rule.StatementAsQuoteToken();
				int _position_body_statement = _position;
				if (_state==SUCCESS&&!_recursion_protection_body_statement_2.contains(_position)){
					_recursion_protection_body_statement_2.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_2.remove(_position_body_statement);
				}
				else {
					_state=FAILED;
				}
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
				if (_state==SUCCESS&&brace_6.get(_position_statement_as_quote_brace)==_position){
					_position+=2;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_6.get(_position_statement_as_quote_brace)+2;
					_succeedOnEnd=false;
				}
				_inputLength=_length_statement_as_quote_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_53.contains(_position)){
				_recursion_protection_inner_53.add(_position);
				parse_inner();
				_recursion_protection_inner_53.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
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
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			if (_state==SUCCESS&&!_recursion_protection_method_body_51.contains(_position)){
				_recursion_protection_method_body_51.add(_position);
				parse_method_body();
				_recursion_protection_method_body_51.remove(_position_method_body);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_70)");
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_70=_position;
				_token__anonymous_70=_token;
				_token=new Token.Parsed("$ANON");
				int _position_statement_as_method = _position;
				if (_state==SUCCESS&&!_recursion_protection_statement_as_method_52.contains(_position)){
					_recursion_protection_statement_as_method_52.add(_position);
					parse_statement_as_method();
					_recursion_protection_statement_as_method_52.remove(_position_statement_as_method);
				}
				else {
					_state=FAILED;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_70)");
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
		}
		public void parse__anonymous_73(){
			int _position__anonymous_73 = -1;
			Token.Parsed _token__anonymous_73 = null;
			_position__anonymous_73=_position;
			_token__anonymous_73=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_74();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_73)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_73;
			}
			else {
				parse_body_call();
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_4.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_75)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_75;
			}
			else {
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
			int _state_45 = _state;
			parse__anonymous_75();
			if (_state_45==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_74)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_74;
			}
			else {
				parse_OPERATOR();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_74)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_74;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_74.addAll(_token);
			}
			_token=_token__anonymous_74;
		}
		public void parse_name_var_element(){
			int _position_name_var_element = -1;
			Token.Parsed _token_name_var_element = null;
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position_name_var_element=_position;
				_token_name_var_element=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_char();
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position_name_var_element=_position;
					_token_name_var_element=_token;
					_token=new Token.Parsed("$ANON");
					int _position_statement_as_method = _position;
					if (_state==SUCCESS&&!_recursion_protection_statement_as_method_83.contains(_position)){
						_recursion_protection_statement_as_method_83.add(_position);
						parse_statement_as_method();
						_recursion_protection_statement_as_method_83.remove(_position_statement_as_method);
					}
					else {
						_state=FAILED;
					}
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
					if (_state==FAILED){
						_state=SUCCESS;
						_position_name_var_element=_position;
						_token_name_var_element=_token;
						_token=new Token.Parsed("$ANON");
						int _position_statement_as_quote = _position;
						if (_state==SUCCESS&&!_recursion_protection_statement_as_quote_84.contains(_position)){
							_recursion_protection_statement_as_quote_84.add(_position);
							parse_statement_as_quote();
							_recursion_protection_statement_as_quote_84.remove(_position_statement_as_quote);
						}
						else {
							_state=FAILED;
						}
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
						if (_state==FAILED){
							_state=SUCCESS;
							_position_name_var_element=_position;
							_token_name_var_element=_token;
							_token=new Token.Parsed("$ANON");
							int _position_statement_as_string = _position;
							if (_state==SUCCESS&&!_recursion_protection_statement_as_string_85.contains(_position)){
								_recursion_protection_statement_as_string_85.add(_position);
								parse_statement_as_string();
								_recursion_protection_statement_as_string_85.remove(_position_statement_as_string);
							}
							else {
								_state=FAILED;
							}
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
							if (_state==FAILED){
								_state=SUCCESS;
								_position_name_var_element=_position;
								_token_name_var_element=_token;
								_token=new Token.Parsed("$ANON");
								parse__anonymous_147();
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
								if (_state==FAILED){
									_state=SUCCESS;
									_position_name_var_element=_position;
									_token_name_var_element=_token;
									_token=new Token.Parsed("$ANON");
									parse__anonymous_150();
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
									if (_state==FAILED){
										_state=SUCCESS;
										_position_name_var_element=_position;
										_token_name_var_element=_token;
										_token=new Token.Parsed("$ANON");
										parse__anonymous_153();
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
									}
								}
							}
						}
					}
				}
			}
		}
		public void parse_class_element(){
			int _position_class_element = -1;
			Token.Parsed _token_class_element = null;
			_position_class_element=_position;
			_token_class_element=_token;
			_token=new Token.Parsed("$ANON");
			int _position_comments = _position;
			if (_state==SUCCESS&&!_recursion_protection_comments_26.contains(_position)){
				_recursion_protection_comments_26.add(_position);
				parse_comments();
				_recursion_protection_comments_26.remove(_position_comments);
			}
			else {
				_state=FAILED;
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
			if (_state==SUCCESS){
				_token_class_element.addAll(_token);
			}
			_token=_token_class_element;
			if (_state==FAILED){
				_state=SUCCESS;
				_position_class_element=_position;
				_token_class_element=_token;
				_token=new Token.Parsed("$ANON");
				int _position_class_declaration = _position;
				if (_state==SUCCESS&&!_recursion_protection_class_declaration_27.contains(_position)){
					_recursion_protection_class_declaration_27.add(_position);
					parse_class_declaration();
					_recursion_protection_class_declaration_27.remove(_position_class_declaration);
				}
				else {
					_state=FAILED;
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
				if (_state==SUCCESS){
					_token_class_element.addAll(_token);
				}
				_token=_token_class_element;
				if (_state==FAILED){
					_state=SUCCESS;
					_position_class_element=_position;
					_token_class_element=_token;
					_token=new Token.Parsed("$ANON");
					int _position_method_declaration = _position;
					if (_state==SUCCESS&&!_recursion_protection_method_declaration_28.contains(_position)){
						_recursion_protection_method_declaration_28.add(_position);
						parse_method_declaration();
						_recursion_protection_method_declaration_28.remove(_position_method_declaration);
					}
					else {
						_state=FAILED;
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
					if (_state==SUCCESS){
						_token_class_element.addAll(_token);
					}
					_token=_token_class_element;
					if (_state==FAILED){
						_state=SUCCESS;
						_position_class_element=_position;
						_token_class_element=_token;
						_token=new Token.Parsed("$ANON");
						int _position_variable_declaration = _position;
						if (_state==SUCCESS&&!_recursion_protection_variable_declaration_29.contains(_position)){
							_recursion_protection_variable_declaration_29.add(_position);
							parse_variable_declaration();
							_recursion_protection_variable_declaration_29.remove(_position_variable_declaration);
						}
						else {
							_state=FAILED;
						}
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
								_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
								_position=_position+1;
								while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					}
				}
			}
		}
		public void parse__anonymous_66(){
			int _position__anonymous_66 = -1;
			Token.Parsed _token__anonymous_66 = null;
			_position__anonymous_66=_position;
			_token__anonymous_66=_token;
			_token=new Tokens.Name.ExceptionToken();
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_30.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_66)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_66;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_66.add(_position__anonymous_66,_token);
			}
			_token=_token__anonymous_66;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_66=_position;
				_token__anonymous_66=_token;
				_token=new Tokens.Name.ExceptionToken();
				int _position_NAME = _position;
				if (_state==SUCCESS&&!_recursion_protection_NAME_49.contains(_position)){
					_recursion_protection_NAME_49.add(_position);
					parse_NAME();
					_recursion_protection_NAME_49.remove(_position_NAME);
				}
				else {
					_state=FAILED;
				}
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
					_token__anonymous_66.add(_position__anonymous_66,_token);
				}
				_token=_token__anonymous_66;
			}
		}
		public void parse__anonymous_65(){
			int _position__anonymous_65 = -1;
			Token.Parsed _token__anonymous_65 = null;
			_position__anonymous_65=_position;
			_token__anonymous_65=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_28.__SYNTAX__);
				_position=_position+5;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void parse__anonymous_130(){
			int _position__anonymous_130 = -1;
			Token.Parsed _token__anonymous_130 = null;
			_position__anonymous_130=_position;
			_token__anonymous_130=_token;
			_token=new Token.Parsed("$ANON");
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_78.contains(_position)){
				_recursion_protection_inner_78.add(_position);
				parse_inner();
				_recursion_protection_inner_78.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(_anonymous_130)");
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
				if (_inputArray[_position+0]!='|'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_68)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_68;
			}
			else {
				parse__anonymous_69();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_68)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_68;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_68.addAll(_token);
			}
			_token=_token__anonymous_68;
		}
		public void parse__anonymous_131(){
			int _position__anonymous_131 = -1;
			Token.Parsed _token__anonymous_131 = null;
			_position__anonymous_131=_position;
			_token__anonymous_131=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_40.__SYNTAX__);
				_position=_position+3;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_state==SUCCESS){
				_token__anonymous_67.addAll(_token);
			}
			_token=_token__anonymous_67;
		}
		public void parse__anonymous_132(){
			int _position__anonymous_132 = -1;
			Token.Parsed _token__anonymous_132 = null;
			_position__anonymous_132=_position;
			_token__anonymous_132=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_38.ARRAY_TYPE);
				_position=_position+2;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public void parse__anonymous_69(){
			int _position__anonymous_69 = -1;
			Token.Parsed _token__anonymous_69 = null;
			_position__anonymous_69=_position;
			_token__anonymous_69=_token;
			_token=new Tokens.Name.ExceptionToken();
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_30.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_69)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_69;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_69.add(_position__anonymous_69,_token);
			}
			_token=_token__anonymous_69;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_69=_position;
				_token__anonymous_69=_token;
				_token=new Tokens.Name.ExceptionToken();
				int _position_NAME = _position;
				if (_state==SUCCESS&&!_recursion_protection_NAME_50.contains(_position)){
					_recursion_protection_NAME_50.add(_position);
					parse_NAME();
					_recursion_protection_NAME_50.remove(_position_NAME);
				}
				else {
					_state=FAILED;
				}
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
					_token__anonymous_69.add(_position__anonymous_69,_token);
				}
				_token=_token__anonymous_69;
			}
		}
		public void parse_class_name_definition(){
			int _position_class_name_definition = -1;
			Token.Parsed _token_class_name_definition = null;
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
				_token.add(_position,Tokens.Syntax.syntax_41.typeName);
				_position=_position+5;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				parse__anonymous_134();
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
		}
		public void parse__anonymous_126(){
			int _position__anonymous_126 = -1;
			Token.Parsed _token__anonymous_126 = null;
			_position__anonymous_126=_position;
			_token__anonymous_126=_token;
			_token=new Token.Parsed("$ANON");
			parse_weak();
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
		public void parse__anonymous_127(){
			int _position__anonymous_127 = -1;
			Token.Parsed _token__anonymous_127 = null;
			_position__anonymous_127=_position;
			_token__anonymous_127=_token;
			_token=new Token.Parsed("$ANON");
			parse_class_name_definition();
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_127=_position;
				_token__anonymous_127=_token;
				_token=new Token.Parsed("$ANON");
				parse_variable_name_definition();
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
		}
		public void parse__anonymous_60(){
			int _position__anonymous_60 = -1;
			Token.Parsed _token__anonymous_60 = null;
			_position__anonymous_60=_position;
			_token__anonymous_60=_token;
			_token=new Token.Parsed("$ANON");
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_42.contains(_position)){
				_recursion_protection_inner_42.add(_position);
				parse_inner();
				_recursion_protection_inner_42.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
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
		public void parse__anonymous_128(){
			int _position__anonymous_128 = -1;
			Token.Parsed _token__anonymous_128 = null;
			_position__anonymous_128=_position;
			_token__anonymous_128=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_129();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_128)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_128;
			}
			else {
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='='){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_39.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_129)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_129;
			}
			else {
				parse_method_argument();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_129)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_129;
				}
				else {
				}
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
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_45.contains(_position)){
				_recursion_protection_inner_45.add(_position);
				parse_inner();
				_recursion_protection_inner_45.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
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
		public void parse__anonymous_122(){
			int _position__anonymous_122 = -1;
			Token.Parsed _token__anonymous_122 = null;
			_position__anonymous_122=_position;
			_token__anonymous_122=_token;
			_token=new Token.Parsed("$ANON");
			parse_inner();
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
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			if (_state==SUCCESS&&!_recursion_protection_method_body_43.contains(_position)){
				_recursion_protection_method_body_43.add(_position);
				parse_method_body();
				_recursion_protection_method_body_43.remove(_position_method_body);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_61=_position;
				_token__anonymous_61=_token;
				_token=new Token.Parsed("$ANON");
				int _position_statement_as_method = _position;
				if (_state==SUCCESS&&!_recursion_protection_statement_as_method_44.contains(_position)){
					_recursion_protection_statement_as_method_44.add(_position);
					parse_statement_as_method();
					_recursion_protection_statement_as_method_44.remove(_position_statement_as_method);
				}
				else {
					_state=FAILED;
				}
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
		}
		public void parse__anonymous_123(){
			int _position__anonymous_123 = -1;
			Token.Parsed _token__anonymous_123 = null;
			_position__anonymous_123=_position;
			_token__anonymous_123=_token;
			_token=new Token.Parsed("$ANON");
			parse_weak();
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
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_48.contains(_position)){
				_recursion_protection_inner_48.add(_position);
				parse_inner();
				_recursion_protection_inner_48.remove(_position_inner);
			}
			else {
				_state=FAILED;
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
			parse__anonymous_125();
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
		public void parse__anonymous_63(){
			int _position__anonymous_63 = -1;
			Token.Parsed _token__anonymous_63 = null;
			_position__anonymous_63=_position;
			_token__anonymous_63=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			if (_state==SUCCESS&&!_recursion_protection_method_body_46.contains(_position)){
				_recursion_protection_method_body_46.add(_position);
				parse_method_body();
				_recursion_protection_method_body_46.remove(_position_method_body);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_63=_position;
				_token__anonymous_63=_token;
				_token=new Token.Parsed("$ANON");
				int _position_statement_as_method = _position;
				if (_state==SUCCESS&&!_recursion_protection_statement_as_method_47.contains(_position)){
					_recursion_protection_statement_as_method_47.add(_position);
					parse_statement_as_method();
					_recursion_protection_statement_as_method_47.remove(_position_statement_as_method);
				}
				else {
					_state=FAILED;
				}
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
		}
		public void parse__anonymous_125(){
			int _position__anonymous_125 = -1;
			Token.Parsed _token__anonymous_125 = null;
			_position__anonymous_125=_position;
			_token__anonymous_125=_token;
			_token=new Tokens.Name.StaticToken();
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='@'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_36.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_125)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_125;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_125.add(_position__anonymous_125,_token);
			}
			_token=_token__anonymous_125;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_125=_position;
				_token__anonymous_125=_token;
				_token=new Tokens.Name.StaticToken();
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
					_token.add(_position,Tokens.Syntax.syntax_37.__SYNTAX__);
					_position=_position+6;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_125)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_125;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_125.add(_position__anonymous_125,_token);
				}
				_token=_token__anonymous_125;
			}
		}
		public void parse_variable_declaration(){
			int _position_variable_declaration = -1;
			Token.Parsed _token_variable_declaration = null;
			_position_variable_declaration=_position;
			_token_variable_declaration=_token;
			_token=new Tokens.Rule.VariableDeclarationToken();
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
					parse__anonymous_124();
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
						int _state_77 = _state;
						parse__anonymous_126();
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
							parse__anonymous_127();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_variable_declaration;
							}
							else {
								int _state_78 = _state;
								parse__anonymous_128();
								if (_state_78==SUCCESS&&_state==FAILED){
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
		}
		public void parse_variable_name_definition(){
			int _position_variable_name_definition = -1;
			Token.Parsed _token_variable_name_definition = null;
			_position_variable_name_definition=_position;
			_token_variable_name_definition=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenall_type_name = _token;
			_token=new Tokens.Name.TypeNameToken();
			int _position_all_type_name = _position;
			parse_all_type_name();
			if (_state==SUCCESS){
				_tokenall_type_name.add(_position_all_type_name,_token);
			}
			_token=_tokenall_type_name;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
					_furthestPosition=_position;
				}
				_position=_position_variable_name_definition;
			}
			else {
				int _state_80 = _state;
				parse__anonymous_131();
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
					int _state_81 = _state;
					while (_position<_inputLength){
						parse__anonymous_132();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_81==SUCCESS&&_state==FAILED){
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
						parse__anonymous_133();
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
		}
		public void parse__anonymous_55(){
			int _position__anonymous_55 = -1;
			Token.Parsed _token__anonymous_55 = null;
			_position__anonymous_55=_position;
			_token__anonymous_55=_token;
			_token=new Token.Parsed("$ANON");
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_36.contains(_position)){
				_recursion_protection_inner_36.add(_position);
				parse_inner();
				_recursion_protection_inner_36.remove(_position_inner);
			}
			else {
				_state=FAILED;
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
		public void parse__anonymous_54(){
			int _position__anonymous_54 = -1;
			Token.Parsed _token__anonymous_54 = null;
			_position__anonymous_54=_position;
			_token__anonymous_54=_token;
			_token=new Tokens.Name.BodyLineToken();
			int _position_body_statement = _position;
			if (_state==SUCCESS&&!_recursion_protection_body_statement_35.contains(_position)){
				_recursion_protection_body_statement_35.add(_position);
				parse_body_statement();
				_recursion_protection_body_statement_35.remove(_position_body_statement);
			}
			else {
				_state=FAILED;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_54)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_54;
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
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_54)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_54;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_54.add(_position__anonymous_54,_token);
			}
			_token=_token__anonymous_54;
		}
		public void parse__anonymous_57(){
			int _position__anonymous_57 = -1;
			Token.Parsed _token__anonymous_57 = null;
			_position__anonymous_57=_position;
			_token__anonymous_57=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			if (_state==SUCCESS&&!_recursion_protection_method_body_37.contains(_position)){
				_recursion_protection_method_body_37.add(_position);
				parse_method_body();
				_recursion_protection_method_body_37.remove(_position_method_body);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_57=_position;
				_token__anonymous_57=_token;
				_token=new Token.Parsed("$ANON");
				int _position_statement_as_method = _position;
				if (_state==SUCCESS&&!_recursion_protection_statement_as_method_38.contains(_position)){
					_recursion_protection_statement_as_method_38.add(_position);
					parse_statement_as_method();
					_recursion_protection_statement_as_method_38.remove(_position_statement_as_method);
				}
				else {
					_state=FAILED;
				}
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
		}
		public void parse__anonymous_120(){
			int _position__anonymous_120 = -1;
			Token.Parsed _token__anonymous_120 = null;
			_position__anonymous_120=_position;
			_token__anonymous_120=_token;
			_token=new Tokens.Name.MethodNameToken();
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_30.NAME);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_120)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_120;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_120.add(_position__anonymous_120,_token);
			}
			_token=_token__anonymous_120;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_120=_position;
				_token__anonymous_120=_token;
				_token=new Tokens.Name.MethodNameToken();
				int _position_name_var = _position;
				if (_state==SUCCESS&&!_recursion_protection_name_var_75.contains(_position)){
					_recursion_protection_name_var_75.add(_position);
					parse_name_var();
					_recursion_protection_name_var_75.remove(_position_name_var);
				}
				else {
					_state=FAILED;
				}
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
					_token__anonymous_120.add(_position__anonymous_120,_token);
				}
				_token=_token__anonymous_120;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_120=_position;
					_token__anonymous_120=_token;
					_token=new Tokens.Name.MethodNameToken();
					int _position_NAME = _position;
					if (_state==SUCCESS&&!_recursion_protection_NAME_76.contains(_position)){
						_recursion_protection_NAME_76.add(_position);
						parse_NAME();
						_recursion_protection_NAME_76.remove(_position_NAME);
					}
					else {
						_state=FAILED;
					}
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
						_token__anonymous_120.add(_position__anonymous_120,_token);
					}
					_token=_token__anonymous_120;
				}
			}
		}
		public void parse__anonymous_56(){
			int _position__anonymous_56 = -1;
			Token.Parsed _token__anonymous_56 = null;
			_position__anonymous_56=_position;
			_token__anonymous_56=_token;
			_token=new Tokens.Name.ConditionalToken();
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
				_token.add(_position,Tokens.Syntax.syntax_20.__SYNTAX__);
				_position=_position+2;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_56;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_56.add(_position__anonymous_56,_token);
			}
			_token=_token__anonymous_56;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_56=_position;
				_token__anonymous_56=_token;
				_token=new Tokens.Name.ConditionalToken();
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
					_token.add(_position,Tokens.Syntax.syntax_21.__SYNTAX__);
					_position=_position+4;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_56;
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
						_token.add(_position,Tokens.Syntax.syntax_20.__SYNTAX__);
						_position=_position+2;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_56;
					}
					else {
					}
				}
				if (_state==SUCCESS){
					_token__anonymous_56.add(_position__anonymous_56,_token);
				}
				_token=_token__anonymous_56;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_56=_position;
					_token__anonymous_56=_token;
					_token=new Tokens.Name.ConditionalToken();
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
						_token.add(_position,Tokens.Syntax.syntax_22.__SYNTAX__);
						_position=_position+5;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_56;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_56.add(_position__anonymous_56,_token);
					}
					_token=_token__anonymous_56;
					if (_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_56=_position;
						_token__anonymous_56=_token;
						_token=new Tokens.Name.ConditionalToken();
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
							_token.add(_position,Tokens.Syntax.syntax_23.__SYNTAX__);
							_position=_position+12;
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_56;
						}
						else {
						}
						if (_state==SUCCESS){
							_token__anonymous_56.add(_position__anonymous_56,_token);
						}
						_token=_token__anonymous_56;
						if (_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_56=_position;
							_token__anonymous_56=_token;
							_token=new Tokens.Name.ConditionalToken();
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
								_token.add(_position,Tokens.Syntax.syntax_24.__SYNTAX__);
								_position=_position+6;
								while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_56;
							}
							else {
							}
							if (_state==SUCCESS){
								_token__anonymous_56.add(_position__anonymous_56,_token);
							}
							_token=_token__anonymous_56;
							if (_state==FAILED){
								_state=SUCCESS;
								_position__anonymous_56=_position;
								_token__anonymous_56=_token;
								_token=new Tokens.Name.ConditionalToken();
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
									_token.add(_position,Tokens.Syntax.syntax_25.__SYNTAX__);
									_position=_position+4;
									while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_56)");
										_furthestPosition=_position;
									}
									_position=_position__anonymous_56;
								}
								else {
								}
								if (_state==SUCCESS){
									_token__anonymous_56.add(_position__anonymous_56,_token);
								}
								_token=_token__anonymous_56;
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_121(){
			int _position__anonymous_121 = -1;
			Token.Parsed _token__anonymous_121 = null;
			_position__anonymous_121=_position;
			_token__anonymous_121=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_parameters = _token;
			_token=new Tokens.Name.InlineToken();
			int _position_method_parameters = _position;
			parse_method_parameters();
			if (_state==SUCCESS){
				_tokenmethod_parameters.add(_position_method_parameters,_token);
			}
			_token=_tokenmethod_parameters;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_121)");
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_121=_position;
				_token__anonymous_121=_token;
				_token=new Token.Parsed("$ANON");
				Token.Parsed _tokenstatement_as_method = _token;
				_token=new Tokens.Name.VariableParametersToken();
				int _position_statement_as_method = _position;
				if (_state==SUCCESS&&!_recursion_protection_statement_as_method_77.contains(_position)){
					_recursion_protection_statement_as_method_77.add(_position);
					parse_statement_as_method();
					_recursion_protection_statement_as_method_77.remove(_position_statement_as_method);
				}
				else {
					_state=FAILED;
				}
				if (_state==SUCCESS){
					_tokenstatement_as_method.add(_position_statement_as_method,_token);
				}
				_token=_tokenstatement_as_method;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_121)");
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
		}
		public void parse__anonymous_59(){
			int _position__anonymous_59 = -1;
			Token.Parsed _token__anonymous_59 = null;
			_position__anonymous_59=_position;
			_token__anonymous_59=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			if (_state==SUCCESS&&!_recursion_protection_method_body_40.contains(_position)){
				_recursion_protection_method_body_40.add(_position);
				parse_method_body();
				_recursion_protection_method_body_40.remove(_position_method_body);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_59=_position;
				_token__anonymous_59=_token;
				_token=new Token.Parsed("$ANON");
				int _position_statement_as_method = _position;
				if (_state==SUCCESS&&!_recursion_protection_statement_as_method_41.contains(_position)){
					_recursion_protection_statement_as_method_41.add(_position);
					parse_statement_as_method();
					_recursion_protection_statement_as_method_41.remove(_position_statement_as_method);
				}
				else {
					_state=FAILED;
				}
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
		}
		public void parse__anonymous_58(){
			int _position__anonymous_58 = -1;
			Token.Parsed _token__anonymous_58 = null;
			_position__anonymous_58=_position;
			_token__anonymous_58=_token;
			_token=new Token.Parsed("$ANON");
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_39.contains(_position)){
				_recursion_protection_inner_39.add(_position);
				parse_inner();
				_recursion_protection_inner_39.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
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
		public void parse__anonymous_115(){
			int _position__anonymous_115 = -1;
			Token.Parsed _token__anonymous_115 = null;
			_position__anonymous_115=_position;
			_token__anonymous_115=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_115)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_115;
			}
			else {
				parse_variable_declaration();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_115)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_115;
				}
				else {
				}
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
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_74.contains(_position)){
				_recursion_protection_inner_74.add(_position);
				parse_inner();
				_recursion_protection_inner_74.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
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
			parse__anonymous_118();
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
		public void parse__anonymous_118(){
			int _position__anonymous_118 = -1;
			Token.Parsed _token__anonymous_118 = null;
			_position__anonymous_118=_position;
			_token__anonymous_118=_token;
			_token=new Tokens.Name.StaticToken();
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='@'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_36.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_118)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_118;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_118.add(_position__anonymous_118,_token);
			}
			_token=_token__anonymous_118;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_118=_position;
				_token__anonymous_118=_token;
				_token=new Tokens.Name.StaticToken();
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
					_token.add(_position,Tokens.Syntax.syntax_37.__SYNTAX__);
					_position=_position+6;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_118)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_118;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_118.add(_position__anonymous_118,_token);
				}
				_token=_token__anonymous_118;
			}
		}
		public void parse_OPERATOR(){
			int _position_OPERATOR = -1;
			Token.Parsed _token_OPERATOR = null;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public void parse__anonymous_51(){
			int _position__anonymous_51 = -1;
			Token.Parsed _token__anonymous_51 = null;
			_position__anonymous_51=_position;
			_token__anonymous_51=_token;
			_token=new Tokens.Name.BodyThrowToken();
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
				_token.add(_position,Tokens.Syntax.syntax_19.__SYNTAX__);
				_position=_position+5;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_51)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_51;
			}
			else {
				parse_body_statement();
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
						_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
						_position=_position+1;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			}
			if (_state==SUCCESS){
				_token__anonymous_51.add(_position__anonymous_51,_token);
			}
			_token=_token__anonymous_51;
		}
		public void parse__anonymous_111(){
			int _position__anonymous_111 = -1;
			Token.Parsed _token__anonymous_111 = null;
			_position__anonymous_111=_position;
			_token__anonymous_111=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_112();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_111)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_111;
			}
			else {
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
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_32.contains(_position)){
				_recursion_protection_inner_32.add(_position);
				parse_inner();
				_recursion_protection_inner_32.remove(_position_inner);
			}
			else {
				_state=FAILED;
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
			_token=new Tokens.Name.TokenInstanceToken();
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!=':'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_7.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_112)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_112;
			}
			else {
				Token.Parsed _tokenNAME = _token;
				_token=new Tokens.Name.TokenNameToken();
				int _position_NAME = _position;
				parse_NAME();
				if (_state==SUCCESS){
					_tokenNAME.add(_position_NAME,_token);
				}
				_token=_tokenNAME;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_112)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_112;
				}
				else {
					parse_method_body();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_112)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_112;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_112.add(_position__anonymous_112,_token);
			}
			_token=_token__anonymous_112;
		}
		public void parse__anonymous_53(){
			int _position__anonymous_53 = -1;
			Token.Parsed _token__anonymous_53 = null;
			_position__anonymous_53=_position;
			_token__anonymous_53=_token;
			_token=new Tokens.Name.BodyLineToken();
			parse_variable_assignment();
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
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				_token__anonymous_53.add(_position__anonymous_53,_token);
			}
			_token=_token__anonymous_53;
		}
		public void parse__anonymous_113(){
			int _position__anonymous_113 = -1;
			Token.Parsed _token__anonymous_113 = null;
			_position__anonymous_113=_position;
			_token__anonymous_113=_token;
			_token=new Token.Parsed("$ANON");
			int _position_variable_declaration = _position;
			if (_state==SUCCESS&&!_recursion_protection_variable_declaration_68.contains(_position)){
				_recursion_protection_variable_declaration_68.add(_position);
				parse_variable_declaration();
				_recursion_protection_variable_declaration_68.remove(_position_variable_declaration);
			}
			else {
				_state=FAILED;
			}
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
			_token=new Tokens.Name.BodyLineToken();
			int _position_variable_declaration = _position;
			if (_state==SUCCESS&&!_recursion_protection_variable_declaration_34.contains(_position)){
				_recursion_protection_variable_declaration_34.add(_position);
				parse_variable_declaration();
				_recursion_protection_variable_declaration_34.remove(_position_variable_declaration);
			}
			else {
				_state=FAILED;
			}
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
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				_token__anonymous_52.add(_position__anonymous_52,_token);
			}
			_token=_token__anonymous_52;
		}
		public void parse__anonymous_114(){
			int _position__anonymous_114 = -1;
			Token.Parsed _token__anonymous_114 = null;
			_position__anonymous_114=_position;
			_token__anonymous_114=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_115();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_114)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_114;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_114.addAll(_token);
			}
			_token=_token__anonymous_114;
		}
		public void parse_body_call(){
			int _position_body_call = -1;
			Token.Parsed _token_body_call = null;
			_position_body_call=_position;
			_token_body_call=_token;
			_token=new Tokens.Rule.BodyCallToken();
			parse__anonymous_76();
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position_body_call=_position;
				_token_body_call=_token;
				_token=new Tokens.Rule.BodyCallToken();
				parse__anonymous_81();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
						_furthestPosition=_position;
					}
					_position=_position_body_call;
				}
				else {
					int _state_54 = _state;
					while (_position<_inputLength){
						parse__anonymous_84();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_54==SUCCESS&&_state==FAILED){
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position_body_call=_position;
					_token_body_call=_token;
					_token=new Tokens.Rule.BodyCallToken();
					parse__anonymous_89();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
							_furthestPosition=_position;
						}
						_position=_position_body_call;
					}
					else {
						int _state_59 = _state;
						while (_position<_inputLength){
							parse__anonymous_92();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_59==SUCCESS&&_state==FAILED){
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
					if (_state==FAILED){
						_state=SUCCESS;
						_position_body_call=_position;
						_token_body_call=_token;
						_token=new Tokens.Rule.BodyCallToken();
						parse__anonymous_98();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
								_furthestPosition=_position;
							}
							_position=_position_body_call;
						}
						else {
							int _state_65 = _state;
							while (_position<_inputLength){
								parse__anonymous_103();
								if (_state==FAILED){
									break;
								}
							}
							if (_state_65==SUCCESS&&_state==FAILED){
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
				_token.add(_position,Tokens.Syntax.syntax_38.ARRAY_TYPE);
				_position=_position+2;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void parse__anonymous_44(){
			int _position__anonymous_44 = -1;
			Token.Parsed _token__anonymous_44 = null;
			_position__anonymous_44=_position;
			_token__anonymous_44=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokentype_var = _token;
			_token=new Tokens.Name.ParentNameToken();
			int _position_type_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_type_var_24.contains(_position)){
				_recursion_protection_type_var_24.add(_position);
				parse_type_var();
				_recursion_protection_type_var_24.remove(_position_type_var);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokentype_var.add(_position_type_var,_token);
			}
			_token=_tokentype_var;
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			parse__anonymous_44();
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
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_152)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_152;
			}
			else {
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
			Token.Parsed _tokentype_var = _token;
			_token=new Tokens.Name.InterfaceNameToken();
			int _position_type_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_type_var_25.contains(_position)){
				_recursion_protection_type_var_25.add(_position);
				parse_type_var();
				_recursion_protection_type_var_25.remove(_position_type_var);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokentype_var.add(_position_type_var,_token);
			}
			_token=_tokentype_var;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_46)");
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
			_token=new Tokens.Name.ExactToken();
			int _state_89 = _state;
			parse__anonymous_154();
			if (_state_89==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_153)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_153;
			}
			else {
				parse__anonymous_155();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_153)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_153;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_153.add(_position__anonymous_153,_token);
			}
			_token=_token__anonymous_153;
		}
		public void parse__anonymous_45(){
			int _position__anonymous_45 = -1;
			Token.Parsed _token__anonymous_45 = null;
			_position__anonymous_45=_position;
			_token__anonymous_45=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_46();
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void parse__anonymous_48(){
			int _position__anonymous_48 = -1;
			Token.Parsed _token__anonymous_48 = null;
			_position__anonymous_48=_position;
			_token__anonymous_48=_token;
			_token=new Tokens.Name.BodyReturnToken();
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
				_token.add(_position,Tokens.Syntax.syntax_17.__SYNTAX__);
				_position=_position+6;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_48)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_48;
			}
			else {
				parse__anonymous_49();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_48)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_48;
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
						_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
						_position=_position+1;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_48)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_48;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_48.add(_position__anonymous_48,_token);
			}
			_token=_token__anonymous_48;
		}
		public void parse__anonymous_47(){
			int _position__anonymous_47 = -1;
			Token.Parsed _token__anonymous_47 = null;
			_position__anonymous_47=_position;
			_token__anonymous_47=_token;
			_token=new Token.Parsed("$ANON");
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_30.contains(_position)){
				_recursion_protection_inner_30.add(_position);
				parse_inner();
				_recursion_protection_inner_30.remove(_position_inner);
			}
			else {
				_state=FAILED;
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
				_token.add(_position,Tokens.Syntax.syntax_18.__SYNTAX__);
				_position=_position+4;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_49=_position;
				_token__anonymous_49=_token;
				_token=new Token.Parsed("$ANON");
				int _position_method_argument = _position;
				if (_state==SUCCESS&&!_recursion_protection_method_argument_31.contains(_position)){
					_recursion_protection_method_argument_31.add(_position);
					parse_method_argument();
					_recursion_protection_method_argument_31.remove(_position_method_argument);
				}
				else {
					_state=FAILED;
				}
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
		}
		public void parse__anonymous_150(){
			int _position__anonymous_150 = -1;
			Token.Parsed _token__anonymous_150 = null;
			_position__anonymous_150=_position;
			_token__anonymous_150=_token;
			_token=new Tokens.Name.VariableToken();
			int _state_88 = _state;
			parse__anonymous_151();
			if (_state_88==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_150)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_150;
			}
			else {
				parse__anonymous_152();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_150)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_150;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_150.add(_position__anonymous_150,_token);
			}
			_token=_token__anonymous_150;
		}
		public void parse__anonymous_148(){
			int _position__anonymous_148 = -1;
			Token.Parsed _token__anonymous_148 = null;
			_position__anonymous_148=_position;
			_token__anonymous_148=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void parse__anonymous_149(){
			int _position__anonymous_149 = -1;
			Token.Parsed _token__anonymous_149 = null;
			_position__anonymous_149=_position;
			_token__anonymous_149=_token;
			_token=new Token.Parsed("$ANON");
			parse_quote();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_149)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_149;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_149.addAll(_token);
			}
			_token=_token__anonymous_149;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_149=_position;
				_token__anonymous_149=_token;
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
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_149)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_149;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_149.addAll(_token);
				}
				_token=_token__anonymous_149;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_149=_position;
					_token__anonymous_149=_token;
					_token=new Token.Parsed("$ANON");
					int _position_NAME = _position;
					if (_state==SUCCESS&&!_recursion_protection_NAME_86.contains(_position)){
						_recursion_protection_NAME_86.add(_position);
						parse_NAME();
						_recursion_protection_NAME_86.remove(_position_NAME);
					}
					else {
						_state=FAILED;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_149)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_149;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_149.addAll(_token);
					}
					_token=_token__anonymous_149;
				}
			}
		}
		public void parse__anonymous_40(){
			int _position__anonymous_40 = -1;
			Token.Parsed _token__anonymous_40 = null;
			_position__anonymous_40=_position;
			_token__anonymous_40=_token;
			_token=new Tokens.Name.ClassNameToken();
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
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_40)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_40;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_40.add(_position__anonymous_40,_token);
			}
			_token=_token__anonymous_40;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_40=_position;
				_token__anonymous_40=_token;
				_token=new Tokens.Name.ClassNameToken();
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
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_40)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_40;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_40.add(_position__anonymous_40,_token);
				}
				_token=_token__anonymous_40;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_40=_position;
					_token__anonymous_40=_token;
					_token=new Tokens.Name.ClassNameToken();
					int _position_NAME = _position;
					if (_state==SUCCESS&&!_recursion_protection_NAME_23.contains(_position)){
						_recursion_protection_NAME_23.add(_position);
						parse_NAME();
						_recursion_protection_NAME_23.remove(_position_NAME);
					}
					else {
						_state=FAILED;
					}
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
						_token__anonymous_40.add(_position__anonymous_40,_token);
					}
					_token=_token__anonymous_40;
				}
			}
		}
		public void parse__anonymous_144(){
			int _position__anonymous_144 = -1;
			Token.Parsed _token__anonymous_144 = null;
			_position__anonymous_144=_position;
			_token__anonymous_144=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_145();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_144)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_144;
			}
			else {
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='+'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_50.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_145)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_145;
			}
			else {
				Token.Parsed _tokenname_var_element = _token;
				_token=new Tokens.Name.NameVarToken();
				int _position_name_var_element = _position;
				parse_name_var_element();
				if (_state==SUCCESS){
					_tokenname_var_element.add(_position_name_var_element,_token);
				}
				_token=_tokenname_var_element;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_145)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_145;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_145.addAll(_token);
			}
			_token=_token__anonymous_145;
		}
		public Parser.NameList getClassNamesAdditionsAnonymous42(){
			return class_names_additions__anonymous_42;
		}
		public void setClassNamesAdditionsAnonymous42(Parser.NameList newClassNamesAdditionsAnonymous42){
			class_names_additions__anonymous_42 = newClassNamesAdditionsAnonymous42;
		}
		public Parser.NameList getClassVariableNamesAdditionsAnonymous42(){
			return class_variable_names_additions__anonymous_42;
		}
		public void setClassVariableNamesAdditionsAnonymous42(Parser.NameList newClassVariableNamesAdditionsAnonymous42){
			class_variable_names_additions__anonymous_42 = newClassVariableNamesAdditionsAnonymous42;
		}
		public void parse__anonymous_42(){
			int _position__anonymous_42 = -1;
			Token.Parsed _token__anonymous_42 = null;
			class_names_additions__anonymous_42=class_names_additions;
			class_names_additions=new Parser.NameList.Child(class_names);
			class_variable_names_additions__anonymous_42=class_variable_names_additions;
			class_variable_names_additions=new Parser.NameList.Child(class_variable_names);
			_position__anonymous_42=_position;
			_token__anonymous_42=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_42)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_42;
			}
			else {
				parse_NAME();
				if (_state==SUCCESS){
					String _value = _token.getLastValue();
					if (_value!=null&&class_variable_names.add(_value)){
						class_variable_names_additions.add(_value);
					}
					_token.add(_position,new Tokens.Name.TemplateTypeNameToken(_value));
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_42)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_42;
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
						_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
						_position=_position+1;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_42)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_42;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_42.addAll(_token);
			}
			_token=_token__anonymous_42;
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
			class_names_additions=class_names_additions__anonymous_42;
			class_variable_names_additions=class_variable_names_additions__anonymous_42;
		}
		public void parse__anonymous_146(){
			int _position__anonymous_146 = -1;
			Token.Parsed _token__anonymous_146 = null;
			_position__anonymous_146=_position;
			_token__anonymous_146=_token;
			_token=new Tokens.Name.ExactToken();
			Token.Parsed _tokenNUMBER = _token;
			_token=new Tokens.Name.NAMEToken();
			int _position_NUMBER = _position;
			parse_NUMBER();
			if (_state==SUCCESS){
				_tokenNUMBER.add(_position_NUMBER,_token);
			}
			_token=_tokenNUMBER;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_146)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_146;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_146.add(_position__anonymous_146,_token);
			}
			_token=_token__anonymous_146;
		}
		public void parse__anonymous_41(){
			int _position__anonymous_41 = -1;
			Token.Parsed _token__anonymous_41 = null;
			_position__anonymous_41=_position;
			_token__anonymous_41=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_42();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_41)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_41;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_41.addAll(_token);
			}
			_token=_token__anonymous_41;
		}
		public void parse__anonymous_147(){
			int _position__anonymous_147 = -1;
			Token.Parsed _token__anonymous_147 = null;
			_position__anonymous_147=_position;
			_token__anonymous_147=_token;
			_token=new Tokens.Name.ExactToken();
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_4.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_147)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_147;
			}
			else {
				int _state_87 = _state;
				parse__anonymous_148();
				if (_state_87==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_147)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_147;
				}
				else {
					parse__anonymous_149();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_147)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_147;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_147.add(_position__anonymous_147,_token);
			}
			_token=_token__anonymous_147;
		}
		public void parse_NUMBER(){
			int _position_NUMBER = -1;
			Token.Parsed _token_NUMBER = null;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public Parser.NameList getClassVariableNamesClassBody(){
			return class_variable_names_class_body;
		}
		public void setClassVariableNamesClassBody(Parser.NameList newClassVariableNamesClassBody){
			class_variable_names_class_body = newClassVariableNamesClassBody;
		}
		public Parser.NameList getVariableNamesClassBody(){
			return variable_names_class_body;
		}
		public void setVariableNamesClassBody(Parser.NameList newVariableNamesClassBody){
			variable_names_class_body = newVariableNamesClassBody;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_class_body=_position;
				_token_class_body=_token;
				_token=new Token.Parsed("$ANON");
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
					_token_class_body.addAll(_token);
				}
				_token=_token_class_body;
				if (_state==SUCCESS&&brace_2.get(_position_class_body_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_2.get(_position_class_body_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_class_body_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
			parse__anonymous_34();
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
			parse__anonymous_141();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_140)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_140;
			}
			else {
				Token.Parsed _tokenname_var_element = _token;
				_token=new Tokens.Name.NameVarToken();
				int _position_name_var_element = _position;
				parse_name_var_element();
				if (_state==SUCCESS){
					_tokenname_var_element.add(_position_name_var_element,_token);
				}
				_token=_tokenname_var_element;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_140)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_140;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_140.addAll(_token);
			}
			_token=_token__anonymous_140;
		}
		public Parser.NameList getClassNamesAdditionsAnonymous32(){
			return class_names_additions__anonymous_32;
		}
		public void setClassNamesAdditionsAnonymous32(Parser.NameList newClassNamesAdditionsAnonymous32){
			class_names_additions__anonymous_32 = newClassNamesAdditionsAnonymous32;
		}
		public Parser.NameList getClassVariableNamesAdditionsAnonymous32(){
			return class_variable_names_additions__anonymous_32;
		}
		public void setClassVariableNamesAdditionsAnonymous32(Parser.NameList newClassVariableNamesAdditionsAnonymous32){
			class_variable_names_additions__anonymous_32 = newClassVariableNamesAdditionsAnonymous32;
		}
		public void parse__anonymous_32(){
			int _position__anonymous_32 = -1;
			Token.Parsed _token__anonymous_32 = null;
			class_names_additions__anonymous_32=class_names_additions;
			class_names_additions=new Parser.NameList.Child(class_names);
			class_variable_names_additions__anonymous_32=class_variable_names_additions;
			class_variable_names_additions=new Parser.NameList.Child(class_variable_names);
			_position__anonymous_32=_position;
			_token__anonymous_32=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_32)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_32;
			}
			else {
				parse_NAME();
				if (_state==SUCCESS){
					String _value = _token.getLastValue();
					if (_value!=null&&class_variable_names.add(_value)){
						class_variable_names_additions.add(_value);
					}
					_token.add(_position,new Tokens.Name.TemplateTypeNameToken(_value));
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_32)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_32;
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
						_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
						_position=_position+1;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_32)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_32;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_32.addAll(_token);
			}
			_token=_token__anonymous_32;
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
			class_names_additions=class_names_additions__anonymous_32;
			class_variable_names_additions=class_variable_names_additions__anonymous_32;
		}
		public void parse__anonymous_141(){
			int _position__anonymous_141 = -1;
			Token.Parsed _token__anonymous_141 = null;
			_position__anonymous_141=_position;
			_token__anonymous_141=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_49.__SYNTAX__);
				_position=_position+2;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_141)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_141;
			}
			else {
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
			parse__anonymous_36();
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
			_token=new Tokens.Name.ConcatToken();
			int _state_85 = _state;
			parse__anonymous_143();
			if (_state_85==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_142)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_142;
			}
			else {
				Token.Parsed _tokenname_var_element = _token;
				_token=new Tokens.Name.NameVarToken();
				int _position_name_var_element = _position;
				parse_name_var_element();
				if (_state==SUCCESS){
					_tokenname_var_element.add(_position_name_var_element,_token);
				}
				_token=_tokenname_var_element;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_142)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_142;
				}
				else {
					int _state_86 = _state;
					int _multiple_index_86 = 0;
					while (_position<_inputLength){
						parse__anonymous_144();
						if (_state==FAILED){
							break;
						}
						else {
							++_multiple_index_86;
						}
					}
					if (_multiple_index_86==0 ){
						_state=FAILED;
					}
					else if (_state_86==SUCCESS&&_multiple_index_86>0 &&_state==FAILED){
						_state=SUCCESS;
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
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_142.add(_position__anonymous_142,_token);
			}
			_token=_token__anonymous_142;
		}
		public void parse__anonymous_34(){
			int _position__anonymous_34 = -1;
			Token.Parsed _token__anonymous_34 = null;
			_position__anonymous_34=_position;
			_token__anonymous_34=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokentype_var = _token;
			_token=new Tokens.Name.ParentNameToken();
			int _position_type_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_type_var_19.contains(_position)){
				_recursion_protection_type_var_19.add(_position);
				parse_type_var();
				_recursion_protection_type_var_19.remove(_position_type_var);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokentype_var.add(_position_type_var,_token);
			}
			_token=_tokentype_var;
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_21.contains(_position)){
				_recursion_protection_inner_21.add(_position);
				parse_inner();
				_recursion_protection_inner_21.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
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
			Token.Parsed _tokentype_var = _token;
			_token=new Tokens.Name.InterfaceNameToken();
			int _position_type_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_type_var_20.contains(_position)){
				_recursion_protection_type_var_20.add(_position);
				parse_type_var();
				_recursion_protection_type_var_20.remove(_position_type_var);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokentype_var.add(_position_type_var,_token);
			}
			_token=_tokentype_var;
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
			_token=new Tokens.Name.ObjectTypeToken();
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
				_token.add(_position,Tokens.Syntax.syntax_13.__SYNTAX__);
				_position=_position+6;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_39)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_39;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_39.add(_position__anonymous_39,_token);
			}
			_token=_token__anonymous_39;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_39=_position;
				_token__anonymous_39=_token;
				_token=new Tokens.Name.ObjectTypeToken();
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
					_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
					_position=_position+10;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_39)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_39;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_39.add(_position__anonymous_39,_token);
				}
				_token=_token__anonymous_39;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_39=_position;
					_token__anonymous_39=_token;
					_token=new Tokens.Name.ObjectTypeToken();
					if (_position+5-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!='e'){
							_state=FAILED;
						}
						if (_inputArray[_position+1]!='n'){
							_state=FAILED;
						}
						if (_inputArray[_position+2]!='u'){
							_state=FAILED;
						}
						if (_inputArray[_position+3]!='m'){
							_state=FAILED;
						}
						if (_inputArray[_position+4]!=' '){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_15.__SYNTAX__);
						_position=_position+5;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"enum \"");
							_furthestPosition=_position;
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
						_token__anonymous_39.add(_position__anonymous_39,_token);
					}
					_token=_token__anonymous_39;
				}
			}
		}
		public void parse_method_argument(){
			int _position_method_argument = -1;
			Token.Parsed _token_method_argument = null;
			_position_method_argument=_position;
			_token_method_argument=_token;
			_token=new Tokens.Rule.MethodArgumentToken();
			int _position_class_declaration = _position;
			if (_state==SUCCESS&&!_recursion_protection_class_declaration_69.contains(_position)){
				_recursion_protection_class_declaration_69.add(_position);
				parse_class_declaration();
				_recursion_protection_class_declaration_69.remove(_position_class_declaration);
			}
			else {
				_state=FAILED;
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
			if (_state==SUCCESS){
				_token_method_argument.add(_position_method_argument,_token);
			}
			_token=_token_method_argument;
			if (_state==FAILED){
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position_method_argument=_position;
					_token_method_argument=_token;
					_token=new Tokens.Rule.MethodArgumentToken();
					int _position_variable_declaration = _position;
					if (_state==SUCCESS&&!_recursion_protection_variable_declaration_70.contains(_position)){
						_recursion_protection_variable_declaration_70.add(_position);
						parse_variable_declaration();
						_recursion_protection_variable_declaration_70.remove(_position_variable_declaration);
					}
					else {
						_state=FAILED;
					}
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
							_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
							_position=_position+1;
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					if (_state==FAILED){
						_state=SUCCESS;
						_position_method_argument=_position;
						_token_method_argument=_token;
						_token=new Tokens.Rule.MethodArgumentToken();
						int _position_as_statement = _position;
						if (_state==SUCCESS&&!_recursion_protection_as_statement_71.contains(_position)){
							_recursion_protection_as_statement_71.add(_position);
							parse_as_statement();
							_recursion_protection_as_statement_71.remove(_position_as_statement);
						}
						else {
							_state=FAILED;
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
						if (_state==SUCCESS){
							_token_method_argument.add(_position_method_argument,_token);
						}
						_token=_token_method_argument;
						if (_state==FAILED){
							_state=SUCCESS;
							_position_method_argument=_position;
							_token_method_argument=_token;
							_token=new Tokens.Rule.MethodArgumentToken();
							int _position_body_statement = _position;
							if (_state==SUCCESS&&!_recursion_protection_body_statement_72.contains(_position)){
								_recursion_protection_body_statement_72.add(_position);
								parse_body_statement();
								_recursion_protection_body_statement_72.remove(_position_body_statement);
							}
							else {
								_state=FAILED;
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
							if (_state==SUCCESS){
								_token_method_argument.add(_position_method_argument,_token);
							}
							_token=_token_method_argument;
							if (_state==FAILED){
								_state=SUCCESS;
								_position_method_argument=_position;
								_token_method_argument=_token;
								_token=new Tokens.Rule.MethodArgumentToken();
								Token.Parsed _tokenmethod_body = _token;
								_token=new Tokens.Name.BodyEntriesToken();
								int _position_method_body = _position;
								if (_state==SUCCESS&&!_recursion_protection_method_body_73.contains(_position)){
									_recursion_protection_method_body_73.add(_position);
									parse_method_body();
									_recursion_protection_method_body_73.remove(_position_method_body);
								}
								else {
									_state=FAILED;
								}
								if (_state==SUCCESS){
									_tokenmethod_body.add(_position_method_body,_token);
								}
								_token=_tokenmethod_body;
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
							}
						}
					}
				}
			}
		}
		public void parse_NAME(){
			int _position_NAME = -1;
			Token.Parsed _token_NAME = null;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public void parse__anonymous_38(){
			int _position__anonymous_38 = -1;
			Token.Parsed _token__anonymous_38 = null;
			_position__anonymous_38=_position;
			_token__anonymous_38=_token;
			_token=new Token.Parsed("$ANON");
			int _position_weak = _position;
			if (_state==SUCCESS&&!_recursion_protection_weak_22.contains(_position)){
				_recursion_protection_weak_22.add(_position);
				parse_weak();
				_recursion_protection_weak_22.remove(_position_weak);
			}
			else {
				_state=FAILED;
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
		public void parse__anonymous_137(){
			int _position__anonymous_137 = -1;
			Token.Parsed _token__anonymous_137 = null;
			_position__anonymous_137=_position;
			_token__anonymous_137=_token;
			_token=new Tokens.Name.AccessToken();
			int _state_83 = _state;
			parse__anonymous_138();
			if (_state_83==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_137)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_137;
			}
			else {
				Token.Parsed _tokenname_var_element = _token;
				_token=new Tokens.Name.NameVarToken();
				int _position_name_var_element = _position;
				parse_name_var_element();
				if (_state==SUCCESS){
					_tokenname_var_element.add(_position_name_var_element,_token);
				}
				_token=_tokenname_var_element;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_137)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_137;
				}
				else {
					int _state_84 = _state;
					int _multiple_index_84 = 0;
					while (_position<_inputLength){
						parse__anonymous_139();
						if (_state==FAILED){
							break;
						}
						else {
							++_multiple_index_84;
						}
					}
					if (_multiple_index_84==0 ){
						_state=FAILED;
					}
					else if (_state_84==SUCCESS&&_multiple_index_84>0 &&_state==FAILED){
						_state=SUCCESS;
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
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_137.add(_position__anonymous_137,_token);
			}
			_token=_token__anonymous_137;
		}
		public void parse__anonymous_138(){
			int _position__anonymous_138 = -1;
			Token.Parsed _token__anonymous_138 = null;
			_position__anonymous_138=_position;
			_token__anonymous_138=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			}
			if (_state==SUCCESS){
				_token__anonymous_139.addAll(_token);
			}
			_token=_token__anonymous_139;
		}
		public Parser.NameList getVariableNamesAdditionsAnonymous133(){
			return variable_names_additions__anonymous_133;
		}
		public void setVariableNamesAdditionsAnonymous133(Parser.NameList newVariableNamesAdditionsAnonymous133){
			variable_names_additions__anonymous_133 = newVariableNamesAdditionsAnonymous133;
		}
		public void parse__anonymous_133(){
			int _position__anonymous_133 = -1;
			Token.Parsed _token__anonymous_133 = null;
			variable_names_additions__anonymous_133=variable_names_additions;
			variable_names_additions=new Parser.NameList.Child(variable_names);
			_position__anonymous_133=_position;
			_token__anonymous_133=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenname_var = _token;
			_token=new Tokens.Name.VariableNameToken();
			int _position_name_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_name_var_79.contains(_position)){
				_recursion_protection_name_var_79.add(_position);
				parse_name_var();
				_recursion_protection_name_var_79.remove(_position_name_var);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokenname_var.add(_position_name_var,_token);
			}
			_token=_tokenname_var;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_133)");
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
				variable_names.removeAll(variable_names_additions);
				variable_names_additions.clear();
				_state=SUCCESS;
				_position__anonymous_133=_position;
				_token__anonymous_133=_token;
				_token=new Token.Parsed("$ANON");
				int _position_NAME = _position;
				if (_state==SUCCESS&&!_recursion_protection_NAME_80.contains(_position)){
					_recursion_protection_NAME_80.add(_position);
					parse_NAME();
					_recursion_protection_NAME_80.remove(_position_NAME);
				}
				else {
					_state=FAILED;
				}
				if (_state==SUCCESS){
					String _value = _token.getLastValue();
					if (_value!=null&&variable_names.add(_value)){
						variable_names_additions.add(_value);
					}
					_token.add(_position,new Tokens.Name.VariableNameToken(_value));
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_133)");
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
				variable_names_additions=variable_names_additions__anonymous_133;
			}
		}
		public Parser.NameList getClassVariableNamesAdditionsAnonymous134(){
			return class_variable_names_additions__anonymous_134;
		}
		public void setClassVariableNamesAdditionsAnonymous134(Parser.NameList newClassVariableNamesAdditionsAnonymous134){
			class_variable_names_additions__anonymous_134 = newClassVariableNamesAdditionsAnonymous134;
		}
		public void parse__anonymous_134(){
			int _position__anonymous_134 = -1;
			Token.Parsed _token__anonymous_134 = null;
			class_variable_names_additions__anonymous_134=class_variable_names_additions;
			class_variable_names_additions=new Parser.NameList.Child(class_variable_names);
			_position__anonymous_134=_position;
			_token__anonymous_134=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenname_var = _token;
			_token=new Tokens.Name.VariableNameToken();
			int _position_name_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_name_var_81.contains(_position)){
				_recursion_protection_name_var_81.add(_position);
				parse_name_var();
				_recursion_protection_name_var_81.remove(_position_name_var);
			}
			else {
				_state=FAILED;
			}
			if (_state==SUCCESS){
				_tokenname_var.add(_position_name_var,_token);
			}
			_token=_tokenname_var;
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(_anonymous_134)");
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
			if (_state==FAILED){
				class_variable_names.removeAll(class_variable_names_additions);
				class_variable_names_additions.clear();
				_state=SUCCESS;
				_position__anonymous_134=_position;
				_token__anonymous_134=_token;
				_token=new Token.Parsed("$ANON");
				int _position_NAME = _position;
				if (_state==SUCCESS&&!_recursion_protection_NAME_82.contains(_position)){
					_recursion_protection_NAME_82.add(_position);
					parse_NAME();
					_recursion_protection_NAME_82.remove(_position_NAME);
				}
				else {
					_state=FAILED;
				}
				if (_state==SUCCESS){
					String _value = _token.getLastValue();
					if (_value!=null&&class_variable_names.add(_value)){
						class_variable_names_additions.add(_value);
					}
					_token.add(_position,new Tokens.Name.VariableNameToken(_value));
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(_anonymous_134)");
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
				class_variable_names_additions=class_variable_names_additions__anonymous_134;
			}
		}
		public void parse__anonymous_31(){
			int _position__anonymous_31 = -1;
			Token.Parsed _token__anonymous_31 = null;
			_position__anonymous_31=_position;
			_token__anonymous_31=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_32();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_31)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_31;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_31.addAll(_token);
			}
			_token=_token__anonymous_31;
		}
		public void parse__anonymous_135(){
			int _position__anonymous_135 = -1;
			Token.Parsed _token__anonymous_135 = null;
			_position__anonymous_135=_position;
			_token__anonymous_135=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_136();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_135)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_135;
			}
			else {
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
			_token=new Tokens.Name.ClassNameToken();
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
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_30)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_30;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_30.add(_position__anonymous_30,_token);
			}
			_token=_token__anonymous_30;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_30=_position;
				_token__anonymous_30=_token;
				_token=new Tokens.Name.ClassNameToken();
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
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_30)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_30;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_30.add(_position__anonymous_30,_token);
				}
				_token=_token__anonymous_30;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_30=_position;
					_token__anonymous_30=_token;
					_token=new Tokens.Name.ClassNameToken();
					int _position_NAME = _position;
					if (_state==SUCCESS&&!_recursion_protection_NAME_18.contains(_position)){
						_recursion_protection_NAME_18.add(_position);
						parse_NAME();
						_recursion_protection_NAME_18.remove(_position_NAME);
					}
					else {
						_state=FAILED;
					}
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
						_token__anonymous_30.add(_position__anonymous_30,_token);
					}
					_token=_token__anonymous_30;
				}
			}
		}
		public void parse__anonymous_136(){
			int _position__anonymous_136 = -1;
			Token.Parsed _token__anonymous_136 = null;
			_position__anonymous_136=_position;
			_token__anonymous_136=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_136)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_136;
			}
			else {
				parse_type_var();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_136)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_136;
				}
				else {
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_29)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_29;
			}
			else {
				parse_packageName();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_29)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_29;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_29.addAll(_token);
			}
			_token=_token__anonymous_29;
		}
		public Parser.NameList getClassVariableNamesMethodArguments(){
			return class_variable_names_method_arguments;
		}
		public void setClassVariableNamesMethodArguments(Parser.NameList newClassVariableNamesMethodArguments){
			class_variable_names_method_arguments = newClassVariableNamesMethodArguments;
		}
		public Parser.NameList getVariableNamesMethodArguments(){
			return variable_names_method_arguments;
		}
		public void setVariableNamesMethodArguments(Parser.NameList newVariableNamesMethodArguments){
			variable_names_method_arguments = newVariableNamesMethodArguments;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
				if (_state==SUCCESS&&brace_3.get(_position_method_arguments_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_method_arguments_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_method_arguments_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_22)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_22;
			}
			else {
				parse_packageName();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_22)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_22;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_22.addAll(_token);
			}
			_token=_token__anonymous_22;
		}
		public void parse__anonymous_173(){
			int _position__anonymous_173 = -1;
			Token.Parsed _token__anonymous_173 = null;
			_position__anonymous_173=_position;
			_token__anonymous_173=_token;
			_token=new Tokens.Name.ConcatToken();
			int _state_98 = _state;
			parse__anonymous_174();
			if (_state_98==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_173)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_173;
			}
			else {
				int _state_99 = _state;
				parse__anonymous_175();
				if (_state_99==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_173)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_173;
				}
				else {
					Token.Parsed _tokentype_var_element = _token;
					_token=new Tokens.Name.TypeVarToken();
					int _position_type_var_element = _position;
					parse_type_var_element();
					if (_state==SUCCESS){
						_tokentype_var_element.add(_position_type_var_element,_token);
					}
					_token=_tokentype_var_element;
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_173)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_173;
					}
					else {
						int _state_100 = _state;
						int _multiple_index_100 = 0;
						while (_position<_inputLength){
							parse__anonymous_176();
							if (_state==FAILED){
								break;
							}
							else {
								++_multiple_index_100;
							}
						}
						if (_multiple_index_100==0 ){
							_state=FAILED;
						}
						else if (_state_100==SUCCESS&&_multiple_index_100>0 &&_state==FAILED){
							_state=SUCCESS;
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
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_173.add(_position__anonymous_173,_token);
			}
			_token=_token__anonymous_173;
		}
		public void parse__anonymous_21(){
			int _position__anonymous_21 = -1;
			Token.Parsed _token__anonymous_21 = null;
			_position__anonymous_21=_position;
			_token__anonymous_21=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_22();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_21)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_21;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_21.addAll(_token);
			}
			_token=_token__anonymous_21;
		}
		public void parse_name_var(){
			int _position_name_var = -1;
			Token.Parsed _token_name_var = null;
			_position_name_var=_position;
			_token_name_var=_token;
			_token=new Tokens.Rule.NameVarToken();
			parse__anonymous_137();
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position_name_var=_position;
				_token_name_var=_token;
				_token=new Tokens.Rule.NameVarToken();
				parse__anonymous_142();
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
				if (_state==FAILED){
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
				if (_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_56.ISTYPENAME);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			int _position_inner = _position;
			if (_state==SUCCESS&&!_recursion_protection_inner_15.contains(_position)){
				_recursion_protection_inner_15.add(_position);
				parse_inner();
				_recursion_protection_inner_15.remove(_position_inner);
			}
			else {
				_state=FAILED;
			}
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='>'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(_anonymous_23)");
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_23=_position;
				_token__anonymous_23=_token;
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
					_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
					_position=_position+6;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(_anonymous_23)");
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
		}
		public void parse__anonymous_176(){
			int _position__anonymous_176 = -1;
			Token.Parsed _token__anonymous_176 = null;
			_position__anonymous_176=_position;
			_token__anonymous_176=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_177();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_176)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_176;
			}
			else {
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
			_token=new Tokens.Name.ObjectTypeToken();
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
				_token.add(_position,Tokens.Syntax.syntax_13.__SYNTAX__);
				_position=_position+6;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_26)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_26;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_26.add(_position__anonymous_26,_token);
			}
			_token=_token__anonymous_26;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_26=_position;
				_token__anonymous_26=_token;
				_token=new Tokens.Name.ObjectTypeToken();
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
					_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
					_position=_position+10;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_26)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_26;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_26.add(_position__anonymous_26,_token);
				}
				_token=_token__anonymous_26;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_26=_position;
					_token__anonymous_26=_token;
					_token=new Tokens.Name.ObjectTypeToken();
					if (_position+5-1 >=_inputLength){
						_state=FAILED;
					}
					else {
						if (_inputArray[_position+0]!='e'){
							_state=FAILED;
						}
						if (_inputArray[_position+1]!='n'){
							_state=FAILED;
						}
						if (_inputArray[_position+2]!='u'){
							_state=FAILED;
						}
						if (_inputArray[_position+3]!='m'){
							_state=FAILED;
						}
						if (_inputArray[_position+4]!=' '){
							_state=FAILED;
						}
					}
					if (_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_15.__SYNTAX__);
						_position=_position+5;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"enum \"");
							_furthestPosition=_position;
						}
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
					if (_state==SUCCESS){
						_token__anonymous_26.add(_position__anonymous_26,_token);
					}
					_token=_token__anonymous_26;
				}
			}
		}
		public void parse__anonymous_25(){
			int _position__anonymous_25 = -1;
			Token.Parsed _token__anonymous_25 = null;
			_position__anonymous_25=_position;
			_token__anonymous_25=_token;
			_token=new Token.Parsed("$ANON");
			int _position_weak = _position;
			if (_state==SUCCESS&&!_recursion_protection_weak_16.contains(_position)){
				_recursion_protection_weak_16.add(_position);
				parse_weak();
				_recursion_protection_weak_16.remove(_position_weak);
			}
			else {
				_state=FAILED;
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
		public void parse__anonymous_170(){
			int _position__anonymous_170 = -1;
			Token.Parsed _token__anonymous_170 = null;
			_position__anonymous_170=_position;
			_token__anonymous_170=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_171();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_170)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_170;
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
					_token.add(_position,Tokens.Syntax.syntax_30.__SYNTAX__);
					_position=_position+1;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_170)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_170;
				}
				else {
					int _state_97 = _state;
					parse__anonymous_172();
					if (_state_97==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
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
				}
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
			parse__anonymous_29();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_28)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_28;
			}
			else {
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
				_token.add(_position,Tokens.Syntax.syntax_49.__SYNTAX__);
				_position=_position+2;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			int _position_packageName = _position;
			if (_state==SUCCESS&&!_recursion_protection_packageName_17.contains(_position)){
				_recursion_protection_packageName_17.add(_position);
				parse_packageName();
				_recursion_protection_packageName_17.remove(_position_packageName);
			}
			else {
				_state=FAILED;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_27)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_27;
			}
			else {
				int _state_26 = _state;
				while (_position<_inputLength){
					parse__anonymous_28();
					if (_state==FAILED){
						break;
					}
				}
				if (_state_26==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_27)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_27;
				}
				else {
				}
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
			parse_name_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_172)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_172;
			}
			else {
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
			parse_name_var();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_166)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_166;
			}
			else {
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
			_token=new Tokens.Name.AccessMethodToken();
			int _state_95 = _state;
			parse__anonymous_168();
			if (_state_95==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_167)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_167;
			}
			else {
				int _state_96 = _state;
				parse__anonymous_169();
				if (_state_96==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_167)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_167;
				}
				else {
					Token.Parsed _tokentype_var_element = _token;
					_token=new Tokens.Name.TypeVarToken();
					int _position_type_var_element = _position;
					parse_type_var_element();
					if (_state==SUCCESS){
						_tokentype_var_element.add(_position_type_var_element,_token);
					}
					_token=_tokentype_var_element;
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_167)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_167;
					}
					else {
						parse__anonymous_170();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_167)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_167;
						}
						else {
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_167.add(_position__anonymous_167,_token);
			}
			_token=_token__anonymous_167;
		}
		public void parse__anonymous_20(){
			int _position__anonymous_20 = -1;
			Token.Parsed _token__anonymous_20 = null;
			_position__anonymous_20=_position;
			_token__anonymous_20=_token;
			_token=new Token.Parsed("$ANON");
			parse_packageName();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_20)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_20;
			}
			else {
				int _state_22 = _state;
				while (_position<_inputLength){
					parse__anonymous_21();
					if (_state==FAILED){
						break;
					}
				}
				if (_state_22==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_20)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_20;
				}
				else {
				}
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
				if (_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_56.ISTYPENAME);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_169)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_169;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_169.addAll(_token);
			}
			_token=_token__anonymous_169;
		}
		public void parse_method_parameters(){
			int _position_method_parameters = -1;
			Token.Parsed _token_method_parameters = null;
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
				_token.add(_position,Tokens.Syntax.syntax_34.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				int _state_69 = _state;
				parse__anonymous_113();
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
					int _state_70 = _state;
					while (_position<_inputLength){
						parse__anonymous_114();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_70==SUCCESS&&_state==FAILED){
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
							_token.add(_position,Tokens.Syntax.syntax_35.__SYNTAX__);
							_position=_position+1;
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public void parse__anonymous_19(){
			int _position__anonymous_19 = -1;
			Token.Parsed _token__anonymous_19 = null;
			_position__anonymous_19=_position;
			_token__anonymous_19=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_20();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_19)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_19;
			}
			else {
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!=':'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_7.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_18=_position;
				_token__anonymous_18=_token;
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
					_token.add(_position,Tokens.Syntax.syntax_8.__SYNTAX__);
					_position=_position+4;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				_token.add(_position,Tokens.Syntax.syntax_49.__SYNTAX__);
				_position=_position+2;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_162=_position;
				_token__anonymous_162=_token;
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
					_token.add(_position,Tokens.Syntax.syntax_57.__SYNTAX__);
					_position=_position+2;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				int _position_body_statement = _position;
				if (_state==SUCCESS&&!_recursion_protection_body_statement_7.contains(_position)){
					_recursion_protection_body_statement_7.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_7.remove(_position_body_statement);
				}
				else {
					_state=FAILED;
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
			}
		}
		public void parse__anonymous_164(){
			int _position__anonymous_164 = -1;
			Token.Parsed _token__anonymous_164 = null;
			_position__anonymous_164=_position;
			_token__anonymous_164=_token;
			_token=new Tokens.Name.AsMethodToken();
			parse__anonymous_165();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_164)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_164;
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
					_token.add(_position,Tokens.Syntax.syntax_30.__SYNTAX__);
					_position=_position+1;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_164)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_164;
				}
				else {
					int _state_93 = _state;
					parse__anonymous_166();
					if (_state_93==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
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
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_164.add(_position__anonymous_164,_token);
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
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				_token.add(_position,Tokens.Syntax.syntax_49.__SYNTAX__);
				_position=_position+2;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			int _position_base_element = _position;
			if (_state==SUCCESS&&!_recursion_protection_base_element_13.contains(_position)){
				_recursion_protection_base_element_13.add(_position);
				parse_base_element();
				_recursion_protection_base_element_13.remove(_position_base_element);
			}
			else {
				_state=FAILED;
			}
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
			int _position_body_element = _position;
			if (_state==SUCCESS&&!_recursion_protection_body_element_6.contains(_position)){
				_recursion_protection_body_element_6.add(_position);
				parse_body_element();
				_recursion_protection_body_element_6.remove(_position_body_element);
			}
			else {
				_state=FAILED;
			}
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
				if (_inputArray[_position+0]!='<'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
					_position=_position+6;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				if (_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_4.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(_anonymous_16)");
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
		public void parse__anonymous_161(){
			int _position__anonymous_161 = -1;
			Token.Parsed _token__anonymous_161 = null;
			_position__anonymous_161=_position;
			_token__anonymous_161=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_162();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_161)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_161;
			}
			else {
				Token.Parsed _tokentype_var_element = _token;
				_token=new Tokens.Name.TypeVarToken();
				int _position_type_var_element = _position;
				parse_type_var_element();
				if (_state==SUCCESS){
					_tokentype_var_element.add(_position_type_var_element,_token);
				}
				_token=_tokentype_var_element;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_161)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_161;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_161.addAll(_token);
			}
			_token=_token__anonymous_161;
		}
		public void parse__anonymous_159(){
			int _position__anonymous_159 = -1;
			Token.Parsed _token__anonymous_159 = null;
			_position__anonymous_159=_position;
			_token__anonymous_159=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			parse_quote();
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
				parse__anonymous_156();
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
		public void parse__anonymous_156(){
			int _position__anonymous_156 = -1;
			Token.Parsed _token__anonymous_156 = null;
			_position__anonymous_156=_position;
			_token__anonymous_156=_token;
			_token=new Tokens.Name.NAMEToken();
			parse_NUMBER();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_156)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_156;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_156.add(_position__anonymous_156,_token);
			}
			_token=_token__anonymous_156;
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_156=_position;
				_token__anonymous_156=_token;
				_token=new Tokens.Name.NAMEToken();
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
					_token.add(_position,Tokens.Syntax.syntax_51.__SYNTAX__);
					_position=_position+5;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_156)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_156;
				}
				else {
				}
				if (_state==SUCCESS){
					_token__anonymous_156.add(_position__anonymous_156,_token);
				}
				_token=_token__anonymous_156;
				if (_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_156=_position;
					_token__anonymous_156=_token;
					_token=new Tokens.Name.NAMEToken();
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
						_token.add(_position,Tokens.Syntax.syntax_52.__SYNTAX__);
						_position=_position+4;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_156)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_156;
					}
					else {
					}
					if (_state==SUCCESS){
						_token__anonymous_156.add(_position__anonymous_156,_token);
					}
					_token=_token__anonymous_156;
					if (_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_156=_position;
						_token__anonymous_156=_token;
						_token=new Tokens.Name.NAMEToken();
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
							_token.add(_position,Tokens.Syntax.syntax_53.__SYNTAX__);
							_position=_position+4;
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_156)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_156;
						}
						else {
						}
						if (_state==SUCCESS){
							_token__anonymous_156.add(_position__anonymous_156,_token);
						}
						_token=_token__anonymous_156;
						if (_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_156=_position;
							_token__anonymous_156=_token;
							_token=new Tokens.Name.NAMEToken();
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
								_token.add(_position,Tokens.Syntax.syntax_54.__SYNTAX__);
								_position=_position+4;
								while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_156)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_156;
							}
							else {
							}
							if (_state==SUCCESS){
								_token__anonymous_156.add(_position__anonymous_156,_token);
							}
							_token=_token__anonymous_156;
							if (_state==FAILED){
								_state=SUCCESS;
								_position__anonymous_156=_position;
								_token__anonymous_156=_token;
								_token=new Tokens.Name.NAMEToken();
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
									_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
									_position=_position+5;
									while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_156)");
										_furthestPosition=_position;
									}
									_position=_position__anonymous_156;
								}
								else {
								}
								if (_state==SUCCESS){
									_token__anonymous_156.add(_position__anonymous_156,_token);
								}
								_token=_token__anonymous_156;
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_157(){
			int _position__anonymous_157 = -1;
			Token.Parsed _token__anonymous_157 = null;
			_position__anonymous_157=_position;
			_token__anonymous_157=_token;
			_token=new Tokens.Name.AccessMultiToken();
			int _state_90 = _state;
			parse__anonymous_158();
			if (_state_90==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_157)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_157;
			}
			else {
				int _state_91 = _state;
				parse__anonymous_159();
				if (_state_91==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_157)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_157;
				}
				else {
					Token.Parsed _tokentype_var_element = _token;
					_token=new Tokens.Name.TypeVarToken();
					int _position_type_var_element = _position;
					parse_type_var_element();
					if (_state==SUCCESS){
						_tokentype_var_element.add(_position_type_var_element,_token);
					}
					_token=_tokentype_var_element;
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_157)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_157;
					}
					else {
						int _state_92 = _state;
						int _multiple_index_92 = 0;
						while (_position<_inputLength){
							parse__anonymous_160();
							if (_state==FAILED){
								break;
							}
							else {
								++_multiple_index_92;
							}
						}
						if (_multiple_index_92==0 ){
							_state=FAILED;
						}
						else if (_state_92==SUCCESS&&_multiple_index_92>0 &&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_157)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_157;
						}
						else {
							int _state_94 = _state;
							parse__anonymous_163();
							if (_state_94==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
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
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_157.add(_position__anonymous_157,_token);
			}
			_token=_token__anonymous_157;
		}
		public Parser.NameList getClassVariableNamesMethodBody(){
			return class_variable_names_method_body;
		}
		public void setClassVariableNamesMethodBody(Parser.NameList newClassVariableNamesMethodBody){
			class_variable_names_method_body = newClassVariableNamesMethodBody;
		}
		public Parser.NameList getVariableNamesMethodBody(){
			return variable_names_method_body;
		}
		public void setVariableNamesMethodBody(Parser.NameList newVariableNamesMethodBody){
			variable_names_method_body = newVariableNamesMethodBody;
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
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
					_position_method_body=_position;
					_token_method_body=_token;
					_token=new Token.Parsed("$ANON");
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
						_token_method_body.addAll(_token);
					}
					_token=_token_method_body;
					if (_state==SUCCESS&&brace_2.get(_position_method_body_brace)==_position){
						_position+=1;
					}
					else {
						_state=SUCCESS;
						_result_acceptor.add(_result);
						_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
						_position=brace_2.get(_position_method_body_brace)+1;
						_succeedOnEnd=false;
					}
					_inputLength=_length_method_body_brace;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
					class_variable_names=class_variable_names_method_body;
					variable_names=variable_names_method_body;
				}
				else {
					_position=brace_2.get(_position)+1;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				if (_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_56.ISTYPENAME);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void parse_body_element(){
			int _position_body_element = -1;
			Token.Parsed _token_body_element = null;
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position_body_element=_position;
				_token_body_element=_token;
				_token=new Tokens.Rule.BodyElementToken();
				int _state_35 = _state;
				parse__anonymous_47();
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
					parse__anonymous_48();
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position_body_element=_position;
					_token_body_element=_token;
					_token=new Tokens.Rule.BodyElementToken();
					int _state_36 = _state;
					parse__anonymous_50();
					if (_state_36==SUCCESS&&_state==FAILED){
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
					}
					if (_state==SUCCESS){
						_token_body_element.add(_position_body_element,_token);
					}
					_token=_token_body_element;
					if (_state==FAILED){
						_state=SUCCESS;
						_position_body_element=_position;
						_token_body_element=_token;
						_token=new Tokens.Rule.BodyElementToken();
						int _position_class_declaration = _position;
						if (_state==SUCCESS&&!_recursion_protection_class_declaration_33.contains(_position)){
							_recursion_protection_class_declaration_33.add(_position);
							parse_class_declaration();
							_recursion_protection_class_declaration_33.remove(_position_class_declaration);
						}
						else {
							_state=FAILED;
						}
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
						if (_state==FAILED){
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
							if (_state==FAILED){
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
								if (_state==FAILED){
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
									if (_state==FAILED){
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
										if (_state==FAILED){
											_state=SUCCESS;
											_position_body_element=_position;
											_token_body_element=_token;
											_token=new Tokens.Rule.BodyElementToken();
											parse__anonymous_54();
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
		public void parse_type_var(){
			int _position_type_var = -1;
			Token.Parsed _token_type_var = null;
			_position_type_var=_position;
			_token_type_var=_token;
			_token=new Tokens.Rule.TypeVarToken();
			parse__anonymous_157();
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position_type_var=_position;
				_token_type_var=_token;
				_token=new Tokens.Rule.TypeVarToken();
				parse__anonymous_167();
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position_type_var=_position;
					_token_type_var=_token;
					_token=new Tokens.Rule.TypeVarToken();
					parse__anonymous_173();
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
					if (_state==FAILED){
						_state=SUCCESS;
						_position_type_var=_position;
						_token_type_var=_token;
						_token=new Tokens.Rule.TypeVarToken();
						int _state_101 = _state;
						parse__anonymous_178();
						if (_state_101==SUCCESS&&_state==FAILED){
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void parse__anonymous_185(){
			int _position__anonymous_185 = -1;
			Token.Parsed _token__anonymous_185 = null;
			_position__anonymous_185=_position;
			_token__anonymous_185=_token;
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
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_185=_position;
				_token__anonymous_185=_token;
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
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public void parse__anonymous_186(){
			int _position__anonymous_186 = -1;
			Token.Parsed _token__anonymous_186 = null;
			_position__anonymous_186=_position;
			_token__anonymous_186=_token;
			_token=new Token.Parsed("$ANON");
			parse_template_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_186)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_186;
			}
			else {
			}
			if (_state==SUCCESS){
				_token__anonymous_186.addAll(_token);
			}
			_token=_token__anonymous_186;
		}
		public void parse__anonymous_180(){
			int _position__anonymous_180 = -1;
			Token.Parsed _token__anonymous_180 = null;
			_position__anonymous_180=_position;
			_token__anonymous_180=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_48.CAMEL);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void parse__anonymous_181(){
			int _position__anonymous_181 = -1;
			Token.Parsed _token__anonymous_181 = null;
			_position__anonymous_181=_position;
			_token__anonymous_181=_token;
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
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_181=_position;
				_token__anonymous_181=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
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
		}
		public void parse__anonymous_182(){
			int _position__anonymous_182 = -1;
			Token.Parsed _token__anonymous_182 = null;
			_position__anonymous_182=_position;
			_token__anonymous_182=_token;
			_token=new Token.Parsed("$ANON");
			parse_template_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_182)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_182;
			}
			else {
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
			_token=new Tokens.Name.ClassToken();
			int _state_104 = _state;
			parse__anonymous_184();
			if (_state_104==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_183)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_183;
			}
			else {
				parse__anonymous_185();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_183)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_183;
				}
				else {
					int _state_105 = _state;
					parse__anonymous_186();
					if (_state_105==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
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
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_183.add(_position__anonymous_183,_token);
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
				if (_inputArray[_position+0]!='+'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_50.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_177)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_177;
			}
			else {
				Token.Parsed _tokentype_var_element = _token;
				_token=new Tokens.Name.TypeVarToken();
				int _position_type_var_element = _position;
				parse_type_var_element();
				if (_state==SUCCESS){
					_tokentype_var_element.add(_position_type_var_element,_token);
				}
				_token=_tokentype_var_element;
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_177)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_177;
				}
				else {
				}
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
				if (_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_56.ISTYPENAME);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_178)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_178;
			}
			else {
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
			_token=new Tokens.Name.ExactToken();
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_4.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_179)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_179;
			}
			else {
				int _state_102 = _state;
				parse__anonymous_180();
				if (_state_102==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_179)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_179;
				}
				else {
					parse__anonymous_181();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_179)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_179;
					}
					else {
						int _state_103 = _state;
						parse__anonymous_182();
						if (_state_103==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
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
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_179.add(_position__anonymous_179,_token);
			}
			_token=_token__anonymous_179;
		}
		public void parse_body_statement(){
			int _position_body_statement = -1;
			Token.Parsed _token_body_statement = null;
			_position_body_statement=_position;
			_token_body_statement=_token;
			_token=new Tokens.Rule.BodyStatementToken();
			int _state_44 = _state;
			parse__anonymous_71();
			if (_state_44==SUCCESS&&_state==FAILED){
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
					int _state_46 = _state;
					while (_position<_inputLength){
						parse__anonymous_72();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_46==SUCCESS&&_state==FAILED){
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position_body_statement=_position;
				_token_body_statement=_token;
				_token=new Tokens.Rule.BodyStatementToken();
				int _position_statement_as_string = _position;
				if (_state==SUCCESS&&!_recursion_protection_statement_as_string_54.contains(_position)){
					_recursion_protection_statement_as_string_54.add(_position);
					parse_statement_as_string();
					_recursion_protection_statement_as_string_54.remove(_position_statement_as_string);
				}
				else {
					_state=FAILED;
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
				if (_state==SUCCESS){
					_token_body_statement.add(_position_body_statement,_token);
				}
				_token=_token_body_statement;
				if (_state==FAILED){
					_state=SUCCESS;
					_position_body_statement=_position;
					_token_body_statement=_token;
					_token=new Tokens.Rule.BodyStatementToken();
					int _position_statement_as_char = _position;
					if (_state==SUCCESS&&!_recursion_protection_statement_as_char_55.contains(_position)){
						_recursion_protection_statement_as_char_55.add(_position);
						parse_statement_as_char();
						_recursion_protection_statement_as_char_55.remove(_position_statement_as_char);
					}
					else {
						_state=FAILED;
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
					if (_state==SUCCESS){
						_token_body_statement.add(_position_body_statement,_token);
					}
					_token=_token_body_statement;
				}
			}
		}
		public void parse_weak(){
			int _position_weak = -1;
			Token.Parsed _token_weak = null;
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
				_token.add(_position,Tokens.Syntax.syntax_12.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public Parser.NameList getClassVariableNamesStatementAsBraced(){
			return class_variable_names_statement_as_braced;
		}
		public void setClassVariableNamesStatementAsBraced(Parser.NameList newClassVariableNamesStatementAsBraced){
			class_variable_names_statement_as_braced = newClassVariableNamesStatementAsBraced;
		}
		public Parser.NameList getVariableNamesStatementAsBraced(){
			return variable_names_statement_as_braced;
		}
		public void setVariableNamesStatementAsBraced(Parser.NameList newVariableNamesStatementAsBraced){
			variable_names_statement_as_braced = newVariableNamesStatementAsBraced;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_statement_as_braced=_position;
				_token_statement_as_braced=_token;
				_token=new Tokens.Rule.StatementAsBracedToken();
				int _position_body_statement = _position;
				if (_state==SUCCESS&&!_recursion_protection_body_statement_5.contains(_position)){
					_recursion_protection_body_statement_5.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_5.remove(_position_body_statement);
				}
				else {
					_state=FAILED;
				}
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
				if (_state==SUCCESS&&brace_3.get(_position_statement_as_braced_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_statement_as_braced_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_statement_as_braced_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void setClassNamesAdditionsAnonymousClass(Parser.NameList newClassNamesAdditionsAnonymousClass){
			class_names_additions_anonymous_class = newClassNamesAdditionsAnonymousClass;
		}
		public void parse_anonymous_class(){
			int _position_anonymous_class = -1;
			Token.Parsed _token_anonymous_class = null;
			class_names_additions_anonymous_class=class_names_additions;
			class_names_additions=new Parser.NameList.Child(class_names);
			_position_anonymous_class=_position;
			_token_anonymous_class=_token;
			_token=new Tokens.Rule.AnonymousClassToken();
			parse__anonymous_17();
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
					String _value = _token.getLastValue();
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
					parse__anonymous_18();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
							_furthestPosition=_position;
						}
						_position=_position_anonymous_class;
					}
					else {
						int _state_23 = _state;
						parse__anonymous_19();
						if (_state_23==SUCCESS&&_state==FAILED){
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
								_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
								_position=_position+1;
								while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				Token.Parsed _tokenall_type_name = _token;
				_token=new Tokens.Name.TemplateParameterToken();
				int _position_all_type_name = _position;
				parse_all_type_name();
				if (_state==SUCCESS){
					_tokenall_type_name.add(_position_all_type_name,_token);
				}
				_token=_tokenall_type_name;
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
			int _position_method_argument = _position;
			if (_state==SUCCESS&&!_recursion_protection_method_argument_0.contains(_position)){
				_recursion_protection_method_argument_0.add(_position);
				parse_method_argument();
				_recursion_protection_method_argument_0.remove(_position_method_argument);
			}
			else {
				_state=FAILED;
			}
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
		public void parse_base_element(){
			int _position_base_element = -1;
			Token.Parsed _token_base_element = null;
			_position_base_element=_position;
			_token_base_element=_token;
			_token=new Token.Parsed("$ANON");
			int _position_comments = _position;
			if (_state==SUCCESS&&!_recursion_protection_comments_8.contains(_position)){
				_recursion_protection_comments_8.add(_position);
				parse_comments();
				_recursion_protection_comments_8.remove(_position_comments);
			}
			else {
				_state=FAILED;
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
			if (_state==SUCCESS){
				_token_base_element.addAll(_token);
			}
			_token=_token_base_element;
			if (_state==FAILED){
				_state=SUCCESS;
				_position_base_element=_position;
				_token_base_element=_token;
				_token=new Token.Parsed("$ANON");
				int _position_imports = _position;
				if (_state==SUCCESS&&!_recursion_protection_imports_9.contains(_position)){
					_recursion_protection_imports_9.add(_position);
					parse_imports();
					_recursion_protection_imports_9.remove(_position_imports);
				}
				else {
					_state=FAILED;
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
				if (_state==SUCCESS){
					_token_base_element.addAll(_token);
				}
				_token=_token_base_element;
				if (_state==FAILED){
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
					if (_state==FAILED){
						_state=SUCCESS;
						_position_base_element=_position;
						_token_base_element=_token;
						_token=new Token.Parsed("$ANON");
						int _position_class_declaration = _position;
						if (_state==SUCCESS&&!_recursion_protection_class_declaration_10.contains(_position)){
							_recursion_protection_class_declaration_10.add(_position);
							parse_class_declaration();
							_recursion_protection_class_declaration_10.remove(_position_class_declaration);
						}
						else {
							_state=FAILED;
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
						if (_state==SUCCESS){
							_token_base_element.addAll(_token);
						}
						_token=_token_base_element;
						if (_state==FAILED){
							_state=SUCCESS;
							_position_base_element=_position;
							_token_base_element=_token;
							_token=new Token.Parsed("$ANON");
							int _position_method_declaration = _position;
							if (_state==SUCCESS&&!_recursion_protection_method_declaration_11.contains(_position)){
								_recursion_protection_method_declaration_11.add(_position);
								parse_method_declaration();
								_recursion_protection_method_declaration_11.remove(_position_method_declaration);
							}
							else {
								_state=FAILED;
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
							if (_state==SUCCESS){
								_token_base_element.addAll(_token);
							}
							_token=_token_base_element;
							if (_state==FAILED){
								_state=SUCCESS;
								_position_base_element=_position;
								_token_base_element=_token;
								_token=new Token.Parsed("$ANON");
								int _position_variable_declaration = _position;
								if (_state==SUCCESS&&!_recursion_protection_variable_declaration_12.contains(_position)){
									_recursion_protection_variable_declaration_12.add(_position);
									parse_variable_declaration();
									_recursion_protection_variable_declaration_12.remove(_position_variable_declaration);
								}
								else {
									_state=FAILED;
								}
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
										_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
										_position=_position+1;
										while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		public void parse_imports(){
			int _position_imports = -1;
			Token.Parsed _token_imports = null;
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
				_token.add(_position,Tokens.Syntax.syntax_3.__SYNTAX__);
				_position=_position+3;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public Parser.NameList getVariableNamesAdditionsBodyManipulate(){
			return variable_names_additions_body_manipulate;
		}
		public void setVariableNamesAdditionsBodyManipulate(Parser.NameList newVariableNamesAdditionsBodyManipulate){
			variable_names_additions_body_manipulate = newVariableNamesAdditionsBodyManipulate;
		}
		public void parse_body_manipulate(){
			int _position_body_manipulate = -1;
			Token.Parsed _token_body_manipulate = null;
			variable_names_additions_body_manipulate=variable_names_additions;
			variable_names_additions=new Parser.NameList.Child(variable_names);
			_position_body_manipulate=_position;
			_token_body_manipulate=_token;
			_token=new Tokens.Rule.BodyManipulateToken();
			int _state_66 = _state;
			parse__anonymous_108();
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
						_token.add(_position,Tokens.Syntax.syntax_33.methodName);
						_position=_position+2;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
						parse__anonymous_109();
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
			if (_state==FAILED){
				variable_names.removeAll(variable_names_additions);
				variable_names_additions.clear();
				_state=SUCCESS;
				_position_body_manipulate=_position;
				_token_body_manipulate=_token;
				_token=new Tokens.Rule.BodyManipulateToken();
				int _state_67 = _state;
				parse__anonymous_110();
				if (_state_67==SUCCESS&&_state==FAILED){
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
							_token.add(_position,Tokens.Syntax.syntax_7.__SYNTAX__);
							_position=_position+1;
							while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
								String _value = _token.getLastValue();
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
								int _state_68 = _state;
								int _multiple_index_68 = 0;
								while (_position<_inputLength){
									parse__anonymous_111();
									if (_state==FAILED){
										break;
									}
									else {
										++_multiple_index_68;
									}
								}
								if (_multiple_index_68==0 ){
									_state=FAILED;
								}
								else if (_state_68==SUCCESS&&_multiple_index_68>0 &&_state==FAILED){
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
		public Parser.NameList getClassVariableNamesAsStatement(){
			return class_variable_names_as_statement;
		}
		public void setClassVariableNamesAsStatement(Parser.NameList newClassVariableNamesAsStatement){
			class_variable_names_as_statement = newClassVariableNamesAsStatement;
		}
		public Parser.NameList getVariableNamesAsStatement(){
			return variable_names_as_statement;
		}
		public void setVariableNamesAsStatement(Parser.NameList newVariableNamesAsStatement){
			variable_names_as_statement = newVariableNamesAsStatement;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
				if (_state==SUCCESS&&brace_9.get(_position_as_statement_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_9.get(_position_as_statement_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_as_statement_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
		public void parse_method_declaration(){
			int _position_method_declaration = -1;
			Token.Parsed _token_method_declaration = null;
			_position_method_declaration=_position;
			_token_method_declaration=_token;
			_token=new Tokens.Rule.MethodDeclarationToken();
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
				int _state_72 = _state;
				parse__anonymous_117();
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
					Token.Parsed _tokenall_type_name = _token;
					_token=new Tokens.Name.TypeNameToken();
					int _position_all_type_name = _position;
					parse_all_type_name();
					if (_state==SUCCESS){
						_tokenall_type_name.add(_position_all_type_name,_token);
					}
					_token=_tokenall_type_name;
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_method_declaration;
					}
					else {
						int _state_73 = _state;
						while (_position<_inputLength){
							parse__anonymous_119();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_73==SUCCESS&&_state==FAILED){
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
							parse__anonymous_120();
							if (_state==FAILED){
								if (_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_method_declaration;
							}
							else {
								parse__anonymous_121();
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
		public void parse_inner(){
			int _position_inner = -1;
			Token.Parsed _token_inner = null;
			_position_inner=_position;
			_token_inner=_token;
			_token=new Tokens.Rule.InnerToken();
			parse__anonymous_23();
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
		}
		public Parser.NameList getClassVariableNamesStatementAsString(){
			return class_variable_names_statement_as_string;
		}
		public void setClassVariableNamesStatementAsString(Parser.NameList newClassVariableNamesStatementAsString){
			class_variable_names_statement_as_string = newClassVariableNamesStatementAsString;
		}
		public Parser.NameList getVariableNamesStatementAsString(){
			return variable_names_statement_as_string;
		}
		public void setVariableNamesStatementAsString(Parser.NameList newVariableNamesStatementAsString){
			variable_names_statement_as_string = newVariableNamesStatementAsString;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_statement_as_string=_position;
				_token_statement_as_string=_token;
				_token=new Tokens.Rule.StatementAsStringToken();
				int _position_body_statement = _position;
				if (_state==SUCCESS&&!_recursion_protection_body_statement_3.contains(_position)){
					_recursion_protection_body_statement_3.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_3.remove(_position_body_statement);
				}
				else {
					_state=FAILED;
				}
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
				if (_state==SUCCESS&&brace_7.get(_position_statement_as_string_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_7.get(_position_statement_as_string_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_statement_as_string_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
		public void parse_type_var_element(){
			int _position_type_var_element = -1;
			Token.Parsed _token_type_var_element = null;
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
			if (_state==FAILED){
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position_type_var_element=_position;
					_token_type_var_element=_token;
					_token=new Token.Parsed("$ANON");
					int _position_statement_as_string = _position;
					if (_state==SUCCESS&&!_recursion_protection_statement_as_string_87.contains(_position)){
						_recursion_protection_statement_as_string_87.add(_position);
						parse_statement_as_string();
						_recursion_protection_statement_as_string_87.remove(_position_statement_as_string);
					}
					else {
						_state=FAILED;
					}
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
					if (_state==FAILED){
						_state=SUCCESS;
						_position_type_var_element=_position;
						_token_type_var_element=_token;
						_token=new Token.Parsed("$ANON");
						parse__anonymous_179();
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
						if (_state==FAILED){
							_state=SUCCESS;
							_position_type_var_element=_position;
							_token_type_var_element=_token;
							_token=new Token.Parsed("$ANON");
							parse__anonymous_183();
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
						}
					}
				}
			}
		}
		public void parse_variable_assignment(){
			int _position_variable_assignment = -1;
			Token.Parsed _token_variable_assignment = null;
			_position_variable_assignment=_position;
			_token_variable_assignment=_token;
			_token=new Tokens.Rule.VariableAssignmentToken();
			int _state_79 = _state;
			parse__anonymous_130();
			if (_state_79==SUCCESS&&_state==FAILED){
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
						_token.add(_position,Tokens.Syntax.syntax_39.__SYNTAX__);
						_position=_position+1;
						while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
		}
		public void parse_packageName(){
			int _position_packageName = -1;
			Token.Parsed _token_packageName = null;
			_position_packageName=_position;
			_token_packageName=_token;
			_token=new Tokens.Rule.PackageNameToken();
			int _state_21 = _state;
			parse__anonymous_16();
			if (_state_21==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
					_furthestPosition=_position;
				}
				_position=_position_packageName;
			}
			else {
				parse_NAME();
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
						_furthestPosition=_position;
					}
					_position=_position_packageName;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token_packageName.add(_position_packageName,_token);
			}
			_token=_token_packageName;
			if (_state==FAILED){
				_state=SUCCESS;
				_position_packageName=_position;
				_token_packageName=_token;
				_token=new Tokens.Rule.PackageNameToken();
				int _position_quote = _position;
				if (_state==SUCCESS&&!_recursion_protection_quote_14.contains(_position)){
					_recursion_protection_quote_14.add(_position);
					parse_quote();
					_recursion_protection_quote_14.remove(_position_quote);
				}
				else {
					_state=FAILED;
				}
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
				if (_state==FAILED){
					_state=SUCCESS;
					_position_packageName=_position;
					_token_packageName=_token;
					_token=new Tokens.Rule.PackageNameToken();
					parse_statement_as_string();
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
				}
			}
		}
		public Parser.NameList getClassVariableNamesComments(){
			return class_variable_names_comments;
		}
		public void setClassVariableNamesComments(Parser.NameList newClassVariableNamesComments){
			class_variable_names_comments = newClassVariableNamesComments;
		}
		public Parser.NameList getVariableNamesComments(){
			return variable_names_comments;
		}
		public void setVariableNamesComments(Parser.NameList newVariableNamesComments){
			variable_names_comments = newVariableNamesComments;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
				if (_state==SUCCESS&&brace_1.get(_position_comments_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_1.get(_position_comments_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_comments_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
		public void setClassVariableNamesTemplateParameters(Parser.NameList newClassVariableNamesTemplateParameters){
			class_variable_names_template_parameters = newClassVariableNamesTemplateParameters;
		}
		public Parser.NameList getVariableNamesTemplateParameters(){
			return variable_names_template_parameters;
		}
		public void setVariableNamesTemplateParameters(Parser.NameList newVariableNamesTemplateParameters){
			variable_names_template_parameters = newVariableNamesTemplateParameters;
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
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
					_position_template_parameters=_position;
					_token_template_parameters=_token;
					_token=new Tokens.Rule.TemplateParametersToken();
					Token.Parsed _tokenall_type_name = _token;
					_token=new Tokens.Name.TemplateParameterToken();
					int _position_all_type_name = _position;
					if (_state==SUCCESS&&!_recursion_protection_all_type_name_1.contains(_position)){
						_recursion_protection_all_type_name_1.add(_position);
						parse_all_type_name();
						_recursion_protection_all_type_name_1.remove(_position_all_type_name);
					}
					else {
						_state=FAILED;
					}
					if (_state==SUCCESS){
						_tokenall_type_name.add(_position_all_type_name,_token);
					}
					_token=_tokenall_type_name;
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
					if (_state==SUCCESS&&brace_4.get(_position_template_parameters_brace)==_position){
						_position+=1;
					}
					else {
						_state=SUCCESS;
						_result_acceptor.add(_result);
						_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
						_position=brace_4.get(_position_template_parameters_brace)+1;
						_succeedOnEnd=false;
					}
					_inputLength=_length_template_parameters_brace;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
					class_variable_names=class_variable_names_template_parameters;
					variable_names=variable_names_template_parameters;
				}
				else {
					_position=brace_4.get(_position)+1;
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
			parse__anonymous_100();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_99)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_99;
			}
			else {
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
			_token=new Tokens.Name.GroupToken();
			int _position_type_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_type_var_61.contains(_position)){
				_recursion_protection_type_var_61.add(_position);
				parse_type_var();
				_recursion_protection_type_var_61.remove(_position_type_var);
			}
			else {
				_state=FAILED;
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_98)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_98;
			}
			else {
				int _state_60 = _state;
				while (_position<_inputLength){
					parse__anonymous_99();
					if (_state==FAILED){
						break;
					}
				}
				if (_state_60==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_98)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_98;
				}
				else {
					int _state_61 = _state;
					parse__anonymous_101();
					if (_state_61==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_98)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_98;
					}
					else {
						int _state_62 = _state;
						while (_position<_inputLength){
							parse__anonymous_102();
							if (_state==FAILED){
								break;
							}
						}
						if (_state_62==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_98)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_98;
						}
						else {
						}
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_98.add(_position__anonymous_98,_token);
			}
			_token=_token__anonymous_98;
		}
		public Parser.NameList getClassNamesAdditionsClassDeclaration(){
			return class_names_additions_class_declaration;
		}
		public void setClassNamesAdditionsClassDeclaration(Parser.NameList newClassNamesAdditionsClassDeclaration){
			class_names_additions_class_declaration = newClassNamesAdditionsClassDeclaration;
		}
		public Parser.NameList getClassVariableNamesAdditionsClassDeclaration(){
			return class_variable_names_additions_class_declaration;
		}
		public void setClassVariableNamesAdditionsClassDeclaration(Parser.NameList newClassVariableNamesAdditionsClassDeclaration){
			class_variable_names_additions_class_declaration = newClassVariableNamesAdditionsClassDeclaration;
		}
		public void parse_class_declaration(){
			int _position_class_declaration = -1;
			Token.Parsed _token_class_declaration = null;
			class_names_additions_class_declaration=class_names_additions;
			class_names_additions=new Parser.NameList.Child(class_names);
			class_variable_names_additions_class_declaration=class_variable_names_additions;
			class_variable_names_additions=new Parser.NameList.Child(class_variable_names);
			_position_class_declaration=_position;
			_token_class_declaration=_token;
			_token=new Tokens.Rule.ClassDeclarationToken();
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
				int _state_25 = _state;
				parse__anonymous_25();
				if (_state_25==SUCCESS&&_state==FAILED){
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
					parse__anonymous_26();
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_class_declaration;
					}
					else {
						parse__anonymous_27();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_class_declaration;
						}
						else {
							parse__anonymous_30();
							if (_state==SUCCESS){
								String _value = _token.getLastValue();
								if (_value!=null&&class_names.add(_value)){
									class_names_additions.add(_value);
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
								int _state_27 = _state;
								parse__anonymous_31();
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
									parse__anonymous_33();
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
										int _state_29 = _state;
										while (_position<_inputLength){
											parse__anonymous_35();
											if (_state==FAILED){
												break;
											}
										}
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
			if (_state==FAILED){
				class_names.removeAll(class_names_additions);
				class_names_additions.clear();
				class_variable_names.removeAll(class_variable_names_additions);
				class_variable_names_additions.clear();
				_state=SUCCESS;
				_position_class_declaration=_position;
				_token_class_declaration=_token;
				_token=new Tokens.Rule.ClassDeclarationToken();
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
					int _state_31 = _state;
					parse__anonymous_38();
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
						parse__anonymous_39();
						if (_state==FAILED){
							if (_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_class_declaration;
						}
						else {
							parse__anonymous_40();
							if (_state==SUCCESS){
								String _value = _token.getLastValue();
								if (_value!=null&&class_names.add(_value)){
									class_names_additions.add(_value);
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
								parse__anonymous_41();
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
										_token.add(_position,Tokens.Syntax.syntax_16.__SYNTAX__);
										_position=_position+1;
										while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
										parse__anonymous_43();
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
											if (_position+1-1 >=_inputLength){
												_state=FAILED;
											}
											else {
												if (_inputArray[_position+0]!='/'){
													_state=FAILED;
												}
											}
											if (_state==SUCCESS){
												_token.add(_position,Tokens.Syntax.syntax_16.__SYNTAX__);
												_position=_position+1;
												while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
												int _state_34 = _state;
												while (_position<_inputLength){
													parse__anonymous_45();
													if (_state==FAILED){
														break;
													}
												}
												if (_state_34==SUCCESS&&_state==FAILED){
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
		public void parse_statement_as_char(){
			int _position_statement_as_char = -1;
			Token.Parsed _token_statement_as_char = null;
			_position_statement_as_char=_position;
			_token_statement_as_char=_token;
			_token=new Tokens.Rule.StatementAsCharToken();
			if (_position+5-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='c'){
					_state=FAILED;
				}
				if (_inputArray[_position+1]!='h'){
					_state=FAILED;
				}
				if (_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if (_inputArray[_position+3]!='r'){
					_state=FAILED;
				}
				if (_inputArray[_position+4]!='\\'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_1.__SYNTAX__);
				_position=_position+5;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \"char\\\"");
					_furthestPosition=_position;
				}
			}
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_char(statement_as_char)");
					_furthestPosition=_position;
				}
				_position=_position_statement_as_char;
			}
			else {
				int _position_regex_5 = _position;
				if (_position<_inputLength){
					++_position;
				}
				else {
					_state=FAILED;
				}
				if (_state==SUCCESS){
					_token.add(_position_regex_5,new Tokens.Plain.Value(_input.substring(_position_regex_5,_position)));
					while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
						_furthestPosition=_position;
					}
					_position=_position_regex_5;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_char(statement_as_char)");
						_furthestPosition=_position;
					}
					_position=_position_statement_as_char;
				}
				else {
				}
			}
			if (_state==SUCCESS){
				_token_statement_as_char.add(_position_statement_as_char,_token);
			}
			_token=_token_statement_as_char;
		}
		public void parse__anonymous_91(){
			int _position__anonymous_91 = -1;
			Token.Parsed _token__anonymous_91 = null;
			_position__anonymous_91=_position;
			_token__anonymous_91=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
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
			parse_method_arguments();
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
			if (_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else {
				if (_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if (_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
				_position=_position+1;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_93)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_93;
			}
			else {
				parse__anonymous_94();
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
			if (_state==SUCCESS){
				_token__anonymous_93.addAll(_token);
			}
			_token=_token__anonymous_93;
		}
		public Parser.NameList getClassVariableNamesStatementAsMethod(){
			return class_variable_names_statement_as_method;
		}
		public void setClassVariableNamesStatementAsMethod(Parser.NameList newClassVariableNamesStatementAsMethod){
			class_variable_names_statement_as_method = newClassVariableNamesStatementAsMethod;
		}
		public Parser.NameList getVariableNamesStatementAsMethod(){
			return variable_names_statement_as_method;
		}
		public void setVariableNamesStatementAsMethod(Parser.NameList newVariableNamesStatementAsMethod){
			variable_names_statement_as_method = newVariableNamesStatementAsMethod;
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
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_statement_as_method=_position;
				_token_statement_as_method=_token;
				_token=new Tokens.Rule.StatementAsMethodToken();
				int _position_body_statement = _position;
				if (_state==SUCCESS&&!_recursion_protection_body_statement_4.contains(_position)){
					_recursion_protection_body_statement_4.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_4.remove(_position_body_statement);
				}
				else {
					_state=FAILED;
				}
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
				if (_state==SUCCESS&&brace_8.get(_position_statement_as_method_brace)==_position){
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_8.get(_position_statement_as_method_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_statement_as_method_brace;
				while (_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
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
			int _position_name_var = _position;
			if (_state==SUCCESS&&!_recursion_protection_name_var_59.contains(_position)){
				_recursion_protection_name_var_59.add(_position);
				parse_name_var();
				_recursion_protection_name_var_59.remove(_position_name_var);
			}
			else {
				_state=FAILED;
			}
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
			if (_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_95=_position;
				_token__anonymous_95=_token;
				_token=new Token.Parsed("$ANON");
				int _position_NAME = _position;
				if (_state==SUCCESS&&!_recursion_protection_NAME_60.contains(_position)){
					_recursion_protection_NAME_60.add(_position);
					parse_NAME();
					_recursion_protection_NAME_60.remove(_position_NAME);
				}
				else {
					_state=FAILED;
				}
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
		}
		public void parse__anonymous_94(){
			int _position__anonymous_94 = -1;
			Token.Parsed _token__anonymous_94 = null;
			_position__anonymous_94=_position;
			_token__anonymous_94=_token;
			_token=new Tokens.Name.GroupToken();
			parse__anonymous_95();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_94)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_94;
			}
			else {
				int _state_57 = _state;
				parse__anonymous_96();
				if (_state_57==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if (_state==FAILED){
					if (_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_94)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_94;
				}
				else {
					int _state_58 = _state;
					while (_position<_inputLength){
						parse__anonymous_97();
						if (_state==FAILED){
							break;
						}
					}
					if (_state_58==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if (_state==FAILED){
						if (_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_94)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_94;
					}
					else {
					}
				}
			}
			if (_state==SUCCESS){
				_token__anonymous_94.add(_position__anonymous_94,_token);
			}
			_token=_token__anonymous_94;
		}
		public void parse__anonymous_97(){
			int _position__anonymous_97 = -1;
			Token.Parsed _token__anonymous_97 = null;
			_position__anonymous_97=_position;
			_token__anonymous_97=_token;
			_token=new Token.Parsed("$ANON");
			parse_array_parameters();
			if (_state==FAILED){
				if (_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_97)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_97;
			}
			else {
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
			parse_method_arguments();
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
			public void setParentList(NameList newParentList){
				parentList = newParentList;
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
		public void setState(Integer newState){
			state = newState;
		}
		public Integer getPosition(){
			return position;
		}
		public void setPosition(Integer newPosition){
			position = newPosition;
		}
		public List<Integer> getLineNumberRanges(){
			return lineNumberRanges;
		}
		public void setLineNumberRanges(List<Integer> newLineNumberRanges){
			lineNumberRanges = newLineNumberRanges;
		}
		public String getInput(){
			return input;
		}
		public void setInput(String newInput){
			input = newInput;
		}
		public String getFileName(){
			return fileName;
		}
		public void setFileName(String newFileName){
			fileName=newFileName;
		}
		public long getParseTime(){
			return parseTime;
		}
		public void setParseTime(long newParseTime){
			parseTime = newParseTime;
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
			public void setParsedRoot(Token.Parsed newParsedRoot){
				parsedRoot = newParsedRoot;
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
			public void setRuleName(String newRuleName){
				ruleName = newRuleName;
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
			public void setResults(List<Parser.Result> newResults){
				results = newResults;
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