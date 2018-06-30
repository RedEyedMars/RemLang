package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import java.util.Set;
import java.util.List;
import java.util.Map;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class AnonymousContext extends Parser.Context{
	public AnonymousContext(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public AnonymousContext() {
	}
	public void parse__anonymous_6() {
		int _position__anonymous_6 = -1;
		Token.Parsed _token__anonymous_6 = null;
		int _position_NAME = -1;
		Token.Parsed _token_NAME = null;
		list_names.start(_position);
		_position__anonymous_6=_position;
		_token__anonymous_6=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		parse_quote();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_6)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_6;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_6.addAll(_token);
			_token__anonymous_6.setValue(_token.getValue());
		}
		_token=_token__anonymous_6;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_6);
			_state=SUCCESS;
			_position__anonymous_6=_position;
			_token__anonymous_6=_token;
			_token=new Token.Parsed(Token.Id.ANON);
			_token_NAME=_token;
			_token=new Tokens.Name.RuleNameToken();
			_position_NAME=_position;
			parse_NAME();
			if(_state==SUCCESS) {
				_token_NAME.add(_position_NAME,_token);
			}
			_token=_token_NAME;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_6)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_6;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_6.addAll(_token);
				_token__anonymous_6.setValue(_token.getValue());
			}
			_token=_token__anonymous_6;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_6);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position__anonymous_6);
			}
		}
	}
	public void parse__anonymous_5() {
		int _position__anonymous_5 = -1;
		Token.Parsed _token__anonymous_5 = null;
		list_names.start(_position);
		_position__anonymous_5=_position;
		_token__anonymous_5=_token;
		_token=new Tokens.Name.ImportParameterToken();
		int _state_21 = _state;
		boolean _iteration_achieved_21 = false;
		while(_position<_inputLength) {
			parse__anonymous_6();
			if(_state==FAILED) {
				break;
			}
			else {
				_iteration_achieved_21=true;
			}
		}
		if(_iteration_achieved_21==false) {
			_state=FAILED;
		}
		else if(_state_21==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_5)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_5;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_5.add(_position__anonymous_5,_token);
		}
		_token=_token__anonymous_5;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_5);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_5);
		}
	}
	public void parse__anonymous_8() {
		int _position__anonymous_8 = -1;
		Token.Parsed _token__anonymous_8 = null;
		list_names.start(_position);
		_position__anonymous_8=_position;
		_token__anonymous_8=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		if(_position+4-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='N') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='o') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='n') {
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='e') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_13.ignores_none);
			_position=_position+4;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain None");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_8)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_8;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_8.addAll(_token);
			_token__anonymous_8.setValue(_token.getValue());
		}
		_token=_token__anonymous_8;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_8);
			_state=SUCCESS;
			_position__anonymous_8=_position;
			_token__anonymous_8=_token;
			_token=new Token.Parsed(Token.Id.ANON);
			int _state_23 = _state;
			boolean _iteration_achieved_23 = false;
			while(_position<_inputLength) {
				parse__anonymous_9();
				if(_state==FAILED) {
					break;
				}
				else {
					_iteration_achieved_23=true;
				}
			}
			if(_iteration_achieved_23==false) {
				_state=FAILED;
			}
			else if(_state_23==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_8)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_8;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_8.addAll(_token);
				_token__anonymous_8.setValue(_token.getValue());
			}
			_token=_token__anonymous_8;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_8);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position__anonymous_8);
			}
		}
	}
	public void parse__anonymous_7() {
		int _position__anonymous_7 = -1;
		Token.Parsed _token__anonymous_7 = null;
		int _position_element = -1;
		Token.Parsed _token_element = null;
		list_names.start(_position);
		_position__anonymous_7=_position;
		_token__anonymous_7=_token;
		_token=new Tokens.Name.ImportDefinitionToken();
		int _state_22 = _state;
		boolean _iteration_achieved_22 = false;
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
				_iteration_achieved_22=true;
			}
		}
		if(_iteration_achieved_22==false) {
			_state=FAILED;
		}
		else if(_state_22==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_7)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_7;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_7.add(_position__anonymous_7,_token);
		}
		_token=_token__anonymous_7;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_7);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_7);
		}
	}
	public void parse__anonymous_2() {
		int _position__anonymous_2 = -1;
		Token.Parsed _token__anonymous_2 = null;
		list_names.start(_position);
		_position__anonymous_2=_position;
		_token__anonymous_2=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!=',') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_6.SYNTAX);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_parameters(_anonymous_2)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_2;
		}
		else {
			parse_rule_params();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_parameters(_anonymous_2)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_2;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_2.addAll(_token);
			_token__anonymous_2.setValue(_token.getValue());
		}
		_token=_token__anonymous_2;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_2);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_2);
		}
	}
	public void parse__anonymous_1() {
		int _position__anonymous_1 = -1;
		Token.Parsed _token__anonymous_1 = null;
		list_names.start(_position);
		_position__anonymous_1=_position;
		_token__anonymous_1=_token;
		_token=new Tokens.Name.RangeToken();
		int _position_regex_7 = _position;
		if(_position<_inputLength) {
			++_position;
		}
		else {
			_state=FAILED;
		}
		if(_state==SUCCESS) {
			_token.add(_position_regex_7,new Tokens.Plain.Left(_input.substring(_position_regex_7,_position)));
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
				_furthestPosition=_position;
			}
			_position=_position_regex_7;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(_anonymous_1)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_1;
		}
		else {
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='-') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_5.SYNTAX);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain -");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(_anonymous_1)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_1;
			}
			else {
				int _position_regex_8 = _position;
				if(_position<_inputLength) {
					++_position;
				}
				else {
					_state=FAILED;
				}
				if(_state==SUCCESS) {
					_token.add(_position_regex_8,new Tokens.Plain.Right(_input.substring(_position_regex_8,_position)));
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
						_furthestPosition=_position;
					}
					_position=_position_regex_8;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(_anonymous_1)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_1;
				}
				else {
				}
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_1.add(_position__anonymous_1,_token);
		}
		_token=_token__anonymous_1;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_1);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_1);
		}
	}
	public void parse__anonymous_4() {
		int _position__anonymous_4 = -1;
		Token.Parsed _token__anonymous_4 = null;
		list_names.start(_position);
		_position__anonymous_4=_position;
		_token__anonymous_4=_token;
		_token=new Tokens.Name.BracedParametersToken();
		int _position_regex_10 = _position;
		int _multiple_index_19 = 0;
		while(_position<_inputLength) {
			if(_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n') {
				++_position;
				++_multiple_index_19;
			}
			else {
				break;
			}
		}
		if(_multiple_index_19==0 ) {
			_state=FAILED;
		}
		if(_state==SUCCESS) {
			_token.add(_position_regex_10,new Tokens.Plain.Left(_input.substring(_position_regex_10,_position)));
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[\\\\s]+");
				_furthestPosition=_position;
			}
			_position=_position_regex_10;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_4)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_4;
		}
		else {
			int _position_regex_11 = _position;
			int _multiple_index_20 = 0;
			while(_position<_inputLength) {
				if(_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n') {
					++_position;
					++_multiple_index_20;
				}
				else {
					break;
				}
			}
			if(_multiple_index_20==0 ) {
				_state=FAILED;
			}
			if(_state==SUCCESS) {
				_token.add(_position_regex_11,new Tokens.Plain.Right(_input.substring(_position_regex_11,_position)));
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[\\\\s]+");
					_furthestPosition=_position;
				}
				_position=_position_regex_11;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_4)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_4;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_4.add(_position__anonymous_4,_token);
		}
		_token=_token__anonymous_4;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_4);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_4);
		}
	}
	public void parse__anonymous_3() {
		int _position__anonymous_3 = -1;
		Token.Parsed _token__anonymous_3 = null;
		int _position_NUMBER = -1;
		Token.Parsed _token_NUMBER = null;
		list_names.start(_position);
		_position__anonymous_3=_position;
		_token__anonymous_3=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='@') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_8.SYNTAX);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain @");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_3)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_3;
		}
		else {
			_token_NUMBER=_token;
			_token=new Tokens.Name.PassConstraintToken();
			_position_NUMBER=_position;
			parse_NUMBER();
			if(_state==SUCCESS) {
				_token_NUMBER.add(_position_NUMBER,_token);
			}
			_token=_token_NUMBER;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_3)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_3;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_3.addAll(_token);
			_token__anonymous_3.setValue(_token.getValue());
		}
		_token=_token__anonymous_3;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_3);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_3);
		}
	}
	public void parse__anonymous_9() {
		int _position__anonymous_9 = -1;
		Token.Parsed _token__anonymous_9 = null;
		list_names.start(_position);
		_position__anonymous_9=_position;
		_token__anonymous_9=_token;
		_token=new Tokens.Name.IgnoresCharacterToken();
		parse_single_ignores_character();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule_params(_anonymous_9)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_9;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_9.add(_position__anonymous_9,_token);
		}
		_token=_token__anonymous_9;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_9);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_9);
		}
	}
	public void parse__anonymous_0() {
		int _position__anonymous_0 = -1;
		Token.Parsed _token__anonymous_0 = null;
		int _position_single_ignores_character = -1;
		Token.Parsed _token_single_ignores_character = null;
		list_names.start(_position);
		_position__anonymous_0=_position;
		_token__anonymous_0=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		_token_single_ignores_character=_token;
		_token=new Tokens.Name.IgnoreCharacterToken();
		_position_single_ignores_character=_position;
		parse_single_ignores_character();
		if(_state==SUCCESS) {
			_token_single_ignores_character.add(_position_single_ignores_character,_token);
		}
		_token=_token_single_ignores_character;
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"ignores(_anonymous_0)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_0;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_0.addAll(_token);
			_token__anonymous_0.setValue(_token.getValue());
		}
		_token=_token__anonymous_0;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_0);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_0);
		}
	}
	public void parse__anonymous_22() {
		int _position__anonymous_22 = -1;
		Token.Parsed _token__anonymous_22 = null;
		list_names.start(_position);
		_position__anonymous_22=_position;
		_token__anonymous_22=_token;
		_token=new Tokens.Name.MultipleToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='?') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_22)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_22;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_22.add(_position__anonymous_22,_token);
		}
		_token=_token__anonymous_22;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_22);
			_state=SUCCESS;
			_position__anonymous_22=_position;
			_token__anonymous_22=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='*') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_22.MANY);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_22)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_22;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_22.add(_position__anonymous_22,_token);
			}
			_token=_token__anonymous_22;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_22);
				_state=SUCCESS;
				_position__anonymous_22=_position;
				_token__anonymous_22=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='+') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_22)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_22;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token__anonymous_22.add(_position__anonymous_22,_token);
				}
				_token=_token__anonymous_22;
				if(_state==FAILED) {
					list_names.reject(_position__anonymous_22);
				}
				else if(_state==SUCCESS) {
					list_names.accept(_position__anonymous_22);
				}
			}
		}
	}
	public void parse__anonymous_21() {
		int _position__anonymous_21 = -1;
		Token.Parsed _token__anonymous_21 = null;
		list_names.start(_position);
		_position__anonymous_21=_position;
		_token__anonymous_21=_token;
		_token=new Tokens.Name.MultipleToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='?') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_21)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_21;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_21.add(_position__anonymous_21,_token);
		}
		_token=_token__anonymous_21;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_21);
			_state=SUCCESS;
			_position__anonymous_21=_position;
			_token__anonymous_21=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='*') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_22.MANY);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_21)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_21;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_21.add(_position__anonymous_21,_token);
			}
			_token=_token__anonymous_21;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_21);
				_state=SUCCESS;
				_position__anonymous_21=_position;
				_token__anonymous_21=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='+') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_21)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_21;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token__anonymous_21.add(_position__anonymous_21,_token);
				}
				_token=_token__anonymous_21;
				if(_state==FAILED) {
					list_names.reject(_position__anonymous_21);
				}
				else if(_state==SUCCESS) {
					list_names.accept(_position__anonymous_21);
				}
			}
		}
	}
	public void parse__anonymous_24() {
		int _position__anonymous_24 = -1;
		Token.Parsed _token__anonymous_24 = null;
		list_names.start(_position);
		_position__anonymous_24=_position;
		_token__anonymous_24=_token;
		_token=new Tokens.Name.MultipleToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='?') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_24)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_24;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_24.add(_position__anonymous_24,_token);
		}
		_token=_token__anonymous_24;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_24);
			_state=SUCCESS;
			_position__anonymous_24=_position;
			_token__anonymous_24=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='*') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_22.MANY);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_24)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_24;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_24.add(_position__anonymous_24,_token);
			}
			_token=_token__anonymous_24;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_24);
				_state=SUCCESS;
				_position__anonymous_24=_position;
				_token__anonymous_24=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='+') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_24)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_24;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token__anonymous_24.add(_position__anonymous_24,_token);
				}
				_token=_token__anonymous_24;
				if(_state==FAILED) {
					list_names.reject(_position__anonymous_24);
				}
				else if(_state==SUCCESS) {
					list_names.accept(_position__anonymous_24);
				}
			}
		}
	}
	public void parse__anonymous_23() {
		int _position__anonymous_23 = -1;
		Token.Parsed _token__anonymous_23 = null;
		list_names.start(_position);
		_position__anonymous_23=_position;
		_token__anonymous_23=_token;
		_token=new Tokens.Name.MultipleToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='?') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_23)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_23;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_23.add(_position__anonymous_23,_token);
		}
		_token=_token__anonymous_23;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_23);
			_state=SUCCESS;
			_position__anonymous_23=_position;
			_token__anonymous_23=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='*') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_22.MANY);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_23)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_23;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_23.add(_position__anonymous_23,_token);
			}
			_token=_token__anonymous_23;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_23);
				_state=SUCCESS;
				_position__anonymous_23=_position;
				_token__anonymous_23=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='+') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_23)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_23;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token__anonymous_23.add(_position__anonymous_23,_token);
				}
				_token=_token__anonymous_23;
				if(_state==FAILED) {
					list_names.reject(_position__anonymous_23);
				}
				else if(_state==SUCCESS) {
					list_names.accept(_position__anonymous_23);
				}
			}
		}
	}
	public void parse__anonymous_25() {
		int _position__anonymous_25 = -1;
		Token.Parsed _token__anonymous_25 = null;
		list_names.start(_position);
		_position__anonymous_25=_position;
		_token__anonymous_25=_token;
		_token=new Tokens.Name.MultipleToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='?') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_21.OPTIONAL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_25)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_25;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_25.add(_position__anonymous_25,_token);
		}
		_token=_token__anonymous_25;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_25);
			_state=SUCCESS;
			_position__anonymous_25=_position;
			_token__anonymous_25=_token;
			_token=new Tokens.Name.MultipleToken();
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='*') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_22.MANY);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_25)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_25;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_25.add(_position__anonymous_25,_token);
			}
			_token=_token__anonymous_25;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_25);
				_state=SUCCESS;
				_position__anonymous_25=_position;
				_token__anonymous_25=_token;
				_token=new Tokens.Name.MultipleToken();
				if(_position+1-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='+') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_23.PLUS);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(_anonymous_25)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_25;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token__anonymous_25.add(_position__anonymous_25,_token);
				}
				_token=_token__anonymous_25;
				if(_state==FAILED) {
					list_names.reject(_position__anonymous_25);
				}
				else if(_state==SUCCESS) {
					list_names.accept(_position__anonymous_25);
				}
			}
		}
	}
	public void parse__anonymous_20() {
		int _position__anonymous_20 = -1;
		Token.Parsed _token__anonymous_20 = null;
		int _position_NAME = -1;
		Token.Parsed _token_NAME = null;
		list_names.start(_position);
		_position__anonymous_20=_position;
		_token__anonymous_20=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		if(_position+3-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='i') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='n') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!=' ') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_20.SYNTAX);
			_position=_position+3;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain in ");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_20)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_20;
		}
		else {
			_token_NAME=_token;
			_token=new Tokens.Name.ListNameToken();
			_position_NAME=_position;
			parse_NAME();
			if(_state==SUCCESS) {
				_token_NAME.add(_position_NAME,_token);
			}
			_token=_token_NAME;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_20)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_20;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_20.addAll(_token);
			_token__anonymous_20.setValue(_token.getValue());
		}
		_token=_token__anonymous_20;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_20);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_20);
		}
	}
	public void parse__anonymous_19() {
		int _position__anonymous_19 = -1;
		Token.Parsed _token__anonymous_19 = null;
		int _position_NAME = -1;
		Token.Parsed _token_NAME = null;
		list_names.start(_position);
		_position__anonymous_19=_position;
		_token__anonymous_19=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		if(_position+3-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='a') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='s') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!=' ') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_19.SYNTAX);
			_position=_position+3;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain as ");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_19)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_19;
		}
		else {
			_token_NAME=_token;
			_token=new Tokens.Name.NewNameToken();
			_position_NAME=_position;
			parse_NAME();
			if(_state==SUCCESS) {
				_token_NAME.add(_position_NAME,_token);
			}
			_token=_token_NAME;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"element(_anonymous_19)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_19;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_19.addAll(_token);
			_token__anonymous_19.setValue(_token.getValue());
		}
		_token=_token__anonymous_19;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_19);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_19);
		}
	}
	public void parse__anonymous_18() {
		int _position__anonymous_18 = -1;
		Token.Parsed _token__anonymous_18 = null;
		list_names.start(_position);
		_position__anonymous_18=_position;
		_token__anonymous_18=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='|') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_18.SYNTAX);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain |");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(_anonymous_18)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_18;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_18.addAll(_token);
			_token__anonymous_18.setValue(_token.getValue());
		}
		_token=_token__anonymous_18;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_18);
			_state=SUCCESS;
			_position__anonymous_18=_position;
			_token__anonymous_18=_token;
			_token=new Token.Parsed(Token.Id.ANON);
			if(_position+2-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r') {
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='\t') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_14.SYNTAX);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n\t");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(_anonymous_18)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_18;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_18.addAll(_token);
				_token__anonymous_18.setValue(_token.getValue());
			}
			_token=_token__anonymous_18;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_18);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position__anonymous_18);
			}
		}
	}
	public void parse__anonymous_11() {
		int _position__anonymous_11 = -1;
		Token.Parsed _token__anonymous_11 = null;
		list_names.start(_position);
		_position__anonymous_11=_position;
		_token__anonymous_11=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		parse_rule_parameters();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_11)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_11;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_11.addAll(_token);
			_token__anonymous_11.setValue(_token.getValue());
		}
		_token=_token__anonymous_11;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_11);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_11);
		}
	}
	public void parse__anonymous_10() {
		int _position__anonymous_10 = -1;
		Token.Parsed _token__anonymous_10 = null;
		list_names.start(_position);
		_position__anonymous_10=_position;
		_token__anonymous_10=_token;
		_token=new Token.Parsed(Token.Id.ANON);
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
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_10;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_10.addAll(_token);
			_token__anonymous_10.setValue(_token.getValue());
		}
		_token=_token__anonymous_10;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_10);
			_state=SUCCESS;
			_position__anonymous_10=_position;
			_token__anonymous_10=_token;
			_token=new Token.Parsed(Token.Id.ANON);
			parse_ignores();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_10;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_10.addAll(_token);
				_token__anonymous_10.setValue(_token.getValue());
			}
			_token=_token__anonymous_10;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_10);
				_state=SUCCESS;
				_position__anonymous_10=_position;
				_token__anonymous_10=_token;
				_token=new Token.Parsed(Token.Id.ANON);
				parse_comments();
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_10;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token__anonymous_10.addAll(_token);
					_token__anonymous_10.setValue(_token.getValue());
				}
				_token=_token__anonymous_10;
				if(_state==FAILED) {
					list_names.reject(_position__anonymous_10);
					_state=SUCCESS;
					_position__anonymous_10=_position;
					_token__anonymous_10=_token;
					_token=new Token.Parsed(Token.Id.ANON);
					parse_imports();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_10;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token__anonymous_10.addAll(_token);
						_token__anonymous_10.setValue(_token.getValue());
					}
					_token=_token__anonymous_10;
					if(_state==FAILED) {
						list_names.reject(_position__anonymous_10);
						_state=SUCCESS;
						_position__anonymous_10=_position;
						_token__anonymous_10=_token;
						_token=new Token.Parsed(Token.Id.ANON);
						parse_list();
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_10;
						}
						else {
						}
						if(_state==SUCCESS) {
							_token__anonymous_10.addAll(_token);
							_token__anonymous_10.setValue(_token.getValue());
						}
						_token=_token__anonymous_10;
						if(_state==FAILED) {
							list_names.reject(_position__anonymous_10);
							_state=SUCCESS;
							_position__anonymous_10=_position;
							_token__anonymous_10=_token;
							_token=new Token.Parsed(Token.Id.ANON);
							parse_rule();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(_anonymous_10)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_10;
							}
							else {
							}
							if(_state==SUCCESS) {
								_token__anonymous_10.addAll(_token);
								_token__anonymous_10.setValue(_token.getValue());
							}
							_token=_token__anonymous_10;
							if(_state==FAILED) {
								list_names.reject(_position__anonymous_10);
							}
							else if(_state==SUCCESS) {
								list_names.accept(_position__anonymous_10);
							}
						}
					}
				}
			}
		}
	}
	public void parse__anonymous_13() {
		int _position__anonymous_13 = -1;
		Token.Parsed _token__anonymous_13 = null;
		list_names.start(_position);
		_position__anonymous_13=_position;
		_token__anonymous_13=_token;
		_token=new Token.Parsed(Token.Id.ANON);
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
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_13)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_13;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_13.addAll(_token);
			_token__anonymous_13.setValue(_token.getValue());
		}
		_token=_token__anonymous_13;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_13);
			_state=SUCCESS;
			_position__anonymous_13=_position;
			_token__anonymous_13=_token;
			_token=new Token.Parsed(Token.Id.ANON);
			if(_position+2-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r') {
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='\t') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_14.SYNTAX);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n\t");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_13)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_13;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_13.addAll(_token);
				_token__anonymous_13.setValue(_token.getValue());
			}
			_token=_token__anonymous_13;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_13);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position__anonymous_13);
			}
		}
	}
	public void parse__anonymous_12() {
		int _position__anonymous_12 = -1;
		Token.Parsed _token__anonymous_12 = null;
		list_names.start(_position);
		_position__anonymous_12=_position;
		_token__anonymous_12=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		parse__anonymous_13();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_12)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_12;
		}
		else {
			parse_definition();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"rule(_anonymous_12)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_12;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_12.addAll(_token);
			_token__anonymous_12.setValue(_token.getValue());
		}
		_token=_token__anonymous_12;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_12);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_12);
		}
	}
	public void parse__anonymous_15() {
		int _position__anonymous_15 = -1;
		Token.Parsed _token__anonymous_15 = null;
		list_names.start(_position);
		_position__anonymous_15=_position;
		_token__anonymous_15=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		parse__anonymous_16();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_15)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_15;
		}
		else {
			parse_quote();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_15)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_15;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_15.addAll(_token);
			_token__anonymous_15.setValue(_token.getValue());
		}
		_token=_token__anonymous_15;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_15);
			_state=SUCCESS;
			_position__anonymous_15=_position;
			_token__anonymous_15=_token;
			_token=new Token.Parsed(Token.Id.ANON);
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_15)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_15;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_15.addAll(_token);
				_token__anonymous_15.setValue(_token.getValue());
			}
			_token=_token__anonymous_15;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_15);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position__anonymous_15);
			}
		}
	}
	public void parse__anonymous_14() {
		int _position__anonymous_14 = -1;
		Token.Parsed _token__anonymous_14 = null;
		int _position_NAME = -1;
		Token.Parsed _token_NAME = null;
		list_names.start(_position);
		_position__anonymous_14=_position;
		_token__anonymous_14=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		if(_position+4-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='w') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='i') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='t') {
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='h') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_17.SYNTAX);
			_position=_position+4;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain with");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_14)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_14;
		}
		else {
			_token_NAME=_token;
			_token=new Tokens.Name.ListRuleNameToken();
			_position_NAME=_position;
			parse_NAME();
			if(_state==SUCCESS) {
				_token_NAME.add(_position_NAME,_token);
			}
			_token=_token_NAME;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_14)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_14;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_14.addAll(_token);
			_token__anonymous_14.setValue(_token.getValue());
		}
		_token=_token__anonymous_14;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_14);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_14);
		}
	}
	public void parse__anonymous_17() {
		int _position__anonymous_17 = -1;
		Token.Parsed _token__anonymous_17 = null;
		list_names.start(_position);
		_position__anonymous_17=_position;
		_token__anonymous_17=_token;
		_token=new Tokens.Name.ChoiceToken();
		parse__anonymous_18();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(_anonymous_17)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_17;
		}
		else {
			parse_definition();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"definition(_anonymous_17)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_17;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token__anonymous_17.add(_position__anonymous_17,_token);
		}
		_token=_token__anonymous_17;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_17);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position__anonymous_17);
		}
	}
	public void parse__anonymous_16() {
		int _position__anonymous_16 = -1;
		Token.Parsed _token__anonymous_16 = null;
		list_names.start(_position);
		_position__anonymous_16=_position;
		_token__anonymous_16=_token;
		_token=new Token.Parsed(Token.Id.ANON);
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!=',') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_6.SYNTAX);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_16)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_16;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token__anonymous_16.addAll(_token);
			_token__anonymous_16.setValue(_token.getValue());
		}
		_token=_token__anonymous_16;
		if(_state==FAILED) {
			list_names.reject(_position__anonymous_16);
			_state=SUCCESS;
			_position__anonymous_16=_position;
			_token__anonymous_16=_token;
			_token=new Token.Parsed(Token.Id.ANON);
			if(_position+2-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='\n'&&_inputArray[_position+0]!='\r') {
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='\t') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_14.SYNTAX);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \n\t");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"list(_anonymous_16)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_16;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token__anonymous_16.addAll(_token);
				_token__anonymous_16.setValue(_token.getValue());
			}
			_token=_token__anonymous_16;
			if(_state==FAILED) {
				list_names.reject(_position__anonymous_16);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position__anonymous_16);
			}
		}
	}
}