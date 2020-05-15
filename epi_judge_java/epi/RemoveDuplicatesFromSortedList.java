package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    // TODO - you fill in here.

    System.out.println("L is: " +L);
    if ( L == null ||  L.size() < 2 || (L.size() == 1 && L.data == 0) ) return L;

    ListNode curr = L;

    while( curr.next != null) {
      ListNode nextCurr = curr.next;
      while (nextCurr != null) {
        if (curr.data == nextCurr.data) {
          curr.next = nextCurr.next;
          nextCurr = nextCurr.next;
        }
        else {
          curr = nextCurr;
          break;
        }
      }
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
