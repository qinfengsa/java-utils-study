package org.example.reflections.app.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.example.reflections.app.annotation.MyFieldAnnotation;
import org.example.reflections.app.annotation.MyMethodAnnotation;
import org.example.reflections.app.annotation.MyParamAnnotation;
import org.example.reflections.app.annotation.Service;
import org.example.reflections.app.domain.User;
import org.example.reflections.app.service.HelloService;
import org.example.reflections.app.service.impl.ReHelloServiceImpl;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reflection框架可以：
 * 1. 获取某个类型的全部子类 只要类型、构造器、方法，字段上带有特定注解，便能获取带有这个注解的全部信息（类型、构造器、方法，字段）
 * 2. 获取所有能匹配某个正则表达式的资源
 * 3. 获取所有带有特定签名的方法，包括参数，参数注解，返回类型
 * 4. 获取所有方法的名字
 * 5. 获取代码里所有字段、方法名、构造器的使用
 * @author qinfengsa
 * @date 2022/06/10 16:44
 */
public class ReflectionsMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionsMain.class);

    public static void main(String[] args) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        // 包路径
                        .forPackage("org.example.reflections.app")
                        .addScanners(Scanners.SubTypes,    // 子类扫描
                                Scanners.TypesAnnotated,   // class注解 扫描
                                Scanners.FieldsAnnotated,  // 属性注解 扫描
                                Scanners.MethodsAnnotated, // 方法注解 扫描
                                Scanners.MethodsParameter, // 方法参数注解 扫描
                                Scanners.MethodsSignature, // 方法签名 扫描
                                Scanners.MethodsReturn)    // 方法返回 扫描
        );
        // 查询 HelloService 的子类
        Set<Class<? extends HelloService>> classSet = reflections.getSubTypesOf(HelloService.class);
        LOGGER.info("HelloService子类：{}", classSet);
        // 查询 带指定注解的 class
        Set<Class<?>> annotatedSet = reflections.getTypesAnnotatedWith(Service.class);
        LOGGER.info("注解@Service的类：{}", annotatedSet);
        // 查询 带指定注解的 method
        Set<Method> methods = reflections.getMethodsAnnotatedWith(MyMethodAnnotation.class);
        LOGGER.info("注解@MyMethodAnnotation的方法：{}", methods);
        // 查询 特定参数对应的方法
        Set<Method> paramMethods = reflections.getMethodsWithParameter(MyParamAnnotation.class);
        LOGGER.info("参数注解@MyParamAnnotation的方法：{}", paramMethods);
        // 查询 带指定注解的 field
        Set<Field> fields = reflections.getFieldsAnnotatedWith(MyFieldAnnotation.class);
        LOGGER.info("注解@MyFieldAnnotation的属性：{}", fields);
        // 查询 特定参数类型的方法
        Set<Method> signMethods = reflections.getMethodsWithSignature(int.class, int.class);
        LOGGER.info("参数类型[int,int]的方法：{}", signMethods);
        // 指定返回类型的方法
        Set<Method> intReturnMethods = reflections.getMethodsReturn(int.class);
        LOGGER.info("返回类型[int]的方法：{}", intReturnMethods);


    }


    public void testReflectionUtils() {
        // 所有get方法
        Set<Method> getters =
                ReflectionUtils.getAllMethods(
                        User.class,
                        ReflectionUtils.withModifier(Modifier.PUBLIC),
                        ReflectionUtils.withPrefix("get"),
                        ReflectionUtils.withParametersCount(0));

        LOGGER.info("getters:{}", getters);

        // 参数是Collection的子类，返回值是boolean
        Set<Method> listMethodsFromCollectionToBoolean =
                ReflectionUtils.getAllMethods(
                        List.class,
                        ReflectionUtils.withParametersAssignableTo(Collection.class),
                        ReflectionUtils.withReturnType(boolean.class));
        LOGGER.info("listMethodsFromCollectionToBoolean :{}", listMethodsFromCollectionToBoolean);
        // field 带有MyFieldAnnotation注解，类型是String的子类
        Set<Field> fields =
                ReflectionUtils.getAllFields(
                        ReHelloServiceImpl.class,
                        ReflectionUtils.withAnnotation(MyFieldAnnotation.class),
                        ReflectionUtils.withTypeAssignableTo(String.class));
        LOGGER.debug("fields :{}", fields);
    }
}
