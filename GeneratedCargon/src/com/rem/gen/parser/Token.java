package com.rem.gen.parser;
import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.rem.gen.parser.Token;
import com.rem.gen.parser.Parser;

public interface Token{
	public Token get(String tokenName);
	public Token getLast();
	public Token getLast(String tokenName);
	public List<Token> getAll();
	public List<Token> getAll(String key);
	public List<Token> getAllSafely(String key);
	public void add(Token newToken);
	public String err();
	public String err(int tab);
	public void print();
	public void print(int tab);
	public void printShort();
	public String getFileName();
	public int getLineNumber();
	public String getValue();
	public String getName();
	public static class Parsed{
		protected List<Token.Parsed> children = new ArrayList<Token.Parsed>();
		protected List<Integer> positions = new ArrayList<Integer>();
		protected String name = null;
		public Parsed(final String name) {
			if(name != null){
				this.name = name;
			}
		}
		public Parsed() {
		}
		public List<Token.Parsed> getChildren(){
			return children;
		}
		public void setChildren(List<Token.Parsed> newChildren){
			children = newChildren;
		}
		public List<Integer> getPositions(){
			return positions;
		}
		public void setPositions(List<Integer> newPositions){
			positions = newPositions;
		}
		public String getName(){
			return name;
		}
		public void setName(String newName){
			name = newName;
		}
		public String getValue(){
			if(children.isEmpty()){
				return null;
			}
			else{
				return children.get(0).getValue();
			}
		}
		public void setValue(String newValue){
		}
		public String getLastValue(){
			if(children.isEmpty()){
				return null;
			}
			else{
				return children.get(children.size()-1).getValue();
			}
		}
		public void add(Integer position,Token.Parsed newToken){
			children.add(newToken);
			positions.add(position);
		}
		public void addAll(Token.Parsed inductee){
			for(Integer i = 0;i<inductee.children.size();i++){
				children.add(inductee.children.get(i));
				positions.add(inductee.positions.get(i));
			}
		}
		public static class Import extends Token.Parsed{
			protected String fileName = null;
			public Import(final String initalSuperName, final String fileName) {
				super(initalSuperName);
				if(fileName != null){
					this.fileName = fileName;
				}
			}
			public Import(final String fileName) {
				if(fileName != null){
					this.fileName = fileName;
				}
			}
			public Import() {
			}
			public String getFileName(){
				return fileName;
			}
			public void setFileName(String newFileName){
				fileName = newFileName;
			}
			public List<Token.Parsed> getChildren(){
				return Parser.contexts.get(fileName).get_root().getChildren();
			}
			public List<Integer> getPositions(){
				return Parser.contexts.get(fileName).get_root().getPositions();
			}
		}
	}
	public static class Leaf implements Token{
		protected String name = null;
		protected String value = null;
		protected Integer position = null;
		protected Integer parentPosition = null;
		protected Parser.Result.Pass context = null;
		public Leaf(final String name, final String value, final Integer position, final Integer parentPosition, final Parser.Result.Pass context) {
			if(name != null){
				this.name = name;
			}
			if(value != null){
				this.value = value;
			}
			if(position != null){
				this.position = position;
			}
			if(parentPosition != null){
				this.parentPosition = parentPosition;
			}
			if(context != null){
				this.context = context;
			}
		}
		public Leaf() {
		}
		public String getName(){
			return name;
		}
		public void setName(String newName){
			name = newName;
		}
		public String getValue(){
			return value;
		}
		public void setValue(String newValue){
			value = newValue;
		}
		public Integer getPosition(){
			return position;
		}
		public void setPosition(Integer newPosition){
			position = newPosition;
		}
		public Integer getParentPosition(){
			return parentPosition;
		}
		public void setParentPosition(Integer newParentPosition){
			parentPosition = newParentPosition;
		}
		public Parser.Result.Pass getContext(){
			return context;
		}
		public void setContext(Parser.Result.Pass newContext){
			context = newContext;
		}
		public Token get(String tokenName){
			return this;
		}
		public Token getLast(){
			return null;
		}
		public Token getLast(String tokenName){
			return null;
		}
		public void add(Token token){
		}
		public List<Token> getAll(){
			return null;
		}
		public List<Token> getAll(String key){
			return null;
		}
		public List<Token> getAllSafely(String key){
			return new ArrayList<Token>();
		}
		public String toString(){
			return getValue();
		}
		public String err(){
			return shortString();
		}
		public String err(int tab){
			StringBuilder builder = new StringBuilder();
			for(Integer i = 0;i<tab;i++){
				builder.append("  ");
			}
			builder.append(shortString());
			return builder.toString();
		}
		public void print(){
			printShort();
		}
		public void print(int tab){
			for(Integer i = 0;i<tab;i++){
				System.out.print("  ");
			}
			printShort();
		}
		public void printShort(){
			System.out.print(shortString());
		}
		public String shortString(){
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			builder.append(name);
			builder.append(":");
			builder.append(value);
			builder.append("]");
			return builder.toString();
		}
		public String getFileName(){
			return context.getFileName();
		}
		public int getLineNumber(){
			return context.getLineNumber(position);
		}
	}
	public static class Branch implements Token{
		protected Map<String,List<Token>> namedLists = new HashMap<String,List<Token>>();
		protected List<Token> children = new ArrayList<Token>();
		protected String name = null;
		protected Integer position = null;
		protected Integer parentPosition = null;
		protected Parser.Result.Pass context = null;
		public Branch(final String name, final Integer position, final Integer parentPosition, final Parser.Result.Pass context) {
			if(name != null){
				this.name = name;
			}
			if(position != null){
				this.position = position;
			}
			if(parentPosition != null){
				this.parentPosition = parentPosition;
			}
			if(context != null){
				this.context = context;
			}
		}
		public Branch() {
		}
		public Map<String,List<Token>> getNamedLists(){
			return namedLists;
		}
		public void setNamedLists(Map<String,List<Token>> newNamedLists){
			namedLists = newNamedLists;
		}
		public List<Token> getChildren(){
			return children;
		}
		public void setChildren(List<Token> newChildren){
			children = newChildren;
		}
		public String getName(){
			return name;
		}
		public void setName(String newName){
			name = newName;
		}
		public Integer getPosition(){
			return position;
		}
		public void setPosition(Integer newPosition){
			position = newPosition;
		}
		public Integer getParentPosition(){
			return parentPosition;
		}
		public void setParentPosition(Integer newParentPosition){
			parentPosition = newParentPosition;
		}
		public Parser.Result.Pass getContext(){
			return context;
		}
		public void setContext(Parser.Result.Pass newContext){
			context = newContext;
		}
		public Token get(String tokenName){
			List<Token> nameList = namedLists.get(tokenName);
			if(nameList==null||nameList.isEmpty()){
				return null;
			}
			else{
				return nameList.get(0);
			}
		}
		public String getValue(){
			return children.get(0).getValue();
		}
		public String toString(){
			return children.get(0).getValue();
		}
		public Token getLast(){
			return children.get(children.size()-1);
		}
		public Token getLast(String tokenName){
			return namedLists.get(tokenName).get(namedLists.get(tokenName).size()-1);
		}
		public void add(Token token){
			children.add(token);
			if(namedLists.containsKey(token.getName())==false){
				namedLists.put(token.getName(),new ArrayList<Token>());
			}
			namedLists.get(token.getName()).add(token);
		}
		public List<Token> getAll(){
			return children;
		}
		public List<Token> getAll(String key){
			return namedLists.get(key);
		}
		public List<Token> getAllSafely(String key){
			if(namedLists.containsKey(key)){
				return namedLists.get(key);
			}
			else{
				return new ArrayList<Token>();
			}
		}
		public String err(){
			StringBuilder builder = new StringBuilder();
			builder.append(":>");
			builder.append(name);
			for(Token node:children){
				builder.append(node.err(1));
			}
			return builder.toString();
		}
		public String err(int tab){
			StringBuilder builder = new StringBuilder();
			for(Integer i = 0;i<tab;i++){
				builder.append("  ");
			}
			builder.append(name);
			for(Token node:children){
				builder.append(node.err(tab+1));
			}
			return builder.toString();
		}
		public void print(){
			System.out.println(":>"+name);
			for(Token node:children){
				node.print(1);
			}
		}
		public void print(int tab){
			for(Integer i = 0;i<tab;i++){
				System.out.print("  ");
			}
			System.out.println(name);
			for(Token node:children){
				node.print(tab+1);
			}
		}
		public void printShort(){
			for(Token node:children){
				System.out.print("[");
				System.out.print(node.getName());
				System.out.print(":");
				System.out.print(node.getValue());
				System.out.print("]");
			}
			System.out.println();
		}
		public String getFileName(){
			return context.getFileName();
		}
		public int getLineNumber(){
			return context.getLineNumber(position);
		}
	}
}