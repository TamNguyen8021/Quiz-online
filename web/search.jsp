<%-- 
    Document   : search
    Created on : Feb 26, 2020, 3:28:33 PM
    Author     : Tam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="css/search-style.css"/>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <script src="js/search-script.js"></script>
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
            <p class="left">Welcome, ${sessionScope.LASTNAME}</p>
            <form class="left" action="DispatcherController">
                <input id="logout" type="submit" value="Log out" name="btAction" />
            </form> <br />
            <nav>
                <a href="createQuestion.jsp">Create Question</a>
                <a href="viewHistory.jsp">View History</a> <br />
            </nav>

            <h1>Search Question</h1>
            <form action="DispatcherController" method="GET">
                <input type="search" name="txtSearchValue" value="${param.txtSearchValue}" placeholder="Search"/> 
                <input id="search" type="submit" value="Search" name="btAction" />

                <p>Select status:</p>
                <c:set var="listStatus" value="${sessionScope.STATUS}"/>

                <c:if test="${not empty listStatus}">
                    <select id="selectedStatus" name="selectedStatus">
                        <c:forEach var="status" items="${listStatus}" varStatus="counter">
                            <option value="${status.status}">${status.status}</option>                             
                        </c:forEach>                    
                    </select> <br />
                </c:if>

                <c:if test="${empty listStatus}">
                    <p>Error when loading status</p>
                </c:if>


            </form><br />

            <c:set var="searchValue" value="${param.txtSearchValue}" />

            <c:if test="${not empty searchValue}">
                <c:set var="questions" value="${requestScope.QUESTION_RESULT}" />

                <c:if test="${not empty questions}">
                    <c:set var="selectedStatus" value="${param.selectedStatus}"/>

                    <table border="1">
                        <thead>
                            <tr>
                                <th>ID</th>                               
                                <th>Question</th>
                                <th>Answer</th>
                                <th>Correct</th>
                                <th>Subject</th>
                                <th>Create Date</th>   
                                    <c:if test="${selectedStatus != 'Deactive'}">
                                    <th colspan="2">Action</th>
                                    </c:if>                               
                            </tr>
                        </thead>
                        <tbody>                           
                            <c:forEach var="questionDto" items="${questions.keySet()}">
                            <form class="searchForm" action="DispatcherController" method="POST">
                                <tr>
                                    <td rowspan="5">
                                        ${questionDto.questionID}
                                        <input type="hidden" name="txtQuestionID" value="${questionDto.questionID}" />
                                    </td>
                                    <td  class="left" rowspan="5">
                                        <textarea name="txtQuestionContent">${questionDto.questionContent}</textarea>
                                    </td>                                     
                                </tr>
                                <c:forEach var="answerDto" items="${questions[questionDto]}" varStatus="counter">
                                    <c:if test="${answerDto.questionID == questionDto.questionID}">
                                        <tr>
                                            <td>
                                                <textarea name="txtAnswer${counter.count}Content">${answerDto.answerContent}</textarea>
                                                <input type="hidden" name="answer${counter.count}ID" value="${answerDto.answerID}" />
                                            </td>
                                            <td>
                                                <input type="radio" name="rdCorrect" value="${answerDto.answerID}"
                                                       <c:if test="${answerDto.answerCorrect}">
                                                           checked="checked"
                                                       </c:if>
                                                       />
                                            </td>
                                            <c:if test="${counter.count < 2}">
                                                <td rowspan="4">
                                                    <c:set var="listSubjects" value="${sessionScope.SUBJECT}"/>

                                                    <c:if test="${not empty listSubjects}">
                                                        <select class="center" name="subjectID">
                                                            <c:forEach var="subject" items="${listSubjects}">
                                                                <option value="${subject.subjectID}"
                                                                        <c:if test="${subject.subjectID == questionDto.subjectID}">
                                                                            selected="selected"
                                                                        </c:if>
                                                                        >${subject.subjectID}</option>                                     
                                                            </c:forEach>                    
                                                        </select> <br />
                                                    </c:if>

                                                    <c:if test="${empty listSubjects}">
                                                        <p>Can't load subjects</p>
                                                    </c:if>
                                                </td>
                                                <td class="center" rowspan="4">
                                                    <fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${questionDto.createDate}"/>
                                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                                </td>
                                                <c:if test="${selectedStatus != 'Deactive'}">
                                                    <td class="center" rowspan="4">
                                                        <c:url var="urlRewriting" value="DispatcherController">
                                                            <c:param name="btAction" value="Delete"></c:param>
                                                            <c:param name="questionID" value="${questionDto.questionID}"></c:param>
                                                            <c:param name="lastSearchValue" value="${searchValue}"></c:param>
                                                        </c:url>
                                                        <a class="linkNeedUpdate" href="${urlRewriting}">Delete</a>                                
                                                    </td>
                                                    <td class="center" rowspan="4">
                                                        <input id="update" type="submit" value="Update" name="btAction" />                                                          
                                                    </td>
                                                </c:if> <%-- end if selectedStatus is not "Deactive" --%>                                                   
                                            </c:if> <%-- end if cell subject, delete and update appear more than one time --%>                                               
                                        </tr>
                                    </c:if> <%-- end if answers belong to the current question --%>
                                </c:forEach> <%-- end for each answer in Map question --%>                    
                            </form>                            
                        </c:forEach> <%-- end for each question in Map question --%>    
                    </tbody>
                </table>

                <c:if test="${requestScope.CURRENT_PAGE <= requestScope.NO_OF_PAGES && requestScope.NO_OF_PAGES > 1}">
                    <c:forEach var="page" begin="1" end="${requestScope.NO_OF_PAGES}" varStatus="counter">
                        <c:if test="${page == requestScope.CURRENT_PAGE}">
                            <b><a href="DispatcherController?btAction=Search&txtSearchValue=${searchValue}&page=${counter.count}">${counter.count}</a></b>
                        </c:if>
                        <c:if test="${page != requestScope.CURRENT_PAGE}">
                            <a class="linkNeedUpdate" href="DispatcherController?btAction=Search&txtSearchValue=${searchValue}&page=${counter.count}">${counter.count}</a>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:if> <%-- end if questions is existed --%>

            <c:if test="${empty questions}">
                <h1>No question found!</h1>
            </c:if>
        </c:if> <%-- end if user enters something to search --%>
    </c:if> <%-- end if session is existed --%>

    <c:if test="${empty sessionScope}">
        <c:redirect url="login.html" />        
    </c:if>
</body>
</html>
