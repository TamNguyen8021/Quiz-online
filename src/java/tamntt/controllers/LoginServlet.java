/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tamntt.tbl_Accounts.Tbl_accountsDAO;
import tamntt.tbl_roles.Tbl_roles;
import tamntt.tbl_status.Tbl_statusDAO;
import tamntt.tbl_subjects.Tbl_subjectsDAO;
import tamntt.utils.Hex;

/**
 *
 * @author Tam
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String QUIZ_PAGE = "quiz.jsp";
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = INVALID_PAGE;
        try {
            String id = request.getParameter("txtID");
            String password = request.getParameter("txtPassword");

            Tbl_accountsDAO accountDao = new Tbl_accountsDAO();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String passwordEncoded = Hex.bytesToHex(hash);
            boolean result = accountDao.checkLogin(id, passwordEncoded);

            if (result) {
                HttpSession session = request.getSession();
                String role = accountDao.getRole(id);
                
                if (role.equalsIgnoreCase(Tbl_roles.STUDENT)) {
                    url = QUIZ_PAGE;
                } else {
                    Tbl_statusDAO statusDao = new Tbl_statusDAO();
                    statusDao.loadStatusFromDB();
                    session.setAttribute("STATUS", statusDao.getListStatus());
                    
                    url = SEARCH_PAGE;
                }
                
                String lastname = accountDao.getFullname(id);
                session.setAttribute("LASTNAME", lastname);
                session.setAttribute("USERNAME", id);
                session.setAttribute("ROLE", role);
                
                Tbl_subjectsDAO subjectDao = new Tbl_subjectsDAO();
                subjectDao.loadSubjectFromDB();
                session.setAttribute("SUBJECT", subjectDao.getSubjectList());
            } //end if user is existed
        } catch (SQLException ex) {
            log("LoginServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet _ Naming: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("LoginServlet _ NoSuchAlgorithm: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
