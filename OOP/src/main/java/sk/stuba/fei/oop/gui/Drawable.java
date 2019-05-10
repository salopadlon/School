package sk.stuba.fei.oop.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public interface Drawable<T> extends Shape {
    void draw(Graphics2D g);
    void onClick(MouseEvent e);
    T getElement();
}
