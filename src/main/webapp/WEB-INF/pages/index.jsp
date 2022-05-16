<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 10/05/2022
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<t:App title="Home">
    <jsp:attribute name="head">

    </jsp:attribute>

    <jsp:body>
        <div>
            <section id="hero">
                <h1>Welcome</h1>
            </section>

            <section id="statistics">
                <h1>Statistics</h1>
            </section>
        </div>
    </jsp:body>
</t:App>



