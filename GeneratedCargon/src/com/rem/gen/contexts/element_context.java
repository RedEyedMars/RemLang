package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.regex_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class element_context extends regex_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public element_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public element_context() {
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
	public void parse_element() {
		int _position_element = -1;
		Token.Parsed _token_element = null;
		list_names.start(_position);
		_position_element=_position;
		_token_element=_token;
		_token=new Tokens.Rule.ElementToken();
		parse_atom();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(element)");
				_furthestPosition=_position;
			}
			_position=_position_element;
		}
		else {
			int _state_30 = _state;
			parse__anonymous_19();
			if(_state_30==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(element)");
					_furthestPosition=_position;
				}
				_position=_position_element;
			}
			else {
				int _state_31 = _state;
				parse__anonymous_20();
				if(_state_31==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(element)");
						_furthestPosition=_position;
					}
					_position=_position_element;
				}
				else {
					int _state_32 = _state;
					parse__anonymous_21();
					if(_state_32==SUCCESS&&_state==FAILED) {
						_state=SUCCESS;
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(element)");
							_furthestPosition=_position;
						}
						_position=_position_element;
					}
					else {
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_element.add(_position_element,_token);
		}
		_token=_token_element;
		if(_state==FAILED) {
			list_names.reject(_position_element);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_element);
		}
	}
}