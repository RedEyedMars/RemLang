package lang.rules;

import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;

import gen.Generators;

import com.rem.parser.parser.*;
import lists.*;

public class DefineConstructor extends DefineParser {

	public static final IRule parser = new DefineConstructor();
	public DefineConstructor(){
		super(
		new MultipleParser(
				Rules.define_parameter), "define_constructor");
	}
	@Override
	public void setup(){

		addParameter("output", new DefineParser.ParserParameter(
				Rules.defined_method));
		addParameter("input", new DefineParser.ParserParameter(
				Rules.method_parameter));

		addEvent(new DefineParser.Event("START"){
			@Override
			public void onValidate(IToken successfulToken, Map<String,DefineParser.Parameter<?>> parameters){
				accept(parameters, "chain", new DefineParser.ParserParameter(new ChainParser(  )));
				accept(parameters, "parameters", new DefineParser.StringParameter(""));
				accept(parameters, "header", new DefineParser.StringParameter(""));
			}});
		addEvent(new DefineParser.Event("variableName"){
			@Override
			public void onValidate(IToken successfulToken, Map<String,DefineParser.Parameter<?>> parameters){
				accept(parameters, "chain", new DefineParser.ParserParameter(new AddTokenParser( 
					new ChainParser(new PrintParser("CallMETH"),((DefineParser.ParserParameter)parameters.get("input")).getValue()
							),new DefineParser.StringParameter(successfulToken.getString()).getValue() )));
				accept(parameters, "parameters", new DefineParser.StringParameter("v"));
			}});
		addEvent(new DefineParser.Event("namedSyntax"){
			@Override
			public void onValidate(IToken successfulToken, Map<String,DefineParser.Parameter<?>> parameters){
				accept(parameters, "chain", new DefineParser.ParserParameter(new RegexParser( new DefineParser.StringParameter(successfulToken.get("syntaxName").getString()).getValue(),new DefineParser.StringParameter(successfulToken.get("NON_SPACE").getString()).getValue() )));
				accept(parameters, "parameters", new DefineParser.StringParameter("s"));
				accept(parameters, "header", new DefineParser.StringParameter(successfulToken.get("NON_SPACE").getString()));
			}});
		addEvent(new DefineParser.Event("syntax"){
			@Override
			public void onValidate(IToken successfulToken, Map<String,DefineParser.Parameter<?>> parameters){
				accept(parameters, "chain", new DefineParser.ParserParameter(new ExactParser( new DefineParser.StringParameter("syntax").getValue(),new DefineParser.StringParameter(successfulToken.get("NON_SPACE").getString()).getValue() )));
				accept(parameters, "header", new DefineParser.StringParameter(successfulToken.get("NON_SPACE").getString()));
				accept(parameters, "parameters", new DefineParser.StringParameter("s"));
			}});
		addEvent(new DefineParser.Event("END"){
			@Override
			public void onValidate(IToken successfulToken, Map<String,DefineParser.Parameter<?>> parameters){
				accept(parameters, "constructorName", new DefineParser.StringParameter("$"));
				accept(parameters, "constructorName", ((DefineParser.StringParameter)parameters.get("header")));
				accept(parameters, "constructorName", new DefineParser.StringParameter("("));
				accept(parameters, "constructorName", ((DefineParser.StringParameter)parameters.get("parameters")));
				accept(parameters, "constructorName", new DefineParser.StringParameter(")"));
				accept(parameters, "output", new DefineParser.ParserParameter(
						new AddTokenParser(
								((DefineParser.ParserParameter)parameters.get("chain")).getValue(),
								((DefineParser.StringParameter)parameters.get("constructorName")).getValue() ){
							@Override
							public void parse(ParseContext data){
								String name = this.getTokenName();
								String line = data.getLine();

								System.out.println(name+":"+line);
								super.parse(data);
								System.out.println(name+"("+data.isValid()+")");
							}
						}));
			}});
	}

}