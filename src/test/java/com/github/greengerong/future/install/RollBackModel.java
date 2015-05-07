package com.github.greengerong.future.install;

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
public class RollBackModel {
    private InstallHostModel installHostModel;
    private String reason;

    public RollBackModel(InstallHostModel installHostModel, String reason) {
        this.installHostModel = installHostModel;
        this.reason = reason;
    }

    public InstallHostModel getInstallHostModel() {
        return installHostModel;
    }

    public String getReason() {
        return reason;
    }
}
