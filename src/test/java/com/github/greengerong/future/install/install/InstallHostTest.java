package com.github.greengerong.future.install.install;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

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
public class InstallHostTest {
    public static final int HOST_COUNT = 10;

    @Test
    public void should_Name() throws Exception {
        new InstallHost().install(createInstallHostModels());

    }

    private List<InstallHostModel> createInstallHostModels() {
        final ImmutableList.Builder<InstallHostModel> builder = new ImmutableList.Builder<InstallHostModel>();
        for (int i = 0; i < HOST_COUNT; i++) {
            builder.add(new InstallHostModel(String.format("196.198.0.%s", i)));
        }
        return builder.build();
    }

}