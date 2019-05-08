/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.petrinet;

public class Place extends Node {

    private int tokens;

    public Place(Long id, String label, int tokens, int x, int y) {
        super(id, label, x, y);
        this.tokens = tokens;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
}
