import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * Used for generating the Fibonacci sequence recursively using memorization.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Fibonacci_number">Fibonacci number</a>
 */
public class FibonacciPair {
  /** Fibonacci number sequence index. */
  public final long index;

  /** The Fibonacci number at the previous index. */
  public final BigInteger previous;

  /** The Fibonacci number at the current index. */
  public final BigInteger current;

  /**
   * Initializes a Fibonacci pair for a specific sequence index. For example, for the Fibonacci
   * sequence 0, 1, 1, 2, 3, ... the Fibonacci number at index 0 is 0 and the Fibonacci number at
   * index 1 and index 2 is 1.
   *
   * @param index current sequence index (e.g. 0, 1, 2, 3, ...)
   * @param previous value of previous Fibonacci number (i.e. at index - 1)
   * @param current value of current Fibonacci number (i.e. at index)
   *
   * @throws IllegalArgumentException if a non-negative index is provided
   */
  public FibonacciPair(long index, BigInteger previous, BigInteger current)
      throws IllegalArgumentException {
    if (index < 0) {
      throw new IllegalArgumentException("The index must be a nonnegative number.");
    }

    this.index = index;
    this.previous = previous;
    this.current = current;
  }

  /**
   * Initializes a Fibonacci pair for a specific sequence index. For example, for the Fibonacci
   * sequence 0, 1, 1, 2, 3, ... the Fibonacci number at index 0 is 0 and the Fibonacci number at
   * index 1 and index 2 is 1.
   *
   * @param index current sequence index (e.g. 0, 1, 2, 3, ...)
   * @param previous value of previous Fibonacci number (i.e. at index - 1)
   * @param current value of current Fibonacci number (i.e. at index)
   *
   * @see FibonacciPair#FibonacciPair(long, BigInteger, BigInteger)
   */
  public FibonacciPair(long index, long previous, long current) {
    this(index, BigInteger.valueOf(previous), BigInteger.valueOf(current));
  }

  /**
   * Generates the next Fibonacci number in the sequence.
   *
   * @return the next Fibonacci number in the sequence
   */
  public FibonacciPair next() {
    /*
     * TODO Intentionally make a mistake here.
     *
     * Correct:
     * return new FibonacciPair(index + 1, current, previous.add(current));
     *
     * Incorrect:
     * return new FibonacciPair(index + 1, previous, previous.add(current));
     *
     * (Detected by TestNextF1)
     */
    // return new FibonacciPair(index + 1, current, previous.add(current));
    return new FibonacciPair(index + 1, previous, previous.add(current));
  }

  @Override
  public String toString() {
    return "f(" + index + ") = " + current.toString();
  }

  /**
   * The base case for the 0th Fibonacci number (i.e. F_0 = 0).
   */
  public static FibonacciPair F0 = new FibonacciPair(0, 0, 0);

  /*
   * TODO Intentionally make a mistake here.
   *
   * Correct:
   * public static FibonacciPair F1 = new FibonacciPair(1, 0, 1);
   *
   * Incorrect:
   * public static FibonacciPair F1 = new FibonacciPair(1, 1, 1);
   *
   * (Detected by testFibonacciNumber and others)
   */

  /**
   * The base case for the 1st Fibonacci number (i.e. F_1 = 1).
   */
  // public static FibonacciPair F1 = new FibonacciPair(1, 0, 1);
  public static FibonacciPair F1 = new FibonacciPair(1, 1, 1);

  /**
   * Creates an infinite sequential stream of {@link FibonacciPair} objects starting from the 1st
   * number (index = 1) in the sequence.
   *
   * @return an infinite sequential stream of {@link FibonacciPair} objects
   */
  public static Stream<FibonacciPair> stream() {
    /*
     * TODO Intentionally make a mistake here.
     *
     * Correct:
     * return Stream.concat(Stream.of(F0), Stream.iterate(F1, fn -> fn.next()));
     *
     * Incorrect:
     * return Stream.iterate(F1, fn -> fn.next());
     *
     * (Detected by testFibonacciNumber and others)
     */
    // return Stream.concat(Stream.of(F0), Stream.iterate(F1, fn -> fn.next()));
    return Stream.iterate(F1, fn -> fn.next());
  }

  /**
   * Demonstrates this class.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    FibonacciPair.stream().limit(5).forEach(System.out::println);
  }
}
