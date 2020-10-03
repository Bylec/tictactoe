package tictactoe.rules;

import tictactoe.Tictactoe;

public class VerticalWin extends AbstractRule {
    @Override
    public void check(tictactoe.Tictactoe game, int[] coordinates) {
        String[][] board = game.getBoard();
        boolean gameFinished = true;
        for (int i = 0; i < board.length; i++) {
            if (!board[i][coordinates[1]].equals(game.getPlayerToMoveSign())) {
                gameFinished = false;
                break;
            }
        }
        handleGameState(gameFinished, game, coordinates);
    }
}
