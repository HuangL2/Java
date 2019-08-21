/*
 * File: Queue.java
 * Date: 3/24/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: a class that represents a Queue that can store any variable type
 */
public class Queue<T> {
    
    // front and rear of the queue
    private Node front = null;
    private Node rear = null;
    
    // size of the queue
    private int size = 0;
    
    
    // Node class that represent one element in the queue
    public class Node {
        
        // The value the node represents
        public T item;
        // the Node linked to this Node
        public Node next;
        
        
        // default constructor
        public Node() {
            this(null);
        }
        
        // constructor that assigns the value but not the linked Node
        public Node(T e) {
            this(e, null);
        }
        
        // constructor that assigns both the value and the linked Node
        public Node(T e, Node p) {
            item = e;
            next = p;
        }
    }
    
    
    // add a new element
    public void enqueue(T e) {
        if(isEmpty())
            front = rear = new Node(e);
        else {
            rear.next = new Node(e);
            rear = rear.next;
        }
        
        size ++;
    }
    
    
    // remove the earliest added element
    public T dequeue() {
        T temp = front.item;
        front = front.next;
        size --;
        
        return temp;
    }
    
    
    // return true if there are no elements in the queue
    public boolean isEmpty() {
        return front == null;
    }
    
    
    // returns the size of the queue
    public int size() {
        return size;
    }
    
    
    // toString method
    public String toString() {
        return toStringAux(front);
    }
    
    // toString method helper
    private String toStringAux(Node p) {
        if(p == null)
            return "";
        else 
            return toStringAux(p.next) + " " + p.item;
    }
    
    
    // Main Method
    public static void main(String[] args) {
    
    }
}