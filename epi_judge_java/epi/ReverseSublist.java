package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode<Integer> dummy = new ListNode<>(0, L);
    ListNode<Integer> sublist = dummy;
    int k = 1;
    while (k++ < start) {
      sublist = sublist.next;
    }
    ListNode<Integer> iter = sublist.next;
    while (start++ < finish) {
      ListNode<Integer> temp = iter.next;
      iter.next = temp.next;
      temp.next = sublist.next;
      sublist.next = temp;
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
