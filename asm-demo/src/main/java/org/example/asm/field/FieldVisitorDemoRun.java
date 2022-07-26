package org.example.asm.field;

import java.lang.reflect.Field;

/**
 * @author wangheng
 * @date 2022/07/13 16:11
 */
public class FieldVisitorDemoRun {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        Class<?> clazz = Class.forName("org.example.asm.sample.HelloWorld");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + ":" + field.get(null));
        }
    }
}
