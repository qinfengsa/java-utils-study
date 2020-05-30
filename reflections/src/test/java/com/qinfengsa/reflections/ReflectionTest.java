package com.qinfengsa.reflections;

import com.qinfengsa.api.annotation.Service;
import com.qinfengsa.api.domain.User;
import com.qinfengsa.api.service.HelloService;
import com.qinfengsa.reflections.app.annotation.MyAnnotation;
import com.qinfengsa.reflections.app.annotation.MyConstructorAnnotation;
import com.qinfengsa.reflections.app.annotation.MyFieldAnnotation;
import com.qinfengsa.reflections.app.annotation.MyMethodAnnotation;
import com.qinfengsa.reflections.app.annotation.MyParamAnnotation;
import com.qinfengsa.reflections.app.domain.ReUser;
import com.qinfengsa.reflections.app.service.ReHelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Reflection框架可以：
 * 获取某个类型的全部子类
 * 只要类型、构造器、方法，字段上带有特定注解，便能获取带有这个注解的全部信息（类型、构造器、方法，字段）
 * 获取所有能匹配某个正则表达式的资源
 * 获取所有带有特定签名的方法，包括参数，参数注解，返回类型
 * 获取所有方法的名字
 * 获取代码里所有字段、方法名、构造器的使用
 * @author qinfengsa
 * @date 2020/05/29 10:14
 */
@Slf4j
public class ReflectionTest {



    @Test
    public void test2() {
        // 扫包
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages("com.qinfengsa.reflections.app") // 指定路径URL
                .addScanners(new SubTypesScanner()) // 添加子类扫描工具
                .addScanners(new FieldAnnotationsScanner()) // 添加 属性注解扫描工具
                .addScanners(new MethodAnnotationsScanner() ) // 添加 方法注解扫描工具
                .addScanners(new MethodParameterScanner() ) // 添加方法参数扫描工具
        );

        // 反射出子类
        Set<Class<? extends HelloService>> set = reflections.getSubTypesOf(HelloService.class ) ;
        log.debug("subType:{}",set);

        // 反射出带有指定注解的类
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(MyAnnotation.class );
        log.debug("types:{}",types);

        // 获取带有特定注解对应的方法
        Set<Method> methods = reflections.getMethodsAnnotatedWith(MyMethodAnnotation.class ) ;
        log.debug("methods:{}",methods);

        // 获取带有特定注解对应的字段
        Set<Field> fields = reflections.getFieldsAnnotatedWith(MyFieldAnnotation.class ) ;
        log.debug("fields:{}",fields);

        // 获取特定参数对应的方法
        Set<Method> someMethods = reflections.getMethodsMatchParams(long.class, int.class);

        log.debug("someMethods:{}",someMethods);

        Set<Method> voidMethods = reflections.getMethodsReturn(void.class);
        System.out.println( "voidMethods:" + voidMethods);
        log.debug("voidMethods:{}",voidMethods);

        Set<Method> params = reflections.getMethodsWithAnyParamAnnotated( MyParamAnnotation.class);
        log.debug("params:{}",params);
    }

    /**
     * 获取所有InputStream的子类，限定只扫包前缀为“java”的，也就是jdk自带的
     */
    @Test
    public void testPackage(){
        Reflections  reflections = new Reflections("java.");

        Set<Class<? extends InputStream>> allTypes = reflections.getSubTypesOf(InputStream.class);
        for (Class<?> type : allTypes) {
            log.debug("Found: {}", type.getName());
        }
    }


    /**
     * com.qinfengsa 包下的所有带有service注解的类
     */
    @Test
    public void testAnnotationType(){
        Reflections  reflections = new Reflections("com.qinfengsa");

        Set<Class<?>> typeSet = reflections.getTypesAnnotatedWith(Service.class);

        log.debug("class :{}",typeSet);
    }


    /**
     * 扫描某个类的子类
     */
    @Test
    public void testSubType2(){
        Reflections reflections = new Reflections("com.qinfengsa");
        Set<Class<? extends HelloService>> classes = reflections.getSubTypesOf(HelloService.class);
        log.debug("class :{}",classes);
    }



    /**
     * 所有带 MyMethodAnnotation 注解的方法
     */
    @Test
    public void testAnnotationMethod(){
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage("com.qinfengsa"))
                        .addScanners(new MethodAnnotationsScanner()));

        Set<Method> methods = reflections.getMethodsAnnotatedWith(MyMethodAnnotation.class);
        log.debug("methods:{}",methods);
    }

    /**
     * 所有properties文件
     */
    @Test
    public void testResource(){
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage("com.qinfengsa"))
                        .addScanners(new ResourcesScanner()));

        Set<String> properties =
                reflections.getResources(Pattern.compile(".*\\.properties"));
        log.debug("properties:{}",properties);
    }

    /**
     * 所有带MyConstructorAnnotation注解的构造方法
     */
    @Test
    public void testAnnotationConstructor(){
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage("com.qinfengsa"))
                        .addScanners(new MethodAnnotationsScanner()));

        Set<Constructor> constructors = reflections.getConstructorsAnnotatedWith(MyConstructorAnnotation.class);

        log.debug("constructors : {}",constructors);

    }


    /**
     * 所有带MyFieldAnnotation注解的字段
     */
    @Test
    public void testAnnotationField(){
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage("com.qinfengsa"))
                        .addScanners(new FieldAnnotationsScanner()));

        Set<Field> fields = reflections.getFieldsAnnotatedWith(MyFieldAnnotation.class);
        log.debug("fields:{}",fields);

    }


    /**
     * MethodParameterScanner 方法参数扫描器
     */
    @Test
    public void testMethodParameter(){
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage("com.qinfengsa"))
                        .addScanners(new MethodParameterScanner()));

        // MethodParameterScanner
        Set<Method> someMethods =
                reflections.getMethodsMatchParams(String.class);
        Set<Method> userMethods =
                reflections.getMethodsReturn(ReUser.class);
        Set<Method> paramMethods =
                reflections.getMethodsWithAnyParamAnnotated(MyParamAnnotation.class);

        log.debug("参数符合String的方法:{}",someMethods);
        log.debug("返回值为ReUser的方法:{}",userMethods);
        log.debug("参数带有MyParamAnnotation注解的方法:{}",paramMethods);

    }


    // 测试框架自带的工具类
    @Test
    public void testReflectionUtils(){
        // 所有get方法
        Set<Method> getters = ReflectionUtils.getAllMethods(ReUser.class,
                ReflectionUtils.withModifier(Modifier.PUBLIC),
                ReflectionUtils.withPrefix("get"),
                ReflectionUtils.withParametersCount(0));

        log.debug("getters:{}",getters);

        // 参数是Collection的子类，返回值是boolean
        Set<Method> listMethodsFromCollectionToBoolean =
                ReflectionUtils.getAllMethods(List.class,
                        ReflectionUtils.withParametersAssignableTo(Collection.class),
                        ReflectionUtils.withReturnType(boolean.class));
        log.debug("listMethodsFromCollectionToBoolean :{}",listMethodsFromCollectionToBoolean);
        // field 带有MyFieldAnnotation注解，类型是String的子类
        Set<Field> fields = ReflectionUtils.getAllFields(ReHelloServiceImpl.class,
                ReflectionUtils.withAnnotation(MyFieldAnnotation.class),
                ReflectionUtils.withTypeAssignableTo(String.class));
        log.debug("fields :{}",fields);
    }
}
