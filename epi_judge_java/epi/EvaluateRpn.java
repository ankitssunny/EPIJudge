package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    Deque<Integer> stack = new LinkedList<>();

    String[] expArr = expression.split(",");


    for (String exp: expArr) {
      if (exp.length() == 1 && "+-*/".contains(exp)) {
        int temp2 = stack.removeFirst();
        int temp1 = stack.removeFirst();
        switch (exp) {
          case "+":
            stack.addFirst(temp1 + temp2);
            break;
          case "-":
            stack.addFirst(temp1 - temp2);
            break;
          case "/":
            stack.addFirst(temp1 / temp2);
            break;
          case "*":
            stack.addFirst(temp1 * temp2);
            break;
        }
      }
      else {
        stack.addFirst(Integer.parseInt(exp));
      }
    }
    return stack.removeFirst();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
