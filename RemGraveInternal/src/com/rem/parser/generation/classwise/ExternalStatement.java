package com.rem.parser.generation.classwise;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.rem.parser.generation.*;
public class ExternalStatement extends ExternalImportEntry implements List<ExternalStatement>{
	protected int tabs = 0;
	protected ExternalContext context = new ExternalContext(false);

	public void setParentContext(ExternalContext newContext){
		context.setParent(newContext);
	}
	public ExternalContext getContext() {
		return context;
	}
	public void setTabs(int newTabs){
		tabs = newTabs;
	}
	private List<ExternalStatement> subStatements = new ArrayList<ExternalStatement>();
	private List<String> delimiters = new ArrayList<String>();
	protected String delimiter = "";
	protected String onEmptyDelimiter = null;
	protected Entry prefix = null;
	protected Entry suffix = null;
	private boolean negated = false;
	private boolean braced = false;
	public ExternalStatement(){
	}
	public ExternalStatement(String firstDelimiter, ExternalStatement...statements){
		delimiter = firstDelimiter;
		onEmptyDelimiter = firstDelimiter;
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, Entry initialSuffix, String firstDelimiter, ExternalStatement...statements){
		delimiter = firstDelimiter;
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			prefix = initialPrefix;
		}
		if(initialSuffix instanceof ExternalStatement){
			add((ExternalStatement) initialSuffix);
		}
		else {
			suffix = initialSuffix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, Entry initialSuffix, ExternalStatement...statements){
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			prefix = initialPrefix;
		}
		if(initialSuffix instanceof ExternalStatement){
			add((ExternalStatement) initialSuffix);
		}
		else {
			suffix = initialSuffix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, String firstDelimiter, ExternalStatement...statements){
		delimiter = firstDelimiter;
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			prefix = initialPrefix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, ExternalStatement...statements){
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			prefix = initialPrefix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public void negate(){
		negated = !negated;
	}
	public void brace(){
		braced = !braced;
	}
	public boolean add(ExternalStatement statement){
		this.addSubImport(statement);
		if(subStatements.size()>0){
			delimiters.add(delimiter);
		}
		else {
			delimiters.add("");
		}
		//System.out.println("#"+statement+"#"+bottomContext+"<"+statement.topContext+">"+statement.getContext());
		statement.setParentContext(context);

		if(statement instanceof ExternalVariableEntry){
			context.add(((ExternalVariableEntry)statement));
		}
		return subStatements.add(statement);
	}
	public void set(String newDelimiter){
		delimiter = newDelimiter;
	}
	public void debug(){
		StringBuilder builder = new StringBuilder();
		builder.append("[\n\tnegated =");
		builder.append(negated);
		builder.append("\n\tprefix ");
		if(prefix!=null){
			builder.append("{");
			prefix.get(builder);
			builder.append("}");
		}
		else {
			builder.append("null.");
		}
		for(int i=0;i<subStatements.size();++i){
			builder.append("\n\t");
			builder.append(i);
			builder.append(":");
			builder.append(delimiters.get(i));
			builder.append(" {");
			subStatements.get(i).get(builder);
			builder.append(" }");
		}
		builder.append("\n\tsuffix ");
		if(suffix!=null){
			builder.append("{");
			suffix.get(builder);
			builder.append("}");
		}
		else {
			builder.append("null.");
		}
		System.out.println(builder.toString());
	}
	@Override
	public void get(StringBuilder builder) {
		if(negated){
			builder.append("!");
		}
		if(braced){
			builder.append("(");
		}
		if(prefix!=null){
			if(prefix instanceof TabEntry){
				((TabEntry)prefix).setTab(tabs);
			}
			else if(prefix instanceof ExternalStatement){
				((ExternalStatement)prefix).setTabs(tabs);
			}
			prefix.get(builder);
		}
		if(subStatements.isEmpty()){
			if(onEmptyDelimiter!=null){
				builder.append(onEmptyDelimiter);
			}
		}
		else {
			for(int i=0;i<subStatements.size();++i){
				builder.append(delimiters.get(i));
				subStatements.get(i).setTabs(tabs);
				subStatements.get(i).get(builder);
			}
		}
		if(suffix!=null){
			if(suffix instanceof TabEntry){
				((TabEntry)suffix).setTab(tabs);
			}
			else if(suffix instanceof ExternalStatement){
				((ExternalStatement)suffix).setTabs(tabs);
			}
			suffix.get(builder);
		}
		if(braced){
			builder.append(")");
		}
	}






	@Override
	public int size() {
		return subStatements.size();
	}
	public Integer getSize(){
		return subStatements.size();
	}
	@Override
	public boolean isEmpty() {
		return subStatements.isEmpty();
	}
	@Override
	public boolean contains(Object o) {
		return subStatements.contains(o);
	}
	@Override
	public Iterator<ExternalStatement> iterator() {
		return subStatements.iterator();
	}
	@Override
	public Object[] toArray() {
		return subStatements.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return subStatements.toArray(a);
	}
	@Override
	public boolean remove(Object o) {
		return subStatements.remove(o);
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return subStatements.containsAll(c);
	}
	@Override
	public boolean addAll(Collection<? extends ExternalStatement> c) {
		for(ExternalStatement b:c){
			if(!add(b)){
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean addAll(int index, Collection<? extends ExternalStatement> c) {
		for(ExternalStatement b:c){
			add(index, b);
		}
		return true;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return subStatements.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return subStatements.retainAll(c);
	}
	@Override
	public void clear() {
		subStatements.clear();
	}
	@Override
	public ExternalStatement get(int index) {
		return subStatements.get(index);
	}
	@Override
	public ExternalStatement set(int index, ExternalStatement element) {
		super.addSubImport(element);
		element.setParentContext(context);
		return subStatements.set(index, element);
	}
	@Override
	public void add(int index, ExternalStatement element) {
		super.addSubImport(element);
		element.setParentContext(context);
		subStatements.add(index, element);
	}
	@Override
	public ExternalStatement remove(int index) {
		return subStatements.remove(index);
	}
	@Override
	public int indexOf(Object o) {
		return subStatements.indexOf(o);
	}
	@Override
	public int lastIndexOf(Object o) {
		return subStatements.lastIndexOf(o);
	}
	@Override
	public ListIterator<ExternalStatement> listIterator() {
		return subStatements.listIterator();
	}
	@Override
	public ListIterator<ExternalStatement> listIterator(int index) {
		return subStatements.listIterator(index);
	}
	@Override
	public List<ExternalStatement> subList(int fromIndex, int toIndex) {
		return subStatements.subList(fromIndex, toIndex);
	}

	public static class Body extends ExternalStatement {
		private boolean isCaptured = false; 
		public Body(ExternalStatement... entries) {
			super(new StringEntry("{"),"");
			context.setIsBody(true);
			addAll(Arrays.asList(entries));

		}
		@Override
		public void setTabs(int newTabs){
			this.tabs = newTabs+1;
		}
		@Override
		public void get(StringBuilder builder){

			if(isCaptured == false ){
				super.get(builder);
				new TabEntry(tabs-1,new StringEntry("}")).get(builder);
			}
			else {
				int trueTab = tabs;
				tabs = tabs - 1;
				super.get(builder);
				tabs = trueTab;
			}
		}
		public void add(ExternalStatement.Body otherBody){
			otherBody.prefix = null;
			otherBody.suffix = null;
			otherBody.isCaptured = true;
			super.add(otherBody);
		}
	}
	public static class Parameters extends ExternalStatement {
		public Parameters( ExternalStatement...statements){
			super(",",statements);
			onEmptyDelimiter = null;
		}
	}
	public static class ArrayParameters extends ExternalStatement {
		public ArrayParameters( ExternalStatement...statements){
			super("][",statements);
			onEmptyDelimiter = null;
			prefix = new StringEntry("[");
			suffix = new StringEntry("]");
		}
	}
	public static class NewObject extends ExternalStatement {
		public NewObject(ExternalStatement name){
			super(new StringEntry("new "),new StringEntry(")"),"(",name,new Parameters());
			this.addImport(new ImportEntry(name));
			StringBuilder nameBuilder = new StringBuilder();
			name.get(nameBuilder);
		}
		public NewObject(ExternalStatement name, ExternalStatement.Parameters parameters){
			super(new StringEntry("new "),new StringEntry(")"),"(",name,parameters);
			this.addImport(new ImportEntry(name));
			StringBuilder nameBuilder = new StringBuilder();
			name.get(nameBuilder);
		}
		public NewObject(ExternalStatement name, ExternalStatement.ArrayParameters parameters){
			super(new StringEntry("new "),"",name,parameters);
			this.addImport(new ImportEntry(name));
			StringBuilder nameBuilder = new StringBuilder();
			name.get(nameBuilder);
		}
		public NewObject(ExternalStatement name, ExternalStatement.Parameters parameters, ExternalStatement.ArrayParameters array){
			super(new StringEntry("new "),"",name,new ExternalStatement(new StringEntry("("),new StringEntry(")"),parameters),array);
			this.addImport(new ImportEntry(name));
			StringBuilder nameBuilder = new StringBuilder();
			name.get(nameBuilder);
		}
	}
	public static class TypeName extends ExternalStatement {
		public TypeName(Entry name){
			super(name);
			this.addImport(new ImportEntry(name));
		}
		public TypeName(Entry name, Entry templateParameters){
			super(name,templateParameters);
			this.addImport(new ImportEntry(name));
		}
	}
	public static class Conditional extends ExternalStatement {
		private ExternalStatement __BODY__;
		private StringEntry leftBrace = new StringEntry("(");
		private StringEntry rightBrace = new StringEntry(")");
		public Conditional(String name, ExternalStatement header, ExternalStatement body){
			super(new TabEntry(new StringEntry(name)));
			__BODY__ = body;
			if(header != null){
				super.add(new ExternalStatement(leftBrace,rightBrace,"",header));
			}
			if(__BODY__ instanceof ExternalStatement.Body){
				super.add(body);
			}
			else {
				body.context.setIsBody(true);
				super.add(body);
				//super.add(new ExternalStatement(new StringEntry("{"),new TabEntry(new StringEntry("}")),"",body));
			}
		}
		@Override
		public boolean add(ExternalStatement element){
			return __BODY__.add(element);
		}

		public ExternalStatement setBraces(String left, String right){
			leftBrace.set(left);
			rightBrace.set(right);
			return this;
		}
	}
}