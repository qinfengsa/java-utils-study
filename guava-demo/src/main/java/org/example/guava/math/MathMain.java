package org.example.guava.math;

import com.google.common.math.IntMath;

/**
 * @author qinfengsa
 * @date 2022/01/18 14:43
 */
public class MathMain {

    public static void main(String[] args) {

    }

    // 校验溢出
    private static void check() {
        IntMath.checkedAdd(Integer.MAX_VALUE, 1);
    }
}
