package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {

    Deque<Character> stack = new LinkedList<>();

    for (int i = 0; i < s.length(); i++) {

      if ('{' == s.charAt(i) || '[' == s.charAt(i) || '(' == s.charAt(i)) {
        stack.addFirst(s.charAt(i));
      }
      else {
        if (stack.isEmpty()) return false;
        Character curr = stack.removeFirst();
        switch(s.charAt(i)) {
          case '}':
            if (curr != '{') {
              return false;
            }
            break;

          case ']':
            if (curr != '[') {
              return false;
            }
            break;

          case ')':
            if (curr != '(') {
              return false;
            }
            break;
        }
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
