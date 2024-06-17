package week1_recursion_memoization;

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();

        int curNum = 0;
        String curStr = "";

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                curNum = curNum * 10 + (c - '0');
                // can use Character.getNumericValue(c) instead of c - '0'
                /*
                explain:
                (c - '0') converts the character c to its integer value.
                In ASCII, the character '0' has a value of 48, '1' has a value of 49, etc.
                So, subtracting '0' from any digit character gives its integer value:
                For '1': '1' - '0' = 49 - 48 = 1
                For '2': '2' - '0' = 50 - 48 = 2
                And so on.
                */
            } else if (c == '[') {
                numStack.push(curNum);
                curNum = 0;
                strStack.push(curStr);
                curStr = "";
            } else if (c == ']') {
                int repeatTimes = numStack.pop();
                StringBuilder decodedStr = new StringBuilder(strStack.pop());
                for (int i = 0; i < repeatTimes; i++) {
                    decodedStr.append(curStr);
                }
                curStr = decodedStr.toString();
            } else {
                curStr += c;
            }
        }
        return curStr;
    }
}
