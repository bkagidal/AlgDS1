package com.lab.stackArray;

public class App {
	
	public static void main(String[] args) {
		
		Stack<Integer> mystack = new Stack<>();
		
		mystack.push(1);
		mystack.push(10);
		mystack.push(100);
		
		System.out.println(mystack.pop());
		System.out.println(mystack.pop());
		System.out.println(mystack.pop());
		
	}

}
