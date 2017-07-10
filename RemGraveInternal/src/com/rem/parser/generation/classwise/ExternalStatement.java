package com.rem.parser.generation.classwise;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.rem.parser.generation.*;
public class ExternalStatement extends ExternalImportEntry implements List<ExternalStatement>{
	protected int tabs = 0;
	private ExternalContext topContext = new ExternalContext(null);
	private ExternalContext bottomContext = new ExternalContext(topContext);

	public void setParentContext(ExternalContext newContext){
		topContext.setParent(newContext);
	}
	public ExternalContext getContext() {
		return bottomContext;
	}
	public void setTabs(int newTabs){
		tabs = newTabs;
	}
	private List<ExternalStatement> subStatements = new ArrayList<ExternalStatement>();
	private List<String> delimiters = new ArrayList<String>();
	protected String delimiter = "";
	protected Entry prefix = null;
	protected Entry suffix = null;
	private boolean negated = false;
	public ExternalStatement(){
	}
	public ExternalStatement(String firstDelimiter, ExternalStatement...statements){
		delimiter = firstDelimiter;
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, Entry initialSuffix, String firstDelimiter, ExternalStatement...statements){
		prefix = initialPrefix;
		suffix = initialSuffix;
		delimiter = firstDelimiter;
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, Entry initialSuffix, ExternalStatement...statements){
		prefix = initialPrefix;
		suffix = initialSuffix;
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, String firstDelimiter, ExternalStatement...statements){
		prefix = initialPrefix;
		delimiter = firstDelimiter;
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, ExternalStatement...statements){
		prefix = initialPrefix;
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public void negate(){
		negated = !negated;
	}
	public boolean add(ExternalStatement statement){
		this.addSubImport(statement);
		if(subStatements.size()>0){
			delimiters.add(delimiter);
		}
		else {
			delimiters.add("");
		}
		statement.setParentContext(this.bottomContext);
		bottomContext = statement.getContext();
		return subStatements.add(statement);
	}
	public void set(String newDelimiter){
		delimiter = newDelimiter;
	}
	@Override
	public void get(StringBuilder builder) {
		if(negated){
			builder.append("!");
		}
		if(prefix!=null){
			if(prefix instanceof TabEntry){
				((TabEntry)prefix).setTab(tabs);
			}
			prefix.get(builder);
		}
		for(int i=0;i<subStatements.size();++i){
			builder.append(delimiters.get(i));
			subStatements.get(i).setTabs(tabs);
			subStatements.get(i).get(builder);
		}
		if(suffix!=null){
			if(suffix instanceof TabEntry){
				((TabEntry)suffix).setTab(tabs);
			}
			suffix.get(builder);
		}

	}






	@Override
	public int size() {
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
		if(index==0){
			element.setParentContext(topContext);
			subStatements.get(index+1).getContext().setParent(element.getContext());
		}
		else if(index>=size()){
			element.setParentContext(bottomContext);
			bottomContext = element.getContext();
		}
		else {
			element.setParentContext(subStatements.get(index-1).getContext());
			subStatements.get(index+1).getContext().setParent(element.getContext());
		}
		return subStatements.set(index, element);
	}
	@Override
	public void add(int index, ExternalStatement element) {
		super.addSubImport(element);
		if(index==0){
			element.setParentContext(topContext);
			subStatements.get(index).getContext().setParent(element.getContext());
		}
		else if(index>=size()){
			element.setParentContext(bottomContext);
			bottomContext = element.getContext();
		}
		else {
			element.setParentContext(subStatements.get(index-1).getContext());
			subStatements.get(index).getContext().setParent(element.getContext());
		}
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

		public Body(ExternalStatement... entries) {
			super(new StringEntry("{"),new TabEntry(new StringEntry("}")),"",entries);
		}

	}
	public static class Parameters extends ExternalStatement {
		public Parameters( ExternalStatement...statements){
			super(",",statements);
		}
	}
}