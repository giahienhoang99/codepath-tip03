package week4_review;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    private static List<String> combine(List<String> oldRes, String[] arr) {
        List<String> newRes = new ArrayList<>();
        for (int i = 0; i < oldRes.size(); i++) {
            for (String j : arr) {
                newRes.add(oldRes.get(i) + j);
            }
        }
        return newRes;
    }

    private static List<String> getCombinations(List<Integer> nums, Map<Integer,String[]> map) {
        if (nums.size() == 1) {
            return new ArrayList<String>(Arrays.asList(map.get(nums.get(0))));
        }

        List<String> result = new ArrayList<>();

        String[] arr1 = map.get(nums.get(0));
        String[] arr2 = map.get(nums.get(1));
        for (String i : arr1) {
            for (String j : arr2) {
                result.add(i+j);
            }
        }

        if (nums.size() == 2) return result;

        for (int i=2; i < nums.size(); i++) {
            result = combine(result, map.get(nums.get(i)));
        }

        return result;
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.length() == 0) return result;

        List<Integer> nums = new ArrayList<>();
        Map<Integer, String[]> map = new HashMap<>();
        map.put(2, new String[]{"a", "b", "c"});
        map.put(3, new String[]{"d", "e", "f"});
        map.put(4, new String[]{"g", "h", "i"});
        map.put(5, new String[]{"j", "k", "l"});
        map.put(6, new String[]{"m", "n", "o"});
        map.put(7, new String[]{"p", "q", "r", "s"});
        map.put(8, new String[]{"t", "u", "v"});
        map.put(9, new String[]{"w", "x", "y", "z"});

        for (int i = 0; i < digits.length(); i++) {
            String cur = Character.toString(digits.charAt(i));
            int curNum = Integer.parseInt(cur);
            if (curNum == 0 || curNum == 1) continue; // filter out 0 and 1
            nums.add(curNum);
        }

        if (nums.size() != 0) {
            result = getCombinations(nums, map);
        }

        return result;
    }

    public static void main(String[] args) {
        String test1 = "2";
        List<String> result1 = letterCombinations(test1);
        System.out.println(result1);

        String test2 = "23";
        List<String> result2 = letterCombinations(test2);
        System.out.println(result2);

        String test3 = "234";
        List<String> result3 = letterCombinations(test3);
        System.out.println(result3);

        String test4 = "";
        List<String> result4 = letterCombinations(test4);
        System.out.println(result4);
    }
}
