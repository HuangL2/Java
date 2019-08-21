/*
 * File: IntQueue.java
 * Date: 2/24/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: a class that represent a queue for integers
 * Throws: StackUnderflowException
 */
public class IntQueue {
    
    // size of the array
    private final int SIZE = 10;
    
    // array that represents the queue 
    private int[] a = new int[SIZE];
    
    // the first valid index
    private int front = 0;
    // the next index to put element in
    private int next = 0;
    
    
    
    // adds a new element to the queue
    public void enqueue(int key){
        if(next >= SIZE)
            reset();
        a[next++] = key;
    }
    
    
    // removes and returns the frontmost element in the queue
    // throws StackUnderflowException when there are no 
    // more element left
    public int dequeue() throws StackUnderflowException{
        if(front >= next)
            throw new StackUnderflowException();
        
        return a[front++];
    }
    
    
    // returns true if and only if there are no more valid
    // elements in the array
    public boolean isEmpty() {
        return next == front;
    }
    
    
    // returns the number of valid elements in the array
    public int size() {
        return next - front;
    }
    
    
    // shifts all the valid elements to the right
    public void reset() {
        for(int i = 0; i < next - front; i++) {
            a[i] = a[front + i];
        }
        
        next -= front;
        front = 0;
    }
    
    
    // toString method for IntQueue
    public String toString() {
        String s = "";
        for(int i = SIZE-1; i >= 0; --i)
            s += i + "\t"; 
        s += "\n";
        for(int i = SIZE-1; i >= 0; --i)
            s += a[i] + "\t";
        s += "\nnext = " + next + "   front = " + front + "\n";
        
        return s; 
    }
    
    
    
    // Main Method
    public static void main(String[] args) {
        IntQueue Q = new IntQueue();        
        
        System.out.println("Enqueueing 5, 9, 3, -3, 31 then printing out the queue:\n");
        Q.enqueue(5); 
        Q.enqueue(9); 
        Q.enqueue(3); 
        Q.enqueue(-3); 
        Q.enqueue(31); 
        System.out.println(Q);
        
        System.out.println(Q.size()); 
        
        try {
            System.out.println("Dequeueing 3 times then printing out the queue:\n");
            System.out.println("dequeue: " + Q.dequeue()); 
            System.out.println("\ndequeue: " + Q.dequeue()); 
            System.out.println("\ndequeue: " + Q.dequeue()); 
        } catch (StackUnderflowException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println(Q);
        }
        
        System.out.println("\nEnqueueing 8, -1, 2, 6, 5 then printing out the queue:\n"); 
        Q.enqueue(8); 
        Q.enqueue(-1); 
        Q.enqueue(2); 
        Q.enqueue(6); 
        Q.enqueue(5);
        System.out.println(Q);
        
        
        // First issue:  this one will cause an error! You must fix this by shifting everything
        // to the right (towards the low indices, so that front is at 0 as it was at the beginning)
        
        System.out.println("First issue to fix: the queue has moved all the way to the left!");
        System.out.println("Must write code in reset() to shift all valid elements to right.");
        
        Q.enqueue(666);
        
        System.out.println("\n"+Q);
        
        try {
            System.out.println("\nEmptying out the queue:\n"); 
            while(!Q.isEmpty()) {
                Q.dequeue();
            }
        } catch (StackUnderflowException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("\n"+Q);
        }
        
        // Second issue: after fixing the first problem, you must report an error for this one,
        // which can not be handled normally -- there is no programming solution because
        // there is no element in an empty queue to dequeue! 
        // You must create an exception to report this to the user by printing out an error message.
        
        System.out.println("Second issue to fix: report an error using exceptions for trying to dequeue empty queue.");
        
        try {
            System.out.println("\nQ is empty:  " + Q.isEmpty() + "\n");
            System.out.println("\ndequeue: " + Q.dequeue());
        } catch (StackUnderflowException e) {
            System.err.println(e.getMessage());
        }
    }
    
    class StackUnderflowException extends Exception { 
        public StackUnderflowException() {
            super("Stack Underflew!");
        }
    }
    
}