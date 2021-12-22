/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_questions;

import java.io.Serializable;

/**
 *
 * @author Tam
 */
public class Tbl_questionsCreateError implements Serializable {
    private String noAnswerCorrect;
    private String duplicationQuestion;

    public Tbl_questionsCreateError() {
    }

    /**
     * @return the noAnswerCorrect
     */
    public String getNoAnswerCorrect() {
        return noAnswerCorrect;
    }

    /**
     * @param noAnswerCorrect the noAnswerCorrect to set
     */
    public void setNoAnswerCorrect(String noAnswerCorrect) {
        this.noAnswerCorrect = noAnswerCorrect;
    }

    /**
     * @return the duplicationQuestion
     */
    public String getDuplicationQuestion() {
        return duplicationQuestion;
    }

    /**
     * @param duplicationQuestion the duplicationQuestion to set
     */
    public void setDuplicationQuestion(String duplicationQuestion) {
        this.duplicationQuestion = duplicationQuestion;
    }

}
