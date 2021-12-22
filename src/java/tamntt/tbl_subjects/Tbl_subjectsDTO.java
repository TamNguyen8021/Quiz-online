/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_subjects;

import java.io.Serializable;

/**
 *
 * @author Tam
 */
public class Tbl_subjectsDTO implements Serializable {
    private String subjectID;
    private String subjectName;
    private int question;
    private int time;

    public Tbl_subjectsDTO() {
    }

    public Tbl_subjectsDTO(String subjectID, String subjectName, int question, int time) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.question = question;
        this.time = time;
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
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @return the question
     */
    public int getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(int question) {
        this.question = question;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }
}
