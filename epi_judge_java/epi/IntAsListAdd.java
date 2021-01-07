package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    ListNode<Integer> result = new ListNode<>(0, null);
    ListNode<Integer> resultHead = result;
    int sum = 0, carry = 0;

    while (L1 != null || L2 != null) {
      sum += carry;

      if (L1 != null) {
        sum += L1.data;
        L1 = L1.next;
      }

      if (L2 != null) {
        sum += L2.data;
        L2 = L2.next;
      }
      result.next = new ListNode<>(sum % 10, null);
      carry = sum / 10;
      result = result.next;
      sum = 0;
    }

    if (carry > 0) {
      result.next = new ListNode<>(carry, null);
    }

    return resultHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
