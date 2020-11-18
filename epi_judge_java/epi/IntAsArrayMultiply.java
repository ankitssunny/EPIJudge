package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // TODO - you fill in here.

   /* List<Integer> lower, bigger;
    List<Integer> result = new ArrayList<>();

    int cross = 0;
    boolean positive = true;

    if(num1.size() > num2.size()) {
      lower = num2;
      bigger = num1;
    }
    else {
      lower = num1;
      bigger = num2;
    }

    if (lower.size() == 1 && lower.get(0) == 0) {
      result.add(0);
      return result;
    }

    if ( (lower.get(0) < 0 && bigger.get(0) > 0) || (lower.get(0) > 0 && bigger.get(0) < 0) ) {
      positive = false;
      if(lower.get(0) < 0) {
        lower.set(0, lower.get(0) * -1);
      }
      if(bigger.get(0) < 0) {
        bigger.set(0, bigger.get(0) * -1);
      }
    }
    else {
      if (lower.get(0) < 0 && bigger.get(0) < 0) {
        lower.set(0, lower.get(0) * -1);
        bigger.set(0, bigger.get(0) * -1);
      }
    }

    for (int i = lower.size() - 1 ; i >= 0 ; i--) {
      int k = cross;
      int carryOver = 0;
      for(int j = bigger.size() - 1 ; j >= 0 ; j--) {
        int temp = lower.get(i) * bigger.get(j) + carryOver;

        try {
          temp += result.get(k);
        } catch(IndexOutOfBoundsException e) {
          // Do nothing
        }

        if (temp > 9) {
          carryOver = temp / 10;
          temp %= 10;
        }
        else carryOver = 0;

        try {
          result.set(k, temp);
        } catch (IndexOutOfBoundsException e) {
          result.add(temp);
        }
        if (j == 0 && carryOver > 0) {
          result.add(carryOver);
        }

        //System.out.println("Initial Result: " + result);
        k++;
      }
      cross++;
    }

    if (result.get(result.size() - 1) > 9) {
      result.add(result.get(result.size() - 1) / 10);
      result.set(result.size() - 1, result.get(result.size() - 1)%10);
    }

    if (!positive) {
      result.set(result.size() - 1, result.get(result.size() - 1) * -1);
    }

    for (int n = 0, k = result.size() - 1 ; n < result.size()/2 && k >= result.size()/2 ; n++, k--) {
      int temp = result.get(n);
      result.set(n, result.get(k));
      result.set(k, temp);
    }*/

    List<Integer> result = new ArrayList<>(Collections.nCopies(num2.size() + num1.size(), 0));

    boolean negative = false;
    if ((num1.get(0) < 0 && num2.get(0) > 0) || (num1.get(0) > 0 && num2.get(0) < 0)) {
      negative = true;
    }

    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));


    for (int i = num1.size() - 1 ; i >= 0; i-- ) {
      for (int j = num2.size() - 1 ; j >= 0; j-- ) {
        result.set(i + j + 1, result.get(i + j + 1) + num1.get(i) * num2.get(j));
        result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10);
        result.set(i + j + 1, result.get(i + j + 1) % 10);
      }
    }

    int i = 0;
    while (i < result.size() && result.get(i) == 0) {
      i++;
    }

    result = result.subList(i, result.size());
    if (result.isEmpty()) {
      return Arrays.asList(0) ;
    }

    if (negative) {
      result.set(0, result.get(0) * -1);
    }

    return result;
  }

  public static void main(String[] args) {

    System.exit(
            GenericTest
                    .runFromAnnotations(args, "IntAsArrayMultiply.java",
                            new Object() {}.getClass().getEnclosingClass())
                    .ordinal());

  }
}
