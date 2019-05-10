package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.Drawable;
import sk.stuba.fei.oop.gui.NetFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

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
        for (Iterator<Drawable> iterator = getNetFrame().getNetCanvas().getDrawableList().iterator(); iterator.hasNext();) {
            Drawable drawable = iterator.next();
            if (drawable.contains(e.getX(), e.getY())) {
                    iterator.remove();
                    getNetFrame().getNetCanvas().repaint();
            }
        }
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
