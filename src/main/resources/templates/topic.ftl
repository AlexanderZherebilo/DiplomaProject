<#import "parts/common.ftl" as c>
<@c.page "${topic.name}">
    <#include "parts/navbar.ftl">
<div class="row">
    <div class="col-md-2">
        <#if isAdmin>
            <#include "parts/quizMenu.ftl">
        </#if>
    </div>
    <div class="container">
        <h1 class="text-center">${topic.name}</h1>
        <div class="jumbotron">
            ${topic.theory}
        </div>
    </div>
</div>

</@c.page>