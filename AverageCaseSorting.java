import java.util.Random;
import java.awt.Color; 

/*
 * File: AverageCaseSorting.java
 * Date: 2/18/2016
 * Author: Linming Huang(huangl1@bu.edu)
 * Purpose: This class compares the speed of four sorting algorithms,
 *          insertion, merge, quick, and selection sort on a randomly
 *          generated array of integers.
 * Uses File: StdDraw.java, Sorting.java
 */
public class AverageCaseSorting {
    
    // way to differentiate the four sorting algorithms. 
    private enum SortingType {
        selectionSort,
        insertionSort,
        mergeSort,
        quickSort;
    }
    
    // sort the array using the sorting algorithm mention in type
    // and returns the number of comparisons made while sorting
    private static int runSorting(int[] array, SortingType type) {
        if(type == SortingType.selectionSort)
            Sorting.selectionSort(array);
        else if(type == SortingType.insertionSort)
            Sorting.insertionSort(array);
        else if(type == SortingType.mergeSort)
            Sorting.mergeSort(array);
        else if(type == SortingType.quickSort)
            Sorting.quickSort(array);
        
        return Sorting.counter;
    }
    
    // creates an array with n random int elements. Then, call the
    // overloaded method with the array and return its return value. 
    private static int runSorting(int n, SortingType type) {
        int[] array = new int[n];
        Random random = new Random();
        
        for(int i = 0; i < n; i++)
            array[i] = random.nextInt();
        
        return runSorting(array, type);
    }
    
    
    
    
    // Main method
    public static void main(String[] args) {
        int N = 100;
        
        double pointRadius = 0.005;
        double lineRadius = 0.001;
        
        // draws the graph
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 10 * N);
        StdDraw.setPenRadius(pointRadius);
        StdDraw.setPenColor(Color.black);
        StdDraw.line(0,0,0,10*N); 
        StdDraw.text(N,20,"" + N);
        StdDraw.line(0,0,N,0);
        StdDraw.text(5,10*N,"" + 10*N);
        
        double prevX, prevY;
        
        // draws f(N) = N ^ 2
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(80,350,"N^2");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = i*i;                         
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        // draws f(N) = insertion sort comparisons
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.text(80,300,"Insertion Sort");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = runSorting(i, SortingType.insertionSort);      
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        // draws f(N) = merge sort comparisons 
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.text(80,250,"Merge Sort");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = runSorting(i, SortingType.mergeSort);      
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        // draws f(N) = quick sort comparisons
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(80,200,"Quick Sort");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = runSorting(i, SortingType.quickSort);      
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        // draws f(N) = selection sort comparisons
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.text(80,150,"Selection Sort");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = runSorting(i, SortingType.selectionSort);      
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
              
        // draws f(N) = N
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.text(80,100,"N");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = i;                          
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
    }
    
}