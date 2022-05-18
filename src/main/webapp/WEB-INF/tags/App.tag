<%@ tag description="layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="head" required="false" fragment="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html"/>
    <meta name="description" content=""/>
    <link fetchpriority="low" rel="icon" href="<spring:url value="$assets/favicon.png"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link fetchpriority="high" type="text/css" rel="stylesheet" href="<spring:url value="$styles/App.css"/>"/>
    <link fetchpriority="high" type="text/css" rel="stylesheet" href="<spring:url value="$styles/__Layout.css"/>"/>
    <script type="text/javascript"
            src="<spring:url value="$scripts/background.js" context="module"/>" defer></script>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <jsp:invoke fragment="head"/>
    <title><c:out value="${title}"/></title>
</head>
<body>

<header class="primary-header">
    <div class="primary-header__left">
        <h1>Services Portal</h1>
    </div>
    <div class="primary-header__middle">

    </div>
    <div class="primary-header__right">

    </div>
</header>


<nav class="nav-menu-container">
    <menu class="nav-menu">
        <li>
            <button
                    id="HomeButton"
                    type="button"
                    class="nav-menu-button"
                    aria-label="Return To Homepage"
                    tabindex="0"
                    onclick="window.location.href='<spring:url value="/"/>'"
            >
                <span class="material-symbols-rounded">home</span>
                <span class="nav-menu-button__label">Home</span>

            </button>
        </li>

        <li>
            <button
                    id="KbButton"
                    type="button"
                    class="nav-menu-button"
            >
                <span class="material-symbols-rounded">inventory_2</span>
                <span class="nav-menu-button__label">Knowledge Base</span>
            </button>
        </li>

        <c:if test="${client != null}">
            <c:choose>
                <c:when test="${client.role.label.equals('User')}">
                    <li>
                        <button type="button" class="nav-menu-button" onclick="window.location.href='./yourissues'">
                            Your Issues
                        </button>
                    </li>

                    <li>
                        <button type="button" class="nav-menu-button">
                            Create An Issue
                        </button>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <button type="button" class="nav-menu-button">
                            Issue Manager
                        </button>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:choose>
            <c:when test="${client == null}">
                <li class="list-item-bottom">
                    <button
                            id="LoginButton"
                            type="button"
                            class="nav-menu-button"
                            aria-label="Login"
                    >
                        <span class="material-symbols-rounded">login</span>
                        <span class="nav-menu-button__label">Login</span>

                    </button>
                </li>
            </c:when>

            <c:otherwise>
                <li class="list-item-bottom">
                    <button
                            id="LogoutButton"
                            type="button"
                            class="nav-menu-button"
                    >
                        <span class="material-symbols-rounded">logout</span>
                        <span class="nav-menu-button__label">Logout</span>


                    </button>
                </li>
            </c:otherwise>
        </c:choose>
    </menu>
</nav>

<main>
    <%--    <c:import url="${pageName}.jsp" charEncoding="UTF-8"/>--%>
    <jsp:doBody/>
</main>
</body>
</html>