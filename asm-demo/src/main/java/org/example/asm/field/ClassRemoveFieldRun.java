package org.example.asm.field;

import java.lang.reflect.Field;
import org.example.asm.sample.HelloWorldDemo;
import org.example.asm.sample.HelloWorldDemoTransform;
import org.objectweb.asm.Opcodes;

/**
 * @author qinfengsa
 * @date 2022/07/26 17:31
 */
public class ClassRemoveFieldRun {

    public static void main(String[] args) throws Exception {
        HelloWorldDemoTransform.transform(cw -> new ClassRemoveFieldVisitor(Opcodes.ASM9, cw, "strValue",
                "Ljava/lang/String;"));
    }


    static class HelloWorldRun {

        public static void main(String[] args) throws Exception {
            Class<?> clazz = HelloWorldDemo.class;

            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field f : declaredFields) {
                System.out.println("    " + f.getName());
            }
        }
    }
}
