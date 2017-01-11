package edu.gatech.seclass.replace;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * I modified some parts from my original test.
 */


public class MyMainTest {
    
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;
    
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    
    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }
    
    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }
    
    // Some utilities
    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }
    ///// Input file samples
    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Hello Bill,\n" +
                         "This is a test file for the replace utility\n" +
                         "Let's make sure it has at least a few lines\n" +
                         "so that we can create some interesting test cases...\n" +
                         "And let's say \"Hello bill\" again!");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Howdy Bill,\n" +
                         "This is another test file for the replace utility\n" +
                         "that contains a list:\n" +
                         "-a) Item 1\n" +
                         "-b) Item 2\n" +
                         "...\n" +
                         "and says \"howdy Bill\" twice");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                         "It is important to know your abc and 123," +
                         "so you should study it\n" +
                         "and then repeat with me: abc and 123");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile4() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("My id is yiweiyan, and my school is Georgia Institute of Technology");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile5() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile6() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Fruit shopping: Apple, Apple, apple, banana,\n" +
                         "Banana, Orange, banana, orange juice, tomato, apple, and Banana?");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile7() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Apple, Pen, pineapple, pen, apple,\n" +
                         "pineapple, apple, pen, Apple,\n?" +
                         "Pineapple Pen");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile8() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("AaBbCcDdEeFfGgHhIiJjKkLlMmNn123123456456111222333444555666");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile9() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Aa-B-bCcDdEe-F-fGgHh-I-iJjKk-L-lMmNn");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile10() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("          ");
        
        fileWriter.close();
        return file1;
    }
    
    
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    
    
    //////////////////////////////////////////////////////////////////////
    @Test
    public void testMain1() throws Exception {
        /**
         * Implementation of test frame #<5>
         * Test Case 5             <error>  (follows [if])
         Input Strings(FROM) Length :  Empty
         */
        File inputFile1 = createInputFile1();
        String args[] = {"","TO", "--", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*",
                     errStream.toString().trim());
    }
    
    @Test
    public void testMain2() throws Exception {
        /**
         * Newly created test case
         * Purpose: Test if miss provide FROM or TO
         */
        File inputFile1 = createInputFile1();
        String args[] = {"position", "--", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*",
                     errStream.toString().trim());
    }
    
    @Test  //????
    public void testMain3() throws Exception {
        /**
         * Implementation of test frame #<9>
         * Test Case 9             <error>
         Targeted file numbers :  Zero
         */
        String args[] = {"Bill", "Jack", "--", };
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*",
                     errStream.toString().trim());
    }
    
    @Test
    public void testMain4() throws Exception {
        /**
         * Implementation of test frame #<59>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  l
         */
        File inputFile1 = createInputFile1();
        String args[] = {"-l", "bill", "Mary", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Hello Bill,\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"Hello Mary\" again!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain5() throws Exception {
        /**
         * Implementation of test frame #<17>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Only one
         Filename presented                       :  Valid presented file name
         OPT used                                 :  i
         */
        File inputFile1 = createInputFile4();
        String args[] = {"-i", "YiweiYan", "", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "My id is , and my school is Georgia Institute of Technology";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain6() throws Exception {
        /**
         * Implementation of test frame #<57>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  i,b
         */
        
        File inputFile1 = createInputFile1();
        String args[] = {"-i",  "Bill", "Mary", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Hello Mary,\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"Hello Mary\" again!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain7() throws Exception {
        /**
         * Implementation of test frame #<62>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  No
         Filename presented                       :  Valid presented file name
         OPT used                                 :  None
         */
        File inputFile1 = createInputFile1();
        String args[] = {"Yiwei", "Mary", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Hello Bill,\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"Hello bill\" again!";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain8() throws Exception {
        /**
         * Implementation of test frame #<68>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  No
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  f,l,b
         */
        File inputFile1 = createInputFile6();
        String args[] = {"-f", "-l", "-b", "Apple", "Pineapple", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Fruit shopping: Pineapple, Pineapple, apple, banana,\n" +
        "Banana, Orange, banana, orange juice, tomato, apple, and Banana?";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain9() throws Exception {
        /**
         * Implementation of test frame #<73>
         */
        File inputFile1 = createInputFile6();
        File inputFile2 = createInputFile4();
        
        String args[] = {"and", "&", "--", inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Fruit shopping: Apple, Apple, apple, banana,\n" +
        "Banana, Orange, banana, orange juice, tomato, apple, & Banana?";
        String expected2 = "My id is yiweiyan, & my school is Georgia Institute of Technology";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }
    
    @Test
    public void testMain10() throws Exception {
        /**
         * Implementation of test frame #<78>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  Many
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  f,l,b
         */
        File inputFile1 = createInputFile6();
        File inputFile2 = createInputFile7();
        
        String args[] = {"-f", "-l", "-b", "apple", "BIG APPLE", "--",
            inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Fruit shopping: Apple, Apple, BIG APPLE, banana,\n" +
        "Banana, Orange, banana, orange juice, tomato, BIG APPLE, and Banana?";
        
        String expected2 = "Apple, Pen, pineBIG APPLE, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "PineBIG APPLE Pen";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    public void testMain11() throws Exception {
        /**
         * Implementation of test frame #<87>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  Many
         File content                             :  Not empty
         Wether occur considering case            :  No
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  f,l,i,b
         */
        File inputFile1 = createInputFile6();
        File inputFile2 = createInputFile7();
        
        String args[] = {"-f", "-l", "-i", "-b", "apple", "BIG APPLE", "--",
            inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Fruit shopping: BIG APPLE, Apple, apple, banana,\n" +
        "Banana, Orange, banana, orange juice, tomato, BIG APPLE, and Banana?";
        
        String expected2 = "BIG APPLE, Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "PineBIG APPLE Pen";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    public void testMain12() throws Exception {
        /**
         * Implementation of test frame #<55>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  None
         */
        File inputFile1 = createInputFile7();
        
        String args[] = {"apple", "BIG APPLE", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Apple, Pen, pineBIG APPLE, pen, BIG APPLE,\n" +
        "pineBIG APPLE, BIG APPLE, pen, Apple,\n?" +
        "PineBIG APPLE Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    
    @Test
    public void testMain13() throws Exception {
        /**
         * Implementation of test frame #<44>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Empty
         Targeted file numbers                    :  Many
         File content                             :  Not empty
         Wether occur considering case            :  No
         Total target strings occurrences in file :  Only one
         Filename presented                       :  Valid presented file name
         OPT used                                 :  None
         */
        File inputFile1 = createInputFile4();
        File inputFile2 = createInputFile6();
        
        String args[] = {"and", "", "--", inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "My id is yiweiyan,  my school is Georgia Institute of Technology";
        String expected2 = "Fruit shopping: Apple, Apple, apple, banana,\n" +
        "Banana, Orange, banana, orange juice, tomato, apple,  Banana?";
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }
    
    @Test
    public void testMain14() throws Exception {
        /**
         * Implementation of test frame #<23>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  l
         */
        File inputFile1 = createInputFile7();
        
        String args[] = {"-l", "ea", "", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Apple, Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pinpple Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain15() throws Exception {
        /**
         * Implementation of test frame #<37>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Empty
         Targeted file numbers                    :  Many
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  None
         */
        File inputFile1 = createInputFile7();
        
        String args[] = {"Pe", "", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Apple, n, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pineapple n";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain16() throws Exception {
        /**
         * Implementation of test frame #<42>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Empty
         Targeted file numbers                    :  Many
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  f,l,b
         */
        File inputFile1 = createInputFile6();
        File inputFile2 = createInputFile7();
        
        String args[] = {"-f", "-l", "-b", "apple", "", "--",
            inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Fruit shopping: Apple, Apple, , banana,\n" +
        "Banana, Orange, banana, orange juice, tomato, , and Banana?";
        
        String expected2 = "Apple, Pen, pine, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pine Pen";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    public void testMain17() throws Exception {
        /**
         * Implementation of test frame #<85>
         * Test Case 85            (Key = 3.2.2.3.2.3.2.2.3.3.4.)
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  Many
         File content                             :  Not empty
         Wether occur considering case            :  No
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  i,b
         */
        File inputFile1 = createInputFile4();
        File inputFile2 = createInputFile4();
        
        String args[] = {"-i", "-b", "apple", "bb", "--",
            inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args);
        String expected1 = "My id is yiweiyan, and my school is Georgia Institute of Technology";
        String expected2 = "My id is yiweiyan, and my school is Georgia Institute of Technology";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    public void testMain18() throws Exception {
        /**
         * Implementation of test frame #<81>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  Many
         File content                             :  Not empty
         Wether occur considering case            :  No
         Total target strings occurrences in file :  Only one
         Filename presented                       :  Valid presented file name
         OPT used                                 :  i
         */
        File inputFile1 = createInputFile8();
        File inputFile2 = createInputFile9();
        String args[] = {"-i", "cc", "xxxx", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        String expected1 = "AaBbxxxxDdEeFfGgHhIiJjKkLlMmNn123123456456111222333444555666";
        
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain19() throws Exception {
        /**
         * Implementation of test frame #<53>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Not empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Only one
         Filename presented                       :  Valid presented file name
         OPT used                                 :  i
         */
        File inputFile1 = createInputFile8();
        String args[] = {"-i", "456456", "12 4 ", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "AaBbCcDdEeFfGgHhIiJjKkLlMmNn12312312 4 111222333444555666";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain20() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test TO content with the quote
         */
        File inputFile1 = createInputFile8();
        String args[] = {"111", "\"xxxx\"", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "AaBbCcDdEeFfGgHhIiJjKkLlMmNn123123456456\"xxxx\"222333444555666";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain21() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test "-i" and TO content with the quote
         */
        File inputFile1 = createInputFile8();
        String args[] = {"-i", "aa", "\"xxxx\"", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "\"xxxx\"BbCcDdEeFfGgHhIiJjKkLlMmNn123123456456111222333444555666";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain22() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test "-f" and TO content with the quote + space, single target file
         */
        File inputFile1 = createInputFile1();
        
        String args[] = {"-f", "Hello Bill", "\"H B\"", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "\"H B\",\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"Hello bill\" again!";
        
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain23() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test "-l", "-b" and TO content with the quote
         */
        File inputFile1 = createInputFile2();
        
        String args[] = {"-l","-b", "Howdy Bill", "\"H B\"", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "\"H B\",\n" +
        "This is another test file for the replace utility\n" +
        "that contains a list:\n" +
        "-a) Item 1\n" +
        "-b) Item 2\n" +
        "...\n" +
        "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain24() throws Exception{
        /**
         * Newly created test case.
         * Purpose: Test "-f", "-l", "-b" and TO content with the quote
         */
        File inputFile1 = createInputFile1();
        
        String args[] = {"-f","-l","-b", "Hello Bill", "\"H B\"", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "\"H B\",\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"Hello bill\" again!";
        
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain25() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test "-i", "-f", "-l", "-b" and TO content with the quote
         */
        File inputFile1 = createInputFile1();
        
        String args[] = {"-i", "-f","-l","-b", "Hello Bill", "\"H  B\"", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "\"H  B\",\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"\"H  B\"\" again!";
        
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain26() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test "-f", "-l", "-b" and TO content with the quote, multiple target files.
         */
        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();
        
        String args[] = {"-f","-l","-b", "Howdy Bill", "\"HD\"", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Hello Bill,\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"Hello bill\" again!";
        
        String expected2 = "\"HD\",\n" +
        "This is another test file for the replace utility\n" +
        "that contains a list:\n" +
        "-a) Item 1\n" +
        "-b) Item 2\n" +
        "...\n" +
        "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    public void testMain27() throws Exception {
        /**
         * Newly created test case.
         * Purpose: how can use replace to substitute strings that begin with a dash (“-”). With "-f", "-b".
         */
        
        File inputFile = createInputFile2();
        
        String args1[] = {"-f", "-b", "--", "-a", "1", "--", inputFile.getPath()};
        Main.main(args1);
        
        String expected = "Howdy Bill,\n" +
        "This is another test file for the replace utility\n" +
        "that contains a list:\n" +
        "1) Item 1\n" +
        "-b) Item 2\n" +
        "...\n" +
        "and says \"howdy Bill\" twice";
        
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test
    public void testMain28() throws Exception {
        /**
         * Newly created test case.
         * Purpose: how can use replace to substitute strings that begin with a dash (“-”).
         */
        
        File inputFile = createInputFile2();
        
        String args1[] = {"--", "-b", "Second", "--", inputFile.getPath()};
        Main.main(args1);
        
        String expected = "Howdy Bill,\n" +
        "This is another test file for the replace utility\n" +
        "that contains a list:\n" +
        "-a) Item 1\n" +
        "Second) Item 2\n" +
        "...\n" +
        "and says \"howdy Bill\" twice";
        
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    @Test
    public void testMain29() throws Exception {
        /**
         * Newly created test case.
         * Purpose: how can use replace to substitute strings that begin with a dash (“-”). With "-i".
         
         */
        File inputFile = createInputFile9();
        String args1[] = {"-f", "--", "-i", "888", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "Aa-B-bCcDdEe-F-fGgHh-I888JjKk-L-lMmNn";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    @Test
    public void testMain30() throws Exception {
        /**
         * Newly created test case.
         * Purpose: how can use replace to substitute strings that begin with a dash (“-”). With "-f"
         
         */
        File inputFile = createInputFile9();
        String args1[] = {"-f", "--", "-F", "<delete>", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "Aa-B-bCcDdEe<delete>-fGgHh-I-iJjKk-L-lMmNn";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    @Test
    public void testMain31() throws Exception {
        /**
         * Newly created test case.
         * Purpose: how can use replace to substitute strings that begin with a dash (“-”). With "-l".
         */
        File inputFile = createInputFile9();
        String args1[] = {"-l", "--", "-F", "<delete>", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "Aa-B-bCcDdEe<delete>-fGgHh-I-iJjKk-L-lMmNn";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    
    @Test
    public void testMain32() throws Exception {
        /**
         * Newly created test case.
         * Purpose: how can use replace to substitute strings that begin with a dash (“-”).
         * With "-i","-f","-l", and quote.
         */
        File inputFile = createInputFile9();
        String args1[] = {"-i","-f","-l", "--", "f", "\"<NA>\"", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "Aa-B-bCcDdEe-\"<NA>\"-\"<NA>\"GgHh-I-iJjKk-L-lMmNn";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    @Test
    public void testMain33() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Multi-pairs of from-to: error
         */
        File inputFile = createInputFile9();
        String args[] = {"a","z", "B", "Y", "c", "x", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Az-Y-bCxDdEe-F-fGgHh-I-iJjKk-L-lMmNn";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        
        // Modify to accept multi-pair of FROM-TO:
        //assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*",
        //errStream.toString().trim());
    }
    
    @Test
    public void testMain34() throws Exception {
        /**
         * Newly created test case.
         * Purpose: how can use replace to substitute strings that begin with a dash (“-”).
         * With "-i","-f".
         */
        File inputFile = createInputFile9();
        String args1[] = {"-i","-f", "--", "I", "888", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "Aa-B-bCcDdEe-F-fGgHh-888-iJjKk-L-lMmNn";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    @Test
    public void testMain35() throws Exception {
        /**
         * Newly created test case.
         * Purpose: how can use replace to substitute strings that begin with a dash (“-”).
         * With "-i","-l".
         */
        File inputFile = createInputFile9();
        String args1[] = {"-i","-l", "--", "I", "888", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "Aa-B-bCcDdEe-F-fGgHh-I-888JjKk-L-lMmNn";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    @Test
    public void testMain36() throws Exception {
        /**
         * Implementation of test frame #<9>
         Targeted file numbers :  Zero
         */
        String args1[] = {"-b", "111", "123", "--", };
        Main.main(args1);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*",
                     errStream.toString().trim());
    }
    
    @Test
    public void testMain37() throws Exception {
        /**
         * Implementation of test frame #<24>
         The number of inputs (FROM and TO)       :  One
         Input Strings(FROM) Length               :  More than one character
         Input Strings(TO) Length                 :  Empty
         Targeted file numbers                    :  One
         File content                             :  Not empty
         Wether occur considering case            :  Yes
         Total target strings occurrences in file :  Many
         Filename presented                       :  Valid presented file name
         OPT used                                 :  f,l,b
         */
        
        File inputFile1 = createInputFile7();
        
        String args[] = {"-f","-l","-b", "apple", "FRUIT", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Apple, Pen, pineFRUIT, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "PineFRUIT Pen";
        
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    
    @Test
    public void testMain38() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test a blank replace by empty. (like delete the blank between words)
         */
        File inputFile1 = createInputFile4();
        
        String args[] = {" ", "", "--", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Myidisyiweiyan,andmyschoolisGeorgiaInstituteofTechnology";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain39() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test FROM is some blanks without contents. with "-f", "-l".
         */
        File inputFile1 = createInputFile4();
        
        String args[] = {"-f", "-l", " ", "", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Myid is yiweiyan, and my school is Georgia Institute ofTechnology";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain40() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test FROM content with blank on the left side. Using "-l".
         */
        File inputFile1 = createInputFile6();
        String args[] = {"-l", " Apple", "XXXX", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Fruit shopping: Apple,XXXX, apple, banana,\n" +
        "Banana, Orange, banana, orange juice, tomato, apple, and Banana?";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain41() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test FROM content with blank on the left side. Using "-l", "-i".
         */
        File inputFile1 = createInputFile6();
        String args[] = {"-i", "-l", " Apple", "XXXX", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Fruit shopping: Apple, Apple, apple, banana,\n" +
        "Banana, Orange, banana, orange juice, tomato,XXXX, and Banana?";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain42() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test FROM content with blank on the left side. Using "-l", "-f", "-i".
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-i", "-f", "-l", " Apple", "XXXX", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Apple, Pen, pineapple, pen,XXXX,\n" +
        "pineapple, apple, pen,XXXX,\n?" +
        "Pineapple Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain43() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test FROM content without space on the left side. Using "-l", "-f", "-i".
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-i", "-f", "-l", "Apple", "XXXX", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "XXXX, Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "PineXXXX Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain44() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Test "123" replace by "< numbers >", using "-i".
         */
        File inputFile = createInputFile3();
        String args[] = {"-i", "123", "< numbers >", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and < numbers >?\n" +
        "It is important to know your abc and < numbers >," +
        "so you should study it\n" +
        "and then repeat with me: abc and < numbers >";
        
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    @Test
    public void testMain45() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is [     ] with 5 spaces inside. Using "-i","-f"
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-i", "-f", "apple", "[     ]", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "[     ], Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pineapple Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain46() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is [     ] with 5 spaces inside. Using "-i","-f", "-l"
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-i", "-f", "-l", "apple", "[     ]", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "[     ], Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pine[     ] Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain47() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is [     ] with 5 spaces inside. Using "-f","-l", "-b"
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-f","-l", "-b", "apple", "[     ]", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Apple, Pen, pine[     ], pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pine[     ] Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain48() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is [     ] with 5 spaces inside. Using "-i","-f","-l", "-b" with multiple targeted files
         */
        File inputFile1 = createInputFile7();
        File inputFile2 = createInputFile6();
        String args[] = {"-i", "-f","-l", "-b", "apple", "[     ]", "--", inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args);
        String expected1 = "[     ], Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pine[     ] Pen";
        
        String expected2 = "Fruit shopping: [     ], Apple, apple, banana,\n" +
        "Banana, Orange, banana, orange juice, tomato, [     ], and Banana?";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }
    
    @Test
    public void testMain49() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is "b". Using "-b" with one targeted file
         */
        File inputFile1 = createInputFile7();
        String args[] = { "pen", "b", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Apple, Pen, pineapple, b, apple,\n" +
        "pineapple, apple, b, Apple,\n?" +
        "Pineapple Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain50() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is blank "  ". Using "-f","-l", "-b".
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-f", "-l", "-b", "pp", "  ", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "A  le, Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pinea  le Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain51() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is blank "  ". Using "i", "-f","-l", "-b".
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-i", "-f","-l", "-b", "pp", "  ", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "A  le, Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pinea  le Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain52() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is blank "  pineapple  " which has space on the left and right sides.
         */
        File inputFile1 = createInputFile7();
        String args[] = {"apple", " pineapple ", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = ("Apple, Pen, pine pineapple , pen,  pineapple ,\n" +
                            "pine pineapple ,  pineapple , pen, Apple,\n?" +
                            "Pine pineapple  Pen");
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }
    
    @Test
    public void testMain53() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is blank "  pineapple  " which has space on the left and right sides.
         * Using "-f","-l", "-b".
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-f", "-l", "-b", "apple", "  pineapple  ", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "Apple, Pen, pine  pineapple  , pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pine  pineapple   Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain54() throws Exception {
        /**
         * Newly created test case.
         * Purpose: TO is blank "  pineapple  " which has space on the left and right sides.
         * Using "-i", "-f","-l", "-b".
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-i", "-f","-l", "-b", "apple", "  pineapple  ", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "  pineapple  , Pen, pineapple, pen, apple,\n" +
        "pineapple, apple, pen, Apple,\n?" +
        "Pine  pineapple   Pen";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    
    @Test
    public void testMain55() throws Exception {
        /**
         * Newly created test case.
         * Using -i", "-f","-l", "-b".
         */
        File inputFile1 = createInputFile8();
        String args[] = {"-i", "-f","-l", "-b", "555", "777", "--", inputFile1.getPath()};
        Main.main(args);
        String expected1 = "AaBbCcDdEeFfGgHhIiJjKkLlMmNn123123456456111222333444777666";
        String actual1 = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
    }
    
    @Test
    public void testMain56() throws Exception {
        /**
         * Newly created test case.
         * Purpose: Capital letter of [-i].
         */
        File inputFile1 = createInputFile7();
        String args[] = {"-I", "apple", "Apple", "--", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*",
                     errStream.toString().trim());
    }
    
    
    @Test
    public void testMain57() throws Exception {
        /**
         * Newly created test case.
         * Purpose: FROM is ",", To is ";".
         * Using "-f".
         */
        File inputFile3 = createInputFile3();
        
        String args[] = {"-f", ",", ";", "--", inputFile3.getPath()};
        Main.main(args);
        
        String expected3 = "Howdy Bill; have you learned your abc and 123?\n" +
        "It is important to know your abc and 123," +
        "so you should study it\n" +
        "and then repeat with me: abc and 123";
        
        String actual3 = getFileContent(inputFile3.getPath());
        
        assertEquals("The files differ!", expected3, actual3);
    }
    
    
    @Test
    public void testMain58() throws Exception {
        /**
         * Newly created test case.
         * Purpose: targeted file is with 10 blank spaces.
         * Using "-f".
         */
        File inputFile = createInputFile10();
        String args1[] = {"-f", "-l", " ", "Space|", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "Space|        Space|";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    
    @Test
    public void testMain59() throws Exception {
        /**
         * Newly created test case.
         * Purpose: targeted file is with 10 blank spaces. FROM is " ", TO is "   ".
         * Using "-f", "-l", "-b".
         */
        File inputFile = createInputFile10();
        String args1[] = {"-b", "-f","-l", " ", "   ", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "              ";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test
    public void testMain60() throws Exception {
        /**
         * Newly created test case.
         * Purpose: targeted file is with 10 blank spaces. FROM is " ", TO is "   ".
         * Using "-i","-l","-b","-f".
         */
        File inputFile = createInputFile10();
        String args1[] = {"-i", "-b", "-f","-l", " ", "   ", "--", inputFile.getPath()};
        Main.main(args1);
        String expected = "              ";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
}