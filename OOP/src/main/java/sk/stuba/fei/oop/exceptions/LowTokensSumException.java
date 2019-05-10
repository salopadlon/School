package sk.stuba.fei.oop.exceptions;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class LowTokensSumException extends Exception {

    public LowTokensSumException() { super(); }
    public LowTokensSumException(String message) { super(message); }
    public LowTokensSumException(String message, Throwable cause) { super(message, cause); }
    public LowTokensSumException(Throwable cause) { super(cause); }
}
