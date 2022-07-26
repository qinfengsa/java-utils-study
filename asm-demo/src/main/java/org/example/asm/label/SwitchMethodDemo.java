package org.example.asm.label;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
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
 * @date 2022/07/25 15:54
 */
public class SwitchMethodDemo {

    static final ClassLoader classLoader = SwitchMethodDemo.class.getClassLoader();


    public static void main(String[] args) throws IOException {
        String relativePath = "org/example/asm/sample/HelloWorldSwitch.class";
        String path = classLoader.getResource("").getPath();
        File file = FileUtils.getFile(path + relativePath);
        FileUtils.writeByteArrayToFile(file, dump());
    }

    private static byte[] dump() {
        // class
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V11, ACC_PUBLIC | ACC_SUPER, "org/example/asm/sample/HelloWorldSwitch", null,
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
        // method 2 switch 方法
        {
            // void test(int num)
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "test", "(I)V", null, null);
            Label caseLabel1 = new Label(),
                    caseLabel2 = new Label(),
                    caseLabel3 = new Label(),
                    caseLabel4 = new Label(),
                    caseLabelDefault = new Label(),
                    returnLabel = new Label();

            mv2.visitCode();
            // 第一个参数（int）入栈
            mv2.visitVarInsn(ILOAD, 1);
            mv2.visitTableSwitchInsn(1, 4, caseLabelDefault, caseLabel1, caseLabel2, caseLabel3, caseLabel4);

            // case 1
            mv2.visitLabel(caseLabel1);
            // 引入 import static java.lang.System.out
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            // ldc 指令
            mv2.visitLdcInsn("val = 1");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitJumpInsn(GOTO, returnLabel);

            // case 2
            mv2.visitLabel(caseLabel2);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            // ldc 指令
            mv2.visitLdcInsn("val = 2");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitJumpInsn(GOTO, returnLabel);

            // case 3
            mv2.visitLabel(caseLabel3);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            // ldc 指令
            mv2.visitLdcInsn("val = 3");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitJumpInsn(GOTO, returnLabel);


            // case 4
            mv2.visitLabel(caseLabel4);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            // ldc 指令
            mv2.visitLdcInsn("val = 4");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv2.visitJumpInsn(GOTO, returnLabel);

            // default
            // case 2
            mv2.visitLabel(caseLabelDefault);
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            // ldc 指令
            mv2.visitLdcInsn("val is unknown");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

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
