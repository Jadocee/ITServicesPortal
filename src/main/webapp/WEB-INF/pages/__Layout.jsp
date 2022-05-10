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
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%--<c:set var="title" scope="page"/>--%>
<%--<c:set var="content" scope="page"/>--%>

<t:App title="${title}">
    <jsp:body>
        <header class="primary-header">
            TEST HEADER
            <c:choose>
                <c:when test="${client != null}">
                    <br> THIS WILL SHOW IF CLIENT IS NULL
                </c:when>
                <c:otherwise>
                    <br> THIS WILL SHOW IF CLIENT IS NOT NULL
                </c:otherwise>
            </c:choose>
        </header>

        <aside class="nav-menu-container">
            <nav>
                <menu class="nav-menu">
                    <c:choose>
                        <c:when test="${client == null}">
                            <li>Login</li>
                        </c:when>
                        <c:otherwise>
                            <li>Logout</li>
                        </c:otherwise>
                    </c:choose>
                </menu>
            </nav>
        </aside>

        <main>
            <c:import url="${content}.jsp"/>
        </main>
    </jsp:body>
</t:App>
