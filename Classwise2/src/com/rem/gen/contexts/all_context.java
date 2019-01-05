package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.quote_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class all_context extends quote_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public all_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public all_context() {
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
	public void parse_all_type_name() {
		int _position_all_type_name = -1;
		Token.Parsed _token_all_type_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_all_type_name=_position;
		_token_all_type_name=_token;
		_token=new Tokens.Rule.AllTypeNameToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='%') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_48.SYNTAX);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
				_furthestPosition=_position;
			}
			_position=_position_all_type_name;
		}
		else {
			parse_non_class_name();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
					_furthestPosition=_position;
				}
				_position=_position_all_type_name;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token_all_type_name.add(_position_all_type_name,_token);
		}
		_token=_token_all_type_name;
		if(_state==FAILED) {
			class_names.reject(_position_all_type_name);
			class_variable_names.reject(_position_all_type_name);
			variable_names.reject(_position_all_type_name);
			_state=SUCCESS;
			_position_all_type_name=_position;
			_token_all_type_name=_token;
			_token=new Tokens.Rule.AllTypeNameToken();
			parse_type_var();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
					_furthestPosition=_position;
				}
				_position=_position_all_type_name;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_all_type_name.add(_position_all_type_name,_token);
			}
			_token=_token_all_type_name;
			if(_state==FAILED) {
				class_names.reject(_position_all_type_name);
				class_variable_names.reject(_position_all_type_name);
				variable_names.reject(_position_all_type_name);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_all_type_name);
				class_variable_names.accept(_position_all_type_name);
				variable_names.accept(_position_all_type_name);
			}
		}
	}
}