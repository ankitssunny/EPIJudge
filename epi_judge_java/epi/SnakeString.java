package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SnakeString {
  @EpiTest(testDataFile = "snake_string.tsv")

  // Boom, done by own logic!
  public static String snakeString(String s) {
    List<Integer> frequency = new ArrayList<>();
    frequency.add(0); // mid
    frequency.add(1); // high
    frequency.add(0); // mid
    frequency.add(-1);  // low

    String mid = "", high = "", low = "";
    int pos = 0;
    for (int i = 0; i < s.length(); i++) {

      // decide which String to add to the current character in s.

      int choice = frequency.get(pos);
      String curr = s.charAt(i) + "";

      switch(choice) {
        case 0:
          mid += curr;
          break;
        case 1:
          high += curr;
          break;
        case -1:
          low += curr;
          break;
      }

      // increment pos
      if (pos + 1 > 3) {
        pos = 0;
      }
      else {
        pos++;
      }
    }
    return high + mid + low;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SnakeString.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
