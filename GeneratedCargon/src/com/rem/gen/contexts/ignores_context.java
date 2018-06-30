package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.rule_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class ignores_context extends rule_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public ignores_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public ignores_context() {
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
	public void parse_ignores() {
		int _position_ignores = -1;
		Token.Parsed _token_ignores = null;
		list_names.start(_position);
		_position_ignores=_position;
		_token_ignores=_token;
		_token=new Tokens.Rule.IgnoresToken();
		if(_position+6-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='i') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='g') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='n') {
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='o') {
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='r') {
				_state=FAILED;
			}
			if(_inputArray[_position+5]!='e') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_0.SYNTAX);
			_position=_position+6;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ignore");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(ignores)");
				_furthestPosition=_position;
			}
			_position=_position_ignores;
		}
		else {
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!=':') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_1.SYNTAX);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain :");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(ignores)");
					_furthestPosition=_position;
				}
				_position=_position_ignores;
			}
			else {
				int _state_2 = _state;
				boolean _iteration_achieved_2 = false;
				while(_position<_inputLength) {
					parse__anonymous_0();
					if(_state==FAILED) {
						break;
					}
					else {
						_iteration_achieved_2=true;
					}
				}
				if(_iteration_achieved_2==false) {
					_state=FAILED;
				}
				else if(_state_2==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(ignores)");
						_furthestPosition=_position;
					}
					_position=_position_ignores;
				}
				else {
					if(_position+1-1 >=_inputLength) {
						_state=FAILED;
					}
					else {
						if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r') {
							_state=FAILED;
						}
					}
					if(_state==SUCCESS) {
						_token.add(_position,Tokens.Syntax.syntax_2.SYNTAX);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
							++_position;
						}
					}
					else if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(ignores)");
							_furthestPosition=_position;
						}
						_position=_position_ignores;
					}
					else {
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_ignores.add(_position_ignores,_token);
		}
		_token=_token_ignores;
		if(_state==FAILED) {
			list_names.reject(_position_ignores);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_ignores);
		}
	}
}