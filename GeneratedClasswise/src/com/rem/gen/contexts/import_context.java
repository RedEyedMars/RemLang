package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import java.io.File;
import com.rem.gen.contexts.weak_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class import_context extends weak_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public import_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public import_context() {
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
	public void parse_import_imports__file_import() {
		int _position_import_imports__file_import = -1;
		Token.Parsed _token_import_imports__file_import = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_import_imports__file_import=_position;
		_token_import_imports__file_import=_token;
		_token=new Tokens.Rule.ImportImportsFileImportToken();
		int _state_22 = _state;
		while(_position<_inputLength) {
			parse_anonymous_classes();
			if(_state==FAILED) {
				break;
			}
		}
		if(_state_22==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_import_imports__file_import.addAll(_token);
			_token_import_imports__file_import.setValue(_token.getValue());
		}
		_token=_token_import_imports__file_import;
		if(_state==FAILED) {
			class_names.reject(_position_import_imports__file_import);
			class_variable_names.reject(_position_import_imports__file_import);
			variable_names.reject(_position_import_imports__file_import);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_import_imports__file_import);
			class_variable_names.accept(_position_import_imports__file_import);
			variable_names.accept(_position_import_imports__file_import);
		}
	}
	public void parse_import_imports() {
		int _position_import_imports = -1;
		Token.Parsed _token_import_imports = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_import_imports=_position;
		_token_import_imports=_token;
		_token=new Tokens.Rule.ImportImportsToken();
		if(_position+8-1 >=_inputLength) {
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
			if(_inputArray[_position+6]!='s') {
				_state=FAILED;
			}
			if(_inputArray[_position+7]!=' ') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_11.SYNTAX);
			_position=_position+8;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain imports ");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"import_imports(import_imports)");
				_furthestPosition=_position;
			}
			_position=_position_import_imports;
		}
		else {
			parse_class_file_name();
			if(_state==SUCCESS) {
				_import_class_file_name_value=_token.getLastValue();
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"import_imports(import_imports)");
					_furthestPosition=_position;
				}
				_position=_position_import_imports;
			}
			else {
				if(_position+0-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_10.SYNTAX);
					_position=_position+0;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"import_imports(import_imports)");
						_furthestPosition=_position;
					}
					_position=_position_import_imports;
				}
				else {
				}
			}
		}
		if(_state==SUCCESS) {
			StringBuilder _fileNameBuilder = new StringBuilder();
			_fileNameBuilder.append(_import_class_file_name_value);
			_fileNameBuilder.append(".imports");
			String _import_file_name = _directoryName+_fileNameBuilder.toString();
			if(new File(_import_file_name).exists()) {
				if(Parser.contexts.containsKey(_import_file_name)==false) {
					Parser.Context _import_context = new Parser.Context.ImportImportsFileImport(class_names_root,class_variable_names_root,variable_names_root);
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
				_position=_position_import_imports;
				_state=FAILED;
				System.err.println("Could not find file:"+_directoryName+_fileNameBuilder.toString());
			}
		}
		if(_state==SUCCESS) {
			_token_import_imports.add(_position_import_imports,_token);
		}
		_token=_token_import_imports;
		if(_state==FAILED) {
			class_names.reject(_position_import_imports);
			class_variable_names.reject(_position_import_imports);
			variable_names.reject(_position_import_imports);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_import_imports);
			class_variable_names.accept(_position_import_imports);
			variable_names.accept(_position_import_imports);
		}
	}
	public void parse_import_clws__file_import() {
		int _position_import_clws__file_import = -1;
		Token.Parsed _token_import_clws__file_import = null;
		int _position_base_element = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_import_clws__file_import=_position;
		_token_import_clws__file_import=_token;
		_token=new Tokens.Rule.ImportClwsFileImportToken();
		int _state_21 = _state;
		while(_position<_inputLength) {
			_position_base_element=_position;
			if(_state==SUCCESS&&!_recursion_protection_base_element_5.contains(_position)) {
				_recursion_protection_base_element_5.add(_position);
				parse_base_element();
				_recursion_protection_base_element_5.remove(_position_base_element);
			}
			else {
				_state=FAILED;
			}
			if(_state==FAILED) {
				break;
			}
		}
		if(_state_21==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_import_clws__file_import.addAll(_token);
			_token_import_clws__file_import.setValue(_token.getValue());
		}
		_token=_token_import_clws__file_import;
		if(_state==FAILED) {
			class_names.reject(_position_import_clws__file_import);
			class_variable_names.reject(_position_import_clws__file_import);
			variable_names.reject(_position_import_clws__file_import);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_import_clws__file_import);
			class_variable_names.accept(_position_import_clws__file_import);
			variable_names.accept(_position_import_clws__file_import);
		}
	}
	public void parse_import_clws() {
		int _position_import_clws = -1;
		Token.Parsed _token_import_clws = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_import_clws=_position;
		_token_import_clws=_token;
		_token=new Tokens.Rule.ImportClwsToken();
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
			_token.add(_position,Tokens.Syntax.syntax_9.SYNTAX);
			_position=_position+7;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
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
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"import_clws(import_clws)");
				_furthestPosition=_position;
			}
			_position=_position_import_clws;
		}
		else {
			parse_class_file_name();
			if(_state==SUCCESS) {
				_import_class_file_name_value=_token.getLastValue();
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"import_clws(import_clws)");
					_furthestPosition=_position;
				}
				_position=_position_import_clws;
			}
			else {
				if(_position+0-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_10.SYNTAX);
					_position=_position+0;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"import_clws(import_clws)");
						_furthestPosition=_position;
					}
					_position=_position_import_clws;
				}
				else {
				}
			}
		}
		if(_state==SUCCESS) {
			StringBuilder _fileNameBuilder = new StringBuilder();
			_fileNameBuilder.append(_import_class_file_name_value);
			_fileNameBuilder.append(".clws");
			String _import_file_name = _directoryName+_fileNameBuilder.toString();
			if(new File(_import_file_name).exists()) {
				if(Parser.contexts.containsKey(_import_file_name)==false) {
					Parser.Context _import_context = new Parser.Context.ImportClwsFileImport(class_names_root,class_variable_names_root,variable_names_root);
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
				_position=_position_import_clws;
				_state=FAILED;
				System.err.println("Could not find file:"+_directoryName+_fileNameBuilder.toString());
			}
		}
		if(_state==SUCCESS) {
			_token_import_clws.add(_position_import_clws,_token);
		}
		_token=_token_import_clws;
		if(_state==FAILED) {
			class_names.reject(_position_import_clws);
			class_variable_names.reject(_position_import_clws);
			variable_names.reject(_position_import_clws);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_import_clws);
			class_variable_names.accept(_position_import_clws);
			variable_names.accept(_position_import_clws);
		}
	}
}