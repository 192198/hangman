package com.ogtega.util;

import com.ogtega.model.Display;
import com.ogtega.model.Player;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class GameUtils {

    private static final Scanner reader = new Scanner(System.in);

    public static String userPrompt(String str) {
        String res = "";

        userPrint(str + " : ");

        while(res.matches("")) {
            res = reader.nextLine();
        }
        return serialize(res);
    }

    public static void userPrint(String str) {
        System.out.print("> " + str);
    }

    public static String replace(String str, int index, char c) {
        String pre = str.substring(0, index);
        String post = str.substring(index + 1);
        return pre + c + post;
    }

    public static void gameDisplay(Display input, Player player, Player bot) {
        System.out.print(input + " " + String.valueOf(input.getBlacklist()) + "\n");
        System.out.print("Player: " + player.getPoints() + " Bot: " + bot.getPoints() + "\n");
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
