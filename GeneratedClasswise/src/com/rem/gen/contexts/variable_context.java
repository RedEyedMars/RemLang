package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.weak_context;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class variable_context extends weak_context{
	protected Parser __parser__ = null;
	protected Tokens __tokens__ = null;
	public variable_context(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public variable_context() {
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
	public void parse_variable_declaration(){
		int _position_variable_declaration = -1;
		Token.Parsed _token_variable_declaration = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_variable_declaration=_position;
		_token_variable_declaration=_token;
		_token=new Tokens.Rule.VariableDeclarationToken();
		int _state_76 = _state;
		parse_inner();
		if(_state_76==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
				_furthestPosition=_position;
			}
			_position=_position_variable_declaration;
		}
		else{
			int _state_77 = _state;
			parse_weak();
			if(_state_77==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
					_furthestPosition=_position;
				}
				_position=_position_variable_declaration;
			}
			else{
				int _state_78 = _state;
				parse__anonymous_59();
				if(_state_78==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
						_furthestPosition=_position;
					}
					_position=_position_variable_declaration;
				}
				else{
					int _state_79 = _state;
					parse_weak();
					if(_state_79==SUCCESS&&_state==FAILED){
						_state=SUCCESS;
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
							_furthestPosition=_position;
						}
						_position=_position_variable_declaration;
					}
					else{
						parse__anonymous_60();
						if(_state==FAILED){
							if(_position>=_furthestPosition){
								_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
								_furthestPosition=_position;
							}
							_position=_position_variable_declaration;
						}
						else{
							int _state_80 = _state;
							parse__anonymous_61();
							if(_state_80==SUCCESS&&_state==FAILED){
								_state=SUCCESS;
							}
							if(_state==FAILED){
								if(_position>=_furthestPosition){
									_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_declaration(variable_declaration)");
									_furthestPosition=_position;
								}
								_position=_position_variable_declaration;
							}
							else{
							}
						}
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token_variable_declaration.add(_position_variable_declaration,_token);
		}
		_token=_token_variable_declaration;
		if(_state==FAILED){
			class_names.reject(_position_variable_declaration);
			class_variable_names.reject(_position_variable_declaration);
			variable_names.reject(_position_variable_declaration);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position_variable_declaration);
			class_variable_names.accept(_position_variable_declaration);
			variable_names.accept(_position_variable_declaration);
		}
	}
	public void parse_variable_name_definition(){
		int _position_variable_name_definition = -1;
		Token.Parsed _token_variable_name_definition = null;
		int _position_all_type_name = -1;
		Token.Parsed _token_all_type_name = null;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_variable_name_definition=_position;
		_token_variable_name_definition=_token;
		_token=new Tokens.Rule.VariableNameDefinitionToken();
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
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
				_furthestPosition=_position;
			}
			_position=_position_variable_name_definition;
		}
		else{
			int _state_82 = _state;
			if(_position+3-1 >=_inputLength){
				_state=FAILED;
			}
			else{
				if(_inputArray[_position+0]!='.'){
					_state=FAILED;
				}
				if(_inputArray[_position+1]!='.'){
					_state=FAILED;
				}
				if(_inputArray[_position+2]!='.'){
					_state=FAILED;
				}
			}
			if(_state==SUCCESS){
				_token.add(_position,Tokens.Syntax.syntax_41.__SYNTAX__);
				_position=_position+3;
				while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
					++_position;
				}
			}
			else if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain ...");
					_furthestPosition=_position;
				}
			}
			if(_state_82==SUCCESS&&_state==FAILED){
				_state=SUCCESS;
			}
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
					_furthestPosition=_position;
				}
				_position=_position_variable_name_definition;
			}
			else{
				int _state_83 = _state;
				while(_position<_inputLength){
					if(_position+2-1 >=_inputLength){
						_state=FAILED;
					}
					else{
						if(_inputArray[_position+0]!='['){
							_state=FAILED;
						}
						if(_inputArray[_position+1]!=']'){
							_state=FAILED;
						}
					}
					if(_state==SUCCESS){
						_token.add(_position,Tokens.Syntax.syntax_39.ARRAY_TYPE);
						_position=_position+2;
						while(_position<_inputLength&&(false||_inputArray[_position]==' '||_inputArray[_position]=='\t'||_inputArray[_position]=='\n'||_inputArray[_position]=='\r')){
							++_position;
						}
					}
					else if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"unexpected plain []");
							_furthestPosition=_position;
						}
					}
					if(_state==FAILED){
						break;
					}
				}
				if(_state_83==SUCCESS&&_state==FAILED){
					_state=SUCCESS;
				}
				if(_state==FAILED){
					if(_position>=_furthestPosition){
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
						_furthestPosition=_position;
					}
					_position=_position_variable_name_definition;
				}
				else{
					parse__anonymous_62();
					if(_state==SUCCESS){
						String _value = _token.getLastValue();
						if(_value!=null){
							variable_names.add(_value);
						}
					}
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_name_definition(variable_name_definition)");
							_furthestPosition=_position;
						}
						_position=_position_variable_name_definition;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token_variable_name_definition.addAll(_token);
			_token_variable_name_definition.setValue(_token.getValue());
		}
		_token=_token_variable_name_definition;
		if(_state==FAILED){
			class_names.reject(_position_variable_name_definition);
			class_variable_names.reject(_position_variable_name_definition);
			variable_names.reject(_position_variable_name_definition);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position_variable_name_definition);
			class_variable_names.accept(_position_variable_name_definition);
			variable_names.accept(_position_variable_name_definition);
		}
	}
	public void parse_variable_assignment(){
		int _position_variable_assignment = -1;
		Token.Parsed _token_variable_assignment = null;
		int _position_inner = -1;
		class_names.start(_position);
		class_variable_names.start(_position);
		variable_names.start(_position);
		_position_variable_assignment=_position;
		_token_variable_assignment=_token;
		_token=new Tokens.Rule.VariableAssignmentToken();
		int _state_81 = _state;
		_position_inner=_position;
		if(_state==SUCCESS&&!_recursion_protection_inner_45.contains(_position)){
			_recursion_protection_inner_45.add(_position);
			parse_inner();
			_recursion_protection_inner_45.remove(_position_inner);
		}
		else{
			_state=FAILED;
		}
		if(_state_81==SUCCESS&&_state==FAILED){
			_state=SUCCESS;
		}
		if(_state==FAILED){
			if(_position>=_furthestPosition){
				_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
				_furthestPosition=_position;
			}
			_position=_position_variable_assignment;
		}
		else{
			parse_name_var();
			if(_state==FAILED){
				if(_position>=_furthestPosition){
					_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
					_furthestPosition=_position;
				}
				_position=_position_variable_assignment;
			}
			else{
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
						_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
						_furthestPosition=_position;
					}
					_position=_position_variable_assignment;
				}
				else{
					parse_method_argument();
					if(_state==FAILED){
						if(_position>=_furthestPosition){
							_result=new Parser.Result.Fail(FAILED,_position,_lineNumberRanges,_input,_fileName,"variable_assignment(variable_assignment)");
							_furthestPosition=_position;
						}
						_position=_position_variable_assignment;
					}
					else{
					}
				}
			}
		}
		if(_state==SUCCESS){
			_token_variable_assignment.add(_position_variable_assignment,_token);
		}
		_token=_token_variable_assignment;
		if(_state==FAILED){
			class_names.reject(_position_variable_assignment);
			class_variable_names.reject(_position_variable_assignment);
			variable_names.reject(_position_variable_assignment);
		}
		else if(_state==SUCCESS){
			class_names.accept(_position_variable_assignment);
			class_variable_names.accept(_position_variable_assignment);
			variable_names.accept(_position_variable_assignment);
		}
	}
}