package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.base_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class regex_context extends base_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public regex_context(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public regex_context() {
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
	public void parse_regex_special() {
		int _position_regex_special = -1;
		Token.Parsed _token_regex_special = null;
		list_names.start(_position);
		_position_regex_special=_position;
		_token_regex_special=_token;
		_token=new Tokens.Rule.RegexSpecialToken();
		if(_position+2-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='\\') {
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='d') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_24.number);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\d");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
				_furthestPosition=_position;
			}
			_position=_position_regex_special;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_regex_special.add(_position_regex_special,_token);
		}
		_token=_token_regex_special;
		if(_state==FAILED) {
			list_names.reject(_position_regex_special);
			_state=SUCCESS;
			_position_regex_special=_position;
			_token_regex_special=_token;
			_token=new Tokens.Rule.RegexSpecialToken();
			if(_position+2-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='\\') {
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='.') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_25.dot);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\.");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
					_furthestPosition=_position;
				}
				_position=_position_regex_special;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_regex_special.add(_position_regex_special,_token);
			}
			_token=_token_regex_special;
			if(_state==FAILED) {
				list_names.reject(_position_regex_special);
				_state=SUCCESS;
				_position_regex_special=_position;
				_token_regex_special=_token;
				_token=new Tokens.Rule.RegexSpecialToken();
				if(_position+2-1 >=_inputLength) {
					_state=FAILED;
				}
				else {
					if(_inputArray[_position+0]!='\\') {
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='s') {
						_state=FAILED;
					}
				}
				if(_state==SUCCESS) {
					_token.add(_position,Tokens.Syntax.syntax_26.whitespace);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				else if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\s");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
						_furthestPosition=_position;
					}
					_position=_position_regex_special;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_regex_special.add(_position_regex_special,_token);
				}
				_token=_token_regex_special;
				if(_state==FAILED) {
					list_names.reject(_position_regex_special);
					_state=SUCCESS;
					_position_regex_special=_position;
					_token_regex_special=_token;
					_token=new Tokens.Rule.RegexSpecialToken();
					if(_position+1-1 >=_inputLength) {
						_state=FAILED;
					}
					else {
						if(_inputArray[_position+0]!='\\') {
							_state=FAILED;
						}
					}
					if(_state==SUCCESS) {
						_token.add(_position,Tokens.Syntax.syntax_27.SYNTAX);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
							++_position;
						}
					}
					else if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
							_furthestPosition=_position;
						}
						_position=_position_regex_special;
					}
					else {
						int _position_regex_13 = _position;
						if(_position<_inputLength) {
							if(_inputArray[_position]=='a'||_inputArray[_position]=='b'||_inputArray[_position]=='c'||_inputArray[_position]=='d'||_inputArray[_position]=='e'||_inputArray[_position]=='f'||_inputArray[_position]=='g'||_inputArray[_position]=='h'||_inputArray[_position]=='i'||_inputArray[_position]=='j'||_inputArray[_position]=='k'||_inputArray[_position]=='l'||_inputArray[_position]=='m'||_inputArray[_position]=='n'||_inputArray[_position]=='o'||_inputArray[_position]=='p'||_inputArray[_position]=='q'||_inputArray[_position]=='r'||_inputArray[_position]=='s'||_inputArray[_position]=='t'||_inputArray[_position]=='u'||_inputArray[_position]=='v'||_inputArray[_position]=='w'||_inputArray[_position]=='x'||_inputArray[_position]=='y'||_inputArray[_position]=='z'||_inputArray[_position]=='\"'||_inputArray[_position]=='\'') {
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
							_token.add(_position_regex_13,new Tokens.Plain.Escaped(_input.substring(_position_regex_13,_position)));
							while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
								++_position;
							}
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"[a-z\\\"\\\']");
								_furthestPosition=_position;
							}
							_position=_position_regex_13;
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
								_furthestPosition=_position;
							}
							_position=_position_regex_special;
						}
						else {
						}
					}
					if(_state==SUCCESS) {
						_token_regex_special.add(_position_regex_special,_token);
					}
					_token=_token_regex_special;
					if(_state==FAILED) {
						list_names.reject(_position_regex_special);
						_state=SUCCESS;
						_position_regex_special=_position;
						_token_regex_special=_token;
						_token=new Tokens.Rule.RegexSpecialToken();
						if(_position+1-1 >=_inputLength) {
							_state=FAILED;
						}
						else {
							if(_inputArray[_position+0]!='\\') {
								_state=FAILED;
							}
						}
						if(_state==SUCCESS) {
							_token.add(_position,Tokens.Syntax.syntax_27.slash);
							_position=_position+1;
							while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
								++_position;
							}
						}
						else if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_special(regex_special)");
								_furthestPosition=_position;
							}
							_position=_position_regex_special;
						}
						else {
						}
						if(_state==SUCCESS) {
							_token_regex_special.add(_position_regex_special,_token);
						}
						_token=_token_regex_special;
						if(_state==FAILED) {
							list_names.reject(_position_regex_special);
						}
						else if(_state==SUCCESS) {
							list_names.accept(_position_regex_special);
						}
					}
				}
			}
		}
	}
	public void parse_regex() {
		int _position_regex = -1;
		Token.Parsed _token_regex = null;
		int _length_regex_brace = _inputLength;
		if(brace_3.containsKey(_position)) {
			_inputLength=brace_3.get(_position);
			int _position_regex_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			list_names.start(_position);
			_position_regex=_position;
			_token_regex=_token;
			_token=new Tokens.Rule.RegexToken();
			parse_regex_definition();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex(regex)");
					_furthestPosition=_position;
				}
				_position=_position_regex;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_regex.add(_position_regex,_token);
			}
			_token=_token_regex;
			if(_state==SUCCESS&&brace_3.get(_position_regex_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("regex",_position,_lineNumberRanges));
				_position=brace_3.get(_position_regex_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_regex_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			if(_state==FAILED) {
				list_names.reject(_position_regex);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position_regex);
			}
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex(regex)");
				_furthestPosition=_position;
			}
		}
	}
	public void parse_regex_group_definition() {
		int _position_regex_group_definition = -1;
		Token.Parsed _token_regex_group_definition = null;
		int _position_regex_definition = -1;
		Token.Parsed _token_regex_definition = null;
		int _length_regex_group_definition_brace = _inputLength;
		if(brace_2.containsKey(_position)) {
			_inputLength=brace_2.get(_position);
			int _position_regex_group_definition_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			list_names.start(_position);
			_position_regex_group_definition=_position;
			_token_regex_group_definition=_token;
			_token=new Tokens.Rule.RegexGroupDefinitionToken();
			_token_regex_definition=_token;
			_token=new Tokens.Name.RegexToken();
			_position_regex_definition=_position;
			if(_state==SUCCESS&&!_recursion_protection_regex_definition_2.contains(_position)) {
				_recursion_protection_regex_definition_2.add(_position);
				parse_regex_definition();
				_recursion_protection_regex_definition_2.remove(_position_regex_definition);
			}
			else {
				_state=FAILED;
			}
			if(_state==SUCCESS) {
				_token_regex_definition.add(_position_regex_definition,_token);
			}
			_token=_token_regex_definition;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_group_definition(regex_group_definition)");
					_furthestPosition=_position;
				}
				_position=_position_regex_group_definition;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_regex_group_definition.addAll(_token);
				_token_regex_group_definition.setValue(_token.getValue());
			}
			_token=_token_regex_group_definition;
			if(_state==SUCCESS&&brace_2.get(_position_regex_group_definition_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("regex_group_definition",_position,_lineNumberRanges));
				_position=brace_2.get(_position_regex_group_definition_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_regex_group_definition_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			if(_state==FAILED) {
				list_names.reject(_position_regex_group_definition);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position_regex_group_definition);
			}
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_group_definition(regex_group_definition)");
				_furthestPosition=_position;
			}
		}
	}
	public void parse_regex_option_definition() {
		int _position_regex_option_definition = -1;
		Token.Parsed _token_regex_option_definition = null;
		list_names.start(_position);
		_position_regex_option_definition=_position;
		_token_regex_option_definition=_token;
		_token=new Tokens.Rule.RegexOptionDefinitionToken();
		parse__anonymous_1();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(regex_option_definition)");
				_furthestPosition=_position;
			}
			_position=_position_regex_option_definition;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_regex_option_definition.addAll(_token);
			_token_regex_option_definition.setValue(_token.getValue());
		}
		_token=_token_regex_option_definition;
		if(_state==FAILED) {
			list_names.reject(_position_regex_option_definition);
			_state=SUCCESS;
			_position_regex_option_definition=_position;
			_token_regex_option_definition=_token;
			_token=new Tokens.Rule.RegexOptionDefinitionToken();
			parse_regex_special();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(regex_option_definition)");
					_furthestPosition=_position;
				}
				_position=_position_regex_option_definition;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_regex_option_definition.addAll(_token);
				_token_regex_option_definition.setValue(_token.getValue());
			}
			_token=_token_regex_option_definition;
			if(_state==FAILED) {
				list_names.reject(_position_regex_option_definition);
				_state=SUCCESS;
				_position_regex_option_definition=_position;
				_token_regex_option_definition=_token;
				_token=new Tokens.Rule.RegexOptionDefinitionToken();
				int _position_regex_9 = _position;
				if(_position<_inputLength) {
					++_position;
				}
				else {
					_state=FAILED;
				}
				if(_state==SUCCESS) {
					_token.add(_position_regex_9,new Tokens.Plain.StandAlone(_input.substring(_position_regex_9,_position)));
					while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
						++_position;
					}
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
						_furthestPosition=_position;
					}
					_position=_position_regex_9;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option_definition(regex_option_definition)");
						_furthestPosition=_position;
					}
					_position=_position_regex_option_definition;
				}
				else {
				}
				if(_state==SUCCESS) {
					_token_regex_option_definition.addAll(_token);
					_token_regex_option_definition.setValue(_token.getValue());
				}
				_token=_token_regex_option_definition;
				if(_state==FAILED) {
					list_names.reject(_position_regex_option_definition);
				}
				else if(_state==SUCCESS) {
					list_names.accept(_position_regex_option_definition);
				}
			}
		}
	}
	public void parse_regex_option() {
		int _position_regex_option = -1;
		Token.Parsed _token_regex_option = null;
		int _length_regex_option_brace = _inputLength;
		if(brace_3.containsKey(_position)) {
			_inputLength=brace_3.get(_position);
			int _position_regex_option_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			list_names.start(_position);
			_position_regex_option=_position;
			_token_regex_option=_token;
			_token=new Tokens.Rule.RegexOptionToken();
			int _state_13 = _state;
			if(_position+1-1 >=_inputLength) {
				_state=FAILED;
			}
			else {
				if(_inputArray[_position+0]!='^') {
					_state=FAILED;
				}
			}
			if(_state==SUCCESS) {
				_token.add(_position,Tokens.Syntax.syntax_4.negate);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
					++_position;
				}
			}
			else if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_13==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option(regex_option)");
					_furthestPosition=_position;
				}
				_position=_position_regex_option;
			}
			else {
				int _state_14 = _state;
				while(_position<_inputLength) {
					parse_regex_option_definition();
					if(_state==FAILED) {
						break;
					}
				}
				if(_state_14==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option(regex_option)");
						_furthestPosition=_position;
					}
					_position=_position_regex_option;
				}
				else {
				}
			}
			if(_state==SUCCESS) {
				_token_regex_option.addAll(_token);
				_token_regex_option.setValue(_token.getValue());
			}
			_token=_token_regex_option;
			if(_state==SUCCESS&&brace_3.get(_position_regex_option_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("regex_option",_position,_lineNumberRanges));
				_position=brace_3.get(_position_regex_option_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_regex_option_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
				++_position;
			}
			if(_state==FAILED) {
				list_names.reject(_position_regex_option);
			}
			else if(_state==SUCCESS) {
				list_names.accept(_position_regex_option);
			}
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_option(regex_option)");
				_furthestPosition=_position;
			}
		}
	}
	public void parse_regex_definition() {
		int _position_regex_definition = -1;
		Token.Parsed _token_regex_definition = null;
		list_names.start(_position);
		_position_regex_definition=_position;
		_token_regex_definition=_token;
		_token=new Tokens.Rule.RegexDefinitionToken();
		int _state_33 = _state;
		boolean _iteration_achieved_33 = false;
		while(_position<_inputLength) {
			parse_regex_element();
			if(_state==FAILED) {
				break;
			}
			else {
				_iteration_achieved_33=true;
			}
		}
		if(_iteration_achieved_33==false) {
			_state=FAILED;
		}
		else if(_state_33==SUCCESS&&_state==FAILED) {
			_state=SUCCESS;
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_definition(regex_definition)");
				_furthestPosition=_position;
			}
			_position=_position_regex_definition;
		}
		else {
		}
		if(_state==SUCCESS) {
			_token_regex_definition.addAll(_token);
			_token_regex_definition.setValue(_token.getValue());
		}
		_token=_token_regex_definition;
		if(_state==FAILED) {
			list_names.reject(_position_regex_definition);
		}
		else if(_state==SUCCESS) {
			list_names.accept(_position_regex_definition);
		}
	}
	public void parse_regex_element() {
		int _position_regex_element = -1;
		Token.Parsed _token_regex_element = null;
		int _position_regex_group_definition = -1;
		int _position_regex_option = -1;
		Token.Parsed _token_regex_group_definition = null;
		Token.Parsed _token_regex_option = null;
		list_names.start(_position);
		_position_regex_element=_position;
		_token_regex_element=_token;
		_token=new Tokens.Rule.RegexElementToken();
		_token_regex_option=_token;
		_token=new Tokens.Name.OptionToken();
		_position_regex_option=_position;
		parse_regex_option();
		if(_state==SUCCESS) {
			_token_regex_option.add(_position_regex_option,_token);
		}
		_token=_token_regex_option;
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
				_furthestPosition=_position;
			}
			_position=_position_regex_element;
		}
		else {
			int _state_34 = _state;
			parse__anonymous_22();
			if(_state_34==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
					_furthestPosition=_position;
				}
				_position=_position_regex_element;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token_regex_element.add(_position_regex_element,_token);
		}
		_token=_token_regex_element;
		if(_state==FAILED) {
			list_names.reject(_position_regex_element);
			_state=SUCCESS;
			_position_regex_element=_position;
			_token_regex_element=_token;
			_token=new Tokens.Rule.RegexElementToken();
			_token_regex_group_definition=_token;
			_token=new Tokens.Name.GroupToken();
			_position_regex_group_definition=_position;
			parse_regex_group_definition();
			if(_state==SUCCESS) {
				_token_regex_group_definition.add(_position_regex_group_definition,_token);
			}
			_token=_token_regex_group_definition;
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
					_furthestPosition=_position;
				}
				_position=_position_regex_element;
			}
			else {
				int _state_35 = _state;
				parse__anonymous_23();
				if(_state_35==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
						_furthestPosition=_position;
					}
					_position=_position_regex_element;
				}
				else {
				}
			}
			if(_state==SUCCESS) {
				_token_regex_element.add(_position_regex_element,_token);
			}
			_token=_token_regex_element;
			if(_state==FAILED) {
				list_names.reject(_position_regex_element);
				_state=SUCCESS;
				_position_regex_element=_position;
				_token_regex_element=_token;
				_token=new Tokens.Rule.RegexElementToken();
				parse_regex_special();
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
						_furthestPosition=_position;
					}
					_position=_position_regex_element;
				}
				else {
					int _state_36 = _state;
					parse__anonymous_24();
					if(_state_36==SUCCESS&&_state==FAILED) {
						_state=SUCCESS;
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
							_furthestPosition=_position;
						}
						_position=_position_regex_element;
					}
					else {
					}
				}
				if(_state==SUCCESS) {
					_token_regex_element.add(_position_regex_element,_token);
				}
				_token=_token_regex_element;
				if(_state==FAILED) {
					list_names.reject(_position_regex_element);
					_state=SUCCESS;
					_position_regex_element=_position;
					_token_regex_element=_token;
					_token=new Tokens.Rule.RegexElementToken();
					int _position_regex_12 = _position;
					if(_position<_inputLength) {
						++_position;
					}
					else {
						_state=FAILED;
					}
					if(_state==SUCCESS) {
						_token.add(_position_regex_12,new Tokens.Plain.Character(_input.substring(_position_regex_12,_position)));
						while(_position<_inputLength&&(false||_inputArray[_position]==' ')) {
							++_position;
						}
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".");
							_furthestPosition=_position;
						}
						_position=_position_regex_12;
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
							_furthestPosition=_position;
						}
						_position=_position_regex_element;
					}
					else {
						int _state_37 = _state;
						parse__anonymous_25();
						if(_state_37==SUCCESS&&_state==FAILED) {
							_state=SUCCESS;
						}
						if(_state==FAILED) {
							if(_position>=_furthestPosition) {
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"regex_element(regex_element)");
								_furthestPosition=_position;
							}
							_position=_position_regex_element;
						}
						else {
						}
					}
					if(_state==SUCCESS) {
						_token_regex_element.add(_position_regex_element,_token);
					}
					_token=_token_regex_element;
					if(_state==FAILED) {
						list_names.reject(_position_regex_element);
					}
					else if(_state==SUCCESS) {
						list_names.accept(_position_regex_element);
					}
				}
			}
		}
	}
}