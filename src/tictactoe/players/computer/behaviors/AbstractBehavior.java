package tictactoe.players.computer.behaviors;

import tictactoe.players.computer.AbstractComputerPlayer;

public abstract class AbstractBehavior implements BehaviorInterface {
    private AbstractBehavior next;

    public AbstractBehavior setNext(AbstractBehavior next) {
        this.next = next;
        return next;
    }

    protected int[] applyNext(AbstractComputerPlayer player, String[][] board) {
        return next.apply(player, board);
    }
}
