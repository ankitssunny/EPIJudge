package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {


  private static String[] mapping = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};


  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String phoneNumber) {
    // TODO - you fill in here.
    char[] currMnemonic = new char[phoneNumber.length()];
    List<String> mnemonics = new ArrayList<>();
    helper(phoneNumber, 0 , currMnemonic, mnemonics);
    //System.out.println("mnemonics = " + mnemonics);
    return mnemonics;
  }
  
  public static void helper(String phone, int start, char[] curr, List<String> mnemonics) {
  
    if (start == phone.length()) {
        mnemonics.add(new String(curr));
    }
    
    else {
      
      int currCharIndex = phone.charAt(start) - '0';
      
      for (int i = 0 ; i < mapping[currCharIndex].length(); i++) {
        
        char currChar = mapping[currCharIndex].charAt(i);
        
        curr[start] = currChar;
        
        helper(phone, start + 1, curr, mnemonics);
        
      }
    }
  }
  
  
  
  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
