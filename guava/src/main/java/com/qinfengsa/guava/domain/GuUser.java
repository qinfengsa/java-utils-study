package com.qinfengsa.guava.domain;

import com.google.common.collect.ComparisonChain;
import com.qinfengsa.api.domain.User;

/**
 * @author qinfengsa
 * @date 2020/05/29 10:35
 */
public class GuUser extends User implements Comparable<GuUser> {
    public GuUser(int id, String name, int age) {
        super(id, name, age);
    }

    /**
     * Guava 提供了 ComparisonChain
     * @param that
     * @return
     */
    @Override
    public int compareTo(GuUser that) {
        return ComparisonChain.start()
                .compare(this.getId(),that.getId())
                .compare(this.getAge(),that.getAge())
                .compare(this.getName(),that.getName())
                .result();
    }
}
