package org.example.asm.sample;

/**
 * @author wangheng
 * @date 2022/06/27 11:10
 */
public class HelloWorldDemo {

    public int intValue = 0;

    public String strValue = "delete"; // 删除这个字段

    static {
        System.out.println("class initialization method");
    }

    public void test() {
        System.out.println("Test HelloWorld");
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public int add(int a, int b) { // 删除add方法
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

}
