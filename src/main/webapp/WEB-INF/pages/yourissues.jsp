<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 15/05/2022
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="Your Issues">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/issues.css"/>"/>
    </jsp:attribute>

    <jsp:body>
        <h1>Your Issues</h1>
        <c:choose>
            <c:when test="${issues.size() > 0}">
                <div class="issues-list-container">
                    <div class="issues-list">
                        <c:forEach var="i" begin="0" end="${issues.size() - 1}">
                            <div class="issue-cls">
                                <a class="issue__title" href="<spring:url value="/issues/${issues.get(i).id}"/>"><c:out
                                        value="${issues.get(i).title}"/></a>
                                <span class="issue__created_on">
                            Created by <c:out value="${issues.get(i).author}"/> on <c:out
                                        value="${issues.get(i).createdOn}"/>
                        </span>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                Could not find any issues
            </c:otherwise>
        </c:choose>
    </jsp:body>
</app:Layout>

