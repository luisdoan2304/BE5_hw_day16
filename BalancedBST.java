package hw_day14;

public class BalancedBST {

	private Node root;

	private class Node {
		int value;
		Node left;
		Node right;
		int height;

		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
			this.height = 1;
		}
	}

	private int height(Node node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	private int balanceFactor(Node node) {
		if (node == null) {
			return 0;
		}
		return height(node.left) - height(node.right);
	}

	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		x.right = y;
		y.left = T2;

		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		return x;
	}

	private Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		y.left = x;
		x.right = T2;

		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		return y;
	}

	private Node minValueNode(Node node) {
		Node current = node;

		while (current.left != null) {
			current = current.left;
		}

		return current;
	}

	public Node add(Node node, int value) {
		if (node == null) {
			return new Node(value);
		}

		if (value < node.value) {
			node.left = add(node.left, value);
		} else if (value > node.value) {
			node.right = add(node.right, value);
		} else {
			return node; // duplicate values not allowed
		}

		node.height = 1 + Math.max(height(node.left), height(node.right));

		int balance = balanceFactor(node);

		if (balance > 1 && value < node.left.value) {
			return rightRotate(node);
		}

		if (balance < -1 && value > node.right.value) {
			return leftRotate(node);
		}

		if (balance > 1 && value > node.left.value) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balance < -1 && value < node.right.value) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	public Node delete(Node root, int value) {
		if (root == null) {
			return root;
		}

		if (value < root.value) {
			root.left = delete(root.left, value);
		} else if (value > root.value) {
			root.right = delete(root.right, value);
		} else {
			if (root.left == null || root.right == null) {
				Node temp = null;
				if (temp == root.left) {
					temp = root.right;
				} else {
					temp = root.left;
				}

				if (temp == null) {
					temp = root;
					root = null;
				} else {
					root = temp;
				}
			} else {
				Node temp = minValueNode(root.right);
				root.value = temp.value;
				root.right = delete(root.right, temp.value);
			}
		}

		if (root == null) {
			return root;
		}

		root.height = 1 + Math.max(height(root.left), height(root.right));

		int balance = balanceFactor(root);

		if (balance > 1 && balanceFactor(root.left) >= 0) {
			return rightRotate(root);
		}

		if (balance > 1 && balanceFactor(root.left) < 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		if (balance < -1 && balanceFactor(root.right) <= 0) {
			return leftRotate(root);
		}

		if (balance < -1 && balanceFactor(root.right) > 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
	}

	public Node get(Node root, int value) {
		if (root == null || root.value == value) {
			return root;
		}

		if (value < root.value) {
			return get(root.left, value);
		}

		return get(root.right, value);
	}

	public static void main(String[] args) {
		BalancedBST tree = new BalancedBST();

		tree.root = tree.add(tree.root, 10);
		tree.root = tree.add(tree.root, 20);
		tree.root = tree.add(tree.root, 30);

		tree.root = tree.delete(tree.root, 20);

		Node node = tree.get(tree.root, 10);
		if (node != null) {
			System.out.println("Node with value 10 found");
		} else {
			System.out.println("Node with value 10 not found");
		}
	}
}
