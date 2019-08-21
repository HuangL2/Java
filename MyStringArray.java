/**
 * Class that stimulates the String class library. The key differences are
 * all the methods of this class are static and char arrays are in place of
 * Strings.
 * @author Linming Huang
 */
public class MyStringArray 
{
    /****************************************************
      ********************F I E L D S********************
      ***************************************************/
    
    //Error variables. Used in place of Exceptions.
    public static final char[] ERROR_STRING = { 'N', 'a', 'S' };
    public static final char ERROR_CHARACTER = '#';
    public static final int ERROR_INTEGER = Integer.MIN_VALUE;
    public static final double ERROR_DOUBLE = Double.NaN;
    
    /****************************************************
      *******************M E T H O D S*******************
      ***************************************************/
    
    /**
     * Find the char value in char array s at index i
     * @param s the char array
     * @param i the index
     * @return if index is within array, return the char value at index. Else
     *         returns the ERROR_CHARACTER
     */
    public static char charAt(char[] s, int i) 
    {
        return (0 <= i && i < s.length)? s[i] : ERROR_CHARACTER;
    }
    
    /**
     * Find and return the length of a char array s
     * @param s the char array
     * @return the length in int of the char array s
     */
    public static int length(char[] s) 
    {
        return s.length;
    }
    
    /**
     * Returns a new char array that is a portion of the original array.
     * The new array begins a index l and ends at index r - 1
     * @param s the original array
     * @param l the beginning index, inclusive
     * @param r the ending index, exclusive
     * @return the new char array or ERROR_STRING if the beginning index
     *         is less than the ending index, the beginning index is less than 
     *         0 or the ending index is larger than the original array's length
     */
    public static char[] subString(char[] s, int l, int r)
    {
        if(r > l || l < 0 || r > s.length)
            return ERROR_STRING;
        
        char[] newArray = new char[r - l];
        for(int i = 0; i < newArray.length; i++)
            newArray[i] = s[i + l];
        
        return newArray;
    }
    
    /**
     * Returns a new char array that had converted all the char in the original
     * char array into a lowercase letter.
     * @param c the original char array
     * @return the new char array
     */
    public static char[] toLowerCase(char[] c)
    {
        char[] ca = new char[c.length];
        for(int i = 0; i < ca.length; i++) {
            char lowercase = c[i];
            if('A' <= lowercase && lowercase <= 'Z')
                lowercase += 32;
            ca[i] = lowercase;
        }
        return ca;
    }
    
    /**
     * Returns a new char array that merged the two original arrays
     * @param a the array that will be copied to the new array
     * @param b the array that will be copied to the new array after a is done
     * @return the new char array
     */
    public static char[] concatenate(char[] a, char[] b)
    {
        char[] ca = new char[a.length + b.length];
        for(int i = 0; i < ca.length; i ++)
            ca[i] = (i < a.length)? a[i] : b[i - a.length];
        
        return ca;
    }
    
    /**
     * Returns the int representation of the array
     * @param a the array
     * @return int representation of the array or ERROR_INTEGER if the array
     *         contains a char value that is not a number. The only exception 
     *         would be a negative sign at the beginning of the array
     */
    public static int intValueOf(char[] a) 
    {
        // the int representative of the char array
        int value = 0;
        
        // flag that shows whether or not the int representative is negative
        boolean isNegative = false;
        
        for(int i = 0; i < a.length; i++) {
            if('-' == a[0]) {
                isNegative = true;
                continue;
            }
            
            if('0' <= a[i] && a[i] <= '9') {
                // change the char into an int
                int k = a[i] - '0';
                value *= 10; // add a 0 to the end of the int 
                value += k;
            }
            else 
                return ERROR_INTEGER;
        }
        return value * ((isNegative)? -1 : 1);
    }
    
    /**
     * Returns the double representation of the array
     * @param a the array
     * @return double representation of the array or ERROR_DOUBLE if the array
     *         more than one dot sign or any other sign that is not a number. 
     *         The only exception of this rule negative sign at the beginning 
     *         of the array.
     */
    public static double doubleValueOf(char[] a) 
    {
        // leftNumber : int representive of the whole numbers
        // rightNumber : int representive of the decimal numbers
        // rightLength : length of the decimal numbers
        int leftNumber, rightNumber, rightLength;
        leftNumber = rightNumber = rightLength = 0;
        
        // flag that shows whether or not the double representive is negative
        boolean isNegative = false;
        // flag that shows whether or not a dot had appeared 
        boolean isWhole = true;
        
        for(int i = 0; i < a.length; i++) {
            
            if('-' == a[0]){
                isNegative = true;
                continue;
            }
            
            if('.' == a[i]) {
                if(!isWhole)
                    return ERROR_DOUBLE;
                isWhole = false;
                continue;
            }
            
            if('0' <= a[i] && a[i] <= '9') {
                // change the char into an int
                int k = a[i] - '0';
                if(isWhole) {
                    leftNumber *= 10; // add a 0 to the end of the int 
                    leftNumber += k;
                } else {
                    rightNumber *= 10; // add a 0 to the end of the int 
                    rightNumber += k;
                    rightLength ++;
                }
                
                
            } else return ERROR_DOUBLE;
        }
        
        // returns (-) leftNumber.rightNumber
        return (leftNumber + rightNumber / pow(10, rightLength)) *
            ((isNegative)? -1 : 1);
    }
    
    /**
     * Returns the char array representation of the int
     * @param n the int
     * @return char array representation of the int
     * 
     */
    public static char[] int2MyString(int n) 
    {
        boolean isNegative = (n < 0)? true : false; 
        int length = 0; // length of the array
        
        // find the number of digits in n
        for(int temp = n; temp != 0; temp /= 10)
            length ++;
        
        char[] c = new char[length + ((isNegative)? 1 : 0)];
        
        for(int i = 0; i < c.length; i ++) {
            if(i == 0 && isNegative)
                c[i] = '-';
            else {
                int num; // temp variable
                num = n / (int) pow(10, length - i - 1 + (isNegative? 1 : 0));
                
                // Num becomes the value of the last digit
                num %= 10;
                
                // Makes num positive so that double negativity will not occur
                if(isNegative)
                    num *= -1;
                
                c[i] = (char) (num + '0');
            }
        }
        
        return c;
    }
    
    /**
     * Returns the value of the base raised to the power of the exponent
     * @param base the base
     * @param exponenet the exponent
     * @return base to the power of the exponent
     */
    private static double pow(double base, int exponent) {
        if(exponent < 0)
            return ERROR_INTEGER;
        else if(exponent == 0)
            return 1;
        else {  
            double value = base;
            for(int i = exponent - 1; i > 0; i--) {
                value *= base;
            }
            
            
            return value;
        }
    }
    
    /**
     * Prints a char array
     * @param a the char array
     */
    public static void printCharArray(char[] a)
    {
        for(int i = 0; i < a.length; ++i) {
            System.out.print(a[i]);
        }
        System.out.println();
    }
    
    /****************************************************
      **************M A I N     M E T H O D**************
      ***************************************************/
    public static void main(String[] args)
    {
        System.out.println("\nGrading program for MyStringArray library\n");
        int testNum = 0; 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n8"); 
        char[] test = "CS112 A1".toCharArray(); 
        System.out.println(length(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\nC"); 
        System.out.println(charAt(test,0)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n1"); 
        System.out.println(charAt(test,7)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n#"); 
        System.out.println(charAt(test,9)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\nCS112"); 
        System.out.println(subString(test,0,5)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n12 A1"); 
        System.out.println(subString(test,3,8)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\nNaS"); 
        System.out.println(subString(test,-1,4)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\nNaS"); 
        System.out.println(subString(test,1,9)); 
        System.out.println();  
        
        System.out.println("Test " + (++testNum) + ": Should be:\ncs112 a1"); 
        System.out.println(toLowerCase(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\nCS112 A1"); 
        System.out.println(test); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\nCS112 A1CS112 A1"); 
        System.out.println(concatenate(test,test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\n234"); 
        test = "234".toCharArray(); 
        System.out.println(intValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n-234"); 
        test = "-234".toCharArray(); 
        System.out.println(intValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n-2147483648"); 
        test = "234.4".toCharArray(); 
        System.out.println(intValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n-2147483648"); 
        test = "23a4".toCharArray(); 
        System.out.println(intValueOf(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\n3.141592"); 
        test = "3.141592".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println();   
        
        System.out.println("Test " + (++testNum) + ": Should be:\n-3.141592"); 
        test = "-3.141592".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n10.0"); 
        test = "10.".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n0.5"); 
        test = ".5".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\n0.0"); 
        test = ".".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\n234.0"); 
        test = "234".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\nNaN"); 
        test = "3.141.592".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\nNaN"); 
        test = "3.141a592".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println(); 
        
        int n = 12345; 
        System.out.println("Test " + (++testNum) + ": Should be:\n12345"); 
        printCharArray( int2MyString(n) ); 
        System.out.println(); 
        
        n = -45; 
        System.out.println("Test " + (++testNum) + ": Should be:\n-45"); 
        printCharArray( int2MyString(n) ); 
        System.out.println(); 
        
    }
}