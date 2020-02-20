<#import "parts/common.ftl" as c>

<@c.page "Список курсов">
    <#include "parts/navbar.ftl">
    <div class="row">
        <div class="col-md-2">
            <#if isAdmin>
                <#include "parts/coursesMenu.ftl">
            </#if>
        </div>
        <div class="container col-md-10">
            <h1 class="text-center">Доступные языки программирования</h1>
            <div class="card-columns">
                <#list languages as language>
                    <div class="card my-2" style="width:290px">
                            <#if language.image??>
                                <img src="/img/${language.image}" height="250px" width="200px" class="card-img-top">
                            </#if>
                        <div class="card-body">
                            <a href="courses/${language.id}" class="hyperlink">
                                <h4 class="card-title text-center">${language.name}</h4>
                            </a>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>

</@c.page>