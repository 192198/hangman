package com.ogtega.model;

import com.ogtega.util.GameUtils;

import static com.ogtega.util.DisplayUtils.phraseParser;

public class Display {

    private String phrase;
    private String secret;

    private static char[] whitelist = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Display(String phrase) {

        this.phrase = phrase;
        this.secret = phraseParser(phrase);
    }

    //
    public boolean guess(char guess) {
        boolean res = false;

        updateWhitelist(guess);

        for(int i = 0; i < secret.length(); i++) {
            char c = phrase.charAt(i);
            char d = secret.charAt(i);

            if(c == guess && d != c) {
                secret = GameUtils.replace(secret, i, guess);
                res = true;
            }
        }
        return res && !complete();
    }

    // Update the white list variable by removing char c
    private static void updateWhitelist(char c) {
        // Don't run this if the character at this index has been removed
        if(checkWhitelist(c)) {
            int index = 999;
            int length = whitelist.length;
            char[] res = new char[length - 1];

            for (int i = 0; i < length; i++) {
                if (whitelist[i] != c) {
                    res[i] = whitelist[i];
                } else {
                    index = i;
                    break;
                }
            }

            // Prevent unnecessary removal of a char
            if (index != 999) {
                for (int i = index + 1; i < length; i++) {
                    res[i - 1] = whitelist[i];
                }
            }

            whitelist = res;
        }
    }

    // Run a check if a character is in the white list already
    private static boolean checkWhitelist(char c) {
        boolean res = false;

        for (char d : whitelist) {
            res = res || c == d;
        }

        return res;
    }

    public char[] getWhitelist() {
        return whitelist;
    }

    public boolean complete() {
        return phrase.matches(secret);
    }

    @Override
    public String toString() {
        return secret;
    }
}
