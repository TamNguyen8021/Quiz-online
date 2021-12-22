/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_subjects;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tamntt.utils.DBHelper;

/**
 *
 * @author Tam
 */
public class Tbl_subjectsDAO implements Serializable {
    
    private List<Tbl_subjectsDTO> subjectList;

    public List<Tbl_subjectsDTO> getSubjectList() {
        return subjectList;
    }
    
    //used to display subject for quiz page, search page and create question page
    public void loadSubjectFromDB() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT SubjectID, SubjectName, Question_number, Time "
                        + "FROM tbl_subjects";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String subjectID = rs.getString("SubjectID");
                    String subjectName = rs.getString("SubjectName");
                    int question = rs.getInt("Question_number");
                    int time = rs.getInt("Time");

                    Tbl_subjectsDTO dto = new Tbl_subjectsDTO(subjectID, subjectName, question, time);
                    if (this.subjectList == null) {
                        this.subjectList = new ArrayList<>();
                    } //end if student list is null
                    this.subjectList.add(dto);
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
    
    //used to generate quiz for a subject
    public Tbl_subjectsDTO getSubjectInfo(String subjectID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Tbl_subjectsDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT SubjectName, Question_number, Time "
                        + "FROM tbl_subjects "
                        + "WHERE SubjectID = ?";                
                stm = con.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String subjectName = rs.getString("SubjectName");
                    int totalQuestion = rs.getInt("Question_number");
                    int time = rs.getInt("Time");
                    dto = new Tbl_subjectsDTO(subjectID, subjectName, totalQuestion, time);
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
        return dto;
    }
}
