package org.example.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BiMap 键值对的双向映射
 * BiMap<K, V>是特殊的Map：
 * • 可以用inverse()反转BiMap<K, V>的键值映射
 * • 保证值是唯一的，因此values()返回Set 而不是普通的Collection
 *
 * @author qinfengsa
 * @date 2022/01/11 16:31
 */
public class BiMapMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(BiMapMain.class);

    public static void main(String[] args) {
        BiMap<String, Integer> biMap = HashBiMap.create();

        biMap.put("A", 1);
        biMap.put("B", 2);
        biMap.put("C", 3);
        biMap.put("D", 0);
        // 如果value 已经存在映射 会抛异常 value already present: 0 需要使用forcePut替换
        // biMap.put("E",0);
        biMap.forcePut("E", 0);

        LOGGER.debug("biMap:{}", biMap);
        LOGGER.debug("id :{}", biMap.get("A"));
        LOGGER.debug("name :{}", biMap.inverse().get(0));
    }
}
