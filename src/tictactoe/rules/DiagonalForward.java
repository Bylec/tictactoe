package tictactoe.rules;

import tictactoe.Tictactoe;

public class DiagonalForward extends AbstractRule {
    @Override
    public void check(tictactoe.Tictactoe game, int[] coordinates) {
        String[][] board = game.getBoard();
        boolean gameFinished = false;
        if (coordinates[0] == coordinates[1]) {
            for (int i = 0; i < board.length; i++) {
                if (!board[i][i].equals(game.getPlayerToMoveSign())) {
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
