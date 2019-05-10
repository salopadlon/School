package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.NetFrame;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
abstract class AddElementListener extends ButtonListener {

    AddElementListener(NetFrame netFrame) {
        super(netFrame);
    }

    long generateId() {
        long id = getNetFrame().getNetCanvas().getDrawableList().size()+1;
        return id;

//        setId(getId()+1);
//        return getId();
//        long leftLimit = 1L;
//        long rightLimit = 100L;
//        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
