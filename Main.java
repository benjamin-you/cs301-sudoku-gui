package edu.wm.cs.cs301.sudoku;
import edu.wm.cs.cs301.sudoku.view.Board;
import edu.wm.cs.cs301.sudoku.model.Puzzle;
import edu.wm.cs.cs301.sudoku.view.Display;

import javax.swing.*;


/**
 * @author Benjamin You
 * The Main class maintains the game and the assorted objects. I think it is the coolest one, functionally.
 * */
public class Main {

    private static Puzzle sudo;
    private static Board board;
    private static Display display;
    private static int[][][][] current4D;
    private static int[][] current2D;

    /**
     * @author Benjamin You
     * The Main object, when instantiated, launches an instruction manual before creating a new game and playing.
     * */
    public static void main(String[] args) {

        Object message = new Object[] {"Welcome to the Game !! (Secretly Sudoku.)\n\nThere is one goal in the game. Fill in all the squares.\n\nThere are three main rules. Values cannot be repeated in the same\n(1) row,\n(2) column, or\n(3) sector (think 3x3 grid).\n\nWhen you are ready to play, hit 'OK', and the Game ! will begin."};
        JOptionPane.showConfirmDialog(null, message, "Instructions!", JOptionPane.DEFAULT_OPTION);

        New_Game();
        Play ();

    }

    /**
     * @author Benjamin You
     * The Play class represents the dynamic portion of the Game. First, the display is updated (set into motion).
     * Next, the method waits until the Board object notifies it that the Game is done.
     * */
    public static void Play() {

        board = new Board(current4D, current2D);

        display.update(board);

        // from https://www.geeksforgeeks.org/java-notify-method-in-threads-synchronization-with-examples/
        synchronized (board)
        {
            try {
                board.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // Important: the precondition for this line (Play again?) is that the game has been won.
        if (display.play_again()) {
            New_Game();
            Play();
        }

    }

    /**
     * @author Benjamin You
     * The New_Game method populates the objects with a new puzzle.
     * */
    public static void New_Game () {

        sudo = new Puzzle();
        current4D = sudo.getCurrent4D();
        current2D = sudo.getCurrent();
        display = new Display();

    }


}