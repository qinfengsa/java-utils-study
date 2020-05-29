package com.qinfengsa.guava.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qinfengsa
 * @date 2020/05/27 16:26
 */
@Slf4j
public class JdkInvocationHandlerImpl<T> implements InvocationHandler {


    private T target;

    public JdkInvocationHandlerImpl(T target) {
        this.target = target;
    }

    public T getProxy() {
        Class<?> clazz = target.getClass();
        ClassLoader loader = clazz.getClassLoader();
        Class<?>[] interfaces = clazz.getInterfaces();
        return (T) Proxy.newProxyInstance(loader,interfaces,this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    private void before() {
        log.debug("开始执行:{}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    private void after() {
        log.debug("执行结算:{}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
