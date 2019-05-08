/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.exceptions;

public class TransitionNotExecutableException extends Exception {

    public TransitionNotExecutableException() { super(); }
    public TransitionNotExecutableException(String message) { super(message); }
    public TransitionNotExecutableException(String message, Throwable cause) { super(message, cause); }
    public TransitionNotExecutableException(Throwable cause) { super(cause); }
}
