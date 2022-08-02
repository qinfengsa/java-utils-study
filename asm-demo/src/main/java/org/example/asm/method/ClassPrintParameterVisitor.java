package org.example.asm.method;

import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_NATIVE;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * 打印方法的输入和输出参数
 *
 * @author qinfengsa
 * @date 2022/08/01 17:33
 */
public class ClassPrintParameterVisitor extends ClassVisitor {

    protected ClassPrintParameterVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null) {
            boolean isAbstractMethod = (access & ACC_ABSTRACT) != 0;
            boolean isNativeMethod = (access & ACC_NATIVE) != 0;
            if (!isAbstractMethod && !isNativeMethod) {
                mv = new MethodPrintParameterAdapter(api, mv, access, name, descriptor);
            }
        }
        return mv;
    }

    static class MethodPrintParameterAdapter extends AdviceAdapter {

        protected MethodPrintParameterAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(api, methodVisitor, access, name, descriptor);
        }

        /**
         * 方法入口
         */
        @Override
        protected void onMethodEnter() {
            printMessage("Method Enter: " + getName() + methodDesc);
            Type[] argumentTypes = getArgumentTypes();
            for (int i = 0; i < argumentTypes.length; i++) {
                Type t = argumentTypes[i];
                loadArg(i);
                box(t);
                printValueOnStack("(Ljava/lang/Object;)V");
            }
        }

        /**
         * 方法出口
         *
         * @param opcode one of {@link Opcodes#RETURN}, {@link Opcodes#IRETURN}, {@link Opcodes#FRETURN},
         *               {@link Opcodes#ARETURN}, {@link Opcodes#LRETURN}, {@link Opcodes#DRETURN} or {@link
         *               Opcodes#ATHROW}.
         */
        @Override
        protected void onMethodExit(int opcode) {
            printMessage("Method Exit: " + getName() + methodDesc);
            if (opcode == ATHROW) {
                super.visitLdcInsn("abnormal return");
            } else if (opcode == RETURN) {
                super.visitLdcInsn("return void");
            } else if (opcode == ARETURN) {
                dup();
            } else {
                if (opcode == LRETURN || opcode == DRETURN) {
                    dup2();
                } else {
                    dup();
                }
                box(getReturnType());
            }
            printValueOnStack("(Ljava/lang/Object;)V");
        }

        private void printMessage(String str) {
            super.visitLdcInsn(str);
            super.visitMethodInsn(INVOKESTATIC, "org/example/util/ParameterUtils", "printText", "(Ljava/lang/String;)V", false);
        }

        private void printValueOnStack(String descriptor) {
            super.visitMethodInsn(INVOKESTATIC, "org/example/util/ParameterUtils", "printValueOnStack", descriptor, false);
        }
    }
}
