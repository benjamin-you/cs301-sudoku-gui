package edu.wm.cs.cs301.sudoku.model;


/**
 * @author Benjamin You
 * The main task of the Arbitor is to make decision on the puzzle. As in, to arbitrate. It can be thought of as a little judge!
 * The Board object calls valid_move() from this class to check if the suggested value from PopupMenu is valid.
 * */
public class Arbitor {

    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;
    public static final int EMPTY = 0;

    private int[][] current; // the current version of the puzzle including valid player moves

    // The default constructor.
    public Arbitor(int[][] board) {

        current = board;

    }


    // Checks if the provided value is in the specified row. Returns true if it the value is found.
    private boolean value_in_row (int[][] board, int row_num, int val) {

        for (int c = 0; c < 9; ++c) {

            if (board[row_num][c] == val) return true;

        }

        return false;


    }


    // Checks if the provided value is in the specified column. Returns true if it the value is found.
    private boolean value_in_col (int[][] board, int col_num, int val) {

        for (int r = 0; r < NUM_ROWS; ++r) {

            if (board[r][col_num] == val) return true;

        }

        return false;


    }



    // Finds the sector that contains the coordinates (r, c) by capping the values of r and c.
    private int[][] findSector(int[][] board, int r, int c) {

        if (c < 3) {
            c = 0;
        }
        else if (c < 6) {
            c = 3;
        }
        else {
            c = 6;
        }

        if (r < 3) {
            r = 0;
        }
        else if (r < 6) {
            r = 3;
        }
        else {
            r = 6;
        }

        int[][] sector = new int[3][3];

        for (int i = 0; i < 3; ++i) {

            for (int j = 0; j < 3; ++j) {

                sector[i][j] = board[r+i][c+j];

            }

        }

        return sector;

    }


    // Checks if the value is in a board. Can also check if a value is in a sector.
    private boolean findValue(int[][] board, int value) {

        for (int[] row: board) {

            for (int elem: row) {

                if (elem == value) {
                    return true;
                }

            }

        }

        return false;

    }


    // Checks if a move is valid according to the project specifications AND the rules of Sudoku.
    /**
     * @author Benjamin You
     * The Board object calls this method. It rejects invalid moves via the last statement.
     * The last statement means ( if value not in row and value not in column and value not in sector ).
     * */
    public boolean validMove (int r, int c, int val) {

    // Finds the current sector.
        int[][] sector = findSector(current, r, c);

    // If a value that does not exist is being removed, also return false.
        if (val == 0 && current[r][c] == EMPTY) return false;

    // Otherwise, checks if the move is valid by the rules of Sudoku.
        return ( (!value_in_row(current, r, val)) && (!value_in_col(current, c, val)) && (!findValue(sector, val)) ); // general case, valid sudoku move

    }


}
