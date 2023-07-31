package com;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test1Test {


    @Test
    public void when_exceedsThreshold_then_limit() {
        Test1 toBeTested = new Test1();

        int customerId = 1;

        boolean expected = false;
        boolean actual = true;

        for (int i = 0; i < 4; i++) {
            actual = toBeTested.shouldRateLimit(customerId);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void when_withinThreshold_then_allow() throws Exception {
        Test1 toBeTested = new Test1();

        int customerId = 2;

        boolean expected = false;
        boolean actual = true;

        for (int i = 0; i < 4; i++) {
            actual = toBeTested.shouldRateLimit(customerId);
        }

        assertEquals(expected, actual);

        Thread.sleep(2000);

        expected = true;
        actual = false;

        for (int i = 0; i < 3; i++) {
            actual = toBeTested.shouldRateLimit(customerId);
        }

        assertEquals(expected, actual);
    }
}
