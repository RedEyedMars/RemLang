package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.operator_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class atom_context extends operator_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public atom_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public atom_context() {
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
	public void parse_atom() {
		int _position_atom = -1;
		Token.Parsed _token_atom = null;
		int _position_regex = -1;
		int _position_quote = -1;
		int _position_NAME = -1;
		Token.Parsed _token_regex = null;
		Token.Parsed _token_quote = null;
		Token.Parsed _token_NAME = null;
		list_names.start(_position);
		_position_atom=_position;
		_token_atom=_token;
		_token=new Tokens.Rule.AtomToken();
		parse_braced_definition();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"atom(atom)");
				_furthestPosition=_position;
			}
			_position=_position_atom;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_atom.addAll(_token);
			_token_atom.setValue(_token.getValue());
		}
		_token=_token_atom;
		if(_state==FAILED) {
			list_names.reject(_position_atom);
			_state=SUCCESS;
			_position_atom=_position;
			_token_atom=_token;
			_token=new Tokens.Rule.AtomToken();
			_token_quote=_token;
			_token=new Tokens.Name.QuoteTokenToken();
			_position_quote=_position;
			parse_quote();
			if(_state==SUCCESS) {
				_token_quote.add(_position_quote,_token);
			}
			_token=_token_quote;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"atom(atom)");
					_furthestPosition=_position;
				}
				_position=_position_atom;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_atom.addAll(_token);
				_token_atom.setValue(_token.getValue());
			}
			_token=_token_atom;
			if(_state==FAILED) {
				list_names.reject(_position_atom);
				_state=SUCCESS;
				_position_atom=_position;
				_token_atom=_token;
				_token=new Tokens.Rule.AtomToken();
				_token_regex=_token;
				_token=new Tokens.Name.RegexTokenToken();
				_position_regex=_position;
				parse_regex();
				if(_state==SUCCESS) {
					_token_regex.add(_position_regex,_token);
				}
				_token=_token_regex;
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"atom(atom)");
						_furthestPosition=_position;
					}
					_position=_position_atom;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_atom.addAll(_token);
					_token_atom.setValue(_token.getValue());
				}
				_token=_token_atom;
				if(_state==FAILED) {
					list_names.reject(_position_atom);
					_state=SUCCESS;
					_position_atom=_position;
					_token_atom=_token;
					_token=new Tokens.Rule.AtomToken();
					_token_NAME=_token;
					_token=new Tokens.Name.RuleTokenToken();
					_position_NAME=_position;
					parse_NAME();
					if(_state==SUCCESS) {
						_token_NAME.add(_position_NAME,_token);
					}
					_token=_token_NAME;
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"atom(atom)");
							_furthestPosition=_position;
						}
						_position=_position_atom;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token_atom.addAll(_token);
						_token_atom.setValue(_token.getValue());
					}
					_token=_token_atom;
					if(_state==FAILED) {
						list_names.reject(_position_atom);
					}
					else if(_state==SUCCESS) {
						list_names.accept(_position_atom);
					}
				}
			}
		}
	}
}