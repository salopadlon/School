/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.gui;

import sk.stuba.fei.oop.editing.ArcAdder;
import sk.stuba.fei.oop.editing.ElementRemover;
import sk.stuba.fei.oop.editing.PlaceAdder;
import sk.stuba.fei.oop.editing.TransitionAdder;
import sk.stuba.fei.oop.petrinet.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class NetCanvas extends Canvas implements MouseListener {

    private List<Drawable> drawableList = new ArrayList<>();
    private PetriNet petriNet = new PetriNet();
    private Node arcSource = null;
    private int mode = 0;

    NetCanvas() {
        addMouseListener(this);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paint(Graphics g) {
        try {
            for (Drawable drawable : drawableList) {
                drawable.draw((Graphics2D) g);
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    void setMode(int mode) {
        this.mode = mode;
    }

    public void setDrawableList(List<Drawable> drawableList) {
        this.drawableList = drawableList;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public void addDrawable(Drawable drawable) {
        drawableList.add(drawable);
    }

    public List<Drawable> getDrawableList() {
        return drawableList;
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            switch (mode) {
                case 1:  // Add place
                    new PlaceAdder(this, petriNet, e.getX(), e.getY());
                    break;
                case 2: // Add transition
                    new TransitionAdder(this, petriNet, e.getX(), e.getY());
                    break;
                case 3: // Add arc
                    addArc(e);
                    break;
                case 4: // Remove element
                    new ElementRemover(this, e);
                    break;
                case 0: // Fire
                default:
                    fire(e);
                    break;
            }
            repaint();
        } catch (NullPointerException | ConcurrentModificationException | ClassCastException ex) { }
    }

    private void fire(MouseEvent e) {
        for (Drawable drawable : drawableList) {
            if (drawable.contains(e.getX(), e.getY())) {
                drawable.onClick(e);
            }
        }
    }

    private void addArc(MouseEvent e) {
        for (Drawable drawable : drawableList) {
            if (drawable.contains(e.getX(), e.getY())) {
                if (arcSource == null) {
                    arcSource = (Node) drawable.getElement();
                } else {
                    new ArcAdder(this, petriNet, arcSource, (Node) drawable.getElement());
                    arcSource = null;
                    repaint();
                }
            }
        }
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
