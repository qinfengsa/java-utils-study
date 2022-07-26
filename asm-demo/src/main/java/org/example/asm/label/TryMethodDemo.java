package org.example.asm.label;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V11;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * @author wangheng
 * @date 2022/07/25 15:55
 */
public class TryMethodDemo {


    static final ClassLoader classLoader = TryMethodDemo.class.getClassLoader();


    public static void main(String[] args) throws IOException {
        String relativePath = "org/example/asm/sample/HelloWorldTry.class";
        String path = classLoader.getResource("").getPath();
        File file = FileUtils.getFile(path + relativePath);
        FileUtils.writeByteArrayToFile(file, dump());
    }

    private static byte[] dump() {
        // class
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V11, ACC_PUBLIC | ACC_SUPER, "org/example/asm/sample/HelloWorldTry", null,
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
        // method 2 try 方法
        {
            // void test()
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            Label startLabel = new Label(),
                    endLabel = new Label(),
                    exceptionHandlerLabel = new Label(),
                    returnLabel = new Label();

            mv2.visitCode();
            // try catch
            mv2.visitTryCatchBlock(startLabel, endLabel, exceptionHandlerLabel, "java/lang/InterruptedException");

            // try
            mv2.visitLabel(startLabel);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("Before Sleep");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitLdcInsn(1000L);
            mv2.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "sleep", "(J)V", false);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("After Sleep");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);


            // end
            mv2.visitLabel(endLabel);
            mv2.visitJumpInsn(GOTO, returnLabel);

            // catch
            mv2.visitLabel(exceptionHandlerLabel);

            mv2.visitVarInsn(ASTORE, 1);
            mv2.visitVarInsn(ALOAD, 1);
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/lang/InterruptedException", "printStackTrace", "()V", false);


            // return
            mv2.visitLabel(returnLabel);
            mv2.visitInsn(RETURN);
            mv2.visitMaxs(0, 0);
            mv2.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
