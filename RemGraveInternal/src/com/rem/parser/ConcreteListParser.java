package com.rem.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class ConcreteListParser extends ConcreteParser implements List<IParser>{

	private List<IParser> list;
	public ConcreteListParser(){
		list = new ArrayList<IParser>();
	}
	public ConcreteListParser(int length){
		list = new ArrayList<IParser>(length);
	}
	
	
	@Override
	public boolean add(IParser e) {
		return list.add(e);
	}

	@Override
	public void add(int index, IParser element) {
		list.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends IParser> c) {
		return list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends IParser> c) {
		return list.addAll(index, c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public IParser get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<IParser> iterator() {
		return list.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<IParser> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<IParser> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public IParser remove(int index) {
		return list.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public IParser set(int index, IParser element) {
		return list.set(index, element);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<IParser> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

}
