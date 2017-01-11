package edu.gatech.seclass.test;
import edu.gatech.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuggyClassTestSC1b {
    /**
     * <50% SC + reveal fault:
     */
    @Test
    public void testBuggyMethod1() throws Exception {
        BuggyClass sc1b = new BuggyClass();
        sc1b.buggyMethod1(3, 0);
    }
}