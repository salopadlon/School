package sk.stuba.fei.oop.listeners;

import sk.stuba.fei.oop.gui.NetFrame;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
abstract class ButtonListener {

    private NetFrame netFrame;

    ButtonListener(NetFrame netFrame) {
        this.netFrame = netFrame;
    }

    NetFrame getNetFrame() {
        return netFrame;
    }
}
