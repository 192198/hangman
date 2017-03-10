package com.ogtega.util;

public class DisplayUtils {

    public static String phraseParser(String str) {
        String res = "";

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isAlphabetic(c)) {
                res += (c != ' ') ? '_' : ' ';
            } else {
                res += c;
            }
        }

        return res;
    }
}
