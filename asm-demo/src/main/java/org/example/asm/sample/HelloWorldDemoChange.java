package org.example.asm.sample;

import java.io.File;
import java.util.function.Function;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * @author wangheng
 * @date 2022/07/26 17:08
 */
public class HelloWorldDemoChange {

    static final ClassLoader classLoader = HelloWorldDemoChange.class.getClassLoader();

    public static void change(Function<ClassWriter, ClassVisitor> func) throws Exception {
        String relativePath = "org/example/asm/sample/HelloWorldDemo.class";
        String path = classLoader.getResource("").getPath();
        File file = FileUtils.getFile(path, relativePath);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        // 构建ClassReader
        ClassReader cr = new ClassReader(bytes);
        // 构建ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        // 串连ClassVisitor
        ClassVisitor cv = func.apply(cw);
        // 结合ClassReader和ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        byte[] newBytes = cw.toByteArray();
        FileUtils.writeByteArrayToFile(file, newBytes);
    }
}
