package com.lab.generallinear;

@SuppressWarnings("unchecked")
public class HashTable<Key, Value> {

	private Key[] keys;
	private Value[] values;
	private int capacity;
	private int noOfItems;

	public HashTable() {

		keys = (Key[]) new Object[Constants.TABLE_SIZE];
		values = (Value[]) new Object[Constants.TABLE_SIZE];
		capacity = Constants.TABLE_SIZE;
		noOfItems = 0;
	}

	public HashTable(int newCapacity) {

		keys = (Key[]) new Object[newCapacity];
		values = (Value[]) new Object[newCapacity];
		capacity = newCapacity;
		noOfItems = 0;

	}

	public int size() {
		return this.noOfItems;
	}

	public boolean isEmpty() {
		return this.noOfItems == 0;
	}

	public int hash(Key key) {

		return key.hashCode() % capacity;
	}

	public Value get(Key key) {

		if (key == null)
			return null;

		int index = hash(key);

		while (keys[index] != null) {

			if (keys[index].equals(key))
				return values[index];
			index = (index + 1) % capacity;
		}

		return null;
	}

	public void put(Key key, Value val) {

		if (key == null || val == null)
			return;

		if (this.noOfItems > this.capacity * 0.75)
			resize(2 * this.capacity);

		int index = hash(key);

		while (keys[index] != null) {

			if (keys[index].equals(key)) {
				values[index] = val;
				return;
			}
			index = (index + 1) % capacity;
		}

		keys[index] = key;
		values[index] = val;

		this.noOfItems++;
	}

	public void remove(Key key) {

		if (key == null)
			return;

		int index = hash(key);

		while (!keys[index].equals(key)) {
			index = (index + 1) % capacity;
		}

		keys[index] = null;
		values[index] = null;
		this.noOfItems--;

		index = (index + 1) % capacity;

		while (keys[index] != null) {

			Key tempkey = keys[index];
			Value tempVal = values[index];

			keys[index] = null;
			values[index] = null;
			this.noOfItems--;

			put(tempkey, tempVal);
			
			index = (index+1) % capacity;

		}

		if (this.noOfItems < capacity / 3)
			resize(capacity / 2);
	}

	private void resize(int newCapacity) {

		System.out.println("Resize table with new capacity: " + newCapacity);
		HashTable<Key, Value> newTable = new HashTable(newCapacity);

		for (int i = 0; i < capacity; i++) {

			if (keys[i] != null)
				newTable.put(keys[i], values[i]);
		}

		keys = newTable.getKeys();
		values = newTable.getValues();
		capacity = newTable.getCapacity();

	}

	public Key[] getKeys() {
		return keys;
	}

	public void setKeys(Key[] keys) {
		this.keys = keys;
	}

	public Value[] getValues() {
		return values;
	}

	public void setValues(Value[] values) {
		this.values = values;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

}
