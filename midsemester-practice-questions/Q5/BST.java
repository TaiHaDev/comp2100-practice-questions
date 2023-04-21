import java.util.LinkedList;
import java.util.Queue;

/**
 * Fill in the items below with your UID and name:
 * @author:
 * @UID:
 */
public class BST {
	Node root;

	public BST() {
		this.root = null;
	}

	/**
	 * Please implement this method and feel free to add additional helper methods
	 * @return The results which follows the rules specified in the given question sheet
	 */
	public String DNAGenerator(Integer key) {
		// START YOUR CODE
		Node foundNode = find(root, key);
		if (foundNode == null) return "CGTA";
		StringBuilder resultString = new StringBuilder();
		Queue<Node> queue = new LinkedList<>();
		queue.add(foundNode);
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			if (isOddChildren(current)) resultString.append(current.value);
			if (current.right != null) queue.add(current.right);
			if (current.left != null) queue.add(current.left);
		}
		return resultString.isEmpty() ? null : resultString.toString();
		// END YOUR CODE
	}
	public boolean isOddChildren(Node node) {
		return !((node.left == null && node.right == null) || (node.left != null && node.right != null));
	}

	/**
	 * Search for a node containing a given key
	 * @param n node
	 * @param key 
	 * @return the subtree of the node (inclusive) containing the given key or null if key is not found
	 */
	public Node find(Node n, Integer key) {
		if (n == null) {
			return null;
		}

		if (key == null) {
			return null;
		}

		int cmp = key.compareTo(n.key);
		if (cmp < 0) {
			return find(n.left, key);
		}
		else if (cmp > 0) {
			return find(n.right, key);
		}
		else {
			return n;
		}
	}

	/**
	 * This implementation of insert follows the pseudo code in the lecture slide.
	 * Note that we did not use recursion here.
	 *
	 * @param key value of inserted node
	 * @return inserted node (not the entire tree)
	 */
	public Node insert(Integer key, Character value) {
		Node parent = null;
		Node current = this.root;
		while (current != null) {
			if (current.key.compareTo(key) < 0) {
				parent = current;
				current = current.right;

			} else if (current.key.compareTo(key) > 0){
				parent = current;
				current = current.left;
			} else {
				parent = current;
				break;
			}
		}

		if (parent == null) {
			this.root = new Node(key, value);  // no parent = root is empty
			return root;
		} else {
			Node newNode = new Node(key, value);

			if (parent.key.compareTo(key) < 0) {
				parent.right = newNode;
				newNode.parent = parent;
			} else if (parent.key.compareTo(key) > 0) {
				parent.left = newNode;
				newNode.parent = parent;
			} else {
				parent.value = value;
				return parent;
			}

			return newNode;
		}
	}

	/**
	 * Sum all keys in the tree
	 * @param node
	 * @return the sum of all keys in the tree
	 */
	public int sum(Node node) {
		if (node == null || node.key == null) {
			return 0;
		}

		return node.key + sum(node.left) + sum(node.right);
	}
}
