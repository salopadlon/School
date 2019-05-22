package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ConcurrentModificationException;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class RemoveListener extends ButtonListener implements MouseListener {

    public RemoveListener(NetFrame netFrame) {
        super(netFrame);
    }

    private void remove(MouseEvent e) {
        try {
            for (Drawable drawable : getNetFrame().getNetCanvas().getDrawableList()) {
                if (drawable.contains(e.getX(), e.getY())) {
                    if (drawable.getClass().equals(Arc2DActionHandler.class)) {
                        getNetFrame().getNetCanvas().getDrawableList().remove(drawable.getElement());
                        removeDrawable(drawable);
                    } else {
                        removeDrawable(drawable);
                    }
                }
            }
        } catch (ConcurrentModificationException e1) {}
    }

    private void removeDrawable(Drawable drawable) {
            getNetFrame().getNetCanvas().getDrawableList().remove(drawable);
            getNetFrame().getNetCanvas().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        remove(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
