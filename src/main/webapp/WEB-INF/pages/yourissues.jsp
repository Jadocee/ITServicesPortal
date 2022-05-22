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
        <style>
            .issues-list-container {
                width: 100%;
            }

            .issues-list {
                display: flex;
                flex-direction: column;
                gap: 2rem;
                width: 100%;
            }

            .issue-cls {
                width: 100%;
                display: flex;
                flex-direction: column;
                gap: 0.5em;
                border: hsl(212 12% 21%) solid 1px;
                border-radius: 0.5rem;
                padding: 16px;
            }

            .issue__title {
                margin: 0;
                text-decoration: none;
                font-weight: 600;
                font-size: 16px;
                transition: 80ms cubic-bezier(0.33, 1, 0.68, 1);
                transition-property: color, background-color, box-shadow, border-color;
                background-color: transparent;
                cursor: pointer;
            }

            .issue__title:hover {
                color: #58a6ff;
            }

            .issue__created_on {
                font-size: 12px;
                color: #8b949e;
                line-height: 1.5;
            }


        </style>
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

