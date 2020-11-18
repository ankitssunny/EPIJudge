package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class RandomSubset {

  // Returns a random k-sized subset of {0, 1, ..., n - 1}.
  public static List<Integer> randomSubset(int n, int k) {
    // TODO - you fill in here.

    HashMap<Integer, Integer> map = new HashMap<>();
    Random gen = new Random();

    for (int i = 0; i < k; ++i) {

      int rand = i + gen.nextInt(n - i);

      Integer ptr1 = map.get(rand);
      Integer ptr2 = map.get(i);


      if (ptr1 == null && ptr2 == null) {
        map.put(rand, i);
        map.put(i, rand);

      }
      if (ptr1 == null && ptr2 != null) {

        map.put(rand, ptr2);
        map.put(i, rand);

      }
      if (ptr1 != null && ptr2 == null) {
        map.put(i, ptr1);
        map.put(rand, i);
      }
      if (ptr1 != null && ptr2 != null) {
        map.put(i, ptr1);
        map.put(rand, ptr2);
      }
    }

    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < k; i++) {
      list.add(map.get(i));
    }

    return list;
  }
  private static boolean randomSubsetRunner(TimedExecutor executor, int n,
                                            int k) throws Exception {
    List<List<Integer>> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 1000000; ++i) {
        results.add(randomSubset(n, k));
      }
    });

    int totalPossibleOutcomes = RandomSequenceChecker.binomialCoefficient(n, k);
    List<Integer> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      A.add(i);
    }
    List<List<Integer>> combinations = new ArrayList<>();
    for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(n, k); ++i) {
      combinations.add(RandomSequenceChecker.computeCombinationIdx(A, n, k, i));
    }
    List<Integer> sequence = new ArrayList<>();
    for (List<Integer> result : results) {
      Collections.sort(result);
      sequence.add(combinations.indexOf(result));
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, totalPossibleOutcomes, 0.01);
  }

  @EpiTest(testDataFile = "random_subset.tsv")
  public static void randomSubsetWrapper(TimedExecutor executor, int n, int k)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> randomSubsetRunner(executor, n, k));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RandomSubset.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
