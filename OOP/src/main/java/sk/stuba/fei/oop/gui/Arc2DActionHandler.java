package sk.stuba.fei.oop.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */

public class Arc2DActionHandler extends Rectangle2D.Double implements Drawable<Arc2D> {

    private static final int HEIGHT = 10;
    private Arc2D arc2D;

    public Arc2DActionHandler(Arc2D arc2D) {
        super(arc2D.getX1(), arc2D.getY1(), arc2D.getX2()-arc2D.getX1(), HEIGHT);
        this.arc2D = arc2D;
    }

    @Override
    public void draw(Graphics2D g) {

        double xDiff = arc2D.getX2()-arc2D.getX1();
        double yDiff = arc2D.getY2()-arc2D.getY1();

        double hypotenuse = Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff,2));
        double angle = Math.toDegrees(Math.asin(Math.sin(yDiff/hypotenuse)));

        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(angle), x, y);
        Shape rotated = at.createTransformedShape(this);

        g.setColor(Color.BLUE);
        g.fill(rotated);
        g.draw(rotated);
    }

    @Override
    public void onClick(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            arc2D.getElement().setWeight(arc2D.getElement().getWeight()+1);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (arc2D.getElement().getWeight() > 0) {
                arc2D.getElement().setWeight(arc2D.getElement().getWeight() - 1);
            }
            else {
                arc2D.getElement().setWeight(0);
            }
        }
    }

    @Override
    public Arc2D getElement() {
        return arc2D;
    }
}
