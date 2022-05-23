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
                src="https://cdn.jsdelivr.net/npm/chart.js@3/dist/chart.min.js"
                defer
        ></script>
        <script type="module" defer async>
            const doughtnutCanvas = document.querySelector("#unresolvedIssues");
            const lineCanvas = document.querySelector("#resolvedIssues");
            // let unresolvedData;
            // let resolvedData;
            // let stressRate;
            let lineChart;
            let donutChart;

            await fetch("./API/performance", {
                method: 'GET',
                headers: {'Accept': 'application/json'}
            })
                .then(response => response.json())
                .then(data => {
                    let resolvedData = JSON.parse(data.resolved);
                    let stressRate = parseFloat(data.stressRate);
                    let unresolvedData = JSON.parse(data.unresolved);
                    lineChart = new Chart(lineCanvas, {
                        type: 'line',
                        data: {
                            // labels: ['Resolved Issues in the past 7 days'],
                            datasets: [{
                                label: 'Resolved Issues in the past 7 days',
                                data: resolvedData,
                                borderColor: 'rgb(75, 192, 192)'
                            }]
                        },
                        options: {
                            legend: {
                                display: false
                            },
                            scales: {
                                yAxes: {
                                    ticks: {
                                        beginAtZero: true,
                                        callback: function (value) {
                                            if (Number.isInteger(value)) {
                                                return value;
                                            }
                                        },
                                        stepSize: 1
                                    }
                                }
                            }
                        }
                    });
                    donutChart = new Chart(doughtnutCanvas, {
                        type: 'doughnut',
                        data: {
                            labels: ['Network', 'Software', 'Hardware', 'Account', 'Email'],
                            datasets: [{
                                data: [
                                    'Network' in unresolvedData ? unresolvedData['Network'] : 0,
                                    'Software' in unresolvedData ? unresolvedData['Software'] : 0,
                                    'Hardware' in unresolvedData ? unresolvedData['Hardware'] : 0,
                                    'Account' in unresolvedData ? unresolvedData['Account'] : 0,
                                    'Email' in unresolvedData ? unresolvedData['Email'] : 0
                                ],
                                backgroundColor: [
                                    'rgb(255, 99, 132)',
                                    'rgb(54, 162, 235)',
                                    'rgb(255, 205, 86)',
                                    'rgb(152,251,152)',
                                    'rgb(255,182,193)'
                                ]
                            }],
                            hoverOffset: 4
                        }
                    });

                    document.querySelector("#stressRate").textContent = stressRate.toString();
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
                gap: 16px;
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
                min-width: 34%;
                height: 100%;
                border: 1px solid hsl(212 12% 21%);
                border-radius: 0.5rem;
                padding: 8px;
            }

            .stress-rate-container {
                width: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .stress-rate-cls {
                border: 1px solid hsl(212 12% 21%);
                border-radius: 0.5rem;
                padding: 8px;
                display: flex;
                flex-direction: column;
                align-content: center;
                justify-content: center;
                margin-bottom: 1rem;
                align-items: center;
                width: calc(68% + 2rem);
            }

            .stat-heading {
                margin: 0;
                letter-spacing: 0.05em;
            }

            #stressRate {
                font-size: 4rem;
                font-weight: 600;

            }

            .chart-container > h2 {
                margin: 0 0 0 8px;
                text-align: center;
                letter-spacing: 0.05em;
            }

            .statistics__inline-flex {
                display: flex;
                flex-direction: row;
                gap: 2rem;
                height: 100%;
                width: 100%;
                align-content: center;
                justify-content: center;
            }

            .statistics__center {
                display: flex;
                flex-direction: column;
                justify-content: center;
                height: 100%;
                width: 100%;
                margin: auto 0;
                align-items: center;
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
                <div class="statistics__center">
                    <div class="stress-rate-container">
                        <div class="stress-rate-cls">
                            <h2 class="stat-heading">Stress Rate</h2>
                            <span id="stressRate">Calculating...</span>
                        </div>
                    </div>
                    <div class="statistics__inline-flex">
                        <div class="chart-container">
                            <h2 class="stat-heading">How were performing</h2>
                            <canvas
                                    id="resolvedIssues"
                                    width="400"
                                    height="400"
                            ></canvas>
                        </div>

                        <div class="chart-container">
                            <h2 class="stat-heading">What were working on</h2>
                            <canvas
                                    id="unresolvedIssues"
                                    width="400"
                                    height="400"
                            ></canvas>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </jsp:body>
</app:Layout>



