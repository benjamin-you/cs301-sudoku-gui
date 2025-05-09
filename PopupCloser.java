package edu.wm.cs.cs301.sudoku.view;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Benjamin You
 * The PopupCloser class is just a MouseListener. Its main task is to signal when to close the PopupMenu (a mouse click).
 * Shoutout mostly to IntelliJ for autocompleting this class because whoa it is strange.
 * */
public class PopupCloser implements MouseListener {

    private static PopupMenu popup;

    public PopupCloser (PopupMenu popup) {

        this.popup = popup;

    }

    @Override
    public void mouseClicked (MouseEvent e) {

    }

    /**
     * @author Benjamin You
     * If the mouse is pressed, get rid of the Popup.
     * */
    @Override
    public void mousePressed(MouseEvent e) {

        if (popup.isVisible()) popup.setVisible(false);

    }

    /**
     * @author Benjamin You
     * Sometimes the game bugs out, and makes another window. So when the mouse is released, the popup should leave too.
     * */
    @Override
    public void mouseReleased(MouseEvent e) {

        if (popup.isVisible()) popup.setVisible(false);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * @author Benjamin You
     * The idea is to not allow two PopupMenus to open at once. The only way to do that is for the mouse to leave one of the menus.
     * */
    @Override
    public void mouseExited(MouseEvent e) {

        if (popup.isVisible()) popup.setVisible(false);

    }

}