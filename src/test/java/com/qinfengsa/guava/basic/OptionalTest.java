package com.qinfengsa.guava.basic;

import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 1. 基本工具 [Basic utilities]
 * 1.1 使用和避免null：null是模棱两可的，会引起令人困惑的错误，有些时候它让人很不舒服。很多Guava工具类用快速失败拒绝null值，而不是盲目地接受
 * @author qinfengsa
 * @date 2020/05/21 12:46
 */
@Slf4j
public class OptionalTest {

    /**
     *
     * Java8 已提供 Optional 类的API
     */
    @Test
    public void createOptional() {
        // Optional.of(T) 创建指定引用的Optional 实例，若引用为null 则快速失败
        String str1 = null;
        Optional<String> optional1 = Optional.of(str1);

        // Optional.absent() 创建引用缺失的Optional 实例
        Optional<String> optional2 = Optional.absent();

        optional2.isPresent();

        // Optional.fromNullable(T) 创建指定引用的Optional 实例，若引用为null 则表示缺失
        String str3 = null;
        Optional<String> optional3 = Optional.fromNullable(str3);


    }
    @Test
    public void useOptional() {
        String str = null;
        Optional<String> optional = Optional.fromNullable(str);
        // boolean isPresent() 如果Optional 包含非null 的引用（引用存在），返回true
        log.debug("1:{}",optional.isPresent());


        // T or(T) 返回Optional 所包含的引用，若引用缺失，返回指定的值
        log.debug("or:{}",optional.or("empty"));
        // T orNull() 返回Optional 所包含的引用，若引用缺失，返回null
        log.debug("orNull:{}",optional.orNull());
        // Set<T> asSet() 返回Optional 所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
    }

    @Test
    public void getOptional() {
        String str = "null val";
        Optional<String> optional = Optional.fromNullable(str);
        // T get() 返回Optional 所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
        try {
            log.debug("value:{}",optional.get());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }



    // 1.4 排序: Guava强大的”流畅风格比较器”
    //
    // 1.5 Throwables：简化了异常和错误的传播与检查
}
