import java.util.*;

/**
 * Class that accepts doubles between 0 and 100 from user input using a
 * Scanner and then prints out the inputs using a histogram
 * @author Linming Huang
 */
public class Histogram 
{
    /****************************************************
      ********************F I E L D S********************
      ***************************************************/
    
    // Number of bins and its respective size
    private static int BIN_NUM = 10;
    private static double BIN_SIZE = 100.0 / BIN_NUM;
    
    // Maximum amount of numbers the user can input
    private static int MAX_NUM = 20;
    
    /****************************************************
      **************M A I N     M E T H O D**************
      ***************************************************/
    public static void main(String[] args)
    {
        // Creates a new Scanner for user input
        Scanner sc = new Scanner(System.in);
        
        // Prints the welcome message
        printHeading();
        
        // Generate a double array for user input storage
        double[] numbers = new double[MAX_NUM];
        
        // Ask user for inputs, up to a maximum of MAX_NUM
        for(int i = 0; i < MAX_NUM; i++) {
            // store user input in a variable to check if user had already
            // pressed control-d. If control-d was pressed, getUserInput(sc) 
            // would return -1
            double temp = getUserInput(sc);
            
            if(temp != -1)
                numbers[i] = temp;
            
            // If control-d was pressed or if user had already inputted
            // the maximum input allowed.
            if(temp == -1 || i == numbers.length - 1) {
                // Create a new array with length depending on how many
                // numbers the user inputted. The new array is used for the 
                // histogram. This is used so that the histogram will not
                // mistake the 0.0(s) in the old array in case the user 
                // decides to terminate inputing early
                    double[] tempArray = numbers;
                numbers = new double[i + 1];
                
                // Copy all the inputted value to the new array
                for(int k = 0; k < i + 1; k++)
                    numbers[k] = tempArray[k];
                
                break;
            }
        }
        
        // Sort the numbers the user inputted into a histogram.
        int[] histograms = new int[BIN_NUM];
        for(int i = 0; i < numbers.length; i++) {
            for(int k = 0; k < histograms.length; k++) {
                if (numbers[i] <= (k + 1) * BIN_SIZE) {
                    histograms[k] ++;
                    break;
                }
            }
        }
        
        // Print out the numbers that the user inputted followed by 
        // a histogram it.
        System.out.println("The numbers you have inputted is ...");
        System.out.println(doubleArrayToString(numbers));
        printHistogram(histograms);
    }
    
    /****************************************************
      *******************M E T H O D S*******************
      ***************************************************/
    
    /**
     * Gets and checks if the user input is valid. If the input is
     * invalid, tells the user why and gets and checks the input again.
     * @param sc Scanner object used to gets the input
     * @return the validated user input or -1 if user pressed control-d
     */
    public static double getUserInput(Scanner sc)
    {
        double d;
        try {
            d = sc.nextDouble();
            
            if(validInput(d))
                return d;
            else System.out.println("Please only enter numbers between" +
                                    "0 and 100");
        } catch (InputMismatchException e) {
            sc.next();
            System.out.println("Please enter a double");
        } catch (NoSuchElementException e) {
            return -1;
        }
        
        return getUserInput(sc);
    }
    
    /**
     * Checks if user input is between 0 and 100, inclusive
     * @param d the user input
     * @return true if 0 <= d <= 100. False if otherwise
     */
    public static boolean validInput(double d)
    {
        if(0 <= d && d <= 100)
            return true;
        return false;
    }
    
    /**
     * Prints the welcome message
     */
    public static void printHeading() 
    {
        System.out.println("\nWelcome to Histogram.java");
        System.out.println("Please input up to 20 doubles.");
        System.out.println("You may press control-d to stop the input:");
    }
    
    /**
     * Prints the histogram
     * @param histogram the histogram represented in an int array
     */
    public static void printHistogram(int[] histogram) 
    {
        for(int i = 0; i < histogram.length; i++) {
            String s = "";
            s += (i == 0)? "[" : "(";
            s += String.format("%.1f..%.1f]:\t",
                               i * BIN_SIZE,
                               (i + 1) * BIN_SIZE);
            for(int k = 0; k < histogram[i]; k++)
                s += "*";
            
            System.out.println(s);
        }
    }
    
    /**
     * Converts a double array to String. Used for printing the double array.
     * @param array the double array that will be converted to a String
     * @return a String that represent the double array
     */
    private static String doubleArrayToString(double[] array)
    {
        if(array.length == 0)
            return "";
        
        String s = "{ ";
        for(int i = 0; i < array.length - 1; ++i)
            s += array[i] + ", ";
        s += array[array.length - 1] + " }";
        
        return s;
    }
}