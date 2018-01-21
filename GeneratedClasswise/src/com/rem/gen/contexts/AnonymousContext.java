package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import java.util.Set;
import java.util.List;
import java.util.Map;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Tokens;
import com.rem.gen.parser.Parser;

public class AnonymousContext extends Parser.Context{
	public AnonymousContext(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public AnonymousContext() {
	}
	public void parse__anonymous_88(){
		int _position__anonymous_88 = -1;
		Token.Parsed _token__anonymous_88 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_88=_position;
		_token__anonymous_88=_token;
		_token=new Tokens.Name.ExactToken();
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
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_88)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_88;
		}
		else{
			int _state_104 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_104==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_88)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_88;
			}
			else{
				parse__anonymous_89();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_88)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_88;
				}
				else{
					int _state_105 = _state;
					parse_template_parameters();
					if(_state_105==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_88)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_88;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_88.add(_position__anonymous_88,_token);
		}
		_token=_token__anonymous_88;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_88);
			class_variable_names.reject(_position__anonymous_88);
			variable_names.reject(_position__anonymous_88);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_88);
			class_variable_names.accept(_position__anonymous_88);
			variable_names.accept(_position__anonymous_88);
		}
	}
	public void parse__anonymous_87(){
		int _position__anonymous_87 = -1;
		Token.Parsed _token__anonymous_87 = null;
		int _position_type_var_element = -1;
		Token.Parsed _token_type_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_87=_position;
		_token__anonymous_87=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='+'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_56.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_87)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_87;
		}
		else{
			_token_type_var_element=_token;
			_token=new Tokens.Name.TypeVarToken();
			_position_type_var_element=_position;
			parse_type_var_element();
			if(_state==SUCCESS){
				_token_type_var_element.add(_position_type_var_element,_token);
			}
			_token=_token_type_var_element;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_87)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_87;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_87.addAll(_token);
			_token__anonymous_87.setValue(_token.getValue());
		}
		_token=_token__anonymous_87;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_87);
			class_variable_names.reject(_position__anonymous_87);
			variable_names.reject(_position__anonymous_87);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_87);
			class_variable_names.accept(_position__anonymous_87);
			variable_names.accept(_position__anonymous_87);
		}
	}
	public void parse__anonymous_89(){
		int _position__anonymous_89 = -1;
		Token.Parsed _token__anonymous_89 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_89=_position;
		_token__anonymous_89=_token;
		_token=new Token.Parsed("$ANON");
		if(_pass==FIRST_PASS){
			String _result = _preparsed_NAME.get(_position);
			if(_result==null){
				_state=FAILED;
			}
			else{
				Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
				_token.add(_position,_first_pass_token);
				_position+=_result.length();
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
		}
		else if(_pass==SECOND_PASS){
			_list_name_result=_preparsed_NAME.get(_position);
			if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
				if(_position+_list_name_result.length()<_inputLength){
					int _next_char = _inputArray[_position+_list_name_result.length()];
					if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
					_position+=_list_name_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
					_furthestPosition=_position;
				}
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_89)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_89;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_89.addAll(_token);
			_token__anonymous_89.setValue(_token.getValue());
		}
		_token=_token__anonymous_89;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_89);
			class_variable_names.reject(_position__anonymous_89);
			variable_names.reject(_position__anonymous_89);
			_state=SUCCESS;
			_position__anonymous_89=_position;
			_token__anonymous_89=_token;
			_token=new Token.Parsed("$ANON");
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_89)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_89;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_89.addAll(_token);
				_token__anonymous_89.setValue(_token.getValue());
			}
			_token=_token__anonymous_89;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_89);
				class_variable_names.reject(_position__anonymous_89);
				variable_names.reject(_position__anonymous_89);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_89);
				class_variable_names.accept(_position__anonymous_89);
				variable_names.accept(_position__anonymous_89);
			}
		}
	}
	public void parse__anonymous_80(){
		int _position__anonymous_80 = -1;
		Token.Parsed _token__anonymous_80 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_80=_position;
		_token__anonymous_80=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+2-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='-'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='>'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ->");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_80)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_80;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_80.addAll(_token);
			_token__anonymous_80.setValue(_token.getValue());
		}
		_token=_token__anonymous_80;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_80);
			class_variable_names.reject(_position__anonymous_80);
			variable_names.reject(_position__anonymous_80);
			_state=SUCCESS;
			_position__anonymous_80=_position;
			_token__anonymous_80=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+2-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='\\'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='>'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_58.__SYNTAX__);
				_position=_position+2;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain \\>");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_80)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_80;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_80.addAll(_token);
				_token__anonymous_80.setValue(_token.getValue());
			}
			_token=_token__anonymous_80;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_80);
				class_variable_names.reject(_position__anonymous_80);
				variable_names.reject(_position__anonymous_80);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_80);
				class_variable_names.accept(_position__anonymous_80);
				variable_names.accept(_position__anonymous_80);
			}
		}
	}
	public void parse__anonymous_82(){
		int _position__anonymous_82 = -1;
		Token.Parsed _token__anonymous_82 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_82=_position;
		_token__anonymous_82=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+2-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='-'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='>'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ->");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_82)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_82;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_82.addAll(_token);
			_token__anonymous_82.setValue(_token.getValue());
		}
		_token=_token__anonymous_82;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_82);
			class_variable_names.reject(_position__anonymous_82);
			variable_names.reject(_position__anonymous_82);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_82);
			class_variable_names.accept(_position__anonymous_82);
			variable_names.accept(_position__anonymous_82);
		}
	}
	public void parse__anonymous_81(){
		int _position__anonymous_81 = -1;
		Token.Parsed _token__anonymous_81 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_81=_position;
		_token__anonymous_81=_token;
		_token=new Tokens.Name.AsMethodToken();
		parse__anonymous_82();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_81)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_81;
		}
		else{
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_81)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_81;
			}
			else{
				int _state_96 = _state;
				parse_name_var();
				if(_state_96==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_81)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_81;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_81.add(_position__anonymous_81,_token);
		}
		_token=_token__anonymous_81;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_81);
			class_variable_names.reject(_position__anonymous_81);
			variable_names.reject(_position__anonymous_81);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_81);
			class_variable_names.accept(_position__anonymous_81);
			variable_names.accept(_position__anonymous_81);
		}
	}
	public void parse__anonymous_84(){
		int _position__anonymous_84 = -1;
		Token.Parsed _token__anonymous_84 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_84=_position;
		_token__anonymous_84=_token;
		_token=new Token.Parsed("$ANON");
		parse__anonymous_85();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_84)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_84;
		}
		else{
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='*'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_84)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_84;
			}
			else{
				int _state_99 = _state;
				parse_name_var();
				if(_state_99==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_84)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_84;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_84.addAll(_token);
			_token__anonymous_84.setValue(_token.getValue());
		}
		_token=_token__anonymous_84;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_84);
			class_variable_names.reject(_position__anonymous_84);
			variable_names.reject(_position__anonymous_84);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_84);
			class_variable_names.accept(_position__anonymous_84);
			variable_names.accept(_position__anonymous_84);
		}
	}
	public void parse__anonymous_83(){
		int _position__anonymous_83 = -1;
		Token.Parsed _token__anonymous_83 = null;
		int _position_type_var_element = -1;
		Token.Parsed _token_type_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_83=_position;
		_token__anonymous_83=_token;
		_token=new Tokens.Name.AccessMethodToken();
		int _state_97 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='$'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_57.ISTYPENAME);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain $");
				_furthestPosition=_position;
			}
		}
		if(_state_97==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_83)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_83;
		}
		else{
			int _state_98 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_98==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_83)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_83;
			}
			else{
				_token_type_var_element=_token;
				_token=new Tokens.Name.TypeVarToken();
				_position_type_var_element=_position;
				parse_type_var_element();
				if(_state==SUCCESS){
					_token_type_var_element.add(_position_type_var_element,_token);
				}
				_token=_token_type_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_83)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_83;
				}
				else{
					parse__anonymous_84();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_83)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_83;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_83.add(_position__anonymous_83,_token);
		}
		_token=_token__anonymous_83;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_83);
			class_variable_names.reject(_position__anonymous_83);
			variable_names.reject(_position__anonymous_83);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_83);
			class_variable_names.accept(_position__anonymous_83);
			variable_names.accept(_position__anonymous_83);
		}
	}
	public void parse__anonymous_86(){
		int _position__anonymous_86 = -1;
		Token.Parsed _token__anonymous_86 = null;
		int _position_type_var_element = -1;
		Token.Parsed _token_type_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_86=_position;
		_token__anonymous_86=_token;
		_token=new Tokens.Name.ConcatToken();
		int _state_100 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='$'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_57.ISTYPENAME);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain $");
				_furthestPosition=_position;
			}
		}
		if(_state_100==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_86)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_86;
		}
		else{
			int _state_101 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_101==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_86)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_86;
			}
			else{
				_token_type_var_element=_token;
				_token=new Tokens.Name.TypeVarToken();
				_position_type_var_element=_position;
				parse_type_var_element();
				if(_state==SUCCESS){
					_token_type_var_element.add(_position_type_var_element,_token);
				}
				_token=_token_type_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_86)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_86;
				}
				else{
					int _state_102 = _state;
					boolean _iteration_achieved_102 = false;
					while(_position<_inputLength){
						parse__anonymous_87();
						if(_state==FAILED){
							break;
						}
						else{
							_iteration_achieved_102=true;
						}
					}
					if(_iteration_achieved_102==false){
						_state=FAILED;
					}
					else if(_state_102==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_86)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_86;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_86.add(_position__anonymous_86,_token);
		}
		_token=_token__anonymous_86;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_86);
			class_variable_names.reject(_position__anonymous_86);
			variable_names.reject(_position__anonymous_86);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_86);
			class_variable_names.accept(_position__anonymous_86);
			variable_names.accept(_position__anonymous_86);
		}
	}
	public void parse__anonymous_85(){
		int _position__anonymous_85 = -1;
		Token.Parsed _token__anonymous_85 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_85=_position;
		_token__anonymous_85=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+2-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='-'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='>'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ->");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_85)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_85;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_85.addAll(_token);
			_token__anonymous_85.setValue(_token.getValue());
		}
		_token=_token__anonymous_85;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_85);
			class_variable_names.reject(_position__anonymous_85);
			variable_names.reject(_position__anonymous_85);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_85);
			class_variable_names.accept(_position__anonymous_85);
			variable_names.accept(_position__anonymous_85);
		}
	}
	public void parse__anonymous_77(){
		int _position__anonymous_77 = -1;
		Token.Parsed _token__anonymous_77 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_77=_position;
		_token__anonymous_77=_token;
		_token=new Tokens.Name.NAMEToken();
		parse_NUMBER();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_77)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_77;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_77.add(_position__anonymous_77,_token);
		}
		_token=_token__anonymous_77;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_77);
			class_variable_names.reject(_position__anonymous_77);
			variable_names.reject(_position__anonymous_77);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_77);
			class_variable_names.accept(_position__anonymous_77);
			variable_names.accept(_position__anonymous_77);
		}
	}
	public void parse__anonymous_76(){
		int _position__anonymous_76 = -1;
		Token.Parsed _token__anonymous_76 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_76=_position;
		_token__anonymous_76=_token;
		_token=new Token.Parsed("$ANON");
		parse_quote();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_76)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_76;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_76.addAll(_token);
			_token__anonymous_76.setValue(_token.getValue());
		}
		_token=_token__anonymous_76;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_76);
			class_variable_names.reject(_position__anonymous_76);
			variable_names.reject(_position__anonymous_76);
			_state=SUCCESS;
			_position__anonymous_76=_position;
			_token__anonymous_76=_token;
			_token=new Token.Parsed("$ANON");
			parse__anonymous_77();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_76)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_76;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_76.addAll(_token);
				_token__anonymous_76.setValue(_token.getValue());
			}
			_token=_token__anonymous_76;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_76);
				class_variable_names.reject(_position__anonymous_76);
				variable_names.reject(_position__anonymous_76);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_76);
				class_variable_names.accept(_position__anonymous_76);
				variable_names.accept(_position__anonymous_76);
			}
		}
	}
	public void parse__anonymous_79(){
		int _position__anonymous_79 = -1;
		Token.Parsed _token__anonymous_79 = null;
		int _position_type_var_element = -1;
		Token.Parsed _token_type_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_79=_position;
		_token__anonymous_79=_token;
		_token=new Token.Parsed("$ANON");
		parse__anonymous_80();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_79)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_79;
		}
		else{
			_token_type_var_element=_token;
			_token=new Tokens.Name.TypeVarToken();
			_position_type_var_element=_position;
			parse_type_var_element();
			if(_state==SUCCESS){
				_token_type_var_element.add(_position_type_var_element,_token);
			}
			_token=_token_type_var_element;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_79)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_79;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_79.addAll(_token);
			_token__anonymous_79.setValue(_token.getValue());
		}
		_token=_token__anonymous_79;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_79);
			class_variable_names.reject(_position__anonymous_79);
			variable_names.reject(_position__anonymous_79);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_79);
			class_variable_names.accept(_position__anonymous_79);
			variable_names.accept(_position__anonymous_79);
		}
	}
	public void parse__anonymous_78(){
		int _position__anonymous_78 = -1;
		Token.Parsed _token__anonymous_78 = null;
		int _position_type_var_element = -1;
		Token.Parsed _token_type_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_78=_position;
		_token__anonymous_78=_token;
		_token=new Tokens.Name.AccessMultiToken();
		int _state_92 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='$'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_57.ISTYPENAME);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain $");
				_furthestPosition=_position;
			}
		}
		if(_state_92==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_78;
		}
		else{
			int _state_93 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_93==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_78;
			}
			else{
				_token_type_var_element=_token;
				_token=new Tokens.Name.TypeVarToken();
				_position_type_var_element=_position;
				parse_type_var_element();
				if(_state==SUCCESS){
					_token_type_var_element.add(_position_type_var_element,_token);
				}
				_token=_token_type_var_element;
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_78;
				}
				else{
					int _state_94 = _state;
					boolean _iteration_achieved_94 = false;
					while(_position<_inputLength){
						parse__anonymous_79();
						if(_state==FAILED){
							break;
						}
						else{
							_iteration_achieved_94=true;
						}
					}
					if(_iteration_achieved_94==false){
						_state=FAILED;
					}
					else if(_state_94==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_78;
					}
					else{
						int _state_95 = _state;
						parse__anonymous_81();
						if(_state_95==SUCCESS&&_state==FAILED){
							_state=SUCCESS;
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var(_anonymous_78)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_78;
						}
						else{
						}
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_78.add(_position__anonymous_78,_token);
		}
		_token=_token__anonymous_78;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_78);
			class_variable_names.reject(_position__anonymous_78);
			variable_names.reject(_position__anonymous_78);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_78);
			class_variable_names.accept(_position__anonymous_78);
			variable_names.accept(_position__anonymous_78);
		}
	}
	public void parse__anonymous_71(){
		int _position__anonymous_71 = -1;
		Token.Parsed _token__anonymous_71 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_71=_position;
		_token__anonymous_71=_token;
		_token=new Tokens.Name.ExactToken();
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
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_71)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_71;
		}
		else{
			int _state_89 = _state;
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='^'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
					_furthestPosition=_position;
				}
			}
			if(_state_89==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_71)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_71;
			}
			else{
				parse__anonymous_72();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_71)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_71;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_71.add(_position__anonymous_71,_token);
		}
		_token=_token__anonymous_71;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_71);
			class_variable_names.reject(_position__anonymous_71);
			variable_names.reject(_position__anonymous_71);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_71);
			class_variable_names.accept(_position__anonymous_71);
			variable_names.accept(_position__anonymous_71);
		}
	}
	public void parse__anonymous_70(){
		int _position__anonymous_70 = -1;
		Token.Parsed _token__anonymous_70 = null;
		int _position_NUMBER = -1;
		Token.Parsed _token_NUMBER = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_70=_position;
		_token__anonymous_70=_token;
		_token=new Tokens.Name.ExactToken();
		_token_NUMBER=_token;
		_token=new Tokens.Name.NAMEToken();
		_position_NUMBER=_position;
		parse_NUMBER();
		if(_state==SUCCESS){
			_token_NUMBER.add(_position_NUMBER,_token);
		}
		_token=_token_NUMBER;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_70)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_70;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_70.add(_position__anonymous_70,_token);
		}
		_token=_token__anonymous_70;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_70);
			class_variable_names.reject(_position__anonymous_70);
			variable_names.reject(_position__anonymous_70);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_70);
			class_variable_names.accept(_position__anonymous_70);
			variable_names.accept(_position__anonymous_70);
		}
	}
	public void parse__anonymous_73(){
		int _position__anonymous_73 = -1;
		Token.Parsed _token__anonymous_73 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_73=_position;
		_token__anonymous_73=_token;
		_token=new Tokens.Name.VariableToken();
		int _state_90 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='^'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
				_furthestPosition=_position;
			}
		}
		if(_state_90==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_73)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_73;
		}
		else{
			parse__anonymous_74();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_73)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_73;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_73.add(_position__anonymous_73,_token);
		}
		_token=_token__anonymous_73;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_73);
			class_variable_names.reject(_position__anonymous_73);
			variable_names.reject(_position__anonymous_73);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_73);
			class_variable_names.accept(_position__anonymous_73);
			variable_names.accept(_position__anonymous_73);
		}
	}
	public void parse__anonymous_72(){
		int _position__anonymous_72 = -1;
		Token.Parsed _token__anonymous_72 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_72=_position;
		_token__anonymous_72=_token;
		_token=new Token.Parsed("$ANON");
		parse_quote();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_72)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_72;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_72.addAll(_token);
			_token__anonymous_72.setValue(_token.getValue());
		}
		_token=_token__anonymous_72;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_72);
			class_variable_names.reject(_position__anonymous_72);
			variable_names.reject(_position__anonymous_72);
			_state=SUCCESS;
			_position__anonymous_72=_position;
			_token__anonymous_72=_token;
			_token=new Token.Parsed("$ANON");
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_72)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_72;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_72.addAll(_token);
				_token__anonymous_72.setValue(_token.getValue());
			}
			_token=_token__anonymous_72;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_72);
				class_variable_names.reject(_position__anonymous_72);
				variable_names.reject(_position__anonymous_72);
				_state=SUCCESS;
				_position__anonymous_72=_position;
				_token__anonymous_72=_token;
				_token=new Token.Parsed("$ANON");
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_72)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_72;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_72.addAll(_token);
					_token__anonymous_72.setValue(_token.getValue());
				}
				_token=_token__anonymous_72;
				if(_state==FAILED){
					class_names.reject(_position__anonymous_72);
					class_variable_names.reject(_position__anonymous_72);
					variable_names.reject(_position__anonymous_72);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position__anonymous_72);
					class_variable_names.accept(_position__anonymous_72);
					variable_names.accept(_position__anonymous_72);
				}
			}
		}
	}
	public void parse__anonymous_75(){
		int _position__anonymous_75 = -1;
		Token.Parsed _token__anonymous_75 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_75=_position;
		_token__anonymous_75=_token;
		_token=new Tokens.Name.ExactToken();
		int _state_91 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='^'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
				_furthestPosition=_position;
			}
		}
		if(_state_91==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_75)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_75;
		}
		else{
			parse__anonymous_76();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_75)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_75;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_75.add(_position__anonymous_75,_token);
		}
		_token=_token__anonymous_75;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_75);
			class_variable_names.reject(_position__anonymous_75);
			variable_names.reject(_position__anonymous_75);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_75);
			class_variable_names.accept(_position__anonymous_75);
			variable_names.accept(_position__anonymous_75);
		}
	}
	public void parse__anonymous_74(){
		int _position__anonymous_74 = -1;
		Token.Parsed _token__anonymous_74 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_74=_position;
		_token__anonymous_74=_token;
		_token=new Token.Parsed("$ANON");
		if(_pass==FIRST_PASS){
			String _result = _preparsed_NAME.get(_position);
			if(_result==null){
				_state=FAILED;
			}
			else{
				Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
				_token.add(_position,_first_pass_token);
				_position+=_result.length();
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
		}
		else if(_pass==SECOND_PASS){
			_list_name_result=_preparsed_NAME.get(_position);
			if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
				if(_position+_list_name_result.length()<_inputLength){
					int _next_char = _inputArray[_position+_list_name_result.length()];
					if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
					_position+=_list_name_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
					_furthestPosition=_position;
				}
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var_element(_anonymous_74)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_74;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_74.addAll(_token);
			_token__anonymous_74.setValue(_token.getValue());
		}
		_token=_token__anonymous_74;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_74);
			class_variable_names.reject(_position__anonymous_74);
			variable_names.reject(_position__anonymous_74);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_74);
			class_variable_names.accept(_position__anonymous_74);
			variable_names.accept(_position__anonymous_74);
		}
	}
	public void parse__anonymous_66(){
		int _position__anonymous_66 = -1;
		Token.Parsed _token__anonymous_66 = null;
		int _position_name_var_element = -1;
		Token.Parsed _token_name_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_66=_position;
		_token__anonymous_66=_token;
		_token=new Token.Parsed("$ANON");
		parse__anonymous_67();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_66)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_66;
		}
		else{
			_token_name_var_element=_token;
			_token=new Tokens.Name.NameVarToken();
			_position_name_var_element=_position;
			parse_name_var_element();
			if(_state==SUCCESS){
				_token_name_var_element.add(_position_name_var_element,_token);
			}
			_token=_token_name_var_element;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_66)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_66;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_66.addAll(_token);
			_token__anonymous_66.setValue(_token.getValue());
		}
		_token=_token__anonymous_66;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_66);
			class_variable_names.reject(_position__anonymous_66);
			variable_names.reject(_position__anonymous_66);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_66);
			class_variable_names.accept(_position__anonymous_66);
			variable_names.accept(_position__anonymous_66);
		}
	}
	public void parse__anonymous_65(){
		int _position__anonymous_65 = -1;
		Token.Parsed _token__anonymous_65 = null;
		int _position_name_var_element = -1;
		Token.Parsed _token_name_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_65=_position;
		_token__anonymous_65=_token;
		_token=new Tokens.Name.AccessToken();
		int _state_85 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='^'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
				_furthestPosition=_position;
			}
		}
		if(_state_85==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_65)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_65;
		}
		else{
			_token_name_var_element=_token;
			_token=new Tokens.Name.NameVarToken();
			_position_name_var_element=_position;
			parse_name_var_element();
			if(_state==SUCCESS){
				_token_name_var_element.add(_position_name_var_element,_token);
			}
			_token=_token_name_var_element;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_65)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_65;
			}
			else{
				int _state_86 = _state;
				boolean _iteration_achieved_86 = false;
				while(_position<_inputLength){
					parse__anonymous_66();
					if(_state==FAILED){
						break;
					}
					else{
						_iteration_achieved_86=true;
					}
				}
				if(_iteration_achieved_86==false){
					_state=FAILED;
				}
				else if(_state_86==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_65)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_65;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_65.add(_position__anonymous_65,_token);
		}
		_token=_token__anonymous_65;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_65);
			class_variable_names.reject(_position__anonymous_65);
			variable_names.reject(_position__anonymous_65);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_65);
			class_variable_names.accept(_position__anonymous_65);
			variable_names.accept(_position__anonymous_65);
		}
	}
	public void parse__anonymous_68(){
		int _position__anonymous_68 = -1;
		Token.Parsed _token__anonymous_68 = null;
		int _position_name_var_element = -1;
		Token.Parsed _token_name_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_68=_position;
		_token__anonymous_68=_token;
		_token=new Tokens.Name.ConcatToken();
		int _state_87 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='^'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
				_furthestPosition=_position;
			}
		}
		if(_state_87==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_68)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_68;
		}
		else{
			_token_name_var_element=_token;
			_token=new Tokens.Name.NameVarToken();
			_position_name_var_element=_position;
			parse_name_var_element();
			if(_state==SUCCESS){
				_token_name_var_element.add(_position_name_var_element,_token);
			}
			_token=_token_name_var_element;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_68)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_68;
			}
			else{
				int _state_88 = _state;
				boolean _iteration_achieved_88 = false;
				while(_position<_inputLength){
					parse__anonymous_69();
					if(_state==FAILED){
						break;
					}
					else{
						_iteration_achieved_88=true;
					}
				}
				if(_iteration_achieved_88==false){
					_state=FAILED;
				}
				else if(_state_88==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_68)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_68;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_68.add(_position__anonymous_68,_token);
		}
		_token=_token__anonymous_68;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_68);
			class_variable_names.reject(_position__anonymous_68);
			variable_names.reject(_position__anonymous_68);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_68);
			class_variable_names.accept(_position__anonymous_68);
			variable_names.accept(_position__anonymous_68);
		}
	}
	public void parse__anonymous_67(){
		int _position__anonymous_67 = -1;
		Token.Parsed _token__anonymous_67 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_67=_position;
		_token__anonymous_67=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+2-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='-'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='>'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_55.__SYNTAX__);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ->");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_67)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_67;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_67.addAll(_token);
			_token__anonymous_67.setValue(_token.getValue());
		}
		_token=_token__anonymous_67;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_67);
			class_variable_names.reject(_position__anonymous_67);
			variable_names.reject(_position__anonymous_67);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_67);
			class_variable_names.accept(_position__anonymous_67);
			variable_names.accept(_position__anonymous_67);
		}
	}
	public void parse__anonymous_69(){
		int _position__anonymous_69 = -1;
		Token.Parsed _token__anonymous_69 = null;
		int _position_name_var_element = -1;
		Token.Parsed _token_name_var_element = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_69=_position;
		_token__anonymous_69=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='+'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_56.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain +");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_69)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_69;
		}
		else{
			_token_name_var_element=_token;
			_token=new Tokens.Name.NameVarToken();
			_position_name_var_element=_position;
			parse_name_var_element();
			if(_state==SUCCESS){
				_token_name_var_element.add(_position_name_var_element,_token);
			}
			_token=_token_name_var_element;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"name_var(_anonymous_69)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_69;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_69.addAll(_token);
			_token__anonymous_69.setValue(_token.getValue());
		}
		_token=_token__anonymous_69;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_69);
			class_variable_names.reject(_position__anonymous_69);
			variable_names.reject(_position__anonymous_69);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_69);
			class_variable_names.accept(_position__anonymous_69);
			variable_names.accept(_position__anonymous_69);
		}
	}
	public void parse__anonymous_60(){
		int _position__anonymous_60 = -1;
		Token.Parsed _token__anonymous_60 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_60=_position;
		_token__anonymous_60=_token;
		_token=new Token.Parsed("$ANON");
		parse_class_name_definition();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_60)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_60;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_60.addAll(_token);
			_token__anonymous_60.setValue(_token.getValue());
		}
		_token=_token__anonymous_60;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_60);
			class_variable_names.reject(_position__anonymous_60);
			variable_names.reject(_position__anonymous_60);
			_state=SUCCESS;
			_position__anonymous_60=_position;
			_token__anonymous_60=_token;
			_token=new Token.Parsed("$ANON");
			parse_variable_name_definition();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_60)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_60;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_60.addAll(_token);
				_token__anonymous_60.setValue(_token.getValue());
			}
			_token=_token__anonymous_60;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_60);
				class_variable_names.reject(_position__anonymous_60);
				variable_names.reject(_position__anonymous_60);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_60);
				class_variable_names.accept(_position__anonymous_60);
				variable_names.accept(_position__anonymous_60);
			}
		}
	}
	public void parse__anonymous_62(){
		int _position__anonymous_62 = -1;
		Token.Parsed _token__anonymous_62 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_62=_position;
		_token__anonymous_62=_token;
		_token=new Tokens.Name.VariableNameToken();
		parse_name_var();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_62)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_62;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_62.add(_position__anonymous_62,_token);
		}
		_token=_token__anonymous_62;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_62);
			class_variable_names.reject(_position__anonymous_62);
			variable_names.reject(_position__anonymous_62);
			_state=SUCCESS;
			_position__anonymous_62=_position;
			_token__anonymous_62=_token;
			_token=new Tokens.Name.VariableNameToken();
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(_anonymous_62)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_62;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_62.add(_position__anonymous_62,_token);
			}
			_token=_token__anonymous_62;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_62);
				class_variable_names.reject(_position__anonymous_62);
				variable_names.reject(_position__anonymous_62);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_62);
				class_variable_names.accept(_position__anonymous_62);
				variable_names.accept(_position__anonymous_62);
			}
		}
	}
	public void parse__anonymous_61(){
		int _position__anonymous_61 = -1;
		Token.Parsed _token__anonymous_61 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_61=_position;
		_token__anonymous_61=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='='){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_40.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain =");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_61)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_61;
		}
		else{
			parse_method_argument();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_61)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_61;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_61.addAll(_token);
			_token__anonymous_61.setValue(_token.getValue());
		}
		_token=_token__anonymous_61;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_61);
			class_variable_names.reject(_position__anonymous_61);
			variable_names.reject(_position__anonymous_61);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_61);
			class_variable_names.accept(_position__anonymous_61);
			variable_names.accept(_position__anonymous_61);
		}
	}
	public void parse__anonymous_64(){
		int _position__anonymous_64 = -1;
		Token.Parsed _token__anonymous_64 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_64=_position;
		_token__anonymous_64=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='.'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_64)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_64;
		}
		else{
			parse_type_var();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"all_type_name(_anonymous_64)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_64;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_64.addAll(_token);
			_token__anonymous_64.setValue(_token.getValue());
		}
		_token=_token__anonymous_64;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_64);
			class_variable_names.reject(_position__anonymous_64);
			variable_names.reject(_position__anonymous_64);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_64);
			class_variable_names.accept(_position__anonymous_64);
			variable_names.accept(_position__anonymous_64);
		}
	}
	public void parse__anonymous_63(){
		int _position__anonymous_63 = -1;
		Token.Parsed _token__anonymous_63 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_63=_position;
		_token__anonymous_63=_token;
		_token=new Tokens.Name.VariableNameToken();
		parse_name_var();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(_anonymous_63)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_63;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_63.add(_position__anonymous_63,_token);
		}
		_token=_token__anonymous_63;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_63);
			class_variable_names.reject(_position__anonymous_63);
			variable_names.reject(_position__anonymous_63);
			_state=SUCCESS;
			_position__anonymous_63=_position;
			_token__anonymous_63=_token;
			_token=new Tokens.Name.VariableNameToken();
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_name_definition(_anonymous_63)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_63;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_63.add(_position__anonymous_63,_token);
			}
			_token=_token__anonymous_63;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_63);
				class_variable_names.reject(_position__anonymous_63);
				variable_names.reject(_position__anonymous_63);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_63);
				class_variable_names.accept(_position__anonymous_63);
				variable_names.accept(_position__anonymous_63);
			}
		}
	}
	public void parse__anonymous_55(){
		int _position__anonymous_55 = -1;
		Token.Parsed _token__anonymous_55 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_55=_position;
		_token__anonymous_55=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!=','){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_55)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_55;
		}
		else{
			parse_variable_declaration();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_parameters(_anonymous_55)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_55;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_55.addAll(_token);
			_token__anonymous_55.setValue(_token.getValue());
		}
		_token=_token__anonymous_55;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_55);
			class_variable_names.reject(_position__anonymous_55);
			variable_names.reject(_position__anonymous_55);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_55);
			class_variable_names.accept(_position__anonymous_55);
			variable_names.accept(_position__anonymous_55);
		}
	}
	public void parse__anonymous_54(){
		int _position__anonymous_54 = -1;
		Token.Parsed _token__anonymous_54 = null;
		int _position_NAME = -1;
		Token.Parsed _token_NAME = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_54=_position;
		_token__anonymous_54=_token;
		_token=new Tokens.Name.TokenInstanceToken();
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!=':'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_8.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain :");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_54)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_54;
		}
		else{
			_token_NAME=_token;
			_token=new Tokens.Name.TokenNameToken();
			_position_NAME=_position;
			parse_NAME();
			if(_state==SUCCESS){
				_token_NAME.add(_position_NAME,_token);
			}
			_token=_token_NAME;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_54)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_54;
			}
			else{
				parse_method_body();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_54)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_54;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_54.add(_position__anonymous_54,_token);
		}
		_token=_token__anonymous_54;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_54);
			class_variable_names.reject(_position__anonymous_54);
			variable_names.reject(_position__anonymous_54);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_54);
			class_variable_names.accept(_position__anonymous_54);
			variable_names.accept(_position__anonymous_54);
		}
	}
	public void parse__anonymous_57(){
		int _position__anonymous_57 = -1;
		Token.Parsed _token__anonymous_57 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_57=_position;
		_token__anonymous_57=_token;
		_token=new Tokens.Name.MethodNameToken();
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='*'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_31.NAME);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_57)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_57;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_57.add(_position__anonymous_57,_token);
		}
		_token=_token__anonymous_57;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_57);
			class_variable_names.reject(_position__anonymous_57);
			variable_names.reject(_position__anonymous_57);
			_state=SUCCESS;
			_position__anonymous_57=_position;
			_token__anonymous_57=_token;
			_token=new Tokens.Name.MethodNameToken();
			parse_name_var();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_57)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_57;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_57.add(_position__anonymous_57,_token);
			}
			_token=_token__anonymous_57;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_57);
				class_variable_names.reject(_position__anonymous_57);
				variable_names.reject(_position__anonymous_57);
				_state=SUCCESS;
				_position__anonymous_57=_position;
				_token__anonymous_57=_token;
				_token=new Tokens.Name.MethodNameToken();
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_57)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_57;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_57.add(_position__anonymous_57,_token);
				}
				_token=_token__anonymous_57;
				if(_state==FAILED){
					class_names.reject(_position__anonymous_57);
					class_variable_names.reject(_position__anonymous_57);
					variable_names.reject(_position__anonymous_57);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position__anonymous_57);
					class_variable_names.accept(_position__anonymous_57);
					variable_names.accept(_position__anonymous_57);
				}
			}
		}
	}
	public void parse__anonymous_56(){
		int _position__anonymous_56 = -1;
		Token.Parsed _token__anonymous_56 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_56=_position;
		_token__anonymous_56=_token;
		_token=new Tokens.Name.StaticToken();
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='@'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_37.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain @");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_56)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_56;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_56.add(_position__anonymous_56,_token);
		}
		_token=_token__anonymous_56;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_56);
			class_variable_names.reject(_position__anonymous_56);
			variable_names.reject(_position__anonymous_56);
			_state=SUCCESS;
			_position__anonymous_56=_position;
			_token__anonymous_56=_token;
			_token=new Tokens.Name.StaticToken();
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='c'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_38.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain static");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_56)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_56;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_56.add(_position__anonymous_56,_token);
			}
			_token=_token__anonymous_56;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_56);
				class_variable_names.reject(_position__anonymous_56);
				variable_names.reject(_position__anonymous_56);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_56);
				class_variable_names.accept(_position__anonymous_56);
				variable_names.accept(_position__anonymous_56);
			}
		}
	}
	public void parse__anonymous_59(){
		int _position__anonymous_59 = -1;
		Token.Parsed _token__anonymous_59 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_59=_position;
		_token__anonymous_59=_token;
		_token=new Tokens.Name.StaticToken();
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='@'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_37.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain @");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_59)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_59;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_59.add(_position__anonymous_59,_token);
		}
		_token=_token__anonymous_59;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_59);
			class_variable_names.reject(_position__anonymous_59);
			variable_names.reject(_position__anonymous_59);
			_state=SUCCESS;
			_position__anonymous_59=_position;
			_token__anonymous_59=_token;
			_token=new Tokens.Name.StaticToken();
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='c'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_38.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain static");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(_anonymous_59)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_59;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_59.add(_position__anonymous_59,_token);
			}
			_token=_token__anonymous_59;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_59);
				class_variable_names.reject(_position__anonymous_59);
				variable_names.reject(_position__anonymous_59);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_59);
				class_variable_names.accept(_position__anonymous_59);
				variable_names.accept(_position__anonymous_59);
			}
		}
	}
	public void parse__anonymous_58(){
		int _position__anonymous_58 = -1;
		Token.Parsed _token__anonymous_58 = null;
		int _position_statement_as_method = -1;
		int _position_method_parameters = -1;
		Token.Parsed _token_statement_as_method = null;
		Token.Parsed _token_method_parameters = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_58=_position;
		_token__anonymous_58=_token;
		_token=new Token.Parsed("$ANON");
		_token_method_parameters=_token;
		_token=new Tokens.Name.InlineToken();
		_position_method_parameters=_position;
		parse_method_parameters();
		if(_state==SUCCESS){
			_token_method_parameters.add(_position_method_parameters,_token);
		}
		_token=_token_method_parameters;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_58)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_58;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_58.addAll(_token);
			_token__anonymous_58.setValue(_token.getValue());
		}
		_token=_token__anonymous_58;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_58);
			class_variable_names.reject(_position__anonymous_58);
			variable_names.reject(_position__anonymous_58);
			_state=SUCCESS;
			_position__anonymous_58=_position;
			_token__anonymous_58=_token;
			_token=new Token.Parsed("$ANON");
			_token_statement_as_method=_token;
			_token=new Tokens.Name.VariableParametersToken();
			_position_statement_as_method=_position;
			parse_statement_as_method();
			if(_state==SUCCESS){
				_token_statement_as_method.add(_position_statement_as_method,_token);
			}
			_token=_token_statement_as_method;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_declaration(_anonymous_58)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_58;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_58.addAll(_token);
				_token__anonymous_58.setValue(_token.getValue());
			}
			_token=_token__anonymous_58;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_58);
				class_variable_names.reject(_position__anonymous_58);
				variable_names.reject(_position__anonymous_58);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_58);
				class_variable_names.accept(_position__anonymous_58);
				variable_names.accept(_position__anonymous_58);
			}
		}
	}
	public void parse__anonymous_51(){
		int _position__anonymous_51 = -1;
		Token.Parsed _token__anonymous_51 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_51=_position;
		_token__anonymous_51=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='.'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_51)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_51;
		}
		else{
			parse__anonymous_52();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_51)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_51;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_51.addAll(_token);
			_token__anonymous_51.setValue(_token.getValue());
		}
		_token=_token__anonymous_51;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_51);
			class_variable_names.reject(_position__anonymous_51);
			variable_names.reject(_position__anonymous_51);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_51);
			class_variable_names.accept(_position__anonymous_51);
			variable_names.accept(_position__anonymous_51);
		}
	}
	public void parse__anonymous_50(){
		int _position__anonymous_50 = -1;
		Token.Parsed _token__anonymous_50 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_50=_position;
		_token__anonymous_50=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='.'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_50)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_50;
		}
		else{
			parse_type_var();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_50)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_50;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_50.addAll(_token);
			_token__anonymous_50.setValue(_token.getValue());
		}
		_token=_token__anonymous_50;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_50);
			class_variable_names.reject(_position__anonymous_50);
			variable_names.reject(_position__anonymous_50);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_50);
			class_variable_names.accept(_position__anonymous_50);
			variable_names.accept(_position__anonymous_50);
		}
	}
	public void parse__anonymous_53(){
		int _position__anonymous_53 = -1;
		Token.Parsed _token__anonymous_53 = null;
		int _position_as_statement = -1;
		int _position_method_body = -1;
		Token.Parsed _token_as_statement = null;
		Token.Parsed _token_method_body = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_53=_position;
		_token__anonymous_53=_token;
		_token=new Token.Parsed("$ANON");
		parse_class_declaration();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_53;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_53.addAll(_token);
			_token__anonymous_53.setValue(_token.getValue());
		}
		_token=_token__anonymous_53;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_53);
			class_variable_names.reject(_position__anonymous_53);
			variable_names.reject(_position__anonymous_53);
			_state=SUCCESS;
			_position__anonymous_53=_position;
			_token__anonymous_53=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_declaration();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_53;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_53.addAll(_token);
				_token__anonymous_53.setValue(_token.getValue());
			}
			_token=_token__anonymous_53;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_53);
				class_variable_names.reject(_position__anonymous_53);
				variable_names.reject(_position__anonymous_53);
				_state=SUCCESS;
				_position__anonymous_53=_position;
				_token__anonymous_53=_token;
				_token=new Token.Parsed("$ANON");
				parse_variable_declaration();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_53;
				}
				else{
					if(_position+1-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!=';'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
						_position=_position+1;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_53;
					}
					else{
					}
				}
				if(_state==SUCCESS){
					_token__anonymous_53.addAll(_token);
					_token__anonymous_53.setValue(_token.getValue());
				}
				_token=_token__anonymous_53;
				if(_state==FAILED){
					class_names.reject(_position__anonymous_53);
					class_variable_names.reject(_position__anonymous_53);
					variable_names.reject(_position__anonymous_53);
					_state=SUCCESS;
					_position__anonymous_53=_position;
					_token__anonymous_53=_token;
					_token=new Token.Parsed("$ANON");
					_token_method_body=_token;
					_token=new Tokens.Name.BodyToken();
					_position_method_body=_position;
					parse_method_body();
					if(_state==SUCCESS){
						_token_method_body.add(_position_method_body,_token);
					}
					_token=_token_method_body;
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_53;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_53.addAll(_token);
						_token__anonymous_53.setValue(_token.getValue());
					}
					_token=_token__anonymous_53;
					if(_state==FAILED){
						class_names.reject(_position__anonymous_53);
						class_variable_names.reject(_position__anonymous_53);
						variable_names.reject(_position__anonymous_53);
						_state=SUCCESS;
						_position__anonymous_53=_position;
						_token__anonymous_53=_token;
						_token=new Token.Parsed("$ANON");
						_token_as_statement=_token;
						_token=new Tokens.Name.BodyToken();
						_position_as_statement=_position;
						parse_as_statement();
						if(_state==SUCCESS){
							_token_as_statement.add(_position_as_statement,_token);
						}
						_token=_token_as_statement;
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_manipulate(_anonymous_53)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_53;
						}
						else{
						}
						if(_state==SUCCESS){
							_token__anonymous_53.addAll(_token);
							_token__anonymous_53.setValue(_token.getValue());
						}
						_token=_token__anonymous_53;
						if(_state==FAILED){
							class_names.reject(_position__anonymous_53);
							class_variable_names.reject(_position__anonymous_53);
							variable_names.reject(_position__anonymous_53);
						}
						else if(_state==SUCCESS){
							class_names.accept(_position__anonymous_53);
							class_variable_names.accept(_position__anonymous_53);
							variable_names.accept(_position__anonymous_53);
						}
					}
				}
			}
		}
	}
	public void parse__anonymous_52(){
		int _position__anonymous_52 = -1;
		Token.Parsed _token__anonymous_52 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_52=_position;
		_token__anonymous_52=_token;
		_token=new Tokens.Name.GroupToken();
		parse_NAME();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_52)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_52;
		}
		else{
			int _state_66 = _state;
			parse_method_arguments();
			if(_state_66==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_52)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_52;
			}
			else{
				int _state_67 = _state;
				while(_position<_inputLength){
					parse_array_parameters();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_67==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_52)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_52;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_52.add(_position__anonymous_52,_token);
		}
		_token=_token__anonymous_52;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_52);
			class_variable_names.reject(_position__anonymous_52);
			variable_names.reject(_position__anonymous_52);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_52);
			class_variable_names.accept(_position__anonymous_52);
			variable_names.accept(_position__anonymous_52);
		}
	}
	public void parse__anonymous_44(){
		int _position__anonymous_44 = -1;
		Token.Parsed _token__anonymous_44 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_44=_position;
		_token__anonymous_44=_token;
		_token=new Tokens.Name.GroupToken();
		parse_NAME();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_44)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_44;
		}
		else{
			int _state_55 = _state;
			parse_method_arguments();
			if(_state_55==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_44)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_44;
			}
			else{
				int _state_56 = _state;
				while(_position<_inputLength){
					parse_array_parameters();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_56==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_44)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_44;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_44.add(_position__anonymous_44,_token);
		}
		_token=_token__anonymous_44;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_44);
			class_variable_names.reject(_position__anonymous_44);
			variable_names.reject(_position__anonymous_44);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_44);
			class_variable_names.accept(_position__anonymous_44);
			variable_names.accept(_position__anonymous_44);
		}
	}
	public void parse__anonymous_43(){
		int _position__anonymous_43 = -1;
		Token.Parsed _token__anonymous_43 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_43=_position;
		_token__anonymous_43=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='.'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_43)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_43;
		}
		else{
			parse__anonymous_44();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_43)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_43;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_43.addAll(_token);
			_token__anonymous_43.setValue(_token.getValue());
		}
		_token=_token__anonymous_43;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_43);
			class_variable_names.reject(_position__anonymous_43);
			variable_names.reject(_position__anonymous_43);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_43);
			class_variable_names.accept(_position__anonymous_43);
			variable_names.accept(_position__anonymous_43);
		}
	}
	public void parse__anonymous_46(){
		int _position__anonymous_46 = -1;
		Token.Parsed _token__anonymous_46 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_46=_position;
		_token__anonymous_46=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='.'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_46)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_46;
		}
		else{
			parse__anonymous_47();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_46)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_46;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_46.addAll(_token);
			_token__anonymous_46.setValue(_token.getValue());
		}
		_token=_token__anonymous_46;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_46);
			class_variable_names.reject(_position__anonymous_46);
			variable_names.reject(_position__anonymous_46);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_46);
			class_variable_names.accept(_position__anonymous_46);
			variable_names.accept(_position__anonymous_46);
		}
	}
	public void parse__anonymous_45(){
		int _position__anonymous_45 = -1;
		Token.Parsed _token__anonymous_45 = null;
		int _position_name_var = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_45=_position;
		_token__anonymous_45=_token;
		_token=new Tokens.Name.GroupToken();
		_position_name_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_name_var_36.contains(_position)){
			_recursion_protection_name_var_36.add(_position);
			parse_name_var();
			_recursion_protection_name_var_36.remove(_position_name_var);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_45)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_45;
		}
		else{
			int _state_57 = _state;
			parse_method_arguments();
			if(_state_57==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_45)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_45;
			}
			else{
				int _state_58 = _state;
				while(_position<_inputLength){
					parse_array_parameters();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_58==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_45)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_45;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_45.add(_position__anonymous_45,_token);
		}
		_token=_token__anonymous_45;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_45);
			class_variable_names.reject(_position__anonymous_45);
			variable_names.reject(_position__anonymous_45);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_45);
			class_variable_names.accept(_position__anonymous_45);
			variable_names.accept(_position__anonymous_45);
		}
	}
	public void parse__anonymous_48(){
		int _position__anonymous_48 = -1;
		Token.Parsed _token__anonymous_48 = null;
		int _position_name_var = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_48=_position;
		_token__anonymous_48=_token;
		_token=new Token.Parsed("$ANON");
		_position_name_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_name_var_37.contains(_position)){
			_recursion_protection_name_var_37.add(_position);
			parse_name_var();
			_recursion_protection_name_var_37.remove(_position_name_var);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_48)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_48;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_48.addAll(_token);
			_token__anonymous_48.setValue(_token.getValue());
		}
		_token=_token__anonymous_48;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_48);
			class_variable_names.reject(_position__anonymous_48);
			variable_names.reject(_position__anonymous_48);
			_state=SUCCESS;
			_position__anonymous_48=_position;
			_token__anonymous_48=_token;
			_token=new Token.Parsed("$ANON");
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_48)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_48;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_48.addAll(_token);
				_token__anonymous_48.setValue(_token.getValue());
			}
			_token=_token__anonymous_48;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_48);
				class_variable_names.reject(_position__anonymous_48);
				variable_names.reject(_position__anonymous_48);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_48);
				class_variable_names.accept(_position__anonymous_48);
				variable_names.accept(_position__anonymous_48);
			}
		}
	}
	public void parse__anonymous_47(){
		int _position__anonymous_47 = -1;
		Token.Parsed _token__anonymous_47 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_47=_position;
		_token__anonymous_47=_token;
		_token=new Tokens.Name.GroupToken();
		parse__anonymous_48();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_47)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_47;
		}
		else{
			int _state_60 = _state;
			parse_method_arguments();
			if(_state_60==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_47)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_47;
			}
			else{
				int _state_61 = _state;
				while(_position<_inputLength){
					parse_array_parameters();
					if(_state==FAILED){
						break;
					}
				}
				if(_state_61==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_47)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_47;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_47.add(_position__anonymous_47,_token);
		}
		_token=_token__anonymous_47;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_47);
			class_variable_names.reject(_position__anonymous_47);
			variable_names.reject(_position__anonymous_47);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_47);
			class_variable_names.accept(_position__anonymous_47);
			variable_names.accept(_position__anonymous_47);
		}
	}
	public void parse__anonymous_49(){
		int _position__anonymous_49 = -1;
		Token.Parsed _token__anonymous_49 = null;
		int _position_type_var = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_49=_position;
		_token__anonymous_49=_token;
		_token=new Tokens.Name.GroupToken();
		_position_type_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_type_var_38.contains(_position)){
			_recursion_protection_type_var_38.add(_position);
			parse_type_var();
			_recursion_protection_type_var_38.remove(_position_type_var);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_49)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_49;
		}
		else{
			int _state_62 = _state;
			while(_position<_inputLength){
				parse__anonymous_50();
				if(_state==FAILED){
					break;
				}
			}
			if(_state_62==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_49)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_49;
			}
			else{
				int _state_63 = _state;
				parse_method_arguments();
				if(_state_63==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_49)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_49;
				}
				else{
					int _state_64 = _state;
					while(_position<_inputLength){
						parse_array_parameters();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_64==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_49)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_49;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_49.add(_position__anonymous_49,_token);
		}
		_token=_token__anonymous_49;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_49);
			class_variable_names.reject(_position__anonymous_49);
			variable_names.reject(_position__anonymous_49);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_49);
			class_variable_names.accept(_position__anonymous_49);
			variable_names.accept(_position__anonymous_49);
		}
	}
	public void parse__anonymous_6(){
		int _position__anonymous_6 = -1;
		Token.Parsed _token__anonymous_6 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_6=_position;
		_token__anonymous_6=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='<'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain <");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_6)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_6;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_6.addAll(_token);
			_token__anonymous_6.setValue(_token.getValue());
		}
		_token=_token__anonymous_6;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_6);
			class_variable_names.reject(_position__anonymous_6);
			variable_names.reject(_position__anonymous_6);
			_state=SUCCESS;
			_position__anonymous_6=_position;
			_token__anonymous_6=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='h'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='d'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='d'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='n'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_7.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain hidden");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_6)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_6;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_6.addAll(_token);
				_token__anonymous_6.setValue(_token.getValue());
			}
			_token=_token__anonymous_6;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_6);
				class_variable_names.reject(_position__anonymous_6);
				variable_names.reject(_position__anonymous_6);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_6);
				class_variable_names.accept(_position__anonymous_6);
				variable_names.accept(_position__anonymous_6);
			}
		}
	}
	public void parse__anonymous_5(){
		int _position__anonymous_5 = -1;
		Token.Parsed _token__anonymous_5 = null;
		int _position_body_statement = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_5=_position;
		_token__anonymous_5=_token;
		_token=new Token.Parsed("$ANON");
		int _state_20 = _state;
		boolean _iteration_achieved_20 = false;
		while(_position<_inputLength){
			parse_body_element();
			if(_state==FAILED){
				break;
			}
			else{
				_iteration_achieved_20=true;
			}
		}
		if(_iteration_achieved_20==false){
			_state=FAILED;
		}
		else if(_state_20==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(_anonymous_5)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_5;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_5.addAll(_token);
			_token__anonymous_5.setValue(_token.getValue());
		}
		_token=_token__anonymous_5;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_5);
			class_variable_names.reject(_position__anonymous_5);
			variable_names.reject(_position__anonymous_5);
			_state=SUCCESS;
			_position__anonymous_5=_position;
			_token__anonymous_5=_token;
			_token=new Token.Parsed("$ANON");
			_position_body_statement=_position;
			if(_state==SUCCESS&&!_recursion_protection_body_statement_6.contains(_position)){
				_recursion_protection_body_statement_6.add(_position);
				parse_body_statement();
				_recursion_protection_body_statement_6.remove(_position_body_statement);
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"as_statement(_anonymous_5)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_5;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_5.addAll(_token);
				_token__anonymous_5.setValue(_token.getValue());
			}
			_token=_token__anonymous_5;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_5);
				class_variable_names.reject(_position__anonymous_5);
				variable_names.reject(_position__anonymous_5);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_5);
				class_variable_names.accept(_position__anonymous_5);
				variable_names.accept(_position__anonymous_5);
			}
		}
	}
	public void parse__anonymous_8(){
		int _position__anonymous_8 = -1;
		Token.Parsed _token__anonymous_8 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_8=_position;
		_token__anonymous_8=_token;
		_token=new Token.Parsed("$ANON");
		parse_packageName();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_8)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_8;
		}
		else{
			int _state_25 = _state;
			while(_position<_inputLength){
				parse__anonymous_9();
				if(_state==FAILED){
					break;
				}
			}
			if(_state_25==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_8)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_8;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_8.addAll(_token);
			_token__anonymous_8.setValue(_token.getValue());
		}
		_token=_token__anonymous_8;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_8);
			class_variable_names.reject(_position__anonymous_8);
			variable_names.reject(_position__anonymous_8);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_8);
			class_variable_names.accept(_position__anonymous_8);
			variable_names.accept(_position__anonymous_8);
		}
	}
	public void parse__anonymous_7(){
		int _position__anonymous_7 = -1;
		Token.Parsed _token__anonymous_7 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_7=_position;
		_token__anonymous_7=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!=':'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_8.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain :");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_7)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_7;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_7.addAll(_token);
			_token__anonymous_7.setValue(_token.getValue());
		}
		_token=_token__anonymous_7;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_7);
			class_variable_names.reject(_position__anonymous_7);
			variable_names.reject(_position__anonymous_7);
			_state=SUCCESS;
			_position__anonymous_7=_position;
			_token__anonymous_7=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+4-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='f'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='o'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='m'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_9.__SYNTAX__);
				_position=_position+4;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain from");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_7)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_7;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_7.addAll(_token);
				_token__anonymous_7.setValue(_token.getValue());
			}
			_token=_token__anonymous_7;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_7);
				class_variable_names.reject(_position__anonymous_7);
				variable_names.reject(_position__anonymous_7);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_7);
				class_variable_names.accept(_position__anonymous_7);
				variable_names.accept(_position__anonymous_7);
			}
		}
	}
	public void parse__anonymous_2(){
		int _position__anonymous_2 = -1;
		Token.Parsed _token__anonymous_2 = null;
		int _position_all_type_name = -1;
		Token.Parsed _token_all_type_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_2=_position;
		_token__anonymous_2=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!=','){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(_anonymous_2)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_2;
		}
		else{
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
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"template_parameters(_anonymous_2)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_2;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_2.addAll(_token);
			_token__anonymous_2.setValue(_token.getValue());
		}
		_token=_token__anonymous_2;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_2);
			class_variable_names.reject(_position__anonymous_2);
			variable_names.reject(_position__anonymous_2);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_2);
			class_variable_names.accept(_position__anonymous_2);
			variable_names.accept(_position__anonymous_2);
		}
	}
	public void parse__anonymous_40(){
		int _position__anonymous_40 = -1;
		Token.Parsed _token__anonymous_40 = null;
		int _position_statement_as_braced = -1;
		int _position_body_statement = -1;
		Token.Parsed _token_statement_as_braced = null;
		Token.Parsed _token_body_statement = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_40=_position;
		_token__anonymous_40=_token;
		_token=new Tokens.Name.AsBracedToken();
		_token_statement_as_braced=_token;
		_token=new Tokens.Name.LeftToken();
		_position_statement_as_braced=_position;
		parse_statement_as_braced();
		if(_state==SUCCESS){
			_token_statement_as_braced.add(_position_statement_as_braced,_token);
		}
		_token=_token_statement_as_braced;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_40)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_40;
		}
		else{
			int _state_49 = _state;
			parse__anonymous_41();
			if(_state_49==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_40)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_40;
			}
			else{
				int _state_51 = _state;
				_token_body_statement=_token;
				_token=new Tokens.Name.RightToken();
				_position_body_statement=_position;
				parse_body_statement();
				if(_state==SUCCESS){
					_token_body_statement.add(_position_body_statement,_token);
				}
				_token=_token_body_statement;
				if(_state_51==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_40)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_40;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_40.add(_position__anonymous_40,_token);
		}
		_token=_token__anonymous_40;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_40);
			class_variable_names.reject(_position__anonymous_40);
			variable_names.reject(_position__anonymous_40);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_40);
			class_variable_names.accept(_position__anonymous_40);
			variable_names.accept(_position__anonymous_40);
		}
	}
	public void parse__anonymous_1(){
		int _position__anonymous_1 = -1;
		Token.Parsed _token__anonymous_1 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_1=_position;
		_token__anonymous_1=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!=','){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_1)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_1;
		}
		else{
			parse_method_argument();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_1)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_1;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_1.addAll(_token);
			_token__anonymous_1.setValue(_token.getValue());
		}
		_token=_token__anonymous_1;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_1);
			class_variable_names.reject(_position__anonymous_1);
			variable_names.reject(_position__anonymous_1);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_1);
			class_variable_names.accept(_position__anonymous_1);
			variable_names.accept(_position__anonymous_1);
		}
	}
	public void parse__anonymous_4(){
		int _position__anonymous_4 = -1;
		Token.Parsed _token__anonymous_4 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_4=_position;
		_token__anonymous_4=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!=','){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_0.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ,");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_4)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_4;
		}
		else{
			parse_method_argument();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_4)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_4;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_4.addAll(_token);
			_token__anonymous_4.setValue(_token.getValue());
		}
		_token=_token__anonymous_4;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_4);
			class_variable_names.reject(_position__anonymous_4);
			variable_names.reject(_position__anonymous_4);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_4);
			class_variable_names.accept(_position__anonymous_4);
			variable_names.accept(_position__anonymous_4);
		}
	}
	public void parse__anonymous_42(){
		int _position__anonymous_42 = -1;
		Token.Parsed _token__anonymous_42 = null;
		int _position_all_type_name = -1;
		Token.Parsed _token_all_type_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_42=_position;
		_token__anonymous_42=_token;
		_token=new Tokens.Name.GroupToken();
		if(_position+4-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='n'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='e'){
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='w'){
				_state=FAILED;
			}
			if(_inputArray[_position+3]!=' '){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_33.NEW);
			_position=_position+4;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain new ");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_42)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_42;
		}
		else{
			_token_all_type_name=_token;
			_token=new Tokens.Name.TypeNameToken();
			_position_all_type_name=_position;
			parse_all_type_name();
			if(_state==SUCCESS){
				_token_all_type_name.add(_position_all_type_name,_token);
			}
			_token=_token_all_type_name;
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_42)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_42;
			}
			else{
				int _state_52 = _state;
				parse_method_arguments();
				if(_state_52==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_42)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_42;
				}
				else{
					int _state_53 = _state;
					while(_position<_inputLength){
						parse_array_parameters();
						if(_state==FAILED){
							break;
						}
					}
					if(_state_53==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_42)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_42;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_42.add(_position__anonymous_42,_token);
		}
		_token=_token__anonymous_42;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_42);
			class_variable_names.reject(_position__anonymous_42);
			variable_names.reject(_position__anonymous_42);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_42);
			class_variable_names.accept(_position__anonymous_42);
			variable_names.accept(_position__anonymous_42);
		}
	}
	public void parse__anonymous_3(){
		int _position__anonymous_3 = -1;
		Token.Parsed _token__anonymous_3 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_3=_position;
		_token__anonymous_3=_token;
		_token=new Token.Parsed("$ANON");
		parse_method_argument();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_3)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_3;
		}
		else{
			int _state_19 = _state;
			while(_position<_inputLength){
				parse__anonymous_4();
				if(_state==FAILED){
					break;
				}
			}
			if(_state_19==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"array_parameters(_anonymous_3)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_3;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_3.addAll(_token);
			_token__anonymous_3.setValue(_token.getValue());
		}
		_token=_token__anonymous_3;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_3);
			class_variable_names.reject(_position__anonymous_3);
			variable_names.reject(_position__anonymous_3);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_3);
			class_variable_names.accept(_position__anonymous_3);
			variable_names.accept(_position__anonymous_3);
		}
	}
	public void parse__anonymous_41(){
		int _position__anonymous_41 = -1;
		Token.Parsed _token__anonymous_41 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_41=_position;
		_token__anonymous_41=_token;
		_token=new Token.Parsed("$ANON");
		int _state_50 = _state;
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
		if(_state_50==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_41)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_41;
		}
		else{
			parse_OPERATOR();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_call(_anonymous_41)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_41;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_41.addAll(_token);
			_token__anonymous_41.setValue(_token.getValue());
		}
		_token=_token__anonymous_41;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_41);
			class_variable_names.reject(_position__anonymous_41);
			variable_names.reject(_position__anonymous_41);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_41);
			class_variable_names.accept(_position__anonymous_41);
			variable_names.accept(_position__anonymous_41);
		}
	}
	public void parse__anonymous_9(){
		int _position__anonymous_9 = -1;
		Token.Parsed _token__anonymous_9 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_9=_position;
		_token__anonymous_9=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='.'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_9)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_9;
		}
		else{
			parse_packageName();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"anonymous_class(_anonymous_9)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_9;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_9.addAll(_token);
			_token__anonymous_9.setValue(_token.getValue());
		}
		_token=_token__anonymous_9;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_9);
			class_variable_names.reject(_position__anonymous_9);
			variable_names.reject(_position__anonymous_9);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_9);
			class_variable_names.accept(_position__anonymous_9);
			variable_names.accept(_position__anonymous_9);
		}
	}
	public void parse__anonymous_33(){
		int _position__anonymous_33 = -1;
		Token.Parsed _token__anonymous_33 = null;
		int _position_method_body = -1;
		Token.Parsed _token_method_body = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_33=_position;
		_token__anonymous_33=_token;
		_token=new Token.Parsed("$ANON");
		_token_method_body=_token;
		_token=new Tokens.Name.AsBodyToken();
		_position_method_body=_position;
		parse_method_body();
		if(_state==SUCCESS){
			_token_method_body.add(_position_method_body,_token);
		}
		_token=_token_method_body;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_33)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_33;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_33.addAll(_token);
			_token__anonymous_33.setValue(_token.getValue());
		}
		_token=_token__anonymous_33;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_33);
			class_variable_names.reject(_position__anonymous_33);
			variable_names.reject(_position__anonymous_33);
			_state=SUCCESS;
			_position__anonymous_33=_position;
			_token__anonymous_33=_token;
			_token=new Token.Parsed("$ANON");
			parse_statement_as_method();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_33)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_33;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_33.addAll(_token);
				_token__anonymous_33.setValue(_token.getValue());
			}
			_token=_token__anonymous_33;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_33);
				class_variable_names.reject(_position__anonymous_33);
				variable_names.reject(_position__anonymous_33);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_33);
				class_variable_names.accept(_position__anonymous_33);
				variable_names.accept(_position__anonymous_33);
			}
		}
	}
	public void parse__anonymous_32(){
		int _position__anonymous_32 = -1;
		Token.Parsed _token__anonymous_32 = null;
		int _position_method_body = -1;
		Token.Parsed _token_method_body = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_32=_position;
		_token__anonymous_32=_token;
		_token=new Token.Parsed("$ANON");
		_token_method_body=_token;
		_token=new Tokens.Name.AsBodyToken();
		_position_method_body=_position;
		parse_method_body();
		if(_state==SUCCESS){
			_token_method_body.add(_position_method_body,_token);
		}
		_token=_token_method_body;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_32)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_32;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_32.addAll(_token);
			_token__anonymous_32.setValue(_token.getValue());
		}
		_token=_token__anonymous_32;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_32);
			class_variable_names.reject(_position__anonymous_32);
			variable_names.reject(_position__anonymous_32);
			_state=SUCCESS;
			_position__anonymous_32=_position;
			_token__anonymous_32=_token;
			_token=new Token.Parsed("$ANON");
			parse_statement_as_method();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_32)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_32;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_32.addAll(_token);
				_token__anonymous_32.setValue(_token.getValue());
			}
			_token=_token__anonymous_32;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_32);
				class_variable_names.reject(_position__anonymous_32);
				variable_names.reject(_position__anonymous_32);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_32);
				class_variable_names.accept(_position__anonymous_32);
				variable_names.accept(_position__anonymous_32);
			}
		}
	}
	public void parse__anonymous_0(){
		int _position__anonymous_0 = -1;
		Token.Parsed _token__anonymous_0 = null;
		int _position_method_argument = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_0=_position;
		_token__anonymous_0=_token;
		_token=new Token.Parsed("$ANON");
		_position_method_argument=_position;
		if(_state==SUCCESS&&!_recursion_protection_method_argument_1.contains(_position)){
			_recursion_protection_method_argument_1.add(_position);
			parse_method_argument();
			_recursion_protection_method_argument_1.remove(_position_method_argument);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_0)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_0;
		}
		else{
			int _state_16 = _state;
			while(_position<_inputLength){
				parse__anonymous_1();
				if(_state==FAILED){
					break;
				}
			}
			if(_state_16==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"method_arguments(_anonymous_0)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_0;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_0.addAll(_token);
			_token__anonymous_0.setValue(_token.getValue());
		}
		_token=_token__anonymous_0;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_0);
			class_variable_names.reject(_position__anonymous_0);
			variable_names.reject(_position__anonymous_0);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_0);
			class_variable_names.accept(_position__anonymous_0);
			variable_names.accept(_position__anonymous_0);
		}
	}
	public void parse__anonymous_35(){
		int _position__anonymous_35 = -1;
		Token.Parsed _token__anonymous_35 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_35=_position;
		_token__anonymous_35=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='|'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_32.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain |");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_35)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_35;
		}
		else{
			parse__anonymous_36();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_35)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_35;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_35.addAll(_token);
			_token__anonymous_35.setValue(_token.getValue());
		}
		_token=_token__anonymous_35;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_35);
			class_variable_names.reject(_position__anonymous_35);
			variable_names.reject(_position__anonymous_35);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_35);
			class_variable_names.accept(_position__anonymous_35);
			variable_names.accept(_position__anonymous_35);
		}
	}
	public void parse__anonymous_34(){
		int _position__anonymous_34 = -1;
		Token.Parsed _token__anonymous_34 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_34=_position;
		_token__anonymous_34=_token;
		_token=new Tokens.Name.ExceptionToken();
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='*'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_34)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_34;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_34.add(_position__anonymous_34,_token);
		}
		_token=_token__anonymous_34;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_34);
			class_variable_names.reject(_position__anonymous_34);
			variable_names.reject(_position__anonymous_34);
			_state=SUCCESS;
			_position__anonymous_34=_position;
			_token__anonymous_34=_token;
			_token=new Tokens.Name.ExceptionToken();
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_34)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_34;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_34.add(_position__anonymous_34,_token);
			}
			_token=_token__anonymous_34;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_34);
				class_variable_names.reject(_position__anonymous_34);
				variable_names.reject(_position__anonymous_34);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_34);
				class_variable_names.accept(_position__anonymous_34);
				variable_names.accept(_position__anonymous_34);
			}
		}
	}
	public void parse__anonymous_37(){
		int _position__anonymous_37 = -1;
		Token.Parsed _token__anonymous_37 = null;
		int _position_method_body = -1;
		Token.Parsed _token_method_body = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_37=_position;
		_token__anonymous_37=_token;
		_token=new Token.Parsed("$ANON");
		_token_method_body=_token;
		_token=new Tokens.Name.AsBodyToken();
		_position_method_body=_position;
		parse_method_body();
		if(_state==SUCCESS){
			_token_method_body.add(_position_method_body,_token);
		}
		_token=_token_method_body;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_37)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_37;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_37.addAll(_token);
			_token__anonymous_37.setValue(_token.getValue());
		}
		_token=_token__anonymous_37;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_37);
			class_variable_names.reject(_position__anonymous_37);
			variable_names.reject(_position__anonymous_37);
			_state=SUCCESS;
			_position__anonymous_37=_position;
			_token__anonymous_37=_token;
			_token=new Token.Parsed("$ANON");
			parse_statement_as_method();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_37)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_37;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_37.addAll(_token);
				_token__anonymous_37.setValue(_token.getValue());
			}
			_token=_token__anonymous_37;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_37);
				class_variable_names.reject(_position__anonymous_37);
				variable_names.reject(_position__anonymous_37);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_37);
				class_variable_names.accept(_position__anonymous_37);
				variable_names.accept(_position__anonymous_37);
			}
		}
	}
	public void parse__anonymous_36(){
		int _position__anonymous_36 = -1;
		Token.Parsed _token__anonymous_36 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_36=_position;
		_token__anonymous_36=_token;
		_token=new Tokens.Name.ExceptionToken();
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='*'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_31.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain *");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_36)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_36;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_36.add(_position__anonymous_36,_token);
		}
		_token=_token__anonymous_36;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_36);
			class_variable_names.reject(_position__anonymous_36);
			variable_names.reject(_position__anonymous_36);
			_state=SUCCESS;
			_position__anonymous_36=_position;
			_token__anonymous_36=_token;
			_token=new Tokens.Name.ExceptionToken();
			parse_NAME();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_36)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_36;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_36.add(_position__anonymous_36,_token);
			}
			_token=_token__anonymous_36;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_36);
				class_variable_names.reject(_position__anonymous_36);
				variable_names.reject(_position__anonymous_36);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_36);
				class_variable_names.accept(_position__anonymous_36);
				variable_names.accept(_position__anonymous_36);
			}
		}
	}
	public void parse__anonymous_39(){
		int _position__anonymous_39 = -1;
		Token.Parsed _token__anonymous_39 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_39=_position;
		_token__anonymous_39=_token;
		_token=new Token.Parsed("$ANON");
		int _state_48 = _state;
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
		if(_state_48==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_39)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_39;
		}
		else{
			parse_OPERATOR();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_39)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_39;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_39.addAll(_token);
			_token__anonymous_39.setValue(_token.getValue());
		}
		_token=_token__anonymous_39;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_39);
			class_variable_names.reject(_position__anonymous_39);
			variable_names.reject(_position__anonymous_39);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_39);
			class_variable_names.accept(_position__anonymous_39);
			variable_names.accept(_position__anonymous_39);
		}
	}
	public void parse__anonymous_38(){
		int _position__anonymous_38 = -1;
		Token.Parsed _token__anonymous_38 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_38=_position;
		_token__anonymous_38=_token;
		_token=new Token.Parsed("$ANON");
		parse__anonymous_39();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_38)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_38;
		}
		else{
			parse_body_call();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_statement(_anonymous_38)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_38;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_38.addAll(_token);
			_token__anonymous_38.setValue(_token.getValue());
		}
		_token=_token__anonymous_38;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_38);
			class_variable_names.reject(_position__anonymous_38);
			variable_names.reject(_position__anonymous_38);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_38);
			class_variable_names.accept(_position__anonymous_38);
			variable_names.accept(_position__anonymous_38);
		}
	}
	public void parse__anonymous_31(){
		int _position__anonymous_31 = -1;
		Token.Parsed _token__anonymous_31 = null;
		int _position_method_body = -1;
		Token.Parsed _token_method_body = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_31=_position;
		_token__anonymous_31=_token;
		_token=new Token.Parsed("$ANON");
		_token_method_body=_token;
		_token=new Tokens.Name.AsBodyToken();
		_position_method_body=_position;
		parse_method_body();
		if(_state==SUCCESS){
			_token_method_body.add(_position_method_body,_token);
		}
		_token=_token_method_body;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_31)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_31;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_31.addAll(_token);
			_token__anonymous_31.setValue(_token.getValue());
		}
		_token=_token__anonymous_31;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_31);
			class_variable_names.reject(_position__anonymous_31);
			variable_names.reject(_position__anonymous_31);
			_state=SUCCESS;
			_position__anonymous_31=_position;
			_token__anonymous_31=_token;
			_token=new Token.Parsed("$ANON");
			parse_statement_as_method();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_31)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_31;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_31.addAll(_token);
				_token__anonymous_31.setValue(_token.getValue());
			}
			_token=_token__anonymous_31;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_31);
				class_variable_names.reject(_position__anonymous_31);
				variable_names.reject(_position__anonymous_31);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_31);
				class_variable_names.accept(_position__anonymous_31);
				variable_names.accept(_position__anonymous_31);
			}
		}
	}
	public void parse__anonymous_30(){
		int _position__anonymous_30 = -1;
		Token.Parsed _token__anonymous_30 = null;
		int _position_method_body = -1;
		Token.Parsed _token_method_body = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_30=_position;
		_token__anonymous_30=_token;
		_token=new Token.Parsed("$ANON");
		_token_method_body=_token;
		_token=new Tokens.Name.AsBodyToken();
		_position_method_body=_position;
		parse_method_body();
		if(_state==SUCCESS){
			_token_method_body.add(_position_method_body,_token);
		}
		_token=_token_method_body;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_30)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_30;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_30.addAll(_token);
			_token__anonymous_30.setValue(_token.getValue());
		}
		_token=_token__anonymous_30;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_30);
			class_variable_names.reject(_position__anonymous_30);
			variable_names.reject(_position__anonymous_30);
			_state=SUCCESS;
			_position__anonymous_30=_position;
			_token__anonymous_30=_token;
			_token=new Token.Parsed("$ANON");
			parse_statement_as_method();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_30)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_30;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_30.addAll(_token);
				_token__anonymous_30.setValue(_token.getValue());
			}
			_token=_token__anonymous_30;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_30);
				class_variable_names.reject(_position__anonymous_30);
				variable_names.reject(_position__anonymous_30);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_30);
				class_variable_names.accept(_position__anonymous_30);
				variable_names.accept(_position__anonymous_30);
			}
		}
	}
	public void parse__anonymous_29(){
		int _position__anonymous_29 = -1;
		Token.Parsed _token__anonymous_29 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_29=_position;
		_token__anonymous_29=_token;
		_token=new Tokens.Name.ConditionalToken();
		if(_position+2-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='i'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='f'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_21.__SYNTAX__);
			_position=_position+2;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain if");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_29;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_29.add(_position__anonymous_29,_token);
		}
		_token=_token__anonymous_29;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_29);
			class_variable_names.reject(_position__anonymous_29);
			variable_names.reject(_position__anonymous_29);
			_state=SUCCESS;
			_position__anonymous_29=_position;
			_token__anonymous_29=_token;
			_token=new Tokens.Name.ConditionalToken();
			if(_position+4-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='l'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='s'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='e'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_22.__SYNTAX__);
				_position=_position+4;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain else");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_29;
			}
			else{
				if(_position+2-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='f'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_21.__SYNTAX__);
					_position=_position+2;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain if");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_29;
				}
				else{
				}
			}
			if(_state==SUCCESS){
				_token__anonymous_29.add(_position__anonymous_29,_token);
			}
			_token=_token__anonymous_29;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_29);
				class_variable_names.reject(_position__anonymous_29);
				variable_names.reject(_position__anonymous_29);
				_state=SUCCESS;
				_position__anonymous_29=_position;
				_token__anonymous_29=_token;
				_token=new Tokens.Name.ConditionalToken();
				if(_position+5-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='w'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='h'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='i'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='l'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!='e'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_23.__SYNTAX__);
					_position=_position+5;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain while");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_29;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_29.add(_position__anonymous_29,_token);
				}
				_token=_token__anonymous_29;
				if(_state==FAILED){
					class_names.reject(_position__anonymous_29);
					class_variable_names.reject(_position__anonymous_29);
					variable_names.reject(_position__anonymous_29);
					_state=SUCCESS;
					_position__anonymous_29=_position;
					_token__anonymous_29=_token;
					_token=new Tokens.Name.ConditionalToken();
					if(_position+12-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='s'){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!='y'){
							_state=FAILED;
						}
						if(_inputArray[_position+2]!='n'){
							_state=FAILED;
						}
						if(_inputArray[_position+3]!='c'){
							_state=FAILED;
						}
						if(_inputArray[_position+4]!='h'){
							_state=FAILED;
						}
						if(_inputArray[_position+5]!='r'){
							_state=FAILED;
						}
						if(_inputArray[_position+6]!='o'){
							_state=FAILED;
						}
						if(_inputArray[_position+7]!='n'){
							_state=FAILED;
						}
						if(_inputArray[_position+8]!='i'){
							_state=FAILED;
						}
						if(_inputArray[_position+9]!='z'){
							_state=FAILED;
						}
						if(_inputArray[_position+10]!='e'){
							_state=FAILED;
						}
						if(_inputArray[_position+11]!='d'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_24.__SYNTAX__);
						_position=_position+12;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain synchronized");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
							_furthestPosition=_position;
						}
						_position=_position__anonymous_29;
					}
					else{
					}
					if(_state==SUCCESS){
						_token__anonymous_29.add(_position__anonymous_29,_token);
					}
					_token=_token__anonymous_29;
					if(_state==FAILED){
						class_names.reject(_position__anonymous_29);
						class_variable_names.reject(_position__anonymous_29);
						variable_names.reject(_position__anonymous_29);
						_state=SUCCESS;
						_position__anonymous_29=_position;
						_token__anonymous_29=_token;
						_token=new Tokens.Name.ConditionalToken();
						if(_position+6-1 >=_inputLength){
							_state=FAILED;
						}
						else{
							if(_inputArray[_position+0]!='s'){
								_state=FAILED;
							}
							if(_inputArray[_position+1]!='w'){
								_state=FAILED;
							}
							if(_inputArray[_position+2]!='i'){
								_state=FAILED;
							}
							if(_inputArray[_position+3]!='t'){
								_state=FAILED;
							}
							if(_inputArray[_position+4]!='c'){
								_state=FAILED;
							}
							if(_inputArray[_position+5]!='h'){
								_state=FAILED;
							}
						}
						if(_state==SUCCESS){
							_token.add(_position,Tokens.Syntax.syntax_25.__SYNTAX__);
							_position=_position+6;
							while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
								++_position;
							}
						}
						else if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain switch");
								_furthestPosition=_position;
							}
						}
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
								_furthestPosition=_position;
							}
							_position=_position__anonymous_29;
						}
						else{
						}
						if(_state==SUCCESS){
							_token__anonymous_29.add(_position__anonymous_29,_token);
						}
						_token=_token__anonymous_29;
						if(_state==FAILED){
							class_names.reject(_position__anonymous_29);
							class_variable_names.reject(_position__anonymous_29);
							variable_names.reject(_position__anonymous_29);
							_state=SUCCESS;
							_position__anonymous_29=_position;
							_token__anonymous_29=_token;
							_token=new Tokens.Name.ConditionalToken();
							if(_position+4-1 >=_inputLength){
								_state=FAILED;
							}
							else{
								if(_inputArray[_position+0]!='c'){
									_state=FAILED;
								}
								if(_inputArray[_position+1]!='a'){
									_state=FAILED;
								}
								if(_inputArray[_position+2]!='s'){
									_state=FAILED;
								}
								if(_inputArray[_position+3]!='e'){
									_state=FAILED;
								}
							}
							if(_state==SUCCESS){
								_token.add(_position,Tokens.Syntax.syntax_26.__SYNTAX__);
								_position=_position+4;
								while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
									++_position;
								}
							}
							else if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain case");
									_furthestPosition=_position;
								}
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_conditional(_anonymous_29)");
									_furthestPosition=_position;
								}
								_position=_position__anonymous_29;
							}
							else{
							}
							if(_state==SUCCESS){
								_token__anonymous_29.add(_position__anonymous_29,_token);
							}
							_token=_token__anonymous_29;
							if(_state==FAILED){
								class_names.reject(_position__anonymous_29);
								class_variable_names.reject(_position__anonymous_29);
								variable_names.reject(_position__anonymous_29);
							}
							else if(_state==SUCCESS){
								class_names.accept(_position__anonymous_29);
								class_variable_names.accept(_position__anonymous_29);
								variable_names.accept(_position__anonymous_29);
							}
						}
					}
				}
			}
		}
	}
	public void parse__anonymous_22(){
		int _position__anonymous_22 = -1;
		Token.Parsed _token__anonymous_22 = null;
		int _position_type_var = -1;
		Token.Parsed _token_type_var = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_22=_position;
		_token__anonymous_22=_token;
		_token=new Token.Parsed("$ANON");
		_token_type_var=_token;
		_token=new Tokens.Name.InterfaceNameToken();
		_position_type_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_type_var_19.contains(_position)){
			_recursion_protection_type_var_19.add(_position);
			parse_type_var();
			_recursion_protection_type_var_19.remove(_position_type_var);
		}
		else{
			_state=FAILED;
		}
		if(_state==SUCCESS){
			_token_type_var.add(_position_type_var,_token);
		}
		_token=_token_type_var;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_22)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_22;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_22.addAll(_token);
			_token__anonymous_22.setValue(_token.getValue());
		}
		_token=_token__anonymous_22;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_22);
			class_variable_names.reject(_position__anonymous_22);
			variable_names.reject(_position__anonymous_22);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_22);
			class_variable_names.accept(_position__anonymous_22);
			variable_names.accept(_position__anonymous_22);
		}
	}
	public void parse__anonymous_21(){
		int _position__anonymous_21 = -1;
		Token.Parsed _token__anonymous_21 = null;
		int _position_type_var = -1;
		Token.Parsed _token_type_var = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_21=_position;
		_token__anonymous_21=_token;
		_token=new Token.Parsed("$ANON");
		_token_type_var=_token;
		_token=new Tokens.Name.ParentNameToken();
		_position_type_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_type_var_18.contains(_position)){
			_recursion_protection_type_var_18.add(_position);
			parse_type_var();
			_recursion_protection_type_var_18.remove(_position_type_var);
		}
		else{
			_state=FAILED;
		}
		if(_state==SUCCESS){
			_token_type_var.add(_position_type_var,_token);
		}
		_token=_token_type_var;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_21)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_21;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_21.addAll(_token);
			_token__anonymous_21.setValue(_token.getValue());
		}
		_token=_token__anonymous_21;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_21);
			class_variable_names.reject(_position__anonymous_21);
			variable_names.reject(_position__anonymous_21);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_21);
			class_variable_names.accept(_position__anonymous_21);
			variable_names.accept(_position__anonymous_21);
		}
	}
	public void parse__anonymous_24(){
		int _position__anonymous_24 = -1;
		Token.Parsed _token__anonymous_24 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_24=_position;
		_token__anonymous_24=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+4-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='v'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='o'){
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='i'){
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='d'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_19.__SYNTAX__);
			_position=_position+4;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain void");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_24)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_24;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_24.addAll(_token);
			_token__anonymous_24.setValue(_token.getValue());
		}
		_token=_token__anonymous_24;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_24);
			class_variable_names.reject(_position__anonymous_24);
			variable_names.reject(_position__anonymous_24);
			_state=SUCCESS;
			_position__anonymous_24=_position;
			_token__anonymous_24=_token;
			_token=new Token.Parsed("$ANON");
			parse_method_argument();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_24)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_24;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_24.addAll(_token);
				_token__anonymous_24.setValue(_token.getValue());
			}
			_token=_token__anonymous_24;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_24);
				class_variable_names.reject(_position__anonymous_24);
				variable_names.reject(_position__anonymous_24);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_24);
				class_variable_names.accept(_position__anonymous_24);
				variable_names.accept(_position__anonymous_24);
			}
		}
	}
	public void parse__anonymous_23(){
		int _position__anonymous_23 = -1;
		Token.Parsed _token__anonymous_23 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_23=_position;
		_token__anonymous_23=_token;
		_token=new Tokens.Name.BodyReturnToken();
		if(_position+6-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='r'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='e'){
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='t'){
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='u'){
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='r'){
				_state=FAILED;
			}
			if(_inputArray[_position+5]!='n'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_18.__SYNTAX__);
			_position=_position+6;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain return");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_23)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_23;
		}
		else{
			parse__anonymous_24();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_23)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_23;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!=';'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_23)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_23;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_23.add(_position__anonymous_23,_token);
		}
		_token=_token__anonymous_23;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_23);
			class_variable_names.reject(_position__anonymous_23);
			variable_names.reject(_position__anonymous_23);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_23);
			class_variable_names.accept(_position__anonymous_23);
			variable_names.accept(_position__anonymous_23);
		}
	}
	public void parse__anonymous_26(){
		int _position__anonymous_26 = -1;
		Token.Parsed _token__anonymous_26 = null;
		int _position_variable_declaration = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_26=_position;
		_token__anonymous_26=_token;
		_token=new Tokens.Name.BodyLineToken();
		_position_variable_declaration=_position;
		if(_state==SUCCESS&&!_recursion_protection_variable_declaration_26.contains(_position)){
			_recursion_protection_variable_declaration_26.add(_position);
			parse_variable_declaration();
			_recursion_protection_variable_declaration_26.remove(_position_variable_declaration);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_26)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_26;
		}
		else{
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=';'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_26)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_26;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_26.add(_position__anonymous_26,_token);
		}
		_token=_token__anonymous_26;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_26);
			class_variable_names.reject(_position__anonymous_26);
			variable_names.reject(_position__anonymous_26);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_26);
			class_variable_names.accept(_position__anonymous_26);
			variable_names.accept(_position__anonymous_26);
		}
	}
	public void parse__anonymous_25(){
		int _position__anonymous_25 = -1;
		Token.Parsed _token__anonymous_25 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_25=_position;
		_token__anonymous_25=_token;
		_token=new Tokens.Name.BodyThrowToken();
		if(_position+5-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='t'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='h'){
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='r'){
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='o'){
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='w'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_20.__SYNTAX__);
			_position=_position+5;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain throw");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_25)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_25;
		}
		else{
			parse_body_statement();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_25)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_25;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!=';'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_25)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_25;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_25.add(_position__anonymous_25,_token);
		}
		_token=_token__anonymous_25;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_25);
			class_variable_names.reject(_position__anonymous_25);
			variable_names.reject(_position__anonymous_25);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_25);
			class_variable_names.accept(_position__anonymous_25);
			variable_names.accept(_position__anonymous_25);
		}
	}
	public void parse__anonymous_28(){
		int _position__anonymous_28 = -1;
		Token.Parsed _token__anonymous_28 = null;
		int _position_body_statement = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_28=_position;
		_token__anonymous_28=_token;
		_token=new Tokens.Name.BodyLineToken();
		_position_body_statement=_position;
		if(_state==SUCCESS&&!_recursion_protection_body_statement_27.contains(_position)){
			_recursion_protection_body_statement_27.add(_position);
			parse_body_statement();
			_recursion_protection_body_statement_27.remove(_position_body_statement);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_28)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_28;
		}
		else{
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=';'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_28)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_28;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_28.add(_position__anonymous_28,_token);
		}
		_token=_token__anonymous_28;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_28);
			class_variable_names.reject(_position__anonymous_28);
			variable_names.reject(_position__anonymous_28);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_28);
			class_variable_names.accept(_position__anonymous_28);
			variable_names.accept(_position__anonymous_28);
		}
	}
	public void parse__anonymous_27(){
		int _position__anonymous_27 = -1;
		Token.Parsed _token__anonymous_27 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_27=_position;
		_token__anonymous_27=_token;
		_token=new Tokens.Name.BodyLineToken();
		parse_variable_assignment();
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_27)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_27;
		}
		else{
			if(_position+1-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!=';'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_2.__SYNTAX__);
				_position=_position+1;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ;");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"body_element(_anonymous_27)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_27;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_27.add(_position__anonymous_27,_token);
		}
		_token=_token__anonymous_27;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_27);
			class_variable_names.reject(_position__anonymous_27);
			variable_names.reject(_position__anonymous_27);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_27);
			class_variable_names.accept(_position__anonymous_27);
			variable_names.accept(_position__anonymous_27);
		}
	}
	public void parse__anonymous_20(){
		int _position__anonymous_20 = -1;
		Token.Parsed _token__anonymous_20 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_20=_position;
		_token__anonymous_20=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='<'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain <");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_20)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_20;
		}
		else{
			parse_NAME();
			if(_state==SUCCESS){
				String _value = _token.getLastValue();
				if(_value!=null){
					class_variable_names.add(_value);
				}
				_token.add(_position,new Tokens.Name.TemplateTypeNameToken(_value));
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_20)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_20;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='>'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain >");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_20)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_20;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_20.addAll(_token);
			_token__anonymous_20.setValue(_token.getValue());
		}
		_token=_token__anonymous_20;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_20);
			class_variable_names.reject(_position__anonymous_20);
			variable_names.reject(_position__anonymous_20);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_20);
			class_variable_names.accept(_position__anonymous_20);
			variable_names.accept(_position__anonymous_20);
		}
	}
	public void parse__anonymous_19(){
		int _position__anonymous_19 = -1;
		Token.Parsed _token__anonymous_19 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_19=_position;
		_token__anonymous_19=_token;
		_token=new Tokens.Name.ClassNameToken();
		if(_pass==FIRST_PASS){
			String _result = _preparsed_NAME.get(_position);
			if(_result==null){
				_state=FAILED;
			}
			else{
				Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
				_token.add(_position,_first_pass_token);
				_position+=_result.length();
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
		}
		else if(_pass==SECOND_PASS){
			_list_name_result=_preparsed_NAME.get(_position);
			if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
				if(_position+_list_name_result.length()<_inputLength){
					int _next_char = _inputArray[_position+_list_name_result.length()];
					if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
					_position+=_list_name_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
					_furthestPosition=_position;
				}
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_19)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_19;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_19.add(_position__anonymous_19,_token);
		}
		_token=_token__anonymous_19;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_19);
			class_variable_names.reject(_position__anonymous_19);
			variable_names.reject(_position__anonymous_19);
			_state=SUCCESS;
			_position__anonymous_19=_position;
			_token__anonymous_19=_token;
			_token=new Tokens.Name.ClassNameToken();
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&class_variable_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_19)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_19;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_19.add(_position__anonymous_19,_token);
			}
			_token=_token__anonymous_19;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_19);
				class_variable_names.reject(_position__anonymous_19);
				variable_names.reject(_position__anonymous_19);
				_state=SUCCESS;
				_position__anonymous_19=_position;
				_token__anonymous_19=_token;
				_token=new Tokens.Name.ClassNameToken();
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_19)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_19;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_19.add(_position__anonymous_19,_token);
				}
				_token=_token__anonymous_19;
				if(_state==FAILED){
					class_names.reject(_position__anonymous_19);
					class_variable_names.reject(_position__anonymous_19);
					variable_names.reject(_position__anonymous_19);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position__anonymous_19);
					class_variable_names.accept(_position__anonymous_19);
					variable_names.accept(_position__anonymous_19);
				}
			}
		}
	}
	public void parse__anonymous_18(){
		int _position__anonymous_18 = -1;
		Token.Parsed _token__anonymous_18 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_18=_position;
		_token__anonymous_18=_token;
		_token=new Tokens.Name.ObjectTypeToken();
		if(_position+6-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='c'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='l'){
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='a'){
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='s'){
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='s'){
				_state=FAILED;
			}
			if(_inputArray[_position+5]!=' '){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
			_position=_position+6;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain class ");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_18)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_18;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_18.add(_position__anonymous_18,_token);
		}
		_token=_token__anonymous_18;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_18);
			class_variable_names.reject(_position__anonymous_18);
			variable_names.reject(_position__anonymous_18);
			_state=SUCCESS;
			_position__anonymous_18=_position;
			_token__anonymous_18=_token;
			_token=new Tokens.Name.ObjectTypeToken();
			if(_position+10-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='n'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='f'){
					_state=FAILED;
				}
				if(_inputArray[_position+6]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+7]!='c'){
					_state=FAILED;
				}
				if(_inputArray[_position+8]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+9]!=' '){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_15.__SYNTAX__);
				_position=_position+10;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain interface ");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_18)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_18;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_18.add(_position__anonymous_18,_token);
			}
			_token=_token__anonymous_18;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_18);
				class_variable_names.reject(_position__anonymous_18);
				variable_names.reject(_position__anonymous_18);
				_state=SUCCESS;
				_position__anonymous_18=_position;
				_token__anonymous_18=_token;
				_token=new Tokens.Name.ObjectTypeToken();
				if(_position+5-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='n'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='u'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='m'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!=' '){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_16.__SYNTAX__);
					_position=_position+5;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain enum ");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_18)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_18;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_18.add(_position__anonymous_18,_token);
				}
				_token=_token__anonymous_18;
				if(_state==FAILED){
					class_names.reject(_position__anonymous_18);
					class_variable_names.reject(_position__anonymous_18);
					variable_names.reject(_position__anonymous_18);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position__anonymous_18);
					class_variable_names.accept(_position__anonymous_18);
					variable_names.accept(_position__anonymous_18);
				}
			}
		}
	}
	public void parse__anonymous_11(){
		int _position__anonymous_11 = -1;
		Token.Parsed _token__anonymous_11 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_11=_position;
		_token__anonymous_11=_token;
		_token=new Tokens.Name.ObjectTypeToken();
		if(_position+6-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='c'){
				_state=FAILED;
			}
			if(_inputArray[_position+1]!='l'){
				_state=FAILED;
			}
			if(_inputArray[_position+2]!='a'){
				_state=FAILED;
			}
			if(_inputArray[_position+3]!='s'){
				_state=FAILED;
			}
			if(_inputArray[_position+4]!='s'){
				_state=FAILED;
			}
			if(_inputArray[_position+5]!=' '){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_14.__SYNTAX__);
			_position=_position+6;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain class ");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_11)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_11;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_11.add(_position__anonymous_11,_token);
		}
		_token=_token__anonymous_11;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_11);
			class_variable_names.reject(_position__anonymous_11);
			variable_names.reject(_position__anonymous_11);
			_state=SUCCESS;
			_position__anonymous_11=_position;
			_token__anonymous_11=_token;
			_token=new Tokens.Name.ObjectTypeToken();
			if(_position+10-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='n'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='t'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!='f'){
					_state=FAILED;
				}
				if(_inputArray[_position+6]!='a'){
					_state=FAILED;
				}
				if(_inputArray[_position+7]!='c'){
					_state=FAILED;
				}
				if(_inputArray[_position+8]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+9]!=' '){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_15.__SYNTAX__);
				_position=_position+10;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain interface ");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_11)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_11;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_11.add(_position__anonymous_11,_token);
			}
			_token=_token__anonymous_11;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_11);
				class_variable_names.reject(_position__anonymous_11);
				variable_names.reject(_position__anonymous_11);
				_state=SUCCESS;
				_position__anonymous_11=_position;
				_token__anonymous_11=_token;
				_token=new Tokens.Name.ObjectTypeToken();
				if(_position+5-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='e'){
						_state=FAILED;
					}
					if(_inputArray[_position+1]!='n'){
						_state=FAILED;
					}
					if(_inputArray[_position+2]!='u'){
						_state=FAILED;
					}
					if(_inputArray[_position+3]!='m'){
						_state=FAILED;
					}
					if(_inputArray[_position+4]!=' '){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_16.__SYNTAX__);
					_position=_position+5;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain enum ");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_11)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_11;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_11.add(_position__anonymous_11,_token);
				}
				_token=_token__anonymous_11;
				if(_state==FAILED){
					class_names.reject(_position__anonymous_11);
					class_variable_names.reject(_position__anonymous_11);
					variable_names.reject(_position__anonymous_11);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position__anonymous_11);
					class_variable_names.accept(_position__anonymous_11);
					variable_names.accept(_position__anonymous_11);
				}
			}
		}
	}
	public void parse__anonymous_10(){
		int _position__anonymous_10 = -1;
		Token.Parsed _token__anonymous_10 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_10=_position;
		_token__anonymous_10=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='>'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain >");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(_anonymous_10)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_10;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_10.addAll(_token);
			_token__anonymous_10.setValue(_token.getValue());
		}
		_token=_token__anonymous_10;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_10);
			class_variable_names.reject(_position__anonymous_10);
			variable_names.reject(_position__anonymous_10);
			_state=SUCCESS;
			_position__anonymous_10=_position;
			_token__anonymous_10=_token;
			_token=new Token.Parsed("$ANON");
			if(_position+6-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='i'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='n'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='n'){
					_state=FAILED;
				}
				if(_inputArray[_position+3]!='e'){
					_state=FAILED;
				}
				if(_inputArray[_position+4]!='r'){
					_state=FAILED;
				}
				if(_inputArray[_position+5]!=' '){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_12.__SYNTAX__);
				_position=_position+6;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain inner ");
					_furthestPosition=_position;
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"inner(_anonymous_10)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_10;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_10.addAll(_token);
				_token__anonymous_10.setValue(_token.getValue());
			}
			_token=_token__anonymous_10;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_10);
				class_variable_names.reject(_position__anonymous_10);
				variable_names.reject(_position__anonymous_10);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_10);
				class_variable_names.accept(_position__anonymous_10);
				variable_names.accept(_position__anonymous_10);
			}
		}
	}
	public void parse__anonymous_13(){
		int _position__anonymous_13 = -1;
		Token.Parsed _token__anonymous_13 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_13=_position;
		_token__anonymous_13=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='.'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_10.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain .");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_13)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_13;
		}
		else{
			parse_packageName();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_13)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_13;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_13.addAll(_token);
			_token__anonymous_13.setValue(_token.getValue());
		}
		_token=_token__anonymous_13;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_13);
			class_variable_names.reject(_position__anonymous_13);
			variable_names.reject(_position__anonymous_13);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_13);
			class_variable_names.accept(_position__anonymous_13);
			variable_names.accept(_position__anonymous_13);
		}
	}
	public void parse__anonymous_12(){
		int _position__anonymous_12 = -1;
		Token.Parsed _token__anonymous_12 = null;
		int _position_packageName = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_12=_position;
		_token__anonymous_12=_token;
		_token=new Token.Parsed("$ANON");
		_position_packageName=_position;
		if(_state==SUCCESS&&!_recursion_protection_packageName_14.contains(_position)){
			_recursion_protection_packageName_14.add(_position);
			parse_packageName();
			_recursion_protection_packageName_14.remove(_position_packageName);
		}
		else{
			_state=FAILED;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_12)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_12;
		}
		else{
			int _state_28 = _state;
			while(_position<_inputLength){
				parse__anonymous_13();
				if(_state==FAILED){
					break;
				}
			}
			if(_state_28==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_12)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_12;
			}
			else{
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_12.addAll(_token);
			_token__anonymous_12.setValue(_token.getValue());
		}
		_token=_token__anonymous_12;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_12);
			class_variable_names.reject(_position__anonymous_12);
			variable_names.reject(_position__anonymous_12);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_12);
			class_variable_names.accept(_position__anonymous_12);
			variable_names.accept(_position__anonymous_12);
		}
	}
	public void parse__anonymous_15(){
		int _position__anonymous_15 = -1;
		Token.Parsed _token__anonymous_15 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_15=_position;
		_token__anonymous_15=_token;
		_token=new Token.Parsed("$ANON");
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='<'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_6.__SYNTAX__);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain <");
				_furthestPosition=_position;
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_15)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_15;
		}
		else{
			parse_NAME();
			if(_state==SUCCESS){
				String _value = _token.getLastValue();
				if(_value!=null){
					class_variable_names.add(_value);
				}
				_token.add(_position,new Tokens.Name.TemplateTypeNameToken(_value));
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_15)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_15;
			}
			else{
				if(_position+1-1 >=_inputLength){
					_state=FAILED;
				}
				else{
					if(_inputArray[_position+0]!='>'){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,Tokens.Syntax.syntax_11.__SYNTAX__);
					_position=_position+1;
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
				else if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain >");
						_furthestPosition=_position;
					}
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_15)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_15;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_15.addAll(_token);
			_token__anonymous_15.setValue(_token.getValue());
		}
		_token=_token__anonymous_15;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_15);
			class_variable_names.reject(_position__anonymous_15);
			variable_names.reject(_position__anonymous_15);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_15);
			class_variable_names.accept(_position__anonymous_15);
			variable_names.accept(_position__anonymous_15);
		}
	}
	public void parse__anonymous_14(){
		int _position__anonymous_14 = -1;
		Token.Parsed _token__anonymous_14 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_14=_position;
		_token__anonymous_14=_token;
		_token=new Tokens.Name.ClassNameToken();
		if(_pass==FIRST_PASS){
			String _result = _preparsed_NAME.get(_position);
			if(_result==null){
				_state=FAILED;
			}
			else{
				Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
				_token.add(_position,_first_pass_token);
				_position+=_result.length();
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
		}
		else if(_pass==SECOND_PASS){
			_list_name_result=_preparsed_NAME.get(_position);
			if(_list_name_result!=null&&variable_names.contains(_list_name_result)){
				if(_position+_list_name_result.length()<_inputLength){
					int _next_char = _inputArray[_position+_list_name_result.length()];
					if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,new Tokens.Name.VariableNamesToken(_list_name_result));
					_position+=_list_name_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_names");
					_furthestPosition=_position;
				}
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_14)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_14;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_14.add(_position__anonymous_14,_token);
		}
		_token=_token__anonymous_14;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_14);
			class_variable_names.reject(_position__anonymous_14);
			variable_names.reject(_position__anonymous_14);
			_state=SUCCESS;
			_position__anonymous_14=_position;
			_token__anonymous_14=_token;
			_token=new Tokens.Name.ClassNameToken();
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&class_variable_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_14)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_14;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_14.add(_position__anonymous_14,_token);
			}
			_token=_token__anonymous_14;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_14);
				class_variable_names.reject(_position__anonymous_14);
				variable_names.reject(_position__anonymous_14);
				_state=SUCCESS;
				_position__anonymous_14=_position;
				_token__anonymous_14=_token;
				_token=new Tokens.Name.ClassNameToken();
				parse_NAME();
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_14)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_14;
				}
				else{
				}
				if(_state==SUCCESS){
					_token__anonymous_14.add(_position__anonymous_14,_token);
				}
				_token=_token__anonymous_14;
				if(_state==FAILED){
					class_names.reject(_position__anonymous_14);
					class_variable_names.reject(_position__anonymous_14);
					variable_names.reject(_position__anonymous_14);
				}
				else if(_state==SUCCESS){
					class_names.accept(_position__anonymous_14);
					class_variable_names.accept(_position__anonymous_14);
					variable_names.accept(_position__anonymous_14);
				}
			}
		}
	}
	public void parse__anonymous_17(){
		int _position__anonymous_17 = -1;
		Token.Parsed _token__anonymous_17 = null;
		int _position_type_var = -1;
		Token.Parsed _token_type_var = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_17=_position;
		_token__anonymous_17=_token;
		_token=new Token.Parsed("$ANON");
		_token_type_var=_token;
		_token=new Tokens.Name.InterfaceNameToken();
		_position_type_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_type_var_16.contains(_position)){
			_recursion_protection_type_var_16.add(_position);
			parse_type_var();
			_recursion_protection_type_var_16.remove(_position_type_var);
		}
		else{
			_state=FAILED;
		}
		if(_state==SUCCESS){
			_token_type_var.add(_position_type_var,_token);
		}
		_token=_token_type_var;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_17)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_17;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_17.addAll(_token);
			_token__anonymous_17.setValue(_token.getValue());
		}
		_token=_token__anonymous_17;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_17);
			class_variable_names.reject(_position__anonymous_17);
			variable_names.reject(_position__anonymous_17);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_17);
			class_variable_names.accept(_position__anonymous_17);
			variable_names.accept(_position__anonymous_17);
		}
	}
	public void parse__anonymous_16(){
		int _position__anonymous_16 = -1;
		Token.Parsed _token__anonymous_16 = null;
		int _position_type_var = -1;
		Token.Parsed _token_type_var = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_16=_position;
		_token__anonymous_16=_token;
		_token=new Token.Parsed("$ANON");
		_token_type_var=_token;
		_token=new Tokens.Name.ParentNameToken();
		_position_type_var=_position;
		if(_state==SUCCESS&&!_recursion_protection_type_var_15.contains(_position)){
			_recursion_protection_type_var_15.add(_position);
			parse_type_var();
			_recursion_protection_type_var_15.remove(_position_type_var);
		}
		else{
			_state=FAILED;
		}
		if(_state==SUCCESS){
			_token_type_var.add(_position_type_var,_token);
		}
		_token=_token_type_var;
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_declaration(_anonymous_16)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_16;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_16.addAll(_token);
			_token__anonymous_16.setValue(_token.getValue());
		}
		_token=_token__anonymous_16;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_16);
			class_variable_names.reject(_position__anonymous_16);
			variable_names.reject(_position__anonymous_16);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_16);
			class_variable_names.accept(_position__anonymous_16);
			variable_names.accept(_position__anonymous_16);
		}
	}
	public void parse__anonymous_91(){
		int _position__anonymous_91 = -1;
		Token.Parsed _token__anonymous_91 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_91=_position;
		_token__anonymous_91=_token;
		_token=new Token.Parsed("$ANON");
		if(_pass==FIRST_PASS){
			String _result = _preparsed_NAME.get(_position);
			if(_result==null){
				_state=FAILED;
			}
			else{
				Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
				_token.add(_position,_first_pass_token);
				_position+=_result.length();
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
		}
		else if(_pass==SECOND_PASS){
			_list_name_result=_preparsed_NAME.get(_position);
			if(_list_name_result!=null&&class_variable_names.contains(_list_name_result)){
				if(_position+_list_name_result.length()<_inputLength){
					int _next_char = _inputArray[_position+_list_name_result.length()];
					if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
						_state=FAILED;
					}
				}
				if(_state==SUCCESS){
					_token.add(_position,new Tokens.Name.ClassVariableNamesToken(_list_name_result));
					_position+=_list_name_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else{
				_state=FAILED;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_variable_names");
					_furthestPosition=_position;
				}
			}
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_91)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_91;
		}
		else{
		}
		if(_state==SUCCESS){
			_token__anonymous_91.addAll(_token);
			_token__anonymous_91.setValue(_token.getValue());
		}
		_token=_token__anonymous_91;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_91);
			class_variable_names.reject(_position__anonymous_91);
			variable_names.reject(_position__anonymous_91);
			_state=SUCCESS;
			_position__anonymous_91=_position;
			_token__anonymous_91=_token;
			_token=new Token.Parsed("$ANON");
			if(_pass==FIRST_PASS){
				String _result = _preparsed_NAME.get(_position);
				if(_result==null){
					_state=FAILED;
				}
				else{
					Token.Parsed _first_pass_token = new Tokens.Name.NAMEToken(_result);
					_token.add(_position,_first_pass_token);
					_position+=_result.length();
					while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
						++_position;
					}
				}
			}
			else if(_pass==SECOND_PASS){
				_list_name_result=_preparsed_NAME.get(_position);
				if(_list_name_result!=null&&class_names.contains(_list_name_result)){
					if(_position+_list_name_result.length()<_inputLength){
						int _next_char = _inputArray[_position+_list_name_result.length()];
						if(_next_char == 95 || (_next_char >= 48 && _next_char <= 57 ) || (_next_char >= 65 && _next_char <= 90 ) || (_next_char >= 97 && _next_char <= 122 )){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,new Tokens.Name.ClassNamesToken(_list_name_result));
						_position+=_list_name_result.length();
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
				}
				else{
					_state=FAILED;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"class_names");
						_furthestPosition=_position;
					}
				}
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_91)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_91;
			}
			else{
			}
			if(_state==SUCCESS){
				_token__anonymous_91.addAll(_token);
				_token__anonymous_91.setValue(_token.getValue());
			}
			_token=_token__anonymous_91;
			if(_state==FAILED){
				class_names.reject(_position__anonymous_91);
				class_variable_names.reject(_position__anonymous_91);
				variable_names.reject(_position__anonymous_91);
			}
			else if(_state==SUCCESS){
				class_names.accept(_position__anonymous_91);
				class_variable_names.accept(_position__anonymous_91);
				variable_names.accept(_position__anonymous_91);
			}
		}
	}
	public void parse__anonymous_90(){
		int _position__anonymous_90 = -1;
		Token.Parsed _token__anonymous_90 = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position__anonymous_90=_position;
		_token__anonymous_90=_token;
		_token=new Tokens.Name.ClassToken();
		int _state_106 = _state;
		if(_position+1-1 >=_inputLength){
			_state=FAILED;
		}
		else{
			if(_inputArray[_position+0]!='^'){
				_state=FAILED;
			}
		}
		if(_state==SUCCESS){
			_token.add(_position,Tokens.Syntax.syntax_54.CAMEL);
			_position=_position+1;
			while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
				++_position;
			}
		}
		else if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ^");
				_furthestPosition=_position;
			}
		}
		if(_state_106==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_90)");
				_furthestPosition=_position;
			}
			_position=_position__anonymous_90;
		}
		else{
			parse__anonymous_91();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_90)");
					_furthestPosition=_position;
				}
				_position=_position__anonymous_90;
			}
			else{
				int _state_107 = _state;
				parse_template_parameters();
				if(_state_107==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"type_var_element(_anonymous_90)");
						_furthestPosition=_position;
					}
					_position=_position__anonymous_90;
				}
				else{
				}
			}
		}
		if(_state==SUCCESS){
			_token__anonymous_90.add(_position__anonymous_90,_token);
		}
		_token=_token__anonymous_90;
		if(_state==FAILED){
			class_names.reject(_position__anonymous_90);
			class_variable_names.reject(_position__anonymous_90);
			variable_names.reject(_position__anonymous_90);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position__anonymous_90);
			class_variable_names.accept(_position__anonymous_90);
			variable_names.accept(_position__anonymous_90);
		}
	}
}