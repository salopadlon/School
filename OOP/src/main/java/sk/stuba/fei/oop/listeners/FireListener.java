package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.Drawable;
import sk.stuba.fei.oop.gui.NetFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class FireListener extends ButtonListener implements MouseListener {

    public FireListener(NetFrame netFrame) {
        super(netFrame);
    }

    private void fire(MouseEvent e) {
        for (Drawable drawable : getNetFrame().getNetCanvas().getDrawableList()) {
            if (drawable.contains(e.getX(), e.getY())) {
                drawable.onClick(e);
                getNetFrame().getNetCanvas().repaint();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        fire(e);
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
