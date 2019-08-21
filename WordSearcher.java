import java.io.*;
import java.util.Scanner;

/**
 * File: TrieNode.java
 * Date: 4/20/2016
 * Author: Linming Huang
 * Purpose: A class can search up any English word
 * 
 * Others:
 * 1. each String would have to make the amount of bytes as its amount of
 *    characters
 * 2. we can save the bytes used to store the characters if the character
 *    had already been stored
 * 3. TreNode would take log(N) time while LinkedList would take N time
 * 4. I would have to use loops to iterate through the entire LinkedList 
 *    until the desire words are found. Then start from the beginning if
 *    there is a '?' or '*'. This will continue until the entire list is 
 *    literated through.
 */



public class WordSearcher {

    private static String processWord(String word) {
        char[] c = word.toCharArray();
        String s = "";
        for(int i = 0; i < c.length; i++) {
            if(!Character.isLowerCase(c[i]))
                c[i] = Character.toLowerCase(c[i]);
            if(!('a' <= c[i] && c[i] <= 'z'))
                c[i] = '_';
            s += (c[i] == '_')? "" : c[i];
        }
        return s;
    }
        
    
    public static void main(String[] args) {
        TrieNode n = new TrieNode();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
            
            String word = reader.readLine();
            while(word != null) {                
                n.add(processWord(word));
                word = reader.readLine();
            }
        } catch (IOException ex) {
            System.err.println("NOPE");
        }
        
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
                
        while (input.hasNextLine()) {
            String line = input.nextLine();
            n.match(line);
            System.out.print("Enter a string: ");
        }
    }
}