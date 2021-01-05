package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    if ( L == null ||  L.size() < 2 ) return L;
    ListNode<Integer> curr = L;
    while (curr != null) {
      ListNode<Integer> next = curr.next;
      while (next != null && next.data == curr.data) {
        next = next.next;
      }
      curr.next = next;
      curr = next;
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
