package sk.stuba.fei.oop.gui;

import sk.stuba.fei.oop.petrinet.PetriNet;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class NetCanvas extends Canvas {

    private List<Drawable> drawableList = new ArrayList<>();
    private PetriNet petriNet = new PetriNet();

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
        } catch (NullPointerException | ConcurrentModificationException e) {
            System.out.println(e);
        }
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

}
