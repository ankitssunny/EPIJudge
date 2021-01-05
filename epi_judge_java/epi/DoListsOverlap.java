package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.Set;
public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {

    ListNode<Integer> root0 = IsListCyclic.hasCycle(l0);
    ListNode<Integer> root1 = IsListCyclic.hasCycle(l1);

    // If both are null that means acyclic that means we can use the acyclic logic.
    // If one has cycle and other one doesnt then means no overlap.

    if (root0 == null && root1 == null) {
      return DoTerminatedListsOverlap.overlappingNoCycleLists(l0, l1);
    }
    else if( (root0 == null && root1 != null) ||
            (root1 == null && root0 != null) ) {
      return null;
    }


    // Third case would be both are cyclic, so we find whether the cycle is disjoint or same.

    ListNode<Integer> temp  = root0;
    do {
      temp = temp.next;
    } while (temp != root0 || temp != root1);


    if (temp != root1) {
      return null; // cycle is disjoint
    }

    // find the length of the stem for both nodes.
    // stem is the distance from the start of the node to to root
    int stem0len = stemLength(l0, root0), stem1len = stemLength(l1, root1);

    // Now find the difference in stem length

    int diff = Math.abs(stem0len - stem1len);

    // Now propagate the longer list diff times ahead so that we both nodes have same length of stem.

    if (stem0len > stem1len) {
      l0 = propagateAhead(l0, diff);
    }
    else {
      l1 = propagateAhead(l1, diff);
    }

    while (l0 != l1  && l0 != root0 && l1 != root1) {
      l0 = l0.next;
      l1 = l1.next;
    }

    return l0 == l1 ? l0 : root0;
  }

  public static int stemLength(ListNode<Integer> head, ListNode<Integer> root){
    int dist = 0;

    while( head != root) {
      dist++;
      head = head.next;
    }
    return dist;
  }

  public static ListNode<Integer> propagateAhead(ListNode<Integer> list, int count) {
    while (count-- > 0) {
      list = list.next;
    }
    return list;
  }


  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
