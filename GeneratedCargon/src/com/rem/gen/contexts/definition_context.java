package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.single_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class definition_context extends single_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public definition_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public definition_context() {
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
	public void parse_definition() {
		int _position_definition = -1;
		Token.Parsed _token_definition = null;
		int _position_element = -1;
		Token.Parsed _token_element = null;
		list_names.start(_position);
		_position_definition=_position;
		_token_definition=_token;
		_token=new Tokens.Rule.DefinitionToken();
		int _state_28 = _state;
		boolean _iteration_achieved_28 = false;
		while(_position<_inputLength) {
			_token_element=_token;
			_token=new Tokens.Name.ChainToken();
			_position_element=_position;
			parse_element();
			if(_state==SUCCESS) {
				_token_element.add(_position_element,_token);
			}
			_token=_token_element;
			if(_state==FAILED) {
				break;
			}
			else {
				_iteration_achieved_28=true;
			}
		}
		if(_iteration_achieved_28==false) {
			_state=FAILED;
		}
		else if(_state_28==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(definition)");
				_furthestPosition=_position;
			}
			_position=_position_definition;
		}
		else {
			int _state_29 = _state;
			parse__anonymous_17();
			if(_state_29==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(definition)");
					_furthestPosition=_position;
				}
				_position=_position_definition;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token_definition.add(_position_definition,_token);
		}
		_token=_token_definition;
		if(_state==FAILED) {
			list_names.reject(_position_definition);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_definition);
		}
	}
}