package com.qinfengsa.reflections.app.service;

import com.qinfengsa.api.service.HelloService;

import java.util.List;

/**
 * @author wangheng
 * @date 2020/05/30 21:38
 */
public class FirstHelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "first > " + name;
    }


    public boolean  isEmpty(List<String> list) {
        return list.isEmpty();
    }
}
