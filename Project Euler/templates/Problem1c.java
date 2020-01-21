public class Problem1c {

  public static int inputNonNegativeInteger() {
    // TODO Implement inputNonNegativeInteger method
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  public static void main(String[] args) {
    int max = 0;

    // TODO Safely get user input

    int result = Problem1b.sumMultiples(max);
    System.out.printf("The sum of multiples of 3 or 5 less than %d is %d.", max, result);
  }
}
