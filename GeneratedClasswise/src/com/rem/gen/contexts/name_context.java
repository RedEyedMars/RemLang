package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.body_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class name_context extends body_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public name_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public name_context() {
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
	public void parse_name_atom() {
		int _position_name_atom = -1;
		Token.Parsed _token_name_atom = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_name_atom=_position;
		_token_name_atom=_token;
		_token=new Tokens.Rule.NameAtomToken();
		parse_statement_as_char();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_atom(name_atom)");
				_furthestPosition=_position;
			}
			_position=_position_name_atom;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_name_atom.add(_position_name_atom,_token);
		}
		_token=_token_name_atom;
		if(_state==FAILED) {
			class_names.reject(_position_name_atom);
			class_variable_names.reject(_position_name_atom);
			variable_names.reject(_position_name_atom);
			_state=SUCCESS;
			_position_name_atom=_position;
			_token_name_atom=_token;
			_token=new Tokens.Rule.NameAtomToken();
			parse_statement_as_method();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_atom(name_atom)");
					_furthestPosition=_position;
				}
				_position=_position_name_atom;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_name_atom.add(_position_name_atom,_token);
			}
			_token=_token_name_atom;
			if(_state==FAILED) {
				class_names.reject(_position_name_atom);
				class_variable_names.reject(_position_name_atom);
				variable_names.reject(_position_name_atom);
				_state=SUCCESS;
				_position_name_atom=_position;
				_token_name_atom=_token;
				_token=new Tokens.Rule.NameAtomToken();
				parse_statement_as_quote();
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_atom(name_atom)");
						_furthestPosition=_position;
					}
					_position=_position_name_atom;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_name_atom.add(_position_name_atom,_token);
				}
				_token=_token_name_atom;
				if(_state==FAILED) {
					class_names.reject(_position_name_atom);
					class_variable_names.reject(_position_name_atom);
					variable_names.reject(_position_name_atom);
					_state=SUCCESS;
					_position_name_atom=_position;
					_token_name_atom=_token;
					_token=new Tokens.Rule.NameAtomToken();
					parse_statement_as_string();
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_atom(name_atom)");
							_furthestPosition=_position;
						}
						_position=_position_name_atom;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token_name_atom.add(_position_name_atom,_token);
					}
					_token=_token_name_atom;
					if(_state==FAILED) {
						class_names.reject(_position_name_atom);
						class_variable_names.reject(_position_name_atom);
						variable_names.reject(_position_name_atom);
						_state=SUCCESS;
						_position_name_atom=_position;
						_token_name_atom=_token;
						_token=new Tokens.Rule.NameAtomToken();
						parse_quote();
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_atom(name_atom)");
								_furthestPosition=_position;
							}
							_position=_position_name_atom;
						}
						else {
						}
						if(_state==SUCCESS) {
							_token_name_atom.add(_position_name_atom,_token);
						}
						_token=_token_name_atom;
						if(_state==FAILED) {
							class_names.reject(_position_name_atom);
							class_variable_names.reject(_position_name_atom);
							variable_names.reject(_position_name_atom);
							_state=SUCCESS;
							_position_name_atom=_position;
							_token_name_atom=_token;
							_token=new Tokens.Rule.NameAtomToken();
							parse_NUMBER();
							if(_state==FAILED) {
								if(_position>=_furthestPosition) {
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_atom(name_atom)");
									_furthestPosition=_position;
								}
								_position=_position_name_atom;
							}
							else {
							}
							if(_state==SUCCESS) {
								_token_name_atom.add(_position_name_atom,_token);
							}
							_token=_token_name_atom;
							if(_state==FAILED) {
								class_names.reject(_position_name_atom);
								class_variable_names.reject(_position_name_atom);
								variable_names.reject(_position_name_atom);
								_state=SUCCESS;
								_position_name_atom=_position;
								_token_name_atom=_token;
								_token=new Tokens.Rule.NameAtomToken();
								if(_pass==FIRST_PASS) {
									String _result = _preparsed_NAME.get(_position);
									if(_result==null) {
										_state=FAILED;
									}
									else {
										Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
										_token.add(_position,_first_pass_token);
										_position+=_result.length();
										while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
											++_position;
										}
									}
								}
								else if(_pass==SECOND_PASS) {
									_list_name_result=_preparsed_NAME.get(_position);
									if(_list_name_result!=null&&variable_names.contains(_list_name_result)) {
										if(_position+_list_name_result.length()<_inputLength) {
											int _next_char = _inputArray[_position+_list_name_result.length()];
											if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )) {
												_state=FAILED;
											}
										}
										if(_state==SUCCESS) {
											_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
											_position+=_list_name_result.length();
											while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
												++_position;
											}
										}
									}
									else {
										_state=FAILED;
									}
									if(_state==FAILED) {
										if(_position>=_furthestPosition) {
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
											_furthestPosition=_position;
										}
									}
								}
								if(_state==FAILED) {
									if(_position>=_furthestPosition) {
										_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_atom(name_atom)");
										_furthestPosition=_position;
									}
									_position=_position_name_atom;
								}
								else {
								}
								if(_state==SUCCESS) {
									_token_name_atom.add(_position_name_atom,_token);
								}
								_token=_token_name_atom;
								if(_state==FAILED) {
									class_names.reject(_position_name_atom);
									class_variable_names.reject(_position_name_atom);
									variable_names.reject(_position_name_atom);
									_state=SUCCESS;
									_position_name_atom=_position;
									_token_name_atom=_token;
									_token=new Tokens.Rule.NameAtomToken();
									parse__anonymous_82();
									if(_state==FAILED) {
										if(_position>=_furthestPosition) {
											_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_atom(name_atom)");
											_furthestPosition=_position;
										}
										_position=_position_name_atom;
									}
									else {
									}
									if(_state==SUCCESS) {
										_token_name_atom.add(_position_name_atom,_token);
									}
									_token=_token_name_atom;
									if(_state==FAILED) {
										class_names.reject(_position_name_atom);
										class_variable_names.reject(_position_name_atom);
										variable_names.reject(_position_name_atom);
									}
									else if(_state==SUCCESS) {
										class_names.accept(_position_name_atom);
										class_variable_names.accept(_position_name_atom);
										variable_names.accept(_position_name_atom);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	public void parse_NAME() {
		int _position_NAME = -1;
		Token.Parsed _token_NAME = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_NAME=_position;
		_token_NAME=_token;
		_token=new Tokens.Rule.NAMEToken();
		int _position_regex_1 = _position;
		if(_position<_inputLength) {
			if(_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F'||_inputArray[_position]=='G'||_inputArray[_position]=='H'||_inputArray[_position]=='I'||_inputArray[_position]=='J'||_inputArray[_position]=='K'||_inputArray[_position]=='L'||_inputArray[_position]=='M'||_inputArray[_position]=='N'||_inputArray[_position]=='O'||_inputArray[_position]=='P'||_inputArray[_position]=='Q'||_inputArray[_position]=='R'||_inputArray[_position]=='S'||_inputArray[_position]=='T'||_inputArray[_position]=='U'||_inputArray[_position]=='V'||_inputArray[_position]=='W'||_inputArray[_position]=='X'||_inputArray[_position]=='Y'||_inputArray[_position]=='Z'||_inputArray[_position]=='_') {
				++_position;
			}
			else {
				_state=FAILED;
			}
		}
		else {
			_state=FAILED;
		}
		while(_position<_inputLength) {
			if(_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='A'||_inputArray[_position]=='B'||_inputArray[_position]=='C'||_inputArray[_position]=='D'||_inputArray[_position]=='E'||_inputArray[_position]=='F'||_inputArray[_position]=='G'||_inputArray[_position]=='H'||_inputArray[_position]=='I'||_inputArray[_position]=='J'||_inputArray[_position]=='K'||_inputArray[_position]=='L'||_inputArray[_position]=='M'||_inputArray[_position]=='N'||_inputArray[_position]=='O'||_inputArray[_position]=='P'||_inputArray[_position]=='Q'||_inputArray[_position]=='R'||_inputArray[_position]=='S'||_inputArray[_position]=='T'||_inputArray[_position]=='U'||_inputArray[_position]=='V'||_inputArray[_position]=='W'||_inputArray[_position]=='X'||_inputArray[_position]=='Y'||_inputArray[_position]=='Z'||_inputArray[_position]=='0'||_inputArray[_position]=='1'||_inputArray[_position]=='2'||_inputArray[_position]=='3'||_inputArray[_position]=='4'||_inputArray[_position]=='5'||_inputArray[_position]=='6'||_inputArray[_position]=='7'||_inputArray[_position]=='8'||_inputArray[_position]=='9'||_inputArray[_position]=='_') {
				++_position;
			}
			else {
				break;
			}
		}
		if(_state==SUCCESS) {
			_token.setValue(_input.substring(_position_regex_1,_position));
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z_][a-zA-Z0-9_]*");
				_furthestPosition=_position;
			}
			_position=_position_regex_1;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"NAME(NAME)");
				_furthestPosition=_position;
			}
			_position=_position_NAME;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_NAME.add(_position_NAME,_token);
		}
		_token=_token_NAME;
		if(_state==FAILED) {
			class_names.reject(_position_NAME);
			class_variable_names.reject(_position_NAME);
			variable_names.reject(_position_NAME);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_NAME);
			class_variable_names.accept(_position_NAME);
			variable_names.accept(_position_NAME);
		}
	}
	public void parse_name_var() {
		int _position_name_var = -1;
		Token.Parsed _token_name_var = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_name_var=_position;
		_token_name_var=_token;
		_token=new Tokens.Rule.NameVarToken();
		parse__anonymous_78();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
				_furthestPosition=_position;
			}
			_position=_position_name_var;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_name_var.add(_position_name_var,_token);
		}
		_token=_token_name_var;
		if(_state==FAILED) {
			class_names.reject(_position_name_var);
			class_variable_names.reject(_position_name_var);
			variable_names.reject(_position_name_var);
			_state=SUCCESS;
			_position_name_var=_position;
			_token_name_var=_token;
			_token=new Tokens.Rule.NameVarToken();
			parse_name_atom();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
					_furthestPosition=_position;
				}
				_position=_position_name_var;
			}
			else {
				int _state_77 = _state;
				while(_position<_inputLength) {
					parse__anonymous_81();
					if(_state==FAILED) {
						break;
					}
				}
				if(_state_77==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(name_var)");
						_furthestPosition=_position;
					}
					_position=_position_name_var;
				}
				else {
				}
			}
			if(_state==SUCCESS) {
				_token_name_var.add(_position_name_var,_token);
			}
			_token=_token_name_var;
			if(_state==FAILED) {
				class_names.reject(_position_name_var);
				class_variable_names.reject(_position_name_var);
				variable_names.reject(_position_name_var);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_name_var);
				class_variable_names.accept(_position_name_var);
				variable_names.accept(_position_name_var);
			}
		}
	}
}