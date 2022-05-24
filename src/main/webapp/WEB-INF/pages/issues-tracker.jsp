<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<app:Layout title="Issue Tracker">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/issues.css"/>"/>
        <style>
            #issueTracker {
                display: flex;
                flex-direction: column;
                gap: 1rem;
                width: 100%;
                align-items: center;
            }

            #issueTracker > h1 {
                margin: 0 0 0.5rem 0;
            }

            .issues-group {
                border: 1px solid hsl(212 9% 58%);
                border-radius: 0.5rem;
                padding: 8px;
                overflow: hidden;
                position: relative;
                overflow-y: auto;
                max-height: 300px;
            }

            .issues-group__heading {
                margin: 0;
                font-size: 1.25rem;
                text-transform: uppercase;
                letter-spacing: 0.05em;
            }

            .issues-group__top-bar {
                padding: 8px;
                margin: -8px -8px 8px -8px;
                background-color: hsl(264 64% 35%);
                position: sticky;
                top: -8px;
                left: -8px;
                right: -8px;
            }

            .issues-list-container {
                display: flex;
                flex-direction: column;
                gap: 1rem;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <section id="issueTracker">
            <h1>Issues Tracker</h1>

            <c:choose>
                <c:when test="${!issues.isEmpty()}">
                    <div class="issues-list-container">

                        <c:forEach var="issuesListMap" items="${issues}">
                            <section class="issues-group">
                                <div class="issues-group__top-bar">
                                    <a href="<spring:url value="/issues/tracker?state=${issuesListMap.key}"/>">
                                        <h1 class="issues-group__heading">
                                            <c:out value="${issuesListMap.key}"/>
                                        </h1>
                                    </a>
                                </div>
                                <div class="issues-list">
                                    <c:forEach var="issue" items="${issuesListMap.value}">
                                        <div class="issue-cls">
                                            <a
                                                    class="issue__title"
                                                    href="<spring:url value="/issues/tracker/manage_issue/${issue.id}"/>"
                                            >
                                                <c:out value="${issue.title}"/>
                                            </a>
                                            <span class="issue__created_on">
                                            Created by <c:out value="${issue.author}"/> on <c:out
                                                    value="${issue.createdOn}"/>
                                        </span>
                                        </div>
                                    </c:forEach>
                                </div>
                            </section>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    Could not find any issues
                </c:otherwise>
            </c:choose>
        </section>
    </jsp:body>
</app:Layout>