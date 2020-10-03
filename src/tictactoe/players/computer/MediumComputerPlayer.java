package tictactoe.players.computer;

import tictactoe.players.computer.behaviors.AbstractBehavior;
import tictactoe.players.computer.behaviors.RandomBehavior;
import tictactoe.players.computer.behaviors.in_one.BlockWinBehavior;
import tictactoe.players.computer.behaviors.in_one.WinBehavior;

public class MediumComputerPlayer extends AbstractComputerPlayer {
    public MediumComputerPlayer(String toMove) {
        super(toMove);
        this.computerLevel = "medium";
    }

    @Override
    protected AbstractBehavior createBehaviorChain() {
        AbstractBehavior behaviorChain = new WinBehavior();

        behaviorChain.setNext(new BlockWinBehavior())
                .setNext(new RandomBehavior());

        return behaviorChain;
    }
}
