package com.qinfengsa.guava.reflect;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;
import com.qinfengsa.guava.service.HelloService;
import com.qinfengsa.guava.service.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * Guava的Invokable是对java.lang.reflect.Method和java.lang.reflect.Constructor的流式包装。它简化了常见的反射代码的使用
 * @author qinfengsa
 * @date 2020/05/27 14:13
 */
@Slf4j
public class InvokableTest {


    @Test
    public void testInvokable() {
        HelloService helloService = new HelloServiceImpl();
        Class<?> clazz = helloService.getClass();

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {


            // 把 method 封装成 Invokable
            Invokable methodInvokable = Invokable.from(method);

            methodInvokable.setAccessible(true);
            // 是否public
            log.debug("isPublic: {}",methodInvokable.isPublic());
            // getName() 方法名
            log.debug("方法名: {}",methodInvokable.getName());

            // getDeclaringClass() 获取定义该方法的类
            log.debug("定义该方法的类: {} ",methodInvokable.getDeclaringClass());
            // getOwnerType() 获取定义该方法的class的包装对象Type
            log.debug("定义该方法的类: {} ",methodInvokable.getOwnerType().getType());
            // isOverridable() 该方法是否可以重写
            log.debug("是否可以重写: {} ",methodInvokable.isOverridable());
            // isVarArgs() 该方法是否可变参数
            log.debug("是否可变参数: {} ",methodInvokable.isVarArgs());
            // getReturnType() 该方法返回值类型
            log.debug("返回值类型: {} " ,methodInvokable.getReturnType().getType());
            // getParameters() 获取参数
            ImmutableList<Parameter>  parameterList = methodInvokable.getParameters();
            for (int index = 0; index < parameterList.size(); index++) {
                log.debug("方法参数 {} : {}",index ,parameterList.get(index).getType());
            }
            // getExceptionTypes() 获取异常类
            ImmutableList<TypeToken> exceptionList = methodInvokable.getExceptionTypes();
            for (int index = 0; index < exceptionList.size(); index++) {
                log.debug("异常类 {} : {}", index ,exceptionList.get(index).getType());
            }
            // getAnnotatedReturnType()
            AnnotatedType annotatedType = methodInvokable.getAnnotatedReturnType();
            log.debug("annotatedType : {}" ,annotatedType.getType());
        }

    }
}
