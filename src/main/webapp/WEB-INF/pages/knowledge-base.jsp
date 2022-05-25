<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="Knowledge Base">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/issues.css"/>"/>
    </jsp:attribute>

    <jsp:body>
        <h1>Knowledge Base</h1>
        <c:choose>
            <c:when test="${!issues.isEmpty()}">
                <div class="issues-list-container">
                    <div class="issues-list">
                        <c:forEach var="issue" items="${issues}">
                            <div class="issue-cls">
                                <a class="issue__title" href="<spring:url value="/issues/${issue.id}"/>"><c:out
                                        value="${issue.title}"/></a>
                                <span class="issue__created_on">
                            Created by <c:out value="${issue.author.displayName}"/> on <c:out
                                        value="${issue.createdOn}"/>
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

