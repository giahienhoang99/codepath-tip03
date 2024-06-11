class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");

        int result = 0;

        for (int i = 0; i <= s.length() - 2; i++) {
            char cur = s.charAt(i);
            char next = s.charAt(i+1);
            if (map.get(cur) < map.get(next)) {
                result -= map.get(cur);
            } else {
                result += map.get(cur);
            }
        }

        return result + map.get(s.charAt(s.length()-1));
    }
}