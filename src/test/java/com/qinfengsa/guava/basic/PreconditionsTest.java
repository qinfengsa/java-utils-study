package com.qinfengsa.guava.basic;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * 1.2 前置条件: 让方法中的条件检查更简单
 * @author qinfengsa
 * @date 2020/05/23 06:28
 */
public class PreconditionsTest {


    /**
     * checkArgument(boolean) 检查boolean 是否为true，用来检查 传递给方法的参数。
     * IllegalArgumentException
     */
    @Test
    public void test1() {
        int num = -1;
        Preconditions.checkArgument(num > 0,"不能小于0");
    }



    /**
     * checkNotNull(T) 检查value 是否为null，该方法直接 返回value，因此可以内嵌使用checkNotNull?  NullPointerException
     */
    @Test
    public void test2() {
        String str = null;
        Preconditions.checkNotNull( str,"不能为null");
    }

    /**
     * checkState(boolean) 用来检查对象的某些状态。IllegalStateException
     */
    @Test
    public void test3() {
        int num = -1;
        Preconditions.checkState(num > 0,"state");
    }

    /**
     * checkElementIndex(int index, int size) 检查index 作为索引值对某个列表、字符串或数组是否有效。inde >=0 && index<size
     * IndexOutOfBoundsException
     */
    @Test
    public void test4() {
        int num = -1;
        Preconditions.checkElementIndex(3,3);
    }


    /**
     * checkPositionIndex(int index, int size) 检查index 作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size
     * IndexOutOfBoundsException
     */
    @Test
    public void test5() {
        int num = -1;
        Preconditions.checkPositionIndex(4,3);
    }


    /**
     * checkPositionIndexes(int start, int end, int size) 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效
     * IndexOutOfBoundsException
     */
    @Test
    public void test6() {
        int num = -1;
        Preconditions.checkPositionIndexes(1,4,3);
    }
}