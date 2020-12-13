package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    // TODO - you fill in here.

    // reverse the char array to reverse the string first

    reverse(input, 0, input.length);

    // now reverse each word in the char array

    int start = 0, end = 0;
    while( (end = find(input, start)) != -1) {
      reverse ( input , start, end);
      start = end + 1;
    }

    reverse(input , start, input.length);
  }

  public static void reverse(char[] arr, int start, int end) {

    if (start >= end) {
      return ;
    }
    int last = end - 1;
    for (int i = start; i <= start + (last - start) / 2; i++) {
      char tmp = arr[i];
      arr[i] = arr[last - i + start];
      arr[last - i + start] = tmp;
    }

  }

  public static int find(char[] array, int start) {
    for (int i = start; i < array.length; i++) {
      if (array[i] == ' ') {
        return i;
      }
    }
    return -1;
  }

  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
