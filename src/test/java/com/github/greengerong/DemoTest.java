package com.github.greengerong;

import java.util.concurrent.Future;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/******************************************
 * *
 * Auth: green gerong                     *
 * Date: 2016                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 ******************************************/
public class DemoTest {
    @Test
    public void testName1() throws Exception {
        Observable.from(Lists.newArrayList(1, 2, 3, 4))
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer.intValue() * 10;
                    }
                })
                .subscribeOn(Schedulers.io())
                .toBlocking()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });

        System.out.println("-----------");
    }

    @Test
    public void should_Name() throws Exception {
        Future<String> a = null;
        Observable.from(a)
                .toBlocking();

    }
}