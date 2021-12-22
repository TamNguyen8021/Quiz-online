/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_questions;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Tam
 */
public class Tbl_questionsDTO implements Serializable {
    private int questionID;
    private String questionContent;
    private Timestamp createDate;
    private String subjectID;
    private String status;

    public Tbl_questionsDTO() {
    }

    public Tbl_questionsDTO(int questionID, String questionContent) {
        this.questionID = questionID;
        this.questionContent = questionContent;
    }

    public Tbl_questionsDTO(String questionContent, Timestamp createDate, String subjectID) {
        this.questionContent = questionContent;
        this.createDate = createDate;
        this.subjectID = subjectID;
    }

    public Tbl_questionsDTO(int questionID, String questionContent, Timestamp createDate, String subjectID) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.createDate = createDate;
        this.subjectID = subjectID;
    }

    public Tbl_questionsDTO(int questionID, String questionContent, Timestamp createDate, String subjectID, String status) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }

    /**
     * @return the questionID
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * @param questionID the questionID to set
     */
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    /**
     * @return the questionContent
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * @param questionContent the questionContent to set
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    /**
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
