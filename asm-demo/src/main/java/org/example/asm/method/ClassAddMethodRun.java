package org.example.asm.method;

import java.lang.reflect.Method;
import org.example.asm.sample.HelloWorldDemo;
import org.example.asm.sample.HelloWorldDemoTransform;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * class 添加方法
 *
 * @author qinfengsa
 * @date 2022/07/26 18:04
 */
public class ClassAddMethodRun {

    public static void main(String[] args) throws Exception {
        HelloWorldDemoTransform.transform(cw -> new ClassAddMethodVisitor(Opcodes.ASM9, cw, Opcodes.ACC_PUBLIC,
                "mul", "(II)I", null, null) {
            @Override
            protected void generateMethodBody(MethodVisitor mv) {
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ILOAD, 1);
                mv.visitVarInsn(Opcodes.ILOAD, 2);
                // 乘法
                mv.visitInsn(Opcodes.IMUL);
                mv.visitInsn(Opcodes.IRETURN);
                // maxStack 方法的最大堆栈大小
                // maxLocals 方法的最大局部变量数。
                mv.visitMaxs(2, 3);
                mv.visitEnd();
            }
        });
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
