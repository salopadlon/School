package sk.stuba.fei.oop.editing;

import sk.stuba.fei.oop.actionButtons.ActionButton;
import sk.stuba.fei.oop.gui.Drawable;
import sk.stuba.fei.oop.gui.NetCanvas;
import sk.stuba.fei.oop.gui.NetFrame;

import java.awt.event.ActionEvent;
import java.util.List;

public class OpenFile extends ActionButton {

    private String open = "Open file";

    public OpenFile(ActionEvent e, NetFrame netFrame, NetCanvas canvas) {
        super(e, netFrame, canvas);
        if (e.getActionCommand().equals(open)) {
            openFile();
        }
    }

    private void openFile() {
        List<Drawable> drawables = getNetFrame().getFileHandler().selectFile();
        getCanvas().setDrawableList(drawables);
        getCanvas().setPetriNet(getNetFrame().getFileHandler().getPetriNet());
        getCanvas().repaint();
    }
}
