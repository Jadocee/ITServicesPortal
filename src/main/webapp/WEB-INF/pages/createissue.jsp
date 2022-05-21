<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 11/05/2022
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="Login">
    <jsp:attribute name="head">
        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/form.css"/>"/>
        <%--        <link type="text/css" rel="stylesheet" href="<spring:url value="/$styles/createissue.css"/>"/>--%>
        <script type="text/javascript">
            let lastCategory = undefined;
            const subCategories = {
                "Network": ["Can't Connect", "Speed", "Constant dropouts"],
                "Software": ["Slow to load", "Won't load at all"],
                "Hardware": ["Computer won't turn on", "Computer \"Blue screens\"", "Disk Drive", "Peripherals"],
                "Email": ["Can't send", "Can't receive", "SPAM/Phishing"],
                "Account": ["Password reset", "Wrong details"]
            };

            window.onload = async () => {
                document.querySelector("#categorySelect").addEventListener("change", async (ev) => {
                    changeSubCategoryOptions(ev.currentTarget);
                }, false);
            }

            function changeSubCategoryOptions(baseCategory) {
                const subCategorySelect = document.querySelector("#subcategorySelect");
                removeChildren(subCategorySelect);
                this.subCategories[baseCategory.value].forEach(subCategory => {
                    const option = document.createElement("option");
                    option.setAttribute("value", subCategory);
                    option.textContent = subCategory;
                    subCategorySelect.appendChild(option);
                });
                subCategorySelect.removeAttribute("disabled");
                subCategorySelect.setAttribute("aria-disabled", "false");
            }

            function removeChildren(element) {
                while (element.hasChildNodes()) {
                    element.firstChild.remove();
                }
            }
        </script>
        <style>
            .categories-select-container {
                display: flex;
                flex-direction: row;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <form id="CreateIssueForm" method="post" action="<spring:url value="/new"/>">
            <h1>Create A New Issue</h1>
            <div class="input-container">
                <input type="text" id="titleInput" name="title" minlength="5" maxlength="100" autofocus placeholder=" "
                       required aria-required="true" aria-label="Issue title">
                <label for="titleInput">Title</label>
            </div>
            <div class="form-description-wrapper input-container">
                <textarea
                        id="descriptionInput"
                        name="description"
                        maxlength="1000"
                        minlength="10"
                        required
                        aria-required="true"
                        aria-label="Issue description"
                        placeholder=" "
                >

                </textarea>
                <label for="descriptionInput">Leave a description</label>
            </div>

            <div class="categories-select-container">
                <div>
                    <label for="categorySelect">Select a category</label>
                    <select
                            id="categorySelect"
                            name="category"
                            required aria-required="true"
                            aria-label="Issue category"
                    >
                        <option value="Network">Network</option>
                        <option value="Software">Software</option>
                        <option value="Hardware">Hardware</option>
                        <option value="Email">Email</option>
                        <option value="Account">Account</option>
                    </select>
                </div>

                <div>
                    <label for="categorySelect">Select a sub-category</label>
                    <select
                            id="subcategorySelect"
                            name="subcategory"
                            required
                            aria-required="true"
                            aria-label="Issue category"
                            disabled
                            aria-disabled="true"
                    >

                    </select>
                </div>
            </div>


            <button type="submit">Submit</button>
        </form>
    </jsp:body>
</app:Layout>
<%--
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


</form>--%>
