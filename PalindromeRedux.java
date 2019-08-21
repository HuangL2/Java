/*
 * File: PalindromeRedux.java
 * Date: 2/4/2016
 * Author: Linming Huang
 * Purpose: To debug the original file and leave all the println statements. 
 */
public class PalindromeRedux {
    
    // flag that allows debugging println statements
    public static boolean isDebugging = true;
    
    // Check whether str is a palindrome by moving first half of the string
    // to an array, and then go backward through array and forward
    // through str and compare. 
    public static boolean palindrome(String str) {
        
        // array to hold first half of string
        char[] A = new char[(str.length() / 2 + 
                             ((str.length() % 2 == 1)? 1 : 0))];        
        
        // next location to put a character                  
        int next = 0;                     
        
        // Move first half of str into the array.
        for (int i = 0; i < A.length; i++) {   
            A[next] = str.charAt(i);
            ++next;
        }
                                 
        
        // Compare array backwards with rest of str. 
        
        for (int i = str.length() / 2; i < str.length(); i++) {                                 
            --next; 
            
            char c = A[next];
            
            debug(String.valueOf(c));
            debug(String.valueOf(str.charAt(i)));
            
            if (c != str.charAt(i))            
                return false;
        }
        
        return true;
    }     
    
    
    
    // method used to print the println statements used while debugging
    public static void debug(String msg) {
        if(isDebugging)
            System.err.println("\t" + msg);
    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        
        System.out.println("\nIs redder a palindrome? Should be true:");
        System.out.println(palindrome("redder"));
        
        System.out.println("\nIs reddet a palindrome? Should be false:");
        System.out.println(palindrome("reddet"));
        
        // Additional test cases should follow the same pattern.....
        
        
    }
    
}