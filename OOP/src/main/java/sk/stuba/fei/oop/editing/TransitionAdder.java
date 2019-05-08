package sk.stuba.fei.oop.editing;

import sk.stuba.fei.oop.gui.NetCanvas;
import sk.stuba.fei.oop.gui.Transition2D;
import sk.stuba.fei.oop.petrinet.PetriNet;
import sk.stuba.fei.oop.petrinet.Transition;

public class TransitionAdder extends ElementAdder {

    public TransitionAdder(NetCanvas canvas, PetriNet petriNet, int x, int y) {
        Transition transition = new Transition(generateId(),"t",x,y);
        petriNet.addTransition(transition.getId(),transition);
        canvas.addDrawable(new Transition2D(x,y, transition, petriNet));
    }
}
