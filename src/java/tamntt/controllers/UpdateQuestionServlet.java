/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tamntt.tbl_answers.Tbl_answersDAO;
import tamntt.tbl_questions.Tbl_questionsDAO;

/**
 *
 * @author Tam
 */
public class UpdateQuestionServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";

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
        String questionContent = request.getParameter("txtQuestionContent");
        String answer1Content = request.getParameter("txtAnswer1Content");
        String answer2Content = request.getParameter("txtAnswer2Content");
        String answer3Content = request.getParameter("txtAnswer3Content");
        String answer4Content = request.getParameter("txtAnswer4Content");
        String searchValue = request.getParameter("lastSearchValue");
        String selectedStatus = request.getParameter("selectedStatus");
        int page = 1;

        try {
            if (!questionContent.trim().isEmpty() && !answer1Content.trim().isEmpty()
                    && !answer2Content.trim().isEmpty() && !answer3Content.trim().isEmpty()
                    && !answer4Content.trim().isEmpty()) {
                String subjectID = request.getParameter("subjectID");

                Map<Integer, String> answerOfQuestion = new LinkedHashMap<>();
                int questionID = Integer.parseInt(request.getParameter("txtQuestionID"));
                int answer1ID = Integer.parseInt(request.getParameter("answer1ID"));
                int answer2ID = Integer.parseInt(request.getParameter("answer2ID"));
                int answer3ID = Integer.parseInt(request.getParameter("answer3ID"));
                int answer4ID = Integer.parseInt(request.getParameter("answer4ID"));
                int answerCorrect = Integer.parseInt(request.getParameter("rdCorrect"));

                answerOfQuestion.put(answer1ID, answer1Content);
                answerOfQuestion.put(answer2ID, answer2Content);
                answerOfQuestion.put(answer3ID, answer3Content);
                answerOfQuestion.put(answer4ID, answer4Content);

                Tbl_questionsDAO questionDao = new Tbl_questionsDAO();
                questionDao.updateQuestion(questionID, subjectID, questionContent);

                Tbl_answersDAO answerDao = new Tbl_answersDAO();
                for (Integer id : answerOfQuestion.keySet()) {
                    answerDao.updateAnswer(id, answerOfQuestion.get(id), answerCorrect);
                }
            } //end if question and answers are not empty

            url = "DispatcherController"
                    + "?btAction=Search"
                    + "&txtSearchValue=" + searchValue
                    + "&selectedStatus=" + selectedStatus
                    + "&page=" + page;
        } catch (NamingException ex) {
            log("UpdateQuestionServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateQuestionServlet _ SQL: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("UpdateQuestionServlet _ NumberFormat: " + ex.getMessage());
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
