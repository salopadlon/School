package sk.stuba.fei.oop.buttons;

import sk.stuba.fei.oop.gui.NetFrame;
import sk.stuba.fei.oop.listeners.RemoveListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class RemoveButton extends ListenerButton implements ActionListener {

    public RemoveButton(String label, NetFrame netFrame) throws HeadlessException {
        super(label, netFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        deleteMouseListener();
        getNetFrame().getNetCanvas().addMouseListener(new RemoveListener(getNetFrame()));
    }
}
