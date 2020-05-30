package com.qinfengsa.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author qinfengsa
 * @date 2020/05/24 21:24
 */
@Setter
@Getter
@ToString
public class User {

    private int id;

    private String name;

    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
