/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.gui;

import sk.stuba.fei.oop.petrinet.Place;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Place2D extends Ellipse2D.Float implements Drawable<Place> {

    private Place place;

    public Place2D(int x, int y, Place place) {
        super(x, y, 40, 40);
        this.place = place;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fill(this);
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.BLACK);
        g.draw(this);
        g.drawString("" + place.getLabel(), (int) getBounds2D().getCenterX() - 5, (int) getBounds2D().getCenterY() + 35);
        g.drawString("" + place.getTokens(), (int) getBounds2D().getCenterX() - 3, (int) getBounds2D().getCenterY() + 5);
    }

    @Override
    public void onClick(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            place.setTokens(place.getTokens()+1);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (place.getTokens() > 0) {
                place.setTokens(place.getTokens() - 1);
            }
            else {
                place.setTokens(0);
            }
        }
    }

    @Override
    public Place getElement() {
        return place;
    }
}
