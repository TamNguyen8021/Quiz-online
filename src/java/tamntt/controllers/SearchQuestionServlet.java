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
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tamntt.tbl_answers.Tbl_answersDTO;
import tamntt.tbl_questions.Tbl_questionsDAO;
import tamntt.tbl_questions.Tbl_questionsDTO;

/**
 *
 * @author Tam
 */
public class SearchQuestionServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";

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

        String url = SEARCH_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String searchValue = request.getParameter("txtSearchValue");

                if (searchValue != null || !searchValue.trim().isEmpty()) {
                    String selectedStatus = request.getParameter("selectedStatus");

                    Tbl_questionsDAO dao = new Tbl_questionsDAO();
                    int page = 1;
                    if (request.getParameter("page") != null) {
                        page = Integer.parseInt(request.getParameter("page"));
                    }
                    int noOfRecords = dao.getNoOfQuestions(searchValue, selectedStatus); //get total number of questions
                    int rowsPerPage = 20;
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / rowsPerPage);
                    dao.searchQuestion(searchValue, selectedStatus, page - 1, rowsPerPage);

                    Map<Tbl_questionsDTO, List<Tbl_answersDTO>> questions = dao.getListQuestions();

                    request.setAttribute("QUESTION_RESULT", questions);
                    request.setAttribute("NO_OF_PAGES", noOfPages);
                    request.setAttribute("CURRENT_PAGE", page);
                } //end if searchValue is not empty
            } //end if session is existed
        } catch (NamingException ex) {
            log("SearchQuestionServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("SearchQuestionServlet _ SQL: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("SearchQuestionServlet _ NumberFormat: " + ex.getMessage());
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
