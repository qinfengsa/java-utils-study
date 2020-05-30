package com.qinfengsa.guava.conllection;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.qinfengsa.guava.domain.GuUser;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 2. 集合[Collections]
 * 2.3 强大的集合工具类: 提供java.util.Collections中没有的集合工具
 * @author qinfengsa
 * @date 2020/05/24 22:09
 */
@Slf4j
public class UtilsTest {


    /**
     * Iterators
     */
    @Test
    public void testIterables() {

        List<String> list1 = Arrays.asList("1","3","5");
        List<String> list2 = Arrays.asList("2","4","6");
        List<String> list3 = Arrays.asList("4","3","3");
        // concat(Iterable<Iterable>) 串联多个iterables 的懒视图
        Iterator<String> iterator = Iterators.concat(list1.iterator(),list2.iterator());
        /*while (iterator.hasNext()) {
            log.debug("{}",iterator.next());
        }*/
        // frequency(Iterable, Object) 返回对象在iterable 中出现的次数
        iterator = Iterators.concat(iterator,list3.iterator());
        log.debug("count:{}",Iterators.frequency(iterator,"4"));
        // partition(Iterable, int) 把iterable 按指定大小分割，得到的子集都不能进行修改操作
        // getFirst(Iterable, T default) 返回iterable 的第一个元素，若iterable 为空则返回默认值
        log.debug("last:{}",Iterators.getLast(iterator,"K"));
        // getLast(Iterable) 返回iterable 的最后一个元素，若iterable 为空则抛出NoSuchElementException
        // elementsEqual(Iterable,Iterable) 如果两个iterable 中的所有元素相等且顺序一致，返回true
        // unmodifiableIterable(Iterable) 返回iterable 的不可变视图
        // limit(Iterable, int) 限制iterable 的元素个数限制给定值
        // getOnlyElement(Iterable) 获取iterable 中唯一的元素，如果iterable 为空或有多个元素，则快速失败
    }

    /**
     * Lists
     */
    @Test
    public void testLists() {
        // partition(List, int) 把List 按指定大小分割
        // reverse(List) 返回给定List 的反转视图。注: 如果List 是不可变的，考虑改用ImmutableList.reverse()

        List<Integer> countUp = Arrays.asList(1, 2, 3, 4, 5);
        log.debug("countUp : {}",countUp);
        List<Integer> countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
        log.debug("countDown : {}",countDown);
        List<List<Integer>> parts = Lists.partition(countUp, 2);//{{1,2}, {3,4}, {5}}
        log.debug("parts : {}",parts);

    }

    @Test
    public void testSets() {
        // union(Set, Set) 合并两个集合
        Set<String> set1 = new HashSet<>(Arrays.asList("K","F","C"));
        Set<String> set2 = new HashSet<>(Arrays.asList("E","D","G"));
        Sets.SetView<String> setView = Sets.union(set1,set2);
        log.debug("set:{}",setView);


        Set<String> set3 = new HashSet<>(Arrays.asList("K","F","C"));
        Set<String> set4 = new HashSet<>(Arrays.asList("F","K","G"));
        // intersection(Set, Set) 返回 两个集合的交集
        Sets.SetView<String> setView2 = Sets.intersection(set3,set4);
        log.debug("set2:{}",setView2);


        // difference(Set, Set) 返回 集合1 中 与集合2不同的元素
        Sets.SetView<String> setView3 = Sets.difference(set3,set4);
        log.debug("set3:{}",setView3);
        // symmetricDifference(Set, Set) 返回 集合1与集合2 双方不同的元素
        Sets.SetView<String> setView4 = Sets.symmetricDifference(set3,set4);
        log.debug("set4:{}",setView4);
        // cartesianProduct(List<Set>) 返回所有集合的笛卡儿积
        Set<List<String>> cartes =  Sets.cartesianProduct(set3,set4);
        log.debug("cartes:{}",cartes);
        // powerSet(Set) 返回给定集合的所有子集
        Set<Set<String>> children =  Sets.powerSet(set1);
        for (Set<String> child : children) {
            log.debug("child:{}", child);
        }

    }

    @Test
    public void testMaps() {

        // Maps.uniqueIndex(Iterable,Function) 把集合通过唯一属性，转成map
        // Java8 提供了toMap
        List<GuUser> list = Arrays.asList(new GuUser(1,"A",11),
                new GuUser(2,"B",12), new GuUser(3,"C",20),
                new GuUser(4,"D",22), new GuUser(5,"E",19));
        ImmutableMap<Integer, GuUser> immutableMap =
                Maps.uniqueIndex(list, new Function<GuUser, Integer>() {
                    @Nullable
                    @Override
                    public Integer apply(@Nullable GuUser user) {
                        return user.getId();
                    }
                });
        log.debug("immutableMap:{}",immutableMap);
        // Java8 提供了toMap 实现相同的功能
        Map<Integer, GuUser> map = list.stream().collect(Collectors.toMap(GuUser::getId, java.util.function.Function.identity()));
        log.debug("map:{}",map);
        // Maps.difference(Map, Map) 用来比较两个Map 以获取所有不同点
        Map<Integer,String> map1 = new HashMap<>();
        map1.put(1,"A");
        map1.put(2,"B");
        map1.put(3,"C");
        map1.put(5,"D");
        Map<Integer,String> map2 = new HashMap<>();
        map2.put(1,"A");
        map2.put(2,"I");
        map2.put(3,"J");
        map2.put(6,"K");
        MapDifference<Integer,String> difference = Maps.difference(map1, map2);




        // entriesInCommon() 两个Map 中都有的映射项，包括匹配的键与值
        Map<Integer,String> common = difference.entriesInCommon();
        log.debug("common:{}",common);
        // entriesDiffering() 键相同 但是值不同值映射项
        // 返回的Map 的值类型为MapDifference.ValueDifference，以表示左右两个不同的值
        Map<Integer, MapDifference.ValueDifference<String>> differ = difference.entriesDiffering();
        log.debug("differ:{}",differ);
        // entriesOnlyOnLeft() 键只存在于左边Map 的K-V
        Map<Integer,String> left = difference.entriesOnlyOnLeft();
        log.debug("left:{}",left);
        // entriesOnlyOnRight() 键只存在于右边Map 的K-V
        Map<Integer,String> right = difference.entriesOnlyOnRight();
        log.debug("right:{}",right);
    }


    /**
     * Multisets
     */
    @Test
    public void testMultisets() {
        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("a", 2);
        multiset1.add("b",4);
        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);
        multiset2.add("c", 1);
        // 返回true；因为包含了所有不重复元素，
        multiset1.containsAll(multiset2);
        // containsOccurrences(Multiset super, Multiset sub) 判断 super 是否包含 sub
        // 对任意o，如果sub.count(o)<=super.count(o)，返回true
        boolean b1 = Multisets.containsOccurrences(multiset1, multiset2);
        log.debug("b1:{}",b1);

        // removeOccurrences(Multiset toModify, Multiset toRemove) 对toRemove 中的重复元素，仅在removeFrom 中删除相同个数
        // 如果 toModify中 不存在toRemove中的元素, 返回false
        boolean b2 = Multisets.removeOccurrences(multiset1,multiset2);

        log.debug("b2:{}",b2);
        log.debug("set1;{}",multiset1);

        // 虽然multiset1实际上包含2个"a"，而multiset2包含5个"a"

        // retainOccurrences(Multiset toModify, Multiset toRetain)
        // 保留toModify中toRetain中的元素, 使toModify成为toRetain的子集
        // 修改toModify，以保证任意o 都符合toModify.count(o)<=toRetain.count(o)
        Multisets.retainOccurrences(multiset1,multiset2);
        log.debug("set1;{}",multiset1);


        // intersection(Multiset, Multiset) 返回两个multiset 的交集;
        Multiset<String> multiset = Multisets.intersection(multiset1,multiset2);

        log.debug("multiset : {}",multiset);

        // copyHighestCountFirst(Multiset) 返回Multiset 的不可变拷贝，并将元素按重复出现的次数做降序排列
        Multiset<String> multiset3 = HashMultiset.create();
        multiset3.add("a", 2);
        multiset3.add("b",4);
        multiset3.add("3",1);
        log.debug("sort:{}",Multisets.copyHighestCountFirst(multiset3));
    }


    /**
     * Multimaps
     */
    @Test
    public void testMultimaps() {
        // index 将列表转换成map
        ImmutableSet<String> digits = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            public Integer apply(String string) {
                return string.length();
            }
        };
        ImmutableListMultimap<Integer, String> digitsByLength = Multimaps.index(digits, lengthFunction);
        log.debug("digitsByLength :{}",digitsByLength);
        /*
         * digitsByLength maps:
         * 3 => {"one", "two", "six"}
         * 4 => {"zero", "four", "five", "nine"}
         * 5 => {"three", "seven", "eight"}
         */
        // invertFrom Map 的k-v 反转
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Arrays.asList(2, 4, 6));
        multimap.putAll("a", Arrays.asList(4, 2, 1));
        multimap.putAll("c", Arrays.asList(2, 5, 3));

        HashMultimap<Integer, String> inverse  =
                Multimaps.invertFrom(multimap, HashMultimap.create());
        log.debug("inverse:{}",inverse);

        // forMap(Map)把Map 包装成SetMultimap

    }

    @Test
    public void testTables() {

    }
}
