package org.example.asm.label;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.IF_ICMPGE;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V11;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * @author qinfengsa
 * @date 2022/07/25 15:54
 */
public class ForMethodDemo {

    static final ClassLoader classLoader = ForMethodDemo.class.getClassLoader();


    public static void main(String[] args) throws IOException {
        String relativePath = "org/example/asm/sample/HelloWorld4.class";
        String path = classLoader.getResource("").getPath();
        File file = FileUtils.getFile(path + relativePath);
        FileUtils.writeByteArrayToFile(file, dump());
    }

    private static byte[] dump() {
        // class
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V11, ACC_PUBLIC | ACC_SUPER, "org/example/asm/sample/HelloWorld4", null,
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
        // method 2 for 方法
        {
            // void test( )
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            Label forLabel = new Label(), returnLabel = new Label();

            mv2.visitCode();
            // 声明 i
            mv2.visitInsn(ICONST_0);
            // i
            mv2.visitVarInsn(ISTORE, 1);

            // for
            mv2.visitLabel(forLabel);

            mv2.visitVarInsn(ILOAD, 1);
            mv2.visitIntInsn(BIPUSH, 10);
            mv2.visitJumpInsn(IF_ICMPGE, returnLabel);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitVarInsn(ILOAD, 1);
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv2.visitIincInsn(1, 1);
            mv2.visitJumpInsn(GOTO, forLabel);


            // return
            mv2.visitLabel(returnLabel);
            mv2.visitInsn(RETURN);
            mv2.visitMaxs(1, 1);

            mv2.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
