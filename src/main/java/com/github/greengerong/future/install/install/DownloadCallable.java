package com.github.greengerong.future.install.install;

import com.google.common.eventbus.EventBus;

import java.text.MessageFormat;
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
class DownloadCallable implements Callable<InstallHostModel> {
    private EventBus eventBus;
    private final InstallHostModel installHostModel;

    public DownloadCallable(EventBus eventBus, InstallHostModel installHostModel) {
        this.eventBus = eventBus;
        this.installHostModel = installHostModel;
    }

    @Override
    public InstallHostModel call() throws Exception {
        System.out.println("start download for control event  " + installHostModel.getHostIp());
        SleepUtils.randomSleep();
        if (SleepUtils.isSuccess()) {
            System.out.println(MessageFormat.format("finish download for control event  {0}", installHostModel.getHostIp()));
        } else {
            final String error = "Download error";
            eventBus.post(new RollBackModel(installHostModel, error));
            throw new RuntimeException(error);
        }
        return installHostModel;

    }
}
