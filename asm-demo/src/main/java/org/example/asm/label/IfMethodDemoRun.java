package org.example.asm.label;

import java.lang.reflect.Method;

/**
 * @author qinfengsa
 * @date 2022/07/25 16:03
 */
public class IfMethodDemoRun {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("org.example.asm.sample.HelloWorldIf");
        // 获取构造方法
        // Constructor<?> constructor = clazz.getConstructor();
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("test", int.class);
        method.invoke(obj, 0);
        method.invoke(obj, 1);
    }
}
