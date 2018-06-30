package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.name_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class single_context extends name_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public single_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public single_context() {
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
	public void parse_single_ignores_character() {
		int _position_single_ignores_character = -1;
		Token.Parsed _token_single_ignores_character = null;
		int _length_single_ignores_character_brace = _inputLength;
		if(brace_3.containsKey(_position)) {
			_inputLength=brace_3.get(_position);
			int _position_single_ignores_character_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			list_names.start(_position);
			_position_single_ignores_character=_position;
			_token_single_ignores_character=_token;
			_token=new Tokens.Rule.SingleIgnoresCharacterToken();
			int _position_regex_6 = _position;
			int _state_15 = _state;
			if(_position<_inputLength) {
				if(_position<_inputLength) {
					if(_inputArray[_position]=='\\') {
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
			if(_state_15==SUCCESS) {
				_state=SUCCESS;
			}
			int _state_16 = _state;
			if(_position<_inputLength) {
				if(_position<_inputLength) {
					++_position;
				}
				else {
					_state=FAILED;
				}
			}
			if(_state_16==SUCCESS) {
				_state=SUCCESS;
			}
			if(_state==SUCCESS) {
				_token.setValue(_input.substring(_position_regex_6,_position));
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"\\\\?.?");
					_furthestPosition=_position;
				}
				_position=_position_regex_6;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"single_ignores_character(single_ignores_character)");
					_furthestPosition=_position;
				}
				_position=_position_single_ignores_character;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_single_ignores_character.addAll(_token);
				_token_single_ignores_character.setValue(_token.getValue());
			}
			_token=_token_single_ignores_character;
			if(_state==SUCCESS&&brace_3.get(_position_single_ignores_character_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("single_ignores_character",_position,_lineNumberRanges));
				_position=brace_3.get(_position_single_ignores_character_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_single_ignores_character_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			if(_state==FAILED) {
				list_names.reject(_position_single_ignores_character);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position_single_ignores_character);
			}
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"single_ignores_character(single_ignores_character)");
				_furthestPosition=_position;
			}
		}
	}
}