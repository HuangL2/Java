
/**
 * Class that prints out a poem.
 * @author Linming Huang
 */

public class Haiku
{
 
    public static void main(String[] args)
    {
        
        String author = "Jack Kerouac";
        int year = 1968;
        
        System.out.println(String.format( "\n" +
              "\tArms folded\n" + 
              "\tTo the moon\n" +
              "\tAmond the cows.\n" +
              "\t\t-%s (%d)\n",
              author, year));
    }
}