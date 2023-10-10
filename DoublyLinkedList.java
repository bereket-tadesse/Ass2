/*
 * Major concept of the algorithm: 
 * This class enables the user to swap a node in a doubly linked list with its successor. The essential method which
 * lets user to do this is the swapNodes helper method which gets called by swapwithSuccesor node. The reason i separated
 * the two is for simplicity. The swapNodes accepts two parameters the node to be swapped (let it be P) and its successor. The swap node
 * first checks if both nodes are different from null, then to update the pointers we can look at it in three parts:
 * 1. first step is setting the next pointer of the previous node of P to the successor of P. ( if it doesnot have previous
 * it means it is the first element, so make the succesor the head)
 * 2. second is setting the succesor's next node previous pointer to P.
 * 3. the final step is updating the previous and next pointers of node p and its succesor. (removing the connections of remaining previous
 * and next pointers)
 * The other method contained inside SwapSuccessorNode() method is findNode() which transverses the linked list to find a specific node.
 * 
 * Run time: The run time of  SwapSuccessorNode() is O(n) since the only method which trasnverses the list is findNode() method contained isnide it
 * which has one loop. The rest of the operations (if clauses) have constant run time. 
 */

import java.util.Scanner;

/**
 * A class representing doubly linked list of integers.
 * @author Bereket
 *
 */
public class DoublyLinkedList {
	
	public static void main(String[] args) {
		
		// This is the demo program 
		System.out.println(">>>>>>>>>> This is a demo of swapping element in a doubly linked list <<<<<<<<<<<\n");
		DoublyLinkedList linkedlist = new DoublyLinkedList();
		System.out.println("Please enter INTEGERS as many as you want separated by single space to add them to a doubly linked list: ");
		
		// a reeader object for inputs.
    	Scanner reader = new Scanner(System.in);
    	//stores option choice
    	String stringOfNumbers = reader.nextLine();
    	
    	String[] array = stringOfNumbers.split(" ");
    	
    	for(int i = 0; i < array.length; i ++) {
    		linkedlist.insert(Integer.parseInt(array[i]));
    	}
		
    	System.out.println("Enter the number you would like to swap with its succesor: ");
    	
    	
    	reader = new Scanner(System.in);
    	//stores option choice
    	int number = reader.nextInt();
    	
    	//checking for valid input
    	if(linkedlist.findNode(number) == null) {
    		System.out.println("Please enter a valid number");
    		throw new IllegalArgumentException();
    	} else {
    		System.out.println("Here is the linked list after the number is swapped :)");
    	}
    	
    	linkedlist.swapWithSuccesor(number);
    	
    	linkedlist.printAll();
    	

		
	}
	
	/*
	 * Stores the head of the doubly linked list.
	 */
	private Node head;
	
	/*
	 * getter for the head
	 */
	public Node getHead() {
		return head;
	}
	/*
	 * setter for the head
	 */
	public void setHead(Node head) {
		this.head = head;
	}
	
	/**
	 * A sub-class representing a node.
	 * @author Bereket
	 */
	private static class Node{
		// previous pointer
		private Node previous;
		// next pointer
		private Node next;
		// stores the data of a node
		private int data;
		
		/*
		 * gets the data of the node
		 */
		public int getData() {
			return data;
		}
		
		/*
		 * Sets the data of the nodw
		 */
		public void setData(int data) {
			this.data = data;
		}

		/**
		 * cosntructor for the node class
		 * @param key the data stored in the list
		 */
		public Node(int data ) {
			this.setData(data);
		}
		/*
		 * gets the previous node.
		 */
		public Node getPrevious() {
			return previous;
		}
		/*
		 * sets the previous node
		 */
		public void setPrevious(Node previous) {
			this.previous = previous;
		}
		/*
		 * gets the next node.
		 */
		public Node getNext() {
			return next;
		}
		/*
		 * sets the next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}
			
	}
	
	/**
	 * Inserts a data of a node into the doubly linkedlist.
	 * @param data 
	 */
	public void insert(int data) {
		
		//data to insert
		Node toInsert = new Node(data);
		
		// if empty
		if (this.getHead() == null) {
			this.setHead(toInsert);
		} else {
			// used to trasnverse list
			Node current = getHead();
			// used to store the node before current
			while(current.getNext() != null) {
				current = current.getNext();
			}
			//setting previous of new node
			current.setNext(toInsert);
			//connect next pointer to the new node
			toInsert.setPrevious(current);
			
		}
	}
	
	/**
	 * Searches a node with a value and swaps with its successor
	 * @param number represents the data
	 */
	public void swapWithSuccesor(int number) {
		
		// find the node to swap with
		Node currentNode = this.findNode(number);
		//Successor node
		Node successor = currentNode.next;
		
		//helper method to swap the two nodes
		swapNodes(currentNode, successor);
		
		
	}
	
	/**
	 * A helper method to swap two nodes from a list.
	 * @param currentNode
	 * @param successor
	 */
	private void swapNodes(Node currentNode, Node successor) {
		
		// if node or its successor is empty throw error.
		if (currentNode == null|| successor == null) {
			
			throw new IndexOutOfBoundsException("The element being swaped is null");
			
		} else {
			// if current node has previous set its next to the succesor
			if (currentNode.getPrevious() != null) {
			        currentNode.getPrevious().setNext(successor);

		    } else {
		    	//else set it to head
		        this.setHead(successor);
		    }
		    
			// if successor has next set its previous to the current node
		    if (successor.getNext() != null) {
		        successor.getNext().setPrevious(currentNode);
		    }
		    
		    // store the previous of current so we dont lose it.
		    Node tempPrev = currentNode.getPrevious();
		    //update the pointers of current node and succesor node.
		    currentNode.setPrevious(successor);
		    successor.setPrevious(tempPrev);
		    
		    //update the pointer of current and succesro nodes.
		    currentNode.setNext(successor.getNext());
		    successor.setNext(currentNode);
			    
		}
		
	   
	}
	
	
	/**
	 * Method to find a node with a given value
	 * @param value value of node
	 * @return the node found
	 */
    public Node findNode(int value) {
    	//current node being looked.
        Node current = this.getHead();
        // loop to transverse the list
        while (current != null) {
            if (current.data == value) {
                return current;
            }
            current = current.next;
        }
        return null; // node not found
    }
    
    /**
     * prints all elements in the list.
     */
    private void printAll() {
    	Node current = this.getHead();
    	//loop through the list.
		while (current!= null) {
			System.out.print(current.getData() + " ");
			current= current.getNext();
		}
		
    }
}
