package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Sensor open at door"); // (1)
        date = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", event.getDescription());
        long secondsSinceEpochForD = date.getTime() / 1000;
        long secondsSinceEpochForE = event.getDate().getTime() / 1000;

        assertEquals(secondsSinceEpochForD, secondsSinceEpochForE);
        // assertEquals(Math.abs(e.getDate().getTime()) - d.getTime(), -6.0);
        // assertEquals(200.0, foodWithQty.getQty(), 0.01);
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Sensor open at door", event.toString());
    }
}
