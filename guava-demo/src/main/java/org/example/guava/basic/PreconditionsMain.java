package org.example.guava.basic;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.List;

/**
 * 1.2 前置条件: 让方法中的条件检查更简单
 * <p>
 * Spring 提供了 org.springframework.util.Assert 工具类
 *
 * @author qinfengsa
 * @date 2022/01/06 17:36
 */
public class PreconditionsMain {

    /**
     * checkArgument(boolean)
     * <p>
     * 检查boolean 是否为true，用来检查 传递给方法的参数。 IllegalArgumentException
     */
    public void checkArgument(int num) {
        Preconditions.checkArgument(num > 0, "不能小于0");
    }

    /**
     * checkNotNull(T)
     * <p>
     * 检查value 是否为null，该方法直接 返回value，因此可以内嵌使用checkNotNull? NullPointerException
     */
    public void checkNotNull(String str) {
        Preconditions.checkNotNull(str, "不能为null");
    }

    /**
     * checkState(boolean)
     * <p>
     * 用来检查对象的某些状态。IllegalStateException
     */
    public void checkState(int num) {
        Preconditions.checkState(num > 0, "state");
    }

    /**
     * checkElementIndex(int index, int size)
     * <p>
     * 检查index 作为索引值对某个列表、字符串或数组是否有效。inde >=0 && index<size
     * 抛出 IndexOutOfBoundsException 异常
     */
    public void checkElementIndex() {
        List<String> list = Arrays.asList("1", "2", "3");
        Preconditions.checkElementIndex(3, list.size());
    }

    /**
     * checkPositionIndex(int index, int size)
     * <p>
     * 检查index 作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size
     * 抛出 IndexOutOfBoundsException 异常
     */
    public void checkPositionIndex() {
        List<String> list = Arrays.asList("1", "2", "3");
        Preconditions.checkPositionIndex(4, list.size());
    }

    /**
     * checkPositionIndexes(int start, int end, int size)
     * <p>
     * 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效
     * IndexOutOfBoundsException
     */
    public void checkPositionIndexes() {
        List<String> list = Arrays.asList("1", "2", "3");
        Preconditions.checkPositionIndexes(1, 4, list.size());
    }
}
