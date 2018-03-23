package com.lab.bst;

public class App {
	
	public static void main(String[] args) {
		Tree<Integer> bst = new BinarySearchTree<>();
		
		bst.insert(20);
		bst.insert(10);
		bst.insert(30);
		bst.insert(5);
		bst.insert(12);
		
		bst.traversal();
		
		//System.out.println("\n"+bst.getMax());
		//System.out.println("\n"+bst.getMin());
		
		bst.delete(20);
		System.out.println("\n");
		bst.traversal();
	}

}
