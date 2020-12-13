package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    String sequence = "1";
    for (int i = 1 ; i < n ; i++) {
      String currSequence = "";
      if (sequence.length() == 1) {
        sequence += "1";
      }
      else {
        int j = 0, k = 1;
        while (j < sequence.length() - 1 && k < sequence.length()) {

          if (sequence.charAt(j) != sequence.charAt(k)) {
            currSequence += (k - j) + "" +  sequence.charAt(j);
            j = k;
          }
          k++;
        }
        if (sequence.charAt(k - 1) != sequence.charAt(k - 2)) {
          currSequence += "1" + "" +  sequence.charAt(k - 1);
        }
        else {
          currSequence += (k - j) + "" + sequence.charAt(j);
        }
        sequence = currSequence;
      }
    }
    return sequence;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
