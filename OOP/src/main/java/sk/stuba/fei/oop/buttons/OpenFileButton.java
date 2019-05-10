package sk.stuba.fei.oop.buttons;

import sk.stuba.fei.oop.gui.Drawable;
import sk.stuba.fei.oop.gui.NetFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class OpenFileButton extends ActionButton implements ActionListener {

    public OpenFileButton(String label, NetFrame netFrame) throws HeadlessException {
        super(label, netFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openFile();
    }

    private void openFile() {
        List<Drawable> drawables = getNetFrame().getFileHandler().selectFile();
        getNetFrame().getNetCanvas().setDrawableList(drawables);
        getNetFrame().getNetCanvas().setPetriNet(getNetFrame().getFileHandler().getPetriNet());
        getNetFrame().getNetCanvas().repaint();
    }

}
