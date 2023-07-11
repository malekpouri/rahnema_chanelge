package ir.utux;

import java.util.HashMap;
import java.util.Map;

public class PatternMache {
    public static void main(String[] args) {
        String pattern="xymzmyx";
        String reshte = "ali reza mori yougi mori reza ali";
        System.out.printf("Input: pattern=%s, reshte=%s%n", pattern, reshte);
        System.out.printf("Output: %b%n", isPatternMache(pattern, reshte));
    }

    private static boolean isPatternMache(String pattern, String reshte) {
        String[] words = reshte.split(" ");
        Map<Character,String> charToWord= new HashMap<>();
        Map<String,Character> wordToChar= new HashMap<>();
        if (pattern.length() != words.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (charToWord.containsKey(pattern.charAt(i))) {
                if (!charToWord.get(pattern.charAt(i)).equals(words[i])) {
                    return false;
                }
            } else {
                if (wordToChar.containsKey(words[i])) {
                    return false;
                }
                charToWord.put(pattern.charAt(i), words[i]);
                wordToChar.put(words[i], pattern.charAt(i));
            }
        }
        return true;


    }
}