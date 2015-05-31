package fibbonaci;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFibonacciSequenceTenCompleting() throws Exception {
		String[] testString = new String[1];
		testString[0] = "10";
		Main.main(testString);
	}

	@Test(expected = CalculationErrorException.class)
	public void testStackOverflowErrorCompletion() throws Exception {
		String[] testString = new String[1];
		testString[0] = "10000";
		Main.main(testString);
	}

}
