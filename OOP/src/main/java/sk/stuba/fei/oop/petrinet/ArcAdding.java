package sk.stuba.fei.oop.petrinet;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class ArcAdding extends Arc {

    public ArcAdding(Transition in, Place out, int weight) {
        super(in, out, weight);
        this.getTransition().addArc(this);
    }

    @Override
    void execute() {
        this.getPlace().setTokens(this.getPlace().getTokens() + this.getWeight());
    }

    @Override
    public void test() { }

    private Place getPlace() {
        return (Place) this.getDestination();
    }

    private Transition getTransition() {
        return (Transition) this.getSource();
    }
}
