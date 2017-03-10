package com.ogtega.model;

import com.ogtega.util.GameUtils;

import java.util.Arrays;

import static com.ogtega.util.DisplayUtils.phraseParser;

public class Display {

    private String phrase;
    private String secret;
    private String blacklist = " ";

    public Display(String phrase) {

        this.phrase = phrase;
        this.secret = phraseParser(phrase);
    }

    // Method used for executing a guess
    public boolean guess(char guess, Player player) {
        int count = 0;
        boolean res = false;

        if (!checkBlacklist(guess)) {
            blacklist += guess;
            player.updateWhitelist();
            sortBlacklist();

            for (int i = 0; i < secret.length(); i++) {
                char c = phrase.charAt(i);
                char d = secret.charAt(i);

                if (c == guess && d != c) {
                    secret = GameUtils.replace(secret, i, guess);
                    res = true;
                    count++;
                }
            }
        }

        if(res) {
            for(int i = 0; i < count; i++) {
                player.point();
            }
        } else {
            player.strike();
        }

        return res && !complete();
    }

    private void sortBlacklist() {
        char[] str = blacklist.toCharArray();
        Arrays.sort(str);
        blacklist = new String(str);
    }

    boolean checkBlacklist(char c) {
        boolean res = false;

        for (char d : blacklist.toCharArray()) {
            res = c == d;

            if (res) {
                break;
            }
        }

        return res;
    }

    public String getBlacklist() {
        return blacklist;
    }

    public boolean complete() {
        return phrase.matches(secret);
    }

    @Override
    public String toString() {
        return secret;
    }
}
