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
	protected Parser.NameList list_names_root = new Parser.NameList(null);
	public Parser() {
	}
	public void setSUCCESS(Integer newSUCCESS) {
		SUCCESS = newSUCCESS;
	}
	public void setFAILED(Integer newFAILED) {
		FAILED = newFAILED;
	}
	public void setFIRSTPASS(Integer newFIRSTPASS) {
		FIRST_PASS = newFIRSTPASS;
	}
	public void setSECONDPASS(Integer newSECONDPASS) {
		SECOND_PASS = newSECONDPASS;
	}
	public void setFileNames(Set<String> newFileNames) {
		fileNames = newFileNames;
	}
	public void setContexts(Map<String,Parser.Context> newContexts) {
		contexts = newContexts;
	}
	public Parser.Result parse(String fileName) {
		long startParseTime = System.currentTimeMillis();
		Parser.Result firstResult = parseFile(fileName,FIRST_PASS);
		if(firstResult.getState()==SUCCESS) {
			System.out.println("First-Pass Successful");
			fileNames.clear();
			Parser.Result secondResult = parseFile(fileName,SECOND_PASS);
			if(secondResult.getState()==SUCCESS) {
				System.out.println("Second-Pass Successful");
			}
			else {
				System.out.println("Second-Pass Failed");
			}
			secondResult.setParseTime(System.currentTimeMillis()-startParseTime);
			return secondResult;
		}
		else {
			System.out.println("First-Pass Failed");
			firstResult.setParseTime(System.currentTimeMillis()-startParseTime);
			return firstResult;
		}
	}
	public Parser.Result parseFile(String fileName,int _pass) {
		if(_pass==FIRST_PASS) {
			Parser.Context context = new FinalContext(list_names_root);
			contexts.put(fileName,context);
			return context.parse(fileName,FIRST_PASS);
		}
		else {
			contexts.get(fileName)._root=new Token.Parsed(Token.Id.ROOT);
			contexts.get(fileName)._token=contexts.get(fileName)._root;
			return contexts.get(fileName).parse(fileName,SECOND_PASS);
		}
	}
	public static String readLine(String input,int position) {
		int indexOfLine = input.indexOf('\n',position);
		if(indexOfLine==-1 ) {
			return input.substring(position);
		}
		else {
			return input.substring(position,indexOfLine);
		}
	}
	public Parser.NameList getListNamesRoot() {
		return list_names_root;
	}
	public void setListNamesRoot(Parser.NameList newListNamesRoot) {
		list_names_root = newListNamesRoot;
	}
	public static abstract class Context{
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
		protected Token.Parsed _root = new Token.Parsed(Token.Id.ROOT);
		protected Token.Parsed _token = _root;
		protected Set<ImportThread> _import_threads = new HashSet<ImportThread>();
		protected Map<Integer,Integer> brace_0 = new HashMap<Integer,Integer>();
		protected Parser.NameList list_names_root = null;
		protected Parser.NameList list_names = null;
		protected Map<Integer,String> _preparsed_NAME = new HashMap<Integer,String>();
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
		public Context(final Parser.NameList list_names_root) {
			if(list_names_root != null){
				this.list_names_root = list_names_root;
			}
			list_names=list_names_root.push();
		}
		public Context() {
			list_names=list_names_root.push();
		}
		public Integer getSUCCESS() {
			return SUCCESS;
		}
		public void setSUCCESS(Integer newSUCCESS) {
			SUCCESS = newSUCCESS;
		}
		public Integer getFAILED() {
			return FAILED;
		}
		public void setFAILED(Integer newFAILED) {
			FAILED = newFAILED;
		}
		public Integer getFIRSTPASS() {
			return FIRST_PASS;
		}
		public void setFIRSTPASS(Integer newFIRSTPASS) {
			FIRST_PASS = newFIRSTPASS;
		}
		public Integer getSECONDPASS() {
			return SECOND_PASS;
		}
		public void setSECONDPASS(Integer newSECONDPASS) {
			SECOND_PASS = newSECONDPASS;
		}
		public int get_pass() {
			return _pass;
		}
		public void set_pass(int new_pass) {
			_pass = new_pass;
		}
		public int get_position() {
			return _position;
		}
		public void set_position(int new_position) {
			_position = new_position;
		}
		public int get_inputLength() {
			return _inputLength;
		}
		public void set_inputLength(int new_inputLength) {
			_inputLength = new_inputLength;
		}
		public int get_state() {
			return _state;
		}
		public void set_state(int new_state) {
			_state = new_state;
		}
		public int get_furthestPosition() {
			return _furthestPosition;
		}
		public void set_furthestPosition(int new_furthestPosition) {
			_furthestPosition = new_furthestPosition;
		}
		public int get_lineNumber() {
			return _lineNumber;
		}
		public void set_lineNumber(int new_lineNumber) {
			_lineNumber = new_lineNumber;
		}
		public String get_input() {
			return _input;
		}
		public void set_input(String new_input) {
			_input = new_input;
		}
		public String get_directoryName() {
			return _directoryName;
		}
		public void set_directoryName(String new_directoryName) {
			_directoryName = new_directoryName;
		}
		public String get_fileName() {
			return _fileName;
		}
		public void set_fileName(String new_fileName) {
			_fileName = new_fileName;
		}
		public char[] get_inputArray() {
			return _inputArray;
		}
		public void set_inputArray(char[] new_inputArray) {
			_inputArray = new_inputArray;
		}
		public Parser.Result get_result() {
			return _result;
		}
		public void set_result(Parser.Result new_result) {
			_result = new_result;
		}
		public Parser.Result.Acceptor get_resultAcceptor() {
			return _result_acceptor;
		}
		public void set_resultAcceptor(Parser.Result.Acceptor new_resultAcceptor) {
			_result_acceptor = new_resultAcceptor;
		}
		public Boolean get_succeedOnEnd() {
			return _succeedOnEnd;
		}
		public void set_succeedOnEnd(Boolean new_succeedOnEnd) {
			_succeedOnEnd = new_succeedOnEnd;
		}
		public String get_listNameResult() {
			return _list_name_result;
		}
		public void set_listNameResult(String new_listNameResult) {
			_list_name_result = new_listNameResult;
		}
		public List<Integer> get_lineNumberRanges() {
			return _lineNumberRanges;
		}
		public void set_lineNumberRanges(List<Integer> new_lineNumberRanges) {
			_lineNumberRanges = new_lineNumberRanges;
		}
		public Token.Parsed get_root() {
			return _root;
		}
		public void set_root(Token.Parsed new_root) {
			_root = new_root;
		}
		public Token.Parsed get_token() {
			return _token;
		}
		public void set_token(Token.Parsed new_token) {
			_token = new_token;
		}
		public Set<ImportThread> get_importThreads() {
			return _import_threads;
		}
		public void set_importThreads(Set<ImportThread> new_importThreads) {
			_import_threads = new_importThreads;
		}
		public Parser.Result parse(String _fileName,int _pass_index) {
			Stack<Integer> brace_open_3 = new Stack<Integer>();
			Stack<Integer> brace_open_2 = new Stack<Integer>();
			Stack<Integer> brace_open_1 = new Stack<Integer>();
			Parser.NameList.Builder _builder_NAME = new Parser.NameList.Builder.NAME(_preparsed_NAME);
			Stack<Integer> brace_open_0 = new Stack<Integer>();
			if(fileNames.add(_fileName)==false) {
				return null;
			}
			_pass=_pass_index;
			_directoryName="./";
			int indexOfDirectorySlash = _fileName.lastIndexOf("/");
			if(indexOfDirectorySlash==-1 ) {
				indexOfDirectorySlash=_fileName.lastIndexOf("\\");
			}
			if(indexOfDirectorySlash>-1 ) {
				_directoryName=_fileName.substring(0,indexOfDirectorySlash+1);
				_fileName=_fileName.substring(indexOfDirectorySlash+1);
			}
			StringBuilder _inputBuffer = new StringBuilder();
			try {
				BufferedReader _inputReader = new BufferedReader(new FileReader(_directoryName+_fileName));
				int _readInput = _inputReader.read();
				boolean escaping = false;
				boolean quoting = false;
				while(_readInput>=0 ) {
					_builder_NAME.add(_position,(char)_readInput);
					if(_readInput!=13 ) {
						_inputBuffer.append((char)_readInput);
					}
					if(_readInput=='\n') {
						_lineNumberRanges.add(_position);
					}
					if(escaping) {
						escaping=false;
					}
					else if(!escaping&&_readInput=='\\') {
						escaping=true;
					}
					else if(!quoting&&_readInput=='\"') {
						quoting=true;
						brace_open_0.push(_position);
					}
					else if(quoting&&_readInput=='\"') {
						quoting=false;
						brace_0.put(brace_open_0.pop(),_position);
					}
					else if(!quoting&&!escaping) {
						if(_readInput=='#') {
							if(!brace_open_1.isEmpty()) {
								brace_1.put(brace_open_1.pop(),_position);
							}
						}
						if(_readInput==')') {
							if(!brace_open_2.isEmpty()) {
								brace_2.put(brace_open_2.pop(),_position);
							}
						}
						if(_readInput==']') {
							if(!brace_open_3.isEmpty()) {
								brace_3.put(brace_open_3.pop(),_position);
							}
						}
						if(_readInput=='#') {
							brace_open_1.push(_position);
						}
						if(_readInput=='(') {
							brace_open_2.push(_position);
						}
						if(_readInput=='[') {
							brace_open_3.push(_position);
						}
					}
					if(_readInput!=13 ) {
						++_position;
					}
					_readInput_1=_readInput;
					_readInput=_inputReader.read();
				}
				_lineNumberRanges.add(_position);
				_inputReader.close();
			}
			catch(IOException e0) {
			}
			_builder_NAME.end(_position);
			_input=_inputBuffer.toString();
			_inputArray=_input.toCharArray();
			_inputLength=_inputArray.length;
			this._fileName=_fileName;
			_furthestPosition=0;
			_result=null;
			_position=0;
			_state=SUCCESS;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			if(_pass==Parser.SECOND_PASS) {
				list_names.extend();
			}
			_parse_root();
			if(_pass==Parser.FIRST_PASS) {
				list_names.keep();
			}
			try {
				for(ImportThread _import_thread:_import_threads) {
					_import_thread.join();
					if(_import_thread.getContext().get_state()==FAILED) {
						_state=FAILED;
					}
				}
			}
			catch(Exception e0) {
			}
			_import_threads.clear();
			if(_state==SUCCESS&&_position==_inputLength) {
				if(_succeedOnEnd) {
					Parser.Result.Pass pass = new Parser.Result.Pass(SUCCESS,_position,_lineNumberRanges,_input,_fileName,_root);
					pass.setup();
					_result=pass;
				}
				else {
					_result_acceptor.setFileName(_fileName);
					_result=_result_acceptor;
					_state=FAILED;
				}
			}
			else if(_state==SUCCESS) {
				if(_result!=null) {
					_result_acceptor.add(_result);
				}
				_result_acceptor.add(new Parser.Result.Fail.EOF(_fileName));
				_result_acceptor.setFileName(_fileName);
				_result=_result_acceptor;
				_state=FAILED;
			}
			else if(_state==FAILED) {
				_result_acceptor.add(_result);
				_result_acceptor.setFileName(_fileName);
				_result=_result_acceptor;
			}
			_result.setListNames(list_names);
			return _result;
		}
		public void addImportThread(String importFileName) {
			synchronized(_import_threads) {
				ImportThread thread = new ImportThread(this,Parser.contexts.get(importFileName),importFileName);
				_import_threads.add(thread);
				thread.start();
			}
		}
		public Map<Integer,Integer> getBrace0() {
			return brace_0;
		}
		public void setBrace0(Map<Integer,Integer> newBrace0) {
			brace_0 = newBrace0;
		}
		public Parser.NameList getListNamesRoot() {
			return list_names_root;
		}
		public void setListNamesRoot(Parser.NameList newListNamesRoot) {
			list_names_root = newListNamesRoot;
		}
		public Parser.NameList getListNames() {
			return list_names;
		}
		public void setListNames(Parser.NameList newListNames) {
			list_names = newListNames;
		}
		public Map<Integer,String> get_preparsedNAME() {
			return _preparsed_NAME;
		}
		public void set_preparsedNAME(Map<Integer,String> new_preparsedNAME) {
			_preparsed_NAME = new_preparsedNAME;
		}
		public Map<Integer,Integer> getBrace1() {
			return brace_1;
		}
		public void setBrace1(Map<Integer,Integer> newBrace1) {
			brace_1 = newBrace1;
		}
		public String get_importFILENAMEValue() {
			return _import_FILE_NAME_value;
		}
		public void set_importFILENAMEValue(String new_importFILENAMEValue) {
			_import_FILE_NAME_value = new_importFILENAMEValue;
		}
		public Set<Integer> get_recursionProtectionBase0() {
			return _recursion_protection_base_0;
		}
		public void set_recursionProtectionBase0(Set<Integer> new_recursionProtectionBase0) {
			_recursion_protection_base_0 = new_recursionProtectionBase0;
		}
		public Map<Integer,Integer> getBrace2() {
			return brace_2;
		}
		public void setBrace2(Map<Integer,Integer> newBrace2) {
			brace_2 = newBrace2;
		}
		public Set<Integer> get_recursionProtectionDefinition1() {
			return _recursion_protection_definition_1;
		}
		public void set_recursionProtectionDefinition1(Set<Integer> new_recursionProtectionDefinition1) {
			_recursion_protection_definition_1 = new_recursionProtectionDefinition1;
		}
		public Map<Integer,Integer> getBrace3() {
			return brace_3;
		}
		public void setBrace3(Map<Integer,Integer> newBrace3) {
			brace_3 = newBrace3;
		}
		public Set<Integer> get_recursionProtectionRegexDefinition2() {
			return _recursion_protection_regex_definition_2;
		}
		public void set_recursionProtectionRegexDefinition2(Set<Integer> new_recursionProtectionRegexDefinition2) {
			_recursion_protection_regex_definition_2 = new_recursionProtectionRegexDefinition2;
		}
		public Set<Integer> get_recursionProtectionNAME3() {
			return _recursion_protection_NAME_3;
		}
		public void set_recursionProtectionNAME3(Set<Integer> new_recursionProtectionNAME3) {
			_recursion_protection_NAME_3 = new_recursionProtectionNAME3;
		}
		public Set<Integer> get_recursionProtectionNAME4() {
			return _recursion_protection_NAME_4;
		}
		public void set_recursionProtectionNAME4(Set<Integer> new_recursionProtectionNAME4) {
			_recursion_protection_NAME_4 = new_recursionProtectionNAME4;
		}
		public int get_readInput1() {
			return _readInput_1;
		}
		public void set_readInput1(int new_readInput1) {
			_readInput_1 = new_readInput1;
		}
		public abstract void parse_imports__file_import();
		public abstract void parse_FILE_NAME();
		public abstract void parse__anonymous_6();
		public abstract void parse_rule_params();
		public abstract void parse__anonymous_5();
		public abstract void parse_ignores();
		public abstract void parse__anonymous_8();
		public abstract void parse_quote();
		public abstract void parse__anonymous_7();
		public abstract void parse__anonymous_2();
		public abstract void parse__anonymous_1();
		public abstract void parse__anonymous_4();
		public abstract void parse__anonymous_3();
		public abstract void parse_base_element();
		public abstract void parse_regex_special();
		public abstract void parse__anonymous_9();
		public abstract void parse_element();
		public abstract void parse_braced_definition();
		public abstract void parse_NUMBER();
		public abstract void parse_imports();
		public abstract void parse_rule_parameters();
		public abstract void parse__anonymous_0();
		public abstract void parse_list();
		public abstract void parse_NAME();
		public abstract void parse_single_ignores_character();
		public abstract void parse_regex();
		public abstract void parse_regex_group_definition();
		public abstract void parse_regex_option_definition();
		public abstract void parse_regex_option();
		public abstract void parse__anonymous_22();
		public abstract void parse_rule();
		public abstract void parse__anonymous_21();
		public abstract void parse__anonymous_24();
		public abstract void parse__anonymous_23();
		public abstract void parse__anonymous_25();
		public abstract void parse__anonymous_20();
		public abstract void parse_definition();
		public abstract void parse__anonymous_19();
		public abstract void parse__anonymous_18();
		public abstract void parse_comments();
		public abstract void parse__anonymous_11();
		public abstract void parse__anonymous_10();
		public abstract void parse__anonymous_13();
		public abstract void parse__anonymous_12();
		public abstract void parse_regex_definition();
		public abstract void parse__anonymous_15();
		public abstract void parse__anonymous_14();
		public abstract void parse__anonymous_17();
		public abstract void parse__anonymous_16();
		public abstract void parse_OPERATOR();
		public abstract void parse_regex_element();
		public abstract void parse_atom();
		public abstract void parse_base();
		public void _parse_root() {
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
				this.fileName = fileName;
			}
			public ImportThread() {
			}
			public Parser.Context getParentContext() {
				return parentContext;
			}
			public void setParentContext(Parser.Context newParentContext) {
				parentContext = newParentContext;
			}
			public Parser.Context getContext() {
				return context;
			}
			public void setContext(Parser.Context newContext) {
				context = newContext;
			}
			public String getFileName() {
				return fileName;
			}
			public void setFileName(String newFileName) {
				fileName = newFileName;
			}
			public void run() {
				Parser.Result result = context.parse(fileName,parentContext.get_pass());
				if(result!=null) {
					parentContext.get_resultAcceptor().add(result);
					result.setFileName(fileName);
				}
			}
		}
		public static class ImportsFileImport extends FinalContext{
			public ImportsFileImport(final Parser.NameList initalSuperListNamesRoot) {
				super(initalSuperListNamesRoot);
			}
			public ImportsFileImport() {
			}
			public void _parse_root() {
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
		public NameList getParent() {
			return parent;
		}
		public void setParent(NameList newParent) {
			parent = newParent;
		}
		public Map<Integer,NameList> getChildren() {
			return children;
		}
		public void setChildren(Map<Integer,NameList> newChildren) {
			children = newChildren;
		}
		public List<NameList.Addition> getAdditions() {
			return additions;
		}
		public void setAdditions(List<NameList.Addition> newAdditions) {
			additions = newAdditions;
		}
		public int getAdditionPosition() {
			return addition_position;
		}
		public void setAdditionPosition(int newAdditionPosition) {
			addition_position = newAdditionPosition;
		}
		public NameList push(int position,int pass) {
			NameList result = null;
			if(pass==Parser.SECOND_PASS) {
				synchronized(children) {
					result=children.get(position);
				}
			}
			if(result==null) {
				result=new NameList(this);
				if(pass==Parser.FIRST_PASS) {
					synchronized(children) {
						children.put(position,result);
					}
				}
			}
			else {
				result.clear();
			}
			synchronized(this) {
				synchronized(result) {
					for(String newEntry:this) {
						result.safe_add(newEntry);
					}
				}
			}
			return result;
		}
		public NameList push() {
			NameList result = new NameList(this);
			synchronized(this) {
				synchronized(result) {
					for(String newEntry:this) {
						result.safe_add(newEntry);
					}
				}
			}
			return result;
		}
		public NameList pop() {
			return parent;
		}
		public boolean add(String addition) {
			synchronized(this) {
				if(super.add(addition)) {
					additions.add(new NameList.Addition(addition_position,addition));
					return true;
				}
				else {
					return false;
				}
			}
		}
		public boolean safe_add(String addition) {
			return super.add(addition);
		}
		public void start(int position) {
			addition_position=position;
		}
		public void accept(int position) {
			addition_position=position;
		}
		public void reject(int position) {
			synchronized(this) {
				addition_position=position;
				int i = additions.size()-1;
				while(i>=0 ) {
					if(additions.get(i).getPosition()>=addition_position) {
						remove(additions.remove(i).getEntry());
					}
					else {
						break;
					}
					--i;
				}
			}
		}
		public void extend() {
			synchronized(parent) {
				for(String entry:parent) {
					safe_add(entry);
				}
			}
		}
		public void keep() {
			synchronized(this) {
				synchronized(parent) {
					for(NameList.Addition addition:additions) {
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
				this.position = position;
				this.entry = entry;
			}
			public Addition() {
			}
			public Integer getPosition() {
				return position;
			}
			public void setPosition(Integer newPosition) {
				position = newPosition;
			}
			public String getEntry() {
				return entry;
			}
			public void setEntry(String newEntry) {
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
			public StringBuilder getBuilder() {
				return builder;
			}
			public void setBuilder(StringBuilder newBuilder) {
				builder = newBuilder;
			}
			public int getLength() {
				return length;
			}
			public void setLength(int newLength) {
				length = newLength;
			}
			public int getState() {
				return state;
			}
			public void setState(int newState) {
				state = newState;
			}
			public boolean getMultipleSatisfied() {
				return multiple_satisfied;
			}
			public void setMultipleSatisfied(boolean newMultipleSatisfied) {
				multiple_satisfied = newMultipleSatisfied;
			}
			public Map<Integer,String> get_output() {
				return _output;
			}
			public void set_output(Map<Integer,String> new_output) {
				_output = new_output;
			}
			public boolean can(int position,char newChar) {
				return false;
			}
			public boolean add(int position,char newChar) {
				if(can(position,newChar)) {
					if(builder==null) {
						builder=new StringBuilder();
						length=0;
					}
					builder.append(newChar);
					length+=1;
					return true;
				}
				else {
					if(builder!=null&&state>=0 ) {
						String result = builder.toString();
						_output.put(position-length,result);
					}
					builder=null;
					state=0;
					return false;
				}
			}
			public void end(int position) {
				if(builder!=null&&state>=0 ) {
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
				public boolean can(int position,char nextChar) {
					switch(state) {
						case 0: {
							if((nextChar>='a'&&nextChar<='z')||(nextChar>='A'&&nextChar<='Z')||nextChar=='_') {
								state=1;
								return true;
							}
							else {
								state=-1;
								return false;
							}
						}
						case 1: {
							if((nextChar>='a'&&nextChar<='z')||(nextChar>='A'&&nextChar<='Z')||(nextChar>='0'&&nextChar<='9')||nextChar=='_') {
								return true;
							}
							else {
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
		protected Set<String> list_names = null;
		public Result(final Integer state, final Integer position, final List<Integer> lineNumberRanges, final String input, final String fileName) {
			this.state = state;
			this.position = position;
			if(lineNumberRanges != null){
				this.lineNumberRanges = lineNumberRanges;
			}
			this.input = input;
			this.fileName = fileName;
		}
		public Result() {
		}
		public Integer getState() {
			return state;
		}
		public void setState(Integer newState) {
			state = newState;
		}
		public Integer getPosition() {
			return position;
		}
		public void setPosition(Integer newPosition) {
			position = newPosition;
		}
		public List<Integer> getLineNumberRanges() {
			return lineNumberRanges;
		}
		public void setLineNumberRanges(List<Integer> newLineNumberRanges) {
			lineNumberRanges = newLineNumberRanges;
		}
		public String getInput() {
			return input;
		}
		public void setInput(String newInput) {
			input = newInput;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String newFileName) {
			fileName=newFileName;
		}
		public long getParseTime() {
			return parseTime;
		}
		public void setParseTime(long newParseTime) {
			parseTime = newParseTime;
		}
		public Integer getLineNumber(Integer position) {
			Integer upperBound = 0;
			Integer lineNumber = 0;
			while(lineNumber<lineNumberRanges.size()&&upperBound<position) {
				upperBound=lineNumberRanges.get(lineNumber);
				lineNumber+=1;
			}
			return lineNumber;
		}
		public Integer getLineNumber() {
			Integer upperBound = 0;
			Integer lineNumber = 0;
			while(lineNumber<lineNumberRanges.size()&&upperBound<position) {
				upperBound=lineNumberRanges.get(lineNumber);
				lineNumber+=1;
			}
			return lineNumber;
		}
		public String toString() {
			if(state==Parser.FAILED) {
				Integer lineNumber = getLineNumber();
				Integer rangeIndex = lineNumber-1;
				if(rangeIndex<0 ) {
					rangeIndex=0;
				}
				Integer upperBound = lineNumberRanges.get(rangeIndex);
				Integer lowerBound = 0;
				if(rangeIndex>0 ) {
					lowerBound=lineNumberRanges.get(rangeIndex-1)+1;
				}
				String errorAt;
				if(upperBound<input.length()) {
					errorAt=input.substring(lowerBound,position)+"$>"+input.substring(position,upperBound);
				}
				else {
					errorAt=input.substring(lowerBound,position)+"<$"+input.substring(position);
				}
				if(parseTime<=0 ) {
					if(fileName==null) {
						return "\n\tLine Number: "+lineNumber+"\n\tError: "+errorAt;
					}
					else {
						return "\nFile: "+fileName+" Line : "+lineNumber+"\n\tError: "+errorAt;
					}
				}
				else {
					if(position==-1 ) {
						if(parseTime<1000 ) {
							return "File: "+fileName+"\nParse Time: "+parseTime+"ms";
						}
						else {
							int minutes = (int)(parseTime/1000);
							int hundreds = (int)(parseTime/100)%10;
							int tens = (int)(parseTime/10)%10;
							int ones = ((int)parseTime)%10;
							return "File: "+fileName+"\nParse Time: "+minutes+"."+hundreds+""+tens+""+ones+"s";
						}
					}
					else {
						if(parseTime<1000 ) {
							return "\n\tError: "+errorAt+"\n\tFile: "+fileName+" Line : "+lineNumber+"\n\tParse Time: "+parseTime+"ms";
						}
						else {
							int minutes = (int)(parseTime/1000);
							int hundreds = (int)(parseTime/100)%10;
							int tens = (int)(parseTime/10)%10;
							int ones = ((int)parseTime)%10;
							return "\n\tError: "+errorAt+"\n\tFile: "+fileName+" Line: "+lineNumber+"\n\tParse Time: "+minutes+"."+hundreds+""+tens+""+ones+"s";
						}
					}
				}
			}
			else {
				if(parseTime<=0 ) {
					if(fileName==null) {
						return "";
					}
					else {
						return "File: "+fileName;
					}
				}
				else {
					if(parseTime<1000 ) {
						if(fileName==null) {
							return "Parse Time: "+parseTime+"ms";
						}
						else {
							return "File: "+fileName+"\nParse Time: "+parseTime+"ms";
						}
					}
					else {
						int minutes = (int)(parseTime/1000);
						int hundreds = (int)(parseTime/100)%10;
						int tens = (int)(parseTime/10)%10;
						int ones = ((int)parseTime)%10;
						if(fileName==null) {
							return "Parse Time: "+minutes+"."+hundreds+""+tens+""+ones+"s";
						}
						else {
							return "File: "+fileName+"\nParse Time: "+minutes+"."+hundreds+""+tens+""+ones+"s";
						}
					}
				}
			}
		}
		public Set<String> getListNames() {
			return list_names;
		}
		public void setListNames(Set<String> newListNames) {
			list_names = newListNames;
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
			public Token.Parsed getParsedRoot() {
				return parsedRoot;
			}
			public void setParsedRoot(Token.Parsed newParsedRoot) {
				parsedRoot = newParsedRoot;
			}
			public Token.Branch getRoot() {
				return root;
			}
			public void setRoot(Token.Branch newRoot) {
				root = newRoot;
			}
			public void setup() {
				root=new Token.Branch();
				setup(root,parsedRoot,0);
			}
			public void setup(Token.Branch current,Token.Parsed currentParsed,Integer currentPosition) {
				List<Token.Parsed> children = currentParsed.getChildren();
				List<Integer> positions = currentParsed.getPositions();
				Integer size = currentParsed.getChildren().size();
				for(Integer i = 0;i<size;i++) {
					if(children.get(i).getChildren().isEmpty()==false) {
						if(children.get(i).getName()==null) {
							setup(current,children.get(i),positions.get(i));
						}
						else {
							Token.Branch newToken = new Token.Branch(children.get(i).getName(),positions.get(i),currentPosition,this);
							current.add(newToken);
							setup(newToken,children.get(i),positions.get(i));
						}
					}
					else {
						current.add(new Token.Leaf(children.get(i).getName(),children.get(i).getValue(),positions.get(i),currentPosition,this));
					}
				}
			}
			public String toString() {
				if(fileName!=null) {
					String realFileName = fileName;
					fileName=null;
					String result = super.toString();
					fileName=realFileName;
					if(result.equals("")) {
						return null;
					}
					else {
						return "Passed: "+result;
					}
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
				this.ruleName = ruleName;
			}
			public Fail(final String ruleName) {
				this.ruleName = ruleName;
			}
			public Fail() {
			}
			public String getRuleName() {
				return ruleName;
			}
			public void setRuleName(String newRuleName) {
				ruleName = newRuleName;
			}
			public String toString() {
				return "Failed: "+ruleName+super.toString();
			}
			public static class EOF extends Parser.Result{
				protected String erroneousFile = null;
				public EOF(final Integer initalSuperState, final Integer initalSuperPosition, final List<Integer> initalSuperLineNumberRanges, final String initalSuperInput, final String initalSuperFileName, final String erroneousFile) {
					super(initalSuperState, initalSuperPosition, initalSuperLineNumberRanges, initalSuperInput, initalSuperFileName);
					this.erroneousFile = erroneousFile;
				}
				public EOF(final String erroneousFile) {
					this.erroneousFile = erroneousFile;
				}
				public EOF() {
				}
				public String getErroneousFile() {
					return erroneousFile;
				}
				public void setErroneousFile(String newErroneousFile) {
					erroneousFile = newErroneousFile;
				}
				public String toString() {
					return "End of file not reached:"+erroneousFile;
				}
			}
			public static class EOB extends Parser.Result{
				protected String myRuleName = null;
				protected Integer myPosition = -1;
				protected List<Integer> myLineNumberRanges = null;
				public EOB(final Integer initalSuperState, final Integer initalSuperPosition, final List<Integer> initalSuperLineNumberRanges, final String initalSuperInput, final String initalSuperFileName, final String myRuleName, final Integer myPosition, final List<Integer> myLineNumberRanges) {
					super(initalSuperState, initalSuperPosition, initalSuperLineNumberRanges, initalSuperInput, initalSuperFileName);
					this.myRuleName = myRuleName;
					this.myPosition = myPosition;
					if(myLineNumberRanges != null){
						this.myLineNumberRanges = myLineNumberRanges;
					}
				}
				public EOB(final String myRuleName, final Integer myPosition, final List<Integer> myLineNumberRanges) {
					this.myRuleName = myRuleName;
					this.myPosition = myPosition;
					if(myLineNumberRanges != null){
						this.myLineNumberRanges = myLineNumberRanges;
					}
				}
				public EOB() {
				}
				public String getMyRuleName() {
					return myRuleName;
				}
				public void setMyRuleName(String newMyRuleName) {
					myRuleName = newMyRuleName;
				}
				public Integer getMyPosition() {
					return myPosition;
				}
				public void setMyPosition(Integer newMyPosition) {
					myPosition = newMyPosition;
				}
				public List<Integer> getMyLineNumberRanges() {
					return myLineNumberRanges;
				}
				public void setMyLineNumberRanges(List<Integer> newMyLineNumberRanges) {
					myLineNumberRanges = newMyLineNumberRanges;
				}
				public String toString() {
					lineNumberRanges=myLineNumberRanges;
					position=myPosition;
					return "End of brace not reached by ["+myRuleName+"]:"+getLineNumber();
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
			public List<Parser.Result> getResults() {
				return results;
			}
			public void setResults(List<Parser.Result> newResults) {
				results = newResults;
			}
			public void add(Parser.Result result) {
				result.setFileName(null);
				results.add(result);
			}
			public String toString() {
				StringBuilder builder = new StringBuilder();
				for(Parser.Result result:results) {
					if(result!=null) {
						String resultString = result.toString();
						if(resultString!=null) {
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