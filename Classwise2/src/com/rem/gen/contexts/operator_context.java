package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.variable_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class operator_context extends variable_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public operator_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public operator_context() {
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
	public void parse_OPERATOR() {
		int _position_OPERATOR = -1;
		Token.Parsed _token_OPERATOR = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_OPERATOR=_position;
		_token_OPERATOR=_token;
		_token=new Tokens.Rule.OPERATORToken();
		if(_position+2-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='%') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='%') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_0.SYNTAX);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain %%");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"OPERATOR(OPERATOR)");
				_furthestPosition=_position;
			}
			_position=_position_OPERATOR;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_OPERATOR.add(_position_OPERATOR,_token);
		}
		_token=_token_OPERATOR;
		if(_state==FAILED) {
			class_names.reject(_position_OPERATOR);
			class_variable_names.reject(_position_OPERATOR);
			variable_names.reject(_position_OPERATOR);
			_state=SUCCESS;
			_position_OPERATOR=_position;
			_token_OPERATOR=_token;
			_token=new Tokens.Rule.OPERATORToken();
			int _position_regex_2 = _position;
			if(_position<_inputLength) {
				if(_inputArray[_position]!='a'&&_inputArray[_position]!='b'&&_inputArray[_position]!='c'&&_inputArray[_position]!='d'&&_inputArray[_position]!='e'&&_inputArray[_position]!='f'&&_inputArray[_position]!='g'&&_inputArray[_position]!='h'&&_inputArray[_position]!='i'&&_inputArray[_position]!='j'&&_inputArray[_position]!='k'&&_inputArray[_position]!='l'&&_inputArray[_position]!='m'&&_inputArray[_position]!='n'&&_inputArray[_position]!='o'&&_inputArray[_position]!='p'&&_inputArray[_position]!='q'&&_inputArray[_position]!='r'&&_inputArray[_position]!='s'&&_inputArray[_position]!='t'&&_inputArray[_position]!='u'&&_inputArray[_position]!='v'&&_inputArray[_position]!='w'&&_inputArray[_position]!='x'&&_inputArray[_position]!='y'&&_inputArray[_position]!='z'&&_inputArray[_position]!='A'&&_inputArray[_position]!='B'&&_inputArray[_position]!='C'&&_inputArray[_position]!='D'&&_inputArray[_position]!='E'&&_inputArray[_position]!='F'&&_inputArray[_position]!='G'&&_inputArray[_position]!='H'&&_inputArray[_position]!='I'&&_inputArray[_position]!='J'&&_inputArray[_position]!='K'&&_inputArray[_position]!='L'&&_inputArray[_position]!='M'&&_inputArray[_position]!='N'&&_inputArray[_position]!='O'&&_inputArray[_position]!='P'&&_inputArray[_position]!='Q'&&_inputArray[_position]!='R'&&_inputArray[_position]!='S'&&_inputArray[_position]!='T'&&_inputArray[_position]!='U'&&_inputArray[_position]!='V'&&_inputArray[_position]!='W'&&_inputArray[_position]!='X'&&_inputArray[_position]!='Y'&&_inputArray[_position]!='Z'&&_inputArray[_position]!='0'&&_inputArray[_position]!='1'&&_inputArray[_position]!='2'&&_inputArray[_position]!='3'&&_inputArray[_position]!='4'&&_inputArray[_position]!='5'&&_inputArray[_position]!='6'&&_inputArray[_position]!='7'&&_inputArray[_position]!='8'&&_inputArray[_position]!='9'&&_inputArray[_position]!='_'&&_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'&&_inputArray[_position]!='('&&_inputArray[_position]!=')'&&_inputArray[_position]!='{'&&_inputArray[_position]!='}'&&_inputArray[_position]!='['&&_inputArray[_position]!=']'&&_inputArray[_position]!=';'&&_inputArray[_position]!='\"'&&_inputArray[_position]!='\''&&_inputArray[_position]!='`'&&_inputArray[_position]!=','&&_inputArray[_position]!='\\'&&_inputArray[_position]!='\\'&&_inputArray[_position]!=':') {
					++_position;
				}
				else {
					_state=FAILED;
				}
			}
			else {
				_state=FAILED;
			}
			if(_position<_inputLength) {
				if(_inputArray[_position]!='a'&&_inputArray[_position]!='b'&&_inputArray[_position]!='c'&&_inputArray[_position]!='d'&&_inputArray[_position]!='e'&&_inputArray[_position]!='f'&&_inputArray[_position]!='g'&&_inputArray[_position]!='h'&&_inputArray[_position]!='i'&&_inputArray[_position]!='j'&&_inputArray[_position]!='k'&&_inputArray[_position]!='l'&&_inputArray[_position]!='m'&&_inputArray[_position]!='n'&&_inputArray[_position]!='o'&&_inputArray[_position]!='p'&&_inputArray[_position]!='q'&&_inputArray[_position]!='r'&&_inputArray[_position]!='s'&&_inputArray[_position]!='t'&&_inputArray[_position]!='u'&&_inputArray[_position]!='v'&&_inputArray[_position]!='w'&&_inputArray[_position]!='x'&&_inputArray[_position]!='y'&&_inputArray[_position]!='z'&&_inputArray[_position]!='A'&&_inputArray[_position]!='B'&&_inputArray[_position]!='C'&&_inputArray[_position]!='D'&&_inputArray[_position]!='E'&&_inputArray[_position]!='F'&&_inputArray[_position]!='G'&&_inputArray[_position]!='H'&&_inputArray[_position]!='I'&&_inputArray[_position]!='J'&&_inputArray[_position]!='K'&&_inputArray[_position]!='L'&&_inputArray[_position]!='M'&&_inputArray[_position]!='N'&&_inputArray[_position]!='O'&&_inputArray[_position]!='P'&&_inputArray[_position]!='Q'&&_inputArray[_position]!='R'&&_inputArray[_position]!='S'&&_inputArray[_position]!='T'&&_inputArray[_position]!='U'&&_inputArray[_position]!='V'&&_inputArray[_position]!='W'&&_inputArray[_position]!='X'&&_inputArray[_position]!='Y'&&_inputArray[_position]!='Z'&&_inputArray[_position]!='0'&&_inputArray[_position]!='1'&&_inputArray[_position]!='2'&&_inputArray[_position]!='3'&&_inputArray[_position]!='4'&&_inputArray[_position]!='5'&&_inputArray[_position]!='6'&&_inputArray[_position]!='7'&&_inputArray[_position]!='8'&&_inputArray[_position]!='9'&&_inputArray[_position]!='_'&&_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'&&_inputArray[_position]!='('&&_inputArray[_position]!=')'&&_inputArray[_position]!='{'&&_inputArray[_position]!='}'&&_inputArray[_position]!='['&&_inputArray[_position]!=']'&&_inputArray[_position]!=';'&&_inputArray[_position]!='\"'&&_inputArray[_position]!='\''&&_inputArray[_position]!='`'&&_inputArray[_position]!=','&&_inputArray[_position]!='\\'&&_inputArray[_position]!='\\') {
					++_position;
				}
				else {
					_state=FAILED;
				}
			}
			else {
				_state=FAILED;
			}
			if(_state==SUCCESS) {
				_token.add(_position_regex_2,new Tokens.Plain.Exact(_input.substring(_position_regex_2,_position)));
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z0-9_\\\\s(){}[];\\\"\\\'`,\\\\\\\\:][a-zA-Z0-9_\\\\s(){}[];\\\"\\\'`,\\\\\\\\]");
					_furthestPosition=_position;
				}
				_position=_position_regex_2;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"OPERATOR(OPERATOR)");
					_furthestPosition=_position;
				}
				_position=_position_OPERATOR;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_OPERATOR.add(_position_OPERATOR,_token);
			}
			_token=_token_OPERATOR;
			if(_state==FAILED) {
				class_names.reject(_position_OPERATOR);
				class_variable_names.reject(_position_OPERATOR);
				variable_names.reject(_position_OPERATOR);
				_state=SUCCESS;
				_position_OPERATOR=_position;
				_token_OPERATOR=_token;
				_token=new Tokens.Rule.OPERATORToken();
				int _position_regex_3 = _position;
				if(_position<_inputLength) {
					if(_inputArray[_position]!='a'&&_inputArray[_position]!='b'&&_inputArray[_position]!='c'&&_inputArray[_position]!='d'&&_inputArray[_position]!='e'&&_inputArray[_position]!='f'&&_inputArray[_position]!='g'&&_inputArray[_position]!='h'&&_inputArray[_position]!='i'&&_inputArray[_position]!='j'&&_inputArray[_position]!='k'&&_inputArray[_position]!='l'&&_inputArray[_position]!='m'&&_inputArray[_position]!='n'&&_inputArray[_position]!='o'&&_inputArray[_position]!='p'&&_inputArray[_position]!='q'&&_inputArray[_position]!='r'&&_inputArray[_position]!='s'&&_inputArray[_position]!='t'&&_inputArray[_position]!='u'&&_inputArray[_position]!='v'&&_inputArray[_position]!='w'&&_inputArray[_position]!='x'&&_inputArray[_position]!='y'&&_inputArray[_position]!='z'&&_inputArray[_position]!='A'&&_inputArray[_position]!='B'&&_inputArray[_position]!='C'&&_inputArray[_position]!='D'&&_inputArray[_position]!='E'&&_inputArray[_position]!='F'&&_inputArray[_position]!='G'&&_inputArray[_position]!='H'&&_inputArray[_position]!='I'&&_inputArray[_position]!='J'&&_inputArray[_position]!='K'&&_inputArray[_position]!='L'&&_inputArray[_position]!='M'&&_inputArray[_position]!='N'&&_inputArray[_position]!='O'&&_inputArray[_position]!='P'&&_inputArray[_position]!='Q'&&_inputArray[_position]!='R'&&_inputArray[_position]!='S'&&_inputArray[_position]!='T'&&_inputArray[_position]!='U'&&_inputArray[_position]!='V'&&_inputArray[_position]!='W'&&_inputArray[_position]!='X'&&_inputArray[_position]!='Y'&&_inputArray[_position]!='Z'&&_inputArray[_position]!='0'&&_inputArray[_position]!='1'&&_inputArray[_position]!='2'&&_inputArray[_position]!='3'&&_inputArray[_position]!='4'&&_inputArray[_position]!='5'&&_inputArray[_position]!='6'&&_inputArray[_position]!='7'&&_inputArray[_position]!='8'&&_inputArray[_position]!='9'&&_inputArray[_position]!='_'&&_inputArray[_position]!=' '&&_inputArray[_position]!='\t'&&_inputArray[_position]!='\r'&&_inputArray[_position]!='\n'&&_inputArray[_position]!='('&&_inputArray[_position]!=')'&&_inputArray[_position]!='{'&&_inputArray[_position]!='}'&&_inputArray[_position]!='['&&_inputArray[_position]!=']'&&_inputArray[_position]!=';'&&_inputArray[_position]!='\"'&&_inputArray[_position]!='\''&&_inputArray[_position]!='`'&&_inputArray[_position]!=','&&_inputArray[_position]!='\\'&&_inputArray[_position]!='\\'&&_inputArray[_position]!='%') {
						++_position;
					}
					else {
						_state=FAILED;
					}
				}
				else {
					_state=FAILED;
				}
				if(_state==SUCCESS) {
					_token.add(_position_regex_3,new Tokens.Plain.Exact(_input.substring(_position_regex_3,_position)));
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-zA-Z0-9_\\\\s(){}[];\\\"\\\'`,\\\\\\\\%]");
						_furthestPosition=_position;
					}
					_position=_position_regex_3;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"OPERATOR(OPERATOR)");
						_furthestPosition=_position;
					}
					_position=_position_OPERATOR;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_OPERATOR.add(_position_OPERATOR,_token);
				}
				_token=_token_OPERATOR;
				if(_state==FAILED) {
					class_names.reject(_position_OPERATOR);
					class_variable_names.reject(_position_OPERATOR);
					variable_names.reject(_position_OPERATOR);
					_state=SUCCESS;
					_position_OPERATOR=_position;
					_token_OPERATOR=_token;
					_token=new Tokens.Rule.OPERATORToken();
					if(_position+11-1 >=_inputLength) {
						_state=FAILED;
					}
					else {
						if(_inputArray[_position+0]!='i') {
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='n') {
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='s') {
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='t') {
							_state=FAILED;
						}
						if(_inputArray[_position+4]!='a') {
							_state=FAILED;
						}
						if(_inputArray[_position+5]!='n') {
							_state=FAILED;
						}
						if(_inputArray[_position+6]!='c') {
							_state=FAILED;
						}
						if(_inputArray[_position+7]!='e') {
							_state=FAILED;
						}
						if(_inputArray[_position+8]!='o') {
							_state=FAILED;
						}
						if(_inputArray[_position+9]!='f') {
							_state=FAILED;
						}
						if(_inputArray[_position+10]!=' ') {
							_state=FAILED;
						}
					}
					if(_state==SUCCESS) {
						_token.add(_position,Tokens.Syntax.syntax_1.instance);
						_position=_position+11;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
							++_position;
						}
					}
					else if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain instanceof ");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"OPERATOR(OPERATOR)");
							_furthestPosition=_position;
						}
						_position=_position_OPERATOR;
					}
					else {
					}
					if(_state==SUCCESS) {
						_token_OPERATOR.add(_position_OPERATOR,_token);
					}
					_token=_token_OPERATOR;
					if(_state==FAILED) {
						class_names.reject(_position_OPERATOR);
						class_variable_names.reject(_position_OPERATOR);
						variable_names.reject(_position_OPERATOR);
					}
					else if(_state==SUCCESS) {
						class_names.accept(_position_OPERATOR);
						class_variable_names.accept(_position_OPERATOR);
						variable_names.accept(_position_OPERATOR);
					}
				}
			}
		}
	}
}