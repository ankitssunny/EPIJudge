package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SunsetView {

  public static class BuildingWithID {
    public int id;
    public int height;

    BuildingWithID(int id, int height) {
      this.id = id;
      this.height = height;
    }
  }

  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    // TODO - you fill in here.

    Deque<BuildingWithID> stack = new LinkedList<>();
    int id = 0;

    while (sequence.hasNext()) {
      Integer westSideBuildingHeight = sequence.next();

      while (!stack.isEmpty() && (westSideBuildingHeight >= stack.getLast().height) ){
        stack.removeLast();
      }
      stack.addLast(new BuildingWithID(id++, westSideBuildingHeight));
    }
    List<Integer> list = new ArrayList<>();

    Iterator it = stack.descendingIterator();

    while (it.hasNext()) {
      list.add(((BuildingWithID)it.next()).id);
    }

    return list;
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
