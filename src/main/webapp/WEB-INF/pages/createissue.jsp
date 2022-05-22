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
                NETWORK: [
                    {CONNECTION: "Can't Connect"},
                    {SPEED: "Speed"},
                    {DROPOUTS: "Constant dropouts"}
                ],
                SOFTWARE: [
                    {SLOW: "Slow to load"},
                    {WONTLOAD: "Won't load at all"}
                ],
                HARDWARE: [
                    {POWER: "Computer won't turn on"},
                    {BLUESCREEN: "Computer \"Blue screens\""},
                    {DRIVE: "Disk Drive"},
                    {PERIPHERALS: "Peripherals"}
                ],
                EMAIL: [
                    {SEND: "Can't send"},
                    {RECEIVE: "Can't receive"},
                    {SPAM: "SPAM/Phishing"}
                ],
                ACCOUNT: [
                    {PASSRESET: "Password reset"},
                    {DETAILS: "Wrong details"}
                ]
            };

            window.onload = async () => {
                document.querySelector("#categorySelect").addEventListener("change", async (ev) => {
                    changeSubCategoryOptions(ev.currentTarget);
                }, false);
            }

            function changeSubCategoryOptions(baseCategory) {
                const subCategorySelect = document.querySelector("#subcategorySelect");
                while (subCategorySelect.hasChildNodes()) {
                    subCategorySelect.firstChild.remove();
                }
                const defaultOption = document.createElement("option");
                defaultOption.setAttribute("value", "");
                defaultOption.disabled = true;
                defaultOption.hidden = true;
                defaultOption.selected = true;
                defaultOption.textContent = "Please select";
                subCategorySelect.appendChild(defaultOption);
                subCategories[baseCategory.value].forEach((subCategory) => {
                    Object.entries(subCategory).forEach(([key, val]) => {
                        const option = document.createElement("option");
                        option.setAttribute("value", key);
                        option.textContent = val;
                        subCategorySelect.appendChild(option);
                    })
                });
                subCategorySelect.removeAttribute("disabled");
                subCategorySelect.setAttribute("aria-disabled", "false");
            }
        </script>
        <style>
            .categories-select-container {
                display: grid;
                grid-template-columns: 1fr 1fr;
                justify-items: center;
                align-items: center;
                gap: 2rem;
            }

            .categories-select-container > div {
                display: flex;
                flex-direction: column;
                width: 100%;
                justify-content: center;
                align-content: center;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <form
                id="CreateIssueForm"
                method="post"
                action="<spring:url value="/issues/new"/>"
                accept-charset="UTF-8"
                autocomplete="off"
                autocapitalize="sentences"
                spellcheck="true"
        >
            <div class="input-container">
                <input
                        type="text"
                        id="titleInput"
                        name="title"
                        minlength="5"
                        maxlength="100"
                        autofocus
                        placeholder=" "
                        required
                        aria-required="true"
                        aria-label="Issue title"
                        autocapitalize="sentences"
                        spellcheck="true"
                        lang="en"
                        autocomplete="off"

                >
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
                        placeholder="Leave a description"
                        autocapitalize="sentences"
                        spellcheck="true"
                        lang="en"
                        autocomplete="off"
                        rows="8"
                ></textarea>
                    <%--                <label for="descriptionInput">Leave a description</label>--%>
            </div>

            <div class="categories-select-container">
                <div>
                    <label for="categorySelect">Select a category</label>
                    <select
                            id="categorySelect"
                            name="category"
                            required
                            aria-required="true"
                            aria-label="Issue category"
                    >
                        <option value="" selected disabled hidden>Please select</option>
                        <option value="NETWORK">Network</option>
                        <option value="SOFTWARE">Software</option>
                        <option value="HARDWARE">Hardware</option>
                        <option value="EMAIL">Email</option>
                        <option value="ACCOUNT">Account</option>
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
            <button type="submit" class="form-btn lg">
                Submit<span class="material-symbols-rounded">send</span>
            </button>
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
