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
	protected Parser.NameList.Root list_names_root = new Parser.NameList.Root();
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
			return secondResult;
		}
		else{
			System.out.println("First-Pass Failed");
			return firstResult;
		}
	}
	public Parser.Result parseFile(String fileName,int _pass){
		if(_pass==FIRST_PASS){
			Parser.Context context = new Parser.Context(list_names_root);
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
	public Parser.NameList.Root getListNamesRoot(){
		return list_names_root;
	}
	public void setListNamesRoot(Parser.NameList.Root newListNamesRoot){
		list_names_root = newListNamesRoot;
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
		protected Parser.NameList list_names_root = null;
		protected Parser.NameList list_names = null;
		protected Parser.NameList list_names_additions = null;
		protected Map<Integer,Integer> brace_1 = new HashMap<Integer,Integer>();
		protected String _import_FILE_NAME_value = null;
		protected Set<Integer> _recursion_protection_base_0 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_2 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_definition_1 = new HashSet<Integer>();
		protected Map<Integer,Integer> brace_3 = new HashMap<Integer,Integer>();
		protected Set<Integer> _recursion_protection_regex_definition_2 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_3 = new HashSet<Integer>();
		protected Set<Integer> _recursion_protection_NAME_4 = new HashSet<Integer>();
		protected int _readInput_1 = 0;
		protected Parser.NameList list_names_quote = null;
		protected Parser.NameList list_names_braced_definition = null;
		protected Parser.NameList list_names_additions_list = null;
		protected Parser.NameList list_names_single_ignores_character = null;
		protected Parser.NameList list_names_regex = null;
		protected Parser.NameList list_names_regex_group_definition = null;
		protected Parser.NameList list_names_regex_option = null;
		protected Parser.NameList list_names_comments = null;
		public Context(final Parser.NameList list_names_root) {
			if(list_names_root != null){
				this.list_names_root = list_names_root;
			}
			list_names=list_names_root;
		}
		public Context() {
			list_names=list_names_root;
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
			Stack<Integer> brace_open_3 = new Stack<Integer>();
			Stack<Integer> brace_open_2 = new Stack<Integer>();
			Stack<Integer> brace_open_1 = new Stack<Integer>();
			Stack<Integer> brace_open_0 = new Stack<Integer>();
			if(fileNames.add(_fileName)==false){
				return null;
			}
			long startParseTime = System.currentTimeMillis();
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
						if(_readInput=='#'){
							if(!brace_open_1.isEmpty()){
								brace_1.put(brace_open_1.pop(),_position);
							}
						}
						if(_readInput==')'){
							if(!brace_open_2.isEmpty()){
								brace_2.put(brace_open_2.pop(),_position);
							}
						}
						if(_readInput==']'){
							if(!brace_open_3.isEmpty()){
								brace_3.put(brace_open_3.pop(),_position);
							}
						}
						if(_readInput=='#'){
							brace_open_1.push(_position);
						}
						if(_readInput=='('){
							brace_open_2.push(_position);
						}
						if(_readInput=='['){
							brace_open_3.push(_position);
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
				_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName," tail of file could not be parsed"));
				_result_acceptor.setFileName(_fileName);
				_result=_result_acceptor;
				_state=FAILED;
			}
			else if(_state==FAILED){
				_result_acceptor.add(_result);
				_result_acceptor.setFileName(_fileName);
				_result=_result_acceptor;
			}
			long parseTime = System.currentTimeMillis()-startParseTime;
			_result.setParseTime(parseTime);
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
		public Parser.NameList getListNamesRoot(){
			return list_names_root;
		}
		public void setListNamesRoot(Parser.NameList newListNamesRoot){
			list_names_root = newListNamesRoot;
		}
		public Parser.NameList getListNames(){
			return list_names;
		}
		public void setListNames(Parser.NameList newListNames){
			list_names = newListNames;
		}
		public Parser.NameList getListNamesAdditions(){
			return list_names_additions;
		}
		public void setListNamesAdditions(Parser.NameList newListNamesAdditions){
			list_names_additions = newListNamesAdditions;
		}
		public Map<Integer,Integer> getBrace1(){
			return brace_1;
		}
		public void setBrace1(Map<Integer,Integer> newBrace1){
			brace_1 = newBrace1;
		}
		public String get_importFILENAMEValue(){
			return _import_FILE_NAME_value;
		}
		public void set_importFILENAMEValue(String new_importFILENAMEValue){
			_import_FILE_NAME_value = new_importFILENAMEValue;
		}
		public Set<Integer> get_recursionProtectionBase0(){
			return _recursion_protection_base_0;
		}
		public void set_recursionProtectionBase0(Set<Integer> new_recursionProtectionBase0){
			_recursion_protection_base_0 = new_recursionProtectionBase0;
		}
		public Map<Integer,Integer> getBrace2(){
			return brace_2;
		}
		public void setBrace2(Map<Integer,Integer> newBrace2){
			brace_2 = newBrace2;
		}
		public Set<Integer> get_recursionProtectionDefinition1(){
			return _recursion_protection_definition_1;
		}
		public void set_recursionProtectionDefinition1(Set<Integer> new_recursionProtectionDefinition1){
			_recursion_protection_definition_1 = new_recursionProtectionDefinition1;
		}
		public Map<Integer,Integer> getBrace3(){
			return brace_3;
		}
		public void setBrace3(Map<Integer,Integer> newBrace3){
			brace_3 = newBrace3;
		}
		public Set<Integer> get_recursionProtectionRegexDefinition2(){
			return _recursion_protection_regex_definition_2;
		}
		public void set_recursionProtectionRegexDefinition2(Set<Integer> new_recursionProtectionRegexDefinition2){
			_recursion_protection_regex_definition_2 = new_recursionProtectionRegexDefinition2;
		}
		public Set<Integer> get_recursionProtectionNAME3(){
			return _recursion_protection_NAME_3;
		}
		public void set_recursionProtectionNAME3(Set<Integer> new_recursionProtectionNAME3){
			_recursion_protection_NAME_3 = new_recursionProtectionNAME3;
		}
		public Set<Integer> get_recursionProtectionNAME4(){
			return _recursion_protection_NAME_4;
		}
		public void set_recursionProtectionNAME4(Set<Integer> new_recursionProtectionNAME4){
			_recursion_protection_NAME_4 = new_recursionProtectionNAME4;
		}
		public int get_readInput1(){
			return _readInput_1;
		}
		public void set_readInput1(int new_readInput1){
			_readInput_1 = new_readInput1;
		}
		public void parse_imports__file_import(){
			int _position_imports__file_import = -1;
			Token.Parsed _token_imports__file_import = null;
			_position_imports__file_import=_position;
			_token_imports__file_import=_token;
			_token=new Token.Parsed("$ANON");
			int _position_base = _position;
			if(_state==SUCCESS&&!_recursion_protection_base_0.contains(_position)){
				_recursion_protection_base_0.add(_position);
				parse_base();
				_recursion_protection_base_0.remove(_position_base);
			}
			else{
				_state=FAILED;
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
		public void parse_FILE_NAME(){
			int _position_FILE_NAME = -1;
			Token.Parsed _token_FILE_NAME = null;
			_position_FILE_NAME=_position;
			_token_FILE_NAME=_token;
			_token=new Tokens.Rule.FILENAMEToken();
			int _position_regex_1 = _position;
			int _multiple_index_3 = 0;
			while(_position<_inputLength){
				if(_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'){
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
				_token.setValue(_input.substring(_position_regex_1,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[\\\\s]+");
					_furthestPosition=_position;
				}
				_position=_position_regex_1;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"FILE_NAME(FILE_NAME)");
					_furthestPosition=_position;
				}
				_position=_position_FILE_NAME;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_FILE_NAME.add(_position_FILE_NAME,_token);
			}
			_token=_token_FILE_NAME;
		}
		public void parse__anonymous_6(){
			int _position__anonymous_6 = -1;
			Token.Parsed _token__anonymous_6 = null;
			_position__anonymous_6=_position;
			_token__anonymous_6=_token;
			_token=new Token.Parsed("$ANON");
			parse_quote();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_6)");
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
				Token.Parsed _tokenNAME = _token;
				_token=new Tokens.Name.RuleNameToken();
				int _position_NAME = _position;
				parse_NAME();
				if(_state==SUCCESS){
					_tokenNAME.add(_position_NAME,_token);
				}
				_token=_tokenNAME;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_6)");
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
		public void parse_rule_params(){
			int _position_rule_params = -1;
			Token.Parsed _token_rule_params = null;
			_position_rule_params=_position;
			_token_rule_params=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='l'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='n'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='t'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_7.SILENT);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain silent");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
					_furthestPosition=_position;
				}
				_position=_position_rule_params;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_rule_params.addAll(_token);
			}
			_token=_token_rule_params;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_rule_params=_position;
				_token_rule_params=_token;
				_token=new Token.Parsed("$ANON");
				int _state_18 = _state;
				parse__anonymous_3();
				if(_state_18==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
						_furthestPosition=_position;
					}
					_position=_position_rule_params;
				}
				else{
					if(_position+6-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='B'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='r'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='a'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='c'){
							_state=FAILED;
						}
						if(_inputArray[_position+4]!='e'){
							_state=FAILED;
						}
						if(_inputArray[_position+5]!='d'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
						_position=_position+6;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Braced");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
							_furthestPosition=_position;
						}
						_position=_position_rule_params;
					}
					else{
						parse__anonymous_4();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
								_furthestPosition=_position;
							}
							_position=_position_rule_params;
						}
						else{
						}
					}
				}
				if(_state==SUCCESS){
					_token_rule_params.addAll(_token);
				}
				_token=_token_rule_params;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_rule_params=_position;
					_token_rule_params=_token;
					_token=new Token.Parsed("$ANON");
					if(_position+7-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='I'){
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
						if(_inputArray[_position+6]!='s'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
						_position=_position+7;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Imports");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
							_furthestPosition=_position;
						}
						_position=_position_rule_params;
					}
					else{
						parse__anonymous_5();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
								_furthestPosition=_position;
							}
							_position=_position_rule_params;
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
								_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
								_position=_position+1;
								while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
									_furthestPosition=_position;
								}
								_position=_position_rule_params;
							}
							else{
								parse__anonymous_7();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
										_furthestPosition=_position;
									}
									_position=_position_rule_params;
								}
								else{
								}
							}
						}
					}
					if(_state==SUCCESS){
						_token_rule_params.addAll(_token);
					}
					_token=_token_rule_params;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_rule_params=_position;
						_token_rule_params=_token;
						_token=new Token.Parsed("$ANON");
						if(_position+6-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='I'){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='g'){
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='n'){
								_state=FAILED;
							}
							if(_inputArray[_position+3]!='o'){
								_state=FAILED;
							}
							if(_inputArray[_position+4]!='r'){
								_state=FAILED;
							}
							if(_inputArray[_position+5]!='e'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_12.__SYNTAX__);
							_position=_position+6;
							while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Ignore");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
								_furthestPosition=_position;
							}
							_position=_position_rule_params;
						}
						else{
							parse__anonymous_8();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
									_furthestPosition=_position;
								}
								_position=_position_rule_params;
							}
							else{
							}
						}
						if(_state==SUCCESS){
							_token_rule_params.addAll(_token);
						}
						_token=_token_rule_params;
					}
				}
			}
		}
		public void parse__anonymous_5(){
			int _position__anonymous_5 = -1;
			Token.Parsed _token__anonymous_5 = null;
			_position__anonymous_5=_position;
			_token__anonymous_5=_token;
			_token=new Tokens.Name.ImportParameterToken();
			int _state_21 = _state;
			boolean _iteration_achieved_21 = false;
			while(_position<_inputLength){
				parse__anonymous_6();
				if(_state==FAILED){
					break;
				}
				else{
					_iteration_achieved_21=true;
				}
			}
			if(_iteration_achieved_21==false){
				_state=FAILED;
			}
			else if(_state_21==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_5)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_5;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_5.add(_position__anonymous_5,_token);
			}
			_token=_token__anonymous_5;
		}
		public void parse_ignores(){
			int _position_ignores = -1;
			Token.Parsed _token_ignores = null;
			_position_ignores=_position;
			_token_ignores=_token;
			_token=new Tokens.Rule.IgnoresToken();
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='g'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='n'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='o'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='e'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ignore");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(ignores)");
					_furthestPosition=_position;
				}
				_position=_position_ignores;
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
					_token.add(_position,Tokens.Syntax.syntax_1.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(ignores)");
						_furthestPosition=_position;
					}
					_position=_position_ignores;
				}
				else{
					int _state_2 = _state;
					boolean _iteration_achieved_2 = false;
					while(_position<_inputLength){
						parse__anonymous_0();
						if(_state==FAILED){
							break;
						}
						else{
							_iteration_achieved_2=true;
						}
					}
					if(_iteration_achieved_2==false){
						_state=FAILED;
					}
					else if(_state_2==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(ignores)");
							_furthestPosition=_position;
						}
						_position=_position_ignores;
					}
					else{
						if(_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(ignores)");
								_furthestPosition=_position;
							}
							_position=_position_ignores;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_ignores.add(_position_ignores,_token);
			}
			_token=_token_ignores;
		}
		public void parse__anonymous_8(){
			int _position__anonymous_8 = -1;
			Token.Parsed _token__anonymous_8 = null;
			_position__anonymous_8=_position;
			_token__anonymous_8=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+4-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='N'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='o'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='n'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='e'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_13.ignores_none);
				_position=_position+4;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain None");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_8)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_8;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_8.addAll(_token);
			}
			_token=_token__anonymous_8;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_8=_position;
				_token__anonymous_8=_token;
				_token=new Token.Parsed("$ANON");
				int _state_23 = _state;
				boolean _iteration_achieved_23 = false;
				while(_position<_inputLength){
					parse__anonymous_9();
					if(_state==FAILED){
						break;
					}
					else{
						_iteration_achieved_23=true;
					}
				}
				if(_iteration_achieved_23==false){
					_state=FAILED;
				}
				else if(_state_23==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_8)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_8;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_8.addAll(_token);
				}
				_token=_token__anonymous_8;
			}
		}
		public Parser.NameList getListNamesQuote(){
			return list_names_quote;
		}
		public void setListNamesQuote(Parser.NameList newListNamesQuote){
			list_names_quote = newListNamesQuote;
		}
		public void parse_quote(){
			int _position_quote = -1;
			Token.Parsed _token_quote = null;
			int _length_quote_brace = _inputLength;
			if(brace_0.containsKey(_position)){
				list_names_quote=list_names;
				list_names=new Parser.NameList.Child(list_names);
				_inputLength=brace_0.get(_position);
				int _position_quote_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false)){
					++_position;
				}
				_position_quote=_position;
				_token_quote=_token;
				_token=new Tokens.Rule.QuoteToken();
				int _position_regex_5 = _position;
				int _multiple_index_12 = 0;
				int _state_12 = _state;
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
						++_multiple_index_12;
					}
				}
				if(_state_12==SUCCESS){
					_state=SUCCESS;
				}
				if(_state==SUCCESS){
					_token.setValue(_input.substring(_position_regex_5,_position));
					while(_position<_inputLength&&(false)){
						++_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".*");
						_furthestPosition=_position;
					}
					_position=_position_regex_5;
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
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				list_names=list_names_quote;
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"quote(quote)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_7(){
			int _position__anonymous_7 = -1;
			Token.Parsed _token__anonymous_7 = null;
			_position__anonymous_7=_position;
			_token__anonymous_7=_token;
			_token=new Tokens.Name.ImportDefinitionToken();
			int _state_22 = _state;
			boolean _iteration_achieved_22 = false;
			while(_position<_inputLength){
				Token.Parsed _tokenelement = _token;
				_token=new Tokens.Name.ChainToken();
				int _position_element = _position;
				parse_element();
				if(_state==SUCCESS){
					_tokenelement.add(_position_element,_token);
				}
				_token=_tokenelement;
				if(_state==FAILED){
					break;
				}
				else{
					_iteration_achieved_22=true;
				}
			}
			if(_iteration_achieved_22==false){
				_state=FAILED;
			}
			else if(_state_22==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_7)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_7;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_7.add(_position__anonymous_7,_token);
			}
			_token=_token__anonymous_7;
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
				_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_parameters(_anonymous_2)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_2;
			}
			else{
				parse_rule_params();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_parameters(_anonymous_2)");
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
		public void parse__anonymous_1(){
			int _position__anonymous_1 = -1;
			Token.Parsed _token__anonymous_1 = null;
			_position__anonymous_1=_position;
			_token__anonymous_1=_token;
			_token=new Tokens.Name.RangeToken();
			int _position_regex_7 = _position;
			if(_position<_inputLength){
				++_position;
			}
			else{
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_token.add(_position_regex_7,new Tokens.Plain.Left(_input.substring(_position_regex_7,_position)));
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
					_furthestPosition=_position;
				}
				_position=_position_regex_7;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(_anonymous_1)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_1;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='-'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain -");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(_anonymous_1)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_1;
				}
				else{
					int _position_regex_8 = _position;
					if(_position<_inputLength){
						++_position;
					}
					else{
						_state=FAILED;
					}
					if(_state==SUCCESS){
						_token.add(_position_regex_8,new Tokens.Plain.Right(_input.substring(_position_regex_8,_position)));
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
							++_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
							_furthestPosition=_position;
						}
						_position=_position_regex_8;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(_anonymous_1)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_1;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_1.add(_position__anonymous_1,_token);
			}
			_token=_token__anonymous_1;
		}
		public void parse__anonymous_4(){
			int _position__anonymous_4 = -1;
			Token.Parsed _token__anonymous_4 = null;
			_position__anonymous_4=_position;
			_token__anonymous_4=_token;
			_token=new Tokens.Name.BracedParametersToken();
			int _position_regex_10 = _position;
			int _multiple_index_19 = 0;
			while(_position<_inputLength){
				if(_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'){
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
				_token.add(_position_regex_10,new Tokens.Plain.Left(_input.substring(_position_regex_10,_position)));
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[\\\\s]+");
					_furthestPosition=_position;
				}
				_position=_position_regex_10;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_4)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_4;
			}
			else{
				int _position_regex_11 = _position;
				int _multiple_index_20 = 0;
				while(_position<_inputLength){
					if(_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'){
						++_position;
						++_multiple_index_20;
					}
					else{
						break;
					}
				}
				if(_multiple_index_20==0 ){
					_state=FAILED;
				}
				if(_state==SUCCESS){
					_token.add(_position_regex_11,new Tokens.Plain.Right(_input.substring(_position_regex_11,_position)));
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[\\\\s]+");
						_furthestPosition=_position;
					}
					_position=_position_regex_11;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_4)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_4;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_4.add(_position__anonymous_4,_token);
			}
			_token=_token__anonymous_4;
		}
		public void parse__anonymous_3(){
			int _position__anonymous_3 = -1;
			Token.Parsed _token__anonymous_3 = null;
			_position__anonymous_3=_position;
			_token__anonymous_3=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='@'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_8.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_3)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_3;
			}
			else{
				Token.Parsed _tokenNUMBER = _token;
				_token=new Tokens.Name.PassConstraintToken();
				int _position_NUMBER = _position;
				parse_NUMBER();
				if(_state==SUCCESS){
					_tokenNUMBER.add(_position_NUMBER,_token);
				}
				_token=_tokenNUMBER;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_3)");
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
			int _state_24 = _state;
			boolean _iteration_achieved_24 = false;
			while(_position<_inputLength){
				parse__anonymous_10();
				if(_state==FAILED){
					break;
				}
				else{
					_iteration_achieved_24=true;
				}
			}
			if(_iteration_achieved_24==false){
				_state=FAILED;
			}
			else if(_state_24==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
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
		}
		public void parse_regex_special(){
			int _position_regex_special = -1;
			Token.Parsed _token_regex_special = null;
			_position_regex_special=_position;
			_token_regex_special=_token;
			_token=new Tokens.Rule.RegexSpecialToken();
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='d'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_24.number);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\d");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
					_furthestPosition=_position;
				}
				_position=_position_regex_special;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_regex_special.add(_position_regex_special,_token);
			}
			_token=_token_regex_special;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_regex_special=_position;
				_token_regex_special=_token;
				_token=new Tokens.Rule.RegexSpecialToken();
				if(_position+2-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='\\'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='.'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_25.dot);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\.");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
						_furthestPosition=_position;
					}
					_position=_position_regex_special;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_regex_special.add(_position_regex_special,_token);
				}
				_token=_token_regex_special;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_regex_special=_position;
					_token_regex_special=_token;
					_token=new Tokens.Rule.RegexSpecialToken();
					if(_position+2-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='\\'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='s'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_26.whitespace);
						_position=_position+2;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\s");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
							_furthestPosition=_position;
						}
						_position=_position_regex_special;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_regex_special.add(_position_regex_special,_token);
					}
					_token=_token_regex_special;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_regex_special=_position;
						_token_regex_special=_token;
						_token=new Tokens.Rule.RegexSpecialToken();
						int _position_regex_13 = _position;
						if(_position<_inputLength){
							if(_inputArray[_position]=='\\'){
								++_position;
							}
							else{
								_state=FAILED;
							}
						}
						else{
							_state=FAILED;
						}
						if(_position<_inputLength){
							if(_inputArray[_position]=='\"'){
								++_position;
							}
							else{
								_state=FAILED;
							}
						}
						else{
							_state=FAILED;
						}
						if(_state==SUCCESS){
							_token.add(_position_regex_13,new Tokens.Plain.Quote(_input.substring(_position_regex_13,_position)));
							while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
								++_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"\\\\\\\"");
								_furthestPosition=_position;
							}
							_position=_position_regex_13;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
								_furthestPosition=_position;
							}
							_position=_position_regex_special;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_regex_special.add(_position_regex_special,_token);
						}
						_token=_token_regex_special;
						if(_state==FAILED){
							_state=SUCCESS;
							_position_regex_special=_position;
							_token_regex_special=_token;
							_token=new Tokens.Rule.RegexSpecialToken();
							if(_position+1-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='\''){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_27.apostrophe);
								_position=_position+1;
								while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain '");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
									_furthestPosition=_position;
								}
								_position=_position_regex_special;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_regex_special.add(_position_regex_special,_token);
							}
							_token=_token_regex_special;
							if(_state==FAILED){
								_state=SUCCESS;
								_position_regex_special=_position;
								_token_regex_special=_token;
								_token=new Tokens.Rule.RegexSpecialToken();
								if(_position+2-1 >=_inputLength){
									_state=FAILED;
								}
								else{
									if(_inputArray[_position+0]!='\\'){
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='\\'){
										_state=FAILED;
									}
								}
								if(_state==SUCCESS){
									_token.add(_position,Tokens.Syntax.syntax_28.slash);
									_position=_position+2;
									while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
										++_position;
									}
								}
								else if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\\\");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
										_furthestPosition=_position;
									}
									_position=_position_regex_special;
								}
								else{
								}
								if(_state==SUCCESS){
									_token_regex_special.add(_position_regex_special,_token);
								}
								_token=_token_regex_special;
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
			_token=new Tokens.Name.IgnoresCharacterToken();
			parse_single_ignores_character();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_9)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_9;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_9.add(_position__anonymous_9,_token);
			}
			_token=_token__anonymous_9;
		}
		public void parse_element(){
			int _position_element = -1;
			Token.Parsed _token_element = null;
			_position_element=_position;
			_token_element=_token;
			_token=new Tokens.Rule.ElementToken();
			parse_atom();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(element)");
					_furthestPosition=_position;
				}
				_position=_position_element;
			}
			else{
				int _state_31 = _state;
				parse__anonymous_19();
				if(_state_31==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(element)");
						_furthestPosition=_position;
					}
					_position=_position_element;
				}
				else{
					int _state_32 = _state;
					parse__anonymous_20();
					if(_state_32==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(element)");
							_furthestPosition=_position;
						}
						_position=_position_element;
					}
					else{
						int _state_33 = _state;
						parse__anonymous_21();
						if(_state_33==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(element)");
								_furthestPosition=_position;
							}
							_position=_position_element;
						}
						else{
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_element.add(_position_element,_token);
			}
			_token=_token_element;
		}
		public Parser.NameList getListNamesBracedDefinition(){
			return list_names_braced_definition;
		}
		public void setListNamesBracedDefinition(Parser.NameList newListNamesBracedDefinition){
			list_names_braced_definition = newListNamesBracedDefinition;
		}
		public void parse_braced_definition(){
			int _position_braced_definition = -1;
			Token.Parsed _token_braced_definition = null;
			int _length_braced_definition_brace = _inputLength;
			if(brace_2.containsKey(_position)){
				list_names_braced_definition=list_names;
				list_names=new Parser.NameList.Child(list_names);
				_inputLength=brace_2.get(_position);
				int _position_braced_definition_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				_position_braced_definition=_position;
				_token_braced_definition=_token;
				_token=new Tokens.Rule.BracedDefinitionToken();
				int _position_definition = _position;
				if(_state==SUCCESS&&!_recursion_protection_definition_1.contains(_position)){
					_recursion_protection_definition_1.add(_position);
					parse_definition();
					_recursion_protection_definition_1.remove(_position_definition);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"braced_definition(braced_definition)");
						_furthestPosition=_position;
					}
					_position=_position_braced_definition;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_braced_definition.add(_position_braced_definition,_token);
				}
				_token=_token_braced_definition;
				if(_state==SUCCESS&&brace_2.get(_position_braced_definition_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_2.get(_position_braced_definition_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_braced_definition_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				list_names=list_names_braced_definition;
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"braced_definition(braced_definition)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse_NUMBER(){
			int _position_NUMBER = -1;
			Token.Parsed _token_NUMBER = null;
			_position_NUMBER=_position;
			_token_NUMBER=_token;
			_token=new Tokens.Rule.NUMBERToken();
			int _position_regex_4 = _position;
			if(_position<_inputLength){
				if(_inputArray[_position]=='-'){
					++_position;
				}
			}
			int _state_7 = _state;
			if(_position<_inputLength){
				int _position_of_last_success_6 = _position;
				int _multiple_index_8 = 0;
				int _state_8 = _state;
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
						++_multiple_index_8;
					}
				}
				if(_state_8==SUCCESS){
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
					_position=_position_of_last_success_6;
				}
			}
			if(_state_7==SUCCESS){
				_state=SUCCESS;
			}
			int _multiple_index_9 = 0;
			int _state_9 = _state;
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
					++_multiple_index_9;
				}
			}
			if(_state_9==SUCCESS&&_multiple_index_9>0 ){
				_state=SUCCESS;
			}
			else{
				_state=FAILED;
			}
			int _state_10 = _state;
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
			if(_state_10==SUCCESS){
				_state=SUCCESS;
			}
			int _multiple_index_11 = 0;
			int _state_11 = _state;
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
					++_multiple_index_11;
				}
			}
			if(_state_11==SUCCESS){
				_state=SUCCESS;
			}
			if(_state==SUCCESS){
				_token.setValue(_input.substring(_position_regex_4,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[-]?(\\\\d*[.]?\\\\d+f?\\\\s*");
					_furthestPosition=_position;
				}
				_position=_position_regex_4;
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
		public void parse_imports(){
			int _position_imports = -1;
			Token.Parsed _token_imports = null;
			_position_imports=_position;
			_token_imports=_token;
			_token=new Token.Parsed("$ANON");
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
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
				parse_FILE_NAME();
				if(_state==SUCCESS){
					_import_FILE_NAME_value=_token.getLastValue();
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
						_furthestPosition=_position;
					}
					_position=_position_imports;
				}
				else{
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n");
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
				_fileNameBuilder.append(_import_FILE_NAME_value);
				String _import_file_name = _directoryName+_fileNameBuilder.toString();
				if(new File(_import_file_name).exists()){
					if(Parser.contexts.containsKey(_import_file_name)==false){
						Parser.Context _import_context = new Parser.Context.ImportsFileImport(list_names_root);
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
				_token_imports.addAll(_token);
			}
			_token=_token_imports;
		}
		public void parse_rule_parameters(){
			int _position_rule_parameters = -1;
			Token.Parsed _token_rule_parameters = null;
			_position_rule_parameters=_position;
			_token_rule_parameters=_token;
			_token=new Token.Parsed("$ANON");
			parse_rule_params();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_parameters(rule_parameters)");
					_furthestPosition=_position;
				}
				_position=_position_rule_parameters;
			}
			else{
				int _state_17 = _state;
				while(_position<_inputLength){
					parse__anonymous_2();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_17==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_parameters(rule_parameters)");
						_furthestPosition=_position;
					}
					_position=_position_rule_parameters;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token_rule_parameters.addAll(_token);
			}
			_token=_token_rule_parameters;
		}
		public void parse__anonymous_0(){
			int _position__anonymous_0 = -1;
			Token.Parsed _token__anonymous_0 = null;
			_position__anonymous_0=_position;
			_token__anonymous_0=_token;
			_token=new Token.Parsed("$ANON");
			Token.Parsed _tokensingle_ignores_character = _token;
			_token=new Tokens.Name.IgnoreCharacterToken();
			int _position_single_ignores_character = _position;
			parse_single_ignores_character();
			if(_state==SUCCESS){
				_tokensingle_ignores_character.add(_position_single_ignores_character,_token);
			}
			_token=_tokensingle_ignores_character;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(_anonymous_0)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_0;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_0.addAll(_token);
			}
			_token=_token__anonymous_0;
		}
		public Parser.NameList getListNamesAdditionsList(){
			return list_names_additions_list;
		}
		public void setListNamesAdditionsList(Parser.NameList newListNamesAdditionsList){
			list_names_additions_list = newListNamesAdditionsList;
		}
		public void parse_list(){
			int _position_list = -1;
			Token.Parsed _token_list = null;
			list_names_additions_list=list_names_additions;
			list_names_additions=new Parser.NameList.Child(list_names);
			_position_list=_position;
			_token_list=_token;
			_token=new Tokens.Rule.ListToken();
			int _position_NAME = _position;
			if(_state==SUCCESS&&!_recursion_protection_NAME_4.contains(_position)){
				_recursion_protection_NAME_4.add(_position);
				parse_NAME();
				_recursion_protection_NAME_4.remove(_position_NAME);
			}
			else{
				_state=FAILED;
			}
			if(_state==SUCCESS){
				String _value = _token.getLastValue();
				if(_value!=null&&list_names.add(_value)){
					list_names_additions.add(_value);
				}
				_token.add(_position,new Tokens.Name.ListNameToken(_value));
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
					_furthestPosition=_position;
				}
				_position=_position_list;
			}
			else{
				int _state_26 = _state;
				if(_position+6-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='g'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='l'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='o'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='b'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='a'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='l'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_15.__SYNTAX__);
					_position=_position+6;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain global");
						_furthestPosition=_position;
					}
				}
				list_names_additions=list_names_additions_list;
				if(_state_26==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
						_furthestPosition=_position;
					}
					_position=_position_list;
				}
				else{
					if(_position+4-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='l'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='i'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='s'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='t'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_16.__SYNTAX__);
						_position=_position+4;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain list");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
							_furthestPosition=_position;
						}
						_position=_position_list;
					}
					else{
						int _state_27 = _state;
						parse__anonymous_14();
						list_names_additions=list_names_additions_list;
						if(_state_27==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
								_furthestPosition=_position;
							}
							_position=_position_list;
						}
						else{
							int _state_28 = _state;
							while(_position<_inputLength){
								parse__anonymous_15();
								if(_state==FAILED){
									list_names_additions=list_names_additions_list;
									break;
								}
							}
							if(_state_28==SUCCESS&&_state==FAILED){
								list_names_additions=list_names_additions_list;
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
									_furthestPosition=_position;
								}
								_position=_position_list;
							}
							else{
							}
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_list.add(_position_list,_token);
			}
			_token=_token_list;
			if(_state==FAILED){
				if(list_names_additions!=null){
					list_names.removeAll(list_names_additions);
					list_names_additions.clear();
				}
			}
			else if(_state==SUCCESS){
				if(list_names_additions!=null){
					list_names.addAll(list_names_additions);
					list_names_additions.clear();
				}
			}
			list_names_additions=list_names_additions_list;
		}
		public void parse_NAME(){
			int _position_NAME = -1;
			Token.Parsed _token_NAME = null;
			_position_NAME=_position;
			_token_NAME=_token;
			_token=new Tokens.Rule.NAMEToken();
			int _position_regex_2 = _position;
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
				_token.setValue(_input.substring(_position_regex_2,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z_][a-zA-Z0-9_]*");
					_furthestPosition=_position;
				}
				_position=_position_regex_2;
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
		public Parser.NameList getListNamesSingleIgnoresCharacter(){
			return list_names_single_ignores_character;
		}
		public void setListNamesSingleIgnoresCharacter(Parser.NameList newListNamesSingleIgnoresCharacter){
			list_names_single_ignores_character = newListNamesSingleIgnoresCharacter;
		}
		public void parse_single_ignores_character(){
			int _position_single_ignores_character = -1;
			Token.Parsed _token_single_ignores_character = null;
			int _length_single_ignores_character_brace = _inputLength;
			if(brace_3.containsKey(_position)){
				list_names_single_ignores_character=list_names;
				list_names=new Parser.NameList.Child(list_names);
				_inputLength=brace_3.get(_position);
				int _position_single_ignores_character_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				_position_single_ignores_character=_position;
				_token_single_ignores_character=_token;
				_token=new Token.Parsed("$ANON");
				int _position_regex_6 = _position;
				int _state_15 = _state;
				if(_position<_inputLength){
					if(_position<_inputLength){
						if(_inputArray[_position]=='\\'){
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
				if(_state_15==SUCCESS){
					_state=SUCCESS;
				}
				int _state_16 = _state;
				if(_position<_inputLength){
					if(_position<_inputLength){
						++_position;
					}
					else{
						_state=FAILED;
					}
				}
				if(_state_16==SUCCESS){
					_state=SUCCESS;
				}
				if(_state==SUCCESS){
					_token.setValue(_input.substring(_position_regex_6,_position));
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"\\\\?.?");
						_furthestPosition=_position;
					}
					_position=_position_regex_6;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"single_ignores_character(single_ignores_character)");
						_furthestPosition=_position;
					}
					_position=_position_single_ignores_character;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_single_ignores_character.addAll(_token);
				}
				_token=_token_single_ignores_character;
				if(_state==SUCCESS&&brace_3.get(_position_single_ignores_character_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_single_ignores_character_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_single_ignores_character_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				list_names=list_names_single_ignores_character;
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"single_ignores_character(single_ignores_character)");
					_furthestPosition=_position;
				}
			}
		}
		public Parser.NameList getListNamesRegex(){
			return list_names_regex;
		}
		public void setListNamesRegex(Parser.NameList newListNamesRegex){
			list_names_regex = newListNamesRegex;
		}
		public void parse_regex(){
			int _position_regex = -1;
			Token.Parsed _token_regex = null;
			int _length_regex_brace = _inputLength;
			if(brace_3.containsKey(_position)){
				list_names_regex=list_names;
				list_names=new Parser.NameList.Child(list_names);
				_inputLength=brace_3.get(_position);
				int _position_regex_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				_position_regex=_position;
				_token_regex=_token;
				_token=new Tokens.Rule.RegexToken();
				parse_regex_definition();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex(regex)");
						_furthestPosition=_position;
					}
					_position=_position_regex;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_regex.add(_position_regex,_token);
				}
				_token=_token_regex;
				if(_state==SUCCESS&&brace_3.get(_position_regex_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_regex_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_regex_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				list_names=list_names_regex;
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex(regex)");
					_furthestPosition=_position;
				}
			}
		}
		public Parser.NameList getListNamesRegexGroupDefinition(){
			return list_names_regex_group_definition;
		}
		public void setListNamesRegexGroupDefinition(Parser.NameList newListNamesRegexGroupDefinition){
			list_names_regex_group_definition = newListNamesRegexGroupDefinition;
		}
		public void parse_regex_group_definition(){
			int _position_regex_group_definition = -1;
			Token.Parsed _token_regex_group_definition = null;
			int _length_regex_group_definition_brace = _inputLength;
			if(brace_2.containsKey(_position)){
				list_names_regex_group_definition=list_names;
				list_names=new Parser.NameList.Child(list_names);
				_inputLength=brace_2.get(_position);
				int _position_regex_group_definition_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				_position_regex_group_definition=_position;
				_token_regex_group_definition=_token;
				_token=new Token.Parsed("$ANON");
				Token.Parsed _tokenregex_definition = _token;
				_token=new Tokens.Name.RegexToken();
				int _position_regex_definition = _position;
				if(_state==SUCCESS&&!_recursion_protection_regex_definition_2.contains(_position)){
					_recursion_protection_regex_definition_2.add(_position);
					parse_regex_definition();
					_recursion_protection_regex_definition_2.remove(_position_regex_definition);
				}
				else{
					_state=FAILED;
				}
				if(_state==SUCCESS){
					_tokenregex_definition.add(_position_regex_definition,_token);
				}
				_token=_tokenregex_definition;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_group_definition(regex_group_definition)");
						_furthestPosition=_position;
					}
					_position=_position_regex_group_definition;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_regex_group_definition.addAll(_token);
				}
				_token=_token_regex_group_definition;
				if(_state==SUCCESS&&brace_2.get(_position_regex_group_definition_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_2.get(_position_regex_group_definition_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_regex_group_definition_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				list_names=list_names_regex_group_definition;
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_group_definition(regex_group_definition)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse_regex_option_definition(){
			int _position_regex_option_definition = -1;
			Token.Parsed _token_regex_option_definition = null;
			_position_regex_option_definition=_position;
			_token_regex_option_definition=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_1();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(regex_option_definition)");
					_furthestPosition=_position;
				}
				_position=_position_regex_option_definition;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_regex_option_definition.addAll(_token);
			}
			_token=_token_regex_option_definition;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_regex_option_definition=_position;
				_token_regex_option_definition=_token;
				_token=new Token.Parsed("$ANON");
				parse_regex_special();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(regex_option_definition)");
						_furthestPosition=_position;
					}
					_position=_position_regex_option_definition;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_regex_option_definition.addAll(_token);
				}
				_token=_token_regex_option_definition;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_regex_option_definition=_position;
					_token_regex_option_definition=_token;
					_token=new Token.Parsed("$ANON");
					int _position_regex_9 = _position;
					if(_position<_inputLength){
						++_position;
					}
					else{
						_state=FAILED;
					}
					if(_state==SUCCESS){
						_token.add(_position_regex_9,new Tokens.Plain.StandAlone(_input.substring(_position_regex_9,_position)));
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
							++_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
							_furthestPosition=_position;
						}
						_position=_position_regex_9;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(regex_option_definition)");
							_furthestPosition=_position;
						}
						_position=_position_regex_option_definition;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_regex_option_definition.addAll(_token);
					}
					_token=_token_regex_option_definition;
				}
			}
		}
		public Parser.NameList getListNamesRegexOption(){
			return list_names_regex_option;
		}
		public void setListNamesRegexOption(Parser.NameList newListNamesRegexOption){
			list_names_regex_option = newListNamesRegexOption;
		}
		public void parse_regex_option(){
			int _position_regex_option = -1;
			Token.Parsed _token_regex_option = null;
			int _length_regex_option_brace = _inputLength;
			if(brace_3.containsKey(_position)){
				list_names_regex_option=list_names;
				list_names=new Parser.NameList.Child(list_names);
				_inputLength=brace_3.get(_position);
				int _position_regex_option_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				_position_regex_option=_position;
				_token_regex_option=_token;
				_token=new Token.Parsed("$ANON");
				int _state_13 = _state;
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='^'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_4.negate);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
						_furthestPosition=_position;
					}
				}
				if(_state_13==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option(regex_option)");
						_furthestPosition=_position;
					}
					_position=_position_regex_option;
				}
				else{
					int _state_14 = _state;
					while(_position<_inputLength){
						parse_regex_option_definition();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_14==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option(regex_option)");
							_furthestPosition=_position;
						}
						_position=_position_regex_option;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token_regex_option.addAll(_token);
				}
				_token=_token_regex_option;
				if(_state==SUCCESS&&brace_3.get(_position_regex_option_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"Did not reach end of braces"));
					_position=brace_3.get(_position_regex_option_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_regex_option_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				list_names=list_names_regex_option;
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option(regex_option)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_22(){
			int _position__anonymous_22 = -1;
			Token.Parsed _token__anonymous_22 = null;
			_position__anonymous_22=_position;
			_token__anonymous_22=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='?'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_22)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_22;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_22.add(_position__anonymous_22,_token);
			}
			_token=_token__anonymous_22;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_22=_position;
				_token__anonymous_22=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_22.MANY);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_22)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_22;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_22.add(_position__anonymous_22,_token);
				}
				_token=_token__anonymous_22;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_22=_position;
					_token__anonymous_22=_token;
					_token=new Tokens.Name.MultipleToken();
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='+'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_22)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_22;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_22.add(_position__anonymous_22,_token);
					}
					_token=_token__anonymous_22;
				}
			}
		}
		public void parse_rule(){
			int _position_rule = -1;
			Token.Parsed _token_rule = null;
			_position_rule=_position;
			_token_rule=_token;
			_token=new Tokens.Rule.RuleToken();
			Token.Parsed _tokenNAME = _token;
			_token=new Tokens.Name.RuleNameToken();
			int _position_NAME = _position;
			if(_state==SUCCESS&&!_recursion_protection_NAME_3.contains(_position)){
				_recursion_protection_NAME_3.add(_position);
				parse_NAME();
				_recursion_protection_NAME_3.remove(_position_NAME);
			}
			else{
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_tokenNAME.add(_position_NAME,_token);
			}
			_token=_tokenNAME;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(rule)");
					_furthestPosition=_position;
				}
				_position=_position_rule;
			}
			else{
				int _state_25 = _state;
				parse__anonymous_11();
				if(_state_25==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(rule)");
						_furthestPosition=_position;
					}
					_position=_position_rule;
				}
				else{
					parse__anonymous_12();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(rule)");
							_furthestPosition=_position;
						}
						_position=_position_rule;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token_rule.add(_position_rule,_token);
			}
			_token=_token_rule;
		}
		public void parse__anonymous_21(){
			int _position__anonymous_21 = -1;
			Token.Parsed _token__anonymous_21 = null;
			_position__anonymous_21=_position;
			_token__anonymous_21=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='?'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_21)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_21;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_21.add(_position__anonymous_21,_token);
			}
			_token=_token__anonymous_21;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_21=_position;
				_token__anonymous_21=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_22.MANY);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_21)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_21;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_21.add(_position__anonymous_21,_token);
				}
				_token=_token__anonymous_21;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_21=_position;
					_token__anonymous_21=_token;
					_token=new Tokens.Name.MultipleToken();
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='+'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_21)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_21;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_21.add(_position__anonymous_21,_token);
					}
					_token=_token__anonymous_21;
				}
			}
		}
		public void parse__anonymous_24(){
			int _position__anonymous_24 = -1;
			Token.Parsed _token__anonymous_24 = null;
			_position__anonymous_24=_position;
			_token__anonymous_24=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='?'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_24)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_24;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_24.add(_position__anonymous_24,_token);
			}
			_token=_token__anonymous_24;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_24=_position;
				_token__anonymous_24=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_22.MANY);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_24)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_24;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_24.add(_position__anonymous_24,_token);
				}
				_token=_token__anonymous_24;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_24=_position;
					_token__anonymous_24=_token;
					_token=new Tokens.Name.MultipleToken();
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='+'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_24)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_24;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_24.add(_position__anonymous_24,_token);
					}
					_token=_token__anonymous_24;
				}
			}
		}
		public void parse__anonymous_23(){
			int _position__anonymous_23 = -1;
			Token.Parsed _token__anonymous_23 = null;
			_position__anonymous_23=_position;
			_token__anonymous_23=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='?'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_23)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_23;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_23.add(_position__anonymous_23,_token);
			}
			_token=_token__anonymous_23;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_23=_position;
				_token__anonymous_23=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_22.MANY);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_23)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_23;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_23.add(_position__anonymous_23,_token);
				}
				_token=_token__anonymous_23;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_23=_position;
					_token__anonymous_23=_token;
					_token=new Tokens.Name.MultipleToken();
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='+'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_23)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_23;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_23.add(_position__anonymous_23,_token);
					}
					_token=_token__anonymous_23;
				}
			}
		}
		public void parse__anonymous_25(){
			int _position__anonymous_25 = -1;
			Token.Parsed _token__anonymous_25 = null;
			_position__anonymous_25=_position;
			_token__anonymous_25=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='?'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_25)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_25;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_25.add(_position__anonymous_25,_token);
			}
			_token=_token__anonymous_25;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_25=_position;
				_token__anonymous_25=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='*'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_22.MANY);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_25)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_25;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_25.add(_position__anonymous_25,_token);
				}
				_token=_token__anonymous_25;
				if(_state==FAILED){
					_state=SUCCESS;
					_position__anonymous_25=_position;
					_token__anonymous_25=_token;
					_token=new Tokens.Name.MultipleToken();
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='+'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_25)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_25;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_25.add(_position__anonymous_25,_token);
					}
					_token=_token__anonymous_25;
				}
			}
		}
		public void parse__anonymous_20(){
			int _position__anonymous_20 = -1;
			Token.Parsed _token__anonymous_20 = null;
			_position__anonymous_20=_position;
			_token__anonymous_20=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='n'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_20.__SYNTAX__);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain in");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_20)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_20;
			}
			else{
				Token.Parsed _tokenNAME = _token;
				_token=new Tokens.Name.ListNameToken();
				int _position_NAME = _position;
				parse_NAME();
				if(_state==SUCCESS){
					_tokenNAME.add(_position_NAME,_token);
				}
				_token=_tokenNAME;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_20)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_20;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_20.addAll(_token);
			}
			_token=_token__anonymous_20;
		}
		public void parse_definition(){
			int _position_definition = -1;
			Token.Parsed _token_definition = null;
			_position_definition=_position;
			_token_definition=_token;
			_token=new Tokens.Rule.DefinitionToken();
			int _state_29 = _state;
			boolean _iteration_achieved_29 = false;
			while(_position<_inputLength){
				Token.Parsed _tokenelement = _token;
				_token=new Tokens.Name.ChainToken();
				int _position_element = _position;
				parse_element();
				if(_state==SUCCESS){
					_tokenelement.add(_position_element,_token);
				}
				_token=_tokenelement;
				if(_state==FAILED){
					break;
				}
				else{
					_iteration_achieved_29=true;
				}
			}
			if(_iteration_achieved_29==false){
				_state=FAILED;
			}
			else if(_state_29==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(definition)");
					_furthestPosition=_position;
				}
				_position=_position_definition;
			}
			else{
				int _state_30 = _state;
				parse__anonymous_17();
				if(_state_30==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(definition)");
						_furthestPosition=_position;
					}
					_position=_position_definition;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token_definition.add(_position_definition,_token);
			}
			_token=_token_definition;
		}
		public void parse__anonymous_19(){
			int _position__anonymous_19 = -1;
			Token.Parsed _token__anonymous_19 = null;
			_position__anonymous_19=_position;
			_token__anonymous_19=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='s'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_19.__SYNTAX__);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain as");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_19)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_19;
			}
			else{
				Token.Parsed _tokenNAME = _token;
				_token=new Tokens.Name.NewNameToken();
				int _position_NAME = _position;
				parse_NAME();
				if(_state==SUCCESS){
					_tokenNAME.add(_position_NAME,_token);
				}
				_token=_tokenNAME;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_19)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_19;
				}
				else{
				}
			}
			if(_state==SUCCESS){
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
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='|'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_18.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(_anonymous_18)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_18;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_18.addAll(_token);
			}
			_token=_token__anonymous_18;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_18=_position;
				_token__anonymous_18=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+2-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='\t'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n\t");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(_anonymous_18)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_18;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_18.addAll(_token);
				}
				_token=_token__anonymous_18;
			}
		}
		public Parser.NameList getListNamesComments(){
			return list_names_comments;
		}
		public void setListNamesComments(Parser.NameList newListNamesComments){
			list_names_comments = newListNamesComments;
		}
		public void parse_comments(){
			int _position_comments = -1;
			Token.Parsed _token_comments = null;
			int _length_comments_brace = _inputLength;
			if(brace_1.containsKey(_position)){
				list_names_comments=list_names;
				list_names=new Parser.NameList.Child(list_names);
				_inputLength=brace_1.get(_position);
				int _position_comments_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
				list_names=list_names_comments;
			}
			else{
				_state=FAILED;
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"comments(comments)");
					_furthestPosition=_position;
				}
			}
		}
		public void parse__anonymous_11(){
			int _position__anonymous_11 = -1;
			Token.Parsed _token__anonymous_11 = null;
			_position__anonymous_11=_position;
			_token__anonymous_11=_token;
			_token=new Token.Parsed("$ANON");
			parse_rule_parameters();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_11)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_11;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_11.addAll(_token);
			}
			_token=_token__anonymous_11;
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
				if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
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
				parse_ignores();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
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
					parse_comments();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
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
						parse_imports();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
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
							parse_list();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
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
								parse_rule();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
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
					}
				}
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
				if(_inputArray[_position+0]!='='){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_13)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_13;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_13.addAll(_token);
			}
			_token=_token__anonymous_13;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_13=_position;
				_token__anonymous_13=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+2-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='\t'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n\t");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_13)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_13;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_13.addAll(_token);
				}
				_token=_token__anonymous_13;
			}
		}
		public void parse__anonymous_12(){
			int _position__anonymous_12 = -1;
			Token.Parsed _token__anonymous_12 = null;
			_position__anonymous_12=_position;
			_token__anonymous_12=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_13();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_12)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_12;
			}
			else{
				parse_definition();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_12)");
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
		public void parse_regex_definition(){
			int _position_regex_definition = -1;
			Token.Parsed _token_regex_definition = null;
			_position_regex_definition=_position;
			_token_regex_definition=_token;
			_token=new Token.Parsed("$ANON");
			int _state_34 = _state;
			boolean _iteration_achieved_34 = false;
			while(_position<_inputLength){
				parse_regex_element();
				if(_state==FAILED){
					break;
				}
				else{
					_iteration_achieved_34=true;
				}
			}
			if(_iteration_achieved_34==false){
				_state=FAILED;
			}
			else if(_state_34==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_definition(regex_definition)");
					_furthestPosition=_position;
				}
				_position=_position_regex_definition;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_regex_definition.addAll(_token);
			}
			_token=_token_regex_definition;
		}
		public void parse__anonymous_15(){
			int _position__anonymous_15 = -1;
			Token.Parsed _token__anonymous_15 = null;
			_position__anonymous_15=_position;
			_token__anonymous_15=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_16();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_15)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_15;
			}
			else{
				parse_quote();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_15)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_15;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_15.addAll(_token);
			}
			_token=_token__anonymous_15;
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_15=_position;
				_token__anonymous_15=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_15)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_15;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_15.addAll(_token);
				}
				_token=_token__anonymous_15;
			}
		}
		public void parse__anonymous_14(){
			int _position__anonymous_14 = -1;
			Token.Parsed _token__anonymous_14 = null;
			_position__anonymous_14=_position;
			_token__anonymous_14=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+4-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='w'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='h'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_17.__SYNTAX__);
				_position=_position+4;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain with");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_14)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_14;
			}
			else{
				Token.Parsed _tokenNAME = _token;
				_token=new Tokens.Name.ListRuleNameToken();
				int _position_NAME = _position;
				parse_NAME();
				if(_state==SUCCESS){
					_tokenNAME.add(_position_NAME,_token);
				}
				_token=_tokenNAME;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_14)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_14;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_14.addAll(_token);
			}
			_token=_token__anonymous_14;
		}
		public void parse__anonymous_17(){
			int _position__anonymous_17 = -1;
			Token.Parsed _token__anonymous_17 = null;
			_position__anonymous_17=_position;
			_token__anonymous_17=_token;
			_token=new Tokens.Name.ChoiceToken();
			parse__anonymous_18();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(_anonymous_17)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_17;
			}
			else{
				parse_definition();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(_anonymous_17)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_17;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_17.add(_position__anonymous_17,_token);
			}
			_token=_token__anonymous_17;
		}
		public void parse__anonymous_16(){
			int _position__anonymous_16 = -1;
			Token.Parsed _token__anonymous_16 = null;
			_position__anonymous_16=_position;
			_token__anonymous_16=_token;
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
				_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_16)");
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
			if(_state==FAILED){
				_state=SUCCESS;
				_position__anonymous_16=_position;
				_token__anonymous_16=_token;
				_token=new Token.Parsed("$ANON");
				if(_position+2-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='\t'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n\t");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_16)");
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
		}
		public void parse_OPERATOR(){
			int _position_OPERATOR = -1;
			Token.Parsed _token_OPERATOR = null;
			_position_OPERATOR=_position;
			_token_OPERATOR=_token;
			_token=new Tokens.Rule.OPERATORToken();
			int _position_regex_3 = _position;
			int _multiple_index_5 = 0;
			while(_position<_inputLength){
				if(_inputArray[_position]!='a'&&_inputArray[_position]!='b'&&_inputArray[_position]!='c'&&_inputArray[_position]!='d'&&_inputArray[_position]!='e'&&_inputArray[_position]!='f'&&_inputArray[_position]!='g'&&_inputArray[_position]!='h'&&_inputArray[_position]!='i'&&_inputArray[_position]!='j'&&_inputArray[_position]!='k'&&_inputArray[_position]!='l'&&_inputArray[_position]!='m'&&_inputArray[_position]!='n'&&_inputArray[_position]!='o'&&_inputArray[_position]!='p'&&_inputArray[_position]!='q'&&_inputArray[_position]!='r'&&_inputArray[_position]!='s'&&_inputArray[_position]!='t'&&_inputArray[_position]!='u'&&_inputArray[_position]!='v'&&_inputArray[_position]!='w'&&_inputArray[_position]!='x'&&_inputArray[_position]!='y'&&_inputArray[_position]!='z'&&_inputArray[_position]!='A'&&_inputArray[_position]!='B'&&_inputArray[_position]!='C'&&_inputArray[_position]!='D'&&_inputArray[_position]!='E'&&_inputArray[_position]!='F'&&_inputArray[_position]!='G'&&_inputArray[_position]!='H'&&_inputArray[_position]!='I'&&_inputArray[_position]!='J'&&_inputArray[_position]!='K'&&_inputArray[_position]!='L'&&_inputArray[_position]!='M'&&_inputArray[_position]!='N'&&_inputArray[_position]!='O'&&_inputArray[_position]!='P'&&_inputArray[_position]!='Q'&&_inputArray[_position]!='R'&&_inputArray[_position]!='S'&&_inputArray[_position]!='T'&&_inputArray[_position]!='U'&&_inputArray[_position]!='V'&&_inputArray[_position]!='W'&&_inputArray[_position]!='X'&&_inputArray[_position]!='Y'&&_inputArray[_position]!='Z'&&_inputArray[_position]!='0'&&_inputArray[_position]!='1'&&_inputArray[_position]!='2'&&_inputArray[_position]!='3'&&_inputArray[_position]!='4'&&_inputArray[_position]!='5'&&_inputArray[_position]!='6'&&_inputArray[_position]!='7'&&_inputArray[_position]!='8'&&_inputArray[_position]!='9'&&_inputArray[_position]!='_'&&_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'&&_inputArray[_position]!='\\'&&_inputArray[_position]!='n'&&_inputArray[_position]!='('&&_inputArray[_position]!=')'&&_inputArray[_position]!='{'&&_inputArray[_position]!='}'&&_inputArray[_position]!='['&&_inputArray[_position]!=']'&&_inputArray[_position]!=';'&&_inputArray[_position]!='\"'&&_inputArray[_position]!='\\'&&_inputArray[_position]!='\''&&_inputArray[_position]!='`'&&_inputArray[_position]!=','){
					++_position;
					++_multiple_index_5;
				}
				else{
					break;
				}
			}
			if(_multiple_index_5==0 ){
				_state=FAILED;
			}
			if(_state==SUCCESS){
				_token.setValue(_input.substring(_position_regex_3,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
					++_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z0-9_\\\\s\\n(){}[];\\\"\\\\\'`,]+");
					_furthestPosition=_position;
				}
				_position=_position_regex_3;
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
		public void parse_regex_element(){
			int _position_regex_element = -1;
			Token.Parsed _token_regex_element = null;
			_position_regex_element=_position;
			_token_regex_element=_token;
			_token=new Tokens.Rule.RegexElementToken();
			Token.Parsed _tokenregex_option = _token;
			_token=new Tokens.Name.OptionToken();
			int _position_regex_option = _position;
			parse_regex_option();
			if(_state==SUCCESS){
				_tokenregex_option.add(_position_regex_option,_token);
			}
			_token=_tokenregex_option;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
					_furthestPosition=_position;
				}
				_position=_position_regex_element;
			}
			else{
				int _state_35 = _state;
				parse__anonymous_22();
				if(_state_35==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
						_furthestPosition=_position;
					}
					_position=_position_regex_element;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token_regex_element.add(_position_regex_element,_token);
			}
			_token=_token_regex_element;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_regex_element=_position;
				_token_regex_element=_token;
				_token=new Tokens.Rule.RegexElementToken();
				Token.Parsed _tokenregex_group_definition = _token;
				_token=new Tokens.Name.GroupToken();
				int _position_regex_group_definition = _position;
				parse_regex_group_definition();
				if(_state==SUCCESS){
					_tokenregex_group_definition.add(_position_regex_group_definition,_token);
				}
				_token=_tokenregex_group_definition;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
						_furthestPosition=_position;
					}
					_position=_position_regex_element;
				}
				else{
					int _state_36 = _state;
					parse__anonymous_23();
					if(_state_36==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
							_furthestPosition=_position;
						}
						_position=_position_regex_element;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token_regex_element.add(_position_regex_element,_token);
				}
				_token=_token_regex_element;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_regex_element=_position;
					_token_regex_element=_token;
					_token=new Tokens.Rule.RegexElementToken();
					parse_regex_special();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
							_furthestPosition=_position;
						}
						_position=_position_regex_element;
					}
					else{
						int _state_37 = _state;
						parse__anonymous_24();
						if(_state_37==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
								_furthestPosition=_position;
							}
							_position=_position_regex_element;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token_regex_element.add(_position_regex_element,_token);
					}
					_token=_token_regex_element;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_regex_element=_position;
						_token_regex_element=_token;
						_token=new Tokens.Rule.RegexElementToken();
						int _position_regex_12 = _position;
						if(_position<_inputLength){
							++_position;
						}
						else{
							_state=FAILED;
						}
						if(_state==SUCCESS){
							_token.add(_position_regex_12,new Tokens.Plain.Character(_input.substring(_position_regex_12,_position)));
							while(_position<_inputLength&&(false||_inputArray[_position]==' ')){
								++_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
								_furthestPosition=_position;
							}
							_position=_position_regex_12;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
								_furthestPosition=_position;
							}
							_position=_position_regex_element;
						}
						else{
							int _state_38 = _state;
							parse__anonymous_25();
							if(_state_38==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
									_furthestPosition=_position;
								}
								_position=_position_regex_element;
							}
							else{
							}
						}
						if(_state==SUCCESS){
							_token_regex_element.add(_position_regex_element,_token);
						}
						_token=_token_regex_element;
					}
				}
			}
		}
		public void parse_atom(){
			int _position_atom = -1;
			Token.Parsed _token_atom = null;
			_position_atom=_position;
			_token_atom=_token;
			_token=new Token.Parsed("$ANON");
			parse_braced_definition();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"atom(atom)");
					_furthestPosition=_position;
				}
				_position=_position_atom;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_atom.addAll(_token);
			}
			_token=_token_atom;
			if(_state==FAILED){
				_state=SUCCESS;
				_position_atom=_position;
				_token_atom=_token;
				_token=new Token.Parsed("$ANON");
				Token.Parsed _tokenquote = _token;
				_token=new Tokens.Name.QuoteTokenToken();
				int _position_quote = _position;
				parse_quote();
				if(_state==SUCCESS){
					_tokenquote.add(_position_quote,_token);
				}
				_token=_tokenquote;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"atom(atom)");
						_furthestPosition=_position;
					}
					_position=_position_atom;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_atom.addAll(_token);
				}
				_token=_token_atom;
				if(_state==FAILED){
					_state=SUCCESS;
					_position_atom=_position;
					_token_atom=_token;
					_token=new Token.Parsed("$ANON");
					Token.Parsed _tokenregex = _token;
					_token=new Tokens.Name.RegexTokenToken();
					int _position_regex = _position;
					parse_regex();
					if(_state==SUCCESS){
						_tokenregex.add(_position_regex,_token);
					}
					_token=_tokenregex;
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"atom(atom)");
							_furthestPosition=_position;
						}
						_position=_position_atom;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_atom.addAll(_token);
					}
					_token=_token_atom;
					if(_state==FAILED){
						_state=SUCCESS;
						_position_atom=_position;
						_token_atom=_token;
						_token=new Token.Parsed("$ANON");
						Token.Parsed _tokenNAME = _token;
						_token=new Tokens.Name.RuleTokenToken();
						int _position_NAME = _position;
						parse_NAME();
						if(_state==SUCCESS){
							_tokenNAME.add(_position_NAME,_token);
						}
						_token=_tokenNAME;
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"atom(atom)");
								_furthestPosition=_position;
							}
							_position=_position_atom;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_atom.addAll(_token);
						}
						_token=_token_atom;
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
			public ImportsFileImport(final Parser.NameList initalSuperListNamesRoot) {
				super(initalSuperListNamesRoot);
			}
			public ImportsFileImport() {
			}
			public void _parse_root(){
				parse_imports__file_import();
			}
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
				if(position<newEntry.length()){
					if(children[newEntry.charAt(position)]==null){
						children[newEntry.charAt(position)]=new Parser.NameList.Node();
						++numberOfEntries;
					}
					return children[newEntry.charAt(position)].add(newEntry,position+1);
				}
				else{
					if(value==null||!value.equals(newEntry)){
						value=newEntry;
						return true;
					}
					else{
						return false;
					}
				}
			}
			public String get(int position,int length,char[] input){
				if(position>=length){
					return value;
				}
				if(children[input[position]]!=null){
					String result = children[input[position]].get(position+1,length,input);
					if(result!=null){
						return result;
					}
					else{
						return value;
					}
				}
				return value;
			}
			public int remove(String toRemove,int index){
				if(index>=toRemove.length()){
					if(toRemove.equals(value)){
						value=null;
						return 0;
					}
					else{
						return 2;
					}
				}
				else{
					if(children[toRemove.charAt(index)]!=null){
						int result = children[toRemove.charAt(index)].remove(toRemove,index+1);
						if(result<2){
							if(numberOfEntries>1 ){
								children[toRemove.charAt(index)]=null;
								--numberOfEntries;
							}
							return numberOfEntries;
						}
						else{
							return result;
						}
					}
					else{
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
					if(allEntries.add(newEntry)){
						return super.add(newEntry,0);
					}
					else{
						return false;
					}
				}
				public Set<String> list(){
					return allEntries;
				}
				public void clear(){
					for(String entry:allEntries){
						children[entry.charAt(0)]=null;
					}
				}
				public void removeAll(Parser.NameList.Node.Root fromNode){
					for(String entry:fromNode.allEntries){
						remove(entry,0);
					}
					allEntries.removeAll(fromNode.allEntries);
					numberOfEntries=allEntries.size();
				}
				public void addAll(Parser.NameList.Node.Root fromNode){
					for(String entry:fromNode.allEntries){
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
				synchronized(this){
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
				if(result!=null){
					if(parentResult==null){
						return result;
					}
					else if(parentResult.length()<result.length()){
						return result;
					}
					else{
						return parentResult;
					}
				}
				else{
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
						return "\n\tError: "+errorAt+"\n\tLine Number: "+lineNumber;
					}
					else{
						return "\n\tError: "+errorAt+"\n\tLine Number: "+lineNumber+"\n\tFile Name: "+fileName;
					}
				}
				else{
					if(position==-1 ){
						if(parseTime<1000 ){
							return "File Name: "+fileName+"\nParse Time: "+parseTime+"ms";
						}
						else{
							return "File Name: "+fileName+"\nParse Time: "+parseTime/1000 +"."+parseTime%1000 +"s";
						}
					}
					else{
						if(parseTime<1000 ){
							return "\n\tError: "+errorAt+"\n\tLine Number: "+lineNumber+"\n\tFile Name: "+fileName+"\n\tParse Time: "+parseTime+"ms";
						}
						else{
							return "\n\tError: "+errorAt+"\n\tLine Number: "+lineNumber+"\n\tFile Name: "+fileName+"\n\tParse Time: "+parseTime/1000 +"."+parseTime%1000 +"s";
						}
					}
				}
			}
			else{
				if(parseTime==0 ){
					if(fileName==null){
						return "";
					}
					else{
						return "File Name: "+fileName;
					}
				}
				else{
					if(parseTime<1000 ){
						return "File Name: "+fileName+"\nParse Time: "+parseTime+"ms";
					}
					else{
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
					return "Passed:\n\t"+super.toString();
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
				for(Parser.Result result:results){
					String resultString = result.toString();
					if(resultString!=null){
						builder.append("\n");
						builder.append(resultString);
					}
				}
				return super.toString()+builder.toString();
			}
		}
	}
}