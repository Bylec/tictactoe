package tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Menu {
    private final Scanner scanner = new Scanner(System.in);

    private final List<String> availablePlayerTypes = Arrays.asList(
            Tictactoe.USER,
            Tictactoe.EASY_COMPUTER,
            Tictactoe.MEDIUM_COMPUTER
    );

     String[] start() {
        while (true) {
            System.out.print("Input command: ");
            String configLine = scanner.nextLine();
            String[] arrayConfig = configLine.split(" ");
            if (validateConfig(arrayConfig)) {
                return arrayConfig;
            }
        }
    }

    private boolean validateConfig(String[] config) {
        if (config.length == 1 && config[0].equals(Tictactoe.EXIT)) {
            return true;
        }

        if (config.length != 3) {
            return stop();
        }

        if (config[0].equals(Tictactoe.START) && checkMenuOption(config[1]) && checkMenuOption(config[2])){
            return true;
        }

        return stop();
    }

    private boolean checkMenuOption(String menuOption) {
        return availablePlayerTypes.contains(menuOption);
    }

    private boolean stop() {
        System.out.println("Bad parameters!");
        return false;
    }
}
