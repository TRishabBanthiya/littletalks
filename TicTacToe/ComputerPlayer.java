package TicTacToe;

/**
 * Author: Daniel Hettinger
 *
 * Basic outline for the AI player. Checks the board starting from the first row going
 * across the columns for an empty square, and plays on the first one found.
 */
public class ComputerPlayer {

    // This is the boolean for the AI player's difficulty. If true, the computer will use it's more
    // difficult strategy.
    private boolean isHard;

    /**
     * Constructor for the ComputerPlayer.
     *
     * @param                           isHard determines the computer's strategy.
     */
    public ComputerPlayer(boolean isHard) {
        this.isHard = isHard;
    }
}
