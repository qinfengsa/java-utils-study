package org.example.asm.clazz;

import java.io.PrintWriter;
import org.example.asm.sample.HelloWorldDump;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

/**
 * 将.class文件的内容打印成文字输出
 *
 * @author qinfengsa
 * @date 2022/07/29 11:22
 */
public class TraceClassVisitorDemo {

    public static void main(String[] args) throws Exception {

        // (1) 设置参数
        String className = "org.example.asm.sample.HelloWorldDemo";
        int parsingOptions = ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG;
        boolean asmCode = false;

        // class byte[]
        byte[] bytes = HelloWorldDump.dump();

        // (2) 打印结果
        // ASMifier 打印 asm 生成指令
        // Textifier 打印 字节码
        Printer printer = asmCode ? new ASMifier() : new Textifier();
        PrintWriter printWriter = new PrintWriter(System.out, true);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, printWriter);
        new ClassReader(className).accept(traceClassVisitor, parsingOptions);

        // new ClassReader(bytes).accept(traceClassVisitor, parsingOptions);
    }
}
