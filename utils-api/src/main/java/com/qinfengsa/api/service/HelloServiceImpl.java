package com.qinfengsa.api.service;

import com.qinfengsa.api.annotation.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qinfengsa
 * @date 2020/05/27 15:48
 */
@Slf4j
@Service("test")
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
