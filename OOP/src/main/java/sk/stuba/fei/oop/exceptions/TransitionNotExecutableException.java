package sk.stuba.fei.oop.exceptions;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class TransitionNotExecutableException extends Exception {

    public TransitionNotExecutableException() { super(); }
    public TransitionNotExecutableException(String message) { super(message); }
    public TransitionNotExecutableException(String message, Throwable cause) { super(message, cause); }
    public TransitionNotExecutableException(Throwable cause) { super(cause); }
}
