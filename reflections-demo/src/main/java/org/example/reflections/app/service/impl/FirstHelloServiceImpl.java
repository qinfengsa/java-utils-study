package org.example.reflections.app.service.impl;
 
import java.util.List;
import org.example.reflections.app.service.HelloService;

/**
 * @author qinfengsa
 * @date 2020/05/30 21:38
 */
public class FirstHelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "first > " + name;
    }

    public boolean isEmpty(List<String> list) {
        return list.isEmpty();
    }
}
