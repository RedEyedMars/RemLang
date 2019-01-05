package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.operator_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class anonymous_context extends operator_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public anonymous_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public anonymous_context() {
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
	public void parse_anonymous_class_name() {
		int _position_anonymous_class_name = -1;
		Token.Parsed _token_anonymous_class_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_anonymous_class_name=_position;
		_token_anonymous_class_name=_token;
		_token=new Tokens.Rule.AnonymousClassNameToken();
		parse_NAME();
		if(_state==SUCCESS) {
			String _value = _token.getLastValue();
			if(_value!=null) {
				class_names.add(_value);
			}
			_token.add(_position,new Tokens.Name.ClassNameToken(_value));
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class_name(anonymous_class_name)");
				_furthestPosition=_position;
			}
			_position=_position_anonymous_class_name;
		}
		else {
			int _state_27 = _state;
			while(_position<_inputLength) {
				parse__anonymous_9();
				if(_state==FAILED) {
					break;
				}
			}
			if(_state_27==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class_name(anonymous_class_name)");
					_furthestPosition=_position;
				}
				_position=_position_anonymous_class_name;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token_anonymous_class_name.addAll(_token);
			_token_anonymous_class_name.setValue(_token.getValue());
		}
		_token=_token_anonymous_class_name;
		if(_state==FAILED) {
			class_names.reject(_position_anonymous_class_name);
			class_variable_names.reject(_position_anonymous_class_name);
			variable_names.reject(_position_anonymous_class_name);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_anonymous_class_name);
			class_variable_names.accept(_position_anonymous_class_name);
			variable_names.accept(_position_anonymous_class_name);
		}
	}
	public void parse_anonymous_class() {
		int _position_anonymous_class = -1;
		Token.Parsed _token_anonymous_class = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_anonymous_class=_position;
		_token_anonymous_class=_token;
		_token=new Tokens.Rule.AnonymousClassToken();
		parse_anonymous_class_name();
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
				_furthestPosition=_position;
			}
			_position=_position_anonymous_class;
		}
		else {
			int _state_25 = _state;
			parse__anonymous_7();
			if(_state_25==SUCCESS&&_state==FAILED) {
				_state=SUCCESS;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(anonymous_class)");
					_furthestPosition=_position;
				}
				_position=_position_anonymous_class;
			}
			else {
			}
		}
		if(_state==SUCCESS) {
			_token_anonymous_class.add(_position_anonymous_class,_token);
		}
		_token=_token_anonymous_class;
		if(_state==FAILED) {
			class_names.reject(_position_anonymous_class);
			class_variable_names.reject(_position_anonymous_class);
			variable_names.reject(_position_anonymous_class);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_anonymous_class);
			class_variable_names.accept(_position_anonymous_class);
			variable_names.accept(_position_anonymous_class);
		}
	}
	public void parse_anonymous_classes() {
		int _position_anonymous_classes = -1;
		Token.Parsed _token_anonymous_classes = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_anonymous_classes=_position;
		_token_anonymous_classes=_token;
		_token=new Tokens.Rule.AnonymousClassesToken();
		if(_position+1-1 >=_inputLength) {
			_state=FAILED;
		}
		else {
			if(_inputArray[_position+0]!='<') {
				_state=FAILED;
			}
		}
		if(_state==SUCCESS) {
			_token.add(_position,Tokens.Syntax.syntax_9.SYNTAX);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
		}
		else if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain <");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED) {
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_classes(anonymous_classes)");
				_furthestPosition=_position;
			}
			_position=_position_anonymous_classes;
		}
		else {
			parse_packageName();
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_classes(anonymous_classes)");
					_furthestPosition=_position;
				}
				_position=_position_anonymous_classes;
			}
			else {
				int _state_23 = _state;
				while(_position<_inputLength) {
					parse__anonymous_6();
					if(_state==FAILED) {
						break;
					}
				}
				if(_state_23==SUCCESS&&_state==FAILED) {
					_state=SUCCESS;
				}
				if(_state==FAILED) {
					if(_position>=_furthestPosition) {
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_classes(anonymous_classes)");
						_furthestPosition=_position;
					}
					_position=_position_anonymous_classes;
				}
				else {
					int _state_24 = _state;
					while(_position<_inputLength) {
						parse_anonymous_class();
						if(_state==FAILED) {
							break;
						}
					}
					if(_state_24==SUCCESS&&_state==FAILED) {
						_state=SUCCESS;
					}
					if(_state==FAILED) {
						if(_position>=_furthestPosition) {
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_classes(anonymous_classes)");
							_furthestPosition=_position;
						}
						_position=_position_anonymous_classes;
					}
					else {
					}
				}
			}
		}
		if(_state==SUCCESS) {
			_token_anonymous_classes.add(_position_anonymous_classes,_token);
		}
		_token=_token_anonymous_classes;
		if(_state==FAILED) {
			class_names.reject(_position_anonymous_classes);
			class_variable_names.reject(_position_anonymous_classes);
			variable_names.reject(_position_anonymous_classes);
		}
		else if(_state==SUCCESS) {
			class_names.accept(_position_anonymous_classes);
			class_variable_names.accept(_position_anonymous_classes);
			variable_names.accept(_position_anonymous_classes);
		}
	}
}