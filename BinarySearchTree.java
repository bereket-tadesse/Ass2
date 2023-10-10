import java.util.Scanner;

/**
 * A class which represents a binary search tree storing nodes of intger values.
 * @author Bereket
 * @version 1.0
 */
public class BinarySearchTree {
	
	public static void main(String[] args) {
		

		// This is the demo program 
		System.out.println(">>>>>>>>>> This is a demo of Binary Search Tree class and its method <<<<<<<<<<<\n");
		
		System.out.println("1. insert elements to BST \n 2. find preOrder list \n 3. find sum of BST \n 4. find kth Biggest number \n 5. Quit");
		BinarySearchTree mytree = new BinarySearchTree();
		// Stores the option number
    	int option = 0;
    	
    	//loop which runs continuosly until quit.
    	while(option != 5) {
    		
    		// ask option again
    		System.out.println("Choose An Option: ");
    		
    		// a reeader object for inputs.
        	Scanner reader = new Scanner(System.in);
        	//stores option choice
        	option = reader.nextInt();
    		
        	if (option == 5) {
        		System.out.println("Have a nice day!");
        	} 
        	
        	//number of items.
    		int number = 0; 
    		
        	// switch statemes for the 5 options
    		switch(option) {
    		
    		//Inserting option
			case 1: 
				System.out.println("How many elements would you like to enter?");
				reader = new Scanner(System.in);
				number = reader.nextInt();
				System.out.println("enter a number and hit enter for each: ");
				int count = 1;
				
				//loop to insert
				while(count <= number) {
					reader = new Scanner(System.in);
					int input = reader.nextInt();
					mytree.insert(input);
					count++;
				}
				
				System.out.println("proceed to another options");
				break;
				
			//preorder option
			case 2:
				System.out.print("Preorder: ");
				mytree.preorderRec(mytree.getRoot());
				System.out.println();
				break;
				
			// sum option
			case 3:
				System.out.println("sum is: " + mytree.sum(mytree.getRoot()));
				break;
			
			// getting kth biggest option
			case 4:
				System.out.println("Enter value of k: ");
				reader = new Scanner(System.in);
				int k = reader.nextInt();
				
				//if invalid K value
				if(k > number) {
					System.out.println("Invalid K value");
				}
				System.out.println("Kth Biggest value is: " + mytree.kthBiggest(mytree.getRoot(),k).getKey());
				break;
    	
    		}
    		
		}
    	
		
	}
	
	/**
	 * Stores the root of BST
	 */
	private Node root;
	
	/**
	 *  stores the sum of the binary search tree
	 */
	private int sum = 0; 	
	
	/* 
	 * Stores a counter to compare kth number with which used in kthbiggest helper method. 
	 */
	private int count = 0;
	
	/**
	 * gets the root of the BST
	 * @return return 
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * sets the root of the BST
	 * @param root the root of BST
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * gets the sum of all elements
	 * @return the sum
	 */
	public int getSum() {
		return sum;
	}
	
	/*
	 * sets the sum of the BST.
	 */
	public void setSum(int sum) {
		this.sum = sum;
	}

	/**
	 *  gets count
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * sets the counter 
	 * @param count counter
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * A sub-class representing the Node to be used in the BST class.
	 * @author Bereket
	 * @version 1.0
	 */
	private class Node {
		
		/**
		 * Stores the left node
		 */
		private  Node left;
		/**
		 * Stores the right node
		 */
		private Node right;
		/**
		 * Stores the right node.
		 */
		private int key;
		
		/**
		 * A constrcutor to create the Node
		 * @param key represents the data in the Node.
		 */
		public Node(int key) {
			this.key = key;
			this.right = null;
			this.left = null;	
		}
		//getter for the key
		public int getKey() {
			return this.key;
		}
		//getter for the left node
		public Node getLeft() {
			return left;
		}
		//setter for the left node
		public void setLeft(Node left) {
			this.left = left;
		}
		//getter for the right node
		public Node getRight() {
			return right;
		}
		//setter for the right node
		public void setRight(Node right) {
			this.right = right;
		}
		//setter for the key.
		public void setKey(int key) {
			this.key = key;
		}
		
	}
	
	
	/**
	 * A method which inserts an integer to a BST.
	 * @param key the key to be inserted into the BST.
	 */
	public void insert(int key) {
		
		// a pointer node to transverse the BST
		Node trav = this.getRoot();
		
		// if initially emepty make the key a root.
		if (trav == null) {
			this.setRoot( new Node(key));
		
		} else {
			
			// transverse until the spot is found for the key based on its value.
			Node prev = trav;
			while (trav != null) {
				prev = trav;
				if (key < trav.getKey() ) {
					trav = trav.getLeft();
				} else {
					trav = trav.getRight();
				}
			}
			
			// if key less store to left, otherwise right
			if (key < prev.getKey()) {
				prev.setLeft(new Node(key));
			} else {
				prev.setRight(new Node(key));
			}
		
		}
	}
	

	/**
	 * A ]method which prints our the preorder transversal using recursion.
	 * @param root the starting Node (root)
	 */
    public void preorderRec(Node root) {
        
    	// do nothing if root is null
    	if (root == null) {
        	;
        }
    	
    	// preorder: print root first , then left , then right
        else {
            System.out.print(root.getKey() + " ");
            preorderRec(root.getLeft());
            preorderRec(root.getRight());
        }
    }
	

	/**
	 * A method which uses a helper method to find the kth largest element in BST/
	 * @param k the number to be used as kth element
	 * @return returns returns the kth element node
	 */
//	public Node kthBiggest (int k) {
//		return kthBiggestHelper(this.getRoot(), k);
//	}
//	
	/**
	 * A helper method which uses recursion to find the kth largest element form BST
	 * @param root the node we are staring with ( root)
	 * @param k the number to be used as kth element
	 * @return returns the kth elment node from BST
	 */
	private Node kthBiggest (Node root, int k) {
		
		// base case.
        if (root == null) {
            return null;
        }
       
        // transverse trhough the right tree until end
        Node right = kthBiggest(root.getRight(), k);
 
        // if k'th largest is found in right subtree, return it
        if (right != null)
            return right;

        this.setCount(getCount()+1);
        
        // if count equals that means node is found
        if (getCount() == k) {
        	// reset count 
        	setCount(0); 
            return root;
        }
       
        // transverse left
        return kthBiggest(root.getLeft(), k);
        
	}
	
	/**
	 * A method which finds sum of all elements in the bianry search tree.
	 * @param root
	 * @return
	 */
	public int sum(Node root) {
		//base case
		if (root == null) {
            return 0;
        // tranverse left and right tree.
        } else {
        	return root.getKey() + sum(root.getLeft()) + sum(root.getRight());
        }
	}
	
	
}
