package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    // ----------------------------------------------
    // regular case
    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }

    // empty string
    @Test
    public void testCountNumbers2() {
        mycustomstring.setString("");
        assertEquals(0, mycustomstring.countNumbers());
    }

    // exception: NullPointerException
    @Test(expected = NullPointerException.class)
    public void testCountNumbers3() {
        mycustomstring.countNumbers();
    }

    // test all digits
    @Test
    public void testCountNumbers4() {
        mycustomstring.setString("0 1 2 3 4 5 6 7 8 9");
        assertEquals(10, mycustomstring.countNumbers());
    }

    // test single digits
    @Test
    public void testCountNumbers5() {
        mycustomstring.setString("03242987656780987654567897654567897654345678976 ");
        assertEquals(1, mycustomstring.countNumbers());
    }

    // test no digits
    @Test
    public void testCountNumbers6() {
        mycustomstring.setString("sdf gj asdfb gjhnf pplr& $*^ %^UH*&THJ +_)(*&^%$#$%^&*(OMHYUIOly ugvbjk ");
        assertEquals(0, mycustomstring.countNumbers());
    }

    // test different positions between digits and letters (digit first, letters later; letters first digit later; etc)
    @Test
    public void testCountNumbers7() {
        mycustomstring.setString("   89ijhj  (*&*9 HJ93^&* ");
        assertEquals(3, mycustomstring.countNumbers());
    }


    // -----------------------------------------------------------------------
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    // test n=1, from start
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
        mycustomstring.setString("I'd b3tt3r put");
        assertEquals("I'd b3tt3r put", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false));
    }

    // test n=1, from end
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
        mycustomstring.setString("I'd b3tt3r put");
        assertEquals("I'd b3tt3r put", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true));
    }

    // test n > string length, from start
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
        mycustomstring.setString("I'd b3tt3r put");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(10000, false));
    }

    // test n > string length, from end
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
        mycustomstring.setString("I'd b3tt3r put");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(10000, true));
    }

    // test empty string, from start
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
        mycustomstring.setString("");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false));
    }

    // test empty string, from end
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
        mycustomstring.setString("");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true));
    }

    // exception: n == 0, string not null --> IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
        mycustomstring.setString("");
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, true);
    }

    // exception: n == 0, string null --> IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, true);
    }

    // exception: n < 0, string not null --> IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
        mycustomstring.setString("");
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-3, true);
    }

    // n = random ASCii
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
        mycustomstring.setString("");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd('%', true));
    }

    // exception: string == null and n >0, from start --> NullPointerException
    @Test(expected = NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(19, false);
    }

    // exception: string == null and n >0, from end --> NullPointerException
    @Test(expected = NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(19, true);
    }

    // exception: string == null and n <= 0, from start --> IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd15() {
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-9, false);
    }
    // exception: string == null and n <= 0, from end --> IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd16() {
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, true);
    }


    // -----------------------------------------------------------------------
    // test from instructors
    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    // test 1-base indexing
    @Test
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString("39I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(1, 1);
        assertEquals("Three9I'd b3tt3r put s0me d161ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    // test string with NO digits
    @Test
    public void testConvertDigitsToNamesInSubstring3() {
        String S = "qwertyuioop[]]';lkjhgfdsazxcvbnm,./+_)(*&^%$#@!QWERTYUIOP{}|}{:LKJHGFDSAZXCVBNM<>";
        mycustomstring.setString(S);
        mycustomstring.convertDigitsToNamesInSubstring(1, S.length());
        assertEquals(S, mycustomstring.getString());
    }

    // test all digits
    @Test
    public void testConvertDigitsToNamesInSubstring4() {
        String S = "0123456789";
        mycustomstring.setString(S);
        mycustomstring.convertDigitsToNamesInSubstring(1, S.length());
        assertEquals("ZeroOneTwoThreeFourFiveSixSevenEightNine", mycustomstring.getString());
    }

    // exception: start > end -->  IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring5() {
        String S = "0123456789";
        mycustomstring.setString(S);
        mycustomstring.convertDigitsToNamesInSubstring(2, 1);
    }

    // exception: start < 1 -->  MyIndexOutOfBoundsException
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring6() {
        String S = "0123456789";
        mycustomstring.setString(S);
        mycustomstring.convertDigitsToNamesInSubstring(0, 1);
    }

    // exception: end > string length -->  MyIndexOutOfBoundsException
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring7() {
        String S = "sdf";
        mycustomstring.setString(S);
        mycustomstring.convertDigitsToNamesInSubstring(1, S.length()+1);
    }

    // exception: null string --> NullPointerException
    @Test(expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring8() {
        mycustomstring.convertDigitsToNamesInSubstring(1, 2);
    }

    // exception order: null string & start > end --> IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring9() {
        mycustomstring.convertDigitsToNamesInSubstring(3, 2);
    }

}
