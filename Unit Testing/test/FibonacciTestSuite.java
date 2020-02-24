import org.junit.jupiter.api.Nested;

/*
 * We can use inheritance to combine multiple different test classes into a single test suite. Note
 * the nesting is preserved.
 */

/**
 * Tests Fibonacci number generation.
 *
 * @see FibonacciPairTest
 * @see FibonacciGeneratorTest
 */
public class FibonacciTestSuite {

  /**
   * Tests the {@link FibonacciPair} class.
   *
   * @see FibonacciPair
   */
  @Nested
  public class FibonacciPair extends FibonacciPairTest {

  }

  /**
   * Tests the {@link FibonacciPair} and {@link FibonacciGenerator} classes.
   *
   * @see FibonacciPair
   * @see FibonacciGenerator
   */
  @Nested
  public class FibonacciGenerator extends FibonacciGeneratorTest {

  }
}
