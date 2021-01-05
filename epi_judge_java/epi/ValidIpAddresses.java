package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class ValidIpAddresses {
  @EpiTest(testDataFile = "valid_ip_addresses.tsv")

  public static List<String> getValidIpAddress(String s) {
    List<String> list = new ArrayList<>();

    for (int  i = 1 ; i < 4 && i < s.length(); i++) {
      String temp1 = s.substring(0, i);
      if (isValid(temp1)) {

        for (int j = 1; i + j < s.length() && j < 4; j++) {
          String temp2 = s.substring(i, i + j);

          if (isValid(temp2)) {
            for (int k = 1; i + j + k < s.length() && k < 4; k++) {
              String temp3 = s.substring(i + j, i + j + k);
              String temp4 = s.substring(i + j + k);

              if (isValid(temp3) && isValid(temp4)) {
                list.add(temp1 + "." + temp2 + "." + temp3 + "." + temp4);
              }
            }
          }
        }
      }
    }
    return list;
  }


  public static boolean isValid(String s) {
    // 1. length of s should be 3 or less
    if (s.length() > 3) {
      return false;
    }

    // 2. if s starts with 0 and has more than one length return 0 since "01" is not valid

    if(s.startsWith("0") && s.length() > 1)  {
      return false;
    }

    // 3. s should lie between 0 and 255.

    int temp = Integer.parseInt(s);

    if (temp >=0 && temp < 256) {
      return true;
    }
    else return false;
  }


  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
