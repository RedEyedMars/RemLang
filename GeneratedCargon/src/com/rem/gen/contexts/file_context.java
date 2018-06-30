package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.imports_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class file_context extends imports_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public file_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public file_context() {
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
	public void parse_FILE_NAME() {
		int _position_FILE_NAME = -1;
		Token.Parsed _token_FILE_NAME = null;
		list_names.start(_position);
		_position_FILE_NAME=_position;
		_token_FILE_NAME=_token;
		_token=new Tokens.Rule.FILENAMEToken();
		int _position_regex_1 = _position;
		int _multiple_index_3 = 0;
		while(_position<_inputLength) {
			if(_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n') {
				++_position;
				++_multiple_index_3;
			}
			else {
				break;
			}
		}
		if(_multiple_index_3==0 ) {
			_state=FAILED;
		}
		if(_state==SUCCESS) {
			_token.setValue(_input.substring(_position_regex_1,_position));
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[\\\\s]+");
				_furthestPosition=_position;
			}
			_position=_position_regex_1;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"FILE_NAME(FILE_NAME)");
				_furthestPosition=_position;
			}
			_position=_position_FILE_NAME;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_FILE_NAME.add(_position_FILE_NAME,_token);
		}
		_token=_token_FILE_NAME;
		if(_state==FAILED) {
			list_names.reject(_position_FILE_NAME);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_FILE_NAME);
		}
	}
}