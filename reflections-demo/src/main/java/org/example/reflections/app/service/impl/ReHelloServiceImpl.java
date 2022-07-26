package org.example.reflections.app.service.impl;

import org.example.reflections.app.annotation.MyAnnotation;
import org.example.reflections.app.annotation.MyConstructorAnnotation;
import org.example.reflections.app.annotation.MyFieldAnnotation;
import org.example.reflections.app.annotation.MyMethodAnnotation;
import org.example.reflections.app.annotation.MyParamAnnotation;
import org.example.reflections.app.annotation.Service;
import org.example.reflections.app.domain.User;
import org.example.reflections.app.service.HelloService;

/**
 * @author qinfengsa
 * @date 2020/05/30 21:07
 */
@MyAnnotation("test-type")
@Service("name2")
public class ReHelloServiceImpl implements HelloService {

    @MyFieldAnnotation
    private final String key;


    @MyConstructorAnnotation
    public ReHelloServiceImpl(String key) {
        this.key = key;
    }


    public String getKey() {
        return key;
    }

    @Override
    @MyMethodAnnotation
    public String sayHello(@MyParamAnnotation String name) {
        return "test-type - >" + name;
    }


    public User getUser() {
        return new User(0, "name", 11);
    }


    public int add(int a, int b) {
        return a + b;
    }
}
