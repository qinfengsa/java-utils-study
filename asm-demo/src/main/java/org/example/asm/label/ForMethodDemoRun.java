package org.example.asm.label;

import java.lang.reflect.Method;

/**
 * @author qinfengsa
 * @date 2022/07/25 17:23
 */
public class ForMethodDemoRun {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("org.example.asm.sample.HelloWorld4");
        // 获取构造方法
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("test");
        method.invoke(obj);
    }
}
