package edu.gatech.seclass.test;
import edu.gatech.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuggyClassTestSC1a {
    /**
     * 100% SC + not reveal fault:
     */
    @Test
    public void testBuggyMethod1() throws Exception {
        BuggyClass sc1a = new BuggyClass();
        sc1a.buggyMethod1(4, -4);
        sc1a.buggyMethod1(-6, 2);
        }
}
