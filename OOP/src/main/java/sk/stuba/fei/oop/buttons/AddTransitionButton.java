package sk.stuba.fei.oop.buttons;

import sk.stuba.fei.oop.gui.NetFrame;
import sk.stuba.fei.oop.listeners.AddTransitionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class AddTransitionButton extends ListenerButton implements ActionListener {

    public AddTransitionButton(String label, NetFrame netFrame) throws HeadlessException {
        super(label, netFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        deleteMouseListener();
        getNetFrame().getNetCanvas().addMouseListener(new AddTransitionListener(getNetFrame()));
    }
}
