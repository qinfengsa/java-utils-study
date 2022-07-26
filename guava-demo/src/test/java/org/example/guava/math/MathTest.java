package org.example.guava.math;

import com.google.common.math.IntMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author qinfengsa
 * @date 2022/01/18 16:44
 */
public class MathTest {

    /**
     * 校验 运算溢出
     */
    @Test public void addCheck() {
        Assertions.assertThrows(ArithmeticException.class, () -> IntMath.checkedAdd(Integer.MAX_VALUE, 1));
        Assertions.assertThrows(ArithmeticException.class,
            () -> IntMath.checkedSubtract(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }
}
