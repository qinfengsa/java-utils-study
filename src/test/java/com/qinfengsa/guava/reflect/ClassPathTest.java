package com.qinfengsa.guava.reflect;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * Guava 提供的类加载
 * @author qinfengsa
 * @date 2020/05/27 14:14
 */
@Slf4j
public class ClassPathTest {


    @Test
    public void testClassPath() {

        try {
            // 创建一个ClassPath对象
            ClassPath classpath = ClassPath.from(ClassPathTest.class.getClassLoader());
            // 返回从当前类路径可加载的所有资源，包括所有可加载类的类文件，但不包括“META-INF / MANIFEST.MF”文件
            /*ImmutableSet<ClassPath.ResourceInfo> resources = classpath.getResources();
            log.debug("resource :{}",resources);
            // 返回可加载的所有类
            ImmutableSet<ClassPath.ClassInfo> classInfos = classpath.getAllClasses();
            log.debug("class:{}",classInfos);
            // 返回所有的顶级类（不包括内部类）
            ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classpath.getTopLevelClasses();
            log.debug("topLevelClasses:{}",topLevelClasses);*/
            // 返回指定package下的所有顶级类
            ImmutableSet<ClassPath.ClassInfo> topLevelClass2 = classpath.getTopLevelClasses("com.qinfengsa.guava.service");
            log.debug("topLevelClass2:{}",topLevelClass2);
            // 返回所有指定package以及子package下所有的顶级类
            ImmutableSet<ClassPath.ClassInfo> topLevelClass3 = classpath.getTopLevelClassesRecursive("com.qinfengsa.guava.service");
            log.debug("topLevelClass3:{}",topLevelClass3);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
