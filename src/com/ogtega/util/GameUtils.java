package com.ogtega.util;

import com.ogtega.model.Display;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class GameUtils {

    private static final Scanner reader = new Scanner(System.in);

    public static String userPrompt(String str) {

        userPrint(str + " : ");

        return serialize(reader.nextLine());
    }

    public static void userPrint(String str) {
        System.out.print("> " + str);
    }

    public static String replace(String str, int index, char c) {
        String pre = str.substring(0, index);
        String post = str.substring(index + 1);
        return pre + c + post;
    }

    public static void gameDisplay(Display input) {
        System.out.print(input + " " + String.valueOf(input.getWhitelist()) + "\n");
    }

    public static String serialize(String str) {

        try {
            return new String(str.getBytes("UTF-8"), Charset.forName("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
