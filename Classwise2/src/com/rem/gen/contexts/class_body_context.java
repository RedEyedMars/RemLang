package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.number_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class class_body_context extends number_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public class_body_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public class_body_context() {
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
	public void parse_class_body() {
		int _position_class_body = -1;
		Token.Parsed _token_class_body = null;
		int _length_class_body_brace = _inputLength;
		if(brace_2.containsKey(_position)) {
			class_variable_names=class_variable_names.push(_position,_pass);
			variable_names=variable_names.push(_position,_pass);
			_inputLength=brace_2.get(_position);
			int _position_class_body_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			class_names.start(_position);
			class_variable_names.start(_position);
			variable_names.start(_position);
			_position_class_body=_position;
			_token_class_body=_token;
			_token=new Tokens.Rule.ClassBodyToken();
			int _state_12 = _state;
			while(_position<_inputLength) {
				parse_class_element();
				if(_state==FAILED) {
					break;
				}
			}
			if(_state_12==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_body(class_body)");
					_furthestPosition=_position;
				}
				_position=_position_class_body;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_class_body.addAll(_token);
				_token_class_body.setValue(_token.getValue());
			}
			_token=_token_class_body;
			if(_state==SUCCESS&&brace_2.get(_position_class_body_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("class_body",_position,_lineNumberRanges));
				_position=brace_2.get(_position_class_body_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_class_body_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			if(_state==FAILED) {
				class_names.reject(_position_class_body);
				class_variable_names.reject(_position_class_body);
				variable_names.reject(_position_class_body);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_class_body);
				class_variable_names.accept(_position_class_body);
				variable_names.accept(_position_class_body);
			}
			class_variable_names=class_variable_names.pop();
			variable_names=variable_names.pop();
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_body(class_body)");
				_furthestPosition=_position;
			}
		}
	}
}