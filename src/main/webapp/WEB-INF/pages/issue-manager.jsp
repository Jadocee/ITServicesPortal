<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="${issue.title}">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/issue.css"/>"/>
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/form.css"/>"/>
        <style>
            .add-label {
                position: relative;
                aspect-ratio: 1;
                width: 20px;
                max-height: 20px;
                display: flex;
                align-content: center;
                justify-content: center;
                align-items: center;
            }

            .add-label-btn {
                cursor: pointer;
                font-size: 20px;
                opacity: 0.5;
                transition: opacity 300ms ease-in-out;
            }

            .add-label-btn:hover {
                opacity: 1;
            }

            .add-label-menu {
                position: absolute;
                list-style: none;
                background: hsl(215 21% 11%);
                top: 100%;
                left: 0;
                background-clip: padding-box;
                right: auto;
                margin: 0;
                padding: 4px 0 4px 0;
                z-index: 100;
                width: 200px;
                border: 1px solid;
                border-radius: 6px;
            }

            .add-label-menu > li {
                width: 100%;
                text-align: left;
                display: block;
                padding: 4px 8px 4px 16px;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                background-color: hsl(264 64% 35% / 0);
                transition: background-color 250ms ease-in-out;
                cursor: pointer;
            }

            .add-label-menu > li:hover {
                background-color: hsl(264 64% 35% / 1);
            }

            .issue-label.removable {
                position: relative;
            }

            .issue-label.removable > span[role="button"] {
                font-size: 10px;
                visibility: hidden;
                cursor: pointer;
                background-color: hsl(355 86% 48% / 0.9);
                border: 1px solid;
                border-radius: 6px;
                transition: visibility 251ms linear, opacity 250ms linear;
                position: absolute;
                opacity: 0;
                bottom: 55%;
                left: 94%;
            }

            .issue-label.removable:hover > span[role="button"],
            .issue-label.removable > span[role="button"]:hover {
                visibility: visible;
                opacity: 1;
            }
        </style>
        <script type="text/javascript">
            async function handleRecommendComment(id) {
                const url = '<spring:url value="/issues/tracker/recommend_comment"/>';
                fetch(url, {
                    method: 'POST',
                    body: JSON.stringify({
                        id: id
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.ok) {
                        window.location.reload();
                    }
                }).catch(error => {
                    console.error(error);
                });
            }

            async function addTag(tag) {
                const url = '<spring:url value="/issues/tracker/manage_issue/${issue.id}/add_tag"/>';
                fetch(url, {
                    method: 'POST',
                    body: JSON.stringify({
                        tag: tag
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.ok) {
                        window.location.reload();
                    }
                }).catch(error => {
                    console.error(error);
                });
            }

            function removeTag(el) {
                const url = '<spring:url value="/issues/tracker/manage_issue/${issue.id}/remove_tag"/>';
                let tag;
                switch (el.parentElement.id) {
                    case "knowledge-base-article":
                        tag = 'ARTICLE';
                        break;
                    case "waiting-on-reporter":
                        tag = 'WAITING';
                        break;
                    case "waiting-on-third-party":
                        tag = 'WAITING_TP';
                        break;
                    default:
                        return;
                }
                console.log(tag);
                fetch(url, {
                    method: 'POST',
                    body: JSON.stringify({
                        tag: tag
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.ok) {
                        window.location.reload();
                    }
                }).catch(error => {
                    console.error(error);
                });
            }

            function toggleMenu() {
                const menu = document.querySelector(".add-label-menu");
                menu.hidden = !menu.hidden;
                menu.ariaHidden = menu.hidden.toString();
            }
        </script>
    </jsp:attribute>

    <jsp:body>
        <section class="issue-container">
            <div class="issue-top">
                <h1 class="issue-title"><c:out value="${issue.title}"/></h1>
                <span class="issue-creation-details">
                        <c:out value="${issue.author.displayName}"/> created this issue on <c:out
                        value="${issue.createdOn}"/>
                    </span>
                <div class="issue-labels">
                    <span class="issue-label"><c:out value="${issue.category}"/></span>
                    <span class="issue-label"><c:out value="${issue.subCategory}"/></span>
                    <span class="issue-label"><c:out value="${issue.state}"/></span>
                    <c:forEach var="tag" items="${issue.tags}">
                        <span id="<c:out value="${tag.replace(' ', '-').toLowerCase()}"/>"
                              class="issue-label removable">
                            <span role="button"
                                  aria-label="Remove this label"
                                  class="material-symbols-rounded"
                                  onclick="removeTag(this)"
                            >remove</span>
                            <c:out value="${tag}"/>
                        </span>
                    </c:forEach>
                    <div class="add-label">
                        <span role="button"
                              aria-haspopup="menu"
                              class="material-symbols-rounded add-label-btn"
                              onclick="toggleMenu()"
                        >add</span>

                        <menu role="menu" class="add-label-menu" hidden aria-hidden="true">
                            <c:if test="${!issue.tags.contains('Waiting on third-party')}">
                                <li role="menuitem"
                                    aria-label="Mark as 'waiting on third-party'"
                                    onclick="addTag('WAITING_TP')"
                                >
                                    Waiting on third-party
                                </li>
                            </c:if>
                            <c:if test="${!issue.tags.contains('Waiting on reporter')}">
                                <li role="menuitem"
                                    aria-label="Mark as 'waiting on reporter'"
                                    onclick="addTag('WAITING')"
                                >
                                    Waiting on reporter
                                </li>
                            </c:if>
                            <c:if test="${!issue.tags.contains('Knowledge-Base Article') && issue.state.equals('Completed') || issue.state.equals('Resolved')}">
                                <li role="menuitem"
                                    aria-label="Add to knowledge-base"
                                    onclick="addTag('ARTICLE')"
                                >
                                    Knowledge-base article
                                </li>
                            </c:if>
                        </menu>
                    </div>
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
                                    <c:if test="${!issue.state.equals('Completed') && !issue.state.equals('Resolved') && comment.author.role.equals('ITSTAFF')}">
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