package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.atom_context;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class FinalContext extends atom_context{
	public FinalContext(final Parser.NameList initalSuperListNamesRoot) {
		super(initalSuperListNamesRoot);
	}
	public FinalContext() {
	}
}