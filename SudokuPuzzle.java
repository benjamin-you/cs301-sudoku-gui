package edu.wm.cs.cs301.sudoku.model;

import java.util.Random;

/**
 * @author Benjamin You
 * The SudokuPuzzle class is acopy and paste from Project 1 to appease the autograder. It is not related to the rest of Project 3b.
 */
public class SudokuPuzzle {

    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;

    public static final int EMPTY = 0;
    public static int count = 0;

    // the current version of the puzzle including valid player moves
    private final int[][] current  = new int[NUM_ROWS][NUM_COLS];

    // an array of bits that represents whether the respective slot in current is an original value.
    private final int[][] original = new int[NUM_ROWS][NUM_COLS];

    // the solution to the current puzzle
    private final int[][] solution = new int[NUM_ROWS][NUM_COLS];


    // The default constructor.
    public SudokuPuzzle() {

// The constructor first generates an empty grid,
        for (int r = 0; r < NUM_ROWS; ++r) {

            for (int c = 0; c < NUM_COLS; ++c) {

                current[r][c] = EMPTY;

            }

        }

// then creates a Sudoku ("Sudo").
        createSudo();

    }


    // Another constructor to define the Sudoku off a pre-constructed board. Used for printing the solution.
    public SudokuPuzzle(int[][] board) {

        for (int r = 0; r < NUM_ROWS; ++r) {

            for (int c = 0; c < NUM_COLS; ++c) {

                current[r][c] = board[r][c];

            }

        }

    }


    // Returns the current puzzle.
    public int[][] getCurrent() {

        return current;

    }


    // Returns the solution.
    public int[][] getSolution() {

// While the puzzle is not solved, continue to solve it.
        while (!this.solveSudo()) {}

// Then copy the solution into the solution array.
        for (int k = 0; k < 9; ++k) {

            for (int j = 0; j < 9; ++j) {

                solution[k][j] = current[k][j];

            }

        }

        return solution;

    }


    // Cosmetic function to print a horizontal line.
    private void horizontal_line () {

        System.out.print(" ");
        System.out.print("+");
        for (int t = 0; t < NUM_COLS + (NUM_COLS / 3) - 1; ++t) {
            System.out.print("-");
        }
        System.out.println("+");

    }


    // Public function to print the current Sudoku in a special format.
    public void print () {

        System.out.println("  ABC DEF GHI ");

        for (int t = 0; t < NUM_ROWS; ++t) {

            if (t % 3 == 0) {
                horizontal_line ();		// divides grids vertically
            }

            System.out.print((char) (t + 65)); 	// prints the letters

            for (int s = 0; s < NUM_COLS; ++s) {

                if (s % 3 == 0) {
                    System.out.print("|");	// divides grids horizontally
                }
                System.out.print(current[t][s]);// prints the elements

            }

            System.out.println("|");

        }

        horizontal_line ();			// divides grids vertically

    }


    // Checks if the board is full. Returns true if full.
    private boolean fillCheck (int[][] board) {

        for (int r = 0; r < NUM_ROWS; ++r) {

            for (int c = 0; c < NUM_COLS; ++c) {

                if (board[r][c] == EMPTY) return false;

            }

        }

        return true;


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
    public boolean solveSudo () {

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
                        if (solveSudo()) {

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
    public int[][] findSector(int[][] board, int r, int c) {

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
    public boolean findValue(int[][] board, int value) {

        for (int[] row: board) {

            for (int elem: row) {

                if (elem == value) {
                    return true;
                }

            }

        }

        return false;

    }


    // Checks if there are any zeros (empty slots) left in the Sudoku.
    public boolean emptyCheck () {

        for (int[] row: current) {

            for (int elem: row) {

                if (elem == EMPTY) return false;

            }

        }

        return true;

    }


    // Copies the current puzzle into a separate array.
    public int[][] copyCurrent () {

        int[][] temp = new int[9][9];

        for (int i = 0; i < 9; ++i) {

            for (int j = 0; j < 9; ++j) {

                temp[i][j] = current[i][j];

            }

        }

        return temp;

    }


    // Reduces the Sudoku by one slot.
    public int[][] reduceSudo () {

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
        original[r][c] = 0;		        // this slot is no longer part of the "original" (WIP) Sudoku
        int[][] copy = copyCurrent();	// the array copy

// Count the solutions for this state of the Sudoku.
        count = 0;
        solveSudo();

// If there is not (only) one solution,
        if (count != 1) {

// Restore the slot's value and status as an "original" member of the Sudoku.
            current[r][c] = temp;
            original[r][c] = 1;


        }

// If this is a valid Sudoku, immediately return it. (The current puzzle is now solved, and cannot be returned.)
        return copy;

    }


    // Creates a Sudoku
    public void createSudo () { // initialise count = 0

// by populating the puzzle with values
        while (!this.solveSudo()) {}

// and saving this intermediate result as a solution.
        for (int k = 0; k < 9; ++k) {

            for (int j = 0; j < 9; ++j) {

                solution[k][j] = current[k][j];

            }

        }

// Then, the removal algorithm begins with a set number of reductions to be made. Progress in removing values is kept through intermediate copy arrays.
        int reductions = 27;
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

// Sets the remaining values in the Sudoku as originals.
        for (int i = 0; i < 9; ++i) {

            for (int j = 0; j < 9; ++j) {

                if (current[i][j] != EMPTY) {
                    original[i][j] = 1;
                }
                else {
                    original[i][j] = 0;
                }

            }

        }

    }


    // Checks if an element (value) can be added to the Sudoku.
    public boolean addElem (int r, int c, int val) {

// If the value is out of bounds or not a value move, return false.
        if ( (r > 8) || (c > 8) || (val < 0) || (val > 9) ) return false;
        if (!validMove(r, c, val)) return false;

// Otherwise, add the element
        current[r][c] = val;

// and return true.
        return true;

    }


    // Checks if a move is valid according to the project specifications AND the rules of Sudoku.
    public boolean validMove (int r, int c, int val) {

// Finds the current sector.
        int[][] sector = findSector(current, r, c);

// If an original is being overwritten, return false.
        if (original[r][c] == 1) return false;

// If a value that does not exist is being removed, also return false.
        if (val == 0 && current[r][c] == EMPTY) return false;

// Otherwise, checks if the move is valid by the rules of Sudoku.
        return (!(value_in_row(current, r, val)) && (!value_in_col(current, c, val)) && (!findValue(sector, val))); // general case, valid sudoku move

    }


    // If the puzzle is completely filled, the game is over.
    public boolean winCheck () {

        return emptyCheck();

    }


}
