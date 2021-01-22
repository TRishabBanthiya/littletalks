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

    }

    private void evaluate() {

    }
}
