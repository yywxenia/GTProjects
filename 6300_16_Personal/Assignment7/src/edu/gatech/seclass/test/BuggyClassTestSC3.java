package edu.gatech.seclass.test;

import edu.gatech.seclass.BuggyClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class BuggyClassTestSC3 {
    /**
     * 100% SC + reveal fault + not 100% BC:
    */
    @Test
    public void testBuggyMethod3() throws Exception {
        BuggyClass sc3 = new BuggyClass();
        sc3.buggyMethod3(6,2);
        sc3.buggyMethod3(-6,0);
    }
}