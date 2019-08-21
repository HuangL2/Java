import java.util.Scanner;

/**
 * Class that uses java.util.Scanner to get user inputs.
 * Then with the user inputs, calculate the sum, max, min, range, mean, 
 * variance, standard deviation, and median.
 */ 
public class Statistics 
{
    //used so that the scanner object does not need to be passed into
    //other methods besides the it is declared in
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        // Hi from the system.
        System.out.println("\nWelcome, dear user to Statistics.java");
        System.out.println("I will calcuate the sum, maximum, minimum, " +
                           "range, mean, variance, standard deviation, " + 
                           "and median of the numbers you type in.");
        
        // Where the input and calculations occurs.
        calculate();
        
        // Bye from the system.
        System.out.println("\nThank you for using Statistics.java, " + 
                           "I hope to see you again soon!!!\n");
        
    }
    
    /**
     * Method used to ask for user input and calculates them.
     */
    private static void calculate()
    {
        // Ask for how long should the array be
        int number = getInputInt("\nPlease type how many " +
                                 "numbers will I calculate and " + 
                                 "then hit the return key:");
        
        // Makes sure that the user is not playing around
        int[] a;
        if(number > 0)
            a = new int[number];
        else {
            if(getInputBoolean("Please enter only a natural number\n" +
                               "Do you wish to retry?",
                               "Any other key will terminate the program."))
                calculate();
            return;
        }
        
        // To allow system to say things like 1st, 2nd, 3rd, and so on        
        for(int i = 0; i < a.length; ++i)
        {
            String slot;
            if(i % 10 == 0 && i / 10 != 1)
                slot = (i + 1) + "st";
            else if(i % 10 == 1 && i / 10 != 1)
                slot = (i + 1) + "nd";
            else if(i % 10 == 2 && i / 10 != 1)
                slot = (i + 1) + "rd";
            else 
                slot = (i + 1) + "th";
            
            // Ask user to enter the number they want.
            a[i] = getInputInt("\nPlease type your " + slot + " number " + 
                               "and hit the return key:",
                               "any other key will result in your " + slot + " number be a zero");
        }
        
        // Allows user to confirm if any mistakes were made.
        confirmArray(a);
        
        // Cast int[] into double[]
        double[] doubleA = new double[a.length];
        for(int i = 0; i < doubleA.length; ++i)
            doubleA[i] = a[i];
        
        // Print out the calculations
        System.out.println("\nUnderstood, calculating ... ... ... ... ... ...");
        System.out.println("The sum is " + sum(a));
        System.out.println("The max is " + max(a));
        System.out.println("The min is " + min(a));
        System.out.println("The range is " + range(a));
        System.out.printf("The mean is %.2f\n", mean(doubleA));
        System.out.printf("The variance is %.2f\n", variance(doubleA));
        System.out.printf("The standard deviation is %.2f\n", standardDeviation(doubleA));
        System.out.println("The median is " + median(a));
        
        // Ask the user if wants to reuse.
        System.out.println("\nThank you for using Statistics.java!\n");
        if(getInputBoolean("\nDo you wish to enter another set of numbers?",
                           "Any other key will result in the program quitting"))
            calculate();
    }
    
    /**
     * Method used to allow user to confirm the inputs before the calculations
     */ 
    private static void confirmArray(int[] a)
    {
        // Shows user the array that was inputted
        System.out.println("\nThe number you have inputted is ...");
        System.out.println(intArrayToString(a));
        
        // Ask user if everything is okay.
        if(getInputBoolean("\nAre you sure this is what you wanted?",
                           "Any other key will result in no"))
            return;
        
        // If the user decides that something is wrong, ask the user what is.
        int change = getInputInt("\nWhich slot value do you want to change?");    
        
        // Ask the user what the correct be and makes sure that 
        // the user is not playing around
        if(change >= 0 && change < a.length) {
            a[change] = getInputInt("\nWhat number do you want it to change to?",
                                    "Any other key will result in the same number.");
          // asks user if wants a retry
        } else if (getInputBoolean("\nI'm sorry but slot number " + change + 
                                   " does not exist, do you wish to retry?",
                                   "Any other key will result in a no.")){
            confirmArray(a);
        }
        
        // ask user if there is another mistake
        if (getInputBoolean("\nIs there another error you wish to change?",
                            "Any other key will result in a no."));
            confirmArray(a);
    }
    
    /**
     * Gets the sum.
     */
    private static int sum(int[] a)
    {
        int sum = 0;
        for(int i = 0; i < a.length; ++i)
            sum += (int)a[i];
        return sum;
    }
    
    /**
     * Using Math.max, get the max
     */ 
    private static int max(int[] a)
    {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < a.length; ++i)
            max = Math.max(max, (int)a[i]);
        return max;
    }
    
    /**
     * Using Math.min, get the min
     */ 
    private static int min(int[] a)
    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < a.length; ++i)
            min = Math.min(min, (int)a[i]);
        return min;
    }
    
    /**
     * Get the range by subracting max by min
     */
    private static int range(int[] a)
    {
        return max(a) - min(a);
    }
    
    /**
     * Get the mean
     */ 
    private static double mean(double[] a)
    {
        int[] intA = new int[a.length];
        for(int i = 0; i < intA.length; i ++)
            intA[i] = (int) a[i];
        
        return sum(intA) / (double)a.length;
    }
    
    /**
     * Get the variance
     */
    private static double variance(double[] a)
    {
        double mean = mean(a);
        double[] newA= new double[a.length];
        
        for(int i = 0; i < newA.length; ++i)
            newA[i] = square(a[i] - mean);
        
        return mean(newA);        
    }
    
    /**
     * Get the standard Ddeviation
     */
    private static double standardDeviation(double[] a)
    {
        return Math.sqrt(variance(a));
    }
    
    /**
     * Get the median
     */
    private static int median(int[] a)
    {
        // sort the array
        insertionSort(a);
        
        // return the middle number
        return a[a.length/2];
    }
    
    /**
     * Using insertion sort, sort the array. Used in finding median
     */ 
    private static void insertionSort(int[] a)
    {
        for(int i = 1; i < a.length; ++i){
            int temp = a[i];
            int k;
            
            for(k = i - 1; k >= 0 && temp < a[k]; --k)
                a[k + 1] = a[k];
            a[k + 1] = temp;
        }
    }
    
    /**
     * Square the base. Used in find the variance
     */ 
    private static double square(double base)
    {
        return base * base;
    }
    
    /**
     * Ask the user for an input that is an integer
     * If anything else is inputeed, an error message will appear
     * and will ask the user something else
     * 
     * @param message Tells the user what the integer input will be used for
     * @param illegalQuestion If the user does not input an integer, will ask 
     * the user this question. A 'Y' key press will be yes and any other key
     * press will be no.
     * @param illegalOtherKeyPress Tells the user what is goning to happening
     * if no is the answer
     */
    private static int getInputInt(String message,
                                   String illegalQuestion,
                                   String illegalOtherKeyPress) {
        // to catch the error in case the user input something illegal
        try {
            System.out.println(message);
            
            int input = scanner.nextInt();
            
            return input;
        } catch (java.util.InputMismatchException ex) {
            // so that the illegal vaule inputted will not be put into use
            scanner.next();
            
            // tells the user that a mistake had been made
            System.out.println("\nYou have entered an illegal value.");
            
            // if the user wishes, he or she may enter a different value
            if( getInputBoolean(illegalQuestion, illegalOtherKeyPress) )
                return getInputInt(message, illegalQuestion, illegalOtherKeyPress);
        }
        
        // If the user inputted an illegal value and does not wish to 
        // change what was inputted, the default, zero will be used instead.
        return 0;
    }
    
    /**
     * Overloaded method in case the illegalOtherKeyPress parameter is not 
     * the default one but the illegalQuestion parameter is.
     */
    private static int getInputInt(String message,
                                   String illegalOtherKeyPress) {
        return getInputInt(message,
                           "\nDo you wish to retry?", 
                           illegalOtherKeyPress);
    }
    
    /**
     * Overloaded method in case both the illegalOtherKeyPress and illegalQuestion
     * parameter is the default one
     */
    private static int getInputInt(String message)
    {
        return getInputInt(message, 
                           "\nDo you wish to retry?", 
                           "Any other key will result in the program quitting:");
    }
    
    /**
     * Ask the user a question to which the user can either input 'Y' for yes
     * or any other key for no
     * 
     * @param question The question that will be asked
     * @param otherKeyPress informs the user what will happen if no is answered
     */
    private static boolean getInputBoolean(String question, String otherKeyPress)
    {
        System.out.println(question);
        System.out.println("Type \"Y\", ignoring quotation marks, if yes " +
                           "and then hit the return key. " + otherKeyPress);
        
        String s = scanner.next();
        
        // allows the to accept both 'y' and 'Y'
        if(s.equalsIgnoreCase("Y"))
            return true;
        return false;
    }
    
    /**
     * A toString method for array.
     * Method used for debugging and to tell user the numbers that had been
     * inputted in the confirmArray method. 
     */
    private static String intArrayToString(int[] array)
    {
        String s = "{ ";
        for(int i = 0; i < array.length - 1; ++i)
            s += array[i] + ", ";
        s += array[array.length - 1] + " }";
        
        return s;
    }
}