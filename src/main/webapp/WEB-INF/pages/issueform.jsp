<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 11/05/2022
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
Create an issue
<form action="/report/submit">
    <label for="issueTitle">Title of issue: </label>
    <input type="text" id="issueTitle"><br>
    <label for="description">Description of issue: </label><br>
    <textarea id="description"></textarea><br>
    <label for="catergories">Catergory</label>
    <select name="catergories" id="catergories">
        <option value="Network">Network</option>
        <option value="Software">Software</option>
        <option value="Hardware">Hardware</option>
        <option value="Email">Email</option>
        <option value="Account">Account</option>
    </select><br>
    <input type="submit" value="Submit">


</form>