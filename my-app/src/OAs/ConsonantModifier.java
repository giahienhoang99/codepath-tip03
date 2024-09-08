package OAs;

import java.util.HashSet;
import java.util.Set;

public class ConsonantModifier {
    private static final char[] CONSONANTS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
    private static final Set<Character> VOWELS = new HashSet<>();

    static {
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u'}) {
            VOWELS.add(c);
        }
    }

    private static char getNewConsonant(char cur) {
        int index = -1;

        // Find the current consonant in the CONSONANTS array
        for (int i = 0; i < CONSONANTS.length; i++) {
            if (CONSONANTS[i] == Character.toLowerCase(cur)) {
                index = (i + 1) % CONSONANTS.length;
                break;
            }
        }

        // If the consonant is not found, return the original character
        if (index == -1) {
            return cur;
        }

        char nextConsonant = CONSONANTS[index];
        // Return the next consonant in the same case as the original
        return Character.isUpperCase(cur) ? Character.toUpperCase(nextConsonant) : nextConsonant;
    }

    public static String solution(String message, int n) {
        int count = 0;
        StringBuilder result = new StringBuilder();

        // Iterate through each character in the message
        for (int i = 0; i < message.length(); i++) {
            char cur = message.charAt(i);

            // Check if the character is a consonant and not a vowel
            if (!VOWELS.contains(cur) && Character.isAlphabetic(cur)) {
                count++;
                // Replace consonant if the count is a multiple of n
                if (count % n == 0) {
                    cur = getNewConsonant(cur);
                }
            }

            result.append(cur);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Test the solution with a sample input where n is 3
        String message = "CodeSignal";
        int n = 3;
        System.out.println(solution(message, n)); // Expected "CodeTignal"

        // Additional test cases
        System.out.println(solution("hello world", 3)); // Expected "hello wprld"
        System.out.println(solution("bcdfgh", 3)); // Expected "bcffgj"
    }
}

