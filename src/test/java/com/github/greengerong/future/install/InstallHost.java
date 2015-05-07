package com.github.greengerong.future.install;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Executors;

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
public class InstallHost {

    private final ListeningExecutorService executorService;
    public static final int HOST_COUNT = 10;
    private List<ListenableFuture<InstallHostModel>> downloadFutures = Lists.newArrayList();
    private final EventBus eventBus;

    public InstallHost() {
        executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        eventBus = new AsyncEventBus(executorService);
    }

    @Test
    public void should_Name() throws Exception {
        install(createInstallHostModels());

    }

    public void install(List<InstallHostModel> installHostModels) throws Exception {

        eventBus.register(this);
        final ListenableFuture<Boolean> controlFuture = executorService.submit(new ControlCallback(eventBus, installHostModels));

        final ListenableFuture<List<InstallHostModel>> allDownloadFutures = Futures.transform(controlFuture, new AsyncFunction<Boolean, List<InstallHostModel>>() {
            @Override
            public ListenableFuture<List<InstallHostModel>> apply(Boolean input) throws Exception {
                return Futures.successfulAsList(downloadFutures);
            }
        });


        final ListenableFuture<List<InstallHostModel>> lastStep = Futures.transform(allDownloadFutures, new LastStepCallBack());
        System.out.println("Main Thread finish----------------");
        //
        lastStep.get();

    }

    private List<InstallHostModel> createInstallHostModels() {
        final ImmutableList.Builder<InstallHostModel> builder = new ImmutableList.Builder<InstallHostModel>();
        for (int i = 0; i < HOST_COUNT; i++) {
            builder.add(new InstallHostModel(String.format("196.198.0.%s", i)));
        }
        return builder.build();
    }

    @Subscribe
    public void controlFinish(final InstallHostModel installHostModel) {
        final ListenableFuture<InstallHostModel> future = executorService.submit(new DownloadCallable(eventBus, installHostModel));
        downloadFutures.add(future);
    }

    @Subscribe
    public void rollBack(RollBackModel rollBackModel) {
        System.out.println(rollBackModel.getInstallHostModel().getHostIp() + " error reason: " + rollBackModel.getReason());
        System.out.println(rollBackModel.getInstallHostModel().getHostIp() + " was roll back.");
    }

}
