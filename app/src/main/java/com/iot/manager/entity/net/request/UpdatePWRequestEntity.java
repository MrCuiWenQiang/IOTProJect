package com.iot.manager.entity.net.request;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class UpdatePWRequestEntity {
    private String oldPassword;
    private String newPassword;
    private String passwordConfirm;

    public UpdatePWRequestEntity() {
    }

    public UpdatePWRequestEntity( String oldPassword,String newPassword, String passwordConfirm) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.passwordConfirm = passwordConfirm;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
