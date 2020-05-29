package com.qinfengsa.guava.reflect;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * TypeToken 类是用来解决java运行时泛型类型被擦除的问题的
 * @author qinfengsa
 * @date 2020/05/27 14:13
 */
@Slf4j
public class TypeTokenTest {

    /**
     * 类型擦除
     */
    @Test
    public void testType() {
        List<String> stringList = Lists.newArrayList("A","B","E");
        List<Integer> intList = Lists.newArrayList(1,3,6,44);
        log.debug("intList type is {}",intList.getClass());
        log.debug("stringList type is {}",stringList.getClass());
    }


    @Test
    public void test1() {

        // 定义了一个空的匿名类
        TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {

        };
        log.debug("token class :{}", typeToken.getType());
        // TypeToken解析出了泛型参数的具体类型
        TypeToken<?> genericTypeToken = typeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        log.debug("token class :{}", genericTypeToken.getType());

        log.debug("token class2 :{}", genericTypeToken.getRawType());


        // getType()	获得包装的java.lang.reflect.Type.
        // getRawType()	返回大家熟知的运行时类
        // getSubtype(Class<?>)	返回那些有特定原始类的子类型。举个例子，如果这有一个Iterable并且参数是List.class，那么返回将是List。
        // getSupertype(Class<?>)	产生这个类型的超类，这个超类是指定的原始类型。举个例子，如果这是一个Set并且参数是Iterable.class，结果将会是Iterable。
        // isAssignableFrom(type)	如果这个类型是 assignable from 指定的类型，并且考虑泛型参数，返回true。List<? extends Number>是assignable from List，但List没有.
        // getTypes()	返回一个Set，包含了这个所有接口，子类和类是这个类型的类。返回的Set同样提供了classes()和interfaces()方法允许你只浏览超类和接口类。
        // isArray()	检查某个类型是不是数组，甚至是<? extends A[]>。
        // getComponentType()	返回组件类型数组。
    }
}
