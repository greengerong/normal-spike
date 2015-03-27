package com.github.greengerong;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void should_Name() throws Exception {
        QueryRunner run = new QueryRunner();
        final List<Object> query = run.query("", new ResultSetHandler<List<Object>>() {
            @Override
            public List<Object> handle(ResultSet rs) throws SQLException {
                final ResultSetMetaData metaData = rs.getMetaData();

                List<String> times = new ArrayList<String>();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    times.add(metaData.getColumnName(i));
                }
                return null;
            }
        });

    }
}
