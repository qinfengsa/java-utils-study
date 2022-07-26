package org.example.guava.basic;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1. 基本工具 [Basic utilities]
 * <p>
 * 1.3 常见Object方法: 简化Object方法实现，如hashCode()和toString()
 *
 * @author qinfengsa
 * @date 2022/01/05 18:16
 */
public class ObjectMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMain.class);

    public static void main(String[] args) {
        equal();
        getHash();
    }

    /**
     * JAVA7 引入 Objects.equals API
     */
    public static void equal() {
        Long a = 1024L, b = 1024L;
        LOGGER.debug("result :{}", Objects.equal(a, b));
    }

    /**
     * JAVA7 引入 Objects.hash(Object...)
     */
    public static void getHash() {
        String a = "KS";
        int b = 12;
        LOGGER.debug("hash:{}", Objects.hashCode(a, b));
        LOGGER.debug("hash2:{}", java.util.Objects.hash(a, b));
    }

    static class User implements Comparable<User> {

        private int id;

        private String name;

        private int age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
        }

        /**
         * Guava 提供了 ComparisonChain
         *
         * @param that 比较对象
         * @return 比较结果
         */
        @Override
        public int compareTo(User that) {
            return ComparisonChain.start().compare(this.getId(), that.getId()).compare(this.getAge(), that.getAge())
                    .compare(this.getName(), that.getName()).result();
        }
    }

}
