/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_Accounts;

import java.io.Serializable;

/**
 *
 * @author Tam
 */
public class Tbl_accountsCreateError implements Serializable {
    private String emailWrongMatched;
    private String passwordLengthErr;
    private String fullNameLengthErr;
    private String fullNameWrongMatched;
    private String confirmNotMatched;    
    private String emailIsExisted;

    /**
     * @return the emailWrongMatched
     */
    public String getEmailWrongMatched() {
        return emailWrongMatched;
    }

    /**
     * @param emailWrongMatched the emailWrongMatched to set
     */
    public void setEmailWrongMatched(String emailWrongMatched) {
        this.emailWrongMatched = emailWrongMatched;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    /**
     * @param confirmNotMatched the confirmNotMatched to set
     */
    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    /**
     * @return the emailIsExisted
     */
    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    /**
     * @param emailIsExisted the emailIsExisted to set
     */
    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    /**
     * @return the fullNameWrongMatched
     */
    public String getFullNameWrongMatched() {
        return fullNameWrongMatched;
    }

    /**
     * @param fullNameWrongMatched the fullNameWrongMatched to set
     */
    public void setFullNameWrongMatched(String fullNameWrongMatched) {
        this.fullNameWrongMatched = fullNameWrongMatched;
    }
}
