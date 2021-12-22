/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tamntt.tbl_history.Tbl_historyDAO;
import tamntt.tbl_history.Tbl_historyDTO;

/**
 *
 * @author Tam
 */
public class ShowHistoryServlet extends HttpServlet {

    private final String VIEW_HISTORY_PAGE = "viewHistory.jsp";
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
        
        String url = VIEW_HISTORY_PAGE;
        
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String searchValue = request.getParameter("txtSearchValue");
                if (searchValue != null || !searchValue.trim().isEmpty()) {
                    String email = String.valueOf(session.getAttribute("USERNAME"));
                    String role = String.valueOf(session.getAttribute("ROLE"));
                    Tbl_historyDAO historyDao = new Tbl_historyDAO();
                    
                    int page = 1;
                    if (request.getParameter("page") != null) {
                        page = Integer.parseInt(request.getParameter("page"));
                    }
                    int noOfRecords = 0;
                    if (role.equalsIgnoreCase("Student")) {
                        noOfRecords = historyDao.getAllHistoryRecordsForStudent(searchValue, email);
                    } else {
                        noOfRecords = historyDao.getAllHistoryRecordsForAdmin(searchValue);
                    }
                            
                    int rowsPerPage = 20;
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / rowsPerPage);
                    
                    if (role.equalsIgnoreCase("Student")) {
                        historyDao.searchHistoryByEmail(searchValue, email, page - 1, rowsPerPage);
                    } else {
                        historyDao.searchAllHistory(searchValue, page - 1, rowsPerPage);
                    }
                    
                    List<Tbl_historyDTO> listHistory = historyDao.getListHistory();
                    
                    request.setAttribute("HISTORY", listHistory);
                    request.setAttribute("NO_OF_PAGES", noOfPages);
                    request.setAttribute("CURRENT_PAGE", page);
                } //end if searchValue is existed
            } //end if session is existed
        } catch (NamingException ex) {
            log(("ShowHistoryServlet _ Naming: " + ex.getMessage()));
        } catch (SQLException ex) {
            log(("ShowHistoryServlet _ SQL: " + ex.getMessage()));
        } catch (NumberFormatException ex) {
            log(("ShowHistoryServlet _ NumberFormat: " + ex.getMessage()));
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
