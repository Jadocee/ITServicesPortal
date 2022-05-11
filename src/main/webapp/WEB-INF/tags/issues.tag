<%@ tag description="layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="issues" required="true" %>


<menu>
    <c:forEach var="i" begin="0" end="${issues.size()}" step="1">
        <li>${issues.get(i).getTitle()}</li>
    </c:forEach>
</menu>