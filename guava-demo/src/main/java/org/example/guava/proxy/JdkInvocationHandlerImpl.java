package org.example.guava.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qinfengsa
 * @date 2020/05/27 16:26
 */
public class JdkInvocationHandlerImpl<T> implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdkInvocationHandlerImpl.class);

    private T target;

    public JdkInvocationHandlerImpl(T target) {
        this.target = target;
    }

    public T getProxy() {
        Class<?> clazz = target.getClass();
        ClassLoader loader = clazz.getClassLoader();
        Class<?>[] interfaces = clazz.getInterfaces();
        return (T) Proxy.newProxyInstance(loader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        LOGGER.debug("开始执行:{}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    private void after() {
        LOGGER.debug("执行结算:{}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
