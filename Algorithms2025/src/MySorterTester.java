import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import java.util.concurrent.Callable;
import java.util.Arrays;

/**
 * Code to test submitted solutions for the sorting project.
 *
 */
public class MySorterTester extends SpeedGrader.CodeTester {

    private static final int MAX_SCORE = 100;
    
    private static final double MULTIPLIER_TO_TIME_OUT = 10.0;
    
    private static final int[] SIZES = new int[]{2, 3, 5, 10, 20, 30, 50, 75, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 15000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 200000, 225000, 250000, 275000, 300000, 350000, 400000, 500000, 600000, 650000, 700000, 750000, 800000, 850000, 875000};
    //private static final int N = 10;
    
    public MySorterTester() {
        //no state
    }
    
    public String getCodeAuthors() {
        return MySorter.getAuthors();
    }

    /**
     * Runs the tests and prints feedback if asserts are enabled.
     */
    public Long call() {
        
        //System.out.println("Testing MySorter on an array of size " + N + ".");
        Random rand = new Random();
        
        int n = this.problemSize;

        int start = rand.nextInt() % 10000;
        int step = rand.nextInt() % 10;
        int next = start;

        int[] ints = new int[n];
        ArrayList<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = next;
            integers.add(next);
            next += step;
        }
        Arrays.sort(ints);

        Collections.shuffle(integers);

        Integer[] shuff = integers.toArray(new Integer[0]);
        int[] shuffled = new int[n];
        for (int i = 0; i < n; i++) {
            shuffled[i] = shuff[i];
        }

        //test the function
        long startTime = this.getSystemMillis();
        MySorter.sort(shuffled);
        long stopTime = this.getSystemMillis();
        
        if (n < 20) {
            //System.out.println("Array at the end: " + Arrays.toString(shuffled));
        }
        

        //check that they are in order
        for (int i = 0; i < shuffled.length-1; i++) {
            String message = "Elements are not in sorted order: a[" + i + "] = " + shuffled[i] + ",  a[" + (i+1) + "] = " + shuffled[i+1];
            assert shuffled[i] <= shuffled[i+1] : message;
        }

        //check that they are still the same integers
        for (int i = 0; i < n; i++) {
            String message = "The sorted array doesn't have the same elements as the given array.  a[" + i + "] should have been " + ints[i] + " (" + (start + (i * step)) + ") but it is " + shuffled[i];
            assert shuffled[i] == ints[i] : message;
        }
        
        long time = stopTime-startTime;
        //System.out.println("Test ran in " + time + " milliseconds.");
        //System.out.println();
        return time;
        
    }
    
    public static void main(String[] args) {
        SpeedGrader grader = new SpeedGrader();
        SpeedGrader.CodeTester tester = new MySorterTester();
        grader.runTests(tester, MULTIPLIER_TO_TIME_OUT, MAX_SCORE, SIZES);
    }
}