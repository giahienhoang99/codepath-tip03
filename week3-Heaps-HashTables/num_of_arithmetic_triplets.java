class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i] - diff) && set.contains(nums[i] - 2 * diff)) {
                count++;
            }
            set.add(nums[i]);
        }

        return count;
    }
}