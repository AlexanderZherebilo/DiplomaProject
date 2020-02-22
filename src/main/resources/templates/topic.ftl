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
    <div class="container">
        <div class="form-group my-3">
            <h2 class="text-center">Задание для закрепления</h2><hr>
            <form method="post" class="border my-2">
                <#list topic.quiz as quest>
                    <div class="form-group">
                        <label class="col-form-label mx-3">${quest.problem}</label>
                        <div class="mx-4">
                            <#if quest.hasOptions()>
                                <select name="answers" class="form-control col-sm-4" required>
                                    <option value="" selected>Выберите правильный вариант</option>
                                    <#list quest.options as opt>
                                    <option value="${opt.name}">${opt.name}</option>
                                        <!--<div class="radio">
                                            <label class="radio">
                                                <input type="radio" name="question${quest.id}" value="${opt.name}">
                                                ${opt.name}
                                            </label>
                                        </div>
                                        <input type="hidden" name="answers" value="${opt.name}">
                                        -->
                                    </#list>
                                </select>
                            </#if>
                            <#if !quest.hasOptions()>
                                <div class="col-sm-4">
                                    <input type="text" name="answers" class="form-control" required/>
                                </div>
                            </#if>
                        </div>
                    </div>
                <hr>
                </#list>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-success mx-3">Проверить</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@c.page>