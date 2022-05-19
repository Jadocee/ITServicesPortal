<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 15/05/2022
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="Login">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/login.css"/>"/>
    </jsp:attribute>

    <jsp:body>
        <form id="LoginForm" method="post" action="<spring:url value="/login"/>">
            <h1>Please enter your credentials</h1>
            <div class="LoginForm__inputs-wrapper">
                <div class="input-container">
                    <input type="email" id="emailInput" name="email" maxlength="319" autofocus placeholder=" " required>
                    <label for="emailInput">Email</label>
                </div>
                <div class="input-container">
                    <input type="password" id="passwordInput" name="password" maxlength="30" placeholder=" " required>
                    <label for="passwordInput">Password</label>
                </div>
            </div>
            <button class="login-button" type="submit">Login</button>
        </form>
    </jsp:body>
</app:Layout>

