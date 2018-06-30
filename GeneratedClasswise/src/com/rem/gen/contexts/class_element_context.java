package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.statement_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class class_element_context extends statement_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public class_element_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public class_element_context() {
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
	public void parse_class_element() {
		int _position_class_element = -1;
		Token.Parsed _token_class_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_class_element=_position;
		_token_class_element=_token;
		_token=new Tokens.Rule.ClassElementToken();
		parse_comments();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
				_furthestPosition=_position;
			}
			_position=_position_class_element;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_class_element.addAll(_token);
			_token_class_element.setValue(_token.getValue());
		}
		_token=_token_class_element;
		if(_state==FAILED) {
			class_names.reject(_position_class_element);
			class_variable_names.reject(_position_class_element);
			variable_names.reject(_position_class_element);
			_state=SUCCESS;
			_position_class_element=_position;
			_token_class_element=_token;
			_token=new Tokens.Rule.ClassElementToken();
			parse_class_declaration();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
					_furthestPosition=_position;
				}
				_position=_position_class_element;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_class_element.addAll(_token);
				_token_class_element.setValue(_token.getValue());
			}
			_token=_token_class_element;
			if(_state==FAILED) {
				class_names.reject(_position_class_element);
				class_variable_names.reject(_position_class_element);
				variable_names.reject(_position_class_element);
				_state=SUCCESS;
				_position_class_element=_position;
				_token_class_element=_token;
				_token=new Tokens.Rule.ClassElementToken();
				parse_method_declaration();
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
						_furthestPosition=_position;
					}
					_position=_position_class_element;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_class_element.addAll(_token);
					_token_class_element.setValue(_token.getValue());
				}
				_token=_token_class_element;
				if(_state==FAILED) {
					class_names.reject(_position_class_element);
					class_variable_names.reject(_position_class_element);
					variable_names.reject(_position_class_element);
					_state=SUCCESS;
					_position_class_element=_position;
					_token_class_element=_token;
					_token=new Tokens.Rule.ClassElementToken();
					parse_variable_declaration();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
							_furthestPosition=_position;
						}
						_position=_position_class_element;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token_class_element.addAll(_token);
						_token_class_element.setValue(_token.getValue());
					}
					_token=_token_class_element;
					if(_state==FAILED) {
						class_names.reject(_position_class_element);
						class_variable_names.reject(_position_class_element);
						variable_names.reject(_position_class_element);
					}
					else if(_state==SUCCESS) {
						class_names.accept(_position_class_element);
						class_variable_names.accept(_position_class_element);
						variable_names.accept(_position_class_element);
					}
				}
			}
		}
	}
}