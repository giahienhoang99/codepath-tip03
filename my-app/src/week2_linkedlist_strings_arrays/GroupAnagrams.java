package week2_linkedlist_strings_arrays;

import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // 1: make a String[] list where list[i] = strs[i] w/ chars sorted
        // 2: create a map<string,list<int>>: keys = list[i], vals = list of i
        // 3: declare list<list<String>> result
        // 4: traverse the map:
        // => for each key, create a list<string>, add all strs[val]
        //    => add the list<string> for each key to the result list

        // step 1 + 2 simultaneously
        String[] sorted = new String[strs.length];
        Map<String,List<Integer>> map = new HashMap<>();
        for (int i=0; i < strs.length; i++) {
            char[] cur = strs[i].toCharArray();
            Arrays.sort(cur);
            sorted[i] = new String(cur);
            // add to map
            List<Integer> curList = new ArrayList<>();
            if (!map.containsKey(sorted[i])) {
                curList.add(i);
                map.put(sorted[i], curList);
            } else {
                curList = map.get(sorted[i]);
                curList.add(i);
                map.put(sorted[i], curList);
            }
        }
        // System.out.println(map.toString());

        // step 3
        List<List<String>> result = new ArrayList<>();

        // step 4
        map.forEach((key, val) -> {
            List<String> anagram = new ArrayList<String>();
            for (int v : val) {
                anagram.add(strs[v]);
            }
            result.add(anagram);
            // System.out.println(anagram);
        });

        return result;
    }

    public static void main(String[] args) {
        String[] test1 = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(test1));
        // [["bat"],["nat","tan"],["ate","eat","tea"]]

        String[] test2 = {""};
        System.out.println(groupAnagrams(test2));
        // [[""]]

        String[] test3 = {"a"};
        System.out.println(groupAnagrams(test3));
        // [["a"]]
    }
}
