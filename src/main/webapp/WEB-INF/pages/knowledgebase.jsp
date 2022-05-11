<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 11/05/2022
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div>
    <div>
        <h1>Heading</h1>
    </div>

    <div>
        <t:issues issues="${issues}">

        </t:issues>
    </div>


</div>
