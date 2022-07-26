package org.example.asm.field;

import java.util.Objects;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

/**
 * class 删除 字段
 *
 * @author wangheng
 * @date 2022/07/26 17:26
 */
public class ClassRemoveFieldVisitor extends ClassVisitor {

    private final String fieldName;

    private final String fieldDesc;

    protected ClassRemoveFieldVisitor(int api, ClassVisitor classVisitor, String fieldName, String fieldDesc) {
        super(api, classVisitor);
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (Objects.equals(name, fieldName) && Objects.equals(descriptor, fieldDesc)) {
            // 需要删除的字段 返回null
            return null;
        }
        return super.visitField(access, name, descriptor, signature, value);
    }
}
