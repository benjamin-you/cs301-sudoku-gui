package edu.wm.cs.cs301.sudoku.view;
import javax.swing.*;
import java.awt.*;

/**
 * @author Benjamin You
 * The main feature of the Cell object is the actionListeners it has for the PopupMenu and PopupCloser (secretly MouseListener) classes.
 * Cell has an ID, its location on the board (1-81 from left to right), and val, its initial value.
 * After initialisation, the Cell object is independent. It dynamically communicates with the Board and PopupMenu classes.
 * */
public class Cell extends JButton {

    private int tag;
    private static Board board;

    public Cell (int ID, int val, int width, int height, Board board) {

        this.board = board;
        this.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.gray));
        this.setSize(width, height);
        this.setText(Integer.toString(val));
        tag = ID;

        // if not an original piece [, then val == 0, so] edits can be made.
        if (val == 0) this.addActionListener(e -> await_value());

    }

    /**
     * @author Benjamin You
     * The method waits for a value by creating a MouseListener. Details can be found on the PopupMenu page.
     * */
    private void await_value () {

        PopupMenu popup = new PopupMenu(tag, this.board, this);
        this.add(popup);
        popup.setLocation(this.getLocationOnScreen());
        popup.setVisible(true);
        PopupCloser pc = new PopupCloser(popup);
        popup.addMouseListener(pc);

    }

}