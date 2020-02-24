import java.math.BigInteger;
import java.util.stream.Stream;

public class FibonacciPair {

  public final long index;
  public final BigInteger previous;
  public final BigInteger current;

  public FibonacciPair(long index, BigInteger previous, BigInteger current)
      throws IllegalArgumentException {
    if (index < 0) {
      throw new IllegalArgumentException("The index must be a nonnegative number.");
    }

    this.index = index;
    this.previous = previous;
    this.current = current;
  }

  public FibonacciPair(long index, long previous, long current) {
    this(index, BigInteger.valueOf(previous), BigInteger.valueOf(current));
  }

  // current value calc: f(i) = f(i - 2) + f(i - 1)
  //    next value calc: f(i + 1) = f(i - 1) + f(i)
  //                   : next     = previous + current

  public FibonacciPair next() {
    return null; // TODO
  }

  @Override
  public String toString() {
    return "f(" + index + ") = " + current.toString();
  }

  // f(0) = 0, f(1) = 1
  public static FibonacciPair F0 = new FibonacciPair(0, 0, 0);
  public static FibonacciPair F1 = null; // TODO

  public static Stream<FibonacciPair> stream() {
    return null; // TODO
  }

  public static void main(String[] args) {
    FibonacciPair.stream().limit(5).forEach(System.out::println);
  }
}
