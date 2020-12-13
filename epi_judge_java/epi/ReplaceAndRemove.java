package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class ReplaceAndRemove {

  public static int replaceAndRemove(int size, char[] s) {
    // TODO - you fill in here.
    int writeIndex = 0, aCount = 0;

    for (int i = 0; i < size; i++) {
      if (s[i] != 'b' && s[i] != 'B') {
        s[writeIndex++] = s[i];
      }
      if(s[i] == 'a' || s[i] == 'A') {
        aCount++;
      }
    }

    // Now go backwards and remove occurrences of a

    int currIndex = writeIndex - 1;
    writeIndex = writeIndex + aCount - 1;
    int finalSize = writeIndex + 1;


    while (currIndex >=0) {

      if(s[currIndex] == 'a' || s[currIndex] == 'A') {
        s[writeIndex--] = 'd';
        s[writeIndex--] = 'd';
      }
      else {
        s[writeIndex--] = s[currIndex];
      }

      currIndex--;
    }

    return finalSize;
  }
  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
