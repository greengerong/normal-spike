package com.github.greengerong.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.Test;

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
public class AkkATest {
    @Test
    public void should_get_akka_actor_run() throws Exception {
        final ActorRef greet = ActorSystem.create("greet").actorOf(new Props(GreetActor.class));
        greet.tell("green");

    }
}
