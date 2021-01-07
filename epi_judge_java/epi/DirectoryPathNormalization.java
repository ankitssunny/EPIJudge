package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {

    String[] paths = path.split("/");
    Deque<String> stack = new LinkedList<>();


    for(String p: paths) {

      switch(p) {
        case ".":
        case "":
          break;
        case "..":
          if (stack.isEmpty() || stack.peekFirst().equals(".."))
          {
            stack.addFirst("..");
          }
          else {
            stack.removeFirst();
          }
          break;
        default:
          stack.addFirst(p);
          break;
      }
    }

    if (stack.isEmpty()) return "/";

    StringBuilder strbldr = new StringBuilder();

    while (!stack.isEmpty()) {
      strbldr.insert(0, stack.removeFirst());
      strbldr.insert(0, "/");
    }
    if (path.charAt(0) != '/') {
      strbldr.deleteCharAt(0);
    }

    return strbldr.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
