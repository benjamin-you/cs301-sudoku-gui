package edu.wm.cs.cs301.sudoku.model;
import java.util.Random;


/**
 * @author Benjamin You
 * The primary goal of the Solver object is to generate a Sudoku puzzle. This is the generation portion of project II.
 * */
public class Solver {

    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;
    public static final int EMPTY = 0;
    public static int count = 0;
    private final int[][] current  = new int[NUM_ROWS][NUM_COLS]; // the current version of the puzzle including valid player moves
    private int[][] puzzle = new int[NUM_ROWS][NUM_COLS];

    // The default constructor.
    public Solver() {

// The constructor first generates an empty grid,
        for (int r = 0; r < NUM_ROWS; ++r) {

            for (int c = 0; c < NUM_COLS; ++c) {

                current[r][c] = EMPTY;

            }

        }

// then creates a Sudoku ("Sudo").
        createSudo();

    }


    // Returns the current puzzle.
    public int[][] getPuzzle() {

        return puzzle;

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


    // Shuffles an array. Used to diversify the first guess (per individual slots) of the Sudoku solver.
    private int[] shuffle (int[] list) {

        Random rn = new Random();
        int partner_index, temp;

        for (int i = 0; i < list.length; ++i) { // pair-wise swapping

            partner_index = rn.nextInt(list.length);
            temp = list[i];
            list[i] = list[partner_index];
            list[partner_index] = temp;

        }

        return list;

    }


    // Solves the Sudoku.
    private boolean solve () {

        int r, c;

        int[] numList = {1,2,3,4,5,6,7,8,9};

        for (int i = 0; i < 81; ++i) {

            r = i / 9;
            c = i % 9;

    // If the slot under consideration is empty,
            if (current[r][c] == EMPTY) {

    // Make a random guess.
                numList = shuffle(numList);

                for (int val: numList) {

                    int[][] sector = findSector(current, r, c);

    // If the same value is		found in the same row, 			the same column,		or the same sector, 	then try a different value.
                    if (  (!value_in_row(current, r, val)) && (!value_in_col(current, c, val)) && (!findValue(sector, val))  ) {

    // Otherwise, the guess works.
                        current[r][c] = val;

    // Continue this recursive branch to attempt to solve the Sudoku.
                        if (solve()) {

    // If the code makes it this far, it has found a solution. The count (of solutions) increases, and the code returns True.
                            ++count;
                            return true;

                        }

    // Otherwise, undo each guess made along the recursive branch.
                        else {

                            current[r][c] = EMPTY;

                        }

                    }

                }

    // If no value from 1-9 works, terminate the recursive branch.
                return false;

            }


        }

    // If the grid is filled and no problems are found, then the recursive branch ends.
        return true;

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


    // Copies the current puzzle into a separate array.
    private int[][] copyCurrent () {

        int[][] temp = new int[9][9];

        for (int i = 0; i < 9; ++i) {

            for (int j = 0; j < 9; ++j) {

                temp[i][j] = current[i][j];

            }

        }

        return temp;

    }


    // Reduces the Sudoku by one slot.
    private int[][] reduceSudo () {

        Random rn = new Random();

        int r, c;

    // First, find a non-empty slot.
        do {

            r = rn.nextInt(9);
            c = rn.nextInt(9);

        } while (current[r][c] == EMPTY);

    // Then, empty the slot, and copy the current array in case it is a valid puzzle.
        int temp = current[r][c];	    // copies the removed value
        current[r][c] = EMPTY;		    // empties the slot
        int[][] copy = copyCurrent();	// the array copy

    // Count the solutions for this state of the Sudoku.
        count = 0;
        solve();

    // If there is not (only) one solution,
        if (count != 1) {

    // Restore the slot's value and status as an "original" member of the Sudoku.
            current[r][c] = temp;

        }

        return copy;

    // If this is a valid Sudoku, immediately return it. (The current puzzle is now solved, and cannot be returned.)

    }


    // Creates a Sudoku
    private void createSudo () { // initialise count = 0

    // by populating the puzzle with values
        while (!this.solve()) {}

    // Then, the removal algorithm begins with a set number of reductions to be made. Progress in removing values is kept through intermediate copy arrays.
        int reductions = 10;
        int[][] copy;

        while (reductions > 0) {

            copy = this.reduceSudo();
            for (int i = 0; i < 9; ++i) {

                for (int j = 0; j < 9; ++j) {

                    current[i][j] = copy[i][j];

                }

            }

            --reductions;

        }

        puzzle = copyCurrent();

    }


}