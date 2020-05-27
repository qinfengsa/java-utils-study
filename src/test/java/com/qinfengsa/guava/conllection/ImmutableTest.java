package com.qinfengsa.guava.conllection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2. 集合[Collections]
 * 2.1 不可变集合: 用不变的集合进行防御性编程和性能提升。
 * @author qinfengsa
 * @date 2020/05/24 22:07
 */
@Slf4j
public class ImmutableTest {

    // 不可变对象的优点
    // 当对象被不可信的库调用时，不可变是安全的；
    // 不可变对象被多个线程调用时，不存在竞争问题
    // 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
    // 不可变对象因为固定不变，可以作为常量来安全使用。

    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");


    @Test
    public void test1() {
        log.debug("color : {}",COLOR_NAMES);
    }


    @Test
    public void createImmutable() {
        // of 方法

        ImmutableSet<String> immutableSet1 = ImmutableSet.of("A","B","C","D","E","F","G");
        log.debug("immutableSet1:{}",immutableSet1.toString());
        ImmutableMap<String,Integer>
                immutableMap1 = ImmutableMap.of("A",1,"B",2,"C",3,"D",4 );
        log.debug("immutableMap1:{}",immutableMap1.toString());
        // copyOf
        List<String> list1 = Arrays.asList( "A", "B", "C" );

        ImmutableList<String> immutableList1 = ImmutableList.copyOf(list1);
        log.debug("immutableList1:{}",immutableList1.toString());
        ImmutableList<String> immutableList2 = ImmutableList.copyOf(new String[] {"C","D","E"});
        log.debug("immutableList2:{}",immutableList2.toString());
        Map<String,Integer> map2 = new HashMap<>();
        map2.put("A",1);
        map2.put("B",1);
        map2.put("C",1);
        ImmutableMap<String,Integer>
                immutableMap2 = ImmutableMap.copyOf(map2);
        log.debug("immutableMap2:{}",immutableMap2.toString());
        // builder

        ImmutableList<String> immutableList3
                = ImmutableList.<String>builder()
                .add("F")
                .addAll(list1)
                .build();
        log.debug("immutableList3:{}",immutableList3.toString());
        ImmutableMap<String,Integer>
                immutableMap3 = ImmutableMap.<String, Integer>builder()
                .put("K",10)
                .putAll(map2)
                .build();
        log.debug("immutableMap3:{}",immutableMap3.toString());
    }
}
