package com.lab.redblack;

public class App {
	
	public static void main(String[] args) {

    	RedBlackTree tree = new RedBlackTree();
       
        tree.insertData(10);
        tree.traverse();;
        tree.insertData(20);
        tree.traverse();
        tree.insertData(30);
        tree.traverse();
        tree.insertData(15);
      
        tree.traverse();
    }

}
