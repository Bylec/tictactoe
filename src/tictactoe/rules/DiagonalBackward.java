package tictactoe.rules;

import tictactoe.Tictactoe;

public class DiagonalBackward extends AbstractRule {
    @Override
    public void check(Tictactoe game, int[] coordinates) {
        String[][] board = game.getBoard();
        int n = board.length;
        boolean gameFinished = false;
        if (coordinates[0] + coordinates[1] == n - 1) {
            for (int i = 0; i < n; i++) {
                if (!board[i][(n - 1) - i].equals(game.getPlayerToMoveSign())) {
                    break;
                }
                if (i == board.length - 1) {
                    gameFinished = true;
                }
            }
        }
        handleGameState(gameFinished, game, coordinates);
    }
}
