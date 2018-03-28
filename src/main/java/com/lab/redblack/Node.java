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
	
	

}
