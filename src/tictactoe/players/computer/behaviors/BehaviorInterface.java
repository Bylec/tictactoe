package tictactoe.players.computer.behaviors;

import tictactoe.players.computer.AbstractComputerPlayer;

public interface BehaviorInterface {
    public int[] apply(AbstractComputerPlayer player, String[][] board);
}
