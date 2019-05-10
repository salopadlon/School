package sk.stuba.fei.oop.transformers;

import sk.stuba.fei.oop.generated.Document;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public abstract class Transformer<T> {

    public abstract T transform(Document document);
}
