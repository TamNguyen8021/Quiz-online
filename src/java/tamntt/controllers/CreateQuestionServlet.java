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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tamntt.tbl_answers.Tbl_answersDAO;
import tamntt.tbl_questions.Tbl_questionsCreateError;
import tamntt.tbl_questions.Tbl_questionsDAO;
import tamntt.tbl_questions.Tbl_questionsDTO;

/**
 *
 * @author Tam
 */
public class CreateQuestionServlet extends HttpServlet {

    private final String CREATE_QUESTION_PAGE = "createQuestion.jsp";

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

        String url = CREATE_QUESTION_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String questionContent = request.getParameter("txtQuestionContent");
                String answer1 = request.getParameter("txtAnswer1");
                String answer2 = request.getParameter("txtAnswer2");
                String answer3 = request.getParameter("txtAnswer3");
                String answer4 = request.getParameter("txtAnswer4");
                String answerCorrect = request.getParameter("rdAnswerCorrect");
                String subjectID = request.getParameter("subjectID");
                Tbl_questionsCreateError errors = new Tbl_questionsCreateError();
                boolean foundErr = false;

                if (answerCorrect == null) {
                    foundErr = true;
                    errors.setNoAnswerCorrect("There must be one correct answer");
                }
                
                Tbl_questionsDAO questionDao = new Tbl_questionsDAO();
                if (questionDao.searchDuplicateQuestion(questionContent)) {
                    foundErr = true;
                    errors.setDuplicationQuestion("Question is duplicate");
                }
                
                if (foundErr) {
                    request.setAttribute("CREATE_ERRORS", errors);
                } else {
                    Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                    Tbl_questionsDTO dto = new Tbl_questionsDTO(questionContent, currentDate, subjectID);
                    boolean result = questionDao.createQuestion(dto);

                    if (result) {
                        int questionID = questionDao.getQuestionID(questionContent);
                        Tbl_answersDAO answerDao = new Tbl_answersDAO();
                        if (answerCorrect.equalsIgnoreCase("answer1")) {
                            answerDao.createAnswer(questionID, answer1, true);
                        } else {
                            answerDao.createAnswer(questionID, answer1, false);
                        }
                        if (answerCorrect.equalsIgnoreCase("answer2")) {
                            answerDao.createAnswer(questionID, answer2, true);
                        } else {
                            answerDao.createAnswer(questionID, answer2, false);
                        }
                        if (answerCorrect.equalsIgnoreCase("answer3")) {
                            answerDao.createAnswer(questionID, answer3, true);
                        } else {
                            answerDao.createAnswer(questionID, answer3, false);
                        }
                        if (answerCorrect.equalsIgnoreCase("answer4")) {
                            answerDao.createAnswer(questionID, answer4, true);
                        } else {
                            answerDao.createAnswer(questionID, answer4, false);
                        }
                        request.setAttribute("SUCCESS", "Question created successfully");
                    } //end if create question successfully
                } //end if not found errors
            } //end if session is existed            
        } catch (NamingException ex) {
            log("CreateQuestionServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateQuestionServlet _ SQL: " + ex.getMessage());
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
