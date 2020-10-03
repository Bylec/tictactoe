package tictactoe;

import tictactoe.players.AbstractPlayer;
import tictactoe.players.UserPlayer;
import tictactoe.players.computer.EasyComputerPlayer;
import tictactoe.players.computer.MediumComputerPlayer;
import tictactoe.rules.AbstractRule;
import tictactoe.rules.DiagonalBackward;
import tictactoe.rules.DiagonalForward;
import tictactoe.rules.GameFinishedByDraw;
import tictactoe.rules.HorizontalWin;
import tictactoe.rules.VerticalWin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tictactoe {
    public enum State {
        NOT_FINISHED("Game not finished"),
        DRAW("Draw"),
        X("X wins"),
        O("O wins"),
        GAME_TERMINATED("Game terminated");

        String state;

        State(String state) {
            this.state = state;
        }
    }

    public static final String USER = "user";
    public static final String EASY_COMPUTER = "easy";
    public static final String MEDIUM_COMPUTER = "medium";

    public static final String EXIT = "exit";
    public static final String START = "start";

    public static final String X_SIGN = "X";
    public static final String O_SIGN = "O";
    public static final String BLANK_SIGN = " ";

    private State state;
    private final String[][] board = new String[3][3];

    private AbstractPlayer playerOne;
    private AbstractPlayer playerTwo;
    
    private AbstractPlayer playerToMove;

    Tictactoe() {
        this.state = State.NOT_FINISHED;
        this.createBoard();
    }

    void run() {
        tictactoe.Menu menu = new tictactoe.Menu();
        String[] configArray = menu.start();

        if (terminateGame(configArray)) {
            this.setTerminateGameState();
        } else {
            try {
                this.setPlayers(configArray);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                this.setTerminateGameState();
            }
        }

        this.printBoard();

        while (this.gameNotFinished()) {
            this.processTurn(this.playerToMove);
        }
    }

    private void processTurn(AbstractPlayer player) {
        int[] playerOneCoordinates = player.processTurn(this.board);
        this.printBoard();
        this.checkGamesState(playerOneCoordinates);

        setPlayerToMove();
    }

    private void setPlayers(String[] configArray) throws Exception {
        createPlayerOne(configArray[1]);
        createPlayerTwo(configArray[2]);
    }

    private void createPlayerOne(String playerConfigString) throws Exception {
        this.playerOne = getPlayerFromConfig(playerConfigString, tictactoe.Tictactoe.X_SIGN);
        this.playerToMove = this.playerOne;
    }

    private void createPlayerTwo(String playerConfigString) throws Exception {
        this.playerTwo = getPlayerFromConfig(playerConfigString, tictactoe.Tictactoe.O_SIGN);
    }

    private AbstractPlayer getPlayerFromConfig(String playerConfigString, String sign) throws Exception {
        AbstractPlayer player;
        switch(playerConfigString) {
            case tictactoe.Tictactoe.USER:
                player = new UserPlayer(sign);
                break;
            case tictactoe.Tictactoe.MEDIUM_COMPUTER:
                player = new MediumComputerPlayer(sign);
                break;
            case tictactoe.Tictactoe.EASY_COMPUTER:
                player = new EasyComputerPlayer(sign);
                break;
            default:
                throw new Exception("Couldn't create player from config: " + playerConfigString);
        }

        return player;
    }

    public String[][] getBoard() {
        return this.board.clone();
    }

    public String getPlayerToMoveSign() {
        return this.playerToMove.getToMoveSign();
    }

    private boolean terminateGame(String[] configArray) {
        return configArray[0].equals(tictactoe.Tictactoe.EXIT);
    }

    private void setPlayerToMove() {
        AbstractPlayer playerToMove;
        if (this.playerToMove.equals(this.playerOne)) {
            playerToMove = this.playerTwo;
        } else {
            playerToMove = this.playerOne;
        }
        
        this.playerToMove = playerToMove;
    }

    public static String getOppositeSign (String sign) {
        if (sign.equals(tictactoe.Tictactoe.X_SIGN)) {
            return tictactoe.Tictactoe.O_SIGN;
        }
        return tictactoe.Tictactoe.X_SIGN;
    }

    private void checkGamesState(int[] intCoordinates) {
        AbstractRule finishedByDraw = new GameFinishedByDraw();
        finishedByDraw.setNext(new VerticalWin())
            .setNext(new HorizontalWin())
            .setNext(new DiagonalForward())
            .setNext(new DiagonalBackward());

        finishedByDraw.check(this, intCoordinates);
    }

    public void setDrawState() {
        this.state = State.DRAW;
    }

    public void setWinState() {
        this.state = State.valueOf(this.playerToMove.getToMoveSign());
    }

    private void setTerminateGameState() {
        this.state = State.GAME_TERMINATED;
    }

    private boolean gameNotFinished() {
        return this.state.equals(State.NOT_FINISHED);
    }

    private void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            List<String> lineOfMarks = Arrays.asList(this.board[i]);

            String joinedString = lineOfMarks
                    .stream()
                    .collect(Collectors.joining(" ", "| ", " |"));

            System.out.println(joinedString);
        }
        System.out.println("---------");
    }

    private void createBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = tictactoe.Tictactoe.BLANK_SIGN;
            }
        }
    }
}
