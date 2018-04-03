package com.lab.hash;

public class HashTable {

	private HashItem[] hashTable;

	public HashTable() {
		hashTable = new HashItem[Constants.TABLE_SIZE];
	}

	public int hash(int key) {
		
		return key % Constants.TABLE_SIZE;

		// return 1;
	}

	public void put(int key, int value) {

		int hashArrayVal = hash(key);

		if (hashTable[hashArrayVal] == null) {
			System.out.println("No collision simple insertion...");
			hashTable[hashArrayVal] = new HashItem(key, value);
		} else {
			System.out.println("Collision when inserting with key " + key);
			HashItem hashItem = hashTable[hashArrayVal];

			while (hashItem.getNextHashItem() != null) {
				hashItem = hashItem.getNextHashItem();
				System.out.println("Finding the place to insert" + hashItem.getValue());
			}

			System.out.println("Finally we have found the place to insert...");

			hashItem.setNextHashItem(new HashItem(key, value));
		}
	}

	public int get(int key) {

		int hashArrayIndex = hash(key);

		if (hashTable[hashArrayIndex] == null)
			return -1;

		HashItem hashItem = hashTable[hashArrayIndex];

		while (hashItem != null && hashItem.getKey() != key) {
			hashItem = hashItem.getNextHashItem();
		}

		if (hashItem == null)
			return -1;
		else
			return hashItem.getValue();
	}
}
