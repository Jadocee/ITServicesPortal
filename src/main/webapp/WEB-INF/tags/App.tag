<%@ tag description="layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="scripts" required="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html"/>
    <meta name="description" content=""/>
    <link rel="icon" href="<c:url value="$assets/favicon.png"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="$styles/App.css"/>"/>
    <title><c:out value="${title}"/></title>
</head>
<body>
    <jsp:doBody/>
</body>
</html>