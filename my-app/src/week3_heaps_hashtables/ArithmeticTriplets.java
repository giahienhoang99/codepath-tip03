package week3_heaps_hashtables;

import java.util.HashSet;
import java.util.Set;

public class ArithmeticTriplets {
    public int numOfArithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        int count = 0;

        for (int num : nums) {
            if (set.contains(num - diff) && set.contains(num - 2 * diff)) {
                count++;
            }
            set.add(num);
        }

        return count;
    }

    public static void main(String[] args) {
        ArithmeticTriplets solution = new ArithmeticTriplets();
        System.out.println("Num of arithmetic triplets tests:");
        // Test case 1
        int[] nums = {0, 1, 4, 6, 7, 10};
        int diff = 3;
        int expected = 2;
        int actual = solution.numOfArithmeticTriplets(nums, diff);
        System.out.println("Test case 1: Expected: " + expected + ", Actual: " + actual);

        // Test case 2
        nums = new int[]{4, 5, 6, 7, 8, 9};
        diff = 2;
        expected = 5;
        actual = solution.numOfArithmeticTriplets(nums, diff);
        System.out.println("Test case 2: Expected: " + expected + ", Actual: " + actual);

        // Test case 3
        nums = new int[]{1, 2, 3, 4, 5};
        diff = 1;
        expected = 4;
        actual = solution.numOfArithmeticTriplets(nums, diff);
        System.out.println("Test case 3: Expected: " + expected + ", Actual: " + actual);

        // Test case 4
        nums = new int[]{1, 2, 3, 4, 5};
        diff = 2;
        expected = 3;
        actual = solution.numOfArithmeticTriplets(nums, diff);
        System.out.println("Test case 4: Expected: " + expected + ", Actual: " + actual);
    }
}