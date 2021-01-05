package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {

    ListNode<Integer> evenTail = new ListNode<>(0, null);
    ListNode<Integer> oddTail = new ListNode<>(0, null);

    ListNode<Integer> newHead = evenTail;
    ListNode<Integer> oddHead = oddTail;

    int count = 0;
    while(L != null) {
      if (count % 2 == 0) {
        evenTail.next = L;
        evenTail = evenTail.next;
      }
      else {
        oddTail.next = L;
        oddTail = oddTail.next;
      }

      L = L.next;
      count++;
    }
    oddTail.next = null;
    evenTail.next = oddHead.next;

    return newHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
