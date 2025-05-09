package edu.wm.cs.cs301.sudoku.model;


/**
 * @author Benjamin You
 * This class is sort of redundant. Its role is taken by Solver.
 * But, it was in my project write-up, so I wanted Generator to feel included.
 * */
public class Generator {

    Solver solver = new Solver();

    public int[][] generate_puzzle () {

        return solver.getPuzzle();

    }

}