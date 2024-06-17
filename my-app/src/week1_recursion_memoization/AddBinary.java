package week1_recursion_memoization;

public class AddBinary {
    public String addBinary(String a, String b) {
        // Initialize variables
        int i = a.length() - 1;
        int j = b.length() - 1;

        // Save work by determining the maximum possible size of the StringBuilder
        int maxLen = i > j ? i + 1 : j + 1;
        StringBuilder sb = new StringBuilder(maxLen + 1);

        // At each stage, add the current characters and the previous carry
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int total = carry;
            if (i >= 0) {
                total += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                total += b.charAt(j--) - '0';
            }
            sb.append((char) ((total % 2) + '0'));
            carry = total / 2;
        }

        // If there's a carry at the end, add one more '1'
        if (carry > 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }
}