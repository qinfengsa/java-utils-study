package com.qinfengsa.guava;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 8. 区间[Ranges]
 * 可比较类型的区间API，包括连续和离散类型
 * @author qinfengsa
 * @date 2020/05/21 13:22
 */
@Slf4j
public class RangesTest {

    /**
     * 构建区间
     */
    @Test
    public void testCreateRange() {
        // (a..b) open(C, C)
        Range<Integer> range1 = Range.open(1,3);

        // [a..b] closed(C, C)
        Range<Integer> range2 = Range.closed(1,3);
        // [a..b) closedOpen(C, C)
        Range<Integer> range3 = Range.closedOpen(1,3);
        // (a..b] openClosed(C, C)
        Range<Integer> range4 = Range.openClosed(1,3);
        // (a..+∞) greaterThan(C)
        Range<Integer> range5 = Range.greaterThan(3);
        // [a..+∞) atLeast(C)
        Range<Integer> range6 = Range.atLeast(3);
        // (-∞..b) lessThan(C)
        Range<Integer> range7 = Range.lessThan(3);
        // (-∞..b] atMost(C)
        Range<Integer> range8 = Range.atMost(3);
        // (-∞..+∞) all()
        Range<Integer> range9 = Range.all();

        // (a..+∞)或[a..+∞)，取决于boundType
        Range.downTo(4, BoundType.OPEN);
        // [1..4)，等同于Range.closedOpen(1, 4)
        Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN);

    }


    /**
     * 区间运算
     */
    @Test
    public void testContains() {
        boolean b1 = Range.closed(1, 3).contains(2);//return true
        log.debug("b1:{}",b1);
        boolean b2 = Range.closed(1, 3).contains(4);//return false
        log.debug("b2:{}",b2);
        boolean b3 = Range.lessThan(5).contains(5); //return false
        log.debug("b3:{}",b3);
        boolean b4 = Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)); //return true
        log.debug("b4:{}",b4);
    }


    /**
     * 查询运算
     * hasLowerBound()和hasUpperBound()：判断区间是否有特定边界，或是无限的；
     *
     * lowerBoundType()和upperBoundType()：返回区间边界类型，CLOSED 或OPEN；如果区间没有对应的边界，抛出IllegalStateException；
     *
     * lowerEndpoint()和upperEndpoint()：返回区间的端点值；如果区间没有对应的边界，抛出IllegalStateException；
     * isEmpty()：判断是否为空区间。
     */
    @Test
    public void testSearch() {

        boolean b1 = Range.closedOpen(4, 4).isEmpty(); // returns true
        log.debug("b1:{}",b1);
        boolean b2 = Range.openClosed(4, 4).isEmpty(); // returns true
        log.debug("b2:{}",b2);
        boolean b3 = Range.closed(4, 4).isEmpty(); // returns false
        log.debug("b3:{}",b3);
        int low = Range.closed(3, 10).lowerEndpoint(); // returns 3
        log.debug("low:{}",low);
        int low2 = Range.open(3, 10).lowerEndpoint(); // returns 3
        log.debug("low2:{}",low2);
        BoundType type1 = Range.closed(3, 10).lowerBoundType(); // returns CLOSED
        log.debug("type1:{}",type1);
        BoundType type2 = Range.open(3, 10).upperBoundType(); // returns OPEN

        log.debug("type2:{}",type2);
        Range.open(4, 4).isEmpty(); // Range.open throws IllegalArgumentException

    }


    /**
     * 包含运算
     */
    @Test
    public void testEnclose() {

        // [3..6] 包含[4..5]
        boolean b1 = Range.closed(3,6).encloses(Range.closed(4,5));
        log.debug("b1 :{}",b1);
        // (3..6) 包含(3..6)
        boolean b2 = Range.open(3,6).encloses(Range.open(3,6));
        log.debug("b2 :{}",b2);
        // [3..6] 包含[4..4)，虽然后者是空区间
        boolean b3 = Range.closed(3,6).encloses(Range.closedOpen(4,4));
        log.debug("b3 :{}",b3);
        // (3..6]不包含[3..6]
        boolean b4 = Range.openClosed(3,6).encloses(Range.closed(3,6));
        log.debug("b4 :{}",b4);
        // [4..5]不包含(3..6)，虽然前者包含了后者的所有值，离散域[discrete domains]可以解决这个问题
        boolean b5 = Range.closed(4,5).encloses(Range.open(3,6));
        log.debug("b5 :{}",b5);
        // [3..6]不包含(1..1]，虽然前者包含了后者的所有值
        boolean b6 = Range.openClosed(3,6).encloses(Range.openClosed(1,1));
        log.debug("b6 :{}",b6);
    }

    /**
     * 相连[isConnected]
     * 判断区间是否是相连的
     */
    @Test
    public void testConnect() {
        boolean b1 = Range.closed(3, 5).isConnected(Range.open(5, 10)); // returns true
        log.debug("b1 : {}",b1);
        boolean b2 = Range.closed(0, 9).isConnected(Range.closed(3, 4)); // returns true
        log.debug("b2 : {}",b2);
        boolean b3 = Range.closed(0, 5).isConnected(Range.closed(3, 9)); // returns true
        log.debug("b3 : {}",b3);
        boolean b4 = Range.open(3, 5).isConnected(Range.open(5, 10)); // returns false
        log.debug("b4 : {}",b4);
        boolean b5 = Range.closed(1, 5).isConnected(Range.closed(6, 10)); // returns false
        log.debug("b5 : {}",b5);
    }

    /**
     * 交集[intersection] 返回两个区间的交集
     */
    @Test
    public void testIntersection() {
        Range<Integer> range1 = Range.closed(3, 5).intersection(Range.open(5, 10)); // returns (5, 5]
        log.debug("range1:{}",range1);
        Range<Integer> range2 = Range.closed(0, 9).intersection(Range.closed(3, 4)); // returns [3, 4]
        log.debug("range2:{}",range2);
        Range<Integer> range3 = Range.closed(0, 5).intersection(Range.closed(3, 9)); // returns [3, 5]
        log.debug("range3:{}",range3);
        try {
            Range.open(3, 5).intersection(Range.open(5, 10)); // throws IAE
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        try {
            Range.closed(1, 5).intersection(Range.closed(6, 10)); // throws IAE
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }



    }

    /**
     * 跨区间[span] 同时包括两个区间的最小区间
     */
    @Test
    public void testSpan() {
        Range<Integer> range1 = Range.closed(3, 5).span(Range.open(5, 10)); // returns [3, 10)
        log.debug("range1:{}",range1);
        Range<Integer> range2 = Range.closed(0, 9).span(Range.closed(3, 4)); // returns [0, 9]
        log.debug("range2:{}",range2);
        Range<Integer> range3 = Range.closed(0, 5).span(Range.closed(3, 9)); // returns [0, 9]
        log.debug("range3:{}",range3);
        Range<Integer> range4 = Range.open(3, 5).span(Range.open(5, 10)); // returns (3, 10)
        log.debug("range4:{}",range4);
        Range<Integer> range5 = Range.closed(1, 5).span(Range.closed(6, 10)); // returns [1, 10]
        log.debug("range5:{}",range5);
    }


}
