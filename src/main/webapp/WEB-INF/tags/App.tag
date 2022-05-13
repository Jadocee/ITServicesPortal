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
    <link type="text/css" rel="stylesheet" href="<spring:url value="$styles/App.css"/>"/>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <jsp:invoke fragment="head"/>
    <title><c:out value="${title}"/></title>
</head>
<body>
<jsp:doBody/>
</body>
</html>