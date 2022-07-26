package org.example.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Table 使用多个键做索引
 * Guava 为此提供了新集合类型Table，它有两个支持所有类型的键：”行”和”列”
 * HashBasedTable：本质上用HashMap<R, HashMap<C, V>>实现；
 * TreeBasedTable：本质上用TreeMap<R, TreeMap<C,V>>实现；
 * ArrayTable：要求在构造时就指定行和列的大小，本质上由一个二维数组实现，以提升访问速度和密集Table 的内存利用率
 *
 * @author qinfengsa
 * @date 2022/01/11 16:32
 */
public class TableMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(TableMain.class);

    public static void main(String[] args) {
        Table<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1, 1, "A");
        table.put(1, 2, "B");
        table.put(1, 3, "C");
        table.put(2, 1, "D");
        table.put(2, 2, "E");
        table.put(2, 3, "F");

        LOGGER.debug("row1 :{}", table.row(1));
        LOGGER.debug("col2 :{}", table.column(2));

        LOGGER.debug("2-2 :{}", table.get(2, 2));
    }
}
