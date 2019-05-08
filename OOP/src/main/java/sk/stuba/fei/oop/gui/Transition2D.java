/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.gui;

import sk.stuba.fei.oop.petrinet.PetriNet;
import sk.stuba.fei.oop.petrinet.Transition;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Transition2D extends Rectangle2D.Float implements Drawable<Transition> {

    private Transition transition;
    private PetriNet net;
    private boolean isExecutable;

    public Transition2D(int x, int y, Transition transition, PetriNet net) {
        super(x, y, 40, 40);
        this.transition = net.getTransitionById(transition.getId());
        this.isExecutable = transition.isExecutable();
        this.net = net;
    }

    @Override
    public void draw(Graphics2D g) {
        this.isExecutable = transition.isExecutable();

        if (isExecutable) {
            g.setColor(Color.GREEN);
            g.fill(this);
        } else {
            g.setColor(Color.RED);
            g.fill(this);
        }

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.draw(this);
        g.drawString(""+transition.getLabel(), (int) getBounds2D().getCenterX() - 5,(int) getBounds2D().getCenterY() + 35);
    }

    @Override
    public void onClick(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            net.fire(transition.getId());
        }
    }

    @Override
    public Transition getElement() {
        return transition;
    }
}
