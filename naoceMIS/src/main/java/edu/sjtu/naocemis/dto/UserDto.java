package edu.sjtu.naocemis.dto;

import java.io.Serializable;

/**
 * @author alan_chen
 * @date 2019/8/1
 */
public class UserDto implements Serializable {
    private String applyer;
    private String applyerAccount;

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }


    public String getApplyerAccount() {
        return applyerAccount;
    }

    public void setApplyerAccount(String applyerAccount) {
        this.applyerAccount = applyerAccount;
    }
}
