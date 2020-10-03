package tictactoe.players.computer.behaviors.in_one;

import tictactoe.players.computer.AbstractComputerPlayer;

public class BlockWinBehavior extends AbstractInOneBehavior {
    @Override
    protected String getSignToCheck(AbstractComputerPlayer player) {
        return player.getToMoveSign();
    }
}
