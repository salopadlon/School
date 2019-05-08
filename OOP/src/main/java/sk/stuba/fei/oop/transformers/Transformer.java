/**
 *
 * @project: FEI OOP PetriNet Editor
 * @author: Pavel Sadlon
 *
 */

package sk.stuba.fei.oop.transformers;

import sk.stuba.fei.oop.generated.Document;

public abstract class Transformer<T> {

    public abstract T transform(Document document);
}
