package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    // TODO - you fill in here.
/*
    int i = 1, j, k;
    Double p1Profit = 0.0, p2Profit = 0.0, maxProfit = 0.0;
    while(i < prices.size() - 2) {

      Double minimum1 = prices.get(0);
      for (k = 0; k < i; k++) {
        p1Profit = Math.max(p1Profit, prices.get(k) - minimum1);
        minimum1 = Math.min(minimum1, prices.get(k));
      }

      Double minimum2 = prices.get(i+1);
      for(j = i; j < prices.size(); j++) {
        p2Profit = Math.max(p2Profit, prices.get(j) - minimum2);
        minimum2 = Math.min(minimum2, prices.get(j));
      }
      System.out.println("P1 Profit: " + p1Profit + "\t P2 Profit: " + p2Profit);

      maxProfit = Math.max(maxProfit, p1Profit + p2Profit);
      p1Profit = 0.0;
      p2Profit = 0.0;
      i++;
    }

 */
    Double maxProfit = Double.MIN_VALUE, min1 = prices.get(0), maximum2 = prices.get(prices.size() - 1),
            max1 = Double.MIN_VALUE, max2 = Double.MIN_VALUE;

    List<Double> max1List = new ArrayList<>(), max2List = new ArrayList<>();

    for(int i = 0, j = prices.size() - 1; i< prices.size() && j >= 0; i++, j--) {
      max2 = Math.max(max2,  maximum2 - prices.get(j));
      maximum2 = Math.max(prices.get(j), maximum2);

      max1 = Math.max(max1, prices.get(i) - min1);
      min1 = Math.min(prices.get(i), min1);

      max1List.add(max1);
      max2List.add(max2);
    }

    for(int i = 0, j = prices.size() - 1; i< prices.size() && j >= 0; i++, j--) {
      if (i == 0 ) maxProfit = Math.max(maxProfit, max2List.get(j));
      else maxProfit = Math.max(maxProfit, max2List.get(j) + max1List.get(i - 1));

    }
      return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
