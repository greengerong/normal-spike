package com.github.greengerong.akka;

import akka.actor.UntypedActor;

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
public class GreetActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof String) {
            System.out.println(o);
        } else {
            unhandled(o);
        }
    }
}
