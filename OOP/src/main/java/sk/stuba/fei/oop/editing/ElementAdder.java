package sk.stuba.fei.oop.editing;

abstract class ElementAdder {

    private long id;

    long generateId() {
        long leftLimit = 1L;
        long rightLimit = 100L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public long getId() {
        return id;
    }
}
