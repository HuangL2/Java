/**
 * File: StringStack.java
 * Date: 3/2/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: a class that represent a Stack for Strings using linked
 *          inner class approach
 * Throws StackUnderflowException
 */
public class StringStack {
    
    // first element of the stack
    private Node header;
    // number of elements in the stack
    private int size;
    
    
    // default constructor
    public StringStack() {
        header = new Node();
        size = 0;
    }
    
    
    // add a new element to the head of the stack
    public void push(String s) {
        Node n = new Node(s, header);
        header = n;
        
        size ++;
    }
    
    
    // gets, returns, and removes the top most element
    // throws StackUnderflowException if there are no element in the stack
    public String pop() throws StackUnderflowException {
        String s = top();     // gets the element
        header = header.next; // removes the element
        size --;
        return s;             // returns the element
    }
    
    
    // gets and returns the top most element
    // thorws StackUnderflowException if there are no element in the stack
    public String top() throws StackUnderflowException {
        if(isEmpty())
            throw new StackUnderflowException();
        
        return header.value;
    }
    
    
    // gets and returns the number of elements in the stack
    public int size() {
        return size;
    }
    
    
    // returns true if there are no element in the stack.
    public boolean isEmpty() {
        return size == 0;
    }
    
    
    // toString method
    public String toString() {
        return "|" + toStringAux(header);
    }
    
    // helper toString method
    private String toStringAux(Node n) {
        if(n == null)
            return "";
        else
            return toStringAux(n.next) + " " + n.value;
    }
    
    
    // inner class that represents an element in the stack
    public class Node {
        // the String representative of the element
        public String value;
        // pointer to the next element
        public Node next;
        
        
        // default constructor
        // String representative is empty and pointer is null
        public Node() {
            this("");
        }
        
        // constructor
        // String representative is as specified and pointer is null
        public Node(String value) {
            this(value, null);
        }
        
        // constructor
        // String representative and pointer are as specified
        public Node(String value, Node next) {
            if(value == null)
                value = "";
            
            this.value = value;
            this.next = next;
        }
    }
    
    
    // class that represents the Exception thrown when trying to get an 
    // element form the stack when stack is empty 
    class StackUnderflowException extends Exception {
        
    }
    
    
    
    
    // Main Method
    public static void main(String[] args) {
        
        StringStack S = new StringStack(); 
        
        System.out.println("\n[1] First test toString on empty StringStack... Should print out:"); 
        System.out.println("| "); 
        System.out.println(S); 
        
        // Use step-wise refinement to develop your program: Move the left comment marker "/*"
        // down past one test at a time as you develop each of the methods
        
           
        System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
        System.out.println("\n[3] Push 4 strings... Should print out:\n|  looney a is Trump"); 
        S.push("looney");
        S.push("a");
        S.push("is");
        S.push("Trump");
        System.out.println(S);
        
        System.out.println("\n[4] Test size and isEmpty... Should print out:\n4  false"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
        System.out.println("\n[5] Test top... Should print out:\nTrump"); 
        try {
            System.out.println(S.top());
        }
        catch(StackUnderflowException e) {
            System.err.println("Stack Underflow!");
        }
        
        System.out.println("\n[6] Pop and print... Should print out:\nTrump is a looney");
        while(!S.isEmpty()) {
            try {
                String s = S.pop(); 
                System.out.print(s+" ");
            }
            catch(StackUnderflowException e) {
                System.err.println("Stack Underflow!");
            }
        }
        System.out.println(); 
        
        
        System.out.println("\n[7] Testing stack underflow with top... Should print out:");
        System.err.println("Stack Underflow!");
        try {
            
            System.out.print(S.top());
        }
        catch(StackUnderflowException e) {
            System.err.println("Stack Underflow!");
        }  
        
        System.out.println("\n[8] Testing stack underflow with pop... Should print out:");
        System.err.println("Stack Underflow!");
        try {
            String s = S.pop(); 
            System.out.print(s+" ");
        }
        catch(StackUnderflowException e) {
            System.err.println("Stack Underflow!");
        } 
        
        System.out.println("\n[9] Just for fun... Should print out a long String!");
        try {
            S.push("looney! ");
            S.push("a ");
            S.push("is ");
            S.push("Trump ");
            
            String s = "";
            while(!S.isEmpty())
                s += S.pop();
            
            for(int i = 0; i < 5; ++i)
                S.push(s);
            S.push("\n");
            
            String t = "";
            while(!S.isEmpty())
                t += S.pop();
            
            for(int i = 0; i < 5; ++i)
                S.push(t);
            
            while(!S.isEmpty())
                System.out.println(S.pop());
            
        }
        catch(StackUnderflowException e) {
            System.err.println("Stack Underflow!");
        }
      
    }
}