package sk.stuba.fei.oop.petrinet;

import sk.stuba.fei.oop.exceptions.*;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public abstract class Arc {

    private Node source, destination;
    private int weight;
    private long id;

    Arc(Node in, Node out, int weight) {
        testNodes(in, out);
        this.source = in;
        this.destination = out;
        this.weight = weight;
    }

    Arc(Node in, Node out) {
        testNodes(in, out);
        this.source = in;
        this.destination = out;
    }

    abstract void execute();
    public abstract void test() throws ArcWeightLowerThanOneException, TransitionNotExecutableException, LowTokensSumException;

    private void testNodes(Node first, Node second) {
        testNodesType(first, second);
        testNodesNull(first, second);
    }

    private void testNodesType(Node first, Node second) {
        try {
            if (first.getClass().equals(second.getClass())) {
                throw new ArcNodesSameTypeException("Can not create arc between nodes of the same type");
            }
        } catch (ArcNodesSameTypeException e) {
            System.out.println(e);
        }
    }

    private void testNodesNull(Node first, Node second) {
        try {
            if (first == null || second == null ) {
                throw new ArcNodesNullException("Can not create arc without nodes");
            }
        } catch (ArcNodesNullException e) {
            System.out.println(e);
        }
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
