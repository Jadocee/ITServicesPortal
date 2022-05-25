<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 24/05/2022
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="${issue.title}">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/issue.css"/>"/>
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/form.css"/>"/>
        <style>

        </style>
        <script type="text/javascript" defer>
            async function handleRecommendComment(id) {
                const url = <spring:url value="/tracker/recommend_comment"/>;
                const formData = new FormData();
                formData.append('id', id);
                fetch(url, {
                    method: 'POST',
                    body: formData
                }).then(response => {
                    if (response.status === 6) {
                        window.location.reload();
                    }
                }).catch(error => {
                    console.error(error);
                });
            }
        </script>
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
                    <span class="issue-label"><c:out value="${issue.state}"/></span>
                    <c:forEach var="tag" items="${issue.tags}">
                        <span class="issue-label"><c:out value="${tag}"/></span>
                    </c:forEach>
                </div>

            </div>
            <div class="issue-description">
                <p><c:out value="${issue.desc}"/></p>
            </div>

            <c:if test="${!comments.isEmpty()}">
                <div class="issue-comments-container">
                    <c:forEach var="comment" items="${comments}">
                        <div class="issue-comment">
                            <div class="issue-comment__top-bar">
                                <div>
                                    <c:out value="${comment.author.displayName}"/> commented on
                                    <time datetime="<c:out value="${comment.created}"/>">
                                        <c:out value="${comment.created}"/>
                                    </time>
                                </div>
                                <div role="menubar" class="top-bar__menu-container">
                                    <c:if test="${comment.author.role.equals('ITSTAFF') }">
                                            <span role="button"
                                                  aria-label="Recommend as solution"
                                                  class="material-symbols-rounded"
                                                  onclick="handleRecommendComment('<c:out value="${comment.id}"/>')"
                                            >recommend</span>
                                    </c:if>
                                </div>

                            </div>
                            <p><c:out value="${comment.message}"/></p>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </section>

        <section id="commentFormSection">
            <form
                    id="issueCommentForm"
                    accept-charset="UTF-8"
                    method="post"
                    action="<spring:url value="/issues/${issue.id}/new_comment"/>"
                    autocapitalize="sentences"
                    autocomplete="off"
                    spellcheck="true"
                    lang="en"
            >
                <div class="text-area-container input-container">
                    <textarea
                            id="issueCommentBody"
                            placeholder="Leave a comment"
                            minlength="10"
                            maxlength="600"
                            rows=5
                            required
                            aria-required="true"
                            aria-label="Comment body"
                            name="commentBody"
                    ></textarea>
                </div>
                <div class="form__bottom-container">
                    <button type="submit" class="form-btn md">Comment</button>
                </div>
            </form>
        </section>
    </jsp:body>
</app:Layout>