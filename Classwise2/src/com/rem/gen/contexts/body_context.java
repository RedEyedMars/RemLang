package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.inheritance_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class body_context extends inheritance_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public body_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public body_context() {
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
	public void parse_body_access_token() {
		int _position_body_access_token = -1;
		Token.Parsed _token_body_access_token = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_access_token=_position;
		_token_body_access_token=_token;
		_token=new Tokens.Rule.BodyAccessTokenToken();
		parse__anonymous_58();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_access_token(body_access_token)");
				_furthestPosition=_position;
			}
			_position=_position_body_access_token;
		}
		else {
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!=':') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_11.SYNTAX);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain :");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_access_token(body_access_token)");
					_furthestPosition=_position;
				}
				_position=_position_body_access_token;
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_access_token(body_access_token)");
						_furthestPosition=_position;
					}
					_position=_position_body_access_token;
				}
				else {
					int _state_62 = _state;
					boolean _iteration_achieved_62 = false;
					while(_position<_inputLength) {
						parse__anonymous_60();
						if(_state==FAILED) {
							break;
						}
						else {
							_iteration_achieved_62=true;
						}
					}
					if(_iteration_achieved_62==false) {
						_state=FAILED;
					}
					else if(_state_62==SUCCESS&&_state==FAILED) {
						_state=SUCCESS;
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_access_token(body_access_token)");
							_furthestPosition=_position;
						}
						_position=_position_body_access_token;
					}
					else {
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_body_access_token.add(_position_body_access_token,_token);
		}
		_token=_token_body_access_token;
		if(_state==FAILED) {
			class_names.reject(_position_body_access_token);
			class_variable_names.reject(_position_body_access_token);
			variable_names.reject(_position_body_access_token);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_body_access_token);
			class_variable_names.accept(_position_body_access_token);
			variable_names.accept(_position_body_access_token);
		}
	}
	public void parse_body_conditional() {
		int _position_body_conditional = -1;
		Token.Parsed _token_body_conditional = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_conditional=_position;
		_token_body_conditional=_token;
		_token=new Tokens.Rule.BodyConditionalToken();
		if(_position+2-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='i') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='f') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_26.SYNTAX);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain if");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
				_furthestPosition=_position;
			}
			_position=_position_body_conditional;
		}
		else {
			parse__anonymous_25();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
					_furthestPosition=_position;
				}
				_position=_position_body_conditional;
			}
			else {
				if(_position+5-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='e') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='l') {
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='s') {
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='e') {
						_state=FAILED;
					}
					if(_inputArray[_position+4]!=' ') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_29.SYNTAX);
					_position=_position+5;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain else ");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else {
					parse__anonymous_26();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else {
						if(_position+1-1 >=_inputLength) {
							_state=FAILED;
						}
						else {
							if(_inputArray[_position+0]!='?') {
								_state=FAILED;
							}
						}
						if(_state==SUCCESS) {
							_token.add(_position,Tokens.Syntax.syntax_21.SYNTAX);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
								++_position;
							}
						}
						else if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ?");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else {
							parse_body_statement();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else {
								parse__anonymous_27();
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else {
								}
							}
						}
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_body_conditional.add(_position_body_conditional,_token);
		}
		_token=_token_body_conditional;
		if(_state==FAILED) {
			class_names.reject(_position_body_conditional);
			class_variable_names.reject(_position_body_conditional);
			variable_names.reject(_position_body_conditional);
			_state=SUCCESS;
			_position_body_conditional=_position;
			_token_body_conditional=_token;
			_token=new Tokens.Rule.BodyConditionalToken();
			parse__anonymous_28();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
					_furthestPosition=_position;
				}
				_position=_position_body_conditional;
			}
			else {
				parse_body_statement();
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else {
					parse__anonymous_29();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else {
					}
				}
			}
			if(_state==SUCCESS) {
				_token_body_conditional.add(_position_body_conditional,_token);
			}
			_token=_token_body_conditional;
			if(_state==FAILED) {
				class_names.reject(_position_body_conditional);
				class_variable_names.reject(_position_body_conditional);
				variable_names.reject(_position_body_conditional);
				_state=SUCCESS;
				_position_body_conditional=_position;
				_token_body_conditional=_token;
				_token=new Tokens.Rule.BodyConditionalToken();
				if(_position+4-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='e') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='l') {
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='s') {
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='e') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_31.conditional);
					_position=_position+4;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain else");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
						_furthestPosition=_position;
					}
					_position=_position_body_conditional;
				}
				else {
					parse__anonymous_30();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else {
					}
				}
				if(_state==SUCCESS) {
					_token_body_conditional.add(_position_body_conditional,_token);
				}
				_token=_token_body_conditional;
				if(_state==FAILED) {
					class_names.reject(_position_body_conditional);
					class_variable_names.reject(_position_body_conditional);
					variable_names.reject(_position_body_conditional);
					_state=SUCCESS;
					_position_body_conditional=_position;
					_token_body_conditional=_token;
					_token=new Tokens.Rule.BodyConditionalToken();
					if(_position+4-1 >=_inputLength) {
						_state=FAILED;
					}
					else {
						if(_inputArray[_position+0]!='f') {
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='o') {
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='r') {
							_state=FAILED;
						}
						if(_inputArray[_position+3]!=' ') {
							_state=FAILED;
						}
					}
					if(_state==SUCCESS) {
						_token.add(_position,Tokens.Syntax.syntax_36.conditional);
						_position=_position+4;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
							++_position;
						}
					}
					else if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain for ");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
							_furthestPosition=_position;
						}
						_position=_position_body_conditional;
					}
					else {
						parse__anonymous_31();
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else {
							parse__anonymous_33();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else {
								parse_body_statement();
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else {
									parse__anonymous_34();
									if(_state==FAILED) {
										if(_position>=_furthestPosition) {
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else {
									}
								}
							}
						}
					}
					if(_state==SUCCESS) {
						_token_body_conditional.add(_position_body_conditional,_token);
					}
					_token=_token_body_conditional;
					if(_state==FAILED) {
						class_names.reject(_position_body_conditional);
						class_variable_names.reject(_position_body_conditional);
						variable_names.reject(_position_body_conditional);
						_state=SUCCESS;
						_position_body_conditional=_position;
						_token_body_conditional=_token;
						_token=new Tokens.Rule.BodyConditionalToken();
						if(_position+3-1 >=_inputLength) {
							_state=FAILED;
						}
						else {
							if(_inputArray[_position+0]!='t') {
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='r') {
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='y') {
								_state=FAILED;
							}
						}
						if(_state==SUCCESS) {
							_token.add(_position,Tokens.Syntax.syntax_40.conditional);
							_position=_position+3;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
								++_position;
							}
						}
						else if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain try");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
								_furthestPosition=_position;
							}
							_position=_position_body_conditional;
						}
						else {
							parse__anonymous_35();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else {
							}
						}
						if(_state==SUCCESS) {
							_token_body_conditional.add(_position_body_conditional,_token);
						}
						_token=_token_body_conditional;
						if(_state==FAILED) {
							class_names.reject(_position_body_conditional);
							class_variable_names.reject(_position_body_conditional);
							variable_names.reject(_position_body_conditional);
							_state=SUCCESS;
							_position_body_conditional=_position;
							_token_body_conditional=_token;
							_token=new Tokens.Rule.BodyConditionalToken();
							int _state_40 = _state;
							if(_position+5-1 >=_inputLength) {
								_state=FAILED;
							}
							else {
								if(_inputArray[_position+0]!='p') {
									_state=FAILED;
								}
								if(_inputArray[_position+1]!='r') {
									_state=FAILED;
								}
								if(_inputArray[_position+2]!='i') {
									_state=FAILED;
								}
								if(_inputArray[_position+3]!='n') {
									_state=FAILED;
								}
								if(_inputArray[_position+4]!='t') {
									_state=FAILED;
								}
							}
							if(_state==SUCCESS) {
								_token.add(_position,Tokens.Syntax.syntax_41.PRINT);
								_position=_position+5;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
									++_position;
								}
							}
							else if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain print");
									_furthestPosition=_position;
								}
							}
							if(_state_40==SUCCESS&&_state==FAILED) {
								_state=SUCCESS;
							}
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
									_furthestPosition=_position;
								}
								_position=_position_body_conditional;
							}
							else {
								if(_position+5-1 >=_inputLength) {
									_state=FAILED;
								}
								else {
									if(_inputArray[_position+0]!='c') {
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='a') {
										_state=FAILED;
									}
									if(_inputArray[_position+2]!='t') {
										_state=FAILED;
									}
									if(_inputArray[_position+3]!='c') {
										_state=FAILED;
									}
									if(_inputArray[_position+4]!='h') {
										_state=FAILED;
									}
								}
								if(_state==SUCCESS) {
									_token.add(_position,Tokens.Syntax.syntax_42.conditional);
									_position=_position+5;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
										++_position;
									}
								}
								else if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain catch");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
										_furthestPosition=_position;
									}
									_position=_position_body_conditional;
								}
								else {
									parse__anonymous_36();
									if(_state==FAILED) {
										if(_position>=_furthestPosition) {
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
											_furthestPosition=_position;
										}
										_position=_position_body_conditional;
									}
									else {
										int _state_41 = _state;
										while(_position<_inputLength) {
											parse__anonymous_37();
											if(_state==FAILED) {
												break;
											}
										}
										if(_state_41==SUCCESS&&_state==FAILED) {
											_state=SUCCESS;
										}
										if(_state==FAILED) {
											if(_position>=_furthestPosition) {
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
												_furthestPosition=_position;
											}
											_position=_position_body_conditional;
										}
										else {
											parse__anonymous_39();
											if(_state==FAILED) {
												if(_position>=_furthestPosition) {
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(body_conditional)");
													_furthestPosition=_position;
												}
												_position=_position_body_conditional;
											}
											else {
											}
										}
									}
								}
							}
							if(_state==SUCCESS) {
								_token_body_conditional.add(_position_body_conditional,_token);
							}
							_token=_token_body_conditional;
							if(_state==FAILED) {
								class_names.reject(_position_body_conditional);
								class_variable_names.reject(_position_body_conditional);
								variable_names.reject(_position_body_conditional);
							}
							else if(_state==SUCCESS) {
								class_names.accept(_position_body_conditional);
								class_variable_names.accept(_position_body_conditional);
								variable_names.accept(_position_body_conditional);
							}
						}
					}
				}
			}
		}
	}
	public void parse_body_statement() {
		int _position_body_statement = -1;
		Token.Parsed _token_body_statement = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_statement=_position;
		_token_body_statement=_token;
		_token=new Tokens.Rule.BodyStatementToken();
		parse__anonymous_40();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
				_furthestPosition=_position;
			}
			_position=_position_body_statement;
		}
		else {
			int _state_43 = _state;
			while(_position<_inputLength) {
				parse__anonymous_41();
				if(_state==FAILED) {
					break;
				}
			}
			if(_state_43==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(body_statement)");
					_furthestPosition=_position;
				}
				_position=_position_body_statement;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token_body_statement.add(_position_body_statement,_token);
		}
		_token=_token_body_statement;
		if(_state==FAILED) {
			class_names.reject(_position_body_statement);
			class_variable_names.reject(_position_body_statement);
			variable_names.reject(_position_body_statement);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_body_statement);
			class_variable_names.accept(_position_body_statement);
			variable_names.accept(_position_body_statement);
		}
	}
	public void parse_body_call() {
		int _position_body_call = -1;
		Token.Parsed _token_body_call = null;
		int _position_as_statement = -1;
		int _position_all_type_name = -1;
		int _position_statement_as_braced = -1;
		Token.Parsed _token_all_type_name = null;
		Token.Parsed _token_statement_as_braced = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_call=_position;
		_token_body_call=_token;
		_token=new Tokens.Rule.BodyCallToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='(') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_44.SYNTAX);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain (");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
				_furthestPosition=_position;
			}
			_position=_position_body_call;
		}
		else {
			_token_all_type_name=_token;
			_token=new Tokens.Name.CastStatementToken();
			_position_all_type_name=_position;
			parse_all_type_name();
			if(_state==SUCCESS) {
				_token_all_type_name.add(_position_all_type_name,_token);
			}
			_token=_token_all_type_name;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
					_furthestPosition=_position;
				}
				_position=_position_body_call;
			}
			else {
				if(_position+1-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!=')') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_45.SYNTAX);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain )");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
						_furthestPosition=_position;
					}
					_position=_position_body_call;
				}
				else {
					parse__anonymous_42();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
							_furthestPosition=_position;
						}
						_position=_position_body_call;
					}
					else {
						int _state_47 = _state;
						while(_position<_inputLength) {
							parse__anonymous_45();
							if(_state==FAILED) {
								break;
							}
						}
						if(_state_47==SUCCESS&&_state==FAILED) {
							_state=SUCCESS;
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
								_furthestPosition=_position;
							}
							_position=_position_body_call;
						}
						else {
						}
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_body_call.add(_position_body_call,_token);
		}
		_token=_token_body_call;
		if(_state==FAILED) {
			class_names.reject(_position_body_call);
			class_variable_names.reject(_position_body_call);
			variable_names.reject(_position_body_call);
			_state=SUCCESS;
			_position_body_call=_position;
			_token_body_call=_token;
			_token=new Tokens.Rule.BodyCallToken();
			_token_statement_as_braced=_token;
			_token=new Tokens.Name.AsBracedToken();
			_position_statement_as_braced=_position;
			parse_statement_as_braced();
			if(_state==SUCCESS) {
				_token_statement_as_braced.add(_position_statement_as_braced,_token);
			}
			_token=_token_statement_as_braced;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
					_furthestPosition=_position;
				}
				_position=_position_body_call;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_body_call.add(_position_body_call,_token);
			}
			_token=_token_body_call;
			if(_state==FAILED) {
				class_names.reject(_position_body_call);
				class_variable_names.reject(_position_body_call);
				variable_names.reject(_position_body_call);
				_state=SUCCESS;
				_position_body_call=_position;
				_token_body_call=_token;
				_token=new Tokens.Rule.BodyCallToken();
				_position_as_statement=_position;
				if(_state==SUCCESS&&!_recursion_protection_as_statement_25.contains(_position)) {
					_recursion_protection_as_statement_25.add(_position);
					parse_as_statement();
					_recursion_protection_as_statement_25.remove(_position_as_statement);
				}
				else {
					_state=FAILED;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
						_furthestPosition=_position;
					}
					_position=_position_body_call;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_body_call.add(_position_body_call,_token);
				}
				_token=_token_body_call;
				if(_state==FAILED) {
					class_names.reject(_position_body_call);
					class_variable_names.reject(_position_body_call);
					variable_names.reject(_position_body_call);
					_state=SUCCESS;
					_position_body_call=_position;
					_token_body_call=_token;
					_token=new Tokens.Rule.BodyCallToken();
					parse__anonymous_48();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
							_furthestPosition=_position;
						}
						_position=_position_body_call;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token_body_call.add(_position_body_call,_token);
					}
					_token=_token_body_call;
					if(_state==FAILED) {
						class_names.reject(_position_body_call);
						class_variable_names.reject(_position_body_call);
						variable_names.reject(_position_body_call);
						_state=SUCCESS;
						_position_body_call=_position;
						_token_body_call=_token;
						_token=new Tokens.Rule.BodyCallToken();
						parse__anonymous_50();
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
								_furthestPosition=_position;
							}
							_position=_position_body_call;
						}
						else {
							int _state_56 = _state;
							while(_position<_inputLength) {
								parse__anonymous_52();
								if(_state==FAILED) {
									break;
								}
							}
							if(_state_56==SUCCESS&&_state==FAILED) {
								_state=SUCCESS;
							}
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(body_call)");
									_furthestPosition=_position;
								}
								_position=_position_body_call;
							}
							else {
							}
						}
						if(_state==SUCCESS) {
							_token_body_call.add(_position_body_call,_token);
						}
						_token=_token_body_call;
						if(_state==FAILED) {
							class_names.reject(_position_body_call);
							class_variable_names.reject(_position_body_call);
							variable_names.reject(_position_body_call);
						}
						else if(_state==SUCCESS) {
							class_names.accept(_position_body_call);
							class_variable_names.accept(_position_body_call);
							variable_names.accept(_position_body_call);
						}
					}
				}
			}
		}
	}
	public void parse_body_add_to_class() {
		int _position_body_add_to_class = -1;
		Token.Parsed _token_body_add_to_class = null;
		int _position_type_var = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_add_to_class=_position;
		_token_body_add_to_class=_token;
		_token=new Tokens.Rule.BodyAddToClassToken();
		_position_type_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_type_var_33.contains(_position)) {
			_recursion_protection_type_var_33.add(_position);
			parse_type_var();
			_recursion_protection_type_var_33.remove(_position_type_var);
		}
		else {
			_state=FAILED;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_add_to_class(body_add_to_class)");
				_furthestPosition=_position;
			}
			_position=_position_body_add_to_class;
		}
		else {
			int _state_59 = _state;
			parse__anonymous_55();
			if(_state_59==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_add_to_class(body_add_to_class)");
					_furthestPosition=_position;
				}
				_position=_position_body_add_to_class;
			}
			else {
				if(_position+2-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='+') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='=') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_49.add);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +=");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_add_to_class(body_add_to_class)");
						_furthestPosition=_position;
					}
					_position=_position_body_add_to_class;
				}
				else {
					parse__anonymous_57();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_add_to_class(body_add_to_class)");
							_furthestPosition=_position;
						}
						_position=_position_body_add_to_class;
					}
					else {
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_body_add_to_class.add(_position_body_add_to_class,_token);
		}
		_token=_token_body_add_to_class;
		if(_state==FAILED) {
			class_names.reject(_position_body_add_to_class);
			class_variable_names.reject(_position_body_add_to_class);
			variable_names.reject(_position_body_add_to_class);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_body_add_to_class);
			class_variable_names.accept(_position_body_add_to_class);
			variable_names.accept(_position_body_add_to_class);
		}
	}
	public void parse_body_element() {
		int _position_body_element = -1;
		Token.Parsed _token_body_element = null;
		int _position_comments = -1;
		int _position_variable_declaration = -1;
		int _position_statement_as_method = -1;
		int _position_body_statement = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_body_element=_position;
		_token_body_element=_token;
		_token=new Tokens.Rule.BodyElementToken();
		_position_comments=_position;
		if(_state==SUCCESS&&!_recursion_protection_comments_12.contains(_position)) {
			_recursion_protection_comments_12.add(_position);
			parse_comments();
			_recursion_protection_comments_12.remove(_position_comments);
		}
		else {
			_state=FAILED;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
				_furthestPosition=_position;
			}
			_position=_position_body_element;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_body_element.add(_position_body_element,_token);
		}
		_token=_token_body_element;
		if(_state==FAILED) {
			class_names.reject(_position_body_element);
			class_variable_names.reject(_position_body_element);
			variable_names.reject(_position_body_element);
			_state=SUCCESS;
			_position_body_element=_position;
			_token_body_element=_token;
			_token=new Tokens.Rule.BodyElementToken();
			parse__anonymous_23();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
					_furthestPosition=_position;
				}
				_position=_position_body_element;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_body_element.add(_position_body_element,_token);
			}
			_token=_token_body_element;
			if(_state==FAILED) {
				class_names.reject(_position_body_element);
				class_variable_names.reject(_position_body_element);
				variable_names.reject(_position_body_element);
				_state=SUCCESS;
				_position_body_element=_position;
				_token_body_element=_token;
				_token=new Tokens.Rule.BodyElementToken();
				parse__anonymous_24();
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
						_furthestPosition=_position;
					}
					_position=_position_body_element;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_body_element.add(_position_body_element,_token);
				}
				_token=_token_body_element;
				if(_state==FAILED) {
					class_names.reject(_position_body_element);
					class_variable_names.reject(_position_body_element);
					variable_names.reject(_position_body_element);
					_state=SUCCESS;
					_position_body_element=_position;
					_token_body_element=_token;
					_token=new Tokens.Rule.BodyElementToken();
					parse_body_access_token();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
							_furthestPosition=_position;
						}
						_position=_position_body_element;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token_body_element.add(_position_body_element,_token);
					}
					_token=_token_body_element;
					if(_state==FAILED) {
						class_names.reject(_position_body_element);
						class_variable_names.reject(_position_body_element);
						variable_names.reject(_position_body_element);
						_state=SUCCESS;
						_position_body_element=_position;
						_token_body_element=_token;
						_token=new Tokens.Rule.BodyElementToken();
						_position_variable_declaration=_position;
						if(_state==SUCCESS&&!_recursion_protection_variable_declaration_13.contains(_position)) {
							_recursion_protection_variable_declaration_13.add(_position);
							parse_variable_declaration();
							_recursion_protection_variable_declaration_13.remove(_position_variable_declaration);
						}
						else {
							_state=FAILED;
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
								_furthestPosition=_position;
							}
							_position=_position_body_element;
						}
						else {
						}
						if(_state==SUCCESS) {
							_token_body_element.add(_position_body_element,_token);
						}
						_token=_token_body_element;
						if(_state==FAILED) {
							class_names.reject(_position_body_element);
							class_variable_names.reject(_position_body_element);
							variable_names.reject(_position_body_element);
							_state=SUCCESS;
							_position_body_element=_position;
							_token_body_element=_token;
							_token=new Tokens.Rule.BodyElementToken();
							parse_variable_assignment();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
									_furthestPosition=_position;
								}
								_position=_position_body_element;
							}
							else {
								if(_position+1-1 >=_inputLength) {
									_state=FAILED;
								}
								else {
									if(_inputArray[_position+0]!=';') {
										_state=FAILED;
									}
								}
								if(_state==SUCCESS) {
									_token.add(_position,Tokens.Syntax.syntax_24.SYNTAX);
									_position=_position+1;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
										++_position;
									}
								}
								else if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
										_furthestPosition=_position;
									}
									_position=_position_body_element;
								}
								else {
								}
							}
							if(_state==SUCCESS) {
								_token_body_element.add(_position_body_element,_token);
							}
							_token=_token_body_element;
							if(_state==FAILED) {
								class_names.reject(_position_body_element);
								class_variable_names.reject(_position_body_element);
								variable_names.reject(_position_body_element);
								_state=SUCCESS;
								_position_body_element=_position;
								_token_body_element=_token;
								_token=new Tokens.Rule.BodyElementToken();
								parse_body_add_to_class();
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
										_furthestPosition=_position;
									}
									_position=_position_body_element;
								}
								else {
								}
								if(_state==SUCCESS) {
									_token_body_element.add(_position_body_element,_token);
								}
								_token=_token_body_element;
								if(_state==FAILED) {
									class_names.reject(_position_body_element);
									class_variable_names.reject(_position_body_element);
									variable_names.reject(_position_body_element);
									_state=SUCCESS;
									_position_body_element=_position;
									_token_body_element=_token;
									_token=new Tokens.Rule.BodyElementToken();
									parse_body_conditional();
									if(_state==FAILED) {
										if(_position>=_furthestPosition) {
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
											_furthestPosition=_position;
										}
										_position=_position_body_element;
									}
									else {
									}
									if(_state==SUCCESS) {
										_token_body_element.add(_position_body_element,_token);
									}
									_token=_token_body_element;
									if(_state==FAILED) {
										class_names.reject(_position_body_element);
										class_variable_names.reject(_position_body_element);
										variable_names.reject(_position_body_element);
										_state=SUCCESS;
										_position_body_element=_position;
										_token_body_element=_token;
										_token=new Tokens.Rule.BodyElementToken();
										_position_body_statement=_position;
										if(_state==SUCCESS&&!_recursion_protection_body_statement_14.contains(_position)) {
											_recursion_protection_body_statement_14.add(_position);
											parse_body_statement();
											_recursion_protection_body_statement_14.remove(_position_body_statement);
										}
										else {
											_state=FAILED;
										}
										if(_state==FAILED) {
											if(_position>=_furthestPosition) {
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
												_furthestPosition=_position;
											}
											_position=_position_body_element;
										}
										else {
											if(_position+1-1 >=_inputLength) {
												_state=FAILED;
											}
											else {
												if(_inputArray[_position+0]!=';') {
													_state=FAILED;
												}
											}
											if(_state==SUCCESS) {
												_token.add(_position,Tokens.Syntax.syntax_24.SYNTAX);
												_position=_position+1;
												while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
													++_position;
												}
											}
											else if(_state==FAILED) {
												if(_position>=_furthestPosition) {
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
													_furthestPosition=_position;
												}
											}
											if(_state==FAILED) {
												if(_position>=_furthestPosition) {
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
													_furthestPosition=_position;
												}
												_position=_position_body_element;
											}
											else {
											}
										}
										if(_state==SUCCESS) {
											_token_body_element.add(_position_body_element,_token);
										}
										_token=_token_body_element;
										if(_state==FAILED) {
											class_names.reject(_position_body_element);
											class_variable_names.reject(_position_body_element);
											variable_names.reject(_position_body_element);
											_state=SUCCESS;
											_position_body_element=_position;
											_token_body_element=_token;
											_token=new Tokens.Rule.BodyElementToken();
											_position_statement_as_method=_position;
											if(_state==SUCCESS&&!_recursion_protection_statement_as_method_15.contains(_position)) {
												_recursion_protection_statement_as_method_15.add(_position);
												parse_statement_as_method();
												_recursion_protection_statement_as_method_15.remove(_position_statement_as_method);
											}
											else {
												_state=FAILED;
											}
											if(_state==FAILED) {
												if(_position>=_furthestPosition) {
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(body_element)");
													_furthestPosition=_position;
												}
												_position=_position_body_element;
											}
											else {
											}
											if(_state==SUCCESS) {
												_token_body_element.add(_position_body_element,_token);
											}
											_token=_token_body_element;
											if(_state==FAILED) {
												class_names.reject(_position_body_element);
												class_variable_names.reject(_position_body_element);
												variable_names.reject(_position_body_element);
											}
											else if(_state==SUCCESS) {
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
}