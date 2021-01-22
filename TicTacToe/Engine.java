package TicTacToe;

import java.util.Arrays.*;
import java.util.Scanner;

/**
 * Author: Daniel Hettinger
 *
 * Controls the logic and state of the TicTacToe game.
 */
public class Engine {

    // Constant for the dimensions of the board.
    private static int BOARD_SIZE = 3;

    // Keeps track of the current state of the board. A square can either have an 'X', 'O',
    // or 'NO_PLAYER' (meaning that it is empty).
    private Player board[][];

    private ComputerPlayer computerPlayer;

    /**
     * Constructor for the TicTacToe Engine.
     *
     * @param                           isHard controls the difficulty of the AI. If true, the
     *                                  AI will be set to the hardest setting. If false, it will
     *                                  be set to easy.
     */
    public Engine(boolean isHard) {
        board = new Player[3][3];

        // Loops through the 2D array and initializes all elements to NO_PLAYER
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = Player.NO_PLAYER;
            }
        }

        computerPlayer = new ComputerPlayer(isHard);
    }

    public void run() {
        int row, col;
        Scanner scan = new Scanner(System.in);
        while (evaluate() == Evaluation.NO_WINNER) {
            // COMPUTER'S TURN
            printBoard();
            System.out.print("Press enter to let the computer play");
            scan.next();
            computerPlayer.play(board, BOARD_SIZE);
            System.out.println("\n\n\n");

            if (evaluate() == Evaluation.X_WINS) {
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n");
                printBoard();
                System.out.print("X WINS");
                break;

            } else if (evaluate() == Evaluation.INVALID) {
                throw new RuntimeException("ERROR!");

            } else if (isFullBoard()) {
                System.out.print("\n\n\n\n\n\n\n\n\nTIE!");
                break;
            }

            // HUMAN TURN
            doPlayerTurn();

            if (evaluate() == Evaluation.O_WINS) {
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n");
                printBoard();
                System.out.print("O WINS");
                break;

            } else if (evaluate() == Evaluation.INVALID) {
                throw new RuntimeException("ERROR!");
            }
        }
    }

    private Evaluation evaluate() {
        if (isInvalidState()) {
            return Evaluation.INVALID;

        } else if (checkWinner(Player.X)) {
            return Evaluation.X_WINS;

        } else if (checkWinner(Player.O)) {
            return Evaluation.O_WINS;

        } else {
            return Evaluation.NO_WINNER;
        }
    }

    private boolean checkWinner(Player player) {
        // Make sure that NO_PLAYER isn't checked.
        if (player == Player.NO_PLAYER) {
            throw new RuntimeException("ERROR: The game tried to check is \"NO_PLAYER\" was winner");
        }

        return (hasWonRows(player) || hasWonColumns(player) || hasWonLtoR(player) || hasWonRtoL(player));
    }

    /**
     * Checks the rows for a win.
     *
     * @param                           player is the player that is being checked for.
     * @return                          true if the player has gotten three in a row.
     */
    private boolean hasWonRows(Player player) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                if (player != board[row][col]) {
                    break;

                } else if(col == (BOARD_SIZE - 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks the columns for a win.
     *
     * @param                           player is the player that is being checked for.
     * @return                          true if the player has gotten three in a column.
     */
    private boolean hasWonColumns(Player player) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) {

                if (player != board[row][col]) {
                    break;

                } else if (row == (BOARD_SIZE - 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks the L to R diagonal for a win.
     *
     * @param                           player is the player that is being checked for.
     * @return                          true if the player has gotten three diagonally from left to right.
     */
    private boolean hasWonLtoR(Player player) {
        for (int i = 0; i < BOARD_SIZE; i++) {

            if (player != board[i][i]) {
                break;

            } else if (i == (BOARD_SIZE - 1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks the R to L diagonal for a win.
     *
     * @param                           player is the player that is being checked for.
     * @return                          true if the player has gotten three diagonally from right to left.
     */
    private boolean hasWonRtoL(Player player) {
        for (int col = (BOARD_SIZE - 1), row = 0; row < BOARD_SIZE; col--, row++) {

            if (player != board[row][col]) {
                break;

            } else if (row == (BOARD_SIZE - 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean isInvalidState() {
        int xCount = 0;
        int oCount = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                if (board[row][col] == Player.X) {
                    xCount++;

                } else if (board[row][col] == Player.O) {
                    oCount++;
                }
            }
        }

        //O pieces must be equal to or one less than X pieces
        return ( !( xCount == (oCount + 1) ) && !( xCount == oCount) );
    }

    private void printBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print("|");

                switch (board[row][col]) {
                    case NO_PLAYER -> System.out.print("*");
                    case O -> System.out.print("O");
                    case X -> System.out.print("X");
                }

                System.out.print("|");
            }

            System.out.print("\n");
        }
    }

    private void doPlayerTurn() {
        Scanner scan = new Scanner(System.in);
        int row = 0, col = 0;
        boolean hasPlayedCorrect = true;

        printBoard();

        do {
            // Get input.
            System.out.print("Which row would you like?");
            row = scan.nextInt();
            System.out.print("\nWhich column would you like?");
            col = scan.nextInt();

            if (row < BOARD_SIZE && col < BOARD_SIZE && board[row][col] == Player.NO_PLAYER) {
                hasPlayedCorrect = true;
                board[row][col] = Player.O;

            } else {
                hasPlayedCorrect = false;
                System.out.print("Sorry, incorrect intput\n");
            }
        } while (!hasPlayedCorrect);
    }

    private boolean isFullBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                if (board[row][col] == Player.NO_PLAYER) {
                    return false;
                }
            }
        }

        return true;
    }
}
