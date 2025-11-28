import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.*;
import java.util.concurrent.Callable;
import java.util.Arrays;

/**
 * Code to test submitted solutions for the sorting project.
 *
 * @author Kyle Burke
 */
public class SongSelectorTester extends SpeedGrader.CodeTester {

    private static final int MAX_SCORE = 100;
    
    private static final double MULTIPLIER_TO_TIME_OUT = .100;
    
    private static final int[] SIZES = new int[]{2, 3, 5, 10, 20, 30, 50, 75, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8200, 8400, 8600, 8800, 9000, 9200, 9400, 9600, 9800, 9900, 10000, 10500, 11000, 11500, 11750, 12000, 12500, 13000};
    //private static final int N = 10;
    
    public SongSelectorTester() {
        //no state
    }
    
    public String getCodeAuthors() {
        return SongSelector.getAuthors();
    }

    /**
     * Runs the tests and prints feedback if asserts are enabled.
     */
    public Long call() {
        
        //System.out.println("Testing MySorter on an array of size " + N + ".");
        Random random = new Random();
        
        int n = this.problemSize;

        List<Integer> songLengths = new ArrayList<>(); //lengths in seconds
        while (songLengths.size() < n) {
            int song;
            if (random.nextInt(10) > 7) {
                //possible long song
                song = random.nextInt(1500);
            } else {
                //normal-length song
                song = random.nextInt(300);
            }
            songLengths.add(song);
        }
        
        //run the test!
        long startTime = this.getSystemMillis();
        List<Integer> studentSongs = SongSelector.chooseSongs(songLengths);
        long stopTime = this.getSystemMillis();
        int studentSeconds = getSeconds(songLengths, studentSongs);
        
        boolean test;
        String message;

        test = legalIndices(studentSongs);
        message = "You chose illegal song indices.  Indices chosen: " + studentSongs;
        assert test : message;
        
        if (n <= 20) {

            List<Integer> bestSongs = chooseLongestSongs(songLengths);
            int bestSeconds = getSeconds(songLengths, bestSongs);

            test = bestSeconds <= studentSeconds;
            message = "There is a better way to choose songs than what your code chose on " + songLengths + "\nYour choices: " + studentSongs + "   (" + studentSeconds + " seconds)\nMy choices: " + bestSongs + "   (" + bestSeconds + " seconds)";
            assert test : message;

            test = bestSeconds >= studentSeconds;
            message = "Wait... you beat my best choices.  Please tell Kyle right now, because there's something wrong with his code!\nSong Lengths: " + songLengths + "\nYour choices: " + studentSongs + "   (" + studentSeconds + " seconds)\nMy choices: " + bestSongs + "   (" + bestSeconds + " seconds)";
            assert test : message;
        } else {
            System.out.println("Too large to test correctness, but Kyle certainly will!");
        }
        
        long time = stopTime-startTime;
        return time;
        
    }
    
    private static List<Integer> bruteForceFindLongestSongs(List<Integer> songs) {
        List<List<Integer>> allLists = allSublists(songs.size());
        int maxSeconds = 0;
        List<Integer> bestIndices = new ArrayList<Integer>();
        for (List<Integer> indicesOption : allLists) {
            if (areLegalIndices(indicesOption, songs)) {
                int seconds = getSeconds(songs, indicesOption);
                if (seconds > maxSeconds) {
                    maxSeconds = seconds;
                    bestIndices = indicesOption;
                }
            }
        }
        return bestIndices;
    }
    
    //returns list of all indices of all sublists of a list of length n
    private static List<List<Integer>> allSublists(int n) {
        if (n == 0) {
            List<List<Integer>> options = new ArrayList<List<Integer>>();
            options.add(new ArrayList<Integer>()); //add the empty list
            return options;
        } else {
            List<List<Integer>> oneFewerOptions = allSublists(n-1);
            List<List<Integer>> options = new ArrayList<List<Integer>>();
            for (List<Integer> smallerOption : oneFewerOptions) {
                options.add(smallerOption); //add one without the current index
                List<Integer> copyWithI = new ArrayList<Integer>();
                copyWithI.addAll(smallerOption);
                copyWithI.add(n-1);
                options.add(copyWithI);
            }
            return options;
        }
    }
    
    private static boolean areLegalIndices(List<Integer> indices, List<Integer> songs) {
        for (Integer songIndex : indices) {
            if (songIndex < 0 || songIndex >= songs.size()) return false; //index is too big or too small
            if (indices.indexOf(songIndex) != indices.lastIndexOf(songIndex)) return false; //index appears only once
            if (indices.contains(songIndex - 1) || indices.contains(songIndex + 1)) return false; //there are two neighboring indices
        }
        return true;
    }

    //chooses the longest songs that don't neighbor any others
    private static List<Integer> chooseLongestSongs(List<Integer> songs) {
        return bruteForceFindLongestSongs(songs);
    }

    private static boolean legalIndices(List<Integer> indices) {
        Collections.sort(indices);
        int lastIndex = -2;
        for (Integer index : indices) {
            if (index == lastIndex || lastIndex + 1 == index) {
                return false;
            }
        }
        return true;
    }

    private static int getSeconds(List<Integer> songs, List<Integer> indices) {
        int seconds = 0;
        for (Integer i : indices) {
            seconds += songs.get(i);
        }
        return seconds;
    }
    
    public static void main(String[] args) {
        SpeedGrader grader = new SpeedGrader();
        SpeedGrader.CodeTester tester = new SongSelectorTester();
        grader.runTests(tester, MULTIPLIER_TO_TIME_OUT, MAX_SCORE, SIZES);
    }
}