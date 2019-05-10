package sk.stuba.fei.oop.exceptions;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class ArcWeightLowerThanOneException extends Exception {

    public ArcWeightLowerThanOneException() { super(); }
    public ArcWeightLowerThanOneException(String message) { super(message); }
    public ArcWeightLowerThanOneException(String message, Throwable cause) { super(message, cause); }
    public ArcWeightLowerThanOneException(Throwable cause) { super(cause); }
}
