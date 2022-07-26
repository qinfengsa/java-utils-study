package org.example.asm.clazz;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 修改 class 版本
 *
 * @author wangheng
 * @date 2022/07/26 16:00
 */
public class ClassChangeVersionVisitor extends ClassVisitor {

    public ClassChangeVersionVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(Opcodes.V1_8, access, name, signature, superName, interfaces);
    }
}
