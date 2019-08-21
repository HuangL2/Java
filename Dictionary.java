import java.util.Arrays;
/**
 * File: Dictionary.java
 * Date: 3/2/2016
 * Author: Linming Huang (huangl1@bu.edu
 * Purpose: a class that represents a Dictionary using inner class to
 *          represent each element and an array to store them
 * Throws: KeyNotFoundException
 */
public class Dictionary {
    
    // current length of the array
    private int length = 10;
    // number of valid elements in the array
    private int size = 0;
    
    // the array of Pairs 
    private Pair[] pairs = new Pair[length];
    // location of next empty element in the array
    private int next = 0;
    
    // variables used for iterations
    private int nextPair = 0;
    private int nextStudent = 0;
    private String className;
    
    
    // insert a new Pair into the array if it does not exist already
    // the new Pair will be put in an ascending order
    public void insertStudent(String key, String[] value) {
        if(location(key, 0, next - 1) != -1)
            return;
        
        int index = 0;
        while(index < next && key.compareTo(pairs[index].key) > 0)
            index ++;
        
        for(int i = next; i > index; i--)
            put(pairs[i - 1], i);
        pairs[index] = new Pair(key, value);
        
        next ++;
        size ++;
    }
    
    
    // gets and returns the values of the Pair with the specified key
    // throws KeyNotFoundException if the specified key is not found
    public String[] lookupClasses(String key) throws KeyNotFoundException {
        int lo = 0;
        int hi = next - 1;
        int mid = hi / 2;
        
        while(lo <= hi) {
            int compare = key.compareTo(pairs[mid].key);
            if(compare < 0)
                hi = mid - 1;
            else if(compare > 0)
                lo = mid + 1;
            else 
                return pairs[mid].value;
            
            mid = lo + (hi - lo) / 2;
        }
        
        throw new KeyNotFoundException();
    }
    
    
    // returns true if the specified key is in the array
    public boolean member(String key) {
        try {
            lookupClasses(key);
            return true;
        } catch (KeyNotFoundException ex) {
            return false;
        }
    }
    
    
    // remove the Pair with the specified key from the array
    // if the specified key is not in the array, do nothing
    // all Pairs larger than the removed Pair will be shifted down once
    public void deleteStudent(String key) {
        int index = location(key, 0, next - 1);
        
        if(index != -1) {
            for(int i = index; i < next - 1; i++)
                put(pairs[i + 1], i);
            pairs[next - 1] = null;
            
            next --;
            size --;
        }
    }
    
    
    // removes the specified value from the Pair with the specified key
    // if the specified key is not in the array, or the specified value
    //    is not within the Pair, then do nothing
    public void dropClass(String key, String value) {
        int index = location(key, 0, next - 1);
        
        if(index != -1) {            
            String[] values = pairs[index].value;
            
            int stringIndex = -1;
            for(int i = 0; i < values.length; i++) {
                if(value.equals(values[i])) {
                    stringIndex = i;
                    break;
                }
            }
            
            if(stringIndex != -1) {
                String[] temp = values;
                values = new String[temp.length - 1];
                
                if(values.length == 0)
                    return;
                
                boolean isSkipped = false;
                for(int i = 0; i < temp.length; i++) {
                    if(i == stringIndex) {
                        isSkipped = true;
                        continue;
                    }
                    
                    values[i - (isSkipped? 1 : 0)] = temp[i];
                }
                pairs[index].value = values;
            }
        }
    }
    
    // adds the specified value to the Pair with the specified key
    // if the specified key is not in the array, then create and add a new
    //    pair with the specified key and value
    // if the specified value is in the Pair, then do nothing
    public void addClass(String key, String value) {
        int index = location(key, 0, next - 1);
        
        if(index != -1) {
            String[] values = pairs[index].value;
            
            if(!memberArray(value, values)) {
                String[] temp = values;
                values = new String[temp.length + 1];
                
                for(int i = 0; i < temp.length; i++)
                    values[i] = temp[i];
                values[values.length - 1] = value;
                
                pairs[index].value = values;
            }
        } else {
            insertStudent(key, new String[] {value});
        }
    }
    
    
    // returns true if the specified key is in the array
    // and if the specified value is in the Pair
    public boolean enrolled(String key, String value) {
        int index = location(key, 0, next - 1);
        
        if(index != -1 && memberArray(value, pairs[index].value))
            return true;
        return false;
    }
    
    
    // returns the number of valid Pairs in the array
    public int size() {
        return size;
    }
    
    
    // returns true if there are no valid Pairs in the array
    public boolean isEmpty() {
        return size == 0;
    }
    
    
    // helper method that gets the index of the specified key
    // returns the index if the key is in the array, or -1 if not
    private int location(String key, int lo, int hi) {
        if(size == 0)
            return -1;
        else if(lo > hi)
            return -1;
        
        int mid = lo + ((hi - lo) / 2);
        int compare = key.compareTo(pairs[mid].key);
        
        if(compare == 0)
            return mid;
        else if (compare < 0)
            return location(key, lo, mid - 1);
        else
            return location(key, mid + 1, hi);
    }
    
    
    // helper method that returns ture if the specified String is in the array
    private boolean memberArray(String key, String[] value) {
        for(int i = 0; i < value.length; i++) {
            if(key.equals(value[i]))
                return true;
        }
        return false;
    }
    
    // helper method that puts the specified Pair at the specified index
    // and increase the size of the array if neccessary
    private void put(Pair pair, int index) {
        if(index >= length)
            resize();
        pairs[index] = pair;
    }
    
    // helper method to double the size of the array
    private void resize() {
        length *= 2;
        Pair[] temp = pairs;
        pairs = new Pair[length];
        
        for(int i = 0; i < temp.length; i++)
            pairs[i] = temp[i];
    }
    
    
    // initialize the iterator for Pairs
    public void initPairIterator() {
        nextPair = 0;
    }
    
    // check if there is another Pair that hasn't been iterated
    public boolean hasNextPair() {
        return nextPair != next;
    }
    
    // gets and return the next Pair that hasn't been iterated
    public String nextPair() {
        return pairs[nextPair++].toString();
    }
    
    
    // initialize the iterator for Pairs with the specified value
    public void initStudentIterator(String className) {
        this.className = className;
        nextStudent = -2;
    }
    
    // check if there is another Pair with the specified value that
    // hasn't been iterated
    public boolean hasNextStudent() {
        if(nextStudent == -1)
            return false;
        else if(nextStudent == -2) {
            for(int i = 0; i < pairs.length; i++) {
                if(memberArray(className, pairs[i].value)) {
                    nextStudent = i;
                    return true;
                }
            }
            nextStudent = -1;
            return false;
        } else {
            for(int i = nextStudent + 1; i < size; i++) {
                if(memberArray(className, pairs[i].value)) {
                    nextStudent = i;
                    return true;
                }
            }
            nextStudent = -1;
            return false;
        }
    }
    
    // gets and return the next Pair with the specified value that hasn't
    // been iterated
    public String nextStudent() {
        return pairs[nextStudent].toString();
    }
    
    
    // method used to print the array
    public void printDictionary() {
        for(int i = 0; i < next; i++)
            System.out.println(i + ": " + pairs[i]);
    }
    
    
    // inner class that represent an element in the array
    private class Pair {
        // name of the element
        String key;
        // values stored in the element
        String[] value;
        
        
        // default constructor
        // both key and value are null
        public Pair() {
            this(null, null);
        }
        
        // constructor
        // both key and value are as specified
        public Pair(String key, String[] value) {
            this.key = key;
            this.value = value;
        }
        
        
        // toString method for the Pair
        public String toString() {
            String s = key + ": [";
            for(int i = 0; i < value.length - 1; i++)
                s += value[i] + ",";
            s += value[value.length - 1] + "]";
            
            return s;
        }   
    }
    
    
    // Exception thrown when trying to get a Pair that is not in the Array
    class KeyNotFoundException extends Exception {
    }
    
    
    
    
    
    // Main Method
    public static void main(String[] args) {
        
        Dictionary D = new Dictionary(); 
        
        // Insert three (key,value) pairs and test basic methods
        
        String[] A = { "CS111", "CS131", "WR99", "EC101" };
        String[] B = { "CS111", "MA123", "WR100", "SO100" };
        String[] C = { "CS111", "MA294", "WR150", "CL212" };
        String[] E = { "CS350", "CS235", "EC101", "MU101" };
        String[] F = { "CS111", "MA124", "BI108", "SO105" };
        String[] G = { "CS591", "MA442", "EN212", "EC101" };
 
        // uncomment one test at a time as you develop the corresponding methods above
        
   
        D.insertStudent("Christie,Chris",A); 
        D.insertStudent("Carson,Ben", B);
        D.insertStudent("Trump,Donald", C);
        D.insertStudent("Kasich,John",E); 
        D.insertStudent("Cruz,Ted", F);
        D.insertStudent("Bush,Jeb", G);
        
        System.out.println("\n[1] Should print out:"); 
        System.out.println("0: Bush,Jeb: [CS591,MA442,EN212,EC101]");
        System.out.println("1: Carson,Ben: [CS111,MA123,WR100,SO100]");
        System.out.println("2: Christie,Chris: [CS111,CS131,WR99,EC101]");
        System.out.println("3: Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("4: Kasich,John: [CS350,CS235,EC101,MU101]");
        System.out.println("5: Trump,Donald: [CS111,MA294,WR150,CL212]\n");
        
        D.printDictionary();  
     
        System.out.println("\n[2] Should print out:\n6"); 
        System.out.println(D.size()); 
        
        System.out.println("\n[3] Should print out:\nfalse"); 
        System.out.println(D.isEmpty()); 
        
        System.out.println("\n[4] Should print out:\ntrue"); 
        System.out.println(D.member("Cruz,Ted")); 
        
        System.out.println("\n[5] Should print out:\nfalse"); 
        System.out.println(D.member("Jindal,Bobby")); 
        
        D.deleteStudent("Bush,Jeb");
        D.deleteStudent("Christie,Chris");
        System.out.println("\n[6] Should print out:\nfalse  false"); 
        System.out.println(D.member("Bush,Jeb") + "  " + D.member("Christie,Chris")); 
        
        System.out.println("\n[7] Should print out:\n[CS111, MA123, WR100, SO100]"); 
        String name = "Carson,Ben";
        try {
            System.out.println(Arrays.toString(D.lookupClasses(name))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: " + name);
        }
        
        name = "Jindal,Bobby";
        System.out.println("\n[8] Should print out:");
        System.err.println("Key not found: " + name); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses(name))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: " + name);
        }
        
        System.out.println("\n[9] Should print out:\n[CS111, WR100, SO100]");
        D.dropClass("Carson,Ben", "MA123");
        D.dropClass("Carson,Ben", "EC102"); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses("Carson,Ben"))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: Carson,Ben");
        } 
        
        System.out.println("\n[10] Should print out:\n[CS111, MA294, WR150, CL212, CS591]");  
        D.addClass("Trump,Donald", "CS591");
        D.addClass("Trump,Donald", "WR150"); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses("Trump,Donald"))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: Carson,Ben");
        } 
        
        System.out.println("\n[11] Should print out:\nfalse  true"); 
        D.dropClass("Walker,Scott","PH150");
        System.out.print(D.member("Walker,Scott") + "  " );
        D.addClass("Walker,Scott","PH110"); 
        System.out.println(D.member("Walker,Scott"));   
        
        System.out.println("\n[12] Should print out:\ntrue"); 
        System.out.println(D.enrolled("Trump,Donald", "CS591"));  
        
        System.out.println("\n[13] Should print out:\nfalse"); 
        System.out.println(D.enrolled("Trump,Donald", "CS101"));        
        
        // testing iterators       
        
        System.out.println("\n[14] Should print out:");
        System.out.println("0: Carson,Ben: [CS111,WR100,SO100]");
        System.out.println("1: Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("2: Kasich,John: [CS350,CS235,EC101,MU101]");
        System.out.println("3: Trump,Donald: [CS210,MA294,WR150,CL212,CS591]");
        System.out.println("4: Walker,Scott: [PH110]\n");
        D.printDictionary(); 
        
        System.out.println("\n[15] Should print out same but without index numbers:");
        D.initPairIterator(); 
        
        while(D.hasNextPair()) {
            System.out.println(D.nextPair());
        }
        System.out.println("\n[16] Should print out:\nCarson,Ben:  [CS111,WR100,SO100]");
        D.initPairIterator(); 
        System.out.println(D.nextPair());
        
        System.out.println("\n[17] Should print out:");  
        D.initStudentIterator("CS111");        
        System.out.println("Carson,Ben: [CS111,WR100,SO100]");
        System.out.println("Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("Trump,Donald: [CS111,MA294,WR150,CL212,CS591]\n");
        
        while(D.hasNextStudent()) {
            System.out.println(D.nextStudent());
        }
        
        System.out.println("\n[18] Should print out:\nTrump,Donald:[CS111,MA294,WR150,CL212,CS591]");
        D.initStudentIterator("CL212"); 
        
        while(D.hasNextStudent()) {
            System.out.println(D.nextStudent());
        } 
        
        System.out.println("\n[19] Testing resizing; should print out a dictionary with 14 pairs.\n"); 
        D.insertStudent("Clinton, Hillary",A); 
        D.insertStudent("Sanders,Bernie", B);
        D.insertStudent("Lincoln,Abraham", C);
        D.insertStudent("Kennedy,John",E); 
        D.insertStudent("Bush,George", F);
        D.insertStudent("Reagan,Ronald", G);
        D.insertStudent("Nixon,Dick",A); 
        D.insertStudent("Carter,Jimmy", B);
        D.insertStudent("Johnson,Lyndon", C);
 
        D.printDictionary(); 
        
    }

}