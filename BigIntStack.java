/*
 * File: BigIntStack.java
 * Date: 2/18/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: A class that represents a Stack of BigInts
 * Uses File: BigInt.java
 * 
 */
public class BigIntStack {
    
    // default size of the stack
    private final int DEFAULT_SIZE = 10;
    // array that represents the stack
    private BigInt[] a;
    // the next index of the stack
    private int next = 0;
    
    
    // default constructor that is the same as BigIntStack(DEFAULT_SIZE);
    public BigIntStack() {
        this(DEFAULT_SIZE);
    }
    
    // constructs a BigIntStack with the specified size
    public BigIntStack(int size) {
        a = new BigInt[size];
    }
    
    
    
    
    // returns the number of non-null elements in the array
    public int size() {
        return next;
    }
    
    
    // adds a BigInt to the top of the stack. 
    public void push(BigInt n) {
        a[next] = n;
        next++;
    }
    
    
    // removes a BigInt from the top of the stack.
    public BigInt pop() {
        next --;
        return a[next];
    }
    
    
    // peeks at the BigInt at the top of the stack
    public BigInt top() {
        return a[next - 1];
    }
    
    
    // return true if all elements in the stack if null
    public boolean isEmpty() {
        return next == 0;
    }
    
    
    // toString method for BigIntStack
    public String toString() {
        String s = "| ";
        for(int i = 0; i < next; ++i) {
            s += (a[i] + " ");
        }
        return s;    
    }
    
    
    
    
    // Main method
    public static void main(String [] args) {
        System.out.println("\nUnit Test for BigIntStack Class");
        
        
        BigInt a = new BigInt("999999999999999999");
        BigInt b = new BigInt("44444444444444");
        BigInt c = new BigInt("11111111111111111111111");
        
        
        System.out.println("\na = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        
        
        System.out.println("\nCreating new stack....");
        BigIntStack stack = new BigIntStack();
        
        
        System.out.println("\nTest 1 -- Is Stack Empty: Should be:\n" + 
                           "true");
        System.out.println(stack.isEmpty());
        
        
        System.out.println("\nPushing a, b, and c on stack....");
        stack.push(a);
        stack.push(b);
        stack.push(c);
        
        
        System.out.println("\nTest 1 -- Printing stack: Should be:\n" + 
                           "| 999999999999999999 44444444444444 " + 
                           "11111111111111111111111");
        System.out.println(stack);
        
        
        System.out.println("\nTest 2 -- Is Stack Empty: Should be:\n" + 
                           "false");
        System.out.println(stack.isEmpty());
        
        
        System.out.println("\nTest 3 -- Examining top element: Should be:\n" + 
                           "11111111111111111111111");
        System.out.println(stack.top());
        
        
        System.out.println("\nPop two elements of stack..." +
                           "then print stack and n and m....");
        BigInt n = stack.pop();
        BigInt m = stack.pop();
        
        System.out.println("\nTest 4 -- Should be:\n" + 
                           "| 999999999999999999 " + 
                           "n = 11111111111111111111111 m = 44444444444444");
        System.out.println(stack + "n = " + n + " m = " + m);
    }    
    
}