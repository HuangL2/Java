/*
 * File: Set.java
 * Date: 2/10/2016
 * Author: Linming Huang
 * Purpose: A class that represent a sorted collection in which there are no
 *          duplicate elements
 */
public class Set 
{
    // maximum size of the set
    public static final int SIZE = 20;
    // int value that represents null
    public static final int NO_ELEMENT = Integer.MAX_VALUE;
    
    
    
    // returns a set with size SIZE filled with NO_ELEMENT
    public static int[] getEmptySet() 
    {
        int[] s = new int[SIZE];
        for(int i = 0; i < SIZE; i++)
            s[i] = NO_ELEMENT;
        return s;
    }
    
    
    
    // returns a set made from int array.
    // method will check and ignore any duplicative element.
    // returned set will be sorted
    // original int array is not affected. 
    public static int[] intArray2Set(int[] a)
    {
        int[] s = getEmptySet();
        
        // i to iterate through a
        // j to iterate through s
        // k for checking duplicative element
        for(int i = 0, j = 0; i < a.length; i++) { 
            for(int k = 0; k < j + 1 && k < 20 && s[k] != a[i]; k++) {
                if(s[k] == NO_ELEMENT){                    
                    s = add(a[i], s);
                    j++;
                    break;
                }
            }
        }
        return s;
    }
    
    
    
    // return a String representation of the int array.
    public static String setToString(int[] s)
    {
        String str = "";
        str += "{";
        
        for(int i = 0; i < s.length; i++) {
            if(s[i] == NO_ELEMENT)
                break;
            
            str += (i == 0? "" : ",") + s[i];
        }
        
        str += "}";
        
        return str;
    }
    
    
    
    // returns the number of elements besides NO_ELEMENT in the set.
    public static int size(int[] s)
    {
        int size;
        for(size = 0; size < s.length; size++) {
            if(s[size] == NO_ELEMENT)
                break;
        }
        return size;
    }
    
    
    
    // returns true if element n is contained in array s.
    public static boolean member(int n, int[] s)
    {        
        for(int i = 0; i < s.length; i++) {
            if(s[i] == n)
                return true;
            else if(s[i] == NO_ELEMENT)
                break;
        }
        return false;
    }
    
    
    
    // returns true if the first element of the array is NO_ELEMENT.
    public static boolean isEmpty(int[] s)
    {
        return s[0] == NO_ELEMENT;
    }
    
    
    
    // returns true if every element of s is an element of t.
    public static boolean subset(int[] s, int[] t)
    {
        int[] u = union(s, t);
        
        if(equal(t, u))
            return true;
        
        return false;
    }
    
    
    
    // returns true if every element of s is an element of t and vice versa.
    public static boolean equal(int[] s, int[] t)
    {
        if(s.length != t.length)
            return false;
        
        for(int i = 0; i < s.length; i ++) {
            if(s[i] != t[i])
                return false;
        }
        return true;
    }
    
    
    
    // returns a new array that have a new element added to the array
    // the retruned array will be sorted.
    // if the new element already exists, nothing happens
    // the original array is not modified in any way in this method.
    public static int[] add(int n, int[] s)
    {
        int[] t = copy(s);
        
        for(int i = 0; i < t.length; i++) {            
            if(n == t[i])
                return t;
            else if(t[i] == NO_ELEMENT) {
                t[i] = n;
                break;
            } 
        }
        sort(t);    
        return t;
    }
    
    // returns a new array that have an element removed from the array
    // the returned array will be sorted
    // if the element to be removed is not in the array, nothing happens
    // the original array is not modified in any way in this method.
    public static int[] remove(int n, int[] s)
    {
        int[] t = getEmptySet();
        
        for(int i = 0, j = 0; i < t.length && s[i] != NO_ELEMENT; i++, j++) {            
            if(s[i] == n)             
                j --;
            else 
                t[j] = s[i];
        }
        sort(t);
        return t;
    }
    
    
    
    
    // returns a new array that represents the union of the two arrays
    // the original array is not modified in any way in this method.
    public static int[] union(int[] s, int[] t)
    {
        int[] u = copy(s);
        
        for(int i = 0; i < t.length && t[i] != NO_ELEMENT; i++)
            u = add(t[i], u);
        
        return u;
    }
    
    
    
    // returns a new array that represents the intersection of the two arrays
    // the original array is not modified in any way in this method.
    public static int[] intersection(int[] s, int[] t)
    {
        int[] u = copy(s);
        
        for(int i = 0; i < s.length && s[i] != NO_ELEMENT; i++)
            u = (member(s[i], t))? u : remove(s[i], u);
        
        return u;
    }
    
    
    
    // returns a new array which each elements is only in one of the two arrays
    // the original array is not modified in any way in this method.
    public static int[] setdifference(int[] s, int[] t)
    {
        int[] inter = intersection(s, t);
        int[] u = copy(s);
        
        for(int i = 0; i < inter.length && inter[i] != NO_ELEMENT; i++) {
            u = remove(inter[i], u);
        }
        
        return u;
    }
    
    
    
    // swap element i with element j in array a
    public static void swap(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    
    
    // using insertion sort to sort an array
    public static void sort(int[] a)
    {
        for (int i = 1; i < a.length; i++) { 
            for (int j = i; j > 0 && (a[j]  < a[j-1]); j--) { 
                swap(a, j, j-1); 
            } 
        }
    }
    
    
    
    // returns an exact copy of the array
    public static int[] copy(int[] s)
    {
        int[] c = new int[SIZE];
        for(int i = 0; i < SIZE; i++)
            c[i] = s[i];
        return c;
    }
    
    
    
    
    
    
    
    public static void main(String [] args) {
        
        System.out.println("\nTests for Set\n");
        
        int[] A = {2,4,6,4,8,6,0,2,4,6,8};
        int[] B = {1,3,5,3,7,9,1};
        int[] C = {2,4,8};
        int[] D = {3,5,4,6};
        
        int[] SA = intArray2Set(A); 
        int[] SB = intArray2Set(B);
        int[] SC = intArray2Set(C);
        int[] SD = intArray2Set(D);
        int[] SE = getEmptySet();
        
        System.out.println("Test 1: Should be:\n{0,2,4,6,8}"); 
        System.out.println(setToString(SA)); 
        System.out.println(); 
        
        System.out.println("Test 2: Should be:\n{1,3,5,7,9}"); 
        System.out.println(setToString(SB));; 
        System.out.println(); 
        
        System.out.println("Test 3: Should be:\n{2,4,8}"); 
        System.out.println(setToString(SC)); 
        System.out.println(); 
        
        System.out.println("Test 4: Should be:\n{3,4,5,6}"); 
        System.out.println(setToString(SD)); 
        System.out.println(); 
        
        System.out.println("Test 5: Should be:\n{}"); 
        System.out.println(setToString(SE)); 
        System.out.println(); 
        
        System.out.println("Test 6: Should be:\n0"); 
        System.out.println(size(SE)); 
        System.out.println();          
        
        System.out.println("Test 7: Should be:\n5"); 
        System.out.println(size(SA)); 
        System.out.println(); 
        
        System.out.println("Test 8: Should be:\ntrue"); 
        System.out.println(isEmpty(SE)); 
        System.out.println();            
        
        System.out.println("Test 9: Should be:\nfalse"); 
        System.out.println(isEmpty(SB)); 
        System.out.println();          
        
        System.out.println("Test 10: Should be:\nfalse"); 
        System.out.println(member(3, SA)); 
        System.out.println(); 
        
        System.out.println("Test 11: Should be:\ntrue"); 
        System.out.println(member(3, SB)); 
        System.out.println(); 
        
        System.out.println("Test 12: Should be:\nfalse"); 
        System.out.println(member(3, SE)); 
        System.out.println();            
        
        System.out.println("Test 13: Should be:\nfalse"); 
        System.out.println(subset(SB,SA)); 
        System.out.println();          
        
        System.out.println("Test 14: Should be:\ntrue"); 
        System.out.println(subset(SC,SA)); 
        System.out.println(); 
        
        System.out.println("Test 15: Should be:\ntrue"); 
        System.out.println(subset(SE,SA)); 
        System.out.println();
        
        System.out.println("Test 16: Should be:\ntrue"); 
        System.out.println(equal(SC,SC)); 
        System.out.println(); 
        
        System.out.println("Test 17: Should be:\nfalse"); 
        System.out.println(equal(SB,SA)); 
        System.out.println();
        
        System.out.println("Test 18: Should be:\ntrue"); 
        System.out.println(equal(SE,SE)); 
        System.out.println();
        
        System.out.println("Test 19: Should be:\n{0,2,4,6,8}"); 
        System.out.println(setToString(add(4,SA))); 
        System.out.println();       
        
        System.out.println("Test 20: Should be:\n{4}"); 
        System.out.println(setToString(add(4,SE))); 
        System.out.println();
        
        System.out.println("Test 21: Should be:\n{0,2,6,8}"); 
        System.out.println(setToString(remove(4,SA))); 
        System.out.println();       
        
        System.out.println("Test 22: Should be:\n{1,3,5,7,9}"); 
        System.out.println(setToString(remove(4,SB))); 
        System.out.println();
        
        System.out.println("Test 23: Should be:\n{}"); 
        System.out.println(setToString(remove(4,SE))); 
        System.out.println();
        
        System.out.println("Test 24: Should be:\n{0,2,4,6,8}"); 
        System.out.println(setToString(union(SC,SA))); 
        System.out.println();
        
        System.out.println("Test 25: Should be:\n{0,1,2,3,4,5,6,7,8,9}"); 
        System.out.println(setToString(union(SA,SB))); 
        System.out.println();
        
        System.out.println("Test 26: Should be:\n{3,4,5,6}"); 
        System.out.println(setToString(union(SD,SE))); 
        System.out.println();
        
        System.out.println("Test 27: Should be:\n{0,2,4,6,8}"); 
        System.out.println(setToString(union(SC,SA))); 
        System.out.println();
        
        System.out.println("Test 28: Should be:\n{}"); 
        System.out.println(setToString(intersection(SA,SB))); 
        System.out.println();
        
        System.out.println("Test 29: Should be:\n{4}"); 
        System.out.println(setToString(intersection(SC,SD))); 
        System.out.println();  
        
        System.out.println("Test 30: Should be:\n{2,4,8}"); 
        System.out.println(setToString(intersection(SC,SA))); 
        System.out.println();
        
        System.out.println("Test 31: Should be:\n{0,6}"); 
        System.out.println(setToString(setdifference(SA,SC))); 
        System.out.println();
        
        System.out.println("Test 32: Should be:\n{2,8}"); 
        System.out.println(setToString(setdifference(SC,SD))); 
        System.out.println();  
        
        System.out.println("Test 33: Should be:\n{1,3,5,7,9}"); 
        System.out.println(setToString(setdifference(SB,SE))); 
        System.out.println(); 
        
        System.out.println("Test 34: Should be:\n{}"); 
        System.out.println(setToString(setdifference(SB,SB))); 
        System.out.println(); 
    } 
}