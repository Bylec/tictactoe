package tictactoe.rules;

import tictactoe.Tictactoe;

interface RuleInterface {
    public void check(Tictactoe game, int[] coordinates);
}
