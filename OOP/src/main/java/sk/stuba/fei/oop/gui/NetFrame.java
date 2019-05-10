package sk.stuba.fei.oop.gui;

import sk.stuba.fei.oop.buttons.*;
import sk.stuba.fei.oop.transformers.FileHandler;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class NetFrame extends Frame {

    private NetCanvas netCanvas = new NetCanvas();
    private FileHandler fileHandler = new FileHandler();

    public NetFrame() {
        super("Petrinet");

        Panel panel = new Panel();
        OpenFileButton bOpen = new OpenFileButton("Open File", this);
        SaveFileButton bSave = new SaveFileButton("Save File", this);
        RemoveButton bRemove = new RemoveButton("Remove element", this);
        AddPlaceButton bPlace = new AddPlaceButton("Add Place", this);
        AddTransitionButton bTransition = new AddTransitionButton("Add transition", this);
        AddArcButton bArc = new AddArcButton("Add arc", this);
        FireButton bFire = new FireButton("Fire", this);

        setSize(1200,600);
        setLayout(new BorderLayout());

        panel.add(bOpen);
        bOpen.addActionListener(bOpen);

        panel.add(bSave);
        bSave.addActionListener(bSave);

        panel.add(bRemove);
        bRemove.addActionListener(bRemove);

        panel.add(bPlace);
        bPlace.addActionListener(bPlace);

        panel.add(bTransition);
        bTransition.addActionListener(bTransition);

        panel.add(bArc);
        bArc.addActionListener(bArc);

        panel.add(bFire);
        bFire.addActionListener(bFire);

        add("North",panel);
        add("Center", netCanvas);
        addWindowListener(close());

        setVisible(true);
    }

    private WindowAdapter close() {
        return new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                dispose();
                System.exit(0);
            }
        };
    }

    public NetCanvas getNetCanvas() {
        return netCanvas;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
