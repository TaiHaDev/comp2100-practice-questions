import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Given a binary search tree, implement a method to find the sum of the values of all the nodes that have an odd number of direct children. You can define additional methods of BST and Node classes to complete the task. The method signature is: public Integer oddNodeSum()
 *
 */
public class BST {
	public static void main(String[] args) {
		BST bst = new BST();
		bst.insert(3);
		bst.insert(2);
		System.out.println(bst.oddNodeSum());

	}

	Node root;

	/**
	 * Please implement this method and feel free to add additional helper methods
	 * @return
	 */
	public Integer oddNodeSum() {
		// START YOUR CODE


		return oddNodeSum(root); //you are allowed to change this return statement
		// END YOUR CODE
	}
	public Integer oddNodeSum(Node node) {
		Integer result = 0;
		if (node == null) return 0;
		if (isChildrenOdd(node)) {
			result = node.value;
		}
		return oddNodeSum(node.left) + oddNodeSum(node.right) + result;
	}

	public boolean isChildrenOdd(Node node) {
		return !((node.left == null && node.right == null) || (node.left != null && node.right != null));
	}

	public BST() {
		this.root = null;
	}

	/**
	 * This implementation of insert follows the pseudocode in the lecture slide.
	 * Node that we did not use recursion here.
	 *
	 * @param value value of inserted node
	 * @return inserted node (not the entire tree)
	 */
	public Node insert(Integer value) {
		Node parent = null;
		Node current = this.root;
		while (current != null) {
			if(current.value.compareTo(value) < 0) {
				parent = current;
				current = current.right;
			}else if (current.value.compareTo(value) > 0){
				parent = current;
				current = current.left;
			}
		}

		if (parent == null && current == null) {
			this.root = new Node(value);  // no parent = root is empty
			return root;
		}else {
			Node newNode = new Node(value);

			if(parent.value.compareTo(value) < 0) {
				parent.right = newNode;
				newNode.parent = parent;
			}else if(parent.value.compareTo(value) > 0) {
				parent.left = newNode;
				newNode.parent = parent;
			}
			return newNode;
		}
	}

	public class Node {

		Integer value;
		Node parent;
		Node left;
		Node right;

		public Node(Integer value) {
			this.value = value;
			this.parent = null;
			this.left = null;
			this.right = null;
		}
	}
}