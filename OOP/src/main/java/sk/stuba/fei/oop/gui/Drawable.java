/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface Drawable<T> extends Shape {
    void draw(Graphics2D g);
    void onClick(MouseEvent e);
    T getElement();
}
