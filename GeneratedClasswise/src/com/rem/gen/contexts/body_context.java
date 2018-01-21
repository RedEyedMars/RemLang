package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.class_file_name_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class body_context extends class_file_name_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public body_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public body_context() {
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
	public void parse_body_conditional(){
		int _position_body_conditional = -1;
		Token.Parsed _token_body_conditional = null;
		int _position_inner = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_conditional=_position;
		_token_body_conditional=_token;
		_token=new Tokens.Rule.BodyConditionalToken();
		int _state_39 = _state;
		_position_inner=_position;
		if(_state==SUCCESS&&!_recursion_protection_inner_28.contains(_position)){
			_recursion_protection_inner_28.add(_position);
			parse_inner();
			_recursion_protection_inner_28.remove(_position_inner);
		}
		else{
			_state=FAILED;
		}
		if(_state_39==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
				_furthestPosition=_position;
			}
			_position=_position_body_conditional;
		}
		else{
			parse__anonymous_29();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
					_furthestPosition=_position;
				}
				_position=_position_body_conditional;
			}
			else{
				parse_body_statement();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else{
					parse__anonymous_30();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token_body_conditional.add(_position_body_conditional,_token);
		}
		_token=_token_body_conditional;
		if(_state==FAILED){
			class_names.reject(_position_body_conditional);
			class_variable_names.reject(_position_body_conditional);
			variable_names.reject(_position_body_conditional);
			_state=SUCCESS;
			_position_body_conditional=_position;
			_token_body_conditional=_token;
			_token=new Tokens.Rule.BodyConditionalToken();
			int _state_40 = _state;
			_position_inner=_position;
			if(_state==SUCCESS&&!_recursion_protection_inner_29.contains(_position)){
				_recursion_protection_inner_29.add(_position);
				parse_inner();
				_recursion_protection_inner_29.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_40==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
					_furthestPosition=_position;
				}
				_position=_position_body_conditional;
			}
			else{
				if(_position+4-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='l'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='s'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='e'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_22.conditional);
					_position=_position+4;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain else");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else{
					parse__anonymous_31();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else{
					}
				}
			}
			if(_state==SUCCESS){
				_token_body_conditional.add(_position_body_conditional,_token);
			}
			_token=_token_body_conditional;
			if(_state==FAILED){
				class_names.reject(_position_body_conditional);
				class_variable_names.reject(_position_body_conditional);
				variable_names.reject(_position_body_conditional);
				_state=SUCCESS;
				_position_body_conditional=_position;
				_token_body_conditional=_token;
				_token=new Tokens.Rule.BodyConditionalToken();
				int _state_41 = _state;
				_position_inner=_position;
				if(_state==SUCCESS&&!_recursion_protection_inner_30.contains(_position)){
					_recursion_protection_inner_30.add(_position);
					parse_inner();
					_recursion_protection_inner_30.remove(_position_inner);
				}
				else{
					_state=FAILED;
				}
				if(_state_41==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else{
					if(_position+3-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='f'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='o'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='r'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_27.conditional);
						_position=_position+3;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain for");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else{
						parse_variable_declaration();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else{
							parse_OPERATOR();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else{
								parse_body_statement();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else{
									parse__anonymous_32();
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else{
									}
								}
							}
						}
					}
				}
				if(_state==SUCCESS){
					_token_body_conditional.add(_position_body_conditional,_token);
				}
				_token=_token_body_conditional;
				if(_state==FAILED){
					class_names.reject(_position_body_conditional);
					class_variable_names.reject(_position_body_conditional);
					variable_names.reject(_position_body_conditional);
					_state=SUCCESS;
					_position_body_conditional=_position;
					_token_body_conditional=_token;
					_token=new Tokens.Rule.BodyConditionalToken();
					int _state_42 = _state;
					_position_inner=_position;
					if(_state==SUCCESS&&!_recursion_protection_inner_31.contains(_position)){
						_recursion_protection_inner_31.add(_position);
						parse_inner();
						_recursion_protection_inner_31.remove(_position_inner);
					}
					else{
						_state=FAILED;
					}
					if(_state_42==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else{
						if(_position+3-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='t'){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='r'){
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='y'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_28.conditional);
							_position=_position+3;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain try");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else{
							parse__anonymous_33();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else{
							}
						}
					}
					if(_state==SUCCESS){
						_token_body_conditional.add(_position_body_conditional,_token);
					}
					_token=_token_body_conditional;
					if(_state==FAILED){
						class_names.reject(_position_body_conditional);
						class_variable_names.reject(_position_body_conditional);
						variable_names.reject(_position_body_conditional);
						_state=SUCCESS;
						_position_body_conditional=_position;
						_token_body_conditional=_token;
						_token=new Tokens.Rule.BodyConditionalToken();
						int _state_43 = _state;
						_position_inner=_position;
						if(_state==SUCCESS&&!_recursion_protection_inner_32.contains(_position)){
							_recursion_protection_inner_32.add(_position);
							parse_inner();
							_recursion_protection_inner_32.remove(_position_inner);
						}
						else{
							_state=FAILED;
						}
						if(_state_43==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else{
							int _state_44 = _state;
							if(_position+5-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='p'){
									_state=FAILED;
								}
								if(_inputArray[_position+1]!='r'){
									_state=FAILED;
								}
								if(_inputArray[_position+2]!='i'){
									_state=FAILED;
								}
								if(_inputArray[_position+3]!='n'){
									_state=FAILED;
								}
								if(_inputArray[_position+4]!='t'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_29.__SYNTAX__);
								_position=_position+5;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain print");
									_furthestPosition=_position;
								}
							}
							if(_state_44==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else{
								if(_position+5-1 >=_inputLength){
									_state=FAILED;
								}
								else{
									if(_inputArray[_position+0]!='c'){
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='a'){
										_state=FAILED;
									}
									if(_inputArray[_position+2]!='t'){
										_state=FAILED;
									}
									if(_inputArray[_position+3]!='c'){
										_state=FAILED;
									}
									if(_inputArray[_position+4]!='h'){
										_state=FAILED;
									}
								}
								if(_state==SUCCESS){
									_token.add(_position,Tokens.Syntax.syntax_30.conditional);
									_position=_position+5;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
										++_position;
									}
								}
								else if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain catch");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else{
									parse__anonymous_34();
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else{
										int _state_45 = _state;
										while(_position<_inputLength){
											parse__anonymous_35();
											if(_state==FAILED){
												break;
											}
										}
										if(_state_45==SUCCESS&&_state==FAILED){
											_state=SUCCESS;
										}
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
												_furthestPosition=_position;
											}
											_position=_position_body_conditional;
										}
										else{
											parse__anonymous_37();
											if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
													_furthestPosition=_position;
												}
												_position=_position_body_conditional;
											}
											else{
											}
										}
									}
								}
							}
						}
						if(_state==SUCCESS){
							_token_body_conditional.add(_position_body_conditional,_token);
						}
						_token=_token_body_conditional;
						if(_state==FAILED){
							class_names.reject(_position_body_conditional);
							class_variable_names.reject(_position_body_conditional);
							variable_names.reject(_position_body_conditional);
						}
						else if(_state==SUCCESS){
							class_names.accept(_position_body_conditional);
							class_variable_names.accept(_position_body_conditional);
							variable_names.accept(_position_body_conditional);
						}
					}
				}
			}
		}
	}
	public void parse_body_statement(){
		int _position_body_statement = -1;
		Token.Parsed _token_body_statement = null;
		int _position_statement_as_string = -1;
		int _position_inner = -1;
		int _position_statement_as_char = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_statement=_position;
		_token_body_statement=_token;
		_token=new Tokens.Rule.BodyStatementToken();
		int _state_46 = _state;
		_position_inner=_position;
		if(_state==SUCCESS&&!_recursion_protection_inner_33.contains(_position)){
			_recursion_protection_inner_33.add(_position);
			parse_inner();
			_recursion_protection_inner_33.remove(_position_inner);
		}
		else{
			_state=FAILED;
		}
		if(_state_46==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
				_furthestPosition=_position;
			}
			_position=_position_body_statement;
		}
		else{
			parse_body_call();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
					_furthestPosition=_position;
				}
				_position=_position_body_statement;
			}
			else{
				int _state_47 = _state;
				while(_position<_inputLength){
					parse__anonymous_38();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_47==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
						_furthestPosition=_position;
					}
					_position=_position_body_statement;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token_body_statement.add(_position_body_statement,_token);
		}
		_token=_token_body_statement;
		if(_state==FAILED){
			class_names.reject(_position_body_statement);
			class_variable_names.reject(_position_body_statement);
			variable_names.reject(_position_body_statement);
			_state=SUCCESS;
			_position_body_statement=_position;
			_token_body_statement=_token;
			_token=new Tokens.Rule.BodyStatementToken();
			_position_statement_as_string=_position;
			if(_state==SUCCESS&&!_recursion_protection_statement_as_string_34.contains(_position)){
				_recursion_protection_statement_as_string_34.add(_position);
				parse_statement_as_string();
				_recursion_protection_statement_as_string_34.remove(_position_statement_as_string);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
					_furthestPosition=_position;
				}
				_position=_position_body_statement;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_body_statement.add(_position_body_statement,_token);
			}
			_token=_token_body_statement;
			if(_state==FAILED){
				class_names.reject(_position_body_statement);
				class_variable_names.reject(_position_body_statement);
				variable_names.reject(_position_body_statement);
				_state=SUCCESS;
				_position_body_statement=_position;
				_token_body_statement=_token;
				_token=new Tokens.Rule.BodyStatementToken();
				_position_statement_as_char=_position;
				if(_state==SUCCESS&&!_recursion_protection_statement_as_char_35.contains(_position)){
					_recursion_protection_statement_as_char_35.add(_position);
					parse_statement_as_char();
					_recursion_protection_statement_as_char_35.remove(_position_statement_as_char);
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
						_furthestPosition=_position;
					}
					_position=_position_body_statement;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_body_statement.add(_position_body_statement,_token);
				}
				_token=_token_body_statement;
				if(_state==FAILED){
					class_names.reject(_position_body_statement);
					class_variable_names.reject(_position_body_statement);
					variable_names.reject(_position_body_statement);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position_body_statement);
					class_variable_names.accept(_position_body_statement);
					variable_names.accept(_position_body_statement);
				}
			}
		}
	}
	public void parse_body_call(){
		int _position_body_call = -1;
		Token.Parsed _token_body_call = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_call=_position;
		_token_body_call=_token;
		_token=new Tokens.Rule.BodyCallToken();
		parse__anonymous_40();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
				_furthestPosition=_position;
			}
			_position=_position_body_call;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_body_call.add(_position_body_call,_token);
		}
		_token=_token_body_call;
		if(_state==FAILED){
			class_names.reject(_position_body_call);
			class_variable_names.reject(_position_body_call);
			variable_names.reject(_position_body_call);
			_state=SUCCESS;
			_position_body_call=_position;
			_token_body_call=_token;
			_token=new Tokens.Rule.BodyCallToken();
			parse__anonymous_42();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
					_furthestPosition=_position;
				}
				_position=_position_body_call;
			}
			else{
				int _state_54 = _state;
				while(_position<_inputLength){
					parse__anonymous_43();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_54==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
						_furthestPosition=_position;
					}
					_position=_position_body_call;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token_body_call.add(_position_body_call,_token);
			}
			_token=_token_body_call;
			if(_state==FAILED){
				class_names.reject(_position_body_call);
				class_variable_names.reject(_position_body_call);
				variable_names.reject(_position_body_call);
				_state=SUCCESS;
				_position_body_call=_position;
				_token_body_call=_token;
				_token=new Tokens.Rule.BodyCallToken();
				parse__anonymous_45();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
						_furthestPosition=_position;
					}
					_position=_position_body_call;
				}
				else{
					int _state_59 = _state;
					while(_position<_inputLength){
						parse__anonymous_46();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_59==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
							_furthestPosition=_position;
						}
						_position=_position_body_call;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token_body_call.add(_position_body_call,_token);
				}
				_token=_token_body_call;
				if(_state==FAILED){
					class_names.reject(_position_body_call);
					class_variable_names.reject(_position_body_call);
					variable_names.reject(_position_body_call);
					_state=SUCCESS;
					_position_body_call=_position;
					_token_body_call=_token;
					_token=new Tokens.Rule.BodyCallToken();
					parse__anonymous_49();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
							_furthestPosition=_position;
						}
						_position=_position_body_call;
					}
					else{
						int _state_65 = _state;
						while(_position<_inputLength){
							parse__anonymous_51();
							if(_state==FAILED){
								break;
							}
						}
						if(_state_65==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
								_furthestPosition=_position;
							}
							_position=_position_body_call;
						}
						else{
						}
					}
					if(_state==SUCCESS){
						_token_body_call.add(_position_body_call,_token);
					}
					_token=_token_body_call;
					if(_state==FAILED){
						class_names.reject(_position_body_call);
						class_variable_names.reject(_position_body_call);
						variable_names.reject(_position_body_call);
					}
					else if(_state==SUCCESS){
						class_names.accept(_position_body_call);
						class_variable_names.accept(_position_body_call);
						variable_names.accept(_position_body_call);
					}
				}
			}
		}
	}
	public void parse_body_manipulate(){
		int _position_body_manipulate = -1;
		Token.Parsed _token_body_manipulate = null;
		int _position_inner = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_manipulate=_position;
		_token_body_manipulate=_token;
		_token=new Tokens.Rule.BodyManipulateToken();
		int _state_68 = _state;
		_position_inner=_position;
		if(_state==SUCCESS&&!_recursion_protection_inner_39.contains(_position)){
			_recursion_protection_inner_39.add(_position);
			parse_inner();
			_recursion_protection_inner_39.remove(_position_inner);
		}
		else{
			_state=FAILED;
		}
		if(_state_68==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
				_furthestPosition=_position;
			}
			_position=_position_body_manipulate;
		}
		else{
			parse_type_var();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
					_furthestPosition=_position;
				}
				_position=_position_body_manipulate;
			}
			else{
				if(_position+2-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='+'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='='){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_34.methodName);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +=");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
						_furthestPosition=_position;
					}
					_position=_position_body_manipulate;
				}
				else{
					parse__anonymous_53();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
							_furthestPosition=_position;
						}
						_position=_position_body_manipulate;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token_body_manipulate.add(_position_body_manipulate,_token);
		}
		_token=_token_body_manipulate;
		if(_state==FAILED){
			class_names.reject(_position_body_manipulate);
			class_variable_names.reject(_position_body_manipulate);
			variable_names.reject(_position_body_manipulate);
			_state=SUCCESS;
			_position_body_manipulate=_position;
			_token_body_manipulate=_token;
			_token=new Tokens.Rule.BodyManipulateToken();
			int _state_69 = _state;
			_position_inner=_position;
			if(_state==SUCCESS&&!_recursion_protection_inner_40.contains(_position)){
				_recursion_protection_inner_40.add(_position);
				parse_inner();
				_recursion_protection_inner_40.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_69==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
					_furthestPosition=_position;
				}
				_position=_position_body_manipulate;
			}
			else{
				parse_name_var();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
						_furthestPosition=_position;
					}
					_position=_position_body_manipulate;
				}
				else{
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!=':'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_8.__SYNTAX__);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain :");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
							_furthestPosition=_position;
						}
						_position=_position_body_manipulate;
					}
					else{
						parse_NAME();
						if(_state==SUCCESS){
							String _value = _token.getLastValue();
							if(_value!=null){
								variable_names.add(_value);
							}
							_token.add(_position,new Tokens.Name.VariableNameToken(_value));
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
								_furthestPosition=_position;
							}
							_position=_position_body_manipulate;
						}
						else{
							int _state_70 = _state;
							boolean _iteration_achieved_70 = false;
							while(_position<_inputLength){
								parse__anonymous_54();
								if(_state==FAILED){
									break;
								}
								else{
									_iteration_achieved_70=true;
								}
							}
							if(_iteration_achieved_70==false){
								_state=FAILED;
							}
							else if(_state_70==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(body_manipulate)");
									_furthestPosition=_position;
								}
								_position=_position_body_manipulate;
							}
							else{
							}
						}
					}
				}
			}
			if(_state==SUCCESS){
				_token_body_manipulate.add(_position_body_manipulate,_token);
			}
			_token=_token_body_manipulate;
			if(_state==FAILED){
				class_names.reject(_position_body_manipulate);
				class_variable_names.reject(_position_body_manipulate);
				variable_names.reject(_position_body_manipulate);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position_body_manipulate);
				class_variable_names.accept(_position_body_manipulate);
				variable_names.accept(_position_body_manipulate);
			}
		}
	}
	public void parse_body_element(){
		int _position_body_element = -1;
		Token.Parsed _token_body_element = null;
		int _position_inner = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_element=_position;
		_token_body_element=_token;
		_token=new Tokens.Rule.BodyElementToken();
		parse_comments();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
				_furthestPosition=_position;
			}
			_position=_position_body_element;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_body_element.add(_position_body_element,_token);
		}
		_token=_token_body_element;
		if(_state==FAILED){
			class_names.reject(_position_body_element);
			class_variable_names.reject(_position_body_element);
			variable_names.reject(_position_body_element);
			_state=SUCCESS;
			_position_body_element=_position;
			_token_body_element=_token;
			_token=new Tokens.Rule.BodyElementToken();
			int _state_37 = _state;
			_position_inner=_position;
			if(_state==SUCCESS&&!_recursion_protection_inner_24.contains(_position)){
				_recursion_protection_inner_24.add(_position);
				parse_inner();
				_recursion_protection_inner_24.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_37==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
					_furthestPosition=_position;
				}
				_position=_position_body_element;
			}
			else{
				parse__anonymous_23();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
						_furthestPosition=_position;
					}
					_position=_position_body_element;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token_body_element.add(_position_body_element,_token);
			}
			_token=_token_body_element;
			if(_state==FAILED){
				class_names.reject(_position_body_element);
				class_variable_names.reject(_position_body_element);
				variable_names.reject(_position_body_element);
				_state=SUCCESS;
				_position_body_element=_position;
				_token_body_element=_token;
				_token=new Tokens.Rule.BodyElementToken();
				int _state_38 = _state;
				_position_inner=_position;
				if(_state==SUCCESS&&!_recursion_protection_inner_25.contains(_position)){
					_recursion_protection_inner_25.add(_position);
					parse_inner();
					_recursion_protection_inner_25.remove(_position_inner);
				}
				else{
					_state=FAILED;
				}
				if(_state_38==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
						_furthestPosition=_position;
					}
					_position=_position_body_element;
				}
				else{
					parse__anonymous_25();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
							_furthestPosition=_position;
						}
						_position=_position_body_element;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token_body_element.add(_position_body_element,_token);
				}
				_token=_token_body_element;
				if(_state==FAILED){
					class_names.reject(_position_body_element);
					class_variable_names.reject(_position_body_element);
					variable_names.reject(_position_body_element);
					_state=SUCCESS;
					_position_body_element=_position;
					_token_body_element=_token;
					_token=new Tokens.Rule.BodyElementToken();
					parse_class_declaration();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
							_furthestPosition=_position;
						}
						_position=_position_body_element;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_body_element.add(_position_body_element,_token);
					}
					_token=_token_body_element;
					if(_state==FAILED){
						class_names.reject(_position_body_element);
						class_variable_names.reject(_position_body_element);
						variable_names.reject(_position_body_element);
						_state=SUCCESS;
						_position_body_element=_position;
						_token_body_element=_token;
						_token=new Tokens.Rule.BodyElementToken();
						parse__anonymous_26();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
								_furthestPosition=_position;
							}
							_position=_position_body_element;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_body_element.add(_position_body_element,_token);
						}
						_token=_token_body_element;
						if(_state==FAILED){
							class_names.reject(_position_body_element);
							class_variable_names.reject(_position_body_element);
							variable_names.reject(_position_body_element);
							_state=SUCCESS;
							_position_body_element=_position;
							_token_body_element=_token;
							_token=new Tokens.Rule.BodyElementToken();
							parse__anonymous_27();
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
									_furthestPosition=_position;
								}
								_position=_position_body_element;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_body_element.add(_position_body_element,_token);
							}
							_token=_token_body_element;
							if(_state==FAILED){
								class_names.reject(_position_body_element);
								class_variable_names.reject(_position_body_element);
								variable_names.reject(_position_body_element);
								_state=SUCCESS;
								_position_body_element=_position;
								_token_body_element=_token;
								_token=new Tokens.Rule.BodyElementToken();
								parse_body_manipulate();
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
										_furthestPosition=_position;
									}
									_position=_position_body_element;
								}
								else{
								}
								if(_state==SUCCESS){
									_token_body_element.add(_position_body_element,_token);
								}
								_token=_token_body_element;
								if(_state==FAILED){
									class_names.reject(_position_body_element);
									class_variable_names.reject(_position_body_element);
									variable_names.reject(_position_body_element);
									_state=SUCCESS;
									_position_body_element=_position;
									_token_body_element=_token;
									_token=new Tokens.Rule.BodyElementToken();
									parse_body_conditional();
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
											_furthestPosition=_position;
										}
										_position=_position_body_element;
									}
									else{
									}
									if(_state==SUCCESS){
										_token_body_element.add(_position_body_element,_token);
									}
									_token=_token_body_element;
									if(_state==FAILED){
										class_names.reject(_position_body_element);
										class_variable_names.reject(_position_body_element);
										variable_names.reject(_position_body_element);
										_state=SUCCESS;
										_position_body_element=_position;
										_token_body_element=_token;
										_token=new Tokens.Rule.BodyElementToken();
										parse__anonymous_28();
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
												_furthestPosition=_position;
											}
											_position=_position_body_element;
										}
										else{
										}
										if(_state==SUCCESS){
											_token_body_element.add(_position_body_element,_token);
										}
										_token=_token_body_element;
										if(_state==FAILED){
											class_names.reject(_position_body_element);
											class_variable_names.reject(_position_body_element);
											variable_names.reject(_position_body_element);
										}
										else if(_state==SUCCESS){
											class_names.accept(_position_body_element);
											class_variable_names.accept(_position_body_element);
											variable_names.accept(_position_body_element);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}