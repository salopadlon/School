package sk.stuba.fei.oop.editing;

import sk.stuba.fei.oop.exceptions.ArcNodesSameTypeException;
import sk.stuba.fei.oop.gui.*;
import sk.stuba.fei.oop.petrinet.*;

public class ArcAdder extends ElementAdder {

    public ArcAdder(NetCanvas canvas, PetriNet petriNet, Node arcSource, Node arcDestination) {
        testNodesType(arcSource, arcDestination);
        if (arcSource.getClass().equals(Place.class)) {
            createArcTaking(canvas, petriNet, arcSource, arcDestination);
        } else {
            createArcAdding(canvas, petriNet, arcSource, arcDestination);
        }
    }

    private void createArcAdding(NetCanvas canvas, PetriNet petriNet, Node arcSource, Node arcDestination) {
        ArcAdding aa = new ArcAdding((Transition) arcSource, (Place) arcDestination, 1);
        aa.setId(generateId());
        petriNet.addArc(aa.getId(), aa);
        Arc2D arc2D = new Arc2D(arcSource.getX(), arcSource.getY(), arcDestination.getX(), arcDestination.getY(), aa);
        canvas.addDrawable(arc2D);
        canvas.addDrawable(new Arc2DActionHandler(arc2D));
    }

    private void createArcTaking(NetCanvas canvas, PetriNet petriNet, Node arcSource, Node arcDestination) {
        ArcTaking at = new ArcTaking((Place) arcSource, (Transition) arcDestination, 1);
        at.setId(generateId());
        petriNet.addArc(at.getId(), at);
        Arc2D arc2D = new Arc2D(arcSource.getX(), arcSource.getY(), arcDestination.getX(), arcDestination.getY(), at);
        canvas.addDrawable(arc2D);
        canvas.addDrawable(new Arc2DActionHandler(arc2D));
    }

    private void testNodesType(Node first, Node second) {
        try {
            if (first.getClass().equals(second.getClass())) {
                throw new ArcNodesSameTypeException("Can not create arc between nodes of the same type");
            }
        } catch (ArcNodesSameTypeException e) {
            System.out.println(e);
        }
    }
}
