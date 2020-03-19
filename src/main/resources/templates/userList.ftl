<#import "parts/common.ftl" as c>

<@c.page "Список пользователей">
    <#include "parts/navbar.ftl">
    <div class="form-row m-3">
        <div class="form-group col-md-4 right">
            <form method="get" action="/userList" class="form-inline">
                <input type="text" name="nickname" class="form-control" placeholder="Поиск по логину">
                <button type="submit" class="btn btn-primary ml-2">Найти</button>
            </form>
        </div>
    </div>
    <div class="container">
        <h1 class="text-center">Пользователи приложения</h1>
        <div class="card-columns">
            <#list userList as user>
                <div class="card my-2" style="width:290px">
                    <a href="/userProfile/${user.id}" class="hyperlink">
                        <h3 class="card-title text-center my-2">${user.username}</h3>
                    </a><hr>
                    <div class="card-body" style="font-size: 18px">
                        <p class="<#if user.isAdmin()>text-danger<#else>text-info</#if>">${user.getAchievement()} (${user.points} очков)</p>
                        <p>Изучено тем: ${user.getFinishedTopics()}</p>
                        <p>Любимый язык программирования: <b>${user.getFavouriteLanguage()}</b></p>
                    </div>
                    <div class="card-footer">
                        <a href=mailto:${user.email}>${user.email}</a>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@c.page>