package com.qinfengsa.guava.primitives;

import com.google.common.primitives.Ints;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinfengsa
 * @date 2020/05/26 15:57
 */
@Slf4j
public class IntsTest {


    /**
     * 把数组转为相应包装类的 List
     * java提供了Arrays.asList();
     */
    @Test
    public void testAsList() {
        List<Integer> list1 = Ints.asList(1,13,4,5,7);


        log.debug("list1:{}",list1);
        int[] arrs = {9,8,4,5,11};
        List<Integer> list2 = Ints.asList(arrs);
        log.debug("list2:{}",list2);

        List<Integer> list3 = Arrays.asList(56,8,41,16,33);
        log.debug("list3:{}",list3);
    }


    /**
     * 把集合拷贝为数组
     * 和collection.toArray()一样 线程安全
     */
    @Test
    public void testToArray() {
        List<Integer> list1 = Arrays.asList(1,13,4,5,7);
        // 返回 原生类型 boolean[]
        log.debug("1:{}",Ints.toArray(list1));
        Integer[] arr = list1.toArray(new Integer[3]);
        // 返回 原生类型 Integer[]
        for (Integer b : arr) {
            log.debug("2:{}", b);
        }
    }


    /**
     * 串联多个原生类型数组
     */
    @Test
    public void testConcat() {
        int[] arr1 = {1,13,4,5,7};

        int[] arr2 = {9,8,4,5,11};
        int[] arr3 = {56,8,41,16,33};
        int[] result = Ints.concat(arr1,arr2,arr3);
        log.debug("result : {}",result);
    }


    /**
     * 判断原生类型数组是否包含给定值
     */
    @Test
    public void testContains() {
        int[] arr1 = {1,13,4,5,7};
        boolean result = Ints.contains(arr1,2);
        log.debug("result : {}",result);
    }


    /**
     * 给定值在数组中首次出现处的索引，若不包含此值返回-1
     */
    @Test
    public void testIndexOf() {
        int[] arr1 = {1,13,4,5,7,4};
        int result = Ints.indexOf(arr1,4);
        log.debug("result : {}",result);
    }

    /**
     * 给定值在数组最后出现的索引，若不包含此值返回-1
     */
    @Test
    public void testLastIndexOf() {
        int[] arr1 = {1,13,4,5,7,4,5};
        int result = Ints.lastIndexOf(arr1,4);
        log.debug("result : {}",result);
    }


    /**
     * 把数组用给定分隔符连接为字符串
     */
    @Test
    public void testJoin() {
        int[] arr1 = {1,13,4,5,7,4,5};
        String result = Ints.join(";",arr1);
        log.debug("result : {}",result);

    }




    /**
     * 数组中最小的值
     */
    @Test
    public void testMin() {
        int[] arr1 = {1,13,4,5,7,4,5};
        int min = Ints.min(arr1);
        log.debug("min : {}",min);
    }

    /**
     * 数组中最大的值
     */
    @Test
    public void testMax() {
        int[] arr1 = {1,13,4,5,7,4,5};
        int max = Ints.max(arr1);
        log.debug("max : {}",max);
    }

}
