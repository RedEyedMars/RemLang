package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.as_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class inner_context extends as_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public inner_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public inner_context() {
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
	public void parse_inner() {
		int _position_inner = -1;
		Token.Parsed _token_inner = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_inner=_position;
		_token_inner=_token;
		_token=new Tokens.Rule.InnerToken();
		parse__anonymous_10();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(inner)");
				_furthestPosition=_position;
			}
			_position=_position_inner;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_inner.add(_position_inner,_token);
		}
		_token=_token_inner;
		if(_state==FAILED) {
			class_names.reject(_position_inner);
			class_variable_names.reject(_position_inner);
			variable_names.reject(_position_inner);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_inner);
			class_variable_names.accept(_position_inner);
			variable_names.accept(_position_inner);
		}
	}
}