package org.example.asm.clazz;

import java.io.PrintWriter;
import org.example.asm.sample.HelloWorldDump;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.CheckClassAdapter;

/**
 * 检查（Check）生成的.class文件内容是否正确
 *
 * @author qinfengsa
 * @date 2022/07/29 10:51
 */
public class ClassCheckAdapterDemo {

    public static void main(String[] args) throws Exception {
        // class byte[]
        byte[] bytes = HelloWorldDump.dump();

        // 检查
        PrintWriter printWriter = new PrintWriter(System.out);
        CheckClassAdapter.verify(new ClassReader(bytes), false, printWriter);
    }

 
}
