package tictactoe.players.computer;

import tictactoe.players.AbstractPlayer;
import tictactoe.players.computer.behaviors.AbstractBehavior;

public abstract class AbstractComputerPlayer extends AbstractPlayer {
    AbstractBehavior behaviorChain;
    String computerLevel;

    public AbstractComputerPlayer(String toMove) {
        super(toMove);

        this.behaviorChain = this.createBehaviorChain();
    }

    abstract protected AbstractBehavior createBehaviorChain();

    public int[] processTurn(String[][] board) {
        int[] coordinates = this.behaviorChain.apply(this, board);
        System.out.println("Making move level \"" + this.computerLevel + "\"");
        this.makeMove(board, coordinates);
        return coordinates;
    }
}
