//Using random Algo to win
public class random {
    //private class with move index
    static class Move {
        int row, col;
    } ;
    //update later with choices, this is a sample
    static char player = 'x', computer = 'o';

    public static Move random(char board[][]) {
        Move best = new Move();
        best.row = -1;
        best.col = -1;
        for(int row = 0; row < 3; row ++) {
            for(int col = 0; col < 3; col ++)
            {
                if (board[row][col] == '_') {
                    best.row = row;
                    best.col = col;
                }
            }
        }
        return best;
    }


    //testing driver
    public static void main(String[] args) {
        //testing board
        char board[][] = {{'x', '_', 'x'},
                {'o', '_', 'x'},
                {'_', '_', '_'}};

        Move bestMove = findBestMove(board);

        //change board instead of just showing
        System.out.printf("Computer Plays :\n");
        System.out.printf("ROW: %d COL: %d\n\n",
                bestMove.row, bestMove.col);
    }
}
