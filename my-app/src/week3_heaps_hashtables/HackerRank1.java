package week3_heaps_hashtables;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;

class Result {

    /*
     * Complete the 'rodOffcut' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY lengths as parameter.
     */
    public static List<Integer> rodOffcutOptimized(List<Integer> lengths) {
        List<Integer> rods = new ArrayList<>();

        // Use TreeMap to store rod lengths and their counts
        TreeMap<Integer, Integer> lengthCounts = new TreeMap<>();
        for (int length : lengths) {
            lengthCounts.put(length, lengthCounts.getOrDefault(length, 0) + 1);
        }

        int totalRods = lengths.size();
        int currentCut = 0;

        while (!lengthCounts.isEmpty()) {
            rods.add(totalRods);
            int minKey = lengthCounts.firstKey();
            currentCut += minKey;

            // Remove rods of the current minimum length
            int count = lengthCounts.remove(minKey);
            totalRods -= count;

            // Update the remaining rods
            if (totalRods > 0) {
                TreeMap<Integer, Integer> newLengthCounts = new TreeMap<>();
                for (Map.Entry<Integer, Integer> entry : lengthCounts.entrySet()) {
                    newLengthCounts.put(entry.getKey() - minKey, entry.getValue());
                }
                lengthCounts = newLengthCounts;
            }
        }

        return rods;
    }

    public static List<Integer> rodOffcut(List<Integer> lengths) {
        List<Integer> rods = new ArrayList<>();
        rods.add(lengths.size());  // Initially, all rods are present
        int zeroCount = 0;  // Count of rods cut to zero

        while (zeroCount < lengths.size()) {
            int cutoff = Integer.MAX_VALUE;
            // Find minimum positive length
            for (int length : lengths) {
                if (length > 0) {
                    cutoff = Math.min(cutoff, length);
                }
            }
            // Count of rods still greater than zero
            int currentNonZeroCount = 0;

            // Cut rods by the minimum length
            for (int i = 0; i < lengths.size(); i++) {
                if (lengths.get(i) > 0) {
                    lengths.set(i, lengths.get(i) - cutoff);
                    if (lengths.get(i) > 0) {
                        currentNonZeroCount++;
                    } else {
                        zeroCount++;
                    }
                }
            }

            if (zeroCount == lengths.size()) break;
            rods.add(currentNonZeroCount);  // Add current non-zero rod count
        }

        return rods;
    }
}

public class HackerRank1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Read the number of lengths
        int lengthsCount = Integer.parseInt(bufferedReader.readLine().trim());

        // Read the lengths from the input
        List<Integer> lengths = new ArrayList<>();
        for (int i = 0; i < lengthsCount; i++) {
            lengths.add(Integer.parseInt(bufferedReader.readLine().trim()));
        }

        // Get the result from the rodOffcut function
        //List<Integer> result = Result.rodOffcut(lengths);
        List<Integer> result = Result.rodOffcutOptimized(lengths);

        // Print the result to the console
        for (int count : result) {
            System.out.println(count);
        }

        bufferedReader.close();

        /*  Test 1:
            Input:
            8
            1
            2
            3
            4
            3
            3
            2
            1
            Output:
            8
            6
            4
            1

            Test 2:
            Input:
            6
            5
            4
            4
            2
            2
            8
            Output:
            6
            4
            2
            1

            Test 3:
            Input:
            5
            4
            5
            10
            8
            11
            Output:
            5
            4
            3
            2
            1
         */
    }
}

