package week2_linkedlist_strings_arrays;

public class ShiftingLetters {
    // Initial solution - very slow
    // N = s.length() = shifts.length
    // Time:  O(N) + O(M^2)
    //        O(M^2) due to string concatenation inside a loop
    //        => should use StringBuilder instead
    // Space: O(N + M)
    public String shiftingLettersSlowest(String s, int[] shifts) {
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String result = "";

        int[] prefSumReverse = new int[shifts.length];
        prefSumReverse[shifts.length - 1] = shifts[shifts.length - 1];

        // using prefix sum to compute total shifts for each char of s
        for (int i = shifts.length - 2; i >= 0; i--) {
            prefSumReverse[i] = prefSumReverse[i+1] + shifts[i];
            prefSumReverse[i] = prefSumReverse[i] % 26;
        }
        // shifting chars of s
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int curStepsFromA = cur - 'a';
            int newStepsFromA = curStepsFromA + prefSumReverse[i];
            newStepsFromA = newStepsFromA % 26;
            result += alphabet[newStepsFromA];
        }

        return result;
    }

    public String shiftingLettersSlower(String s, int[] shifts) {
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        StringBuilder result = new StringBuilder();

        int[] prefSumReverse = new int[shifts.length];
        prefSumReverse[shifts.length - 1] = shifts[shifts.length - 1];

        // using prefix sum to compute total shifts for each char of s
        for (int i = shifts.length - 2; i >= 0; i--) {
            prefSumReverse[i] = prefSumReverse[i+1] + shifts[i];
            prefSumReverse[i] = prefSumReverse[i] % 26;
        }
        // shifting chars of s
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int curStepsFromA = cur - 'a';
            int newStepsFromA = curStepsFromA + prefSumReverse[i];
            newStepsFromA %= 26;
            result.append(alphabet[newStepsFromA]);
        }
        return result.toString();
    }

    // Most optimized
    // T: O(N)
    // S: O(N)
    public String shiftingLetters(String s, int[] shifts) {
        int shift = 0;
        char[] arr = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            shift = (shift + shifts[i]) % 26;
            arr[i] = (char) (((arr[i] - 'a' + shift) % 26) + 'a');
        }
        return new String(arr);
    }

    // what we did in session 2
    public String shiftingLettersInSession2(String s, int[] shifts) {
        StringBuilder res = new StringBuilder();
        int shift = 0;

        // loop in reverse order
        for (int i = s.length() - 1; i >= 0; i--) {
            shift += shifts[i];
            shift = shift % 26;
            // shift
            int stepsFromA = (s.charAt(i) - 'a' + shift) % 26;
            char toAddChar = (char) (stepsFromA + 'a');
            res.insert(0, toAddChar);
        }

        return res.toString();
    }
}
