package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.braced_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class number_context extends braced_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public number_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public number_context() {
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
	public void parse_NUMBER() {
		int _position_NUMBER = -1;
		Token.Parsed _token_NUMBER = null;
		list_names.start(_position);
		_position_NUMBER=_position;
		_token_NUMBER=_token;
		_token=new Tokens.Rule.NUMBERToken();
		int _position_regex_4 = _position;
		if(_position<_inputLength) {
			if(_inputArray[_position]=='-') {
				++_position;
			}
		}
		int _state_7 = _state;
		if(_position<_inputLength) {
			int _position_of_last_success_6 = _position;
			int _multiple_index_8 = 0;
			int _state_8 = _state;
			while(_position<_inputLength) {
				if(_position<_inputLength) {
					if(_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9') {
						++_position;
					}
					else {
						_state=FAILED;
					}
				}
				else {
					_state=FAILED;
				}
				if(_state==FAILED) {
					break;
				}
				else {
					++_multiple_index_8;
				}
			}
			if(_state_8==SUCCESS) {
				_state=SUCCESS;
			}
			if(_position<_inputLength) {
				if(_inputArray[_position]=='.') {
					++_position;
				}
				else {
					_state=FAILED;
				}
			}
			else {
				_state=FAILED;
			}
			if(_state==FAILED) {
				_position=_position_of_last_success_6;
			}
		}
		if(_state_7==SUCCESS) {
			_state=SUCCESS;
		}
		int _multiple_index_9 = 0;
		int _state_9 = _state;
		while(_position<_inputLength) {
			if(_position<_inputLength) {
				if(_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9') {
					++_position;
				}
				else {
					_state=FAILED;
				}
			}
			else {
				_state=FAILED;
			}
			if(_state==FAILED) {
				break;
			}
			else {
				++_multiple_index_9;
			}
		}
		if(_state_9==SUCCESS&&_multiple_index_9>0 ) {
			_state=SUCCESS;
		}
		else {
			_state=FAILED;
		}
		int _state_10 = _state;
		if(_position<_inputLength) {
			if(_position<_inputLength) {
				if(_inputArray[_position]=='f') {
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
		if(_state_10==SUCCESS) {
			_state=SUCCESS;
		}
		int _multiple_index_11 = 0;
		int _state_11 = _state;
		while(_position<_inputLength) {
			if(_position<_inputLength) {
				if(_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\r'||_inputArray[_position]=='\n') {
					++_position;
				}
				else {
					_state=FAILED;
				}
			}
			else {
				_state=FAILED;
			}
			if(_state==FAILED) {
				break;
			}
			else {
				++_multiple_index_11;
			}
		}
		if(_state_11==SUCCESS) {
			_state=SUCCESS;
		}
		if(_state==SUCCESS) {
			_token.setValue(_input.substring(_position_regex_4,_position));
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[-]?(\\\\d*[.]?\\\\d+f?\\\\s*");
				_furthestPosition=_position;
			}
			_position=_position_regex_4;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"NUMBER(NUMBER)");
				_furthestPosition=_position;
			}
			_position=_position_NUMBER;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_NUMBER.add(_position_NUMBER,_token);
		}
		_token=_token_NUMBER;
		if(_state==FAILED) {
			list_names.reject(_position_NUMBER);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_NUMBER);
		}
	}
}