
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigInteger;
import java.time.Duration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FibonacciGeneratorTest {

  @Nested
  public class FibonacciNumberTests {
    @Test
    public void testFibonacci20() {
      BigInteger expected = BigInteger.valueOf(6765);
      assertEquals(expected, FibonacciGenerator.fibonacciNumber(20));
    }

    @Test
    public void testFibonacci40() {
      BigInteger expected = new BigInteger("102334155");
      assertEquals(expected, FibonacciGenerator.fibonacciNumber(40));
    }

    @Test
    public void testFibonacci100() {
      BigInteger expected = new BigInteger("354224848179261915075");
      assertEquals(expected, FibonacciGenerator.fibonacciNumber(100));
    }

    // TODO
  }

  public static enum FibonacciNumber {
    F0(0), F1(1), F2(1), F3(2), F4(3), F5(5), F6(8), F7(13), F8(21), F9(34);

    public final BigInteger value;

    private FibonacciNumber(long value) {
      this.value = BigInteger.valueOf(value);
    }

    @Override
    public String toString() {
      return "f(" + this.ordinal() + ") = " + this.value.toString();
    }
  }

  @Nested
  public class FibonacciNumbersTests {
    // TODO
  }

  @Nested
  public class ExceptionTests {

    public final Duration TIMEOUT = Duration.ofMillis(100);

    @Test
    public void testNegativeFibonacciNumber() {
      // TODO
    }

    @Test
    public void testNegativeFibonacciNumbers() {
      // TODO
    }

    @Test
    public void testZeroFibonacciNumbers() {
      // TODO
    }
  }
}
