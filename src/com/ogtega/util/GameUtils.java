package com.ogtega.util;

import com.ogtega.model.Display;
import com.ogtega.model.Player;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameUtils {

    private static String logs = "\n\n";
    private static final String clear = "\033[H\033[2J";
    private static final Scanner reader = new Scanner(System.in);
    private static final String[] words = {"meerkat","aardvark","addax","alligator","alpaca","anteater","antelope","aoudad","ape","argali","armadillo","baboon","badger","basilisk","bat","bear","beaver","bighorn","bison","boar","budgerigar","buffalo","bull","bunny","burro","camel","canary","capybara","cat","chameleon","chamois","cheetah","chimpanzee","chinchilla","chipmunk","civet","coati","colt","cougar","cow","coyote","crocodile","crow","deer","dingo","doe","dung beetle","dog","donkey","dormouse","dromedary","duckbill platypus","dugong","eland","elephant","elk","ermine","ewe","fawn","ferret","finch","fish","fox","frog","gazelle","gemsbok","gila monster","giraffe","gnu","goat","gopher","gorilla","grizzly bear","ground hog","guanaco","guinea pig","hamster","hare","hartebeest","hedgehog","highland cow","hippopotamus","hog","horse","hyena","ibex","iguana","impala","jackal","jaguar","jerboa","kangaroo","kitten","koala","lamb","lemur","leopard","lion","lizard","llama","lovebird","lynx","mandrill","mare","marmoset","marten","mink","mole","mongoose","monkey","moose","mountain goat","mouse","mule","musk deer","musk-ox","muskrat","mustang","mynah bird","newt","ocelot","okapi","opossum","orangutan","oryx","otter","ox","panda","panther","parakeet","parrot","peccary","pig","octopus","thorny devil","starfish","blue crab","snowy owl","chicken","rooster","bumble bee","eagle owl","polar bear","pony","porcupine","porpoise","prairie dog","pronghorn","puma","puppy","quagga","rabbit","raccoon","ram","rat","reindeer","rhinoceros","salamander","seal","sheep","shrew","silver fox","skunk","sloth","snake","springbok","squirrel","stallion","steer","tapir","tiger","toad","turtle","vicuna","walrus","warthog","waterbuck","weasel","whale","wildcat","bald eagle","wolf","wolverine","wombat","woodchuck","yak","zebra","zebu"};

    public static String userPrompt(String str) {
        String res = "";

        userPrint(str + " : ");

        while (res.matches("")) {
            res = reader.nextLine();
        }
        return serialize(res);
    }

    public static void userPrint(String str) {
        System.out.print("> " + str);
        System.out.print("\033[s");
        System.out.print(logs);
        System.out.print("\033[u");
    }

    public static void userPrint(String str, boolean log) {
        System.out.print("> " + str);
        logs += str;
    }

    public static void pageBreak() {
        System.out.println(clear);
        System.out.flush();
    }

    public static String replace(String str, int index, char c) {
        String pre = str.substring(0, index);
        String post = str.substring(index + 1);
        return pre + c + post;
    }

    public static String randomWord() {
        return words[new Random().nextInt(words.length)];
    }

    public static void gameDisplay(Display input, Player player, Player bot) {
        pageBreak();
        System.out.print(player.getName() + ": " + player.getPoints() + " | Bot: " + bot.getPoints() + " " + String.valueOf(input.getBlacklist()) + "\n\n");
        System.out.print(input + " " + "\n\n");
    }

    public static void clearLogs() {
        logs = "\n\n";
    }

    private static String serialize(String str) {

        try {
            return new String(str.getBytes("UTF-8"), Charset.forName("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
