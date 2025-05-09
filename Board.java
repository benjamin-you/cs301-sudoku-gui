package edu.wm.cs.cs301.sudoku.view;
import edu.wm.cs.cs301.sudoku.model.Arbitor;
import javax.swing.*;
import java.awt.*;

/**
 * @author Benjamin You
 * The main task of the Board class is to monitor the status of the game.
 * The Board class contains 9 Sector objects (each of 9 Cell objects) for a total of 81 Cell objects.
 * It can be thought of as the back-end to Display.
 * */
public class Board extends JPanel {

    private int filled_cells;
    private Cell cell;
    private int[][] current;

    public Board (int[][][][] vals, int[][] current) {

        GridLayout GL = new GridLayout(3,3, 4, 4);
        this.setLayout(GL);
        this.setSize(480, 480);
        this.current = current;

        Sector s11 = new Sector (11, vals[0][0] , this);
        Sector s12 = new Sector (12, vals[0][1] , this);
        Sector s13 = new Sector (13, vals[0][2] , this);
        Sector s21 = new Sector (21, vals[1][0] , this);
        Sector s22 = new Sector (22, vals[1][1] , this);
        Sector s23 = new Sector (23, vals[1][2] , this);
        Sector s31 = new Sector (31, vals[2][0] , this);
        Sector s32 = new Sector (32, vals[2][1] , this);
        Sector s33 = new Sector (33, vals[2][2] , this);

        this.add(s11);
        this.add(s12);
        this.add(s13);
        this.add(s21);
        this.add(s22);
        this.add(s23);
        this.add(s31);
        this.add(s32);
        this.add(s33);

        for (int r = 0; r < 9; ++r) {
            for (int c = 0; c < 9; ++c) {
                if (current[r][c] != 0) ++filled_cells;
            }
        }

    }

    /**
     * @author Benjamin You
     * The PopupMenu object passes this method the location (ID) to attempt an insertion of val[ue].
     * The attempt_update() method checks if the move is valid by the rules of Sudoku. If so, it is added to current.
     * The method also checks if the board is full. If the board is filled, it must be filled with valid values.
     * */
    public void attempt_update (int ID, int val, Cell cell) {

        this.cell = cell;
        int r = ID / 9;
        int c = ID % 9;
        Arbitor judge = new Arbitor(this.current);

        if (judge.validMove(r, c, val) || val == 0) {
            this.current[r][c] = val;
            ++filled_cells;
            this.cell.setText(Integer.toString(val));
        }

        // This synchronised environment is tied to its partner in Main. This is how Board communicates up the hierarchy.
        if (this.filled_cells == 81) {
            synchronized (this) {
                this.notify();
            }
        }

    }

}