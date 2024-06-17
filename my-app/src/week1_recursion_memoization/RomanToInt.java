package week1_recursion_memoization;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    public int romanToInt(String s) {
        // Initialize the map with correct key-value pairs
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            char cur = s.charAt(i);
            char next = s.charAt(i + 1);

            if (map.get(cur) < map.get(next)) {
                result -= map.get(cur);
            } else {
                result += map.get(cur);
            }
        }

        // Add the value of the last character
        return result + map.get(s.charAt(s.length() - 1));
    }

    public static void main(String[] args) {
        RomanToInt solution = new RomanToInt();
        System.out.println("III is in integer: " + solution.romanToInt("III"));    // 3
        System.out.println("IV is in integer: " + solution.romanToInt("IV"));     // 4
        System.out.println("IX is in integer: " + solution.romanToInt("IX"));     // 9
        System.out.println("LVIII is in integer: " + solution.romanToInt("LVIII"));  // 58
        System.out.println("MCMXCIV is in integer: " + solution.romanToInt("MCMXCIV")); // 1994
    }
}
