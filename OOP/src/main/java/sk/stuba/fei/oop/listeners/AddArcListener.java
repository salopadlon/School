package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.exceptions.ArcNodesSameTypeException;
import sk.stuba.fei.oop.gui.*;
import sk.stuba.fei.oop.petrinet.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ConcurrentModificationException;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class AddArcListener extends AddElementListener implements MouseListener {

    private Node arcSource = null;

    public AddArcListener(NetFrame netFrame) {
        super(netFrame);
    }

    private void addArc(MouseEvent e) {
        for (Drawable drawable : getNetFrame().getNetCanvas().getDrawableList()) {
            if (drawable.contains(e.getX(), e.getY())) {
                if (arcSource == null) {
                    arcSource = (Node) drawable.getElement();
                } else {
                    createArc(drawable);
                    arcSource = null;
                    getNetFrame().getNetCanvas().repaint();
                }
            }
        }
    }

    private void createArc(Drawable drawable) {
        Node arcDestination = (Node) drawable.getElement();
        testNodesType(arcSource, arcDestination);
        this.setId(getId()+1);
        if (arcSource.getClass().equals(Place.class)) {
            createArcTaking(getNetFrame().getNetCanvas(), getNetFrame().getNetCanvas().getPetriNet(), arcSource, arcDestination);
        } else {
            createArcAdding(getNetFrame().getNetCanvas(), getNetFrame().getNetCanvas().getPetriNet(), arcSource, arcDestination);
        }
    }

    private void createArcAdding(NetCanvas netCanvas, PetriNet petriNet, Node arcSource, Node arcDestination) {
        ArcAdding aa = new ArcAdding((Transition) arcSource, (Place) arcDestination, 1);
        aa.setId(getId());
        petriNet.addArc(aa.getId(), aa);
        Arc2D arc2D = new Arc2D(arcSource.getX(), arcSource.getY(), arcDestination.getX(), arcDestination.getY(), aa);
        netCanvas.addDrawable(arc2D);
        netCanvas.addDrawable(new Arc2DActionHandler(arc2D));
    }

    private void createArcTaking(NetCanvas netCanvas, PetriNet petriNet, Node arcSource, Node arcDestination) {
        ArcTaking at = new ArcTaking((Place) arcSource, (Transition) arcDestination, 1);
        at.setId(getId());
        petriNet.addArc(at.getId(), at);
        Arc2D arc2D = new Arc2D(arcSource.getX(), arcSource.getY(), arcDestination.getX(), arcDestination.getY(), at);
        netCanvas.addDrawable(arc2D);
        netCanvas.addDrawable(new Arc2DActionHandler(arc2D));
    }

    private void testNodesType(Node first, Node second) {
        try {
            if (first.getClass().equals(second.getClass())) {
                throw new ArcNodesSameTypeException("Can not create arc between nodes of the same type");
            }
        } catch (ArcNodesSameTypeException e) {
            System.out.println(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            addArc(e);
        } catch (ConcurrentModificationException | ClassCastException e1) {}
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
