package edu.gatech.seclass.test;

import edu.gatech.seclass.BuggyClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yywxenia on 11/4/16.
 */
public class BuggyClassTestBC4 {
    /**
     * 100%BC + not reveal.
     */
    @Test
    public void testBuggyMethod4() throws Exception {
        BuggyClass bc4 = new BuggyClass();
        bc4.buggyMethod4(5,1);
        bc4.buggyMethod4(-5,1);
        bc4.buggyMethod4(0,1);
    }
}