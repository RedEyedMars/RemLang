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
		public static class Exact extends Token.Parsed{
			protected String value = null;
			public Exact(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public Exact(final String value) {
				this.value = value;
			}
			public Exact() {
			}
			public String getValue() {
				return value;
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._exact;
			}
		}
		public static class Value extends Token.Parsed{
			protected String value = null;
			public Value(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public Value(final String value) {
				this.value = value;
			}
			public Value() {
			}
			public String getValue() {
				return value;
			}
			public void setValue(String newValue) {
				value = newValue;
			}
			public Token.Id getName() {
				return Token.Id._value;
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
				return "??";
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
				return "::";
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
				return "!!";
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
				return "%%";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_4 extends Token.Parsed{
			public static Tokens.Syntax.syntax_4 SYNTAX = new Tokens.Syntax.syntax_4(Token.Id._SYNTAX);
			public static Token.Parsed exact = new Tokens.Syntax.syntax_4(Token.Id._exact);
			public syntax_4(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_4() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_4 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "instanceof ";
			}
			public void setValue(String newValue) {
			}
			public void setExact(Token.Parsed newExact) {
				exact = newExact;
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
				return ",";
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
				return "''";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_7 extends Token.Parsed{
			public static Tokens.Syntax.syntax_7 SYNTAX = new Tokens.Syntax.syntax_7(Token.Id._SYNTAX);
			public syntax_7(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_7() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_7 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "'";
			}
			public void setValue(String newValue) {
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
				return "char\\\\";
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
				return "import ";
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
				return "";
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
				return "imports ";
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
				return "<";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_13 extends Token.Parsed{
			public static Tokens.Syntax.syntax_13 SYNTAX = new Tokens.Syntax.syntax_13(Token.Id._SYNTAX);
			public syntax_13(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_13() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_13 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return ".";
			}
			public void setValue(String newValue) {
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
				return ":";
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
				return "|";
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
				return ">";
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
				return "inner ";
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
				return "~";
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
				return "@";
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
				return "static ";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_21 extends Token.Parsed{
			public static Tokens.Syntax.syntax_21 SYNTAX = new Tokens.Syntax.syntax_21(Token.Id._SYNTAX);
			public syntax_21(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_21() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_21 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "class ";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_22 extends Token.Parsed{
			public static Tokens.Syntax.syntax_22 SYNTAX = new Tokens.Syntax.syntax_22(Token.Id._SYNTAX);
			public syntax_22(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_22() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_22 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "interface ";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_23 extends Token.Parsed{
			public static Tokens.Syntax.syntax_23 SYNTAX = new Tokens.Syntax.syntax_23(Token.Id._SYNTAX);
			public syntax_23(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_23() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_23 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "enum ";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_24 extends Token.Parsed{
			public static Tokens.Syntax.syntax_24 SYNTAX = new Tokens.Syntax.syntax_24(Token.Id._SYNTAX);
			public static Token.Parsed weak = new Tokens.Syntax.syntax_24(Token.Id._weak);
			public syntax_24(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_24() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_24 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "?";
			}
			public void setValue(String newValue) {
			}
			public void setWeak(Token.Parsed newWeak) {
				weak = newWeak;
			}
		}
		public static class syntax_25 extends Token.Parsed{
			public static Tokens.Syntax.syntax_25 SYNTAX = new Tokens.Syntax.syntax_25(Token.Id._SYNTAX);
			public syntax_25(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_25() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_25 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "return";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_26 extends Token.Parsed{
			public static Tokens.Syntax.syntax_26 SYNTAX = new Tokens.Syntax.syntax_26(Token.Id._SYNTAX);
			public syntax_26(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_26() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_26 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return ";";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_27 extends Token.Parsed{
			public static Tokens.Syntax.syntax_27 SYNTAX = new Tokens.Syntax.syntax_27(Token.Id._SYNTAX);
			public syntax_27(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_27() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_27 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "throw";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_28 extends Token.Parsed{
			public static Tokens.Syntax.syntax_28 SYNTAX = new Tokens.Syntax.syntax_28(Token.Id._SYNTAX);
			public syntax_28(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_28() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_28 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "if ";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_29 extends Token.Parsed{
			public static Tokens.Syntax.syntax_29 SYNTAX = new Tokens.Syntax.syntax_29(Token.Id._SYNTAX);
			public static Token.Parsed conditional = new Tokens.Syntax.syntax_29(Token.Id._conditional);
			public syntax_29(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_29() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_29 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "else";
			}
			public void setValue(String newValue) {
			}
			public void setConditional(Token.Parsed newConditional) {
				conditional = newConditional;
			}
		}
		public static class syntax_30 extends Token.Parsed{
			public static Tokens.Syntax.syntax_30 SYNTAX = new Tokens.Syntax.syntax_30(Token.Id._SYNTAX);
			public syntax_30(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_30() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_30 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "if";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_31 extends Token.Parsed{
			public static Tokens.Syntax.syntax_31 SYNTAX = new Tokens.Syntax.syntax_31(Token.Id._SYNTAX);
			public syntax_31(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_31() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_31 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "while";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_32 extends Token.Parsed{
			public static Tokens.Syntax.syntax_32 SYNTAX = new Tokens.Syntax.syntax_32(Token.Id._SYNTAX);
			public syntax_32(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_32() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_32 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "synchronized";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_33 extends Token.Parsed{
			public static Tokens.Syntax.syntax_33 SYNTAX = new Tokens.Syntax.syntax_33(Token.Id._SYNTAX);
			public syntax_33(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_33() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_33 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "switch";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_34 extends Token.Parsed{
			public static Tokens.Syntax.syntax_34 SYNTAX = new Tokens.Syntax.syntax_34(Token.Id._SYNTAX);
			public syntax_34(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_34() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_34 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "case";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_35 extends Token.Parsed{
			public static Tokens.Syntax.syntax_35 SYNTAX = new Tokens.Syntax.syntax_35(Token.Id._SYNTAX);
			public static Token.Parsed conditional = new Tokens.Syntax.syntax_35(Token.Id._conditional);
			public syntax_35(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_35() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_35 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "for ";
			}
			public void setValue(String newValue) {
			}
			public void setConditional(Token.Parsed newConditional) {
				conditional = newConditional;
			}
		}
		public static class syntax_36 extends Token.Parsed{
			public static Tokens.Syntax.syntax_36 SYNTAX = new Tokens.Syntax.syntax_36(Token.Id._SYNTAX);
			public static Token.Parsed isFinal = new Tokens.Syntax.syntax_36(Token.Id._isFinal);
			public syntax_36(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_36() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_36 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "!";
			}
			public void setValue(String newValue) {
			}
			public void setIsFinal(Token.Parsed newIsFinal) {
				isFinal = newIsFinal;
			}
		}
		public static class syntax_37 extends Token.Parsed{
			public static Tokens.Syntax.syntax_37 SYNTAX = new Tokens.Syntax.syntax_37(Token.Id._SYNTAX);
			public syntax_37(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_37() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_37 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return ">=";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_38 extends Token.Parsed{
			public static Tokens.Syntax.syntax_38 SYNTAX = new Tokens.Syntax.syntax_38(Token.Id._SYNTAX);
			public syntax_38(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_38() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_38 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "<=";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_39 extends Token.Parsed{
			public static Tokens.Syntax.syntax_39 SYNTAX = new Tokens.Syntax.syntax_39(Token.Id._SYNTAX);
			public static Token.Parsed conditional = new Tokens.Syntax.syntax_39(Token.Id._conditional);
			public syntax_39(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_39() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_39 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "try";
			}
			public void setValue(String newValue) {
			}
			public void setConditional(Token.Parsed newConditional) {
				conditional = newConditional;
			}
		}
		public static class syntax_40 extends Token.Parsed{
			public static Tokens.Syntax.syntax_40 SYNTAX = new Tokens.Syntax.syntax_40(Token.Id._SYNTAX);
			public static Token.Parsed PRINT = new Tokens.Syntax.syntax_40(Token.Id._PRINT);
			public syntax_40(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_40() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_40 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "print";
			}
			public void setValue(String newValue) {
			}
			public void setPRINT(Token.Parsed newPRINT) {
				PRINT = newPRINT;
			}
		}
		public static class syntax_41 extends Token.Parsed{
			public static Tokens.Syntax.syntax_41 SYNTAX = new Tokens.Syntax.syntax_41(Token.Id._SYNTAX);
			public static Token.Parsed conditional = new Tokens.Syntax.syntax_41(Token.Id._conditional);
			public syntax_41(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_41() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_41 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "catch";
			}
			public void setValue(String newValue) {
			}
			public void setConditional(Token.Parsed newConditional) {
				conditional = newConditional;
			}
		}
		public static class syntax_42 extends Token.Parsed{
			public static Tokens.Syntax.syntax_42 SYNTAX = new Tokens.Syntax.syntax_42(Token.Id._SYNTAX);
			public syntax_42(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_42() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_42 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "*";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_43 extends Token.Parsed{
			public static Tokens.Syntax.syntax_43 SYNTAX = new Tokens.Syntax.syntax_43(Token.Id._SYNTAX);
			public syntax_43(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_43() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_43 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "(";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_44 extends Token.Parsed{
			public static Tokens.Syntax.syntax_44 SYNTAX = new Tokens.Syntax.syntax_44(Token.Id._SYNTAX);
			public syntax_44(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_44() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_44 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return ")";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_45 extends Token.Parsed{
			public static Tokens.Syntax.syntax_45 SYNTAX = new Tokens.Syntax.syntax_45(Token.Id._SYNTAX);
			public static Token.Parsed NEW = new Tokens.Syntax.syntax_45(Token.Id._NEW);
			public syntax_45(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_45() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_45 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "new ";
			}
			public void setValue(String newValue) {
			}
			public void setNEW(Token.Parsed newNEW) {
				NEW = newNEW;
			}
		}
		public static class syntax_46 extends Token.Parsed{
			public static Tokens.Syntax.syntax_46 SYNTAX = new Tokens.Syntax.syntax_46(Token.Id._SYNTAX);
			public syntax_46(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_46() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_46 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "%";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_47 extends Token.Parsed{
			public static Tokens.Syntax.syntax_47 SYNTAX = new Tokens.Syntax.syntax_47(Token.Id._SYNTAX);
			public syntax_47(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_47() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_47 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "->*";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_48 extends Token.Parsed{
			public static Tokens.Syntax.syntax_48 SYNTAX = new Tokens.Syntax.syntax_48(Token.Id._SYNTAX);
			public static Token.Parsed add = new Tokens.Syntax.syntax_48(Token.Id._add);
			public syntax_48(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_48() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_48 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "+=";
			}
			public void setValue(String newValue) {
			}
			public void setAdd(Token.Parsed newAdd) {
				add = newAdd;
			}
		}
		public static class syntax_49 extends Token.Parsed{
			public static Tokens.Syntax.syntax_49 SYNTAX = new Tokens.Syntax.syntax_49(Token.Id._SYNTAX);
			public static Token.Parsed get = new Tokens.Syntax.syntax_49(Token.Id._get);
			public syntax_49(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_49() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_49 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "->";
			}
			public void setValue(String newValue) {
			}
			public void setGet(Token.Parsed newGet) {
				get = newGet;
			}
		}
		public static class syntax_50 extends Token.Parsed{
			public static Tokens.Syntax.syntax_50 SYNTAX = new Tokens.Syntax.syntax_50(Token.Id._SYNTAX);
			public syntax_50(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_50() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_50 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "=>";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_51 extends Token.Parsed{
			public static Tokens.Syntax.syntax_51 SYNTAX = new Tokens.Syntax.syntax_51(Token.Id._SYNTAX);
			public syntax_51(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_51() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_51 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "static";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_52 extends Token.Parsed{
			public static Tokens.Syntax.syntax_52 SYNTAX = new Tokens.Syntax.syntax_52(Token.Id._SYNTAX);
			public static Token.Parsed ARRAY_TYPE = new Tokens.Syntax.syntax_52(Token.Id._ARRAY_TYPE);
			public syntax_52(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_52() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_52 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "[]";
			}
			public void setValue(String newValue) {
			}
			public void setARRAYTYPE(Token.Parsed newARRAYTYPE) {
				ARRAY_TYPE = newARRAYTYPE;
			}
		}
		public static class syntax_53 extends Token.Parsed{
			public static Tokens.Syntax.syntax_53 SYNTAX = new Tokens.Syntax.syntax_53(Token.Id._SYNTAX);
			public static Token.Parsed weak = new Tokens.Syntax.syntax_53(Token.Id._weak);
			public syntax_53(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_53() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_53 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "{?}";
			}
			public void setValue(String newValue) {
			}
			public void setWeak(Token.Parsed newWeak) {
				weak = newWeak;
			}
		}
		public static class syntax_54 extends Token.Parsed{
			public static Tokens.Syntax.syntax_54 SYNTAX = new Tokens.Syntax.syntax_54(Token.Id._SYNTAX);
			public syntax_54(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_54() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_54 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "=";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_55 extends Token.Parsed{
			public static Tokens.Syntax.syntax_55 SYNTAX = new Tokens.Syntax.syntax_55(Token.Id._SYNTAX);
			public static Token.Parsed INLINE_LIST = new Tokens.Syntax.syntax_55(Token.Id._INLINE_LIST);
			public syntax_55(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_55() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_55 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "...";
			}
			public void setValue(String newValue) {
			}
			public void setINLINELIST(Token.Parsed newINLINELIST) {
				INLINE_LIST = newINLINELIST;
			}
		}
		public static class syntax_56 extends Token.Parsed{
			public static Tokens.Syntax.syntax_56 SYNTAX = new Tokens.Syntax.syntax_56(Token.Id._SYNTAX);
			public static Token.Parsed typeName = new Tokens.Syntax.syntax_56(Token.Id._typeName);
			public syntax_56(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_56() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_56 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Class ";
			}
			public void setValue(String newValue) {
			}
			public void setTypeName(Token.Parsed newTypeName) {
				typeName = newTypeName;
			}
		}
		public static class syntax_57 extends Token.Parsed{
			public static Tokens.Syntax.syntax_57 SYNTAX = new Tokens.Syntax.syntax_57(Token.Id._SYNTAX);
			public static Token.Parsed OutputClass = new Tokens.Syntax.syntax_57(Token.Id._OutputClass);
			public syntax_57(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_57() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_57 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Class";
			}
			public void setValue(String newValue) {
			}
			public void setOutputClass(Token.Parsed newOutputClass) {
				OutputClass = newOutputClass;
			}
		}
		public static class syntax_58 extends Token.Parsed{
			public static Tokens.Syntax.syntax_58 SYNTAX = new Tokens.Syntax.syntax_58(Token.Id._SYNTAX);
			public static Token.Parsed OutputMethod = new Tokens.Syntax.syntax_58(Token.Id._OutputMethod);
			public syntax_58(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_58() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_58 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Method";
			}
			public void setValue(String newValue) {
			}
			public void setOutputMethod(Token.Parsed newOutputMethod) {
				OutputMethod = newOutputMethod;
			}
		}
		public static class syntax_59 extends Token.Parsed{
			public static Tokens.Syntax.syntax_59 SYNTAX = new Tokens.Syntax.syntax_59(Token.Id._SYNTAX);
			public static Token.Parsed OutputVariable = new Tokens.Syntax.syntax_59(Token.Id._OutputVariable);
			public syntax_59(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_59() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_59 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Variable";
			}
			public void setValue(String newValue) {
			}
			public void setOutputVariable(Token.Parsed newOutputVariable) {
				OutputVariable = newOutputVariable;
			}
		}
		public static class syntax_60 extends Token.Parsed{
			public static Tokens.Syntax.syntax_60 SYNTAX = new Tokens.Syntax.syntax_60(Token.Id._SYNTAX);
			public static Token.Parsed OutputContext = new Tokens.Syntax.syntax_60(Token.Id._OutputContext);
			public syntax_60(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_60() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_60 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Context";
			}
			public void setValue(String newValue) {
			}
			public void setOutputContext(Token.Parsed newOutputContext) {
				OutputContext = newOutputContext;
			}
		}
		public static class syntax_61 extends Token.Parsed{
			public static Tokens.Syntax.syntax_61 SYNTAX = new Tokens.Syntax.syntax_61(Token.Id._SYNTAX);
			public syntax_61(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_61() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_61 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "ConditionalHeader";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_62 extends Token.Parsed{
			public static Tokens.Syntax.syntax_62 SYNTAX = new Tokens.Syntax.syntax_62(Token.Id._SYNTAX);
			public syntax_62(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_62() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_62 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Header";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_63 extends Token.Parsed{
			public static Tokens.Syntax.syntax_63 SYNTAX = new Tokens.Syntax.syntax_63(Token.Id._SYNTAX);
			public syntax_63(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_63() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_63 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Concatenation";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_64 extends Token.Parsed{
			public static Tokens.Syntax.syntax_64 SYNTAX = new Tokens.Syntax.syntax_64(Token.Id._SYNTAX);
			public syntax_64(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_64() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_64 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Concat";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_65 extends Token.Parsed{
			public static Tokens.Syntax.syntax_65 SYNTAX = new Tokens.Syntax.syntax_65(Token.Id._SYNTAX);
			public static Token.Parsed OutputQuote = new Tokens.Syntax.syntax_65(Token.Id._OutputQuote);
			public syntax_65(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_65() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_65 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Quote";
			}
			public void setValue(String newValue) {
			}
			public void setOutputQuote(Token.Parsed newOutputQuote) {
				OutputQuote = newOutputQuote;
			}
		}
		public static class syntax_66 extends Token.Parsed{
			public static Tokens.Syntax.syntax_66 SYNTAX = new Tokens.Syntax.syntax_66(Token.Id._SYNTAX);
			public static Token.Parsed OutputCast = new Tokens.Syntax.syntax_66(Token.Id._OutputCast);
			public syntax_66(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_66() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_66 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Cast";
			}
			public void setValue(String newValue) {
			}
			public void setOutputCast(Token.Parsed newOutputCast) {
				OutputCast = newOutputCast;
			}
		}
		public static class syntax_67 extends Token.Parsed{
			public static Tokens.Syntax.syntax_67 SYNTAX = new Tokens.Syntax.syntax_67(Token.Id._SYNTAX);
			public static Token.Parsed OutputString = new Tokens.Syntax.syntax_67(Token.Id._OutputString);
			public syntax_67(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_67() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_67 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "String";
			}
			public void setValue(String newValue) {
			}
			public void setOutputString(Token.Parsed newOutputString) {
				OutputString = newOutputString;
			}
		}
		public static class syntax_68 extends Token.Parsed{
			public static Tokens.Syntax.syntax_68 SYNTAX = new Tokens.Syntax.syntax_68(Token.Id._SYNTAX);
			public static Token.Parsed OutputStatement = new Tokens.Syntax.syntax_68(Token.Id._OutputStatement);
			public syntax_68(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_68() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_68 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Statement";
			}
			public void setValue(String newValue) {
			}
			public void setOutputStatement(Token.Parsed newOutputStatement) {
				OutputStatement = newOutputStatement;
			}
		}
		public static class syntax_69 extends Token.Parsed{
			public static Tokens.Syntax.syntax_69 SYNTAX = new Tokens.Syntax.syntax_69(Token.Id._SYNTAX);
			public syntax_69(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_69() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_69 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Parameters";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_70 extends Token.Parsed{
			public static Tokens.Syntax.syntax_70 SYNTAX = new Tokens.Syntax.syntax_70(Token.Id._SYNTAX);
			public syntax_70(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_70() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_70 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Parameter";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_71 extends Token.Parsed{
			public static Tokens.Syntax.syntax_71 SYNTAX = new Tokens.Syntax.syntax_71(Token.Id._SYNTAX);
			public syntax_71(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_71() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_71 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Arguments";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_72 extends Token.Parsed{
			public static Tokens.Syntax.syntax_72 SYNTAX = new Tokens.Syntax.syntax_72(Token.Id._SYNTAX);
			public syntax_72(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_72() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_72 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Argument";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_73 extends Token.Parsed{
			public static Tokens.Syntax.syntax_73 SYNTAX = new Tokens.Syntax.syntax_73(Token.Id._SYNTAX);
			public syntax_73(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_73() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_73 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Token";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_74 extends Token.Parsed{
			public static Tokens.Syntax.syntax_74 SYNTAX = new Tokens.Syntax.syntax_74(Token.Id._SYNTAX);
			public static Token.Parsed Id = new Tokens.Syntax.syntax_74(Token.Id._Id);
			public syntax_74(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_74() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_74 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Id";
			}
			public void setValue(String newValue) {
			}
			public void setId(Token.Parsed newId) {
				Id = newId;
			}
		}
		public static class syntax_75 extends Token.Parsed{
			public static Tokens.Syntax.syntax_75 SYNTAX = new Tokens.Syntax.syntax_75(Token.Id._SYNTAX);
			public static Token.Parsed Parser = new Tokens.Syntax.syntax_75(Token.Id._Parser);
			public syntax_75(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_75() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_75 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Parser";
			}
			public void setValue(String newValue) {
			}
			public void setParser(Token.Parsed newParser) {
				Parser = newParser;
			}
		}
		public static class syntax_76 extends Token.Parsed{
			public static Tokens.Syntax.syntax_76 SYNTAX = new Tokens.Syntax.syntax_76(Token.Id._SYNTAX);
			public syntax_76(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_76() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_76 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Conditional";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_77 extends Token.Parsed{
			public static Tokens.Syntax.syntax_77 SYNTAX = new Tokens.Syntax.syntax_77(Token.Id._SYNTAX);
			public syntax_77(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_77() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_77 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Cond";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_78 extends Token.Parsed{
			public static Tokens.Syntax.syntax_78 SYNTAX = new Tokens.Syntax.syntax_78(Token.Id._SYNTAX);
			public static Token.Parsed OutputNewObject = new Tokens.Syntax.syntax_78(Token.Id._OutputNewObject);
			public syntax_78(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_78() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_78 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "New Object";
			}
			public void setValue(String newValue) {
			}
			public void setOutputNewObject(Token.Parsed newOutputNewObject) {
				OutputNewObject = newOutputNewObject;
			}
		}
		public static class syntax_79 extends Token.Parsed{
			public static Tokens.Syntax.syntax_79 SYNTAX = new Tokens.Syntax.syntax_79(Token.Id._SYNTAX);
			public static Token.Parsed Result = new Tokens.Syntax.syntax_79(Token.Id._Result);
			public syntax_79(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_79() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_79 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Result";
			}
			public void setValue(String newValue) {
			}
			public void setResult(Token.Parsed newResult) {
				Result = newResult;
			}
		}
		public static class syntax_80 extends Token.Parsed{
			public static Tokens.Syntax.syntax_80 SYNTAX = new Tokens.Syntax.syntax_80(Token.Id._SYNTAX);
			public static Token.Parsed Pass = new Tokens.Syntax.syntax_80(Token.Id._Pass);
			public syntax_80(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_80() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_80 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Pass";
			}
			public void setValue(String newValue) {
			}
			public void setPass(Token.Parsed newPass) {
				Pass = newPass;
			}
		}
		public static class syntax_81 extends Token.Parsed{
			public static Tokens.Syntax.syntax_81 SYNTAX = new Tokens.Syntax.syntax_81(Token.Id._SYNTAX);
			public static Token.Parsed Fail = new Tokens.Syntax.syntax_81(Token.Id._Fail);
			public syntax_81(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_81() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_81 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Fail";
			}
			public void setValue(String newValue) {
			}
			public void setFail(Token.Parsed newFail) {
				Fail = newFail;
			}
		}
		public static class syntax_82 extends Token.Parsed{
			public static Tokens.Syntax.syntax_82 SYNTAX = new Tokens.Syntax.syntax_82(Token.Id._SYNTAX);
			public static Token.Parsed OutputBody = new Tokens.Syntax.syntax_82(Token.Id._OutputBody);
			public syntax_82(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_82() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_82 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Body";
			}
			public void setValue(String newValue) {
			}
			public void setOutputBody(Token.Parsed newOutputBody) {
				OutputBody = newOutputBody;
			}
		}
		public static class syntax_83 extends Token.Parsed{
			public static Tokens.Syntax.syntax_83 SYNTAX = new Tokens.Syntax.syntax_83(Token.Id._SYNTAX);
			public static Token.Parsed OutputType = new Tokens.Syntax.syntax_83(Token.Id._OutputType);
			public syntax_83(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_83() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_83 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Type";
			}
			public void setValue(String newValue) {
			}
			public void setOutputType(Token.Parsed newOutputType) {
				OutputType = newOutputType;
			}
		}
		public static class syntax_84 extends Token.Parsed{
			public static Tokens.Syntax.syntax_84 SYNTAX = new Tokens.Syntax.syntax_84(Token.Id._SYNTAX);
			public static Token.Parsed OutputExact = new Tokens.Syntax.syntax_84(Token.Id._OutputExact);
			public syntax_84(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_84() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_84 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Exact";
			}
			public void setValue(String newValue) {
			}
			public void setOutputExact(Token.Parsed newOutputExact) {
				OutputExact = newOutputExact;
			}
		}
		public static class syntax_85 extends Token.Parsed{
			public static Tokens.Syntax.syntax_85 SYNTAX = new Tokens.Syntax.syntax_85(Token.Id._SYNTAX);
			public static Token.Parsed CallableOutput = new Tokens.Syntax.syntax_85(Token.Id._CallableOutput);
			public syntax_85(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_85() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_85 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Callable";
			}
			public void setValue(String newValue) {
			}
			public void setCallableOutput(Token.Parsed newCallableOutput) {
				CallableOutput = newCallableOutput;
			}
		}
		public static class syntax_86 extends Token.Parsed{
			public static Tokens.Syntax.syntax_86 SYNTAX = new Tokens.Syntax.syntax_86(Token.Id._SYNTAX);
			public static Token.Parsed OutputCall = new Tokens.Syntax.syntax_86(Token.Id._OutputCall);
			public syntax_86(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_86() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_86 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Call";
			}
			public void setValue(String newValue) {
			}
			public void setOutputCall(Token.Parsed newOutputCall) {
				OutputCall = newOutputCall;
			}
		}
		public static class syntax_87 extends Token.Parsed{
			public static Tokens.Syntax.syntax_87 SYNTAX = new Tokens.Syntax.syntax_87(Token.Id._SYNTAX);
			public static Token.Parsed OutputHelper = new Tokens.Syntax.syntax_87(Token.Id._OutputHelper);
			public syntax_87(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_87() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_87 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Helper";
			}
			public void setValue(String newValue) {
			}
			public void setOutputHelper(Token.Parsed newOutputHelper) {
				OutputHelper = newOutputHelper;
			}
		}
		public static class syntax_88 extends Token.Parsed{
			public static Tokens.Syntax.syntax_88 SYNTAX = new Tokens.Syntax.syntax_88(Token.Id._SYNTAX);
			public static Token.Parsed OutputOperator = new Tokens.Syntax.syntax_88(Token.Id._OutputOperator);
			public syntax_88(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_88() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_88 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Operator";
			}
			public void setValue(String newValue) {
			}
			public void setOutputOperator(Token.Parsed newOutputOperator) {
				OutputOperator = newOutputOperator;
			}
		}
		public static class syntax_89 extends Token.Parsed{
			public static Tokens.Syntax.syntax_89 SYNTAX = new Tokens.Syntax.syntax_89(Token.Id._SYNTAX);
			public syntax_89(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_89() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_89 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "StaticCall";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_90 extends Token.Parsed{
			public static Tokens.Syntax.syntax_90 SYNTAX = new Tokens.Syntax.syntax_90(Token.Id._SYNTAX);
			public syntax_90(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_90() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_90 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Static Call";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_91 extends Token.Parsed{
			public static Tokens.Syntax.syntax_91 SYNTAX = new Tokens.Syntax.syntax_91(Token.Id._SYNTAX);
			public syntax_91(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_91() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_91 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Static";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_92 extends Token.Parsed{
			public static Tokens.Syntax.syntax_92 SYNTAX = new Tokens.Syntax.syntax_92(Token.Id._SYNTAX);
			public static Token.Parsed OutputLambda = new Tokens.Syntax.syntax_92(Token.Id._OutputLambda);
			public syntax_92(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_92() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_92 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Lambda";
			}
			public void setValue(String newValue) {
			}
			public void setOutputLambda(Token.Parsed newOutputLambda) {
				OutputLambda = newOutputLambda;
			}
		}
		public static class syntax_93 extends Token.Parsed{
			public static Tokens.Syntax.syntax_93 SYNTAX = new Tokens.Syntax.syntax_93(Token.Id._SYNTAX);
			public static Token.Parsed OutputBraced = new Tokens.Syntax.syntax_93(Token.Id._OutputBraced);
			public syntax_93(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_93() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_93 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Braced";
			}
			public void setValue(String newValue) {
			}
			public void setOutputBraced(Token.Parsed newOutputBraced) {
				OutputBraced = newOutputBraced;
			}
		}
		public static class syntax_94 extends Token.Parsed{
			public static Tokens.Syntax.syntax_94 SYNTAX = new Tokens.Syntax.syntax_94(Token.Id._SYNTAX);
			public static Token.Parsed Output = new Tokens.Syntax.syntax_94(Token.Id._Output);
			public syntax_94(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_94() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_94 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Output";
			}
			public void setValue(String newValue) {
			}
			public void setOutput(Token.Parsed newOutput) {
				Output = newOutput;
			}
		}
		public static class syntax_95 extends Token.Parsed{
			public static Tokens.Syntax.syntax_95 SYNTAX = new Tokens.Syntax.syntax_95(Token.Id._SYNTAX);
			public static Token.Parsed LineableOutput = new Tokens.Syntax.syntax_95(Token.Id._LineableOutput);
			public syntax_95(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_95() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_95 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "Lineable";
			}
			public void setValue(String newValue) {
			}
			public void setLineableOutput(Token.Parsed newLineableOutput) {
				LineableOutput = newLineableOutput;
			}
		}
		public static class syntax_96 extends Token.Parsed{
			public static Tokens.Syntax.syntax_96 SYNTAX = new Tokens.Syntax.syntax_96(Token.Id._SYNTAX);
			public static Token.Parsed OutputNewObject = new Tokens.Syntax.syntax_96(Token.Id._OutputNewObject);
			public syntax_96(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_96() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_96 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "New";
			}
			public void setValue(String newValue) {
			}
			public void setOutputNewObject(Token.Parsed newOutputNewObject) {
				OutputNewObject = newOutputNewObject;
			}
		}
		public static class syntax_97 extends Token.Parsed{
			public static Tokens.Syntax.syntax_97 SYNTAX = new Tokens.Syntax.syntax_97(Token.Id._SYNTAX);
			public static Token.Parsed OutputArguments = new Tokens.Syntax.syntax_97(Token.Id._OutputArguments);
			public syntax_97(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_97() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_97 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "A";
			}
			public void setValue(String newValue) {
			}
			public void setOutputArguments(Token.Parsed newOutputArguments) {
				OutputArguments = newOutputArguments;
			}
		}
		public static class syntax_98 extends Token.Parsed{
			public static Tokens.Syntax.syntax_98 SYNTAX = new Tokens.Syntax.syntax_98(Token.Id._SYNTAX);
			public static Token.Parsed OutputBody = new Tokens.Syntax.syntax_98(Token.Id._OutputBody);
			public syntax_98(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_98() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_98 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "B";
			}
			public void setValue(String newValue) {
			}
			public void setOutputBody(Token.Parsed newOutputBody) {
				OutputBody = newOutputBody;
			}
		}
		public static class syntax_99 extends Token.Parsed{
			public static Tokens.Syntax.syntax_99 SYNTAX = new Tokens.Syntax.syntax_99(Token.Id._SYNTAX);
			public static Token.Parsed OutputClass = new Tokens.Syntax.syntax_99(Token.Id._OutputClass);
			public syntax_99(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_99() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_99 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "C";
			}
			public void setValue(String newValue) {
			}
			public void setOutputClass(Token.Parsed newOutputClass) {
				OutputClass = newOutputClass;
			}
		}
		public static class syntax_100 extends Token.Parsed{
			public static Tokens.Syntax.syntax_100 SYNTAX = new Tokens.Syntax.syntax_100(Token.Id._SYNTAX);
			public static Token.Parsed OutputExact = new Tokens.Syntax.syntax_100(Token.Id._OutputExact);
			public syntax_100(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_100() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_100 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "E";
			}
			public void setValue(String newValue) {
			}
			public void setOutputExact(Token.Parsed newOutputExact) {
				OutputExact = newOutputExact;
			}
		}
		public static class syntax_101 extends Token.Parsed{
			public static Tokens.Syntax.syntax_101 SYNTAX = new Tokens.Syntax.syntax_101(Token.Id._SYNTAX);
			public static Token.Parsed OutputHelper = new Tokens.Syntax.syntax_101(Token.Id._OutputHelper);
			public syntax_101(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_101() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_101 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "H";
			}
			public void setValue(String newValue) {
			}
			public void setOutputHelper(Token.Parsed newOutputHelper) {
				OutputHelper = newOutputHelper;
			}
		}
		public static class syntax_102 extends Token.Parsed{
			public static Tokens.Syntax.syntax_102 SYNTAX = new Tokens.Syntax.syntax_102(Token.Id._SYNTAX);
			public static Token.Parsed OutputMethod = new Tokens.Syntax.syntax_102(Token.Id._OutputMethod);
			public syntax_102(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_102() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_102 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "M";
			}
			public void setValue(String newValue) {
			}
			public void setOutputMethod(Token.Parsed newOutputMethod) {
				OutputMethod = newOutputMethod;
			}
		}
		public static class syntax_103 extends Token.Parsed{
			public static Tokens.Syntax.syntax_103 SYNTAX = new Tokens.Syntax.syntax_103(Token.Id._SYNTAX);
			public static Token.Parsed OutputNewObject = new Tokens.Syntax.syntax_103(Token.Id._OutputNewObject);
			public syntax_103(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_103() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_103 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "N";
			}
			public void setValue(String newValue) {
			}
			public void setOutputNewObject(Token.Parsed newOutputNewObject) {
				OutputNewObject = newOutputNewObject;
			}
		}
		public static class syntax_104 extends Token.Parsed{
			public static Tokens.Syntax.syntax_104 SYNTAX = new Tokens.Syntax.syntax_104(Token.Id._SYNTAX);
			public static Token.Parsed OutputOperator = new Tokens.Syntax.syntax_104(Token.Id._OutputOperator);
			public syntax_104(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_104() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_104 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "O";
			}
			public void setValue(String newValue) {
			}
			public void setOutputOperator(Token.Parsed newOutputOperator) {
				OutputOperator = newOutputOperator;
			}
		}
		public static class syntax_105 extends Token.Parsed{
			public static Tokens.Syntax.syntax_105 SYNTAX = new Tokens.Syntax.syntax_105(Token.Id._SYNTAX);
			public static Token.Parsed OutputParameters = new Tokens.Syntax.syntax_105(Token.Id._OutputParameters);
			public syntax_105(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_105() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_105 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "P";
			}
			public void setValue(String newValue) {
			}
			public void setOutputParameters(Token.Parsed newOutputParameters) {
				OutputParameters = newOutputParameters;
			}
		}
		public static class syntax_106 extends Token.Parsed{
			public static Tokens.Syntax.syntax_106 SYNTAX = new Tokens.Syntax.syntax_106(Token.Id._SYNTAX);
			public static Token.Parsed OutputStatement = new Tokens.Syntax.syntax_106(Token.Id._OutputStatement);
			public syntax_106(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_106() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_106 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "S";
			}
			public void setValue(String newValue) {
			}
			public void setOutputStatement(Token.Parsed newOutputStatement) {
				OutputStatement = newOutputStatement;
			}
		}
		public static class syntax_107 extends Token.Parsed{
			public static Tokens.Syntax.syntax_107 SYNTAX = new Tokens.Syntax.syntax_107(Token.Id._SYNTAX);
			public syntax_107(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_107() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_107 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "T";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_108 extends Token.Parsed{
			public static Tokens.Syntax.syntax_108 SYNTAX = new Tokens.Syntax.syntax_108(Token.Id._SYNTAX);
			public static Token.Parsed OutputVariable = new Tokens.Syntax.syntax_108(Token.Id._OutputVariable);
			public syntax_108(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_108() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_108 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "V";
			}
			public void setValue(String newValue) {
			}
			public void setOutputVariable(Token.Parsed newOutputVariable) {
				OutputVariable = newOutputVariable;
			}
		}
		public static class syntax_109 extends Token.Parsed{
			public static Tokens.Syntax.syntax_109 SYNTAX = new Tokens.Syntax.syntax_109(Token.Id._SYNTAX);
			public static Token.Parsed getAllSafely = new Tokens.Syntax.syntax_109(Token.Id._getAllSafely);
			public syntax_109(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_109() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_109 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "->>";
			}
			public void setValue(String newValue) {
			}
			public void setGetAllSafely(Token.Parsed newGetAllSafely) {
				getAllSafely = newGetAllSafely;
			}
		}
		public static class syntax_110 extends Token.Parsed{
			public static Tokens.Syntax.syntax_110 SYNTAX = new Tokens.Syntax.syntax_110(Token.Id._SYNTAX);
			public syntax_110(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_110() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_110 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "+";
			}
			public void setValue(String newValue) {
			}
		}
		public static class syntax_111 extends Token.Parsed{
			public static Tokens.Syntax.syntax_111 SYNTAX = new Tokens.Syntax.syntax_111(Token.Id._SYNTAX);
			public static Token.Parsed asVariable = new Tokens.Syntax.syntax_111(Token.Id._asVariable);
			public syntax_111(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public syntax_111() {
			}
			public void setSYNTAX(Tokens.Syntax.syntax_111 newSYNTAX) {
				SYNTAX = newSYNTAX;
			}
			public String getValue() {
				return "$";
			}
			public void setValue(String newValue) {
			}
			public void setAsVariable(Token.Parsed newAsVariable) {
				asVariable = newAsVariable;
			}
		}
	}
	public static class Name{
		public Name() {
		}
		public static class TemplateParameterToken extends Token.Parsed{
			protected String value = null;
			public TemplateParameterToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public TemplateParameterToken(final String value) {
				this.value = value;
			}
			public TemplateParameterToken() {
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
				return Token.Id._template_parameter;
			}
		}
		public static class AnonymousClassToken extends Token.Parsed{
			protected String value = null;
			public AnonymousClassToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public AnonymousClassToken(final String value) {
				this.value = value;
			}
			public AnonymousClassToken() {
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
				return Token.Id._anonymous_class;
			}
		}
		public static class ClassNameToken extends Token.Parsed{
			protected String value = null;
			public ClassNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ClassNameToken(final String value) {
				this.value = value;
			}
			public ClassNameToken() {
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
				return Token.Id._className;
			}
		}
		public static class StaticToken extends Token.Parsed{
			protected String value = null;
			public StaticToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public StaticToken(final String value) {
				this.value = value;
			}
			public StaticToken() {
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
				return Token.Id._static;
			}
		}
		public static class ObjectTypeToken extends Token.Parsed{
			protected String value = null;
			public ObjectTypeToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ObjectTypeToken(final String value) {
				this.value = value;
			}
			public ObjectTypeToken() {
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
				return Token.Id._objectType;
			}
		}
		public static class TemplateTypeNameToken extends Token.Parsed{
			protected String value = null;
			public TemplateTypeNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public TemplateTypeNameToken(final String value) {
				this.value = value;
			}
			public TemplateTypeNameToken() {
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
				return Token.Id._templateTypeName;
			}
		}
		public static class ParentNameToken extends Token.Parsed{
			protected String value = null;
			public ParentNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ParentNameToken(final String value) {
				this.value = value;
			}
			public ParentNameToken() {
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
				return Token.Id._parentName;
			}
		}
		public static class InterfaceNameToken extends Token.Parsed{
			protected String value = null;
			public InterfaceNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public InterfaceNameToken(final String value) {
				this.value = value;
			}
			public InterfaceNameToken() {
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
				return Token.Id._interfaceName;
			}
		}
		public static class BodyReturnToken extends Token.Parsed{
			protected String value = null;
			public BodyReturnToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public BodyReturnToken(final String value) {
				this.value = value;
			}
			public BodyReturnToken() {
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
				return Token.Id._body_return;
			}
		}
		public static class BodyThrowToken extends Token.Parsed{
			protected String value = null;
			public BodyThrowToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public BodyThrowToken(final String value) {
				this.value = value;
			}
			public BodyThrowToken() {
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
				return Token.Id._body_throw;
			}
		}
		public static class ConditionalToken extends Token.Parsed{
			protected String value = null;
			public ConditionalToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ConditionalToken(final String value) {
				this.value = value;
			}
			public ConditionalToken() {
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
				return Token.Id._conditional;
			}
		}
		public static class AsBodyToken extends Token.Parsed{
			protected String value = null;
			public AsBodyToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public AsBodyToken(final String value) {
				this.value = value;
			}
			public AsBodyToken() {
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
				return Token.Id._as_body;
			}
		}
		public static class VariableDeclarationToken extends Token.Parsed{
			protected String value = null;
			public VariableDeclarationToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public VariableDeclarationToken(final String value) {
				this.value = value;
			}
			public VariableDeclarationToken() {
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
				return Token.Id._variable_declaration;
			}
		}
		public static class OPERATORToken extends Token.Parsed{
			protected String value = null;
			public OPERATORToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public OPERATORToken(final String value) {
				this.value = value;
			}
			public OPERATORToken() {
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
				return Token.Id._OPERATOR;
			}
		}
		public static class ExceptionToken extends Token.Parsed{
			protected String value = null;
			public ExceptionToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ExceptionToken(final String value) {
				this.value = value;
			}
			public ExceptionToken() {
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
				return Token.Id._exception;
			}
		}
		public static class CastStatementToken extends Token.Parsed{
			protected String value = null;
			public CastStatementToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public CastStatementToken(final String value) {
				this.value = value;
			}
			public CastStatementToken() {
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
				return Token.Id._cast_statement;
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
		public static class NameVarToken extends Token.Parsed{
			protected String value = null;
			public NameVarToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public NameVarToken(final String value) {
				this.value = value;
			}
			public NameVarToken() {
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
				return Token.Id._name_var;
			}
		}
		public static class SeparatorToken extends Token.Parsed{
			protected String value = null;
			public SeparatorToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public SeparatorToken(final String value) {
				this.value = value;
			}
			public SeparatorToken() {
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
				return Token.Id._separator;
			}
		}
		public static class AsBracedToken extends Token.Parsed{
			protected String value = null;
			public AsBracedToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public AsBracedToken(final String value) {
				this.value = value;
			}
			public AsBracedToken() {
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
				return Token.Id._as_braced;
			}
		}
		public static class AllTypeNameToken extends Token.Parsed{
			protected String value = null;
			public AllTypeNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public AllTypeNameToken(final String value) {
				this.value = value;
			}
			public AllTypeNameToken() {
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
				return Token.Id._all_type_name;
			}
		}
		public static class AccessMethodToken extends Token.Parsed{
			protected String value = null;
			public AccessMethodToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public AccessMethodToken(final String value) {
				this.value = value;
			}
			public AccessMethodToken() {
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
				return Token.Id._accessMethod;
			}
		}
		public static class BodyToken extends Token.Parsed{
			protected String value = null;
			public BodyToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public BodyToken(final String value) {
				this.value = value;
			}
			public BodyToken() {
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
				return Token.Id._body;
			}
		}
		public static class TokenAccessToken extends Token.Parsed{
			protected String value = null;
			public TokenAccessToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public TokenAccessToken(final String value) {
				this.value = value;
			}
			public TokenAccessToken() {
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
				return Token.Id._tokenAccess;
			}
		}
		public static class AccessToken extends Token.Parsed{
			protected String value = null;
			public AccessToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public AccessToken(final String value) {
				this.value = value;
			}
			public AccessToken() {
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
				return Token.Id._access;
			}
		}
		public static class VariableNameToken extends Token.Parsed{
			protected String value = null;
			public VariableNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public VariableNameToken(final String value) {
				this.value = value;
			}
			public VariableNameToken() {
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
				return Token.Id._variableName;
			}
		}
		public static class TokenInstanceToken extends Token.Parsed{
			protected String value = null;
			public TokenInstanceToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public TokenInstanceToken(final String value) {
				this.value = value;
			}
			public TokenInstanceToken() {
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
				return Token.Id._tokenInstance;
			}
		}
		public static class TokenNameToken extends Token.Parsed{
			protected String value = null;
			public TokenNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public TokenNameToken(final String value) {
				this.value = value;
			}
			public TokenNameToken() {
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
				return Token.Id._tokenName;
			}
		}
		public static class ParameterToken extends Token.Parsed{
			protected String value = null;
			public ParameterToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ParameterToken(final String value) {
				this.value = value;
			}
			public ParameterToken() {
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
				return Token.Id._parameter;
			}
		}
		public static class LambdaToken extends Token.Parsed{
			protected String value = null;
			public LambdaToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public LambdaToken(final String value) {
				this.value = value;
			}
			public LambdaToken() {
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
				return Token.Id._lambda;
			}
		}
		public static class BodyEntriesToken extends Token.Parsed{
			protected String value = null;
			public BodyEntriesToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public BodyEntriesToken(final String value) {
				this.value = value;
			}
			public BodyEntriesToken() {
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
				return Token.Id._body_entries;
			}
		}
		public static class MethodNameToken extends Token.Parsed{
			protected String value = null;
			public MethodNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public MethodNameToken(final String value) {
				this.value = value;
			}
			public MethodNameToken() {
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
				return Token.Id._methodName;
			}
		}
		public static class InlineToken extends Token.Parsed{
			protected String value = null;
			public InlineToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public InlineToken(final String value) {
				this.value = value;
			}
			public InlineToken() {
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
				return Token.Id._inline;
			}
		}
		public static class VariableParametersToken extends Token.Parsed{
			protected String value = null;
			public VariableParametersToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public VariableParametersToken(final String value) {
				this.value = value;
			}
			public VariableParametersToken() {
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
				return Token.Id._variableParameters;
			}
		}
		public static class TypeNameToken extends Token.Parsed{
			protected String value = null;
			public TypeNameToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public TypeNameToken(final String value) {
				this.value = value;
			}
			public TypeNameToken() {
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
				return Token.Id._typeName;
			}
		}
		public static class OutputConditionalHeaderToken extends Token.Parsed{
			protected String value = null;
			public OutputConditionalHeaderToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public OutputConditionalHeaderToken(final String value) {
				this.value = value;
			}
			public OutputConditionalHeaderToken() {
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
				return Token.Id._OutputConditionalHeader;
			}
		}
		public static class OutputConcatenationToken extends Token.Parsed{
			protected String value = null;
			public OutputConcatenationToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public OutputConcatenationToken(final String value) {
				this.value = value;
			}
			public OutputConcatenationToken() {
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
				return Token.Id._OutputConcatenation;
			}
		}
		public static class OutputParametersToken extends Token.Parsed{
			protected String value = null;
			public OutputParametersToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public OutputParametersToken(final String value) {
				this.value = value;
			}
			public OutputParametersToken() {
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
				return Token.Id._OutputParameters;
			}
		}
		public static class OutputArgumentsToken extends Token.Parsed{
			protected String value = null;
			public OutputArgumentsToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public OutputArgumentsToken(final String value) {
				this.value = value;
			}
			public OutputArgumentsToken() {
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
				return Token.Id._OutputArguments;
			}
		}
		public static class TokenToken extends Token.Parsed{
			protected String value = null;
			public TokenToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public TokenToken(final String value) {
				this.value = value;
			}
			public TokenToken() {
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
				return Token.Id._Token;
			}
		}
		public static class OutputConditionalToken extends Token.Parsed{
			protected String value = null;
			public OutputConditionalToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public OutputConditionalToken(final String value) {
				this.value = value;
			}
			public OutputConditionalToken() {
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
				return Token.Id._OutputConditional;
			}
		}
		public static class OutputStaticCallToken extends Token.Parsed{
			protected String value = null;
			public OutputStaticCallToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public OutputStaticCallToken(final String value) {
				this.value = value;
			}
			public OutputStaticCallToken() {
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
				return Token.Id._OutputStaticCall;
			}
		}
		public static class VariableNamesToken extends Token.Parsed{
			protected String value = null;
			public VariableNamesToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public VariableNamesToken(final String value) {
				this.value = value;
			}
			public VariableNamesToken() {
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
				return Token.Id._variable_names;
			}
		}
		public static class NAMEToken extends Token.Parsed{
			protected String value = null;
			public NAMEToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public NAMEToken(final String value) {
				this.value = value;
			}
			public NAMEToken() {
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
				return Token.Id._NAME;
			}
		}
		public static class ClassVariableNamesToken extends Token.Parsed{
			protected String value = null;
			public ClassVariableNamesToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ClassVariableNamesToken(final String value) {
				this.value = value;
			}
			public ClassVariableNamesToken() {
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
				return Token.Id._class_variable_names;
			}
		}
		public static class ClassToken extends Token.Parsed{
			protected String value = null;
			public ClassToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ClassToken(final String value) {
				this.value = value;
			}
			public ClassToken() {
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
				return Token.Id._class;
			}
		}
		public static class ClassNamesToken extends Token.Parsed{
			protected String value = null;
			public ClassNamesToken(final Token.Id initalSuperName, final String value) {
				super(initalSuperName);
				this.value = value;
			}
			public ClassNamesToken(final String value) {
				this.value = value;
			}
			public ClassNamesToken() {
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
				return Token.Id._class_names;
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
		public static class ClassBodyToken extends Token.Parsed{
			protected String value = null;
			public ClassBodyToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ClassBodyToken() {
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
				return Token.Id._class_body;
			}
		}
		public static class MethodBodyToken extends Token.Parsed{
			protected String value = null;
			public MethodBodyToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public MethodBodyToken() {
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
				return Token.Id._method_body;
			}
		}
		public static class MethodArgumentsToken extends Token.Parsed{
			protected String value = null;
			public MethodArgumentsToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public MethodArgumentsToken() {
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
				return Token.Id._method_arguments;
			}
		}
		public static class TemplateParametersToken extends Token.Parsed{
			protected String value = null;
			public TemplateParametersToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public TemplateParametersToken() {
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
				return Token.Id._template_parameters;
			}
		}
		public static class ArrayParametersToken extends Token.Parsed{
			protected String value = null;
			public ArrayParametersToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ArrayParametersToken() {
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
				return Token.Id._array_parameters;
			}
		}
		public static class StatementAsQuoteToken extends Token.Parsed{
			protected String value = null;
			public StatementAsQuoteToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsQuoteToken() {
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
				return Token.Id._statement_as_quote;
			}
		}
		public static class StatementAsStringToken extends Token.Parsed{
			protected String value = null;
			public StatementAsStringToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsStringToken() {
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
				return Token.Id._statement_as_string;
			}
		}
		public static class StatementAsMethodToken extends Token.Parsed{
			protected String value = null;
			public StatementAsMethodToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsMethodToken() {
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
				return Token.Id._statement_as_method;
			}
		}
		public static class StatementAsBracedToken extends Token.Parsed{
			protected String value = null;
			public StatementAsBracedToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsBracedToken() {
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
				return Token.Id._statement_as_braced;
			}
		}
		public static class AsStatementToken extends Token.Parsed{
			protected String value = null;
			public AsStatementToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public AsStatementToken() {
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
				return Token.Id._as_statement;
			}
		}
		public static class CastStatementToken extends Token.Parsed{
			protected String value = null;
			public CastStatementToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public CastStatementToken() {
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
				return Token.Id._cast_statement;
			}
		}
		public static class StatementAsCharToken extends Token.Parsed{
			protected String value = null;
			public StatementAsCharToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsCharToken() {
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
				return Token.Id._statement_as_char;
			}
		}
		public static class ClassFileNameToken extends Token.Parsed{
			protected String value = null;
			public ClassFileNameToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ClassFileNameToken() {
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
				return Token.Id._class_file_name;
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
		public static class ImportClwsToken extends Token.Parsed{
			protected String value = null;
			public ImportClwsToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ImportClwsToken() {
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
				return Token.Id._import_clws;
			}
		}
		public static class ImportClwsFileImportToken extends Token.Parsed{
			protected String value = null;
			public ImportClwsFileImportToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ImportClwsFileImportToken() {
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
				return Token.Id._import_clws__file_import;
			}
		}
		public static class ImportImportsToken extends Token.Parsed{
			protected String value = null;
			public ImportImportsToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ImportImportsToken() {
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
				return Token.Id._import_imports;
			}
		}
		public static class ImportImportsFileImportToken extends Token.Parsed{
			protected String value = null;
			public ImportImportsFileImportToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ImportImportsFileImportToken() {
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
				return Token.Id._import_imports__file_import;
			}
		}
		public static class PackageNameToken extends Token.Parsed{
			protected String value = null;
			public PackageNameToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public PackageNameToken() {
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
				return Token.Id._packageName;
			}
		}
		public static class AnonymousClassesToken extends Token.Parsed{
			protected String value = null;
			public AnonymousClassesToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public AnonymousClassesToken() {
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
				return Token.Id._anonymous_classes;
			}
		}
		public static class AnonymousClassToken extends Token.Parsed{
			protected String value = null;
			public AnonymousClassToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public AnonymousClassToken() {
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
				return Token.Id._anonymous_class;
			}
		}
		public static class AnonymousClassNameToken extends Token.Parsed{
			protected String value = null;
			public AnonymousClassNameToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public AnonymousClassNameToken() {
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
				return Token.Id._anonymous_class_name;
			}
		}
		public static class InnerToken extends Token.Parsed{
			protected String value = null;
			public InnerToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public InnerToken() {
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
				return Token.Id._inner;
			}
		}
		public static class WeakToken extends Token.Parsed{
			protected String value = null;
			public WeakToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public WeakToken() {
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
				return Token.Id._weak;
			}
		}
		public static class ClassDeclarationToken extends Token.Parsed{
			protected String value = null;
			public ClassDeclarationToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ClassDeclarationToken() {
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
				return Token.Id._class_declaration;
			}
		}
		public static class ClassElementToken extends Token.Parsed{
			protected String value = null;
			public ClassElementToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ClassElementToken() {
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
				return Token.Id._class_element;
			}
		}
		public static class BodyElementToken extends Token.Parsed{
			protected String value = null;
			public BodyElementToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BodyElementToken() {
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
				return Token.Id._body_element;
			}
		}
		public static class BodyConditionalToken extends Token.Parsed{
			protected String value = null;
			public BodyConditionalToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BodyConditionalToken() {
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
				return Token.Id._body_conditional;
			}
		}
		public static class BodyStatementToken extends Token.Parsed{
			protected String value = null;
			public BodyStatementToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BodyStatementToken() {
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
				return Token.Id._body_statement;
			}
		}
		public static class BodyCallToken extends Token.Parsed{
			protected String value = null;
			public BodyCallToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BodyCallToken() {
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
				return Token.Id._body_call;
			}
		}
		public static class BodyAddToClassToken extends Token.Parsed{
			protected String value = null;
			public BodyAddToClassToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BodyAddToClassToken() {
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
				return Token.Id._body_add_to_class;
			}
		}
		public static class BodyAccessTokenToken extends Token.Parsed{
			protected String value = null;
			public BodyAccessTokenToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public BodyAccessTokenToken() {
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
				return Token.Id._body_access_token;
			}
		}
		public static class MethodParametersToken extends Token.Parsed{
			protected String value = null;
			public MethodParametersToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public MethodParametersToken() {
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
				return Token.Id._method_parameters;
			}
		}
		public static class MethodArgumentToken extends Token.Parsed{
			protected String value = null;
			public MethodArgumentToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public MethodArgumentToken() {
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
				return Token.Id._method_argument;
			}
		}
		public static class MethodDeclarationToken extends Token.Parsed{
			protected String value = null;
			public MethodDeclarationToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public MethodDeclarationToken() {
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
				return Token.Id._method_declaration;
			}
		}
		public static class VariableDeclarationToken extends Token.Parsed{
			protected String value = null;
			public VariableDeclarationToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public VariableDeclarationToken() {
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
				return Token.Id._variable_declaration;
			}
		}
		public static class VariableAssignmentToken extends Token.Parsed{
			protected String value = null;
			public VariableAssignmentToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public VariableAssignmentToken() {
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
				return Token.Id._variable_assignment;
			}
		}
		public static class VariableNameDefinitionToken extends Token.Parsed{
			protected String value = null;
			public VariableNameDefinitionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public VariableNameDefinitionToken() {
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
				return Token.Id._variable_name_definition;
			}
		}
		public static class NonClassVariableNameDefinitionToken extends Token.Parsed{
			protected String value = null;
			public NonClassVariableNameDefinitionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public NonClassVariableNameDefinitionToken() {
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
				return Token.Id._non_class_variable_name_definition;
			}
		}
		public static class ClassVariableNameDefinitionToken extends Token.Parsed{
			protected String value = null;
			public ClassVariableNameDefinitionToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public ClassVariableNameDefinitionToken() {
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
				return Token.Id._class_variable_name_definition;
			}
		}
		public static class AllTypeNameToken extends Token.Parsed{
			protected String value = null;
			public AllTypeNameToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public AllTypeNameToken() {
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
				return Token.Id._all_type_name;
			}
		}
		public static class NonClassNameToken extends Token.Parsed{
			protected String value = null;
			public NonClassNameToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public NonClassNameToken() {
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
				return Token.Id._non_class_name;
			}
		}
		public static class NameVarToken extends Token.Parsed{
			protected String value = null;
			public NameVarToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public NameVarToken() {
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
				return Token.Id._name_var;
			}
		}
		public static class NameAtomToken extends Token.Parsed{
			protected String value = null;
			public NameAtomToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public NameAtomToken() {
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
				return Token.Id._name_atom;
			}
		}
		public static class TypeVarToken extends Token.Parsed{
			protected String value = null;
			public TypeVarToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public TypeVarToken() {
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
				return Token.Id._type_var;
			}
		}
		public static class TypeAtomToken extends Token.Parsed{
			protected String value = null;
			public TypeAtomToken(final Token.Id initalSuperName) {
				super(initalSuperName);
			}
			public TypeAtomToken() {
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
				return Token.Id._type_atom;
			}
		}
	}
}