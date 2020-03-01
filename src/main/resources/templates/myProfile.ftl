<#import "parts/common.ftl" as c>

<@c.page "Профиль">
    <#include "parts/navbar.ftl">
    <div class="row">
        <div class="col-md-2">

        </div>
        <div class="container my-3 col-md-10">
            <div class="row">
                <div class="col-md-3 card" style="width:300px">
                    <div class="card-body">
                        <h4 class="card-title text-center">${currentUser.name} ${currentUser.surname}</h4>
                        <svg width="90%" height="90%" viewBox="0 0 42 42" class="donut">
                            <circle class="donut-hole" cx="21" cy="21" r="15.91549430918954" fill="#fff"></circle>
                            <circle class="donut-ring" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#d2d3d4" stroke-width="3"></circle>
                            <!-- unused 10% -->
                            <g class="chart-text">
                                <text x="50%" y="50%" class="chart-number">
                                    ${currentUser.points}
                                </text>
                                <text x="50%" y="50%" class="chart-label">
                                    очков
                                </text>
                            </g>
                        </svg>
                    </div>
                </div>
                <div class="col-md-3 bg-light text-dark border border-dark">
                    <b>Логин:</b> ${currentUser.username}<br>
                    <b>Email:</b> ${currentUser.email}<br><br>
                    <#if isAdmin>
                        <i>Вы являетесь администратором сайта. Вам доступно управление
                        материалами для изучения языков программирования. Перейдите в раздел
                        <a href="/courses">курсы</a> чтобы внести правки в материалы</i><br><br>
                        <p class="border well p-1">На данный момент задания содержат: <span style="font-size: 20px">${topicNum}</span> из <span style="font-size: 20px">${allTopics}</span> теоретических глав</p>
                        <#if topicNum != allTopics>
                            <h5 class="text-danger">Дополните заданиями следующие разделы:</h5>
                            <ol>
                                <#list topicsWithout as wth>
                                    <li><a href="courses/topics/${wth.id}" class="hyperlink">${wth.name} (${wth.language.name})</a></li>
                                </#list>
                            </ol>
                        </#if>
                        <p class="border well p-1">На данный момент <span style="font-size: 20px">${notEmptyLangs}</span> из <span style="font-size: 20px">${langNum}</span> содержат теоретический материал</p>
                        <#if notEmptyLangs != langNum>
                            <h5 class="text-danger">Заполните материалами следующие курсы языков:</h5>
                            <ol>
                                <#list langsWithout as wth>
                                    <li><a href="courses/${wth.id}" class="hyperlink">${wth.name}</a></li>
                                </#list>
                            </ol>
                        </#if>
                    </#if>
                </div>
            <div class="col-md-4 bg-warning border border-dark">
                <h2 class="text-center">Личная статистика</h2>
                <b>Текущее звание:</b> <span class="text-info" style="font-size: 24px">${currentUser.getAchievement()}</span><br>
                <b>Начато курсов: <span style="font-size: 20px">${startedLangs}</span>/${langNum}</b><br>
                <b>Завершено курсов: <span class="text-success" style="font-size: 20px">${currentUser.getTotalFinishedCourses(userLangs)}</span>/${langNum}</b> <br>
                <b>Начато тестов: <span style="font-size: 20px">${currentUser.getStartedTopics()}</span>/${topicNum}</b><br>
                <b>Успешно пройдено тестов: <span class="text-success" style="font-size: 20px">${currentUser.getFinishedTopics()}</span>/${topicNum}</b> <br>
                <b>Общее количество ошибок: <span class="text-danger" style="font-size: 20px">${currentUser.getTotalErrors()}</span></b><br>
            </div>
            </div>
            <div class="row">
                <div class="col-md-10 my-2 bg-success">
                    <h2 class="text-center">Изучаемые языки:</h2>
                        <#list userLangs as lan>
                            <svg width="40%" height="40%" viewBox="0 0 42 42" class="donut">
                                <circle class="donut-hole" cx="21" cy="21" r="15.91549430918954" fill="#fff"></circle>
                                <circle class="donut-ring" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#d2d3d4" stroke-width="3"></circle>
                                <circle class="donut-segment" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#FFBE18" stroke-width="3" stroke-dasharray="${currentUser.getLangProgress(lan)} ${100 - currentUser.getLangProgress(lan)}" stroke-dashoffset="25"></circle>
                                <g class="chart-text">
                                    <text x="50%" y="50%" style="font-size: 0.35em" class="chart-number">
                                        <a href="courses/${lan.id}" class="hyperlink">
                                            ${lan.name}
                                        </a>
                                    </text>
                                </g>
                            </svg>
                        <#else> <span style="font-size: 24px">Пока не начат ни один курс</span>
                        </#list>
                </div>
            </div>
        </div>
    </div>
</@c.page>