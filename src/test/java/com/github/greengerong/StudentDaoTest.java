package com.github.greengerong;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/******************************************
 * *
 * Auth: green gerong                     *
 * Date: 2016                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 ******************************************/
public class StudentDaoTest {

    private StudentDao studentDao;

    @Before
    public void setUp() throws Exception {
        studentDao = new StudentDao();
    }

    @Test
    public void should_say_hello() throws Exception {
        final String name = studentDao.say("joe");
        assertThat(name, is("hello joe"));



    }
}