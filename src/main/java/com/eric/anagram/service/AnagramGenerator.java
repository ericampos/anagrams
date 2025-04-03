package com.eric.anagram.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnagramGenerator {
    public static List<String> generateAnagrams(String input) {
        validateInput(input);
        Set<String> anagramSet = new HashSet<>();
        char[] chars = input.toCharArray();
        generateAnagramsHelper(new StringBuilder(), new boolean[chars.length], chars, anagramSet);
        return new ArrayList<>(anagramSet);
    }

    private static void validateInput(String input) {
        if (input == null || input.isEmpty() || !input.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Input must be a non-empty string of letters");
        }
    }

    private static void generateAnagramsHelper(StringBuilder prefix, boolean[] used, char[] chars, Set<String> anagrams) {
        if (prefix.length() == chars.length) {
            anagrams.add(prefix.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            prefix.append(chars[i]);
            generateAnagramsHelper(prefix, used, chars, anagrams);
            used[i] = false;
            prefix.deleteCharAt(prefix.length() - 1);
        }

    }

}
