package org.example.asm.method;

import java.lang.reflect.Method;

/**
 * @author wangheng
 * @date 2022/07/26 11:11
 */
public class MethodVisitorDemoRun {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("org.example.asm.sample.HelloWorld");
        // 获取构造方法
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method m1 = clazz.getDeclaredMethod("test", int.class, int.class);
        m1.invoke(obj, 10, 20);

        Method m2 = clazz.getDeclaredMethod("printDate");
        m2.invoke(obj);
    }

}
