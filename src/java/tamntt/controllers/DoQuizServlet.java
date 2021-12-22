/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
import tamntt.tbl_subjects.Tbl_subjectsDAO;
import tamntt.tbl_subjects.Tbl_subjectsDTO;

/**
 *
 * @author Tam
 */
public class DoQuizServlet extends HttpServlet {

    private final String DO_QUIZ_PAGE = "doQuiz.jsp";

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

        String url = DO_QUIZ_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String subjectID = request.getParameter("subjectID");
                Tbl_subjectsDAO subjectDao = new Tbl_subjectsDAO();
                Tbl_subjectsDTO dto = subjectDao.getSubjectInfo(subjectID);

                request.setAttribute("SUBJECT", subjectID);
                request.setAttribute("TOTAL_QUESTION", dto.getQuestion());

                if (session.getAttribute("TIME") == null) {
                    session.setAttribute("TIME", dto.getTime() * 60 * 1000); //convert to millisecs to use with JS in jsp
                } else {
                    if (request.getParameter("countdown") != null) {
                        session.setAttribute("TIME", request.getParameter("countdown"));
                    }
                }

                Tbl_questionsDAO questionDao = new Tbl_questionsDAO();
                int page = 1;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                int noOfRecords = dto.getQuestion(); //number of questions to take a quiz
                int rowsPerPage = 1;
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / rowsPerPage);
                
                if (session.getAttribute("QUESTION_RESULT") == null) {
                    questionDao.loadRandomQuestion(subjectID, noOfRecords);
                    Map<Tbl_questionsDTO, List<Tbl_answersDTO>> questions = questionDao.getListQuestions();
                    session.setAttribute("QUESTION_RESULT", questions);
                } //end if session does not have a list of questions         
                
                if (request.getParameter("selectedAnswer") != null) {
                    Map<Integer, Integer> selectedAnswersByPages = null;
                    if (session.getAttribute("SELECTED_ANSWER") == null) {
                        selectedAnswersByPages = new HashMap<>();
                    } else {
                        selectedAnswersByPages = (HashMap<Integer, Integer>) session.getAttribute("SELECTED_ANSWER");
                    }
                    int selectedAnswer = Integer.parseInt(request.getParameter("selectedAnswer"));
                    if (!selectedAnswersByPages.containsKey(page)) {
                        selectedAnswersByPages.put(page, selectedAnswer);
                    } else {
                        selectedAnswersByPages.replace(page, selectedAnswer);
                    }
                    session.setAttribute("SELECTED_ANSWER", selectedAnswersByPages);
                } //end if request parameter "selectedAnswer" is existed

                request.setAttribute("NO_OF_PAGES", noOfPages);
                request.setAttribute("CURRENT_PAGE", page);
            } //end if session is existed
        } catch (NamingException ex) {
            log("DoQuizServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("DoQuizServlet _ SQL: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("DoQuizServlet _ NumberFormat: " + ex.getMessage());
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
