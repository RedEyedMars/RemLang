package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.name_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class quote_context extends name_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public quote_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public quote_context() {
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
	public void parse_quote() {
		int _position_quote = -1;
		Token.Parsed _token_quote = null;
		int _length_quote_brace = _inputLength;
		if(brace_0.containsKey(_position)) {
			class_variable_names=class_variable_names.push(_position,_pass);
			variable_names=variable_names.push(_position,_pass);
			_inputLength=brace_0.get(_position);
			int _position_quote_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false)) {
				++_position;
			}
			class_names.start(_position);
			class_variable_names.start(_position);
			variable_names.start(_position);
			_position_quote=_position;
			_token_quote=_token;
			_token=new Tokens.Rule.QuoteToken();
			int _position_regex_5 = _position;
			int _multiple_index_11 = 0;
			int _state_11 = _state;
			while(_position<_inputLength) {
				if(_position<_inputLength) {
					++_position;
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
				_token.setValue(_input.substring(_position_regex_5,_position));
				while(_position<_inputLength&&(false)) {
					++_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".*");
					_furthestPosition=_position;
				}
				_position=_position_regex_5;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"quote(quote)");
					_furthestPosition=_position;
				}
				_position=_position_quote;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_quote.add(_position_quote,_token);
			}
			_token=_token_quote;
			if(_state==SUCCESS&&brace_0.get(_position_quote_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("quote",_position,_lineNumberRanges));
				_position=brace_0.get(_position_quote_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_quote_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			if(_state==FAILED) {
				class_names.reject(_position_quote);
				class_variable_names.reject(_position_quote);
				variable_names.reject(_position_quote);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_quote);
				class_variable_names.accept(_position_quote);
				variable_names.accept(_position_quote);
			}
			class_variable_names=class_variable_names.pop();
			variable_names=variable_names.pop();
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"quote(quote)");
				_furthestPosition=_position;
			}
		}
	}
}