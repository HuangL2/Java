import java.util.Random;
import java.awt.Color;

/**
 * File: AverageCaseBSTs.java
 * Date: 3/30/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: class to test and find out the average case of Binary Search Trees.
 * 
 * Others: Theta estimate: log(N)
 *         C estimate:     2.55
 */

public class AverageCaseBSTs {
    
    // class that represet an element in the tree
    static class Node {
        int key;
        Node left, right;
        
        public Node(int key) {
            this(key, null, null);
        }
        
        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }
    
    // get the height of the tree recursivly
    public static int height(Node t) {
        if (t == null)
            return -1;
        else 
            return 1 + Math.max( height(t.left), height(t.right) ); 
    }
    
    // returns true if a number is a member of the tree
    public static boolean member(Node node, int key) {
        if(node == null)
            return false;
        else if(key < node.key)
            return member(node.left, key);
        else if(key > node.key)
            return member(node.right, key);
        else 
            return true;
    }
    
    // insert a new number into the tree
    public static Node insert(Node t, int key) {
        if (t == null)
            return new Node(key);
        else if (key < t.key) {
            t.left = insert(t.left, key);
            return t;
        } else if (key > t.key) {
            t.right = insert(t.right, key);
            return t;
        } else
            return t;
    }
    
    // find and return the average height of 1000 trees n elements that 
    // are randomly generated
    private static int runBSTInsertion(int n) {
        int sum = 0;
        for(int x = 0; x < 1000; x++) {
            int[] a = genRandomArray(n);
            
            Node b = new Node(a[0]);
            
            for(int i = 1; i < a.length; i ++)
            b = insert(b, a[i]);
            
            sum += height(b);
        }
        
        return sum / 1000;
    }
    
    // return an int array of randomly generated numbers
    static int[] genRandomArray(int size) {
        Random r = new Random();
        
        int[] a = new int[size];
        for(int i = 0; i < a.length; i++)
            a[i] = r.nextInt();
        
        return a;
    }
    
    
    
    // Main Method
    public static void main(String[] args) {
        int N = 100;
        
        double pointRadius = 0.005;
        double lineRadius = 0.001;
        
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.setPenRadius(pointRadius);
        StdDraw.setPenColor(Color.black);
        StdDraw.line(0,0,0,N); 
        StdDraw.text(N,20,"" + N);
        StdDraw.line(0,0,N,0);
        StdDraw.text(5,N,"" + N);
        
        double prevX, prevY;
        
        // BSTInsertion
        StdDraw.setPenColor(Color.blue);
        prevX = 0; prevY = 0;
        for(int i = 1; i <= N; i++) {
            int y = runBSTInsertion(i);
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        // Log functino that is similar to the BSTInsertion
        StdDraw.setPenColor(Color.green);
        prevX = 0; prevY = 0;
        for(int i = 1; i <= N; i++) {
            int y = (int)(2.55 * Math.log(i));
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
    }    
}