/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.gui;

import sk.stuba.fei.oop.exceptions.ArcWeightLowerThanOneException;
import sk.stuba.fei.oop.exceptions.LowTokensSumException;
import sk.stuba.fei.oop.exceptions.TransitionNotExecutableException;
import sk.stuba.fei.oop.petrinet.Arc;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Arc2D extends Line2D.Float implements Drawable<Arc> {

    private static final int WIDTH = 10;
    private Arc arc;

    public Arc2D(int x1, int y1, int x2, int y2, Arc arc) {
        super(x1,y1,x2,y2);
        setXY();
        this.arc = arc;
    }

    @Override
    public void draw(Graphics2D g) {
        try {
            arc.test();
        } catch (ArcWeightLowerThanOneException | TransitionNotExecutableException | LowTokensSumException e) { }

        int x = (int) (getX2());
        int y = (int) (getY2() - WIDTH/2);
        double xDiff = getX2()-getX1();
        double yDiff = getY2()-getY1();
        double hypotenuse = Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff,2));
        double angle = Math.toDegrees(Math.asin(Math.sin(yDiff/hypotenuse)));

        Polygon polygon = new Polygon();
        AffineTransform at = new AffineTransform();

        if (getX2() < getX1()) {
            polygon.addPoint(x + WIDTH/2, y);
            polygon.addPoint(x, y + WIDTH);
            polygon.addPoint(x + WIDTH, y + WIDTH);
            at.rotate(Math.toRadians(270-angle), x+WIDTH/2, y+WIDTH/2);
        } else {
            polygon.addPoint(x + WIDTH/2, y);
            polygon.addPoint(x, y + WIDTH);
            polygon.addPoint(x + WIDTH, y + WIDTH);
            at.rotate(Math.toRadians(90+angle), x, y);
        }

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.draw(this);

        Shape rotated = at.createTransformedShape(polygon);
        g.setStroke(new BasicStroke(1));
        g.fill(rotated);
        g.drawString(""+arc.getWeight(),(int) getBounds2D().getCenterX(),(int) getBounds2D().getCenterY() - 5);
    }

    private void setXY() {
        if (getX2() < getX1()) {
            this.y1 = y1 + 20;
            this.x2 = x2 + 40;
            this.y2 = y2 + 20;

        } else {
            this.x1 = x1 + 40;
            this.y1 = y1 + 20;
            this.y2 = y2 + 20;
        }
    }

    @Override
    public void onClick(MouseEvent e) { }

    @Override
    public Arc getElement() {
        return arc;
    }

}
