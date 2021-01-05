package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {

    int length0 = lengthNode(l0), length1 = lengthNode(l1);
    int diff = length0 > length1 ? length0 - length1 : length1 - length0;

    if (length0 > length1) {
      l0 = advanceNodeByDiff(l0, diff);
    }
    else {
      l1 = advanceNodeByDiff(l1, diff);
    }

    while (l0 != null && l1 != null) {
      if (l0 == l1) {
        return l0;
      }

      else{
        l0 = l0.next;
        l1 = l1.next;
      }
    }

    return null;
  }

  public static ListNode<Integer> advanceNodeByDiff(ListNode<Integer> node, int diff) {
    while(diff > 0) {
      node = node.next;
      diff--;
    }
    return node;
  }


  public static int lengthNode(ListNode<Integer> node) {

    int length = 0;

    while (node != null) {
      node = node.next;
      length++;
    }
    return length;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
