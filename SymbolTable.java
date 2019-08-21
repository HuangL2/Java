/*
 * File: SymbolTable.java
 * Date: 3/24/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: a class that represents a symbol table.
 * Throws: KeyNotFoundException
 */
public class SymbolTable<Value> {
    
    // class that represents an element
    private class Node{
        String variable;   // name of the Node
        Value value;       // value the Node represents
        Node next;         // next Node after this one
        Node previous;     // previous Node before this one
        
        // default constructor
        public Node() {
        }
        
        // constructor that only assign the name and the value
        public Node(String k, Value v) {
            this(k, v, null, null);
        }
        
        // constructor that assigns the name, value, next Node and previous Node
        public Node(String k, Value v, Node p, Node n) {
            variable = k;
            value = v;
            next = n;
            previous = p;
        }
    }
    
    // firstmost and lastmost Node
    private Node head = null;
    private Node end = null;
    
    
    
    
    // create and add a new Node to the table. if the element with the
    // specified name is already within the table, the value of the element
    // will be changed to the currently specified one.
    // the name will be put in ascending order of the name
    public void put(String var, Value val) {
        if(head == null) {
            Node newNode = new Node(var, val);
            head = newNode;
            end = newNode;
        } else
            putHelper(null, head, var, val);
    }
    
    // helper method that find the index the new element will go and
    // insert it in.
    private void putHelper(Node previous, Node current, String var, Value val) {
        if(current == null) {
            Node newNode = new Node(var, val, previous, null);
            previous.next = newNode;
            end = newNode;
        } else if(current.variable.compareTo(var) < 0)
            putHelper(current, current.next, var, val);
        else if(current.variable.compareTo(var) > 0) {
            Node newNode = new Node(var, val, previous, current);
            previous.next = newNode;
            current.previous = newNode;
        } else
            current.value = val;
    }
    
    
    
    // return the value element with the specified name
    // throws KeyNotFoundException if an element of such name is not found
    public Value get(String var) throws KeyNotFoundException {
        return getHelper(head, var);
    }
    
    // helper method to find the index of the element
    private Value getHelper(Node p, String var) throws KeyNotFoundException {
        if(p == null)
            throw new KeyNotFoundException();
        else if(p.variable.equals(var))
            return p.value;
        else
            return getHelper(p.next, var);
    }
    
    
    
    
    // returns true if the table contains an element with the specified name
    public boolean contains(String var) {
        return containsHelper(head, var);
    }
    
    // helper method to iterate through the table
    private boolean containsHelper(Node p, String var) {
        if(p == null)
            return false;
        else if(p.variable.equals(var))
            return true;
        else
            return containsHelper(p.next, var);
    }
    
    
    
    
    // delete the element with the specified name if it exists
    public void delete(String var) {
        deleteHelper(head, var);
    }
    
    // helper method for delete
    private void deleteHelper(Node current, String var) {
        // if the element does not exist, do nothing
        if(current == null || current.variable.compareTo(var) > 0)
            return;
        
        // iterate through the tabler
        else if(current.variable.compareTo(var) < 0)
            deleteHelper(current.next, var);
        
        // the element exists
        else {
            if(current == head) {       // element is the head
                if(current.next == null) {     // and is the only element 
                    head = null;
                    end = null;
                } else {                       // and is not the only element
                    head = current.next;
                    current.next.previous = null;
                }
            } else if(current == end) { // element is the end
                end = current.previous;
                current.previous.next = null;
            } else {                    // element is neither
                current.previous.next = current.next;
                current.next.previous = current.previous;
            }
        }
    }
    
    
    
    // gets and returns the name of the first element
    // throws KeyNotFoundException if the table is empty
    public String min() throws KeyNotFoundException {
        if(head == null)
            throw new KeyNotFoundException();
        return head.variable;
    }
    
    // gets and returns the name of the last element
    // throws KeyNotFoundException if the table is empty
    public String max() throws KeyNotFoundException {
        if(end == null)
            throw new KeyNotFoundException();
        return end.variable;
    }
    
    
    
    // gets the largest element that is smaller than or equal to the var.
    // throws KeyNotFoundException if the table is empty
    //        or if var is the smaller than the first element
    public String floor(String var) throws KeyNotFoundException {
        if(head == null || var.compareTo(head.variable) < 0)
            throw new KeyNotFoundException();
        return floorHelper(head, var);
    }
    
    // helper method to get the floor
    private String floorHelper(Node p, String var) {
        if(p.next == null)
            return p.variable;
        else if(p.variable.compareTo(var) < 0)
            return floorHelper(p.next, var);
        else if(p.variable.compareTo(var) > 0)
            return p.previous.variable;
        else
            return var;
    }
    
    
    
    // gets the smallest element that is larger than or equal to the var
    // throws KeyNotFoundException if the table is empty
    //        or if var is the larger than the last element
    private String ceiling(String var) throws KeyNotFoundException {
        if(head == null || var.compareTo(end.variable) > 0)
            throw new KeyNotFoundException();
        return ceilingHelper(end, var);
    }
    
    // helper method for the ceiling
    private String ceilingHelper(Node p, String var) {
        if(p.previous == null)
            return p.variable;
        else if(p.variable.compareTo(var) < 0)
            return p.next.variable;
        else if(p.variable.compareTo(var) > 0)
            return ceilingHelper(p.previous, var);
        else
            return var;
    }
    
    
    // get the number of elements before where var is or would be
    public int rank(String var) {
        return rankHelper(head, 0, var);
    }
    
    // helper method to get the rank
    private int rankHelper(Node p, int cr, String var) {
        if(p.next == null)
            return cr + 1;
        else if(p.variable.compareTo(var) < 0)
            return rankHelper(p.next, cr + 1, var);
        else 
            return cr;
    }
    
    
    
    // gets and returns the name of the element at the nth index
    // throws KeyNotFoundException if n is negative
    //        or is greater than the size - 1
    public String select(int n) throws KeyNotFoundException {
        return selectHelper(head, n);
    }
    
    // helper method for selection
    private String selectHelper(Node p, int r) throws KeyNotFoundException{
        if(r < 0 || p == null)
            throw new KeyNotFoundException();
        else if(r == 0)
            return p.variable;
        else
            return selectHelper(p.next, r - 1);
    }
    
    
    
    // delete the first element if it exists
    public void deleteMin() {
        if(head == null)
            return;
        else if(head.next == null) {
            head = null;
            end = null;
        } else {
            head = head.next;
            head.previous = null;
        }
    }
    
    // delete the last element if it exists
    public void deleteMax() {
        if(end == null)
            return;
        else if(end.previous == null) {
            head = null;
            end = null;
        } else {
            end = end.previous;
            end.next = null;
        }
    }
    
    
    // gets and return the size of the table
    public int size() {
        return sizeHelper(head, null, null, 0);
    }
    
    // gets and return the size of the table starting at lo and ending at lo 
    public int size(String lo, String hi) {
        return sizeHelper(head, lo, hi, 0);
    }
    
    // helper method to find the size
    private int sizeHelper(Node p, String lo, String hi, int size) {  
        if(p == null)                     // if size is 0
            return size;
        else if(p.next == null) {         // if reach the end of the table
            return size + 1;}
        else if(lo != null && p.variable.compareTo(lo) < 0) // if lo > current
            return sizeHelper(p.next, lo, hi, size);
        else {
            if(hi != null && p.variable.compareTo(hi) > 0)  // if hi < current
                return size;
            else
                return sizeHelper(p.next, lo, hi, size + 1); 
        }
    }
    
    
    
    // returns true if the table is empty
    public boolean isEmpty() {
        return head == null;
    }
    
    
    
    // toString method
    public String toString() {
        if(head == null)
            return "";
        return toStringHelper(head);
    }
    
    // toString helper method
    private String toStringHelper(Node p) {
        if(p == null)
            return "";
        
        return ((p == head)? "" : ": ") + 
            "(" + p.variable + "," + p.value + ") " 
            + toStringHelper(p.next);
    }
    
    
    
    
    // Main Method
    public static void main(String[] args) {
        
        
        SymbolTable<Integer> S = new SymbolTable<Integer>();  
        
        
        S.put("a",3); 
        S.put("c",1);
        S.put("b",1);
        
        System.out.println("\n[1] Should print out:\n(a,3) : (b,1) : (c,1) "); 
        System.out.println(S); 
        
        System.out.println("\n[2] Should print out:\n3"); 
        System.out.println(S.size()); 
        
        System.out.println("\n[3] Should print out:\nfalse"); 
        System.out.println(S.isEmpty()); 
        
        System.out.println("\n[4] Should print out:\n1");  
        String testKey = "c"; 
        try {
            System.out.println(S.get(testKey)); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key " + testKey + " does not exist!"); 
        }
        
        System.out.println("\n[5] Should print out:\nKey d does not exist!");  
        testKey = "d"; 
        try {
            System.out.println(S.get(testKey)); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key " + testKey + " does not exist!"); 
        }
        
        System.out.println("\n[6] Should print out:\n(a,3) : (b,1) : (c,4) "); 
        S.put("c",4);
        System.out.println(S);    
        
        System.out.println("\n[7] Should print out:\ntrue"); 
        System.out.println(S.contains("c"));  
        
        System.out.println("\n[8] Should print out:\ntrue"); 
        System.out.println(S.contains("a")); 
        
        System.out.println("\n[9] Should print out:\nfalse"); 
        System.out.println(S.contains("e"));  
        
        S.delete("a"); 
        S.delete("d"); 
        S.delete("c"); 
        System.out.println("\n[10] Should print out:\n(b,1)");     
        System.out.println(S); 
        
        System.out.println("\n[11] Should print out:\n0");  
        S.delete("b"); 
        System.out.println(S.size()); 
        
        System.out.println("\n[12] Should print out:\nnope! nope! nope! nope! nope!");  
        try{
            System.out.println(S.min()); 
        }
        catch(KeyNotFoundException e) {
            System.out.print("nope! "); 
        }
        
        try{
            System.out.println(S.max()); 
        }
        catch(KeyNotFoundException e) {
            System.out.print("nope! "); 
        }
        try{
            System.out.println(S.floor("a")); 
        }
        catch(KeyNotFoundException e) {
            System.out.print("nope! "); 
        }
        try{
            System.out.println(S.ceiling("a")); 
        }
        catch(KeyNotFoundException e) {
            System.out.print("nope! "); 
        }
        
        
        try{
            System.out.println(S.select(0)); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("nope! "); 
        }
        
        
        
        SymbolTable<String> T = new SymbolTable<String>(); 
        
        T.put("09:00:00","Chicago");
        T.put("09:00:03","Phoenix");
        T.put("09:00:13","Houston");
        T.put("09:00:59","Chicago"); 
        T.put("09:36:14","Seattle");
        T.put("09:37:44","Phoenix");
        T.put("09:10:25","Seattle");
        T.put("09:14:25","Phoenix");
        T.put("09:19:32","Chicago");
        T.put("09:19:46","Chicago");
        T.put("09:21:05","Chicago");
        T.put("09:22:43","Seattle");
        T.put("09:01:10","Houston");
        T.put("09:03:13","Chicago");
        T.put("09:10:11","Seattle");
        T.put("09:25:52","Chicago");
        T.put("09:22:54","Seattle");  
        T.put("09:35:21","Chicago");
        
        System.out.println("\n[13] Should print out:\n(09:00:00,Chicago) : (09:00:03,Phoenix) : (09:00:13,Houston) : (09:00:59,Chicago) : (09:01:10,Houston) : (09:03:13,Chicago) : (09:10:11,Seattle) : (09:10:25,Seattle) : (09:14:25,Phoenix) : (09:19:32,Chicago) : (09:19:46,Chicago) : (09:21:05,Chicago) : (09:22:43,Seattle) : (09:22:54,Seattle) : (09:25:52,Chicago) : (09:35:21,Chicago) : (09:36:14,Seattle) : (09:37:44,Phoenix)");      
        System.out.println("\n" + T);    
        
        try{
            System.out.println("\n[14] Should print out:\n09:00:00");
            System.out.println(T.min()); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key 09:00:00 does not exist!"); 
        } 
        try{
            System.out.println("\n[15] Should print out:\n09:37:44");
            System.out.println(T.max()); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key 09:37:44 does not exist!"); 
        } 
        
        try{
            System.out.println("\n[16] Should print out:\n09:03:13");
            System.out.println(T.floor("09:05:00")); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key 09:05:00 does not exist!"); 
        } 
        
        try{
            System.out.println("\n[17] Should print out:\n09:35:21");
            System.out.println(T.floor("09:35:21")); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key 09:35:21 does not exist!"); 
        }
        
        try{
            System.out.println("\n[18] Should print out:\n09:35:21");
            System.out.println(T.ceiling("09:30:00")); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key 09:35:21 does not exist!"); 
        } 
        
        try{
            System.out.println("\n[19] Should print out:\n09:00:00");
            System.out.println(T.ceiling("09:00:00")); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key 09:00:00 does not exist!"); 
        }
        
        try{
            System.out.println("\n[20] Should print out:\n09:10:25");
            System.out.println(T.select(7)); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Key 09:10:25 does not exist!"); 
        } 
        
        
        System.out.println("\n[21] Should print out:\n7");
        System.out.println(T.rank("09:10:25")); 
        
        System.out.println("\n[22] Should print out:\n15");
        System.out.println(T.rank("09:30:00"));     
        
        System.out.println("\n[23] Should print out:\n5");
        System.out.println(T.size("09:15:00", "09:25:00")); 
        
        try {
            System.out.println("\n[24] Should print out:\n18 18");
            System.out.println(T.size() + " " + T.size(T.min(), T.max())); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Symbol table empty!"); 
        }
        
        try {   
            System.out.println("\n[25] Should print out:\n09:00:03");
            T.deleteMin(); 
            System.out.println(T.min()); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Symbol table empty!");
        }
        
        try {   
            System.out.println("\n[26] Should print out:\n09:36:14");
            T.deleteMax(); 
            System.out.println(T.max()); 
        }
        catch(KeyNotFoundException e) {
            System.out.println("Symbol table empty!");
        }
        
        System.out.println("\n[27] Should print out:\n16");
        System.out.println(T.size());    
        
    }
}

// Exception thrown when trying to get an element that is not there
class KeyNotFoundException extends Exception {}

