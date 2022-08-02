package org.example.asm.method;

import java.util.Objects;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * class 删除方法
 *
 * @author qinfengsa
 * @date 2022/07/26 18:03
 */
public class ClassRemoveMethodVisitor extends ClassVisitor {

    private final String methodName;

    private final String methodDesc;

    protected ClassRemoveMethodVisitor(int api, ClassVisitor classVisitor, String methodName, String methodDesc) {
        super(api, classVisitor);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (Objects.equals(name, methodName) && Objects.equals(descriptor, methodDesc)) {
            // 删除 直接返回 null
            return null;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
