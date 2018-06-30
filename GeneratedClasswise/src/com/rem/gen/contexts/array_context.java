package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.AnonymousContext;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class array_context extends AnonymousContext{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public array_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public array_context() {
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
	public void parse_array_parameters() {
		int _position_array_parameters = -1;
		Token.Parsed _token_array_parameters = null;
		int _length_array_parameters_brace = _inputLength;
		if(brace_5.containsKey(_position)) {
			class_variable_names=class_variable_names.push(_position,_pass);
			variable_names=variable_names.push(_position,_pass);
			_inputLength=brace_5.get(_position);
			int _position_array_parameters_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			class_names.start(_position);
			class_variable_names.start(_position);
			variable_names.start(_position);
			_position_array_parameters=_position;
			_token_array_parameters=_token;
			_token=new Tokens.Rule.ArrayParametersToken();
			int _state_17 = _state;
			parse__anonymous_3();
			if(_state_17==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(array_parameters)");
					_furthestPosition=_position;
				}
				_position=_position_array_parameters;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_array_parameters.add(_position_array_parameters,_token);
			}
			_token=_token_array_parameters;
			if(_state==SUCCESS&&brace_5.get(_position_array_parameters_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("array_parameters",_position,_lineNumberRanges));
				_position=brace_5.get(_position_array_parameters_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_array_parameters_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			if(_state==FAILED) {
				class_names.reject(_position_array_parameters);
				class_variable_names.reject(_position_array_parameters);
				variable_names.reject(_position_array_parameters);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_array_parameters);
				class_variable_names.accept(_position_array_parameters);
				variable_names.accept(_position_array_parameters);
			}
			class_variable_names=class_variable_names.pop();
			variable_names=variable_names.pop();
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(array_parameters)");
				_furthestPosition=_position;
			}
		}
	}
}