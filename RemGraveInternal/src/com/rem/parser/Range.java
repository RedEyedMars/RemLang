package com.rem.parser;

public class Range {

	private int front;
	private int back;

	public Range(int front, int back){
		this.front = front;
		this.back = back;
	}
	public boolean contains(int middle){
		if(back==-1){
			return middle>=front;
		}
		else {
			return middle>=front&&middle<back;
		}
	}
	public void setBack(int newBack){
		this.back = newBack;
	}
	public boolean startsWith(int position) {
		return this.front == position;
	}
}
