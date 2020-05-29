package com.qinfengsa.guava.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qinfengsa
 * @date 2020/05/27 15:48
 */
@Slf4j
public class HelloServiceImpl implements HelloService {

    public HelloServiceImpl() {

    }

    @Override
    public String sayHello(String name) {
        String result = " Hello! -> " + name;
        print(result);
        return result;
    }


    private void print(String result) {
        log.debug(result);
    }
}
