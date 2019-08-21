/**
 * File: StringQueue.java
 * Date: 3/2/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: a class that represent a Queue for Strings using linked
 *          inner class approach
 * Throws: StackUnderflowException
 */
public class StringQueue {
    
    // header of the queue
    // used to store the first and last element of the queue
    private Node header;
    
    // number of elements in the stack
    private int size;
    
    
    // default constructor
    public StringQueue() {
        header = new Node(null, null, null);
        size = 0;
    }
    
    
    // add a new element to the first element of the queue
    public void enqueue(String s) {
        if(size == 0) {
            Node newNode = new Node(s, header, header);
            header.next = newNode;
            header.previous = newNode;
        } else {   
            Node newNode = new Node(s, header.next, header);
            header.next.previous = newNode;
            header.next = newNode;
        }            
        size ++;
    }
    
    
    // gets, returns, and removes the last element of the queue
    // throws StackUnderflowException if there are no element in the queue
    public String dequeue() throws StackUnderflowException {
        if(size == 0)
            throw new StackUnderflowException();
        
        String s = header.previous.value;
        
        if(size != 1) {
            header.previous.previous.next = header;
            header.previous = header.previous.previous;
        } else {
            header.previous = null;
            header.next = null;
        }
        
        size --;
        return s;
    }
    
    
    // gets and returns the number of elements in the queue
    public int size() {
        return size;
    }
    
    
    // returns true if there are no element in the queue
    public boolean isEmpty() {
        return size == 0;
    }
    
    
    // toSring method
    public String toString() {
        return toStringAux(header.previous);
    }
    
    // helper toString method
    private String toStringAux(Node n) {
        if(n == null || n == header)
            return "";
        else
            return toStringAux(n.previous) + " " + n.value;
    }
    
    
    // inner class that represent an element in the queue
    public class Node {
        // the String representative of the element
        public String value;
        // pointer to the next element
        public Node next;
        // pointer to the previous element
        public Node previous;
        
        
        // default constructor
        // String representative is empty and pointers are null
        public Node() {
            this("");
        }
        
        // constructor
        // String representative is as specified and pointer is null
        public Node(String value) {
            this(value, null, null);
        }
        
        // constructor
        // String representative and pointers are as specified
        public Node(String value, Node next, Node previous) {
            if(value == null)
                value = "";
            
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
        
        
        // toString method
        public String toString() {
            return value;
        }
        
    }
    
    // class that represents the Exception thrown when trying to get an
    // element from the queue when it is empty
    class StackUnderflowException extends Exception {
        
    }
    
    
    
    
    // Main Method
    public static void main(String[] args) {
        StringQueue q = new StringQueue();
        
        System.out.println("\n[1] First test toString on empty StringQueue..." +
                           "Should print out:\n");
        System.out.println(q);
        
        System.out.println("\n[2] Test size and isEmpty... Should print out:\n" +
                           "0 true");
        System.out.println(q.size() + " " + q.isEmpty());
        
        System.out.println("\n[3] Enqueue 10 strings... Should print out:\n" + 
                           " ! President for run to looney be to have You'd");
        q.enqueue("You'd");        
        q.enqueue("have");
        q.enqueue("to");
        q.enqueue("be");
        q.enqueue("looney");
        q.enqueue("to");
        q.enqueue("run");
        q.enqueue("for");
        q.enqueue("president");
        q.enqueue("!");
        System.out.println(q);
        
        System.out.println("\n[4] Test size and isEmpty... Should print out:\n" +
                           "10 false");
        System.out.println(q.size() + " " + q.isEmpty()); 
        
        System.out.println("\n[5] Dequeue and print... Should print out:\n" +
                           "You'd have to be looney to run for President !");
        try {
            while(!q.isEmpty())
                System.out.print(q.dequeue() + " ");
            System.out.println();
        } catch (StackUnderflowException ex) {
            System.err.println("Queue Underflow!");
        }
        
        System.out.println("\n[6] Testing queue underflow with dequeue... " + 
                           "Should print out:\nQueue Underflow!");
        try {
            System.out.println(q.dequeue());
        } catch (StackUnderflowException ex) {
            System.err.println("Queue Underflow!");
        }
        
        System.out.println("\n[7] Just for fun... Should print out a long String!");
        for(int i = 0; i < 5; i++)
            System.out.println("So Trump is a looney! So Trump is a looney!" + 
                               "So Trump is a looney! So Trump is a looney!" + 
                               "So Trump is a looney!\n" );

                
    }
}