package org.example.javap;

/**
 * @author wangheng
 * @date 2022/06/23 15:48
 */
public class JavaDemo {

    private static final int P_1 = 1;

    public static final int P_2 = 2;


    public static void main(String[] args) {
        int m = 0, n = 0;
        for (int i = 0; i < 10; i++) {
            m++;
            ++n;
        }
        System.out.println("m=" + m);
        System.out.println("n=" + n);
    }

    private int method1(int a, int b) {
        return a + b;
    }

    public void method2(String str1, String str2) {
        System.out.println(str1 + str2);
    }

    String method3() {
        return "";
    }
}
