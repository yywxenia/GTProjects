package edu.gatech.seclass.test;
import edu.gatech.seclass.BuggyClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yywxenia on 11/4/16.
 */
public class BuggyClassTestSC5 {

    @Test
    public void testBuggyMethod5() throws Exception {
        BuggyClass sc5 = new BuggyClass();
        sc5.buggyMethod5(0);
    }
}