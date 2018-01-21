package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.quote_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class all_context extends quote_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public all_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public all_context() {
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
	public void parse_all_type_name(){
		int _position_all_type_name = -1;
		Token.Parsed _token_all_type_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_all_type_name=_position;
		_token_all_type_name=_token;
		_token=new Tokens.Rule.AllTypeNameToken();
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
			_token.add(_position,Tokens.Syntax.syntax_42.__SYNTAX__);
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
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
				_furthestPosition=_position;
			}
			_position=_position_all_type_name;
		}
		else{
		}
		if(_state==SUCCESS){
			_token_all_type_name.addAll(_token);
			_token_all_type_name.setValue(_token.getValue());
		}
		_token=_token_all_type_name;
		if(_state==FAILED){
			class_names.reject(_position_all_type_name);
			class_variable_names.reject(_position_all_type_name);
			variable_names.reject(_position_all_type_name);
			_state=SUCCESS;
			_position_all_type_name=_position;
			_token_all_type_name=_token;
			_token=new Tokens.Rule.AllTypeNameToken();
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='M'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='h'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='o'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='d'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_43.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Method");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
					_furthestPosition=_position;
				}
				_position=_position_all_type_name;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_all_type_name.addAll(_token);
				_token_all_type_name.setValue(_token.getValue());
			}
			_token=_token_all_type_name;
			if(_state==FAILED){
				class_names.reject(_position_all_type_name);
				class_variable_names.reject(_position_all_type_name);
				variable_names.reject(_position_all_type_name);
				_state=SUCCESS;
				_position_all_type_name=_position;
				_token_all_type_name=_token;
				_token=new Tokens.Rule.AllTypeNameToken();
				if(_position+8-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='V'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='a'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='r'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='a'){
						_state=FAILED;
					}
					if(_inputArray[_position+5]!='b'){
						_state=FAILED;
					}
					if(_inputArray[_position+6]!='l'){
						_state=FAILED;
					}
					if(_inputArray[_position+7]!='e'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_44.__SYNTAX__);
					_position=_position+8;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Variable");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
						_furthestPosition=_position;
					}
					_position=_position_all_type_name;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_all_type_name.addAll(_token);
					_token_all_type_name.setValue(_token.getValue());
				}
				_token=_token_all_type_name;
				if(_state==FAILED){
					class_names.reject(_position_all_type_name);
					class_variable_names.reject(_position_all_type_name);
					variable_names.reject(_position_all_type_name);
					_state=SUCCESS;
					_position_all_type_name=_position;
					_token_all_type_name=_token;
					_token=new Tokens.Rule.AllTypeNameToken();
					if(_position+4-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='B'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='o'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='d'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='y'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_45.__SYNTAX__);
						_position=_position+4;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Body");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
							_furthestPosition=_position;
						}
						_position=_position_all_type_name;
					}
					else{
					}
					if(_state==SUCCESS){
						_token_all_type_name.addAll(_token);
						_token_all_type_name.setValue(_token.getValue());
					}
					_token=_token_all_type_name;
					if(_state==FAILED){
						class_names.reject(_position_all_type_name);
						class_variable_names.reject(_position_all_type_name);
						variable_names.reject(_position_all_type_name);
						_state=SUCCESS;
						_position_all_type_name=_position;
						_token_all_type_name=_token;
						_token=new Tokens.Rule.AllTypeNameToken();
						if(_position+9-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='S'){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='t'){
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='a'){
								_state=FAILED;
							}
							if(_inputArray[_position+3]!='t'){
								_state=FAILED;
							}
							if(_inputArray[_position+4]!='e'){
								_state=FAILED;
							}
							if(_inputArray[_position+5]!='m'){
								_state=FAILED;
							}
							if(_inputArray[_position+6]!='e'){
								_state=FAILED;
							}
							if(_inputArray[_position+7]!='n'){
								_state=FAILED;
							}
							if(_inputArray[_position+8]!='t'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_46.__SYNTAX__);
							_position=_position+9;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Statement");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
								_furthestPosition=_position;
							}
							_position=_position_all_type_name;
						}
						else{
						}
						if(_state==SUCCESS){
							_token_all_type_name.addAll(_token);
							_token_all_type_name.setValue(_token.getValue());
						}
						_token=_token_all_type_name;
						if(_state==FAILED){
							class_names.reject(_position_all_type_name);
							class_variable_names.reject(_position_all_type_name);
							variable_names.reject(_position_all_type_name);
							_state=SUCCESS;
							_position_all_type_name=_position;
							_token_all_type_name=_token;
							_token=new Tokens.Rule.AllTypeNameToken();
							if(_position+10-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='P'){
									_state=FAILED;
								}
								if(_inputArray[_position+1]!='a'){
									_state=FAILED;
								}
								if(_inputArray[_position+2]!='r'){
									_state=FAILED;
								}
								if(_inputArray[_position+3]!='a'){
									_state=FAILED;
								}
								if(_inputArray[_position+4]!='m'){
									_state=FAILED;
								}
								if(_inputArray[_position+5]!='e'){
									_state=FAILED;
								}
								if(_inputArray[_position+6]!='t'){
									_state=FAILED;
								}
								if(_inputArray[_position+7]!='e'){
									_state=FAILED;
								}
								if(_inputArray[_position+8]!='r'){
									_state=FAILED;
								}
								if(_inputArray[_position+9]!='s'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_47.__SYNTAX__);
								_position=_position+10;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Parameters");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
									_furthestPosition=_position;
								}
								_position=_position_all_type_name;
							}
							else{
							}
							if(_state==SUCCESS){
								_token_all_type_name.addAll(_token);
								_token_all_type_name.setValue(_token.getValue());
							}
							_token=_token_all_type_name;
							if(_state==FAILED){
								class_names.reject(_position_all_type_name);
								class_variable_names.reject(_position_all_type_name);
								variable_names.reject(_position_all_type_name);
								_state=SUCCESS;
								_position_all_type_name=_position;
								_token_all_type_name=_token;
								_token=new Tokens.Rule.AllTypeNameToken();
								if(_position+7-1 >=_inputLength){
									_state=FAILED;
								}
								else{
									if(_inputArray[_position+0]!='C'){
										_state=FAILED;
									}
									if(_inputArray[_position+1]!='o'){
										_state=FAILED;
									}
									if(_inputArray[_position+2]!='n'){
										_state=FAILED;
									}
									if(_inputArray[_position+3]!='t'){
										_state=FAILED;
									}
									if(_inputArray[_position+4]!='e'){
										_state=FAILED;
									}
									if(_inputArray[_position+5]!='x'){
										_state=FAILED;
									}
									if(_inputArray[_position+6]!='t'){
										_state=FAILED;
									}
								}
								if(_state==SUCCESS){
									_token.add(_position,Tokens.Syntax.syntax_48.__SYNTAX__);
									_position=_position+7;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
										++_position;
									}
								}
								else if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain Context");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
										_furthestPosition=_position;
									}
									_position=_position_all_type_name;
								}
								else{
								}
								if(_state==SUCCESS){
									_token_all_type_name.addAll(_token);
									_token_all_type_name.setValue(_token.getValue());
								}
								_token=_token_all_type_name;
								if(_state==FAILED){
									class_names.reject(_position_all_type_name);
									class_variable_names.reject(_position_all_type_name);
									variable_names.reject(_position_all_type_name);
									_state=SUCCESS;
									_position_all_type_name=_position;
									_token_all_type_name=_token;
									_token=new Tokens.Rule.AllTypeNameToken();
									if(_position+2-1 >=_inputLength){
										_state=FAILED;
									}
									else{
										if(_inputArray[_position+0]!='%'){
											_state=FAILED;
										}
										if(_inputArray[_position+1]!='T'){
											_state=FAILED;
										}
									}
									if(_state==SUCCESS){
										_token.add(_position,Tokens.Syntax.syntax_49.__SYNTAX__);
										_position=_position+2;
										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
											++_position;
										}
									}
									else if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %T");
											_furthestPosition=_position;
										}
									}
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
											_furthestPosition=_position;
										}
										_position=_position_all_type_name;
									}
									else{
									}
									if(_state==SUCCESS){
										_token_all_type_name.addAll(_token);
										_token_all_type_name.setValue(_token.getValue());
									}
									_token=_token_all_type_name;
									if(_state==FAILED){
										class_names.reject(_position_all_type_name);
										class_variable_names.reject(_position_all_type_name);
										variable_names.reject(_position_all_type_name);
										_state=SUCCESS;
										_position_all_type_name=_position;
										_token_all_type_name=_token;
										_token=new Tokens.Rule.AllTypeNameToken();
										if(_position+7-1 >=_inputLength){
											_state=FAILED;
										}
										else{
											if(_inputArray[_position+0]!='%'){
												_state=FAILED;
											}
											if(_inputArray[_position+1]!='P'){
												_state=FAILED;
											}
											if(_inputArray[_position+2]!='a'){
												_state=FAILED;
											}
											if(_inputArray[_position+3]!='r'){
												_state=FAILED;
											}
											if(_inputArray[_position+4]!='s'){
												_state=FAILED;
											}
											if(_inputArray[_position+5]!='e'){
												_state=FAILED;
											}
											if(_inputArray[_position+6]!='r'){
												_state=FAILED;
											}
										}
										if(_state==SUCCESS){
											_token.add(_position,Tokens.Syntax.syntax_50.__SYNTAX__);
											_position=_position+7;
											while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
												++_position;
											}
										}
										else if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %Parser");
												_furthestPosition=_position;
											}
										}
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
												_furthestPosition=_position;
											}
											_position=_position_all_type_name;
										}
										else{
										}
										if(_state==SUCCESS){
											_token_all_type_name.addAll(_token);
											_token_all_type_name.setValue(_token.getValue());
										}
										_token=_token_all_type_name;
										if(_state==FAILED){
											class_names.reject(_position_all_type_name);
											class_variable_names.reject(_position_all_type_name);
											variable_names.reject(_position_all_type_name);
											_state=SUCCESS;
											_position_all_type_name=_position;
											_token_all_type_name=_token;
											_token=new Tokens.Rule.AllTypeNameToken();
											if(_position+7-1 >=_inputLength){
												_state=FAILED;
											}
											else{
												if(_inputArray[_position+0]!='%'){
													_state=FAILED;
												}
												if(_inputArray[_position+1]!='R'){
													_state=FAILED;
												}
												if(_inputArray[_position+2]!='e'){
													_state=FAILED;
												}
												if(_inputArray[_position+3]!='s'){
													_state=FAILED;
												}
												if(_inputArray[_position+4]!='u'){
													_state=FAILED;
												}
												if(_inputArray[_position+5]!='l'){
													_state=FAILED;
												}
												if(_inputArray[_position+6]!='t'){
													_state=FAILED;
												}
											}
											if(_state==SUCCESS){
												_token.add(_position,Tokens.Syntax.syntax_51.__SYNTAX__);
												_position=_position+7;
												while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
													++_position;
												}
											}
											else if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %Result");
													_furthestPosition=_position;
												}
											}
											if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
													_furthestPosition=_position;
												}
												_position=_position_all_type_name;
											}
											else{
											}
											if(_state==SUCCESS){
												_token_all_type_name.addAll(_token);
												_token_all_type_name.setValue(_token.getValue());
											}
											_token=_token_all_type_name;
											if(_state==FAILED){
												class_names.reject(_position_all_type_name);
												class_variable_names.reject(_position_all_type_name);
												variable_names.reject(_position_all_type_name);
												_state=SUCCESS;
												_position_all_type_name=_position;
												_token_all_type_name=_token;
												_token=new Tokens.Rule.AllTypeNameToken();
												if(_position+5-1 >=_inputLength){
													_state=FAILED;
												}
												else{
													if(_inputArray[_position+0]!='%'){
														_state=FAILED;
													}
													if(_inputArray[_position+1]!='P'){
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
													_token.add(_position,Tokens.Syntax.syntax_52.__SYNTAX__);
													_position=_position+5;
													while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
														++_position;
													}
												}
												else if(_state==FAILED){
													if(_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %Pass");
														_furthestPosition=_position;
													}
												}
												if(_state==FAILED){
													if(_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
														_furthestPosition=_position;
													}
													_position=_position_all_type_name;
												}
												else{
												}
												if(_state==SUCCESS){
													_token_all_type_name.addAll(_token);
													_token_all_type_name.setValue(_token.getValue());
												}
												_token=_token_all_type_name;
												if(_state==FAILED){
													class_names.reject(_position_all_type_name);
													class_variable_names.reject(_position_all_type_name);
													variable_names.reject(_position_all_type_name);
													_state=SUCCESS;
													_position_all_type_name=_position;
													_token_all_type_name=_token;
													_token=new Tokens.Rule.AllTypeNameToken();
													if(_position+5-1 >=_inputLength){
														_state=FAILED;
													}
													else{
														if(_inputArray[_position+0]!='%'){
															_state=FAILED;
														}
														if(_inputArray[_position+1]!='F'){
															_state=FAILED;
														}
														if(_inputArray[_position+2]!='a'){
															_state=FAILED;
														}
														if(_inputArray[_position+3]!='i'){
															_state=FAILED;
														}
														if(_inputArray[_position+4]!='l'){
															_state=FAILED;
														}
													}
													if(_state==SUCCESS){
														_token.add(_position,Tokens.Syntax.syntax_53.__SYNTAX__);
														_position=_position+5;
														while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
															++_position;
														}
													}
													else if(_state==FAILED){
														if(_position>=_furthestPosition){
															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %Fail");
															_furthestPosition=_position;
														}
													}
													if(_state==FAILED){
														if(_position>=_furthestPosition){
															_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
															_furthestPosition=_position;
														}
														_position=_position_all_type_name;
													}
													else{
													}
													if(_state==SUCCESS){
														_token_all_type_name.addAll(_token);
														_token_all_type_name.setValue(_token.getValue());
													}
													_token=_token_all_type_name;
													if(_state==FAILED){
														class_names.reject(_position_all_type_name);
														class_variable_names.reject(_position_all_type_name);
														variable_names.reject(_position_all_type_name);
														_state=SUCCESS;
														_position_all_type_name=_position;
														_token_all_type_name=_token;
														_token=new Tokens.Rule.AllTypeNameToken();
														parse_type_var();
														if(_state==FAILED){
															if(_position>=_furthestPosition){
																_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
																_furthestPosition=_position;
															}
															_position=_position_all_type_name;
														}
														else{
															int _state_84 = _state;
															while(_position<_inputLength){
																parse__anonymous_64();
																if(_state==FAILED){
																	break;
																}
															}
															if(_state_84==SUCCESS&&_state==FAILED){
																_state=SUCCESS;
															}
															if(_state==FAILED){
																if(_position>=_furthestPosition){
																	_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(all_type_name)");
																	_furthestPosition=_position;
																}
																_position=_position_all_type_name;
															}
															else{
															}
														}
														if(_state==SUCCESS){
															_token_all_type_name.addAll(_token);
															_token_all_type_name.setValue(_token.getValue());
														}
														_token=_token_all_type_name;
														if(_state==FAILED){
															class_names.reject(_position_all_type_name);
															class_variable_names.reject(_position_all_type_name);
															variable_names.reject(_position_all_type_name);
														}
														else if(_state==SUCCESS){
															class_names.accept(_position_all_type_name);
															class_variable_names.accept(_position_all_type_name);
															variable_names.accept(_position_all_type_name);
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