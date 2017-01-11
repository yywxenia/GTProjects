package edu.gatech.seclass.test;
import edu.gatech.seclass.BuggyClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuggyClassTestSC4 {
    /**
     * 100%SC + reveal fault.
     */
    @Test
    public void testBuggyMethod4() throws Exception {
        BuggyClass sc4 = new BuggyClass();
        sc4.buggyMethod4(5,1);
        sc4.buggyMethod4(-5,0);
    }
}