package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.NetFrame;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
abstract class AddElementListener extends ButtonListener {

    private static long id;

    AddElementListener(NetFrame netFrame) {
        super(netFrame);
    }

    long getId() {
        return id;
    }

    public void setId(long id) {
        AddElementListener.id = id;
    }
}
