package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.comments_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class template_context extends comments_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public template_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public template_context() {
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
	public void parse_template_parameters(){
		int _position_template_parameters = -1;
		Token.Parsed _token_template_parameters = null;
		int _position_all_type_name = -1;
		Token.Parsed _token_all_type_name = null;
		int _length_template_parameters_brace = _inputLength;
		if(brace_4.containsKey(_position)){
			if(_pass==Parser.SECOND_PASS){
				class_variable_names=class_variable_names.push();
			}
			if(_pass==Parser.SECOND_PASS){
				variable_names=variable_names.push();
			}
			if(_pass==SECOND_PASS){
				_inputLength=brace_4.get(_position);
				int _position_template_parameters_brace = _position;
				_position+=1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				class_names.start(_position);
				class_variable_names.start(_position);
				variable_names.start(_position);
				_position_template_parameters=_position;
				_token_template_parameters=_token;
				_token=new Tokens.Rule.TemplateParametersToken();
				_token_all_type_name=_token;
				_token=new Tokens.Name.TemplateParameterToken();
				_position_all_type_name=_position;
				parse_all_type_name();
				if(_state==SUCCESS){
					_token_all_type_name.add(_position_all_type_name,_token);
				}
				_token=_token_all_type_name;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
						_furthestPosition=_position;
					}
					_position=_position_template_parameters;
				}
				else{
					int _state_17 = _state;
					while(_position<_inputLength){
						parse__anonymous_2();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_17==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
							_furthestPosition=_position;
						}
						_position=_position_template_parameters;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token_template_parameters.add(_position_template_parameters,_token);
				}
				_token=_token_template_parameters;
				if(_state==SUCCESS&&brace_4.get(_position_template_parameters_brace)==_position){
					_position+=1;
				}
				else{
					_state=SUCCESS;
					_result_acceptor.add(_result);
					_result_acceptor.add(new Parser.Result.Fail.EOB(_position,_lineNumberRanges));
					_position=brace_4.get(_position_template_parameters_brace)+1;
					_succeedOnEnd=false;
				}
				_inputLength=_length_template_parameters_brace;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
				if(_state==FAILED){
					class_names.reject(_position_template_parameters);
					class_variable_names.reject(_position_template_parameters);
					variable_names.reject(_position_template_parameters);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position_template_parameters);
					class_variable_names.accept(_position_template_parameters);
					variable_names.accept(_position_template_parameters);
				}
				class_variable_names=class_variable_names.pop();
				variable_names=variable_names.pop();
			}
			else{
				_position=brace_4.get(_position)+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
		}
		else{
			_state=FAILED;
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(template_parameters)");
				_furthestPosition=_position;
			}
		}
	}
}