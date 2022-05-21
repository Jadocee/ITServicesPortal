<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 21/05/2022
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="${issue.title}">
    <jsp:attribute name="head">
        <style>
            .issue-container {
                width: 100%;
            }

            .issue-creation-details {
                opacity: 0.64;
                font-size: 14px;
                color: #8b949e;
            }

            .issue-top {
                padding-bottom: 8px;
                border-bottom: 1px solid #8b949e;
                margin-bottom: 16px;
                display: flex;
                flex-direction: column;
                gap: 0.5em;
            }

            .issue-title {
                margin: 0;
                letter-spacing: 0.05em;
            }

            .issue-description {
                border: 1px solid #8b949e;
                border-radius: 0.5rem;
                padding: 16px;
                overflow: visible;
            }

            .issue-description > p {
                margin: 0;
                line-height: 1.5;
                word-wrap: break-word;
            }

            .issue-labels {
                display: flex;
                flex-direction: row;
                gap: 0.5em;
                align-content: center;
            }

            .issue-label {
                border: 1px solid #fff;
                border-radius: 2em;
                white-space: nowrap;
                font-weight: 500;
                font-size: 12px;
                padding: 0 7px;
                display: inline-block;
                line-height: 18px;
            }

            .issue-label:hover {
                cursor: default;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <section class="issue-container">
            <div class="issue-top">
                <h1 class="issue-title"><c:out value="${issue.title}"/></h1>
                <span class="issue-creation-details">
                        <c:out value="${issue.author}"/> created this issue on <c:out value="${issue.createdOn}"/>
                    </span>
                <div class="issue-labels">
                    <span class="issue-label"><c:out value="${issue.category}"/></span>
                    <span class="issue-label"><c:out value="${issue.subCategory}"/></span>
                    <c:if test="${issue.tags != null}">
                        <c:forEach var="i" begin="0" end="${issue.tags.size() - 1}">
                            <span class="issue-label"><c:out value="${issue.tags.get(i)}"/></span>
                        </c:forEach>
                    </c:if>
                </div>

            </div>
            <div class="issue-description">
                <p><c:out value="${issue.desc}"/></p>
            </div>
        </section>
    </jsp:body>
</app:Layout>