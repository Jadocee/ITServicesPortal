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
            <c:when test="${foundArticles}">
                <div class="issues-list-container">
                    <div class="issues-list">
                        <c:forEach var="article" items="${articles}">
                            <div class="issue-cls">
                                <a class="issue__title" href="<spring:url value="/knowledge_base/${article.id}"/>">
                                    <c:out value="${article.title}"/>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                The Knowledge-Base is currently empty.
            </c:otherwise>
        </c:choose>
    </jsp:body>
</app:Layout>

