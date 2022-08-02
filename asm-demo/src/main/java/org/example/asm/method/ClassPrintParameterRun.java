package org.example.asm.method;

import org.example.asm.sample.HelloWorldDemo;
import org.example.asm.sample.HelloWorldDemoTransform;
import org.objectweb.asm.Opcodes;

/**
 * 打印方法的输入和输出参数
 *
 * @author wangheng
 * @date 2022/08/01 18:19
 */
public class ClassPrintParameterRun {

    public static void main(String[] args) throws Exception {
        HelloWorldDemoTransform.transform(cw -> new ClassPrintParameterVisitor(Opcodes.ASM9, cw));
        HelloWorldDemo demo = new HelloWorldDemo();

        int result = demo.add(1, 3);
        System.out.println("result:" + result);
    }
}
