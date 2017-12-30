package com.rem.gen.parser;
import java.util.*;
import java.io.*;
import java.util.List;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Tokens;

public  class Tokens{
	public Tokens() {
	}
	public static class Plain{
		public Plain() {
		}
		public static class Comment extends Token.Parsed{
			protected String value = null;
			public Comment(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public Comment(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public Comment() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "comment";
			}
		}
		public static class Value extends Token.Parsed{
			protected String value = null;
			public Value(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public Value(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public Value() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "value";
			}
		}
	}
	public static class Syntax{
		public Syntax() {
		}
		public static class syntax_0 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_0 __SYNTAX__ = new Tokens.Syntax.syntax_0("SYNTAX");
			public syntax_0(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_0() {
			}
			public String getValue(){
				return ",";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_1 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_1 __SYNTAX__ = new Tokens.Syntax.syntax_1("SYNTAX");
			public syntax_1(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_1() {
			}
			public String getValue(){
				return "char\\";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_2 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_2 __SYNTAX__ = new Tokens.Syntax.syntax_2("SYNTAX");
			public syntax_2(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_2() {
			}
			public String getValue(){
				return ";";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_3 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_3 __SYNTAX__ = new Tokens.Syntax.syntax_3("SYNTAX");
			public syntax_3(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_3() {
			}
			public String getValue(){
				return "@@@";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_4 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_4 __SYNTAX__ = new Tokens.Syntax.syntax_4("SYNTAX");
			public syntax_4(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_4() {
			}
			public String getValue(){
				return "\\";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_5 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_5 __SYNTAX__ = new Tokens.Syntax.syntax_5("SYNTAX");
			public syntax_5(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_5() {
			}
			public String getValue(){
				return "<";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_6 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_6 __SYNTAX__ = new Tokens.Syntax.syntax_6("SYNTAX");
			public syntax_6(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_6() {
			}
			public String getValue(){
				return "hidden";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_7 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_7 __SYNTAX__ = new Tokens.Syntax.syntax_7("SYNTAX");
			public syntax_7(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_7() {
			}
			public String getValue(){
				return ":";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_8 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_8 __SYNTAX__ = new Tokens.Syntax.syntax_8("SYNTAX");
			public syntax_8(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_8() {
			}
			public String getValue(){
				return "from";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_9 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_9 __SYNTAX__ = new Tokens.Syntax.syntax_9("SYNTAX");
			public syntax_9(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_9() {
			}
			public String getValue(){
				return ".";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_10 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_10 __SYNTAX__ = new Tokens.Syntax.syntax_10("SYNTAX");
			public syntax_10(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_10() {
			}
			public String getValue(){
				return ">";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_11 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_11 __SYNTAX__ = new Tokens.Syntax.syntax_11("SYNTAX");
			public syntax_11(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_11() {
			}
			public String getValue(){
				return "inner ";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_12 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_12 __SYNTAX__ = new Tokens.Syntax.syntax_12("SYNTAX");
			public syntax_12(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_12() {
			}
			public String getValue(){
				return "~";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_13 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_13 __SYNTAX__ = new Tokens.Syntax.syntax_13("SYNTAX");
			public syntax_13(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_13() {
			}
			public String getValue(){
				return "class ";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_14 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_14 __SYNTAX__ = new Tokens.Syntax.syntax_14("SYNTAX");
			public syntax_14(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_14() {
			}
			public String getValue(){
				return "interface ";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_15 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_15 __SYNTAX__ = new Tokens.Syntax.syntax_15("SYNTAX");
			public syntax_15(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_15() {
			}
			public String getValue(){
				return "enum ";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_16 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_16 __SYNTAX__ = new Tokens.Syntax.syntax_16("SYNTAX");
			public syntax_16(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_16() {
			}
			public String getValue(){
				return "/";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_17 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_17 __SYNTAX__ = new Tokens.Syntax.syntax_17("SYNTAX");
			public syntax_17(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_17() {
			}
			public String getValue(){
				return "return";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_18 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_18 __SYNTAX__ = new Tokens.Syntax.syntax_18("SYNTAX");
			public syntax_18(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_18() {
			}
			public String getValue(){
				return "void";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_19 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_19 __SYNTAX__ = new Tokens.Syntax.syntax_19("SYNTAX");
			public syntax_19(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_19() {
			}
			public String getValue(){
				return "throw";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_20 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_20 __SYNTAX__ = new Tokens.Syntax.syntax_20("SYNTAX");
			public syntax_20(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_20() {
			}
			public String getValue(){
				return "if";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_21 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_21 __SYNTAX__ = new Tokens.Syntax.syntax_21("SYNTAX");
			public static final Token.Parsed conditional = new Tokens.Syntax.syntax_21("conditional");
			public syntax_21(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_21() {
			}
			public String getValue(){
				return "else";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_22 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_22 __SYNTAX__ = new Tokens.Syntax.syntax_22("SYNTAX");
			public syntax_22(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_22() {
			}
			public String getValue(){
				return "while";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_23 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_23 __SYNTAX__ = new Tokens.Syntax.syntax_23("SYNTAX");
			public syntax_23(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_23() {
			}
			public String getValue(){
				return "synchronized";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_24 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_24 __SYNTAX__ = new Tokens.Syntax.syntax_24("SYNTAX");
			public syntax_24(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_24() {
			}
			public String getValue(){
				return "switch";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_25 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_25 __SYNTAX__ = new Tokens.Syntax.syntax_25("SYNTAX");
			public syntax_25(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_25() {
			}
			public String getValue(){
				return "case";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_26 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_26 __SYNTAX__ = new Tokens.Syntax.syntax_26("SYNTAX");
			public static final Token.Parsed conditional = new Tokens.Syntax.syntax_26("conditional");
			public syntax_26(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_26() {
			}
			public String getValue(){
				return "for";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_27 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_27 __SYNTAX__ = new Tokens.Syntax.syntax_27("SYNTAX");
			public static final Token.Parsed conditional = new Tokens.Syntax.syntax_27("conditional");
			public syntax_27(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_27() {
			}
			public String getValue(){
				return "try";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_28 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_28 __SYNTAX__ = new Tokens.Syntax.syntax_28("SYNTAX");
			public syntax_28(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_28() {
			}
			public String getValue(){
				return "print";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_29 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_29 __SYNTAX__ = new Tokens.Syntax.syntax_29("SYNTAX");
			public static final Token.Parsed conditional = new Tokens.Syntax.syntax_29("conditional");
			public syntax_29(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_29() {
			}
			public String getValue(){
				return "catch";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_30 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_30 __SYNTAX__ = new Tokens.Syntax.syntax_30("SYNTAX");
			public static final Token.Parsed NAME = new Tokens.Syntax.syntax_30("NAME");
			public syntax_30(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_30() {
			}
			public String getValue(){
				return "*";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_31 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_31 __SYNTAX__ = new Tokens.Syntax.syntax_31("SYNTAX");
			public syntax_31(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_31() {
			}
			public String getValue(){
				return "|";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_32 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_32 __SYNTAX__ = new Tokens.Syntax.syntax_32("SYNTAX");
			public static final Token.Parsed NEW = new Tokens.Syntax.syntax_32("NEW");
			public syntax_32(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_32() {
			}
			public String getValue(){
				return "new ";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_33 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_33 __SYNTAX__ = new Tokens.Syntax.syntax_33("SYNTAX");
			public static final Token.Parsed methodName = new Tokens.Syntax.syntax_33("methodName");
			public syntax_33(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_33() {
			}
			public String getValue(){
				return "+=";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_34 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_34 __SYNTAX__ = new Tokens.Syntax.syntax_34("SYNTAX");
			public syntax_34(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_34() {
			}
			public String getValue(){
				return "(";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_35 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_35 __SYNTAX__ = new Tokens.Syntax.syntax_35("SYNTAX");
			public syntax_35(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_35() {
			}
			public String getValue(){
				return ")";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_36 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_36 __SYNTAX__ = new Tokens.Syntax.syntax_36("SYNTAX");
			public syntax_36(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_36() {
			}
			public String getValue(){
				return "@";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_37 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_37 __SYNTAX__ = new Tokens.Syntax.syntax_37("SYNTAX");
			public syntax_37(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_37() {
			}
			public String getValue(){
				return "static";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_38 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_38 __SYNTAX__ = new Tokens.Syntax.syntax_38("SYNTAX");
			public static final Token.Parsed ARRAY_TYPE = new Tokens.Syntax.syntax_38("ARRAY_TYPE");
			public syntax_38(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_38() {
			}
			public String getValue(){
				return "[]";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_39 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_39 __SYNTAX__ = new Tokens.Syntax.syntax_39("SYNTAX");
			public syntax_39(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_39() {
			}
			public String getValue(){
				return "=";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_40 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_40 __SYNTAX__ = new Tokens.Syntax.syntax_40("SYNTAX");
			public syntax_40(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_40() {
			}
			public String getValue(){
				return "...";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_41 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_41 __SYNTAX__ = new Tokens.Syntax.syntax_41("SYNTAX");
			public static final Token.Parsed typeName = new Tokens.Syntax.syntax_41("typeName");
			public syntax_41(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_41() {
			}
			public String getValue(){
				return "Class";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_42 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_42 __SYNTAX__ = new Tokens.Syntax.syntax_42("SYNTAX");
			public syntax_42(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_42() {
			}
			public String getValue(){
				return "Method";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_43 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_43 __SYNTAX__ = new Tokens.Syntax.syntax_43("SYNTAX");
			public syntax_43(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_43() {
			}
			public String getValue(){
				return "Variable";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_44 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_44 __SYNTAX__ = new Tokens.Syntax.syntax_44("SYNTAX");
			public syntax_44(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_44() {
			}
			public String getValue(){
				return "Body";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_45 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_45 __SYNTAX__ = new Tokens.Syntax.syntax_45("SYNTAX");
			public syntax_45(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_45() {
			}
			public String getValue(){
				return "Statement";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_46 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_46 __SYNTAX__ = new Tokens.Syntax.syntax_46("SYNTAX");
			public syntax_46(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_46() {
			}
			public String getValue(){
				return "Parameters";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_47 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_47 __SYNTAX__ = new Tokens.Syntax.syntax_47("SYNTAX");
			public syntax_47(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_47() {
			}
			public String getValue(){
				return "Context";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_48 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_48 __SYNTAX__ = new Tokens.Syntax.syntax_48("SYNTAX");
			public static final Token.Parsed CAMEL = new Tokens.Syntax.syntax_48("CAMEL");
			public syntax_48(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_48() {
			}
			public String getValue(){
				return "^";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_49 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_49 __SYNTAX__ = new Tokens.Syntax.syntax_49("SYNTAX");
			public syntax_49(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_49() {
			}
			public String getValue(){
				return "->";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_50 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_50 __SYNTAX__ = new Tokens.Syntax.syntax_50("SYNTAX");
			public syntax_50(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_50() {
			}
			public String getValue(){
				return "+";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_51 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_51 __SYNTAX__ = new Tokens.Syntax.syntax_51("SYNTAX");
			public syntax_51(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_51() {
			}
			public String getValue(){
				return "super";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_52 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_52 __SYNTAX__ = new Tokens.Syntax.syntax_52("SYNTAX");
			public syntax_52(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_52() {
			}
			public String getValue(){
				return "this";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_53 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_53 __SYNTAX__ = new Tokens.Syntax.syntax_53("SYNTAX");
			public syntax_53(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_53() {
			}
			public String getValue(){
				return "null";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_54 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_54 __SYNTAX__ = new Tokens.Syntax.syntax_54("SYNTAX");
			public syntax_54(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_54() {
			}
			public String getValue(){
				return "true";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_55 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_55 __SYNTAX__ = new Tokens.Syntax.syntax_55("SYNTAX");
			public syntax_55(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_55() {
			}
			public String getValue(){
				return "false";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_56 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_56 __SYNTAX__ = new Tokens.Syntax.syntax_56("SYNTAX");
			public static final Token.Parsed ISTYPENAME = new Tokens.Syntax.syntax_56("ISTYPENAME");
			public syntax_56(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_56() {
			}
			public String getValue(){
				return "$";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_57 extends Token.Parsed{
			public static final Tokens.Syntax.syntax_57 __SYNTAX__ = new Tokens.Syntax.syntax_57("SYNTAX");
			public syntax_57(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_57() {
			}
			public String getValue(){
				return "\\>";
			}
			public void setValue(String newValue){
			}
		}
	}
	public static class Name{
		public Name() {
		}
		public static class TemplateParameterToken extends Token.Parsed{
			protected String value = null;
			public TemplateParameterToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public TemplateParameterToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public TemplateParameterToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "template_parameter";
			}
		}
		public static class ClassNameToken extends Token.Parsed{
			protected String value = null;
			public ClassNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ClassNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ClassNameToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "className";
			}
		}
		public static class ObjectTypeToken extends Token.Parsed{
			protected String value = null;
			public ObjectTypeToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ObjectTypeToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ObjectTypeToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "objectType";
			}
		}
		public static class VariableNamesToken extends Token.Parsed{
			protected String value = null;
			public VariableNamesToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public VariableNamesToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public VariableNamesToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "variable_names";
			}
		}
		public static class ClassVariableNamesToken extends Token.Parsed{
			protected String value = null;
			public ClassVariableNamesToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ClassVariableNamesToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ClassVariableNamesToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "class_variable_names";
			}
		}
		public static class TemplateTypeNameToken extends Token.Parsed{
			protected String value = null;
			public TemplateTypeNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public TemplateTypeNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public TemplateTypeNameToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "templateTypeName";
			}
		}
		public static class ParentNameToken extends Token.Parsed{
			protected String value = null;
			public ParentNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ParentNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ParentNameToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "parentName";
			}
		}
		public static class InterfaceNameToken extends Token.Parsed{
			protected String value = null;
			public InterfaceNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public InterfaceNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public InterfaceNameToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "interfaceName";
			}
		}
		public static class BodyReturnToken extends Token.Parsed{
			protected String value = null;
			public BodyReturnToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public BodyReturnToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public BodyReturnToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "body_return";
			}
		}
		public static class BodyThrowToken extends Token.Parsed{
			protected String value = null;
			public BodyThrowToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public BodyThrowToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public BodyThrowToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "body_throw";
			}
		}
		public static class BodyLineToken extends Token.Parsed{
			protected String value = null;
			public BodyLineToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public BodyLineToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public BodyLineToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "body_line";
			}
		}
		public static class ConditionalToken extends Token.Parsed{
			protected String value = null;
			public ConditionalToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ConditionalToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ConditionalToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "conditional";
			}
		}
		public static class AsBodyToken extends Token.Parsed{
			protected String value = null;
			public AsBodyToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public AsBodyToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public AsBodyToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "as_body";
			}
		}
		public static class ExceptionToken extends Token.Parsed{
			protected String value = null;
			public ExceptionToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ExceptionToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ExceptionToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "exception";
			}
		}
		public static class AsBracedToken extends Token.Parsed{
			protected String value = null;
			public AsBracedToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public AsBracedToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public AsBracedToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "as_braced";
			}
		}
		public static class LeftToken extends Token.Parsed{
			protected String value = null;
			public LeftToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public LeftToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public LeftToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "left";
			}
		}
		public static class RightToken extends Token.Parsed{
			protected String value = null;
			public RightToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public RightToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public RightToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "right";
			}
		}
		public static class GroupToken extends Token.Parsed{
			protected String value = null;
			public GroupToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public GroupToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public GroupToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "group";
			}
		}
		public static class TypeNameToken extends Token.Parsed{
			protected String value = null;
			public TypeNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public TypeNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public TypeNameToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "typeName";
			}
		}
		public static class BodyToken extends Token.Parsed{
			protected String value = null;
			public BodyToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public BodyToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public BodyToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "body";
			}
		}
		public static class VariableNameToken extends Token.Parsed{
			protected String value = null;
			public VariableNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public VariableNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public VariableNameToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "variableName";
			}
		}
		public static class TokenInstanceToken extends Token.Parsed{
			protected String value = null;
			public TokenInstanceToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public TokenInstanceToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public TokenInstanceToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "tokenInstance";
			}
		}
		public static class TokenNameToken extends Token.Parsed{
			protected String value = null;
			public TokenNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public TokenNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public TokenNameToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "tokenName";
			}
		}
		public static class BodyEntriesToken extends Token.Parsed{
			protected String value = null;
			public BodyEntriesToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public BodyEntriesToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public BodyEntriesToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "body_entries";
			}
		}
		public static class StaticToken extends Token.Parsed{
			protected String value = null;
			public StaticToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public StaticToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public StaticToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "static";
			}
		}
		public static class MethodNameToken extends Token.Parsed{
			protected String value = null;
			public MethodNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public MethodNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public MethodNameToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "methodName";
			}
		}
		public static class InlineToken extends Token.Parsed{
			protected String value = null;
			public InlineToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public InlineToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public InlineToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "inline";
			}
		}
		public static class VariableParametersToken extends Token.Parsed{
			protected String value = null;
			public VariableParametersToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public VariableParametersToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public VariableParametersToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "variableParameters";
			}
		}
		public static class AccessToken extends Token.Parsed{
			protected String value = null;
			public AccessToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public AccessToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public AccessToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "access";
			}
		}
		public static class NameVarToken extends Token.Parsed{
			protected String value = null;
			public NameVarToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public NameVarToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public NameVarToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "name_var";
			}
		}
		public static class ConcatToken extends Token.Parsed{
			protected String value = null;
			public ConcatToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ConcatToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ConcatToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "concat";
			}
		}
		public static class ExactToken extends Token.Parsed{
			protected String value = null;
			public ExactToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ExactToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ExactToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "exact";
			}
		}
		public static class NAMEToken extends Token.Parsed{
			protected String value = null;
			public NAMEToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public NAMEToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public NAMEToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "NAME";
			}
		}
		public static class VariableToken extends Token.Parsed{
			protected String value = null;
			public VariableToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public VariableToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public VariableToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "variable";
			}
		}
		public static class AccessMultiToken extends Token.Parsed{
			protected String value = null;
			public AccessMultiToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public AccessMultiToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public AccessMultiToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "access_multi";
			}
		}
		public static class TypeVarToken extends Token.Parsed{
			protected String value = null;
			public TypeVarToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public TypeVarToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public TypeVarToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "type_var";
			}
		}
		public static class AsMethodToken extends Token.Parsed{
			protected String value = null;
			public AsMethodToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public AsMethodToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public AsMethodToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "as_method";
			}
		}
		public static class AccessMethodToken extends Token.Parsed{
			protected String value = null;
			public AccessMethodToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public AccessMethodToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public AccessMethodToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "access_method";
			}
		}
		public static class ClassToken extends Token.Parsed{
			protected String value = null;
			public ClassToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ClassToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ClassToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "class";
			}
		}
		public static class ClassNamesToken extends Token.Parsed{
			protected String value = null;
			public ClassNamesToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ClassNamesToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ClassNamesToken() {
			}
			public String getValue(){
				if (children.isEmpty()){
					return value;
				}
				else {
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
			}
			public String getName(){
				return "class_names";
			}
		}
	}
	public static class Rule{
		public Rule() {
		}
		public static class BaseToken extends Token.Parsed{
			protected String value = null;
			public BaseToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BaseToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "base";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class CommentsToken extends Token.Parsed{
			protected String value = null;
			public CommentsToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public CommentsToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "comments";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class NAMEToken extends Token.Parsed{
			protected String value = null;
			public NAMEToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public NAMEToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "NAME";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class OPERATORToken extends Token.Parsed{
			protected String value = null;
			public OPERATORToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public OPERATORToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "OPERATOR";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class NUMBERToken extends Token.Parsed{
			protected String value = null;
			public NUMBERToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public NUMBERToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "NUMBER";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class QuoteToken extends Token.Parsed{
			protected String value = null;
			public QuoteToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public QuoteToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "quote";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassBodyToken extends Token.Parsed{
			protected String value = null;
			public ClassBodyToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassBodyToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_body";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodBodyToken extends Token.Parsed{
			protected String value = null;
			public MethodBodyToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodBodyToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_body";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodArgumentsToken extends Token.Parsed{
			protected String value = null;
			public MethodArgumentsToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodArgumentsToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_arguments";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class TemplateParametersToken extends Token.Parsed{
			protected String value = null;
			public TemplateParametersToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public TemplateParametersToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "template_parameters";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ArrayParametersToken extends Token.Parsed{
			protected String value = null;
			public ArrayParametersToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ArrayParametersToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "array_parameters";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsQuoteToken extends Token.Parsed{
			protected String value = null;
			public StatementAsQuoteToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsQuoteToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_quote";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsStringToken extends Token.Parsed{
			protected String value = null;
			public StatementAsStringToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsStringToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_string";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsMethodToken extends Token.Parsed{
			protected String value = null;
			public StatementAsMethodToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsMethodToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_method";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsBracedToken extends Token.Parsed{
			protected String value = null;
			public StatementAsBracedToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsBracedToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_braced";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class AsStatementToken extends Token.Parsed{
			protected String value = null;
			public AsStatementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public AsStatementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "as_statement";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class StatementAsCharToken extends Token.Parsed{
			protected String value = null;
			public StatementAsCharToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public StatementAsCharToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "statement_as_char";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassFileNameToken extends Token.Parsed{
			protected String value = null;
			public ClassFileNameToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassFileNameToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_file_name";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BaseElementToken extends Token.Parsed{
			protected String value = null;
			public BaseElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BaseElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "base_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ImportsToken extends Token.Parsed{
			protected String value = null;
			public ImportsToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ImportsToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "imports";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class PackageNameToken extends Token.Parsed{
			protected String value = null;
			public PackageNameToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public PackageNameToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "packageName";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class AnonymousClassToken extends Token.Parsed{
			protected String value = null;
			public AnonymousClassToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public AnonymousClassToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "anonymous_class";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class InnerToken extends Token.Parsed{
			protected String value = null;
			public InnerToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public InnerToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "inner";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class WeakToken extends Token.Parsed{
			protected String value = null;
			public WeakToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public WeakToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "weak";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassDeclarationToken extends Token.Parsed{
			protected String value = null;
			public ClassDeclarationToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassDeclarationToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_declaration";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassElementToken extends Token.Parsed{
			protected String value = null;
			public ClassElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyElementToken extends Token.Parsed{
			protected String value = null;
			public BodyElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyConditionalToken extends Token.Parsed{
			protected String value = null;
			public BodyConditionalToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyConditionalToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_conditional";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyStatementToken extends Token.Parsed{
			protected String value = null;
			public BodyStatementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyStatementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_statement";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyCallToken extends Token.Parsed{
			protected String value = null;
			public BodyCallToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyCallToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_call";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class BodyManipulateToken extends Token.Parsed{
			protected String value = null;
			public BodyManipulateToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BodyManipulateToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "body_manipulate";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodParametersToken extends Token.Parsed{
			protected String value = null;
			public MethodParametersToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodParametersToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_parameters";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodArgumentToken extends Token.Parsed{
			protected String value = null;
			public MethodArgumentToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodArgumentToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_argument";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class MethodDeclarationToken extends Token.Parsed{
			protected String value = null;
			public MethodDeclarationToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public MethodDeclarationToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "method_declaration";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class VariableDeclarationToken extends Token.Parsed{
			protected String value = null;
			public VariableDeclarationToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public VariableDeclarationToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "variable_declaration";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class VariableAssignmentToken extends Token.Parsed{
			protected String value = null;
			public VariableAssignmentToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public VariableAssignmentToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "variable_assignment";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class VariableNameDefinitionToken extends Token.Parsed{
			protected String value = null;
			public VariableNameDefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public VariableNameDefinitionToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "variable_name_definition";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class ClassNameDefinitionToken extends Token.Parsed{
			protected String value = null;
			public ClassNameDefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ClassNameDefinitionToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "class_name_definition";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class AllTypeNameToken extends Token.Parsed{
			protected String value = null;
			public AllTypeNameToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public AllTypeNameToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "all_type_name";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class NameVarToken extends Token.Parsed{
			protected String value = null;
			public NameVarToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public NameVarToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "name_var";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class NameVarElementToken extends Token.Parsed{
			protected String value = null;
			public NameVarElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public NameVarElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "name_var_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class TypeVarToken extends Token.Parsed{
			protected String value = null;
			public TypeVarToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public TypeVarToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "type_var";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
		public static class TypeVarElementToken extends Token.Parsed{
			protected String value = null;
			public TypeVarElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public TypeVarElementToken() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "type_var_element";
			}
			public void setString(String newValue){
				if (value==null){
					value=newValue;
				}
			}
		}
	}
}