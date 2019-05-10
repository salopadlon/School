package sk.stuba.fei.oop.petrinet;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
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
