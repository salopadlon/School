/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.petrinet;

import sk.stuba.fei.oop.exceptions.ArcWeightLowerThanOneException;
import sk.stuba.fei.oop.exceptions.LowTokensSumException;
import sk.stuba.fei.oop.exceptions.TransitionNotExecutableException;

public class ArcTaking extends Arc {

    public ArcTaking(Place in, Transition out, int weight) {
        super(in, out, weight);
        this.getTransition().addArc(this);
    }

    @Override
    public void test() throws ArcWeightLowerThanOneException, TransitionNotExecutableException, LowTokensSumException {
        testTokensSum();
        testWeight();
        this.getTransition().testExecutable();
    }

    private void testTokensSum() throws LowTokensSumException {
        if (this.getPlace().getTokens() < this.getWeight()) {
            this.getTransition().setExecutable(false);
            throw new LowTokensSumException("Arc weight is higher than number of tokens in place " + this.getPlace().getLabel());
        } else { this.getTransition().setExecutable(true); }
    }

    private void testWeight() throws ArcWeightLowerThanOneException {
        if (this.getWeight() <= 0) {
            throw new ArcWeightLowerThanOneException("Arc weight lower than one");
        }
    }

    @Override
    void execute() {
        this.getPlace().setTokens(this.getPlace().getTokens() - this.getWeight());
    }

    private Transition getTransition() {
        return (Transition) this.getDestination();
    }

    private Place getPlace() {
        return (Place) this.getSource();
    }
}
