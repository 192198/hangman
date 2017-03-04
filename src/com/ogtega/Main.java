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

        gameDisplay(display, player, bot);
        round();
    }

    public static void playerTurn() {
        while (true) {
            if(!display.guess(Character.toLowerCase(userPrompt("guess").charAt(0)))) {
                player.strike();
                gameDisplay(display, player, bot);
                break;
            }

            player.point();
            gameDisplay(display, player, bot);
        }
    }

    public static void botTurn() {
        while(true) {

            char guess = bot.guess();
            userPrint("Bot guessed " + guess + "\n");
            if(!display.guess(guess)) {
                bot.strike();
                gameDisplay(display, player, bot);
                break;
            } else {
                bot.point();
                gameDisplay(display, player, bot);
            }
        }
    }

    private static void round() {
        while(true) {
            playerTurn();
            if(display.complete() || player.getStrikes() > 8 && bot.getStrikes() > 8){
                userPrint(getSummary() + "\n");
                break;
            }
            botTurn();
            if(display.complete() || player.getStrikes() > 8 && bot.getStrikes() > 8){
                userPrint(getSummary() + "\n");
                break;
            }
        }
    }

    private static String getSummary() {
        String res = "";

        System.out.println(player.getPoints() - bot.getPoints());

        if(player.getPoints() == bot.getPoints()) {
            return("You tied with a bot...");
        }

        res += (bot.getPoints() < player.getPoints()) ? "Congratulations you beat the bot by " + (player.getPoints() - bot.getPoints()) + "!" :
                "The bot won by " + (bot.getPoints() - player.getPoints()) + "...";

        return res;
    }

    private static void init() {
        display = new Display("magic-ass wand");

        bot = new Player("Bot", display);
        player = new Player(userPrompt("Enter your name"), display);

        userPrint("Welcome " + player.getName() + "\n");
    }
}
