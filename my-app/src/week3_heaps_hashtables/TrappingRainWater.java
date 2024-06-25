package week3_heaps_hashtables;

public class TrappingRainWater {
    private static int maxLeft(int[] height, int index) {
        int maxLeft = 0;
        for (int i=index-1; i >= 0; i--) {
            maxLeft = Math.max(maxLeft, height[i]);
        }
        return maxLeft;
    }
    private static int maxRight(int[] height, int index) {
        int maxRight = 0;
        for (int i=index + 1; i < height.length; i++) {
            maxRight = Math.max(maxRight, height[i]);
        }
        return maxRight;
    }
    // Initial solution - Brute force solution
    // Hint: water[i] = Math.min(maxL, maxR) - height[i];
    public int trap(int[] height) {
        int water = 0;
        for (int i = 1; i < height.length; i++) {
            int maxL = maxLeft(height, i);
            int maxR = maxRight(height, i);
            // check if water[i] >= 0;
            int waterToAdd = Math.max(Math.min(maxL, maxR) - height[i], 0);
            // or            Math.min(maxL,maxR) - height[i] < 0 ?
            //               0 : Math.min(maxL,maxR) - height[i];
            water += waterToAdd;
        }
        return water;
    }
}
