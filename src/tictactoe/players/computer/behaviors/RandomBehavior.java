package tictactoe.players.computer.behaviors;

import tictactoe.players.computer.AbstractComputerPlayer;

import java.util.concurrent.ThreadLocalRandom;

public class RandomBehavior extends AbstractBehavior {
    @Override
    public int[] apply(AbstractComputerPlayer player, String[][] board) {
        while(true) {
            int[] coordinates = generateRandomCoordinates();
            if (player.checkCellIsFree(board, coordinates)) {
                return coordinates;
            }
        }
    }

    protected int[] generateRandomCoordinates() {
        int[] coordinatesArray = new int[2];

        coordinatesArray[0] = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        coordinatesArray[1] = ThreadLocalRandom.current().nextInt(0, 2 + 1);

        return coordinatesArray;
    }
}
