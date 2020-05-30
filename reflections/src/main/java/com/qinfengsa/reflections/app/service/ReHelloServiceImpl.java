package com.qinfengsa.reflections.app.service;

import com.qinfengsa.api.annotation.Service;
import com.qinfengsa.api.service.HelloService;
import com.qinfengsa.reflections.app.annotation.MyAnnotation;
import com.qinfengsa.reflections.app.annotation.MyConstructorAnnotation;
import com.qinfengsa.reflections.app.annotation.MyFieldAnnotation;
import com.qinfengsa.reflections.app.annotation.MyMethodAnnotation;
import com.qinfengsa.reflections.app.annotation.MyParamAnnotation;
import com.qinfengsa.reflections.app.domain.ReUser;

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

    @Override
    @MyMethodAnnotation
    public String sayHello(@MyParamAnnotation String name) {
        return "test-type - >" + name;
    }


    public ReUser getUser() {
        return new ReUser(0,"name",11);
    }
}
