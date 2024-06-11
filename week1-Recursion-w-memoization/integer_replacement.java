class Solution {
    public int integerReplacement(int n) {
        // base case
        if (n == 1) {
            return 0;
        }
        if (n == Integer.MAX_VALUE) {
            return 32;
        }
        // recursion
        if (n % 2 == 0) {
            return integerReplacement(n/2) + 1;
        }
        return Math.min(integerReplacement(n-1) + 1, integerReplacement(n+1) + 1);
    }
}