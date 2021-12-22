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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tamntt.tbl_Accounts.Tbl_accountsCreateError;
import tamntt.tbl_Accounts.Tbl_accountsDAO;
import tamntt.tbl_Accounts.Tbl_accountsDTO;
import tamntt.utils.Hex;

/**
 *
 * @author Tam
 */
public class CreateNewAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String CREATE_SUCCESS_PAGE = "createSuccess.html";
    private final String EMAIL_REGEX = "^[\\w.-]+[A-Za-z0-9]+@[\\w.-]+\\.[a-z0-9]{2,4}$";
    private final String FULLNAME_REGEX = "^[A-Za-z]$";

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

        String url = ERROR_PAGE;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        Tbl_accountsCreateError errors = new Tbl_accountsCreateError();
        boolean foundErr = false;

        try {
            if (!email.matches(EMAIL_REGEX)) {
                foundErr = true;
                errors.setEmailWrongMatched("Email is in invalid format");
            }
            
            if (password.trim().length() < 6) {
                foundErr = true;
                errors.setPasswordLengthErr("Password is required inputted more than 5 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must matched password");
            }
            
            if (fullName.trim().length() < 2) {
                foundErr = true;
                errors.setFullNameLengthErr("Fullname is required inputted more than 1 character");
            }
            
            if (!fullName.matches(FULLNAME_REGEX)) {
                foundErr = true;
                errors.setFullNameWrongMatched("Fullname must contains alphabet characters only");
            }

            if (foundErr) {
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                String passwordEncoded = Hex.bytesToHex(hash);
                
                Tbl_accountsDTO dto = new Tbl_accountsDTO(email, fullName, passwordEncoded);
                Tbl_accountsDAO dao = new Tbl_accountsDAO();
                boolean result = dao.createAccount(dto);

                if (result) {
                    url = CREATE_SUCCESS_PAGE;
                } //end if create account successfully
            } //end if not found errors
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL: " + msg);
            if (msg.contains("duplicate")) {
                errors.setEmailIsExisted(email + " is existed!!!");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet _ Naming: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("CreateNewAccountServlet _ NoSuchAlgorithm: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
