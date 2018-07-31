package com.rem.gen.parser;
import java.util.ArrayList;
import com.rem.gen.parser.Token;
import java.util.function.Consumer;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;
import java.util.stream.IntStream;
public class Token implements Consumer<Token> {
	protected List<Token> children;
	protected Map<Id, List<Token>> map;
	protected String value;
	protected Id id;
	public List<Token> getChildren(){
		return children;
	}
	public Map<Id, List<Token>> getMap(){
		return map;
	}
	public Id getId(){
		return id;
	}
	public Token (Id id){
		this.id=id;
		this.children=new ArrayList<Token>();
		this.map=new EnumMap<Id, List<Token>>(Id.class);
	}
	public Token (Id id,String value){
		this.id=id;
		if(value!=null){
			this.value=value;
		}
		else{
			this.children=new ArrayList<Token>();
			this.map=new EnumMap<Id, List<Token>>(Id.class);
		}
	}
	public void accept(Token newToken){
		this.children.add(newToken);
		if(this.map.containsKey(newToken.id)==false){
			this.map.put(newToken.id,new ArrayList<Token>());
		}
		this.map.get(newToken.id).add(newToken);
	}
	public void build(Consumer<Token> builder){
		if(children!=null){
			children.forEach(builder);
		}
		else{
			builder.accept(this);
		}
	}
	public Token get(Id id){
		List<Token> result = map.get(id);
		if(result!=null&&result.isEmpty()==false){
			return result.get(0);
		}
		else{
			return null;
		}
	}
	public List<Token> getAll(){
		return children;
	}
	public List<Token> getAllSafely(Id id){
		List<Token> result = map.get(id);
		if(result!=null){
			return result;
		}
		else{
			return new ArrayList<Token>();
		}
	}
	public String toString(){
		if(children==null){
			return value;
		}
		else{
			return children.isEmpty()?null:children.get(0).toString();
		}
	}
	public void print(){
		print(System.out::print);
	}
	public void print(Consumer<String> consumer){
		print(0,consumer);
	}
	public void print(int tabs,Consumer<String> consumer){
		if(id==Token.Id.ROOT){
			if(children!=null){
				children.forEach( C->C.print(tabs,consumer));
			}
			return ;
		}
		if(tabs!=0 ){
			consumer.accept("\n");
		}
		IntStream.range(0,tabs).forEach( I->consumer.accept("  "));
		consumer.accept(id.toString().substring(1));
		if(children==null){
			consumer.accept(":"+value);
		}
		else{
			children.forEach( C->C.print(tabs+1,consumer));
		}
	}
	public Token.Id getName(){
		return id;
	}
	public String getValue(){
		return toString();
	}
	public static enum Id {
		 ROOT,
		 WHITESPACE,
		 _SYNTAX,
		 _base,
		 _comment,
		 _comments,
		 _ignoreCharacter,
		 _ignores,
		 _imports,
		 _FILE_NAME,
		 _NAME,
		 _OPERATOR,
		 _NUMBER,
		 _quote,
		 _braced_definition,
		 _regex,
		 _negate,
		 _regex_option,
		 _regex_group_definition,
		 _single_ignores_character,
		 _range,
		 _left,
		 _right,
		 _standAlone,
		 _regex_option_definition,
		 _rule_parameters,
		 _SILENT,
		 _passConstraint,
		 _braced_parameters,
		 _import_parameter,
		 _ruleName,
		 _import_definition,
		 _chain,
		 _ignores_none,
		 _ignores_character,
		 _rule_params,
		 _base_element,
		 _rule,
		 _listName,
		 _listRuleName,
		 _list,
		 _choice,
		 _definition,
		 _newName,
		 _multiple,
		 _OPTIONAL,
		 _MANY,
		 _PLUS,
		 _element,
		 _quoteToken,
		 _regexToken,
		 _ruleToken,
		 _atom,
		 _regex_definition,
		 _option,
		 _group,
		 _character,
		 _regex_element,
		 _number,
		 _dot,
		 _whitespace,
		 _escaped,
		 _slash,
		 _regex_special
	}
}
