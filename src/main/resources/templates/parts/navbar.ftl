<#include "security.ftl">
<#import "common.ftl" as c>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">SL</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Домой</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/courses">Курсы</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="">Пользователи</a>
            </li>
            </#if>
            <li class="nav-item">
                <a class="nav-link" href="">Рейтинги</a>
            </li>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="">Настройки аккаунта</a>
            </li>
            </#if>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="">Мой профиль</a>
            </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3">${name}</div>
        <@c.logout />
    </div>
</nav>