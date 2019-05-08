/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.petrinet;

public abstract class Node {

    private long id;
    private String label;
    private int x,y;

    Node(Long id, String label, int x, int y) {
        this.id = id;
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public long getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTokens() {
        return this.getTokens();
    }
}
