package com.qinfengsa.guava.primitives;

import com.google.common.primitives.Booleans;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinfengsa
 * @date 2020/05/26 15:58
 */
@Slf4j
public class BooleansTest {

    /**
     * 把数组转为相应包装类的 List
     * java提供了Arrays.asList();
     */
    @Test
    public void testAsList() {
        List<Boolean> list1 = Booleans.asList(true,false,true);


        log.debug("list1:{}",list1);
        boolean[] arrs = {true,false,false};
        List<Boolean> list2 = Booleans.asList(arrs);
        log.debug("list2:{}",list2);

        List<Boolean> list3 = Arrays.asList(true,false,true);
        log.debug("list3:{}",list3);
    }


    /**
     * 把集合拷贝为数组
     * 和collection.toArray()一样 线程安全
     */
    @Test
    public void testToArray() {
        List<Boolean> list1 = Arrays.asList(true,false,true);
        // 返回 原生类型 boolean[]
        log.debug("1:{}",Booleans.toArray(list1));
        Boolean[] arr = list1.toArray(new Boolean[3]);
        // 返回 原生类型 Boolean[]
        for (Boolean b : arr) {
            log.debug("2:{}", b);
        }
    }


    /**
     * 串联多个原生类型数组
     */
    @Test
    public void testConcat() {
        boolean[] arr1 = {true,false,true,false};

        boolean[] arr2 = {false,false,true};
        boolean[] arr3 = {true,false,false,true};
        boolean[] result = Booleans.concat(arr1,arr2,arr3);
        log.debug("result : {}",result);
    }


    /**
     * 判断原生类型数组是否包含给定值
     */
    @Test
    public void testContains() {
        boolean[] arr1 = {true,false,true,false};
        boolean result = Booleans.contains(arr1,false);
        log.debug("result : {}",result);
    }


    /**
     * 给定值在数组中首次出现处的索引，若不包含此值返回-1
     */
    @Test
    public void testIndexOf() {
        boolean[] arr1 = {true,false,true,false};
        int result = Booleans.indexOf(arr1,false);
        log.debug("result : {}",result);
    }

    /**
     * 给定值在数组最后出现的索引，若不包含此值返回-1
     */
    @Test
    public void testLastIndexOf() {
        boolean[] arr1 = {true,false,true,false};
        int result = Booleans.lastIndexOf(arr1,false);
        log.debug("result : {}",result);
    }


    /**
     * 把数组用给定分隔符连接为字符串
     */
    @Test
    public void testJoin() {
        boolean[] arr1 = {true,false,true,false};
        String result = Booleans.join(";",arr1);
        log.debug("result : {}",result);

    }





    //Comparator<prim[]> lexicographicalC
    //omparator()
    //按字典序比较原生类型数
    //组的Comparator

}
