package org.example.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Multiset 统计元素出现次数的集合Set
 * HashMultiset 通过 Map<E, Count> backingMap 实现
 * TreeMultiset 中的元素个数存储在 树的节点AvlNode中
 *
 * @author qinfengsa
 * @date 2022/01/11 16:30
 */
public class MultisetMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(MultisetMain.class);

    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("A");
        multiset.add("A");
        multiset.add("B");
        multiset.add("C");
        multiset.add("D");

        // count(E) 给定元素在Multiset 中的计数
        LOGGER.debug("count1:{}", multiset.count("A"));

        // elementSet() Multiset 中不重复元素的集合，类型为Set<E>
        LOGGER.debug("elementSet:{}", multiset.elementSet());

        // entrySet() 和Map 的 entrySet 类似，返回Set<Multiset.Entry<E>>，
        // 其中包含的Entry 支持getElement()和getCount()方法
        LOGGER.debug("entrySet:{}", multiset.entrySet());
        // add(E, int) 增加给定元素在Multiset 中的计数
        multiset.add("B", 2);
        LOGGER.debug("count2:{}", multiset.count("B"));
        LOGGER.debug("set:{}", multiset);
        // remove(E, int) 减少给定元素在Multiset 中的计数
        multiset.remove("A", 1);
        LOGGER.debug("count3:{}", multiset.count("A"));
        LOGGER.debug("set:{}", multiset);
        // setCount(E, int) 设置给定元素在Multiset 中的计数，
        // 不可以为size() 返回集合元素的总个数（包括重复的元素）
        multiset.setCount("C", 4);
        LOGGER.debug("count3:{}", multiset.count("C"));
        LOGGER.debug("set:{}", multiset);
    }
}
