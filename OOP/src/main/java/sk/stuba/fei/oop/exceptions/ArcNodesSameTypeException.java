package sk.stuba.fei.oop.exceptions;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class ArcNodesSameTypeException extends Exception {

    public ArcNodesSameTypeException() { super(); }
    public ArcNodesSameTypeException(String message) { super(message); }
    public ArcNodesSameTypeException(String message, Throwable cause) { super(message, cause); }
    public ArcNodesSameTypeException(Throwable cause) { super(cause); }
}
