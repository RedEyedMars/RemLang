package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.anonymous_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class method_context extends anonymous_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public method_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public method_context() {
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
	public void parse_method_prototype_parameters() {
		int _position_method_prototype_parameters = -1;
		Token.Parsed _token_method_prototype_parameters = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_method_prototype_parameters=_position;
		_token_method_prototype_parameters=_token;
		_token=new Tokens.Rule.MethodPrototypeParametersToken();
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
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_prototype_parameters(method_prototype_parameters)");
				_furthestPosition=_position;
			}
			_position=_position_method_prototype_parameters;
		}
		else {
			int _state_63 = _state;
			parse_type_var();
			if(_state_63==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_prototype_parameters(method_prototype_parameters)");
					_furthestPosition=_position;
				}
				_position=_position_method_prototype_parameters;
			}
			else {
				int _state_64 = _state;
				while(_position<_inputLength) {
					parse__anonymous_61();
					if(_state==FAILED) {
						break;
					}
				}
				if(_state_64==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_prototype_parameters(method_prototype_parameters)");
						_furthestPosition=_position;
					}
					_position=_position_method_prototype_parameters;
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_prototype_parameters(method_prototype_parameters)");
							_furthestPosition=_position;
						}
						_position=_position_method_prototype_parameters;
					}
					else {
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_method_prototype_parameters.add(_position_method_prototype_parameters,_token);
		}
		_token=_token_method_prototype_parameters;
		if(_state==FAILED) {
			class_names.reject(_position_method_prototype_parameters);
			class_variable_names.reject(_position_method_prototype_parameters);
			variable_names.reject(_position_method_prototype_parameters);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_method_prototype_parameters);
			class_variable_names.accept(_position_method_prototype_parameters);
			variable_names.accept(_position_method_prototype_parameters);
		}
	}
	public void parse_method_declaration() {
		int _position_method_declaration = -1;
		Token.Parsed _token_method_declaration = null;
		int _position_inner = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_method_declaration=_position;
		_token_method_declaration=_token;
		_token=new Tokens.Rule.MethodDeclarationToken();
		int _state_72 = _state;
		_position_inner=_position;
		if(_state==SUCCESS&&!_recursion_protection_inner_42.contains(_position)) {
			_recursion_protection_inner_42.add(_position);
			parse_inner();
			_recursion_protection_inner_42.remove(_position_inner);
		}
		else {
			_state=FAILED;
		}
		if(_state_72==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
				_furthestPosition=_position;
			}
			_position=_position_method_declaration;
		}
		else {
			int _state_73 = _state;
			parse__anonymous_68();
			if(_state_73==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
					_furthestPosition=_position;
				}
				_position=_position_method_declaration;
			}
			else {
				parse_all_type_name();
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_method_declaration;
				}
				else {
					int _state_74 = _state;
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
					if(_state_74==SUCCESS&&_state==FAILED) {
						_state=SUCCESS;
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_method_declaration;
					}
					else {
						int _state_75 = _state;
						parse__anonymous_69();
						if(_state_75==SUCCESS&&_state==FAILED) {
							_state=SUCCESS;
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_method_declaration;
						}
						else {
							parse__anonymous_70();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_method_declaration;
							}
							else {
								int _state_76 = _state;
								parse__anonymous_71();
								if(_state_76==SUCCESS&&_state==FAILED) {
									_state=SUCCESS;
								}
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
										_furthestPosition=_position;
									}
									_position=_position_method_declaration;
								}
								else {
									parse__anonymous_73();
									if(_state==FAILED) {
										if(_position>=_furthestPosition) {
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(method_declaration)");
											_furthestPosition=_position;
										}
										_position=_position_method_declaration;
									}
									else {
									}
								}
							}
						}
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_method_declaration.add(_position_method_declaration,_token);
		}
		_token=_token_method_declaration;
		if(_state==FAILED) {
			class_names.reject(_position_method_declaration);
			class_variable_names.reject(_position_method_declaration);
			variable_names.reject(_position_method_declaration);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_method_declaration);
			class_variable_names.accept(_position_method_declaration);
			variable_names.accept(_position_method_declaration);
		}
	}
	public void parse_method_argument() {
		int _position_method_argument = -1;
		Token.Parsed _token_method_argument = null;
		int _position_variable_declaration = -1;
		int _position_method_declaration = -1;
		int _position_method_body = -1;
		int _position_class_declaration = -1;
		int _position_body_statement = -1;
		Token.Parsed _token_method_body = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_method_argument=_position;
		_token_method_argument=_token;
		_token=new Tokens.Rule.MethodArgumentToken();
		parse__anonymous_65();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
				_furthestPosition=_position;
			}
			_position=_position_method_argument;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_method_argument.add(_position_method_argument,_token);
		}
		_token=_token_method_argument;
		if(_state==FAILED) {
			class_names.reject(_position_method_argument);
			class_variable_names.reject(_position_method_argument);
			variable_names.reject(_position_method_argument);
			_state=SUCCESS;
			_position_method_argument=_position;
			_token_method_argument=_token;
			_token=new Tokens.Rule.MethodArgumentToken();
			_position_class_declaration=_position;
			if(_state==SUCCESS&&!_recursion_protection_class_declaration_37.contains(_position)) {
				_recursion_protection_class_declaration_37.add(_position);
				parse_class_declaration();
				_recursion_protection_class_declaration_37.remove(_position_class_declaration);
			}
			else {
				_state=FAILED;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
					_furthestPosition=_position;
				}
				_position=_position_method_argument;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_method_argument.add(_position_method_argument,_token);
			}
			_token=_token_method_argument;
			if(_state==FAILED) {
				class_names.reject(_position_method_argument);
				class_variable_names.reject(_position_method_argument);
				variable_names.reject(_position_method_argument);
				_state=SUCCESS;
				_position_method_argument=_position;
				_token_method_argument=_token;
				_token=new Tokens.Rule.MethodArgumentToken();
				_position_method_declaration=_position;
				if(_state==SUCCESS&&!_recursion_protection_method_declaration_38.contains(_position)) {
					_recursion_protection_method_declaration_38.add(_position);
					parse_method_declaration();
					_recursion_protection_method_declaration_38.remove(_position_method_declaration);
				}
				else {
					_state=FAILED;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
						_furthestPosition=_position;
					}
					_position=_position_method_argument;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_method_argument.add(_position_method_argument,_token);
				}
				_token=_token_method_argument;
				if(_state==FAILED) {
					class_names.reject(_position_method_argument);
					class_variable_names.reject(_position_method_argument);
					variable_names.reject(_position_method_argument);
					_state=SUCCESS;
					_position_method_argument=_position;
					_token_method_argument=_token;
					_token=new Tokens.Rule.MethodArgumentToken();
					_position_variable_declaration=_position;
					if(_state==SUCCESS&&!_recursion_protection_variable_declaration_39.contains(_position)) {
						_recursion_protection_variable_declaration_39.add(_position);
						parse_variable_declaration();
						_recursion_protection_variable_declaration_39.remove(_position_variable_declaration);
					}
					else {
						_state=FAILED;
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
							_furthestPosition=_position;
						}
						_position=_position_method_argument;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token_method_argument.add(_position_method_argument,_token);
					}
					_token=_token_method_argument;
					if(_state==FAILED) {
						class_names.reject(_position_method_argument);
						class_variable_names.reject(_position_method_argument);
						variable_names.reject(_position_method_argument);
						_state=SUCCESS;
						_position_method_argument=_position;
						_token_method_argument=_token;
						_token=new Tokens.Rule.MethodArgumentToken();
						parse_as_statement();
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
								_furthestPosition=_position;
							}
							_position=_position_method_argument;
						}
						else {
						}
						if(_state==SUCCESS) {
							_token_method_argument.add(_position_method_argument,_token);
						}
						_token=_token_method_argument;
						if(_state==FAILED) {
							class_names.reject(_position_method_argument);
							class_variable_names.reject(_position_method_argument);
							variable_names.reject(_position_method_argument);
							_state=SUCCESS;
							_position_method_argument=_position;
							_token_method_argument=_token;
							_token=new Tokens.Rule.MethodArgumentToken();
							_position_body_statement=_position;
							if(_state==SUCCESS&&!_recursion_protection_body_statement_40.contains(_position)) {
								_recursion_protection_body_statement_40.add(_position);
								parse_body_statement();
								_recursion_protection_body_statement_40.remove(_position_body_statement);
							}
							else {
								_state=FAILED;
							}
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
									_furthestPosition=_position;
								}
								_position=_position_method_argument;
							}
							else {
							}
							if(_state==SUCCESS) {
								_token_method_argument.add(_position_method_argument,_token);
							}
							_token=_token_method_argument;
							if(_state==FAILED) {
								class_names.reject(_position_method_argument);
								class_variable_names.reject(_position_method_argument);
								variable_names.reject(_position_method_argument);
								_state=SUCCESS;
								_position_method_argument=_position;
								_token_method_argument=_token;
								_token=new Tokens.Rule.MethodArgumentToken();
								_token_method_body=_token;
								_token=new Tokens.Name.BodyEntriesToken();
								_position_method_body=_position;
								if(_state==SUCCESS&&!_recursion_protection_method_body_41.contains(_position)) {
									_recursion_protection_method_body_41.add(_position);
									parse_method_body();
									_recursion_protection_method_body_41.remove(_position_method_body);
								}
								else {
									_state=FAILED;
								}
								if(_state==SUCCESS) {
									_token_method_body.add(_position_method_body,_token);
								}
								_token=_token_method_body;
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_argument(method_argument)");
										_furthestPosition=_position;
									}
									_position=_position_method_argument;
								}
								else {
								}
								if(_state==SUCCESS) {
									_token_method_argument.add(_position_method_argument,_token);
								}
								_token=_token_method_argument;
								if(_state==FAILED) {
									class_names.reject(_position_method_argument);
									class_variable_names.reject(_position_method_argument);
									variable_names.reject(_position_method_argument);
								}
								else if(_state==SUCCESS) {
									class_names.accept(_position_method_argument);
									class_variable_names.accept(_position_method_argument);
									variable_names.accept(_position_method_argument);
								}
							}
						}
					}
				}
			}
		}
	}
	public void parse_method_arguments() {
		int _position_method_arguments = -1;
		Token.Parsed _token_method_arguments = null;
		int _length_method_arguments_brace = _inputLength;
		if(brace_3.containsKey(_position)) {
			class_variable_names=class_variable_names.push(_position,_pass);
			variable_names=variable_names.push(_position,_pass);
			_inputLength=brace_3.get(_position);
			int _position_method_arguments_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			class_names.start(_position);
			class_variable_names.start(_position);
			variable_names.start(_position);
			_position_method_arguments=_position;
			_token_method_arguments=_token;
			_token=new Tokens.Rule.MethodArgumentsToken();
			int _state_14 = _state;
			parse__anonymous_0();
			if(_state_14==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(method_arguments)");
					_furthestPosition=_position;
				}
				_position=_position_method_arguments;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_method_arguments.add(_position_method_arguments,_token);
			}
			_token=_token_method_arguments;
			if(_state==SUCCESS&&brace_3.get(_position_method_arguments_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("method_arguments",_position,_lineNumberRanges));
				_position=brace_3.get(_position_method_arguments_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_method_arguments_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			if(_state==FAILED) {
				class_names.reject(_position_method_arguments);
				class_variable_names.reject(_position_method_arguments);
				variable_names.reject(_position_method_arguments);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_method_arguments);
				class_variable_names.accept(_position_method_arguments);
				variable_names.accept(_position_method_arguments);
			}
			class_variable_names=class_variable_names.pop();
			variable_names=variable_names.pop();
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(method_arguments)");
				_furthestPosition=_position;
			}
		}
	}
	public void parse_method_parameters() {
		int _position_method_parameters = -1;
		Token.Parsed _token_method_parameters = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_method_parameters=_position;
		_token_method_parameters=_token;
		_token=new Tokens.Rule.MethodParametersToken();
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
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
				_furthestPosition=_position;
			}
			_position=_position_method_parameters;
		}
		else {
			int _state_65 = _state;
			parse__anonymous_62();
			if(_state_65==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
					_furthestPosition=_position;
				}
				_position=_position_method_parameters;
			}
			else {
				int _state_67 = _state;
				while(_position<_inputLength) {
					parse__anonymous_63();
					if(_state==FAILED) {
						break;
					}
				}
				if(_state_67==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
						_furthestPosition=_position;
					}
					_position=_position_method_parameters;
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
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(method_parameters)");
							_furthestPosition=_position;
						}
						_position=_position_method_parameters;
					}
					else {
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_method_parameters.add(_position_method_parameters,_token);
		}
		_token=_token_method_parameters;
		if(_state==FAILED) {
			class_names.reject(_position_method_parameters);
			class_variable_names.reject(_position_method_parameters);
			variable_names.reject(_position_method_parameters);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_method_parameters);
			class_variable_names.accept(_position_method_parameters);
			variable_names.accept(_position_method_parameters);
		}
	}
	public void parse_method_body() {
		int _position_method_body = -1;
		Token.Parsed _token_method_body = null;
		int _position_body_element = -1;
		int _length_method_body_brace = _inputLength;
		if(brace_2.containsKey(_position)) {
			if(_pass==Parser.SECOND_PASS) {
				class_variable_names=class_variable_names.push();
			}
			if(_pass==Parser.SECOND_PASS) {
				variable_names=variable_names.push();
			}
			if(_pass==SECOND_PASS) {
				_inputLength=brace_2.get(_position);
				int _position_method_body_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
				class_names.start(_position);
				class_variable_names.start(_position);
				variable_names.start(_position);
				_position_method_body=_position;
				_token_method_body=_token;
				_token=new Tokens.Rule.MethodBodyToken();
				int _state_13 = _state;
				while(_position<_inputLength) {
					_position_body_element=_position;
					if(_state==SUCCESS&&!_recursion_protection_body_element_0.contains(_position)) {
						_recursion_protection_body_element_0.add(_position);
						parse_body_element();
						_recursion_protection_body_element_0.remove(_position_body_element);
					}
					else {
						_state=FAILED;
					}
					if(_state==FAILED) {
						break;
					}
				}
				if(_state_13==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_body(method_body)");
						_furthestPosition=_position;
					}
					_position=_position_method_body;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_method_body.addAll(_token);
					_token_method_body.setValue(_token.getValue());
				}
				_token=_token_method_body;
				if(_state==SUCCESS&&brace_2.get(_position_method_body_brace)==_position) {
					_position+=1;
				}
				else {
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail.EOB("method_body",_position,_lineNumberRanges));
					_position=brace_2.get(_position_method_body_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_method_body_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
				if(_state==FAILED) {
					class_names.reject(_position_method_body);
					class_variable_names.reject(_position_method_body);
					variable_names.reject(_position_method_body);
				}
				else if(_state==SUCCESS) {
					class_names.accept(_position_method_body);
					class_variable_names.accept(_position_method_body);
					variable_names.accept(_position_method_body);
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else {
				_position=brace_2.get(_position)+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
			}
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_body(method_body)");
				_furthestPosition=_position;
			}
		}
	}
}