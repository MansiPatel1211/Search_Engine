package Main;

import java.util.HashMap;

public class Node<t> {
	t value;
	char key;
	
	HashMap<Character, Node<t>> child;//create child of node in the hash map
	
	public Node() {
		this.child = new HashMap<Character, Node<t>>();
	}
	
	public Node(char key) {
		this.key = key;
		this.child = new HashMap<Character, Node<t>>();
	}
}
