/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.transformers;

import sk.stuba.fei.oop.generated.Arc;
import sk.stuba.fei.oop.generated.Document;
import sk.stuba.fei.oop.generated.Place;
import sk.stuba.fei.oop.generated.Transition;
import sk.stuba.fei.oop.gui.Arc2D;
import sk.stuba.fei.oop.gui.Drawable;
import sk.stuba.fei.oop.gui.Place2D;
import sk.stuba.fei.oop.gui.Transition2D;
import sk.stuba.fei.oop.petrinet.PetriNet;
import java.util.LinkedList;
import java.util.List;

public class DrawableTransformer extends Transformer<List<Drawable>> {

    private PetriNet net;

    DrawableTransformer(PetriNet net) {
        if (net == null) {
            throw new IllegalArgumentException();
        }
        this.net = net;
    }


    @Override
    public List<Drawable> transform(Document document) {

        List<Drawable> drawables = new LinkedList<>();

        for (Arc arc : document.getArc()) {
            Arc2D arc2D = new Arc2D(net.getNode(arc.getSourceId()).getX(),net.getNode(arc.getSourceId()).getY(),
                    net.getNode(arc.getDestinationId()).getX(),net.getNode(arc.getDestinationId()).getY(),
                    net.getArcById(arc.getId()));
            drawables.add(arc2D);
        }

        for (Transition transition : document.getTransition()) {
            Transition2D transition2D = new Transition2D(transition.getX(),transition.getY(),
                    net.getTransitionById(transition.getId()),net);
            drawables.add(transition2D);
        }

        for (Place place : document.getPlace()) {
            Place2D place2D = new Place2D(place.getX(),place.getY(), net.getPlaceById(place.getId()));
            drawables.add(place2D);
        }

        return drawables;
    }
}
