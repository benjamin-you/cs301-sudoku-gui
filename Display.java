package edu.wm.cs.cs301.sudoku.view;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * @author Benjamin You
 * The Display class is an undercover JFrame. It handles the Display when moves are made and the game is replayed.
 * */
public class Display extends JFrame {

    public Board board;

    public Display () {

        this.setSize (480, 480);
        this.setTitle("the Game !");
        this.setResizable(false);
        this.setVisible(true);

    }

    /**
     * @author Benjamin You
     * The update() method updates the board by swapping out the old board with the updated one.
     * */
    public void update (Board board) {

        this.board = board;
        this.setVisible(false);
        this.board.setVisible(false);

        this.getContentPane().remove(this.board);
        this.getContentPane().add(board);
        this.getContentPane().invalidate();
        this.getContentPane().validate();
        this.getContentPane().repaint();

        this.board.setBackground(Color.darkGray);
        this.setSize (480, 480);
        this.setResizable(false);
        this.setVisible(true);
        this.board.setVisible(true);

    }

    /**
     * @author Benjamin You
     * The play_again() method disposes of the current game Display and launches a "Play again?" window. If yes, a new Display is made.
     * It has a "yes" and "no" instead of a "Play again" and "Quit". I opt for the more concise option, and I hope the specifications allow it.
     * */
    // from https://www.geeksforgeeks.org/java-joptionpane/
    public boolean play_again () {

        String pathToFile = "./src/main/java/edu/wm/cs/cs301/sudoku/resources/yay.jpg";
        BufferedImage image;
        try {
            image = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Icon icon = new ImageIcon(image);

        Object message = new Object[] {"Do you want to play again?", icon};
        int yes_no = JOptionPane.showConfirmDialog(null, message, "You win!", JOptionPane.YES_NO_OPTION);

        this.dispose();

        return (yes_no == JOptionPane.YES_OPTION);

    }

}
