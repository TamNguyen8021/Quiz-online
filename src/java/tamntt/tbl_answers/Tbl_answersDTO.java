/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_answers;

import java.io.Serializable;

/**
 *
 * @author Tam
 */
public class Tbl_answersDTO implements Serializable {
    private int questionID;
    private int answerID;
    private String answerContent;
    private boolean answerCorrect;
    private String status;

    public Tbl_answersDTO() {
    }

    public Tbl_answersDTO(int questionID, int answerID, String answerContent, boolean answerCorrect) {
        this.questionID = questionID;
        this.answerID = answerID;
        this.answerContent = answerContent;
        this.answerCorrect = answerCorrect;
    }

    public Tbl_answersDTO(int questionID, int answerID, String answerContent, boolean answerCorrect, String status) {
        this.questionID = questionID;
        this.answerID = answerID;
        this.answerContent = answerContent;
        this.answerCorrect = answerCorrect;
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
     * @return the answerID
     */
    public int getAnswerID() {
        return answerID;
    }

    /**
     * @param answerID the answerID to set
     */
    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    /**
     * @return the answerContent
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * @param answerContent the answerContent to set
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * @return the answerCorrect
     */
    public boolean isAnswerCorrect() {
        return answerCorrect;
    }

    /**
     * @param answerCorrect the answerCorrect to set
     */
    public void setAnswerCorrect(boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
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
