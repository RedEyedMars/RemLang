package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.element_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class braced_context extends element_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public braced_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public braced_context() {
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
	public void parse_braced_definition() {
		int _position_braced_definition = -1;
		Token.Parsed _token_braced_definition = null;
		int _position_definition = -1;
		int _length_braced_definition_brace = _inputLength;
		if(brace_2.containsKey(_position)) {
			_inputLength=brace_2.get(_position);
			int _position_braced_definition_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			list_names.start(_position);
			_position_braced_definition=_position;
			_token_braced_definition=_token;
			_token=new Tokens.Rule.BracedDefinitionToken();
			_position_definition=_position;
			if(_state==SUCCESS&&!_recursion_protection_definition_1.contains(_position)) {
				_recursion_protection_definition_1.add(_position);
				parse_definition();
				_recursion_protection_definition_1.remove(_position_definition);
			}
			else {
				_state=FAILED;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"braced_definition(braced_definition)");
					_furthestPosition=_position;
				}
				_position=_position_braced_definition;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_braced_definition.add(_position_braced_definition,_token);
			}
			_token=_token_braced_definition;
			if(_state==SUCCESS&&brace_2.get(_position_braced_definition_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("braced_definition",_position,_lineNumberRanges));
				_position=brace_2.get(_position_braced_definition_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_braced_definition_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			if(_state==FAILED) {
				list_names.reject(_position_braced_definition);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position_braced_definition);
			}
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"braced_definition(braced_definition)");
				_furthestPosition=_position;
			}
		}
	}
}