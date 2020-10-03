package tictactoe.players;

import java.util.Scanner;

public class UserPlayer extends AbstractPlayer {
    private final Scanner scanner = new Scanner(System.in);

    public UserPlayer(String toMove) {
        super(toMove);
    }

    @Override
    public int[] processTurn(String[][] board) {
        while (true) {
            String inputCoordinates = this.enterCoordinates();
            String[] coordinatesArray = inputCoordinates.split(" ");
            if (this.validateCoordinates(coordinatesArray)) {
                int[] coordinates = this.parseCoordinates(coordinatesArray);
                if (checkCellIsFree(board, coordinates)) {
                    this.makeMove(board, coordinates);
                    return coordinates;
                } else {
                    this.errorMsg = "This cell is occupied! Choose another one!";
                    this.printError();
                }
            } else {
                this.printError();
            }
        }
    }

    private String enterCoordinates() {
        System.out.print("Enter the coordinates:");
        return scanner.nextLine();
    }

    private int[] parseCoordinates(String[] coordinates) {
        int[] intCoordinates = new int[2];
        intCoordinates[0] = 3 - Integer.parseInt(coordinates[1]);
        intCoordinates[1] = Integer.parseInt(coordinates[0]) - 1;

        return intCoordinates;
    }

    private boolean validateCoordinates(String[] coordinates) {
        for (String coordinate : coordinates) {
            if (!this.validate(coordinate)) {
                return false;
            }
        }

        if (coordinates.length != 2) {
            this.errorMsg = "You should enter two coordinates!";
            return false;
        }

        return true;
    }

    private boolean validate(String coordinate) {
        if (isNotNumeric(coordinate)) {
            this.errorMsg = "You should enter numbers!";
            return false;
        }

        if (isNotWithinRange(coordinate)) {
            this.errorMsg = "Coordinates should be from 1 to 3!";
            return false;
        }

        return true;
    }

    private boolean isNotWithinRange(String s) {
        int valueToCheck = Integer.parseInt(s);
        return valueToCheck <= 0 || valueToCheck >= 4;
    }

    private static boolean isNotNumeric(String s) {
        return s == null || !s.matches("[-+]?\\d*\\.?\\d+");
    }
}
