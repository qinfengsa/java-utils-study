package org.example.asm.method;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V11;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * @author qinfengsa
 * @date 2022/07/26 11:11
 */
public class MethodVisitorDemo {

    static final ClassLoader classLoader = MethodVisitorDemo.class.getClassLoader();


    public static void main(String[] args) throws IOException {
        String relativePath = "org/example/asm/sample/HelloWorld.class";
        String path = classLoader.getResource("").getPath();
        File file = FileUtils.getFile(path + relativePath);
        FileUtils.writeByteArrayToFile(file, dump());
    }

    private static byte[] dump() {
        // class
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V11, ACC_PUBLIC | ACC_SUPER, "org/example/asm/sample/HelloWorld", null,
                "java/lang/Object", null);
        // method 1 构造方法
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
        // method 2 static {} 代码块
        {
            // static {}
            MethodVisitor mv2 = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);

            mv2.visitCode();
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("class initialization method");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            // return
            mv2.visitInsn(RETURN);
            mv2.visitMaxs(2, 0);
            mv2.visitEnd();
        }
        // method 3 void test(int,int)
        {
            // void test(int,int)
            MethodVisitor mv3 = cw.visitMethod(ACC_PUBLIC, "test", "(II)V", null, null);
            mv3.visitCode();
            mv3.visitVarInsn(ILOAD, 1);
            mv3.visitVarInsn(ILOAD, 2);
            mv3.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "max", "(II)I", false);
            mv3.visitVarInsn(ISTORE, 3);
            mv3.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv3.visitVarInsn(ILOAD, 3);
            mv3.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv3.visitInsn(RETURN);
            mv3.visitMaxs(1, 1);
            mv3.visitEnd();
        }

        {
            // void printDate()
            MethodVisitor mv4 = cw.visitMethod(ACC_PUBLIC, "printDate", "()V", null, null);
            mv4.visitCode();
            // new Date
            mv4.visitTypeInsn(NEW, "java/util/Date");
            mv4.visitInsn(DUP);
            mv4.visitMethodInsn(INVOKESPECIAL, "java/util/Date", "<init>", "()V", false);
            mv4.visitVarInsn(ASTORE, 1);
            mv4.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv4.visitVarInsn(ALOAD, 1);
            mv4.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
            mv4.visitInsn(RETURN);
            mv4.visitMaxs(2, 2);
            mv4.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
