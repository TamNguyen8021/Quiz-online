<%-- 
    Document   : showResult
    Created on : May 27, 2020, 11:10:36 AM
    Author     : Tam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Result</title>
        <link rel="stylesheet" href="css/show-result-style.css"/>
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
            <p class="left">Welcome, ${sessionScope.LASTNAME}</p>
            <form class="left" action="DispatcherController">
                <input type="submit" value="Log out" name="btAction" />
            </form> <br />

            <nav>
                <a href="quiz.jsp">Home</a>
            </nav>

            <c:set var="noOfCorrect" value="${requestScope.CORRECT_ANSWER}"/>           
            <c:set var="score" value="${requestScope.SCORE}"/>

            <c:if test="${not empty noOfCorrect && not empty score}">
                <h1>This is your result</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Total Correct Answers</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${noOfCorrect}</td>
                            <td>${score}</td>
                        </tr>
                    </tbody>
                </table>

            </c:if> <%-- end if student's score and no. of correct answers loaded successfully --%>

            <c:if test="${empty noOfCorrect || empty score}">
                <p>Some error occurred while processing...</p>
            </c:if>            
        </c:if> <%-- end if session is existed --%>

        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html" />        
        </c:if>       
    </body>
</html>
