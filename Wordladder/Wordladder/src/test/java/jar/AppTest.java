package jar;

import java.io.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }
    
    
    public void test1() throws IOException {
	
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		App.valid("word","word");
		assertEquals("The two words must be different.\r\n", out.toString());
	
    }
    
    
    public void test2() throws IOException {
    	
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		App.valid("word","wordq");
		assertEquals("The two words must be the same length.\r\n", out.toString());
	
    }
    
    public void test3() throws IOException {
    	
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		App.valid("word","worq");
		assertEquals("", out.toString());
	
    }
    
    public void test4() throws IOException {
    	
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		ByteArrayInputStream in = new ByteArrayInputStream("src/res/dictionary.txt\n\n".getBytes());
		System.setIn(in);
		App.main(null);
		assertEquals(	
		"Dictionary file name?\r\n" +
		"Word #1 (or Enter to quit): \r\n" + 
		"Have a nice day.\r\n",out.toString());
		
  	}
    
    public void test5() throws IOException {
    	
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		ByteArrayInputStream in = new ByteArrayInputStream(("src/res/dictionary.txt\nword\nword\n\n").getBytes());
		System.setIn(in);
		App.main(null);
		assertEquals(	
		"Dictionary file name?\r\n" +
		"Word #1 (or Enter to quit): \r\n"+
		"Have a nice day.\r\n",out.toString());
		
  	}
    
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	//TestSuite suite= new TestSuite(); 
    	//suite.addTest(new AppTest("test1"));
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    
    public void testApp()
    {
        assertTrue( true );
    }
}
