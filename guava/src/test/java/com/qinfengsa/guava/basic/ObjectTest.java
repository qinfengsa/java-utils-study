package com.qinfengsa.guava.basic;

import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 1.3 常见Object方法: 简化Object方法实现，如hashCode()和toString()
 * @author qinfengsa
 * @date 2020/05/23 06:29
 */
@Slf4j
public class ObjectTest {


    /**
     * JAVA7  引入 Objects.equals API
     */
    @Test
    public void equalTest() {
        Long a = 1024L,b = 1024L;
        log.debug("result :{}",Objects.equal(a,b));
    }


    /**
     * JAVA7  引入 Objects.hash(Object...)
     */
    @Test
    public void hashTest() {
        String a = "KS";
        int b = 12;
        log.debug("hash:{}",Objects.hashCode(a,b));
        log.debug("hash2:{}",java.util.Objects.hash(a,b));
    }
}
