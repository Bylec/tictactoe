package tictactoe.players;

public abstract class AbstractPlayer implements Player {

    private final String toMoveSign;
    protected String errorMsg;

    public AbstractPlayer(String toMoveSign) {
        this.toMoveSign = toMoveSign;
    }

    abstract public int[] processTurn(String[][] board);

    public boolean checkCellIsFree(String[][] board, int[] coordinates) {
        return board[coordinates[0]][coordinates[1]].equals(" ");
    }

    public void makeMove(String[][] board, int[] coordinates) {
        board[coordinates[0]][coordinates[1]] = this.toMoveSign;
    }

    public String getToMoveSign() {
        return toMoveSign;
    }

    protected void printError() {
        System.out.println(this.errorMsg);
    }
}
