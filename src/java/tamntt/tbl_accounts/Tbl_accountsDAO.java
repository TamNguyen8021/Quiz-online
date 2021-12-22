/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.tbl_Accounts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tamntt.tbl_roles.Tbl_roles;
import tamntt.tbl_status.Tbl_status;
import tamntt.utils.DBHelper;

/**
 *
 * @author Tam
 */
public class Tbl_accountsDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Email FROM tbl_accounts WHERE Email = ? AND Password = ? AND Status = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, Tbl_status.ACTIVE);
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

    public String getFullname(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String lastname = "";
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Name "
                        + "FROM tbl_accounts "
                        + "WHERE Email = ? AND Status = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, Tbl_status.ACTIVE);
                rs = stm.executeQuery();
                if (rs.next()) {
                    lastname = rs.getString("Name");
                } //end if con is not null
            }
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
        return lastname;
    }
    
    public String getRole(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String role = "";
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Role "
                        + "FROM tbl_accounts "
                        + "WHERE Email = ? AND Status = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, Tbl_status.ACTIVE);
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getString("Role");
                } //end if con is not null
            }
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
        return role;
    }

    public boolean createAccount(Tbl_accountsDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_accounts(Email, Name, Password, Role, Status) "
                        + "VALUES(?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getName());
                stm.setString(3, dto.getPassword());
                stm.setString(4, Tbl_roles.STUDENT);
                stm.setString(5, Tbl_status.NEW);
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

}
