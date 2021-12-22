/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_history;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Tam
 */
public class Tbl_historyDTO implements Serializable {
    private int id;
    private String email;
    private String subjectID;
    private int noOfCorrectAnswer;
    private int noOfQuestion;
    private float score;
    private Timestamp doTime;

    public Tbl_historyDTO() {
    }

    public Tbl_historyDTO(String email, String subjectID, int noOfCorrectAnswer, int noOfQuestion, float score, Timestamp doTime) {
        this.email = email;
        this.subjectID = subjectID;
        this.noOfCorrectAnswer = noOfCorrectAnswer;
        this.noOfQuestion = noOfQuestion;
        this.score = score;
        this.doTime = doTime;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the noOfCorrectAnswer
     */
    public int getNoOfCorrectAnswer() {
        return noOfCorrectAnswer;
    }

    /**
     * @param noOfCorrectAnswer the noOfCorrectAnswer to set
     */
    public void setNoOfCorrectAnswer(int noOfCorrectAnswer) {
        this.noOfCorrectAnswer = noOfCorrectAnswer;
    }

    /**
     * @return the noOfQuestion
     */
    public int getNoOfQuestion() {
        return noOfQuestion;
    }

    /**
     * @param noOfQuestion the noOfQuestion to set
     */
    public void setNoOfQuestion(int noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

    /**
     * @return the doTime
     */
    public Timestamp getDoTime() {
        return doTime;
    }

    /**
     * @param doTime the doTime to set
     */
    public void setDoTime(Timestamp doTime) {
        this.doTime = doTime;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
