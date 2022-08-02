package org.example.asm.label;

import java.lang.reflect.Method;

/**
 * @author qinfengsa
 * @date 2022/07/25 18:20
 */
public class TryMethodDemoRun {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("org.example.asm.sample.HelloWorldTry");
        // 获取构造方法
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("test");
        method.invoke(obj);
    }
}
