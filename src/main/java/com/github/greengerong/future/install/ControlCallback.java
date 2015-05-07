package com.github.greengerong.future.install;

import com.google.common.eventbus.EventBus;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.Callable;

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
public class ControlCallback implements Callable<Boolean> {

    private final EventBus eventBus;
    private final List<InstallHostModel> hostModels;

    public ControlCallback(EventBus eventBus, List<InstallHostModel> hostModels) {
        this.eventBus = eventBus;
        this.hostModels = hostModels;
    }

    @Override
    public Boolean call() throws Exception {
        for (InstallHostModel hostModel : hostModels) {

            SleepUtils.randomSleep();
            if (SleepUtils.isSuccess()) {
                System.out.println(MessageFormat.format("finish control event  {0}", hostModel.getHostIp()));
                eventBus.post(hostModel);
            } else {
                System.out.println(MessageFormat.format("Control event  {0} error !", hostModel.getHostIp()));
                //you can post a error message to do some things is ok.
            }

        }
        return true;
    }

}
