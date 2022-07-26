package org.example.asm.clazz;

import org.example.asm.sample.HelloWorldDemo;
import org.example.asm.sample.HelloWorldDemoChange;
import org.objectweb.asm.Opcodes;

/**
 * 为 class 添加 clone 接口
 *
 * @author wangheng
 * @date 2022/07/26 17:01
 */
public class ClassCloneRun {
    
    public static void main(String[] args) throws Exception {
        HelloWorldDemoChange.change(cw -> new ClassCloneVisitor(Opcodes.ASM9, cw));
    }


    static class HelloWorldRun {

        public static void main(String[] args) throws Exception {
            HelloWorldDemo demo = new HelloWorldDemo();
            Object cloneObj = demo.clone();
            System.out.println(cloneObj);
        }
    }
}
