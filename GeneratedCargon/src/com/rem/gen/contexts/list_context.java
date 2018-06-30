package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.number_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class list_context extends number_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public list_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public list_context() {
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
	public void parse_list() {
		int _position_list = -1;
		Token.Parsed _token_list = null;
		int _position_NAME = -1;
		list_names.start(_position);
		_position_list=_position;
		_token_list=_token;
		_token=new Tokens.Rule.ListToken();
		_position_NAME=_position;
		if(_state==SUCCESS&&!_recursion_protection_NAME_4.contains(_position)) {
			_recursion_protection_NAME_4.add(_position);
			parse_NAME();
			_recursion_protection_NAME_4.remove(_position_NAME);
		}
		else {
			_state=FAILED;
		}
		if(_state==SUCCESS) {
			String _value = _token.getLastValue();
			if(_value!=null) {
				list_names.add(_value);
			}
			_token.add(_position,new Tokens.Name.ListNameToken(_value));
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
				_furthestPosition=_position;
			}
			_position=_position_list;
		}
		else {
			int _state_26 = _state;
			if(_position+6-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='g') {
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='l') {
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='o') {
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='b') {
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='a') {
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='l') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_15.SYNTAX);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain global");
					_furthestPosition=_position;
				}
			}
			if(_state_26==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
					_furthestPosition=_position;
				}
				_position=_position_list;
			}
			else {
				if(_position+4-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='l') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='i') {
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='s') {
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='t') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_16.SYNTAX);
					_position=_position+4;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain list");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
						_furthestPosition=_position;
					}
					_position=_position_list;
				}
				else {
					parse__anonymous_14();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
							_furthestPosition=_position;
						}
						_position=_position_list;
					}
					else {
						int _state_27 = _state;
						while(_position<_inputLength) {
							parse__anonymous_15();
							if(_state==FAILED) {
								break;
							}
						}
						if(_state_27==SUCCESS&&_state==FAILED) {
							_state=SUCCESS;
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(list)");
								_furthestPosition=_position;
							}
							_position=_position_list;
						}
						else {
						}
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_list.add(_position_list,_token);
		}
		_token=_token_list;
		if(_state==FAILED) {
			list_names.reject(_position_list);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_list);
		}
	}
}