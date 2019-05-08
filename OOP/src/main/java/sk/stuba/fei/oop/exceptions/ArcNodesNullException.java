/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.exceptions;

public class ArcNodesNullException extends Exception {

    public ArcNodesNullException() { super(); }
    public ArcNodesNullException(String message) { super(message); }
    public ArcNodesNullException(String message, Throwable cause) { super(message, cause); }
    public ArcNodesNullException(Throwable cause) { super(cause); }
}
