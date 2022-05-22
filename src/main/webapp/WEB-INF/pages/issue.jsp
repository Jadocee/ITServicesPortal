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
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/form.css"/>"/>
        <style>
            .issue-container {
                width: 100%;
            }

            .issue-creation-details {
                font-size: 14px;
                color: hsl(212 9% 58% / 0.8);
            }

            .issue-top {
                padding-bottom: 8px;
                border-bottom: 1px solid hsl(212 9% 58%);
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
                border: 1px solid hsl(212 9% 58%);
                border-radius: 0.5rem;
                padding: 16px;
                overflow: visible;
            }

            p {
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

            #commentFormSection {
                width: 100%;
                display: flex;
                flex-direction: column;
                align-items: center;
                border: 1px solid hsl(212 9% 58%);
                border-radius: 0.5rem;
                margin-top: 16px;
                padding: 8px;
            }

            #commentFormSection > form {
                width: 100%;
                max-width: 100% !important;
                gap: 0.5rem !important;
            }

            .text-area-container {
                width: 100%;
            }

            #issueCommentBody {
                width: 100%;
            }

            .form__bottom-container {
                width: 100%;
                display: flex;
                flex-direction: row;
                justify-content: flex-end;
                max-height: fit-content;
            }

            .issue-comments-container {
                margin-top: 2rem;
                display: flex;
                flex-direction: column;
                gap: 2rem;
                position: relative;
            }

            .issue-comment::before {
                content: "";
                position: absolute;
                top: 0;
                bottom: 0;
                left: 0;
                display: block;
                width: 2px;
                background-color: hsl(212 9% 58%);
                margin-top: -2rem;
                z-index: -1;
                margin-left: 16px;
            }

            .issue-comment {
                border: 1px solid hsl(212 9% 58%);
                border-radius: 0.5rem;
                padding: 16px;
                width: 100%;
                display: flex;
                flex-direction: column;
                gap: 16px;
                align-content: center;
                justify-content: flex-start;
                overflow: hidden;
                background-color: hsl(216 28% 7%);
            }

            .issue-comment > div {
                background: hsl(264 64% 35%);
                color: hsl(212 9% 58%);
                padding: 8px 16px;
                margin: -16px -16px 0 -16px;
                font-weight: 400;
                font-size: 14px;
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
                    <span class="issue-label"><c:out value="${issue.state}"/></span>
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

            <c:if test="${comments.size() > 0}">
                <div class="issue-comments-container">
                    <c:forEach var="i" begin="0" end="${comments.size() - 1}">
                        <div class="issue-comment">
                            <div>
                                <c:out value="${comments.get(i).author}"/> commented on
                                <time datetime="<c:out value="${comments.get(i).created}"/>">
                                    <c:out value="${comments.get(i).created}"/>
                                </time>
                            </div>
                            <p><c:out value="${comments.get(i).message}"/></p>
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