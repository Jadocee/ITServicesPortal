<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="Your Issues">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/issues.css"/>"/>
        <style>
            .issue-cls {
                position: relative;
            }

            .notification {
                position: absolute;
                display: flex;
                flex-direction: row;
                gap: 8px;
                border: 1px solid hsl(212 9% 58%);
                border-radius: 0.5rem;
                justify-content: center;
                align-items: center;
                align-content: center;
                font-weight: 500;
                padding: 1px 7px;
                left: 80%;
                bottom: 75%;
                background-color: hsl(345 100% 50% / 0.9);
            }

        </style>
    </jsp:attribute>

    <jsp:body>
        <h1>Your Issues</h1>
        <c:choose>
            <c:when test="${foundIssues}">
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
                                <c:if test="${issue.tags.contains('Waiting on reporter')}">
                                    <div class="notification">
                                        <span class="material-symbols-rounded">notification_important</span>
                                        Action required
                                    </div>
                                </c:if>
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

