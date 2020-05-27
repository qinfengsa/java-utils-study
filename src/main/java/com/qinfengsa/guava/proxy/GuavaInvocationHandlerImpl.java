package com.qinfengsa.guava.proxy;

import com.google.common.reflect.Reflection;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qinfengsa
 * @date 2020/05/27 16:40
 */
@Slf4j
public class GuavaInvocationHandlerImpl<T> implements InvocationHandler {

    /**
     * 目标对象对应的接口(因为一个对象可以实现多个接口，我们不知道是那个接口，所以传递进来)
     */
    private Class<T> targetInterface;
    /**
     * 目标对象
     */
    private T target;


    public GuavaInvocationHandlerImpl(Class<T> targetInterface, T target) {
        this.targetInterface = targetInterface;
        this.target = target;
    }

    public T getProxy() {
        return Reflection.newProxy(targetInterface,this);
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
