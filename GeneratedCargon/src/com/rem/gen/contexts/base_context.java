package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.quote_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class base_context extends quote_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public base_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public base_context() {
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
	public void parse_base_element() {
		int _position_base_element = -1;
		Token.Parsed _token_base_element = null;
		list_names.start(_position);
		_position_base_element=_position;
		_token_base_element=_token;
		_token=new Tokens.Rule.BaseElementToken();
		int _state_24 = _state;
		boolean _iteration_achieved_24 = false;
		while(_position<_inputLength) {
			parse__anonymous_10();
			if(_state==FAILED) {
				break;
			}
			else {
				_iteration_achieved_24=true;
			}
		}
		if(_iteration_achieved_24==false) {
			_state=FAILED;
		}
		else if(_state_24==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
				_furthestPosition=_position;
			}
			_position=_position_base_element;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_base_element.addAll(_token);
			_token_base_element.setValue(_token.getValue());
		}
		_token=_token_base_element;
		if(_state==FAILED) {
			list_names.reject(_position_base_element);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_base_element);
		}
	}
	public void parse_base() {
		int _position_base = -1;
		Token.Parsed _token_base = null;
		list_names.start(_position);
		_position_base=_position;
		_token_base=_token;
		_token=new Tokens.Rule.BaseToken();
		int _state_0 = _state;
		while(_position<_inputLength) {
			parse_base_element();
			if(_state==FAILED) {
				break;
			}
		}
		if(_state_0==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base(base)");
				_furthestPosition=_position;
			}
			_position=_position_base;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_base.addAll(_token);
			_token_base.setValue(_token.getValue());
		}
		_token=_token_base;
		if(_state==FAILED) {
			list_names.reject(_position_base);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_base);
		}
	}
}