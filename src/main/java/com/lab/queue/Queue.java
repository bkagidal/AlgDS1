package com.lab.queue;

public class Queue<T extends Comparable<T>> {

	private Node<T> firstNode;
	private Node<T> lastNode;
	private int count;

	public boolean isEmpty() {
		return this.firstNode == null;
	}

	public int size() {
		return count;
	}

	public void enqueue(T newData) {

		this.count++;

		Node<T> oldLastNode = this.lastNode;
		this.lastNode = new Node<>(newData);
		this.lastNode.setNextNode(null);

		if (isEmpty())
			this.firstNode = this.lastNode;
		else
			oldLastNode.setNextNode(lastNode);
	}

	public T dequeue() {

		this.count--;

		T dataTodelete = firstNode.getData();

		firstNode = firstNode.getNextNode();

		if (isEmpty())
			lastNode = null;

		return dataTodelete;
	}

	public void print() {

		Node<T> temp = firstNode;

		while (temp != null) {
			System.out.print(temp +" ");
			temp = temp.getNextNode();
		}
	}

}
