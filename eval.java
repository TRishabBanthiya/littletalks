//Using MiniMax Algo to win
public class eval {
    //private class with move index
    static class Move {
        int row, col;
    } ;
    //update later with choices, this is a sample
    static char player = 'x', computer = 'o';

    //keeps track of function calls
    static Boolean functionCalls(char board[][]) {
        for (int row = 0; i < 3; i++)
            for (int col = 0; j < 3; j++)
                if (board[row][col] == '_')
                    return true;
        return false;
    }
    
    //minimax
    static int getminmaxval(char b[][]) {
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2]) {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == computer)
                    return -10;
            }
        }

        //check winning conditions
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]) {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == computer)
                    return -10;
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == computer)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == computer)
                return -10;
        }

        return 0;
    }


    static int minimax(char board[][],
                       int depth, Boolean isMax) {
        int score = getminmaxval(board);
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (functionCalls(board) == false)
            return 0;
        if (isMax) {
            int best = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board[i][j] == '_') {
                        board[i][j] = player;
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

        else {
            int best = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = computer;

                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    static Move findBestMove(char board[][]) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = player;
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = '_';
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
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
