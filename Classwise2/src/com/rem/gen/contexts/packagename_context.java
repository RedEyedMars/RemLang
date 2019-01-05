package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.cast_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class packagename_context extends cast_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public packagename_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public packagename_context() {
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
	public void parse_packageName() {
		int _position_packageName = -1;
		Token.Parsed _token_packageName = null;
		int _position_name_var = -1;
		int _position_NAME = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_packageName=_position;
		_token_packageName=_token;
		_token=new Tokens.Rule.PackageNameToken();
		_position_name_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_name_var_6.contains(_position)) {
			_recursion_protection_name_var_6.add(_position);
			parse_name_var();
			_recursion_protection_name_var_6.remove(_position_name_var);
		}
		else {
			_state=FAILED;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
				_furthestPosition=_position;
			}
			_position=_position_packageName;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_packageName.add(_position_packageName,_token);
		}
		_token=_token_packageName;
		if(_state==FAILED) {
			class_names.reject(_position_packageName);
			class_variable_names.reject(_position_packageName);
			variable_names.reject(_position_packageName);
			_state=SUCCESS;
			_position_packageName=_position;
			_token_packageName=_token;
			_token=new Tokens.Rule.PackageNameToken();
			_position_NAME=_position;
			if(_state==SUCCESS&&!_recursion_protection_NAME_7.contains(_position)) {
				_recursion_protection_NAME_7.add(_position);
				parse_NAME();
				_recursion_protection_NAME_7.remove(_position_NAME);
			}
			else {
				_state=FAILED;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
					_furthestPosition=_position;
				}
				_position=_position_packageName;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_packageName.add(_position_packageName,_token);
			}
			_token=_token_packageName;
			if(_state==FAILED) {
				class_names.reject(_position_packageName);
				class_variable_names.reject(_position_packageName);
				variable_names.reject(_position_packageName);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_packageName);
				class_variable_names.accept(_position_packageName);
				variable_names.accept(_position_packageName);
			}
		}
	}
}