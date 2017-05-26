package com.rem.parser.parser;

import java.util.HashMap;
import java.util.Map;

import com.rem.parser.ParseContext;
import com.rem.parser.token.IToken;

public class DefineParser extends ConcreteRule{


	private IParser subParser;
	private Map<String, Parameter<?>> settings = new HashMap<String,Parameter<?>>();
	private Map<String, Event> events = new HashMap<String, Event>();
	public DefineParser(IParser initialParser, String name){
		super(name);
		subParser = initialParser;
		events.put("START", new EmptyEvent("START"));
		events.put("END", new EmptyEvent("END"));
	}
	@Override
	public void setup(){

	}
	@Override
	public void real_parse(ParseContext data) {
		subParser.parse(data);
		if(data.isValid()){
			Map<String, Parameter<?>> parameters = new HashMap<String, Parameter<?>>();
			for(String key:settings.keySet()){
				parameters.put(key, settings.get(key));
			}
			IToken token = data.getToken();
			events.get("START").onValidate(token, parameters);
			for(IToken.Key key:token.keySet()){
				Event event = events.get(key.getName());
				if(event!=null){
					event.onValidate(token.get(key), parameters);
				}
			}
			events.get("END").onValidate(token, parameters);
		}
	}
	public void addParameter(String parameterName, Parameter<?> argument){
		settings.put(parameterName, argument);
	}
	public void addEvent(Event event){
		events.put(event.getName(), event);
	}
	public void accept(Map<String,Parameter<?>> parameters, String parameterName, ParserParameter argument){
		if(parameters.containsKey(parameterName)){
			Parameter<?> parameter = parameters.get(parameterName);
			Object value = parameter.getValue();
			if(value instanceof ConcreteListParser){
				((ConcreteListParser)value).add(argument.getValue());
			}
			else if(value instanceof ConcreteRule){
				ConcreteRule rule = ((ConcreteRule)value);
				if(rule.subParser==null||!(rule.subParser instanceof ConcreteListParser)){
					rule.subParser = argument.getValue();
				}
				else {
					((ConcreteListParser)rule.subParser).add(argument.getValue());
				}
			}
			else {
				parameters.put(parameterName, argument);
			}
		}
		else {
			parameters.put(parameterName, argument);
		}
	}
	public void accept(Map<String,Parameter<?>> parameters, String parameterName, StringParameter argument){
		if(parameters.containsKey(parameterName)){
			if(parameters.get(parameterName) instanceof StringParameter){
				((StringParameter)parameters.get(parameterName)).append(argument.getValue());
			}
			else {
				parameters.put(parameterName, argument);
			}
		}
		else {
			parameters.put(parameterName, argument);
		}		
	}
	public void accept(Map<String,Parameter<?>> parameters, String parameterName, IntParameter argument){
		if(parameters.containsKey(parameterName)){
			if(parameters.get(parameterName) instanceof IntParameter){
				((IntParameter)parameters.get(parameterName)).append(argument.getValue());
			}
			else if(parameters.get(parameterName) instanceof StringParameter){
				((StringParameter)parameters.get(parameterName)).append(argument.getValue().toString());
			}
			else {
				parameters.put(parameterName, argument);
			}
		}
		else {
			parameters.put(parameterName, argument);
		}
	}
	public static abstract class Parameter <Type>{
		protected Type value;
		public Parameter(Type value){
			this.value = value;
		}
		public Type getValue(){
			return value;
		}
	}
	public static class ParserParameter extends Parameter<IParser> {

		public ParserParameter(IParser value) {
			super(value);
		}

	}
	public static class IntParameter extends Parameter<Integer>{
		public IntParameter(Integer value) {
			super(value);
		}
		public void append(Integer value) {
			this.value += value;
		}
	}
	public static class StringParameter extends Parameter<String>{

		private StringBuilder builder;
		private boolean accumulated = true;
		public StringParameter(String value) {
			super(value);
		}

		public void append(String value) {
			if(builder == null){
				builder = new StringBuilder();
				builder.append(this.value);
			}
			builder.append(value);
			accumulated = false;
		}
		
		@Override
		public String getValue(){
			if(accumulated){
				return value;
			}
			else {
				value = builder.toString();
				accumulated = true;
				return value;
			}
		}
	}
	public static abstract class Event {
		private String name;
		public Event(String eventName){
			name = eventName;
		}
		public String getName(){
			return name;
		}
		public abstract void onValidate(IToken successfulToken, Map<String,Parameter<?>> parameters);
	}
	public static class EmptyEvent extends Event{
		public EmptyEvent(String eventName){
			super(eventName);
		}
		public void onValidate(IToken successfulToken, Map<String,Parameter<?>> parameters){

		}
	}
}
