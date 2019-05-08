/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.gui;

import sk.stuba.fei.oop.generated.Arc;
import sk.stuba.fei.oop.generated.Document;
import sk.stuba.fei.oop.generated.Place;
import sk.stuba.fei.oop.generated.Transition;
import sk.stuba.fei.oop.transformers.FileHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class NetFrame extends Frame implements ActionListener {

    private String open="Open file";
    private String save="Save file";
    private String remove="Remove element";
    private String place="Add place";
    private String transition="Add transition";
    private String arc="Add arc";
    private String fire="Fire";
    private NetCanvas canvas = new NetCanvas();
    private FileHandler fileHandler = new FileHandler();

    public NetFrame() {
        super("Petrinet");

        Panel panel = new Panel();
        Button bOpen = new Button(open);
        Button bSave = new Button(save);
        Button bRemove = new Button(remove);
        Button bPlace = new Button(place);
        Button bTransition = new Button(transition);
        Button bArc = new Button(arc);
        Button bFire = new Button(fire);

        setSize(1200,600);
        setLayout(new BorderLayout());

        panel.add(bOpen);
        bOpen.addActionListener(this);

        panel.add(bSave);
        bSave.addActionListener(this);

        panel.add(bRemove);
        bRemove.addActionListener(this);

        panel.add(bPlace);
        bPlace.addActionListener(this);

        panel.add(bTransition);
        bTransition.addActionListener(this);

        panel.add(bArc);
        bArc.addActionListener(this);

        panel.add(bFire);
        bFire.addActionListener(this);

        add("North",panel);
        add("Center", canvas);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(open)) {
            openFile();
        }

        if (e.getActionCommand().equals(save)) {
            saveFile();
        }

        if (e.getActionCommand().equals(remove)) {
            canvas.setMode(4);
        }

        if (e.getActionCommand().equals(place)) {
            canvas.setMode(1);
        }

        if (e.getActionCommand().equals(transition)) {
            canvas.setMode(2);
        }

        if (e.getActionCommand().equals(arc)) {
            canvas.setMode(3);
        }

        if (e.getActionCommand().equals(fire)) {
            canvas.setMode(0);
        }
    }

    private void saveFile() {
        Document document = new Document();

        for (Drawable drawable : canvas.getDrawableList()) {
            if (drawable.getClass().equals(sk.stuba.fei.oop.gui.Place2D.class)) {
                Place place = new Place();
                sk.stuba.fei.oop.petrinet.Place placePetri = (sk.stuba.fei.oop.petrinet.Place) drawable.getElement();
                place.setId((short) placePetri.getId());
                place.setLabel(placePetri.getLabel());
                place.setTokens((short) placePetri.getTokens());
                place.setX((short) placePetri.getX());
                place.setY((short) placePetri.getY());
                place.setStatic(false);
                document.getPlace().add(place);
            }

            if (drawable.getClass().equals(sk.stuba.fei.oop.gui.Transition2D.class)) {
                Transition transition = new Transition();
                sk.stuba.fei.oop.petrinet.Transition transitionPetri = (sk.stuba.fei.oop.petrinet.Transition) drawable.getElement();
                transition.setId((short) transitionPetri.getId());
                transition.setLabel(transitionPetri.getLabel());
                transition.setX((short) transitionPetri.getX());
                transition.setY((short) transitionPetri.getY());
                document.getTransition().add(transition);
            }

            if (drawable.getClass().equals(sk.stuba.fei.oop.gui.Arc2D.class)) {
                Arc arc = new Arc();
                sk.stuba.fei.oop.petrinet.Arc arcPetri = (sk.stuba.fei.oop.petrinet.Arc) drawable.getElement();
                arc.setId((short) arcPetri.getId());
                arc.setMultiplicity((short) arcPetri.getWeight());
                arc.setSourceId((short) arcPetri.getSource().getId());
                arc.setDestinationId((short) arcPetri.getDestination().getId());
                arc.setBreakPoint(null);
                if (arc.getClass().equals(sk.stuba.fei.oop.petrinet.ArcReset.class)) {
                    arc.setType("reset");
                } else {
                    arc.setType("regular");
                }
                document.getArc().add(arc);
            }
        }

        fileHandler.saveFile(document);
    }

    private void openFile() {
        List<Drawable> drawables = fileHandler.selectFile();
        canvas.setDrawableList(drawables);
        canvas.setPetriNet(fileHandler.getPetriNet());
        canvas.repaint();
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
