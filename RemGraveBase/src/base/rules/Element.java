package base.rules;

import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.MultipleParser;
import com.rem.parser.OptionalParser;
import com.rem.parser.ParseData;
import com.rem.parser.ParseUtil;
import com.rem.parser.IRule;
import base.lists.Braces;
import base.lists.Tokens;
import com.rem.parser.AddTokenParser;
import com.rem.parser.ChainParser;

public class Element extends ChoiceParser implements IRule {

	public static final IParser parser = new Element();


	private Element(){
		super(
				);
	}

	@Override
	public void setup(){
		add(
				new ChoiceParser(
						new AddTokenParser(
								new ChainParser(
										Definition.parser,
										new OptionalParser(Tokens.SPACES),
										new AddTokenParser(
												new ChoiceParser(Tokens.OPTIONAL,Tokens.MANY,Tokens.PLUS),"option")),"multiple"),
						new AddTokenParser(Braces.BRACE,"braced"),
						new AddTokenParser(Terminal.parser,"terminal")));

		/*
		add(new AddTokenParser(
				new ChainParser(
						Definition.parser,new MultipleParser(Definition.parser)),"chain"){
			@Override
			public String toString(){
				return "Element.addChain";
			}
		});
		add(new AddTokenParser(
				new ChainParser(
						Definition.parser,
						new OptionalParser(Tokens.SPACES),
						new ChoiceParser(Tokens.PIPE,new ChainParser(Tokens.NEWLINE,Tokens.TAB)),
						Definition.parser),"choice"){
			@Override
			public String toString(){
				return "Element.addChoice";
			}

		});
		add(new AddTokenParser(
				new ChainParser(
						Definition.parser,new OptionalParser(Tokens.SPACES),
							new AddTokenParser(new ChoiceParser(
								Tokens.OPTIONAL,Tokens.MANY,Tokens.PLUS),"option")),"multiple"){
			@Override
			public String toString(){
				return "Element.addMultiple";
			}
		});
		add(new ChainParser(new AddTokenParser(Braces.BRACE,"braced")){

			@Override
			public String toString(){
				return "Element.addBrace";
			}
		});
		add(new AddTokenParser(Terminal.parser,"terminal"){
			@Override
			public String toString(){
				return "Element.addTerminal";
			}
		});*/
	}

	@Override
	public String toString(){
		return "Element";
	}

}
