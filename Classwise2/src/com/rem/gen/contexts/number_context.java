package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.base_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class number_context extends base_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public number_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
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
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_NUMBER=_position;
		_token_NUMBER=_token;
		_token=new Tokens.Rule.NUMBERToken();
		int _position_regex_4 = _position;
		if(_position<_inputLength) {
			if(_inputArray[_position]=='-') {
				++_position;
			}
		}
		int _state_4 = _state;
		if(_position<_inputLength) {
			int _position_of_last_success_7 = _position;
			int _multiple_index_5 = 0;
			int _state_5 = _state;
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
					++_multiple_index_5;
				}
			}
			if(_state_5==SUCCESS) {
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
				_position=_position_of_last_success_7;
			}
		}
		if(_state_4==SUCCESS) {
			_state=SUCCESS;
		}
		int _multiple_index_6 = 0;
		int _state_6 = _state;
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
				++_multiple_index_6;
			}
		}
		if(_state_6==SUCCESS&&_multiple_index_6>0 ) {
			_state=SUCCESS;
		}
		else {
			_state=FAILED;
		}
		if(_position<_inputLength) {
			if(_inputArray[_position]=='f') {
				++_position;
			}
		}
		int _state_8 = _state;
		if(_position<_inputLength) {
			int _position_of_last_success_12 = _position;
			if(_position<_inputLength) {
				if(_inputArray[_position]=='x') {
					++_position;
				}
				else {
					_state=FAILED;
				}
			}
			else {
				_state=FAILED;
			}
			int _multiple_index_9 = 0;
			while(_position<_inputLength) {
				if(_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F') {
					++_position;
					++_multiple_index_9;
				}
				else {
					break;
				}
			}
			if(_multiple_index_9==0 ) {
				_state=FAILED;
			}
			if(_state==FAILED) {
				_position=_position_of_last_success_12;
			}
		}
		if(_state_8==SUCCESS) {
			_state=SUCCESS;
		}
		int _multiple_index_10 = 0;
		int _state_10 = _state;
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
				++_multiple_index_10;
			}
		}
		if(_state_10==SUCCESS) {
			_state=SUCCESS;
		}
		if(_state==SUCCESS) {
			_token.setValue(_input.substring(_position_regex_4,_position));
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[-]?(\\\\d*[.]?\\\\d+[f]?(x[A-F]+?\\\\s*");
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
			class_names.reject(_position_NUMBER);
			class_variable_names.reject(_position_NUMBER);
			variable_names.reject(_position_NUMBER);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_NUMBER);
			class_variable_names.accept(_position_NUMBER);
			variable_names.accept(_position_NUMBER);
		}
	}
}