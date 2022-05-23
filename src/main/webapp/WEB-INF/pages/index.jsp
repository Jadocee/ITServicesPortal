<%--
  Created by IntelliJ IDEA.
  User: Jaydon
  Date: 10/05/2022
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags/layout" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<app:Layout title="Home">
    <jsp:attribute name="head">
        <script
                type="module"
                src="https://cdn.jsdelivr.net/npm/chart.js@3/dist/chart.min.js"
                defer
        ></script>
        <script type="module" defer>
            const ctx = document.querySelector("#performanceChart");
            // const config = {
            //     type: 'doughnut',
            //     data: data,
            // };
            const labels = ['Network', 'Software', 'Hardware', 'Account', 'Email']
            const data = {
                labels: labels,
                datasets: [{
                    label: 'Resolved',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                },
                    {
                        label: 'Unresolved',
                        data: [65, 59, 80, 81, 56, 55, 40],
                        fill: false,
                        borderColor: 'rgb(75, 192, 192)',
                        tension: 0.1
                    }]
            };
            const chart = new Chart(ctx, {
                type: 'line',
                data: data,
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });

        </script>
        <style>
            .index-container {
                width: 100%;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .index-section {
                display: flex;
                flex-direction: column;
                align-content: center;
                gap: 1rem;
                align-items: center;
                justify-content: flex-start;
                min-height: calc(100vh - 4rem);
                width: 100%;
            }

            #hero > p {
                font-size: 1.25rem;
                font-weight: 300;
                margin: 0;
                text-align: center;
                width: 40%;
                max-width: 40%;
            }

            #hero {
                justify-content: center !important;
            }

            #hero > h1 {
                font-weight: 700;
                line-height: 1.2;
                font-size: 3rem;
                margin: 0;
            }

            .index-section > h1 {
                font-weight: 600;
                line-height: 1.2;
                font-size: 2.5rem;
                margin: 0;
            }

            #hero > .hero__bottom-container > button[type="button"] {
                width: fit-content;
                padding: 5px 8px;
                font-size: 0.85rem;
                font-weight: 500;
                line-height: 20px;
                display: flex;
                flex-direction: row;
                align-items: center;
                gap: 8px;
            }

            .chart-container {
                width: 40%;
                height: 100%;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <div class="index-container">
            <section id="hero" class="index-section">
                <h1>Welcome to IT Services Portal</h1>
                <p>
                    Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                    Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit
                    amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.
                </p>
                <div class="hero__bottom-container">
                    <button
                            type="button"
                            onclick="document.querySelector('#statistics').scrollIntoView({behavior: 'smooth', block: 'end', inline: 'nearest'})"
                    >
                        <span class="material-symbols-rounded">monitoring</span>View our stats
                    </button>
                </div>
            </section>

            <section id="statistics" class="index-section">
                <h1>Our Performance</h1>
                <div class="chart-container">
                    <canvas
                            id="performanceChart"
                            width="400"
                            height="400"
                            style="margin-bottom: auto; margin-top: auto;"
                    ></canvas>
                </div>
            </section>
        </div>
    </jsp:body>
</app:Layout>



