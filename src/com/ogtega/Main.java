package com.ogtega;

import com.ogtega.model.Display;
import com.ogtega.model.Player;

import static com.ogtega.util.GameUtils.*;

public class Main {

    private static Display display;

    private static Player bot;
    private static Player player;

    public static void main(String[] args) {

        pageBreak();
        init();
        gameDisplay(display, player, bot);

        while(true) {
            round();

            if(userPrompt("Play again (Y/n)").equalsIgnoreCase("n")) {
                pageBreak();
                userPrint("Thanks for playing!");
                break;
            }
            display = new Display(randomWord());
            clearLogs();
            player.reset();
            bot.reset();
            pageBreak();
            gameDisplay(display, player, bot);
        }
    }

    private static void playerTurn() {
        while (true) {
            if (!display.guess(Character.toLowerCase(userPrompt("guess").charAt(0)), player)) {
                gameDisplay(display, player, bot);
                break;
            }

            gameDisplay(display, player, bot);
        }
    }

    private static void botTurn() {
        while (true) {

            char guess = bot.guess();
            userPrint("Bot guessed " + guess + "\n", true);
            if (!display.guess(guess, bot)) {
                gameDisplay(display, player, bot);
                break;
            } else {
                gameDisplay(display, player, bot);
            }
        }
    }

    private static void round() {
        while (true) {
            playerTurn();
            if (display.complete()) {
                gameDisplay(display, player, bot);
                userPrint(getSummary() + "\n");
                break;
            }
            botTurn();
            if (display.complete()) {
                gameDisplay(display, player, bot);
                userPrint(getSummary() + "\n");
                break;
            }
        }
    }

    private static String getSummary() {
        String res = "";

        if (player.getPoints() == bot.getPoints()) {
            return ("You tied with a bot...");
        }

        res += (bot.getPoints() < player.getPoints()) ? "Congratulations you beat the bot by " + (player.getPoints() - bot.getPoints()) + "!" :
                "The bot won by " + (bot.getPoints() - player.getPoints()) + "...";

        return res;
    }

    private static void init() {
        display = new Display(randomWord());

        bot = new Player("Bot", display);
        player = new Player(userPrompt("Enter your name"), display);

        userPrint("Welcome " + player.getName() + "\n");
    }
}
