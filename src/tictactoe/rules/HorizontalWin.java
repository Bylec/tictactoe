package tictactoe.rules;

import tictactoe.Tictactoe;

public class HorizontalWin extends AbstractRule {
    @Override
    public void check(Tictactoe game, int[] coordinates) {
        String[][] board = game.getBoard();
        boolean gameFinished = true;
        for (int i = 0; i < board.length; i++) {
            if (!board[coordinates[0]][i].equals(game.getPlayerToMoveSign())) {
                gameFinished = false;
                break;
            }
        }
        handleGameState(gameFinished, game, coordinates);
    }
}
