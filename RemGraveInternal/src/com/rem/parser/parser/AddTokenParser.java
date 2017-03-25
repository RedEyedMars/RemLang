package com.rem.parser.parser;

import com.rem.parser.ParseContext;
import com.rem.parser.token.IToken;

/**
 * Evaluates the subParser and wraps the subtokens it creates into a {@link com.rem.parser.token.BranchToken}.
 * @author Geoffrey
 *
 */
public class AddTokenParser extends ConcreteParser implements IParser {
	//The Sub Parser to evaluate.
	protected IParser subParser;
	//The name string given to the branch token created by this parser.
	protected String name;
	//If this is silent, it doesn't actually create the token.
	private boolean silent;
	
	/**
	 * Create a place holder parser, contains neither name, nor a subParser.
	 */
	public AddTokenParser(){
		this(null,IParser.DEFAULT);
	}
	/**
	 * Contructor that contructs the Parser, but without the subParser, this needs to be set before compiletime.
	 * @param initialName - Name given to the token created.
	 */
	public AddTokenParser(String initialName){
		this(null,initialName);
	}
	/**
	 * Creates the Parser without a name, just an initial subParser.
	 * @param initialParser - the subParser to be parsed.
	 */
	public AddTokenParser(IParser initialParser){
		this(initialParser,IParser.DEFAULT);
	}
	/**
	 * Creates the complete Parser, with subParser and name.
	 * @param initialParser - the subParser to be evaluated.
	 * @param initialName - the name given to the collection of tokens created by the sub parser.
	 */
	public AddTokenParser(IParser initialParser,String initialName){
		subParser = initialParser;
		name = initialName;

	}
	/**
	 * Sets the sub parser to the given parser.
	 * @param newParser - new parser that will be used in the stead of the previous sub parser.
	 */
	public void set(IParser newParser){
		subParser = newParser;
	}
	
	/**
	 * If true this parser will not create/add any token to wrap the tokens created by the sub parser. Normal behaviour if false.
	 * @param silence - whether or not to actually add a token.
	 */
	public void isSilent(boolean silence){
		this.silent = silence;
	}

	/**
	 * Parses the ParseData at the given position, wrapping the resulting Tokens created into an overarching parent Token.
	 */
	@Override
	public void real_parse(ParseContext data) {
		//If the sub parser is null then do not try to do anything with this parser.
		if(subParser==null) return;
		//if silent, ignore the adding of the parent token.
		if(silent){
			//still parses the sub parser.
			subParser.parse(data);
		}
		else {
			//record the initial position.
			int position = data.getFrontPosition();
			//generate a token that will surround the made tokens.
			IToken token = data.addTokenLayer();
			//parse the sub parser.
			subParser.parse(data);
			//aggragrate the tokens created.
			data.collectTokens();
			//if the sub parser was successful add the created tokens to the aggregration token tree in the data.
			if(data.isValid()){
				//ParseUtil.debug("internal|addtoken",this,"("+ParseUtil.currentParser+"):"+name+":"+token.getValue());
				//Put the created token, with this parser's name into the token tree.
				data.getToken().put(new IToken.Key(name,-1,position), token);
			}
		}
	}
}
