package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.array_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class class_file_name_context extends array_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public class_file_name_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public class_file_name_context() {
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
	public void parse_class_file_name(){
		int _position_class_file_name = -1;
		Token.Parsed _token_class_file_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_class_file_name=_position;
		_token_class_file_name=_token;
		_token=new Tokens.Rule.ClassFileNameToken();
		int _position_regex_6 = _position;
		int _multiple_index_21 = 0;
		while(_position<_inputLength){
			if(_inputArray[_position]!='.'){
				++_position;
				++_multiple_index_21;
			}
			else{
				break;
			}
		}
		if(_multiple_index_21==0 ){
			_state=FAILED;
		}
		if(_state==SUCCESS){
			_token.setValue(_input.substring(_position_regex_6,_position));
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[.]+");
				_furthestPosition=_position;
			}
			_position=_position_regex_6;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_file_name(class_file_name)");
				_furthestPosition=_position;
			}
			_position=_position_class_file_name;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_class_file_name.add(_position_class_file_name,_token);
		}
		_token=_token_class_file_name;
		if(_state==FAILED){
			class_names.reject(_position_class_file_name);
			class_variable_names.reject(_position_class_file_name);
			variable_names.reject(_position_class_file_name);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position_class_file_name);
			class_variable_names.accept(_position_class_file_name);
			variable_names.accept(_position_class_file_name);
		}
	}
}