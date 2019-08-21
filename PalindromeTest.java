import java.util.Scanner;

/**
 * Class that checks if a sentence that the user inputted via a Scanner 
 * object is a palindrome or not
 */
public class PalindromeTest {
    
    public static void main(String[] args) { 
        //Welcome message from the system
        System.out.println("\nWelcome to PalidromeTest.java");
        System.out.println("Here I will test if a sentence is a palindrome" +
                           "or not!");
        
        //Define Scanner for user input
        Scanner sc = new Scanner(System.in);
        
        
        //Ask user for input
        System.out.println("\nType in a sentence or Control-d to end:");
        
        char[] charsToRemove = {'.', ',', ':', ';', '!', '?', 
            '"', '\'', '/', '-', '(', ')', '~'};
        
        while(sc.hasNextLine()) {
            String st = sc.nextLine();
            String temp = st.toLowerCase();
            
            // Remove all the chars in charasToRemove in the String
            for(int i = 0; i < charsToRemove.length; i++)
                temp = temp.replace(Character.toString(charsToRemove[i]), "");
            
            // Remove all white space in the String
            for(int i = temp.length() - 1; i >= 0; i--)
                if(Character.isWhitespace(temp.charAt(i)))
                temp = temp.substring(0, i) + 
                temp.substring(i + 1, temp.length());
            
            // stop the program if the user entered nothing
            if(temp.equals(""))
                break;
            
            // The left and right index
            int left = 0, right = temp.length() - 1;
            boolean isPalindrome = true;
            
            // Check if the letters at the left and right index are the same
            while(left < right && isPalindrome){
                if(!(temp.charAt(left) == temp.charAt(right)))
                    isPalindrome = false;
                left ++;
                right --;
            }
            
            System.out.println(isPalindrome? "Palindrome!" : 
                                   "Not a palindrome!");
        }
        
        // Ending message
        System.out.println("Bye!");
    } 
    
}
