/*
 * File: HW07Problem02.java
 * Date: 3/24/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: class that represent a linked list
 *          used for homework 07 problem 02
 */
public class HW07Problem02 {
    
    // prints the linked list
    public static void printList(Node p) {
        if(p == null)
            System.out.println(".");
        else {
            System.out.print(p.item + " -> ");
            printList(p.next);
        }
    }
    
    // prints the linked list with a string before the printing
    public static void printList(String head, Node p){
        System.out.print(head + " -> ");
        printList(p);
    }
    
    
    
    // reverse the list
    public static Node reverse(Node p) {
        return reverseHelper(p, null);
    }
    
    // helper method to help reverse the list using recursion
    public static Node reverseHelper(Node p, Node q) {
        if(p == null)
            return q;
        else {
            Node temp = p.next;
            p.next = q;
            return reverseHelper(temp, p);
        }
    }
    
    
    // changes an array to a linked list iteratively and returns it
    public static Node arrayToLinkedList(int[] a) {
        if(a.length == 0)
            return null;
        
        Node current = null;
        Node previous = null;
        
        // add the elements in the array to the list
        for(int i = a.length - 1; i >= 0; i--) {
            previous = current;
            current = new Node(a[i], previous);
        }
        
        return current;
    }
    
    
    // delete the nth element of the linked list iteratively and returns
    // the new list 
    public static Node deleteNth(Node p, int n) {
        if(n == 1)
            p = p.next;
        else {
            Node current = p;
            Node previous = null;
            
            // get and delete the Node at the nth place
            for(int i = 1; i < n && current != null; i++) {
                previous = current;
                current = current.next;
            }
            if(current != null)
                previous.next = current.next;
        }
        return p;
    }
    
    
    // returns true if list p and q are equal. This is check through iteration
    public static boolean equalLists(Node p, Node q) {
        Node pCurrent = p;
        Node qCurrent = q;
        
        // check the equality of the two lists
        while(pCurrent != null && qCurrent != null) {
            if(pCurrent.item != qCurrent.item)
                return false;
            pCurrent = pCurrent.next;
            qCurrent = qCurrent.next;
        }
        
        if(pCurrent == null && qCurrent == null)
            return true;
        return false;
    }
    
    
    // returns a new linked list that only contains every other elements in
    // the original list
    public static Node everyOther(Node p) {
        if(p == null)
            return null;
        
        Node qFirst = new Node(p.item, null);
        Node qCurrent = qFirst;
        
        Node current = p.next;
        if(current != null)
            current = current.next;
        
        while(current != null) {
            qCurrent.next = new Node(current.item, null);
            qCurrent = qCurrent.next;
            
            current = current.next;
            if(current != null)
                current = current.next;
        }
        
        return qFirst;
    }
    
    
    // return true if the two lists are equal. This is done through recursion
    public static boolean equalListRec(Node p, Node q) {
        if(p == null && q == null)
            return true;
        else if(p == null || q == null)
            return false;
        
        if(p.item == q.item)
            return equalListRec(p.next, q.next);
        return false;
    }
    
    
    // return a new Node that contains every other element in the original list.
    // This is done through recurison
    public static Node everyOtherRec(Node p) {
        if(p == null)
            return null;
        Node q = new Node(p.item, null);
        
        Node pCurrent = p.next;
        if(pCurrent != null) {
            pCurrent = pCurrent.next;
            everyOtherRecHelper(pCurrent, q);
        }
        return q;
    }
    
    // helper method to get and set every other element in the original list to
    // the new list
    private static void everyOtherRecHelper(Node pCurrent, Node qCurrent) {
        qCurrent.next = new Node(pCurrent.item, null);
        
        if(pCurrent != null && pCurrent.next != null)
            everyOtherRecHelper(pCurrent.next.next, qCurrent.next);
    }
   
    
    // create and return a new linked list where its elements starts with the
    // first list, then switch to the second list at the nth element, and
    // finally ends with the remaining unadded element(s) in the first list
    public static Node splice(int n, Node p, Node q) {
        Node newNode = new Node((n == 0)? q.item : p.item, null);
        
        if(n == 0) 
            spliceHelper(n - 1, newNode, p, q.next);
        else
            spliceHelper(n - 1, newNode, p.next, q);
        
        return newNode;
    }
    
    // helper method for splicing
    private static void spliceHelper(int n, Node newNode, Node p, Node q) {
        if(p == null && q == null)
            return;        
        if(n == 0) {
            if(q == null) {
                spliceHelper(-1, newNode, p, q);}
            else {
                newNode.next = new Node(q.item, null);
                spliceHelper(0, newNode.next, p, q.next);
            }
        } else {
            if(p == null)
                spliceHelper(0, newNode, p, q);
            else {
                newNode.next = new Node(p.item, null);
                spliceHelper(n - 1, newNode.next, p.next, q);
            }
        }
    }
    
    
    // create and return a new linked list with only the elements that are
    // present in both the first and the second list.
    public static Node intersection(Node p, Node q) {
        Node newNode = new Node(-1, null);
        
        intersectionHelper(newNode, p, q);
        
        return newNode.next;
    }
    
    // helper method to find and add the elements that are in both list.
    private static void intersectionHelper(Node newNode, Node p, Node q) {

        if(p == null || q == null)
            return;
        else if(p.item > q.item)
            intersectionHelper(newNode, p, q.next);
        else if(q.item > p.item)
            intersectionHelper(newNode, p.next, q);
        else {
            newNode.next = new Node(p.item, null);
            intersectionHelper(newNode.next, p.next, q.next);
        }
    }
    
    
    
    // Main Method
    public static void main(String[] args) {
        
    }    
}


// class that represents an element in a linked list
class Node {
    // the value the Node represent
    int item;
    // the Node linked to this Node
    Node next;
    
    // constructor that assigns both the value and the linked Node
    public Node(int i, Node n) {
        item = i;
        next = n;
    }
}