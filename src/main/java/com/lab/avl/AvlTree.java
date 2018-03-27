package com.lab.avl;

public class AvlTree implements Tree {

	private Node root;

	@Override
	public void insert(int data) {

		root = insert(root, data);

	}

	private Node insert(Node node, int data) {

		if (node == null) {
			return new Node(data);
		}
		if (data < node.getData()) {
			node.setLeftChild(insert(node.getLeftChild(), data));
		} else {
			node.setRightChild(insert(node.getRightChild(), data));
		}
		node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);

		return setViolation(node, data);
	}

	private Node setViolation(Node node, int data) {

		int balance = getBalance(node);

		// heavy left left
		if (balance > 1 && data < node.getLeftChild().getData()) {
			System.out.println("Tree is left left heavy...");
			return rightRotation(node);
		}

		// heavy right right
		if (balance < -1 && data > node.getRightChild().getData()) {
			System.out.println("Tree is right right heavy...");
			return leftRotation(node);
		}

		// heavy left right
		if (balance > 1 && data > node.getLeftChild().getData()) {
			System.out.println("Tree is left Right heavy");
			node.setLeftChild(leftRotation(node.getLeftChild()));
			return rightRotation(node);
		}

		// heavy right left
		if (balance < -1 && data < node.getRightChild().getData()) {
			System.out.println("Tree is Right Left heavy");
			node.setRightChild(rightRotation(node.getRightChild()));
			return leftRotation(node);
		}

		return node;
	}

	private Node rightRotation(Node node) {

		System.out.println("Rotating to Right on Node :" + node);

		Node tempLeftNode = node.getLeftChild();
		Node t = tempLeftNode.getRightChild();

		tempLeftNode.setRightChild(node);
		node.setLeftChild(t);

		node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
		tempLeftNode.setHeight(Math.max(height(tempLeftNode.getLeftChild()), height(tempLeftNode.getRightChild())) + 1);

		return tempLeftNode;

	}

	private Node leftRotation(Node node) {

		System.out.println("Rotating to Left on Node :" + node);

		Node tempRightNode = node.getRightChild();
		Node t = tempRightNode.getLeftChild();

		tempRightNode.setLeftChild(node);
		node.setRightChild(t);

		node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
		tempRightNode
				.setHeight(Math.max(height(tempRightNode.getLeftChild()), height(tempRightNode.getRightChild())) + 1);

		return tempRightNode;

	}

	private int getBalance(Node node) {

		if (node == null)
			return 0;

		return height(node.getLeftChild()) - height(node.getRightChild());
	}

	private int height(Node node) {

		if (node == null)
			return -1;

		return node.getHeight();
	}

	@Override
	public void traverse() {

		if (root != null)
			inOrderTravesal(root);
	}

	private void inOrderTravesal(Node node) {

		if (node.getLeftChild() != null)
			inOrderTravesal(node.getLeftChild());

		System.out.print(node + " ");

		if (node.getRightChild() != null)
			inOrderTravesal(node.getRightChild());

	}

	@Override
	public void delete(int data) {

		if (root == null)
			return;

		delete(root, data);

	}

	private Node delete(Node node, int data) {

		if (node == null)
			return node;

		if (data < node.getData())
			node.setLeftChild(delete(node.getLeftChild(), data));
		else if (data > node.getData())
			node.setRightChild(delete(node.getRightChild(), data));
		else {

			// node leaf node
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				System.out.println("Removing a leaf node...");
				return null;
			}

			// no left node
			if (node.getLeftChild() == null) {
				System.out.println("Removing the right child...");
				Node tempNode = node.getRightChild();
				node = null;
				return tempNode;
			} else if (node.getRightChild() == null) {
				System.out.println("Removing the left child...");
				Node tempNode = node.getLeftChild();
				node = null;
				return tempNode;
			}

			// has both left and right node

			System.out.println("Removing item with two children...");
			Node temp = getPredecessor(node.getLeftChild());

			node.setData(temp.getData());
			node.setLeftChild(delete(node.getLeftChild(), temp.getData()));
		}

		node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);

		return settleDeletion(node);
	}

	private Node settleDeletion(Node node) {

		int balance = getBalance(node);

		// OK, we know the tree is left heavy BUT it can be left-right heavy or
		// left-left heavy
		if (balance > 1) {

			// left right heavy situation: left rotation on parent + right
			// rotation on grandparent
			if (getBalance(node.getLeftChild()) < 0) {
				node.setLeftChild(leftRotation(node.getLeftChild()));
			}

			// this is the right rotation on grandparent ( if left-left heavy,
			// thats single right rotation is needed
			return rightRotation(node);
		}

		// OK, we know the tree is right heavy BUT it can be left-right heavy or
		// right-right heavy
		if (balance < -1) {
			// right - left heavy so we need a right rotation ( on parent!!! )
			// before left rotation
			if (getBalance(node.getRightChild()) > 0) {
				node.setRightChild(rightRotation(node.getRightChild()));
			}

			// left rotation on grand parent
			return leftRotation(node);
		}

		return node;
	}

	private Node getPredecessor(Node node) {

		if (node.getRightChild() != null)
			getPredecessor(node.getRightChild());

		return node;
	}

}
