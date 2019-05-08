package sk.stuba.fei.oop.editing;

import sk.stuba.fei.oop.gui.NetCanvas;
import sk.stuba.fei.oop.gui.Place2D;
import sk.stuba.fei.oop.petrinet.PetriNet;
import sk.stuba.fei.oop.petrinet.Place;

public class PlaceAdder extends ElementAdder {

    public PlaceAdder(NetCanvas canvas, PetriNet petriNet, int x, int y) {
        Place place = new Place(generateId(),"p",0,x,y);
        petriNet.addPlace(place.getId(), place);
        canvas.addDrawable(new Place2D(x,y,place));
    }
}
