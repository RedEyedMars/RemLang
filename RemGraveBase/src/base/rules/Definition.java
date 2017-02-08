package base.rules;

import base.lists.Tokens;

import com.rem.parser.AddTokenParser;
import com.rem.parser.ChainParser;
import com.rem.parser.ChoiceParser;
import com.rem.parser.IParser;
import com.rem.parser.IRule;
import com.rem.parser.ManyParser;
import com.rem.parser.MultipleParser;
import com.rem.parser.OptionalParser;

public class Definition extends ChoiceParser implements IRule {
	public static final IParser parser = new Definition();

	private Definition(){
		super();
	}

	@Override
	public void setup() {
		add(
				new AddTokenParser(
						new ChainParser(
								new AddTokenParser(
										new ChainParser(
								Atom.parser,
								new ManyParser(
										Atom.parser)),"chain"),
								new AddTokenParser(
										new OptionalParser(
												new ChainParser(
														new OptionalParser(Tokens.SPACES),
														new ChoiceParser(
																Tokens.PIPE,
																new ChainParser(Tokens.NEWLINE,Tokens.TAB)),
														Definition.parser)),"choice")),"definition"));
		/*
		add(	
				new AddTokenParser(			
				new ChainParser(
						new OptionalParser(Tokens.SPACES),
						Element.parser,
						new AddTokenParser(Parameters.parser,"parameters")),"definition"));*/
	}
}
