<%-- 
    Document   : createQuestion
    Created on : May 25, 2020, 9:37:23 AM
    Author     : Tam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question</title>
        <link rel="stylesheet" href="css/create-question-style.css"/>
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
            <p class="left">Welcome, ${sessionScope.LASTNAME}</p>
            <form class="left" action="DispatcherController">
                <input id="logout" type="submit" value="Log out" name="btAction" />
            </form> <br />         
            <nav>
                <a href="search.jsp">Search Question</a>
            </nav>


            <c:set var="listSubjects" value="${sessionScope.SUBJECT}"/>

            <c:if test="${not empty listSubjects}">
                <div>
                    <h1>Create Question</h1>
                    <form action="DispatcherController" method="POST">
                        <c:set var="success" value="${requestScope.SUCCESS}" />

                        <c:if test="${not empty success}">
                            <strong>${success}</strong> <br />
                        </c:if>

                        <c:set var="errors" value="${requestScope.CREATE_ERRORS}" />

                        <c:if test="${not empty errors.noAnswerCorrect}">
                            <font color="red">
                            ${errors.noAnswerCorrect}
                            </font><br />
                        </c:if>

                        <c:if test="${not empty errors.duplicationQuestion}">
                            <font color="red">
                            ${errors.duplicationQuestion}
                            </font><br />
                        </c:if>

                        <textarea name="txtQuestionContent" placeholder="Question*" required="required"></textarea> <br />
                        <textarea name="txtAnswer1" placeholder="Answer1*" required="required"></textarea>
                        <input type="radio" name="rdAnswerCorrect" value="answer1"/> <br />
                        <textarea name="txtAnswer2" placeholder="Answer2*" required="required"></textarea>
                        <input type="radio" name="rdAnswerCorrect" value="answer2"/> <br />
                        <textarea name="txtAnswer3" placeholder="Answer3*" required="required"></textarea>
                        <input type="radio" name="rdAnswerCorrect" value="answer3"/> <br />
                        <textarea name="txtAnswer4" placeholder="Answer4*" required="required"></textarea>
                        <input type="radio" name="rdAnswerCorrect" value="answer4"/> <br />
                        Subject*:
                        <select name="subjectID">
                            <c:forEach var="subject" items="${listSubjects}">
                                <option value="${subject.subjectID}">${subject.subjectID}</option>                                     
                            </c:forEach>                    
                        </select> <br />

                        <input class="custom-width" type="submit" value="Create Question" name="btAction" />
                        <input class="custom-width" type="reset" value="Reset" />
                    </form>
                </div>
            </c:if>

            <c:if test="${empty listSubjects}">
                <h1>Can't load subjects</h1>
                <a href="search.jsp">Click here to go back</a>
            </c:if>
        </c:if>

        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html" />
        </c:if>
    </body>
</html>
