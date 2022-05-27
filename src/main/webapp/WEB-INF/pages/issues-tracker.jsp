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
                max-height: 300px;
            }

            .issues-group > .issues-list.scrollable {
                overflow-y: auto;
                overflow-x: hidden;
                padding-right: 8px;
                scroll-snap-type: y proximity;
                max-height: calc(284px - 2rem);
            }

            .issues-group > .issues-list.scrollable > .issue-cls {
                scroll-snap-align: center;
            }

            .scrollable::-webkit-scrollbar {
                width: 8px;
                background-color: hsl(215 21% 11%);
                border-radius: 8px;
            }

            .scrollable::-webkit-scrollbar-thumb {
                background-color: hsl(253 89% 42%);
                border-radius: 8px;
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
                background-color: hsl(253 89% 42%);
                position: sticky;
                top: -8px;
                left: -8px;
                right: -8px;
                display: flex;
                align-content: center;
                align-items: center;
                justify-content: flex-start;
                height: 2rem;
                max-height: 2rem;
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
                <c:when test="${foundIssues}">
                    <div class="issues-list-container">

                        <c:forEach var="issuesListMap" items="${issues}">
                            <section class="issues-group">
                                <div class="issues-group__top-bar">
                                    <h1 class="issues-group__heading">
                                        <c:out value="${issuesListMap.key}"/>
                                    </h1>
                                </div>
                                <div class="issues-list scrollable">
                                    <c:forEach var="issue" items="${issuesListMap.value}">
                                        <div class="issue-cls">
                                            <a
                                                    class="issue__title"
                                                    href="<spring:url value="/issues/tracker/manage_issue/${issue.id}"/>"
                                            >
                                                <c:out value="${issue.title}"/>
                                            </a>
                                            <span class="issue__created_on">
                                            Created by <c:out value="${issue.author.displayName}"/> on <c:out
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