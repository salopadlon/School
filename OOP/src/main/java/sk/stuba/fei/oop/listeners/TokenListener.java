package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.Arc2DActionHandler;
import sk.stuba.fei.oop.gui.Drawable;
import sk.stuba.fei.oop.gui.NetFrame;
import sk.stuba.fei.oop.gui.Place2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class TokenListener extends ButtonListener implements MouseListener {


    public TokenListener(NetFrame netFrame) {
        super(netFrame);
    }

    private void edit(MouseEvent e) {
        for (Drawable drawable : getNetFrame().getNetCanvas().getDrawableList()) {
            if (drawable.getClass().equals(Place2D.class) && drawable.contains(e.getX(), e.getY())) {
                action(e, drawable);
            }
            if (drawable.getClass().equals(Arc2DActionHandler.class) && drawable.contains(e.getX(), e.getY())) {
                action(e, drawable);
            }
        }
    }

    private void action(MouseEvent e, Drawable drawable) {
        drawable.onClick(e);
        getNetFrame().getNetCanvas().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        edit(e);
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
