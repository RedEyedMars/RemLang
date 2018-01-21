package com.rem.gen.contexts;
import java.util.*;
import java.io.*;
import com.rem.gen.contexts.class_declaration_context;
import com.rem.gen.parser.Parser;
import com.rem.gen.parser.Tokens;

public class FinalContext extends class_declaration_context{
	public FinalContext(final Parser.NameList initalSuperClassNamesRoot, final Parser.NameList initalSuperClassVariableNamesRoot, final Parser.NameList initalSuperVariableNamesRoot) {
		super(initalSuperClassNamesRoot, initalSuperClassVariableNamesRoot, initalSuperVariableNamesRoot);
	}
	public FinalContext() {
	}
}