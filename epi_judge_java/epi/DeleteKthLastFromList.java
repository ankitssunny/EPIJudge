package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {

    ListNode<Integer> dummy = new ListNode<>(0, L);

    ListNode<Integer> kthNode = dummy.next;

    while (k-- > 0) {
      kthNode = kthNode.next;
    }

    ListNode<Integer> nodeToDelete = dummy;
    while (kthNode != null) {
      nodeToDelete = nodeToDelete.next;
      kthNode = kthNode.next;
    }

    nodeToDelete.next = nodeToDelete.next.next;

    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
