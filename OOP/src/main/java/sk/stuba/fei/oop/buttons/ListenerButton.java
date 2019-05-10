package sk.stuba.fei.oop.buttons;

import sk.stuba.fei.oop.gui.NetFrame;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
abstract class ListenerButton extends ActionButton implements ActionListener {

    ListenerButton(String label, NetFrame netFrame) throws HeadlessException {
        super(label, netFrame);
    }

    void deleteMouseListener() {
        for (MouseListener mouseListener : getNetFrame().getNetCanvas().getMouseListeners()) {
            getNetFrame().getNetCanvas().removeMouseListener(mouseListener);
        }
    }
}
