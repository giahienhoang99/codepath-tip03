package week4_review;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>(); // Result list to store the pairs
        // Priority queue to store pairs with smallest sums, sorted by the sum
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Push the initial pairs into the priority queue
        for (int num : nums1) {
            // The sum and the index of the second element in nums2
            pq.offer(new int[]{num + nums2[0], 0});
        }

        // Pop the k smallest pairs from the priority queue
        while (k > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            int sum = pair[0]; // Get the smallest sum
            int pos = pair[1]; // Get the index of the second element in nums2

            List<Integer> currentPair = new ArrayList<>();
            currentPair.add(sum - nums2[pos]);
            currentPair.add(nums2[pos]);
            result.add(currentPair); // Add the pair to the result list

            // If there're more elements in nums2, push the next pair into the priority queue
            if (pos + 1 < nums2.length) {
                pq.offer(new int[]{sum - nums2[pos] + nums2[pos + 1], pos + 1});
            }

            k--; // Decrement k
        }

        return result;
    }
}
