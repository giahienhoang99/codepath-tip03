package week3_heaps_hashtables;

import java.util.*;

public class ThreeSum {
    // Time: O(N^2)
    // Space: O(1)
    public static List<List<Integer>> threeSumOptimized(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int j = i + 1;
            int k = nums.length - 1;
            // Two Pointer
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i],nums[j],nums[k])));
                    // skip nums[j] duplicates
                    j++;
                    while (j < k && nums[j] == nums[j-1]) j++;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSumHashMap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int fixed = nums[i];
            Map<Integer, Integer> seen = new HashMap<>();

            for (int j = i + 1; j < nums.length; j++) {
                int cur = nums[j];
                int complement = -fixed - cur;

                if (seen.containsKey(complement)) {
                    result.add(Arrays.asList(fixed, complement, cur));

                    // Skip duplicates for the current element
                    while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                        j++;
                    }
                }

                seen.put(cur, j);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test1 = {-1,0,1,2,-1,-4};
        int[] test2 = {0,1,1};
        int[] test3 = {0,0,0};
        System.out.println(threeSumHashMap(test1)); // [[-1, 0, 1], [-1, -1, 2]]
        System.out.println(threeSumOptimized(test1)); // [[-1, 0, 1], [-1, -1, 2]]
        System.out.println(threeSumHashMap(test2)); // []
        System.out.println(threeSumOptimized(test2)); // []
        System.out.println(threeSumHashMap(test3)); // [[0, 0, 0]]
        System.out.println(threeSumOptimized(test3)); // [[0, 0, 0]]
    }
}
