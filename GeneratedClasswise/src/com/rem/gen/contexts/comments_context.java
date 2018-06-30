package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.packagename_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public abstract class comments_context extends packagename_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public comments_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public comments_context() {
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
	public void parse_comments() {
		int _position_comments = -1;
		Token.Parsed _token_comments = null;
		int _length_comments_brace = _inputLength;
		if(brace_1.containsKey(_position)) {
			class_variable_names=class_variable_names.push(_position,_pass);
			variable_names=variable_names.push(_position,_pass);
			_inputLength=brace_1.get(_position);
			int _position_comments_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			class_names.start(_position);
			class_variable_names.start(_position);
			variable_names.start(_position);
			_position_comments=_position;
			_token_comments=_token;
			_token=new Tokens.Rule.CommentsToken();
			int _position_regex_0 = _position;
			int _multiple_index_1 = 0;
			int _state_1 = _state;
			while(_position<_inputLength) {
				if(_position<_inputLength) {
					++_position;
				}
				else {
					_state=FAILED;
				}
				if(_state==FAILED) {
					break;
				}
				else {
					++_multiple_index_1;
				}
			}
			if(_state_1==SUCCESS) {
				_state=SUCCESS;
			}
			if(_state==SUCCESS) {
				_token.add(_position_regex_0,new Tokens.Plain.Comment(_input.substring(_position_regex_0,_position)));
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
					++_position;
				}
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,".*");
					_furthestPosition=_position;
				}
				_position=_position_regex_0;
			}
			if(_state==FAILED) {
				if(_position>=_furthestPosition) {
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"comments(comments)");
					_furthestPosition=_position;
				}
				_position=_position_comments;
			}
			else {
			}
			if(_state==SUCCESS) {
				_token_comments.add(_position_comments,_token);
			}
			_token=_token_comments;
			if(_state==SUCCESS&&brace_1.get(_position_comments_brace)==_position) {
				_position+=1;
			}
			else {
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB("comments",_position,_lineNumberRanges));
				_position=brace_1.get(_position_comments_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_comments_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')) {
				++_position;
			}
			if(_state==FAILED) {
				class_names.reject(_position_comments);
				class_variable_names.reject(_position_comments);
				variable_names.reject(_position_comments);
			}
			else if(_state==SUCCESS) {
				class_names.accept(_position_comments);
				class_variable_names.accept(_position_comments);
				variable_names.accept(_position_comments);
			}
			class_variable_names=class_variable_names.pop();
			variable_names=variable_names.pop();
		}
		else {
			_state=FAILED;
			if(_position>=_furthestPosition) {
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"comments(comments)");
				_furthestPosition=_position;
			}
		}
	}
}