package com.rem.gen.parser;
import java.util.*;
import java.io.*;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Stack;
import com.rem.gen.contexts.FinalContext;
import java.io.BufferedReader;
import java.io.FileReader;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;

public class Parser{
	public static Integer SUCCESS = 0;
	public static Integer FAILED = 1;
	public static Integer FIRST_PASS = 0;
	public static Integer SECOND_PASS = 1;
	public static Set<String> fileNames = new HashSet<String>();
	public static Map<String,Parser.Context> contexts = new HashMap<String,Parser.Context>();
	protected Parser.NameList class_names_root = new Parser.NameList(null);
	protected Parser.NameList class_variable_names_root = new Parser.NameList(null);
	protected Parser.NameList variable_names_root = new Parser.NameList(null);
	public Parser() {
	}
	public void setSUCCESS(Integer newSUCCESS){
		SUCCESS = newSUCCESS;
	}
	public void setFAILED(Integer newFAILED){
		FAILED = newFAILED;
	}
	public void setFIRSTPASS(Integer newFIRSTPASS){
		FIRST_PASS = newFIRSTPASS;
	}
	public void setSECONDPASS(Integer newSECONDPASS){
		SECOND_PASS = newSECONDPASS;
	}
	public void setFileNames(Set<String> newFileNames){
		fileNames = newFileNames;
	}
	public void setContexts(Map<String,Parser.Context> newContexts){
		contexts = newContexts;
	}
	public Parser.Result parse(String fileName){
		long startParseTime = System.currentTimeMillis();
		Parser.Result firstResult = parseFile(fileName,FIRST_PASS);
		if(firstResult.getState()==SUCCESS){
			System.out.println("First-Pass Successful");
			fileNames.clear();
			Parser.Result secondResult = parseFile(fileName,SECOND_PASS);
			if(secondResult.getState()==SUCCESS){
				System.out.println("Second-Pass Successful");
			}
			else{
				System.out.println("Second-Pass Failed");
			}
			secondResult.setParseTime(System.currentTimeMillis()-startParseTime);
			return secondResult;
		}
		else{
			System.out.println("First-Pass Failed");
			firstResult.setParseTime(System.currentTimeMillis()-startParseTime);
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
		variable_names_root.add("super");
		variable_names_root.add("this");
		variable_names_root.add("null");
		variable_names_root.add("true");
		variable_names_root.add("false");
		if(_pass==FIRST_PASS){
			Parser.Context context = new FinalContext(class_names_root,class_variable_names_root,variable_names_root);
			contexts.put(fileName,context);
			return context.parse(fileName,FIRST_PASS);
		}
		else{
			contexts.get(fileName)._root=new Token.Parsed("$ROOT");
			contexts.get(fileName)._token=contexts.get(fileName)._root;
			return contexts.get(fileName).parse(fileName,SECOND_PASS);
		}
	}
	public static String readLine(String input,int position){
		int indexOfLine = input.indexOf('\n',position);
		if(indexOfLine==-1 ){
			return input.substring(position);
		}
		else{
			return input.substring(position,indexOfLine);
		}
	}
	public Parser.NameList getClassNamesRoot(){
		return class_names_root;
	}
	public void setClassNamesRoot(Parser.NameList newClassNamesRoot){
		class_names_root = newClassNamesRoot;
	}
	public Parser.NameList getClassVariableNamesRoot(){
		return class_variable_names_root;
	}
	public void setClassVariableNamesRoot(Parser.NameList newClassVariableNamesRoot){
		class_variable_names_root = newClassVariableNamesRoot;
	}
	public Parser.NameList getVariableNamesRoot(){
		return variable_names_root;
	}
	public void setVariableNamesRoot(Parser.NameList newVariableNamesRoot){
		variable_names_root = newVariableNamesRoot;
	}
	public static class Context{
		protected Integer SUCCESS = Parser.SUCCESS;
		protected Integer FAILED = Parser.FAILED;
		protected Integer FIRST_PASS = Parser.FIRST_PASS;
		protected Integer SECOND_PASS = Parser.SECOND_PASS;
		protected int _pass = FIRST_PASS;
		protected int _position = 0;
		protected int _inputLength = -1;
		protected int _state = SUCCESS;
		protected int _furthestPosition = -1;
		protected int _lineNumber = 1;
		protected String _input = null;
		protected String _directoryName = null;
		protected String _fileName = null;
		protected char[] _inputArray = null;
		protected Parser.Result _result = null;
		protected Parser.Result.Acceptor _result_acceptor = new Parser.Result.Acceptor();
		protected Boolean _succeedOnEnd = true;
		protected String _list_name_result = null;
		protected List<Integer> _lineNumberRanges = new ArrayList<Integer>();
		protected Token.Parsed _root = new Token.Parsed("$ROOT");
		protected Token.Parsed _token = _root;
		protected Set<ImportThread> _import_threads = new HashSet<ImportThread>();
		protected Map<Integer,Integer> brace_0 = new HashMap<Integer,Integer>();
		protected Parser.NameList class_names_root = null;
		protected Parser.NameList class_names = null;
		protected Map<Integer,String> _preparsed_NAME = new HashMap<Integer,String>();
		protected Parser.NameList class_variable_names_root = null;
		protected Parser.NameList class_variable_names = null;
		protected Parser.NameList variable_names_root = null;
		protected Parser.NameList variable_names = null;
		protected Map<Integer,Integer> brace_1 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_2 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_element_0 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_3 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_method_argument_1 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_4 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_5 = new HashMap<Integer,Integer>();
		protected Map<Integer,Integer> brace_6 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_statement_2 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_7 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_statement_3 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_8 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_statement_4 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_5 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_9 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_body_statement_6 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_comments_7 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_imports_8 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_declaration_9 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_declaration_10 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_11 = new HashSet<Integer>();
		protected String _import_class_file_name_value = null;
		protected Set<Integer> _recursion_protection_base_element_12 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_13 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_packageName_14 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_15 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_16 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_17 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_18 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_19 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_comments_20 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_declaration_21 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_declaration_22 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_23 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_24 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_25 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_variable_declaration_26 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_27 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_28 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_29 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_30 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_31 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_32 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_33 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_string_34 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_char_35 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_36 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_name_var_37 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_type_var_38 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_39 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_40 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_class_declaration_41 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_body_statement_42 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_method_body_43 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_44 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_inner_45 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_method_46 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_statement_as_string_47 = new HashSet<Integer>();
		protected int _readInput_1 = 0;
		public Context(final Parser.NameList class_names_root, final Parser.NameList class_variable_names_root, final Parser.NameList variable_names_root) {
			if(class_names_root != null){
				this.class_names_root = class_names_root;
			}
			if(class_variable_names_root != null){
				this.class_variable_names_root = class_variable_names_root;
			}
			if(variable_names_root != null){
				this.variable_names_root = variable_names_root;
			}
			class_names=class_names_root.push();
			class_variable_names=class_variable_names_root.push();
			variable_names=variable_names_root.push();
		}
		public Context() {
			class_names=class_names_root.push();
			class_variable_names=class_variable_names_root.push();
			variable_names=variable_names_root.push();
		}
		public Integer getSUCCESS(){
			return SUCCESS;
		}
		public void setSUCCESS(Integer newSUCCESS){
			SUCCESS = newSUCCESS;
		}
		public Integer getFAILED(){
			return FAILED;
		}
		public void setFAILED(Integer newFAILED){
			FAILED = newFAILED;
		}
		public Integer getFIRSTPASS(){
			return FIRST_PASS;
		}
		public void setFIRSTPASS(Integer newFIRSTPASS){
			FIRST_PASS = newFIRSTPASS;
		}
		public Integer getSECONDPASS(){
			return SECOND_PASS;
		}
		public void setSECONDPASS(Integer newSECONDPASS){
			SECOND_PASS = newSECONDPASS;
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
		public String get_directoryName(){
			return _directoryName;
		}
		public void set_directoryName(String new_directoryName){
			_directoryName = new_directoryName;
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
		public Set<ImportThread> get_importThreads(){
			return _import_threads;
		}
		public void set_importThreads(Set<ImportThread> new_importThreads){
			_import_threads = new_importThreads;
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
			Parser.NameList.Builder _builder_NAME = new Parser.NameList.Builder.NAME(_preparsed_NAME);
			Stack<Integer> brace_open_0 = new Stack<Integer>();
			if(fileNames.add(_fileName)==false){
				return null;
			}
			_pass=_pass_index;
			_directoryName="./";
			int indexOfDirectorySlash = _fileName.lastIndexOf("/");
			if(indexOfDirectorySlash==-1 ){
				indexOfDirectorySlash=_fileName.lastIndexOf("\\");
			}
			if(indexOfDirectorySlash>-1 ){
				_directoryName=_fileName.substring(0,indexOfDirectorySlash+1);
				_fileName=_fileName.substring(indexOfDirectorySlash+1);
			}
			StringBuilder _inputBuffer = new StringBuilder();
			try{
				BufferedReader _inputReader = new BufferedReader(new FileReader(_directoryName+_fileName));
				int _readInput = _inputReader.read();
				boolean escaping = false;
				boolean quoting = false;
				while(_readInput>=0 ){
					_builder_NAME.add(_position,(char)_readInput);
					if(_readInput!=13 ){
						_inputBuffer.append((char)_readInput);
					}
					if(_readInput=='\n'){
						_lineNumberRanges.add(_position);
					}
					if(escaping){
						escaping=false;
					}
					else if(!escaping&&_readInput=='\\'){
						escaping=true;
					}
					else if(!quoting&&_readInput=='\"'){
						quoting=true;
						brace_open_0.push(_position);
					}
					else if(quoting&&_readInput=='\"'){
						quoting=false;
						brace_0.put(brace_open_0.pop(),_position);
					}
					else if(!quoting&&!escaping){
						if(_readInput=='\''&&_readInput_1=='\''){
							if(!brace_open_6.isEmpty()){
								brace_6.put(brace_open_6.pop(),_position-1);
							}
						}
						if(_readInput=='\''&&_readInput_1=='\''){
							brace_open_6.push(_position-1);
						}
						if(_readInput=='`'){
							if(!brace_open_8.isEmpty()){
								brace_8.put(brace_open_8.pop(),_position);
							}
						}
						if(_readInput=='#'){
							if(!brace_open_1.isEmpty()){
								brace_1.put(brace_open_1.pop(),_position);
							}
						}
						if(_readInput=='\''){
							if(!brace_open_7.isEmpty()){
								brace_7.put(brace_open_7.pop(),_position);
							}
						}
						if(_readInput==')'){
							if(!brace_open_3.isEmpty()){
								brace_3.put(brace_open_3.pop(),_position);
							}
						}
						if(_readInput=='|'){
							if(!brace_open_9.isEmpty()){
								brace_9.put(brace_open_9.pop(),_position);
							}
						}
						if(_readInput=='}'){
							if(!brace_open_2.isEmpty()){
								brace_2.put(brace_open_2.pop(),_position);
							}
						}
						if(_readInput==']'){
							if(!brace_open_5.isEmpty()){
								brace_5.put(brace_open_5.pop(),_position);
							}
						}
						if(_readInput=='>'){
							if(!brace_open_4.isEmpty()){
								brace_4.put(brace_open_4.pop(),_position);
							}
						}
						if(_readInput=='`'){
							brace_open_8.push(_position);
						}
						if(_readInput=='#'){
							brace_open_1.push(_position);
						}
						if(_readInput=='\''){
							brace_open_7.push(_position);
						}
						if(_readInput=='('){
							brace_open_3.push(_position);
						}
						if(_readInput=='{'){
							brace_open_2.push(_position);
						}
						if(_readInput=='['){
							brace_open_5.push(_position);
						}
						if(_readInput=='<'){
							brace_open_4.push(_position);
						}
						if(_readInput=='|'){
							brace_open_9.push(_position);
						}
					}
					if(_readInput!=13 ){
						++_position;
					}
					_readInput_1=_readInput;
					_readInput=_inputReader.read();
				}
				_lineNumberRanges.add(_position);
				_inputReader.close();
			}
			catch(IOException e1){
			}
			_input=_inputBuffer.toString();
			_inputArray=_input.toCharArray();
			_inputLength=_inputArray.length;
			int _fileId = fileNames.size();
			this._fileName=_fileName;
			_furthestPosition=0;
			_result=null;
			_position=0;
			_state=SUCCESS;
			while(_position<_inputLength&&(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
			if(_pass==Parser.SECOND_PASS){
				class_names.extend();
			}
			if(_pass==Parser.SECOND_PASS){
				class_variable_names.extend();
			}
			if(_pass==Parser.SECOND_PASS){
				variable_names.extend();
			}
			_parse_root();
			if(_pass==Parser.FIRST_PASS){
				class_names.keep();
			}
			if(_pass==Parser.FIRST_PASS){
				class_variable_names.keep();
			}
			if(_pass==Parser.FIRST_PASS){
				variable_names.keep();
			}
			try{
				for(ImportThread _import_thread:_import_threads){
					_import_thread.join();
					if(_import_thread.getContext().get_state()==FAILED){
						_state=FAILED;
					}
				}
			}
			catch(Exception e0){
			}
			_import_threads.clear();
			if(_state==SUCCESS&&_position==_inputLength){
				if(_succeedOnEnd){
					Parser.Result.Pass pass = new Parser.Result.Pass(SUCCESS,_position,_lineNumberRanges,_input,_fileName,_root);
					pass.setup();
					_result=pass;
				}
				else{
					_result_acceptor.setFileName(_fileName);
					_result=_result_acceptor;
					_state=FAILED;
				}
			}
			else if(_state==SUCCESS){
				if(_result!=null){
					_result_acceptor.add(_result);
				}
				_result_acceptor.add(new Parser.Result.Fail.EOF(_fileName));
				_result_acceptor.setFileName(_fileName);
				_result=_result_acceptor;
				_state=FAILED;
			}
			else if(_state==FAILED){
				_result_acceptor.add(_result);
				_result_acceptor.setFileName(_fileName);
				_result=_result_acceptor;
			}
			_result.setClassNames(class_names);
			_result.setClassVariableNames(class_variable_names);
			_result.setVariableNames(variable_names);
			return _result;
		}
		public void addImportThread(String importFileName){
			synchronized(_import_threads){
				ImportThread thread = new ImportThread(this,Parser.contexts.get(importFileName),importFileName);
				_import_threads.add(thread);
				thread.start();
			}
		}
		public Map<Integer,Integer> getBrace0(){
			return brace_0;
		}
		public void setBrace0(Map<Integer,Integer> newBrace0){
			brace_0 = newBrace0;
		}
		public Parser.NameList getClassNamesRoot(){
			return class_names_root;
		}
		public void setClassNamesRoot(Parser.NameList newClassNamesRoot){
			class_names_root = newClassNamesRoot;
		}
		public Parser.NameList getClassNames(){
			return class_names;
		}
		public void setClassNames(Parser.NameList newClassNames){
			class_names = newClassNames;
		}
		public Map<Integer,String> get_preparsedNAME(){
			return _preparsed_NAME;
		}
		public void set_preparsedNAME(Map<Integer,String> new_preparsedNAME){
			_preparsed_NAME = new_preparsedNAME;
		}
		public Parser.NameList getClassVariableNamesRoot(){
			return class_variable_names_root;
		}
		public void setClassVariableNamesRoot(Parser.NameList newClassVariableNamesRoot){
			class_variable_names_root = newClassVariableNamesRoot;
		}
		public Parser.NameList getClassVariableNames(){
			return class_variable_names;
		}
		public void setClassVariableNames(Parser.NameList newClassVariableNames){
			class_variable_names = newClassVariableNames;
		}
		public Parser.NameList getVariableNamesRoot(){
			return variable_names_root;
		}
		public void setVariableNamesRoot(Parser.NameList newVariableNamesRoot){
			variable_names_root = newVariableNamesRoot;
		}
		public Parser.NameList getVariableNames(){
			return variable_names;
		}
		public void setVariableNames(Parser.NameList newVariableNames){
			variable_names = newVariableNames;
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
		public Set<Integer> get_recursionProtectionBodyElement0(){
			return _recursion_protection_body_element_0;
		}
		public void set_recursionProtectionBodyElement0(Set<Integer> new_recursionProtectionBodyElement0){
			_recursion_protection_body_element_0 = new_recursionProtectionBodyElement0;
		}
		public Map<Integer,Integer> getBrace3(){
			return brace_3;
		}
		public void setBrace3(Map<Integer,Integer> newBrace3){
			brace_3 = newBrace3;
		}
		public Set<Integer> get_recursionProtectionMethodArgument1(){
			return _recursion_protection_method_argument_1;
		}
		public void set_recursionProtectionMethodArgument1(Set<Integer> new_recursionProtectionMethodArgument1){
			_recursion_protection_method_argument_1 = new_recursionProtectionMethodArgument1;
		}
		public Map<Integer,Integer> getBrace4(){
			return brace_4;
		}
		public void setBrace4(Map<Integer,Integer> newBrace4){
			brace_4 = newBrace4;
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
		public Set<Integer> get_recursionProtectionBodyStatement6(){
			return _recursion_protection_body_statement_6;
		}
		public void set_recursionProtectionBodyStatement6(Set<Integer> new_recursionProtectionBodyStatement6){
			_recursion_protection_body_statement_6 = new_recursionProtectionBodyStatement6;
		}
		public Set<Integer> get_recursionProtectionComments7(){
			return _recursion_protection_comments_7;
		}
		public void set_recursionProtectionComments7(Set<Integer> new_recursionProtectionComments7){
			_recursion_protection_comments_7 = new_recursionProtectionComments7;
		}
		public Set<Integer> get_recursionProtectionImports8(){
			return _recursion_protection_imports_8;
		}
		public void set_recursionProtectionImports8(Set<Integer> new_recursionProtectionImports8){
			_recursion_protection_imports_8 = new_recursionProtectionImports8;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration9(){
			return _recursion_protection_class_declaration_9;
		}
		public void set_recursionProtectionClassDeclaration9(Set<Integer> new_recursionProtectionClassDeclaration9){
			_recursion_protection_class_declaration_9 = new_recursionProtectionClassDeclaration9;
		}
		public Set<Integer> get_recursionProtectionMethodDeclaration10(){
			return _recursion_protection_method_declaration_10;
		}
		public void set_recursionProtectionMethodDeclaration10(Set<Integer> new_recursionProtectionMethodDeclaration10){
			_recursion_protection_method_declaration_10 = new_recursionProtectionMethodDeclaration10;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration11(){
			return _recursion_protection_variable_declaration_11;
		}
		public void set_recursionProtectionVariableDeclaration11(Set<Integer> new_recursionProtectionVariableDeclaration11){
			_recursion_protection_variable_declaration_11 = new_recursionProtectionVariableDeclaration11;
		}
		public String get_importClassFileNameValue(){
			return _import_class_file_name_value;
		}
		public void set_importClassFileNameValue(String new_importClassFileNameValue){
			_import_class_file_name_value = new_importClassFileNameValue;
		}
		public Set<Integer> get_recursionProtectionBaseElement12(){
			return _recursion_protection_base_element_12;
		}
		public void set_recursionProtectionBaseElement12(Set<Integer> new_recursionProtectionBaseElement12){
			_recursion_protection_base_element_12 = new_recursionProtectionBaseElement12;
		}
		public Set<Integer> get_recursionProtectionInner13(){
			return _recursion_protection_inner_13;
		}
		public void set_recursionProtectionInner13(Set<Integer> new_recursionProtectionInner13){
			_recursion_protection_inner_13 = new_recursionProtectionInner13;
		}
		public Set<Integer> get_recursionProtectionPackageName14(){
			return _recursion_protection_packageName_14;
		}
		public void set_recursionProtectionPackageName14(Set<Integer> new_recursionProtectionPackageName14){
			_recursion_protection_packageName_14 = new_recursionProtectionPackageName14;
		}
		public Set<Integer> get_recursionProtectionTypeVar15(){
			return _recursion_protection_type_var_15;
		}
		public void set_recursionProtectionTypeVar15(Set<Integer> new_recursionProtectionTypeVar15){
			_recursion_protection_type_var_15 = new_recursionProtectionTypeVar15;
		}
		public Set<Integer> get_recursionProtectionTypeVar16(){
			return _recursion_protection_type_var_16;
		}
		public void set_recursionProtectionTypeVar16(Set<Integer> new_recursionProtectionTypeVar16){
			_recursion_protection_type_var_16 = new_recursionProtectionTypeVar16;
		}
		public Set<Integer> get_recursionProtectionInner17(){
			return _recursion_protection_inner_17;
		}
		public void set_recursionProtectionInner17(Set<Integer> new_recursionProtectionInner17){
			_recursion_protection_inner_17 = new_recursionProtectionInner17;
		}
		public Set<Integer> get_recursionProtectionTypeVar18(){
			return _recursion_protection_type_var_18;
		}
		public void set_recursionProtectionTypeVar18(Set<Integer> new_recursionProtectionTypeVar18){
			_recursion_protection_type_var_18 = new_recursionProtectionTypeVar18;
		}
		public Set<Integer> get_recursionProtectionTypeVar19(){
			return _recursion_protection_type_var_19;
		}
		public void set_recursionProtectionTypeVar19(Set<Integer> new_recursionProtectionTypeVar19){
			_recursion_protection_type_var_19 = new_recursionProtectionTypeVar19;
		}
		public Set<Integer> get_recursionProtectionComments20(){
			return _recursion_protection_comments_20;
		}
		public void set_recursionProtectionComments20(Set<Integer> new_recursionProtectionComments20){
			_recursion_protection_comments_20 = new_recursionProtectionComments20;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration21(){
			return _recursion_protection_class_declaration_21;
		}
		public void set_recursionProtectionClassDeclaration21(Set<Integer> new_recursionProtectionClassDeclaration21){
			_recursion_protection_class_declaration_21 = new_recursionProtectionClassDeclaration21;
		}
		public Set<Integer> get_recursionProtectionMethodDeclaration22(){
			return _recursion_protection_method_declaration_22;
		}
		public void set_recursionProtectionMethodDeclaration22(Set<Integer> new_recursionProtectionMethodDeclaration22){
			_recursion_protection_method_declaration_22 = new_recursionProtectionMethodDeclaration22;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration23(){
			return _recursion_protection_variable_declaration_23;
		}
		public void set_recursionProtectionVariableDeclaration23(Set<Integer> new_recursionProtectionVariableDeclaration23){
			_recursion_protection_variable_declaration_23 = new_recursionProtectionVariableDeclaration23;
		}
		public Set<Integer> get_recursionProtectionInner24(){
			return _recursion_protection_inner_24;
		}
		public void set_recursionProtectionInner24(Set<Integer> new_recursionProtectionInner24){
			_recursion_protection_inner_24 = new_recursionProtectionInner24;
		}
		public Set<Integer> get_recursionProtectionInner25(){
			return _recursion_protection_inner_25;
		}
		public void set_recursionProtectionInner25(Set<Integer> new_recursionProtectionInner25){
			_recursion_protection_inner_25 = new_recursionProtectionInner25;
		}
		public Set<Integer> get_recursionProtectionVariableDeclaration26(){
			return _recursion_protection_variable_declaration_26;
		}
		public void set_recursionProtectionVariableDeclaration26(Set<Integer> new_recursionProtectionVariableDeclaration26){
			_recursion_protection_variable_declaration_26 = new_recursionProtectionVariableDeclaration26;
		}
		public Set<Integer> get_recursionProtectionBodyStatement27(){
			return _recursion_protection_body_statement_27;
		}
		public void set_recursionProtectionBodyStatement27(Set<Integer> new_recursionProtectionBodyStatement27){
			_recursion_protection_body_statement_27 = new_recursionProtectionBodyStatement27;
		}
		public Set<Integer> get_recursionProtectionInner28(){
			return _recursion_protection_inner_28;
		}
		public void set_recursionProtectionInner28(Set<Integer> new_recursionProtectionInner28){
			_recursion_protection_inner_28 = new_recursionProtectionInner28;
		}
		public Set<Integer> get_recursionProtectionInner29(){
			return _recursion_protection_inner_29;
		}
		public void set_recursionProtectionInner29(Set<Integer> new_recursionProtectionInner29){
			_recursion_protection_inner_29 = new_recursionProtectionInner29;
		}
		public Set<Integer> get_recursionProtectionInner30(){
			return _recursion_protection_inner_30;
		}
		public void set_recursionProtectionInner30(Set<Integer> new_recursionProtectionInner30){
			_recursion_protection_inner_30 = new_recursionProtectionInner30;
		}
		public Set<Integer> get_recursionProtectionInner31(){
			return _recursion_protection_inner_31;
		}
		public void set_recursionProtectionInner31(Set<Integer> new_recursionProtectionInner31){
			_recursion_protection_inner_31 = new_recursionProtectionInner31;
		}
		public Set<Integer> get_recursionProtectionInner32(){
			return _recursion_protection_inner_32;
		}
		public void set_recursionProtectionInner32(Set<Integer> new_recursionProtectionInner32){
			_recursion_protection_inner_32 = new_recursionProtectionInner32;
		}
		public Set<Integer> get_recursionProtectionInner33(){
			return _recursion_protection_inner_33;
		}
		public void set_recursionProtectionInner33(Set<Integer> new_recursionProtectionInner33){
			_recursion_protection_inner_33 = new_recursionProtectionInner33;
		}
		public Set<Integer> get_recursionProtectionStatementAsString34(){
			return _recursion_protection_statement_as_string_34;
		}
		public void set_recursionProtectionStatementAsString34(Set<Integer> new_recursionProtectionStatementAsString34){
			_recursion_protection_statement_as_string_34 = new_recursionProtectionStatementAsString34;
		}
		public Set<Integer> get_recursionProtectionStatementAsChar35(){
			return _recursion_protection_statement_as_char_35;
		}
		public void set_recursionProtectionStatementAsChar35(Set<Integer> new_recursionProtectionStatementAsChar35){
			_recursion_protection_statement_as_char_35 = new_recursionProtectionStatementAsChar35;
		}
		public Set<Integer> get_recursionProtectionNameVar36(){
			return _recursion_protection_name_var_36;
		}
		public void set_recursionProtectionNameVar36(Set<Integer> new_recursionProtectionNameVar36){
			_recursion_protection_name_var_36 = new_recursionProtectionNameVar36;
		}
		public Set<Integer> get_recursionProtectionNameVar37(){
			return _recursion_protection_name_var_37;
		}
		public void set_recursionProtectionNameVar37(Set<Integer> new_recursionProtectionNameVar37){
			_recursion_protection_name_var_37 = new_recursionProtectionNameVar37;
		}
		public Set<Integer> get_recursionProtectionTypeVar38(){
			return _recursion_protection_type_var_38;
		}
		public void set_recursionProtectionTypeVar38(Set<Integer> new_recursionProtectionTypeVar38){
			_recursion_protection_type_var_38 = new_recursionProtectionTypeVar38;
		}
		public Set<Integer> get_recursionProtectionInner39(){
			return _recursion_protection_inner_39;
		}
		public void set_recursionProtectionInner39(Set<Integer> new_recursionProtectionInner39){
			_recursion_protection_inner_39 = new_recursionProtectionInner39;
		}
		public Set<Integer> get_recursionProtectionInner40(){
			return _recursion_protection_inner_40;
		}
		public void set_recursionProtectionInner40(Set<Integer> new_recursionProtectionInner40){
			_recursion_protection_inner_40 = new_recursionProtectionInner40;
		}
		public Set<Integer> get_recursionProtectionClassDeclaration41(){
			return _recursion_protection_class_declaration_41;
		}
		public void set_recursionProtectionClassDeclaration41(Set<Integer> new_recursionProtectionClassDeclaration41){
			_recursion_protection_class_declaration_41 = new_recursionProtectionClassDeclaration41;
		}
		public Set<Integer> get_recursionProtectionBodyStatement42(){
			return _recursion_protection_body_statement_42;
		}
		public void set_recursionProtectionBodyStatement42(Set<Integer> new_recursionProtectionBodyStatement42){
			_recursion_protection_body_statement_42 = new_recursionProtectionBodyStatement42;
		}
		public Set<Integer> get_recursionProtectionMethodBody43(){
			return _recursion_protection_method_body_43;
		}
		public void set_recursionProtectionMethodBody43(Set<Integer> new_recursionProtectionMethodBody43){
			_recursion_protection_method_body_43 = new_recursionProtectionMethodBody43;
		}
		public Set<Integer> get_recursionProtectionInner44(){
			return _recursion_protection_inner_44;
		}
		public void set_recursionProtectionInner44(Set<Integer> new_recursionProtectionInner44){
			_recursion_protection_inner_44 = new_recursionProtectionInner44;
		}
		public Set<Integer> get_recursionProtectionInner45(){
			return _recursion_protection_inner_45;
		}
		public void set_recursionProtectionInner45(Set<Integer> new_recursionProtectionInner45){
			_recursion_protection_inner_45 = new_recursionProtectionInner45;
		}
		public Set<Integer> get_recursionProtectionStatementAsMethod46(){
			return _recursion_protection_statement_as_method_46;
		}
		public void set_recursionProtectionStatementAsMethod46(Set<Integer> new_recursionProtectionStatementAsMethod46){
			_recursion_protection_statement_as_method_46 = new_recursionProtectionStatementAsMethod46;
		}
		public Set<Integer> get_recursionProtectionStatementAsString47(){
			return _recursion_protection_statement_as_string_47;
		}
		public void set_recursionProtectionStatementAsString47(Set<Integer> new_recursionProtectionStatementAsString47){
			_recursion_protection_statement_as_string_47 = new_recursionProtectionStatementAsString47;
		}
		public int get_readInput1(){
			return _readInput_1;
		}
		public void set_readInput1(int new_readInput1){
			_readInput_1 = new_readInput1;
		}
		public void parse_array_parameters(){
		}
		public void parse_class_file_name(){
		}
		public void parse__anonymous_88(){
		}
		public void parse__anonymous_87(){
		}
		public void parse__anonymous_89(){
		}
		public void parse_body_conditional(){
		}
		public void parse__anonymous_80(){
		}
		public void parse__anonymous_82(){
		}
		public void parse_quote(){
		}
		public void parse__anonymous_81(){
		}
		public void parse__anonymous_84(){
		}
		public void parse__anonymous_83(){
		}
		public void parse__anonymous_86(){
		}
		public void parse_all_type_name(){
		}
		public void parse_type_var(){
		}
		public void parse__anonymous_85(){
		}
		public void parse__anonymous_77(){
		}
		public void parse__anonymous_76(){
		}
		public void parse__anonymous_79(){
		}
		public void parse__anonymous_78(){
		}
		public void parse_statement_as_quote(){
		}
		public void parse__anonymous_71(){
		}
		public void parse__anonymous_70(){
		}
		public void parse__anonymous_73(){
		}
		public void parse__anonymous_72(){
		}
		public void parse__anonymous_75(){
		}
		public void parse_name_var_element(){
		}
		public void parse__anonymous_74(){
		}
		public void parse_body_statement(){
		}
		public void parse_class_element(){
		}
		public void parse__anonymous_66(){
		}
		public void parse__anonymous_65(){
		}
		public void parse__anonymous_68(){
		}
		public void parse__anonymous_67(){
		}
		public void parse_class_name_definition(){
		}
		public void parse__anonymous_69(){
		}
		public void parse_weak(){
		}
		public void parse__anonymous_60(){
		}
		public void parse__anonymous_62(){
		}
		public void parse__anonymous_61(){
		}
		public void parse__anonymous_64(){
		}
		public void parse__anonymous_63(){
		}
		public void parse_statement_as_braced(){
		}
		public void parse_variable_declaration(){
		}
		public void parse_variable_name_definition(){
		}
		public void parse__anonymous_55(){
		}
		public void parse__anonymous_54(){
		}
		public void parse__anonymous_57(){
		}
		public void parse__anonymous_56(){
		}
		public void parse__anonymous_59(){
		}
		public void parse__anonymous_58(){
		}
		public void parse_OPERATOR(){
		}
		public void parse__anonymous_51(){
		}
		public void parse__anonymous_50(){
		}
		public void parse__anonymous_53(){
		}
		public void parse__anonymous_52(){
		}
		public void parse_body_call(){
		}
		public void parse_imports__file_import(){
		}
		public void parse__anonymous_44(){
		}
		public void parse__anonymous_43(){
		}
		public void parse__anonymous_46(){
		}
		public void parse__anonymous_45(){
		}
		public void parse__anonymous_48(){
		}
		public void parse__anonymous_47(){
		}
		public void parse__anonymous_49(){
		}
		public void parse_anonymous_class(){
		}
		public void parse__anonymous_6(){
		}
		public void parse__anonymous_5(){
		}
		public void parse__anonymous_8(){
		}
		public void parse__anonymous_7(){
		}
		public void parse__anonymous_2(){
		}
		public void parse__anonymous_40(){
		}
		public void parse__anonymous_1(){
		}
		public void parse__anonymous_4(){
		}
		public void parse__anonymous_42(){
		}
		public void parse__anonymous_3(){
		}
		public void parse_base_element(){
		}
		public void parse__anonymous_41(){
		}
		public void parse__anonymous_9(){
		}
		public void parse_NUMBER(){
		}
		public void parse_class_body(){
		}
		public void parse_imports(){
		}
		public void parse_body_manipulate(){
		}
		public void parse_as_statement(){
		}
		public void parse_method_declaration(){
		}
		public void parse__anonymous_33(){
		}
		public void parse__anonymous_32(){
		}
		public void parse__anonymous_0(){
		}
		public void parse__anonymous_35(){
		}
		public void parse__anonymous_34(){
		}
		public void parse__anonymous_37(){
		}
		public void parse_inner(){
		}
		public void parse__anonymous_36(){
		}
		public void parse__anonymous_39(){
		}
		public void parse_method_argument(){
		}
		public void parse_NAME(){
		}
		public void parse__anonymous_38(){
		}
		public void parse_statement_as_string(){
		}
		public void parse__anonymous_31(){
		}
		public void parse__anonymous_30(){
		}
		public void parse__anonymous_29(){
		}
		public void parse_type_var_element(){
		}
		public void parse_method_arguments(){
		}
		public void parse__anonymous_22(){
		}
		public void parse__anonymous_21(){
		}
		public void parse_variable_assignment(){
		}
		public void parse_name_var(){
		}
		public void parse__anonymous_24(){
		}
		public void parse__anonymous_23(){
		}
		public void parse__anonymous_26(){
		}
		public void parse__anonymous_25(){
		}
		public void parse__anonymous_28(){
		}
		public void parse__anonymous_27(){
		}
		public void parse__anonymous_20(){
		}
		public void parse_method_parameters(){
		}
		public void parse_packageName(){
		}
		public void parse__anonymous_19(){
		}
		public void parse__anonymous_18(){
		}
		public void parse_comments(){
		}
		public void parse_template_parameters(){
		}
		public void parse__anonymous_11(){
		}
		public void parse__anonymous_10(){
		}
		public void parse__anonymous_13(){
		}
		public void parse__anonymous_12(){
		}
		public void parse_class_declaration(){
		}
		public void parse__anonymous_15(){
		}
		public void parse__anonymous_14(){
		}
		public void parse_statement_as_char(){
		}
		public void parse__anonymous_17(){
		}
		public void parse__anonymous_16(){
		}
		public void parse__anonymous_91(){
		}
		public void parse__anonymous_90(){
		}
		public void parse_statement_as_method(){
		}
		public void parse_method_body(){
		}
		public void parse_body_element(){
		}
		public void parse_base(){
		}
		public void _parse_root(){
			parse_base();
		}
		public static class ImportThread extends Thread{
			protected Parser.Context parentContext = null;
			protected Parser.Context context = null;
			protected String fileName = null;
			public ImportThread(final Parser.Context parentContext, final Parser.Context context, final String fileName) {
				if(parentContext != null){
					this.parentContext = parentContext;
				}
				if(context != null){
					this.context = context;
				}
				if(fileName != null){
					this.fileName = fileName;
				}
			}
			public ImportThread() {
			}
			public Parser.Context getParentContext(){
				return parentContext;
			}
			public void setParentContext(Parser.Context newParentContext){
				parentContext = newParentContext;
			}
			public Parser.Context getContext(){
				return context;
			}
			public void setContext(Parser.Context newContext){
				context = newContext;
			}
			public String getFileName(){
				return fileName;
			}
			public void setFileName(String newFileName){
				fileName = newFileName;
			}
			public void run(){
				Parser.Result result = context.parse(fileName,parentContext.get_pass());
				if(result!=null){
					parentContext.get_resultAcceptor().add(result);
					result.setFileName(fileName);
				}
			}
		}
		public static class ImportsFileImport extends FinalContext{
			public ImportsFileImport(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
				super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
			}
			public ImportsFileImport() {
			}
			public void _parse_root(){
				parse_imports__file_import();
			}
		}
	}
	public static class NameList extends HashSet<String>{
		protected NameList parent = null;
		protected Map<Integer,NameList> children = new HashMap<Integer,NameList>();
		protected List<NameList.Addition> additions = new ArrayList<NameList.Addition>();
		protected int addition_position = -1;
		public NameList(final NameList parent) {
			super();
			if(parent != null){
				this.parent = parent;
			}
		}
		public NameList() {
		}
		public NameList getParent(){
			return parent;
		}
		public void setParent(NameList newParent){
			parent = newParent;
		}
		public Map<Integer,NameList> getChildren(){
			return children;
		}
		public void setChildren(Map<Integer,NameList> newChildren){
			children = newChildren;
		}
		public List<NameList.Addition> getAdditions(){
			return additions;
		}
		public void setAdditions(List<NameList.Addition> newAdditions){
			additions = newAdditions;
		}
		public int getAdditionPosition(){
			return addition_position;
		}
		public void setAdditionPosition(int newAdditionPosition){
			addition_position = newAdditionPosition;
		}
		public NameList push(int position,int pass){
			NameList result = null;
			if(pass==Parser.SECOND_PASS){
				synchronized(children){
					result=children.get(position);
				}
			}
			if(result==null){
				result=new NameList(this);
				if(pass==Parser.FIRST_PASS){
					synchronized(children){
						children.put(position,result);
					}
				}
			}
			else{
				result.clear();
			}
			synchronized(this){
				synchronized(result){
					for(String newEntry:this){
						result.safe_add(newEntry);
					}
				}
			}
			return result;
		}
		public NameList push(){
			NameList result = new NameList(this);
			synchronized(this){
				synchronized(result){
					for(String newEntry:this){
						result.safe_add(newEntry);
					}
				}
			}
			return result;
		}
		public NameList pop(){
			return parent;
		}
		public boolean add(String addition){
			synchronized(this){
				if(super.add(addition)){
					additions.add(new NameList.Addition(addition_position,addition));
					return true;
				}
				else{
					return false;
				}
			}
		}
		public boolean safe_add(String addition){
			return super.add(addition);
		}
		public void start(int position){
			addition_position=position;
		}
		public void accept(int position){
			addition_position=position;
		}
		public void reject(int position){
			synchronized(this){
				addition_position=position;
				int i = additions.size()-1;
				while(i>=0 ){
					if(additions.get(i).getPosition()>=addition_position){
						remove(additions.remove(i).getEntry());
					}
					else{
						break;
					}
					--i;
				}
			}
		}
		public void extend(){
			synchronized(parent){
				for(String entry:parent){
					safe_add(entry);
				}
			}
		}
		public void keep(){
			synchronized(this){
				synchronized(parent){
					for(NameList.Addition addition:additions){
						parent.safe_add(addition.getEntry());
					}
				}
				additions.clear();
			}
		}
		public static class Addition{
			protected Integer position;
			protected String entry;
			public Addition(final Integer position, final String entry) {
				if(position != null){
					this.position = position;
				}
				if(entry != null){
					this.entry = entry;
				}
			}
			public Addition() {
			}
			public Integer getPosition(){
				return position;
			}
			public void setPosition(Integer newPosition){
				position = newPosition;
			}
			public String getEntry(){
				return entry;
			}
			public void setEntry(String newEntry){
				entry = newEntry;
			}
		}
		public static class Builder{
			protected StringBuilder builder = null;
			protected int length = 0;
			protected int state = 0;
			protected boolean multiple_satisfied = false;
			protected Map<Integer,String> _output = null;
			public Builder(final Map<Integer,String> _output) {
				if(_output != null){
					this._output = _output;
				}
			}
			public Builder() {
			}
			public StringBuilder getBuilder(){
				return builder;
			}
			public void setBuilder(StringBuilder newBuilder){
				builder = newBuilder;
			}
			public int getLength(){
				return length;
			}
			public void setLength(int newLength){
				length = newLength;
			}
			public int getState(){
				return state;
			}
			public void setState(int newState){
				state = newState;
			}
			public boolean getMultipleSatisfied(){
				return multiple_satisfied;
			}
			public void setMultipleSatisfied(boolean newMultipleSatisfied){
				multiple_satisfied = newMultipleSatisfied;
			}
			public Map<Integer,String> get_output(){
				return _output;
			}
			public void set_output(Map<Integer,String> new_output){
				_output = new_output;
			}
			public boolean can(int position,char newChar){
				return false;
			}
			public boolean add(int position,char newChar){
				if(can(position,newChar)){
					if(builder==null){
						builder=new StringBuilder();
						length=0;
					}
					builder.append(newChar);
					length+=1;
					return true;
				}
				else{
					if(builder!=null&&state>=0 ){
						String result = builder.toString();
						_output.put(position-length,result);
					}
					builder=null;
					state=0;
					return false;
				}
			}
			public void end(int position){
				if(builder!=null&&state>=0 ){
					String result = builder.toString();
					_output.put(position-length,result);
				}
			}
			public static class NAME extends Parser.NameList.Builder{
				public NAME(final Map<Integer,String> initalSuper_output) {
					super(initalSuper_output);
				}
				public NAME() {
				}
				public boolean can(int position,char nextChar){
					switch(state){
						case 0:{
							if((nextChar>='a'&&nextChar<='z')||(nextChar>='A'&&nextChar<='Z')||nextChar=='_'){
								state=1;
								return true;
							}
							else{
								state=-1;
								return false;
							}
						}
						case 1:{
							if((nextChar>='a'&&nextChar<='z')||(nextChar>='A'&&nextChar<='Z')||(nextChar>='0'&&nextChar<='9')||nextChar=='_'){
								return true;
							}
							else{
								state=2;
								return can(position,nextChar);
							}
						}
					}
					return false;
				}
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
		protected Set<String> class_names = null;
		protected Set<String> class_variable_names = null;
		protected Set<String> variable_names = null;
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
			while(lineNumber<lineNumberRanges.size()&&upperBound<position){
				upperBound=lineNumberRanges.get(lineNumber);
				lineNumber+=1;
			}
			return lineNumber;
		}
		public Integer getLineNumber(){
			Integer upperBound = 0;
			Integer lineNumber = 0;
			while(lineNumber<lineNumberRanges.size()&&upperBound<position){
				upperBound=lineNumberRanges.get(lineNumber);
				lineNumber+=1;
			}
			return lineNumber;
		}
		public String toString(){
			if(state==Parser.FAILED){
				Integer lineNumber = getLineNumber();
				Integer rangeIndex = lineNumber-1;
				if(rangeIndex<0 ){
					rangeIndex=0;
				}
				Integer upperBound = lineNumberRanges.get(rangeIndex);
				Integer lowerBound = 0;
				if(rangeIndex>0 ){
					lowerBound=lineNumberRanges.get(rangeIndex-1)+1;
				}
				String errorAt;
				if(upperBound<input.length()){
					errorAt=input.substring(lowerBound,position)+"$>"+input.substring(position,upperBound);
				}
				else{
					errorAt=input.substring(lowerBound,position)+"<$"+input.substring(position);
				}
				if(parseTime<=0 ){
					if(fileName==null){
						return "\n\tLine Number: "+lineNumber+"\n\tError: "+errorAt;
					}
					else{
						return "\nFile: "+fileName+" Line : "+lineNumber+"\n\tError: "+errorAt;
					}
				}
				else{
					if(position==-1 ){
						if(parseTime<1000 ){
							return "File: "+fileName+"\nParse Time: "+parseTime+"ms";
						}
						else{
							int minutes = (int)(parseTime/1000);
							int hundreds = (int)(parseTime/100)%10;
							int tens = (int)(parseTime/10)%10;
							int ones = ((int)parseTime)%10;
							return "File: "+fileName+"\nParse Time: "+minutes+"."+hundreds+""+tens+""+ones+"s";
						}
					}
					else{
						if(parseTime<1000 ){
							return "\n\tError: "+errorAt+"\n\tFile: "+fileName+" Line : "+lineNumber+"\n\tParse Time: "+parseTime+"ms";
						}
						else{
							int minutes = (int)(parseTime/1000);
							int hundreds = (int)(parseTime/100)%10;
							int tens = (int)(parseTime/10)%10;
							int ones = ((int)parseTime)%10;
							return "\n\tError: "+errorAt+"\n\tFile: "+fileName+" Line: "+lineNumber+"\n\tParse Time: "+minutes+"."+hundreds+""+tens+""+ones+"s";
						}
					}
				}
			}
			else{
				if(parseTime<=0 ){
					if(fileName==null){
						return "";
					}
					else{
						return "File: "+fileName;
					}
				}
				else{
					if(parseTime<1000 ){
						if(fileName==null){
							return "Parse Time: "+parseTime+"ms";
						}
						else{
							return "File: "+fileName+"\nParse Time: "+parseTime+"ms";
						}
					}
					else{
						int minutes = (int)(parseTime/1000);
						int hundreds = (int)(parseTime/100)%10;
						int tens = (int)(parseTime/10)%10;
						int ones = ((int)parseTime)%10;
						if(fileName==null){
							return "Parse Time: "+minutes+"."+hundreds+""+tens+""+ones+"s";
						}
						else{
							return "File: "+fileName+"\nParse Time: "+minutes+"."+hundreds+""+tens+""+ones+"s";
						}
					}
				}
			}
		}
		public Set<String> getClassNames(){
			return class_names;
		}
		public void setClassNames(Set<String> newClassNames){
			class_names = newClassNames;
		}
		public Set<String> getClassVariableNames(){
			return class_variable_names;
		}
		public void setClassVariableNames(Set<String> newClassVariableNames){
			class_variable_names = newClassVariableNames;
		}
		public Set<String> getVariableNames(){
			return variable_names;
		}
		public void setVariableNames(Set<String> newVariableNames){
			variable_names = newVariableNames;
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
				for(Integer i = 0;i<size;i++){
					if(children.get(i).getChildren().isEmpty()==false){
						if(children.get(i).getName()==null){
							setup(current,children.get(i),positions.get(i));
						}
						else{
							Token.Branch newToken = new Token.Branch(children.get(i).getName(),positions.get(i),currentPosition,this);
							current.add(newToken);
							setup(newToken,children.get(i),positions.get(i));
						}
					}
					else{
						current.add(new Token.Leaf(children.get(i).getName(),children.get(i).getValue(),positions.get(i),currentPosition,this));
					}
				}
			}
			public String toString(){
				if(fileName!=null){
					String realFileName = fileName;
					fileName=null;
					String result = super.toString();
					fileName=realFileName;
					if(result.equals("")){
						return null;
					}
					else{
						return "Passed: "+result;
					}
				}
				else{
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
				return "Failed: "+ruleName+super.toString();
			}
			public static class EOF extends Parser.Result{
				protected String erroneousFile = null;
				public EOF(final Integer initalSuperState, final Integer initalSuperPosition, final List<Integer> initalSuperLineNumberRanges, final String initalSuperInput, final String initalSuperFileName, final String erroneousFile) {
					super(initalSuperState, initalSuperPosition, initalSuperLineNumberRanges, initalSuperInput, initalSuperFileName);
					if(erroneousFile != null){
						this.erroneousFile = erroneousFile;
					}
				}
				public EOF(final String erroneousFile) {
					if(erroneousFile != null){
						this.erroneousFile = erroneousFile;
					}
				}
				public EOF() {
				}
				public String getErroneousFile(){
					return erroneousFile;
				}
				public void setErroneousFile(String newErroneousFile){
					erroneousFile = newErroneousFile;
				}
				public String toString(){
					return "End of file not reached:"+erroneousFile;
				}
			}
			public static class EOB extends Parser.Result{
				protected Integer position = -1;
				protected List<Integer> myLineNumberRanges = null;
				public EOB(final Integer initalSuperState, final Integer initalSuperPosition, final List<Integer> initalSuperLineNumberRanges, final String initalSuperInput, final String initalSuperFileName, final Integer position, final List<Integer> myLineNumberRanges) {
					super(initalSuperState, initalSuperPosition, initalSuperLineNumberRanges, initalSuperInput, initalSuperFileName);
					if(position != null){
						this.position = position;
					}
					if(myLineNumberRanges != null){
						this.myLineNumberRanges = myLineNumberRanges;
					}
				}
				public EOB(final Integer position, final List<Integer> myLineNumberRanges) {
					if(position != null){
						this.position = position;
					}
					if(myLineNumberRanges != null){
						this.myLineNumberRanges = myLineNumberRanges;
					}
				}
				public EOB() {
				}
				public Integer getPosition(){
					return position;
				}
				public void setPosition(Integer newPosition){
					position = newPosition;
				}
				public List<Integer> getMyLineNumberRanges(){
					return myLineNumberRanges;
				}
				public void setMyLineNumberRanges(List<Integer> newMyLineNumberRanges){
					myLineNumberRanges = newMyLineNumberRanges;
				}
				public String toString(){
					lineNumberRanges=myLineNumberRanges;
					return "End of brace not reached:"+getLineNumber();
				}
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
				for(Parser.Result result:results){
					if(result!=null){
						String resultString = result.toString();
						if(resultString!=null){
							builder.append("\n");
							builder.append(resultString);
						}
					}
				}
				return super.toString()+builder.toString();
			}
		}
	}
}