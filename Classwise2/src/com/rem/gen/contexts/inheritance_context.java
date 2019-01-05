package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.class_file_name_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class inheritance_context extends class_file_name_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public inheritance_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public inheritance_context() {
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
	public void parse_inheritance_declaration() {
		int _position_inheritance_declaration = -1;
		Token.Parsed _token_inheritance_declaration = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_inheritance_declaration=_position;
		_token_inheritance_declaration=_token;
		_token=new Tokens.Rule.InheritanceDeclarationToken();
		if(_position+9-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='i') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='n') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='h') {
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='e') {
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='r') {
				_state=FAILED;
			}
			if(_inputArray[_position+5]!='i') {
				_state=FAILED;
			}
			if(_inputArray[_position+6]!='t') {
				_state=FAILED;
			}
			if(_inputArray[_position+7]!='s') {
				_state=FAILED;
			}
			if(_inputArray[_position+8]!=' ') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_22.SYNTAX);
			_position=_position+9;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain inherits ");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inheritance_declaration(inheritance_declaration)");
				_furthestPosition=_position;
			}
			_position=_position_inheritance_declaration;
		}
		else {
			parse_NAME();
			if(_state==SUCCESS) {
				String _value = _token.getLastValue();
				if(_value!=null) {
					variable_names.add(_value);
				}
				_token.add(_position,new Tokens.Name.VariableNameToken(_value));
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inheritance_declaration(inheritance_declaration)");
					_furthestPosition=_position;
				}
				_position=_position_inheritance_declaration;
			}
			else {
				int _state_37 = _state;
				while(_position<_inputLength) {
					parse__anonymous_22();
					if(_state==FAILED) {
						break;
					}
				}
				if(_state_37==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inheritance_declaration(inheritance_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_inheritance_declaration;
				}
				else {
				}
			}
		}
		if(_state==SUCCESS) {
			_token_inheritance_declaration.add(_position_inheritance_declaration,_token);
		}
		_token=_token_inheritance_declaration;
		if(_state==FAILED) {
			class_names.reject(_position_inheritance_declaration);
			class_variable_names.reject(_position_inheritance_declaration);
			variable_names.reject(_position_inheritance_declaration);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_inheritance_declaration);
			class_variable_names.accept(_position_inheritance_declaration);
			variable_names.accept(_position_inheritance_declaration);
		}
	}
}