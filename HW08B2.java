/**
 * File: HW02B2.java
 * Date: 3/30/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: Homework 08 Part 2, contains many recursion methods for 
 *          a Binary Search Tree
 */
public class HW08B2 {
    
    // class that represent an element in the tree
    private static class Node {
        int item;
        Node left;
        Node right;
        
        public Node(int k) {
            this(k, null, null);
        }
        
        public Node(int k, Node l, Node r) {
            item = k;
            left = l;
            right = r;
        }
    }
    
    
    // gets and returns the height of the tree
    private static int height(Node t) {
        if(t == null)
            return -1;
        else 
            return 1 + Math.max(height(t.left), height(t.right));
    }
    
    
    // insert a new element into the tree
    private static Node insert(Node t, int item) {
        if(t == null)
            return new Node(item);
        else { 
            if(item < t.item)
                t.left = insert(t.left, item);
            else if(item > t.item)
                t.right = insert(t.right, item);
            return t;
        }
    }
    
    // insert an array of integer into the tree
    private static Node insertArray(int[] a, Node r) {
        Node t = null;
        for(int i = 0; i < a.length; i++)
            t = insert(t, a[i]);
        return t;
    }
    
    // print the tree
    private static void printIndentedTree(Node t) {
        System.out.println();
        printIndentedTreeAux(t, "");
        System.out.println();
    }
    
    // helper method for printIndentedTree
    private static void printIndentedTreeAux(Node t, String indent) {
        if(t != null) {
            printIndentedTreeAux(t.right, indent + "   ");
            System.out.println(indent + t.item);
            printIndentedTreeAux(t.left, indent + "   ");
        }
    }
    
    
    // gets and returns the number of elements in the tree
    private static int size(Node t) {
        if (t == null)
            return 0;
        return 1 + size(t.left) + size(t.right);
    }
    
    
    // toString method for the tree. 
    // String returned will be in inflix style
    private static String treeToString(Node r) {
        if(r == null)
            return "";
        return String.format("(%s %d %s)",
                             treeToString(r.left),
                             r.item,
                             treeToString(r.right));
    }
    
    
    // toString method for the tree.
    // String returned will be in prefix style
    private static String treeToString2(Node r) {
        if(r == null)
            return ".";
        return String.format("%d(%s,%s)",
                             r.item,
                             treeToString2(r.left),
                             treeToString2(r.right));
    }
    
    
    // gets and returns the number of leaves in the tree
    private static int numLeaves(Node r) {
        if(r == null)
            return 0;
        else if(r.left == null && r.right == null)
            return 1;
        return numLeaves(r.left) + numLeaves(r.right);
    }
    
    
    // reverse the tree by flipping all left and right pointers
    private static Node reverse(Node r) {
        if(r == null)
            return null;
        
        reverse(r.left);
        reverse(r.right);
        
        Node temp = r.left;
        r.left = r.right;
        r.right = temp;
        
        return r;
    }
    
    
    // creates and returns a new tree that is the copy of the original one
    private static Node copy(Node r) {
        if(r == null)
            return null;
        
        Node c = new Node(r.item);
        c.left = copy(r.left);
        c.right = copy(r.right);
        return c;
    }
    
    
    // returns true if the tree is a binary search tree, that is left 
    // elements are all less than center element while right elements
    // are all greater than center element.
    public static boolean isBST(Node r) {
        return isBSTHelper(r, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    
    // helper method for testing if a tree is a binary search tree
    private static boolean isBSTHelper(Node r, int hi, int lo) {
        if(r == null)
            return true;
        if(!(lo < r.item && r.item < hi))
            return false;
        return isBSTHelper(r.left, r.item, lo) &&
            isBSTHelper(r.right, hi, r.item);
    }
    
    
    // returns true if all elements in the tree have either 0 or 2 children
    public static boolean isFull(Node r) {
        if(r == null)
            return true;
        else if(r.right == null && r.left == null)
            return true;
        else if(r.right != null && r.left != null)
            return isFull(r.right) && isFull(r.left);
        else 
            return false;
    }
    
    
    // returns true if all but the last element in the tree have only 1 
    // children
    public static boolean isDegenerate(Node r) {
        if(r == null)
            return false;
        else if(r.right != null && r.left == null)
            return isDegenerate(r.right);
        else if(r.right == null && r.left != null)
            return isDegenerate(r.left);
        else if(r.right == null && r.left == null)
            return true;
        return false;
    }
    
    
    // returns true if all subtrees have the same size
    public static boolean isPerfect(Node r) {
        if(r == null)
            return true;
        else if(size(r.left) == size(r.right))
            return isPerfect(r.left) && isPerfect(r.right); 
        else 
            return false;
    }
    
    
    // returns true if all elements in the two trees are equal
    public static boolean equals(Node r, Node s) {
        if(r == null && s == null)
            return true;
        else if((r == null && s != null) || (r != null && s == null))
            return false;
        else if(r.item == s.item) 
            return equals(r.left, s.left) && equals(r.right, s.right);
        else 
            return false;
    }
    
    
    
    // Main Method
    public static void main(String[] args) {
        

        System.out.println("Sample trees for testing tree methods for HW08B2:"); 
        
        System.out.println("\nTree 0 is null.\n"); 
        Node root0 = null; 
        
        Node root1=null;
        
        int[] A = { 5, 2, 9, 4,1, 7, 12 };
        root1 = insertArray(A,root1);
        System.out.println("Tree 1:"); 
        printIndentedTree(root1);
        
        Node root2=null;
        
        int[] B = { 1, 2, 3, 6, 5, 4 };
        root2 = insertArray(B,root2);
        System.out.println("Tree 2:"); 
        printIndentedTree(root2);   
        
        Node root3=null;
        
        int[]C = { 1, 6, 2, 4, 3, 5 };
        root3 = insertArray(C,root3);
        System.out.println("Tree 3:"); 
        printIndentedTree(root3);     
        
        Node root4=null;
        
        int[] D = { 6, 2, 15, 9, 4, 1, 18, 3, 12, 7,5,10,13 };
        root4 = insertArray(D,root4);
        System.out.println("Tree 4:"); 
        printIndentedTree(root4);     
        
        System.out.println("\nPerformance Tests...."); 
        
        System.out.println("\nTesting size....");
        System.out.println("\n[0] Should print out:\n" + D.length); 
        System.err.println(size(root4)); 
        
        System.out.println("\nTesting treeToString....");
        System.out.println("\n[1] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))"); 
        System.err.println(treeToString(root1)); 
        
        System.out.println("\n[2] Should print out:\n( 1 ( 2 ( 3 ((( 4 ) 5 ) 6 ))))"); 
        System.err.println(treeToString(root2)); 
        
        System.out.println("\nTesting treeToString2....");
        System.out.println("\n[3] Should print out:\n5(2(1(.,.),4(.,.)),9(7(.,.),12(.,.)))"); 
        System.err.println(treeToString2(root1)); 
        
        System.out.println("\n[4] Should print out:\n1(.,6(2(.,4(3(.,.),5(.,.))),.))"); 
        System.err.println(treeToString2(root3)); 
        
        System.out.println("\nTesting numLeaves ....");
        System.out.println("\n[5] Should print out:\n0"); 
        System.err.println(numLeaves(root0)); 
        
        System.out.println("\n[6] Should print out:\n7"); 
        System.err.println(numLeaves(root4)); 
        
        System.out.println("\nTesting isFull ....");
        System.out.println("\n[7] Should print out:\ntrue"); 
        System.err.println(isFull(root0)); 
        
        System.out.println("\n[8] Should print out:\ntrue"); 
        System.err.println(isFull(root1)); 
        
        System.out.println("\n[9] Should print out:\nfalse"); 
        System.err.println(isFull(root3)); 
        
        System.out.println("\n[10] Should print out:\ntrue"); 
        System.err.println(isFull(root4)); 
        
        System.out.println("\nTesting isDegenerate ....");
        System.out.println("\n[11] Should print out:\nfalse"); 
        System.err.println(isDegenerate(root0)); 
        
        System.out.println("\n[12] Should print out:\ntrue"); 
        System.err.println(isDegenerate(root2)); 
        
        System.out.println("\n[13] Should print out:\nfalse"); 
        System.err.println(isDegenerate(root3));  
        
        System.out.println("\n[14] Should print out:\nfalse"); 
        System.err.println(isDegenerate(root4)); 
        
        System.out.println("\nTesting isPerfect ....");
        System.out.println("\n[15] Should print out:\nfalse"); 
        System.err.println(isPerfect(root3));  
        
        System.out.println("\n[16] Should print out:\ntrue"); 
        System.err.println(isPerfect(root1));        
        
        
        System.out.println("\nTesting isBST ....");
        
// not BSTs 
        Node temp = new Node(4, new Node(5), null);
        Node temp2 = new Node(4, new Node(2, new Node(1), new Node(5)), new Node(10));
        
        System.out.println("\n[17] Should print out:\ntrue"); 
        System.err.println(isBST(root0)); 
        
        System.out.println("\n[18] Should print out:\ntrue"); 
        System.err.println(isBST(root4)); 
        
        System.out.println("\n[19] Should print out:\nfalse"); 
        System.err.println(isBST(temp)); 
        
        System.out.println("\n[20] Should print out:\nfalse"); 
        System.err.println(isBST(temp2)); 
        
        System.out.println("\n[21] Should print out:\nfalse"); 
        System.err.println(isBST(temp2)); 
        
        
        int[] A1 = { 9, 4,1, 7, 5, 2,  12 };
        System.out.println("\nTesting copy ....");
        root1 = insertArray(A,root1);
        
        temp = copy(root1);
        System.out.println("\n[22] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))");  
        System.err.println(treeToString(root1)); 
        
        System.out.println("\n[23] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))");  
        System.err.println(treeToString(temp)); 
        
        temp = insert(temp, 1000); 
        
        System.out.println("\n[24] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 ( 1000 ))))");  
        System.err.println(treeToString(temp)); 
        
        System.out.println("\n[25] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))");  
        System.err.println(treeToString(root1)); 
        
        System.out.println("\nTesting reverse ....");
        
        System.out.println("\n[26] Should print out:\n((( 12 ) 9 ( 7 )) 5 (( 4 ) 2 ( 1 )))");  
        System.err.println(treeToString(reverse(copy(root1)))); 
        
        System.out.println("\n[27] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))");  
        System.err.println(treeToString(reverse(reverse(root1)))); 
        
        System.out.println("\nTesting equals ....");
        
        System.out.println("\n[28] Should print out:\nfalse"); 
        System.err.println(equals(root1, root2)); 
        
        System.out.println("\n[29] Should print out:\nfalse"); 
        System.err.println(equals(root1, temp)); 
        
        int[] A2 = { 5, 2, 9, 1, 4, 12, 7 };
        temp = null; 
        temp = insertArray(A2,temp);
        
        System.out.println("\n[30] Should print out:\ntrue"); 
        System.err.println(equals(root1, temp)); 
        
        
        
        System.out.println("\n :-) "); 
        
        boolean IamDone = true; 
        System.out.println("\n[31] Should print out:\nHigh Five!"); 
        if(IamDone)
            System.err.println("High Five!");
        else
            System.err.println("Nope!"); 
        
        
    }
}
