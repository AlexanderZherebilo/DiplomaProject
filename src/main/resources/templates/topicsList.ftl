<#import "parts/common.ftl" as c>
<@c.page "${language.name}">
    <#include "parts/navbar.ftl">
<div class="row">
    <div class="col-md-2">
        <#if isAdmin>
            <#include "parts/topicsMenu.ftl">
        </#if>
    </div>
    <div class="container">
        <div class="border bg-warning">
            <h1 class="text-center">${language.name}:</h1>
            <span style="font-size: 17px">${language.description}</span>
        </div>
        <h1 class="text-center">Содержание</h1>
        <div class="jumbotron">
            <ul style="list-style-type: none">
                <#list language.getTopics() as topic>
                    <h3 class="text-center my-2">
                        <li><a href="topics/${topic.id}" class="hyperlink">${topic.name}</a></li>
                    </h3>
                </#list>
            </ul>
        </div>
    </div>
</div>
</@c.page>