package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.import_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class non_context extends import_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public non_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public non_context() {
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
	public void parse_non_class_name() {
		int _position_non_class_name = -1;
		Token.Parsed _token_non_class_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_non_class_name=_position;
		_token_non_class_name=_token;
		_token=new Tokens.Rule.NonClassNameToken();
		if(_position+5-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='C') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='l') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='a') {
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='s') {
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='s') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_58.OutputClass);
			_position=_position+5;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Class");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
				_furthestPosition=_position;
			}
			_position=_position_non_class_name;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_non_class_name.add(_position_non_class_name,_token);
		}
		_token=_token_non_class_name;
		if(_state==FAILED) {
			class_names.reject(_position_non_class_name);
			class_variable_names.reject(_position_non_class_name);
			variable_names.reject(_position_non_class_name);
			_state=SUCCESS;
			_position_non_class_name=_position;
			_token_non_class_name=_token;
			_token=new Tokens.Rule.NonClassNameToken();
			if(_position+6-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='M') {
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='e') {
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='t') {
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='h') {
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='o') {
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='d') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_59.OutputMethod);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Method");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
					_furthestPosition=_position;
				}
				_position=_position_non_class_name;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_non_class_name.add(_position_non_class_name,_token);
			}
			_token=_token_non_class_name;
			if(_state==FAILED) {
				class_names.reject(_position_non_class_name);
				class_variable_names.reject(_position_non_class_name);
				variable_names.reject(_position_non_class_name);
				_state=SUCCESS;
				_position_non_class_name=_position;
				_token_non_class_name=_token;
				_token=new Tokens.Rule.NonClassNameToken();
				if(_position+8-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='V') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='a') {
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='r') {
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='i') {
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='a') {
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='b') {
						_state=FAILED;
					}
					if(_inputArray[_position+6]!='l') {
						_state=FAILED;
					}
					if(_inputArray[_position+7]!='e') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_60.OutputVariable);
					_position=_position+8;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Variable");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
						_furthestPosition=_position;
					}
					_position=_position_non_class_name;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_non_class_name.add(_position_non_class_name,_token);
				}
				_token=_token_non_class_name;
				if(_state==FAILED) {
					class_names.reject(_position_non_class_name);
					class_variable_names.reject(_position_non_class_name);
					variable_names.reject(_position_non_class_name);
					_state=SUCCESS;
					_position_non_class_name=_position;
					_token_non_class_name=_token;
					_token=new Tokens.Rule.NonClassNameToken();
					if(_position+7-1 >=_inputLength) {
						_state=FAILED;
					}
					else {
						if(_inputArray[_position+0]!='C') {
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='o') {
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='n') {
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='t') {
							_state=FAILED;
						}
						if(_inputArray[_position+4]!='e') {
							_state=FAILED;
						}
						if(_inputArray[_position+5]!='x') {
							_state=FAILED;
						}
						if(_inputArray[_position+6]!='t') {
							_state=FAILED;
						}
					}
					if(_state==SUCCESS) {
						_token.add(_position,Tokens.Syntax.syntax_61.OutputContext);
						_position=_position+7;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
							++_position;
						}
					}
					else if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Context");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
							_furthestPosition=_position;
						}
						_position=_position_non_class_name;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token_non_class_name.add(_position_non_class_name,_token);
					}
					_token=_token_non_class_name;
					if(_state==FAILED) {
						class_names.reject(_position_non_class_name);
						class_variable_names.reject(_position_non_class_name);
						variable_names.reject(_position_non_class_name);
						_state=SUCCESS;
						_position_non_class_name=_position;
						_token_non_class_name=_token;
						_token=new Tokens.Rule.NonClassNameToken();
						parse__anonymous_79();
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
								_furthestPosition=_position;
							}
							_position=_position_non_class_name;
						}
						else {
						}
						if(_state==SUCCESS) {
							_token_non_class_name.add(_position_non_class_name,_token);
						}
						_token=_token_non_class_name;
						if(_state==FAILED) {
							class_names.reject(_position_non_class_name);
							class_variable_names.reject(_position_non_class_name);
							variable_names.reject(_position_non_class_name);
							_state=SUCCESS;
							_position_non_class_name=_position;
							_token_non_class_name=_token;
							_token=new Tokens.Rule.NonClassNameToken();
							parse__anonymous_80();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
									_furthestPosition=_position;
								}
								_position=_position_non_class_name;
							}
							else {
							}
							if(_state==SUCCESS) {
								_token_non_class_name.add(_position_non_class_name,_token);
							}
							_token=_token_non_class_name;
							if(_state==FAILED) {
								class_names.reject(_position_non_class_name);
								class_variable_names.reject(_position_non_class_name);
								variable_names.reject(_position_non_class_name);
								_state=SUCCESS;
								_position_non_class_name=_position;
								_token_non_class_name=_token;
								_token=new Tokens.Rule.NonClassNameToken();
								if(_position+5-1 >=_inputLength) {
									_state=FAILED;
								}
								else {
									if(_inputArray[_position+0]!='Q') {
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='u') {
										_state=FAILED;
									}
									if(_inputArray[_position+2]!='o') {
										_state=FAILED;
									}
									if(_inputArray[_position+3]!='t') {
										_state=FAILED;
									}
									if(_inputArray[_position+4]!='e') {
										_state=FAILED;
									}
								}
								if(_state==SUCCESS) {
									_token.add(_position,Tokens.Syntax.syntax_66.OutputQuote);
									_position=_position+5;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
										++_position;
									}
								}
								else if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Quote");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
										_furthestPosition=_position;
									}
									_position=_position_non_class_name;
								}
								else {
								}
								if(_state==SUCCESS) {
									_token_non_class_name.add(_position_non_class_name,_token);
								}
								_token=_token_non_class_name;
								if(_state==FAILED) {
									class_names.reject(_position_non_class_name);
									class_variable_names.reject(_position_non_class_name);
									variable_names.reject(_position_non_class_name);
									_state=SUCCESS;
									_position_non_class_name=_position;
									_token_non_class_name=_token;
									_token=new Tokens.Rule.NonClassNameToken();
									if(_position+4-1 >=_inputLength) {
										_state=FAILED;
									}
									else {
										if(_inputArray[_position+0]!='C') {
											_state=FAILED;
										}
										if(_inputArray[_position+1]!='a') {
											_state=FAILED;
										}
										if(_inputArray[_position+2]!='s') {
											_state=FAILED;
										}
										if(_inputArray[_position+3]!='t') {
											_state=FAILED;
										}
									}
									if(_state==SUCCESS) {
										_token.add(_position,Tokens.Syntax.syntax_67.OutputCast);
										_position=_position+4;
										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
											++_position;
										}
									}
									else if(_state==FAILED) {
										if(_position>=_furthestPosition) {
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Cast");
											_furthestPosition=_position;
										}
									}
									if(_state==FAILED) {
										if(_position>=_furthestPosition) {
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
											_furthestPosition=_position;
										}
										_position=_position_non_class_name;
									}
									else {
									}
									if(_state==SUCCESS) {
										_token_non_class_name.add(_position_non_class_name,_token);
									}
									_token=_token_non_class_name;
									if(_state==FAILED) {
										class_names.reject(_position_non_class_name);
										class_variable_names.reject(_position_non_class_name);
										variable_names.reject(_position_non_class_name);
										_state=SUCCESS;
										_position_non_class_name=_position;
										_token_non_class_name=_token;
										_token=new Tokens.Rule.NonClassNameToken();
										if(_position+6-1 >=_inputLength) {
											_state=FAILED;
										}
										else {
											if(_inputArray[_position+0]!='S') {
												_state=FAILED;
											}
											if(_inputArray[_position+1]!='t') {
												_state=FAILED;
											}
											if(_inputArray[_position+2]!='r') {
												_state=FAILED;
											}
											if(_inputArray[_position+3]!='i') {
												_state=FAILED;
											}
											if(_inputArray[_position+4]!='n') {
												_state=FAILED;
											}
											if(_inputArray[_position+5]!='g') {
												_state=FAILED;
											}
										}
										if(_state==SUCCESS) {
											_token.add(_position,Tokens.Syntax.syntax_68.OutputString);
											_position=_position+6;
											while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
												++_position;
											}
										}
										else if(_state==FAILED) {
											if(_position>=_furthestPosition) {
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain String");
												_furthestPosition=_position;
											}
										}
										if(_state==FAILED) {
											if(_position>=_furthestPosition) {
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
												_furthestPosition=_position;
											}
											_position=_position_non_class_name;
										}
										else {
										}
										if(_state==SUCCESS) {
											_token_non_class_name.add(_position_non_class_name,_token);
										}
										_token=_token_non_class_name;
										if(_state==FAILED) {
											class_names.reject(_position_non_class_name);
											class_variable_names.reject(_position_non_class_name);
											variable_names.reject(_position_non_class_name);
											_state=SUCCESS;
											_position_non_class_name=_position;
											_token_non_class_name=_token;
											_token=new Tokens.Rule.NonClassNameToken();
											if(_position+9-1 >=_inputLength) {
												_state=FAILED;
											}
											else {
												if(_inputArray[_position+0]!='S') {
													_state=FAILED;
												}
												if(_inputArray[_position+1]!='t') {
													_state=FAILED;
												}
												if(_inputArray[_position+2]!='a') {
													_state=FAILED;
												}
												if(_inputArray[_position+3]!='t') {
													_state=FAILED;
												}
												if(_inputArray[_position+4]!='e') {
													_state=FAILED;
												}
												if(_inputArray[_position+5]!='m') {
													_state=FAILED;
												}
												if(_inputArray[_position+6]!='e') {
													_state=FAILED;
												}
												if(_inputArray[_position+7]!='n') {
													_state=FAILED;
												}
												if(_inputArray[_position+8]!='t') {
													_state=FAILED;
												}
											}
											if(_state==SUCCESS) {
												_token.add(_position,Tokens.Syntax.syntax_69.OutputStatement);
												_position=_position+9;
												while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
													++_position;
												}
											}
											else if(_state==FAILED) {
												if(_position>=_furthestPosition) {
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Statement");
													_furthestPosition=_position;
												}
											}
											if(_state==FAILED) {
												if(_position>=_furthestPosition) {
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
													_furthestPosition=_position;
												}
												_position=_position_non_class_name;
											}
											else {
											}
											if(_state==SUCCESS) {
												_token_non_class_name.add(_position_non_class_name,_token);
											}
											_token=_token_non_class_name;
											if(_state==FAILED) {
												class_names.reject(_position_non_class_name);
												class_variable_names.reject(_position_non_class_name);
												variable_names.reject(_position_non_class_name);
												_state=SUCCESS;
												_position_non_class_name=_position;
												_token_non_class_name=_token;
												_token=new Tokens.Rule.NonClassNameToken();
												parse__anonymous_81();
												if(_state==FAILED) {
													if(_position>=_furthestPosition) {
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
														_furthestPosition=_position;
													}
													_position=_position_non_class_name;
												}
												else {
												}
												if(_state==SUCCESS) {
													_token_non_class_name.add(_position_non_class_name,_token);
												}
												_token=_token_non_class_name;
												if(_state==FAILED) {
													class_names.reject(_position_non_class_name);
													class_variable_names.reject(_position_non_class_name);
													variable_names.reject(_position_non_class_name);
													_state=SUCCESS;
													_position_non_class_name=_position;
													_token_non_class_name=_token;
													_token=new Tokens.Rule.NonClassNameToken();
													parse__anonymous_82();
													if(_state==FAILED) {
														if(_position>=_furthestPosition) {
															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
															_furthestPosition=_position;
														}
														_position=_position_non_class_name;
													}
													else {
													}
													if(_state==SUCCESS) {
														_token_non_class_name.add(_position_non_class_name,_token);
													}
													_token=_token_non_class_name;
													if(_state==FAILED) {
														class_names.reject(_position_non_class_name);
														class_variable_names.reject(_position_non_class_name);
														variable_names.reject(_position_non_class_name);
														_state=SUCCESS;
														_position_non_class_name=_position;
														_token_non_class_name=_token;
														_token=new Tokens.Rule.NonClassNameToken();
														parse__anonymous_83();
														if(_state==FAILED) {
															if(_position>=_furthestPosition) {
																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																_furthestPosition=_position;
															}
															_position=_position_non_class_name;
														}
														else {
														}
														if(_state==SUCCESS) {
															_token_non_class_name.add(_position_non_class_name,_token);
														}
														_token=_token_non_class_name;
														if(_state==FAILED) {
															class_names.reject(_position_non_class_name);
															class_variable_names.reject(_position_non_class_name);
															variable_names.reject(_position_non_class_name);
															_state=SUCCESS;
															_position_non_class_name=_position;
															_token_non_class_name=_token;
															_token=new Tokens.Rule.NonClassNameToken();
															if(_position+2-1 >=_inputLength) {
																_state=FAILED;
															}
															else {
																if(_inputArray[_position+0]!='I') {
																	_state=FAILED;
																}
																if(_inputArray[_position+1]!='d') {
																	_state=FAILED;
																}
															}
															if(_state==SUCCESS) {
																_token.add(_position,Tokens.Syntax.syntax_75.Id);
																_position=_position+2;
																while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																	++_position;
																}
															}
															else if(_state==FAILED) {
																if(_position>=_furthestPosition) {
																	_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Id");
																	_furthestPosition=_position;
																}
															}
															if(_state==FAILED) {
																if(_position>=_furthestPosition) {
																	_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																	_furthestPosition=_position;
																}
																_position=_position_non_class_name;
															}
															else {
															}
															if(_state==SUCCESS) {
																_token_non_class_name.add(_position_non_class_name,_token);
															}
															_token=_token_non_class_name;
															if(_state==FAILED) {
																class_names.reject(_position_non_class_name);
																class_variable_names.reject(_position_non_class_name);
																variable_names.reject(_position_non_class_name);
																_state=SUCCESS;
																_position_non_class_name=_position;
																_token_non_class_name=_token;
																_token=new Tokens.Rule.NonClassNameToken();
																if(_position+6-1 >=_inputLength) {
																	_state=FAILED;
																}
																else {
																	if(_inputArray[_position+0]!='P') {
																		_state=FAILED;
																	}
																	if(_inputArray[_position+1]!='a') {
																		_state=FAILED;
																	}
																	if(_inputArray[_position+2]!='r') {
																		_state=FAILED;
																	}
																	if(_inputArray[_position+3]!='s') {
																		_state=FAILED;
																	}
																	if(_inputArray[_position+4]!='e') {
																		_state=FAILED;
																	}
																	if(_inputArray[_position+5]!='r') {
																		_state=FAILED;
																	}
																}
																if(_state==SUCCESS) {
																	_token.add(_position,Tokens.Syntax.syntax_76.Parser);
																	_position=_position+6;
																	while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																		++_position;
																	}
																}
																else if(_state==FAILED) {
																	if(_position>=_furthestPosition) {
																		_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Parser");
																		_furthestPosition=_position;
																	}
																}
																if(_state==FAILED) {
																	if(_position>=_furthestPosition) {
																		_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																		_furthestPosition=_position;
																	}
																	_position=_position_non_class_name;
																}
																else {
																}
																if(_state==SUCCESS) {
																	_token_non_class_name.add(_position_non_class_name,_token);
																}
																_token=_token_non_class_name;
																if(_state==FAILED) {
																	class_names.reject(_position_non_class_name);
																	class_variable_names.reject(_position_non_class_name);
																	variable_names.reject(_position_non_class_name);
																	_state=SUCCESS;
																	_position_non_class_name=_position;
																	_token_non_class_name=_token;
																	_token=new Tokens.Rule.NonClassNameToken();
																	parse__anonymous_84();
																	if(_state==FAILED) {
																		if(_position>=_furthestPosition) {
																			_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																			_furthestPosition=_position;
																		}
																		_position=_position_non_class_name;
																	}
																	else {
																	}
																	if(_state==SUCCESS) {
																		_token_non_class_name.add(_position_non_class_name,_token);
																	}
																	_token=_token_non_class_name;
																	if(_state==FAILED) {
																		class_names.reject(_position_non_class_name);
																		class_variable_names.reject(_position_non_class_name);
																		variable_names.reject(_position_non_class_name);
																		_state=SUCCESS;
																		_position_non_class_name=_position;
																		_token_non_class_name=_token;
																		_token=new Tokens.Rule.NonClassNameToken();
																		if(_position+10-1 >=_inputLength) {
																			_state=FAILED;
																		}
																		else {
																			if(_inputArray[_position+0]!='N') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+1]!='e') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+2]!='w') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+3]!=' ') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+4]!='O') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+5]!='b') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+6]!='j') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+7]!='e') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+8]!='c') {
																				_state=FAILED;
																			}
																			if(_inputArray[_position+9]!='t') {
																				_state=FAILED;
																			}
																		}
																		if(_state==SUCCESS) {
																			_token.add(_position,Tokens.Syntax.syntax_79.OutputNewObject);
																			_position=_position+10;
																			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																				++_position;
																			}
																		}
																		else if(_state==FAILED) {
																			if(_position>=_furthestPosition) {
																				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain New Object");
																				_furthestPosition=_position;
																			}
																		}
																		if(_state==FAILED) {
																			if(_position>=_furthestPosition) {
																				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																				_furthestPosition=_position;
																			}
																			_position=_position_non_class_name;
																		}
																		else {
																		}
																		if(_state==SUCCESS) {
																			_token_non_class_name.add(_position_non_class_name,_token);
																		}
																		_token=_token_non_class_name;
																		if(_state==FAILED) {
																			class_names.reject(_position_non_class_name);
																			class_variable_names.reject(_position_non_class_name);
																			variable_names.reject(_position_non_class_name);
																			_state=SUCCESS;
																			_position_non_class_name=_position;
																			_token_non_class_name=_token;
																			_token=new Tokens.Rule.NonClassNameToken();
																			if(_position+6-1 >=_inputLength) {
																				_state=FAILED;
																			}
																			else {
																				if(_inputArray[_position+0]!='R') {
																					_state=FAILED;
																				}
																				if(_inputArray[_position+1]!='e') {
																					_state=FAILED;
																				}
																				if(_inputArray[_position+2]!='s') {
																					_state=FAILED;
																				}
																				if(_inputArray[_position+3]!='u') {
																					_state=FAILED;
																				}
																				if(_inputArray[_position+4]!='l') {
																					_state=FAILED;
																				}
																				if(_inputArray[_position+5]!='t') {
																					_state=FAILED;
																				}
																			}
																			if(_state==SUCCESS) {
																				_token.add(_position,Tokens.Syntax.syntax_80.Result);
																				_position=_position+6;
																				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																					++_position;
																				}
																			}
																			else if(_state==FAILED) {
																				if(_position>=_furthestPosition) {
																					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Result");
																					_furthestPosition=_position;
																				}
																			}
																			if(_state==FAILED) {
																				if(_position>=_furthestPosition) {
																					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																					_furthestPosition=_position;
																				}
																				_position=_position_non_class_name;
																			}
																			else {
																			}
																			if(_state==SUCCESS) {
																				_token_non_class_name.add(_position_non_class_name,_token);
																			}
																			_token=_token_non_class_name;
																			if(_state==FAILED) {
																				class_names.reject(_position_non_class_name);
																				class_variable_names.reject(_position_non_class_name);
																				variable_names.reject(_position_non_class_name);
																				_state=SUCCESS;
																				_position_non_class_name=_position;
																				_token_non_class_name=_token;
																				_token=new Tokens.Rule.NonClassNameToken();
																				if(_position+4-1 >=_inputLength) {
																					_state=FAILED;
																				}
																				else {
																					if(_inputArray[_position+0]!='P') {
																						_state=FAILED;
																					}
																					if(_inputArray[_position+1]!='a') {
																						_state=FAILED;
																					}
																					if(_inputArray[_position+2]!='s') {
																						_state=FAILED;
																					}
																					if(_inputArray[_position+3]!='s') {
																						_state=FAILED;
																					}
																				}
																				if(_state==SUCCESS) {
																					_token.add(_position,Tokens.Syntax.syntax_81.Pass);
																					_position=_position+4;
																					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																						++_position;
																					}
																				}
																				else if(_state==FAILED) {
																					if(_position>=_furthestPosition) {
																						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Pass");
																						_furthestPosition=_position;
																					}
																				}
																				if(_state==FAILED) {
																					if(_position>=_furthestPosition) {
																						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																						_furthestPosition=_position;
																					}
																					_position=_position_non_class_name;
																				}
																				else {
																				}
																				if(_state==SUCCESS) {
																					_token_non_class_name.add(_position_non_class_name,_token);
																				}
																				_token=_token_non_class_name;
																				if(_state==FAILED) {
																					class_names.reject(_position_non_class_name);
																					class_variable_names.reject(_position_non_class_name);
																					variable_names.reject(_position_non_class_name);
																					_state=SUCCESS;
																					_position_non_class_name=_position;
																					_token_non_class_name=_token;
																					_token=new Tokens.Rule.NonClassNameToken();
																					if(_position+4-1 >=_inputLength) {
																						_state=FAILED;
																					}
																					else {
																						if(_inputArray[_position+0]!='F') {
																							_state=FAILED;
																						}
																						if(_inputArray[_position+1]!='a') {
																							_state=FAILED;
																						}
																						if(_inputArray[_position+2]!='i') {
																							_state=FAILED;
																						}
																						if(_inputArray[_position+3]!='l') {
																							_state=FAILED;
																						}
																					}
																					if(_state==SUCCESS) {
																						_token.add(_position,Tokens.Syntax.syntax_82.Fail);
																						_position=_position+4;
																						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																							++_position;
																						}
																					}
																					else if(_state==FAILED) {
																						if(_position>=_furthestPosition) {
																							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Fail");
																							_furthestPosition=_position;
																						}
																					}
																					if(_state==FAILED) {
																						if(_position>=_furthestPosition) {
																							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																							_furthestPosition=_position;
																						}
																						_position=_position_non_class_name;
																					}
																					else {
																					}
																					if(_state==SUCCESS) {
																						_token_non_class_name.add(_position_non_class_name,_token);
																					}
																					_token=_token_non_class_name;
																					if(_state==FAILED) {
																						class_names.reject(_position_non_class_name);
																						class_variable_names.reject(_position_non_class_name);
																						variable_names.reject(_position_non_class_name);
																						_state=SUCCESS;
																						_position_non_class_name=_position;
																						_token_non_class_name=_token;
																						_token=new Tokens.Rule.NonClassNameToken();
																						if(_position+4-1 >=_inputLength) {
																							_state=FAILED;
																						}
																						else {
																							if(_inputArray[_position+0]!='B') {
																								_state=FAILED;
																							}
																							if(_inputArray[_position+1]!='o') {
																								_state=FAILED;
																							}
																							if(_inputArray[_position+2]!='d') {
																								_state=FAILED;
																							}
																							if(_inputArray[_position+3]!='y') {
																								_state=FAILED;
																							}
																						}
																						if(_state==SUCCESS) {
																							_token.add(_position,Tokens.Syntax.syntax_83.OutputBody);
																							_position=_position+4;
																							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																								++_position;
																							}
																						}
																						else if(_state==FAILED) {
																							if(_position>=_furthestPosition) {
																								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Body");
																								_furthestPosition=_position;
																							}
																						}
																						if(_state==FAILED) {
																							if(_position>=_furthestPosition) {
																								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																								_furthestPosition=_position;
																							}
																							_position=_position_non_class_name;
																						}
																						else {
																						}
																						if(_state==SUCCESS) {
																							_token_non_class_name.add(_position_non_class_name,_token);
																						}
																						_token=_token_non_class_name;
																						if(_state==FAILED) {
																							class_names.reject(_position_non_class_name);
																							class_variable_names.reject(_position_non_class_name);
																							variable_names.reject(_position_non_class_name);
																							_state=SUCCESS;
																							_position_non_class_name=_position;
																							_token_non_class_name=_token;
																							_token=new Tokens.Rule.NonClassNameToken();
																							if(_position+4-1 >=_inputLength) {
																								_state=FAILED;
																							}
																							else {
																								if(_inputArray[_position+0]!='T') {
																									_state=FAILED;
																								}
																								if(_inputArray[_position+1]!='y') {
																									_state=FAILED;
																								}
																								if(_inputArray[_position+2]!='p') {
																									_state=FAILED;
																								}
																								if(_inputArray[_position+3]!='e') {
																									_state=FAILED;
																								}
																							}
																							if(_state==SUCCESS) {
																								_token.add(_position,Tokens.Syntax.syntax_84.OutputType);
																								_position=_position+4;
																								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																									++_position;
																								}
																							}
																							else if(_state==FAILED) {
																								if(_position>=_furthestPosition) {
																									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Type");
																									_furthestPosition=_position;
																								}
																							}
																							if(_state==FAILED) {
																								if(_position>=_furthestPosition) {
																									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																									_furthestPosition=_position;
																								}
																								_position=_position_non_class_name;
																							}
																							else {
																							}
																							if(_state==SUCCESS) {
																								_token_non_class_name.add(_position_non_class_name,_token);
																							}
																							_token=_token_non_class_name;
																							if(_state==FAILED) {
																								class_names.reject(_position_non_class_name);
																								class_variable_names.reject(_position_non_class_name);
																								variable_names.reject(_position_non_class_name);
																								_state=SUCCESS;
																								_position_non_class_name=_position;
																								_token_non_class_name=_token;
																								_token=new Tokens.Rule.NonClassNameToken();
																								if(_position+5-1 >=_inputLength) {
																									_state=FAILED;
																								}
																								else {
																									if(_inputArray[_position+0]!='E') {
																										_state=FAILED;
																									}
																									if(_inputArray[_position+1]!='x') {
																										_state=FAILED;
																									}
																									if(_inputArray[_position+2]!='a') {
																										_state=FAILED;
																									}
																									if(_inputArray[_position+3]!='c') {
																										_state=FAILED;
																									}
																									if(_inputArray[_position+4]!='t') {
																										_state=FAILED;
																									}
																								}
																								if(_state==SUCCESS) {
																									_token.add(_position,Tokens.Syntax.syntax_85.OutputExact);
																									_position=_position+5;
																									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																										++_position;
																									}
																								}
																								else if(_state==FAILED) {
																									if(_position>=_furthestPosition) {
																										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Exact");
																										_furthestPosition=_position;
																									}
																								}
																								if(_state==FAILED) {
																									if(_position>=_furthestPosition) {
																										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																										_furthestPosition=_position;
																									}
																									_position=_position_non_class_name;
																								}
																								else {
																								}
																								if(_state==SUCCESS) {
																									_token_non_class_name.add(_position_non_class_name,_token);
																								}
																								_token=_token_non_class_name;
																								if(_state==FAILED) {
																									class_names.reject(_position_non_class_name);
																									class_variable_names.reject(_position_non_class_name);
																									variable_names.reject(_position_non_class_name);
																									_state=SUCCESS;
																									_position_non_class_name=_position;
																									_token_non_class_name=_token;
																									_token=new Tokens.Rule.NonClassNameToken();
																									if(_position+8-1 >=_inputLength) {
																										_state=FAILED;
																									}
																									else {
																										if(_inputArray[_position+0]!='C') {
																											_state=FAILED;
																										}
																										if(_inputArray[_position+1]!='a') {
																											_state=FAILED;
																										}
																										if(_inputArray[_position+2]!='l') {
																											_state=FAILED;
																										}
																										if(_inputArray[_position+3]!='l') {
																											_state=FAILED;
																										}
																										if(_inputArray[_position+4]!='a') {
																											_state=FAILED;
																										}
																										if(_inputArray[_position+5]!='b') {
																											_state=FAILED;
																										}
																										if(_inputArray[_position+6]!='l') {
																											_state=FAILED;
																										}
																										if(_inputArray[_position+7]!='e') {
																											_state=FAILED;
																										}
																									}
																									if(_state==SUCCESS) {
																										_token.add(_position,Tokens.Syntax.syntax_86.CallableOutput);
																										_position=_position+8;
																										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																											++_position;
																										}
																									}
																									else if(_state==FAILED) {
																										if(_position>=_furthestPosition) {
																											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Callable");
																											_furthestPosition=_position;
																										}
																									}
																									if(_state==FAILED) {
																										if(_position>=_furthestPosition) {
																											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																											_furthestPosition=_position;
																										}
																										_position=_position_non_class_name;
																									}
																									else {
																									}
																									if(_state==SUCCESS) {
																										_token_non_class_name.add(_position_non_class_name,_token);
																									}
																									_token=_token_non_class_name;
																									if(_state==FAILED) {
																										class_names.reject(_position_non_class_name);
																										class_variable_names.reject(_position_non_class_name);
																										variable_names.reject(_position_non_class_name);
																										_state=SUCCESS;
																										_position_non_class_name=_position;
																										_token_non_class_name=_token;
																										_token=new Tokens.Rule.NonClassNameToken();
																										if(_position+4-1 >=_inputLength) {
																											_state=FAILED;
																										}
																										else {
																											if(_inputArray[_position+0]!='C') {
																												_state=FAILED;
																											}
																											if(_inputArray[_position+1]!='a') {
																												_state=FAILED;
																											}
																											if(_inputArray[_position+2]!='l') {
																												_state=FAILED;
																											}
																											if(_inputArray[_position+3]!='l') {
																												_state=FAILED;
																											}
																										}
																										if(_state==SUCCESS) {
																											_token.add(_position,Tokens.Syntax.syntax_87.OutputCall);
																											_position=_position+4;
																											while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																												++_position;
																											}
																										}
																										else if(_state==FAILED) {
																											if(_position>=_furthestPosition) {
																												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Call");
																												_furthestPosition=_position;
																											}
																										}
																										if(_state==FAILED) {
																											if(_position>=_furthestPosition) {
																												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																												_furthestPosition=_position;
																											}
																											_position=_position_non_class_name;
																										}
																										else {
																										}
																										if(_state==SUCCESS) {
																											_token_non_class_name.add(_position_non_class_name,_token);
																										}
																										_token=_token_non_class_name;
																										if(_state==FAILED) {
																											class_names.reject(_position_non_class_name);
																											class_variable_names.reject(_position_non_class_name);
																											variable_names.reject(_position_non_class_name);
																											_state=SUCCESS;
																											_position_non_class_name=_position;
																											_token_non_class_name=_token;
																											_token=new Tokens.Rule.NonClassNameToken();
																											if(_position+6-1 >=_inputLength) {
																												_state=FAILED;
																											}
																											else {
																												if(_inputArray[_position+0]!='H') {
																													_state=FAILED;
																												}
																												if(_inputArray[_position+1]!='e') {
																													_state=FAILED;
																												}
																												if(_inputArray[_position+2]!='l') {
																													_state=FAILED;
																												}
																												if(_inputArray[_position+3]!='p') {
																													_state=FAILED;
																												}
																												if(_inputArray[_position+4]!='e') {
																													_state=FAILED;
																												}
																												if(_inputArray[_position+5]!='r') {
																													_state=FAILED;
																												}
																											}
																											if(_state==SUCCESS) {
																												_token.add(_position,Tokens.Syntax.syntax_88.OutputHelper);
																												_position=_position+6;
																												while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																													++_position;
																												}
																											}
																											else if(_state==FAILED) {
																												if(_position>=_furthestPosition) {
																													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Helper");
																													_furthestPosition=_position;
																												}
																											}
																											if(_state==FAILED) {
																												if(_position>=_furthestPosition) {
																													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																													_furthestPosition=_position;
																												}
																												_position=_position_non_class_name;
																											}
																											else {
																											}
																											if(_state==SUCCESS) {
																												_token_non_class_name.add(_position_non_class_name,_token);
																											}
																											_token=_token_non_class_name;
																											if(_state==FAILED) {
																												class_names.reject(_position_non_class_name);
																												class_variable_names.reject(_position_non_class_name);
																												variable_names.reject(_position_non_class_name);
																												_state=SUCCESS;
																												_position_non_class_name=_position;
																												_token_non_class_name=_token;
																												_token=new Tokens.Rule.NonClassNameToken();
																												if(_position+8-1 >=_inputLength) {
																													_state=FAILED;
																												}
																												else {
																													if(_inputArray[_position+0]!='O') {
																														_state=FAILED;
																													}
																													if(_inputArray[_position+1]!='p') {
																														_state=FAILED;
																													}
																													if(_inputArray[_position+2]!='e') {
																														_state=FAILED;
																													}
																													if(_inputArray[_position+3]!='r') {
																														_state=FAILED;
																													}
																													if(_inputArray[_position+4]!='a') {
																														_state=FAILED;
																													}
																													if(_inputArray[_position+5]!='t') {
																														_state=FAILED;
																													}
																													if(_inputArray[_position+6]!='o') {
																														_state=FAILED;
																													}
																													if(_inputArray[_position+7]!='r') {
																														_state=FAILED;
																													}
																												}
																												if(_state==SUCCESS) {
																													_token.add(_position,Tokens.Syntax.syntax_89.OutputOperator);
																													_position=_position+8;
																													while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																														++_position;
																													}
																												}
																												else if(_state==FAILED) {
																													if(_position>=_furthestPosition) {
																														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Operator");
																														_furthestPosition=_position;
																													}
																												}
																												if(_state==FAILED) {
																													if(_position>=_furthestPosition) {
																														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																														_furthestPosition=_position;
																													}
																													_position=_position_non_class_name;
																												}
																												else {
																												}
																												if(_state==SUCCESS) {
																													_token_non_class_name.add(_position_non_class_name,_token);
																												}
																												_token=_token_non_class_name;
																												if(_state==FAILED) {
																													class_names.reject(_position_non_class_name);
																													class_variable_names.reject(_position_non_class_name);
																													variable_names.reject(_position_non_class_name);
																													_state=SUCCESS;
																													_position_non_class_name=_position;
																													_token_non_class_name=_token;
																													_token=new Tokens.Rule.NonClassNameToken();
																													parse__anonymous_85();
																													if(_state==FAILED) {
																														if(_position>=_furthestPosition) {
																															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																															_furthestPosition=_position;
																														}
																														_position=_position_non_class_name;
																													}
																													else {
																													}
																													if(_state==SUCCESS) {
																														_token_non_class_name.add(_position_non_class_name,_token);
																													}
																													_token=_token_non_class_name;
																													if(_state==FAILED) {
																														class_names.reject(_position_non_class_name);
																														class_variable_names.reject(_position_non_class_name);
																														variable_names.reject(_position_non_class_name);
																														_state=SUCCESS;
																														_position_non_class_name=_position;
																														_token_non_class_name=_token;
																														_token=new Tokens.Rule.NonClassNameToken();
																														if(_position+6-1 >=_inputLength) {
																															_state=FAILED;
																														}
																														else {
																															if(_inputArray[_position+0]!='L') {
																																_state=FAILED;
																															}
																															if(_inputArray[_position+1]!='a') {
																																_state=FAILED;
																															}
																															if(_inputArray[_position+2]!='m') {
																																_state=FAILED;
																															}
																															if(_inputArray[_position+3]!='b') {
																																_state=FAILED;
																															}
																															if(_inputArray[_position+4]!='d') {
																																_state=FAILED;
																															}
																															if(_inputArray[_position+5]!='a') {
																																_state=FAILED;
																															}
																														}
																														if(_state==SUCCESS) {
																															_token.add(_position,Tokens.Syntax.syntax_93.OutputLambda);
																															_position=_position+6;
																															while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																++_position;
																															}
																														}
																														else if(_state==FAILED) {
																															if(_position>=_furthestPosition) {
																																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Lambda");
																																_furthestPosition=_position;
																															}
																														}
																														if(_state==FAILED) {
																															if(_position>=_furthestPosition) {
																																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																_furthestPosition=_position;
																															}
																															_position=_position_non_class_name;
																														}
																														else {
																														}
																														if(_state==SUCCESS) {
																															_token_non_class_name.add(_position_non_class_name,_token);
																														}
																														_token=_token_non_class_name;
																														if(_state==FAILED) {
																															class_names.reject(_position_non_class_name);
																															class_variable_names.reject(_position_non_class_name);
																															variable_names.reject(_position_non_class_name);
																															_state=SUCCESS;
																															_position_non_class_name=_position;
																															_token_non_class_name=_token;
																															_token=new Tokens.Rule.NonClassNameToken();
																															if(_position+6-1 >=_inputLength) {
																																_state=FAILED;
																															}
																															else {
																																if(_inputArray[_position+0]!='B') {
																																	_state=FAILED;
																																}
																																if(_inputArray[_position+1]!='r') {
																																	_state=FAILED;
																																}
																																if(_inputArray[_position+2]!='a') {
																																	_state=FAILED;
																																}
																																if(_inputArray[_position+3]!='c') {
																																	_state=FAILED;
																																}
																																if(_inputArray[_position+4]!='e') {
																																	_state=FAILED;
																																}
																																if(_inputArray[_position+5]!='d') {
																																	_state=FAILED;
																																}
																															}
																															if(_state==SUCCESS) {
																																_token.add(_position,Tokens.Syntax.syntax_94.OutputBraced);
																																_position=_position+6;
																																while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																	++_position;
																																}
																															}
																															else if(_state==FAILED) {
																																if(_position>=_furthestPosition) {
																																	_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Braced");
																																	_furthestPosition=_position;
																																}
																															}
																															if(_state==FAILED) {
																																if(_position>=_furthestPosition) {
																																	_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																	_furthestPosition=_position;
																																}
																																_position=_position_non_class_name;
																															}
																															else {
																															}
																															if(_state==SUCCESS) {
																																_token_non_class_name.add(_position_non_class_name,_token);
																															}
																															_token=_token_non_class_name;
																															if(_state==FAILED) {
																																class_names.reject(_position_non_class_name);
																																class_variable_names.reject(_position_non_class_name);
																																variable_names.reject(_position_non_class_name);
																																_state=SUCCESS;
																																_position_non_class_name=_position;
																																_token_non_class_name=_token;
																																_token=new Tokens.Rule.NonClassNameToken();
																																if(_position+6-1 >=_inputLength) {
																																	_state=FAILED;
																																}
																																else {
																																	if(_inputArray[_position+0]!='O') {
																																		_state=FAILED;
																																	}
																																	if(_inputArray[_position+1]!='u') {
																																		_state=FAILED;
																																	}
																																	if(_inputArray[_position+2]!='t') {
																																		_state=FAILED;
																																	}
																																	if(_inputArray[_position+3]!='p') {
																																		_state=FAILED;
																																	}
																																	if(_inputArray[_position+4]!='u') {
																																		_state=FAILED;
																																	}
																																	if(_inputArray[_position+5]!='t') {
																																		_state=FAILED;
																																	}
																																}
																																if(_state==SUCCESS) {
																																	_token.add(_position,Tokens.Syntax.syntax_95.Output);
																																	_position=_position+6;
																																	while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																		++_position;
																																	}
																																}
																																else if(_state==FAILED) {
																																	if(_position>=_furthestPosition) {
																																		_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Output");
																																		_furthestPosition=_position;
																																	}
																																}
																																if(_state==FAILED) {
																																	if(_position>=_furthestPosition) {
																																		_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																		_furthestPosition=_position;
																																	}
																																	_position=_position_non_class_name;
																																}
																																else {
																																}
																																if(_state==SUCCESS) {
																																	_token_non_class_name.add(_position_non_class_name,_token);
																																}
																																_token=_token_non_class_name;
																																if(_state==FAILED) {
																																	class_names.reject(_position_non_class_name);
																																	class_variable_names.reject(_position_non_class_name);
																																	variable_names.reject(_position_non_class_name);
																																	_state=SUCCESS;
																																	_position_non_class_name=_position;
																																	_token_non_class_name=_token;
																																	_token=new Tokens.Rule.NonClassNameToken();
																																	if(_position+8-1 >=_inputLength) {
																																		_state=FAILED;
																																	}
																																	else {
																																		if(_inputArray[_position+0]!='L') {
																																			_state=FAILED;
																																		}
																																		if(_inputArray[_position+1]!='i') {
																																			_state=FAILED;
																																		}
																																		if(_inputArray[_position+2]!='n') {
																																			_state=FAILED;
																																		}
																																		if(_inputArray[_position+3]!='e') {
																																			_state=FAILED;
																																		}
																																		if(_inputArray[_position+4]!='a') {
																																			_state=FAILED;
																																		}
																																		if(_inputArray[_position+5]!='b') {
																																			_state=FAILED;
																																		}
																																		if(_inputArray[_position+6]!='l') {
																																			_state=FAILED;
																																		}
																																		if(_inputArray[_position+7]!='e') {
																																			_state=FAILED;
																																		}
																																	}
																																	if(_state==SUCCESS) {
																																		_token.add(_position,Tokens.Syntax.syntax_96.LineableOutput);
																																		_position=_position+8;
																																		while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																			++_position;
																																		}
																																	}
																																	else if(_state==FAILED) {
																																		if(_position>=_furthestPosition) {
																																			_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Lineable");
																																			_furthestPosition=_position;
																																		}
																																	}
																																	if(_state==FAILED) {
																																		if(_position>=_furthestPosition) {
																																			_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																			_furthestPosition=_position;
																																		}
																																		_position=_position_non_class_name;
																																	}
																																	else {
																																	}
																																	if(_state==SUCCESS) {
																																		_token_non_class_name.add(_position_non_class_name,_token);
																																	}
																																	_token=_token_non_class_name;
																																	if(_state==FAILED) {
																																		class_names.reject(_position_non_class_name);
																																		class_variable_names.reject(_position_non_class_name);
																																		variable_names.reject(_position_non_class_name);
																																		_state=SUCCESS;
																																		_position_non_class_name=_position;
																																		_token_non_class_name=_token;
																																		_token=new Tokens.Rule.NonClassNameToken();
																																		if(_position+3-1 >=_inputLength) {
																																			_state=FAILED;
																																		}
																																		else {
																																			if(_inputArray[_position+0]!='N') {
																																				_state=FAILED;
																																			}
																																			if(_inputArray[_position+1]!='e') {
																																				_state=FAILED;
																																			}
																																			if(_inputArray[_position+2]!='w') {
																																				_state=FAILED;
																																			}
																																		}
																																		if(_state==SUCCESS) {
																																			_token.add(_position,Tokens.Syntax.syntax_97.OutputNewObject);
																																			_position=_position+3;
																																			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																				++_position;
																																			}
																																		}
																																		else if(_state==FAILED) {
																																			if(_position>=_furthestPosition) {
																																				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain New");
																																				_furthestPosition=_position;
																																			}
																																		}
																																		if(_state==FAILED) {
																																			if(_position>=_furthestPosition) {
																																				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																				_furthestPosition=_position;
																																			}
																																			_position=_position_non_class_name;
																																		}
																																		else {
																																		}
																																		if(_state==SUCCESS) {
																																			_token_non_class_name.add(_position_non_class_name,_token);
																																		}
																																		_token=_token_non_class_name;
																																		if(_state==FAILED) {
																																			class_names.reject(_position_non_class_name);
																																			class_variable_names.reject(_position_non_class_name);
																																			variable_names.reject(_position_non_class_name);
																																			_state=SUCCESS;
																																			_position_non_class_name=_position;
																																			_token_non_class_name=_token;
																																			_token=new Tokens.Rule.NonClassNameToken();
																																			if(_position+1-1 >=_inputLength) {
																																				_state=FAILED;
																																			}
																																			else {
																																				if(_inputArray[_position+0]!='A') {
																																					_state=FAILED;
																																				}
																																			}
																																			if(_state==SUCCESS) {
																																				_token.add(_position,Tokens.Syntax.syntax_98.OutputArguments);
																																				_position=_position+1;
																																				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																					++_position;
																																				}
																																			}
																																			else if(_state==FAILED) {
																																				if(_position>=_furthestPosition) {
																																					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain A");
																																					_furthestPosition=_position;
																																				}
																																			}
																																			if(_state==FAILED) {
																																				if(_position>=_furthestPosition) {
																																					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																					_furthestPosition=_position;
																																				}
																																				_position=_position_non_class_name;
																																			}
																																			else {
																																			}
																																			if(_state==SUCCESS) {
																																				_token_non_class_name.add(_position_non_class_name,_token);
																																			}
																																			_token=_token_non_class_name;
																																			if(_state==FAILED) {
																																				class_names.reject(_position_non_class_name);
																																				class_variable_names.reject(_position_non_class_name);
																																				variable_names.reject(_position_non_class_name);
																																				_state=SUCCESS;
																																				_position_non_class_name=_position;
																																				_token_non_class_name=_token;
																																				_token=new Tokens.Rule.NonClassNameToken();
																																				if(_position+1-1 >=_inputLength) {
																																					_state=FAILED;
																																				}
																																				else {
																																					if(_inputArray[_position+0]!='B') {
																																						_state=FAILED;
																																					}
																																				}
																																				if(_state==SUCCESS) {
																																					_token.add(_position,Tokens.Syntax.syntax_99.OutputBody);
																																					_position=_position+1;
																																					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																						++_position;
																																					}
																																				}
																																				else if(_state==FAILED) {
																																					if(_position>=_furthestPosition) {
																																						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain B");
																																						_furthestPosition=_position;
																																					}
																																				}
																																				if(_state==FAILED) {
																																					if(_position>=_furthestPosition) {
																																						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																						_furthestPosition=_position;
																																					}
																																					_position=_position_non_class_name;
																																				}
																																				else {
																																				}
																																				if(_state==SUCCESS) {
																																					_token_non_class_name.add(_position_non_class_name,_token);
																																				}
																																				_token=_token_non_class_name;
																																				if(_state==FAILED) {
																																					class_names.reject(_position_non_class_name);
																																					class_variable_names.reject(_position_non_class_name);
																																					variable_names.reject(_position_non_class_name);
																																					_state=SUCCESS;
																																					_position_non_class_name=_position;
																																					_token_non_class_name=_token;
																																					_token=new Tokens.Rule.NonClassNameToken();
																																					if(_position+1-1 >=_inputLength) {
																																						_state=FAILED;
																																					}
																																					else {
																																						if(_inputArray[_position+0]!='C') {
																																							_state=FAILED;
																																						}
																																					}
																																					if(_state==SUCCESS) {
																																						_token.add(_position,Tokens.Syntax.syntax_100.OutputClass);
																																						_position=_position+1;
																																						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																							++_position;
																																						}
																																					}
																																					else if(_state==FAILED) {
																																						if(_position>=_furthestPosition) {
																																							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain C");
																																							_furthestPosition=_position;
																																						}
																																					}
																																					if(_state==FAILED) {
																																						if(_position>=_furthestPosition) {
																																							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																							_furthestPosition=_position;
																																						}
																																						_position=_position_non_class_name;
																																					}
																																					else {
																																					}
																																					if(_state==SUCCESS) {
																																						_token_non_class_name.add(_position_non_class_name,_token);
																																					}
																																					_token=_token_non_class_name;
																																					if(_state==FAILED) {
																																						class_names.reject(_position_non_class_name);
																																						class_variable_names.reject(_position_non_class_name);
																																						variable_names.reject(_position_non_class_name);
																																						_state=SUCCESS;
																																						_position_non_class_name=_position;
																																						_token_non_class_name=_token;
																																						_token=new Tokens.Rule.NonClassNameToken();
																																						if(_position+1-1 >=_inputLength) {
																																							_state=FAILED;
																																						}
																																						else {
																																							if(_inputArray[_position+0]!='E') {
																																								_state=FAILED;
																																							}
																																						}
																																						if(_state==SUCCESS) {
																																							_token.add(_position,Tokens.Syntax.syntax_101.OutputExact);
																																							_position=_position+1;
																																							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																								++_position;
																																							}
																																						}
																																						else if(_state==FAILED) {
																																							if(_position>=_furthestPosition) {
																																								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain E");
																																								_furthestPosition=_position;
																																							}
																																						}
																																						if(_state==FAILED) {
																																							if(_position>=_furthestPosition) {
																																								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																								_furthestPosition=_position;
																																							}
																																							_position=_position_non_class_name;
																																						}
																																						else {
																																						}
																																						if(_state==SUCCESS) {
																																							_token_non_class_name.add(_position_non_class_name,_token);
																																						}
																																						_token=_token_non_class_name;
																																						if(_state==FAILED) {
																																							class_names.reject(_position_non_class_name);
																																							class_variable_names.reject(_position_non_class_name);
																																							variable_names.reject(_position_non_class_name);
																																							_state=SUCCESS;
																																							_position_non_class_name=_position;
																																							_token_non_class_name=_token;
																																							_token=new Tokens.Rule.NonClassNameToken();
																																							if(_position+1-1 >=_inputLength) {
																																								_state=FAILED;
																																							}
																																							else {
																																								if(_inputArray[_position+0]!='H') {
																																									_state=FAILED;
																																								}
																																							}
																																							if(_state==SUCCESS) {
																																								_token.add(_position,Tokens.Syntax.syntax_102.OutputHelper);
																																								_position=_position+1;
																																								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																									++_position;
																																								}
																																							}
																																							else if(_state==FAILED) {
																																								if(_position>=_furthestPosition) {
																																									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain H");
																																									_furthestPosition=_position;
																																								}
																																							}
																																							if(_state==FAILED) {
																																								if(_position>=_furthestPosition) {
																																									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																									_furthestPosition=_position;
																																								}
																																								_position=_position_non_class_name;
																																							}
																																							else {
																																							}
																																							if(_state==SUCCESS) {
																																								_token_non_class_name.add(_position_non_class_name,_token);
																																							}
																																							_token=_token_non_class_name;
																																							if(_state==FAILED) {
																																								class_names.reject(_position_non_class_name);
																																								class_variable_names.reject(_position_non_class_name);
																																								variable_names.reject(_position_non_class_name);
																																								_state=SUCCESS;
																																								_position_non_class_name=_position;
																																								_token_non_class_name=_token;
																																								_token=new Tokens.Rule.NonClassNameToken();
																																								if(_position+1-1 >=_inputLength) {
																																									_state=FAILED;
																																								}
																																								else {
																																									if(_inputArray[_position+0]!='M') {
																																										_state=FAILED;
																																									}
																																								}
																																								if(_state==SUCCESS) {
																																									_token.add(_position,Tokens.Syntax.syntax_103.OutputMethod);
																																									_position=_position+1;
																																									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																										++_position;
																																									}
																																								}
																																								else if(_state==FAILED) {
																																									if(_position>=_furthestPosition) {
																																										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain M");
																																										_furthestPosition=_position;
																																									}
																																								}
																																								if(_state==FAILED) {
																																									if(_position>=_furthestPosition) {
																																										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																										_furthestPosition=_position;
																																									}
																																									_position=_position_non_class_name;
																																								}
																																								else {
																																								}
																																								if(_state==SUCCESS) {
																																									_token_non_class_name.add(_position_non_class_name,_token);
																																								}
																																								_token=_token_non_class_name;
																																								if(_state==FAILED) {
																																									class_names.reject(_position_non_class_name);
																																									class_variable_names.reject(_position_non_class_name);
																																									variable_names.reject(_position_non_class_name);
																																									_state=SUCCESS;
																																									_position_non_class_name=_position;
																																									_token_non_class_name=_token;
																																									_token=new Tokens.Rule.NonClassNameToken();
																																									if(_position+1-1 >=_inputLength) {
																																										_state=FAILED;
																																									}
																																									else {
																																										if(_inputArray[_position+0]!='N') {
																																											_state=FAILED;
																																										}
																																									}
																																									if(_state==SUCCESS) {
																																										_token.add(_position,Tokens.Syntax.syntax_104.OutputNewObject);
																																										_position=_position+1;
																																										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																											++_position;
																																										}
																																									}
																																									else if(_state==FAILED) {
																																										if(_position>=_furthestPosition) {
																																											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain N");
																																											_furthestPosition=_position;
																																										}
																																									}
																																									if(_state==FAILED) {
																																										if(_position>=_furthestPosition) {
																																											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																											_furthestPosition=_position;
																																										}
																																										_position=_position_non_class_name;
																																									}
																																									else {
																																									}
																																									if(_state==SUCCESS) {
																																										_token_non_class_name.add(_position_non_class_name,_token);
																																									}
																																									_token=_token_non_class_name;
																																									if(_state==FAILED) {
																																										class_names.reject(_position_non_class_name);
																																										class_variable_names.reject(_position_non_class_name);
																																										variable_names.reject(_position_non_class_name);
																																										_state=SUCCESS;
																																										_position_non_class_name=_position;
																																										_token_non_class_name=_token;
																																										_token=new Tokens.Rule.NonClassNameToken();
																																										if(_position+1-1 >=_inputLength) {
																																											_state=FAILED;
																																										}
																																										else {
																																											if(_inputArray[_position+0]!='O') {
																																												_state=FAILED;
																																											}
																																										}
																																										if(_state==SUCCESS) {
																																											_token.add(_position,Tokens.Syntax.syntax_105.OutputOperator);
																																											_position=_position+1;
																																											while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																												++_position;
																																											}
																																										}
																																										else if(_state==FAILED) {
																																											if(_position>=_furthestPosition) {
																																												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain O");
																																												_furthestPosition=_position;
																																											}
																																										}
																																										if(_state==FAILED) {
																																											if(_position>=_furthestPosition) {
																																												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																												_furthestPosition=_position;
																																											}
																																											_position=_position_non_class_name;
																																										}
																																										else {
																																										}
																																										if(_state==SUCCESS) {
																																											_token_non_class_name.add(_position_non_class_name,_token);
																																										}
																																										_token=_token_non_class_name;
																																										if(_state==FAILED) {
																																											class_names.reject(_position_non_class_name);
																																											class_variable_names.reject(_position_non_class_name);
																																											variable_names.reject(_position_non_class_name);
																																											_state=SUCCESS;
																																											_position_non_class_name=_position;
																																											_token_non_class_name=_token;
																																											_token=new Tokens.Rule.NonClassNameToken();
																																											if(_position+1-1 >=_inputLength) {
																																												_state=FAILED;
																																											}
																																											else {
																																												if(_inputArray[_position+0]!='P') {
																																													_state=FAILED;
																																												}
																																											}
																																											if(_state==SUCCESS) {
																																												_token.add(_position,Tokens.Syntax.syntax_106.OutputParameters);
																																												_position=_position+1;
																																												while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																													++_position;
																																												}
																																											}
																																											else if(_state==FAILED) {
																																												if(_position>=_furthestPosition) {
																																													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain P");
																																													_furthestPosition=_position;
																																												}
																																											}
																																											if(_state==FAILED) {
																																												if(_position>=_furthestPosition) {
																																													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																													_furthestPosition=_position;
																																												}
																																												_position=_position_non_class_name;
																																											}
																																											else {
																																											}
																																											if(_state==SUCCESS) {
																																												_token_non_class_name.add(_position_non_class_name,_token);
																																											}
																																											_token=_token_non_class_name;
																																											if(_state==FAILED) {
																																												class_names.reject(_position_non_class_name);
																																												class_variable_names.reject(_position_non_class_name);
																																												variable_names.reject(_position_non_class_name);
																																												_state=SUCCESS;
																																												_position_non_class_name=_position;
																																												_token_non_class_name=_token;
																																												_token=new Tokens.Rule.NonClassNameToken();
																																												if(_position+1-1 >=_inputLength) {
																																													_state=FAILED;
																																												}
																																												else {
																																													if(_inputArray[_position+0]!='S') {
																																														_state=FAILED;
																																													}
																																												}
																																												if(_state==SUCCESS) {
																																													_token.add(_position,Tokens.Syntax.syntax_107.OutputStatement);
																																													_position=_position+1;
																																													while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																														++_position;
																																													}
																																												}
																																												else if(_state==FAILED) {
																																													if(_position>=_furthestPosition) {
																																														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain S");
																																														_furthestPosition=_position;
																																													}
																																												}
																																												if(_state==FAILED) {
																																													if(_position>=_furthestPosition) {
																																														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																														_furthestPosition=_position;
																																													}
																																													_position=_position_non_class_name;
																																												}
																																												else {
																																												}
																																												if(_state==SUCCESS) {
																																													_token_non_class_name.add(_position_non_class_name,_token);
																																												}
																																												_token=_token_non_class_name;
																																												if(_state==FAILED) {
																																													class_names.reject(_position_non_class_name);
																																													class_variable_names.reject(_position_non_class_name);
																																													variable_names.reject(_position_non_class_name);
																																													_state=SUCCESS;
																																													_position_non_class_name=_position;
																																													_token_non_class_name=_token;
																																													_token=new Tokens.Rule.NonClassNameToken();
																																													parse__anonymous_86();
																																													if(_state==FAILED) {
																																														if(_position>=_furthestPosition) {
																																															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																															_furthestPosition=_position;
																																														}
																																														_position=_position_non_class_name;
																																													}
																																													else {
																																													}
																																													if(_state==SUCCESS) {
																																														_token_non_class_name.add(_position_non_class_name,_token);
																																													}
																																													_token=_token_non_class_name;
																																													if(_state==FAILED) {
																																														class_names.reject(_position_non_class_name);
																																														class_variable_names.reject(_position_non_class_name);
																																														variable_names.reject(_position_non_class_name);
																																														_state=SUCCESS;
																																														_position_non_class_name=_position;
																																														_token_non_class_name=_token;
																																														_token=new Tokens.Rule.NonClassNameToken();
																																														if(_position+1-1 >=_inputLength) {
																																															_state=FAILED;
																																														}
																																														else {
																																															if(_inputArray[_position+0]!='V') {
																																																_state=FAILED;
																																															}
																																														}
																																														if(_state==SUCCESS) {
																																															_token.add(_position,Tokens.Syntax.syntax_109.OutputVariable);
																																															_position=_position+1;
																																															while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
																																																++_position;
																																															}
																																														}
																																														else if(_state==FAILED) {
																																															if(_position>=_furthestPosition) {
																																																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain V");
																																																_furthestPosition=_position;
																																															}
																																														}
																																														if(_state==FAILED) {
																																															if(_position>=_furthestPosition) {
																																																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_name(non_class_name)");
																																																_furthestPosition=_position;
																																															}
																																															_position=_position_non_class_name;
																																														}
																																														else {
																																														}
																																														if(_state==SUCCESS) {
																																															_token_non_class_name.add(_position_non_class_name,_token);
																																														}
																																														_token=_token_non_class_name;
																																														if(_state==FAILED) {
																																															class_names.reject(_position_non_class_name);
																																															class_variable_names.reject(_position_non_class_name);
																																															variable_names.reject(_position_non_class_name);
																																														}
																																														else if(_state==SUCCESS) {
																																															class_names.accept(_position_non_class_name);
																																															class_variable_names.accept(_position_non_class_name);
																																															variable_names.accept(_position_non_class_name);
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
	public void parse_non_class_variable_name_definition() {
		int _position_non_class_variable_name_definition = -1;
		Token.Parsed _token_non_class_variable_name_definition = null;
		int _position_all_type_name = -1;
		Token.Parsed _token_all_type_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_non_class_variable_name_definition=_position;
		_token_non_class_variable_name_definition=_token;
		_token=new Tokens.Rule.NonClassVariableNameDefinitionToken();
		_token_all_type_name=_token;
		_token=new Tokens.Name.TypeNameToken();
		_position_all_type_name=_position;
		parse_all_type_name();
		if(_state==SUCCESS) {
			_token_all_type_name.add(_position_all_type_name,_token);
		}
		_token=_token_all_type_name;
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_variable_name_definition(non_class_variable_name_definition)");
				_furthestPosition=_position;
			}
			_position=_position_non_class_variable_name_definition;
		}
		else {
			int _state_81 = _state;
			if(_position+3-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='.') {
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='.') {
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='.') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_56.INLINE_LIST);
				_position=_position+3;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ...");
					_furthestPosition=_position;
				}
			}
			if(_state_81==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_variable_name_definition(non_class_variable_name_definition)");
					_furthestPosition=_position;
				}
				_position=_position_non_class_variable_name_definition;
			}
			else {
				int _state_82 = _state;
				while(_position<_inputLength) {
					if(_position+2-1 >=_inputLength) {
						_state=FAILED;
					}
					else {
						if(_inputArray[_position+0]!='[') {
							_state=FAILED;
						}
						if(_inputArray[_position+1]!=']') {
							_state=FAILED;
						}
					}
					if(_state==SUCCESS) {
						_token.add(_position,Tokens.Syntax.syntax_53.ARRAY_TYPE);
						_position=_position+2;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
							++_position;
						}
					}
					else if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain []");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED) {
						break;
					}
				}
				if(_state_82==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_variable_name_definition(non_class_variable_name_definition)");
						_furthestPosition=_position;
					}
					_position=_position_non_class_variable_name_definition;
				}
				else {
					parse__anonymous_77();
					if(_state==SUCCESS) {
						String _value = _token.getLastValue();
						if(_value!=null) {
							variable_names.add(_value);
						}
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"non_class_variable_name_definition(non_class_variable_name_definition)");
							_furthestPosition=_position;
						}
						_position=_position_non_class_variable_name_definition;
					}
					else {
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_non_class_variable_name_definition.addAll(_token);
			_token_non_class_variable_name_definition.setValue(_token.getValue());
		}
		_token=_token_non_class_variable_name_definition;
		if(_state==FAILED) {
			class_names.reject(_position_non_class_variable_name_definition);
			class_variable_names.reject(_position_non_class_variable_name_definition);
			variable_names.reject(_position_non_class_variable_name_definition);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_non_class_variable_name_definition);
			class_variable_names.accept(_position_non_class_variable_name_definition);
			variable_names.accept(_position_non_class_variable_name_definition);
		}
	}
}