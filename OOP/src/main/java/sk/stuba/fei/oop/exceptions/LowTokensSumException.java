/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.exceptions;

public class LowTokensSumException extends Exception {

    public LowTokensSumException() { super(); }
    public LowTokensSumException(String message) { super(message); }
    public LowTokensSumException(String message, Throwable cause) { super(message, cause); }
    public LowTokensSumException(Throwable cause) { super(cause); }
}
