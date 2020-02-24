/*
 * You have to make sure you have the right assertions imported. JUnit 5 classes are in the package
 * org.junit.jupiter.api.Assertions.* whereas JUnit 4 classes are in org.junit.Assert.* instead.
 *
 * You can configure Eclipse to let you import * here instead of each individual assert statement
 * requiring its own static import.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link FibonacciPair} class.
 *
 * @see FibonacciPair
 */
public class FibonacciPairTest {

  /*
   * We can use the @Nested annotation to group tests within this file. This allows you to choose
   * groups of tests to run, rather than choosing a single test versus the entire file of tests.
   */

  /**
   * Tests the expected values from the {@link FibonacciPair#F1} test case.
   *
   * @see FibonacciPair
   * @see FibonacciPair#F1
   */
  @Nested
  public class TestNextF1 {

    // Setup any members required by the tests in this group.

    /** The test case. */
    public FibonacciPair testCase;

    /** The expected index. */
    public long expectedIndex;

    /** The expected previous value. */
    public BigInteger expectedPrevious;

    /** The expected current value. */
    public BigInteger expectedCurrent;

    /** The expected next next value (next twice). */
    public BigInteger expectedNextCurrent;

    /*
     * Make sure these are properly re-initialized before every test. This makes sure each test gets
     * a "clean" environment uncontaminated by other tests.
     */

    /**
     * Sets up the environment for each test, including the current test case and the expected
     * values.
     */
    @BeforeEach
    public void setup() {
      testCase = new FibonacciPair(1, 0, 1);

      // We can lookup these values on Wikipedia.
      // https://en.wikipedia.org/wiki/Fibonacci_number
      expectedIndex = 2;
      expectedPrevious = BigInteger.valueOf(1);
      expectedCurrent = BigInteger.valueOf(1);
      expectedNextCurrent = BigInteger.valueOf(2);
    }

    /*
     * The @Test annotation indicates a specific JUnit test.
     */

    /**
     * Tests if the next index is as expected.
     */
    @Test
    public void testNextIndex() {
      // Specify the expected result.
      long expected = expectedIndex;

      // Specify the actual result.
      long actual = testCase.next().index;

      // Every test must have an assertion. The expected value always goes first.
      assertEquals(expected, actual);
    }

    /*
     * You usually place each thing you are testing in a separate test method for a strict "unit"
     * test. However you could technically place all of these tests in the same method, but the then
     * methods can fail for more than one reason. This can make debugging harder at times.
     */

    /**
     * Tests the next previous value is as expected.
     */
    @Test
    public void testNextPrevious() {
      BigInteger expected = expectedPrevious;
      BigInteger actual = testCase.next().previous;

      /*
       * You can add a custom message to the output if the test fails. This message is NOT output if
       * the test passes.
       */
      assertEquals(expected, actual, "The previous value was wrong!");
    }

    /**
     * Tests the next value is as expected.
     */
    @Test
    public void testNextCurrent() {
      BigInteger expected = expectedCurrent;
      BigInteger actual = testCase.next().current;

      /*
       * If you have to do something time/space inefficient to generate the debug output, put it in
       * a lambda expression. It will only run that code if the test fails (i.e. this version is
       * lazy, the other was eager).
       */
      assertEquals(expected, actual, () -> "This string " + "concatenation is" + "only performed if " + " the test fails.");
    }

    // You can also customize the display name of the test.

    /**
     * Tests the next next value (calling next twice) is as expected.
     */
    @Test
    @DisplayName("Test Next Twice")
    public void testNextNext() {
      BigInteger expected = expectedNextCurrent;
      BigInteger actual = testCase.next().next().current;
      assertEquals(expected, actual);
    }
  }

  /*
   * The plus side of manually specifying each and every test with the @Test annotation is that you
   * can easily run one test at a time. However, for many tests of the same form, it can be
   * cumbersome. What if we wanted to do everything above, but for a different base case?
   *
   * Well... @Test annotations are inherited!
   */

  /**
   * Tests the expected values from the index = 2 test case.
   *
   * @see FibonacciPair
   * @see TestNextF1
   */
  @Nested
  public class TestNextF2 extends TestNextF1 {

    @Override
    @BeforeEach
    public void setup() {
      testCase = new FibonacciPair(2, 1, 1);

      expectedIndex = 3;
      expectedPrevious = BigInteger.valueOf(1);
      expectedCurrent = BigInteger.valueOf(2);
      expectedNextCurrent = BigInteger.valueOf(3);
    }

    /*
     * Notice now, however, that we do not have individual test methods that we can right-click in
     * Eclipse to run individually. But, you can still manually create a "Run Configuration" to run
     * the inherited test methods individually.
     */
  }

}
