package org.example.guava.basic;

import com.google.common.collect.Ordering;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 排序
 *
 * @author qinfengsa
 * @date 2022/01/06 17:34
 */
public class SortMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(SortMain.class);

    public static void main(String[] args) {
        stringSort();
    }

    public static void stringSort() {
        // java 实现
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        // guava 实现  Ordering 实现了Comparator接口
        Ordering<String> ordering = new Ordering<>() {
            @Override public int compare(String left, String right) {
                return left.length() - right.length();
            }
        };
        List<String> list = Arrays.asList("1", "22", "333", "0000");
        list.sort(ordering.reversed());
        LOGGER.debug("reverse:{}", list);
        list.sort(comparator);
        LOGGER.debug("natural:{}", list);
    }
}
