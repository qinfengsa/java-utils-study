package org.example.guava.math;

import com.google.common.math.IntMath;
import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qinfengsa
 * @date 2022/01/18 16:37
 */
public class IntMathMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntMathMain.class);

    public static void main(String[] args) {
        // DOWN：向零方向舍入（去尾法）
        // UP：远离零方向舍入
        // FLOOR：向负无限大方向舍入
        // CEILING：向正无限大方向舍入
        // UNNECESSARY：不需要舍入，如果用此模式进行舍入，应直接抛出ArithmeticException
        // HALF_UP：向最近的整数舍入，其中x.5 远离零方向舍入
        // HALF_DOWN：向最近的整数舍入，其中x.5 向零方向舍入
        // HALF_EVEN：向最近的整数舍入，其中x.5 向相邻的偶数舍入
        // 除法
        int result = IntMath.divide(2, 3, RoundingMode.HALF_UP);
        LOGGER.debug("除法:{}", result);
        // 2为底的对数
        result = IntMath.log2(10, RoundingMode.HALF_UP);
        LOGGER.debug("2为底的对数:{}", result);
        // 10为底的对数
        result = IntMath.log10(10, RoundingMode.HALF_UP);
        LOGGER.debug("10为底的对数:{}", result);
        // 最大公约数
        result = IntMath.gcd(10, 27);
        LOGGER.debug("最大公约数:{}", result);
        // mod 求余
        result = IntMath.mod(10, 7);
        LOGGER.debug("10%7={}", result);
        // 次幂
        result = IntMath.pow(2, 7);
        LOGGER.debug("2^7={}", result);

        // 是否 2 的次幂
        LOGGER.debug("7是2的次幂:{}", IntMath.isPowerOfTwo(7));

        int fac = IntMath.factorial(11);
        LOGGER.debug("11!={}", fac);

        // gcd( int,int)mod( int,int)pow( int,int)isPowerOfTwo( int)factorial( int)binomial( int,int)

        int bi = IntMath.binomial(4, 2);
        LOGGER.debug("C 4 2={}", bi);
    }
}
