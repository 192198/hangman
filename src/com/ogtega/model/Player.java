package com.ogtega.model;


import java.util.Random;

public class Player {

    private int points;
    private int strikes;
    private String name;
    private Display display;

    private static char[] whitelist = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Player(String name, Display display) {
        this.points = 0;
        this.strikes = 0;
        this.name = name;
        this.display = display;
    }

    public char guess() {
        updateWhitelist();
        return whitelist[new Random().nextInt(whitelist.length)];
    }

    private void updateWhitelist() {
        String res = "";

        for(char c : whitelist) {
            if(!display.checkBlacklist(c)) {
                res += c;
            }
        }

        whitelist = res.toCharArray();
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
        strikes++;
    }

    public int getStrikes() {
        return strikes;
    }
}
