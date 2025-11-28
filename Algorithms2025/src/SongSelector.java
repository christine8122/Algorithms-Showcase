import java.util.ArrayList;
import java.util.List;

public class SongSelector {
    /**
     * Returns the authors' names.
     * @return  The names of the authors of this file.
     */
    public static String getAuthors() {
        return "Bridget Martinez and Christine Samons";
    }

    public static List<Integer> chooseSongs(List<Integer> songs) {
        int length = songs.size();

        //just in case
        if (length == 0){
            return new ArrayList<>();
        }


        //keep track of max total song length from first songs with no neighbors
        int[] maxTime = new int[length];
        //keeps track of if a song is included in the optimal solution
        boolean[] track = new boolean[length];

        //base case
        maxTime[0] = songs.get(0);
        track[0] = true;

        //idk if this is the optimal way to go through the songs
        if (length > 1) {
            if (songs.get(1) > songs.get(0)) {
                maxTime[1] = songs.get(1);
                track[1] = true;
            } else {
                maxTime[1] = songs.get(0);
                track[1] = false;
            }
        }

        for (int i = 2; i < length; i++) {
            //choose songs that arent neighboring
            //since no neighboring songs, add the current songs length to the best total from two songs back.
            int include = songs.get(i) + maxTime[i - 2];

            //just take the best total up to the previous song.
            int exclude = maxTime[i - 1];

             //choose whichever option gives a better total length.
            if (include > exclude) {
                maxTime[i] = include;
                track[i] = true;
            } else {
                maxTime[i] = exclude; //skip
                track[i] = false;
            }
        }

        //backtrack to find selected indices
        List<Integer> longSongs = new ArrayList<>();
        for (int i = length - 1; i >= 0;) {
            if (track[i]) {
                longSongs.add(0, i); // add to front
                //skip the neighboring song since we can't choose adjacent songs.
                i -= 2;
            } else {
                //go backwards one
                i--;
            }
        }

        return longSongs;
    }
}
