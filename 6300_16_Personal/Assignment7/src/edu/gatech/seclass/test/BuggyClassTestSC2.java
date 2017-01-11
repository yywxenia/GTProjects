package edu.gatech.seclass.test;
import edu.gatech.seclass.BuggyClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 100% SC + not reveal fault.
 */
public class BuggyClassTestSC2 {

    @Test
    public void testBuggyMethod2() throws Exception {
        BuggyClass sc2 = new BuggyClass();
        sc2.buggyMethod2(5, 1);
        sc2.buggyMethod2(-5, 1);
        sc2.buggyMethod2(0, 1);
    }
}