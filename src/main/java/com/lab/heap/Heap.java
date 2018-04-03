package com.lab.heap;

public class Heap {

	Integer[] heap;
	int currentPosition = -1;

	public Heap(int size) {
		heap = new Integer[size];
	}

	public void insert(int item) {

		if (isFull()) {
			throw new RuntimeException("Heap is already is full...");
		}

		heap[++currentPosition] = item;
		fixUp(currentPosition);
	}

	private void fixUp(int index) {

		int parentIndex = (index - 1) / 2;

		while (parentIndex >= 0 && heap[parentIndex] < heap[index]) {
			int temp = heap[index];
			heap[index] = heap[parentIndex];
			heap[parentIndex] = temp;
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
	}

	public int getMax() {
		int result = heap[0];

		heap[0] = heap[currentPosition--];
		heap[currentPosition + 1] = null;
		fixdown(0, -1);
		return result;

	}

	private void fixdown(int index, int upto) {

		if (upto < 0)
			upto = currentPosition;

		while (index <= upto) {
			int leftChild = 2 * index + 1;
			int rightChild = 2 * index + 2;
			if (leftChild <= upto) {

				int childTOSwap;

				if (rightChild > upto)
					childTOSwap = leftChild;
				else
					childTOSwap = (heap[leftChild] > heap[rightChild]) ? leftChild : rightChild;

				if (heap[index] < heap[childTOSwap]) {
					int temp = heap[childTOSwap];
					heap[childTOSwap] = heap[index];
					heap[index] = temp;
				} else
					break;

				index = childTOSwap;
			} else
				break;

		}

	}

	public void heapsort() {
		for (int i = 0; i <= currentPosition; i++) {
			int temp = this.heap[0]; //
			System.out.print(temp + " ");
			this.heap[0] = this.heap[currentPosition - i];
			this.heap[currentPosition - i] = temp;
			fixdown(0, currentPosition - i - 1);
		}
	}

	private boolean isFull() {
		return this.currentPosition == heap.length;
	}
}
