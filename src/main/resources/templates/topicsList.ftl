<#import "parts/common.ftl" as c>
<@c.page "${language.name}">
    <#include "parts/navbar.ftl">
<div class="col-md-2">
        <#if isAdmin>
            <#include "parts/topicsMenu.ftl">
        </#if>
</div>
<div class="container">
    ${language.description}
    <div class="jumbotron">
        <ul>
            <#list language.getTopics() as topic>
                <li>
                    <a href="topics/${topic.id}" class="hyperlink">
                        ${topic.name}
                    </a>
                </li>
            </#list>
        </ul>
    </div>
</div>
<footer class="row justify-content-center bg-dark" style="position: absolute; bottom: 0; width: 100%;">
    <p class="text-light">&copy; 2020 BSUIR, Zherebilo AV</p>
</footer>

</@c.page>