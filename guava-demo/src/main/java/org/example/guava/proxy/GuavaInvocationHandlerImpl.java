package org.example.guava.proxy;

import com.google.common.reflect.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qinfengsa
 * @date 2020/05/27 16:40
 */
public class GuavaInvocationHandlerImpl<T> implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaInvocationHandlerImpl.class);

    /** 目标对象对应的接口(因为一个对象可以实现多个接口，我们不知道是那个接口，所以传递进来) */
    private Class<T> targetInterface;
    /** 目标对象 */
    private T target;

    public GuavaInvocationHandlerImpl(Class<T> targetInterface, T target) {
        this.targetInterface = targetInterface;
        this.target = target;
    }

    public T getProxy() {
        return Reflection.newProxy(targetInterface, this);
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
