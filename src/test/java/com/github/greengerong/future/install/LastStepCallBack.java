package com.github.greengerong.future.install;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.text.MessageFormat;
import java.util.List;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.FluentIterable.from;

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
public class LastStepCallBack implements AsyncFunction<List<InstallHostModel>, List<InstallHostModel>> {
    @Override
    public ListenableFuture<List<InstallHostModel>> apply(List<InstallHostModel> hostModels) throws Exception {
        System.out.println(String.format("All download done!"));
        final List<InstallHostModel> successHostModels = from(hostModels).filter(notNull()).toList();
        System.out.println(MessageFormat.format("Download success host ip is {0}", successHostModels));
        return Futures.immediateFuture(successHostModels);
    }

}
