package com.lab.splay;

public class Node<T> {

	T data;
	Node<T> leftNode;
	Node<T> rightNode;
	Node<T> parent;

	Node(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node<T> leftNode) {
		this.leftNode = leftNode;
	}

	public Node<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node<T> rightNode) {
		this.rightNode = rightNode;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

}
