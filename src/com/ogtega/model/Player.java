package com.ogtega.model;


import java.util.Random;

public class Player {

    private int points;
    private String name;
    private Display display;

    private static char[] whitelist = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Player(String name, Display display) {
        this.points = 0;
        this.name = name;
        this.display = display;
    }

    public char guess() {
        return whitelist[new Random().nextInt(whitelist.length)];
    }

    public void updateWhitelist() {
        String res = "";

        for (char c : whitelist) {
            if (!display.checkBlacklist(c)) {
                res += c;
            }
        }

        whitelist = res.toCharArray();
    }

    public void reset() {
        this.points = 0;
        whitelist = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void point() {
        points++;
    }

    public void strike() {
        points--;
    }
}
