package treeDS;

import java.util.*;

public class BinaryTree {

	public static final int GLOBALSPACE = 5;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BST bt = new BST();
		boolean flag = true;
		int val = 0;
		while (flag) {
			System.out.println();
			System.out.println("1  - Insert a Node(Iterative Approach)");
			System.out.println("2. - Insert a Node(Recursive Approach)");
			System.out.println("3  - Insert a Node(Level Order Insertion)");
			System.out.println("4  - Print 2D");
			System.out.println("5  - Height of Tree");
			System.out.println("6  - Sum of all Nodes in the Tree");
			System.out.println("7  - Total Number of Nodes in the Tree");
			System.out.println("8  - Maximum Value in the Binary Tree");
			System.out.println("9  - Print Level Order/Breadth First Search (using Queue)");
			System.out.println("10 - Print Level Order/Breadth First Search (using Recursion)");
			System.out.println("11 - Print a Certain Level of the Tree");
			System.out.println("12 - Sum of values at level K");
			System.out.println("13 - Print Pre-order Traversal");
			System.out.println("14 - Print In-order Traversal");
			System.out.println("15 - Print Post-order Traversal");
//			System.out.println("3  - Delete a Node");
			System.out.println("0  - Exit");

			int ch = sc.nextInt();
			switch (ch) {
			case 0:
				flag = false;
				break;
			case 1:
				System.out.print("Enter Value to Insert: ");
				val = sc.nextInt();
				bt.insertIterative(val);
				break;
			case 2:
				System.out.print("Enter Value to Insert: ");
				val = sc.nextInt();
				bt.root = bt.insertRecursive(bt.root, val);
				break;
			case 3:
				System.out.println("Enter Value to Insert: ");
				val = sc.nextInt();
				bt.insertLevelOrder(bt.root, val);
				break;
			case 4:
				bt.print2D(bt.root, GLOBALSPACE);
				break;
			case 5:
				System.out.println("Height of the Tree is = " + bt.height(bt.root));
				break;
			case 6:
				System.out.println("Sum of all the Nodes in the Tree is = " + bt.sumOfNodes(bt.root));
				break;
			case 7:
				System.out.println("Total Number of Nodes in the Tree is = " + bt.countNodes(bt.root));
				break;
			case 8:
				System.out.println("The Maximum Value of the Binary Tree is = " + bt.maxValue(bt.root));
				break;
			case 9:
				System.out.println("The Binary Tree in Level Order/Breadth First Search (using Queue) is = ");
				bt.printLevelOrderQueue(bt.root);
				break;
			case 10:
				System.out.println("The Binary Tree in Level Order/Breadth First Search (using Recursion) is = ");
				bt.printLevelOrderRecursion(bt.root);
				break;
			case 11:
				System.out.print("Enter the Level to print Level Order: ");
				int l = sc.nextInt();
				System.out.println("The Nodes in Level-" + l + " in Level Order are:");
				bt.printGivenLevel(bt.root, l);
				break;
			case 12:
				System.out.print("Enter Level(K): ");
				val = sc.nextInt();
				bt.sum = 0;
				System.out.println("Sum of values at level K is = " + bt.sumAtK(bt.root, val));
				break;
			case 13:
				System.out.println("The Tree Nodes in Pre-Order Fashion:");
				bt.printPreOrder(bt.root);
				break;
			case 14:
				System.out.println("The Tree Nodes in In-Order Fashion:");
				bt.printInOrder(bt.root);
				break;
			case 15:
				System.out.println("The Tree Nodes in Post-Order Fashion:");
				bt.printPostOrder(bt.root);
				break;
//				case 2:
//			System.out.print("Enter Value to Search: ");
//			val = sc.nextInt();
//				System.out.println(bt.Search(val));
//			break;
//		case 3:
//			System.out.print("Enter Value to Delete: ");
//			val = sc.nextInt();
//				bt.root=bt.delete(bt.root,val);
//			break;
			}
		}
	}
}

class BST {
	class Node {
		int data;
		Node left;
		Node right;

		public Node(int val) {
			data = val;
			left = null;
			right = null;
		}
	}

	Node root;

	public BST() {
		root = null;
	}

	public boolean isTreeEmpty() {
		return root == null;
	}

// START	1  - Insert a Node(Iterative Approach)
	public void insertIterative(int val) {
		Node n = new Node(val);

		if (isTreeEmpty()) {
			root = n;
			System.out.println("Value Inserted as the Root Node.");
		}

		else {
			Node temp = root;
			while (temp != null) {
				// check if the value already exists at the current node
				if (val == temp.data) {
					System.out.println("Value Already Exists!");
					return;
				}
				// to insert in the left position
				else if (val < temp.data && temp.left == null) {
					temp.left = n;
					System.out.println("Value inserted at the left.");
					break;
				}
				// to traverse to the left node
				else if (val < temp.data) {
					temp = temp.left;
				}
				// to insert in the right position
				else if (val > temp.data && temp.right == null) {
					temp.right = n;
					System.out.println("Value inserted at the right.");
					break;
				}
				// to traverse to the right node
				else {
					temp = temp.right;
				}

			}
		}
	}
// END 1 - Insert a Node(Iterative Approach)

// START	2  - Insert a Node(Recursive Approach)
	public Node insertRecursive(Node root, int val) {
		if (root == null) {
			return new Node(val);
		}
		// if the value is less than the root node then recur for the left subtree
		if (val < root.data) {
			root.left = insertRecursive(root.left, val);
		}

		// else recur for the right subtree
		else {
			root.right = insertRecursive(root.right, val);
		}
		return root;
	}
// END 2 - Insert a Node(Recursive Approach)

// START 3 - Insert a Node(Level Order Insertion)
	public void insertLevelOrder(Node rt, int val) {
		Node nn = new Node(val);
		if (rt == null) {
			rt = nn;
			System.out.println("Inserted at the Root");
			root = rt;
			return;
		}
		Queue<Node> q = new LinkedList<Node>();
		q.add(rt);

		while (!q.isEmpty()) {
			Node n = q.element();
			q.remove();

			if (n.left == null) {
				n.left = nn;
				System.out.println("Value inserted at the left.");
				root = rt;
				return;
			} else if (n.right == null) {
				n.right = nn;
				System.out.println("Value inserted at the right.");
				root = rt;
				return;
			} else {
				q.add(n.left);
				q.add(n.right);
			}
		}
	}
// END 3 - Insert a Node(Level Order Insertion)

// START 4 - Print 2D
	public void print2D(Node r, int space) {

		if (r == null) // base case
			return;

		print2D(r.right, space + BinaryTree.GLOBALSPACE);
		System.out.println();
		for (int i = BinaryTree.GLOBALSPACE; i < space; i++)
			System.out.print(" ");
		System.out.println(r.data);
		print2D(r.left, space + BinaryTree.GLOBALSPACE);
	}
// END 4 - Print 2D

// START 5 - Height of Tree
	public int height(Node r) {
		if (r == null)
			return -1;
		else {
			int lheight = height(r.left);
			int rheight = height(r.right);

			return Math.max(lheight, rheight) + 1;
		}
	}
// END 5 - Height of Tree

// START 6 - Sum of all Nodes in the Tree
	public int sumOfNodes(Node r) {
		if (r == null) {
			return 0;
		}
		int lsum = sumOfNodes(r.left); // to calc the sum of left subtree
		int rsum = sumOfNodes(r.right); // to calc the sum of right subtree
		return lsum + rsum + r.data;
	}
// END 6 - Sum of all Nodes in the Tree

// START 7 - Total Number of Nodes in the Tree
	public int countNodes(Node r) {
		if (r == null) {
			return 0;
		}
		int leftCount = countNodes(r.left); // count the total nodes in left subtree
		int rightCount = countNodes(r.right); // count the total nodes in right subtree
		return leftCount + rightCount + 1;
	}
// END 7 - Total Number of Nodes in the Tree

// START 8 - Maximum Value in the Binary Tree
	public int maxValue(Node r) {
		if (r == null)
			return Integer.MIN_VALUE;
		int lmax = maxValue(r.left); // find the Maximum Value in the left subtree
		int rmax = maxValue(r.right); // find the Maximum Value in the right subtree
		return Math.max(r.data, Math.max(lmax, rmax));
	}
// END 8 - Maximum Value in the Binary Tree

// START 9 - Print Level Order/Breadth First Search (using queue)
	public void printLevelOrderQueue(Node r) {
		Node temp = null;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(r);
		while (queue.size() > 0) {
			temp = queue.poll(); // store the value of current node in 'temp' and dequeue it
			System.out.print(temp.data + " "); // print the current node value
			if (temp.left != null)
				queue.add(temp.left); // enqueue the left child
			if (temp.right != null)
				queue.add(temp.right); // enqueue the right child
		}
	}
// END 9 - Print Level Order/Breadth First Search (using queue)

// START 10 - Print Level Order/Breadth First Search (using Recursion)
	public void printLevelOrderRecursion(Node r) {
		int h = height(r);
		for (int i = 0; i <= h; i++)
			printGivenLevel(r, i);
	}
// END 10 - Print Level Order/Breadth First Search (using Recursion)

// START 11 - Print Given Level of the Tree
	public void printGivenLevel(Node r, int level) {
		if (r == null) // base case
			return;
		else if (level == 0)
			System.out.print(r.data + " ");
		else { // level > 0
			printGivenLevel(r.left, level - 1);
			printGivenLevel(r.right, level - 1);
		}
	}
// END 11 - Print Given Level of the Tree

// START 12 - Sum of values at level K
	int sum = 0;

	public int sumAtK(Node r, int K) {
		if (r == null)
			return sum;
		else if (K == 0) // if K is at root level (0)
			sum = sum + r.data;
		else {
			sumAtK(r.left, K - 1);
			sumAtK(r.right, K - 1);
		}
		return sum;
	}
// END 12 - Sum of values at level K

// START 13 - Print Pre-Order Traversal (NODE, LEFT, RIGHT)
	public void printPreOrder(Node r) { // N L R
		if (r == null)
			return;
		System.out.print(r.data + " ");
		printPreOrder(r.left);
		printPreOrder(r.right);
	}
// END 13 - Print Pre-Order Traversal

// START 14 - Print In-Order Traversal (LEFT, NODE, RIGHT)
	public void printInOrder(Node r) { // L N R
		if (r == null)
			return;
		printInOrder(r.left);
		System.out.print(r.data + " ");
		printInOrder(r.right);
	}
// END 14 - Print In-Order Traversal

// START 15 - Print Post-Order Traversal (LEFT, RIGHT, NODE)
	public void printPostOrder(Node r) { // L R N
		if (r == null)
			return;
		printPostOrder(r.left);
		printPostOrder(r.right);
		System.out.print(r.data + " ");
	}
// END 15 - Print Post-Order Traversal
}