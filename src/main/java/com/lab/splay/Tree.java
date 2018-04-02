package com.lab.splay;

public interface Tree<T extends Comparable<T>> {
	
	public void insert(T data);
	public T find(T data);
	public T getMax();
	public T getMin();
	public T printRoot();
	public void traverse();

}
