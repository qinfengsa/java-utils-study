package org.example.guava.collection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Multimap 一个键映射到多个值 等价于 Map<K, List>或Map<K, Set>
 * Multimap 通过Map<K, Collection<V>> map 实现
 *
 * @author qinfengsa
 * @date 2022/01/11 16:31
 */
public class MultimapMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultimapMain.class);

    public static void main(String[] args) {
        Multimap<String, Integer> multimap = HashMultimap.create();
        // put(K, V) 添加键到单个值的映射
        multimap.put("A", 1);
        multimap.put("A", 3);
        multimap.put("A", 4);
        multimap.put("B", 1);
        multimap.put("B", 3);
        multimap.put("B", 0);

        List<Integer> list = Arrays.asList(5, 7, 11);
        // putAll(K, Iterable<V>) 依次添加键到多个值的映射
        multimap.putAll("D", list);
        LOGGER.debug("multimap :{}", multimap);
        LOGGER.debug("keys:{}", multimap.keySet());
        LOGGER.debug("values:{}", multimap.values());
        // remove(K, V) 移除键到值的映射；如果有这样的键值并成功移除，返回true。
        multimap.remove("B", 0);
        LOGGER.debug("multimap :{}", multimap);

        // removeAll(K) 清除键对应的所有值，返回的集合包含所有之前映射到K 的值，修改这个集合不会影响Multimap
        Collection<Integer> collection = multimap.removeAll("B");
        LOGGER.debug("removeAll : {}", collection);
        LOGGER.debug("multimap :{}", multimap);

        // 替换
        // replaceValues(K, Iterable<V>) 清除键对应的所有值，并重新把key 关联到Iterable 中的每个元素。返回的集合包含所有之前映射到K 的值
        Collection<Integer> collection2 = multimap.replaceValues("D", Arrays.asList(5, 7));
        LOGGER.debug("removeValue : {}", collection2);
        LOGGER.debug("multimap :{}", multimap);

        Map<String, Collection<Integer>> map = multimap.asMap();
        LOGGER.debug("map:{}", map);
    }
}
