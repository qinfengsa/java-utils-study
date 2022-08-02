package org.example.asm.method;

import java.util.Objects;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * class 添加方法
 *
 * @author qinfengsa
 * @date 2022/07/26 18:04
 */
public abstract class ClassAddMethodVisitor extends ClassVisitor {

    private final int methodAccess;

    private final String methodName;

    private final String methodDesc;

    private final String methodSignature;

    private final String[] methodExceptions;

    private boolean methodPresent = false;


    protected ClassAddMethodVisitor(int api, ClassVisitor classVisitor, int methodAccess, String methodName,
                                    String methodDesc, String methodSignature, String[] methodExceptions) {
        super(api, classVisitor);
        this.methodAccess = methodAccess;
        this.methodName = methodName;
        this.methodDesc = methodDesc;
        this.methodSignature = methodSignature;
        this.methodExceptions = methodExceptions;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (Objects.equals(name, methodName) && Objects.equals(descriptor, methodDesc)) {
            methodPresent = true;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        if (!methodPresent) {
            MethodVisitor mv = super.visitMethod(methodAccess, methodName, methodDesc, methodSignature, methodExceptions);
            if (Objects.nonNull(mv)) {
                // 生成 方法体
                generateMethodBody(mv);
            }
        }
        super.visitEnd();
    }

    protected abstract void generateMethodBody(MethodVisitor mv);
}
