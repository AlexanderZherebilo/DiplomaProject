<#import "parts/common.ftl" as c>

<@c.page "Задание для темы">
    <#include "parts/navbar.ftl">
<div class="container">
    <h2 class="text-center">Список вопросов раздела "${topic.name}"</h2>
    <#if topic.hasQuestions()>
    <table class="table table-hover">
        <thead>
        <th>Вопрос</th>
        <th>Варианты ответов</th>
        <th>Правильный ответ</th>
        </thead>
        <tbody>
        <#list topic.quiz as question>
        <tr>
            <td>${question.problem}</td>
            <td>
                <#if question.hasOptions()>
                    <#list question.options as option>${option.name}<#sep><br></#list>
                </#if>
            </td>
            <td>${question.answer}</td>
        </tr>
        </#list>
        </tbody>
    </table>
    </#if>
    <a class="btn btn-primary my-3" data-toggle="collapse" href="#collapseQuiz" role="button" aria-expanded="false" aria-controls="collapseQuiz">Меню добавления вопроса</a>
    <div class="collapse <#if message??>show</#if>" id="collapseQuiz">
        <div class="form-group my-3">
            <form method="post" onsubmit="return checkAns();" class="border my-5">
                <h4 class="text-left mx-2">Добавить вопрос к заданию</h4>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label mx-3">Вопрос:</label>
                    <div class="col-sm-4">
                        <textarea type="text" name="problem" rows="6" class="form-control" required></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label mx-3">Тип вопроса:</label>
                    <div class="col-sm-4">
                        <select name="type" id="type" onchange="showMenu()" class="form-control">
                            <option value="without" id="without">Без вариантов ответов</option>
                            <option value="with" id="with">С вариантами ответов</option>
                        </select>
                    </div>
                </div>
                <div id="MenuNum" hidden>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label mx-3">Число вариантов:</label>
                        <div class="col-sm-4">
                            <input type="number" name="num" min="2" max="5" value="2" id="num" onchange="optionsInputs()" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label mx-3">Варианты ответов:</label>
                        <div class="form-group row">
                            <div class="col-sm-12" id="inplist">
                                <input type="text" name="options" class="form-control my-2 mx-3"/>
                                <input type="text" name="options" class="form-control my-2 mx-3"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label mx-3">Правильный ответ:</label>
                    <div class="col-sm-4">
                        <input type="text" name="answer" id="ans" class="form-control" required/>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary mx-3">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function showMenu() {
        if (document.getElementById("type").value == "with")
            document.getElementById("MenuNum").hidden = false;
        else document.getElementById("MenuNum").hidden = true;
    }

    function optionsInputs() {
        var num = document.getElementById("num").value;
        var exs = document.getElementsByName("options").length;
        var lst = document.getElementById("inplist");
        if (num >= exs) {
            for (var i=0; i < num - exs; i++) {
                el = document.createElement('input');
                el.type = "text";
                el.name = "options";
                el.setAttribute("class", "form-control my-2 mx-3");
                lst.appendChild(el);
            }
        } else {
            for (var i=0; i < exs - num; i++) {
                var size = document.getElementById("inplist").childNodes.length;
                var end = document.getElementById("inplist").childNodes.item(size - 1);
                lst.removeChild(end);
            }
        }
    }

    function checkAns() {
        var ans = document.getElementById("ans").value;
        var opts = document.getElementsByName("options");
        var type = document.getElementById("type").value;
        var res = true;
        for (var i=0; i< opts.length; i++) {
            var o = opts[i].value;
            if (o.localeCompare(ans) != 0 && type.localeCompare("with") == 0)
                res = false;
            else res = true;
        }
        if (res == false)
            alert("Среди указанных вариантов ответа нет правильного");
        return res;
    }
</script>
</@c.page>