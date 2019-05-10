package sk.stuba.fei.oop.exceptions;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class ArcResetNodeInTransitionException extends Exception {

    public ArcResetNodeInTransitionException() { super(); }
    public ArcResetNodeInTransitionException(String message) { super(message); }
    public ArcResetNodeInTransitionException(String message, Throwable cause) { super(message, cause); }
    public ArcResetNodeInTransitionException(Throwable cause) { super(cause); }
}
