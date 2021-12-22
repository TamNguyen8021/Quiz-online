/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_answers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tamntt.tbl_status.Tbl_status;
import tamntt.utils.DBHelper;

/**
 *
 * @author Tam
 */
public class Tbl_answersDAO implements Serializable {

    private List<Tbl_answersDTO> listAnswers;

    public List<Tbl_answersDTO> getListAnswers() {
        return listAnswers;
    }

    //used for search questions function
    public void getAnswer(int questionID, String status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT AnswerID, Answer_content, Answer_correct "
                        + "FROM tbl_answers "
                        + "WHERE QuestionID = ? AND Status = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, questionID);
                stm.setString(2, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int answerID = rs.getInt("AnswerID");
                    String answerContent = rs.getString("Answer_content");
                    boolean answerCorrect = rs.getBoolean("Answer_correct");

                    Tbl_answersDTO dto = new Tbl_answersDTO(questionID, answerID, answerContent, answerCorrect);
                    if (this.listAnswers == null) {
                        this.listAnswers = new ArrayList<>();
                    } //end if student list is null
                    this.listAnswers.add(dto);
                } //end while rs
            } //end if con is not null
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    //used for delete question function
    public boolean deleteAnswerOfQuestion(int questionID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_answers SET Status = ? WHERE QuestionID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, Tbl_status.DEACTIVE);
                stm.setInt(2, questionID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } //end if con is not null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    //used for update question function
    public boolean updateAnswer(int answerID, String answerContent, int answerCorrect) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_answers "
                        + "SET Answer_content = ?, Answer_correct = ? "
                        + "WHERE AnswerID = ? AND Status = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, answerContent);
                if (answerCorrect == answerID) {
                    stm.setBoolean(2, true);
                } else {
                    stm.setBoolean(2, false);
                }                
                stm.setInt(3, answerID);
                stm.setString(4, Tbl_status.ACTIVE);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } //end if con is not null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    //used for create question function
    public boolean createAnswer(int questionID, String answerContent, boolean answerCorrect) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_answers(QuestionID, Answer_content, Answer_correct, Status) "
                        + "VALUES(?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, questionID);
                stm.setString(2, answerContent);
                stm.setBoolean(3, answerCorrect);
                stm.setString(4, Tbl_status.ACTIVE);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } //end if con is not null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    //used to grade a student when finish quiz
    public boolean checkCorrectAnswer(int answerID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT AnswerID "
                        + "FROM tbl_answers "
                        + "WHERE AnswerID = ? AND Answer_correct = ? AND Status = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, answerID);
                stm.setBoolean(2, true);
                stm.setString(3, Tbl_status.ACTIVE);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                } //end if rs
            } //end if con is not null
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
