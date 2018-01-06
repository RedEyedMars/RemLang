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
import java.io.BufferedReader;
import java.io.FileReader;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

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
		if(_pass==FIRST_PASS){
			Parser.Context context = new Parser.Context(class_names_root,class_variable_names_root,variable_names_root);
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
			class_names=class_names_root;
			class_variable_names=class_variable_names_root;
			variable_names=variable_names_root;
		}
		public Context() {
			class_names=class_names_root;
			class_variable_names=class_variable_names_root;
			variable_names=variable_names_root;
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
			_parse_root();
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
			int _position_array_parameters = -1;
			Token.Parsed _token_array_parameters = null;
			int _length_array_parameters_brace = _inputLength;
			if(brace_5.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_5.get(_position);
				int _position_array_parameters_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_array_parameters=_position;
				_token_array_parameters=_token;
				_token=new Tokens.Rule.ArrayParametersToken();
				int _state_16 = _state;
				parse__anonymous_3();
				if(_state_16==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(array_parameters)");
						_furthestPosition=_position;
					}
					_position=_position_array_parameters;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_array_parameters.add(_position_array_parameters,_token);
				}
				_token=_token_array_parameters;
				if(_state==SUCCESS&&brace_5.get(_position_array_parameters_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_5.get(_position_array_parameters_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_array_parameters_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
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
			while(_position<_inputLength){
				if(_inputArray[_position]!='.'){
					++_position;
					++_multiple_index_19;
				}
				else{
					break;
				}
			}
			if(_multiple_index_19==0 ){
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_token.setValue(_input.substring(_position_regex_6,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[.]+");
					_furthestPosition=_position;
				}
				_position=_position_regex_6;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_file_name(class_file_name)");
					_furthestPosition=_position;
				}
				_position=_position_class_file_name;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_class_file_name.add(_position_class_file_name,_token);
			}
			_token=_token_class_file_name;
		}
		public void parse__anonymous_88(){
			int _position__anonymous_88 = -1;
			Token.Parsed _token__anonymous_88 = null;
			_position__anonymous_88=_position;
			_token__anonymous_88=_token;
			_token=new Tokens.Name.ExactToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_88)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_88;
			}
			else{
				int _state_102 = _state;
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='^'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
						_furthestPosition=_position;
					}
				}
				if(_state_102==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_88)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_88;
				}
				else{
					parse__anonymous_89();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_88)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_88;
					}
					else{
						int _state_103 = _state;
						parse_template_parameters();
						if(_state_103==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_88)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_88;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_88.add(_position__anonymous_88,_token);
			}
			_token=_token__anonymous_88;
		}
		public void parse__anonymous_87(){
			int _position__anonymous_87 = -1;
			Token.Parsed _token__anonymous_87 = null;
			_position__anonymous_87=_position;
			_token__anonymous_87=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='+'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_56.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_87)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_87;
			}
			else{
				Token.Parsed _tokentype_var_element = _token;
				_token=new Tokens.Name.TypeVarToken();
				int _position_type_var_element = _position;
				parse_type_var_element();
				if(_state==SUCCESS){
					_tokentype_var_element.add(_position_type_var_element,_token);
				}
				_token=_tokentype_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_87)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_87;
				}
				else{
				}
			}
			if(_state==SUCCESS){
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
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_89)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_89;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_89.addAll(_token);
			}
			_token=_token__anonymous_89;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_89=_position;
				_token__anonymous_89=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_89)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_89;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_89.addAll(_token);
				}
				_token=_token__anonymous_89;
			}
		}
		public void parse_body_conditional(){
			int _position_body_conditional = -1;
			Token.Parsed _token_body_conditional = null;
			_position_body_conditional=_position;
			_token_body_conditional=_token;
			_token=new Tokens.Rule.BodyConditionalToken();
			int _state_37 = _state;
			int _position_inner = _position;
			if(_state==SUCCESS&&!_recursion_protection_inner_28.contains(_position)){
				_recursion_protection_inner_28.add(_position);
				parse_inner();
				_recursion_protection_inner_28.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_37==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
					_furthestPosition=_position;
				}
				_position=_position_body_conditional;
			}
			else{
				parse__anonymous_29();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else{
					parse_body_statement();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else{
						parse__anonymous_30();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_body_conditional.add(_position_body_conditional,_token);
			}
			_token=_token_body_conditional;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_body_conditional=_position;
				_token_body_conditional=_token;
				_token=new Tokens.Rule.BodyConditionalToken();
				int _state_38 = _state;
				_position_inner=_position;
				if(_state==SUCCESS&&!_recursion_protection_inner_29.contains(_position)){
					_recursion_protection_inner_29.add(_position);
					parse_inner();
					_recursion_protection_inner_29.remove(_position_inner);
				}
				else{
					_state=FAILED;
				}
				if(_state_38==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else{
					if(_position+4-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='e'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='l'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='s'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='e'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_22.conditional);
						_position=_position+4;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain else");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else{
						parse__anonymous_31();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else{
						}
					}
				}
				if(_state==SUCCESS){
					_token_body_conditional.add(_position_body_conditional,_token);
				}
				_token=_token_body_conditional;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_body_conditional=_position;
					_token_body_conditional=_token;
					_token=new Tokens.Rule.BodyConditionalToken();
					int _state_39 = _state;
					_position_inner=_position;
					if(_state==SUCCESS&&!_recursion_protection_inner_30.contains(_position)){
						_recursion_protection_inner_30.add(_position);
						parse_inner();
						_recursion_protection_inner_30.remove(_position_inner);
					}
					else{
						_state=FAILED;
					}
					if(_state_39==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else{
						if(_position+3-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='f'){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='o'){
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='r'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_27.conditional);
							_position=_position+3;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain for");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else{
							parse_variable_declaration();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else{
								parse_OPERATOR();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else{
									parse_body_statement();
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else{
										parse__anonymous_32();
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
												_furthestPosition=_position;
											}
											_position=_position_body_conditional;
										}
										else{
										}
									}
								}
							}
						}
					}
					if(_state==SUCCESS){
						_token_body_conditional.add(_position_body_conditional,_token);
					}
					_token=_token_body_conditional;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_body_conditional=_position;
						_token_body_conditional=_token;
						_token=new Tokens.Rule.BodyConditionalToken();
						int _state_40 = _state;
						_position_inner=_position;
						if(_state==SUCCESS&&!_recursion_protection_inner_31.contains(_position)){
							_recursion_protection_inner_31.add(_position);
							parse_inner();
							_recursion_protection_inner_31.remove(_position_inner);
						}
						else{
							_state=FAILED;
						}
						if(_state_40==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else{
							if(_position+3-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='t'){
									_state=FAILED;
								}
								if(_inputArray[_position+1]!='r'){
									_state=FAILED;
								}
								if(_inputArray[_position+2]!='y'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_28.conditional);
								_position=_position+3;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain try");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else{
								parse__anonymous_33();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else{
								}
							}
						}
						if(_state==SUCCESS){
							_token_body_conditional.add(_position_body_conditional,_token);
						}
						_token=_token_body_conditional;
						if(_state==FAILED){
							_state=SUCCESS;
							_position_body_conditional=_position;
							_token_body_conditional=_token;
							_token=new Tokens.Rule.BodyConditionalToken();
							int _state_41 = _state;
							_position_inner=_position;
							if(_state==SUCCESS&&!_recursion_protection_inner_32.contains(_position)){
								_recursion_protection_inner_32.add(_position);
								parse_inner();
								_recursion_protection_inner_32.remove(_position_inner);
							}
							else{
								_state=FAILED;
							}
							if(_state_41==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else{
								int _state_42 = _state;
								if(_position+5-1 >=_inputLength){
									_state=FAILED;
								}
								else{
									if(_inputArray[_position+0]!='p'){
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='r'){
										_state=FAILED;
									}
									if(_inputArray[_position+2]!='i'){
										_state=FAILED;
									}
									if(_inputArray[_position+3]!='n'){
										_state=FAILED;
									}
									if(_inputArray[_position+4]!='t'){
										_state=FAILED;
									}
								}
								if(_state==SUCCESS){
									_token.add(_position,Tokens.Syntax.syntax_29.__SYNTAX__);
									_position=_position+5;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
										++_position;
									}
								}
								else if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain print");
										_furthestPosition=_position;
									}
								}
								if(_state_42==SUCCESS&&_state==FAILED){
									_state=SUCCESS;
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else{
									if(_position+5-1 >=_inputLength){
										_state=FAILED;
									}
									else{
										if(_inputArray[_position+0]!='c'){
											_state=FAILED;
										}
										if(_inputArray[_position+1]!='a'){
											_state=FAILED;
										}
										if(_inputArray[_position+2]!='t'){
											_state=FAILED;
										}
										if(_inputArray[_position+3]!='c'){
											_state=FAILED;
										}
										if(_inputArray[_position+4]!='h'){
											_state=FAILED;
										}
									}
									if(_state==SUCCESS){
										_token.add(_position,Tokens.Syntax.syntax_30.conditional);
										_position=_position+5;
										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
											++_position;
										}
									}
									else if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain catch");
											_furthestPosition=_position;
										}
									}
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else{
										parse__anonymous_34();
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
												_furthestPosition=_position;
											}
											_position=_position_body_conditional;
										}
										else{
											int _state_43 = _state;
											while(_position<_inputLength){
												parse__anonymous_35();
												if(_state==FAILED){
													break;
												}
											}
											if(_state_43==SUCCESS&&_state==FAILED){
												_state=SUCCESS;
											}
											if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
													_furthestPosition=_position;
												}
												_position=_position_body_conditional;
											}
											else{
												parse__anonymous_37();
												if(_state==FAILED){
													if(_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
														_furthestPosition=_position;
													}
													_position=_position_body_conditional;
												}
												else{
												}
											}
										}
									}
								}
							}
							if(_state==SUCCESS){
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
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='-'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ->");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_80)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_80;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_80.addAll(_token);
			}
			_token=_token__anonymous_80;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_80=_position;
				_token__anonymous_80=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+2-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='\\'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='>'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_63.__SYNTAX__);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\>");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_80)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_80;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_80.addAll(_token);
				}
				_token=_token__anonymous_80;
			}
		}
		public void parse__anonymous_82(){
			int _position__anonymous_82 = -1;
			Token.Parsed _token__anonymous_82 = null;
			_position__anonymous_82=_position;
			_token__anonymous_82=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='-'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ->");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_82)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_82;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_82.addAll(_token);
			}
			_token=_token__anonymous_82;
		}
		public void parse_quote(){
			int _position_quote = -1;
			Token.Parsed _token_quote = null;
			int _length_quote_brace = _inputLength;
			if(brace_0.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_0.get(_position);
				int _position_quote_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false)){
					++_position;
				}
				_position_quote=_position;
				_token_quote=_token;
				_token=new Tokens.Rule.QuoteToken();
				int _position_regex_4 = _position;
				int _multiple_index_10 = 0;
				int _state_10 = _state;
				while(_position<_inputLength){
					if(_position<_inputLength){
						++_position;
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						break;
					}
					else{
						++_multiple_index_10;
					}
				}
				if(_state_10==SUCCESS){
					_state=SUCCESS;
				}
				if(_state==SUCCESS){
					_token.setValue(_input.substring(_position_regex_4,_position));
					while(_position<_inputLength&&(false)){
						++_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".*");
						_furthestPosition=_position;
					}
					_position=_position_regex_4;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"quote(quote)");
						_furthestPosition=_position;
					}
					_position=_position_quote;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_quote.add(_position_quote,_token);
				}
				_token=_token_quote;
				if(_state==SUCCESS&&brace_0.get(_position_quote_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_0.get(_position_quote_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_quote_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
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
			_token=new Tokens.Name.AsMethodToken();
			parse__anonymous_82();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_81)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_81;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_81)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_81;
				}
				else{
					int _state_94 = _state;
					parse_name_var();
					if(_state_94==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_81)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_81;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_81.add(_position__anonymous_81,_token);
			}
			_token=_token__anonymous_81;
		}
		public void parse__anonymous_84(){
			int _position__anonymous_84 = -1;
			Token.Parsed _token__anonymous_84 = null;
			_position__anonymous_84=_position;
			_token__anonymous_84=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_85();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_84)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_84;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_84)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_84;
				}
				else{
					int _state_97 = _state;
					parse_name_var();
					if(_state_97==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_84)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_84;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_84.addAll(_token);
			}
			_token=_token__anonymous_84;
		}
		public void parse__anonymous_83(){
			int _position__anonymous_83 = -1;
			Token.Parsed _token__anonymous_83 = null;
			_position__anonymous_83=_position;
			_token__anonymous_83=_token;
			_token=new Tokens.Name.AccessMethodToken();
			int _state_95 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_62.ISTYPENAME);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain $");
					_furthestPosition=_position;
				}
			}
			if(_state_95==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_83)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_83;
			}
			else{
				int _state_96 = _state;
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='^'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
						_furthestPosition=_position;
					}
				}
				if(_state_96==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_83)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_83;
				}
				else{
					Token.Parsed _tokentype_var_element = _token;
					_token=new Tokens.Name.TypeVarToken();
					int _position_type_var_element = _position;
					parse_type_var_element();
					if(_state==SUCCESS){
						_tokentype_var_element.add(_position_type_var_element,_token);
					}
					_token=_tokentype_var_element;
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_83)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_83;
					}
					else{
						parse__anonymous_84();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_83)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_83;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_83.add(_position__anonymous_83,_token);
			}
			_token=_token__anonymous_83;
		}
		public void parse__anonymous_86(){
			int _position__anonymous_86 = -1;
			Token.Parsed _token__anonymous_86 = null;
			_position__anonymous_86=_position;
			_token__anonymous_86=_token;
			_token=new Tokens.Name.ConcatToken();
			int _state_98 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_62.ISTYPENAME);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain $");
					_furthestPosition=_position;
				}
			}
			if(_state_98==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_86)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_86;
			}
			else{
				int _state_99 = _state;
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='^'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
						_furthestPosition=_position;
					}
				}
				if(_state_99==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_86)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_86;
				}
				else{
					Token.Parsed _tokentype_var_element = _token;
					_token=new Tokens.Name.TypeVarToken();
					int _position_type_var_element = _position;
					parse_type_var_element();
					if(_state==SUCCESS){
						_tokentype_var_element.add(_position_type_var_element,_token);
					}
					_token=_tokentype_var_element;
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_86)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_86;
					}
					else{
						int _state_100 = _state;
						boolean _iteration_achieved_100 = false;
						while(_position<_inputLength){
							parse__anonymous_87();
							if(_state==FAILED){
								break;
							}
							else{
								_iteration_achieved_100=true;
							}
						}
						if(_iteration_achieved_100==false){
							_state=FAILED;
						}
						else if(_state_100==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_86)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_86;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_86.add(_position__anonymous_86,_token);
			}
			_token=_token__anonymous_86;
		}
		public void parse_all_type_name(){
			int _position_all_type_name = -1;
			Token.Parsed _token_all_type_name = null;
			_position_all_type_name=_position;
			_token_all_type_name=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+5-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='C'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='l'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='s'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_42.__SYNTAX__);
				_position=_position+5;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Class");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
					_furthestPosition=_position;
				}
				_position=_position_all_type_name;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_all_type_name.addAll(_token);
			}
			_token=_token_all_type_name;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_all_type_name=_position;
				_token_all_type_name=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='M'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='t'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='h'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='o'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='d'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_43.__SYNTAX__);
					_position=_position+6;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Method");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
						_furthestPosition=_position;
					}
					_position=_position_all_type_name;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_all_type_name.addAll(_token);
				}
				_token=_token_all_type_name;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_all_type_name=_position;
					_token_all_type_name=_token;
					_token=new Token.Parsed("$ANON");
					if(_position+8-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='V'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='a'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='r'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='i'){
							_state=FAILED;
						}
						if(_inputArray[_position+4]!='a'){
							_state=FAILED;
						}
						if(_inputArray[_position+5]!='b'){
							_state=FAILED;
						}
						if(_inputArray[_position+6]!='l'){
							_state=FAILED;
						}
						if(_inputArray[_position+7]!='e'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_44.__SYNTAX__);
						_position=_position+8;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Variable");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
							_furthestPosition=_position;
						}
						_position=_position_all_type_name;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_all_type_name.addAll(_token);
					}
					_token=_token_all_type_name;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_all_type_name=_position;
						_token_all_type_name=_token;
						_token=new Token.Parsed("$ANON");
						if(_position+4-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='B'){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='o'){
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='d'){
								_state=FAILED;
							}
							if(_inputArray[_position+3]!='y'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_45.__SYNTAX__);
							_position=_position+4;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Body");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
								_furthestPosition=_position;
							}
							_position=_position_all_type_name;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_all_type_name.addAll(_token);
						}
						_token=_token_all_type_name;
						if(_state==FAILED){
							_state=SUCCESS;
							_position_all_type_name=_position;
							_token_all_type_name=_token;
							_token=new Token.Parsed("$ANON");
							if(_position+9-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='S'){
									_state=FAILED;
								}
								if(_inputArray[_position+1]!='t'){
									_state=FAILED;
								}
								if(_inputArray[_position+2]!='a'){
									_state=FAILED;
								}
								if(_inputArray[_position+3]!='t'){
									_state=FAILED;
								}
								if(_inputArray[_position+4]!='e'){
									_state=FAILED;
								}
								if(_inputArray[_position+5]!='m'){
									_state=FAILED;
								}
								if(_inputArray[_position+6]!='e'){
									_state=FAILED;
								}
								if(_inputArray[_position+7]!='n'){
									_state=FAILED;
								}
								if(_inputArray[_position+8]!='t'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_46.__SYNTAX__);
								_position=_position+9;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Statement");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
									_furthestPosition=_position;
								}
								_position=_position_all_type_name;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_all_type_name.addAll(_token);
							}
							_token=_token_all_type_name;
							if(_state==FAILED){
								_state=SUCCESS;
								_position_all_type_name=_position;
								_token_all_type_name=_token;
								_token=new Token.Parsed("$ANON");
								if(_position+10-1 >=_inputLength){
									_state=FAILED;
								}
								else{
									if(_inputArray[_position+0]!='P'){
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='a'){
										_state=FAILED;
									}
									if(_inputArray[_position+2]!='r'){
										_state=FAILED;
									}
									if(_inputArray[_position+3]!='a'){
										_state=FAILED;
									}
									if(_inputArray[_position+4]!='m'){
										_state=FAILED;
									}
									if(_inputArray[_position+5]!='e'){
										_state=FAILED;
									}
									if(_inputArray[_position+6]!='t'){
										_state=FAILED;
									}
									if(_inputArray[_position+7]!='e'){
										_state=FAILED;
									}
									if(_inputArray[_position+8]!='r'){
										_state=FAILED;
									}
									if(_inputArray[_position+9]!='s'){
										_state=FAILED;
									}
								}
								if(_state==SUCCESS){
									_token.add(_position,Tokens.Syntax.syntax_47.__SYNTAX__);
									_position=_position+10;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
										++_position;
									}
								}
								else if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Parameters");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
										_furthestPosition=_position;
									}
									_position=_position_all_type_name;
								}
								else{
								}
								if(_state==SUCCESS){
									_token_all_type_name.addAll(_token);
								}
								_token=_token_all_type_name;
								if(_state==FAILED){
									_state=SUCCESS;
									_position_all_type_name=_position;
									_token_all_type_name=_token;
									_token=new Token.Parsed("$ANON");
									if(_position+7-1 >=_inputLength){
										_state=FAILED;
									}
									else{
										if(_inputArray[_position+0]!='C'){
											_state=FAILED;
										}
										if(_inputArray[_position+1]!='o'){
											_state=FAILED;
										}
										if(_inputArray[_position+2]!='n'){
											_state=FAILED;
										}
										if(_inputArray[_position+3]!='t'){
											_state=FAILED;
										}
										if(_inputArray[_position+4]!='e'){
											_state=FAILED;
										}
										if(_inputArray[_position+5]!='x'){
											_state=FAILED;
										}
										if(_inputArray[_position+6]!='t'){
											_state=FAILED;
										}
									}
									if(_state==SUCCESS){
										_token.add(_position,Tokens.Syntax.syntax_48.__SYNTAX__);
										_position=_position+7;
										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
											++_position;
										}
									}
									else if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Context");
											_furthestPosition=_position;
										}
									}
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
											_furthestPosition=_position;
										}
										_position=_position_all_type_name;
									}
									else{
									}
									if(_state==SUCCESS){
										_token_all_type_name.addAll(_token);
									}
									_token=_token_all_type_name;
									if(_state==FAILED){
										_state=SUCCESS;
										_position_all_type_name=_position;
										_token_all_type_name=_token;
										_token=new Token.Parsed("$ANON");
										if(_position+2-1 >=_inputLength){
											_state=FAILED;
										}
										else{
											if(_inputArray[_position+0]!='%'){
												_state=FAILED;
											}
											if(_inputArray[_position+1]!='T'){
												_state=FAILED;
											}
										}
										if(_state==SUCCESS){
											_token.add(_position,Tokens.Syntax.syntax_49.__SYNTAX__);
											_position=_position+2;
											while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
												++_position;
											}
										}
										else if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %T");
												_furthestPosition=_position;
											}
										}
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
												_furthestPosition=_position;
											}
											_position=_position_all_type_name;
										}
										else{
										}
										if(_state==SUCCESS){
											_token_all_type_name.addAll(_token);
										}
										_token=_token_all_type_name;
										if(_state==FAILED){
											_state=SUCCESS;
											_position_all_type_name=_position;
											_token_all_type_name=_token;
											_token=new Token.Parsed("$ANON");
											if(_position+7-1 >=_inputLength){
												_state=FAILED;
											}
											else{
												if(_inputArray[_position+0]!='%'){
													_state=FAILED;
												}
												if(_inputArray[_position+1]!='P'){
													_state=FAILED;
												}
												if(_inputArray[_position+2]!='a'){
													_state=FAILED;
												}
												if(_inputArray[_position+3]!='r'){
													_state=FAILED;
												}
												if(_inputArray[_position+4]!='s'){
													_state=FAILED;
												}
												if(_inputArray[_position+5]!='e'){
													_state=FAILED;
												}
												if(_inputArray[_position+6]!='r'){
													_state=FAILED;
												}
											}
											if(_state==SUCCESS){
												_token.add(_position,Tokens.Syntax.syntax_50.__SYNTAX__);
												_position=_position+7;
												while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
													++_position;
												}
											}
											else if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %Parser");
													_furthestPosition=_position;
												}
											}
											if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
													_furthestPosition=_position;
												}
												_position=_position_all_type_name;
											}
											else{
											}
											if(_state==SUCCESS){
												_token_all_type_name.addAll(_token);
											}
											_token=_token_all_type_name;
											if(_state==FAILED){
												_state=SUCCESS;
												_position_all_type_name=_position;
												_token_all_type_name=_token;
												_token=new Token.Parsed("$ANON");
												if(_position+7-1 >=_inputLength){
													_state=FAILED;
												}
												else{
													if(_inputArray[_position+0]!='%'){
														_state=FAILED;
													}
													if(_inputArray[_position+1]!='R'){
														_state=FAILED;
													}
													if(_inputArray[_position+2]!='e'){
														_state=FAILED;
													}
													if(_inputArray[_position+3]!='s'){
														_state=FAILED;
													}
													if(_inputArray[_position+4]!='u'){
														_state=FAILED;
													}
													if(_inputArray[_position+5]!='l'){
														_state=FAILED;
													}
													if(_inputArray[_position+6]!='t'){
														_state=FAILED;
													}
												}
												if(_state==SUCCESS){
													_token.add(_position,Tokens.Syntax.syntax_51.__SYNTAX__);
													_position=_position+7;
													while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
														++_position;
													}
												}
												else if(_state==FAILED){
													if(_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %Result");
														_furthestPosition=_position;
													}
												}
												if(_state==FAILED){
													if(_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
														_furthestPosition=_position;
													}
													_position=_position_all_type_name;
												}
												else{
												}
												if(_state==SUCCESS){
													_token_all_type_name.addAll(_token);
												}
												_token=_token_all_type_name;
												if(_state==FAILED){
													_state=SUCCESS;
													_position_all_type_name=_position;
													_token_all_type_name=_token;
													_token=new Token.Parsed("$ANON");
													if(_position+5-1 >=_inputLength){
														_state=FAILED;
													}
													else{
														if(_inputArray[_position+0]!='%'){
															_state=FAILED;
														}
														if(_inputArray[_position+1]!='P'){
															_state=FAILED;
														}
														if(_inputArray[_position+2]!='a'){
															_state=FAILED;
														}
														if(_inputArray[_position+3]!='s'){
															_state=FAILED;
														}
														if(_inputArray[_position+4]!='s'){
															_state=FAILED;
														}
													}
													if(_state==SUCCESS){
														_token.add(_position,Tokens.Syntax.syntax_52.__SYNTAX__);
														_position=_position+5;
														while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
															++_position;
														}
													}
													else if(_state==FAILED){
														if(_position>=_furthestPosition){
															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %Pass");
															_furthestPosition=_position;
														}
													}
													if(_state==FAILED){
														if(_position>=_furthestPosition){
															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
															_furthestPosition=_position;
														}
														_position=_position_all_type_name;
													}
													else{
													}
													if(_state==SUCCESS){
														_token_all_type_name.addAll(_token);
													}
													_token=_token_all_type_name;
													if(_state==FAILED){
														_state=SUCCESS;
														_position_all_type_name=_position;
														_token_all_type_name=_token;
														_token=new Token.Parsed("$ANON");
														if(_position+5-1 >=_inputLength){
															_state=FAILED;
														}
														else{
															if(_inputArray[_position+0]!='%'){
																_state=FAILED;
															}
															if(_inputArray[_position+1]!='F'){
																_state=FAILED;
															}
															if(_inputArray[_position+2]!='a'){
																_state=FAILED;
															}
															if(_inputArray[_position+3]!='i'){
																_state=FAILED;
															}
															if(_inputArray[_position+4]!='l'){
																_state=FAILED;
															}
														}
														if(_state==SUCCESS){
															_token.add(_position,Tokens.Syntax.syntax_53.__SYNTAX__);
															_position=_position+5;
															while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
																++_position;
															}
														}
														else if(_state==FAILED){
															if(_position>=_furthestPosition){
																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %Fail");
																_furthestPosition=_position;
															}
														}
														if(_state==FAILED){
															if(_position>=_furthestPosition){
																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
																_furthestPosition=_position;
															}
															_position=_position_all_type_name;
														}
														else{
														}
														if(_state==SUCCESS){
															_token_all_type_name.addAll(_token);
														}
														_token=_token_all_type_name;
														if(_state==FAILED){
															_state=SUCCESS;
															_position_all_type_name=_position;
															_token_all_type_name=_token;
															_token=new Token.Parsed("$ANON");
															parse_type_var();
															if(_state==FAILED){
																if(_position>=_furthestPosition){
																	_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
																	_furthestPosition=_position;
																}
																_position=_position_all_type_name;
															}
															else{
																int _state_82 = _state;
																while(_position<_inputLength){
																	parse__anonymous_64();
																	if(_state==FAILED){
																		break;
																	}
																}
																if(_state_82==SUCCESS&&_state==FAILED){
																	_state=SUCCESS;
																}
																if(_state==FAILED){
																	if(_position>=_furthestPosition){
																		_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
																		_furthestPosition=_position;
																	}
																	_position=_position_all_type_name;
																}
																else{
																}
															}
															if(_state==SUCCESS){
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
						}
					}
				}
			}
		}
		public void parse_type_var(){
			int _position_type_var = -1;
			Token.Parsed _token_type_var = null;
			_position_type_var=_position;
			_token_type_var=_token;
			_token=new Tokens.Rule.TypeVarToken();
			parse__anonymous_78();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
					_furthestPosition=_position;
				}
				_position=_position_type_var;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_type_var.add(_position_type_var,_token);
			}
			_token=_token_type_var;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_type_var=_position;
				_token_type_var=_token;
				_token=new Tokens.Rule.TypeVarToken();
				parse__anonymous_83();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
						_furthestPosition=_position;
					}
					_position=_position_type_var;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_type_var.add(_position_type_var,_token);
				}
				_token=_token_type_var;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_type_var=_position;
					_token_type_var=_token;
					_token=new Tokens.Rule.TypeVarToken();
					parse__anonymous_86();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
							_furthestPosition=_position;
						}
						_position=_position_type_var;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_type_var.add(_position_type_var,_token);
					}
					_token=_token_type_var;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_type_var=_position;
						_token_type_var=_token;
						_token=new Tokens.Rule.TypeVarToken();
						int _state_101 = _state;
						if(_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='$'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_62.ISTYPENAME);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain $");
								_furthestPosition=_position;
							}
						}
						if(_state_101==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
								_furthestPosition=_position;
							}
							_position=_position_type_var;
						}
						else{
							parse_type_var_element();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
									_furthestPosition=_position;
								}
								_position=_position_type_var;
							}
							else{
							}
						}
						if(_state==SUCCESS){
							_token_type_var.add(_position_type_var,_token);
						}
						_token=_token_type_var;
					}
				}
			}
		}
		public void parse__anonymous_85(){
			int _position__anonymous_85 = -1;
			Token.Parsed _token__anonymous_85 = null;
			_position__anonymous_85=_position;
			_token__anonymous_85=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='-'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ->");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_85)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_85;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_85.addAll(_token);
			}
			_token=_token__anonymous_85;
		}
		public void parse__anonymous_77(){
			int _position__anonymous_77 = -1;
			Token.Parsed _token__anonymous_77 = null;
			_position__anonymous_77=_position;
			_token__anonymous_77=_token;
			_token=new Tokens.Name.NAMEToken();
			parse_NUMBER();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_77)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_77;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_77.add(_position__anonymous_77,_token);
			}
			_token=_token__anonymous_77;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_77=_position;
				_token__anonymous_77=_token;
				_token=new Tokens.Name.NAMEToken();
				if(_position+5-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='s'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='u'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='p'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='r'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_57.__SYNTAX__);
					_position=_position+5;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain super");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_77)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_77;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_77.add(_position__anonymous_77,_token);
				}
				_token=_token__anonymous_77;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_77=_position;
					_token__anonymous_77=_token;
					_token=new Tokens.Name.NAMEToken();
					if(_position+4-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='t'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='h'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='i'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='s'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_58.__SYNTAX__);
						_position=_position+4;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain this");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_77)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_77;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_77.add(_position__anonymous_77,_token);
					}
					_token=_token__anonymous_77;
					if(_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_77=_position;
						_token__anonymous_77=_token;
						_token=new Tokens.Name.NAMEToken();
						if(_position+4-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='n'){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='u'){
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='l'){
								_state=FAILED;
							}
							if(_inputArray[_position+3]!='l'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_59.__SYNTAX__);
							_position=_position+4;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain null");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_77)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_77;
						}
						else{
						}
						if(_state==SUCCESS){
							_token__anonymous_77.add(_position__anonymous_77,_token);
						}
						_token=_token__anonymous_77;
						if(_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_77=_position;
							_token__anonymous_77=_token;
							_token=new Tokens.Name.NAMEToken();
							if(_position+4-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='t'){
									_state=FAILED;
								}
								if(_inputArray[_position+1]!='r'){
									_state=FAILED;
								}
								if(_inputArray[_position+2]!='u'){
									_state=FAILED;
								}
								if(_inputArray[_position+3]!='e'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_60.__SYNTAX__);
								_position=_position+4;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain true");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_77)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_77;
							}
							else{
							}
							if(_state==SUCCESS){
								_token__anonymous_77.add(_position__anonymous_77,_token);
							}
							_token=_token__anonymous_77;
							if(_state==FAILED){
								_state=SUCCESS;
								_position__anonymous_77=_position;
								_token__anonymous_77=_token;
								_token=new Tokens.Name.NAMEToken();
								if(_position+5-1 >=_inputLength){
									_state=FAILED;
								}
								else{
									if(_inputArray[_position+0]!='f'){
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='a'){
										_state=FAILED;
									}
									if(_inputArray[_position+2]!='l'){
										_state=FAILED;
									}
									if(_inputArray[_position+3]!='s'){
										_state=FAILED;
									}
									if(_inputArray[_position+4]!='e'){
										_state=FAILED;
									}
								}
								if(_state==SUCCESS){
									_token.add(_position,Tokens.Syntax.syntax_61.__SYNTAX__);
									_position=_position+5;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
										++_position;
									}
								}
								else if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain false");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_77)");
										_furthestPosition=_position;
									}
									_position=_position__anonymous_77;
								}
								else{
								}
								if(_state==SUCCESS){
									_token__anonymous_77.add(_position__anonymous_77,_token);
								}
								_token=_token__anonymous_77;
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_76(){
			int _position__anonymous_76 = -1;
			Token.Parsed _token__anonymous_76 = null;
			_position__anonymous_76=_position;
			_token__anonymous_76=_token;
			_token=new Token.Parsed("$ANON");
			parse_quote();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_76)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_76;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_76.addAll(_token);
			}
			_token=_token__anonymous_76;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_76=_position;
				_token__anonymous_76=_token;
				_token=new Token.Parsed("$ANON");
				parse__anonymous_77();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_76)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_76;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_76.addAll(_token);
				}
				_token=_token__anonymous_76;
			}
		}
		public void parse__anonymous_79(){
			int _position__anonymous_79 = -1;
			Token.Parsed _token__anonymous_79 = null;
			_position__anonymous_79=_position;
			_token__anonymous_79=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_80();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_79)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_79;
			}
			else{
				Token.Parsed _tokentype_var_element = _token;
				_token=new Tokens.Name.TypeVarToken();
				int _position_type_var_element = _position;
				parse_type_var_element();
				if(_state==SUCCESS){
					_tokentype_var_element.add(_position_type_var_element,_token);
				}
				_token=_tokentype_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_79)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_79;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_79.addAll(_token);
			}
			_token=_token__anonymous_79;
		}
		public void parse__anonymous_78(){
			int _position__anonymous_78 = -1;
			Token.Parsed _token__anonymous_78 = null;
			_position__anonymous_78=_position;
			_token__anonymous_78=_token;
			_token=new Tokens.Name.AccessMultiToken();
			int _state_90 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='$'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_62.ISTYPENAME);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain $");
					_furthestPosition=_position;
				}
			}
			if(_state_90==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_78;
			}
			else{
				int _state_91 = _state;
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='^'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
						_furthestPosition=_position;
					}
				}
				if(_state_91==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_78;
				}
				else{
					Token.Parsed _tokentype_var_element = _token;
					_token=new Tokens.Name.TypeVarToken();
					int _position_type_var_element = _position;
					parse_type_var_element();
					if(_state==SUCCESS){
						_tokentype_var_element.add(_position_type_var_element,_token);
					}
					_token=_tokentype_var_element;
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_78;
					}
					else{
						int _state_92 = _state;
						boolean _iteration_achieved_92 = false;
						while(_position<_inputLength){
							parse__anonymous_79();
							if(_state==FAILED){
								break;
							}
							else{
								_iteration_achieved_92=true;
							}
						}
						if(_iteration_achieved_92==false){
							_state=FAILED;
						}
						else if(_state_92==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_78;
						}
						else{
							int _state_93 = _state;
							parse__anonymous_81();
							if(_state_93==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_78;
							}
							else{
							}
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_78.add(_position__anonymous_78,_token);
			}
			_token=_token__anonymous_78;
		}
		public void parse_statement_as_quote(){
			int _position_statement_as_quote = -1;
			Token.Parsed _token_statement_as_quote = null;
			int _length_statement_as_quote_brace = _inputLength;
			if(brace_6.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_6.get(_position);
				int _position_statement_as_quote_brace = _position;
				_position+=2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_statement_as_quote=_position;
				_token_statement_as_quote=_token;
				_token=new Tokens.Rule.StatementAsQuoteToken();
				int _position_body_statement = _position;
				if(_state==SUCCESS&&!_recursion_protection_body_statement_2.contains(_position)){
					_recursion_protection_body_statement_2.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_2.remove(_position_body_statement);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_quote(statement_as_quote)");
						_furthestPosition=_position;
					}
					_position=_position_statement_as_quote;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_statement_as_quote.add(_position_statement_as_quote,_token);
				}
				_token=_token_statement_as_quote;
				if(_state==SUCCESS&&brace_6.get(_position_statement_as_quote_brace)==_position){
					_position+=2;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_6.get(_position_statement_as_quote_brace)+2;
					_succeedOnEnd=false;
				}
				_inputLength=_length_statement_as_quote_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
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
			_token=new Tokens.Name.ExactToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_71)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_71;
			}
			else{
				int _state_87 = _state;
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='^'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
						_furthestPosition=_position;
					}
				}
				if(_state_87==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_71)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_71;
				}
				else{
					parse__anonymous_72();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_71)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_71;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_71.add(_position__anonymous_71,_token);
			}
			_token=_token__anonymous_71;
		}
		public void parse__anonymous_70(){
			int _position__anonymous_70 = -1;
			Token.Parsed _token__anonymous_70 = null;
			_position__anonymous_70=_position;
			_token__anonymous_70=_token;
			_token=new Tokens.Name.ExactToken();
			Token.Parsed _tokenNUMBER = _token;
			_token=new Tokens.Name.NAMEToken();
			int _position_NUMBER = _position;
			parse_NUMBER();
			if(_state==SUCCESS){
				_tokenNUMBER.add(_position_NUMBER,_token);
			}
			_token=_tokenNUMBER;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_70)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_70;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_70.add(_position__anonymous_70,_token);
			}
			_token=_token__anonymous_70;
		}
		public void parse__anonymous_73(){
			int _position__anonymous_73 = -1;
			Token.Parsed _token__anonymous_73 = null;
			_position__anonymous_73=_position;
			_token__anonymous_73=_token;
			_token=new Tokens.Name.VariableToken();
			int _state_88 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_88==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_73)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_73;
			}
			else{
				parse__anonymous_74();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_73)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_73;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_73.add(_position__anonymous_73,_token);
			}
			_token=_token__anonymous_73;
		}
		public void parse__anonymous_72(){
			int _position__anonymous_72 = -1;
			Token.Parsed _token__anonymous_72 = null;
			_position__anonymous_72=_position;
			_token__anonymous_72=_token;
			_token=new Token.Parsed("$ANON");
			parse_quote();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_72)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_72;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_72.addAll(_token);
			}
			_token=_token__anonymous_72;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_72=_position;
				_token__anonymous_72=_token;
				_token=new Token.Parsed("$ANON");
				if(_pass==FIRST_PASS){
					String _result = _preparsed_NAME.get(_position);
					if(_result==null){
						_state=FAILED;
					}
					else{
						Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
						_token.add(_position,_first_pass_token);
						_position+=_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else if(_pass==SECOND_PASS){
					_list_name_result=_preparsed_NAME.get(_position);
					if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
						if(_position+_list_name_result.length()<_inputLength){
							int _next_char = _inputArray[_position+_list_name_result.length()];
							if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
							_position+=_list_name_result.length();
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
							_furthestPosition=_position;
						}
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_72)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_72;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_72.addAll(_token);
				}
				_token=_token__anonymous_72;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_72=_position;
					_token__anonymous_72=_token;
					_token=new Token.Parsed("$ANON");
					parse_NAME();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_72)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_72;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_72.addAll(_token);
					}
					_token=_token__anonymous_72;
				}
			}
		}
		public void parse__anonymous_75(){
			int _position__anonymous_75 = -1;
			Token.Parsed _token__anonymous_75 = null;
			_position__anonymous_75=_position;
			_token__anonymous_75=_token;
			_token=new Tokens.Name.ExactToken();
			int _state_89 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_89==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_75)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_75;
			}
			else{
				parse__anonymous_76();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_75)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_75;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_75.add(_position__anonymous_75,_token);
			}
			_token=_token__anonymous_75;
		}
		public void parse_name_var_element(){
			int _position_name_var_element = -1;
			Token.Parsed _token_name_var_element = null;
			_position_name_var_element=_position;
			_token_name_var_element=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_70();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
					_furthestPosition=_position;
				}
				_position=_position_name_var_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_name_var_element.addAll(_token);
			}
			_token=_token_name_var_element;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_name_var_element=_position;
				_token_name_var_element=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_char();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
						_furthestPosition=_position;
					}
					_position=_position_name_var_element;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_name_var_element.addAll(_token);
				}
				_token=_token_name_var_element;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_name_var_element=_position;
					_token_name_var_element=_token;
					_token=new Token.Parsed("$ANON");
					int _position_statement_as_method = _position;
					if(_state==SUCCESS&&!_recursion_protection_statement_as_method_46.contains(_position)){
						_recursion_protection_statement_as_method_46.add(_position);
						parse_statement_as_method();
						_recursion_protection_statement_as_method_46.remove(_position_statement_as_method);
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
							_furthestPosition=_position;
						}
						_position=_position_name_var_element;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_name_var_element.addAll(_token);
					}
					_token=_token_name_var_element;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_name_var_element=_position;
						_token_name_var_element=_token;
						_token=new Token.Parsed("$ANON");
						parse_statement_as_quote();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
								_furthestPosition=_position;
							}
							_position=_position_name_var_element;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_name_var_element.addAll(_token);
						}
						_token=_token_name_var_element;
						if(_state==FAILED){
							_state=SUCCESS;
							_position_name_var_element=_position;
							_token_name_var_element=_token;
							_token=new Token.Parsed("$ANON");
							int _position_statement_as_string = _position;
							if(_state==SUCCESS&&!_recursion_protection_statement_as_string_47.contains(_position)){
								_recursion_protection_statement_as_string_47.add(_position);
								parse_statement_as_string();
								_recursion_protection_statement_as_string_47.remove(_position_statement_as_string);
							}
							else{
								_state=FAILED;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
									_furthestPosition=_position;
								}
								_position=_position_name_var_element;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_name_var_element.addAll(_token);
							}
							_token=_token_name_var_element;
							if(_state==FAILED){
								_state=SUCCESS;
								_position_name_var_element=_position;
								_token_name_var_element=_token;
								_token=new Token.Parsed("$ANON");
								parse__anonymous_71();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
										_furthestPosition=_position;
									}
									_position=_position_name_var_element;
								}
								else{
								}
								if(_state==SUCCESS){
									_token_name_var_element.addAll(_token);
								}
								_token=_token_name_var_element;
								if(_state==FAILED){
									_state=SUCCESS;
									_position_name_var_element=_position;
									_token_name_var_element=_token;
									_token=new Token.Parsed("$ANON");
									parse__anonymous_73();
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
											_furthestPosition=_position;
										}
										_position=_position_name_var_element;
									}
									else{
									}
									if(_state==SUCCESS){
										_token_name_var_element.addAll(_token);
									}
									_token=_token_name_var_element;
									if(_state==FAILED){
										_state=SUCCESS;
										_position_name_var_element=_position;
										_token_name_var_element=_token;
										_token=new Token.Parsed("$ANON");
										parse__anonymous_75();
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
												_furthestPosition=_position;
											}
											_position=_position_name_var_element;
										}
										else{
										}
										if(_state==SUCCESS){
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
		public void parse__anonymous_74(){
			int _position__anonymous_74 = -1;
			Token.Parsed _token__anonymous_74 = null;
			_position__anonymous_74=_position;
			_token__anonymous_74=_token;
			_token=new Token.Parsed("$ANON");
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_74)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_74;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_74.addAll(_token);
			}
			_token=_token__anonymous_74;
		}
		public void parse_body_statement(){
			int _position_body_statement = -1;
			Token.Parsed _token_body_statement = null;
			_position_body_statement=_position;
			_token_body_statement=_token;
			_token=new Tokens.Rule.BodyStatementToken();
			int _state_44 = _state;
			int _position_inner = _position;
			if(_state==SUCCESS&&!_recursion_protection_inner_33.contains(_position)){
				_recursion_protection_inner_33.add(_position);
				parse_inner();
				_recursion_protection_inner_33.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_44==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
					_furthestPosition=_position;
				}
				_position=_position_body_statement;
			}
			else{
				parse_body_call();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
						_furthestPosition=_position;
					}
					_position=_position_body_statement;
				}
				else{
					int _state_45 = _state;
					while(_position<_inputLength){
						parse__anonymous_38();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_45==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
							_furthestPosition=_position;
						}
						_position=_position_body_statement;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token_body_statement.add(_position_body_statement,_token);
			}
			_token=_token_body_statement;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_body_statement=_position;
				_token_body_statement=_token;
				_token=new Tokens.Rule.BodyStatementToken();
				int _position_statement_as_string = _position;
				if(_state==SUCCESS&&!_recursion_protection_statement_as_string_34.contains(_position)){
					_recursion_protection_statement_as_string_34.add(_position);
					parse_statement_as_string();
					_recursion_protection_statement_as_string_34.remove(_position_statement_as_string);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
						_furthestPosition=_position;
					}
					_position=_position_body_statement;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_body_statement.add(_position_body_statement,_token);
				}
				_token=_token_body_statement;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_body_statement=_position;
					_token_body_statement=_token;
					_token=new Tokens.Rule.BodyStatementToken();
					int _position_statement_as_char = _position;
					if(_state==SUCCESS&&!_recursion_protection_statement_as_char_35.contains(_position)){
						_recursion_protection_statement_as_char_35.add(_position);
						parse_statement_as_char();
						_recursion_protection_statement_as_char_35.remove(_position_statement_as_char);
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
							_furthestPosition=_position;
						}
						_position=_position_body_statement;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_body_statement.add(_position_body_statement,_token);
					}
					_token=_token_body_statement;
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
			if(_state==SUCCESS&&!_recursion_protection_comments_20.contains(_position)){
				_recursion_protection_comments_20.add(_position);
				parse_comments();
				_recursion_protection_comments_20.remove(_position_comments);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
					_furthestPosition=_position;
				}
				_position=_position_class_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_class_element.addAll(_token);
			}
			_token=_token_class_element;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_class_element=_position;
				_token_class_element=_token;
				_token=new Token.Parsed("$ANON");
				int _position_class_declaration = _position;
				if(_state==SUCCESS&&!_recursion_protection_class_declaration_21.contains(_position)){
					_recursion_protection_class_declaration_21.add(_position);
					parse_class_declaration();
					_recursion_protection_class_declaration_21.remove(_position_class_declaration);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
						_furthestPosition=_position;
					}
					_position=_position_class_element;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_class_element.addAll(_token);
				}
				_token=_token_class_element;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_class_element=_position;
					_token_class_element=_token;
					_token=new Token.Parsed("$ANON");
					int _position_method_declaration = _position;
					if(_state==SUCCESS&&!_recursion_protection_method_declaration_22.contains(_position)){
						_recursion_protection_method_declaration_22.add(_position);
						parse_method_declaration();
						_recursion_protection_method_declaration_22.remove(_position_method_declaration);
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
							_furthestPosition=_position;
						}
						_position=_position_class_element;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_class_element.addAll(_token);
					}
					_token=_token_class_element;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_class_element=_position;
						_token_class_element=_token;
						_token=new Token.Parsed("$ANON");
						int _position_variable_declaration = _position;
						if(_state==SUCCESS&&!_recursion_protection_variable_declaration_23.contains(_position)){
							_recursion_protection_variable_declaration_23.add(_position);
							parse_variable_declaration();
							_recursion_protection_variable_declaration_23.remove(_position_variable_declaration);
						}
						else{
							_state=FAILED;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
								_furthestPosition=_position;
							}
							_position=_position_class_element;
						}
						else{
							if(_position+1-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!=';'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
								_position=_position+1;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
									_furthestPosition=_position;
								}
								_position=_position_class_element;
							}
							else{
							}
						}
						if(_state==SUCCESS){
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
			_token=new Token.Parsed("$ANON");
			parse__anonymous_67();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_66)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_66;
			}
			else{
				Token.Parsed _tokenname_var_element = _token;
				_token=new Tokens.Name.NameVarToken();
				int _position_name_var_element = _position;
				parse_name_var_element();
				if(_state==SUCCESS){
					_tokenname_var_element.add(_position_name_var_element,_token);
				}
				_token=_tokenname_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_66)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_66;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_66.addAll(_token);
			}
			_token=_token__anonymous_66;
		}
		public void parse__anonymous_65(){
			int _position__anonymous_65 = -1;
			Token.Parsed _token__anonymous_65 = null;
			_position__anonymous_65=_position;
			_token__anonymous_65=_token;
			_token=new Tokens.Name.AccessToken();
			int _state_83 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_83==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_65)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_65;
			}
			else{
				Token.Parsed _tokenname_var_element = _token;
				_token=new Tokens.Name.NameVarToken();
				int _position_name_var_element = _position;
				parse_name_var_element();
				if(_state==SUCCESS){
					_tokenname_var_element.add(_position_name_var_element,_token);
				}
				_token=_tokenname_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_65)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_65;
				}
				else{
					int _state_84 = _state;
					boolean _iteration_achieved_84 = false;
					while(_position<_inputLength){
						parse__anonymous_66();
						if(_state==FAILED){
							break;
						}
						else{
							_iteration_achieved_84=true;
						}
					}
					if(_iteration_achieved_84==false){
						_state=FAILED;
					}
					else if(_state_84==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_65)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_65;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_65.add(_position__anonymous_65,_token);
			}
			_token=_token__anonymous_65;
		}
		public void parse__anonymous_68(){
			int _position__anonymous_68 = -1;
			Token.Parsed _token__anonymous_68 = null;
			_position__anonymous_68=_position;
			_token__anonymous_68=_token;
			_token=new Tokens.Name.ConcatToken();
			int _state_85 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_85==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_68)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_68;
			}
			else{
				Token.Parsed _tokenname_var_element = _token;
				_token=new Tokens.Name.NameVarToken();
				int _position_name_var_element = _position;
				parse_name_var_element();
				if(_state==SUCCESS){
					_tokenname_var_element.add(_position_name_var_element,_token);
				}
				_token=_tokenname_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_68)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_68;
				}
				else{
					int _state_86 = _state;
					boolean _iteration_achieved_86 = false;
					while(_position<_inputLength){
						parse__anonymous_69();
						if(_state==FAILED){
							break;
						}
						else{
							_iteration_achieved_86=true;
						}
					}
					if(_iteration_achieved_86==false){
						_state=FAILED;
					}
					else if(_state_86==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_68)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_68;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_68.add(_position__anonymous_68,_token);
			}
			_token=_token__anonymous_68;
		}
		public void parse__anonymous_67(){
			int _position__anonymous_67 = -1;
			Token.Parsed _token__anonymous_67 = null;
			_position__anonymous_67=_position;
			_token__anonymous_67=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='-'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ->");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_67)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_67;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_67.addAll(_token);
			}
			_token=_token__anonymous_67;
		}
		public void parse_class_name_definition(){
			int _position_class_name_definition = -1;
			Token.Parsed _token_class_name_definition = null;
			_position_class_name_definition=_position;
			_token_class_name_definition=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+5-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='C'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='l'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='s'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_42.typeName);
				_position=_position+5;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Class");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(class_name_definition)");
					_furthestPosition=_position;
				}
				_position=_position_class_name_definition;
			}
			else{
				parse__anonymous_63();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(class_name_definition)");
						_furthestPosition=_position;
					}
					_position=_position_class_name_definition;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token_class_name_definition.addAll(_token);
			}
			_token=_token_class_name_definition;
		}
		public void parse__anonymous_69(){
			int _position__anonymous_69 = -1;
			Token.Parsed _token__anonymous_69 = null;
			_position__anonymous_69=_position;
			_token__anonymous_69=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='+'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_56.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_69)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_69;
			}
			else{
				Token.Parsed _tokenname_var_element = _token;
				_token=new Tokens.Name.NameVarToken();
				int _position_name_var_element = _position;
				parse_name_var_element();
				if(_state==SUCCESS){
					_tokenname_var_element.add(_position_name_var_element,_token);
				}
				_token=_tokenname_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_69)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_69;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_69.addAll(_token);
			}
			_token=_token__anonymous_69;
		}
		public void parse_weak(){
			int _position_weak = -1;
			Token.Parsed _token_weak = null;
			_position_weak=_position;
			_token_weak=_token;
			_token=new Tokens.Rule.WeakToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='~'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_13.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ~");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"weak(weak)");
					_furthestPosition=_position;
				}
				_position=_position_weak;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_weak.add(_position_weak,_token);
			}
			_token=_token_weak;
		}
		public void parse__anonymous_60(){
			int _position__anonymous_60 = -1;
			Token.Parsed _token__anonymous_60 = null;
			_position__anonymous_60=_position;
			_token__anonymous_60=_token;
			_token=new Token.Parsed("$ANON");
			parse_class_name_definition();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_60)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_60;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_60.addAll(_token);
			}
			_token=_token__anonymous_60;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_60=_position;
				_token__anonymous_60=_token;
				_token=new Token.Parsed("$ANON");
				parse_variable_name_definition();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_60)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_60;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_60.addAll(_token);
				}
				_token=_token__anonymous_60;
			}
		}
		public void parse__anonymous_62(){
			int _position__anonymous_62 = -1;
			Token.Parsed _token__anonymous_62 = null;
			variable_names=variable_names.push();
			_position__anonymous_62=_position;
			_token__anonymous_62=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenname_var = _token;
			_token=new Tokens.Name.VariableNameToken();
			int _position_name_var = _position;
			parse_name_var();
			if(_state==SUCCESS){
				_tokenname_var.add(_position_name_var,_token);
			}
			_token=_tokenname_var;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_62)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_62;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_62.addAll(_token);
			}
			_token=_token__anonymous_62;
			if(_state==FAILED){
				variable_names.reject();
				_state=SUCCESS;
				_position__anonymous_62=_position;
				_token__anonymous_62=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if(_state==SUCCESS){
					String _value = _token.getLastValue();
					if(_value!=null){
						variable_names.add(_value);
					}
					_token.add(_position,new Tokens.Name.VariableNameToken(_value));
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_62)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_62;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_62.addAll(_token);
				}
				_token=_token__anonymous_62;
				if(_state==FAILED){
					variable_names.reject();
				}
				else if(_state==SUCCESS){
					variable_names.accept();
				}
				variable_names=variable_names.pop();
			}
		}
		public void parse__anonymous_61(){
			int _position__anonymous_61 = -1;
			Token.Parsed _token__anonymous_61 = null;
			_position__anonymous_61=_position;
			_token__anonymous_61=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='='){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_40.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain =");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_61)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_61;
			}
			else{
				parse_method_argument();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_61)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_61;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_61.addAll(_token);
			}
			_token=_token__anonymous_61;
		}
		public void parse__anonymous_64(){
			int _position__anonymous_64 = -1;
			Token.Parsed _token__anonymous_64 = null;
			_position__anonymous_64=_position;
			_token__anonymous_64=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_64)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_64;
			}
			else{
				parse_type_var();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_64)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_64;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_64.addAll(_token);
			}
			_token=_token__anonymous_64;
		}
		public void parse__anonymous_63(){
			int _position__anonymous_63 = -1;
			Token.Parsed _token__anonymous_63 = null;
			class_variable_names=class_variable_names.push();
			_position__anonymous_63=_position;
			_token__anonymous_63=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenname_var = _token;
			_token=new Tokens.Name.VariableNameToken();
			int _position_name_var = _position;
			parse_name_var();
			if(_state==SUCCESS){
				_tokenname_var.add(_position_name_var,_token);
			}
			_token=_tokenname_var;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(_anonymous_63)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_63;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_63.addAll(_token);
			}
			_token=_token__anonymous_63;
			if(_state==FAILED){
				class_variable_names.reject();
				_state=SUCCESS;
				_position__anonymous_63=_position;
				_token__anonymous_63=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if(_state==SUCCESS){
					String _value = _token.getLastValue();
					if(_value!=null){
						class_variable_names.add(_value);
					}
					_token.add(_position,new Tokens.Name.VariableNameToken(_value));
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(_anonymous_63)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_63;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_63.addAll(_token);
				}
				_token=_token__anonymous_63;
				if(_state==FAILED){
					class_variable_names.reject();
				}
				else if(_state==SUCCESS){
					class_variable_names.accept();
				}
				class_variable_names=class_variable_names.pop();
			}
		}
		public void parse_statement_as_braced(){
			int _position_statement_as_braced = -1;
			Token.Parsed _token_statement_as_braced = null;
			int _length_statement_as_braced_brace = _inputLength;
			if(brace_3.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_3.get(_position);
				int _position_statement_as_braced_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_statement_as_braced=_position;
				_token_statement_as_braced=_token;
				_token=new Tokens.Rule.StatementAsBracedToken();
				int _position_body_statement = _position;
				if(_state==SUCCESS&&!_recursion_protection_body_statement_5.contains(_position)){
					_recursion_protection_body_statement_5.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_5.remove(_position_body_statement);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_braced(statement_as_braced)");
						_furthestPosition=_position;
					}
					_position=_position_statement_as_braced;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_statement_as_braced.add(_position_statement_as_braced,_token);
				}
				_token=_token_statement_as_braced;
				if(_state==SUCCESS&&brace_3.get(_position_statement_as_braced_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_statement_as_braced_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_statement_as_braced_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_braced(statement_as_braced)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse_variable_declaration(){
			int _position_variable_declaration = -1;
			Token.Parsed _token_variable_declaration = null;
			_position_variable_declaration=_position;
			_token_variable_declaration=_token;
			_token=new Tokens.Rule.VariableDeclarationToken();
			int _state_74 = _state;
			parse_inner();
			if(_state_74==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
					_furthestPosition=_position;
				}
				_position=_position_variable_declaration;
			}
			else{
				int _state_75 = _state;
				parse_weak();
				if(_state_75==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_variable_declaration;
				}
				else{
					int _state_76 = _state;
					parse__anonymous_59();
					if(_state_76==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_variable_declaration;
					}
					else{
						int _state_77 = _state;
						parse_weak();
						if(_state_77==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_variable_declaration;
						}
						else{
							parse__anonymous_60();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_variable_declaration;
							}
							else{
								int _state_78 = _state;
								parse__anonymous_61();
								if(_state_78==SUCCESS&&_state==FAILED){
									_state=SUCCESS;
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_variable_declaration;
								}
								else{
								}
							}
						}
					}
				}
			}
			if(_state==SUCCESS){
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
			if(_state==SUCCESS){
				_tokenall_type_name.add(_position_all_type_name,_token);
			}
			_token=_tokenall_type_name;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
					_furthestPosition=_position;
				}
				_position=_position_variable_name_definition;
			}
			else{
				int _state_80 = _state;
				if(_position+3-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='.'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='.'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='.'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_41.__SYNTAX__);
					_position=_position+3;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ...");
						_furthestPosition=_position;
					}
				}
				if(_state_80==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
						_furthestPosition=_position;
					}
					_position=_position_variable_name_definition;
				}
				else{
					int _state_81 = _state;
					while(_position<_inputLength){
						if(_position+2-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='['){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!=']'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_39.ARRAY_TYPE);
							_position=_position+2;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain []");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							break;
						}
					}
					if(_state_81==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
							_furthestPosition=_position;
						}
						_position=_position_variable_name_definition;
					}
					else{
						parse__anonymous_62();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
								_furthestPosition=_position;
							}
							_position=_position_variable_name_definition;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
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
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=','){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_55)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_55;
			}
			else{
				parse_variable_declaration();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_55)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_55;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_55.addAll(_token);
			}
			_token=_token__anonymous_55;
		}
		public void parse__anonymous_54(){
			int _position__anonymous_54 = -1;
			Token.Parsed _token__anonymous_54 = null;
			_position__anonymous_54=_position;
			_token__anonymous_54=_token;
			_token=new Tokens.Name.TokenInstanceToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=':'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_8.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain :");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_54)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_54;
			}
			else{
				Token.Parsed _tokenNAME = _token;
				_token=new Tokens.Name.TokenNameToken();
				int _position_NAME = _position;
				parse_NAME();
				if(_state==SUCCESS){
					_tokenNAME.add(_position_NAME,_token);
				}
				_token=_tokenNAME;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_54)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_54;
				}
				else{
					parse_method_body();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_54)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_54;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_54.add(_position__anonymous_54,_token);
			}
			_token=_token__anonymous_54;
		}
		public void parse__anonymous_57(){
			int _position__anonymous_57 = -1;
			Token.Parsed _token__anonymous_57 = null;
			_position__anonymous_57=_position;
			_token__anonymous_57=_token;
			_token=new Tokens.Name.MethodNameToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_31.NAME);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_57)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_57;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_57.add(_position__anonymous_57,_token);
			}
			_token=_token__anonymous_57;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_57=_position;
				_token__anonymous_57=_token;
				_token=new Tokens.Name.MethodNameToken();
				parse_name_var();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_57)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_57;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_57.add(_position__anonymous_57,_token);
				}
				_token=_token__anonymous_57;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_57=_position;
					_token__anonymous_57=_token;
					_token=new Tokens.Name.MethodNameToken();
					parse_NAME();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_57)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_57;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_57.add(_position__anonymous_57,_token);
					}
					_token=_token__anonymous_57;
				}
			}
		}
		public void parse__anonymous_56(){
			int _position__anonymous_56 = -1;
			Token.Parsed _token__anonymous_56 = null;
			_position__anonymous_56=_position;
			_token__anonymous_56=_token;
			_token=new Tokens.Name.StaticToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='@'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_37.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain @");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_56)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_56;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_56.add(_position__anonymous_56,_token);
			}
			_token=_token__anonymous_56;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_56=_position;
				_token__anonymous_56=_token;
				_token=new Tokens.Name.StaticToken();
				if(_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='s'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='t'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='a'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='t'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='c'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_38.__SYNTAX__);
					_position=_position+6;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain static");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_56)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_56;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_56.add(_position__anonymous_56,_token);
				}
				_token=_token__anonymous_56;
			}
		}
		public void parse__anonymous_59(){
			int _position__anonymous_59 = -1;
			Token.Parsed _token__anonymous_59 = null;
			_position__anonymous_59=_position;
			_token__anonymous_59=_token;
			_token=new Tokens.Name.StaticToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='@'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_37.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain @");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_59)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_59;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_59.add(_position__anonymous_59,_token);
			}
			_token=_token__anonymous_59;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_59=_position;
				_token__anonymous_59=_token;
				_token=new Tokens.Name.StaticToken();
				if(_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='s'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='t'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='a'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='t'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='c'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_38.__SYNTAX__);
					_position=_position+6;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain static");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_59)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_59;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_59.add(_position__anonymous_59,_token);
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
			Token.Parsed _tokenmethod_parameters = _token;
			_token=new Tokens.Name.InlineToken();
			int _position_method_parameters = _position;
			parse_method_parameters();
			if(_state==SUCCESS){
				_tokenmethod_parameters.add(_position_method_parameters,_token);
			}
			_token=_tokenmethod_parameters;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_58)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_58;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_58.addAll(_token);
			}
			_token=_token__anonymous_58;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_58=_position;
				_token__anonymous_58=_token;
				_token=new Token.Parsed("$ANON");
				Token.Parsed _tokenstatement_as_method = _token;
				_token=new Tokens.Name.VariableParametersToken();
				int _position_statement_as_method = _position;
				parse_statement_as_method();
				if(_state==SUCCESS){
					_tokenstatement_as_method.add(_position_statement_as_method,_token);
				}
				_token=_tokenstatement_as_method;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_58)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_58;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_58.addAll(_token);
				}
				_token=_token__anonymous_58;
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
			while(_position<_inputLength){
				if(_inputArray[_position]!='a'&&_inputArray[_position]!='b'&&_inputArray[_position]!='c'&&_inputArray[_position]!='d'&&_inputArray[_position]!='e'&&_inputArray[_position]!='f'&&_inputArray[_position]!='g'&&_inputArray[_position]!='h'&&_inputArray[_position]!='i'&&_inputArray[_position]!='j'&&_inputArray[_position]!='k'&&_inputArray[_position]!='l'&&_inputArray[_position]!='m'&&_inputArray[_position]!='n'&&_inputArray[_position]!='o'&&_inputArray[_position]!='p'&&_inputArray[_position]!='q'&&_inputArray[_position]!='r'&&_inputArray[_position]!='s'&&_inputArray[_position]!='t'&&_inputArray[_position]!='u'&&_inputArray[_position]!='v'&&_inputArray[_position]!='w'&&_inputArray[_position]!='x'&&_inputArray[_position]!='y'&&_inputArray[_position]!='z'&&_inputArray[_position]!='A'&&_inputArray[_position]!='B'&&_inputArray[_position]!='C'&&_inputArray[_position]!='D'&&_inputArray[_position]!='E'&&_inputArray[_position]!='F'&&_inputArray[_position]!='G'&&_inputArray[_position]!='H'&&_inputArray[_position]!='I'&&_inputArray[_position]!='J'&&_inputArray[_position]!='K'&&_inputArray[_position]!='L'&&_inputArray[_position]!='M'&&_inputArray[_position]!='N'&&_inputArray[_position]!='O'&&_inputArray[_position]!='P'&&_inputArray[_position]!='Q'&&_inputArray[_position]!='R'&&_inputArray[_position]!='S'&&_inputArray[_position]!='T'&&_inputArray[_position]!='U'&&_inputArray[_position]!='V'&&_inputArray[_position]!='W'&&_inputArray[_position]!='X'&&_inputArray[_position]!='Y'&&_inputArray[_position]!='Z'&&_inputArray[_position]!='0'&&_inputArray[_position]!='1'&&_inputArray[_position]!='2'&&_inputArray[_position]!='3'&&_inputArray[_position]!='4'&&_inputArray[_position]!='5'&&_inputArray[_position]!='6'&&_inputArray[_position]!='7'&&_inputArray[_position]!='8'&&_inputArray[_position]!='9'&&_inputArray[_position]!='_'&&_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'&&_inputArray[_position]!='('&&_inputArray[_position]!=')'&&_inputArray[_position]!='{'&&_inputArray[_position]!='}'&&_inputArray[_position]!='['&&_inputArray[_position]!=']'&&_inputArray[_position]!=';'&&_inputArray[_position]!='\"'&&_inputArray[_position]!='\\'&&_inputArray[_position]!='\''&&_inputArray[_position]!='`'&&_inputArray[_position]!=','){
					++_position;
					++_multiple_index_3;
				}
				else{
					break;
				}
			}
			if(_multiple_index_3==0 ){
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_token.setValue(_input.substring(_position_regex_2,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z0-9_\\\\s(){}[];\\\"\\\\\'`,]+");
					_furthestPosition=_position;
				}
				_position=_position_regex_2;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"OPERATOR(OPERATOR)");
					_furthestPosition=_position;
				}
				_position=_position_OPERATOR;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_OPERATOR.add(_position_OPERATOR,_token);
			}
			_token=_token_OPERATOR;
		}
		public void parse__anonymous_51(){
			int _position__anonymous_51 = -1;
			Token.Parsed _token__anonymous_51 = null;
			_position__anonymous_51=_position;
			_token__anonymous_51=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_51)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_51;
			}
			else{
				parse__anonymous_52();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_51)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_51;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_51.addAll(_token);
			}
			_token=_token__anonymous_51;
		}
		public void parse__anonymous_50(){
			int _position__anonymous_50 = -1;
			Token.Parsed _token__anonymous_50 = null;
			_position__anonymous_50=_position;
			_token__anonymous_50=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_50)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_50;
			}
			else{
				parse_type_var();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_50)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_50;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_50.addAll(_token);
			}
			_token=_token__anonymous_50;
		}
		public void parse__anonymous_53(){
			int _position__anonymous_53 = -1;
			Token.Parsed _token__anonymous_53 = null;
			_position__anonymous_53=_position;
			_token__anonymous_53=_token;
			_token=new Token.Parsed("$ANON");
			parse_class_declaration();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_53;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_53.addAll(_token);
			}
			_token=_token__anonymous_53;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_53=_position;
				_token__anonymous_53=_token;
				_token=new Token.Parsed("$ANON");
				parse_method_declaration();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_53;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_53.addAll(_token);
				}
				_token=_token__anonymous_53;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_53=_position;
					_token__anonymous_53=_token;
					_token=new Token.Parsed("$ANON");
					parse_variable_declaration();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_53;
					}
					else{
						if(_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!=';'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_53;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token__anonymous_53.addAll(_token);
					}
					_token=_token__anonymous_53;
					if(_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_53=_position;
						_token__anonymous_53=_token;
						_token=new Token.Parsed("$ANON");
						Token.Parsed _tokenmethod_body = _token;
						_token=new Tokens.Name.BodyToken();
						int _position_method_body = _position;
						parse_method_body();
						if(_state==SUCCESS){
							_tokenmethod_body.add(_position_method_body,_token);
						}
						_token=_tokenmethod_body;
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_53;
						}
						else{
						}
						if(_state==SUCCESS){
							_token__anonymous_53.addAll(_token);
						}
						_token=_token__anonymous_53;
						if(_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_53=_position;
							_token__anonymous_53=_token;
							_token=new Token.Parsed("$ANON");
							Token.Parsed _tokenas_statement = _token;
							_token=new Tokens.Name.BodyToken();
							int _position_as_statement = _position;
							parse_as_statement();
							if(_state==SUCCESS){
								_tokenas_statement.add(_position_as_statement,_token);
							}
							_token=_tokenas_statement;
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_53;
							}
							else{
							}
							if(_state==SUCCESS){
								_token__anonymous_53.addAll(_token);
							}
							_token=_token__anonymous_53;
						}
					}
				}
			}
		}
		public void parse__anonymous_52(){
			int _position__anonymous_52 = -1;
			Token.Parsed _token__anonymous_52 = null;
			_position__anonymous_52=_position;
			_token__anonymous_52=_token;
			_token=new Tokens.Name.GroupToken();
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_52)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_52;
			}
			else{
				int _state_64 = _state;
				parse_method_arguments();
				if(_state_64==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_52)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_52;
				}
				else{
					int _state_65 = _state;
					while(_position<_inputLength){
						parse_array_parameters();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_65==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_52)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_52;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_52.add(_position__anonymous_52,_token);
			}
			_token=_token__anonymous_52;
		}
		public void parse_body_call(){
			int _position_body_call = -1;
			Token.Parsed _token_body_call = null;
			_position_body_call=_position;
			_token_body_call=_token;
			_token=new Tokens.Rule.BodyCallToken();
			parse__anonymous_40();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
					_furthestPosition=_position;
				}
				_position=_position_body_call;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_body_call.add(_position_body_call,_token);
			}
			_token=_token_body_call;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_body_call=_position;
				_token_body_call=_token;
				_token=new Tokens.Rule.BodyCallToken();
				parse__anonymous_42();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
						_furthestPosition=_position;
					}
					_position=_position_body_call;
				}
				else{
					int _state_52 = _state;
					while(_position<_inputLength){
						parse__anonymous_43();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_52==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
							_furthestPosition=_position;
						}
						_position=_position_body_call;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token_body_call.add(_position_body_call,_token);
				}
				_token=_token_body_call;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_body_call=_position;
					_token_body_call=_token;
					_token=new Tokens.Rule.BodyCallToken();
					parse__anonymous_45();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
							_furthestPosition=_position;
						}
						_position=_position_body_call;
					}
					else{
						int _state_57 = _state;
						while(_position<_inputLength){
							parse__anonymous_46();
							if(_state==FAILED){
								break;
							}
						}
						if(_state_57==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
								_furthestPosition=_position;
							}
							_position=_position_body_call;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token_body_call.add(_position_body_call,_token);
					}
					_token=_token_body_call;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_body_call=_position;
						_token_body_call=_token;
						_token=new Tokens.Rule.BodyCallToken();
						parse__anonymous_49();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
								_furthestPosition=_position;
							}
							_position=_position_body_call;
						}
						else{
							int _state_63 = _state;
							while(_position<_inputLength){
								parse__anonymous_51();
								if(_state==FAILED){
									break;
								}
							}
							if(_state_63==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
									_furthestPosition=_position;
								}
								_position=_position_body_call;
							}
							else{
							}
						}
						if(_state==SUCCESS){
							_token_body_call.add(_position_body_call,_token);
						}
						_token=_token_body_call;
					}
				}
			}
		}
		public void parse_imports__file_import(){
			int _position_imports__file_import = -1;
			Token.Parsed _token_imports__file_import = null;
			_position_imports__file_import=_position;
			_token_imports__file_import=_token;
			_token=new Token.Parsed("$ANON");
			int _state_20 = _state;
			while(_position<_inputLength){
				int _position_base_element = _position;
				if(_state==SUCCESS&&!_recursion_protection_base_element_12.contains(_position)){
					_recursion_protection_base_element_12.add(_position);
					parse_base_element();
					_recursion_protection_base_element_12.remove(_position_base_element);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					break;
				}
			}
			if(_state_20==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
			}
			else{
			}
			if(_state==SUCCESS){
				_token_imports__file_import.addAll(_token);
			}
			_token=_token_imports__file_import;
		}
		public void parse__anonymous_44(){
			int _position__anonymous_44 = -1;
			Token.Parsed _token__anonymous_44 = null;
			_position__anonymous_44=_position;
			_token__anonymous_44=_token;
			_token=new Tokens.Name.GroupToken();
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_44)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_44;
			}
			else{
				int _state_53 = _state;
				parse_method_arguments();
				if(_state_53==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_44)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_44;
				}
				else{
					int _state_54 = _state;
					while(_position<_inputLength){
						parse_array_parameters();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_54==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_44)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_44;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_44.add(_position__anonymous_44,_token);
			}
			_token=_token__anonymous_44;
		}
		public void parse__anonymous_43(){
			int _position__anonymous_43 = -1;
			Token.Parsed _token__anonymous_43 = null;
			_position__anonymous_43=_position;
			_token__anonymous_43=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_43)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_43;
			}
			else{
				parse__anonymous_44();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_43)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_43;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_43.addAll(_token);
			}
			_token=_token__anonymous_43;
		}
		public void parse__anonymous_46(){
			int _position__anonymous_46 = -1;
			Token.Parsed _token__anonymous_46 = null;
			_position__anonymous_46=_position;
			_token__anonymous_46=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_46)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_46;
			}
			else{
				parse__anonymous_47();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_46)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_46;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_46.addAll(_token);
			}
			_token=_token__anonymous_46;
		}
		public void parse__anonymous_45(){
			int _position__anonymous_45 = -1;
			Token.Parsed _token__anonymous_45 = null;
			_position__anonymous_45=_position;
			_token__anonymous_45=_token;
			_token=new Tokens.Name.GroupToken();
			int _position_name_var = _position;
			if(_state==SUCCESS&&!_recursion_protection_name_var_36.contains(_position)){
				_recursion_protection_name_var_36.add(_position);
				parse_name_var();
				_recursion_protection_name_var_36.remove(_position_name_var);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_45)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_45;
			}
			else{
				int _state_55 = _state;
				parse_method_arguments();
				if(_state_55==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_45)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_45;
				}
				else{
					int _state_56 = _state;
					while(_position<_inputLength){
						parse_array_parameters();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_56==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_45)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_45;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_45.add(_position__anonymous_45,_token);
			}
			_token=_token__anonymous_45;
		}
		public void parse__anonymous_48(){
			int _position__anonymous_48 = -1;
			Token.Parsed _token__anonymous_48 = null;
			_position__anonymous_48=_position;
			_token__anonymous_48=_token;
			_token=new Token.Parsed("$ANON");
			int _position_name_var = _position;
			if(_state==SUCCESS&&!_recursion_protection_name_var_37.contains(_position)){
				_recursion_protection_name_var_37.add(_position);
				parse_name_var();
				_recursion_protection_name_var_37.remove(_position_name_var);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_48)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_48;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_48.addAll(_token);
			}
			_token=_token__anonymous_48;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_48=_position;
				_token__anonymous_48=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_48)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_48;
				}
				else{
				}
				if(_state==SUCCESS){
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
			_token=new Tokens.Name.GroupToken();
			parse__anonymous_48();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_47)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_47;
			}
			else{
				int _state_58 = _state;
				parse_method_arguments();
				if(_state_58==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_47)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_47;
				}
				else{
					int _state_59 = _state;
					while(_position<_inputLength){
						parse_array_parameters();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_59==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_47)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_47;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_47.add(_position__anonymous_47,_token);
			}
			_token=_token__anonymous_47;
		}
		public void parse__anonymous_49(){
			int _position__anonymous_49 = -1;
			Token.Parsed _token__anonymous_49 = null;
			_position__anonymous_49=_position;
			_token__anonymous_49=_token;
			_token=new Tokens.Name.GroupToken();
			int _position_type_var = _position;
			if(_state==SUCCESS&&!_recursion_protection_type_var_38.contains(_position)){
				_recursion_protection_type_var_38.add(_position);
				parse_type_var();
				_recursion_protection_type_var_38.remove(_position_type_var);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_49)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_49;
			}
			else{
				int _state_60 = _state;
				while(_position<_inputLength){
					parse__anonymous_50();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_60==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_49)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_49;
				}
				else{
					int _state_61 = _state;
					parse_method_arguments();
					if(_state_61==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_49)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_49;
					}
					else{
						int _state_62 = _state;
						while(_position<_inputLength){
							parse_array_parameters();
							if(_state==FAILED){
								break;
							}
						}
						if(_state_62==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_49)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_49;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_49.add(_position__anonymous_49,_token);
			}
			_token=_token__anonymous_49;
		}
		public void parse_anonymous_class(){
			int _position_anonymous_class = -1;
			Token.Parsed _token_anonymous_class = null;
			class_names=class_names.push();
			_position_anonymous_class=_position;
			_token_anonymous_class=_token;
			_token=new Tokens.Rule.AnonymousClassToken();
			parse__anonymous_6();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
					_furthestPosition=_position;
				}
				_position=_position_anonymous_class;
			}
			else{
				parse_NAME();
				if(_state==SUCCESS){
					String _value = _token.getLastValue();
					if(_value!=null){
						class_names.add(_value);
					}
					_token.add(_position,new Tokens.Name.ClassNameToken(_value));
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
						_furthestPosition=_position;
					}
					_position=_position_anonymous_class;
				}
				else{
					parse__anonymous_7();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
							_furthestPosition=_position;
						}
						_position=_position_anonymous_class;
					}
					else{
						int _state_22 = _state;
						parse__anonymous_8();
						if(_state_22==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
								_furthestPosition=_position;
							}
							_position=_position_anonymous_class;
						}
						else{
							if(_position+1-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!=';'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
								_position=_position+1;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
									_furthestPosition=_position;
								}
								_position=_position_anonymous_class;
							}
							else{
							}
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_anonymous_class.add(_position_anonymous_class,_token);
			}
			_token=_token_anonymous_class;
			if(_state==FAILED){
				class_names.reject();
			}
			else if(_state==SUCCESS){
				class_names.accept();
			}
			class_names=class_names.pop();
		}
		public void parse__anonymous_6(){
			int _position__anonymous_6 = -1;
			Token.Parsed _token__anonymous_6 = null;
			_position__anonymous_6=_position;
			_token__anonymous_6=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='<'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain <");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_6)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_6;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_6.addAll(_token);
			}
			_token=_token__anonymous_6;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_6=_position;
				_token__anonymous_6=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='h'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='d'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='d'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='n'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_7.__SYNTAX__);
					_position=_position+6;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain hidden");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_6)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_6;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_6.addAll(_token);
				}
				_token=_token__anonymous_6;
			}
		}
		public void parse__anonymous_5(){
			int _position__anonymous_5 = -1;
			Token.Parsed _token__anonymous_5 = null;
			_position__anonymous_5=_position;
			_token__anonymous_5=_token;
			_token=new Token.Parsed("$ANON");
			int _state_18 = _state;
			boolean _iteration_achieved_18 = false;
			while(_position<_inputLength){
				parse_body_element();
				if(_state==FAILED){
					break;
				}
				else{
					_iteration_achieved_18=true;
				}
			}
			if(_iteration_achieved_18==false){
				_state=FAILED;
			}
			else if(_state_18==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(_anonymous_5)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_5;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_5.addAll(_token);
			}
			_token=_token__anonymous_5;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_5=_position;
				_token__anonymous_5=_token;
				_token=new Token.Parsed("$ANON");
				int _position_body_statement = _position;
				if(_state==SUCCESS&&!_recursion_protection_body_statement_6.contains(_position)){
					_recursion_protection_body_statement_6.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_6.remove(_position_body_statement);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(_anonymous_5)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_5;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_5.addAll(_token);
				}
				_token=_token__anonymous_5;
			}
		}
		public void parse__anonymous_8(){
			int _position__anonymous_8 = -1;
			Token.Parsed _token__anonymous_8 = null;
			_position__anonymous_8=_position;
			_token__anonymous_8=_token;
			_token=new Token.Parsed("$ANON");
			parse_packageName();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_8)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_8;
			}
			else{
				int _state_23 = _state;
				while(_position<_inputLength){
					parse__anonymous_9();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_23==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_8)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_8;
				}
				else{
				}
			}
			if(_state==SUCCESS){
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
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=':'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_8.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain :");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_7)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_7;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_7.addAll(_token);
			}
			_token=_token__anonymous_7;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_7=_position;
				_token__anonymous_7=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+4-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='f'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='r'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='o'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='m'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
					_position=_position+4;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain from");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_7)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_7;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_7.addAll(_token);
				}
				_token=_token__anonymous_7;
			}
		}
		public void parse__anonymous_2(){
			int _position__anonymous_2 = -1;
			Token.Parsed _token__anonymous_2 = null;
			_position__anonymous_2=_position;
			_token__anonymous_2=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=','){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(_anonymous_2)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_2;
			}
			else{
				Token.Parsed _tokenall_type_name = _token;
				_token=new Tokens.Name.TemplateParameterToken();
				int _position_all_type_name = _position;
				parse_all_type_name();
				if(_state==SUCCESS){
					_tokenall_type_name.add(_position_all_type_name,_token);
				}
				_token=_tokenall_type_name;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(_anonymous_2)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_2;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_2.addAll(_token);
			}
			_token=_token__anonymous_2;
		}
		public void parse__anonymous_40(){
			int _position__anonymous_40 = -1;
			Token.Parsed _token__anonymous_40 = null;
			_position__anonymous_40=_position;
			_token__anonymous_40=_token;
			_token=new Tokens.Name.AsBracedToken();
			Token.Parsed _tokenstatement_as_braced = _token;
			_token=new Tokens.Name.LeftToken();
			int _position_statement_as_braced = _position;
			parse_statement_as_braced();
			if(_state==SUCCESS){
				_tokenstatement_as_braced.add(_position_statement_as_braced,_token);
			}
			_token=_tokenstatement_as_braced;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_40)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_40;
			}
			else{
				int _state_47 = _state;
				parse__anonymous_41();
				if(_state_47==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_40)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_40;
				}
				else{
					int _state_49 = _state;
					Token.Parsed _tokenbody_statement = _token;
					_token=new Tokens.Name.RightToken();
					int _position_body_statement = _position;
					parse_body_statement();
					if(_state==SUCCESS){
						_tokenbody_statement.add(_position_body_statement,_token);
					}
					_token=_tokenbody_statement;
					if(_state_49==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_40)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_40;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_40.add(_position__anonymous_40,_token);
			}
			_token=_token__anonymous_40;
		}
		public void parse__anonymous_1(){
			int _position__anonymous_1 = -1;
			Token.Parsed _token__anonymous_1 = null;
			_position__anonymous_1=_position;
			_token__anonymous_1=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=','){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_1)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_1;
			}
			else{
				parse_method_argument();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_1)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_1;
				}
				else{
				}
			}
			if(_state==SUCCESS){
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
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=','){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_4)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_4;
			}
			else{
				parse_method_argument();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_4)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_4;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_4.addAll(_token);
			}
			_token=_token__anonymous_4;
		}
		public void parse__anonymous_42(){
			int _position__anonymous_42 = -1;
			Token.Parsed _token__anonymous_42 = null;
			_position__anonymous_42=_position;
			_token__anonymous_42=_token;
			_token=new Tokens.Name.GroupToken();
			if(_position+4-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='n'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='w'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!=' '){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_33.NEW);
				_position=_position+4;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain new ");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_42)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_42;
			}
			else{
				Token.Parsed _tokenall_type_name = _token;
				_token=new Tokens.Name.TypeNameToken();
				int _position_all_type_name = _position;
				parse_all_type_name();
				if(_state==SUCCESS){
					_tokenall_type_name.add(_position_all_type_name,_token);
				}
				_token=_tokenall_type_name;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_42)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_42;
				}
				else{
					int _state_50 = _state;
					parse_method_arguments();
					if(_state_50==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_42)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_42;
					}
					else{
						int _state_51 = _state;
						while(_position<_inputLength){
							parse_array_parameters();
							if(_state==FAILED){
								break;
							}
						}
						if(_state_51==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_42)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_42;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_42.add(_position__anonymous_42,_token);
			}
			_token=_token__anonymous_42;
		}
		public void parse__anonymous_3(){
			int _position__anonymous_3 = -1;
			Token.Parsed _token__anonymous_3 = null;
			_position__anonymous_3=_position;
			_token__anonymous_3=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_argument();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_3)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_3;
			}
			else{
				int _state_17 = _state;
				while(_position<_inputLength){
					parse__anonymous_4();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_17==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_3)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_3;
				}
				else{
				}
			}
			if(_state==SUCCESS){
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
			if(_state==SUCCESS&&!_recursion_protection_comments_7.contains(_position)){
				_recursion_protection_comments_7.add(_position);
				parse_comments();
				_recursion_protection_comments_7.remove(_position_comments);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
					_furthestPosition=_position;
				}
				_position=_position_base_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_base_element.addAll(_token);
			}
			_token=_token_base_element;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_base_element=_position;
				_token_base_element=_token;
				_token=new Token.Parsed("$ANON");
				int _position_imports = _position;
				if(_state==SUCCESS&&!_recursion_protection_imports_8.contains(_position)){
					_recursion_protection_imports_8.add(_position);
					parse_imports();
					_recursion_protection_imports_8.remove(_position_imports);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
						_furthestPosition=_position;
					}
					_position=_position_base_element;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_base_element.addAll(_token);
				}
				_token=_token_base_element;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_base_element=_position;
					_token_base_element=_token;
					_token=new Token.Parsed("$ANON");
					parse_anonymous_class();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
							_furthestPosition=_position;
						}
						_position=_position_base_element;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_base_element.addAll(_token);
					}
					_token=_token_base_element;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_base_element=_position;
						_token_base_element=_token;
						_token=new Token.Parsed("$ANON");
						int _position_class_declaration = _position;
						if(_state==SUCCESS&&!_recursion_protection_class_declaration_9.contains(_position)){
							_recursion_protection_class_declaration_9.add(_position);
							parse_class_declaration();
							_recursion_protection_class_declaration_9.remove(_position_class_declaration);
						}
						else{
							_state=FAILED;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
								_furthestPosition=_position;
							}
							_position=_position_base_element;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_base_element.addAll(_token);
						}
						_token=_token_base_element;
						if(_state==FAILED){
							_state=SUCCESS;
							_position_base_element=_position;
							_token_base_element=_token;
							_token=new Token.Parsed("$ANON");
							int _position_method_declaration = _position;
							if(_state==SUCCESS&&!_recursion_protection_method_declaration_10.contains(_position)){
								_recursion_protection_method_declaration_10.add(_position);
								parse_method_declaration();
								_recursion_protection_method_declaration_10.remove(_position_method_declaration);
							}
							else{
								_state=FAILED;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
									_furthestPosition=_position;
								}
								_position=_position_base_element;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_base_element.addAll(_token);
							}
							_token=_token_base_element;
							if(_state==FAILED){
								_state=SUCCESS;
								_position_base_element=_position;
								_token_base_element=_token;
								_token=new Token.Parsed("$ANON");
								int _position_variable_declaration = _position;
								if(_state==SUCCESS&&!_recursion_protection_variable_declaration_11.contains(_position)){
									_recursion_protection_variable_declaration_11.add(_position);
									parse_variable_declaration();
									_recursion_protection_variable_declaration_11.remove(_position_variable_declaration);
								}
								else{
									_state=FAILED;
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
										_furthestPosition=_position;
									}
									_position=_position_base_element;
								}
								else{
									if(_position+1-1 >=_inputLength){
										_state=FAILED;
									}
									else{
										if(_inputArray[_position+0]!=';'){
											_state=FAILED;
										}
									}
									if(_state==SUCCESS){
										_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
										_position=_position+1;
										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
											++_position;
										}
									}
									else if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
											_furthestPosition=_position;
										}
									}
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
											_furthestPosition=_position;
										}
										_position=_position_base_element;
									}
									else{
									}
								}
								if(_state==SUCCESS){
									_token_base_element.addAll(_token);
								}
								_token=_token_base_element;
							}
						}
					}
				}
			}
		}
		public void parse__anonymous_41(){
			int _position__anonymous_41 = -1;
			Token.Parsed _token__anonymous_41 = null;
			_position__anonymous_41=_position;
			_token__anonymous_41=_token;
			_token=new Token.Parsed("$ANON");
			int _state_48 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\");
					_furthestPosition=_position;
				}
			}
			if(_state_48==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_41)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_41;
			}
			else{
				parse_OPERATOR();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_41)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_41;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_41.addAll(_token);
			}
			_token=_token__anonymous_41;
		}
		public void parse__anonymous_9(){
			int _position__anonymous_9 = -1;
			Token.Parsed _token__anonymous_9 = null;
			_position__anonymous_9=_position;
			_token__anonymous_9=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_9)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_9;
			}
			else{
				parse_packageName();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_9)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_9;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_9.addAll(_token);
			}
			_token=_token__anonymous_9;
		}
		public void parse_NUMBER(){
			int _position_NUMBER = -1;
			Token.Parsed _token_NUMBER = null;
			_position_NUMBER=_position;
			_token_NUMBER=_token;
			_token=new Tokens.Rule.NUMBERToken();
			int _position_regex_3 = _position;
			if(_position<_inputLength){
				if(_inputArray[_position]=='-'){
					++_position;
				}
			}
			int _state_5 = _state;
			if(_position<_inputLength){
				int _position_of_last_success_5 = _position;
				int _multiple_index_6 = 0;
				int _state_6 = _state;
				while(_position<_inputLength){
					if(_position<_inputLength){
						if(_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9'){
							++_position;
						}
						else{
							_state=FAILED;
						}
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						break;
					}
					else{
						++_multiple_index_6;
					}
				}
				if(_state_6==SUCCESS){
					_state=SUCCESS;
				}
				if(_position<_inputLength){
					if(_inputArray[_position]=='.'){
						++_position;
					}
					else{
						_state=FAILED;
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					_position=_position_of_last_success_5;
				}
			}
			if(_state_5==SUCCESS){
				_state=SUCCESS;
			}
			int _multiple_index_7 = 0;
			int _state_7 = _state;
			while(_position<_inputLength){
				if(_position<_inputLength){
					if(_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9'){
						++_position;
					}
					else{
						_state=FAILED;
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					break;
				}
				else{
					++_multiple_index_7;
				}
			}
			if(_state_7==SUCCESS&&_multiple_index_7>0 ){
				_state=SUCCESS;
			}
			else{
				_state=FAILED;
			}
			int _state_8 = _state;
			if(_position<_inputLength){
				if(_position<_inputLength){
					if(_inputArray[_position]=='f'){
						++_position;
					}
					else{
						_state=FAILED;
					}
				}
				else{
					_state=FAILED;
				}
			}
			if(_state_8==SUCCESS){
				_state=SUCCESS;
			}
			int _multiple_index_9 = 0;
			int _state_9 = _state;
			while(_position<_inputLength){
				if(_position<_inputLength){
					if(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n'){
						++_position;
					}
					else{
						_state=FAILED;
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					break;
				}
				else{
					++_multiple_index_9;
				}
			}
			if(_state_9==SUCCESS){
				_state=SUCCESS;
			}
			if(_state==SUCCESS){
				_token.setValue(_input.substring(_position_regex_3,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[-]?(\\\\d*[.]?\\\\d+f?\\\\s*");
					_furthestPosition=_position;
				}
				_position=_position_regex_3;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"NUMBER(NUMBER)");
					_furthestPosition=_position;
				}
				_position=_position_NUMBER;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_NUMBER.add(_position_NUMBER,_token);
			}
			_token=_token_NUMBER;
		}
		public void parse_class_body(){
			int _position_class_body = -1;
			Token.Parsed _token_class_body = null;
			int _length_class_body_brace = _inputLength;
			if(brace_2.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_2.get(_position);
				int _position_class_body_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_class_body=_position;
				_token_class_body=_token;
				_token=new Token.Parsed("$ANON");
				int _state_11 = _state;
				while(_position<_inputLength){
					parse_class_element();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_11==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_body(class_body)");
						_furthestPosition=_position;
					}
					_position=_position_class_body;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_class_body.addAll(_token);
				}
				_token=_token_class_body;
				if(_state==SUCCESS&&brace_2.get(_position_class_body_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_2.get(_position_class_body_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_class_body_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_body(class_body)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse_imports(){
			int _position_imports = -1;
			Token.Parsed _token_imports = null;
			_position_imports=_position;
			_token_imports=_token;
			_token=new Tokens.Rule.ImportsToken();
			if(_position+7-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='m'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='p'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='o'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+6]!=' '){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_3.__SYNTAX__);
				_position=_position+7;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain import ");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
					_furthestPosition=_position;
				}
				_position=_position_imports;
			}
			else{
				parse_class_file_name();
				if(_state==SUCCESS){
					_import_class_file_name_value=_token.getLastValue();
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
						_furthestPosition=_position;
					}
					_position=_position_imports;
				}
				else{
					if(_position+5-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='.'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='c'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='l'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='w'){
							_state=FAILED;
						}
						if(_inputArray[_position+4]!='s'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_4.__SYNTAX__);
						_position=_position+5;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .clws");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
							_furthestPosition=_position;
						}
						_position=_position_imports;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				StringBuilder _fileNameBuilder = new StringBuilder();
				_fileNameBuilder.append(_import_class_file_name_value);
				_fileNameBuilder.append(".clws");
				String _import_file_name = _directoryName+_fileNameBuilder.toString();
				if(new File(_import_file_name).exists()){
					if(Parser.contexts.containsKey(_import_file_name)==false){
						Parser.Context _import_context = new Parser.Context.ImportsFileImport(class_names_root,class_variable_names_root,variable_names_root);
						Parser.contexts.put(_import_file_name,_import_context);
					}
					Parser.contexts.get(_import_file_name).set_root(new Token.Parsed("$ROOT"));
					Parser.contexts.get(_import_file_name).set_token(Parser.contexts.get(_import_file_name).get_root());
					_token.add(_position,new Token.Parsed.Import(_import_file_name));
					addImportThread(_import_file_name);
				}
				else{
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,_directoryName+_fileNameBuilder.toString()+" cannot be found!");
						_furthestPosition=_position;
					}
					_position=_position_imports;
					_state=FAILED;
					System.err.println("Could not find file:"+_directoryName+_fileNameBuilder.toString());
				}
			}
			if(_state==SUCCESS){
				_token_imports.add(_position_imports,_token);
			}
			_token=_token_imports;
		}
		public void parse_body_manipulate(){
			int _position_body_manipulate = -1;
			Token.Parsed _token_body_manipulate = null;
			variable_names=variable_names.push();
			_position_body_manipulate=_position;
			_token_body_manipulate=_token;
			_token=new Tokens.Rule.BodyManipulateToken();
			int _state_66 = _state;
			int _position_inner = _position;
			if(_state==SUCCESS&&!_recursion_protection_inner_39.contains(_position)){
				_recursion_protection_inner_39.add(_position);
				parse_inner();
				_recursion_protection_inner_39.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_66==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
					_furthestPosition=_position;
				}
				_position=_position_body_manipulate;
			}
			else{
				parse_type_var();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
						_furthestPosition=_position;
					}
					_position=_position_body_manipulate;
				}
				else{
					if(_position+2-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='+'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='='){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_34.methodName);
						_position=_position+2;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +=");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
							_furthestPosition=_position;
						}
						_position=_position_body_manipulate;
					}
					else{
						parse__anonymous_53();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
								_furthestPosition=_position;
							}
							_position=_position_body_manipulate;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_body_manipulate.add(_position_body_manipulate,_token);
			}
			_token=_token_body_manipulate;
			if(_state==FAILED){
				variable_names.reject();
				_state=SUCCESS;
				_position_body_manipulate=_position;
				_token_body_manipulate=_token;
				_token=new Tokens.Rule.BodyManipulateToken();
				int _state_67 = _state;
				_position_inner=_position;
				if(_state==SUCCESS&&!_recursion_protection_inner_40.contains(_position)){
					_recursion_protection_inner_40.add(_position);
					parse_inner();
					_recursion_protection_inner_40.remove(_position_inner);
				}
				else{
					_state=FAILED;
				}
				if(_state_67==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
						_furthestPosition=_position;
					}
					_position=_position_body_manipulate;
				}
				else{
					parse_name_var();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
							_furthestPosition=_position;
						}
						_position=_position_body_manipulate;
					}
					else{
						if(_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!=':'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_8.__SYNTAX__);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain :");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
								_furthestPosition=_position;
							}
							_position=_position_body_manipulate;
						}
						else{
							parse_NAME();
							if(_state==SUCCESS){
								String _value = _token.getLastValue();
								if(_value!=null){
									variable_names.add(_value);
								}
								_token.add(_position,new Tokens.Name.VariableNameToken(_value));
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
									_furthestPosition=_position;
								}
								_position=_position_body_manipulate;
							}
							else{
								int _state_68 = _state;
								boolean _iteration_achieved_68 = false;
								while(_position<_inputLength){
									parse__anonymous_54();
									if(_state==FAILED){
										break;
									}
									else{
										_iteration_achieved_68=true;
									}
								}
								if(_iteration_achieved_68==false){
									_state=FAILED;
								}
								else if(_state_68==SUCCESS&&_state==FAILED){
									_state=SUCCESS;
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
										_furthestPosition=_position;
									}
									_position=_position_body_manipulate;
								}
								else{
								}
							}
						}
					}
				}
				if(_state==SUCCESS){
					_token_body_manipulate.add(_position_body_manipulate,_token);
				}
				_token=_token_body_manipulate;
				if(_state==FAILED){
					variable_names.reject();
				}
				else if(_state==SUCCESS){
					variable_names.accept();
				}
				variable_names=variable_names.pop();
			}
		}
		public void parse_as_statement(){
			int _position_as_statement = -1;
			Token.Parsed _token_as_statement = null;
			int _length_as_statement_brace = _inputLength;
			if(brace_9.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_9.get(_position);
				int _position_as_statement_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_as_statement=_position;
				_token_as_statement=_token;
				_token=new Tokens.Rule.AsStatementToken();
				parse__anonymous_5();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(as_statement)");
						_furthestPosition=_position;
					}
					_position=_position_as_statement;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_as_statement.add(_position_as_statement,_token);
				}
				_token=_token_as_statement;
				if(_state==SUCCESS&&brace_9.get(_position_as_statement_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_9.get(_position_as_statement_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_as_statement_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
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
			int _position_inner = _position;
			if(_state==SUCCESS&&!_recursion_protection_inner_44.contains(_position)){
				_recursion_protection_inner_44.add(_position);
				parse_inner();
				_recursion_protection_inner_44.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_71==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
					_furthestPosition=_position;
				}
				_position=_position_method_declaration;
			}
			else{
				int _state_72 = _state;
				parse__anonymous_56();
				if(_state_72==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_method_declaration;
				}
				else{
					Token.Parsed _tokenall_type_name = _token;
					_token=new Tokens.Name.TypeNameToken();
					int _position_all_type_name = _position;
					parse_all_type_name();
					if(_state==SUCCESS){
						_tokenall_type_name.add(_position_all_type_name,_token);
					}
					_token=_tokenall_type_name;
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_method_declaration;
					}
					else{
						int _state_73 = _state;
						while(_position<_inputLength){
							if(_position+2-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='['){
									_state=FAILED;
								}
								if(_inputArray[_position+1]!=']'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_39.ARRAY_TYPE);
								_position=_position+2;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain []");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								break;
							}
						}
						if(_state_73==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_method_declaration;
						}
						else{
							parse__anonymous_57();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_method_declaration;
							}
							else{
								parse__anonymous_58();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_method_declaration;
								}
								else{
									parse_method_body();
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_method_declaration;
									}
									else{
									}
								}
							}
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_method_declaration.add(_position_method_declaration,_token);
			}
			_token=_token_method_declaration;
		}
		public void parse__anonymous_33(){
			int _position__anonymous_33 = -1;
			Token.Parsed _token__anonymous_33 = null;
			_position__anonymous_33=_position;
			_token__anonymous_33=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			parse_method_body();
			if(_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_33)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_33;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_33.addAll(_token);
			}
			_token=_token__anonymous_33;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_33=_position;
				_token__anonymous_33=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_33)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_33;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_33.addAll(_token);
				}
				_token=_token__anonymous_33;
			}
		}
		public void parse__anonymous_32(){
			int _position__anonymous_32 = -1;
			Token.Parsed _token__anonymous_32 = null;
			_position__anonymous_32=_position;
			_token__anonymous_32=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			parse_method_body();
			if(_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_32)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_32;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_32.addAll(_token);
			}
			_token=_token__anonymous_32;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_32=_position;
				_token__anonymous_32=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_32)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_32;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_32.addAll(_token);
				}
				_token=_token__anonymous_32;
			}
		}
		public void parse__anonymous_0(){
			int _position__anonymous_0 = -1;
			Token.Parsed _token__anonymous_0 = null;
			_position__anonymous_0=_position;
			_token__anonymous_0=_token;
			_token=new Token.Parsed("$ANON");
			int _position_method_argument = _position;
			if(_state==SUCCESS&&!_recursion_protection_method_argument_1.contains(_position)){
				_recursion_protection_method_argument_1.add(_position);
				parse_method_argument();
				_recursion_protection_method_argument_1.remove(_position_method_argument);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_0)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_0;
			}
			else{
				int _state_14 = _state;
				while(_position<_inputLength){
					parse__anonymous_1();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_14==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_0)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_0;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_0.addAll(_token);
			}
			_token=_token__anonymous_0;
		}
		public void parse__anonymous_35(){
			int _position__anonymous_35 = -1;
			Token.Parsed _token__anonymous_35 = null;
			_position__anonymous_35=_position;
			_token__anonymous_35=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='|'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_32.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain |");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_35)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_35;
			}
			else{
				parse__anonymous_36();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_35)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_35;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_35.addAll(_token);
			}
			_token=_token__anonymous_35;
		}
		public void parse__anonymous_34(){
			int _position__anonymous_34 = -1;
			Token.Parsed _token__anonymous_34 = null;
			_position__anonymous_34=_position;
			_token__anonymous_34=_token;
			_token=new Tokens.Name.ExceptionToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_34)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_34;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_34.add(_position__anonymous_34,_token);
			}
			_token=_token__anonymous_34;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_34=_position;
				_token__anonymous_34=_token;
				_token=new Tokens.Name.ExceptionToken();
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_34)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_34;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_34.add(_position__anonymous_34,_token);
				}
				_token=_token__anonymous_34;
			}
		}
		public void parse__anonymous_37(){
			int _position__anonymous_37 = -1;
			Token.Parsed _token__anonymous_37 = null;
			_position__anonymous_37=_position;
			_token__anonymous_37=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			parse_method_body();
			if(_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_37)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_37;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_37.addAll(_token);
			}
			_token=_token__anonymous_37;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_37=_position;
				_token__anonymous_37=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_37)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_37;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_37.addAll(_token);
				}
				_token=_token__anonymous_37;
			}
		}
		public void parse_inner(){
			int _position_inner = -1;
			Token.Parsed _token_inner = null;
			_position_inner=_position;
			_token_inner=_token;
			_token=new Tokens.Rule.InnerToken();
			parse__anonymous_10();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(inner)");
					_furthestPosition=_position;
				}
				_position=_position_inner;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_inner.add(_position_inner,_token);
			}
			_token=_token_inner;
		}
		public void parse__anonymous_36(){
			int _position__anonymous_36 = -1;
			Token.Parsed _token__anonymous_36 = null;
			_position__anonymous_36=_position;
			_token__anonymous_36=_token;
			_token=new Tokens.Name.ExceptionToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_36)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_36;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_36.add(_position__anonymous_36,_token);
			}
			_token=_token__anonymous_36;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_36=_position;
				_token__anonymous_36=_token;
				_token=new Tokens.Name.ExceptionToken();
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_36)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_36;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_36.add(_position__anonymous_36,_token);
				}
				_token=_token__anonymous_36;
			}
		}
		public void parse__anonymous_39(){
			int _position__anonymous_39 = -1;
			Token.Parsed _token__anonymous_39 = null;
			_position__anonymous_39=_position;
			_token__anonymous_39=_token;
			_token=new Token.Parsed("$ANON");
			int _state_46 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\");
					_furthestPosition=_position;
				}
			}
			if(_state_46==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_39)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_39;
			}
			else{
				parse_OPERATOR();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_39)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_39;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_39.addAll(_token);
			}
			_token=_token__anonymous_39;
		}
		public void parse_method_argument(){
			int _position_method_argument = -1;
			Token.Parsed _token_method_argument = null;
			_position_method_argument=_position;
			_token_method_argument=_token;
			_token=new Tokens.Rule.MethodArgumentToken();
			int _position_class_declaration = _position;
			if(_state==SUCCESS&&!_recursion_protection_class_declaration_41.contains(_position)){
				_recursion_protection_class_declaration_41.add(_position);
				parse_class_declaration();
				_recursion_protection_class_declaration_41.remove(_position_class_declaration);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
					_furthestPosition=_position;
				}
				_position=_position_method_argument;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_method_argument.add(_position_method_argument,_token);
			}
			_token=_token_method_argument;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_method_argument=_position;
				_token_method_argument=_token;
				_token=new Tokens.Rule.MethodArgumentToken();
				parse_method_declaration();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
						_furthestPosition=_position;
					}
					_position=_position_method_argument;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_method_argument.add(_position_method_argument,_token);
				}
				_token=_token_method_argument;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_method_argument=_position;
					_token_method_argument=_token;
					_token=new Tokens.Rule.MethodArgumentToken();
					parse_variable_declaration();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
							_furthestPosition=_position;
						}
						_position=_position_method_argument;
					}
					else{
						if(_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!=';'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
								_furthestPosition=_position;
							}
							_position=_position_method_argument;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token_method_argument.add(_position_method_argument,_token);
					}
					_token=_token_method_argument;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_method_argument=_position;
						_token_method_argument=_token;
						_token=new Tokens.Rule.MethodArgumentToken();
						parse_as_statement();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
								_furthestPosition=_position;
							}
							_position=_position_method_argument;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_method_argument.add(_position_method_argument,_token);
						}
						_token=_token_method_argument;
						if(_state==FAILED){
							_state=SUCCESS;
							_position_method_argument=_position;
							_token_method_argument=_token;
							_token=new Tokens.Rule.MethodArgumentToken();
							int _position_body_statement = _position;
							if(_state==SUCCESS&&!_recursion_protection_body_statement_42.contains(_position)){
								_recursion_protection_body_statement_42.add(_position);
								parse_body_statement();
								_recursion_protection_body_statement_42.remove(_position_body_statement);
							}
							else{
								_state=FAILED;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
									_furthestPosition=_position;
								}
								_position=_position_method_argument;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_method_argument.add(_position_method_argument,_token);
							}
							_token=_token_method_argument;
							if(_state==FAILED){
								_state=SUCCESS;
								_position_method_argument=_position;
								_token_method_argument=_token;
								_token=new Tokens.Rule.MethodArgumentToken();
								Token.Parsed _tokenmethod_body = _token;
								_token=new Tokens.Name.BodyEntriesToken();
								int _position_method_body = _position;
								if(_state==SUCCESS&&!_recursion_protection_method_body_43.contains(_position)){
									_recursion_protection_method_body_43.add(_position);
									parse_method_body();
									_recursion_protection_method_body_43.remove(_position_method_body);
								}
								else{
									_state=FAILED;
								}
								if(_state==SUCCESS){
									_tokenmethod_body.add(_position_method_body,_token);
								}
								_token=_tokenmethod_body;
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
										_furthestPosition=_position;
									}
									_position=_position_method_argument;
								}
								else{
								}
								if(_state==SUCCESS){
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
			if(_position<_inputLength){
				if(_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F'||_inputArray[_position]=='G'||_inputArray[_position]=='H'||_inputArray[_position]=='I'||_inputArray[_position]=='J'||_inputArray[_position]=='K'||_inputArray[_position]=='L'||_inputArray[_position]=='M'||_inputArray[_position]=='N'||_inputArray[_position]=='O'||_inputArray[_position]=='P'||_inputArray[_position]=='Q'||_inputArray[_position]=='R'||_inputArray[_position]=='S'||_inputArray[_position]=='T'||_inputArray[_position]=='U'||_inputArray[_position]=='V'||_inputArray[_position]=='W'||_inputArray[_position]=='X'||_inputArray[_position]=='Y'||_inputArray[_position]=='Z'||_inputArray[_position]=='_'){
					++_position;
				}
				else{
					_state=FAILED;
				}
			}
			else{
				_state=FAILED;
			}
			while(_position<_inputLength){
				if(_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F'||_inputArray[_position]=='G'||_inputArray[_position]=='H'||_inputArray[_position]=='I'||_inputArray[_position]=='J'||_inputArray[_position]=='K'||_inputArray[_position]=='L'||_inputArray[_position]=='M'||_inputArray[_position]=='N'||_inputArray[_position]=='O'||_inputArray[_position]=='P'||_inputArray[_position]=='Q'||_inputArray[_position]=='R'||_inputArray[_position]=='S'||_inputArray[_position]=='T'||_inputArray[_position]=='U'||_inputArray[_position]=='V'||_inputArray[_position]=='W'||_inputArray[_position]=='X'||_inputArray[_position]=='Y'||_inputArray[_position]=='Z'||_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9'||_inputArray[_position]=='_'){
					++_position;
				}
				else{
					break;
				}
			}
			if(_state==SUCCESS){
				_token.setValue(_input.substring(_position_regex_1,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z_][a-zA-Z0-9_]*");
					_furthestPosition=_position;
				}
				_position=_position_regex_1;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"NAME(NAME)");
					_furthestPosition=_position;
				}
				_position=_position_NAME;
			}
			else{
			}
			if(_state==SUCCESS){
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
			parse__anonymous_39();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_38)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_38;
			}
			else{
				parse_body_call();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_38)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_38;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_38.addAll(_token);
			}
			_token=_token__anonymous_38;
		}
		public void parse_statement_as_string(){
			int _position_statement_as_string = -1;
			Token.Parsed _token_statement_as_string = null;
			int _length_statement_as_string_brace = _inputLength;
			if(brace_7.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_7.get(_position);
				int _position_statement_as_string_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_statement_as_string=_position;
				_token_statement_as_string=_token;
				_token=new Tokens.Rule.StatementAsStringToken();
				int _position_body_statement = _position;
				if(_state==SUCCESS&&!_recursion_protection_body_statement_3.contains(_position)){
					_recursion_protection_body_statement_3.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_3.remove(_position_body_statement);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_string(statement_as_string)");
						_furthestPosition=_position;
					}
					_position=_position_statement_as_string;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_statement_as_string.add(_position_statement_as_string,_token);
				}
				_token=_token_statement_as_string;
				if(_state==SUCCESS&&brace_7.get(_position_statement_as_string_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_7.get(_position_statement_as_string_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_statement_as_string_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_string(statement_as_string)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_31(){
			int _position__anonymous_31 = -1;
			Token.Parsed _token__anonymous_31 = null;
			_position__anonymous_31=_position;
			_token__anonymous_31=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			parse_method_body();
			if(_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_31)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_31;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_31.addAll(_token);
			}
			_token=_token__anonymous_31;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_31=_position;
				_token__anonymous_31=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_31)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_31;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_31.addAll(_token);
				}
				_token=_token__anonymous_31;
			}
		}
		public void parse__anonymous_30(){
			int _position__anonymous_30 = -1;
			Token.Parsed _token__anonymous_30 = null;
			_position__anonymous_30=_position;
			_token__anonymous_30=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokenmethod_body = _token;
			_token=new Tokens.Name.AsBodyToken();
			int _position_method_body = _position;
			parse_method_body();
			if(_state==SUCCESS){
				_tokenmethod_body.add(_position_method_body,_token);
			}
			_token=_tokenmethod_body;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_30)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_30;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_30.addAll(_token);
			}
			_token=_token__anonymous_30;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_30=_position;
				_token__anonymous_30=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_method();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_30)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_30;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_30.addAll(_token);
				}
				_token=_token__anonymous_30;
			}
		}
		public void parse__anonymous_29(){
			int _position__anonymous_29 = -1;
			Token.Parsed _token__anonymous_29 = null;
			_position__anonymous_29=_position;
			_token__anonymous_29=_token;
			_token=new Tokens.Name.ConditionalToken();
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='f'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_21.__SYNTAX__);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain if");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_29;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_29.add(_position__anonymous_29,_token);
			}
			_token=_token__anonymous_29;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_29=_position;
				_token__anonymous_29=_token;
				_token=new Tokens.Name.ConditionalToken();
				if(_position+4-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='l'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='s'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_22.__SYNTAX__);
					_position=_position+4;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain else");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_29;
				}
				else{
					if(_position+2-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='i'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='f'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_21.__SYNTAX__);
						_position=_position+2;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain if");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_29;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token__anonymous_29.add(_position__anonymous_29,_token);
				}
				_token=_token__anonymous_29;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_29=_position;
					_token__anonymous_29=_token;
					_token=new Tokens.Name.ConditionalToken();
					if(_position+5-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='w'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='h'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='i'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='l'){
							_state=FAILED;
						}
						if(_inputArray[_position+4]!='e'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_23.__SYNTAX__);
						_position=_position+5;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain while");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_29;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_29.add(_position__anonymous_29,_token);
					}
					_token=_token__anonymous_29;
					if(_state==FAILED){
						_state=SUCCESS;
						_position__anonymous_29=_position;
						_token__anonymous_29=_token;
						_token=new Tokens.Name.ConditionalToken();
						if(_position+12-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='s'){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='y'){
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='n'){
								_state=FAILED;
							}
							if(_inputArray[_position+3]!='c'){
								_state=FAILED;
							}
							if(_inputArray[_position+4]!='h'){
								_state=FAILED;
							}
							if(_inputArray[_position+5]!='r'){
								_state=FAILED;
							}
							if(_inputArray[_position+6]!='o'){
								_state=FAILED;
							}
							if(_inputArray[_position+7]!='n'){
								_state=FAILED;
							}
							if(_inputArray[_position+8]!='i'){
								_state=FAILED;
							}
							if(_inputArray[_position+9]!='z'){
								_state=FAILED;
							}
							if(_inputArray[_position+10]!='e'){
								_state=FAILED;
							}
							if(_inputArray[_position+11]!='d'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_24.__SYNTAX__);
							_position=_position+12;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain synchronized");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_29;
						}
						else{
						}
						if(_state==SUCCESS){
							_token__anonymous_29.add(_position__anonymous_29,_token);
						}
						_token=_token__anonymous_29;
						if(_state==FAILED){
							_state=SUCCESS;
							_position__anonymous_29=_position;
							_token__anonymous_29=_token;
							_token=new Tokens.Name.ConditionalToken();
							if(_position+6-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='s'){
									_state=FAILED;
								}
								if(_inputArray[_position+1]!='w'){
									_state=FAILED;
								}
								if(_inputArray[_position+2]!='i'){
									_state=FAILED;
								}
								if(_inputArray[_position+3]!='t'){
									_state=FAILED;
								}
								if(_inputArray[_position+4]!='c'){
									_state=FAILED;
								}
								if(_inputArray[_position+5]!='h'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_25.__SYNTAX__);
								_position=_position+6;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain switch");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_29;
							}
							else{
							}
							if(_state==SUCCESS){
								_token__anonymous_29.add(_position__anonymous_29,_token);
							}
							_token=_token__anonymous_29;
							if(_state==FAILED){
								_state=SUCCESS;
								_position__anonymous_29=_position;
								_token__anonymous_29=_token;
								_token=new Tokens.Name.ConditionalToken();
								if(_position+4-1 >=_inputLength){
									_state=FAILED;
								}
								else{
									if(_inputArray[_position+0]!='c'){
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='a'){
										_state=FAILED;
									}
									if(_inputArray[_position+2]!='s'){
										_state=FAILED;
									}
									if(_inputArray[_position+3]!='e'){
										_state=FAILED;
									}
								}
								if(_state==SUCCESS){
									_token.add(_position,Tokens.Syntax.syntax_26.__SYNTAX__);
									_position=_position+4;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
										++_position;
									}
								}
								else if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain case");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
										_furthestPosition=_position;
									}
									_position=_position__anonymous_29;
								}
								else{
								}
								if(_state==SUCCESS){
									_token__anonymous_29.add(_position__anonymous_29,_token);
								}
								_token=_token__anonymous_29;
							}
						}
					}
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
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
					_furthestPosition=_position;
				}
				_position=_position_type_var_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_type_var_element.addAll(_token);
			}
			_token=_token_type_var_element;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_type_var_element=_position;
				_token_type_var_element=_token;
				_token=new Token.Parsed("$ANON");
				parse_statement_as_quote();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
						_furthestPosition=_position;
					}
					_position=_position_type_var_element;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_type_var_element.addAll(_token);
				}
				_token=_token_type_var_element;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_type_var_element=_position;
					_token_type_var_element=_token;
					_token=new Token.Parsed("$ANON");
					parse_statement_as_string();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
							_furthestPosition=_position;
						}
						_position=_position_type_var_element;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_type_var_element.addAll(_token);
					}
					_token=_token_type_var_element;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_type_var_element=_position;
						_token_type_var_element=_token;
						_token=new Token.Parsed("$ANON");
						parse__anonymous_88();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
								_furthestPosition=_position;
							}
							_position=_position_type_var_element;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_type_var_element.addAll(_token);
						}
						_token=_token_type_var_element;
						if(_state==FAILED){
							_state=SUCCESS;
							_position_type_var_element=_position;
							_token_type_var_element=_token;
							_token=new Token.Parsed("$ANON");
							parse__anonymous_90();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
									_furthestPosition=_position;
								}
								_position=_position_type_var_element;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_type_var_element.addAll(_token);
							}
							_token=_token_type_var_element;
						}
					}
				}
			}
		}
		public void parse_method_arguments(){
			int _position_method_arguments = -1;
			Token.Parsed _token_method_arguments = null;
			int _length_method_arguments_brace = _inputLength;
			if(brace_3.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_3.get(_position);
				int _position_method_arguments_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_method_arguments=_position;
				_token_method_arguments=_token;
				_token=new Tokens.Rule.MethodArgumentsToken();
				int _state_13 = _state;
				parse__anonymous_0();
				if(_state_13==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(method_arguments)");
						_furthestPosition=_position;
					}
					_position=_position_method_arguments;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_method_arguments.add(_position_method_arguments,_token);
				}
				_token=_token_method_arguments;
				if(_state==SUCCESS&&brace_3.get(_position_method_arguments_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_method_arguments_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_method_arguments_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
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
			Token.Parsed _tokentype_var = _token;
			_token=new Tokens.Name.InterfaceNameToken();
			int _position_type_var = _position;
			if(_state==SUCCESS&&!_recursion_protection_type_var_19.contains(_position)){
				_recursion_protection_type_var_19.add(_position);
				parse_type_var();
				_recursion_protection_type_var_19.remove(_position_type_var);
			}
			else{
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_tokentype_var.add(_position_type_var,_token);
			}
			_token=_tokentype_var;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_22)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_22;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_22.addAll(_token);
			}
			_token=_token__anonymous_22;
		}
		public void parse__anonymous_21(){
			int _position__anonymous_21 = -1;
			Token.Parsed _token__anonymous_21 = null;
			_position__anonymous_21=_position;
			_token__anonymous_21=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokentype_var = _token;
			_token=new Tokens.Name.ParentNameToken();
			int _position_type_var = _position;
			if(_state==SUCCESS&&!_recursion_protection_type_var_18.contains(_position)){
				_recursion_protection_type_var_18.add(_position);
				parse_type_var();
				_recursion_protection_type_var_18.remove(_position_type_var);
			}
			else{
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_tokentype_var.add(_position_type_var,_token);
			}
			_token=_tokentype_var;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_21)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_21;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_21.addAll(_token);
			}
			_token=_token__anonymous_21;
		}
		public void parse_variable_assignment(){
			int _position_variable_assignment = -1;
			Token.Parsed _token_variable_assignment = null;
			_position_variable_assignment=_position;
			_token_variable_assignment=_token;
			_token=new Tokens.Rule.VariableAssignmentToken();
			int _state_79 = _state;
			int _position_inner = _position;
			if(_state==SUCCESS&&!_recursion_protection_inner_45.contains(_position)){
				_recursion_protection_inner_45.add(_position);
				parse_inner();
				_recursion_protection_inner_45.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_79==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
					_furthestPosition=_position;
				}
				_position=_position_variable_assignment;
			}
			else{
				parse_name_var();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
						_furthestPosition=_position;
					}
					_position=_position_variable_assignment;
				}
				else{
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='='){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_40.__SYNTAX__);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain =");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
							_furthestPosition=_position;
						}
						_position=_position_variable_assignment;
					}
					else{
						parse_method_argument();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
								_furthestPosition=_position;
							}
							_position=_position_variable_assignment;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_variable_assignment.add(_position_variable_assignment,_token);
			}
			_token=_token_variable_assignment;
		}
		public void parse_name_var(){
			int _position_name_var = -1;
			Token.Parsed _token_name_var = null;
			_position_name_var=_position;
			_token_name_var=_token;
			_token=new Tokens.Rule.NameVarToken();
			parse__anonymous_65();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
					_furthestPosition=_position;
				}
				_position=_position_name_var;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_name_var.add(_position_name_var,_token);
			}
			_token=_token_name_var;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_name_var=_position;
				_token_name_var=_token;
				_token=new Tokens.Rule.NameVarToken();
				parse__anonymous_68();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
						_furthestPosition=_position;
					}
					_position=_position_name_var;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_name_var.add(_position_name_var,_token);
				}
				_token=_token_name_var;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_name_var=_position;
					_token_name_var=_token;
					_token=new Tokens.Rule.NameVarToken();
					parse_name_var_element();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
							_furthestPosition=_position;
						}
						_position=_position_name_var;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_name_var.add(_position_name_var,_token);
					}
					_token=_token_name_var;
				}
			}
		}
		public void parse__anonymous_24(){
			int _position__anonymous_24 = -1;
			Token.Parsed _token__anonymous_24 = null;
			_position__anonymous_24=_position;
			_token__anonymous_24=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+4-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='v'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='o'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='d'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_19.__SYNTAX__);
				_position=_position+4;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain void");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_24)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_24;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_24.addAll(_token);
			}
			_token=_token__anonymous_24;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_24=_position;
				_token__anonymous_24=_token;
				_token=new Token.Parsed("$ANON");
				parse_method_argument();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_24)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_24;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_24.addAll(_token);
				}
				_token=_token__anonymous_24;
			}
		}
		public void parse__anonymous_23(){
			int _position__anonymous_23 = -1;
			Token.Parsed _token__anonymous_23 = null;
			_position__anonymous_23=_position;
			_token__anonymous_23=_token;
			_token=new Tokens.Name.BodyReturnToken();
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='u'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='n'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_18.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain return");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_23)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_23;
			}
			else{
				parse__anonymous_24();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_23)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_23;
				}
				else{
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!=';'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_23)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_23;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_23.add(_position__anonymous_23,_token);
			}
			_token=_token__anonymous_23;
		}
		public void parse__anonymous_26(){
			int _position__anonymous_26 = -1;
			Token.Parsed _token__anonymous_26 = null;
			_position__anonymous_26=_position;
			_token__anonymous_26=_token;
			_token=new Tokens.Name.BodyLineToken();
			int _position_variable_declaration = _position;
			if(_state==SUCCESS&&!_recursion_protection_variable_declaration_26.contains(_position)){
				_recursion_protection_variable_declaration_26.add(_position);
				parse_variable_declaration();
				_recursion_protection_variable_declaration_26.remove(_position_variable_declaration);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_26)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_26;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!=';'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_26)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_26;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_26.add(_position__anonymous_26,_token);
			}
			_token=_token__anonymous_26;
		}
		public void parse__anonymous_25(){
			int _position__anonymous_25 = -1;
			Token.Parsed _token__anonymous_25 = null;
			_position__anonymous_25=_position;
			_token__anonymous_25=_token;
			_token=new Tokens.Name.BodyThrowToken();
			if(_position+5-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='h'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='o'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='w'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_20.__SYNTAX__);
				_position=_position+5;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain throw");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_25)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_25;
			}
			else{
				parse_body_statement();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_25)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_25;
				}
				else{
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!=';'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_25)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_25;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_25.add(_position__anonymous_25,_token);
			}
			_token=_token__anonymous_25;
		}
		public void parse__anonymous_28(){
			int _position__anonymous_28 = -1;
			Token.Parsed _token__anonymous_28 = null;
			_position__anonymous_28=_position;
			_token__anonymous_28=_token;
			_token=new Tokens.Name.BodyLineToken();
			int _position_body_statement = _position;
			if(_state==SUCCESS&&!_recursion_protection_body_statement_27.contains(_position)){
				_recursion_protection_body_statement_27.add(_position);
				parse_body_statement();
				_recursion_protection_body_statement_27.remove(_position_body_statement);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_28)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_28;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!=';'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_28)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_28;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_28.add(_position__anonymous_28,_token);
			}
			_token=_token__anonymous_28;
		}
		public void parse__anonymous_27(){
			int _position__anonymous_27 = -1;
			Token.Parsed _token__anonymous_27 = null;
			_position__anonymous_27=_position;
			_token__anonymous_27=_token;
			_token=new Tokens.Name.BodyLineToken();
			parse_variable_assignment();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_27)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_27;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!=';'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_27)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_27;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_27.add(_position__anonymous_27,_token);
			}
			_token=_token__anonymous_27;
		}
		public void parse__anonymous_20(){
			int _position__anonymous_20 = -1;
			Token.Parsed _token__anonymous_20 = null;
			class_names=class_names.push();
			class_variable_names=class_variable_names.push();
			_position__anonymous_20=_position;
			_token__anonymous_20=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='<'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain <");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_20)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_20;
			}
			else{
				parse_NAME();
				if(_state==SUCCESS){
					String _value = _token.getLastValue();
					if(_value!=null){
						class_variable_names.add(_value);
					}
					_token.add(_position,new Tokens.Name.TemplateTypeNameToken(_value));
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_20)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_20;
				}
				else{
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='>'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain >");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_20)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_20;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_20.addAll(_token);
			}
			_token=_token__anonymous_20;
			if(_state==FAILED){
				class_names.reject();
				class_variable_names.reject();
			}
			else if(_state==SUCCESS){
				class_names.accept();
				class_variable_names.accept();
			}
			class_names=class_names.pop();
			class_variable_names=class_variable_names.pop();
		}
		public void parse_method_parameters(){
			int _position_method_parameters = -1;
			Token.Parsed _token_method_parameters = null;
			_position_method_parameters=_position;
			_token_method_parameters=_token;
			_token=new Tokens.Rule.MethodParametersToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='('){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_35.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain (");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
					_furthestPosition=_position;
				}
				_position=_position_method_parameters;
			}
			else{
				int _state_69 = _state;
				parse_variable_declaration();
				if(_state_69==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
						_furthestPosition=_position;
					}
					_position=_position_method_parameters;
				}
				else{
					int _state_70 = _state;
					while(_position<_inputLength){
						parse__anonymous_55();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_70==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
							_furthestPosition=_position;
						}
						_position=_position_method_parameters;
					}
					else{
						if(_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!=')'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_36.__SYNTAX__);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain )");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
								_furthestPosition=_position;
							}
							_position=_position_method_parameters;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_method_parameters.add(_position_method_parameters,_token);
			}
			_token=_token_method_parameters;
		}
		public void parse_packageName(){
			int _position_packageName = -1;
			Token.Parsed _token_packageName = null;
			_position_packageName=_position;
			_token_packageName=_token;
			_token=new Tokens.Rule.PackageNameToken();
			int _state_21 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\");
					_furthestPosition=_position;
				}
			}
			if(_state_21==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
					_furthestPosition=_position;
				}
				_position=_position_packageName;
			}
			else{
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
						_furthestPosition=_position;
					}
					_position=_position_packageName;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token_packageName.add(_position_packageName,_token);
			}
			_token=_token_packageName;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_packageName=_position;
				_token_packageName=_token;
				_token=new Tokens.Rule.PackageNameToken();
				parse_quote();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
						_furthestPosition=_position;
					}
					_position=_position_packageName;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_packageName.add(_position_packageName,_token);
				}
				_token=_token_packageName;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_packageName=_position;
					_token_packageName=_token;
					_token=new Tokens.Rule.PackageNameToken();
					parse_statement_as_string();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
							_furthestPosition=_position;
						}
						_position=_position_packageName;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_packageName.add(_position_packageName,_token);
					}
					_token=_token_packageName;
				}
			}
		}
		public void parse__anonymous_19(){
			int _position__anonymous_19 = -1;
			Token.Parsed _token__anonymous_19 = null;
			_position__anonymous_19=_position;
			_token__anonymous_19=_token;
			_token=new Tokens.Name.ClassNameToken();
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_19)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_19;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_19.add(_position__anonymous_19,_token);
			}
			_token=_token__anonymous_19;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_19=_position;
				_token__anonymous_19=_token;
				_token=new Tokens.Name.ClassNameToken();
				if(_pass==FIRST_PASS){
					String _result = _preparsed_NAME.get(_position);
					if(_result==null){
						_state=FAILED;
					}
					else{
						Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
						_token.add(_position,_first_pass_token);
						_position+=_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else if(_pass==SECOND_PASS){
					_list_name_result=_preparsed_NAME.get(_position);
					if(_list_name_result!=null&&class_variable_names.contains(_list_name_result)){
						if(_position+_list_name_result.length()<_inputLength){
							int _next_char = _inputArray[_position+_list_name_result.length()];
							if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
							_position+=_list_name_result.length();
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
							_furthestPosition=_position;
						}
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_19)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_19;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_19.add(_position__anonymous_19,_token);
				}
				_token=_token__anonymous_19;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_19=_position;
					_token__anonymous_19=_token;
					_token=new Tokens.Name.ClassNameToken();
					parse_NAME();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_19)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_19;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_19.add(_position__anonymous_19,_token);
					}
					_token=_token__anonymous_19;
				}
			}
		}
		public void parse__anonymous_18(){
			int _position__anonymous_18 = -1;
			Token.Parsed _token__anonymous_18 = null;
			_position__anonymous_18=_position;
			_token__anonymous_18=_token;
			_token=new Tokens.Name.ObjectTypeToken();
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='c'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='l'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!=' '){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain class ");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_18)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_18;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_18.add(_position__anonymous_18,_token);
			}
			_token=_token__anonymous_18;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_18=_position;
				_token__anonymous_18=_token;
				_token=new Tokens.Name.ObjectTypeToken();
				if(_position+10-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='n'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='t'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='r'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='f'){
						_state=FAILED;
					}
					if(_inputArray[_position+6]!='a'){
						_state=FAILED;
					}
					if(_inputArray[_position+7]!='c'){
						_state=FAILED;
					}
					if(_inputArray[_position+8]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+9]!=' '){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_15.__SYNTAX__);
					_position=_position+10;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain interface ");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_18)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_18;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_18.add(_position__anonymous_18,_token);
				}
				_token=_token__anonymous_18;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_18=_position;
					_token__anonymous_18=_token;
					_token=new Tokens.Name.ObjectTypeToken();
					if(_position+5-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='e'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='n'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='u'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='m'){
							_state=FAILED;
						}
						if(_inputArray[_position+4]!=' '){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_16.__SYNTAX__);
						_position=_position+5;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain enum ");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_18)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_18;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_18.add(_position__anonymous_18,_token);
					}
					_token=_token__anonymous_18;
				}
			}
		}
		public void parse_comments(){
			int _position_comments = -1;
			Token.Parsed _token_comments = null;
			int _length_comments_brace = _inputLength;
			if(brace_1.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_1.get(_position);
				int _position_comments_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_comments=_position;
				_token_comments=_token;
				_token=new Tokens.Rule.CommentsToken();
				int _position_regex_0 = _position;
				int _multiple_index_1 = 0;
				int _state_1 = _state;
				while(_position<_inputLength){
					if(_position<_inputLength){
						++_position;
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						break;
					}
					else{
						++_multiple_index_1;
					}
				}
				if(_state_1==SUCCESS){
					_state=SUCCESS;
				}
				if(_state==SUCCESS){
					_token.add(_position_regex_0,new Tokens.Plain.Comment(_input.substring(_position_regex_0,_position)));
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".*");
						_furthestPosition=_position;
					}
					_position=_position_regex_0;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"comments(comments)");
						_furthestPosition=_position;
					}
					_position=_position_comments;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_comments.add(_position_comments,_token);
				}
				_token=_token_comments;
				if(_state==SUCCESS&&brace_1.get(_position_comments_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_1.get(_position_comments_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_comments_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"comments(comments)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse_template_parameters(){
			int _position_template_parameters = -1;
			Token.Parsed _token_template_parameters = null;
			int _length_template_parameters_brace = _inputLength;
			if(brace_4.containsKey(_position)){
				if(_pass==SECOND_PASS){
					class_variable_names=class_variable_names.push();
				}
				if(_pass==SECOND_PASS){
					variable_names=variable_names.push();
				}
				if(_pass==SECOND_PASS){
					_inputLength=brace_4.get(_position);
					int _position_template_parameters_brace = _position;
					_position+=1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
					_position_template_parameters=_position;
					_token_template_parameters=_token;
					_token=new Tokens.Rule.TemplateParametersToken();
					Token.Parsed _tokenall_type_name = _token;
					_token=new Tokens.Name.TemplateParameterToken();
					int _position_all_type_name = _position;
					parse_all_type_name();
					if(_state==SUCCESS){
						_tokenall_type_name.add(_position_all_type_name,_token);
					}
					_token=_tokenall_type_name;
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
							_furthestPosition=_position;
						}
						_position=_position_template_parameters;
					}
					else{
						int _state_15 = _state;
						while(_position<_inputLength){
							parse__anonymous_2();
							if(_state==FAILED){
								break;
							}
						}
						if(_state_15==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
								_furthestPosition=_position;
							}
							_position=_position_template_parameters;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token_template_parameters.add(_position_template_parameters,_token);
					}
					_token=_token_template_parameters;
					if(_state==SUCCESS&&brace_4.get(_position_template_parameters_brace)==_position){
						_position+=1;
					}
					else{
						_state=SUCCESS;
						_result_acceptor.add(_result);
						_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
						_position=brace_4.get(_position_template_parameters_brace)+1;
						_succeedOnEnd=false;
					}
					_inputLength=_length_template_parameters_brace;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
					class_variable_names=class_variable_names.pop();
					variable_names=variable_names.pop();
				}
				else{
					_position=brace_4.get(_position)+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_11(){
			int _position__anonymous_11 = -1;
			Token.Parsed _token__anonymous_11 = null;
			_position__anonymous_11=_position;
			_token__anonymous_11=_token;
			_token=new Tokens.Name.ObjectTypeToken();
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='c'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='l'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!=' '){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain class ");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_11)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_11;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_11.add(_position__anonymous_11,_token);
			}
			_token=_token__anonymous_11;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_11=_position;
				_token__anonymous_11=_token;
				_token=new Tokens.Name.ObjectTypeToken();
				if(_position+10-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='n'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='t'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='r'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='f'){
						_state=FAILED;
					}
					if(_inputArray[_position+6]!='a'){
						_state=FAILED;
					}
					if(_inputArray[_position+7]!='c'){
						_state=FAILED;
					}
					if(_inputArray[_position+8]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+9]!=' '){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_15.__SYNTAX__);
					_position=_position+10;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain interface ");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_11)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_11;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_11.add(_position__anonymous_11,_token);
				}
				_token=_token__anonymous_11;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_11=_position;
					_token__anonymous_11=_token;
					_token=new Tokens.Name.ObjectTypeToken();
					if(_position+5-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='e'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='n'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='u'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='m'){
							_state=FAILED;
						}
						if(_inputArray[_position+4]!=' '){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_16.__SYNTAX__);
						_position=_position+5;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain enum ");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_11)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_11;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_11.add(_position__anonymous_11,_token);
					}
					_token=_token__anonymous_11;
				}
			}
		}
		public void parse__anonymous_10(){
			int _position__anonymous_10 = -1;
			Token.Parsed _token__anonymous_10 = null;
			_position__anonymous_10=_position;
			_token__anonymous_10=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='>'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain >");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(_anonymous_10)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_10;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_10.addAll(_token);
			}
			_token=_token__anonymous_10;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_10=_position;
				_token__anonymous_10=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='n'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='n'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='r'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!=' '){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_12.__SYNTAX__);
					_position=_position+6;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain inner ");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(_anonymous_10)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_10;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_10.addAll(_token);
				}
				_token=_token__anonymous_10;
			}
		}
		public void parse__anonymous_13(){
			int _position__anonymous_13 = -1;
			Token.Parsed _token__anonymous_13 = null;
			_position__anonymous_13=_position;
			_token__anonymous_13=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_13)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_13;
			}
			else{
				parse_packageName();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_13)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_13;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_13.addAll(_token);
			}
			_token=_token__anonymous_13;
		}
		public void parse__anonymous_12(){
			int _position__anonymous_12 = -1;
			Token.Parsed _token__anonymous_12 = null;
			_position__anonymous_12=_position;
			_token__anonymous_12=_token;
			_token=new Token.Parsed("$ANON");
			int _position_packageName = _position;
			if(_state==SUCCESS&&!_recursion_protection_packageName_14.contains(_position)){
				_recursion_protection_packageName_14.add(_position);
				parse_packageName();
				_recursion_protection_packageName_14.remove(_position_packageName);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_12)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_12;
			}
			else{
				int _state_26 = _state;
				while(_position<_inputLength){
					parse__anonymous_13();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_26==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_12)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_12;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_12.addAll(_token);
			}
			_token=_token__anonymous_12;
		}
		public void parse_class_declaration(){
			int _position_class_declaration = -1;
			Token.Parsed _token_class_declaration = null;
			class_names=class_names.push();
			class_variable_names=class_variable_names.push();
			_position_class_declaration=_position;
			_token_class_declaration=_token;
			_token=new Tokens.Rule.ClassDeclarationToken();
			int _state_24 = _state;
			int _position_inner = _position;
			if(_state==SUCCESS&&!_recursion_protection_inner_13.contains(_position)){
				_recursion_protection_inner_13.add(_position);
				parse_inner();
				_recursion_protection_inner_13.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_24==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
					_furthestPosition=_position;
				}
				_position=_position_class_declaration;
			}
			else{
				int _state_25 = _state;
				parse_weak();
				if(_state_25==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_class_declaration;
				}
				else{
					parse__anonymous_11();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_class_declaration;
					}
					else{
						parse__anonymous_12();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_class_declaration;
						}
						else{
							parse__anonymous_14();
							if(_state==SUCCESS){
								String _value = _token.getLastValue();
								if(_value!=null){
									class_names.add(_value);
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_class_declaration;
							}
							else{
								int _state_27 = _state;
								parse__anonymous_15();
								if(_state_27==SUCCESS&&_state==FAILED){
									_state=SUCCESS;
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_class_declaration;
								}
								else{
									int _state_28 = _state;
									parse__anonymous_16();
									if(_state_28==SUCCESS&&_state==FAILED){
										_state=SUCCESS;
									}
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_class_declaration;
									}
									else{
										int _state_29 = _state;
										while(_position<_inputLength){
											parse__anonymous_17();
											if(_state==FAILED){
												break;
											}
										}
										if(_state_29==SUCCESS&&_state==FAILED){
											_state=SUCCESS;
										}
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
												_furthestPosition=_position;
											}
											_position=_position_class_declaration;
										}
										else{
											parse_class_body();
											if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
													_furthestPosition=_position;
												}
												_position=_position_class_declaration;
											}
											else{
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_class_declaration.add(_position_class_declaration,_token);
			}
			_token=_token_class_declaration;
			if(_state==FAILED){
				class_names.reject();
				class_variable_names.reject();
				_state=SUCCESS;
				_position_class_declaration=_position;
				_token_class_declaration=_token;
				_token=new Tokens.Rule.ClassDeclarationToken();
				int _state_30 = _state;
				_position_inner=_position;
				if(_state==SUCCESS&&!_recursion_protection_inner_17.contains(_position)){
					_recursion_protection_inner_17.add(_position);
					parse_inner();
					_recursion_protection_inner_17.remove(_position_inner);
				}
				else{
					_state=FAILED;
				}
				if(_state_30==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_class_declaration;
				}
				else{
					int _state_31 = _state;
					parse_weak();
					if(_state_31==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_class_declaration;
					}
					else{
						parse__anonymous_18();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_class_declaration;
						}
						else{
							parse__anonymous_19();
							if(_state==SUCCESS){
								String _value = _token.getLastValue();
								if(_value!=null){
									class_names.add(_value);
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_class_declaration;
							}
							else{
								int _state_32 = _state;
								parse__anonymous_20();
								if(_state_32==SUCCESS&&_state==FAILED){
									_state=SUCCESS;
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_class_declaration;
								}
								else{
									if(_position+1-1 >=_inputLength){
										_state=FAILED;
									}
									else{
										if(_inputArray[_position+0]!='/'){
											_state=FAILED;
										}
									}
									if(_state==SUCCESS){
										_token.add(_position,Tokens.Syntax.syntax_17.__SYNTAX__);
										_position=_position+1;
										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
											++_position;
										}
									}
									else if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain /");
											_furthestPosition=_position;
										}
									}
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_class_declaration;
									}
									else{
										int _state_33 = _state;
										parse__anonymous_21();
										if(_state_33==SUCCESS&&_state==FAILED){
											_state=SUCCESS;
										}
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
												_furthestPosition=_position;
											}
											_position=_position_class_declaration;
										}
										else{
											if(_position+1-1 >=_inputLength){
												_state=FAILED;
											}
											else{
												if(_inputArray[_position+0]!='/'){
													_state=FAILED;
												}
											}
											if(_state==SUCCESS){
												_token.add(_position,Tokens.Syntax.syntax_17.__SYNTAX__);
												_position=_position+1;
												while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
													++_position;
												}
											}
											else if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain /");
													_furthestPosition=_position;
												}
											}
											if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
													_furthestPosition=_position;
												}
												_position=_position_class_declaration;
											}
											else{
												int _state_34 = _state;
												while(_position<_inputLength){
													parse__anonymous_22();
													if(_state==FAILED){
														break;
													}
												}
												if(_state_34==SUCCESS&&_state==FAILED){
													_state=SUCCESS;
												}
												if(_state==FAILED){
													if(_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
														_furthestPosition=_position;
													}
													_position=_position_class_declaration;
												}
												else{
													parse_class_body();
													if(_state==FAILED){
														if(_position>=_furthestPosition){
															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
															_furthestPosition=_position;
														}
														_position=_position_class_declaration;
													}
													else{
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
				if(_state==SUCCESS){
					_token_class_declaration.add(_position_class_declaration,_token);
				}
				_token=_token_class_declaration;
				if(_state==FAILED){
					class_names.reject();
					class_variable_names.reject();
				}
				else if(_state==SUCCESS){
					class_names.accept();
					class_variable_names.accept();
				}
				class_names=class_names.pop();
				class_variable_names=class_variable_names.pop();
			}
		}
		public void parse__anonymous_15(){
			int _position__anonymous_15 = -1;
			Token.Parsed _token__anonymous_15 = null;
			class_names=class_names.push();
			class_variable_names=class_variable_names.push();
			_position__anonymous_15=_position;
			_token__anonymous_15=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='<'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain <");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_15)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_15;
			}
			else{
				parse_NAME();
				if(_state==SUCCESS){
					String _value = _token.getLastValue();
					if(_value!=null){
						class_variable_names.add(_value);
					}
					_token.add(_position,new Tokens.Name.TemplateTypeNameToken(_value));
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_15)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_15;
				}
				else{
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='>'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain >");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_15)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_15;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_15.addAll(_token);
			}
			_token=_token__anonymous_15;
			if(_state==FAILED){
				class_names.reject();
				class_variable_names.reject();
			}
			else if(_state==SUCCESS){
				class_names.accept();
				class_variable_names.accept();
			}
			class_names=class_names.pop();
			class_variable_names=class_variable_names.pop();
		}
		public void parse__anonymous_14(){
			int _position__anonymous_14 = -1;
			Token.Parsed _token__anonymous_14 = null;
			_position__anonymous_14=_position;
			_token__anonymous_14=_token;
			_token=new Tokens.Name.ClassNameToken();
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_14)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_14;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_14.add(_position__anonymous_14,_token);
			}
			_token=_token__anonymous_14;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_14=_position;
				_token__anonymous_14=_token;
				_token=new Tokens.Name.ClassNameToken();
				if(_pass==FIRST_PASS){
					String _result = _preparsed_NAME.get(_position);
					if(_result==null){
						_state=FAILED;
					}
					else{
						Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
						_token.add(_position,_first_pass_token);
						_position+=_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else if(_pass==SECOND_PASS){
					_list_name_result=_preparsed_NAME.get(_position);
					if(_list_name_result!=null&&class_variable_names.contains(_list_name_result)){
						if(_position+_list_name_result.length()<_inputLength){
							int _next_char = _inputArray[_position+_list_name_result.length()];
							if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
							_position+=_list_name_result.length();
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
							_furthestPosition=_position;
						}
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_14)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_14;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_14.add(_position__anonymous_14,_token);
				}
				_token=_token__anonymous_14;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_14=_position;
					_token__anonymous_14=_token;
					_token=new Tokens.Name.ClassNameToken();
					parse_NAME();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_14)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_14;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_14.add(_position__anonymous_14,_token);
					}
					_token=_token__anonymous_14;
				}
			}
		}
		public void parse_statement_as_char(){
			int _position_statement_as_char = -1;
			Token.Parsed _token_statement_as_char = null;
			_position_statement_as_char=_position;
			_token_statement_as_char=_token;
			_token=new Tokens.Rule.StatementAsCharToken();
			if(_position+5-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='c'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='h'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='\\'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_1.__SYNTAX__);
				_position=_position+5;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain char\\");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_char(statement_as_char)");
					_furthestPosition=_position;
				}
				_position=_position_statement_as_char;
			}
			else{
				int _position_regex_5 = _position;
				if(_position<_inputLength){
					++_position;
				}
				else{
					_state=FAILED;
				}
				if(_state==SUCCESS){
					_token.add(_position_regex_5,new Tokens.Plain.Value(_input.substring(_position_regex_5,_position)));
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
						_furthestPosition=_position;
					}
					_position=_position_regex_5;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_char(statement_as_char)");
						_furthestPosition=_position;
					}
					_position=_position_statement_as_char;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token_statement_as_char.add(_position_statement_as_char,_token);
			}
			_token=_token_statement_as_char;
		}
		public void parse__anonymous_17(){
			int _position__anonymous_17 = -1;
			Token.Parsed _token__anonymous_17 = null;
			_position__anonymous_17=_position;
			_token__anonymous_17=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokentype_var = _token;
			_token=new Tokens.Name.InterfaceNameToken();
			int _position_type_var = _position;
			if(_state==SUCCESS&&!_recursion_protection_type_var_16.contains(_position)){
				_recursion_protection_type_var_16.add(_position);
				parse_type_var();
				_recursion_protection_type_var_16.remove(_position_type_var);
			}
			else{
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_tokentype_var.add(_position_type_var,_token);
			}
			_token=_tokentype_var;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_17)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_17;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_17.addAll(_token);
			}
			_token=_token__anonymous_17;
		}
		public void parse__anonymous_16(){
			int _position__anonymous_16 = -1;
			Token.Parsed _token__anonymous_16 = null;
			_position__anonymous_16=_position;
			_token__anonymous_16=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokentype_var = _token;
			_token=new Tokens.Name.ParentNameToken();
			int _position_type_var = _position;
			if(_state==SUCCESS&&!_recursion_protection_type_var_15.contains(_position)){
				_recursion_protection_type_var_15.add(_position);
				parse_type_var();
				_recursion_protection_type_var_15.remove(_position_type_var);
			}
			else{
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_tokentype_var.add(_position_type_var,_token);
			}
			_token=_tokentype_var;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_16)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_16;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_16.addAll(_token);
			}
			_token=_token__anonymous_16;
		}
		public void parse__anonymous_91(){
			int _position__anonymous_91 = -1;
			Token.Parsed _token__anonymous_91 = null;
			_position__anonymous_91=_position;
			_token__anonymous_91=_token;
			_token=new Token.Parsed("$ANON");
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&class_variable_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_91)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_91;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_91.addAll(_token);
			}
			_token=_token__anonymous_91;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_91=_position;
				_token__anonymous_91=_token;
				_token=new Token.Parsed("$ANON");
				if(_pass==FIRST_PASS){
					String _result = _preparsed_NAME.get(_position);
					if(_result==null){
						_state=FAILED;
					}
					else{
						Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
						_token.add(_position,_first_pass_token);
						_position+=_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else if(_pass==SECOND_PASS){
					_list_name_result=_preparsed_NAME.get(_position);
					if(_list_name_result!=null&&class_names.contains(_list_name_result)){
						if(_position+_list_name_result.length()<_inputLength){
							int _next_char = _inputArray[_position+_list_name_result.length()];
							if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,new Tokens.Name.ClassNamesToken(_list_name_result));
							_position+=_list_name_result.length();
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_names");
							_furthestPosition=_position;
						}
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_91)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_91;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_91.addAll(_token);
				}
				_token=_token__anonymous_91;
			}
		}
		public void parse__anonymous_90(){
			int _position__anonymous_90 = -1;
			Token.Parsed _token__anonymous_90 = null;
			_position__anonymous_90=_position;
			_token__anonymous_90=_token;
			_token=new Tokens.Name.ClassToken();
			int _state_104 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_104==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_90)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_90;
			}
			else{
				parse__anonymous_91();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_90)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_90;
				}
				else{
					int _state_105 = _state;
					parse_template_parameters();
					if(_state_105==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_90)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_90;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_90.add(_position__anonymous_90,_token);
			}
			_token=_token__anonymous_90;
		}
		public void parse_statement_as_method(){
			int _position_statement_as_method = -1;
			Token.Parsed _token_statement_as_method = null;
			int _length_statement_as_method_brace = _inputLength;
			if(brace_8.containsKey(_position)){
				class_variable_names=class_variable_names.push();
				variable_names=variable_names.push();
				_inputLength=brace_8.get(_position);
				int _position_statement_as_method_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				_position_statement_as_method=_position;
				_token_statement_as_method=_token;
				_token=new Tokens.Rule.StatementAsMethodToken();
				int _position_body_statement = _position;
				if(_state==SUCCESS&&!_recursion_protection_body_statement_4.contains(_position)){
					_recursion_protection_body_statement_4.add(_position);
					parse_body_statement();
					_recursion_protection_body_statement_4.remove(_position_body_statement);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_method(statement_as_method)");
						_furthestPosition=_position;
					}
					_position=_position_statement_as_method;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_statement_as_method.add(_position_statement_as_method,_token);
				}
				_token=_token_statement_as_method;
				if(_state==SUCCESS&&brace_8.get(_position_statement_as_method_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_8.get(_position_statement_as_method_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_statement_as_method_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_method(statement_as_method)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse_method_body(){
			int _position_method_body = -1;
			Token.Parsed _token_method_body = null;
			int _length_method_body_brace = _inputLength;
			if(brace_2.containsKey(_position)){
				if(_pass==SECOND_PASS){
					class_variable_names=class_variable_names.push();
				}
				if(_pass==SECOND_PASS){
					variable_names=variable_names.push();
				}
				if(_pass==SECOND_PASS){
					_inputLength=brace_2.get(_position);
					int _position_method_body_brace = _position;
					_position+=1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
					_position_method_body=_position;
					_token_method_body=_token;
					_token=new Token.Parsed("$ANON");
					int _state_12 = _state;
					while(_position<_inputLength){
						int _position_body_element = _position;
						if(_state==SUCCESS&&!_recursion_protection_body_element_0.contains(_position)){
							_recursion_protection_body_element_0.add(_position);
							parse_body_element();
							_recursion_protection_body_element_0.remove(_position_body_element);
						}
						else{
							_state=FAILED;
						}
						if(_state==FAILED){
							break;
						}
					}
					if(_state_12==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_body(method_body)");
							_furthestPosition=_position;
						}
						_position=_position_method_body;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_method_body.addAll(_token);
					}
					_token=_token_method_body;
					if(_state==SUCCESS&&brace_2.get(_position_method_body_brace)==_position){
						_position+=1;
					}
					else{
						_state=SUCCESS;
						_result_acceptor.add(_result);
						_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
						_position=brace_2.get(_position_method_body_brace)+1;
						_succeedOnEnd=false;
					}
					_inputLength=_length_method_body_brace;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
					class_variable_names=class_variable_names.pop();
					variable_names=variable_names.pop();
				}
				else{
					_position=brace_2.get(_position)+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_body(method_body)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse_body_element(){
			int _position_body_element = -1;
			Token.Parsed _token_body_element = null;
			_position_body_element=_position;
			_token_body_element=_token;
			_token=new Tokens.Rule.BodyElementToken();
			parse_comments();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
					_furthestPosition=_position;
				}
				_position=_position_body_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_body_element.add(_position_body_element,_token);
			}
			_token=_token_body_element;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_body_element=_position;
				_token_body_element=_token;
				_token=new Tokens.Rule.BodyElementToken();
				int _state_35 = _state;
				int _position_inner = _position;
				if(_state==SUCCESS&&!_recursion_protection_inner_24.contains(_position)){
					_recursion_protection_inner_24.add(_position);
					parse_inner();
					_recursion_protection_inner_24.remove(_position_inner);
				}
				else{
					_state=FAILED;
				}
				if(_state_35==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
						_furthestPosition=_position;
					}
					_position=_position_body_element;
				}
				else{
					parse__anonymous_23();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
							_furthestPosition=_position;
						}
						_position=_position_body_element;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token_body_element.add(_position_body_element,_token);
				}
				_token=_token_body_element;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_body_element=_position;
					_token_body_element=_token;
					_token=new Tokens.Rule.BodyElementToken();
					int _state_36 = _state;
					_position_inner=_position;
					if(_state==SUCCESS&&!_recursion_protection_inner_25.contains(_position)){
						_recursion_protection_inner_25.add(_position);
						parse_inner();
						_recursion_protection_inner_25.remove(_position_inner);
					}
					else{
						_state=FAILED;
					}
					if(_state_36==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
							_furthestPosition=_position;
						}
						_position=_position_body_element;
					}
					else{
						parse__anonymous_25();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
								_furthestPosition=_position;
							}
							_position=_position_body_element;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token_body_element.add(_position_body_element,_token);
					}
					_token=_token_body_element;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_body_element=_position;
						_token_body_element=_token;
						_token=new Tokens.Rule.BodyElementToken();
						parse_class_declaration();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
								_furthestPosition=_position;
							}
							_position=_position_body_element;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_body_element.add(_position_body_element,_token);
						}
						_token=_token_body_element;
						if(_state==FAILED){
							_state=SUCCESS;
							_position_body_element=_position;
							_token_body_element=_token;
							_token=new Tokens.Rule.BodyElementToken();
							parse__anonymous_26();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
									_furthestPosition=_position;
								}
								_position=_position_body_element;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_body_element.add(_position_body_element,_token);
							}
							_token=_token_body_element;
							if(_state==FAILED){
								_state=SUCCESS;
								_position_body_element=_position;
								_token_body_element=_token;
								_token=new Tokens.Rule.BodyElementToken();
								parse__anonymous_27();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
										_furthestPosition=_position;
									}
									_position=_position_body_element;
								}
								else{
								}
								if(_state==SUCCESS){
									_token_body_element.add(_position_body_element,_token);
								}
								_token=_token_body_element;
								if(_state==FAILED){
									_state=SUCCESS;
									_position_body_element=_position;
									_token_body_element=_token;
									_token=new Tokens.Rule.BodyElementToken();
									parse_body_manipulate();
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
											_furthestPosition=_position;
										}
										_position=_position_body_element;
									}
									else{
									}
									if(_state==SUCCESS){
										_token_body_element.add(_position_body_element,_token);
									}
									_token=_token_body_element;
									if(_state==FAILED){
										_state=SUCCESS;
										_position_body_element=_position;
										_token_body_element=_token;
										_token=new Tokens.Rule.BodyElementToken();
										parse_body_conditional();
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
												_furthestPosition=_position;
											}
											_position=_position_body_element;
										}
										else{
										}
										if(_state==SUCCESS){
											_token_body_element.add(_position_body_element,_token);
										}
										_token=_token_body_element;
										if(_state==FAILED){
											_state=SUCCESS;
											_position_body_element=_position;
											_token_body_element=_token;
											_token=new Tokens.Rule.BodyElementToken();
											parse__anonymous_28();
											if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
													_furthestPosition=_position;
												}
												_position=_position_body_element;
											}
											else{
											}
											if(_state==SUCCESS){
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
			while(_position<_inputLength){
				parse_base_element();
				if(_state==FAILED){
					break;
				}
			}
			if(_state_0==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base(base)");
					_furthestPosition=_position;
				}
				_position=_position_base;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_base.addAll(_token);
			}
			_token=_token_base;
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
		public static class ImportsFileImport extends Parser.Context{
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
		protected Set<String> additions = new HashSet<String>();
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
		public Set<String> getAdditions(){
			return additions;
		}
		public void setAdditions(Set<String> newAdditions){
			additions = newAdditions;
		}
		public NameList push(){
			NameList result = new NameList(this);
			for(String newEntry:this){
				result.supAdd(newEntry);
			}
			return result;
		}
		public NameList pop(){
			return parent;
		}
		public boolean supAdd(String newAddition){
			return super.add(newAddition);
		}
		public boolean add(String addition){
			if(super.add(addition)){
				additions.add(addition);
				return true;
			}
			else{
				return false;
			}
		}
		public void accept(){
			if(parent!=null){
				for(String newEntry:additions){
					parent.add(newEntry);
				}
			}
			additions.clear();
		}
		public void reject(){
			removeAll(additions);
			if(parent!=null){
				parent.removeAll(additions);
			}
			additions.clear();
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