package org.example.asm.clazz;

import org.example.asm.sample.HelloWorldDemoChange;
import org.objectweb.asm.Opcodes;

/**
 * 修改 class 版本
 * 验证 javap -p -v sample.HelloWorld
 *
 * @author wangheng
 * @date 2022/07/26 16:14
 */
public class ClassChangeVersionRun {
 
    public static void main(String[] args) throws Exception {
        HelloWorldDemoChange.change(cw -> new ClassChangeVersionVisitor(Opcodes.ASM9, cw));
    }
}
