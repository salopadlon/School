package sk.stuba.fei.oop.petrinet;

import sk.stuba.fei.oop.exceptions.TransitionNotExecutableException;
import java.util.ArrayList;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class Transition extends Node {

    private ArrayList<Arc> arcs = new ArrayList<>();
    private boolean executable = true;

    public Transition(Long id, String label, int x, int y) {
        super(id, label, x, y);
    }

    public boolean isExecutable() {
        return executable;
    }

    void setExecutable(boolean executable) {
        this.executable = executable;
    }

    void testExecutable() throws TransitionNotExecutableException {
        if (!this.isExecutable()) {
            throw new TransitionNotExecutableException("Transition " + this.getLabel() + " is not executable");
        }
    }

    void addArc(Arc arc) {
        arcs.add(arc);
    }

    ArrayList<Arc> getArcs() {
        return arcs;
    }


}
