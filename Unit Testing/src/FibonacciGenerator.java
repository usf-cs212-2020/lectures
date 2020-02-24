import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Uses Fibonacci generation to illustrate unit testing and streams.
 *
 * @see FibonacciPair
 */
public class FibonacciGenerator {

  /**
   * Returns a single Fibonacci number at the specified index.
   *
   * @param index the index of the Fibonacci number
   * @return the value of the Fibonacci number at the specified index
   *
   * @throws IllegalArgumentException if a non-negative index is provided
   */
  public static BigInteger fibonacciNumber(long index) {
    if (index < 0) {
      throw new IllegalArgumentException("The index must be a nonnegative number.");
    }
    /*
     * TODO Intentionally make a mistake here. Lines below actually do not matter in a correct
     * implementation.
     */
    else if (index == 0) {
      return FibonacciPair.F0.current;
    } else if (index == 1) {
      return FibonacciPair.F1.current;
    }

    /*
     * Just skip numbers in the stream until we generate the one we want. Will this do what we want?
     * Remember, f(n) = f(n - 1) + f(n - 2), or alternatively f(n - 2) = f(n - 1) + f(n)...
     */

    /*
     * TODO Intentionally make a mistake here.
     *
     * Correct:
     * return FibonacciPair.stream().skip(index).findFirst().get().current;
     *
     * Incorrect:
     * return FibonacciPair.stream().skip(index - 1).findFirst().get().current;
     *
     * (Detected by testFibonacciNumbers f(0) = 0 and others)
     */

    // return FibonacciPair.stream().skip(index).findFirst().get().current;
    return FibonacciPair.stream().skip(index - 1).findFirst().get().current;
  }

  /**
   * Returns an infinite {@link Stream} of Fibonacci numbers as {@link BigInteger} objects starting
   * with 0.
   *
   * @return an infinite stream of Fibonacci numbers
   */
  public static Stream<BigInteger> fibonacciSequence() {
    return FibonacciPair.stream().map(fn -> fn.current);
  }

  /**
   * Returns the sequence of Fibonacci numbers up to the specified index limit. For example, a limit
   * of 10 will return 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 as a {@link List} of 10 {@link BigInteger}
   * objects.
   *
   * @param limit the number of Fibonacci numbers to return (also the maximum index to consider when
   *        generating numbers)
   * @return a list of Fibonacci numbers
   */
  public static List<BigInteger> fibonacciNumbers(long limit) {
    Stream<BigInteger> stream = fibonacciSequence().limit(limit);
    List<BigInteger> list = stream.collect(Collectors.toList());
    return list;
  }

  /**
   * Demonstrates this class.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    /*
     * We could try and test our implementation here, but it is clunky. Instead, we will move to
     * unit testing.
     */
    System.out.println(fibonacciNumber(0));
    System.out.println(fibonacciNumber(1));
    System.out.println(fibonacciNumber(2));
    System.out.println(fibonacciNumber(3));
    System.out.println(fibonacciNumber(4));
    System.out.println(fibonacciNumber(5));

    fibonacciSequence().limit(5).forEach(System.out::println);
    System.out.println(fibonacciNumbers(10));
  }
}
