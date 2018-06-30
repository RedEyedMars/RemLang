package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.file_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class rule_context extends file_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public rule_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public rule_context() {
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
	public void parse_rule_params() {
		int _position_rule_params = -1;
		Token.Parsed _token_rule_params = null;
		list_names.start(_position);
		_position_rule_params=_position;
		_token_rule_params=_token;
		_token=new Tokens.Rule.RuleParamsToken();
		if(_position+6-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='s') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='i') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='l') {
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='e') {
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='n') {
				_state=FAILED;
			}
			if(_inputArray[_position+5]!='t') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_7.SILENT);
			_position=_position+6;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain silent");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
				_furthestPosition=_position;
			}
			_position=_position_rule_params;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_rule_params.addAll(_token);
			_token_rule_params.setValue(_token.getValue());
		}
		_token=_token_rule_params;
		if(_state==FAILED) {
			list_names.reject(_position_rule_params);
			_state=SUCCESS;
			_position_rule_params=_position;
			_token_rule_params=_token;
			_token=new Tokens.Rule.RuleParamsToken();
			int _state_18 = _state;
			parse__anonymous_3();
			if(_state_18==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
					_furthestPosition=_position;
				}
				_position=_position_rule_params;
			}
			else {
				if(_position+6-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='B') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='r') {
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='a') {
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='c') {
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='e') {
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='d') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_9.SYNTAX);
					_position=_position+6;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Braced");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
						_furthestPosition=_position;
					}
					_position=_position_rule_params;
				}
				else {
					parse__anonymous_4();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
							_furthestPosition=_position;
						}
						_position=_position_rule_params;
					}
					else {
					}
				}
			}
			if(_state==SUCCESS) {
				_token_rule_params.addAll(_token);
				_token_rule_params.setValue(_token.getValue());
			}
			_token=_token_rule_params;
			if(_state==FAILED) {
				list_names.reject(_position_rule_params);
				_state=SUCCESS;
				_position_rule_params=_position;
				_token_rule_params=_token;
				_token=new Tokens.Rule.RuleParamsToken();
				if(_position+7-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='I') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='m') {
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='p') {
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='o') {
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='r') {
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='t') {
						_state=FAILED;
					}
					if(_inputArray[_position+6]!='s') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_10.SYNTAX);
					_position=_position+7;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Imports");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
						_furthestPosition=_position;
					}
					_position=_position_rule_params;
				}
				else {
					parse__anonymous_5();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
							_furthestPosition=_position;
						}
						_position=_position_rule_params;
					}
					else {
						if(_position+1-1 >=_inputLength) {
							_state=FAILED;
						}
						else {
							if(_inputArray[_position+0]!='=') {
								_state=FAILED;
							}
						}
						if(_state==SUCCESS) {
							_token.add(_position,Tokens.Syntax.syntax_11.SYNTAX);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
								++_position;
							}
						}
						else if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain =");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
								_furthestPosition=_position;
							}
							_position=_position_rule_params;
						}
						else {
							parse__anonymous_7();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
									_furthestPosition=_position;
								}
								_position=_position_rule_params;
							}
							else {
							}
						}
					}
				}
				if(_state==SUCCESS) {
					_token_rule_params.addAll(_token);
					_token_rule_params.setValue(_token.getValue());
				}
				_token=_token_rule_params;
				if(_state==FAILED) {
					list_names.reject(_position_rule_params);
					_state=SUCCESS;
					_position_rule_params=_position;
					_token_rule_params=_token;
					_token=new Tokens.Rule.RuleParamsToken();
					if(_position+6-1 >=_inputLength) {
						_state=FAILED;
					}
					else {
						if(_inputArray[_position+0]!='I') {
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
						_token.add(_position,Tokens.Syntax.syntax_12.SYNTAX);
						_position=_position+6;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
							++_position;
						}
					}
					else if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Ignore");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
							_furthestPosition=_position;
						}
						_position=_position_rule_params;
					}
					else {
						parse__anonymous_8();
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(rule_params)");
								_furthestPosition=_position;
							}
							_position=_position_rule_params;
						}
						else {
						}
					}
					if(_state==SUCCESS) {
						_token_rule_params.addAll(_token);
						_token_rule_params.setValue(_token.getValue());
					}
					_token=_token_rule_params;
					if(_state==FAILED) {
						list_names.reject(_position_rule_params);
					}
					else if(_state==SUCCESS) {
						list_names.accept(_position_rule_params);
					}
				}
			}
		}
	}
	public void parse_rule_parameters() {
		int _position_rule_parameters = -1;
		Token.Parsed _token_rule_parameters = null;
		list_names.start(_position);
		_position_rule_parameters=_position;
		_token_rule_parameters=_token;
		_token=new Tokens.Rule.RuleParametersToken();
		parse_rule_params();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_parameters(rule_parameters)");
				_furthestPosition=_position;
			}
			_position=_position_rule_parameters;
		}
		else {
			int _state_17 = _state;
			while(_position<_inputLength) {
				parse__anonymous_2();
				if(_state==FAILED) {
					break;
				}
			}
			if(_state_17==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_parameters(rule_parameters)");
					_furthestPosition=_position;
				}
				_position=_position_rule_parameters;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token_rule_parameters.addAll(_token);
			_token_rule_parameters.setValue(_token.getValue());
		}
		_token=_token_rule_parameters;
		if(_state==FAILED) {
			list_names.reject(_position_rule_parameters);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_rule_parameters);
		}
	}
	public void parse_rule() {
		int _position_rule = -1;
		Token.Parsed _token_rule = null;
		int _position_NAME = -1;
		Token.Parsed _token_NAME = null;
		list_names.start(_position);
		_position_rule=_position;
		_token_rule=_token;
		_token=new Tokens.Rule.RuleToken();
		_token_NAME=_token;
		_token=new Tokens.Name.RuleNameToken();
		_position_NAME=_position;
		if(_state==SUCCESS&&!_recursion_protection_NAME_3.contains(_position)) {
			_recursion_protection_NAME_3.add(_position);
			parse_NAME();
			_recursion_protection_NAME_3.remove(_position_NAME);
		}
		else {
			_state=FAILED;
		}
		if(_state==SUCCESS) {
			_token_NAME.add(_position_NAME,_token);
		}
		_token=_token_NAME;
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(rule)");
				_furthestPosition=_position;
			}
			_position=_position_rule;
		}
		else {
			int _state_25 = _state;
			parse__anonymous_11();
			if(_state_25==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(rule)");
					_furthestPosition=_position;
				}
				_position=_position_rule;
			}
			else {
				parse__anonymous_12();
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(rule)");
						_furthestPosition=_position;
					}
					_position=_position_rule;
				}
				else {
				}
			}
		}
		if(_state==SUCCESS) {
			_token_rule.add(_position_rule,_token);
		}
		_token=_token_rule;
		if(_state==FAILED) {
			list_names.reject(_position_rule);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_rule);
		}
	}
}