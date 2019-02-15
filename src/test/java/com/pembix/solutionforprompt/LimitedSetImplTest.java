package com.pembix.solutionforprompt;

import org.junit.Test;

import static org.junit.Assert.*;

public class LimitedSetImplTest {

    private LimitedSetImpl<String> impl = new LimitedSetImpl<>();

    @Test
    public void addNotOverCapacity() {
        for (int i = 1; i < 6; i++) {
            impl.add("String " + i);
        }
        impl.contains("String 5");
        //if object is duplicate, number of contains should stay the same
        impl.add("String 5");
        impl.add("String 6");
        impl.remove("String 6");

        assertEquals(5, impl.getElements().size());
        //cast to int because of ambiguous method call in assertEquals
        assertEquals(1, (int) impl.getElements().get("String 5"));
        assertTrue(!impl.getElements().containsKey("String 6"));

    }

    @Test
    public void addOverCapacity() {
        //adding 9 regular elements
        for (int i = 1; i < 10; i++) {
            impl.add("String " + i);
            impl.contains("String " + i);
        }
        //add without calling contains()
        impl.add("String 10");
        //add eleventh element
        impl.add("String added");

        assertEquals(10, impl.getElements().size());
        assertNotNull(impl.getElements().get("String added"));
        //the only string with 0 calls to contains(), so will be removed
        assertNull(impl.getElements().get("String 10"));
    }


}
