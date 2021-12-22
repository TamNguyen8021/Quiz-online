<%-- 
    Document   : viewHistory
    Created on : May 28, 2020, 2:00:23 PM
    Author     : Tam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View History</title>
        <link rel="stylesheet" href="css/view-history-style.css"/>
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
            <p class="left">Welcome, ${sessionScope.LASTNAME}</p>
            <form class="left" action="DispatcherController">
                <input type="submit" value="Log out" name="btAction" />
            </form> <br />
            <nav>
                <c:if test="${sessionScope.ROLE == 'Student'}">
                    <a class="change-style" href="quiz.jsp">Home</a>
                </c:if>
                <c:if test="${sessionScope.ROLE == 'Admin'}">
                    <a href="search.jsp">Search</a>
                    <a href="createQuestion.jsp">Create Question</a>
                </c:if>
            </nav>

            <h1>History</h1>
            <form action="DispatcherController" method="GET">
                <input type="search" name="txtSearchValue" value="${param.txtSearchValue}" placeholder="Search"/>
                <input type="submit" value="Search history" name="btAction" />
            </form><br />

            <c:set var="searchValue" value="${param.txtSearchValue}" />

            <c:if test="${not empty searchValue}">
                <c:set var="listHistory" value="${requestScope.HISTORY}" />

                <c:if test="${not empty listHistory}">                  
                    <table border="1">
                        <thead>
                            <tr>
                                <c:if test="${sessionScope.ROLE == 'Admin'}">
                                    <th>Email</th>
                                    </c:if>
                                <th>Subject</th>
                                <th>Total Correct Answers</th>
                                <th>Total Questions</th>
                                <th>Score</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="historyRecord" items="${listHistory}">
                                <tr>
                                    <c:if test="${sessionScope.ROLE == 'Admin'}">
                                        <td>
                                            ${historyRecord.email}
                                        </td>
                                    </c:if>
                                    <td>
                                        ${historyRecord.subjectID}
                                    </td>
                                    <td>
                                        ${historyRecord.noOfCorrectAnswer}
                                    </td>
                                    <td>
                                        ${historyRecord.noOfQuestion}
                                    </td>
                                    <td>
                                        ${historyRecord.score}
                                    </td>
                                    <td>
                                        <fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${historyRecord.doTime}"/>                                      
                                    </td>
                                </tr>
                            </c:forEach> <%-- end for each element is listHistory --%>
                        </tbody>
                    </table>

                    <c:if test="${requestScope.CURRENT_PAGE <= requestScope.NO_OF_PAGES && requestScope.NO_OF_PAGES > 1}">
                        <c:forEach var="page" begin="1" end="${requestScope.NO_OF_PAGES}" varStatus="counter">
                            <c:if test="${page == requestScope.CURRENT_PAGE}">
                                <b><a href="DispatcherController?btAction=Search history&txtSearchValue=${searchValue}&page=${counter.count}">${counter.count}</a></b>
                            </c:if>
                            <c:if test="${page != requestScope.CURRENT_PAGE}">
                                <a class="change-style" href="DispatcherController?btAction=Search history&txtSearchValue=${searchValue}&page=${counter.count}">${counter.count}</a>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:if> <%-- end if listHistory is existed --%>

                <c:if test="${empty listHistory}">
                    <p>No records found</p>
                </c:if>
            </c:if> <%-- end if user enters something to search --%>
        </c:if> <%-- end if session is existed --%>

        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html" />        
        </c:if>
    </body>
</html>
