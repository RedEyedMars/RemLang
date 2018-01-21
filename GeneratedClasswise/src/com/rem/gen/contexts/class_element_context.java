package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.name_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class class_element_context extends name_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public class_element_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public class_element_context() {
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
	public void parse_class_element(){
		int _position_class_element = -1;
		Token.Parsed _token_class_element = null;
		int _position_comments = -1;
		int _position_variable_declaration = -1;
		int _position_method_declaration = -1;
		int _position_class_declaration = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_class_element=_position;
		_token_class_element=_token;
		_token=new Tokens.Rule.ClassElementToken();
		_position_comments=_position;
		if(_state==SUCCESS&&!_recursion_protection_comments_20.contains(_position)){
			_recursion_protection_comments_20.add(_position);
			parse_comments();
			_recursion_protection_comments_20.remove(_position_comments);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
				_furthestPosition=_position;
			}
			_position=_position_class_element;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_class_element.addAll(_token);
			_token_class_element.setValue(_token.getValue());
		}
		_token=_token_class_element;
		if(_state==FAILED){
			class_names.reject(_position_class_element);
			class_variable_names.reject(_position_class_element);
			variable_names.reject(_position_class_element);
			_state=SUCCESS;
			_position_class_element=_position;
			_token_class_element=_token;
			_token=new Tokens.Rule.ClassElementToken();
			_position_class_declaration=_position;
			if(_state==SUCCESS&&!_recursion_protection_class_declaration_21.contains(_position)){
				_recursion_protection_class_declaration_21.add(_position);
				parse_class_declaration();
				_recursion_protection_class_declaration_21.remove(_position_class_declaration);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
					_furthestPosition=_position;
				}
				_position=_position_class_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_class_element.addAll(_token);
				_token_class_element.setValue(_token.getValue());
			}
			_token=_token_class_element;
			if(_state==FAILED){
				class_names.reject(_position_class_element);
				class_variable_names.reject(_position_class_element);
				variable_names.reject(_position_class_element);
				_state=SUCCESS;
				_position_class_element=_position;
				_token_class_element=_token;
				_token=new Tokens.Rule.ClassElementToken();
				_position_method_declaration=_position;
				if(_state==SUCCESS&&!_recursion_protection_method_declaration_22.contains(_position)){
					_recursion_protection_method_declaration_22.add(_position);
					parse_method_declaration();
					_recursion_protection_method_declaration_22.remove(_position_method_declaration);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
						_furthestPosition=_position;
					}
					_position=_position_class_element;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_class_element.addAll(_token);
					_token_class_element.setValue(_token.getValue());
				}
				_token=_token_class_element;
				if(_state==FAILED){
					class_names.reject(_position_class_element);
					class_variable_names.reject(_position_class_element);
					variable_names.reject(_position_class_element);
					_state=SUCCESS;
					_position_class_element=_position;
					_token_class_element=_token;
					_token=new Tokens.Rule.ClassElementToken();
					_position_variable_declaration=_position;
					if(_state==SUCCESS&&!_recursion_protection_variable_declaration_23.contains(_position)){
						_recursion_protection_variable_declaration_23.add(_position);
						parse_variable_declaration();
						_recursion_protection_variable_declaration_23.remove(_position_variable_declaration);
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
							_furthestPosition=_position;
						}
						_position=_position_class_element;
					}
					else{
						if(_position+1-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!=';'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_element(class_element)");
								_furthestPosition=_position;
							}
							_position=_position_class_element;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token_class_element.addAll(_token);
						_token_class_element.setValue(_token.getValue());
					}
					_token=_token_class_element;
					if(_state==FAILED){
						class_names.reject(_position_class_element);
						class_variable_names.reject(_position_class_element);
						variable_names.reject(_position_class_element);
					}
					else if(_state==SUCCESS){
						class_names.accept(_position_class_element);
						class_variable_names.accept(_position_class_element);
						variable_names.accept(_position_class_element);
					}
				}
			}
		}
	}
}