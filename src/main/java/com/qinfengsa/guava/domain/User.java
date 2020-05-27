package com.qinfengsa.guava.domain;

import com.google.common.collect.ComparisonChain;
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
public class User implements Comparable<User> {

    private int id;

    private String name;



    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * Guava 提供了 ComparisonChain
     * @param that
     * @return
     */
    @Override
    public int compareTo(User that) {
        return ComparisonChain.start()
            .compare(this.id,that.id)
            .compare(this.age,that.age)
            .compare(this.name,that.name)
            .result();
    }

    /*@Override
    public String toString() {

    }*/
}
