package edu.wm.cs.cs301.sudoku.model;


/**
 * @author Benjamin You
 * The Puzzle object, like the Generator, is a relic of planning. But, at least it can make the current array 4D!
 * */
public class Puzzle {

    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;
    public static final int EMPTY = 0;
    private int[][] current  = new int[NUM_ROWS][NUM_COLS];

    public Puzzle() {

        for (int r = 0; r < NUM_ROWS; ++r) {

            for (int c = 0; c < NUM_COLS; ++c) {

                current[r][c] = EMPTY;

            }

        }

        Generator gen = new Generator();
        current = gen.generate_puzzle();

    }


    public int[][] getCurrent() {

        return current;

    }


    public int[][][][] getCurrent4D() {

        int[][][][] current4D = new int[NUM_ROWS/3][NUM_ROWS/3][NUM_ROWS/3][NUM_ROWS/3];

        for (int u = 0; u < NUM_ROWS/3; ++u) {

            for (int v = 0; v < NUM_COLS/3; ++v) {

                for (int w = 0; w < NUM_ROWS/3; ++w) {

                    for (int x = 0; x < NUM_COLS/3; ++x) {

                        current4D[u][v][w][x] = current[u*3 + w][v*3 + x];

                    }

                }

            }

        }

        return current4D;

    }

}
