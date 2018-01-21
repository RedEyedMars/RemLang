package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.all_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class type_context extends all_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public type_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public type_context() {
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
	public void parse_type_var(){
		int _position_type_var = -1;
		Token.Parsed _token_type_var = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_type_var=_position;
		_token_type_var=_token;
		_token=new Tokens.Rule.TypeVarToken();
		parse__anonymous_78();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
				_furthestPosition=_position;
			}
			_position=_position_type_var;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_type_var.add(_position_type_var,_token);
		}
		_token=_token_type_var;
		if(_state==FAILED){
			class_names.reject(_position_type_var);
			class_variable_names.reject(_position_type_var);
			variable_names.reject(_position_type_var);
			_state=SUCCESS;
			_position_type_var=_position;
			_token_type_var=_token;
			_token=new Tokens.Rule.TypeVarToken();
			parse__anonymous_83();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
					_furthestPosition=_position;
				}
				_position=_position_type_var;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_type_var.add(_position_type_var,_token);
			}
			_token=_token_type_var;
			if(_state==FAILED){
				class_names.reject(_position_type_var);
				class_variable_names.reject(_position_type_var);
				variable_names.reject(_position_type_var);
				_state=SUCCESS;
				_position_type_var=_position;
				_token_type_var=_token;
				_token=new Tokens.Rule.TypeVarToken();
				parse__anonymous_86();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
						_furthestPosition=_position;
					}
					_position=_position_type_var;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_type_var.add(_position_type_var,_token);
				}
				_token=_token_type_var;
				if(_state==FAILED){
					class_names.reject(_position_type_var);
					class_variable_names.reject(_position_type_var);
					variable_names.reject(_position_type_var);
					_state=SUCCESS;
					_position_type_var=_position;
					_token_type_var=_token;
					_token=new Tokens.Rule.TypeVarToken();
					int _state_103 = _state;
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='$'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_57.ISTYPENAME);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain $");
							_furthestPosition=_position;
						}
					}
					if(_state_103==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
							_furthestPosition=_position;
						}
						_position=_position_type_var;
					}
					else{
						parse_type_var_element();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(type_var)");
								_furthestPosition=_position;
							}
							_position=_position_type_var;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token_type_var.add(_position_type_var,_token);
					}
					_token=_token_type_var;
					if(_state==FAILED){
						class_names.reject(_position_type_var);
						class_variable_names.reject(_position_type_var);
						variable_names.reject(_position_type_var);
					}
					else if(_state==SUCCESS){
						class_names.accept(_position_type_var);
						class_variable_names.accept(_position_type_var);
						variable_names.accept(_position_type_var);
					}
				}
			}
		}
	}
	public void parse_type_var_element(){
		int _position_type_var_element = -1;
		Token.Parsed _token_type_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_type_var_element=_position;
		_token_type_var_element=_token;
		_token=new Tokens.Rule.TypeVarElementToken();
		parse_statement_as_method();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
				_furthestPosition=_position;
			}
			_position=_position_type_var_element;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_type_var_element.addAll(_token);
			_token_type_var_element.setValue(_token.getValue());
		}
		_token=_token_type_var_element;
		if(_state==FAILED){
			class_names.reject(_position_type_var_element);
			class_variable_names.reject(_position_type_var_element);
			variable_names.reject(_position_type_var_element);
			_state=SUCCESS;
			_position_type_var_element=_position;
			_token_type_var_element=_token;
			_token=new Tokens.Rule.TypeVarElementToken();
			parse_statement_as_quote();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
					_furthestPosition=_position;
				}
				_position=_position_type_var_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_type_var_element.addAll(_token);
				_token_type_var_element.setValue(_token.getValue());
			}
			_token=_token_type_var_element;
			if(_state==FAILED){
				class_names.reject(_position_type_var_element);
				class_variable_names.reject(_position_type_var_element);
				variable_names.reject(_position_type_var_element);
				_state=SUCCESS;
				_position_type_var_element=_position;
				_token_type_var_element=_token;
				_token=new Tokens.Rule.TypeVarElementToken();
				parse_statement_as_string();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
						_furthestPosition=_position;
					}
					_position=_position_type_var_element;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_type_var_element.addAll(_token);
					_token_type_var_element.setValue(_token.getValue());
				}
				_token=_token_type_var_element;
				if(_state==FAILED){
					class_names.reject(_position_type_var_element);
					class_variable_names.reject(_position_type_var_element);
					variable_names.reject(_position_type_var_element);
					_state=SUCCESS;
					_position_type_var_element=_position;
					_token_type_var_element=_token;
					_token=new Tokens.Rule.TypeVarElementToken();
					parse__anonymous_88();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
							_furthestPosition=_position;
						}
						_position=_position_type_var_element;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_type_var_element.addAll(_token);
						_token_type_var_element.setValue(_token.getValue());
					}
					_token=_token_type_var_element;
					if(_state==FAILED){
						class_names.reject(_position_type_var_element);
						class_variable_names.reject(_position_type_var_element);
						variable_names.reject(_position_type_var_element);
						_state=SUCCESS;
						_position_type_var_element=_position;
						_token_type_var_element=_token;
						_token=new Tokens.Rule.TypeVarElementToken();
						parse__anonymous_90();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(type_var_element)");
								_furthestPosition=_position;
							}
							_position=_position_type_var_element;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_type_var_element.addAll(_token);
							_token_type_var_element.setValue(_token.getValue());
						}
						_token=_token_type_var_element;
						if(_state==FAILED){
							class_names.reject(_position_type_var_element);
							class_variable_names.reject(_position_type_var_element);
							variable_names.reject(_position_type_var_element);
						}
						else if(_state==SUCCESS){
							class_names.accept(_position_type_var_element);
							class_variable_names.accept(_position_type_var_element);
							variable_names.accept(_position_type_var_element);
						}
					}
				}
			}
		}
	}
}