package sk.stuba.fei.oop.buttons;

import sk.stuba.fei.oop.gui.NetFrame;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
abstract class ActionButton extends Button implements ActionListener {

    private NetFrame netFrame;

    ActionButton(String label, NetFrame netFrame) throws HeadlessException {
        super(label);
        this.netFrame = netFrame;
    }

    NetFrame getNetFrame() {
        return netFrame;
    }
}
