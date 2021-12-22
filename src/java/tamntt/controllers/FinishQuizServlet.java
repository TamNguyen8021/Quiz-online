/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tamntt.tbl_answers.Tbl_answersDAO;
import tamntt.tbl_history.Tbl_historyDAO;
import tamntt.tbl_history.Tbl_historyDTO;
import tamntt.tbl_subjects.Tbl_subjectsDAO;
import tamntt.tbl_subjects.Tbl_subjectsDTO;

/**
 *
 * @author Tam
 */
public class FinishQuizServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String SHOW_RESULT_PAGE = "showResult.jsp";

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

        int noOfCorrectAnswer = 0;
        float score = 0;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Map<Integer, Integer> listAnswers = (HashMap<Integer, Integer>) session.getAttribute("SELECTED_ANSWER");

                session.removeAttribute("QUESTION_RESULT"); //remove these attributes so when student take a new
                session.removeAttribute("SELECTED_ANSWER"); //quiz, all info will be refresh
                session.removeAttribute("TIME");

                String subjectID = request.getParameter("subjectID");
                if (listAnswers != null) {
                    if (request.getParameter("selectedAnswer") != null) {
                        int page = 1;
                        if (request.getParameter("page") != null) {
                            page = Integer.parseInt(request.getParameter("page"));
                        }
                        listAnswers.put(page, Integer.parseInt(request.getParameter("selectedAnswer")));
                    }
                    Tbl_answersDAO answerDao = new Tbl_answersDAO();
                    for (Integer answerID : listAnswers.values()) {
                        if (answerDao.checkCorrectAnswer(answerID)) {
                            noOfCorrectAnswer++;
                        }
                    } //end for each answer in listAnswers                  
                } //end if listAnswers is not null

                Tbl_subjectsDAO subjectDao = new Tbl_subjectsDAO();
                Tbl_subjectsDTO subjectDto = subjectDao.getSubjectInfo(subjectID);
                score = noOfCorrectAnswer * 1.0f / subjectDto.getQuestion() * 10;

                String email = String.valueOf(session.getAttribute("USERNAME"));
                Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                Tbl_historyDAO historyDao = new Tbl_historyDAO();
                Tbl_historyDTO historyDto = new Tbl_historyDTO(email, subjectID, noOfCorrectAnswer, subjectDto.getQuestion(), score, currentDate);
                boolean result = historyDao.addHistory(historyDto);

                if (result) {
                    url = SHOW_RESULT_PAGE;
                    request.setAttribute("CORRECT_ANSWER", noOfCorrectAnswer);
                    request.setAttribute("SCORE", score);
                } //end if add history to database successfully
            }
        } catch (NamingException ex) {
            log("FinishQuizServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("FinishQuizServlet _ SQL: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("FinishQuizServlet _ NumberFormat: " + ex.getMessage());
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
