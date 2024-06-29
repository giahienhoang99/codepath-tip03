package week4_review;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }

        int max = 0, count = 0;
        Set<Character> seen = new HashSet<>();

        // sliding window
        int left = 0, right = 1;
        seen.add(s.charAt(left));

        // increase window size as well as track seen values
        while (right < s.length() && left <= right) {
            // when meet seen val increase left ptr of window til it passes seen val
            while (seen.contains(s.charAt(right)) && left < right) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(s.charAt(right));
            right++;
            max = Math.max(max, right - left);
        }

        return max;
    }
}
