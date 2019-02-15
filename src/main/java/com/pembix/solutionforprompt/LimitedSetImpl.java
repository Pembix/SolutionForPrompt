package com.pembix.solutionforprompt;

import com.google.common.collect.TreeMultimap;

import java.util.HashMap;
import java.util.Map;

public class LimitedSetImpl<T extends Comparable> implements LimitedSet<T> {

    private int capacity = 10;
    private Map<T, Integer> elements = new HashMap<>();
    private TreeMultimap<Integer, T> elementsByCall = TreeMultimap.create();

    LimitedSetImpl() {
    }

    public LimitedSetImpl(int capacity) {
        this.capacity = capacity;
    }

    //for testing purposes
    Map<T, Integer> getElements() {
        return elements;
    }

    public void add(T o) {
        //if elements contains key, number of calls contains() should be preserved
        if (!elements.containsKey(o)) {
            if (elements.size() >= capacity) {
                //remove element with the least number of calls
                removeUnpopularElement();
            }
            elements.put(o, 0);
            elementsByCall.put(0, o);
        }
    }

    public boolean remove(T o) {
        elementsByCall.remove(elements.get(o), o);
        return elements.remove(o) == null ? false : true;
    }

    public boolean contains(T o) {
        if (elements.containsKey(o)) {
            int calls = elements.getOrDefault(o, 0);
            elementsByCall.remove(calls, o);
            elementsByCall.put(calls + 1, o);
            elements.put(o, calls + 1);
            return true;
        } else {
            return false;
        }
    }

    private void removeUnpopularElement() {
        Map.Entry<Integer, T> entry = elementsByCall.entries().iterator().next();
        T toBeDeleted = entry.getValue();
        Integer calls = entry.getKey();
        elements.remove(toBeDeleted);
        elementsByCall.remove(calls, toBeDeleted);
    }
}
