package com.ogtega;

import com.ogtega.model.Display;
import com.ogtega.model.Player;

import static com.ogtega.util.GameUtils.*;

public class Main {

    private static Display display;

    private static Player bot;
    private static Player player;

    public static void main(String[] args) {

        init();

        gameDisplay(display);
        round();
    }

    public static void playerTurn() {
        while (true) {
            if(!display.guess(userPrompt("guess: ").charAt(0))) {
                player.strike();
                break;
            }

            gameDisplay(display);
            userPrint(getSummary() + "\n");
        }
    }

    public static void botTurn() {
        while(true) {

            char guess = bot.guess(display.getWhitelist());
            userPrint("Bot guessed " + guess + "\n");
            if(!display.guess(guess)) {
                gameDisplay(display);
                bot.strike();
                break;
            }
        }
    }

    private static void round() {
        while(true) {
            playerTurn();
            botTurn();
            if(display.complete()){
                break;
            }
        }
    }

    private static String getSummary() {
        String res = "";

        res += (player.getStrikes() < bot.getStrikes()) ? "Congratulations you beat the bot by " + (bot.getStrikes() - player.getStrikes()) + " points!" :
                "The bot won by " + (player.getStrikes() - bot.getStrikes()) + "...";

        return res;
    }

    private static void init() {
        display = new Display("magic-ass wand");

        bot = new Player("Bot", display);
        player = new Player(userPrompt("Enter your name"), display);

        userPrint("Welcome " + player.getName() + "\n");
    }
}
