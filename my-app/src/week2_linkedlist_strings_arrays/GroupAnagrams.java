package week2_linkedlist_strings_arrays;

import java.util.*;

public class GroupAnagrams {
    // Time:  O(N*M)
    // Explanation:
    //        O(N*M) = creating the count arr for each string
    //        O(M)   = generating key from count arr
    //        O(N)   = inserting into hashmap + constructing result list
    // Space: O(N*M)
    // Explanation:
    //        O(N*M) = space of HashMap
    //        O(M)   = auxiliary space for count arr
    public static List<List<String>> groupAnagramsOptimized(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // Create a frequency count of characters
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            // Convert the count array to a string to use as a key
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();

            // Add the original string to the map
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    // Time:  O(N*MLogM)
    //        O(N*MLogM) = time for sorting N strings
    //             MLogM = time for sorting a string
    //                 M = avg. length of each string
    //        O(N) = inserting each string into hashmap = O(1) * N strings
    //        O(N) = constructing result list from hashmap
    // Space: O(N*M)
    //        O(N*M) = space of HashMap = N strings * M
    //             M = avg. length of each string
    //        O(N*M) = space of result list constructed from hashmap
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String word : strs){
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            if(!map.containsKey(sortedWord)){
                map.put(sortedWord , new ArrayList<>());
            }

            map.get(sortedWord).add(word);
        }

        return new ArrayList<>(map.values());
    }

    // Time:  O(N*MLogM)
    // Explanation:
    //        O(N*MLogM) = time for sorting N strings
    //        O(N) = inserting each string into hashmap = O(1) * N strings
    //        O(N*M) = constructing result list from hashmap
    // Space: O(N*M)
    // Explanation:
    //        O(N*M) = space of HashMap
    //        O(N*M) = space of result list constructed from hashmap
    public static List<List<String>> groupAnagramsInitial(String[] strs) {
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
        System.out.println(groupAnagramsOptimized(test1));
        // [["bat"],["nat","tan"],["ate","eat","tea"]]

        String[] test2 = {""};
        System.out.println(groupAnagrams(test2));
        System.out.println(groupAnagramsOptimized(test2));
        // [[""]]

        String[] test3 = {"a"};
        System.out.println(groupAnagrams(test3));
        System.out.println(groupAnagramsOptimized(test3));
        // [["a"]]
    }
}
