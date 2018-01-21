package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.template_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class class_declaration_context extends template_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public class_declaration_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public class_declaration_context() {
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
	public void parse_class_declaration(){
		int _position_class_declaration = -1;
		Token.Parsed _token_class_declaration = null;
		int _position_inner = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_class_declaration=_position;
		_token_class_declaration=_token;
		_token=new Tokens.Rule.ClassDeclarationToken();
		int _state_26 = _state;
		_position_inner=_position;
		if(_state==SUCCESS&&!_recursion_protection_inner_13.contains(_position)){
			_recursion_protection_inner_13.add(_position);
			parse_inner();
			_recursion_protection_inner_13.remove(_position_inner);
		}
		else{
			_state=FAILED;
		}
		if(_state_26==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
				_furthestPosition=_position;
			}
			_position=_position_class_declaration;
		}
		else{
			int _state_27 = _state;
			parse_weak();
			if(_state_27==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
					_furthestPosition=_position;
				}
				_position=_position_class_declaration;
			}
			else{
				parse__anonymous_11();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_class_declaration;
				}
				else{
					parse__anonymous_12();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_class_declaration;
					}
					else{
						parse__anonymous_14();
						if(_state==SUCCESS){
							String _value = _token.getLastValue();
							if(_value!=null){
								class_names.add(_value);
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_class_declaration;
						}
						else{
							int _state_29 = _state;
							parse__anonymous_15();
							if(_state_29==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_class_declaration;
							}
							else{
								int _state_30 = _state;
								parse__anonymous_16();
								if(_state_30==SUCCESS&&_state==FAILED){
									_state=SUCCESS;
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_class_declaration;
								}
								else{
									int _state_31 = _state;
									while(_position<_inputLength){
										parse__anonymous_17();
										if(_state==FAILED){
											break;
										}
									}
									if(_state_31==SUCCESS&&_state==FAILED){
										_state=SUCCESS;
									}
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_class_declaration;
									}
									else{
										parse_class_body();
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
												_furthestPosition=_position;
											}
											_position=_position_class_declaration;
										}
										else{
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token_class_declaration.add(_position_class_declaration,_token);
		}
		_token=_token_class_declaration;
		if(_state==FAILED){
			class_names.reject(_position_class_declaration);
			class_variable_names.reject(_position_class_declaration);
			variable_names.reject(_position_class_declaration);
			_state=SUCCESS;
			_position_class_declaration=_position;
			_token_class_declaration=_token;
			_token=new Tokens.Rule.ClassDeclarationToken();
			int _state_32 = _state;
			_position_inner=_position;
			if(_state==SUCCESS&&!_recursion_protection_inner_17.contains(_position)){
				_recursion_protection_inner_17.add(_position);
				parse_inner();
				_recursion_protection_inner_17.remove(_position_inner);
			}
			else{
				_state=FAILED;
			}
			if(_state_32==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
					_furthestPosition=_position;
				}
				_position=_position_class_declaration;
			}
			else{
				int _state_33 = _state;
				parse_weak();
				if(_state_33==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_class_declaration;
				}
				else{
					parse__anonymous_18();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_class_declaration;
					}
					else{
						parse__anonymous_19();
						if(_state==SUCCESS){
							String _value = _token.getLastValue();
							if(_value!=null){
								class_names.add(_value);
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_class_declaration;
						}
						else{
							int _state_34 = _state;
							parse__anonymous_20();
							if(_state_34==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_class_declaration;
							}
							else{
								if(_position+1-1 >=_inputLength){
									_state=FAILED;
								}
								else{
									if(_inputArray[_position+0]!='/'){
										_state=FAILED;
									}
								}
								if(_state==SUCCESS){
									_token.add(_position,Tokens.Syntax.syntax_17.__SYNTAX__);
									_position=_position+1;
									while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
										++_position;
									}
								}
								else if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain /");
										_furthestPosition=_position;
									}
								}
								if(_state==FAILED){
									if(_position>=_furthestPosition){
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_class_declaration;
								}
								else{
									int _state_35 = _state;
									parse__anonymous_21();
									if(_state_35==SUCCESS&&_state==FAILED){
										_state=SUCCESS;
									}
									if(_state==FAILED){
										if(_position>=_furthestPosition){
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_class_declaration;
									}
									else{
										if(_position+1-1 >=_inputLength){
											_state=FAILED;
										}
										else{
											if(_inputArray[_position+0]!='/'){
												_state=FAILED;
											}
										}
										if(_state==SUCCESS){
											_token.add(_position,Tokens.Syntax.syntax_17.__SYNTAX__);
											_position=_position+1;
											while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
												++_position;
											}
										}
										else if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain /");
												_furthestPosition=_position;
											}
										}
										if(_state==FAILED){
											if(_position>=_furthestPosition){
												_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
												_furthestPosition=_position;
											}
											_position=_position_class_declaration;
										}
										else{
											int _state_36 = _state;
											while(_position<_inputLength){
												parse__anonymous_22();
												if(_state==FAILED){
													break;
												}
											}
											if(_state_36==SUCCESS&&_state==FAILED){
												_state=SUCCESS;
											}
											if(_state==FAILED){
												if(_position>=_furthestPosition){
													_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
													_furthestPosition=_position;
												}
												_position=_position_class_declaration;
											}
											else{
												parse_class_body();
												if(_state==FAILED){
													if(_position>=_furthestPosition){
														_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(class_declaration)");
														_furthestPosition=_position;
													}
													_position=_position_class_declaration;
												}
												else{
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
			if(_state==SUCCESS){
				_token_class_declaration.add(_position_class_declaration,_token);
			}
			_token=_token_class_declaration;
			if(_state==FAILED){
				class_names.reject(_position_class_declaration);
				class_variable_names.reject(_position_class_declaration);
				variable_names.reject(_position_class_declaration);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position_class_declaration);
				class_variable_names.accept(_position_class_declaration);
				variable_names.accept(_position_class_declaration);
			}
		}
	}
}