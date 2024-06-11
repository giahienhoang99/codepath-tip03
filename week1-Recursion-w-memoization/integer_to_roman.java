class Solution {
    public String intToRoman(int num) {
        int[] keys = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] values = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

        StringBuilder result = new StringBuilder();

        for (int i = keys.length - 1; i >= 0; --i) {
            while (num >= keys[i]) {
                result.append(values[i]);
                num -= keys[i];
            }
        }

        return result.toString();
    }
}