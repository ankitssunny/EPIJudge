package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    Deque<BinaryTreeNode<Integer>> queue = new LinkedList<>();
    queue.addLast(tree);
    List<List<Integer>> levels = new ArrayList<>();
    while(!queue.isEmpty()) {
      List<Integer> thisLevel = new ArrayList<>();
      Deque<BinaryTreeNode<Integer>> currQ = new LinkedList<>();
      while (!queue.isEmpty()) {
        BinaryTreeNode<Integer> currNode = queue.removeFirst();
        if (currNode != null) {
          thisLevel.add(currNode.data);
          currQ.addLast(currNode.left);
          currQ.addLast(currNode.right);
        }
      }
      if (!thisLevel.isEmpty()) {
        levels.add(thisLevel);
      }
      queue = currQ;
    }
    return levels;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
