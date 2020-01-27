import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class FindDemo {

  private static final String[] animals =
      new String[] {"cow", "ape", "dog", "bee", "boa", "cat", "ant", "bat"};

  private static final String[] letters = new String[] {"b", "d", "e"};

  private static void inefficientDemo() {
    int iterations = 0;

    for (String letter : letters) {
      System.out.print(letter.toUpperCase() + ": ");

      // TODO Fill in implementation

      System.out.println();
    }

    System.out.println("Iterations: " + iterations);
    System.out.println();
  }

  private static void listDemo() {

    ArrayList<String> list = new ArrayList<String>();
    Collections.addAll(list, animals);
    Collections.sort(list);

    int iterations = 0;

    for (String letter : letters) {
      System.out.print(letter.toUpperCase() + ": ");

      // TODO Fill in implementation

      System.out.println();
    }

    System.out.println("Iterations: " + iterations);
    System.out.println();
  }

  private static void tailsetDemo() {
    TreeSet<String> set = new TreeSet<String>();
    Collections.addAll(set, animals);

    int iterations = 0;

    for (String letter : letters) {
      System.out.print(letter.toUpperCase() + ": ");

      // TODO Fill in implementation

      System.out.println();
    }

    System.out.println("Iterations: " + iterations);
    System.out.println();
  }

  private static void setDemoTraditionalFor() {
    TreeSet<String> set = new TreeSet<String>();
    Collections.addAll(set, animals);

    int iterations = 0;

    // Uses an enhanced for loop to iterate through letters
    for (String letter : letters) {
      System.out.print(letter.toUpperCase() + ": ");

      // TODO Fill in implementation

      System.out.println();
    }

    System.out.println("Iterations: " + iterations);
    System.out.println();
  }

  private static void streamApproach() {

    for (String letter : letters) {
      System.out.print(letter.toUpperCase() + ": ");

      // TODO Fill in implementation

      System.out.println();
    }

    System.out.println();
  }

  public static void main(String[] args) {
    inefficientDemo();
    listDemo();
    tailsetDemo();
    setDemoTraditionalFor();
    streamApproach();
  }
}
