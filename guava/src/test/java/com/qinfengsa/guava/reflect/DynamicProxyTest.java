package com.qinfengsa.guava.reflect;

import com.qinfengsa.api.service.HelloService;
import com.qinfengsa.api.service.HelloServiceImpl;
import com.qinfengsa.guava.proxy.GuavaInvocationHandlerImpl;
import com.qinfengsa.guava.proxy.JdkInvocationHandlerImpl;
import com.qinfengsa.guava.proxy.GuavaNewInvocationHandlerImpl;
import org.junit.Test;

/**
 * Guava为了方便大家很好的处理动态代理，给大家做了两件事：简化生成代理对象的生成、提供了AbstractInvocationHandler
 * @author qinfengsa
 * @date 2020/05/27 14:14
 */
public class DynamicProxyTest {


    /**
     * JDK 动态代理
     */
    @Test
    public void testJdKProxy() {
        HelloService helloService =  new JdkInvocationHandlerImpl<HelloService>(new HelloServiceImpl()).getProxy();
        helloService.sayHello("win");
    }


    /**
     * Guava 动态代理
     */
    @Test
    public void testGuavaDynamicProxy() {
        /// Reflection.newProxy(HelloService.class,);
        HelloService helloService =  new GuavaInvocationHandlerImpl<>(HelloService.class,
                new HelloServiceImpl()).getProxy();
        helloService.sayHello("win");
    }


    /**
     * Guava 动态代理 AbstractInvocationHandler 3个功能
     *
     * 一个代理实例equal另外一个代理实例，只要他们有同样的接口类型和equal的invocation handlers。
     *
     * 一个代理实例的toString()会被代理到invocation handler的toString()，这样更容易自定义toString()。
     *
     * AbstractInvocationHandler确保传递给handleInvocation(Object, Method, Object[]))的参数数组永远不会空，从而减少了空指针异常的机会。
     *
     */
    @Test
    public void testGuavaNewDynamicProxy() {
        HelloService helloService =  new GuavaNewInvocationHandlerImpl<>(HelloService.class,
                new HelloServiceImpl()).getProxy();
        helloService.sayHello("w");
    }
}
