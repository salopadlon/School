/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.exceptions;

public class ArcWeightLowerThanOneException extends Exception {

    public ArcWeightLowerThanOneException() { super(); }
    public ArcWeightLowerThanOneException(String message) { super(message); }
    public ArcWeightLowerThanOneException(String message, Throwable cause) { super(message, cause); }
    public ArcWeightLowerThanOneException(Throwable cause) { super(cause); }
}
