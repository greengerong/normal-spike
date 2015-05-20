package com.github.greengerong;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2015                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class SpringIocTest {
    @Test
    public void should_spring_ioc() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.github.greengerong");
        final Class<?> requiredType = OrderController.class;
        final OrderController bean = (OrderController) context.getBean(requiredType);
        System.out.println(bean.add(1));
        this.getClass().getAnnotation(Controller.class);
    }

    @Test
    public void should_byte_serializable_java_bean() throws Exception {
        final String path = "target/" + this.getClass().getSimpleName();

        final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        final ConcurrentHashMap<String, CacheItem> map = new ConcurrentHashMap<String, CacheItem>();
        map.put("key", new CacheItem(10.11, new Date()));
        out.writeObject(map);
        out.flush();
        out.close();

        final ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        final ConcurrentHashMap<String, CacheItem> obj = (ConcurrentHashMap<String, CacheItem>) in.readObject();
        System.out.println(obj);
    }

}
