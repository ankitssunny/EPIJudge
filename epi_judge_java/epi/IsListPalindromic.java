package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {

    int size = 0;
    ListNode<Integer> newHead = L;
    ListNode<Integer> slow = L;
    ListNode<Integer> fast = L;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      size++;
    }

    int start = 0 ;

    ListNode<Integer> reverse = ReverseSublist.reverseSublist(slow, start, size - 1 );

    while (newHead != null && reverse != null) {

      if (newHead.data == reverse.data) {
        newHead = newHead.next;
        reverse = reverse.next;
      }
      else return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
