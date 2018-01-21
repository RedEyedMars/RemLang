package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.class_body_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class as_context extends class_body_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public as_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public as_context() {
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
	public void parse_as_statement(){
		int _position_as_statement = -1;
		Token.Parsed _token_as_statement = null;
		int _length_as_statement_brace = _inputLength;
		if(brace_9.containsKey(_position)){
			class_variable_names=class_variable_names.push(_position,_pass);
			variable_names=variable_names.push(_position,_pass);
			_inputLength=brace_9.get(_position);
			int _position_as_statement_brace = _position;
			_position+=1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
			class_names.start(_position);
			class_variable_names.start(_position);
			variable_names.start(_position);
			_position_as_statement=_position;
			_token_as_statement=_token;
			_token=new Tokens.Rule.AsStatementToken();
			parse__anonymous_5();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(as_statement)");
					_furthestPosition=_position;
				}
				_position=_position_as_statement;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_as_statement.add(_position_as_statement,_token);
			}
			_token=_token_as_statement;
			if(_state==SUCCESS&&brace_9.get(_position_as_statement_brace)==_position){
				_position+=1;
			}
			else{
				_state=SUCCESS;
				_result_acceptor.add(_result);
				_result_acceptor.add(new Parser.Result.Fail.EOB(_position,_lineNumberRanges));
				_position=brace_9.get(_position_as_statement_brace)+1;
				_succeedOnEnd=false;
			}
			_inputLength=_length_as_statement_brace;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
			if(_state==FAILED){
				class_names.reject(_position_as_statement);
				class_variable_names.reject(_position_as_statement);
				variable_names.reject(_position_as_statement);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position_as_statement);
				class_variable_names.accept(_position_as_statement);
				variable_names.accept(_position_as_statement);
			}
			class_variable_names=class_variable_names.pop();
			variable_names=variable_names.pop();
		}
		else{
			_state=FAILED;
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(as_statement)");
				_furthestPosition=_position;
			}
		}
	}
}