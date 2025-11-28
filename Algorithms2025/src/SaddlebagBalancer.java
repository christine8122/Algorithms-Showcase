import java.util.*;

public class SaddlebagBalancer {
    /**
     * Returns the authors' names.
     * @return  The names of the authors of this file.
     */
    public static String getAuthors() {
        return "Bridget Martinez and Christine Samons";
    }
    public static List<Integer> getPartition(List<Integer> packageWeights) {
        ArrayList<Integer> weights = new ArrayList<>(packageWeights);
        int total = 0;
        for (int weight : weights) {
            total += weight;
        }
        int goalNum = total / 2;
        List<Integer> partition = new ArrayList<>(Collections.nCopies(packageWeights.size(), 0));
        if (backtracking(packageWeights, partition, 0, 0, goalNum)) {
            return partition;
        }
        //keep track of the elements and position
        return new ArrayList<>(Collections.nCopies(packageWeights.size(), 0));
    }
    private static boolean backtracking(List<Integer> weights, List<Integer> partition, int index, int currentSum, int targetSum) {
        if (currentSum == targetSum) {
            return true;
        }
        if (index >= weights.size() || currentSum > targetSum) {
            return false;
        }
        partition.set(index, 1);
        if (backtracking(weights, partition, index + 1, currentSum + weights.get(index), targetSum)) {
            return true;
        }        
        partition.set(index, 0);
        return backtracking(weights, partition, index + 1, currentSum, targetSum);
    }
}
