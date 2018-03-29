package com.lab.redblack;

import javax.swing.text.AbstractDocument.LeafElement;

public class RedBlackTree implements Tree {

	private Node root;

	@Override
	public void traverse() {
		if (root == null)
			return;

		inOrderTraversal(root);

	}

	private void inOrderTraversal(Node node) {

		if (node.getLeftChild() != null)
			inOrderTraversal(node.getLeftChild());
		System.out.print(node + " ");

		if (node.getRightChild() != null)
			inOrderTraversal(node.getRightChild());

	}

	@Override
	public void insertData(int newData) {

		Node node = new Node(newData);
		root = inserDataIntoTree(root, node);

		fixViolations(node);
	}

	private void fixViolations(Node node) {

		Node parentNode = null;
		Node grandParentNode = null;

		while (node != root && node.getColor() != NodeColor.BLACK && node.getParent().getColor() == NodeColor.RED) {

			parentNode = node.getParent();
			grandParentNode = node.getParent().getParent();

			if (parentNode == grandParentNode.getLeftChild()) {

				Node uncle = grandParentNode.getRightChild();

				if (uncle != null && uncle.getColor() == NodeColor.RED) {
					grandParentNode.setColor(NodeColor.RED);
					parentNode.setColor(NodeColor.BLACK);
					uncle.setColor(NodeColor.BLACK);
					node = grandParentNode;
				} else if (node == parentNode.getRightChild()) {
					rotateLeft(parentNode);
					node = parentNode;
					parentNode = node.getParent();
				}
				
				rotateRight(grandParentNode);
				System.out.println("Recoloring "+parentNode+" + "+grandParentNode);
				NodeColor tempcolr = parentNode.getColor();
				parentNode.setColor(grandParentNode.getColor());
				grandParentNode.setColor(tempcolr);
				
				node = parentNode;
			}else{
				Node uncle = grandParentNode.getLeftChild();
				
				if(uncle != null && uncle.getColor() == NodeColor.RED){
					grandParentNode.setColor(NodeColor.RED);
					parentNode.setColor(NodeColor.BLACK);
					uncle.setColor(NodeColor.BLACK);
					node = grandParentNode;
				}else if(node == parentNode.getLeftChild()){
					rotateRight(parentNode);
					node = parentNode;
					parentNode = node.getParent();
				}
				
				rotateLeft(grandParentNode);
				System.out.println("Recoloring "+parentNode+" + "+grandParentNode);
				NodeColor tempcolr = parentNode.getColor();
				parentNode.setColor(grandParentNode.getColor());
				grandParentNode.setColor(tempcolr);
				
				node = parentNode;
				
			}

		}
		
		if(root.getColor() == NodeColor.RED)
			root.setColor(NodeColor.BLACK);
		

	}

	private Node inserDataIntoTree(Node root, Node node) {

		if (root == null)
			return node;

		if (node.getData() < root.getData()) {
			root.setLeftChild(inserDataIntoTree(root.getLeftChild(),node));
			root.getLeftChild().setParent(root);
		} else if (node.getData() > root.getData()) {
			root.setRightChild(inserDataIntoTree(root.getRightChild(),node));
			root.getRightChild().setParent(root);
		}
		return root;
	}

	private void rotateRight(Node node) {
		System.out.println("Rotate Right on node :" + node);

		Node tempLeftNode = node.getLeftChild();
		node.setLeftChild(tempLeftNode.getRightChild());

		if (node.getLeftChild() != null)
			node.getLeftChild().setParent(node);// this is original right child
												// of left node before rotating

		tempLeftNode.setParent(node.getParent());

		if (tempLeftNode.getParent() == null)
			root = tempLeftNode;
		else if (node == node.getParent().getLeftChild())
			node.getParent().setLeftChild(tempLeftNode);
		else
			node.getParent().setRightChild(tempLeftNode);

		tempLeftNode.setRightChild(node);
		node.setParent(tempLeftNode);
	}

	private void rotateLeft(Node node) {

		System.out.println("Rotating left on node :" + node);
		Node tempRightNode = node.getRightChild();
		node.setRightChild(tempRightNode.getLeftChild());

		if (node.getRightChild() != null)
			node.getRightChild().setParent(node);

		tempRightNode.setParent(node.getParent());

		if (tempRightNode.getParent() == null)
			root = tempRightNode;
		else if (node == node.getParent().getLeftChild())
			node.getParent().setLeftChild(tempRightNode);
		else
			node.getParent().setRightChild(tempRightNode);

		tempRightNode.setLeftChild(node);
		node.setParent(tempRightNode);
	}

}
