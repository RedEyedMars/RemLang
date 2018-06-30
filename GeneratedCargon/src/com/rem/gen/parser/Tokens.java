package com.rem.gen.parser;
import java.util.*;
import java.io.*;
import java.util.List;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Tokens;

public class Tokens{
	public Tokens() {
	}
	public static class Plain{
		public Plain() {
		}
		public static class Comment extends Token.Parsed{
			protected String value = null;
			public Comment(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public Comment(final String value) {
				this.value = value;
			}
			public Comment() {
			}
			public String getValue() {
				return value;
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._comment;
			}
		}
		public static class Left extends Token.Parsed{
			protected String value = null;
			public Left(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public Left(final String value) {
				this.value = value;
			}
			public Left() {
			}
			public String getValue() {
				return value;
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._left;
			}
		}
		public static class Right extends Token.Parsed{
			protected String value = null;
			public Right(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public Right(final String value) {
				this.value = value;
			}
			public Right() {
			}
			public String getValue() {
				return value;
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._right;
			}
		}
		public static class StandAlone extends Token.Parsed{
			protected String value = null;
			public StandAlone(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public StandAlone(final String value) {
				this.value = value;
			}
			public StandAlone() {
			}
			public String getValue() {
				return value;
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._standAlone;
			}
		}
		public static class Character extends Token.Parsed{
			protected String value = null;
			public Character(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public Character(final String value) {
				this.value = value;
			}
			public Character() {
			}
			public String getValue() {
				return value;
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._character;
			}
		}
		public static class Escaped extends Token.Parsed{
			protected String value = null;
			public Escaped(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public Escaped(final String value) {
				this.value = value;
			}
			public Escaped() {
			}
			public String getValue() {
				return value;
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._escaped;
			}
		}
	}
	public static class Syntax{
		public Syntax() {
		}
		public static class syntax_0 extends Token.Parsed{
			public static Tokens.Syntax.syntax_0 SYNTAX = new Tokens.Syntax.syntax_0(Token.Id._SYNTAX);
			public syntax_0(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_0() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_0 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "ignore";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_1 extends Token.Parsed{
			public static Tokens.Syntax.syntax_1 SYNTAX = new Tokens.Syntax.syntax_1(Token.Id._SYNTAX);
			public syntax_1(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_1() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_1 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return ":";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_2 extends Token.Parsed{
			public static Tokens.Syntax.syntax_2 SYNTAX = new Tokens.Syntax.syntax_2(Token.Id._SYNTAX);
			public syntax_2(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_2() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_2 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "\\n";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_3 extends Token.Parsed{
			public static Tokens.Syntax.syntax_3 SYNTAX = new Tokens.Syntax.syntax_3(Token.Id._SYNTAX);
			public syntax_3(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_3() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_3 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "import ";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_4 extends Token.Parsed{
			public static Tokens.Syntax.syntax_4 SYNTAX = new Tokens.Syntax.syntax_4(Token.Id._SYNTAX);
			public static Token.Parsed negate = new Tokens.Syntax.syntax_4(Token.Id._negate);
			public syntax_4(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_4() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_4 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "^";
			}
			public void setValue(String newValue) {
			}
			public void setNegate(Token.Parsed newNegate) {
				negate = newNegate;
			}
		}
		public static class syntax_5 extends Token.Parsed{
			public static Tokens.Syntax.syntax_5 SYNTAX = new Tokens.Syntax.syntax_5(Token.Id._SYNTAX);
			public syntax_5(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_5() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_5 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "-";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_6 extends Token.Parsed{
			public static Tokens.Syntax.syntax_6 SYNTAX = new Tokens.Syntax.syntax_6(Token.Id._SYNTAX);
			public syntax_6(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_6() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_6 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return ",";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_7 extends Token.Parsed{
			public static Tokens.Syntax.syntax_7 SYNTAX = new Tokens.Syntax.syntax_7(Token.Id._SYNTAX);
			public static Token.Parsed SILENT = new Tokens.Syntax.syntax_7(Token.Id._SILENT);
			public syntax_7(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_7() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_7 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "silent";
			}
			public void setValue(String newValue) {
			}
			public void setSILENT(Token.Parsed newSILENT) {
				SILENT = newSILENT;
			}
		}
		public static class syntax_8 extends Token.Parsed{
			public static Tokens.Syntax.syntax_8 SYNTAX = new Tokens.Syntax.syntax_8(Token.Id._SYNTAX);
			public syntax_8(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_8() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_8 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "@";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_9 extends Token.Parsed{
			public static Tokens.Syntax.syntax_9 SYNTAX = new Tokens.Syntax.syntax_9(Token.Id._SYNTAX);
			public syntax_9(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_9() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_9 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Braced";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_10 extends Token.Parsed{
			public static Tokens.Syntax.syntax_10 SYNTAX = new Tokens.Syntax.syntax_10(Token.Id._SYNTAX);
			public syntax_10(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_10() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_10 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Imports";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_11 extends Token.Parsed{
			public static Tokens.Syntax.syntax_11 SYNTAX = new Tokens.Syntax.syntax_11(Token.Id._SYNTAX);
			public syntax_11(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_11() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_11 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "=";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_12 extends Token.Parsed{
			public static Tokens.Syntax.syntax_12 SYNTAX = new Tokens.Syntax.syntax_12(Token.Id._SYNTAX);
			public syntax_12(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_12() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_12 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Ignore";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_13 extends Token.Parsed{
			public static Tokens.Syntax.syntax_13 SYNTAX = new Tokens.Syntax.syntax_13(Token.Id._SYNTAX);
			public static Token.Parsed ignores_none = new Tokens.Syntax.syntax_13(Token.Id._ignores_none);
			public syntax_13(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_13() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_13 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "None";
			}
			public void setValue(String newValue) {
			}
			public void setIgnoresNone(Token.Parsed newIgnoresNone) {
				ignores_none = newIgnoresNone;
			}
		}
		public static class syntax_14 extends Token.Parsed{
			public static Tokens.Syntax.syntax_14 SYNTAX = new Tokens.Syntax.syntax_14(Token.Id._SYNTAX);
			public syntax_14(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_14() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_14 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "\\n\\t";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_15 extends Token.Parsed{
			public static Tokens.Syntax.syntax_15 SYNTAX = new Tokens.Syntax.syntax_15(Token.Id._SYNTAX);
			public syntax_15(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_15() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_15 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "global";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_16 extends Token.Parsed{
			public static Tokens.Syntax.syntax_16 SYNTAX = new Tokens.Syntax.syntax_16(Token.Id._SYNTAX);
			public syntax_16(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_16() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_16 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "list";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_17 extends Token.Parsed{
			public static Tokens.Syntax.syntax_17 SYNTAX = new Tokens.Syntax.syntax_17(Token.Id._SYNTAX);
			public syntax_17(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_17() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_17 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "with";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_18 extends Token.Parsed{
			public static Tokens.Syntax.syntax_18 SYNTAX = new Tokens.Syntax.syntax_18(Token.Id._SYNTAX);
			public syntax_18(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_18() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_18 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "|";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_19 extends Token.Parsed{
			public static Tokens.Syntax.syntax_19 SYNTAX = new Tokens.Syntax.syntax_19(Token.Id._SYNTAX);
			public syntax_19(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_19() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_19 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "as ";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_20 extends Token.Parsed{
			public static Tokens.Syntax.syntax_20 SYNTAX = new Tokens.Syntax.syntax_20(Token.Id._SYNTAX);
			public syntax_20(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_20() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_20 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "in ";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_21 extends Token.Parsed{
			public static Tokens.Syntax.syntax_21 SYNTAX = new Tokens.Syntax.syntax_21(Token.Id._SYNTAX);
			public static Token.Parsed OPTIONAL = new Tokens.Syntax.syntax_21(Token.Id._OPTIONAL);
			public syntax_21(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_21() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_21 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "?";
			}
			public void setValue(String newValue) {
			}
			public void setOPTIONAL(Token.Parsed newOPTIONAL) {
				OPTIONAL = newOPTIONAL;
			}
		}
		public static class syntax_22 extends Token.Parsed{
			public static Tokens.Syntax.syntax_22 SYNTAX = new Tokens.Syntax.syntax_22(Token.Id._SYNTAX);
			public static Token.Parsed MANY = new Tokens.Syntax.syntax_22(Token.Id._MANY);
			public syntax_22(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_22() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_22 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "*";
			}
			public void setValue(String newValue) {
			}
			public void setMANY(Token.Parsed newMANY) {
				MANY = newMANY;
			}
		}
		public static class syntax_23 extends Token.Parsed{
			public static Tokens.Syntax.syntax_23 SYNTAX = new Tokens.Syntax.syntax_23(Token.Id._SYNTAX);
			public static Token.Parsed PLUS = new Tokens.Syntax.syntax_23(Token.Id._PLUS);
			public syntax_23(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_23() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_23 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "+";
			}
			public void setValue(String newValue) {
			}
			public void setPLUS(Token.Parsed newPLUS) {
				PLUS = newPLUS;
			}
		}
		public static class syntax_24 extends Token.Parsed{
			public static Tokens.Syntax.syntax_24 SYNTAX = new Tokens.Syntax.syntax_24(Token.Id._SYNTAX);
			public static Token.Parsed number = new Tokens.Syntax.syntax_24(Token.Id._number);
			public syntax_24(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_24() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_24 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "\\d";
			}
			public void setValue(String newValue) {
			}
			public void setNumber(Token.Parsed newNumber) {
				number = newNumber;
			}
		}
		public static class syntax_25 extends Token.Parsed{
			public static Tokens.Syntax.syntax_25 SYNTAX = new Tokens.Syntax.syntax_25(Token.Id._SYNTAX);
			public static Token.Parsed dot = new Tokens.Syntax.syntax_25(Token.Id._dot);
			public syntax_25(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_25() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_25 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "\\.";
			}
			public void setValue(String newValue) {
			}
			public void setDot(Token.Parsed newDot) {
				dot = newDot;
			}
		}
		public static class syntax_26 extends Token.Parsed{
			public static Tokens.Syntax.syntax_26 SYNTAX = new Tokens.Syntax.syntax_26(Token.Id._SYNTAX);
			public static Token.Parsed whitespace = new Tokens.Syntax.syntax_26(Token.Id._whitespace);
			public syntax_26(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_26() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_26 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "\\s";
			}
			public void setValue(String newValue) {
			}
			public void setWhitespace(Token.Parsed newWhitespace) {
				whitespace = newWhitespace;
			}
		}
		public static class syntax_27 extends Token.Parsed{
			public static Tokens.Syntax.syntax_27 SYNTAX = new Tokens.Syntax.syntax_27(Token.Id._SYNTAX);
			public static Token.Parsed slash = new Tokens.Syntax.syntax_27(Token.Id._slash);
			public syntax_27(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_27() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_27 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "\\\\";
			}
			public void setValue(String newValue) {
			}
			public void setSlash(Token.Parsed newSlash) {
				slash = newSlash;
			}
		}
	}
	public static class Name{
		public Name() {
		}
		public static class IgnoreCharacterToken extends Token.Parsed{
			protected String value = null;
			public IgnoreCharacterToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public IgnoreCharacterToken(final String value) {
				this.value = value;
			}
			public IgnoreCharacterToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._ignoreCharacter;
			}
		}
		public static class RegexToken extends Token.Parsed{
			protected String value = null;
			public RegexToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public RegexToken(final String value) {
				this.value = value;
			}
			public RegexToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._regex;
			}
		}
		public static class RangeToken extends Token.Parsed{
			protected String value = null;
			public RangeToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public RangeToken(final String value) {
				this.value = value;
			}
			public RangeToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._range;
			}
		}
		public static class PassConstraintToken extends Token.Parsed{
			protected String value = null;
			public PassConstraintToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public PassConstraintToken(final String value) {
				this.value = value;
			}
			public PassConstraintToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._passConstraint;
			}
		}
		public static class BracedParametersToken extends Token.Parsed{
			protected String value = null;
			public BracedParametersToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public BracedParametersToken(final String value) {
				this.value = value;
			}
			public BracedParametersToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._braced_parameters;
			}
		}
		public static class ImportParameterToken extends Token.Parsed{
			protected String value = null;
			public ImportParameterToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ImportParameterToken(final String value) {
				this.value = value;
			}
			public ImportParameterToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._import_parameter;
			}
		}
		public static class RuleNameToken extends Token.Parsed{
			protected String value = null;
			public RuleNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public RuleNameToken(final String value) {
				this.value = value;
			}
			public RuleNameToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._ruleName;
			}
		}
		public static class ImportDefinitionToken extends Token.Parsed{
			protected String value = null;
			public ImportDefinitionToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ImportDefinitionToken(final String value) {
				this.value = value;
			}
			public ImportDefinitionToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._import_definition;
			}
		}
		public static class ChainToken extends Token.Parsed{
			protected String value = null;
			public ChainToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ChainToken(final String value) {
				this.value = value;
			}
			public ChainToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._chain;
			}
		}
		public static class IgnoresCharacterToken extends Token.Parsed{
			protected String value = null;
			public IgnoresCharacterToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public IgnoresCharacterToken(final String value) {
				this.value = value;
			}
			public IgnoresCharacterToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._ignores_character;
			}
		}
		public static class ListNameToken extends Token.Parsed{
			protected String value = null;
			public ListNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ListNameToken(final String value) {
				this.value = value;
			}
			public ListNameToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._listName;
			}
		}
		public static class ListRuleNameToken extends Token.Parsed{
			protected String value = null;
			public ListRuleNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ListRuleNameToken(final String value) {
				this.value = value;
			}
			public ListRuleNameToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._listRuleName;
			}
		}
		public static class ChoiceToken extends Token.Parsed{
			protected String value = null;
			public ChoiceToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ChoiceToken(final String value) {
				this.value = value;
			}
			public ChoiceToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._choice;
			}
		}
		public static class NewNameToken extends Token.Parsed{
			protected String value = null;
			public NewNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public NewNameToken(final String value) {
				this.value = value;
			}
			public NewNameToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._newName;
			}
		}
		public static class MultipleToken extends Token.Parsed{
			protected String value = null;
			public MultipleToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public MultipleToken(final String value) {
				this.value = value;
			}
			public MultipleToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._multiple;
			}
		}
		public static class QuoteTokenToken extends Token.Parsed{
			protected String value = null;
			public QuoteTokenToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public QuoteTokenToken(final String value) {
				this.value = value;
			}
			public QuoteTokenToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._quoteToken;
			}
		}
		public static class RegexTokenToken extends Token.Parsed{
			protected String value = null;
			public RegexTokenToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public RegexTokenToken(final String value) {
				this.value = value;
			}
			public RegexTokenToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._regexToken;
			}
		}
		public static class RuleTokenToken extends Token.Parsed{
			protected String value = null;
			public RuleTokenToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public RuleTokenToken(final String value) {
				this.value = value;
			}
			public RuleTokenToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._ruleToken;
			}
		}
		public static class OptionToken extends Token.Parsed{
			protected String value = null;
			public OptionToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public OptionToken(final String value) {
				this.value = value;
			}
			public OptionToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._option;
			}
		}
		public static class GroupToken extends Token.Parsed{
			protected String value = null;
			public GroupToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public GroupToken(final String value) {
				this.value = value;
			}
			public GroupToken() {
			}
			public String getValue() {
				if(children.isEmpty()) {
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue) {
				value=newValue;
			}
			public Token.Id getName() {
				return Token.Id._group;
			}
		}
	}
	public static class Rule{
		public Rule() {
		}
		public static class BaseToken extends Token.Parsed{
			protected String value = null;
			public BaseToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BaseToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._base;
			}
		}
		public static class CommentsToken extends Token.Parsed{
			protected String value = null;
			public CommentsToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public CommentsToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._comments;
			}
		}
		public static class IgnoresToken extends Token.Parsed{
			protected String value = null;
			public IgnoresToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public IgnoresToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._ignores;
			}
		}
		public static class ImportsToken extends Token.Parsed{
			protected String value = null;
			public ImportsToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ImportsToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._imports;
			}
		}
		public static class ImportsFileImportToken extends Token.Parsed{
			protected String value = null;
			public ImportsFileImportToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ImportsFileImportToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._imports__file_import;
			}
		}
		public static class FILENAMEToken extends Token.Parsed{
			protected String value = null;
			public FILENAMEToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public FILENAMEToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._FILE_NAME;
			}
		}
		public static class NAMEToken extends Token.Parsed{
			protected String value = null;
			public NAMEToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public NAMEToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._NAME;
			}
		}
		public static class OPERATORToken extends Token.Parsed{
			protected String value = null;
			public OPERATORToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public OPERATORToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._OPERATOR;
			}
		}
		public static class NUMBERToken extends Token.Parsed{
			protected String value = null;
			public NUMBERToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public NUMBERToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._NUMBER;
			}
		}
		public static class QuoteToken extends Token.Parsed{
			protected String value = null;
			public QuoteToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public QuoteToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._quote;
			}
		}
		public static class BracedDefinitionToken extends Token.Parsed{
			protected String value = null;
			public BracedDefinitionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BracedDefinitionToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._braced_definition;
			}
		}
		public static class RegexToken extends Token.Parsed{
			protected String value = null;
			public RegexToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RegexToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._regex;
			}
		}
		public static class RegexOptionToken extends Token.Parsed{
			protected String value = null;
			public RegexOptionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RegexOptionToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._regex_option;
			}
		}
		public static class RegexGroupDefinitionToken extends Token.Parsed{
			protected String value = null;
			public RegexGroupDefinitionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RegexGroupDefinitionToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._regex_group_definition;
			}
		}
		public static class SingleIgnoresCharacterToken extends Token.Parsed{
			protected String value = null;
			public SingleIgnoresCharacterToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public SingleIgnoresCharacterToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._single_ignores_character;
			}
		}
		public static class RegexOptionDefinitionToken extends Token.Parsed{
			protected String value = null;
			public RegexOptionDefinitionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RegexOptionDefinitionToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._regex_option_definition;
			}
		}
		public static class RuleParametersToken extends Token.Parsed{
			protected String value = null;
			public RuleParametersToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RuleParametersToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._rule_parameters;
			}
		}
		public static class RuleParamsToken extends Token.Parsed{
			protected String value = null;
			public RuleParamsToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RuleParamsToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._rule_params;
			}
		}
		public static class BaseElementToken extends Token.Parsed{
			protected String value = null;
			public BaseElementToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BaseElementToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._base_element;
			}
		}
		public static class RuleToken extends Token.Parsed{
			protected String value = null;
			public RuleToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RuleToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._rule;
			}
		}
		public static class ListToken extends Token.Parsed{
			protected String value = null;
			public ListToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ListToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._list;
			}
		}
		public static class DefinitionToken extends Token.Parsed{
			protected String value = null;
			public DefinitionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public DefinitionToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._definition;
			}
		}
		public static class ElementToken extends Token.Parsed{
			protected String value = null;
			public ElementToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ElementToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._element;
			}
		}
		public static class AtomToken extends Token.Parsed{
			protected String value = null;
			public AtomToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public AtomToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._atom;
			}
		}
		public static class RegexDefinitionToken extends Token.Parsed{
			protected String value = null;
			public RegexDefinitionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RegexDefinitionToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._regex_definition;
			}
		}
		public static class RegexElementToken extends Token.Parsed{
			protected String value = null;
			public RegexElementToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RegexElementToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._regex_element;
			}
		}
		public static class RegexSpecialToken extends Token.Parsed{
			protected String value = null;
			public RegexSpecialToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public RegexSpecialToken() {
			}
			public String getValue() {
				if(value==null) {
					return super.getValue();
				}
				else {
					return value;
				}
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._regex_special;
			}
		}
	}
}