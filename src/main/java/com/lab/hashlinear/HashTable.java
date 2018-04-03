package com.lab.hashlinear;

public class HashTable {

	private HashItem[] hashItem;

	public HashTable() {
		hashItem = new HashItem[Constants.TABLE_SIZE];
	}

	public int hashFun(int key) {

		//return key % Constants.TABLE_SIZE;

		return 0;
	}

	public void put(int key, int value) {

		int generateHashKey = hashFun(key);

		while (hashItem[generateHashKey] != null) {
			System.out.println("Collision at index :"+generateHashKey);
			generateHashKey = (generateHashKey + 1) % Constants.TABLE_SIZE;
			System.out.println("Trying to find next insert pos at index :" + generateHashKey);
		}

		System.out.println("Inserting at index :" + generateHashKey);
		hashItem[generateHashKey] = new HashItem(key, value);
	}
	
	public int get(int key){
		
		int generatedKey = hashFun(key);
		
		while(hashItem[generatedKey] != null && hashItem[generatedKey].getKey() != key){
			generatedKey = (generatedKey+1) % Constants.TABLE_SIZE;
		}
		
		
		if(hashItem[generatedKey] == null)
			return -1;
		else
			return hashItem[generatedKey].getValue();
	}
}
