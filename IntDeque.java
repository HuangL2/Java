/*
 * File: IntDeque.java
 * Date: 2/24/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: a class that represents a Queue for integers
 * Thorws QueueUnderflowException
 */
public class IntDeque {
    
    
    
    // current size of the array
    private int SIZE = 10;
    // number of valid elements
    private int length = 0;
    
    // the next index of the queue
    private int next = 0;
    // the first index of the queue
    private int front = 0;
    
    // is only next used? use for resizing
    private int nextCount = 0;
    
    // the int array that represent the queue
    private int[] a = new int[SIZE];
    
    
    
    // adds an element to the rear
    public void enqueueRear(int k) {
        if(length == a.length && next == front)
            resize();
        
        a[next] = k;
        next = nextSlot(next);
        length ++;
        nextCount ++;
    }
    
    
    // remove and returns the frontmost element
    // throws QueueUnderflowException when there are no
    // more element left
    public int dequeueFront() throws QueueUnderflowException {
        if(front == next && length == 0)
            throw new QueueUnderflowException();
        
        int value = a[front];
        front = nextSlot(front);
        length --;
        nextCount --;
        
        return value;
    }
    
    
    // adds an element to the front
    public void enqueueFront(int k) {
        if(length == a.length && next == front)
            resize();
        
        front = previousSlot(front);
        a[front] = k;
        length ++;
    }
    
    
    // removes and returns the rearmost element
    // throws QueueUnderflowException when there are no 
    // more element left
    public int dequeueRear() throws QueueUnderflowException {
        if(front == next && length == 0)
            throw new QueueUnderflowException();
        
        next = previousSlot(next);
        int value = a[next];
        length --;
        
        return value;
    }
    
    
    // gets and return the number of valid element in the queue
    public int size() {
        return length;
    }
    
    
    // return true if and only if there are no valid element in the queue
    public boolean isEmpty() {
        return length == 0;
    }
    
    
    // helper method that return the next index
    private int nextSlot(int i) {
        return (i + 1) % a.length;
    }
    
    
    // helper method that return the previous index 
    private int previousSlot(int i) {
        return (i + a.length - 1) % a.length;
    }
    
    
    // resize the array to twice its original size when trying to
    // add a new element when all indexes contains a valid element
    private void resize() {
        // seperate the array into 2 parts
        // b is all elements to the left
        // c is all elements to the right
        int[] b = new int[a.length];
        int[] c = new int[a.length];        
        
        // when all element of the queue is filled by only
        // enqueueRear or by only enqueueFront
        if(front == 0 && next == 0) {
            if(nextCount == a.length) { // case by only enqueRear
                next = a.length;
                front = 0;
                
                for(int i = 0; i < a.length; i ++)
                    c[i] = a[i];
            } else {                    // case by only enqueueFront
                next = 0;
                front = a.length;
                
                for(int i = 0; i < a.length; i++)
                    b[i] = a[i];
            }
        } else {
            int pivot = previousSlot(next);
            next = next;
            front = front + a.length;        
            
            for(int i = pivot + 1; i < a.length; i++)
                b[i] = a[i];
            for(int i = pivot; i >= 0; i--)
                c[i] = a[i];
        }
        
        // original array is the union of b and c
        a = new int[b.length + c.length];
        SIZE = a.length;
        for(int i = 0; i < a.length; i++) {
            if(i < c.length)
                a[i] = c[i];
            else
                a[i] = b[i - b.length];
        }
    }
    
    
    // toString method for IntDeque
    public String toString() {
        String s = "[";
        for(int i = a.length - 1; i > 0; i--)
            s += a[i] + ", ";
        s += a[0] + "] ";
        
        s += " length: " + a.length;
        s += "  size: " + size();
        s += "  front: " + front;
        s += "  next: " + next;
        
        return s;
    }
    
    
    
    
    // QueueUnderflowException.
    // thrown when trying to dequeue when there are no more
    // valid element
    // prints message "Queue Underflew"
    private class QueueUnderflowException extends Exception{
        public QueueUnderflowException() {
            super("Queue Underflew!");
        }
    }
    
    
    
    
    // Main Method
    public static void main(String[] args) {
        
        IntDeque D = new IntDeque(); 
        
        
        System.out.println("\n[1] First test toString on empty dequeue... Should print out:"); 
        System.out.println("[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  length: 10  size: 0  front: 0  next: 0"); 
        System.out.println(D); 
        
        
        System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
        System.out.println(D.size() + "  " + D.isEmpty()); 
        
        
        System.out.println("\n[3] Test enqueueRear.... Should print out:"
                               + "\n[0, 0, 0, 0, 0, 0, 4, 3, 2, 1]  length: 10  size: 4  front: 0  next: 4");
        D.enqueueRear(1); 
        D.enqueueRear(2);
        D.enqueueRear(3); 
        D.enqueueRear(4);
        System.out.println(D); 
        
        
        System.out.println("\n[4] Test size and isEmpty again... Should print out:\n4  false"); 
        System.out.println(D.size() + "  " + D.isEmpty()); 
        
        
        System.out.println("\n[5] Test dequeueFront.... Should print out:"
                               + "\nn = 1  m = 3");
        int n, m;
        try {
            n = D.dequeueFront(); 
            D.dequeueFront();  
            m = D.dequeueFront(); 
            System.out.println("n = " + n + "  m = " + m); 
        } catch (QueueUnderflowException e) {
            System.err.println(e.getMessage());
        }
        
        
        System.out.println("\n[6] And should print out:"
                               + "\n[0, 0, 0, 0, 0, 0, 4, 3, 2, 1]  length: 10  size: 1  front: 3  next: 4");
        System.out.println(D); 
        
        
        System.out.println("\n[7] Test wrap-around of enqueueRear .... Should print out:"
                               + "\n[10, 9, 8, 7, 6, 5, 4, 13, 12, 11]  length: 10  size: 10  front: 3  next: 3");
        for(int i = 5; i < 14; ++i)
            D.enqueueRear(i);
        System.out.println(D); 
        
        
        System.out.println("\n[8] Test wrap-around of dequeueFront .... Should print out:"
                               + "\n[10, 9, 8, 7, 6, 5, 4, 13, 12, 11]  length: 10  size: 0  front: 3  next: 3  m = 13");
        try {
            for(int i = 0; i < 9; ++i)
                D.dequeueFront();
            m = D.dequeueFront(); 
            System.out.println(D + "  m = " + m); 
        } catch (QueueUnderflowException e) {
            System.err.println(e.getMessage());
        }
    }
    
}