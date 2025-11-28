import java.util.*;

/**
 * Tester for programs that find the code word door to pass through.
 */
public class CodeDoorTester {

    //Student's code that finds the hole in the wall
    CodeDoorFinder finder;
    
    //worst ratio that will earn points
    private static final int WORST_RATIO_FOR_POINTS = 10000;

    //worst ratio seen during a test
    private long maxRatio;
    
    //worst location seen during a test
    private long maxRatioLocation;
    
    //whether we should print out the results of every test.
    private boolean verbose;
    

    /**
     * Constructor.
     */
    public CodeDoorTester(CodeDoorFinder finder, boolean isVerbose) {
        this.maxRatio = 1;
        this.maxRatioLocation=0;
        this.finder = finder;
        this.verbose = isVerbose;
    }

    /**
     * Tests the finder on a single CodeDoorWall
     */
    private void runTest(long distance) {
        if (this.keepTesting()) {
            CodeDoorWall wall = new CodeDoorWall(distance);
            finder.findDoor(wall);
            long ratio = wall.getRatio();
            if (this.verbose) {
                System.out.println("Ratio at distance " + distance + ": " + ratio);
            }
            if (ratio > this.maxRatio) {
                System.out.println("Got a new worst ratio at distance " + distance + ": " + ratio);
                this.maxRatio = ratio;
                this.maxRatioLocation = distance;
            }
        } else {
            if (this.verbose) {
                System.out.println("Skipping a test because we've gone past the worst ratio worth points.");
            }
        }
    }

    /**
     * Runs the tests.
     */
    public void run() {
        // You may rename this method to better suit the purpose of your test case
        // Your test case logic here

        Random random = new Random();

        CodeDoorWall wall;
        long distance;

        

        
        System.out.println("*~~~~~~~~ Time for public distances. ~~~~~~~~*");
        //cover all cases in a short range.
        int coverAllMax = 100;
        for (int i = 1; i <= coverAllMax; i++) {
            //positive
            this.runTest(i);
            
            //negative
            this.runTest(-i);
        }

        //the easy and deterministic ones
        long[] distances = new long[]{1, 2, 5, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 2000000, 2500000, 3000000, 5000000, 6000000, 7000000, 8500000};
        for (int i = 0; i < distances.length; i++) {
            distance = distances[i];
            //positive
            this.runTest(distance);
            
            //negative
            this.runTest(-distance);
        }


        
        System.out.println("\n*~~~~~~~~ Time for random distances. ~~~~~~~~*");
        
        //again, but with random offsets
        long[] distancesB = new long[2 * distances.length];
        for (int i = 0; i < distances.length; i++) {
            long oldDistance = distances[i];
            long offset = (random.nextInt() % oldDistance) - (oldDistance / 2);
            distance = oldDistance + offset;
            distancesB[2*i] = distance;
            //do it again for the negative
            offset = (random.nextInt() % oldDistance) - (oldDistance / 2);
            distance = -oldDistance + offset;
            distancesB[2*i+1] = distance;
        }
        
        for (int i = 0; i < distancesB.length; i++) {
            distance = distancesB[i];
            //positive
            this.runTest(distance);
            
            //negative
            this.runTest(-distance);
        }
        
        List<List<Integer>> pointLevels = new ArrayList<>();
        pointLevels.add(Arrays.asList(7, 100));
        pointLevels.add(Arrays.asList(9, 98));
        pointLevels.add(Arrays.asList(11, 96));
        pointLevels.add(Arrays.asList(15, 93));
        pointLevels.add(Arrays.asList(20, 90));
        pointLevels.add(Arrays.asList(30, 80));
        pointLevels.add(Arrays.asList(50, 70));
        pointLevels.add(Arrays.asList(100, 60));
        pointLevels.add(Arrays.asList(250, 50));
        pointLevels.add(Arrays.asList(500, 40));
        pointLevels.add(Arrays.asList(1000, 30));
        pointLevels.add(Arrays.asList(3000, 20));
        pointLevels.add(Arrays.asList(WORST_RATIO_FOR_POINTS, 10));
        
        int pointsEarned = 0;
        
        for (List<Integer> level : pointLevels) {
            int targetRatio = level.get(0);
            if (this.maxRatio <= targetRatio) {
                pointsEarned = level.get(1);
                break;
            }
        }
        
        System.out.println("Max ratio from distance " + this.maxRatioLocation + ": " + this.maxRatio);
        System.out.println("Earned " + pointsEarned + " points!");
        System.out.println();
        System.out.println("Score: " + pointsEarned + "/100");
    }
    
    //determines whether we should keep testing
    private boolean keepTesting() {
        return this.maxRatio <= WORST_RATIO_FOR_POINTS;
    }
    
    public static void main(String[] args) {
        //default is false;
        boolean verbose = false;
        if (args.length > 0) {
            verbose = args[0].toLowerCase().startsWith("t");
        } else {
            System.out.println("Running in non-verbose mode.  If you want to specify the verbosity, add a t (for true) or f (for false) to the end of the command line.");
        }
        CodeDoorTester tester = new CodeDoorTester(new CodeDoorFinder(), verbose);
        tester.run();
    }
}
