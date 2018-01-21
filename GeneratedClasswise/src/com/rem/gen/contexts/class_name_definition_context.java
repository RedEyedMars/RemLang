package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.class_element_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class class_name_definition_context extends class_element_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public class_name_definition_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public class_name_definition_context() {
	}
	public Parser get_Parser(){
		return __parser__;
	}
	public void set_Parser(Parser new_Parser){
		__parser__ = new_Parser;
	}
	public Tokens get_Tokens(){
		return __tokens__;
	}
	public void set_Tokens(Tokens new_Tokens){
		__tokens__ = new_Tokens;
	}
	public void parse_class_name_definition(){
		int _position_class_name_definition = -1;
		Token.Parsed _token_class_name_definition = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_class_name_definition=_position;
		_token_class_name_definition=_token;
		_token=new Tokens.Rule.ClassNameDefinitionToken();
		if(_position+5-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='C'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='l'){
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='a'){
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='s'){
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='s'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_42.typeName);
			_position=_position+5;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Class");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(class_name_definition)");
				_furthestPosition=_position;
			}
			_position=_position_class_name_definition;
		}
		else{
			parse__anonymous_63();
			if(_state==SUCCESS){
				String _value = _token.getLastValue();
				if(_value!=null){
					class_variable_names.add(_value);
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(class_name_definition)");
					_furthestPosition=_position;
				}
				_position=_position_class_name_definition;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token_class_name_definition.addAll(_token);
			_token_class_name_definition.setValue(_token.getValue());
		}
		_token=_token_class_name_definition;
		if(_state==FAILED){
			class_names.reject(_position_class_name_definition);
			class_variable_names.reject(_position_class_name_definition);
			variable_names.reject(_position_class_name_definition);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position_class_name_definition);
			class_variable_names.accept(_position_class_name_definition);
			variable_names.accept(_position_class_name_definition);
		}
	}
}