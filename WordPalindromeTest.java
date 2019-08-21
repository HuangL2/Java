import java.util.Scanner;


public class WordPalindromeTest {    
    
    public static void main(String[] args) { 
        //Welcome message from the system
        
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
            
            // Split the String into subStrings by spaces
            String[] sa = temp.split("\\s+");
            
            // The left and right index
            int left = 0, right = sa.length - 1;
            boolean isPalindrome = true;
            
            // Check if the words at the left and right index are the same
            while(left < right && isPalindrome){
                if(!(sa[left].equals(sa[right])))
                    isPalindrome = false;
                left ++;
                right --;
            }
            
            System.out.println(isPalindrome? "Palindrome!" : "Not a palindrome!");
        }        
        // Ending message
        System.out.println("Bye!");
    } 
}
