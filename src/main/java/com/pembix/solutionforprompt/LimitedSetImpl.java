package com.pembix.solutionforprompt;

import java.util.HashMap;
import java.util.Map;

public class LimitedSetImpl<T> implements LimitedSet<T> {

    private int capacity = 10;
    private Map<T, Integer> elements = new HashMap<>();

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
        if (elements.size() >= capacity) {
            //remove element with the least number of calls
            removeUnpopularElement();
        }
        //if elements contains key, number of calls contains() should be preserved
        if (!elements.containsKey(o)) {
            elements.put(o, 0);
        }
    }

    public boolean remove(T o) {
        return elements.remove(o) == null ? false : true;
    }

    public boolean contains(T o) {
        if (elements.containsKey(o)) {
            elements.put(o, elements.getOrDefault(o, 0) + 1);
            return true;
        } else {
            return false;
        }
    }

    private void removeUnpopularElement() {
        Map.Entry<T, Integer> firstEntry = elements.entrySet().iterator().next();
        T toBeDeleted = firstEntry.getKey();
        Integer minimum = firstEntry.getValue();

        for (Map.Entry<T, Integer> entry : elements.entrySet()) {
            if (entry.getValue() < minimum) {
                minimum = entry.getValue();
                toBeDeleted = entry.getKey();
            }
        }

        elements.remove(toBeDeleted);
    }
}
