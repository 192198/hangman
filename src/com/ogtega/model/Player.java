package com.ogtega.model;


import java.util.Random;

public class Player {

    private int strikes;
    private String name;
    private Display display;

    public Player(String name, Display display) {
        this.strikes = 0;
        this.name = name;
        this.display = display;
    }

    public Player(int strikes, String name, Display display) {
        this.strikes = strikes;
        this.name = name;
        this.display = display;
    }

    public char guess(char[] arr) {

        return arr[new Random().nextInt(arr.length)];
    }

    public String getName() {
        return name;
    }

    public int getStrikes() {
        return strikes;
    }

    public void strike() {
        this.strikes--;
    }
}
