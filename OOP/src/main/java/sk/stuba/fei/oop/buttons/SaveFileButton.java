package sk.stuba.fei.oop.buttons;

import sk.stuba.fei.oop.generated.Arc;
import sk.stuba.fei.oop.generated.Document;
import sk.stuba.fei.oop.generated.Place;
import sk.stuba.fei.oop.generated.Transition;
import sk.stuba.fei.oop.gui.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class SaveFileButton extends ActionButton implements ActionListener {

    public SaveFileButton(String label, NetFrame netFrame) throws HeadlessException {
        super(label, netFrame);
    }

    private void saveFile() {
        Document document = new Document();

        for (Drawable drawable : getNetFrame().getNetCanvas().getDrawableList()) {
            if (drawable.getClass().equals(Place2D.class)) {
                savePlaces(document, drawable);
            }

            if (drawable.getClass().equals(Transition2D.class)) {
                saveTransitions(document, drawable);
            }

            if (drawable.getClass().equals(Arc2D.class)) {
                saveArcs(document, drawable);
            }
        }

        getNetFrame().getFileHandler().saveFile(document);
    }

    private void saveArcs(Document document, Drawable drawable) {
        Arc arc = new Arc();
        sk.stuba.fei.oop.petrinet.Arc arcPetri = (sk.stuba.fei.oop.petrinet.Arc) drawable.getElement();
        arc.setId((short) arcPetri.getId());
        arc.setMultiplicity((short) arcPetri.getWeight());
        arc.setSourceId((short) arcPetri.getSource().getId());
        arc.setDestinationId((short) arcPetri.getDestination().getId());
        arc.setBreakPoint(null);
        setTypeOfArc(arc);
        document.getArc().add(arc);
    }

    private void setTypeOfArc(Arc arc) {
        if (arc.getClass().equals(sk.stuba.fei.oop.petrinet.ArcReset.class)) {
            arc.setType("reset");
        } else {
            arc.setType("regular");
        }
    }

    private void saveTransitions(Document document, Drawable drawable) {
        Transition transition = new Transition();
        sk.stuba.fei.oop.petrinet.Transition transitionPetri = (sk.stuba.fei.oop.petrinet.Transition) drawable.getElement();
        transition.setId((short) transitionPetri.getId());
        transition.setLabel(transitionPetri.getLabel());
        transition.setX((short) transitionPetri.getX());
        transition.setY((short) transitionPetri.getY());
        document.getTransition().add(transition);
    }

    private void savePlaces(Document document, Drawable drawable) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        saveFile();
    }
}
