package com.lab.stack;

public class Stack<T extends Comparable<T>> {

	private Node<T> root;
	private int count;

	public void push(T data) {

		if (root == null)
			root = new Node<>(data);
		else {
			Node<T> oldData = root;
			root = new Node<>(data);
			root.setNextNode(oldData);
		}
		count++;
	}

	public T pop() {

		if (root == null) {
			System.out.println("Stack is Empty");
			return null;
		} else {
			T data = root.getData();
			root = root.getNextNode();
			count--;
			return data;
		}

	}

	public T peek() {

		if (root != null) {
			return root.data;
		} else {
			System.out.println("Stack is EMpty");
			return null;
		}

	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return root == null;
	}

}
