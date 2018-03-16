package com.lab.linkedlist;

public class LinkedList<T extends Comparable<T>> implements List<T> {

	private Node<T> root;
	private int listSize;

	@Override
	public void insert(T data) {

		if (root == null)
			this.root = new Node<>(data);
		else
			insertAtBegin(data);
		++listSize;

	}

	private void insertAtBegin(T data) {

		Node<T> newNode = new Node<>(data);
		newNode.setNextNode(root);
		root = newNode;

	}

	private void insertAtEnd(T data, Node<T> node) {

		if (node.getNextNode() != null)
			insertAtEnd(data, node.getNextNode());
		else {
			Node<T> newNode = new Node<>(data);
			node.setNextNode(newNode);
		}

	}

	@Override
	public void remove(T data) {

		if (root == null)
			return;

		if (root.getData().compareTo(data) == 0)
			root = root.getNextNode();
		else
			remove(data, root, root.getNextNode());

		listSize--;
	}

	private void remove(T dataToRemove, Node<T> prevNode, Node<T> actualNode) {

		while (actualNode != null) {
			if (actualNode.getData().compareTo(dataToRemove) == 0) {
				prevNode.setNextNode(actualNode.getNextNode());
				actualNode = null;
				return;
			}

			prevNode = actualNode;
			actualNode = actualNode.getNextNode();
		}

	}

	@Override
	public void traverseList() {

		if (root == null)
			return;

		Node<T> node = root;

		while (node != null) {
			System.out.print(node + "->");
			node = node.getNextNode();
		}

	}

	@Override
	public int size() {
		return listSize;
	}

}
