package edu.wm.cs.cs301.sudoku.view;
import javax.swing.*;
import java.awt.*;


/**
 * @author Benjamin You
 * The PopupMenu class uses its reference to the parent Board class to communicate user input.
 * The correct ObjectListener then calls attempt_update() from the Board object with its ret[urn]_val[ue].
 * */
public class PopupMenu extends JPopupMenu {

    public Board board;
    public Cell cell;
    public static int ret_val = 0;

    public PopupMenu (int ID, Board board, Cell cell) {

        GridLayout GL = new GridLayout(4,3);
        this.setLayout(GL);
        this.setSize(36, 48);
        this.board = board;
        this.cell = cell;

        JMenuItem one = new JMenuItem("1");
        one.addActionListener(e -> {
            ret_val = 1;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem two = new JMenuItem("2");
        two.addActionListener(e -> {
            ret_val = 2;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem three = new JMenuItem("3");
        three.addActionListener(e -> {
            ret_val = 3;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem four = new JMenuItem("4");
        four.addActionListener(e -> {
            ret_val = 4;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem five = new JMenuItem("5");
        five.addActionListener(e -> {
            ret_val = 5;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem six = new JMenuItem("6");
        six.addActionListener(e -> {
            ret_val = 6;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem seven = new JMenuItem("7");
        seven.addActionListener(e -> {
            ret_val = 7;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem eight = new JMenuItem("8");
        eight.addActionListener(e -> {
            ret_val = 8;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem nine = new JMenuItem("9");
        nine.addActionListener(e -> {
            ret_val = 9;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });
        JMenuItem clear = new JMenuItem("CLEAR");
        clear.  addActionListener(e -> {
            ret_val = 0;
            this.setVisible(false);
            this.board.attempt_update(ID, ret_val, this.cell);
        });

        one.    setSize(12,12);
        two.    setSize(12,12);
        three.  setSize(12,12);
        four.   setSize(12,12);
        five.   setSize(12,12);
        six.    setSize(12,12);
        seven.  setSize(12,12);
        eight.  setSize(12,12);
        nine.   setSize(12,12);
        clear.  setSize(36,12);

        this.add(one);
        this.add(two);
        this.add(three);
        this.add(four);
        this.add(five);
        this.add(six);
        this.add(seven);
        this.add(eight);
        this.add(nine);
        this.add(clear);

    }

}