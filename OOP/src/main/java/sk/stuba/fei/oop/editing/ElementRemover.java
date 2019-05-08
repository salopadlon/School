package sk.stuba.fei.oop.editing;

import sk.stuba.fei.oop.gui.Arc2DActionHandler;
import sk.stuba.fei.oop.gui.Drawable;
import sk.stuba.fei.oop.gui.NetCanvas;
import sk.stuba.fei.oop.petrinet.Arc;

import java.awt.event.MouseEvent;

public class ElementRemover {

//    abstract void remove();
//}

    public ElementRemover(NetCanvas netCanvas, MouseEvent e) {
//        for (Iterator<Drawable> iterator = netCanvas.getDrawableList().iterator(); iterator.hasNext();) {
//            Drawable drawable = iterator.next();
//            if (drawable.contains(e.getX(), e.getY())) {
//                if (drawable.getClass().equals(Arc2DActionHandler.class)) {
//                    netCanvas.getDrawableList().remove(drawable.getElement());
//                    iterator.remove();
//                } else {
//                    iterator.remove();
//                }
//            }
//        }

        for (Drawable drawable : netCanvas.getDrawableList()) {
            if (drawable.contains(e.getX(), e.getY())) {
                if (!(netCanvas.getPetriNet().getArcHashMap().isEmpty())) {
                    if (drawable.getClass().equals(Arc2DActionHandler.class)) {
                        netCanvas.getPetriNet()
                        netCanvas.getDrawableList().remove(drawable.getElement());
                        netCanvas.getDrawableList().remove(drawable);
                    } else {
                        for (Arc arc : netCanvas.getPetriNet().getArcHashMap().values()) {
                            if (arc.getSource().equals(drawable.getElement()) || arc.getDestination().equals(drawable.getElement())) {
                                for (Drawable drawable1 : netCanvas.getDrawableList()) {
                                    if (drawable1.getElement().equals(arc)) {
                                        netCanvas.getDrawableList().remove(drawable1);
                                        netCanvas.getDrawableList().remove(drawable);
                                    }
                                }
                            }
                        }
                    }
                } else {

                    netCanvas.getDrawableList().remove(drawable);
                }
            }
        }
    }
}
