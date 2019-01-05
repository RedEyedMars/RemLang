package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.method_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class base_context extends method_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public base_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
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
		int _position_import_clws = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_base_element=_position;
		_token_base_element=_token;
		_token=new Tokens.Rule.BaseElementToken();
		parse_comments();
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
			class_names.reject(_position_base_element);
			class_variable_names.reject(_position_base_element);
			variable_names.reject(_position_base_element);
			_state=SUCCESS;
			_position_base_element=_position;
			_token_base_element=_token;
			_token=new Tokens.Rule.BaseElementToken();
			parse_import_imports();
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
				class_names.reject(_position_base_element);
				class_variable_names.reject(_position_base_element);
				variable_names.reject(_position_base_element);
				_state=SUCCESS;
				_position_base_element=_position;
				_token_base_element=_token;
				_token=new Tokens.Rule.BaseElementToken();
				_position_import_clws=_position;
				if(_state==SUCCESS&&!_recursion_protection_import_clws_4.contains(_position)) {
					_recursion_protection_import_clws_4.add(_position);
					parse_import_clws();
					_recursion_protection_import_clws_4.remove(_position_import_clws);
				}
				else {
					_state=FAILED;
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
					class_names.reject(_position_base_element);
					class_variable_names.reject(_position_base_element);
					variable_names.reject(_position_base_element);
					_state=SUCCESS;
					_position_base_element=_position;
					_token_base_element=_token;
					_token=new Tokens.Rule.BaseElementToken();
					parse_class_declaration();
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
						class_names.reject(_position_base_element);
						class_variable_names.reject(_position_base_element);
						variable_names.reject(_position_base_element);
						_state=SUCCESS;
						_position_base_element=_position;
						_token_base_element=_token;
						_token=new Tokens.Rule.BaseElementToken();
						parse_method_declaration();
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
							class_names.reject(_position_base_element);
							class_variable_names.reject(_position_base_element);
							variable_names.reject(_position_base_element);
							_state=SUCCESS;
							_position_base_element=_position;
							_token_base_element=_token;
							_token=new Tokens.Rule.BaseElementToken();
							parse_variable_declaration();
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
								class_names.reject(_position_base_element);
								class_variable_names.reject(_position_base_element);
								variable_names.reject(_position_base_element);
							}
							else if(_state==SUCCESS) {
								class_names.accept(_position_base_element);
								class_variable_names.accept(_position_base_element);
								variable_names.accept(_position_base_element);
							}
						}
					}
				}
			}
		}
	}
	public void parse_base() {
		int _position_base = -1;
		Token.Parsed _token_base = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
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
			class_names.reject(_position_base);
			class_variable_names.reject(_position_base);
			variable_names.reject(_position_base);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_base);
			class_variable_names.accept(_position_base);
			variable_names.accept(_position_base);
		}
	}
}