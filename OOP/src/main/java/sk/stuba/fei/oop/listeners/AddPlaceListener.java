package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.NetFrame;
import sk.stuba.fei.oop.gui.Place2D;
import sk.stuba.fei.oop.petrinet.Place;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class AddPlaceListener extends AddElementListener implements MouseListener {


    public AddPlaceListener(NetFrame netFrame) {
        super(netFrame);
    }

    private void addPlace(MouseEvent e) {
        setId(getId()+1);
        Place place = new Place(getId(),"p"+getId(),0,e.getX(),e.getY());
        getNetFrame().getNetCanvas().getPetriNet().addPlace(place.getId(), place);
        getNetFrame().getNetCanvas().addDrawable(new Place2D(e.getX(),e.getY(),place));
        getNetFrame().getNetCanvas().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        addPlace(e);
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
