/**
 * 
 * @author Rakesh Raju
 * 11/17/2017
 *
 */

public class Hashtable {

	HashNode[] table;
	int size;
	int tableSize = 300000;

	public Hashtable() {
		size = 0;
		table = new HashNode[tableSize];
	}

	//returns amount of key/value pairs in the hashtable
	public int getSize() {
		return size;
	}

	//puts a key/value pair into the Hashtable instance
	public void put(String key, String val) {
		if (isFull()) {
			resize();
		}

		//hashes the value
		int hash = (hashValue(key) % tableSize);
		//determines whether the location is empty, inserts it immediately if null
		if (table[hash] == null) {
			table[hash] = new HashNode(val, key);
			size++;
		} else {
			HashNode temp = table[hash];
			//searches for the next null instance,
			//and will add it there if it doesn't find the key to already exist 
			//in the hashtable, which then just updates the value
			while (temp.next != null && !temp.key.equals(key)) {
				temp = temp.next;
			}
			if (temp.key.equals(key)) {
				temp.value = val;
			} else {
				temp.next = new HashNode(val, key);
			}

			size++;
		}

	}

	private boolean isFull() {
		if (size == tableSize) {
			return true;
		}

		return false;
	}

	//returns the value of a provided key in the hashtable instance
	public String get(String key) {
		int hash = (hashValue(key) % tableSize);

		HashNode temp = table[hash];
		while (temp != null) {
			if (temp.getKey().equals(key)) {
				return temp.getData();
			}

			temp = temp.next;
		}

		return null;

	}

	//removes the value from the hashtable with the provided key
	public String remove(String key) {
		int hash = (hashValue(key) % tableSize);
		if (table[hash] != null) {
			HashNode prevtemp = null;
			HashNode temp = table[hash];
			while (temp.next != null && !temp.key.equals(key)) {
				prevtemp = temp;
				temp = temp.next;
			}
			if (temp.key.equals(key)) {
				String value = temp.getData();
				if (prevtemp == null) {
					table[hash] = temp.next;
				} else {
					prevtemp.next = temp.next;
				}
				size--;
				return value;
			}
		}
		return null;

	}

	public boolean containsKey(String key) {
		int hash = (hashValue(key) % tableSize);
		if (table[hash] == null) {
			return false;
		} else {
			HashNode temp = table[hash];
			while (temp != null && !temp.getKey().equals(key)) {
				temp = temp.next;
			}
			if (temp == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	//hashes the key
	public int hashValue(String key) {
		int hashValue = key.hashCode();
		hashValue %= tableSize;

		if (hashValue < 0) {
			hashValue += tableSize;
		}
		return hashValue;
	}

	private void resize() {
		tableSize = tableSize * 2;
		HashNode[] temp = new HashNode[tableSize];

		for (int i = 0; i < table.length; i++) {
			temp[i] = table[i];
		}

		table = temp;
	}

}
