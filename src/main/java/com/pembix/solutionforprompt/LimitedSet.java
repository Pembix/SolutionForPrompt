package com.pembix.solutionforprompt;

public interface LimitedSet<T extends Comparable> {

    void add(final T t);

    boolean remove(final T t);

    boolean contains(final T t);

}
