package tictactoe.players.computer;

import tictactoe.players.computer.behaviors.AbstractBehavior;
import tictactoe.players.computer.behaviors.RandomBehavior;

public class EasyComputerPlayer extends AbstractComputerPlayer {

    public EasyComputerPlayer(String toMove) {
        super(toMove);
        this.computerLevel = "easy";
    }

    @Override
    protected AbstractBehavior createBehaviorChain() {
        return new RandomBehavior();
    }
}
