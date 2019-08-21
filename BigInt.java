/*
 * File: BigInt.java
 * Date: 2/18/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: a class that represent an integer over the size of 2^32
 */
public class BigInt {
    
    
    // creates an array of integer to represent the BigInt
    private int[] bigInt = new int[BigMath.SIZE];
    
    
    
    
    
    // default constructer, same as BigInt(0)
    public BigInt() {
        putValue(0);
    }
    
    // constructs a BigInt with value of s as a number
    public BigInt(String s) {
        putValue(s);
    }
    
    // constructs a BigInt with value of a chained together
    // i.e. if a = {1,2,3,5}, then the value will be 1235.
    public BigInt(int[] a) {
        putValue(a);
    }
    
    // constructs a BigInt with vale of n
    public BigInt(int n) {
        putValue(n);
    }
    
    
    // Change the value of the BigInt to the value of s as a number.
    // This method changes s to an int array to make use of the 
    // overloaded method that accepts an int array
    public void putValue(String s) {
        
        s = s.trim();
        int[] a = new int[s.length()];
        
        for(int i = 0; i < a.length; i++) {
            a[i] = s.charAt(i) - '0';
        }
        
        putValue(a);
    }
    
    
    // Change the value of the BigInt to the value of a chained together.
    // This method changes the array to match the array that represent 
    // the BigInt, that is make this array the same size as the 
    // represented array and add leading 0s so that it can makes use of
    // the private method init.
    public void putValue(int[] a) {
        
        int[] ia = new int[bigInt.length];
        
        for(int i = a.length - 1; i >= 0; i--) {
            ia[i - a.length + bigInt.length] = a[i];
        }
        
        init(ia);
    }
    
    
    // Change the value of the BigInt to the value of n.
    // This method changes n to an int array that is the same size of the
    // array that represents the BigInt and adds leading 0s so that it
    // can make use of the private method init
    public void putValue(int n) {
        int[] ia = new int[bigInt.length];
        
        for(int i = ia.length - 1; i >= 0; i--) {
            ia[i] = n % 10;
            n /= 10;
        }
        
        init(ia);
    }
    
    
    // copies the int array to the int array that represents the BigInt
    private void init(int[] a) {
        if(a.length != bigInt.length)
            return;
        
        for(int i = 0; i < bigInt.length; i++) 
            bigInt[i] = a[i];
    }
    
    
    
    
    
    
    // returns the length of the BigInt.
    // The return value ignores the leading 0s.
    public int length() {
        int i = 0;
        while(bigInt[i] == 0 && i != bigInt.length - 1) {
            i++;
        }
        
        return bigInt.length - i;
    }
    
    
    // returns the digit at index i.
    // the index starts at the first non-zero number
    public int digitAt(int i) {
        int l = length();
        
        return bigInt[bigInt.length - l + i];
    }
    
    
    // changes the digit at index i to n.
    // the index starts at the first non-zero number
    public void putDigitAt(int i, int n) {
        int l = length();
        
        bigInt[bigInt.length - l + i] = n;
    }
    
    
    // toString method for BigInt
    public String toString() {
        String s = "";
        for(int i = 0; i < length(); i++)
            s += digitAt(i);
        return s;
    }
    
    
    
    
    
    // Main method
    public static void main(String [] args) {
        
        System.out.println("\nUnit Test for BigInt Class");
        
        int[] a = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6,4,3,3,8};
        String b = "314159265358979323846264338327950288";
        int c = 314159265;
        
        BigInt A = new BigInt();
        A.putValue(a);
        
        System.out.println("\nTest 1: Should be:\n27");
        System.out.println( A.length() );
        
        System.out.println("\nTest 2: Should be:\n314159265358979323846264338");
        System.out.println( A );
        
        
        
        BigInt B = new BigInt();
        B.putValue(b);
        
        System.out.println("\nTest 3: Should be:\n36");
        System.out.println( B.length() );
        
        System.out.println("\nTest 4: Should be:\n314159265358979323846264338327950288");
        System.out.println( B );
        
        BigInt C = new BigInt();
        C.putValue(c);  
        
        System.out.println("\nTest 5: Should be:\n9");
        System.out.println( C.length() );
        
        System.out.println("\nTest 6: Should be:\n314159265");
        System.out.println( C );
        
        BigInt Z = new BigInt();       // will be zero
        
        // Special case: 0 will have length 1 and print out as a single digit
        
        System.out.println("\nTest 7: Should be:\n1");
        System.out.println( Z.length() );
        
        System.out.println("\nTest 8: Should be:\n0");
        System.out.println( Z );
        
        BigInt One = new BigInt();
        One.putValue(1);
        
        System.out.println("\nTest 9: Should be:\n1 1");
        System.out.println( One.length() + " " + One);
        
        // Even if string or array has leading zeros, you just put them in, and they get
        // treated like any other leading zeros when you check length and print out. 
        
        System.out.println("\nTest 10: Should be:\n1");
        BigInt D = new BigInt();
        D.putValue("000000004");
        System.out.println( D.length() ); 
        
        System.out.println("\nTest 11: Should be:\n4");
        System.out.println( D ); 
        
        System.out.println("\nTest 12: Should be:\n3 234");
        BigInt E = new BigInt();
        int[] e = {0,0,0,0,2,3,4};
        E.putValue(e);
        System.out.println( E.length() + " " + E ); 
        
        System.out.println("\nTest 13: Should be:\n3 4 5");
        System.out.println( C.digitAt(0) + " " + C.digitAt(2) + " " + C.digitAt( C.length() - 1 ) );
        
        System.out.println("\nTest 14: Should be:\n1000001");
        BigInt F = new BigInt("1000001");
        System.out.println( F ); 
        
        System.out.println("\nTest 15: Should be:\n12345");
        int[] g = {1,2,3,4,5};
        BigInt G = new BigInt(g);
        System.out.println( G ); 
        
        System.out.println("\nTest 16: Should be:\n54321");
        System.out.println( new BigInt(54321) ); 
        
        System.out.println("\nTest 17: Should be:\n984159265");
        C.putDigitAt(0,9);
        C.putDigitAt(1,8);
        System.out.println( C );
        
        System.out.println("\nTest 18: Should be:\n984100005");
        C.putDigitAt(4,0);
        C.putDigitAt(5,0);
        C.putDigitAt(6,0);
        C.putDigitAt(7,0);
        System.out.println( C );
        
        System.out.println("\nTest 19: Should be:\n100005");
        C.putDigitAt(0,0);
        C.putDigitAt(0,0);
        C.putDigitAt(0,0);
        System.out.println( C );
        
        System.out.println("\nTest 20: Should be:\n5");
        C.putDigitAt(0,0);
        System.out.println( C );     
    }   
}