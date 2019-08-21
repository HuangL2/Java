
/**
 * Class that changes the time in seconds into time in years, months, days,
 * hours, minutes, and seconds.
 * @author Linming Huang
 */

public class Age
{
    
    public static void main(String[] args)
    {
        int ageInSeconds = 1234567890;
        System.out.println();
        
        // change the time in seconds to years, months, and days
        System.out.printf("Age in years: %.2f\n",
                          ageInSeconds / (365 * 24 * 60 * 60.0));
        System.out.printf("Age in months: %.2f\n",
                          ageInSeconds / (30 * 24 * 60 * 60.0));
        System.out.printf("Age in days: %.2f\n\n",
                          ageInSeconds / (24 * 60 * 60.0));
        
        // int variable used to record the remaining time in seconds
        int remainingAge = ageInSeconds;
        
        // convert and prints out the time into years, months, days, hours,
        // minutes, and seconds.
        System.out.printf(
              "Age in years, months, days, hours, minutes, and seconds :\n" + 
              "\tYears: %d\n" + 
              "\tMonths: %d\n" + 
              "\tDays: %d\n" + 
              "\tHours: %d\n" + 
              "\tMinutes: %d\n" + 
              "\tSeconds: %d\n",
              remainingAge / (365 * 24 * 60 * 60),
              (remainingAge %= (365 * 24 * 60 * 60)) / (30 * 24 * 60 * 60),
              (remainingAge %= (30 * 24 * 60 * 60)) / (24 * 60 * 60),
              (remainingAge %= (24 * 60 * 60)) / (60 * 60),
              (remainingAge %= (60 * 60)) / 60,
              remainingAge %= 60);
        
        System.out.println();
    }
}