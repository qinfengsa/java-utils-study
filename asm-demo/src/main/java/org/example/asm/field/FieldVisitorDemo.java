package org.example.asm.field;

import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.V11;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

/**
 * @author wangheng
 * @date 2022/07/13 16:07
 */
public class FieldVisitorDemo {

    static final ClassLoader classLoader = FieldVisitorDemo.class.getClassLoader();


    public static void main(String[] args) throws IOException {
        String relativePath = "org/example/asm/sample/HelloWorld.class";
        String path = classLoader.getResource("").getPath();
        File file = FileUtils.getFile(path + relativePath);
        FileUtils.writeByteArrayToFile(file, dump());
    }

    private static byte[] dump() {
        // class
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V11, ACC_PUBLIC | ACC_SUPER, "org/example/asm/sample/HelloWorld", null,
                "java/lang/Object", null);

        {
            // field1
            // public static final int num = 100
            FieldVisitor field1 = cw.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, "num",
                    "I", null, 100);
            field1.visitEnd();
        }
        {
            FieldVisitor field2 = cw.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, "name",
                    "Ljava/lang/String;", null, "King");
            {
                AnnotationVisitor anno1 = field2.visitAnnotation("Lorg/example/annotation/MyTag;", false);
                anno1.visit("name", "tomcat");
                anno1.visit("age", 11);
                anno1.visitEnd();
            }

            field2.visitEnd();
        }
        cw.visitEnd();
        return cw.toByteArray();
    }
}
