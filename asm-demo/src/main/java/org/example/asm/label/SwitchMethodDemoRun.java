package org.example.asm.label;

import java.lang.reflect.Method;

/**
 * @author wangheng
 * @date 2022/07/25 17:17
 */
public class SwitchMethodDemoRun {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("org.example.asm.sample.HelloWorldSwitch");
        // 获取构造方法
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("test", int.class);
        for (int i = 1; i < 6; i++) {
            method.invoke(obj, i);
        }
    }
}
