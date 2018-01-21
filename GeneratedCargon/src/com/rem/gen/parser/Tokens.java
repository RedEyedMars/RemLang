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
		public static class Left extends Token.Parsed{
			protected String value = null;
			public Left(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public Left(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public Left() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "left";
			}
		}
		public static class Right extends Token.Parsed{
			protected String value = null;
			public Right(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public Right(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public Right() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "right";
			}
		}
		public static class StandAlone extends Token.Parsed{
			protected String value = null;
			public StandAlone(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public StandAlone(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public StandAlone() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "standAlone";
			}
		}
		public static class Character extends Token.Parsed{
			protected String value = null;
			public Character(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public Character(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public Character() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "character";
			}
		}
		public static class Escaped extends Token.Parsed{
			protected String value = null;
			public Escaped(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public Escaped(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public Escaped() {
			}
			public String getValue(){
				return value;
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "escaped";
			}
		}
	}
	public static class Syntax{
		public Syntax() {
		}
		public static class syntax_0 extends Token.Parsed{
			public static Tokens.Syntax.syntax_0 __SYNTAX__ = new Tokens.Syntax.syntax_0("SYNTAX");
			public syntax_0(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_0() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_0 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "ignore";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_1 extends Token.Parsed{
			public static Tokens.Syntax.syntax_1 __SYNTAX__ = new Tokens.Syntax.syntax_1("SYNTAX");
			public syntax_1(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_1() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_1 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return ":";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_2 extends Token.Parsed{
			public static Tokens.Syntax.syntax_2 __SYNTAX__ = new Tokens.Syntax.syntax_2("SYNTAX");
			public syntax_2(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_2() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_2 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "\\n";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_3 extends Token.Parsed{
			public static Tokens.Syntax.syntax_3 __SYNTAX__ = new Tokens.Syntax.syntax_3("SYNTAX");
			public syntax_3(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_3() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_3 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "import ";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_4 extends Token.Parsed{
			public static Tokens.Syntax.syntax_4 __SYNTAX__ = new Tokens.Syntax.syntax_4("SYNTAX");
			public static Token.Parsed negate = new Tokens.Syntax.syntax_4("negate");
			public syntax_4(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_4() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_4 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "^";
			}
			public void setValue(String newValue){
			}
			public void setNegate(Token.Parsed newNegate){
				negate = newNegate;
			}
		}
		public static class syntax_5 extends Token.Parsed{
			public static Tokens.Syntax.syntax_5 __SYNTAX__ = new Tokens.Syntax.syntax_5("SYNTAX");
			public syntax_5(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_5() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_5 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "-";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_6 extends Token.Parsed{
			public static Tokens.Syntax.syntax_6 __SYNTAX__ = new Tokens.Syntax.syntax_6("SYNTAX");
			public syntax_6(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_6() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_6 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return ",";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_7 extends Token.Parsed{
			public static Tokens.Syntax.syntax_7 __SYNTAX__ = new Tokens.Syntax.syntax_7("SYNTAX");
			public static Token.Parsed SILENT = new Tokens.Syntax.syntax_7("SILENT");
			public syntax_7(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_7() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_7 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "silent";
			}
			public void setValue(String newValue){
			}
			public void setSILENT(Token.Parsed newSILENT){
				SILENT = newSILENT;
			}
		}
		public static class syntax_8 extends Token.Parsed{
			public static Tokens.Syntax.syntax_8 __SYNTAX__ = new Tokens.Syntax.syntax_8("SYNTAX");
			public syntax_8(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_8() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_8 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "@";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_9 extends Token.Parsed{
			public static Tokens.Syntax.syntax_9 __SYNTAX__ = new Tokens.Syntax.syntax_9("SYNTAX");
			public syntax_9(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_9() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_9 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "Braced";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_10 extends Token.Parsed{
			public static Tokens.Syntax.syntax_10 __SYNTAX__ = new Tokens.Syntax.syntax_10("SYNTAX");
			public syntax_10(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_10() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_10 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "Imports";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_11 extends Token.Parsed{
			public static Tokens.Syntax.syntax_11 __SYNTAX__ = new Tokens.Syntax.syntax_11("SYNTAX");
			public syntax_11(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_11() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_11 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "=";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_12 extends Token.Parsed{
			public static Tokens.Syntax.syntax_12 __SYNTAX__ = new Tokens.Syntax.syntax_12("SYNTAX");
			public syntax_12(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_12() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_12 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "Ignore";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_13 extends Token.Parsed{
			public static Tokens.Syntax.syntax_13 __SYNTAX__ = new Tokens.Syntax.syntax_13("SYNTAX");
			public static Token.Parsed ignores_none = new Tokens.Syntax.syntax_13("ignores_none");
			public syntax_13(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_13() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_13 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "None";
			}
			public void setValue(String newValue){
			}
			public void setIgnoresNone(Token.Parsed newIgnoresNone){
				ignores_none = newIgnoresNone;
			}
		}
		public static class syntax_14 extends Token.Parsed{
			public static Tokens.Syntax.syntax_14 __SYNTAX__ = new Tokens.Syntax.syntax_14("SYNTAX");
			public syntax_14(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_14() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_14 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "\\n\\t";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_15 extends Token.Parsed{
			public static Tokens.Syntax.syntax_15 __SYNTAX__ = new Tokens.Syntax.syntax_15("SYNTAX");
			public syntax_15(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_15() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_15 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "global";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_16 extends Token.Parsed{
			public static Tokens.Syntax.syntax_16 __SYNTAX__ = new Tokens.Syntax.syntax_16("SYNTAX");
			public syntax_16(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_16() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_16 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "list";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_17 extends Token.Parsed{
			public static Tokens.Syntax.syntax_17 __SYNTAX__ = new Tokens.Syntax.syntax_17("SYNTAX");
			public syntax_17(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_17() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_17 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "with";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_18 extends Token.Parsed{
			public static Tokens.Syntax.syntax_18 __SYNTAX__ = new Tokens.Syntax.syntax_18("SYNTAX");
			public syntax_18(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_18() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_18 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "|";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_19 extends Token.Parsed{
			public static Tokens.Syntax.syntax_19 __SYNTAX__ = new Tokens.Syntax.syntax_19("SYNTAX");
			public syntax_19(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_19() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_19 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "as ";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_20 extends Token.Parsed{
			public static Tokens.Syntax.syntax_20 __SYNTAX__ = new Tokens.Syntax.syntax_20("SYNTAX");
			public syntax_20(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_20() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_20 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "in ";
			}
			public void setValue(String newValue){
			}
		}
		public static class syntax_21 extends Token.Parsed{
			public static Tokens.Syntax.syntax_21 __SYNTAX__ = new Tokens.Syntax.syntax_21("SYNTAX");
			public static Token.Parsed OPTIONAL = new Tokens.Syntax.syntax_21("OPTIONAL");
			public syntax_21(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_21() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_21 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "?";
			}
			public void setValue(String newValue){
			}
			public void setOPTIONAL(Token.Parsed newOPTIONAL){
				OPTIONAL = newOPTIONAL;
			}
		}
		public static class syntax_22 extends Token.Parsed{
			public static Tokens.Syntax.syntax_22 __SYNTAX__ = new Tokens.Syntax.syntax_22("SYNTAX");
			public static Token.Parsed MANY = new Tokens.Syntax.syntax_22("MANY");
			public syntax_22(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_22() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_22 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "*";
			}
			public void setValue(String newValue){
			}
			public void setMANY(Token.Parsed newMANY){
				MANY = newMANY;
			}
		}
		public static class syntax_23 extends Token.Parsed{
			public static Tokens.Syntax.syntax_23 __SYNTAX__ = new Tokens.Syntax.syntax_23("SYNTAX");
			public static Token.Parsed PLUS = new Tokens.Syntax.syntax_23("PLUS");
			public syntax_23(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_23() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_23 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "+";
			}
			public void setValue(String newValue){
			}
			public void setPLUS(Token.Parsed newPLUS){
				PLUS = newPLUS;
			}
		}
		public static class syntax_24 extends Token.Parsed{
			public static Tokens.Syntax.syntax_24 __SYNTAX__ = new Tokens.Syntax.syntax_24("SYNTAX");
			public static Token.Parsed number = new Tokens.Syntax.syntax_24("number");
			public syntax_24(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_24() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_24 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "\\d";
			}
			public void setValue(String newValue){
			}
			public void setNumber(Token.Parsed newNumber){
				number = newNumber;
			}
		}
		public static class syntax_25 extends Token.Parsed{
			public static Tokens.Syntax.syntax_25 __SYNTAX__ = new Tokens.Syntax.syntax_25("SYNTAX");
			public static Token.Parsed dot = new Tokens.Syntax.syntax_25("dot");
			public syntax_25(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_25() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_25 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "\\.";
			}
			public void setValue(String newValue){
			}
			public void setDot(Token.Parsed newDot){
				dot = newDot;
			}
		}
		public static class syntax_26 extends Token.Parsed{
			public static Tokens.Syntax.syntax_26 __SYNTAX__ = new Tokens.Syntax.syntax_26("SYNTAX");
			public static Token.Parsed whitespace = new Tokens.Syntax.syntax_26("whitespace");
			public syntax_26(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_26() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_26 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "\\s";
			}
			public void setValue(String newValue){
			}
			public void setWhitespace(Token.Parsed newWhitespace){
				whitespace = newWhitespace;
			}
		}
		public static class syntax_27 extends Token.Parsed{
			public static Tokens.Syntax.syntax_27 __SYNTAX__ = new Tokens.Syntax.syntax_27("SYNTAX");
			public static Token.Parsed slash = new Tokens.Syntax.syntax_27("slash");
			public syntax_27(final String initalSuperName) {
				super(initalSuperName);
			}
			public syntax_27() {
			}
			public void set_SYNTAX(Tokens.Syntax.syntax_27 new_SYNTAX){
				__SYNTAX__ = new_SYNTAX;
			}
			public String getValue(){
				return "\\\\";
			}
			public void setValue(String newValue){
			}
			public void setSlash(Token.Parsed newSlash){
				slash = newSlash;
			}
		}
	}
	public static class Name{
		public Name() {
		}
		public static class IgnoreCharacterToken extends Token.Parsed{
			protected String value = null;
			public IgnoreCharacterToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public IgnoreCharacterToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public IgnoreCharacterToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "ignoreCharacter";
			}
		}
		public static class RegexToken extends Token.Parsed{
			protected String value = null;
			public RegexToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public RegexToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public RegexToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "regex";
			}
		}
		public static class RangeToken extends Token.Parsed{
			protected String value = null;
			public RangeToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public RangeToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public RangeToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "range";
			}
		}
		public static class PassConstraintToken extends Token.Parsed{
			protected String value = null;
			public PassConstraintToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public PassConstraintToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public PassConstraintToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "passConstraint";
			}
		}
		public static class BracedParametersToken extends Token.Parsed{
			protected String value = null;
			public BracedParametersToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public BracedParametersToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public BracedParametersToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "braced_parameters";
			}
		}
		public static class ImportParameterToken extends Token.Parsed{
			protected String value = null;
			public ImportParameterToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ImportParameterToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ImportParameterToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "import_parameter";
			}
		}
		public static class RuleNameToken extends Token.Parsed{
			protected String value = null;
			public RuleNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public RuleNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public RuleNameToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "ruleName";
			}
		}
		public static class ImportDefinitionToken extends Token.Parsed{
			protected String value = null;
			public ImportDefinitionToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ImportDefinitionToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ImportDefinitionToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "import_definition";
			}
		}
		public static class ChainToken extends Token.Parsed{
			protected String value = null;
			public ChainToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ChainToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ChainToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "chain";
			}
		}
		public static class IgnoresCharacterToken extends Token.Parsed{
			protected String value = null;
			public IgnoresCharacterToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public IgnoresCharacterToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public IgnoresCharacterToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "ignores_character";
			}
		}
		public static class ListNameToken extends Token.Parsed{
			protected String value = null;
			public ListNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ListNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ListNameToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "listName";
			}
		}
		public static class ListRuleNameToken extends Token.Parsed{
			protected String value = null;
			public ListRuleNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ListRuleNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ListRuleNameToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "listRuleName";
			}
		}
		public static class ChoiceToken extends Token.Parsed{
			protected String value = null;
			public ChoiceToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public ChoiceToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public ChoiceToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "choice";
			}
		}
		public static class NewNameToken extends Token.Parsed{
			protected String value = null;
			public NewNameToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public NewNameToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public NewNameToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "newName";
			}
		}
		public static class MultipleToken extends Token.Parsed{
			protected String value = null;
			public MultipleToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public MultipleToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public MultipleToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "multiple";
			}
		}
		public static class QuoteTokenToken extends Token.Parsed{
			protected String value = null;
			public QuoteTokenToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public QuoteTokenToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public QuoteTokenToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "quoteToken";
			}
		}
		public static class RegexTokenToken extends Token.Parsed{
			protected String value = null;
			public RegexTokenToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public RegexTokenToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public RegexTokenToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "regexToken";
			}
		}
		public static class RuleTokenToken extends Token.Parsed{
			protected String value = null;
			public RuleTokenToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public RuleTokenToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public RuleTokenToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "ruleToken";
			}
		}
		public static class OptionToken extends Token.Parsed{
			protected String value = null;
			public OptionToken(final String initalSuperName, final String value) {
				super(initalSuperName);
				if(value != null){
					this.value = value;
				}
			}
			public OptionToken(final String value) {
				if(value != null){
					this.value = value;
				}
			}
			public OptionToken() {
			}
			public String getValue(){
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "option";
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
				if(children.isEmpty()){
					return value;
				}
				else{
					return children.get(0).getValue();
				}
			}
			public void setValue(String newValue){
				value=newValue;
			}
			public String getName(){
				return "group";
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
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "base";
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
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "comments";
			}
		}
		public static class IgnoresToken extends Token.Parsed{
			protected String value = null;
			public IgnoresToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public IgnoresToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "ignores";
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
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "imports";
			}
		}
		public static class ImportsFileImportToken extends Token.Parsed{
			protected String value = null;
			public ImportsFileImportToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ImportsFileImportToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "imports__file_import";
			}
		}
		public static class FILENAMEToken extends Token.Parsed{
			protected String value = null;
			public FILENAMEToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public FILENAMEToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "FILE_NAME";
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
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "NAME";
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
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "OPERATOR";
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
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "NUMBER";
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
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "quote";
			}
		}
		public static class BracedDefinitionToken extends Token.Parsed{
			protected String value = null;
			public BracedDefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public BracedDefinitionToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "braced_definition";
			}
		}
		public static class RegexToken extends Token.Parsed{
			protected String value = null;
			public RegexToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RegexToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "regex";
			}
		}
		public static class RegexOptionToken extends Token.Parsed{
			protected String value = null;
			public RegexOptionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RegexOptionToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "regex_option";
			}
		}
		public static class RegexGroupDefinitionToken extends Token.Parsed{
			protected String value = null;
			public RegexGroupDefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RegexGroupDefinitionToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "regex_group_definition";
			}
		}
		public static class SingleIgnoresCharacterToken extends Token.Parsed{
			protected String value = null;
			public SingleIgnoresCharacterToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public SingleIgnoresCharacterToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "single_ignores_character";
			}
		}
		public static class RegexOptionDefinitionToken extends Token.Parsed{
			protected String value = null;
			public RegexOptionDefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RegexOptionDefinitionToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "regex_option_definition";
			}
		}
		public static class RuleParametersToken extends Token.Parsed{
			protected String value = null;
			public RuleParametersToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RuleParametersToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "rule_parameters";
			}
		}
		public static class RuleParamsToken extends Token.Parsed{
			protected String value = null;
			public RuleParamsToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RuleParamsToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "rule_params";
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
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "base_element";
			}
		}
		public static class RuleToken extends Token.Parsed{
			protected String value = null;
			public RuleToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RuleToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "rule";
			}
		}
		public static class ListToken extends Token.Parsed{
			protected String value = null;
			public ListToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ListToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "list";
			}
		}
		public static class DefinitionToken extends Token.Parsed{
			protected String value = null;
			public DefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public DefinitionToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "definition";
			}
		}
		public static class ElementToken extends Token.Parsed{
			protected String value = null;
			public ElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public ElementToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "element";
			}
		}
		public static class AtomToken extends Token.Parsed{
			protected String value = null;
			public AtomToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public AtomToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "atom";
			}
		}
		public static class RegexDefinitionToken extends Token.Parsed{
			protected String value = null;
			public RegexDefinitionToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RegexDefinitionToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "regex_definition";
			}
		}
		public static class RegexElementToken extends Token.Parsed{
			protected String value = null;
			public RegexElementToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RegexElementToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "regex_element";
			}
		}
		public static class RegexSpecialToken extends Token.Parsed{
			protected String value = null;
			public RegexSpecialToken(final String initalSuperName) {
				super(initalSuperName);
			}
			public RegexSpecialToken() {
			}
			public String getValue(){
				if(value==null){
					return super.getValue();
				}
				else{
					return value;
				}
			}
			public void setValue(String newValue){
				value = newValue;
			}
			public String getName(){
				return "regex_special";
			}
		}
	}
}