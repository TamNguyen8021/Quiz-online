<%-- 
    Document   : create
    Created on : Mar 16, 2020, 2:51:57 PM
    Author     : Tam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        <link rel="stylesheet" href="css/create-account-style.css"/>
    </head>
    <body>
        <div>
            <h1>Sign Up</h1>
            <form action="DispatcherController" method="POST">
                <c:set var="errors" value="${requestScope.CREATE_ERRORS}"></c:set>

                <c:if test="${not empty errors.emailIsExisted}">
                    <font color="red">
                    ${errors.emailIsExisted}
                    </font><br />
                </c:if>

                <c:if test="${not empty errors.emailWrongMatched}">
                    <font color="red">
                    ${errors.emailWrongMatched}
                    </font><br />
                </c:if>

                <c:if test="${not empty errors.passwordLengthErr}">
                    <font color="red">
                    ${errors.passwordLengthErr}
                    </font><br />
                </c:if>

                <c:if test="${not empty errors.confirmNotMatched}">
                    <font color="red">
                    ${errors.confirmNotMatched}
                    </font><br />
                </c:if>

                <c:if test="${not empty errors.fullNameLengthErr}">
                    <font color="red">
                    ${errors.fullNameLengthErr}
                    </font><br />
                </c:if>

                <c:if test="${not empty errors.fullNameWrongMatched}">
                    <font color="red">
                    ${errors.fullNameWrongMatched}
                    </font><br />
                </c:if>

                <input type="email" name="txtEmail" value="${param.txtEmail}" placeholder="Email*" required="required"/> <br />
                <input type="password" name="txtPassword" value="" placeholder="Password*" required="required"/> <br />
                <input type="password" name="txtConfirm" value="" placeholder="Confirm*"/><br />
                <input type="text" name="txtFullname" value="${param.txtFullname}" placeholder="Full name*" required="required"/> <br />

                <input type="submit" value="Create Account" name="btAction" />
                <input type="reset" value="Reset" />
            </form>
            <p>Have an account? <a href="login.html">Sign In</a></p>
        </div>
    </body>
</html>
