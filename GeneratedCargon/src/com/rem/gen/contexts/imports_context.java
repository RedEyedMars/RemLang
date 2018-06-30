package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import java.io.File;
import com.rem.gen.contexts.AnonymousContext;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class imports_context extends AnonymousContext{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public imports_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public imports_context() {
	}
	public Parser get_Parser() {
		return __parser__;
	}
	public void set_Parser(Parser new_Parser) {
		__parser__ = new_Parser;
	}
	public Tokens get_Tokens() {
		return __tokens__;
	}
	public void set_Tokens(Tokens new_Tokens) {
		__tokens__ = new_Tokens;
	}
	public void parse_imports__file_import() {
		int _position_imports__file_import = -1;
		Token.Parsed _token_imports__file_import = null;
		int _position_base = -1;
		list_names.start(_position);
		_position_imports__file_import=_position;
		_token_imports__file_import=_token;
		_token=new Tokens.Rule.ImportsFileImportToken();
		_position_base=_position;
		if(_state==SUCCESS&&!_recursion_protection_base_0.contains(_position)) {
			_recursion_protection_base_0.add(_position);
			parse_base();
			_recursion_protection_base_0.remove(_position_base);
		}
		else {
			_state=FAILED;
		}
		if(_state==FAILED) {
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_imports__file_import.addAll(_token);
			_token_imports__file_import.setValue(_token.getValue());
		}
		_token=_token_imports__file_import;
		if(_state==FAILED) {
			list_names.reject(_position_imports__file_import);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_imports__file_import);
		}
	}
	public void parse_imports() {
		int _position_imports = -1;
		Token.Parsed _token_imports = null;
		list_names.start(_position);
		_position_imports=_position;
		_token_imports=_token;
		_token=new Tokens.Rule.ImportsToken();
		if(_position+7-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='i') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='m') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='p') {
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='o') {
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='r') {
				_state=FAILED;
			}
			if(_inputArray[_position+5]!='t') {
				_state=FAILED;
			}
			if(_inputArray[_position+6]!=' ') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_3.SYNTAX);
			_position=_position+7;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain import ");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
				_furthestPosition=_position;
			}
			_position=_position_imports;
		}
		else {
			parse_FILE_NAME();
			if(_state==SUCCESS) {
				_import_FILE_NAME_value=_token.getLastValue();
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
					_furthestPosition=_position;
				}
				_position=_position_imports;
			}
			else {
				if(_position+1-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_2.SYNTAX);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"imports(imports)");
						_furthestPosition=_position;
					}
					_position=_position_imports;
				}
				else {
				}
			}
		}
		if(_state==SUCCESS) {
			StringBuilder _fileNameBuilder = new StringBuilder();
			_fileNameBuilder.append(_import_FILE_NAME_value);
			String _import_file_name = _directoryName+_fileNameBuilder.toString();
			if(new File(_import_file_name).exists()) {
				if(Parser.contexts.containsKey(_import_file_name)==false) {
					Parser.Context _import_context = new Parser.Context.ImportsFileImport(list_names_root);
					Parser.contexts.put(_import_file_name,_import_context);
				}
				Parser.contexts.get(_import_file_name).set_root(new Token.Parsed(Token.Id.ROOT));
				Parser.contexts.get(_import_file_name).set_token(Parser.contexts.get(_import_file_name).get_root());
				_token.add(_position,new Token.Parsed.Import(_import_file_name));
				addImportThread(_import_file_name);
			}
			else {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,_directoryName+_fileNameBuilder.toString()+" cannot be found!");
					_furthestPosition=_position;
				}
				_position=_position_imports;
				_state=FAILED;
				System.err.println("Could not find file:"+_directoryName+_fileNameBuilder.toString());
			}
		}
		if(_state==SUCCESS) {
			_token_imports.addAll(_token);
			_token_imports.setValue(_token.getValue());
		}
		_token=_token_imports;
		if(_state==FAILED) {
			list_names.reject(_position_imports);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_imports);
		}
	}
}