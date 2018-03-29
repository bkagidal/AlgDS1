package com.lab.redblack;

public class Node {

	private int data;
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	private NodeColor color;

	public Node(int data) {
		this.data = data;
		this.color = NodeColor.RED;
	}
	
	@Override
	public String toString() {
		return ""+data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public NodeColor getColor() {
		return color;
	}

	public void setColor(NodeColor color) {
		this.color = color;
	}

}
