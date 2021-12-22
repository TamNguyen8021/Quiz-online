/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamntt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tam
 */
public class DispatcherController extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String CREATE_NEW_ACCOUNT_SERVLET = "CreateNewAccountServlet";
    private final String SEARCH_QUESTION_SERVLET = "SearchQuestionServlet";
    private final String DELETE_QUESTION_SERVLET = "DeleteQuestionServlet";
    private final String UPDATE_QUESTION_SERVLET = "UpdateQuestionServlet";
    private final String CREATE_QUESTION_SERVLET = "CreateQuestionServlet";
    private final String DO_QUIZ_SERVLET = "DoQuizServlet";
    private final String FINISH_QUIZ_SERVLET = "FinishQuizServlet";
    private final String SHOW_HISTORY_SERVLET = "ShowHistoryServlet";

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

        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction");
        try {
            if (button == null) {

            } else if (button.equalsIgnoreCase("Sign In")) {
                url = LOGIN_SERVLET;
            } else if (button.equalsIgnoreCase("Log out")) {
                url = LOGOUT_SERVLET;
            } else if (button.equalsIgnoreCase(("Create Account"))) {
                url = CREATE_NEW_ACCOUNT_SERVLET;
            } else if (button.equalsIgnoreCase(("Search"))) {
                url = SEARCH_QUESTION_SERVLET;
            } else if (button.equalsIgnoreCase(("Delete"))) {
                url = DELETE_QUESTION_SERVLET;
            } else if (button.equalsIgnoreCase(("Update"))) {
                url = UPDATE_QUESTION_SERVLET;
            } else if (button.equalsIgnoreCase(("Create Question"))) {
                url = CREATE_QUESTION_SERVLET;
            } else if (button.equalsIgnoreCase("DoQuiz")) {
                url = DO_QUIZ_SERVLET;
            } else if (button.equalsIgnoreCase("Finish Quiz")) {
                url = FINISH_QUIZ_SERVLET;
            } else if (button.equalsIgnoreCase("Search history")) {
                url = SHOW_HISTORY_SERVLET;
            }
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
