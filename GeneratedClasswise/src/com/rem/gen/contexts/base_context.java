package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.anonymous_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class base_context extends anonymous_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public base_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public base_context() {
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
	public void parse_base_element(){
		int _position_base_element = -1;
		Token.Parsed _token_base_element = null;
		int _position_comments = -1;
		int _position_imports = -1;
		int _position_variable_declaration = -1;
		int _position_method_declaration = -1;
		int _position_class_declaration = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_base_element=_position;
		_token_base_element=_token;
		_token=new Tokens.Rule.BaseElementToken();
		_position_comments=_position;
		if(_state==SUCCESS&&!_recursion_protection_comments_7.contains(_position)){
			_recursion_protection_comments_7.add(_position);
			parse_comments();
			_recursion_protection_comments_7.remove(_position_comments);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
				_furthestPosition=_position;
			}
			_position=_position_base_element;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_base_element.addAll(_token);
			_token_base_element.setValue(_token.getValue());
		}
		_token=_token_base_element;
		if(_state==FAILED){
			class_names.reject(_position_base_element);
			class_variable_names.reject(_position_base_element);
			variable_names.reject(_position_base_element);
			_state=SUCCESS;
			_position_base_element=_position;
			_token_base_element=_token;
			_token=new Tokens.Rule.BaseElementToken();
			_position_imports=_position;
			if(_state==SUCCESS&&!_recursion_protection_imports_8.contains(_position)){
				_recursion_protection_imports_8.add(_position);
				parse_imports();
				_recursion_protection_imports_8.remove(_position_imports);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
					_furthestPosition=_position;
				}
				_position=_position_base_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_base_element.addAll(_token);
				_token_base_element.setValue(_token.getValue());
			}
			_token=_token_base_element;
			if(_state==FAILED){
				class_names.reject(_position_base_element);
				class_variable_names.reject(_position_base_element);
				variable_names.reject(_position_base_element);
				_state=SUCCESS;
				_position_base_element=_position;
				_token_base_element=_token;
				_token=new Tokens.Rule.BaseElementToken();
				parse_anonymous_class();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
						_furthestPosition=_position;
					}
					_position=_position_base_element;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_base_element.addAll(_token);
					_token_base_element.setValue(_token.getValue());
				}
				_token=_token_base_element;
				if(_state==FAILED){
					class_names.reject(_position_base_element);
					class_variable_names.reject(_position_base_element);
					variable_names.reject(_position_base_element);
					_state=SUCCESS;
					_position_base_element=_position;
					_token_base_element=_token;
					_token=new Tokens.Rule.BaseElementToken();
					_position_class_declaration=_position;
					if(_state==SUCCESS&&!_recursion_protection_class_declaration_9.contains(_position)){
						_recursion_protection_class_declaration_9.add(_position);
						parse_class_declaration();
						_recursion_protection_class_declaration_9.remove(_position_class_declaration);
					}
					else{
						_state=FAILED;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
							_furthestPosition=_position;
						}
						_position=_position_base_element;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_base_element.addAll(_token);
						_token_base_element.setValue(_token.getValue());
					}
					_token=_token_base_element;
					if(_state==FAILED){
						class_names.reject(_position_base_element);
						class_variable_names.reject(_position_base_element);
						variable_names.reject(_position_base_element);
						_state=SUCCESS;
						_position_base_element=_position;
						_token_base_element=_token;
						_token=new Tokens.Rule.BaseElementToken();
						_position_method_declaration=_position;
						if(_state==SUCCESS&&!_recursion_protection_method_declaration_10.contains(_position)){
							_recursion_protection_method_declaration_10.add(_position);
							parse_method_declaration();
							_recursion_protection_method_declaration_10.remove(_position_method_declaration);
						}
						else{
							_state=FAILED;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
								_furthestPosition=_position;
							}
							_position=_position_base_element;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_base_element.addAll(_token);
							_token_base_element.setValue(_token.getValue());
						}
						_token=_token_base_element;
						if(_state==FAILED){
							class_names.reject(_position_base_element);
							class_variable_names.reject(_position_base_element);
							variable_names.reject(_position_base_element);
							_state=SUCCESS;
							_position_base_element=_position;
							_token_base_element=_token;
							_token=new Tokens.Rule.BaseElementToken();
							_position_variable_declaration=_position;
							if(_state==SUCCESS&&!_recursion_protection_variable_declaration_11.contains(_position)){
								_recursion_protection_variable_declaration_11.add(_position);
								parse_variable_declaration();
								_recursion_protection_variable_declaration_11.remove(_position_variable_declaration);
							}
							else{
								_state=FAILED;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
									_furthestPosition=_position;
								}
								_position=_position_base_element;
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
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base_element(base_element)");
										_furthestPosition=_position;
									}
									_position=_position_base_element;
								}
								else{
								}
							}
							if(_state==SUCCESS){
								_token_base_element.addAll(_token);
								_token_base_element.setValue(_token.getValue());
							}
							_token=_token_base_element;
							if(_state==FAILED){
								class_names.reject(_position_base_element);
								class_variable_names.reject(_position_base_element);
								variable_names.reject(_position_base_element);
							}
							else if(_state==SUCCESS){
								class_names.accept(_position_base_element);
								class_variable_names.accept(_position_base_element);
								variable_names.accept(_position_base_element);
							}
						}
					}
				}
			}
		}
	}
	public void parse_base(){
		int _position_base = -1;
		Token.Parsed _token_base = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_base=_position;
		_token_base=_token;
		_token=new Tokens.Rule.BaseToken();
		int _state_0 = _state;
		while(_position<_inputLength){
			parse_base_element();
			if(_state==FAILED){
				break;
			}
		}
		if(_state_0==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"base(base)");
				_furthestPosition=_position;
			}
			_position=_position_base;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_base.addAll(_token);
			_token_base.setValue(_token.getValue());
		}
		_token=_token_base;
		if(_state==FAILED){
			class_names.reject(_position_base);
			class_variable_names.reject(_position_base);
			variable_names.reject(_position_base);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position_base);
			class_variable_names.accept(_position_base);
			variable_names.accept(_position_base);
		}
	}
}