/*
 * File: BigMath.java
 * Date: 2/18/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: a class that does basic mathematical operations, addition and
 *          multiplication, on BigInt using BigIntStack
 * Uses Files: BigInt.java, BigIntStack.java
 */
public class BigMath {
    
    // default length of a BigInt
    public static final int SIZE = 50;
    
    
    // compares the two elements. 
    // Returns -1 if n < m.
    // Returns 1 if n > m.
    // Returns 0 if n = m
    public static int compare(BigInt n, BigInt m) {
        if(n.length() < m.length())
            return -1;
        else if(n.length() > m.length())
            return 1;
        
        for(int i = 0; i < n.length(); i++) {
            if(n.digitAt(i) < m.digitAt(i))
                return -1;
            else if(n.digitAt(i) > m.digitAt(i))
                return 1;
        }
        
        return 0;
    }
    
    
    // returns true if n is equal to m
    public static boolean equals(BigInt n, BigInt m) {
        return compare(n, m) == 0;
    }
    
    
    // adds the value of n and m and returns it.
    public static BigInt add(BigInt n, BigInt m) {
        // stacks to store the values of n and m
        IntStack sa = new IntStack(n.length());
        IntStack sb = new IntStack(m.length());
        
        // stack to store the value of n + m
        IntStack ss = new IntStack(SIZE);
        
        // initalize the stacks that stores the values of n and m
        for(int i = 0; i < n.length(); i++)
            sa.push(n.digitAt(i));
        for(int i = 0; i < m.length(); i++)
            sb.push(m.digitAt(i));
        
        // variable used to store the extra value of the next digit 
        int carry = 0;
        
        while(!sa.isEmpty() || !sb.isEmpty()) {
            // get the next value of sa and sb, or 0 if its empty
            int i = sa.isEmpty()? 0 : sa.pop(); 
            int j = sb.isEmpty()? 0 : sb.pop();
            
            // k is the value of the current digit
            int k = i + j + carry;
            carry = 0;
            
            if(k / 10 != 0) {
                carry = k / 10;
                k %= 10;
            }
            ss.push(k); // stores the value of k to ss
            
            
            // in case sa and sb is both empty but there is still another
            // digit due to the carried over number
            if(carry > 0 && sa.isEmpty() && sb.isEmpty())
                ss.push(carry);
        }
        
        // make an int array of size SIZE and copies the value of ss to it. 
        int[] a = new int[SIZE];
        for(int i = a.length - ss.size(); i < a.length; i++) {
            a[i] = ss.pop();
        }
        
        return new BigInt(a);
    }
    
    
    // multiplies the values of n and m. 
    public static BigInt multByInt(BigInt n, int m) {
        // stack to store the value of n
        IntStack sa = new IntStack(n.length());
        
        // stack to store the value of n * m
        IntStack ss = new IntStack(SIZE);
        
        // initalizes sa
        for(int i = 0; i < n.length(); i++)
            sa.push(n.digitAt(i));
        
        // variable used to store the extra value of the next digit 
        int carry = 0;
        
        
        while(!sa.isEmpty()) {
            // get the value of sa and multiply it by m
            int k = sa.pop() * m + carry;
            carry = 0;
            
            // makes sure that k is a digit
            if(k / 10 != 0) {
                carry = k / 10;
                k %= 10;
            }
            ss.push(k); // stores the value of k to ss
            
            // in case sa is empty but there is still more digits due to 
            // the carried over number
            while(carry > 0 && sa.isEmpty()) {
                ss.push(carry % 10);
                carry /= 10;
            }
        }
        
        // make an int array of size SIZE and copies the value of ss to it
        int[] a = new int[SIZE];
        for(int i = a.length - ss.size(); i < a.length; i++) {
            a[i] = ss.isEmpty()? 0 : ss.pop();
        }
        
        return new BigInt(a);
    }
    
    
    // multiplies the value of n and m
    public static BigInt mult(BigInt n, BigInt m) {
        // stack to store the value of m
        IntStack sb = new IntStack(m.length());
        
        // stack to store the values of n * m
        BigIntStack ss = new BigIntStack(SIZE);
        
        // initializes sb
        for(int i = 0; i < m.length(); i++)
            sb.push(m.digitAt(i));
        
        // multiplies n by each value of m
        while(!sb.isEmpty())
            ss.push(multByInt(n, sb.pop()));

        // value is the result of the multiplication
        BigInt value = new BigInt();
        
        // variable to represent the starting size of ss 
        int ssSize = ss.size(); 
        for(int i = 0; i < ssSize; i++) {
            BigInt bi = ss.pop();
            
            // adds 0 to the end of the BigInt that corresponds to its digit
            for(int j = ssSize - i - 1; j > 0; j--)
                bi.putValue(bi.toString() + "0");
            
            value = add(value, bi);
        }
        
        return value;
    }
    
    
    
    
    
    // Main method
    public static void main(String [] args) {
        
        System.out.println("\nUnit Test for BigMath Class");
        
        BigInt A = new BigInt("9999");
        
        BigInt B = new BigInt(1);
        
        int[] c = {1,8,2,7};
        BigInt C = new BigInt(c);
        
        BigInt D = new BigInt(234);
        BigInt E = new BigInt(235);
        BigInt F = new BigInt(9999);
        BigInt Z = new BigInt(0);
        
        System.out.println("\nTest 1: Should be:\n4 9999");
        System.out.println( A.length() + " " + A );
        
        System.out.println("\nTest 2: Should be:\n1 1");
        System.out.println( B.length() + " " + B );
        
        System.out.println("\nTest 3: Should be:\n4 1827");
        System.out.println( C.length() + " " + C );
        
        System.out.println("\nTest 4: Should be:\n3 234");
        System.out.println( D.length() + " " + D );
        
        System.out.println("\nTest 5: Should be:\n-1");
        System.out.println( compare(D,E) );
        
        System.out.println("\nTest 6: Should be:\n1");
        System.out.println( compare(E,D) );
        
        System.out.println("\nTest 7: Should be:\n1");
        System.out.println( compare(C,D) );
        
        System.out.println("\nTest 8: Should be:\n-1");
        System.out.println( compare(D,C) );
        
        System.out.println("\nTest 9: Should be:\n1");
        System.out.println( compare(A,Z) );
        
        System.out.println("\nTest 10: Should be:\n-1");
        System.out.println( compare(Z,A) );
        
        BigInt G = new BigInt(9999);        
        System.out.println("\nTest 11: Should be:\n0 true");
        System.out.println( compare(A,G) + " " + equals(A,G) );
        
        System.out.println("\nTest 12: Should be:\n-1 false");
        System.out.println( compare(E,F) + " " + equals(F,E) );
        
        System.out.println("\nTest 13: Should be:\n2");
        System.out.println( add(B,B) );
        
        System.out.println("\nTest 14: Should be:\n1827");
        System.out.println( add(C,Z) );
        
        System.out.println("\nTest 15: Should be:\n1827");
        System.out.println( add(Z,C) );
        
        System.out.println("\nTest 16: Should be:\n0");
        System.out.println( add(Z,Z) );
        
        System.out.println("\nTest 17: Should be:\n10000");
        System.out.println( add(A,B) );
        
        System.out.println("\nTest 18: Should be:\n10000");
        System.out.println( add(B,A) );
        
        System.out.println("\nTest 19: Should be:\n2061");
        System.out.println( add(C,D) );
        
        System.out.println("\nTest 20: Should be:\n2061");
        System.out.println( add(D,C) );
        
        System.out.println("\nTest 21: Should be:\n469");
        System.out.println( add(E,D) );
        
        System.out.println("\nTest 22: Should be:\n469");
        System.out.println( add(D,E) );  
        
        System.out.println("\nTest 23: Should be:\n1827");
        System.out.println( multByInt(C,1) ); 
        
        System.out.println("\nTest 24: Should be:\n3654");
        System.out.println( multByInt(C,2) );
        
        System.out.println("\nTest 25: Should be:\n0");
        System.out.println( multByInt(Z,8) );
        
        System.out.println("\nTest 26: Should be:\n99990");
        System.out.println( multByInt(A,10) );
        
        System.out.println("\nTest 27: Should be:\n4");
        BigInt Two = new BigInt(2); 
        System.out.println( mult(Two,Two) );
        
        System.out.println("\nTest 28: Should be:\n468  468");
        System.out.println( mult(D,Two) + "  " + mult(Two,D));
        
        System.out.println("\nTest 29: Should be:\n54990  54990");
        System.out.println( mult(D,E) + "  " + mult(E,D));
        
        System.out.println("\nTest 30: Should be:\n2339766  2339766");
        System.out.println( mult(D,A) + "  " + mult(A,D));
        
        System.out.println("\nTest 31: Should be:\n1013459064417062778220931703313214582990003217000");
        BigInt T = mult(A, mult( B, mult( C, mult( D, mult ( E, F ) ) ) ) ); 
        System.out.println( mult( T, mult( T, T ) ) );
    }
}