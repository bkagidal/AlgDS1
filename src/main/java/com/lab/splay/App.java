package com.lab.splay;

public class App {

	public static void main(String[] args) {
		
		Tree<Integer> splayTree = new SplayTree<Integer>();
		
		splayTree.insert(10);
		splayTree.insert(0);
		splayTree.insert(234);
		splayTree.insert(-3);
		splayTree.insert(23);
		splayTree.insert(-56);
		splayTree.insert(78);
		
		System.out.println(splayTree.printRoot());
		splayTree.traverse();
		System.out.println("\n");
		System.out.println(splayTree.getMax());
		System.out.println(splayTree.getMin());
		System.out.println("\n");
		System.out.println(splayTree.find(23));
		
		System.out.println(splayTree.printRoot());
		splayTree.traverse();
		
	}

}
