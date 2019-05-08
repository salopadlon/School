package sk.stuba.fei.oop.actionButtons;

import sk.stuba.fei.oop.gui.NetCanvas;
import sk.stuba.fei.oop.gui.NetFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButton implements ActionListener {

    private NetFrame netFrame;
    private NetCanvas canvas;

    public ActionButton(ActionEvent e, NetFrame netFrame, NetCanvas canvas) {
        this.netFrame = netFrame;
        this.canvas = canvas;
    }

    public NetFrame getNetFrame() {
        return netFrame;
    }

    public NetCanvas getCanvas() {
        return canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
