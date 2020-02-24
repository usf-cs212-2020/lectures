import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FibonacciGenerator {

  public static BigInteger fibonacciNumber(long index) {
    if (index < 0) {
      throw new IllegalArgumentException("The index must be a nonnegative number.");
    }

    return null; // TODO
  }

  public static Stream<BigInteger> fibonacciSequence() {
    return null; // TODO
  }

  public static List<BigInteger> fibonacciNumbers(long limit) {
    Stream<BigInteger> stream = fibonacciSequence().limit(limit);
    List<BigInteger> list = stream.collect(Collectors.toList());
    return list;
  }

  public static void main(String[] args) {
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
