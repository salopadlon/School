package sk.stuba.fei.oop.transformers;

import sk.stuba.fei.oop.generated.Arc;
import sk.stuba.fei.oop.generated.Document;
import sk.stuba.fei.oop.generated.Place;
import sk.stuba.fei.oop.generated.Transition;
import sk.stuba.fei.oop.petrinet.ArcAdding;
import sk.stuba.fei.oop.petrinet.ArcReset;
import sk.stuba.fei.oop.petrinet.ArcTaking;
import sk.stuba.fei.oop.petrinet.PetriNet;
import java.util.ArrayList;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class PetriNetTransformer extends Transformer<PetriNet> {

    @Override
    public PetriNet transform(Document document) {

        PetriNet net = new PetriNet();
        ArrayList<Short> transIds = new ArrayList<>();

        for (Transition transition : document.getTransition()) {
            sk.stuba.fei.oop.petrinet.Transition transition1 = new sk.stuba.fei.oop.petrinet.Transition((long) transition.getId(), transition.getLabel(), transition.getX(), transition.getY());
            transIds.add(transition.getId());
            net.addTransition(transition1.getId(), transition1);
        }

        for (Place place : document.getPlace()) {
            sk.stuba.fei.oop.petrinet.Place place1 = new sk.stuba.fei.oop.petrinet.Place((long) place.getId(), place.getLabel(), place.getTokens(), place.getX(), place.getY());
            net.addPlace(place1.getId(), place1);
        }

        for (Arc arc : document.getArc()) {
            if (arc.getType().toLowerCase().equals("reset")) {
                ArcReset arcR = new ArcReset(net.getPlaceById(arc.getSourceId()), net.getTransitionById(arc.getDestinationId()));
                net.addArc((long) arc.getId(), arcR);
            } else if (transIds.contains(arc.getSourceId())){
                ArcAdding arcA = new ArcAdding(net.getTransitionById(arc.getSourceId()),net.getPlaceById(arc.getDestinationId()),arc.getMultiplicity());
                net.addArc((long) arc.getId(),arcA);
            } else {
                ArcTaking arcT = new ArcTaking(net.getPlaceById(arc.getSourceId()),net.getTransitionById(arc.getDestinationId()),arc.getMultiplicity());
                net.addArc((long) arc.getId(),arcT);
            }
        }
        return net;
    }
}
