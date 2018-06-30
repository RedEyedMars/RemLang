package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.type_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class statement_context extends type_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public statement_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public statement_context() {
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
	public void parse_statement_as_quote() {
		int _position_statement_as_quote = -1;
		Token.Parsed _token_statement_as_quote = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_statement_as_quote=_position;
		_token_statement_as_quote=_token;
		_token=new Tokens.Rule.StatementAsQuoteToken();
		if(_position+2-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='\'') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='\'') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_6.SYNTAX);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \'\'");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_quote(statement_as_quote)");
				_furthestPosition=_position;
			}
			_position=_position_statement_as_quote;
		}
		else {
			parse_body_statement();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_quote(statement_as_quote)");
					_furthestPosition=_position;
				}
				_position=_position_statement_as_quote;
			}
			else {
				if(_position+2-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='\'') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='\'') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_6.SYNTAX);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \'\'");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_quote(statement_as_quote)");
						_furthestPosition=_position;
					}
					_position=_position_statement_as_quote;
				}
				else {
				}
			}
		}
		if(_state==SUCCESS) {
			_token_statement_as_quote.add(_position_statement_as_quote,_token);
		}
		_token=_token_statement_as_quote;
		if(_state==FAILED) {
			class_names.reject(_position_statement_as_quote);
			class_variable_names.reject(_position_statement_as_quote);
			variable_names.reject(_position_statement_as_quote);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_statement_as_quote);
			class_variable_names.accept(_position_statement_as_quote);
			variable_names.accept(_position_statement_as_quote);
		}
	}
	public void parse_statement_as_braced() {
		int _position_statement_as_braced = -1;
		Token.Parsed _token_statement_as_braced = null;
		int _position_body_statement = -1;
		int _length_statement_as_braced_brace = _inputLength;
		if(brace_3.containsKey(_position)) {
			class_variable_names=class_variable_names.push(_position,_pass);
			variable_names=variable_names.push(_position,_pass);
			_inputLength=brace_3.get(_position);
			int _position_statement_as_braced_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			class_names.start(_position);
			class_variable_names.start(_position);
			variable_names.start(_position);
			_position_statement_as_braced=_position;
			_token_statement_as_braced=_token;
			_token=new Tokens.Rule.StatementAsBracedToken();
			_position_body_statement=_position;
			if(_state==SUCCESS&&!_recursion_protection_body_statement_2.contains(_position)) {
				_recursion_protection_body_statement_2.add(_position);
				parse_body_statement();
				_recursion_protection_body_statement_2.remove(_position_body_statement);
			}
			else {
				_state=FAILED;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_braced(statement_as_braced)");
					_furthestPosition=_position;
				}
				_position=_position_statement_as_braced;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_statement_as_braced.add(_position_statement_as_braced,_token);
			}
			_token=_token_statement_as_braced;
			if(_state==SUCCESS&&brace_3.get(_position_statement_as_braced_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("statement_as_braced",_position,_lineNumberRanges));
				_position=brace_3.get(_position_statement_as_braced_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_statement_as_braced_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			if(_state==FAILED) {
				class_names.reject(_position_statement_as_braced);
				class_variable_names.reject(_position_statement_as_braced);
				variable_names.reject(_position_statement_as_braced);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_statement_as_braced);
				class_variable_names.accept(_position_statement_as_braced);
				variable_names.accept(_position_statement_as_braced);
			}
			class_variable_names=class_variable_names.pop();
			variable_names=variable_names.pop();
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_braced(statement_as_braced)");
				_furthestPosition=_position;
			}
		}
	}
	public void parse_statement_as_string() {
		int _position_statement_as_string = -1;
		Token.Parsed _token_statement_as_string = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_statement_as_string=_position;
		_token_statement_as_string=_token;
		_token=new Tokens.Rule.StatementAsStringToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='\'') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_7.SYNTAX);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \'");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_string(statement_as_string)");
				_furthestPosition=_position;
			}
			_position=_position_statement_as_string;
		}
		else {
			parse_body_statement();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_string(statement_as_string)");
					_furthestPosition=_position;
				}
				_position=_position_statement_as_string;
			}
			else {
				if(_position+1-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='\'') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_7.SYNTAX);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \'");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_string(statement_as_string)");
						_furthestPosition=_position;
					}
					_position=_position_statement_as_string;
				}
				else {
				}
			}
		}
		if(_state==SUCCESS) {
			_token_statement_as_string.add(_position_statement_as_string,_token);
		}
		_token=_token_statement_as_string;
		if(_state==FAILED) {
			class_names.reject(_position_statement_as_string);
			class_variable_names.reject(_position_statement_as_string);
			variable_names.reject(_position_statement_as_string);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_statement_as_string);
			class_variable_names.accept(_position_statement_as_string);
			variable_names.accept(_position_statement_as_string);
		}
	}
	public void parse_statement_as_char() {
		int _position_statement_as_char = -1;
		Token.Parsed _token_statement_as_char = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_statement_as_char=_position;
		_token_statement_as_char=_token;
		_token=new Tokens.Rule.StatementAsCharToken();
		if(_position+5-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='c') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='h') {
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='a') {
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='r') {
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='\\') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_8.SYNTAX);
			_position=_position+5;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain char\\");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_char(statement_as_char)");
				_furthestPosition=_position;
			}
			_position=_position_statement_as_char;
		}
		else {
			int _position_regex_6 = _position;
			if(_position<_inputLength) {
				++_position;
			}
			else {
				_state=FAILED;
			}
			if(_state==SUCCESS) {
				_token.add(_position_regex_6,new Tokens.Plain.Value(_input.substring(_position_regex_6,_position)));
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
					_furthestPosition=_position;
				}
				_position=_position_regex_6;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_char(statement_as_char)");
					_furthestPosition=_position;
				}
				_position=_position_statement_as_char;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token_statement_as_char.add(_position_statement_as_char,_token);
		}
		_token=_token_statement_as_char;
		if(_state==FAILED) {
			class_names.reject(_position_statement_as_char);
			class_variable_names.reject(_position_statement_as_char);
			variable_names.reject(_position_statement_as_char);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_statement_as_char);
			class_variable_names.accept(_position_statement_as_char);
			variable_names.accept(_position_statement_as_char);
		}
	}
	public void parse_statement_as_method() {
		int _position_statement_as_method = -1;
		Token.Parsed _token_statement_as_method = null;
		int _length_statement_as_method_brace = _inputLength;
		if(brace_6.containsKey(_position)) {
			class_variable_names=class_variable_names.push(_position,_pass);
			variable_names=variable_names.push(_position,_pass);
			_inputLength=brace_6.get(_position);
			int _position_statement_as_method_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			class_names.start(_position);
			class_variable_names.start(_position);
			variable_names.start(_position);
			_position_statement_as_method=_position;
			_token_statement_as_method=_token;
			_token=new Tokens.Rule.StatementAsMethodToken();
			parse_body_statement();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_method(statement_as_method)");
					_furthestPosition=_position;
				}
				_position=_position_statement_as_method;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_statement_as_method.add(_position_statement_as_method,_token);
			}
			_token=_token_statement_as_method;
			if(_state==SUCCESS&&brace_6.get(_position_statement_as_method_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("statement_as_method",_position,_lineNumberRanges));
				_position=brace_6.get(_position_statement_as_method_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_statement_as_method_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			if(_state==FAILED) {
				class_names.reject(_position_statement_as_method);
				class_variable_names.reject(_position_statement_as_method);
				variable_names.reject(_position_statement_as_method);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_statement_as_method);
				class_variable_names.accept(_position_statement_as_method);
				variable_names.accept(_position_statement_as_method);
			}
			class_variable_names=class_variable_names.pop();
			variable_names=variable_names.pop();
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"statement_as_method(statement_as_method)");
				_furthestPosition=_position;
			}
		}
	}
}