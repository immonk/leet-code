package com.leetcode.hashmap;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class $1657 {

    public boolean closeStrings(String word1, String word2) {

        // Base case 1 : String length is different
        if (word1.length() != word2.length())
            return false;

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < word1.length(); i++)
            map1.put(word1.charAt(i), map1.getOrDefault(word1.charAt(i), 0) + 1);

        for (int i = 0; i < word2.length(); i++)
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0) + 1);

        // Base case 2: has different character from each other
        if (!map2.keySet().containsAll(map1.keySet()))
            return false;

        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            int value = entry.getValue();
            char key = entry.getKey();
            int targetFrequency = map2.get(key);
            if (targetFrequency == value)
                continue;

            // following when value is diff
            if (!findPair(key, value, map1, map2)) {
                System.out.println(key + " " + value + " " + targetFrequency);
                return false;
            }

        }


        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            if (!Objects.equals(entry.getValue(), map2.get(entry.getKey()))) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                return false;
            }
        }


        return true;

    }

    private boolean findPair(char key, int value, Map<Character, Integer> source, Map<Character, Integer> target) {
        for (Map.Entry<Character, Integer> entry : source.entrySet()) {
            if (entry.getKey() != key
                    && !Objects.equals(entry.getValue(), target.get(entry.getKey()))
                    && Objects.equals(entry.getValue(), target.get(key))
            ) {
                source.put(key, entry.getValue());
                source.put(entry.getKey(), value);
                return true;

            }
        }
        return false;
    }


}