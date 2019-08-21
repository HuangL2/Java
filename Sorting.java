/*
 * File: Sorting.java
 * Date: 2/18/2016
 * Author: Linming Huang (huangl1@bu.edu)
 * Purpose: class that contains the four sorting algorithm,
 *          insertion, selection, merge, and quick sort
 */
public class Sorting {
    
    // number of comparisons made
    public static int counter;
    
    
    // sort using insertion sort
    public static void insertionSort(int[] a) {
        counter = 0;
        for(int i = 1; i < a.length; i++) {
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
                swap(a, j, j-1);
        }
    }
    
    
    // sort using selection sort
    public static void selectionSort(int[] a) {
        counter = 0;
        for(int i = 0; i < a.length; i++) {
            int min = i;
            for(int j = i+1; j < a.length; j++) {
                if(less(a[j], a[min])) 
                    min = j;
            }
            swap(a, i, min);
        }
    }
    
    
    // sort using merge sort
    public static void mergeSort(int[] a) {
        counter = 0;
        int[] aux = new int[a.length];
        msHelper(a, aux, 0, a.length - 1);
    }
    
    
    // helper method for merge sort that seperates the array
    private static void msHelper(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) 
            return;
        int mid = lo + (hi - lo) / 2;
        msHelper(a, aux, lo, mid);
        msHelper(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
    
    // helper method for merge sort that merges the array
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for(int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid)
                a[k] = aux[j++];
            else if(j > hi)
                a[k] = aux[i++];
            else if(less(aux[j], aux[i])) 
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    
    
    // sort using quick sort
    public static void quickSort(int[] a) {
        counter = 0;
        qsHelper(a, 0, a.length - 1);
    }    

    
    // helper method for quick sort
    private static void qsHelper(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi;
        int pivot = a[lo + (hi - lo)/2];
        
        while(i <= j) {
            while(less(a[i], pivot))
                i++;
            while(less(pivot, a[j]))
                j--;
            if(i <= j) {
                swap(a, i, j);
                i ++;
                j --;
            }
        }
        if(lo < j)
            qsHelper(a, lo, j);
        if(i < hi)
            qsHelper(a, i, hi);
    }
    
    
    // swaps two elements at the stated indicies
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    
    // compare the two elements
    // adds 1 to the comparison counter
    private static boolean less(int a, int b) {
        counter ++;
        return a < b;
    }
    
    
    // toString method for int arrays
    public static String intArrayToString(int[] array)
    {
        String s = "[ ";
        for(int i = 0; i < array.length - 1; ++i)
            s += array[i] + ", ";
        s += array[array.length - 1] + " ]";
        
        return s;
    }
    
    
    
    
    // Main method
    public static void main(String[] args) { 
        int[] original = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        
        int[] a, b, c, d;
        a = original.clone();
        b = original.clone();
        c = original.clone();
        d = original.clone();
        
        System.out.println("\nA before:\t" + intArrayToString(a));
        selectionSort(a);
        System.out.println("A after:\t" + intArrayToString(a));
        System.out.printf("\nselectionSort(A) took %d comparison\n", counter);
        
        System.out.println("\nB before:\t" + intArrayToString(b));        
        insertionSort(b);
        System.out.println("B after:\t" + intArrayToString(b));
        System.out.printf("\ninsertionSort(B) took %d comparison\n", counter);
        
        System.out.println("\nC before:\t" + intArrayToString(c));
        mergeSort(c);
        System.out.println("C after:\t" + intArrayToString(c));
        System.out.printf("\nmergeSort(C) took %d comparison\n", counter);
        
        System.out.println("\nD before:\t" + intArrayToString(d));
        quickSort(d);
        System.out.println("D before:\t" + intArrayToString(d));
        System.out.printf("\nquickSort(D) took %d comparison\n", counter);
    }
    
}
