package edu.wm.cs.cs301.sudoku.view;
import javax.swing.*;
import java.awt.*;

// this represents one of the 9 3x3 sectors in the 9x9 puzzle
/**
 * @author Benjamin You
 * The Sector class represents one of the nine 3x3 sectors in the 9x9 puzzle. Their "tag" is RC (ex. 23 is second row, third column).
 * The vals array represents the values of this specific sector in the initial puzzle.
 * Each "cell" is a Cell object. The Sector object also passes a reference to the board object to Cell.
 * */
public class Sector extends JPanel {

    private static Board board;

    public Sector (int tag, int[][] vals, Board board) {

        GridLayout GL = new GridLayout(3,3, -1,-1);
        this.setLayout(GL);
        this.board = board;
        int ID = ((tag/10) - 1) * 27 + ((tag % 10) - 1) * 3; // the true coordinates of the sector's top left corner
        int width = 20;
        int height = 20;

        Cell one     = new Cell (ID + 0,  vals[0][0], width, height , this.board);
        Cell two     = new Cell (ID + 1,  vals[0][1], width, height , this.board);
        Cell three   = new Cell (ID + 2,  vals[0][2], width, height , this.board);
        Cell four    = new Cell (ID + 9,  vals[1][0], width, height , this.board);
        Cell five    = new Cell (ID + 10, vals[1][1], width, height , this.board);
        Cell six     = new Cell (ID + 11, vals[1][2], width, height , this.board);
        Cell seven   = new Cell (ID + 18, vals[2][0], width, height , this.board);
        Cell eight   = new Cell (ID + 19, vals[2][1], width, height , this.board);
        Cell nine    = new Cell (ID + 20, vals[2][2], width, height , this.board);

        this.add(one);
        this.add(two);
        this.add(three);
        this.add(four);
        this.add(five);
        this.add(six);
        this.add(seven);
        this.add(eight);
        this.add(nine);

    }

}