package tictactoe.players.computer.behaviors.in_one;

import tictactoe.Tictactoe;
import tictactoe.players.computer.AbstractComputerPlayer;

public class WinBehavior extends AbstractInOneBehavior {
    @Override
    protected String getSignToCheck(AbstractComputerPlayer player) {
        return tictactoe.Tictactoe.getOppositeSign(player.getToMoveSign());
    }
}
