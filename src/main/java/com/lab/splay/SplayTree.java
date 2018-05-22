package com.lab.splay;

public class SplayTree<T extends Comparable<T>> implements Tree<T> {

	int size;
	Node<T> root;

	@Override
	public void insert(T data) {

		Node<T> tempNode = root;
        Node<T> parentNode = null;

		while (tempNode != null) {
			parentNode = tempNode;
			if (data.compareTo(tempNode.getData()) < 0)
				tempNode = tempNode.getLeftNode();
			else
				tempNode = tempNode.getRightNode();
		}

		tempNode = new Node(data);
		tempNode.setParent(parentNode);

		if (tempNode.getParent() == null)
			root = tempNode;
		else if (tempNode.getData().compareTo(parentNode.getData()) < 0)
			parentNode.setLeftNode(tempNode);
		else
			parentNode.setRightNode(tempNode);

		this.size++;
		splay(tempNode);
	}

	private void rotateRight(Node<T> node) {

		Node<T> tempLeftNode = node.getLeftNode();

		node.setLeftNode(tempLeftNode.getRightNode());

		if (node.getLeftNode() != null)
			node.getLeftNode().setParent(node);

		tempLeftNode.setParent(node.getParent());

		if (tempLeftNode.getParent() == null)
			root = tempLeftNode;
		else if (node.getParent().getRightNode() == node)
			node.getParent().setRightNode(tempLeftNode);
		else
			node.getParent().setLeftNode(tempLeftNode);

		tempLeftNode.setRightNode(node);

		node.setParent(tempLeftNode);

	}

	private void rotateLeft(Node<T> node) {

		Node<T> tempRightNode = node.getRightNode();

		node.setRightNode(tempRightNode.getLeftNode());

		if (node.getRightNode() != null)
			node.getRightNode().setParent(node);

		tempRightNode.setParent(node.getParent());

		if (tempRightNode.getParent() == null)
			root = tempRightNode;
		else if (node.getParent().getLeftNode() == node)
			node.getParent().setLeftNode(tempRightNode);
		else
			node.getParent().setRightNode(tempRightNode);

		tempRightNode.setLeftNode(node);
		node.setParent(tempRightNode);

	}

	private void splay(Node<T> node) {

		while (node.getParent() != null) {

			// Zig
			if (node.getParent().getParent() == null) { // child of root node

				if (node.getParent().getLeftNode() == node)
					rotateRight(node.getParent());
				else
					rotateLeft(node.getParent());
			} else if (node.getParent().getLeftNode() == node
					&& node.getParent().getParent().getLeftNode() == node.getParent()) { // Zig
																							// Zig
				rotateRight(node.getParent().getParent());
				rotateRight(node.getParent());
			} else if (node.getParent().getRightNode() == node
					&& node.getParent().getParent().getRightNode() == node.getParent()) {
				rotateLeft(node.getParent().getParent());
				rotateLeft(node.getParent());
			} else if (node.getParent().getLeftNode() == node
					&& node.getParent().getParent().getRightNode() == node.getParent()) { // Zig Zag

				rotateRight(node.getParent());
				rotateLeft(node.getParent());
			}else{
				rotateLeft(node.getParent());
				rotateRight(node.getParent());
			}
		}

	}

	@Override
	public T find(T data) {

		Node<T> tempNode = root;

		while (tempNode != null) {

			if (data.compareTo(tempNode.getData()) < 0)
				tempNode = tempNode.getLeftNode();
			else if (data.compareTo(tempNode.getData()) > 0)
				tempNode = tempNode.getRightNode();
			else {
				splay(tempNode);
				return tempNode.getData();
			}
		}

		return null;

	}

	@Override
	public T getMax() {

		if (root != null)
			return getMax(root);

		return null;
	}

	private T getMax(Node<T> node) {

		if (node.getRightNode() != null)
			return getMax(node.getRightNode());
		else
			return node.getData();

	}

	@Override
	public T getMin() {

		if (root != null)
			return getMin(root);

		return null;
	}

	private T getMin(Node<T> node) {

		if (node.getLeftNode() != null)
			return getMin(node.getLeftNode());
		else
			return node.getData();

	}

	@Override
	public void traverse() {

		if (root != null)
			inOrderTraversal(root);
	}

	private void inOrderTraversal(Node<T> node) {

		if (node.getLeftNode() != null)
			inOrderTraversal(node.getLeftNode());

		System.out.print(node + " ");

		if (node.getRightNode() != null)
			inOrderTraversal(node.getRightNode());
	}

	@Override
	public T printRoot() {
		return root.getData();
	}

}
