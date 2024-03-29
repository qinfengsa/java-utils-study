package org.example.asm.print;

import java.io.IOException;
import java.io.PrintWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

/**
 * @author qinfengsa
 * @date 2022/06/27 11:12
 */
public class AsmPrintDemo {

    public static void main(String[] args) throws IOException {
        String className = "org.example.asm.sample.HelloWorldDemo";
        int parsingOptions = ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG;
        boolean asmCode = true;

        // (2) 打印结果
        System.out.println("ASM 生成class指令");
        Printer printer = new ASMifier();
        PrintWriter printWriter = new PrintWriter(System.out, true);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, printWriter);
        new ClassReader(className).accept(traceClassVisitor, parsingOptions);
        System.out.println("字节码");
        printer = new Textifier();

        traceClassVisitor = new TraceClassVisitor(null, printer, printWriter);
        new ClassReader(className).accept(traceClassVisitor, parsingOptions);
    }
}
