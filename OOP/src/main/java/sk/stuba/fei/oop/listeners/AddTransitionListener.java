package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.NetFrame;
import sk.stuba.fei.oop.gui.Transition2D;
import sk.stuba.fei.oop.petrinet.Transition;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class AddTransitionListener extends AddElementListener implements MouseListener {


    public AddTransitionListener(NetFrame netFrame) {
        super(netFrame);
    }

    private void addTransition(MouseEvent e) {
        setId(getId()+1);
        Transition transition = new Transition(getId(),"t"+getId(),e.getX(),e.getY());
        getNetFrame().getNetCanvas().getPetriNet().addTransition(transition.getId(),transition);
        getNetFrame().getNetCanvas().addDrawable(new Transition2D(e.getX(),e.getY(), transition, getNetFrame().getNetCanvas().getPetriNet()));
        getNetFrame().getNetCanvas().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        addTransition(e);
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
