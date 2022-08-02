package org.example.asm.method;

import java.lang.reflect.Method;
import org.example.asm.sample.HelloWorldDemo;
import org.example.asm.sample.HelloWorldDemoTransform;
import org.objectweb.asm.Opcodes;

/**
 * class 删除方法
 *
 * @author qinfengsa
 * @date 2022/07/26 18:03
 */
public class ClassRemoveMethodRun {

    public static void main(String[] args) throws Exception {
        HelloWorldDemoTransform.transform(cw -> new ClassRemoveMethodVisitor(Opcodes.ASM9, cw,
                "add", "(II)I"));
    }

    static class HelloWorldRun {

        public static void main(String[] args) throws Exception {
            Class<?> clazz = HelloWorldDemo.class;

            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method m : declaredMethods) {
                System.out.println("    " + m.getName());
            }
        }
    }
}
