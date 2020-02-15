<#import "parts/common.ftl" as c>
<@c.page "Регистрация">
<header class="row justify-content-center bg-info">
    <h1>Регистрация пользователя Solo Learn</h1>
</header>
<div class="container"><br>
    <div class="row justify-content-center">
        <form class="col-6 jumbotron" action="/registration" onsubmit="return formValidation();" method="post">
            <h2 class="text-center">Введите данные пользователя</h2>
            <label for="inputLogin" class="mb-2 mr-sm-2">Логин:</label>
            <input type="text" name="username" id="inputLogin" class="form-control" placeholder="Логин" title="Используйте только латинские символы и цифры, также символ '_'" minlength="7" pattern="^[a-zA-Z0-9_]+$" required autofocus><br>
            <label for="inputPassword" class="mb-2 mr-sm-2">Пароль:</label>
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Пароль" title="Используйте только латинские символы и цифры, также символ '_'" minlength="8" pattern="^[a-zA-Z0-9_]+$" required><br>
            <input type="password" id="repeatPassword" class="form-control" placeholder="Повторите пароль" title="Используйте только латинские символы и цифры, также символ '_'" minlength="7" pattern="^[a-zA-Z0-9_]+$" required><br>
            <label for="inputEmail" class="mb-2 mr-sm-2">Email, к которому будет привязан аккаунт:</label>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Адрес эл. почты" required><br>
            <span class="text-danger">${Error?if_exists}</span>
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрировать</button><br>
        </form>
    </div>
</div>
<footer class="row justify-content-center bg-dark" style="position: relative; bottom: 0; width: 100%;">
    <p class="text-light">&copy; 2020 BSUIR, Zherebilo AV</p>
</footer>
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