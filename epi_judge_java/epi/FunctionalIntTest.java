package epi;

import java.util.Collections;

public class FunctionalIntTest {

    @FunctionalInterface
    interface FuncInt {
        int action(int a, int b);
    }

     static public void lambdaTest(int a, int b, FuncInt func) {

         System.out.println("Output = " + func.action(a, b));

    }


    public static void main(String[] args) {

        FuncInt add = (x, y) -> x + y;

        FunctionalIntTest.lambdaTest(5, 10, add);




    }
}
