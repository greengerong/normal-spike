package com.github.greengerong.dyloadjar;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

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
public class DyLoaderJar {
    public void load() throws Exception {
        final URL url = new URL("file:/Users/zxgerong/project/opensource/java/normal-spike/mutiple-module-demo/target/mutiple-module-demo-1.0-SNAPSHOT.jar");
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, classLoader);
        final Class<?> aClass = urlClassLoader.loadClass("com.github.greengerong.App");
        final Method main = aClass.getMethod("greet", String.class);
        final Object green = main.invoke(aClass.newInstance(), "green");
        System.out.println(green);
    }
}
