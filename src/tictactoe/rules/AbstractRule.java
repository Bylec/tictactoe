package tictactoe.rules;

import tictactoe.Tictactoe;

public abstract class AbstractRule implements RuleInterface {
    private AbstractRule next;

    public AbstractRule setNext(AbstractRule next) {
        this.next = next;
        return next;
    }

    public abstract void check(tictactoe.Tictactoe game, int[] coordinates);

    protected void checkNext(tictactoe.Tictactoe game, int[] coordinates) {
        if (next != null) {
            next.check(game, coordinates);
        }
    }

    protected void handleGameState(boolean gameFinished, tictactoe.Tictactoe game, int[] coordinates) {
        if (gameFinished) {
            this.setWinState(game);
        } else {
            checkNext(game, coordinates);
        }
    }

    protected void setWinState(tictactoe.Tictactoe game) {
        game.setWinState();
        System.out.println(game.getPlayerToMoveSign() + " wins");
    }
}
