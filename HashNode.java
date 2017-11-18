/**
 * 
 * @author Rakesh Raju
 * 11/17/2017
 *
 */
public class HashNode {

	HashNode next;
	String  value;
	String key;
	
		public HashNode(String data, String key) {
			this.value = data;
			this.key = key;
		}
		
		public HashNode() {
			next = null;
			value = null;
			key = null;
		}
	
		public String getKey() {
			return key;
		}
		
		public String getData() {
			return value;
		}
}