/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_questions;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tamntt.tbl_answers.Tbl_answersDAO;
import tamntt.tbl_answers.Tbl_answersDTO;
import tamntt.tbl_status.Tbl_status;
import tamntt.utils.DBHelper;

/**
 *
 * @author Tam
 */
public class Tbl_questionsDAO implements Serializable {

    private Map<Tbl_questionsDTO, List<Tbl_answersDTO>> listQuestions;

    public Map<Tbl_questionsDTO, List<Tbl_answersDTO>> getListQuestions() {
        return listQuestions;
    }

    //get total rows of search questions, used for pagination
    public int getNoOfQuestions(String searchValue, String status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT QuestionID "
                        + "FROM tbl_questions "
                        + "WHERE Question_content LIKE ? AND Status = ? OR SubjectID LIKE ? AND Status = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, status);
                stm.setString(3, "%" + searchValue + "%");
                stm.setString(4, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    count++;
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
        return count;
    }

    //used to create answers for a question
    public int getQuestionID(String questionContent) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int questionID = -1;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT QuestionID "
                        + "FROM tbl_questions "
                        + "WHERE Question_content = ? AND Status = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionContent);
                stm.setString(2, Tbl_status.ACTIVE);
                rs = stm.executeQuery();
                if (rs.next()) {
                    questionID = rs.getInt("QuestionID");
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
        return questionID;
    }

    //search questions according to status
    public void searchQuestion(String searchValue, String status, int offset, int rowsPerPage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT QuestionID, Question_content, CreateDate, SubjectID "
                        + "FROM tbl_questions "
                        + "WHERE Question_content LIKE ? AND Status = ? OR SubjectID LIKE ? AND Status = ? "
                        + "ORDER BY Question_content "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, status);
                stm.setString(3, "%" + searchValue + "%");
                stm.setString(4, status);
                stm.setInt(5, rowsPerPage * offset);
                stm.setInt(6, rowsPerPage);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int questionID = rs.getInt("QuestionID");
                    String questionContent = rs.getString("Question_content");
                    Timestamp createDate = rs.getTimestamp("CreateDate");
                    String subjectID = rs.getString("SubjectID");

                    Tbl_questionsDTO dto = new Tbl_questionsDTO(questionID, questionContent, createDate, subjectID);
                    if (this.listQuestions == null) {
                        this.listQuestions = new LinkedHashMap<>();
                    } //end if listQuestions is null

                    Tbl_answersDAO dao = new Tbl_answersDAO();
                    dao.getAnswer(questionID, status);
                    this.listQuestions.put(dto, dao.getListAnswers());
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

    //used when create a question
    public boolean searchDuplicateQuestion(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT QuestionID "
                        + "FROM tbl_questions "
                        + "WHERE Question_content = ? AND Status = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, searchValue);
                stm.setString(2, Tbl_status.ACTIVE);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
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

    public boolean deleteQuestion(int questionID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_questions SET Status = ? WHERE QuestionID = ?";
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

    public boolean updateQuestion(int questionID, String subjectID, String questionContent) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_questions "
                        + "SET SubjectID = ?, Question_content = ? "
                        + "WHERE QuestionID = ? AND Status = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, subjectID);
                stm.setString(2, questionContent);
                stm.setInt(3, questionID);
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

    public boolean createQuestion(Tbl_questionsDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_questions(Question_content, CreateDate, SubjectID, Status) "
                        + "VALUES(?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getQuestionContent());
                stm.setTimestamp(2, dto.getCreateDate());
                stm.setString(3, dto.getSubjectID());
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

    //used to generate quiz
    public void loadRandomQuestion(String subjectID, int totalQuestion) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP(?) QuestionID, Question_content "
                        + "FROM tbl_questions "
                        + "WHERE SubjectID = ? AND Status = ? "
                        + "ORDER BY NEWID()";
                stm = con.prepareStatement(sql);
                stm.setInt(1, totalQuestion);
                stm.setString(2, subjectID);
                stm.setString(3, Tbl_status.ACTIVE);               
                rs = stm.executeQuery();
                while (rs.next()) {
                    int questionID = rs.getInt("QuestionID");
                    String questionContent = rs.getString("Question_content");

                    if (this.listQuestions == null) {
                        this.listQuestions = new LinkedHashMap<>();
                    } //end if student list is null

                    Tbl_questionsDTO dto = new Tbl_questionsDTO(questionID, questionContent);

                    Tbl_answersDAO dao = new Tbl_answersDAO();
                    dao.getAnswer(questionID, Tbl_status.ACTIVE);
                    this.listQuestions.put(dto, dao.getListAnswers());
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
}
