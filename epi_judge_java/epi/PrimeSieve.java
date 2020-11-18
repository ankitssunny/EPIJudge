package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    // TODO - you fill in here.

    List<Integer> primes = new ArrayList<>();
    List<Boolean> currPrimes = new ArrayList<>(Collections.nCopies(n + 1 ,Boolean.TRUE));
    currPrimes.set(0, Boolean.FALSE);
    currPrimes.set(1, Boolean.FALSE);


    for (int i = 2; i <= n; i++) {
      if (currPrimes.get(i)) {
          primes.add(i);
        for (int j = i + i ; j <= n ; j += i) {
          currPrimes.set(j, Boolean.FALSE);
        }
      }
    }

    return primes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
