/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 * @author huy.pham
 */
public class BST {	
	Node root;

	/**
	 * Node class
	 */
	public class Node {
		String value;
		Node parent;
		Node left;
		Node right;

		public Node(String value) {
			this.value = value;
			this.parent = null;
			this.left = null;
			this.right = null;
		}

		/**
		 * @param s
		 * @return the node that has the given value.
		 */
	    public Node find(String s) {
			// TODO: Add your implementation here.
			if (this.value.compareTo(s) > 0) {
				if (this.left != null) return this.left.find(s);
				else return null;
			} else if (this.value.compareTo(s) < 0) {
				if (this.right != null) return this.right.find(s);
				else return null;
			} else {
				return this;
			}
		}

		/**
		 * Insert a new node into the tree
		 * @param s
		 * @return {@link Node}
		 */
		public Node insert(String s) {
			// TODO: Add your implementation here.
			if (this.value.compareTo(s) > 0) {
				if (this.left != null) {
					this.left = this.left.insert(s);
				} else {
					this.left = new Node(s);
				}
			} else if (this.value.compareTo(s) < 0) {
				if (this.right != null) {
					this.right = this.right.insert(s);
				} else {
					this.right = new Node(s);
				}
			}
			return this;
		}
		
		/**
		 * @return pre-order traversal of the nodes that have odd number of children.
		 */
		public String printOddNodes() {
			// TODO: Add your implementation here.
			StringBuilder stringBuilder = new StringBuilder();
			if (left != null && right == null || (left == null && right != null)) {
				stringBuilder.append(value).append(" ");
			}

			if (left != null) {
				stringBuilder.append(left.printOddNodes());
			}
			if (right != null) {
				stringBuilder.append(right.printOddNodes());
			}
			return stringBuilder.toString();
		}
	}

	public String printOddNodes() {
		return root.printOddNodes();
	}
	
	public BST() {
		root = null;
	}

	public Node insert(String value) {
		if (root == null) {
			root = new Node(value);
			return root;
		}

		return root.insert(value);
	}
	
	public Node find(String s) {
		if (root == null) {
			return null;
		}
		
		return root.find(s);
	}
}