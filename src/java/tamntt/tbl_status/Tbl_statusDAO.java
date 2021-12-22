/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_status;

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
public class Tbl_statusDAO implements Serializable {
    public List<Tbl_statusDTO> listStatus;

    public List<Tbl_statusDTO> getListStatus() {
        return listStatus;
    }
    
    //used to search questions by status
    public void loadStatusFromDB() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Status "
                        + "FROM tbl_status "
                        + "WHERE Status != ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, Tbl_status.NEW);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String status = rs.getString("Status");

                    Tbl_statusDTO dto = new Tbl_statusDTO(status);
                    if (this.listStatus == null) {
                        this.listStatus = new ArrayList<>();
                    } //end if student list is null
                    this.listStatus.add(dto);
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
