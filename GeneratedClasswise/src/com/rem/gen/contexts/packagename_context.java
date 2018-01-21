package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.inner_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class packagename_context extends inner_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public packagename_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public packagename_context() {
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
	public void parse_packageName(){
		int _position_packageName = -1;
		Token.Parsed _token_packageName = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_packageName=_position;
		_token_packageName=_token;
		_token=new Tokens.Rule.PackageNameToken();
		int _state_23 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='\\'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_5.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\");
				_furthestPosition=_position;
			}
		}
		if(_state_23==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
				_furthestPosition=_position;
			}
			_position=_position_packageName;
		}
		else{
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
					_furthestPosition=_position;
				}
				_position=_position_packageName;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token_packageName.add(_position_packageName,_token);
		}
		_token=_token_packageName;
		if(_state==FAILED){
			class_names.reject(_position_packageName);
			class_variable_names.reject(_position_packageName);
			variable_names.reject(_position_packageName);
			_state=SUCCESS;
			_position_packageName=_position;
			_token_packageName=_token;
			_token=new Tokens.Rule.PackageNameToken();
			parse_quote();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
					_furthestPosition=_position;
				}
				_position=_position_packageName;
			}
			else{
			}
			if(_state==SUCCESS){
				_token_packageName.add(_position_packageName,_token);
			}
			_token=_token_packageName;
			if(_state==FAILED){
				class_names.reject(_position_packageName);
				class_variable_names.reject(_position_packageName);
				variable_names.reject(_position_packageName);
				_state=SUCCESS;
				_position_packageName=_position;
				_token_packageName=_token;
				_token=new Tokens.Rule.PackageNameToken();
				parse_statement_as_string();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"packageName(packageName)");
						_furthestPosition=_position;
					}
					_position=_position_packageName;
				}
				else{
				}
				if(_state==SUCCESS){
					_token_packageName.add(_position_packageName,_token);
				}
				_token=_token_packageName;
				if(_state==FAILED){
					class_names.reject(_position_packageName);
					class_variable_names.reject(_position_packageName);
					variable_names.reject(_position_packageName);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position_packageName);
					class_variable_names.accept(_position_packageName);
					variable_names.accept(_position_packageName);
				}
			}
		}
	}
}