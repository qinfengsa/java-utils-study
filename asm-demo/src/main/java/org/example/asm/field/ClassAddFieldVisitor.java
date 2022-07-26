package org.example.asm.field;

import java.util.Objects;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

/**
 * class 添加 字段
 *
 * @author wangheng
 * @date 2022/07/26 17:48
 */
public class ClassAddFieldVisitor extends ClassVisitor {

    private final int access;

    private final String fieldName;

    private final String fieldDesc;

    private boolean fieldPresent;

    protected ClassAddFieldVisitor(int api, ClassVisitor classVisitor, int access, String filedName, String filedDesc) {
        super(api, classVisitor);
        this.access = access;
        this.fieldName = filedName;
        this.fieldDesc = filedDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (Objects.equals(name, fieldName)) {
            this.fieldPresent = true;
        }
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!this.fieldPresent) {
            FieldVisitor fv = super.visitField(access, fieldName, fieldDesc, null, null);
            if (Objects.nonNull(fv)) {
                super.visitEnd();
            }
        }
        super.visitEnd();
    }
}
