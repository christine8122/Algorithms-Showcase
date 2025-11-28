import java.awt.Point;
import java.util.*;
import java.lang.Math;

public class FriendsFinder {
    /**
     * Returns the authors' names.
     * @return The names of the authors of this file.
     */
    public static String getAuthors() {
        return "Bridget Martinez and Christine Samons";
    }
    
    
    public static List<Point> nearestFriends(List<Point> friends) {
        if (friends == null || friends.size() < 2) {
            return new ArrayList<>();
        }
        
        double minDist = Double.MAX_VALUE;
        Point closestA = null;
        Point closestB = null;

        double minDistSquared = Double.MAX_VALUE;

        //main brute force algorithm
        for (int i = 0; i < friends.size(); i++) {
            Point a = friends.get(i);
            
            for (int j = i + 1; j < friends.size(); j++) {
                Point b = friends.get(j);
                
                //calculate squared distance
                double dx = a.x - b.x;
                double dy = a.y - b.y;
                double distSquared = dx*dx + dy*dy;
                
                //only update if this distance is smaller
                if (distSquared < minDistSquared) {
                    minDistSquared = distSquared;
                    minDist = Math.sqrt(distSquared);
                    closestA = a;
                    closestB = b;
                }
            }
        }

        return Arrays.asList(closestA, closestB);
    }
}