package tictactoe.rules;

import tictactoe.Tictactoe;

public class GameFinishedByDraw extends AbstractRule {
    @Override
    public void check(Tictactoe game, int[] coordinates) {
        String[][] board = game.getBoard();
        boolean gameFinished = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].equals(" ")) {
                    gameFinished = false;
                    break;
                }
            }
        }
        if (gameFinished) {
            setDrawState(game);
        } else {
            checkNext(game, coordinates);
        }
    }

    private void setDrawState(Tictactoe game) {
        game.setDrawState();
        System.out.println("Draw");
    }
}
