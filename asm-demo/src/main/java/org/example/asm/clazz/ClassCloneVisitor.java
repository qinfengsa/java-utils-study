package org.example.asm.clazz;

import org.objectweb.asm.ClassVisitor;

/**
 * 为 class 添加 clone 接口
 *
 * @author qinfengsa
 * @date 2022/07/26 16:58
 */
public class ClassCloneVisitor extends ClassVisitor {

    protected ClassCloneVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, new String[]{"java/lang/Cloneable"});
    }
}
