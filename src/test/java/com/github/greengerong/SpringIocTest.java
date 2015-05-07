package com.github.greengerong;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

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


}
