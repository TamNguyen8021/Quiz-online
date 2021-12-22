/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_history;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tamntt.utils.DBHelper;

/**
 *
 * @author Tam
 */
public class Tbl_historyDAO implements Serializable {
    
    List<Tbl_historyDTO> listHistory;

    public List<Tbl_historyDTO> getListHistory() {
        return listHistory;
    }
    
    public boolean addHistory(Tbl_historyDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_history(Email, SubjectID, Correct_answer, Total_question, Score, DoTime) "
                        + "VALUES(?, ?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getSubjectID());
                stm.setInt(3, dto.getNoOfCorrectAnswer());
                stm.setInt(4, dto.getNoOfQuestion());
                stm.setFloat(5, dto.getScore());
                stm.setTimestamp(6, dto.getDoTime());
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
    
    public void searchHistoryByEmail(String searchValue, String email, int offset, int rowsPerPage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT SubjectID,  Correct_answer, Total_question, Score, DoTime "
                        + "FROM tbl_history "
                        + "WHERE SubjectID LIKE ? AND Email = ? "
                        + "ORDER BY DoTime "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, email);
                stm.setInt(3, offset * rowsPerPage);
                stm.setInt(4, rowsPerPage);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String subjectID = rs.getString("SubjectID");
                    int noOfCorrectAnswer = rs.getInt("Correct_answer");
                    int totalQuestion = rs.getInt("Total_question");
                    float score = rs.getFloat("Score");
                    Timestamp doTime = rs.getTimestamp("DoTime");

                    Tbl_historyDTO dto = new Tbl_historyDTO(email, subjectID, noOfCorrectAnswer, totalQuestion, score, doTime);
                    if (this.listHistory == null) {
                        this.listHistory = new ArrayList<>();
                        System.out.println("Create list");
                    } //end if listHistory is null

                    this.listHistory.add(dto);
                    System.out.println(listHistory.size());
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
    
    public void searchAllHistory(String searchValue, int offset, int rowsPerPage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, SubjectID,  Correct_answer, Total_question, Score, DoTime "
                        + "FROM tbl_history "
                        + "WHERE SubjectID LIKE ? "
                        + "ORDER BY DoTime "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, offset * rowsPerPage);
                stm.setInt(3, rowsPerPage);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String subjectID = rs.getString("SubjectID");
                    int noOfCorrectAnswer = rs.getInt("Correct_answer");
                    int totalQuestion = rs.getInt("Total_question");
                    float score = rs.getFloat("Score");
                    Timestamp doTime = rs.getTimestamp("DoTime");

                    Tbl_historyDTO dto = new Tbl_historyDTO(email, subjectID, noOfCorrectAnswer, totalQuestion, score, doTime);
                    if (this.listHistory == null) {
                        this.listHistory = new ArrayList<>();
                    } //end if listHistory is null

                    this.listHistory.add(dto);
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
    
    //used for pagination when search history
    public int getAllHistoryRecordsForStudent(String searchValue, String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT DoTime "
                        + "FROM tbl_history "
                        + "WHERE SubjectID LIKE ? AND Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    rows++;
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
        return rows;
    }
    
    public int getAllHistoryRecordsForAdmin(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT DoTime "
                        + "FROM tbl_history "
                        + "WHERE SubjectID LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    rows++;
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
        return rows;
    }
}
