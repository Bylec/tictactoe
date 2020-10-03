package tictactoe.players.computer.behaviors.in_one;

import tictactoe.Tictactoe;
import tictactoe.players.computer.AbstractComputerPlayer;
import tictactoe.players.computer.behaviors.AbstractBehavior;

abstract class AbstractInOneBehavior extends AbstractBehavior {
    abstract protected String getSignToCheck(AbstractComputerPlayer player);

    @Override
    public int[] apply(AbstractComputerPlayer player, String[][] board) {
        String signToCheck = this.getSignToCheck(player);

        int[] coordinates = checkHorizontalLinesForTwoTheRow(board, signToCheck);
        if (coordinates != null) {
            return coordinates;
        }

        String[][] reversedBoard = this.reverseBoard(board);
        coordinates = checkHorizontalLinesForTwoTheRow(reversedBoard, signToCheck, true);
        if (coordinates != null) {
            return coordinates;
        }

        coordinates = checkDiagonalForwardForTwoInTheRow(board, signToCheck);
        if (coordinates != null) {
            return coordinates;
        }

        coordinates = checkDiagonalBackwardForTwoInTheRow(board, signToCheck);
        if (coordinates != null) {
            return coordinates;
        }

        return applyNext(player, board);
    }

    protected int[] checkHorizontalLinesForTwoTheRow(String[][] board, String oppositeSign) {
        return this.checkHorizontalLinesForTwoTheRow(board, oppositeSign, false);
    }

    protected int[] checkHorizontalLinesForTwoTheRow(String[][] board, String oppositeSign, boolean reversed) {
        int[] blankSignCoordinate = new int[2];
        for (int i = 0; i < board.length; i++) {
            int blankCountInRow = 0;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].equals(Tictactoe.BLANK_SIGN)) {
                    blankCountInRow++;
                    blankSignCoordinate = new int[]{i, j};
                }
                if (board[i][j].equals(oppositeSign) || blankCountInRow > 1) {
                    break;
                }
                if (j == board[0].length - 1) {
                    if (reversed) {
                        return new int[]{blankSignCoordinate[1], blankSignCoordinate[0]};
                    }
                    return blankSignCoordinate;
                }
            }
        }

        return null;
    }

    protected int[] checkDiagonalForwardForTwoInTheRow(String[][] board, String oppositeSign) {
        int blankCountInRow = 0;
        int[] blankSignCoordinate = new int[2];
        outerloop:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == j) {
                    if (board[i][j].equals(Tictactoe.BLANK_SIGN)) {
                        blankCountInRow++;
                        blankSignCoordinate = new int[]{i, j};
                    }
                    if (board[i][j].equals(oppositeSign) || blankCountInRow > 1) {
                        break outerloop;
                    }
                    if (i == board.length - 1 && j == board.length - 1) {
                        return blankSignCoordinate;
                    }
                }

            }
        }

        return null;
    }

    protected int[] checkDiagonalBackwardForTwoInTheRow(String[][] board, String signToCheck) {
        int blankCountInRow = 0;
        int[] blankSignCoordinate = new int[2];
        outerloop:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i + j == board.length - 1) {
                    if (board[i][j].equals(Tictactoe.BLANK_SIGN)) {
                        blankCountInRow++;
                        blankSignCoordinate = new int[]{i, j};
                    }
                    if (board[i][j].equals(signToCheck) || blankCountInRow > 1) {
                        break outerloop;
                    }
                    if (i == board.length - 1) {
                        return blankSignCoordinate;
                    }
                }

            }
        }

        return null;
    }

    protected String[][] reverseBoard(String[][] board) {
        String[][] boardReversed = new String[3][3];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boardReversed[i][j] = board[j][i];
            }
        }

        return boardReversed;
    }

}
