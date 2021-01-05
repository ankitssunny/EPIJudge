package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {

    ListNode<Integer> result = new ListNode<>(0, null);
    ListNode<Integer> curr = result;
    ListNode<Integer> p1 = L1, p2 = L2;
    while (p1 != null && null != p2) {
      if (p1.data <  p2.data) {
        curr.next = p1;
        p1 = p1.next;
      }
      else {
        curr.next = p2;
        p2 = p2.next;
      }
      curr = curr.next;
    }

    curr.next =  p1 == null ? p2 : p1;

    return result.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
