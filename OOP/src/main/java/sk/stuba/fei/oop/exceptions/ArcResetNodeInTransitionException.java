/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.exceptions;

public class ArcResetNodeInTransitionException extends Exception {

    public ArcResetNodeInTransitionException() { super(); }
    public ArcResetNodeInTransitionException(String message) { super(message); }
    public ArcResetNodeInTransitionException(String message, Throwable cause) { super(message, cause); }
    public ArcResetNodeInTransitionException(Throwable cause) { super(cause); }
}
