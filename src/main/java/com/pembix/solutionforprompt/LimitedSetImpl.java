package com.pembix.solutionforprompt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LimitedSetImpl<T> implements LimitedSet<T> {

    private int capacity = 10;
    private Set<T> elements = new HashSet<>();
    private Map<T, Integer> callsPerElement = new HashMap<>();

    LimitedSetImpl() {
    }

    public LimitedSetImpl(int capacity) {
        this.capacity = capacity;
    }

    //for testing purposes
    Set<T> getElements() {
        return elements;
    }

    //for testing purposes
    Map<T, Integer> getCallsPerElement() {
        return callsPerElement;
    }

    public void add(T o) {
        if (elements.size() >= capacity) {
            //remove element with the least number of calls
            removeUnpopularElement();
        }
        if (elements.add(o)) {
            //if this object is not duplicate of the other object in the set, then we put zero for a number of calls
            callsPerElement.put(o, 0);
        }
    }

    public boolean remove(T o) {
        callsPerElement.remove(o);
        return elements.remove(o);
    }

    public boolean contains(T o) {
        if (elements.contains(o)) {
            callsPerElement.put(o, callsPerElement.get(o) + 1);
            return true;
        } else {
            return false;
        }
    }

    private void removeUnpopularElement() {
        Map.Entry<T, Integer> firstEntry = callsPerElement.entrySet().iterator().next();
        T toBeDeleted = firstEntry.getKey();
        Integer minimum = firstEntry.getValue();

        for (Map.Entry<T, Integer> entry : callsPerElement.entrySet()) {
            if (entry.getValue() < minimum) {
                minimum = entry.getValue();
                toBeDeleted = entry.getKey();
            }
        }

        elements.remove(toBeDeleted);
        callsPerElement.remove(toBeDeleted);
    }
}
