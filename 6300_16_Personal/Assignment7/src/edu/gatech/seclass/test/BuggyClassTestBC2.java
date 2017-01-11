package edu.gatech.seclass.test;
import edu.gatech.seclass.BuggyClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * >50% BC + reveal fault.
 */
public class BuggyClassTestBC2 {

    @Test
    public void testBuggyMethod2() throws Exception {
        BuggyClass bc2 = new BuggyClass();
        bc2.buggyMethod2(5, 1);
        bc2.buggyMethod2(-5, 0);

    }
}