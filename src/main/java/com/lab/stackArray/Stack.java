package com.lab.stackArray;

public class Stack<T> {

	private T[] stack;
	private int noOfItems;

	public Stack() {
		stack = (T[]) new Object[1];
	}

	public void push(T data) {

		if (noOfItems == stack.length)
			resize(2 * stack.length);

		stack[noOfItems++] = data;

	}

	public T pop() {
		
		T popItem = stack[--noOfItems];

		if (noOfItems > 0 && noOfItems == stack.length / 4)
			resize(stack.length / 2);

		return popItem;
	}

	private void resize(int capacity) {

		T[] stackCopy = (T[]) new Object[capacity];

		for (int i = 0; i < noOfItems; i++)
			stackCopy[i] = stack[i];

		stack = stackCopy;
	}

	public boolean isEmpty() {
		return noOfItems == 0;
	}

	public int size() {
		return noOfItems;
	}

}
