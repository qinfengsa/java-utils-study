package com.qinfengsa.guava.conllection;

import com.google.common.collect.BiMap;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.Table;
import com.google.common.collect.TreeRangeSet;
import com.qinfengsa.guava.domain.GuUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 2. 集合[Collections]
 * 2.2 新集合类型: multisets, multimaps, tables, bidirectional maps等
 * @author qinfengsa
 * @date 2020/05/21 13:16
 */
@Slf4j
public class CollectionsTest {

    /**
     * Multiset 统计元素出现次数的集合Set
     * HashMultiset 通过 Map<E, Count> backingMap 实现
     * TreeMultiset 中的元素个数存储在 树的节点AvlNode中
     */
    @Test
    public void testMultiset() {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("A");
        multiset.add("A");
        multiset.add("B");
        multiset.add("C");
        multiset.add("D");

        // count(E) 给定元素在Multiset 中的计数
        log.debug("count1:{}",multiset.count("A"));

        // elementSet() Multiset 中不重复元素的集合，类型为Set<E>
        log.debug("elementSet:{}",multiset.elementSet());

        // entrySet() 和Map 的 entrySet 类似，返回Set<Multiset.Entry<E>>，其中包含的Entry 支持getElement()和getCount()方法
        log.debug("entrySet:{}",multiset.entrySet());
        // add(E, int) 增加给定元素在Multiset 中的计数
        multiset.add("B",2);
        log.debug("count2:{}",multiset.count("B"));
        log.debug("set:{}",multiset);
        // remove(E, int) 减少给定元素在Multiset 中的计数
        multiset.remove("A",1);
        log.debug("count3:{}",multiset.count("A"));
        log.debug("set:{}",multiset);
        // setCount(E, int) 设置给定元素在Multiset 中的计数，不可以为size() 返回集合元素的总个数（包括重复的元素）
        multiset.setCount("C",4);
        log.debug("count3:{}",multiset.count("C"));
        log.debug("set:{}",multiset);
    }

    /**
     * Multimap 一个键映射到多个值 等价于 Map<K, List>或Map<K, Set>
     * Multimap 通过Map<K, Collection<V>> map 实现
     */
    @Test
    public void testMultimap() {

        Multimap<String,Integer> multimap = HashMultimap.create();
        // put(K, V) 添加键到单个值的映射
        multimap.put("A",1);
        multimap.put("A",3);
        multimap.put("A",4);
        multimap.put("B",1);
        multimap.put("B",3);
        multimap.put("B",0);

        List<Integer> list = Arrays.asList(5,7,11);
        // putAll(K, Iterable<V>) 依次添加键到多个值的映射
        multimap.putAll("D",list);
        log.debug("multimap :{}",multimap);
        log.debug("keys:{}",multimap.keySet());
        log.debug("values:{}",multimap.values());
        // remove(K, V) 移除键到值的映射；如果有这样的键值并成功移除，返回true。
        multimap.remove("B",0);
        log.debug("multimap :{}",multimap);


        // removeAll(K) 清除键对应的所有值，返回的集合包含所有之前映射到K 的值，修改这个集合不会影响Multimap
        Collection<Integer> collection = multimap.removeAll("B");
        log.debug("removeAll : {}",collection);
        log.debug("multimap :{}",multimap);

        // 替换
        // replaceValues(K, Iterable<V>) 清除键对应的所有值，并重新把key 关联到Iterable 中的每个元素。返回的集合包含所有之前映射到K 的值
        Collection<Integer> collection2 = multimap.replaceValues("D",Arrays.asList(5,7));
        log.debug("removeValue : {}",collection2);
        log.debug("multimap :{}",multimap);

        Map<String,Collection<Integer>> map = multimap.asMap();
        log.debug("map:{}",map);

    }

    /**
     * BiMap 键值对的双向映射
     * BiMap<K, V>是特殊的Map：
     * • 可以用inverse()反转BiMap<K, V>的键值映射
     * • 保证值是唯一的，因此values()返回Set 而不是普通的Collection
     */
    @Test
    public void testBiMap() {
        BiMap<String,Integer> biMap = HashBiMap.create();

        biMap.put("A",1);
        biMap.put("B",2);
        biMap.put("C",3);
        biMap.put("D",0);
        // 如果value 已经存在映射 会抛异常 value already present: 0 需要使用forcePut替换
        // biMap.put("E",0);
        biMap.forcePut("E",0);

        log.debug("biMap:{}",biMap);
        log.debug("id :{}",biMap.get("A"));
        log.debug("name :{}",biMap.inverse().get(0));
    }


    /**
     * Table 使用多个键做索引
     * Guava 为此提供了新集合类型Table，它有两个支持所有类型的键：”行”和”列”
     * HashBasedTable：本质上用HashMap<R, HashMap<C, V>>实现；
     * TreeBasedTable：本质上用TreeMap<R, TreeMap<C,V>>实现；
     * ArrayTable：要求在构造时就指定行和列的大小，本质上由一个二维数组实现，以提升访问速度和密集Table 的内存利用率
     */
    @Test
    public void testTable() {
        Table<Integer,Integer,String> table = HashBasedTable.create();
        table.put(1,1,"A");
        table.put(1,2,"B");
        table.put(1,3,"C");
        table.put(2,1,"D");
        table.put(2,2,"E");
        table.put(2,3,"F");

        log.debug("row1 :{}",table.row(1));
        log.debug("col2 :{}",table.column(2));

        log.debug("2 2 :{}",table.get(2,2));
    }

    public void testClassToInstanceMap() {
        ClassToInstanceMap<GuUser> map = MutableClassToInstanceMap.create();



    }


    /**
     * RangeSet 描述了一组不相连的、非空的区间
     */
    @Test
    public void testRangeSet() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}

    }

    /**
     * RangeMap 描述了”不相交的、非空的区间”到特定值的映射
     */
    @Test
    public void testRangeMap() {

    }
}
