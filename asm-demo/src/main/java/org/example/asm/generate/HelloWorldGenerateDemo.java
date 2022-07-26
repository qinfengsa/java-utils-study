package org.example.asm.generate;

import java.util.Objects;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author wangheng
 * @date 2022/06/24 15:45
 */
public class HelloWorldGenerateDemo {

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.loadClass("org.example.asm.sample.HelloWorld");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        System.out.println(instance);
    }

    static class MyClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if (Objects.equals(name, "org.example.asm.sample.HelloWorld")) {
                byte[] bytes = HelloWorldGenerate.dump();
                return defineClass(name, bytes, 0, bytes.length);
            }
            throw new ClassNotFoundException("Class Not Found: " + name);
        }
    }


    static class HelloWorldGenerate implements Opcodes {

        public static byte[] dump() {
            // class
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cw.visit(V11, ACC_PUBLIC | ACC_SUPER, "org/example/asm/sample/HelloWorld", null,
                    "java/lang/Object", null);
            // method 1
            {
                MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V",
                        null, null);
                mv1.visitCode();
                mv1.visitVarInsn(ALOAD, 0);
                mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
                mv1.visitInsn(RETURN);
                mv1.visitMaxs(1, 1);
                mv1.visitEnd();
            }

            // method 2
            {
                MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;",
                        null, null);
                mv2.visitCode();
                mv2.visitLdcInsn("This is a HelloWorld object.");
                mv2.visitInsn(ARETURN);
                mv2.visitMaxs(1, 1);
                mv2.visitEnd();
            }

            cw.visitEnd();

            return cw.toByteArray();
        }
    }
}
