<%-- 
    Document   : doQuiz
    Created on : May 27, 2020, 8:53:20 AM
    Author     : Tam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Quiz</title> 
        <link rel="stylesheet" href="css/do-quiz-style.css"/>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>      
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
            <p>Welcome, ${sessionScope.LASTNAME}</p>

            <c:set var="questions" value="${sessionScope.QUESTION_RESULT}" />

            <c:if test="${not empty questions}">
                <c:set var="time" value="${sessionScope.TIME}"/>
                <c:set var="subject" value="${requestScope.SUBJECT}"/>
                <c:set var="currentPage" value="${requestScope.CURRENT_PAGE}"/>
                <c:set var="selectedAnswersByPages" value="${sessionScope.SELECTED_ANSWER}"/>

                <h1>Quiz ${subject}</h1>
                <p id="countdownTimer"></p>

                <c:forEach var="questionDto" begin="${currentPage - 1}" end="${currentPage - 1}" items="${questions.keySet()}">
                    <form action="DispatcherController" method="POST">
                        ${currentPage}.
                        <p><c:out value="${questionDto.questionContent}" /></p> <br />
                        <input type="hidden" name="subjectID" value="${subject}" />
                        <c:set var="listAnswers" value="${questions.values()}" />
                        <c:forEach var="listAnswers" begin="${currentPage - 1}" end="${currentPage - 1}" items="${questions.values()}" varStatus="counter">
                            <c:forEach var="answerDto" items="${listAnswers}">
                                <c:if test="${answerDto.questionID == questionDto.questionID}">
                                    <input type="radio" name="selectedAnswer" value="${answerDto.answerID}" 
                                           <c:if test="${selectedAnswersByPages[currentPage].intValue() == answerDto.answerID}">
                                               checked="checked"
                                           </c:if>
                                           />            
                                    <p><c:out value="${answerDto.answerContent}" /></p> <br />                                             
                                </c:if> <%-- -end if answers belonged to the question --%>     
                            </c:forEach> <%-- end for each answerDto in listAnswers --%>
                        </c:forEach> <%-- end for each listAnswers in map questions --%>

                        <c:if test="${currentPage > 1}">
                            <a href="DispatcherController?btAction=DoQuiz&subjectID=${subject}&page=${currentPage - 1}">Previous</a>
                        </c:if>
                        <c:if test="${currentPage < requestScope.NO_OF_PAGES}">          
                            <a href="DispatcherController?btAction=DoQuiz&subjectID=${subject}&page=${currentPage + 1}">Next</a>
                        </c:if> <br />

                        <input type="submit" id="finish" value="Finish Quiz" name="btAction" />
                    </form>
                </c:forEach>
            </c:if> <%-- end if questions is existed --%>

            <c:if test="${empty questions}">
                <p>Some error occur while processing...</p>
                <a href="quiz.jsp">Click here to go back</a>
            </c:if>
        </c:if> <%-- end if session is existed --%>

        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html" />
        </c:if>

        <script>
            //Set a global variable to countdown time
            var countdown = 0;

            //Take the time of the subject
            var subjectTime = ${time};

            // Set the time we're counting down to
            var countDownTime = new Date().setTime(new Date().getTime() + subjectTime);

            // Update the count down every 1 second
            var x = setInterval(function () {

                // Get today's date and time
                var currentTime = new Date().getTime();

                // Find the distance between currentTime and countDownTime
                countdown = countDownTime - currentTime;

                // Time calculations for hours, minutes and seconds
                var hours = Math.floor((countdown % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var minutes = Math.floor((countdown % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((countdown % (1000 * 60)) / 1000);

                // Output the result in an element with id="countdownTimer"
                document.getElementById("countdownTimer").innerHTML = hours + ":" + minutes + ":" + seconds;

                // If the count down is over, submit answers and go to showResult page 
                if (countdown === 0) {
                    clearInterval(x);
                    $("#finish").click();
                }
            });

            $(document).ready(function () {
                $("input[type='radio']").click(function () {
                    var radioVal = $("[name=selectedAnswer]:checked").val();
                    $.post("DispatcherController?btAction=DoQuiz&subjectID=${subject}&page=${currentPage}&selectedAnswer=" + radioVal);
                });

                $("a").click(function () {
                    var href = $(this).attr("href");
                    href += "&countdown=" + countdown;
                    $("a").attr("href", href);
                });
            });
        </script>
    </body>
</html>
