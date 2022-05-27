<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="${issue.title}">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/form.css"/>"/>
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/issue.css"/>"/>
    </jsp:attribute>

    <jsp:body>
        <section class="issue-container">
            <div class="issue-top">
                <h1 class="issue-title"><c:out value="${article.title}"/></h1>
                <span class="issue-creation-details">
                    Article created on
                    <time datetime="<c:out value="${article.addedOn}"/>">
                        <c:out value="${article.addedOn}"/>
                    </time>
                </span>
                <div class="issue-labels">
                    <span class="issue-label"><c:out value="${article.category}"/></span>
                    <span class="issue-label"><c:out value="${article.subCategory}"/></span>
                    <span class="issue-label"><c:out value="${article.state}"/></span>
                    <c:forEach var="tag" items="${article.tags}">
                        <span class="issue-label"><c:out value="${tag}"/></span>
                    </c:forEach>
                </div>

            </div>
            <div class="issue-description">
                <p><c:out value="${article.desc}"/></p>
            </div>

            <div class="issue-comments-container">
                <div class="issue-comment">
                    <div class="issue-comment__top-bar">
                        <div>Solution provided on
                            <time datetime="<c:out value="${article.resolvedOn}"/>">
                                <c:out value="${article.resolvedOn}"/>
                            </time>
                        </div>
                    </div>
                    <p><c:out value="${article.solution}"/></p>
                </div>
            </div>
        </section>
    </jsp:body>
</app:Layout>