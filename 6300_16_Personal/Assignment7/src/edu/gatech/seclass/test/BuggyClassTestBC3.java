package edu.gatech.seclass.test;

import edu.gatech.seclass.BuggyClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yywxenia on 11/4/16.
 */
public class BuggyClassTestBC3 {
    @Test
    /**
     * 100% BC + not reveal fault.
     */
    public void testBuggyMethod3() throws Exception {
        BuggyClass bc3 = new BuggyClass();
        bc3.buggyMethod3(6,2);
        bc3.buggyMethod3(-6,2);
        bc3.buggyMethod3(0,2);
    }
}