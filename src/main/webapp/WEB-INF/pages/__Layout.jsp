<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 10/05/2022
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%--<c:set var="title" scope="page"/>--%>
<%--<c:set var="content" scope="page"/>--%>

<t:App title="${pageTitle}">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="$styles/__Layout.css"/>"/>
    </jsp:attribute>

    <jsp:body>
        <header class="primary-header">
            <div class="primary-header__left">
                <h1>Services Portal</h1>
            </div>
            <div class="primary-header__middle">

            </div>
            <div class="primary-header__right">

            </div>
        </header>

        <div id="container">

            <nav class="nav-menu-container">
                <menu class="nav-menu">
                    <c:choose>
                        <c:when test="${client == null}">
                            <li>
                                <button type="button" class="nav-menu-button">
                                    Login
                                </button>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li>
                                <button type="button" class="nav-menu-button">
                                    Logout
                                </button>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <li>
                        <button type="button" class="nav-menu-button">
                            Home
                        </button>
                    </li>

                    <li>
                        <button type="button" class="nav-menu-button">
                            Knowledge Base
                        </button>
                    </li>

                    <c:if test="${client != null}">
                        <c:choose>
                            <c:when test="${client.role.label.equals('User')}">
                                <li>
                                    <button type="button" class="nav-menu-button">
                                        Your Issues
                                    </button>
                                </li>

                                <li>
                                    <button type="button" class="nav-menu-button">
                                        Create An Issue
                                    </button>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <button type="button" class="nav-menu-button">
                                        Issue Manager
                                    </button>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </menu>
            </nav>

            <main>
                <c:import url="${pageName}.jsp"/>
            </main>

        </div>


    </jsp:body>
</t:App>
