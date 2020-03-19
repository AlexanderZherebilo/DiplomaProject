<#import "parts/common.ftl" as c>

<@c.page "Параметры">
    <#include "parts/navbar.ftl">

    <div class="container"><br>
        <div class="row justify-content-center">
            <form class="col-6 jumbotron" action="/settings" onsubmit="return formValidation();" method="post">
                <h2 class="text-center">Настройка данных пользователя</h2>
                <label for="inputName" class="mb-2 mr-sm-2">Имя:</label>
                <input type="text" name="name" id="inputName" class="form-control" placeholder="Введите реальное имя" title="Используйте только русские символы" pattern="^[а-яА-Я]+$" value="${user.name}" required autofocus><br>
                <label for="inputName" class="mb-2 mr-sm-2">Фамилия:</label>
                <input type="text" name="surname" id="inputSurname" class="form-control" placeholder="Введите реальную фамилию" title="Используйте только русские символы" pattern="^[а-яА-Я]+$" value="${user.surname}" required><br>
                <label for="inputLogin" class="mb-2 mr-sm-2">Логин:</label>
                <input type="text" name="username" id="inputLogin" class="form-control" placeholder="Логин" title="Используйте только латинские символы и цифры, также символ '_'" minlength="7" pattern="^[a-zA-Z0-9_]+$" ${user.username} required><br>
                <label for="inputPassword" class="mb-2 mr-sm-2">Пароль:</label>
                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Пароль" title="Используйте только латинские символы и цифры, также символ '_'" minlength="8" pattern="^[a-zA-Z0-9_]+$" value="" required><br>
                <input type="password" id="repeatPassword" class="form-control" placeholder="Повторите пароль" title="Используйте только латинские символы и цифры, также символ '_'" minlength="7" pattern="^[a-zA-Z0-9_]+$" value="" required><br>
                <label for="inputEmail" class="mb-2 mr-sm-2">Email:</label>
                <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Адрес эл. почты" value="${user.email}" required><br>
                <p class="border well p-1 text-danger"><b>Внимание! После внесения данных, для повторной авторизации потребуется вновь активировать аккаунт через указанную почту!</b></p>
                <span class="text-danger">${Error?if_exists}</span>
                <br>
                <input type="hidden" name="id" value="${user.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-lg btn-primary btn-block" type="submit">Сохранить</button><br>
            </form>
        </div>
    </div>

<script>
    function formValidation() {
        if ($("#inputPassword").val() !== $("#repeatPassword").val()) {
            $("#repeatPassword").after("<div class='text-danger lead'>Пароли не совпадают!</div>");
            return false;
        }
        else return true;
    }
</script>
</@c.page>