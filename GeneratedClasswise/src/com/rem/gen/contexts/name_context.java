package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.statement_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class name_context extends statement_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public name_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public name_context() {
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
	public void parse_name_var_element(){
		int _position_name_var_element = -1;
		Token.Parsed _token_name_var_element = null;
		int _position_statement_as_string = -1;
		int _position_statement_as_method = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_name_var_element=_position;
		_token_name_var_element=_token;
		_token=new Tokens.Rule.NameVarElementToken();
		parse__anonymous_70();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
				_furthestPosition=_position;
			}
			_position=_position_name_var_element;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_name_var_element.addAll(_token);
			_token_name_var_element.setValue(_token.getValue());
		}
		_token=_token_name_var_element;
		if(_state==FAILED){
			class_names.reject(_position_name_var_element);
			class_variable_names.reject(_position_name_var_element);
			variable_names.reject(_position_name_var_element);
			_state=SUCCESS;
			_position_name_var_element=_position;
			_token_name_var_element=_token;
			_token=new Tokens.Rule.NameVarElementToken();
			parse_statement_as_char();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
					_furthestPosition=_position;
				}
				_position=_position_name_var_element;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_name_var_element.addAll(_token);
				_token_name_var_element.setValue(_token.getValue());
			}
			_token=_token_name_var_element;
			if(_state==FAILED){
				class_names.reject(_position_name_var_element);
				class_variable_names.reject(_position_name_var_element);
				variable_names.reject(_position_name_var_element);
				_state=SUCCESS;
				_position_name_var_element=_position;
				_token_name_var_element=_token;
				_token=new Tokens.Rule.NameVarElementToken();
				_position_statement_as_method=_position;
				if(_state==SUCCESS&&!_recursion_protection_statement_as_method_46.contains(_position)){
					_recursion_protection_statement_as_method_46.add(_position);
					parse_statement_as_method();
					_recursion_protection_statement_as_method_46.remove(_position_statement_as_method);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
						_furthestPosition=_position;
					}
					_position=_position_name_var_element;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_name_var_element.addAll(_token);
					_token_name_var_element.setValue(_token.getValue());
				}
				_token=_token_name_var_element;
				if(_state==FAILED){
					class_names.reject(_position_name_var_element);
					class_variable_names.reject(_position_name_var_element);
					variable_names.reject(_position_name_var_element);
					_state=SUCCESS;
					_position_name_var_element=_position;
					_token_name_var_element=_token;
					_token=new Tokens.Rule.NameVarElementToken();
					parse_statement_as_quote();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
							_furthestPosition=_position;
						}
						_position=_position_name_var_element;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_name_var_element.addAll(_token);
						_token_name_var_element.setValue(_token.getValue());
					}
					_token=_token_name_var_element;
					if(_state==FAILED){
						class_names.reject(_position_name_var_element);
						class_variable_names.reject(_position_name_var_element);
						variable_names.reject(_position_name_var_element);
						_state=SUCCESS;
						_position_name_var_element=_position;
						_token_name_var_element=_token;
						_token=new Tokens.Rule.NameVarElementToken();
						_position_statement_as_string=_position;
						if(_state==SUCCESS&&!_recursion_protection_statement_as_string_47.contains(_position)){
							_recursion_protection_statement_as_string_47.add(_position);
							parse_statement_as_string();
							_recursion_protection_statement_as_string_47.remove(_position_statement_as_string);
						}
						else{
							_state=FAILED;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
								_furthestPosition=_position;
							}
							_position=_position_name_var_element;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_name_var_element.addAll(_token);
							_token_name_var_element.setValue(_token.getValue());
						}
						_token=_token_name_var_element;
						if(_state==FAILED){
							class_names.reject(_position_name_var_element);
							class_variable_names.reject(_position_name_var_element);
							variable_names.reject(_position_name_var_element);
							_state=SUCCESS;
							_position_name_var_element=_position;
							_token_name_var_element=_token;
							_token=new Tokens.Rule.NameVarElementToken();
							parse__anonymous_71();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
									_furthestPosition=_position;
								}
								_position=_position_name_var_element;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_name_var_element.addAll(_token);
								_token_name_var_element.setValue(_token.getValue());
							}
							_token=_token_name_var_element;
							if(_state==FAILED){
								class_names.reject(_position_name_var_element);
								class_variable_names.reject(_position_name_var_element);
								variable_names.reject(_position_name_var_element);
								_state=SUCCESS;
								_position_name_var_element=_position;
								_token_name_var_element=_token;
								_token=new Tokens.Rule.NameVarElementToken();
								parse__anonymous_73();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
										_furthestPosition=_position;
									}
									_position=_position_name_var_element;
								}
								else{
								}
								if(_state==SUCCESS){
									_token_name_var_element.addAll(_token);
									_token_name_var_element.setValue(_token.getValue());
								}
								_token=_token_name_var_element;
								if(_state==FAILED){
									class_names.reject(_position_name_var_element);
									class_variable_names.reject(_position_name_var_element);
									variable_names.reject(_position_name_var_element);
									_state=SUCCESS;
									_position_name_var_element=_position;
									_token_name_var_element=_token;
									_token=new Tokens.Rule.NameVarElementToken();
									parse__anonymous_75();
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(name_var_element)");
											_furthestPosition=_position;
										}
										_position=_position_name_var_element;
									}
									else{
									}
									if(_state==SUCCESS){
										_token_name_var_element.addAll(_token);
										_token_name_var_element.setValue(_token.getValue());
									}
									_token=_token_name_var_element;
									if(_state==FAILED){
										class_names.reject(_position_name_var_element);
										class_variable_names.reject(_position_name_var_element);
										variable_names.reject(_position_name_var_element);
									}
									else if(_state==SUCCESS){
										class_names.accept(_position_name_var_element);
										class_variable_names.accept(_position_name_var_element);
										variable_names.accept(_position_name_var_element);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	public void parse_NAME(){
		int _position_NAME = -1;
		Token.Parsed _token_NAME = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_NAME=_position;
		_token_NAME=_token;
		_token=new Tokens.Rule.NAMEToken();
		int _position_regex_1 = _position;
		if(_position<_inputLength){
			if(_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F'||_inputArray[_position]=='G'||_inputArray[_position]=='H'||_inputArray[_position]=='I'||_inputArray[_position]=='J'||_inputArray[_position]=='K'||_inputArray[_position]=='L'||_inputArray[_position]=='M'||_inputArray[_position]=='N'||_inputArray[_position]=='O'||_inputArray[_position]=='P'||_inputArray[_position]=='Q'||_inputArray[_position]=='R'||_inputArray[_position]=='S'||_inputArray[_position]=='T'||_inputArray[_position]=='U'||_inputArray[_position]=='V'||_inputArray[_position]=='W'||_inputArray[_position]=='X'||_inputArray[_position]=='Y'||_inputArray[_position]=='Z'||_inputArray[_position]=='_'){
				++_position;
			}
			else{
				_state=FAILED;
			}
		}
		else{
			_state=FAILED;
		}
		while(_position<_inputLength){
			if(_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F'||_inputArray[_position]=='G'||_inputArray[_position]=='H'||_inputArray[_position]=='I'||_inputArray[_position]=='J'||_inputArray[_position]=='K'||_inputArray[_position]=='L'||_inputArray[_position]=='M'||_inputArray[_position]=='N'||_inputArray[_position]=='O'||_inputArray[_position]=='P'||_inputArray[_position]=='Q'||_inputArray[_position]=='R'||_inputArray[_position]=='S'||_inputArray[_position]=='T'||_inputArray[_position]=='U'||_inputArray[_position]=='V'||_inputArray[_position]=='W'||_inputArray[_position]=='X'||_inputArray[_position]=='Y'||_inputArray[_position]=='Z'||_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9'||_inputArray[_position]=='_'){
				++_position;
			}
			else{
				break;
			}
		}
		if(_state==SUCCESS){
			_token.setValue(_input.substring(_position_regex_1,_position));
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z_][a-zA-Z0-9_]*");
				_furthestPosition=_position;
			}
			_position=_position_regex_1;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"NAME(NAME)");
				_furthestPosition=_position;
			}
			_position=_position_NAME;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_NAME.add(_position_NAME,_token);
		}
		_token=_token_NAME;
		if(_state==FAILED){
			class_names.reject(_position_NAME);
			class_variable_names.reject(_position_NAME);
			variable_names.reject(_position_NAME);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position_NAME);
			class_variable_names.accept(_position_NAME);
			variable_names.accept(_position_NAME);
		}
	}
	public void parse_name_var(){
		int _position_name_var = -1;
		Token.Parsed _token_name_var = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_name_var=_position;
		_token_name_var=_token;
		_token=new Tokens.Rule.NameVarToken();
		parse__anonymous_65();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
				_furthestPosition=_position;
			}
			_position=_position_name_var;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_name_var.add(_position_name_var,_token);
		}
		_token=_token_name_var;
		if(_state==FAILED){
			class_names.reject(_position_name_var);
			class_variable_names.reject(_position_name_var);
			variable_names.reject(_position_name_var);
			_state=SUCCESS;
			_position_name_var=_position;
			_token_name_var=_token;
			_token=new Tokens.Rule.NameVarToken();
			parse__anonymous_68();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
					_furthestPosition=_position;
				}
				_position=_position_name_var;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_name_var.add(_position_name_var,_token);
			}
			_token=_token_name_var;
			if(_state==FAILED){
				class_names.reject(_position_name_var);
				class_variable_names.reject(_position_name_var);
				variable_names.reject(_position_name_var);
				_state=SUCCESS;
				_position_name_var=_position;
				_token_name_var=_token;
				_token=new Tokens.Rule.NameVarToken();
				parse_name_var_element();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
						_furthestPosition=_position;
					}
					_position=_position_name_var;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_name_var.add(_position_name_var,_token);
				}
				_token=_token_name_var;
				if(_state==FAILED){
					class_names.reject(_position_name_var);
					class_variable_names.reject(_position_name_var);
					variable_names.reject(_position_name_var);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position_name_var);
					class_variable_names.accept(_position_name_var);
					variable_names.accept(_position_name_var);
				}
			}
		}
	}
}