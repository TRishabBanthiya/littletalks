package TicTacToe;

import java.util.Arrays.*;

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
        Evaluation evaluation = Evaluation.NO_WINNER;
        while (evaluation == Evaluation.NO_WINNER) {
            // Do some shit
        }
    }

    private Evaluation evaluate() {
        return Evaluation.INVALID;
    }

    private boolean checkWinner(Player player) {
        // Make sure that NO_PLAYER isn't checked.
        if (player == Player.NO_PLAYER) {
            throw new RuntimeException("ERROR: The game tried to check is \"NO_PLAYER\" was winner");
        }

        //TODO come back to this!
        return true;
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


}
