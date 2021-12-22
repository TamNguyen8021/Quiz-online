<%-- 
    Document   : quiz
    Created on : May 20, 2020, 7:05:15 AM
    Author     : Tam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
        <link rel="stylesheet" href="css/quiz-style.css"/>
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
            <p class="left">Welcome, ${sessionScope.LASTNAME}</p>
            <form class="left" action="DispatcherController">
                <input type="submit" value="Log out" name="btAction" />
            </form> <br />
            <nav>
                <a href="viewHistory.jsp">View History</a>
            </nav>            
            
            <c:set var="subject" value="${sessionScope.SUBJECT}"/>

            <c:if test="${not empty subject}">
                <h1>List Subjects</h1>           
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Subject</th>
                            <th>Total Questions</th>
                            <th>Time</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="listSubject" items="${subject}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${listSubject.subjectID}</td>
                                <td>${listSubject.question}</td>
                                <td>${listSubject.time}</td>
                                <td>
                                    <a href="DispatcherController?btAction=DoQuiz&subjectID=${listSubject.subjectID}">Start quiz</a>
                                </td>
                            </tr>
                        </c:forEach> <%-- end for each element is listSubject --%>                           
                    
                </tbody>
            </table>           
            </c:if> <%-- end if subject is existed --%>

        <c:if test="${empty subject}">
            <h1>Can't load subjects. Please try again in a few minutes.</h1>
        </c:if>
    </c:if>

    <c:if test="${empty sessionScope}">
        <c:redirect url="login.html" />        
    </c:if>
</body>
</html>
