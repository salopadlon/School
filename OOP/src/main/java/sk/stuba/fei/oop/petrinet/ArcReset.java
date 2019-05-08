/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.petrinet;

import sk.stuba.fei.oop.exceptions.ArcResetNodeInTransitionException;

public class ArcReset extends Arc {

    private final int weight = 1;

    public ArcReset(Place in, Transition out) {
        super(in, out);
        testNodeIn(in);
        this.getTransition().addArc(this);
    }

    @Override
    void execute() {
        this.getPlace().setTokens(0);
    }

    @Override
    public void test() { }

    private void testNodeIn(Node in) {
        try {
            if (in.getClass() == Transition.class) {
                throw new ArcResetNodeInTransitionException("Reset arc input node can not be of Transition type");
            }

        } catch (ArcResetNodeInTransitionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getWeight() {
        return weight;
    }

    private Place getPlace() {
        return (Place) this.getSource();
    }

    private Transition getTransition() {
        return (Transition) this.getDestination();
    }
}
