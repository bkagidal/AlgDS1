package com.lab.bst;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	Node<T> root;

	@Override
	public void traversal() {

		if (root != null)
			inOrderTraversal(root);

	}

	private void inOrderTraversal(Node<T> node) {

		if (node.getLeftChild() != null)
			inOrderTraversal(node.getLeftChild());

		System.out.print(node + " ");

		if (node.getRightChild() != null)
			inOrderTraversal(node.getRightChild());

	}

	@Override
	public void insert(T data) {

		if (root == null)
			root = new Node<T>(data);
		else
			insertData(data, root);

	}

	private void insertData(T data, Node<T> node) {

		if (data.compareTo(node.getData()) < 0) {
			if (node.getLeftChild() != null)
				insertData(data, node.getLeftChild());
			else {
				Node<T> newNode = new Node<>(data);
				node.setLeftChild(newNode);
			}
		}

		if (data.compareTo(node.getData()) > 0) {
			if (node.getRightChild() != null)
				insertData(data, node.getRightChild());
			else {
				Node<T> newNode = new Node<>(data);
				node.setRightChild(newNode);
			}
		}
	}

	@Override
	public void delete(T data) {

		if (root != null)
			deleteValue(data, root);
	}

	private Node<T> deleteValue(T data, Node<T> node) {

		if (node == null)
			return node;

		if (data.compareTo(node.getData()) < 0) {
			node.setLeftChild(deleteValue(data, node.getLeftChild()));
		} else if (data.compareTo(node.getData()) > 0) {
			node.setRightChild(deleteValue(data, node.getRightChild()));
		} else { // we found the node to be deleted

			// node has no children its leaf node
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				return null;
			}

			// node has just right child
			if (node.getLeftChild() == null) {
				Node<T> tempNode = node.getRightChild();
				node = null;
				return tempNode;
			} else if (node.getRightChild() == null) { // node has left child
				Node<T> tempNode = node.getLeftChild();
				node = null;
				return tempNode;
			}

			// node has two children

			Node<T> tempNode = getPredecessor(node.getLeftChild());
			
			node.setData(tempNode.getData()); //First replace the data of last node with current node(node that needs be deleted. then delete that last node in next step
			node.setLeftChild(deleteValue(tempNode.getData(), node.getLeftChild()));; // delete the last node. we already copied the data of last
                                                                                      //node to node thats needs to	be deleted

		}

		return node;
	}

	private Node<T> getPredecessor(Node<T> node) {

		if (node.getRightChild() != null)
			return node.getRightChild();
		
		return node;
	}

	@Override
	public T getMax() {

		if (root == null)
			return null;

		return getMaxValue(root);
	}

	private T getMaxValue(Node<T> node) {

		if (node.getRightChild() != null) {
			return getMaxValue(node.getRightChild());
		}

		return node.getData();
	}

	@Override
	public T getMin() {

		if (root == null)
			return null;
		return getMinValue(root);
	}

	private T getMinValue(Node<T> node) {

		if (node.getLeftChild() != null) {
			return getMinValue(node.getLeftChild());
		}

		return node.getData();
	}

}
